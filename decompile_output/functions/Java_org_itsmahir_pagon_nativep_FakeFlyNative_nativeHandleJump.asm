; === Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleJump ===
; RVA: 0x2390

  0x35a6e2390  mov        rdx, qword ptr [rip + 0x282b0f1]
  0x35a6e2397  test       rdx, rdx
  0x35a6e239a  je         0x35a6e23b0
  0x35a6e239c  mov        rax, qword ptr [rcx]
  0x35a6e239f  mov        r8, qword ptr [rip + 0x282b0d2]
  0x35a6e23a6  mov        rax, qword ptr [rax + 0x468]
  0x35a6e23ad  jmp        rax
  0x35a6e23b0  ret        
