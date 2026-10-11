; === Java_org_itsmahir_pagon_nativep_NativeAPI_nativeSetValue ===
; RVA: 0x19b0

  0x35a6e19b0  push       r15
  0x35a6e19b2  push       r14
  0x35a6e19b4  push       r13
  0x35a6e19b6  push       r12
  0x35a6e19b8  push       rbp
  0x35a6e19b9  push       rdi
  0x35a6e19ba  push       rsi
  0x35a6e19bb  push       rbx
  0x35a6e19bc  sub        rsp, 0x48
  0x35a6e19c0  mov        rbx, qword ptr [rsp + 0xb0]
  0x35a6e19c8  mov        rax, qword ptr [rcx]
  0x35a6e19cb  mov        rsi, rcx
  0x35a6e19ce  mov        r15, r9
  0x35a6e19d1  mov        qword ptr [rsp + 0x30], r9
  0x35a6e19d6  mov        rdx, r8
  0x35a6e19d9  mov        qword ptr [rsp + 0x28], r8
  0x35a6e19de  xor        r8d, r8d
  0x35a6e19e1  mov        qword ptr [rsp + 0x38], rbx
  0x35a6e19e6  call       qword ptr [rax + 0x548]
  0x35a6e19ec  xor        r8d, r8d
  0x35a6e19ef  mov        rdx, r15
  0x35a6e19f2  mov        rcx, rsi
  0x35a6e19f5  mov        rdi, rax
  0x35a6e19f8  mov        rax, qword ptr [rsi]
  0x35a6e19fb  call       qword ptr [rax + 0x548]
  0x35a6e1a01  xor        r8d, r8d
  0x35a6e1a04  mov        rdx, rbx
  0x35a6e1a07  mov        rcx, rsi
  0x35a6e1a0a  mov        r13, rax
  0x35a6e1a0d  mov        rax, qword ptr [rsi]
  0x35a6e1a10  call       qword ptr [rax + 0x548]
  0x35a6e1a16  mov        r12d, dword ptr [rip + 0xb603]
  0x35a6e1a1d  mov        qword ptr [rsp + 0x20], rax
  0x35a6e1a22  test       r12d, r12d
  0x35a6e1a25  jle        0x35a6e1acc
  0x35a6e1a2b  xor        ebx, ebx
  0x35a6e1a2d  xor        r14d, r14d
  0x35a6e1a30  lea        rbp, [rip + 0xb609]
  0x35a6e1a37  jmp        0x35a6e1a50
  0x35a6e1a39  nop        dword ptr [rax]
  0x35a6e1a40  add        r14d, 1
  0x35a6e1a44  add        rbx, 0x50408
  0x35a6e1a4b  cmp        r14d, r12d
  0x35a6e1a4e  je         0x35a6e1acc
  0x35a6e1a50  lea        rcx, [rbp + rbx]
  0x35a6e1a55  mov        rdx, rdi
  0x35a6e1a58  call       0x35a6e8750
  0x35a6e1a5d  test       eax, eax
  0x35a6e1a5f  jne        0x35a6e1a40
  0x35a6e1a61  movsxd     r8, r14d
  0x35a6e1a64  lea        r15, [rbp + rbx + 0x308]
  0x35a6e1a6c  xor        r14d, r14d
  0x35a6e1a6f  imul       r8, r8, 0x50408
  0x35a6e1a76  movsxd     r12, dword ptr [rbp + r8 + 0x304]
  0x35a6e1a7e  test       r12d, r12d
  0x35a6e1a81  jg         0x35a6e1a98
  0x35a6e1a83  jmp        0x35a6e1acc
  0x35a6e1a85  nop        dword ptr [rax]
  0x35a6e1a88  add        r14, 1
  0x35a6e1a8c  add        r15, 0x100
  0x35a6e1a93  cmp        r12, r14
  0x35a6e1a96  je         0x35a6e1acc
  0x35a6e1a98  mov        rdx, r13
  0x35a6e1a9b  mov        rcx, r15
  0x35a6e1a9e  call       0x35a6e8750
  0x35a6e1aa3  test       eax, eax
  0x35a6e1aa5  jne        0x35a6e1a88
  0x35a6e1aa7  lea        r8, [r14 + 0xc3]
  0x35a6e1aae  mov        rdx, qword ptr [rsp + 0x20]
  0x35a6e1ab3  shl        r8, 8
  0x35a6e1ab7  lea        rax, [rbx + r8 + 8]
  0x35a6e1abc  mov        r8d, 0xff
  0x35a6e1ac2  lea        rcx, [rbp + rax]
  0x35a6e1ac7  call       0x35a6e8770
  0x35a6e1acc  mov        rax, qword ptr [rsi]
  0x35a6e1acf  mov        rdx, qword ptr [rsp + 0x28]
  0x35a6e1ad4  mov        r8, rdi
  0x35a6e1ad7  mov        rcx, rsi
  0x35a6e1ada  call       qword ptr [rax + 0x550]
  0x35a6e1ae0  mov        rax, qword ptr [rsi]
  0x35a6e1ae3  mov        r8, r13
  0x35a6e1ae6  mov        rcx, rsi
  0x35a6e1ae9  mov        rdx, qword ptr [rsp + 0x30]
  0x35a6e1aee  call       qword ptr [rax + 0x550]
  0x35a6e1af4  mov        rax, qword ptr [rsi]
  0x35a6e1af7  mov        r8, qword ptr [rsp + 0x20]
  0x35a6e1afc  mov        rcx, rsi
  0x35a6e1aff  mov        rdx, qword ptr [rsp + 0x38]
  0x35a6e1b04  mov        rax, qword ptr [rax + 0x550]
  0x35a6e1b0b  add        rsp, 0x48
  0x35a6e1b0f  pop        rbx
  0x35a6e1b10  pop        rsi
  0x35a6e1b11  pop        rdi
  0x35a6e1b12  pop        rbp
  0x35a6e1b13  pop        r12
  0x35a6e1b15  pop        r13
  0x35a6e1b17  pop        r14
  0x35a6e1b19  pop        r15
  0x35a6e1b1b  jmp        rax
  0x35a6e1b1e  nop        
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
