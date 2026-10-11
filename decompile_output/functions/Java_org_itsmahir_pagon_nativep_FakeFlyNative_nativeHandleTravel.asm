; === Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleTravel ===
; RVA: 0x2190

  0x35a6e2190  push       r12
  0x35a6e2192  push       rbp
  0x35a6e2193  push       rdi
  0x35a6e2194  push       rsi
  0x35a6e2195  push       rbx
  0x35a6e2196  sub        rsp, 0x20
  0x35a6e219a  mov        edi, dword ptr [rsp + 0x70]
  0x35a6e219e  mov        r10d, dword ptr [rsp + 0x78]
  0x35a6e21a3  mov        eax, dword ptr [rsp + 0x80]
  0x35a6e21aa  mov        edx, dword ptr [rsp + 0x88]
  0x35a6e21b1  mov        esi, dword ptr [rsp + 0x90]
  0x35a6e21b8  mov        ebp, dword ptr [rsp + 0x98]
  0x35a6e21bf  mov        r11d, dword ptr [rsp + 0xa0]
  0x35a6e21c7  mov        rbx, rcx
  0x35a6e21ca  mov        rcx, qword ptr [rip + 0x282b2b7]
  0x35a6e21d1  test       rcx, rcx
  0x35a6e21d4  je         0x35a6e21e0
  0x35a6e21d6  test       r8b, r8b
  0x35a6e21d9  jne        0x35a6e21f0
  0x35a6e21db  cmp        esi, -1
  0x35a6e21de  jne        0x35a6e21f0
  0x35a6e21e0  add        rsp, 0x20
  0x35a6e21e4  pop        rbx
  0x35a6e21e5  pop        rsi
  0x35a6e21e6  pop        rdi
  0x35a6e21e7  pop        rbp
  0x35a6e21e8  pop        r12
  0x35a6e21ea  ret        
