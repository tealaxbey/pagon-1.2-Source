// ============================================================
//  FakeFlyNative.cpp
//  Sınıf: org.itsmahir.pagon.nativep.FakeFlyNative
//  Export: 6 JNI fonksiyon
//
//  Java sınıfı: org/itsmahir/pagon/impl/features/modules/movement/FakeFly
//  Callback metotları (hepsi ()V imzalı):
//    cbCancelTravel, cbCancelJump, cbDisarmElytra,
//    cbSwapElytra,   cbStartFlying, cbFirework,
//    cbMcFirework,   cbJump,        cbRotate
// ============================================================

#include "../include/globals.h"

// ── Yardımcı: Java callback çağrısı ─────────────────────────
// assembly: call vtable+0x468 → CallVoidMethod(env, obj, methodID)
static inline void callVoid(JNIEnv* env, jobject obj, jmethodID mid)
{
    if (mid != nullptr)
        env->CallVoidMethod(obj, mid);
}


// ============================================================
//  fakeFlyInit  (iç fonksiyon, export değil)
//  RVA: 0x1ef0 (nativeOnEnable içindeki "je 0x1ef0" dalı)
//
//  Assembly özeti:
//    lea rdx,[rip+0x818e] → "org/itsmahir/pagon/impl/.../FakeFly"
//    call vtable+0x30     → FindClass
//    test rax,rax → je 0x2098 (FindClass başarısız)
//    0x2098: "mov ecx,2; call [rip+0x6fdd]" → stderr
//            lea rdx,[rip+0x7fb6] → "[native] FindClass FakeFly failed"
//            jmp 0x1ed3
//
//    başarılı:
//    call vtable+0xa8 → NewGlobalRef(class)
//    call vtable+0xb8 → ExceptionClear (?)
//    9 adet GetMethodID (vtable+0x388) çağrısı:
//      her biri: rdx=class, r8=methodName, r9=sig "()V"
//      sonuç global pointer'a yazılıyor
//    jmp 0x1ed3 → g_flyTickCount=0, g_flyState=2
// ============================================================
static bool fakeFlyInit(JNIEnv* env, jobject obj)
{
    // ── Çift kontrol (double-checked locking) ────────────────
    // assembly: cmp eax,2 / je / cmp eax,1 / je / cmp eax,0 / je
    if (g_flyInitState == 2) return true;
    if (g_flyInitState == 1) return false;  // başka thread devrede

    g_flyInitState = 1;

    // ── FindClass ─────────────────────────────────────────────
    // lea rdx,[rip+0x818e] → sınıf adı
    jclass cls = env->FindClass(
        "org/itsmahir/pagon/impl/features/modules/movement/FakeFly");

    if (cls == nullptr)
    {
        // 0x2098: FindClass başarısız
        // call stderr → "[native] FindClass FakeFly failed"
        g_flyInitState = 0;
        return false;
    }

    // call vtable+0xa8 → NewGlobalRef
    g_fakeFlyClass = reinterpret_cast<jclass>(env->NewGlobalRef(cls));

    // ── 9 adet GetMethodID (vtable+0x388) ─────────────────────
    // Assembly sırası (her biri: rdx=g_fakeFlyClass, r8=name, r9=sig):
    //  0x1f49 → cbCancelTravel  → [rip+0x282b519]
    //  0x1f71 → cbCancelJump    → [rip+0x282b4e9]
    //  0x1f99 → cbDisarmElytra  → [rip+0x282b4b9]
    //  0x1fc1 → cbSwapElytra    → [rip+0x282b489]
    //  0x1fe9 → cbStartFlying   → [rip+0x282b459]
    //  0x2011 → cbFirework      → [rip+0x282b429]
    //  0x2039 → cbMcFirework    → [rip+0x282b3d8]
    //  0x205a → cbJump          → [rip+0x282b3d8] (farklı offset)
    //  0x2082 → cbRotate        → [rip+0x282b3b9]
    g_cbCancelTravel  = env->GetMethodID(g_fakeFlyClass, "cbCancelTravel",  "()V");
    g_cbCancelJump    = env->GetMethodID(g_fakeFlyClass, "cbCancelJump",    "()V");
    g_cbDisarmElytra  = env->GetMethodID(g_fakeFlyClass, "cbDisarmElytra",  "()V");
    g_cbSwapElytra    = env->GetMethodID(g_fakeFlyClass, "cbSwapElytra",    "()V");
    g_cbStartFlying   = env->GetMethodID(g_fakeFlyClass, "cbStartFlying",   "()V");
    g_cbFirework      = env->GetMethodID(g_fakeFlyClass, "cbFirework",      "()V");
    g_cbMcFirework    = env->GetMethodID(g_fakeFlyClass, "cbMcFirework",    "()V");
    g_cbJump          = env->GetMethodID(g_fakeFlyClass, "cbJump",          "()V");
    g_cbRotate        = env->GetMethodID(g_fakeFlyClass, "cbRotate",        "()V");

    g_flyInitState = 2;
    return true;
}


// ============================================================
//  1. FakeFlyNative::nativeOnEnable
//  RVA: 0x1ec0
//
//  Assembly özeti:
//    cmp qword ptr [g_flyEnv], 0
//    je  → 0x1ef0 (ilk kez, init yap)
//    --- zaten init edilmiş:
//    mov [g_flyTickCount], 0   (0x282b567)
//    mov [g_flyState],     2   (0x282b559)
//    ret
//    --- 0x1ef0: FindClass + 9x GetMethodID
//    --- sonunda: jmp 0x1ed3 → tickCount=0, state=2
// ============================================================
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeOnEnable(
    JNIEnv*  env,
    jobject  obj)
{
    // cmp qword ptr [g_flyEnv], 0
    if (g_flyEnv != nullptr)
    {
        // Zaten init edilmiş — sadece sıfırla
        // mov [g_flyTickCount], 0
        g_flyTickCount = 0;
        // mov [g_flyState], 2
        g_flyState = 2;
        return;
    }

    // İlk kez — class + metodları yükle
    if (!fakeFlyInit(env, obj))
        return;  // FindClass başarısız

    // g_flyEnv ve g_flyObj'yi kaydet
    g_flyEnv = env;
    g_flyObj = env->NewGlobalRef(obj);

    // Sıfırla
    g_flyTickCount = 0;
    g_flyState     = 2;
}


// ============================================================
//  2. FakeFlyNative::nativeOnDisable
//  RVA: 0x20c0
//
//  Assembly (tam, 3 satır):
//    mov dword ptr [rip+0x282b37a], 0   → g_flyState=0
//    mov dword ptr [rip+0x282b36c], 0   → g_flyTickCount=0
//    ret
// ============================================================
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeOnDisable(
    JNIEnv*  /*env*/,
    jobject  /*obj*/)
{
    g_flyState     = 0;
    g_flyTickCount = 0;
}


// ============================================================
//  3. FakeFlyNative::nativeHandleTick
//  RVA: 0x20e0
//
//  Parametre sırası (x64 Windows ABI):
//    rcx = env
//    rdx = obj (JNI object)
//    r8b = isSprinting  (jboolean)
//    r9b = someFlag     (stack'ten: [rsp+0x80] sonradan ekleniyor)
//    stack+0x80 = isOnGround (jboolean)
//
//  Assembly özeti:
//    mov rdx,[g_flyEnv]
//    test rdx,rdx → je 0x2170 (init değil → ret)
//    mov eax,[rsp+0x80]   → isOnGround
//    or al, r9b           → isOnGround | someFlag
//    jne → 0x210b         → değil: state=0 + cbCancelTravel
//    test r8b,r8b → jne 0x2130 → isSprinting=true → sayaç artır
//
//    ── 0x210b (durdurma): ──
//    mov [g_flyState], 0
//    mov rax, vtable+0x468    → CallVoidMethod
//    mov r8, [g_flyObj]
//    jmp rax                  → cbCancelTravel(env, obj)
//
//    ── 0x2130 (isOnGround=true isSprinting=true): ──
//    add [g_flyTickCount], 1
//    cmp eax, 1 → jle → eşik geçilmedi, sadece cbRotate
//    eşik geçildi (tickCount > 1):
//      call vtable+0x468 → cbSwapElytra
//      mov [g_flyTickCount], 0
//      mov [g_flyState], 0
//      jmp cbRotate
// ============================================================
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleTick(
    JNIEnv*  env,
    jobject  obj,
    jboolean isSprinting,
    jboolean someFlag,
    jboolean isOnGround)
{
    // mov rdx,[g_flyEnv]; test rdx,rdx → je ret
    if (g_flyEnv == nullptr) return;

    // or al, r9b → isOnGround | someFlag
    bool groundOrFlag = (isOnGround != 0) || (someFlag != 0);

    if (!groundOrFlag)
    {
        // 0x210b: durdurma dalı
        // mov [g_flyState], 0
        g_flyState = 0;
        // jmp vtable+0x468 → CallVoidMethod(env, g_flyObj, cbCancelTravel)
        callVoid(env, g_flyObj, g_cbCancelTravel);
        return;
    }

    // test r8b,r8b → jne 0x2130 (isSprinting)
    if (isSprinting)
    {
        // 0x2130: sayaç artır
        // add eax,1; mov [g_flyTickCount], eax
        int tc = g_flyTickCount + 1;
        g_flyTickCount = tc;

        // cmp eax, 1 → jle (eşik geçilmedi)
        if (tc > 1)
        {
            // eşik aşıldı:
            // call vtable+0x468 → CallVoidMethod cbSwapElytra
            callVoid(env, g_flyObj, g_cbSwapElytra);
            // mov [g_flyTickCount], 0
            g_flyTickCount = 0;
            // mov [g_flyState], 0
            g_flyState = 0;
        }

        // her durumda cbRotate çağır (0x2115 → jmp vtable+0x468)
        callVoid(env, g_flyObj, g_cbRotate);
    }
    else
    {
        // isOnGround=true ama isSprinting=false:
        // sadece cbRotate
        callVoid(env, g_flyObj, g_cbRotate);
    }
}


// ============================================================
//  4. FakeFlyNative::nativeHandleMove
//  RVA: 0x2180
//  Assembly: ret — boş stub
// ============================================================
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleMove(
    JNIEnv*  /*env*/,
    jobject  /*obj*/)
{
    // ret — boş stub
}


// ============================================================
//  5. FakeFlyNative::nativeHandleTravel
//  RVA: 0x2190
//
//  Parametreler (stack'ten okunuyor):
//    rcx = env, rdx = obj (JNI), r8b = isTravelling
//    [rsp+0x70] = sideSpeed (int)   → edi
//    [rsp+0x78] = forwardSpeed (int) → r10d
//    [rsp+0x80] = yaw         (int) → eax
//    [rsp+0x88] = pitch       (int) → edx
//    [rsp+0x90] = onGround    (int) → esi
//    [rsp+0x98] = isFlying    (int) → ebp
//    [rsp+0xa0] = isInWater   (int) → r11d
//
//  Assembly özeti:
//    mov rcx,[g_flyEnv]
//    test rcx,rcx → je ret  (init değil)
//    test r8b,r8b → jne 0x21f0  (isTravelling=true → devam)
//    cmp esi,-1   → jne 0x21f0  (sideSpeed!=-1 → devam)
//    ret (her iki koşul false → erken çıkış)
//
//    ── 0x21f0: mantık dalları ──
//    setne/sete/cmovne/or → onGround + sideSpeed birleştirme
//    je 0x2300 → sıfır → sadece cbCancelJump
//    test r9b,r9b → jne 0x22cd → isElytraActive=true → cbRotate
//    test r11b,r11b / test bpl,bpl / setne dl → isFlying/isInWater
//    and r9d,edx → her ikisi de false ise
//
//    ── Temel yol (0x2239) ──
//    call vtable+0x468 → CallVoidMethod(env, g_flyObj, cbCancelTravel, sideSpeed)
//    call vtable+0x468 → CallVoidMethod(env, g_flyObj, cbDisarmElytra)
//    test bpl → isFlying?
//      true: travelTickCount++; cmp eax,2 → jle atla
//            > 2: cbFirework(env, g_flyObj); travelTickCount=0
//    call vtable+0x468 → cbCancelJump(env, g_flyObj, sideSpeed)
//
//    ── 0x22cd: cbRotate dalı ──
//    test dil → sideSpeed==0?
//      je ret
//    jne: cbStartFlying(env, g_flyObj)
//
//    ── 0x2300: sadece cbCancelJump ──
//    cbCancelJump(env, g_flyObj)
//
//    ── 0x2328: isFlying dalı ──
//    cbCancelTravel(env, g_flyObj, sideSpeed)
//    test bpl → isFlying?
//      travelTickCount++; jne cbStartFlying
//      cmp eax,2 → jle ret
//      cbMcFirework(env, g_flyObj); travelTickCount=0
// ============================================================
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleTravel(
    JNIEnv*  env,
    jobject  obj,
    jboolean isTravelling,
    jint     sideSpeed,
    jint     forwardSpeed,
    jint     yaw,
    jint     pitch,
    jint     onGround,
    jint     isFlying,
    jint     isInWater)
{
    // mov rcx,[g_flyEnv]; test rcx,rcx → je ret
    if (g_flyEnv == nullptr) return;

    // test r8b,r8b → jne devam; cmp esi,-1 → jne devam
    if (!isTravelling && sideSpeed == -1) return;

    // ── onGround + sideSpeed birleştirme ──
    // setne r12b (sideSpeed!=0)
    // sete dl (sideSpeed==0)
    // or al,r10b → forwardSpeed
    // cmovne eax,edx
    bool hasSideOrForward = (sideSpeed != 0) || (forwardSpeed != 0);
    bool sideZero         = (sideSpeed == 0);

    // je 0x2300 → hasSideOrForward=false → sadece cbCancelJump
    if (!hasSideOrForward)
    {
        // 0x2300: cbCancelJump(env, g_flyObj)
        callVoid(env, g_flyObj, g_cbCancelJump);
        return;
    }

    // test r9b,r9b → isElytraActive
    bool isElytraActive = (pitch != 0);  // r9 = pitch slot
    if (isElytraActive)
    {
        // 0x22cd:
        if (!sideZero)
            callVoid(env, g_flyObj, g_cbStartFlying);
        return;
    }

    // ── isFlying ve isInWater birleştirme ──
    // sete r9b (isFlying==0), setne dl (isInWater!=0)
    // and r9d,edx
    bool flyAndWater = (isFlying == 0) && (isInWater != 0);

    if (!flyAndWater)
    {
        // 0x2328: isFlying dalı
        // cbCancelTravel(env, g_flyObj, sideSpeed)
        callVoid(env, g_flyObj, g_cbCancelTravel);

        if (isFlying != 0)
        {
            g_travelTickCount++;
            if (!sideZero)
            {
                callVoid(env, g_flyObj, g_cbStartFlying);
                return;
            }
            if (g_travelTickCount > 2)
            {
                callVoid(env, g_flyObj, g_cbMcFirework);
                g_travelTickCount = 0;
            }
        }
        // cbRotate yolu
        if (!sideZero)
            callVoid(env, g_flyObj, g_cbRotate);
        return;
    }

    // ── Temel yol (0x2239) ──
    // cbCancelTravel(env, g_flyObj, sideSpeed)
    callVoid(env, g_flyObj, g_cbCancelTravel);
    // cbDisarmElytra
    callVoid(env, g_flyObj, g_cbDisarmElytra);

    if (isFlying != 0)
    {
        g_travelTickCount++;
        if (sideZero && g_travelTickCount > 2)
        {
            callVoid(env, g_flyObj, g_cbFirework);
            g_travelTickCount = 0;
        }
    }

    // cbCancelJump(env, g_flyObj, sideSpeed)
    callVoid(env, g_flyObj, g_cbCancelJump);

    // 0x22cd yolu
    if (!sideZero)
        callVoid(env, g_flyObj, g_cbRotate);
}


// ============================================================
//  6. FakeFlyNative::nativeHandleJump
//  RVA: 0x2390
//
//  Assembly (tam — 6 satır):
//    mov rdx,[rip+0x282b0f1]   → g_flyObj
//    test rdx,rdx → je ret     → g_flyObj null ise çık
//    mov rax,[rcx]             → env vtable
//    mov r8,[rip+0x282b0d2]    → kaydedilmiş arg (g_flyObj)
//    mov rax,[rax+0x468]       → CallVoidMethod pointer
//    jmp rax                   → tail-call cbCancelJump
// ============================================================
extern "C" JNIEXPORT void JNICALL
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleJump(
    JNIEnv*  env,
    jobject  obj)
{
    // test rdx,rdx → je ret
    if (g_flyObj == nullptr) return;

    // jmp vtable+0x468 → cbCancelJump (tail-call)
    callVoid(env, g_flyObj, g_cbCancelJump);
}
