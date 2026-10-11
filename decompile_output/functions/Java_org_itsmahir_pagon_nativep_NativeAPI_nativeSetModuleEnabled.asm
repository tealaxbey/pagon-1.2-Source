; === Java_org_itsmahir_pagon_nativep_NativeAPI_nativeSetModuleEnabled ===
; RVA: 0x1500

  0x35a6e1500  push       r15
  0x35a6e1502  push       r14
  0x35a6e1504  push       r13
  0x35a6e1506  push       r12
  0x35a6e1508  push       rbp
  0x35a6e1509  push       rdi
  0x35a6e150a  push       rsi
  0x35a6e150b  push       rbx
  0x35a6e150c  sub        rsp, 0x28
  0x35a6e1510  mov        rax, qword ptr [rcx]
  0x35a6e1513  mov        rbp, r8
  0x35a6e1516  mov        rdi, rcx
  0x35a6e1519  mov        r12d, r9d
  0x35a6e151c  xor        r8d, r8d
  0x35a6e151f  mov        rdx, rbp
  0x35a6e1522  call       qword ptr [rax + 0x548]
  0x35a6e1528  mov        r15d, dword ptr [rip + 0xbaf1]
  0x35a6e152f  mov        rsi, rax
  0x35a6e1532  test       r15d, r15d
  0x35a6e1535  jle        0x35a6e1580
  0x35a6e1537  lea        r13, [rip + 0xbb02]
  0x35a6e153e  xor        ebx, ebx
  0x35a6e1540  mov        r14, r13
  0x35a6e1543  jmp        0x35a6e1557
  0x35a6e1545  nop        dword ptr [rax]
  0x35a6e1548  add        ebx, 1
  0x35a6e154b  add        r14, 0x50408
  0x35a6e1552  cmp        ebx, r15d
  0x35a6e1555  je         0x35a6e1580
  0x35a6e1557  mov        rdx, rsi
  0x35a6e155a  mov        rcx, r14
  0x35a6e155d  call       0x35a6e8750
  0x35a6e1562  test       eax, eax
  0x35a6e1564  jne        0x35a6e1548
  0x35a6e1566  movsxd     rbx, ebx
  0x35a6e1569  xor        eax, eax
  0x35a6e156b  imul       rbx, rbx, 0x50408
  0x35a6e1572  test       r12b, r12b
  0x35a6e1575  setne      al
  0x35a6e1578  mov        dword ptr [r13 + rbx + 0x300], eax
  0x35a6e1580  mov        rax, qword ptr [rdi]
  0x35a6e1583  mov        r8, rsi
  0x35a6e1586  mov        rdx, rbp
  0x35a6e1589  mov        rcx, rdi
  0x35a6e158c  mov        rax, qword ptr [rax + 0x550]
  0x35a6e1593  add        rsp, 0x28
  0x35a6e1597  pop        rbx
  0x35a6e1598  pop        rsi
  0x35a6e1599  pop        rdi
  0x35a6e159a  pop        rbp
  0x35a6e159b  pop        r12
  0x35a6e159d  pop        r13
  0x35a6e159f  pop        r14
  0x35a6e15a1  pop        r15
  0x35a6e15a3  jmp        rax
  0x35a6e15a6  nop        word ptr cs:[rax + rax]
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
