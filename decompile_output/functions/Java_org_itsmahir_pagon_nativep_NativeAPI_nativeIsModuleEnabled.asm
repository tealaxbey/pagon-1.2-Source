; === Java_org_itsmahir_pagon_nativep_NativeAPI_nativeIsModuleEnabled ===
; RVA: 0x15b0

  0x35a6e15b0  push       r14
  0x35a6e15b2  push       r13
  0x35a6e15b4  push       r12
  0x35a6e15b6  push       rbp
  0x35a6e15b7  push       rdi
  0x35a6e15b8  push       rsi
  0x35a6e15b9  push       rbx
  0x35a6e15ba  sub        rsp, 0x20
  0x35a6e15be  mov        rax, qword ptr [rcx]
  0x35a6e15c1  mov        r14, r8
  0x35a6e15c4  mov        r13, rcx
  0x35a6e15c7  xor        r8d, r8d
  0x35a6e15ca  mov        rdx, r14
  0x35a6e15cd  call       qword ptr [rax + 0x548]
  0x35a6e15d3  mov        ebp, dword ptr [rip + 0xba47]
  0x35a6e15d9  mov        rdi, rax
  0x35a6e15dc  test       ebp, ebp
  0x35a6e15de  jle        0x35a6e1650
  0x35a6e15e0  lea        r12, [rip + 0xba59]
  0x35a6e15e7  xor        ebx, ebx
  0x35a6e15e9  mov        rsi, r12
  0x35a6e15ec  jmp        0x35a6e15fe
  0x35a6e15ee  nop        
  0x35a6e15f0  add        ebx, 1
  0x35a6e15f3  add        rsi, 0x50408
  0x35a6e15fa  cmp        ebx, ebp
  0x35a6e15fc  je         0x35a6e1650
  0x35a6e15fe  mov        rdx, rdi
  0x35a6e1601  mov        rcx, rsi
  0x35a6e1604  call       0x35a6e8750
  0x35a6e1609  test       eax, eax
  0x35a6e160b  jne        0x35a6e15f0
  0x35a6e160d  movsxd     rbx, ebx
  0x35a6e1610  mov        rax, qword ptr [r13]
  0x35a6e1614  mov        r8, rdi
  0x35a6e1617  mov        rdx, r14
  0x35a6e161a  imul       rbx, rbx, 0x50408
  0x35a6e1621  mov        rcx, r13
  0x35a6e1624  call       qword ptr [rax + 0x550]
  0x35a6e162a  mov        eax, dword ptr [r12 + rbx + 0x300]
  0x35a6e1632  test       eax, eax
  0x35a6e1634  setne      al
  0x35a6e1637  add        rsp, 0x20
  0x35a6e163b  pop        rbx
  0x35a6e163c  pop        rsi
  0x35a6e163d  pop        rdi
  0x35a6e163e  pop        rbp
  0x35a6e163f  pop        r12
  0x35a6e1641  pop        r13
  0x35a6e1643  pop        r14
  0x35a6e1645  ret        
