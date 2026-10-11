// ============================================================
//  NativeAPI.java
//  Paket: org.itsmahir.pagon.nativep
//
//  Bu dosya pagon_native.dll'in export tablosundan ve
//  JNI naming convention'ından geri türetilmiştir.
//  DLL fonksiyon adları:
//    Java_org_itsmahir_pagon_nativep_NativeAPI_nativeXXX
//  → sınıf: org.itsmahir.pagon.nativep.NativeAPI
// ============================================================
package org.itsmahir.pagon.nativep;

public class NativeAPI {

    // ── DLL yükleme ──────────────────────────────────────────
    static {
        System.loadLibrary("pagon_native");
    }

    // ── Modül kayıt / kontrol ─────────────────────────────────

    /**
     * Yeni bir modül kaydeder.
     * DLL max 128 modül tutar (MAX_MODULES = 0x7F).
     * Aynı isimde modül varsa üzerine yazar.
     *
     * @param name        modül adı (max 255 karakter)
     * @param category    kategori adı
     * @param description açıklama
     */
    public native void nativeRegisterModule(String name, String category, String description);

    /**
     * Modülü açar veya kapar.
     * DLL'de: g_modules[i].enabled = enabled ? 1 : 0
     */
    public native void nativeSetModuleEnabled(String name, boolean enabled);

    /**
     * Modülün açık olup olmadığını döner.
     * Modül bulunamazsa false döner.
     */
    public native boolean nativeIsModuleEnabled(String name);

    /**
     * Tüm kayıtlı modül adlarını dizi olarak döner.
     * DLL: NewObjectArray(moduleCount, String.class)
     */
    public native String[] nativeGetAllModules();

    // ── Değer (value) kayıt / okuma / yazma ───────────────────

    /**
     * Bir modüle değer kaydeder.
     * Her modül max 64 değer tutabilir (MAX_VALUES = 0x3F).
     *
     * @param moduleName   hedef modül adı
     * @param valueName    değer adı (max 255 karakter)
     * @param defaultValue varsayılan değer metni
     * @param extra1       ek parametre 1 (tip etiketi vb.)
     * @param extra2       ek parametre 2 (min/max vb.)
     */
    public native void nativeRegisterValue(
        String moduleName,
        String valueName,
        String defaultValue,
        String extra1,
        String extra2
    );

    /**
     * Bir değeri günceller.
     * Modül veya değer bulunamazsa sessizce döner.
     */
    public native void nativeSetValue(String moduleName, String valueName, String newValue);

    /**
     * Bir değeri okur.
     * Bulunamazsa "(null)" döner.
     */
    public native String nativeGetValue(String moduleName, String valueName);

    // ── Şifre çözme ────────────────────────────────────────────

    /**
     * XOR tabanlı şifre çözücü.
     *
     * Algoritma (DLL assembly'den türetildi):
     *   out[0]  = in[0] ^ 0x77
     *   out[i]  = key[i&63] ^ xorA ^ key[xorB&63] ^ in[i]   (i >= 1)
     *   xorA    = 0x0D, her adım +0x0D
     *   xorB    = 0x0A, her adım +0x07
     *
     * Key tablosu (.rdata offset 0x8227, 64 byte):
     *   F2 6D 91 3E C0 B7 4A 25 FE 18 63 AC DF 71 09 E4
     *   B2 56 8F 3A C7 ED 14 FB 80 4C 92 2E F5 BB 67 D1
     *   0E 85 39 FA 43 1E B8 7C E3 50 AF 32 CD 68 F7 1B
     *   94 23 DE 75 0A E1 BC 46 8D 5B 6E 61 74 69 76 65
     *
     * @param data   şifreli veri
     * @param length işlenecek byte sayısı
     * @return       çözülmüş veri
     */
    public native byte[] nativeDecrypt(byte[] data, int length);

    // ── Lifecycle (boş stub'lar) ───────────────────────────────
    // DLL'de hepsi sadece "ret" — Java tarafında override edilmeli

    /** DLL tarafında boş stub. */
    public native void nativeOnEnable();

    /** DLL tarafında boş stub. */
    public native void nativeOnDisable();

    /** DLL tarafında boş stub. */
    public native void nativeOnTick();

    /** DLL tarafında boş stub. */
    public native void nativeOnRender();
}
