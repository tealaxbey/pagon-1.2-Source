#pragma once
// ============================================================
//  types.h — pagon_native.dll ortak veri yapıları
//  Kaynak: x64 assembly analizi (STRUCT_SIZE = 0x50408)
// ============================================================

#include <jni.h>
#include <windows.h>

// ── Boyut sabitleri (assembly'den doğrudan alındı) ──────────
#define MAX_MODULES   128       // 0x7F  — RegisterModule üst sınırı
#define MAX_VALUES     64       // 0x3F  — RegisterValue üst sınırı
#define NAME_SIZE     256       // 0x100 — her string alanı
#define STRUCT_SIZE   0x50408   // 327688 byte — bir ModuleEntry'nin toplam boyutu

// ── Her değer kaydı (+0x308 offset'inden itibaren) ──────────
// valueName : +0x000  (256 byte)
// valueData : +0x100  (256 byte)  — 4 adet 0x100'lük alan var
//             +0x200  (256 byte)  (extra1)
//             +0x300  (256 byte)  (extra2)
// Toplam value girdi boyutu = 4 * 256 = 0x400 = 1024 byte
// (imul rbp, rbp, 0x100 → her valueName için 0x100 adım)
struct ValueEntry {
    char valueName  [NAME_SIZE];   // +0x000
    char valueData  [NAME_SIZE];   // +0x100
    char valueExtra1[NAME_SIZE];   // +0x200
    char valueExtra2[NAME_SIZE];   // +0x300
};

// ── Modül kaydı (toplam = STRUCT_SIZE = 0x50408) ────────────
// +0x000 : name[256]
// +0x100 : category[256]
// +0x200 : description[256]
// +0x300 : enabled (int)
// +0x304 : valueCount (int)
// +0x308 : values[64] (her biri 1024 byte = 64 * 1024 = 0x10000)
// +0x40c08 geri kalan alan — enum listesi (int[]) 0x14102 adımlı
struct ModuleEntry {
    char       name       [NAME_SIZE];     // +0x000
    char       category   [NAME_SIZE];     // +0x100
    char       description[NAME_SIZE];     // +0x200
    int        enabled;                    // +0x300
    int        valueCount;                 // +0x304
    ValueEntry values[MAX_VALUES];         // +0x308  (64 * 1024 = 0x10000 byte)
    // Geri kalan: enum/slider değerleri (imul r9, 0x14102 ile erişiliyor)
    int        enumValues[0x14102];        // +0x40c08
};

// ── Decrypt key tablosu (.rdata offset 0x8227 — 64 byte) ────
// nativeDecrypt'te "lea r10, [rip+0x8212]" ile erişiliyor.
static const unsigned char DECRYPT_KEY_TABLE[64] = {
    0xF2, 0x6D, 0x91, 0x3E, 0xC0, 0xB7, 0x4A, 0x25,
    0xFE, 0x18, 0x63, 0xAC, 0xDF, 0x71, 0x09, 0xE4,
    0xB2, 0x56, 0x8F, 0x3A, 0xC7, 0xED, 0x14, 0xFB,
    0x80, 0x4C, 0x92, 0x2E, 0xF5, 0xBB, 0x67, 0xD1,
    0x0E, 0x85, 0x39, 0xFA, 0x43, 0x1E, 0xB8, 0x7C,
    0xE3, 0x50, 0xAF, 0x32, 0xCD, 0x68, 0xF7, 0x1B,
    0x94, 0x23, 0xDE, 0x75, 0x0A, 0xE1, 0xBC, 0x46,
    0x8D, 0x5B, 0x6E, 0x61, 0x74, 0x69, 0x76, 0x65,
};

// ── JNI vtable offset'leri (Windows x64 JVM ABI) ────────────
#define JNI_FindClass               0x030
#define JNI_NewGlobalRef            0x0A8
#define JNI_DeleteGlobalRef         0x0B8
#define JNI_GetMethodID             0x388  // (env* + 0x388 çağrısı)
#define JNI_NewStringUTF            0x538
#define JNI_GetStringUTFChars       0x548
#define JNI_ReleaseStringUTFChars   0x550
#define JNI_NewObjectArray          0x560
#define JNI_GetObjectArrayElement   0x568
#define JNI_SetObjectArrayElement   0x570
#define JNI_GetArrayLength          0x580
#define JNI_GetByteArrayElements    0x5C0
#define JNI_NewByteArray            0x5C0  // aynı slot, farklı kullanım
#define JNI_ReleaseByteArrayElements 0x600
#define JNI_CallVoidMethod          0x468
