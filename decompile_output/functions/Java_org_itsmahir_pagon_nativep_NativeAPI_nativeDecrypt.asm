; === Java_org_itsmahir_pagon_nativep_NativeAPI_nativeDecrypt ===
; RVA: 0x1d90

  0x35a6e1d90  push       r13
  0x35a6e1d92  push       r12
  0x35a6e1d94  push       rbp
  0x35a6e1d95  push       rdi
  0x35a6e1d96  push       rsi
  0x35a6e1d97  push       rbx
  0x35a6e1d98  sub        rsp, 0x28
  0x35a6e1d9c  mov        rax, qword ptr [rcx]
  0x35a6e1d9f  mov        r12, r8
  0x35a6e1da2  mov        rsi, rcx
  0x35a6e1da5  mov        ebx, r9d
  0x35a6e1da8  xor        r8d, r8d
  0x35a6e1dab  mov        rdx, r12
  0x35a6e1dae  call       qword ptr [rax + 0x5c0]
  0x35a6e1db4  mov        edx, ebx
  0x35a6e1db6  mov        rcx, rsi
  0x35a6e1db9  mov        rbp, rax
  0x35a6e1dbc  mov        rax, qword ptr [rsi]
  0x35a6e1dbf  call       qword ptr [rax + 0x580]
  0x35a6e1dc5  xor        r8d, r8d
  0x35a6e1dc8  mov        rcx, rsi
  0x35a6e1dcb  mov        rdi, rax
  0x35a6e1dce  mov        rax, qword ptr [rsi]
  0x35a6e1dd1  mov        rdx, rdi
  0x35a6e1dd4  call       qword ptr [rax + 0x5c0]
  0x35a6e1dda  mov        r13, rax
  0x35a6e1ddd  test       ebx, ebx
  0x35a6e1ddf  jle        0x35a6e1e47
  0x35a6e1de1  movzx      eax, byte ptr [rbp]
  0x35a6e1de5  xor        eax, 0x77
  0x35a6e1de8  mov        byte ptr [r13], al
  0x35a6e1dec  cmp        ebx, 1
  0x35a6e1def  je         0x35a6e1e47
  0x35a6e1df1  mov        r9d, 0xd
  0x35a6e1df7  mov        r8d, 0xa
  0x35a6e1dfd  mov        ecx, 1
  0x35a6e1e02  mov        edx, 1
  0x35a6e1e07  lea        r10, [rip + 0x8212]
  0x35a6e1e0e  nop        
  0x35a6e1e10  mov        eax, edx
  0x35a6e1e12  add        edx, 1
  0x35a6e1e15  and        eax, 0x3f
  0x35a6e1e18  movzx      r11d, byte ptr [r10 + rax]
  0x35a6e1e1d  xor        r11d, r9d
  0x35a6e1e20  add        r9d, 0xd
  0x35a6e1e24  mov        eax, r11d
  0x35a6e1e27  mov        r11d, r8d
  0x35a6e1e2a  add        r8d, 7
  0x35a6e1e2e  and        r11d, 0x3f
  0x35a6e1e32  xor        al, byte ptr [r10 + r11]
  0x35a6e1e36  xor        al, byte ptr [rbp + rcx]
  0x35a6e1e3a  mov        byte ptr [r13 + rcx], al
  0x35a6e1e3f  add        rcx, 1
  0x35a6e1e43  cmp        ebx, edx
  0x35a6e1e45  jne        0x35a6e1e10
  0x35a6e1e47  mov        rax, qword ptr [rsi]
  0x35a6e1e4a  mov        r8, rbp
  0x35a6e1e4d  mov        rdx, r12
  0x35a6e1e50  mov        rcx, rsi
  0x35a6e1e53  mov        r9d, 2
  0x35a6e1e59  call       qword ptr [rax + 0x600]
  0x35a6e1e5f  mov        rax, qword ptr [rsi]
  0x35a6e1e62  xor        r9d, r9d
  0x35a6e1e65  mov        r8, r13
  0x35a6e1e68  mov        rdx, rdi
  0x35a6e1e6b  mov        rcx, rsi
  0x35a6e1e6e  call       qword ptr [rax + 0x600]
  0x35a6e1e74  mov        rax, rdi
  0x35a6e1e77  add        rsp, 0x28
  0x35a6e1e7b  pop        rbx
  0x35a6e1e7c  pop        rsi
  0x35a6e1e7d  pop        rdi
  0x35a6e1e7e  pop        rbp
  0x35a6e1e7f  pop        r12
  0x35a6e1e81  pop        r13
  0x35a6e1e83  ret        
