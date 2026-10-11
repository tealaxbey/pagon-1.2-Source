; === Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleTick ===
; RVA: 0x20e0

  0x35a6e20e0  sub        rsp, 0x38
  0x35a6e20e4  mov        rdx, qword ptr [rip + 0x282b39d]
  0x35a6e20eb  mov        eax, dword ptr [rsp + 0x80]
  0x35a6e20f2  test       rdx, rdx
  0x35a6e20f5  je         0x35a6e2170
  0x35a6e20f7  mov        r10, qword ptr [rcx]
  0x35a6e20fa  or         al, r9b
  0x35a6e20fd  mov        r11, qword ptr [r10 + 0x468]
  0x35a6e2104  jne        0x35a6e210b
  0x35a6e2106  test       r8b, r8b
  0x35a6e2109  jne        0x35a6e2130
  0x35a6e210b  mov        dword ptr [rip + 0x282b32f], 0
  0x35a6e2115  mov        rax, qword ptr [r10 + 0x468]
  0x35a6e211c  mov        r8, qword ptr [rip + 0x282b325]
  0x35a6e2123  add        rsp, 0x38
  0x35a6e2127  jmp        rax
  0x35a6e212a  nop        word ptr [rax + rax]
  0x35a6e2130  mov        eax, dword ptr [rip + 0x282b30e]
  0x35a6e2136  add        eax, 1
  0x35a6e2139  mov        dword ptr [rip + 0x282b305], eax
  0x35a6e213f  cmp        eax, 1
  0x35a6e2142  jle        0x35a6e2115
  0x35a6e2144  mov        qword ptr [rsp + 0x28], rcx
  0x35a6e2149  mov        r8, qword ptr [rip + 0x282b320]
  0x35a6e2150  call       r11
  0x35a6e2153  mov        rcx, qword ptr [rsp + 0x28]
  0x35a6e2158  mov        rdx, qword ptr [rip + 0x282b329]
  0x35a6e215f  mov        dword ptr [rip + 0x282b2db], 0
  0x35a6e2169  mov        r10, qword ptr [rcx]
  0x35a6e216c  jmp        0x35a6e2115
  0x35a6e216e  nop        
  0x35a6e2170  add        rsp, 0x38
  0x35a6e2174  ret        
