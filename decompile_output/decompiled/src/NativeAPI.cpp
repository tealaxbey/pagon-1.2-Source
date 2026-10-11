// ============================================================
//  NativeAPI.cpp
//  Sınıf: org.itsmahir.pagon.nativep.NativeAPI
//  Export: 12 JNI fonksiyon
//
//  Tüm kod x64 assembly'den pseudo-C++ olarak türetilmiştir.
//  Her fonksiyonun üstünde ilgili RVA ve kritik instruction'lar
//  yorum olarak belirtilmiştir.
// ============================================================

#include "../include/globals.h"
#include <cstring>

// ── Yardımcı: strncpy + null-terminate ──────────────────────
// Assembly: call 0x35a6e8770
// rcx=dst, rdx=src, r8d=maxLen(0xFF)
static void safe_strncpy(char* dst, const char* src, int maxLen)
{
    strncpy(dst, src, maxLen);
    dst[maxLen] = '\0';
}

// ── Yardımcı: strcmp benzeri (modül adı vs JNI string) ──────
// Assembly: call 0x35a6e8750
// rcx=ModuleEntry*, rdx=const char* (JNI'den GetStringUTFChars)
// Dönüş: 0=eşleşti, !=0=farklı
static int module_name_cmp(const ModuleEntry* entry, const char* name)
{
    return strcmp(entry->name, name);
}

// ── Yardımcı: value adı karşılaştırması ─────────────────────
static int value_name_cmp(const ValueEntry* entry, const char* name)
{
    return strcmp(entry->valueName, name);
}


// ============================================================
//  1. nativeRegisterModule
//  RVA: 0x1390
//
//  Assembly özeti:
//    GetStringUTFChars x3  (vtable +0x548)
//    movsxd rdi, [g_moduleCount]
//    cmp edi, 0x7f → kapasite kontrolü
//    test edi,edi → ilk modül mü?
//    jg → mevcut modül arama döngüsü
//    imul rdi, rdi, 0x50408 → slot offset hesabı
//    call safe_strncpy x3  (name, category, description)
//    mov qword ptr [r14+rdi+0x300], 0 → enabled=0
//    ReleaseStringUTFChars x3 (vtable +0x550)
// ============================================================
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeRegisterModule(
    JNIEnv*  env,
    jobject  /*obj*/,
    jstring  jName,
    jstring  jCategory,
    jstring  jDescription)
{
    const char* name        = env->GetStringUTFChars(jName,        nullptr);
    const char* category    = env->GetStringUTFChars(jCategory,    nullptr);
    const char* description = env->GetStringUTFChars(jDescription, nullptr);

    // cmp edi, 0x7f — kapasite üst sınırı
    if (g_moduleCount <= MAX_MODULES)
    {
        // test edi,edi → jg mevcut arama: g_moduleCount > 0 ise ara
        int slot = -1;
        if (g_moduleCount > 0)
        {
            for (int i = 0; i < g_moduleCount; i++)
            {
                // call 0x8750 → strcmp
                if (module_name_cmp(&g_modules[i], name) == 0)
                {
                    slot = i;
                    break;
                }
            }
        }

        if (slot == -1)
        {
            // Yeni slot: lea eax,[rdi+1] → g_moduleCount+1
            slot = g_moduleCount;
            // mov dword ptr [rip+0xbbe2], eax → g_moduleCount++
            g_moduleCount = slot + 1;
        }

        // imul rdi, rdi, 0x50408 → slot * STRUCT_SIZE
        // lea rcx,[r14+rdi]      → &g_modules[slot].name
        safe_strncpy(g_modules[slot].name,        name,        0xFF);
        // lea rcx,[r14+rdi+0x100] → &g_modules[slot].category
        safe_strncpy(g_modules[slot].category,    category,    0xFF);
        // lea rcx,[r14+rdi+0x200] → &g_modules[slot].description
        safe_strncpy(g_modules[slot].description, description, 0xFF);
        // mov qword ptr [r14+rdi+0x300], 0 → enabled = 0
        g_modules[slot].enabled    = 0;
        // valueCount sıfırlanmıyor (mevcut slot güncellemesi için)
    }

    env->ReleaseStringUTFChars(jName,        name);
    env->ReleaseStringUTFChars(jCategory,    category);
    env->ReleaseStringUTFChars(jDescription, description);
}


// ============================================================
//  2. nativeSetModuleEnabled
//  RVA: 0x1500
//
//  Assembly özeti:
//    rcx=env, r8=jName(string), r9d=enabled(boolean)
//    GetStringUTFChars (vtable +0x548)
//    mov r15d,[g_moduleCount]
//    test r15d,r15d → jle (sıfır/negatifse atla)
//    döngü: add ebx,1; add r14,0x50408; cmp ebx,r15d
//    call 0x8750 → strcmp
//    jne döngü → farklıysa devam
//    imul rbx, rbx, 0x50408
//    test r12b,r12b → setne al → enabled ? 1 : 0
//    mov [r13+rbx+0x300], eax → enabled yaz
//    ReleaseStringUTFChars (vtable +0x550) + jmp rax
// ============================================================
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeSetModuleEnabled(
    JNIEnv*  env,
    jobject  /*obj*/,
    jstring  jName,
    jboolean enabled)
{
    const char* name = env->GetStringUTFChars(jName, nullptr);

    // test r15d,r15d → jle: modül yoksa atla
    for (int i = 0; i < g_moduleCount; i++)
    {
        if (module_name_cmp(&g_modules[i], name) == 0)
        {
            // test r12b,r12b → setne al
            // enabled != 0 → 1, aksi → 0
            g_modules[i].enabled = (enabled != 0) ? 1 : 0;
            break;
        }
    }

    // jmp rax → tail-call ReleaseStringUTFChars
    env->ReleaseStringUTFChars(jName, name);
}


// ============================================================
//  3. nativeIsModuleEnabled
//  RVA: 0x15b0
//
//  Assembly özeti:
//    r14=jName, r13=env
//    GetStringUTFChars → rdi = char*
//    mov ebp,[g_moduleCount]
//    döngü strcmp, bulunamazsa → 0x1650 (not found path)
//    not found: ReleaseStringUTFChars → xor eax,eax → ret (false)
//    bulundu: imul rbx, rbx, 0x50408
//    call ReleaseStringUTFChars
//    mov eax,[r12+rbx+0x300] → enabled oku
//    test eax,eax → setne al → ret
// ============================================================
extern "C" JNIEXPORT jboolean JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeIsModuleEnabled(
    JNIEnv*  env,
    jobject  /*obj*/,
    jstring  jName)
{
    const char* name = env->GetStringUTFChars(jName, nullptr);

    // test ebp,ebp → jle 0x1650: modül yoksa false
    for (int i = 0; i < g_moduleCount; i++)
    {
        if (module_name_cmp(&g_modules[i], name) == 0)
        {
            // mov eax,[r12+rbx+0x300] → enabled oku
            int enabled = g_modules[i].enabled;
            env->ReleaseStringUTFChars(jName, name);
            // test eax,eax → setne al
            return (enabled != 0) ? JNI_TRUE : JNI_FALSE;
        }
    }

    // 0x1650: bulunamadı
    env->ReleaseStringUTFChars(jName, name);
    return JNI_FALSE;   // xor eax,eax
}


// ============================================================
//  4. nativeRegisterValue
//  RVA: 0x1680
//
//  Assembly özeti:
//    5 parametre: r8=moduleName, r9=valueName,
//                 stack+0xe0=defaultValue, +0xe8=extra1, +0xf0=extra2
//    GetStringUTFChars x4 (vtable +0x548)
//    mov ebp,[g_moduleCount] → döngü ile modül ara
//    cmp r15d, 0x3f → değer kapasitesi kontrolü (MAX_VALUES=64)
//    test r15d,r15d → jle → valueCount=0, direkt ekle
//    değer döngüsü: lea r14,[r13+r10+0x308]
//                   add rbp,0x100 → her valueName 256 byte
//    match: lea ecx,[r15+1] → valueCount++
//    imul rax, r9, 0x50408 → modül offset
//    mov [r13+rax+0x304], ecx → valueCount yaz
//    safe_strncpy x4 (valueName, defaultValue, extra1, extra2)
//    ReleaseStringUTFChars x4
// ============================================================
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeRegisterValue(
    JNIEnv*  env,
    jobject  /*obj*/,
    jstring  jModuleName,
    jstring  jValueName,
    jstring  jDefaultValue,
    jstring  jExtra1,
    jstring  jExtra2)
{
    const char* modName  = env->GetStringUTFChars(jModuleName,   nullptr);
    const char* valName  = env->GetStringUTFChars(jValueName,    nullptr);
    const char* defValue = env->GetStringUTFChars(jDefaultValue, nullptr);
    const char* extra1   = env->GetStringUTFChars(jExtra1,       nullptr);
    const char* extra2   = env->GetStringUTFChars(jExtra2,       nullptr);

    for (int i = 0; i < g_moduleCount; i++)
    {
        if (module_name_cmp(&g_modules[i], modName) == 0)
        {
            ModuleEntry& mod = g_modules[i];

            // cmp r15d, 0x3f → değer kapasitesi üst sınırı
            if (mod.valueCount > MAX_VALUES) break;

            // test r15d,r15d → jle: mevcut değer yoksa direkt ekle
            int slot = -1;
            if (mod.valueCount > 0)
            {
                // lea r14,[r13+r10+0x308] → &mod.values[0]
                // add rbp, 0x100 → her valueName +256 adım
                for (int j = 0; j < mod.valueCount; j++)
                {
                    if (value_name_cmp(&mod.values[j], valName) == 0)
                    {
                        slot = j;
                        break;
                    }
                }
            }

            if (slot == -1)
            {
                // lea ecx,[r15+1] → valueCount+1
                slot = mod.valueCount;
                // mov [r13+rax+0x304], ecx
                mod.valueCount = slot + 1;
            }

            // safe_strncpy çağrıları (call 0x8770)
            safe_strncpy(mod.values[slot].valueName,   valName,  0xFF);
            safe_strncpy(mod.values[slot].valueData,   defValue, 0xFF);
            safe_strncpy(mod.values[slot].valueExtra1, extra1,   0xFF);
            safe_strncpy(mod.values[slot].valueExtra2, extra2,   0xFF);
            break;
        }
    }

    env->ReleaseStringUTFChars(jModuleName,   modName);
    env->ReleaseStringUTFChars(jValueName,    valName);
    env->ReleaseStringUTFChars(jDefaultValue, defValue);
    env->ReleaseStringUTFChars(jExtra1,       extra1);
    env->ReleaseStringUTFChars(jExtra2,       extra2);
}


// ============================================================
//  5. nativeSetValue
//  RVA: 0x19b0
//
//  Assembly özeti:
//    r8=moduleName, r9=valueName, stack+0xb0=newValue
//    GetStringUTFChars x3
//    modül döngüsü → değer döngüsü
//    bulunca: lea r8,[r14+0xc3] → (valueIdx + 0xc3) << 8
//             bu offset = valueData alanı
//    call 0x8770 → safe_strncpy (newValue → valueData)
//    ReleaseStringUTFChars x3 + jmp rax
// ============================================================
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeSetValue(
    JNIEnv*  env,
    jobject  /*obj*/,
    jstring  jModuleName,
    jstring  jValueName,
    jstring  jNewValue)
{
    const char* modName  = env->GetStringUTFChars(jModuleName, nullptr);
    const char* valName  = env->GetStringUTFChars(jValueName,  nullptr);
    const char* newValue = env->GetStringUTFChars(jNewValue,   nullptr);

    for (int i = 0; i < g_moduleCount; i++)
    {
        if (module_name_cmp(&g_modules[i], modName) == 0)
        {
            ModuleEntry& mod = g_modules[i];
            for (int j = 0; j < mod.valueCount; j++)
            {
                if (value_name_cmp(&mod.values[j], valName) == 0)
                {
                    // lea r8,[r14+0xc3]; shl r8,8 → valueData offset
                    safe_strncpy(mod.values[j].valueData, newValue, 0xFF);
                    break;
                }
            }
            break;
        }
    }

    env->ReleaseStringUTFChars(jModuleName, modName);
    env->ReleaseStringUTFChars(jValueName,  valName);
    env->ReleaseStringUTFChars(jNewValue,   newValue);
}


// ============================================================
//  6. nativeGetValue
//  RVA: 0x1b20
//
//  Assembly özeti:
//    r8=moduleName, r9=valueName
//    GetStringUTFChars x2
//    modül döngüsü → değer döngüsü
//    bulunca: lea r8,[r14+0xc3]; shl r8,8 → valueData offset
//             lea rdx,[rbp+rax]
//             call vtable+0x538 → NewStringUTF(env, valueData)
//    bulunmazsa: 0x1c88 → NewStringUTF(env, "(null)") ← RIP+0x836e
//    ReleaseStringUTFChars x2
// ============================================================
extern "C" JNIEXPORT jstring JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeGetValue(
    JNIEnv*  env,
    jobject  /*obj*/,
    jstring  jModuleName,
    jstring  jValueName)
{
    const char* modName = env->GetStringUTFChars(jModuleName, nullptr);
    const char* valName = env->GetStringUTFChars(jValueName,  nullptr);
    jstring result = nullptr;

    for (int i = 0; i < g_moduleCount; i++)
    {
        if (module_name_cmp(&g_modules[i], modName) == 0)
        {
            ModuleEntry& mod = g_modules[i];
            for (int j = 0; j < mod.valueCount; j++)
            {
                if (value_name_cmp(&mod.values[j], valName) == 0)
                {
                    // call vtable+0x538 → NewStringUTF
                    result = env->NewStringUTF(mod.values[j].valueData);
                    break;
                }
            }
            break;
        }
    }

    env->ReleaseStringUTFChars(jModuleName, modName);
    env->ReleaseStringUTFChars(jValueName,  valName);

    if (result == nullptr)
    {
        // 0x1c88: lea rdx,[rip+0x836e] → "(null)" string
        result = env->NewStringUTF("(null)");
    }

    return result;
}


// ============================================================
//  7. nativeGetAllModules
//  RVA: 0x1cf0
//
//  Assembly özeti:
//    lea rdx,[rip+0x8302] → "java/lang/String"
//    call vtable+0x30 → FindClass
//    mov rsi, vtable+0x560 → NewObjectArray
//    mov edx,[g_moduleCount]
//    call rsi → NewObjectArray(env, moduleCount, String.class, null)
//    döngü:
//      lea rdx,[rdi] → &g_modules[i].name, add rdi,0x50408
//      call vtable+0x538 → NewStringUTF(name)
//      call vtable+0x570 → SetObjectArrayElement(arr, i, jstr)
//    ret rbp (array)
// ============================================================
extern "C" JNIEXPORT jobjectArray JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeGetAllModules(
    JNIEnv*  env,
    jobject  /*obj*/)
{
    // lea rdx,[rip+0x8302] → "java/lang/String"
    jclass stringClass = env->FindClass("java/lang/String");

    // mov edx,[g_moduleCount]; NewObjectArray
    jobjectArray arr = env->NewObjectArray(
        g_moduleCount, stringClass, nullptr);

    // döngü: add rdi,0x50408 → her modül STRUCT_SIZE adım
    for (int i = 0; i < g_moduleCount; i++)
    {
        // call vtable+0x538 → NewStringUTF(g_modules[i].name)
        jstring jName = env->NewStringUTF(g_modules[i].name);
        // call vtable+0x570 → SetObjectArrayElement
        env->SetObjectArrayElement(arr, i, jName);
    }

    return arr;
}


// ============================================================
//  8. nativeDecrypt
//  RVA: 0x1d90
//
//  Assembly özeti:
//    r8=jData(byteArray), r9d=length
//    call vtable+0x5c0 → GetByteArrayElements(data, 0)
//    call vtable+0x580 → GetArrayLength → ebx=length
//    call vtable+0x5c0 → NewByteArray(length) → r13=dst
//    test ebx,ebx → jle (length==0 atla)
//    --- byte[0]: movzx eax,[rbp]; xor eax,0x77; mov [r13],al
//    --- byte[i] (i>=1):
//        r9d=0x0D, r8d=0x0A, ecx=1, edx=1
//        DÖNGÜ:
//          eax=edx; edx++
//          eax &= 0x3f       → tablo index A
//          r11d = key[eax] ^ r9d
//          r9d += 0x0D
//          r11d = r8d & 0x3f → tablo index B
//          r8d += 0x07
//          al ^= key[r11d]   → keyB
//          al ^= src[rcx]    → plaintext
//          dst[rcx] = al
//          rcx++; cmp ebx,edx → devam
//    call vtable+0x600 → ReleaseByteArrayElements(data, src, 2)
//    call vtable+0x600 → ReleaseByteArrayElements(out,  dst, 0)
//    ret rdi (output array)
// ============================================================
extern "C" JNIEXPORT jbyteArray JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeDecrypt(
    JNIEnv*    env,
    jobject    /*obj*/,
    jbyteArray jData,
    jint       length)
{
    // call vtable+0x5c0 → GetByteArrayElements
    jbyte* src = env->GetByteArrayElements(jData, nullptr);

    // call vtable+0x580 → GetArrayLength (gerçek length)
    jint realLen = env->GetArrayLength(jData);
    if (length > realLen) length = realLen;

    // call vtable+0x5c0 → NewByteArray (yeni çıkış dizisi)
    jbyteArray jOut = env->NewByteArray(length);
    jbyte* dst      = env->GetByteArrayElements(jOut, nullptr);

    if (length > 0)
    {
        // byte[0]: xor 0x77 (sabit)
        dst[0] = src[0] ^ 0x77;

        if (length > 1)
        {
            // Döngü parametreleri:
            //   r9d = xorA başlangıç = 0x0D
            //   r8d = xorB başlangıç = 0x0A
            unsigned int xorA = 0x0D;
            unsigned int xorB = 0x0A;
            unsigned int edx  = 1;   // tablo indeks sayacı

            for (int i = 1; i < length; i++)
            {
                // eax = edx; edx++
                unsigned int eax = edx++;

                // tablo index A: eax & 0x3f
                unsigned int idxA = eax & 0x3F;
                // r11d = key[idxA] ^ xorA
                unsigned int keyA = DECRYPT_KEY_TABLE[idxA] ^ xorA;
                // r9d += 0x0D
                xorA += 0x0D;

                // tablo index B: xorB & 0x3f
                unsigned int idxB = xorB & 0x3F;
                unsigned char keyB = DECRYPT_KEY_TABLE[idxB];
                // r8d += 0x07
                xorB += 0x07;

                // al = keyA ^ keyB ^ src[i]
                dst[i] = (jbyte)((unsigned char)keyA ^ keyB ^ (unsigned char)src[i]);
            }
        }
    }

    // mode=2 → copy-back + free (src)
    env->ReleaseByteArrayElements(jData, src, 2);
    // mode=0 → copy-back + free (dst)
    env->ReleaseByteArrayElements(jOut,  dst, 0);

    return jOut;
}


// ============================================================
//  9-12. nativeOnEnable / nativeOnDisable / nativeOnTick /
//        nativeOnRender  (NativeAPI)
//  RVA: 0x1cb0 / 0x1cc0 / 0x1cd0 / 0x1ce0
//  Assembly: ret — boş stub, Java tarafından override bekleniyor
// ============================================================
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeOnEnable(JNIEnv*, jobject) {}

extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeOnDisable(JNIEnv*, jobject) {}

extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeOnTick(JNIEnv*, jobject) {}

extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeOnRender(JNIEnv*, jobject) {}
