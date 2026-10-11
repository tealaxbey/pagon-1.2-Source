cmake_minimum_required(VERSION 3.16)
project(pagon_native)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# ── JNI başlık dosyaları ─────────────────────────────────────
find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})
include_directories(include)

# ── Kaynak dosyaları ─────────────────────────────────────────
set(SOURCES
    src/globals.cpp
    src/dllmain.cpp
    src/internal_helpers.cpp
    src/NativeAPI.cpp
    src/FakeFlyNative.cpp
)

# ── DLL hedefi ───────────────────────────────────────────────
add_library(pagon_native SHARED ${SOURCES})

target_link_libraries(pagon_native ${JNI_LIBRARIES})

# ── Derleyici seçenekleri ─────────────────────────────────────
if(MSVC)
    target_compile_options(pagon_native PRIVATE /W3 /O2 /EHsc)
    # DLL export için
    target_compile_definitions(pagon_native PRIVATE _WINDLL)
else()
    # MinGW GCC (orijinal binary GCC 13-win32 ile derlenmiş)
    target_compile_options(pagon_native PRIVATE
        -Wall -O2 -m64
        -fexceptions
        -fno-rtti
    )
    target_link_options(pagon_native PRIVATE
        -Wl,--kill-at          # __stdcall süslemelerini kaldır
        -shared
    )
endif()

# ── Windows x64 hedef platformu ──────────────────────────────
if(WIN32)
    target_compile_definitions(pagon_native PRIVATE
        WIN32_LEAN_AND_MEAN
        NOMINMAX
    )
    target_link_libraries(pagon_native kernel32 msvcrt)
endif()
