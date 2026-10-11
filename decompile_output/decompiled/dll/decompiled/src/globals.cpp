// ============================================================
//  globals.cpp — global değişken tanımları
// ============================================================

#include "../include/globals.h"

// NativeAPI
ModuleEntry g_modules[MAX_MODULES]  = {};
int         g_moduleCount           = 0;
int         g_attachCount           = 0;

// FakeFlyNative state
JNIEnv*   g_flyEnv        = nullptr;
jobject   g_flyObj         = nullptr;
int       g_flyState       = 0;
int       g_flyTickCount   = 0;
int       g_flyInitState   = 0;

// FakeFly Java referansları
jclass    g_fakeFlyClass   = nullptr;
jmethodID g_cbCancelTravel = nullptr;
jmethodID g_cbCancelJump   = nullptr;
jmethodID g_cbDisarmElytra = nullptr;
jmethodID g_cbSwapElytra   = nullptr;
jmethodID g_cbStartFlying  = nullptr;
jmethodID g_cbFirework     = nullptr;
jmethodID g_cbMcFirework   = nullptr;
jmethodID g_cbJump         = nullptr;
jmethodID g_cbRotate       = nullptr;

// Travel/jump sayaçları
int g_travelTickCount = 0;
int g_jumpTickCount   = 0;
