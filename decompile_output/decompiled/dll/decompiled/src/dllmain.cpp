// ============================================================
//  dllmain.cpp — DllMain + GCC CRT başlatma
//  RVA: 0x1000 (.text başı)
//
//  Assembly analizi:
//    0x1000  lea rcx,[rip+0xbff9]  → CRT init tablosuna pointer
//    0x1007  jmp 0x8200            → __gcc_register_frame
//
//  DLL_PROCESS_ATTACH (fdwReason==1):
//    gs:[0x30] → TEB → Thread ID okuma
//    lock cmpxchg döngüsü → thread-safe tek seferlik init
//    0x86e8 çağrısı → InitializeCriticalSection
//    g_attachCount++ (0xbed4)
//
//  DLL_PROCESS_DETACH (fdwReason==0):
//    g_attachCount--
//    Sleep(1000) döngüsü → diğer thread'leri bekle
//    DeleteCriticalSection (0x86d8 çağrısı)
//
//  DLL_THREAD_ATTACH / DLL_THREAD_DETACH: stub (ret)
// ============================================================

#include "../include/globals.h"
#include <windows.h>
#include <synchapi.h>

// GCC CRT fonksiyonları (MinGW runtime)
extern "C" void __gcc_register_frame(void*);
extern "C" void _initterm(void**, void**);

// CRT init/fini tabloları (.CRT section — RVA 0x2830000)
extern "C" void* __CTOR_LIST__[];
extern "C" void* __DTOR_LIST__[];

// Kritik bölge (thread-safe init için — assembly'deki lock cmpxchg'nin hedefi)
static CRITICAL_SECTION g_cs;
static volatile LONG    g_csInitialized = 0;

BOOL WINAPI DllMain(HINSTANCE hinstDLL, DWORD fdwReason, LPVOID lpvReserved)
{
    switch (fdwReason)
    {
    // ── DLL_PROCESS_ATTACH ─────────────────────────────────
    // Assembly: test edx,edx → jne (fdwReason != 0 → başka dal)
    //           cmp eax,[attachCount] → zaten attach olduysa atla
    case DLL_PROCESS_ATTACH:
    {
        // Thread-safe tek seferlik init
        // lock cmpxchg qword ptr [rbx], rdi
        // rbx = &g_csLock, rdi = 1 (claim), r12 = 0 (beklenen)
        if (InterlockedCompareExchange(&g_csInitialized, 1, 0) == 0)
        {
            InitializeCriticalSection(&g_cs);

            // GCC CRT global constructor'ları çalıştır
            // 0x1212 → call 0x2760 → _initterm benzeri döngü
            _initterm(__CTOR_LIST__, __CTOR_LIST__ + 1);
        }
        else
        {
            // Başka thread init ediyor — tamamlanmasını bekle (Sleep 1000ms döngüsü)
            // "mov ecx, 0x3e8; call r13" → Sleep(1000)
            while (g_csInitialized != 2)
                Sleep(1000);
        }

        // cmp eax, 2 → init tamamlandı mı?
        // "add dword ptr [rip+0xbed4], 1"
        g_attachCount++;

        // Init tamamlandı işareti
        InterlockedExchange(&g_csInitialized, 2);
        break;
    }

    // ── DLL_PROCESS_DETACH ─────────────────────────────────
    // Assembly: test edx,edx → je (fdwReason == 0)
    //           sub eax,1 → attachCount--
    //           Sleep döngüsü
    case DLL_PROCESS_DETACH:
    {
        g_attachCount--;

        // Global destructor'ları çalıştır (GCC .fini tablosu)
        // 0x1212 → call 0x2760 → _fini benzeri
        if (lpvReserved == nullptr) // yalnızca FreeLibrary'de, process sonunda değil
        {
            _initterm(__DTOR_LIST__, __DTOR_LIST__ + 1);
        }

        if (g_csInitialized == 2)
        {
            DeleteCriticalSection(&g_cs);
            InterlockedExchange(&g_csInitialized, 0);
        }

        // FakeFly global referanslarını temizle
        if (g_flyObj && g_flyEnv)
        {
            g_flyEnv->DeleteGlobalRef(g_flyObj);
            g_flyObj = nullptr;
        }
        if (g_fakeFlyClass && g_flyEnv)
        {
            g_flyEnv->DeleteGlobalRef(g_fakeFlyClass);
            g_fakeFlyClass = nullptr;
        }
        break;
    }

    case DLL_THREAD_ATTACH:  break;   // stub — ret
    case DLL_THREAD_DETACH:  break;   // stub — ret
    }

    return TRUE;
}
