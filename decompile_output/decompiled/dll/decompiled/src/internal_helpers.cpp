// ============================================================
//  internal_helpers.cpp
//  Export edilmeyen iç yardımcı fonksiyonlar
//
//  Bu fonksiyonlar .text bölgesinde export tablosunda yoktur
//  ama export edilen fonksiyonlardan çağrılırlar.
//  Assembly'de "call 0x35a6e87XX" gibi doğrudan adresle çağrılır.
//
//  Tespit edilen iç fonksiyonlar:
//    0x8750 → jstring_strcmp   (modül/değer adı karşılaştırma)
//    0x8770 → jstring_strncpy  (güvenli kopyalama, max 255)
//    0x8200 → __gcc_register_frame / CRT frame kayıt
//    0x8220 → __do_global_dtors_aux
//    0x8300 → __do_global_ctors_aux
//    0x86d8 → __mingw_raise_matherr (hata yönetimi)
//    0x86e8 → InitializeCriticalSection wrapper
// ============================================================

#include "../include/globals.h"
#include <cstring>
#include <cstdlib>

// ── 0x8750: module_strcmp ────────────────────────────────────
// Assembly (RVA 0x8750, file 0x7D50):
//   FF 25 1A 6B 82 02  → jmp [rip+0x2826b1a]
//   Bu bir import thunk — msvcrt!strcmp'e yönlendirilmiş
//
// rcx = const char* (ModuleEntry.name veya ValueEntry.valueName)
// rdx = const char* (JNI GetStringUTFChars sonucu)
// dönüş: 0=eşit, <0 veya >0=farklı (strcmp semantiği)
int module_strcmp(const char* entryName, const char* queryName)
{
    // msvcrt!strcmp wrapper — import thunk
    return strcmp(entryName, queryName);
}

// ── 0x8770: safe_strncpy ─────────────────────────────────────
// Assembly (RVA 0x8770, file 0x7D70):
//   FF 25 1A 6B 82 02  → jmp [rip+0x2826b1a]
//   Bu da bir import thunk — msvcrt!strncpy'e yönlendirilmiş
//
// rcx = char* dst
// rdx = const char* src (JNI string)
// r8d = int maxLen (her zaman 0xFF = 255)
void safe_strncpy(char* dst, const char* src, int maxLen)
{
    // msvcrt!strncpy wrapper
    strncpy(dst, src, (size_t)maxLen);
    // null terminator garantisi (strncpy null eklemeyebilir)
    dst[maxLen] = '\0';
}

// ── 0x8200: __gcc_register_frame ────────────────────────────
// Assembly (RVA 0x8200, file 0x7800):
//   48 85 C9           → test rcx,rcx
//   74 12              → je ret (null ise çık)
//   66 0F EF C0        → pxor xmm0,xmm0 (temizle)
//   31 C0              → xor eax,eax
//   48 C7 41 10 00..   → mov qword ptr [rcx+0x10], 0
//   0F 11 01           → movups [rcx], xmm0
//   C3                 → ret
//
// GCC exception frame kayıt tablosu sıfırlama fonksiyonu.
// DllMain DLL_PROCESS_ATTACH'ta çağrılır.
void __gcc_register_frame_init(void* frameTable)
{
    if (frameTable == nullptr) return;

    // xmm0 sıfırla, ilk 24 byte'ı temizle
    memset(frameTable, 0, 24);
}

// ── 0x8220: __do_global_dtors_aux / frame walker ─────────────
// Assembly (RVA 0x8220, file 0x7820):
//   55 41 54 57 56 53  → push rbp,r12,rdi,rsi,rbx
//   48 83 EC 20        → sub rsp, 0x20
//   48 8D 6C 24 20     → lea rbp,[rsp+0x20]
//   48 89 CB           → mov rbx,rcx  (frame pointer)
//   48 89 D7           → mov rdi,rdx  (callback)
//   48 85 C9           → test rcx,rcx
//   0F 84 B2 00 00 00  → je (null → çık)
//   B9 08 00 00 00     → mov ecx, 8
//   E8 A8 04 00 00     → call malloc(8) veya benzeri
//   48 83 3B 00        → cmp qword ptr [rbx], 0
//   74 6A              → je (boş liste)
//   ...
//
// Bu fonksiyon GCC exception frame listesini dolaşır.
// _initterm / __do_global_dtors_aux benzeri GCC CRT iç fonksiyonu.
// pagon_native kodundan doğrudan çağrılmaz — CRT tarafından.

// ── 0x8300: __do_global_ctors_aux ───────────────────────────
// Assembly (RVA 0x8300, file 0x7900):
//   55 57 56 53        → push rbp,rdi,rsi,rbx
//   48 83 EC 28        → sub rsp, 0x28
//   48 8D 6C 24 20     → lea rbp,[rsp+0x20]
//   48 89 CF           → mov rdi,rcx  (ctor listesi başlangıcı)
//   B9 08 00 00 00     → mov ecx, 8
//   E8 D6 03 00 00     → call (alloc)
//   48 8B 37           → mov rsi,[rdi]  (ilk ctor pointer)
//   66 0F EF C0        → pxor xmm0,xmm0
//   48 8B 5F 08        → mov rbx,[rdi+8]
//   ...
//
// Global constructor çalıştırma döngüsü (GCC CRT iç fonksiyonu).

// ── 0x86d8: __mingw_raise_matherr ───────────────────────────
// assembly: mov ecx, 0x1f; call 0x86d8
// 0x1f = 31 → SIGFPE / matematik hatası sinyali
// MinGW runtime hata işleyicisi. Kullanıcı kodundan çağrılmaz.

// ── 0x86e8: EnterCriticalSection / InitializeCriticalSection wrapper
// assembly: call 0x86e8
// Bu fonksiyon g_cs kritik bölgesini initialize eder
// KERNEL32!InitializeCriticalSection'a import thunk üzerinden ulaşır.

// ── CRT TLS (Thread Local Storage) ─────────────────────────
// .tls section (RVA 0x2831000):
// TlsGetValue / TlsSetValue çağrıları KERNEL32'den import edilmiş.
// GCC runtime thread-local değişkenleri için kullanılıyor.
// pagon_native.dll'in kendi thread-local değişkeni yok —
// sadece GCC CRT'nin ihtiyacı var.

// ── Pseudo-relocation engine (MinGW özel) ────────────────────
// String tablosunda görülen mesajlar:
//   "Address %p has no image-section"
//   "VirtualQuery failed for %d bytes at address %p"
//   "VirtualProtect failed with code 0x%x"
//   "Unknown pseudo relocation protocol version %d."
//   "Unknown pseudo relocation bit size %d."
//   "%d bit pseudo relocation at %p out of range..."
//
// Bunlar MinGW'nin __do_pseudo_reloc fonksiyonundan geliyor.
// DLL yüklendiğinde .reloc section'ı işler.
// KERNEL32!VirtualQuery ve KERNEL32!VirtualProtect kullanır.
// Tamamen GCC runtime kodu — pagon_native mantığıyla ilgisi yok.
