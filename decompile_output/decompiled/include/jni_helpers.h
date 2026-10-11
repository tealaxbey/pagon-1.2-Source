#pragma once
// ============================================================
//  jni_helpers.h — JNI vtable yardımcı makroları
//  Kaynak: pagon_native.dll import tablosu + vtable offset analizi
//
//  Windows x64 JNI ABI'sinde JNIEnv* bir vtable pointer'a işaret eder.
//  Her JNI fonksiyonu sabit bir offset'tedir.
//  Assembly'de "call qword ptr [rax + 0xXXX]" şeklinde görülür.
// ============================================================

#include <jni.h>

// ── CallVoidMethod yardımcısı ────────────────────────────────
// assembly: call vtable+0x468 → CallVoidMethod(env, obj, mid)
static inline void jni_callVoid(JNIEnv* env, jobject obj, jmethodID mid)
{
    if (env != nullptr && obj != nullptr && mid != nullptr)
        env->CallVoidMethod(obj, mid);
}

// ── CallVoidMethod + tek int argüman ────────────────────────
// bazı callback'lerde sideSpeed int parametre geçiriliyor
static inline void jni_callVoidI(JNIEnv* env, jobject obj, jmethodID mid, jint arg)
{
    if (env != nullptr && obj != nullptr && mid != nullptr)
        env->CallVoidMethod(obj, mid, arg);
}

// ── NewStringUTF güvenli sarmal ──────────────────────────────
// assembly: call vtable+0x538 → NewStringUTF
static inline jstring jni_newString(JNIEnv* env, const char* str)
{
    if (str == nullptr) str = "(null)";
    return env->NewStringUTF(str);
}

// ── GetStringUTFChars / ReleaseStringUTFChars kısaltmaları ──
static inline const char* jni_getStr(JNIEnv* env, jstring js)
{
    return env->GetStringUTFChars(js, nullptr);
}

static inline void jni_releaseStr(JNIEnv* env, jstring js, const char* cs)
{
    env->ReleaseStringUTFChars(js, cs);
}
