#pragma once
// ============================================================
//  globals.h — pagon_native.dll global değişken bildirimleri
//  Tüm adresler RIP-relative erişimden hesaplandı.
// ============================================================

#include "types.h"

// ── NativeAPI global deposu (.data bölgesi) ──────────────────
// g_modules   : tüm modül kayıtlarının dizisi
// g_moduleCount: şu anda kayıtlı modül sayısı
extern ModuleEntry g_modules[MAX_MODULES];
extern int         g_moduleCount;

// ── DllMain referans sayacı ───────────────────────────────────
// DLL_PROCESS_ATTACH her çağrıldığında artar.
// "add dword ptr [rip + 0xbed4], 1" → bu değişken
extern int g_attachCount;

// ── FakeFlyNative state değişkenleri (.bss/.data) ────────────
// Hepsi RIP-relative "mov qword/dword ptr [rip + X]" ile erişiliyor.

// g_flyEnv  : JNIEnv* — nativeOnEnable'da kaydedilir
// RIP+0x282b5ba (nativeOnEnable'daki "cmp qword ptr [rip+...]" 0 check)
extern JNIEnv*   g_flyEnv;

// g_flyObj  : Java FakeFly nesnesi (NewGlobalRef ile tutulur)
// RIP+0x282b563
extern jobject   g_flyObj;

// g_flyState : 0=kapalı, 1=aktif, 2=sıfırlanıyor
// RIP+0x282b559 (nativeOnEnable'da "mov dword ptr [...], 2")
extern int       g_flyState;

// g_flyTickCount : her onGround tick'te artar, eşik=2 geçince sıfırlanır
// RIP+0x282b567 (nativeOnEnable'da "mov dword ptr [...], 0")
// RIP+0x282b30e / 0x282b305 (nativeHandleTick içinde okuma/yazma)
extern int       g_flyTickCount;

// g_flyInitState : fakeFlyInit tamamlanma durumu
// 0=başlatılmadı, 1=başlatılıyor, 2=tamam
// (assembly: cmp eax,2 / cmp eax,1 / cmp eax,0 zinciri)
extern int       g_flyInitState;

// ── FakeFlyNative Java sınıf/metod referansları ───────────────
// Hepsi nativeOnEnable içinde GetMethodID (vtable +0x388) ile doldurulur.
// Sınıf: "org/itsmahir/pagon/impl/features/modules/movement/FakeFly"
extern jclass    g_fakeFlyClass;
extern jmethodID g_cbCancelTravel;   // "cbCancelTravel" "()V"
extern jmethodID g_cbCancelJump;     // "cbCancelJump"   "()V"
extern jmethodID g_cbDisarmElytra;   // "cbDisarmElytra" "()V"
extern jmethodID g_cbSwapElytra;     // "cbSwapElytra"   "()V"
extern jmethodID g_cbStartFlying;    // "cbStartFlying"  "()V"
extern jmethodID g_cbFirework;       // "cbFirework"     "()V"
extern jmethodID g_cbMcFirework;     // "cbMcFirework"   "()V"
extern jmethodID g_cbJump;           // "cbJump"         "()V"
extern jmethodID g_cbRotate;         // "cbRotate"       "()V"

// ── Travel state sayaçları (nativeHandleTravel içinde) ────────
// RIP+0x282b1d0 / 0x282b1c7  → travelTickCount (artıyor, eşik=2)
extern int g_travelTickCount;
// RIP+0x282b0fe / 0x282b0f5  → jumpTickCount
extern int g_jumpTickCount;
