// ============================================================
//  FakeFlyNative.java
//  Paket: org.itsmahir.pagon.nativep
//
//  Bu dosya pagon_native.dll'in export tablosundan ve
//  string tablosundan geri türetilmiştir.
//
//  DLL string tablosundan tespit edilen Java sınıfı:
//    "org/itsmahir/pagon/impl/features/modules/movement/FakeFly"
//
//  DLL export isimleri:
//    Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeXXX
//  → bu sınıf: org.itsmahir.pagon.nativep.FakeFlyNative
//
//  NOT: DLL, FakeFly sınıfının aşağıdaki callback metodlarını
//  çağırır (GetMethodID + CallVoidMethod ile):
//    cbCancelTravel, cbCancelJump, cbDisarmElytra,
//    cbSwapElytra,   cbStartFlying, cbFirework,
//    cbMcFirework,   cbJump,        cbRotate
//
//  Bu callback'ler Java (impl) tarafında implement edilmelidir.
// ============================================================
package org.itsmahir.pagon.nativep;

public abstract class FakeFlyNative {

    // ── DLL yükleme ──────────────────────────────────────────
    static {
        System.loadLibrary("pagon_native");
    }

    // ── Native metodlar ──────────────────────────────────────

    /**
     * FakeFly modülünü etkinleştirir.
     *
     * DLL davranışı:
     *  - İlk çağrıda FindClass + 9x GetMethodID ile Java callback'lerini yükler.
     *    Yüklenen sınıf: "org/itsmahir/pagon/impl/features/modules/movement/FakeFly"
     *  - Sonraki çağrılarda tickCount ve state sıfırlanır.
     *  - Başarılı init sonrası: g_flyState = 2, g_flyTickCount = 0
     */
    public native void nativeOnEnable();

    /**
     * FakeFly modülünü devre dışı bırakır.
     *
     * DLL davranışı:
     *  g_flyState = 0, g_flyTickCount = 0
     */
    public native void nativeOnDisable();

    /**
     * Her oyun tick'inde çağrılır.
     *
     * DLL davranışı:
     *  - g_flyEnv null ise çık (init yapılmamış)
     *  - isOnGround=false AND someFlag=false → state=0, cbCancelTravel çağır
     *  - isSprinting=true → tickCount++
     *    tickCount > 1 → cbSwapElytra; tickCount=0; state=0
     *  - Her durumda cbRotate çağır
     *
     * @param isSprinting  oyuncu sprint yapıyor mu
     * @param someFlag     ek bayrak (onGround ile OR'lanıyor)
     * @param isOnGround   oyuncu yerde mi
     */
    public native void nativeHandleTick(boolean isSprinting, boolean someFlag, boolean isOnGround);

    /**
     * Hareket olayında çağrılır.
     * DLL: boş stub (ret)
     */
    public native void nativeHandleMove();

    /**
     * Seyahat/portal geçişinde çağrılır.
     *
     * DLL davranışı:
     *  - g_flyEnv null → çık
     *  - isTravelling=false AND sideSpeed=-1 → çık
     *  - hasSideOrForward=false → cbCancelJump
     *  - isElytraActive=true AND sideSpeed!=0 → cbStartFlying
     *  - Temel yol: cbCancelTravel + cbDisarmElytra + cbCancelJump
     *    isFlying: travelTickCount++; > 2 → cbFirework; travelTickCount=0
     *
     * @param isTravelling  seyahat oluyor mu
     * @param sideSpeed     yatay hız bileşeni
     * @param forwardSpeed  ileri hız bileşeni
     * @param yaw           yaw açısı
     * @param pitch         pitch açısı (elytra aktif kontrolü için)
     * @param onGround      yerde mi
     * @param isFlying      uçuyor mu
     * @param isInWater     suda mı
     */
    public native void nativeHandleTravel(
        boolean isTravelling,
        int sideSpeed,
        int forwardSpeed,
        int yaw,
        int pitch,
        int onGround,
        int isFlying,
        int isInWater
    );

    /**
     * Zıplama olayında çağrılır.
     *
     * DLL davranışı:
     *  - g_flyObj null → çık
     *  - cbCancelJump çağır (tail-call)
     */
    public native void nativeHandleJump();

    // ── Java tarafında implement edilmesi gereken callback'ler ──
    // DLL bu metodları CallVoidMethod ile doğrudan çağırır.
    // Bu sınıfı extend eden sınıf bunları override etmelidir.

    /** Seyahati iptal et. */
    public abstract void cbCancelTravel();

    /** Zıplamayı iptal et. */
    public abstract void cbCancelJump();

    /** Elytra'yı söndür (devre dışı bırak). */
    public abstract void cbDisarmElytra();

    /** Normal armor ile elytra'yı değiştir. */
    public abstract void cbSwapElytra();

    /** Uçmaya başla. */
    public abstract void cbStartFlying();

    /** Havai fişek ateşle (elytra ivmesi için). */
    public abstract void cbFirework();

    /** Minecraft havai fişeği ateşle. */
    public abstract void cbMcFirework();

    /** Zıpla. */
    public abstract void cbJump();

    /** Rotasyonu güncelle. */
    public abstract void cbRotate();
}
