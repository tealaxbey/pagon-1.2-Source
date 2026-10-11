; === Java_org_itsmahir_pagon_nativep_NativeAPI_nativeGetValue ===
; RVA: 0x1b20

  0x35a6e1b20  push       r15
  0x35a6e1b22  push       r14
  0x35a6e1b24  push       r13
  0x35a6e1b26  push       r12
  0x35a6e1b28  push       rbp
  0x35a6e1b29  push       rdi
  0x35a6e1b2a  push       rsi
  0x35a6e1b2b  push       rbx
  0x35a6e1b2c  sub        rsp, 0x38
  0x35a6e1b30  mov        rax, qword ptr [rcx]
  0x35a6e1b33  mov        rsi, rcx
  0x35a6e1b36  mov        rdx, r8
  0x35a6e1b39  mov        rbx, r9
  0x35a6e1b3c  mov        qword ptr [rsp + 0x20], r8
  0x35a6e1b41  mov        qword ptr [rsp + 0x28], r9
  0x35a6e1b46  xor        r8d, r8d
  0x35a6e1b49  call       qword ptr [rax + 0x548]
  0x35a6e1b4f  xor        r8d, r8d
  0x35a6e1b52  mov        rdx, rbx
  0x35a6e1b55  mov        rcx, rsi
  0x35a6e1b58  mov        rdi, rax
  0x35a6e1b5b  mov        rax, qword ptr [rsi]
  0x35a6e1b5e  call       qword ptr [rax + 0x548]
  0x35a6e1b64  mov        r12d, dword ptr [rip + 0xb4b5]
  0x35a6e1b6b  mov        r13, rax
  0x35a6e1b6e  test       r12d, r12d
  0x35a6e1b71  jle        0x35a6e1c60
  0x35a6e1b77  xor        ebx, ebx
  0x35a6e1b79  xor        r14d, r14d
  0x35a6e1b7c  lea        rbp, [rip + 0xb4bd]
  0x35a6e1b83  jmp        0x35a6e1b9c
  0x35a6e1b85  nop        dword ptr [rax]
  0x35a6e1b88  add        r14d, 1
  0x35a6e1b8c  add        rbx, 0x50408
  0x35a6e1b93  cmp        r14d, r12d
  0x35a6e1b96  je         0x35a6e1c60
  0x35a6e1b9c  lea        rcx, [rbp + rbx]
  0x35a6e1ba1  mov        rdx, rdi
  0x35a6e1ba4  call       0x35a6e8750
  0x35a6e1ba9  test       eax, eax
  0x35a6e1bab  jne        0x35a6e1b88
  0x35a6e1bad  movsxd     r8, r14d
  0x35a6e1bb0  lea        r15, [rbp + rbx + 0x308]
  0x35a6e1bb8  xor        r14d, r14d
  0x35a6e1bbb  imul       r8, r8, 0x50408
  0x35a6e1bc2  movsxd     r12, dword ptr [rbp + r8 + 0x304]
  0x35a6e1bca  test       r12d, r12d
  0x35a6e1bcd  jg         0x35a6e1be8
  0x35a6e1bcf  jmp        0x35a6e1c60
  0x35a6e1bd4  nop        dword ptr [rax]
  0x35a6e1bd8  add        r14, 1
  0x35a6e1bdc  add        r15, 0x100
  0x35a6e1be3  cmp        r12, r14
  0x35a6e1be6  je         0x35a6e1c60
  0x35a6e1be8  mov        rdx, r13
  0x35a6e1beb  mov        rcx, r15
  0x35a6e1bee  call       0x35a6e8750
  0x35a6e1bf3  test       eax, eax
  0x35a6e1bf5  jne        0x35a6e1bd8
  0x35a6e1bf7  lea        r8, [r14 + 0xc3]
  0x35a6e1bfe  mov        rcx, rsi
  0x35a6e1c01  shl        r8, 8
  0x35a6e1c05  lea        rax, [rbx + r8 + 8]
  0x35a6e1c0a  lea        rdx, [rbp + rax]
  0x35a6e1c0f  mov        rax, qword ptr [rsi]
  0x35a6e1c12  call       qword ptr [rax + 0x538]
  0x35a6e1c18  mov        rdx, qword ptr [rsp + 0x20]
  0x35a6e1c1d  mov        r8, rdi
  0x35a6e1c20  mov        rcx, rsi
  0x35a6e1c23  mov        rbx, rax
  0x35a6e1c26  mov        rax, qword ptr [rsi]
  0x35a6e1c29  call       qword ptr [rax + 0x550]
  0x35a6e1c2f  mov        rax, qword ptr [rsi]
  0x35a6e1c32  mov        r8, r13
  0x35a6e1c35  mov        rcx, rsi
  0x35a6e1c38  mov        rdx, qword ptr [rsp + 0x28]
  0x35a6e1c3d  call       qword ptr [rax + 0x550]
  0x35a6e1c43  test       rbx, rbx
  0x35a6e1c46  je         0x35a6e1c88
  0x35a6e1c48  mov        rax, rbx
  0x35a6e1c4b  add        rsp, 0x38
  0x35a6e1c4f  pop        rbx
  0x35a6e1c50  pop        rsi
  0x35a6e1c51  pop        rdi
  0x35a6e1c52  pop        rbp
  0x35a6e1c53  pop        r12
  0x35a6e1c55  pop        r13
  0x35a6e1c57  pop        r14
  0x35a6e1c59  pop        r15
  0x35a6e1c5b  ret        
