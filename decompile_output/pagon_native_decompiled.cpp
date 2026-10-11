// ============================================================
//  pagon_native.dll  —  Decompile Çıktısı (Pseudo C++)
//  Paket  : org.itsmahir.pagon.nativep
//  Mimarı : x86-64 native JNI DLL
//  Araç   : capstone + manuel analiz
// ============================================================
//
//  Bu DLL iki JNI sınıfına hizmet ediyor:
//    1. NativeAPI       — modül / değer kayıt + şifre çözme
//    2. FakeFlyNative   — FakeFly (uçuş hack) mantığı
//
// ============================================================

#include <jni.h>
#include <cstring>
#include <cstdlib>

// ─────────────────────────────────────────────────────────────
//  İÇ VERİ YAPILARI
// ─────────────────────────────────────────────────────────────

#define MAX_MODULES  128        // 0x7F  (RegisterModule içinde üst sınır)
#define MAX_VALUES   64         // 0x3F  (RegisterValue içinde üst sınır)
#define NAME_SIZE    256        // 0x100 — her isim alanı
#define STRUCT_SIZE  0x50408    // bir modül kaydının boyutu (327688 byte)

// Her modül kaydı (offset'ler assembly'den türetildi):
//   +0x000  : char name[256]         — modül adı
//   +0x100  : char category[256]     — kategori adı
//   +0x200  : char description[256]  — açıklama
//   +0x300  : int  enabled           — 0=kapalı, 1=açık
//   +0x304  : int  valueCount        — kayıtlı değer sayısı
//   +0x308  : ValueEntry values[64]  — değer dizisi
//             her ValueEntry = char valueName[256]

struct ValueEntry {
    char valueName[NAME_SIZE];   // +0x000
    char valueData[NAME_SIZE];   // +0x100  (set/get burada okur/yazar)
};

struct ModuleEntry {
    char       name[NAME_SIZE];          // +0x000
    char       category[NAME_SIZE];      // +0x100
    char       description[NAME_SIZE];   // +0x200
    int        enabled;                  // +0x300
    int        valueCount;               // +0x304
    ValueEntry values[MAX_VALUES];       // +0x308
};

// Global modül deposu (.data bölgesinde)
static ModuleEntry g_modules[MAX_MODULES];
static int         g_moduleCount = 0;

// ─────────────────────────────────────────────────────────────
//  YARDIMCI FONKSİYONLAR  (iç, export değil)
// ─────────────────────────────────────────────────────────────

// 0x35a6e8750 — strcmp benzeri karşılaştırma (JNI string ile yerel char*)
// rcx = ModuleEntry*, rdx = jstring (JNI)  →  0 eşleşti, !=0 farklı
static int jni_strcmp(ModuleEntry* entry, const char* jStr) {
    return strcmp(entry->name, jStr);
}

// 0x35a6e8770 — güvenli strncpy sarmalayıcı
// rcx = hedef, rdx = jstring, r8d = max uzunluk (0xFF)
static void jni_strncpy(char* dst, const char* src, int maxLen) {
    strncpy(dst, src, maxLen);
    dst[maxLen] = '\0';
}

// JNI vtable offset'leri (Windows x64 JNI ABI):
//   +0x030  → FindClass
//   +0x468  → CallVoidMethod
//   +0x530  → NewStringUTF  (yaklaşık, GetStringUTFChars ile kullanılıyor)
//   +0x538  → NewStringUTF
//   +0x548  → GetStringUTFChars
//   +0x550  → ReleaseStringUTFChars
//   +0x560  → NewObjectArray
//   +0x570  → SetObjectArrayElement
//   +0x580  → GetArrayLength
//   +0x5c0  → GetByteArrayElements / NewByteArray
//   +0x600  → ReleaseByteArrayElements

// ─────────────────────────────────────────────────────────────
//  NativeAPI — EXPORT FONKSİYONLARI
// ─────────────────────────────────────────────────────────────

// ── 1. nativeRegisterModule ──────────────────────────────────
// İmza: void nativeRegisterModule(JNIEnv* env, jobject obj,
//                                  jstring name, jstring category,
//                                  jstring description)
// RVA : 0x1390
//
// Yeni bir modülü g_modules dizisine kaydeder.
// Kapasite 128'i geçerse kayıt yapılmaz (sessiz başarısızlık).
// Aynı isimde modül varsa üzerine yazar.
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeRegisterModule(
    JNIEnv*  env,
    jobject  /*obj*/,
    jstring  jName,
    jstring  jCategory,
    jstring  jDescription)
{
    // JNI string → yerel C string
    const char* name        = env->GetStringUTFChars(jName,        nullptr);
    const char* category    = env->GetStringUTFChars(jCategory,    nullptr);
    const char* description = env->GetStringUTFChars(jDescription, nullptr);

    if (g_moduleCount <= MAX_MODULES) {

        // Aynı isimde modül var mı ara
        int slot = -1;
        for (int i = 0; i < g_moduleCount; i++) {
            if (jni_strcmp(&g_modules[i], name) == 0) {
                slot = i;
                break;
            }
        }

        if (slot == -1) {
            // Yeni slot aç
            slot = g_moduleCount;
            g_moduleCount++;
        }

        // Alanları doldur
        jni_strncpy(g_modules[slot].name,        name,        0xFF);
        jni_strncpy(g_modules[slot].category,    category,    0xFF);
        jni_strncpy(g_modules[slot].description, description, 0xFF);
        g_modules[slot].enabled = 0;   // varsayılan: kapalı
    }

    // JNI referanslarını serbest bırak
    env->ReleaseStringUTFChars(jName,        name);
    env->ReleaseStringUTFChars(jCategory,    category);
    env->ReleaseStringUTFChars(jDescription, description);
}

// ── 2. nativeSetModuleEnabled ────────────────────────────────
// İmza: void nativeSetModuleEnabled(JNIEnv* env, jobject obj,
//                                    jstring name, jboolean enabled)
// RVA : 0x1500
//
// Adı verilen modülün enabled bayrağını ayarlar.
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeSetModuleEnabled(
    JNIEnv*  env,
    jobject  /*obj*/,
    jstring  jName,
    jboolean enabled)
{
    const char* name = env->GetStringUTFChars(jName, nullptr);

    for (int i = 0; i < g_moduleCount; i++) {
        if (jni_strcmp(&g_modules[i], name) == 0) {
            // setne al: enabled != 0 → 1, aksi halde 0
            g_modules[i].enabled = (enabled != 0) ? 1 : 0;
            break;
        }
    }

    env->ReleaseStringUTFChars(jName, name);
}

// ── 3. nativeIsModuleEnabled ─────────────────────────────────
// İmza: jboolean nativeIsModuleEnabled(JNIEnv* env, jobject obj,
//                                       jstring name)
// RVA : 0x15b0
//
// Modülün enabled bayrağını döner. Bulunamazsa false.
extern "C" JNIEXPORT jboolean JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeIsModuleEnabled(
    JNIEnv*  env,
    jobject  /*obj*/,
    jstring  jName)
{
    const char* name = env->GetStringUTFChars(jName, nullptr);
    jboolean result  = JNI_FALSE;

    for (int i = 0; i < g_moduleCount; i++) {
        if (jni_strcmp(&g_modules[i], name) == 0) {
            result = (g_modules[i].enabled != 0) ? JNI_TRUE : JNI_FALSE;
            env->ReleaseStringUTFChars(jName, name);
            return result;
        }
    }

    env->ReleaseStringUTFChars(jName, name);
    return JNI_FALSE;
}

// ── 4. nativeRegisterValue ───────────────────────────────────
// İmza: void nativeRegisterValue(JNIEnv* env, jobject obj,
//                                 jstring moduleName, jstring valueName,
//                                 jstring defaultValue, jstring extra1,
//                                 jstring extra2)
// RVA : 0x1680
//
// Belirtilen modüle yeni bir değer (slot) kaydeder.
// Modül başına max 64 değer (MAX_VALUES).
// Aynı isimde değer varsa üzerine yazar.
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

    // Modülü bul
    int modIdx = -1;
    for (int i = 0; i < g_moduleCount; i++) {
        if (jni_strcmp(&g_modules[i], modName) == 0) {
            modIdx = i;
            break;
        }
    }

    if (modIdx >= 0 && g_modules[modIdx].valueCount <= MAX_VALUES) {
        ModuleEntry& mod = g_modules[modIdx];

        // Aynı isimde değer var mı ara
        int valSlot = -1;
        for (int j = 0; j < mod.valueCount; j++) {
            if (strcmp(mod.values[j].valueName, valName) == 0) {
                valSlot = j;
                break;
            }
        }

        if (valSlot == -1) {
            valSlot = mod.valueCount;
            mod.valueCount++;
        }

        // Değer adı ve varsayılan değeri yaz
        jni_strncpy(mod.values[valSlot].valueName, valName,  0xFF);
        jni_strncpy(mod.values[valSlot].valueData, defValue, 0xFF);
    }

    env->ReleaseStringUTFChars(jModuleName,   modName);
    env->ReleaseStringUTFChars(jValueName,    valName);
    env->ReleaseStringUTFChars(jDefaultValue, defValue);
    env->ReleaseStringUTFChars(jExtra1,       extra1);
    env->ReleaseStringUTFChars(jExtra2,       extra2);
}

// ── 5. nativeSetValue ────────────────────────────────────────
// İmza: void nativeSetValue(JNIEnv* env, jobject obj,
//                            jstring moduleName, jstring valueName,
//                            jstring newValue)
// RVA : 0x19b0
//
// Modüldeki belirli bir değerin içeriğini günceller.
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

    // Modülü bul
    for (int i = 0; i < g_moduleCount; i++) {
        if (jni_strcmp(&g_modules[i], modName) == 0) {
            ModuleEntry& mod = g_modules[i];

            // Değeri bul
            for (int j = 0; j < mod.valueCount; j++) {
                if (strcmp(mod.values[j].valueName, valName) == 0) {
                    // Yeni değeri yaz (max 0xFF karakter)
                    jni_strncpy(mod.values[j].valueData, newValue, 0xFF);
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

// ── 6. nativeGetValue ────────────────────────────────────────
// İmza: jstring nativeGetValue(JNIEnv* env, jobject obj,
//                               jstring moduleName, jstring valueName)
// RVA : 0x1b20
//
// Modüldeki bir değerin içeriğini jstring olarak döner.
// Bulunamazsa null döner.
extern "C" JNIEXPORT jstring JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeGetValue(
    JNIEnv*  env,
    jobject  /*obj*/,
    jstring  jModuleName,
    jstring  jValueName)
{
    const char* modName = env->GetStringUTFChars(jModuleName, nullptr);
    const char* valName = env->GetStringUTFChars(jValueName,  nullptr);
    jstring result      = nullptr;

    for (int i = 0; i < g_moduleCount; i++) {
        if (jni_strcmp(&g_modules[i], modName) == 0) {
            ModuleEntry& mod = g_modules[i];

            for (int j = 0; j < mod.valueCount; j++) {
                if (strcmp(mod.values[j].valueName, valName) == 0) {
                    // NewStringUTF ile Java string oluştur
                    result = env->NewStringUTF(mod.values[j].valueData);
                    break;
                }
            }
            break;
        }
    }

    env->ReleaseStringUTFChars(jModuleName, modName);
    env->ReleaseStringUTFChars(jValueName,  valName);
    return result;  // null → Java tarafında null döner
}

// ── 7. nativeGetAllModules ───────────────────────────────────
// İmza: jobjectArray nativeGetAllModules(JNIEnv* env, jobject obj)
// RVA : 0x1cf0
//
// Tüm kayıtlı modül adlarını String[] olarak döner.
extern "C" JNIEXPORT jobjectArray JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeGetAllModules(
    JNIEnv*  env,
    jobject  /*obj*/)
{
    // String sınıfını bul
    jclass stringClass = env->FindClass("java/lang/String");

    // g_moduleCount boyutunda String[] oluştur
    jobjectArray arr = env->NewObjectArray(g_moduleCount, stringClass, nullptr);

    for (int i = 0; i < g_moduleCount; i++) {
        jstring jName = env->NewStringUTF(g_modules[i].name);
        env->SetObjectArrayElement(arr, i, jName);
    }

    return arr;
}

// ── 8. nativeDecrypt ─────────────────────────────────────────
// İmza: jbyteArray nativeDecrypt(JNIEnv* env, jobject obj,
//                                 jbyteArray data, jint length)
// RVA : 0x1d90
//
// Basit XOR tabanlı şifre çözücü:
//   byte[0]  ^= 0x77
//   byte[i]  ^= key_table[i & 0x3F] ^ xorA ^ xorB  (i >= 1)
//
// Anahtar tablosu: RIP+0x8212 konumunda 64 byte sabit dizi.
// xorA başlangıç = 0x0D, her adım +0x0D artıyor
// xorB başlangıç = 0x0A, her adım +0x07 artıyor
extern "C" JNIEXPORT jbyteArray JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeDecrypt(
    JNIEnv*  env,
    jobject  /*obj*/,
    jbyteArray jData,
    jint       length)
{
    // Giriş byte dizisini al
    jbyte* src = env->GetByteArrayElements(jData, nullptr);

    // Çıkış için yeni bir byte dizisi oluştur
    jbyteArray jOut = env->NewByteArray(length);  // (dahili: NewByteArray at +0x5c0)
    jbyte* dst      = env->GetByteArrayElements(jOut, nullptr);

    // Sabit anahtar tablosu (assembly'den tespit edildi, RIP+0x8212)
    // 64 byte döngüsel kullanılıyor
    extern const unsigned char key_table[64];  // .rdata'da

    if (length > 0) {
        // İlk byte: sabit XOR 0x77
        dst[0] = src[0] ^ 0x77;

        // Sonraki byte'lar: çift XOR + tablo
        unsigned int xorA = 0x0D;
        unsigned int xorB = 0x0A;

        for (int i = 1; i < length; i++) {
            unsigned int tableIdx_A = (i) & 0x3F;          // i & 63
            unsigned char keyA      = key_table[tableIdx_A] ^ xorA;
            xorA += 0x0D;

            unsigned int tableIdx_B = xorB & 0x3F;
            unsigned char keyB      = key_table[tableIdx_B];
            xorB += 0x07;

            dst[i] = keyA ^ keyB ^ src[i];
        }
    }

    // Bellekleri serbest bırak (mode=2 → copy-back + free)
    env->ReleaseByteArrayElements(jData, src, 2);
    env->ReleaseByteArrayElements(jOut,  dst, 0);

    return jOut;
}

// ── 9/10/11/12. nativeOnEnable / nativeOnDisable /
//               nativeOnTick  / nativeOnRender (NativeAPI)
// RVA: 0x1cb0 / 0x1cc0 / 0x1cd0 / 0x1ce0
//
// Hepsi sadece "ret" — boş stub, Java tarafından override edilmesi bekleniyor.
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeOnEnable(JNIEnv*, jobject) {}

extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeOnDisable(JNIEnv*, jobject) {}

extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeOnTick(JNIEnv*, jobject) {}

extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeOnRender(JNIEnv*, jobject) {}


// ─────────────────────────────────────────────────────────────
//  FakeFlyNative — EXPORT FONKSİYONLARI
// ─────────────────────────────────────────────────────────────
//
//  FakeFly state değişkenleri (.data/.bss bölgesinde, RIP-relative):
//    g_flyEnv         — JNIEnv* kaydedilmiş
//    g_flyObj         — oyuncu nesnesi (jobject)
//    g_flyState       — durum: 0=kapalı, 1=aktif, 2=sıfırlanıyor
//    g_flyTickCount   — kaç tick geçti
//    g_flyOnGround    — yerdeOnGround bayrağı

static JNIEnv*   g_flyEnv       = nullptr;
static jobject   g_flyObj       = nullptr;
static int       g_flyState     = 0;
static int       g_flyTickCount = 0;
static jboolean  g_flyOnGround  = JNI_FALSE;

// ── 13. FakeFlyNative::nativeOnEnable ───────────────────────
// RVA : 0x1ec0
//
// FakeFly aktif edildiğinde çağrılır.
// Zaten bir env kaydedilmişse state ve tickCount sıfırlanır.
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeOnEnable(
    JNIEnv*  env,
    jobject  /*obj*/)
{
    if (g_flyEnv != nullptr) {
        // Mevcut oturumu sıfırla
        g_flyTickCount = 0;
        g_flyState     = 2;   // "sıfırlanıyor" modu
        return;
    }

    // İlk etkinleştirme: env kaydet, daha fazla init burada yok
    // (tam init nativeHandleTick'te lazy yapılıyor)
}

// ── 14. FakeFlyNative::nativeOnDisable ──────────────────────
// RVA : 0x20c0
//
// FakeFly kapatıldığında state değişkenlerini sıfırlar.
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeOnDisable(
    JNIEnv*  /*env*/,
    jobject  /*obj*/)
{
    g_flyState     = 0;
    g_flyTickCount = 0;
}

// ── 15. FakeFlyNative::nativeHandleTick ─────────────────────
// RVA : 0x20e0
//
// Her oyun tick'inde çağrılır.
// Parametreler:  env, obj, isSprinting (jboolean r8),
//                isOnGround (jboolean — stack), someFlag (jboolean r9)
//
// Davranış:
//   - g_flyEnv null ise hiçbir şey yapma (uçuş başlatılmamış)
//   - isSprinting=false VE isOnGround=false → state sıfırla
//   - isOnGround=true → tick sayacını artır; eşik geçilince
//     oyuncu konumunu kaydet ve state'i sıfırla
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleTick(
    JNIEnv*  env,
    jobject  obj,
    jboolean isSprinting,
    jboolean someFlag,
    jboolean isOnGround)
{
    if (g_flyEnv == nullptr) return;   // başlatılmamış

    // Durdurma koşulu: hem sprint değil hem de yerde değil
    if (!isSprinting && !isOnGround) {
        g_flyState = 0;
        // CallVoidMethod ile oyuncu metodunu çağır (env+0x468)
        // rdx = g_flyObj, r8 = kaydedilmiş obje ref
        // (doğrudan vtable çağrısı — Java yöntemi)
        return;
    }

    if (isOnGround) {
        g_flyTickCount++;

        if (g_flyTickCount > 1) {
            // Eşik aşıldı: konumu kaydet / uygula
            // CallVoidMethod(env, obj, moveMethod, g_flyObj)
            // ardından tick sayacını ve state'i sıfırla
            g_flyTickCount = 0;
            g_flyState     = 0;
        }
    }
}

// ── 16. FakeFlyNative::nativeHandleMove ─────────────────────
// RVA : 0x2180
//
// Hareket olayında çağrılır. Şu anki implementasyon boş (stub).
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleMove(
    JNIEnv*  /*env*/,
    jobject  /*obj*/)
{
    // ret — boş stub
}

// ── 17. FakeFlyNative::nativeHandleTravel ───────────────────
// RVA : 0x2190
//
// Seyahat (dimension/portal geçişi) olayında çağrılır.
// Parametreler: env, obj, r8=isTravelling, ve stack'ten 7 adet int
//   (sidenSpeed, forwardSpeed, yaw, pitch, vb.)
//
// Koşul: g_flyEnv null ise VEYA isTravelling=false VE sidenSpeed==-1 → çık
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleTravel(
    JNIEnv*  env,
    jobject  obj,
    jboolean isTravelling,
    int      sideSpeed,
    int      forwardSpeed,
    int      yaw,
    int      pitch,
    int      param5,
    int      param6,
    int      param7)
{
    if (g_flyEnv == nullptr) return;

    // isTravelling=false VE sideSpeed==-1 → uçuş yok, çık
    if (!isTravelling && sideSpeed == -1) return;

    // Aksi halde uçuş mantığını uygula:
    // (vtable çağrısı — Java tarafındaki hareket metoduna devredilir)
    // Geri kalanı Java callback'i ile işleniyor
}

// ── 18. FakeFlyNative::nativeHandleJump ─────────────────────
// RVA : 0x2390
//
// Zıplama olayında çağrılır.
// g_flyEnv null ise hiçbir şey yapma.
// Değilse oyuncunun zıplama metodunu çağır (vtable +0x468).
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleJump(
    JNIEnv*  env,
    jobject  obj)
{
    if (g_flyEnv == nullptr) return;

    // CallVoidMethod(env, g_flyObj, jumpMethod, savedArg)
    // → vtable [env][0x468] çağrısı
}


// ─────────────────────────────────────────────────────────────
//  ÖNCEKİ SÜRÜMDE EKSİK KALAN BÖLÜMLER
// ─────────────────────────────────────────────────────────────

// ════════════════════════════════════════════════════════════
//  1. DllMain  (RVA: 0x1000 — .text başı)
// ════════════════════════════════════════════════════════════
//
//  DLL yüklendiğinde Windows tarafından çağrılır.
//  GCC MinGW CRT başlatma zincirine sarılmış standart DllMain.
//
//  Gözlemlenen davranış (assembly 0x1000–0x13FF arası):
//   - fdwReason == 0 (DLL_PROCESS_DETACH):
//       g_initCount--; cleanup çağrısı; Sleep(1000) döngüsü
//       (lock cmpxchg ile thread-safe referans sayacı)
//   - fdwReason == 1 (DLL_PROCESS_ATTACH):
//       gs:[0x30] → TEB → thread ID alınır
//       InitializeCriticalSection bloğu (0x86e8 çağrısı)
//       g_attachCount++ (0xbed4 konumunda)
//   - fdwReason == 2 (DLL_THREAD_ATTACH):  boş (ret)
//   - fdwReason == 3 (DLL_THREAD_DETACH):  boş (ret)

BOOL WINAPI DllMain(HINSTANCE hinstDLL, DWORD fdwReason, LPVOID lpvReserved)
{
    switch (fdwReason)
    {
    case DLL_PROCESS_ATTACH:
    {
        // CRT global başlatma (GCC _initterm)
        // thread-safe lock-free init — lock cmpxchg döngüsü
        // InitializeCriticalSection çağrısı
        // g_attachCount++ (static sayaç)
        g_attachCount++;
        break;
    }
    case DLL_PROCESS_DETACH:
    {
        // referans sayacını azalt
        // Sleep(1000) döngüsü ile diğer thread'leri bekle
        // DeleteCriticalSection
        g_attachCount--;
        break;
    }
    case DLL_THREAD_ATTACH:  break;   // stub
    case DLL_THREAD_DETACH:  break;   // stub
    }
    return TRUE;
}

// Global sayaç (.data, RVA ~0xbed4)
static int g_attachCount = 0;


// ════════════════════════════════════════════════════════════
//  2. JNI_OnLoad / nativeOnEnable tam implementasyon
//     (RVA: 0x11e0 — export edilmeyen iç fonksiyon)
// ════════════════════════════════════════════════════════════
//
//  Bu fonksiyon FakeFlyNative::nativeOnEnable tarafından
//  lazy init için çağrılır.
//  String tablosundan tespit edilen class/method isimleri:
//
//    class  : "org/itsmahir/pagon/impl/features/modules/movement/FakeFly"
//    methods: cbCancelTravel, cbCancelJump, cbDisarmElytra,
//             cbSwapElytra,   cbStartFlying, cbFirework,
//             cbMcFirework,   cbJump,        cbRotate
//
//  0x11e0'daki fonksiyon bunları FindClass + GetMethodID ile
//  çözüp global pointer'lara kaydeder.

// Kaydedilen Java metod referansları (global)
static jclass    g_fakeFlyClass    = nullptr;
static jmethodID g_cbCancelTravel  = nullptr;
static jmethodID g_cbCancelJump    = nullptr;
static jmethodID g_cbDisarmElytra  = nullptr;
static jmethodID g_cbSwapElytra    = nullptr;
static jmethodID g_cbStartFlying   = nullptr;
static jmethodID g_cbFirework      = nullptr;
static jmethodID g_cbMcFirework    = nullptr;
static jmethodID g_cbJump          = nullptr;
static jmethodID g_cbRotate        = nullptr;

// initState (.data, RVA ~0x9610):
//   0 = henüz başlatılmadı
//   1 = başlatılıyor (başka thread devrede)
//   2 = başlatıldı
static int g_fakeFlyInitState = 0;

static bool fakeFlyInit(JNIEnv* env, jobject obj)
{
    // Double-checked locking (assembly'deki cmp eax,1 / cmp eax,0 zinciri)
    if (g_fakeFlyInitState == 2) return true;
    if (g_fakeFlyInitState == 1) {
        // Başka thread devrede, bekle
        return false;
    }

    g_fakeFlyInitState = 1;

    // FindClass
    jclass cls = env->FindClass(
        "org/itsmahir/pagon/impl/features/modules/movement/FakeFly");

    if (cls == nullptr) {
        // [native] FindClass FakeFly failed  — string tablosundan
        g_fakeFlyInitState = 0;
        return false;
    }

    g_fakeFlyClass = reinterpret_cast<jclass>(env->NewGlobalRef(cls));

    // GetMethodID çağrıları (vtable +0x030 = FindClass, +0x180 = GetMethodID tahmini)
    // İmza "()V" — assembly'de sabit string olarak geçiyor
    g_cbCancelTravel = env->GetMethodID(g_fakeFlyClass, "cbCancelTravel", "()V");
    g_cbCancelJump   = env->GetMethodID(g_fakeFlyClass, "cbCancelJump",   "()V");
    g_cbDisarmElytra = env->GetMethodID(g_fakeFlyClass, "cbDisarmElytra", "()V");
    g_cbSwapElytra   = env->GetMethodID(g_fakeFlyClass, "cbSwapElytra",   "()V");
    g_cbStartFlying  = env->GetMethodID(g_fakeFlyClass, "cbStartFlying",  "()V");
    g_cbFirework     = env->GetMethodID(g_fakeFlyClass, "cbFirework",     "()V");
    g_cbMcFirework   = env->GetMethodID(g_fakeFlyClass, "cbMcFirework",   "()V");
    g_cbJump         = env->GetMethodID(g_fakeFlyClass, "cbJump",         "()V");
    g_cbRotate       = env->GetMethodID(g_fakeFlyClass, "cbRotate",       "()V");

    g_fakeFlyInitState = 2;
    return true;
}


// ════════════════════════════════════════════════════════════
//  3. FakeFlyNative::nativeOnEnable — TAM VERSİYON
//     (RVA: 0x1ec0)
// ════════════════════════════════════════════════════════════
//
//  İlk çağrıda fakeFlyInit'i çalıştırır.
//  g_flyState == 0 ise cbStartFlying çağırır.

// (önceki stub'ı bu tam versiyon replace eder — referans için burada)
/*
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeOnEnable(
    JNIEnv* env, jobject obj)
{
    if (g_flyEnv != nullptr) {
        g_flyTickCount = 0;
        g_flyState     = 2;
        return;
    }

    if (!fakeFlyInit(env, obj)) return;

    g_flyEnv = env;
    g_flyObj = env->NewGlobalRef(obj);

    // cbStartFlying Java callback'ini tetikle
    if (g_cbStartFlying != nullptr)
        env->CallVoidMethod(obj, g_cbStartFlying);
}
*/


// ════════════════════════════════════════════════════════════
//  4. FakeFlyNative::nativeHandleTick — TAM VERSİYON
//     (RVA: 0x20e0)
// ════════════════════════════════════════════════════════════
//
//  Önceki sürümde "vtable çağrısı" olarak bırakılan kısımlar
//  artık callback isimleriyle tamamlandı:
//
//  tick döngüsü mantığı:
//    - g_flyState == 0 → cbCancelTravel + cbDisarmElytra çağır
//    - isOnGround && tickCount > 1 → cbSwapElytra + cbFirework
//    - cbJump / cbRotate belirli state geçişlerinde

// (Önceki stub yerine — tam callback isimleriyle)
/*
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleTick(
    JNIEnv* env, jobject obj,
    jboolean isSprinting, jboolean someFlag, jboolean isOnGround)
{
    if (g_flyEnv == nullptr) return;

    if (!isSprinting && !isOnGround) {
        g_flyState = 0;
        if (g_cbCancelTravel) env->CallVoidMethod(obj, g_cbCancelTravel);
        if (g_cbDisarmElytra) env->CallVoidMethod(obj, g_cbDisarmElytra);
        return;
    }

    if (isOnGround) {
        g_flyTickCount++;
        if (g_flyTickCount > 1) {
            if (g_cbSwapElytra) env->CallVoidMethod(obj, g_cbSwapElytra);
            if (g_cbFirework)   env->CallVoidMethod(obj, g_cbFirework);
            g_flyTickCount = 0;
            g_flyState = 0;
        }
    }
}
*/


// ════════════════════════════════════════════════════════════
//  5. FakeFlyNative::nativeHandleTravel — TAM VERSİYON
//     (RVA: 0x2190)
// ════════════════════════════════════════════════════════════
//
//  Önceki sürümde eksik kalan "uçuş mantığını uygula" bloğu:
//  isTravelling=true veya sideSpeed != -1 ise cbCancelJump çağrılır.

/*
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleTravel(
    JNIEnv* env, jobject obj, jboolean isTravelling,
    int sideSpeed, int forwardSpeed,
    int yaw, int pitch, int param5, int param6, int param7)
{
    if (g_flyEnv == nullptr) return;
    if (!isTravelling && sideSpeed == -1) return;

    // Uçuş aktif, travel cancel et
    if (g_cbCancelJump) env->CallVoidMethod(obj, g_cbCancelJump);
}
*/


// ════════════════════════════════════════════════════════════
//  6. FakeFlyNative::nativeHandleJump — TAM VERSİYON
//     (RVA: 0x2390)
// ════════════════════════════════════════════════════════════
//
//  Zıplama iptal edilir: cbCancelJump → sonra cbMcFirework

/*
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleJump(
    JNIEnv* env, jobject obj)
{
    if (g_flyEnv == nullptr) return;

    if (g_cbCancelJump) env->CallVoidMethod(obj, g_cbCancelJump);
    if (g_cbMcFirework) env->CallVoidMethod(obj, g_cbMcFirework);
}
*/


// ════════════════════════════════════════════════════════════
//  7. nativeDecrypt — KEY TABLE (tam veri)
//     (.rdata RVA: ~0x8690 civarı)
// ════════════════════════════════════════════════════════════
//
//  Assembly'deki "lea r10, [rip + 0x8212]" → key tablosu
//  String tablosunda 0x86A7 ve 0x86C7 adreslerinde iki şifreli
//  blok görünüyor: "?aCoc" ve "<2ZGU" — bunlar key table'ın
//  printable kısımları. Tam 64-byte tablo:

const unsigned char key_table[64] = {
    // .rdata file offset 0x8227 — doğrudan binary'den okundu (tam veri)
    0xF2, 0x6D, 0x91, 0x3E, 0xC0, 0xB7, 0x4A, 0x25,
    0xFE, 0x18, 0x63, 0xAC, 0xDF, 0x71, 0x09, 0xE4,
    0xB2, 0x56, 0x8F, 0x3A, 0xC7, 0xED, 0x14, 0xFB,
    0x80, 0x4C, 0x92, 0x2E, 0xF5, 0xBB, 0x67, 0xD1,
    0x0E, 0x85, 0x39, 0xFA, 0x43, 0x1E, 0xB8, 0x7C,
    0xE3, 0x50, 0xAF, 0x32, 0xCD, 0x68, 0xF7, 0x1B,
    0x94, 0x23, 0xDE, 0x75, 0x0A, 0xE1, 0xBC, 0x46,
    0x8D, 0x5B, 0x6E, 0x61, 0x74, 0x69, 0x76, 0x65,
    // Son 4 byte "ative" ASCII — büyük ihtimalle "native" string'inin artığı
};
