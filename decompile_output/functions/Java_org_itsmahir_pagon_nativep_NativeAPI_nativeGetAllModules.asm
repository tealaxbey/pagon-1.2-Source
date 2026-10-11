; === Java_org_itsmahir_pagon_nativep_NativeAPI_nativeGetAllModules ===
; RVA: 0x1cf0

  0x35a6e1cf0  push       rbp
  0x35a6e1cf1  push       rdi
  0x35a6e1cf2  push       rsi
  0x35a6e1cf3  push       rbx
  0x35a6e1cf4  sub        rsp, 0x28
  0x35a6e1cf8  lea        rdx, [rip + 0x8302]
  0x35a6e1cff  mov        rax, qword ptr [rcx]
  0x35a6e1d02  mov        rsi, qword ptr [rax + 0x560]
  0x35a6e1d09  mov        rbx, rcx
  0x35a6e1d0c  call       qword ptr [rax + 0x30]
  0x35a6e1d0f  mov        edx, dword ptr [rip + 0xb30b]
  0x35a6e1d15  xor        r9d, r9d
  0x35a6e1d18  mov        rcx, rbx
  0x35a6e1d1b  mov        r8, rax
  0x35a6e1d1e  call       rsi
  0x35a6e1d20  mov        rbp, rax
  0x35a6e1d23  mov        eax, dword ptr [rip + 0xb2f7]
  0x35a6e1d29  test       eax, eax
  0x35a6e1d2b  jle        0x35a6e1d77
  0x35a6e1d2d  lea        rdi, [rip + 0xb30c]
  0x35a6e1d34  xor        esi, esi
  0x35a6e1d36  nop        word ptr cs:[rax + rax]
  0x35a6e1d40  mov        rax, qword ptr [rbx]
  0x35a6e1d43  mov        rdx, rdi
  0x35a6e1d46  mov        rcx, rbx
  0x35a6e1d49  add        rdi, 0x50408
  0x35a6e1d50  call       qword ptr [rax + 0x538]
  0x35a6e1d56  mov        r10, qword ptr [rbx]
  0x35a6e1d59  mov        r8d, esi
  0x35a6e1d5c  mov        rdx, rbp
  0x35a6e1d5f  mov        r9, rax
  0x35a6e1d62  mov        rcx, rbx
  0x35a6e1d65  add        esi, 1
  0x35a6e1d68  call       qword ptr [r10 + 0x570]
  0x35a6e1d6f  cmp        dword ptr [rip + 0xb2ab], esi
  0x35a6e1d75  jg         0x35a6e1d40
  0x35a6e1d77  mov        rax, rbp
  0x35a6e1d7a  add        rsp, 0x28
  0x35a6e1d7e  pop        rbx
  0x35a6e1d7f  pop        rsi
  0x35a6e1d80  pop        rdi
  0x35a6e1d81  pop        rbp
  0x35a6e1d82  ret        
