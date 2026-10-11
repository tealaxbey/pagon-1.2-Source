; === Java_org_itsmahir_pagon_nativep_NativeAPI_nativeRegisterModule ===
; RVA: 0x1390

  0x35a6e1390  push       r15
  0x35a6e1392  push       r14
  0x35a6e1394  push       r13
  0x35a6e1396  push       r12
  0x35a6e1398  push       rbp
  0x35a6e1399  push       rdi
  0x35a6e139a  push       rsi
  0x35a6e139b  push       rbx
  0x35a6e139c  sub        rsp, 0x48
  0x35a6e13a0  mov        rax, qword ptr [rcx]
  0x35a6e13a3  mov        r15, qword ptr [rsp + 0xb0]
  0x35a6e13ab  mov        r12, r8
  0x35a6e13ae  mov        rbx, rcx
  0x35a6e13b1  mov        rbp, r9
  0x35a6e13b4  xor        r8d, r8d
  0x35a6e13b7  mov        rdx, r12
  0x35a6e13ba  call       qword ptr [rax + 0x548]
  0x35a6e13c0  xor        r8d, r8d
  0x35a6e13c3  mov        rdx, rbp
  0x35a6e13c6  mov        rcx, rbx
  0x35a6e13c9  mov        rsi, rax
  0x35a6e13cc  mov        rax, qword ptr [rbx]
  0x35a6e13cf  call       qword ptr [rax + 0x548]
  0x35a6e13d5  xor        r8d, r8d
  0x35a6e13d8  mov        rdx, r15
  0x35a6e13db  mov        rcx, rbx
  0x35a6e13de  mov        qword ptr [rsp + 0x28], rax
  0x35a6e13e3  mov        rax, qword ptr [rbx]
  0x35a6e13e6  call       qword ptr [rax + 0x548]
  0x35a6e13ec  movsxd     rdi, dword ptr [rip + 0xbc2d]
  0x35a6e13f3  mov        qword ptr [rsp + 0x30], rax
  0x35a6e13f8  cmp        edi, 0x7f
  0x35a6e13fb  jg         0x35a6e1483
  0x35a6e1401  lea        rax, [rip + 0xbc38]
  0x35a6e1408  xor        r13d, r13d
  0x35a6e140b  mov        qword ptr [rsp + 0x38], rax
  0x35a6e1410  mov        r14, rax
  0x35a6e1413  test       edi, edi
  0x35a6e1415  jg         0x35a6e14ec
  0x35a6e141b  nop        dword ptr [rax + rax]
  0x35a6e1420  lea        eax, [rdi + 1]
  0x35a6e1423  mov        r14, qword ptr [rsp + 0x38]
  0x35a6e1428  mov        r8d, 0xff
  0x35a6e142e  mov        rdx, rsi
  0x35a6e1431  imul       rdi, rdi, 0x50408
  0x35a6e1438  mov        dword ptr [rip + 0xbbe2], eax
  0x35a6e143e  lea        rcx, [r14 + rdi]
  0x35a6e1442  call       0x35a6e8770
  0x35a6e1447  mov        rdx, qword ptr [rsp + 0x28]
  0x35a6e144c  lea        rcx, [r14 + rdi + 0x100]
  0x35a6e1454  mov        r8d, 0xff
  0x35a6e145a  call       0x35a6e8770
  0x35a6e145f  mov        rdx, qword ptr [rsp + 0x30]
  0x35a6e1464  lea        rcx, [r14 + rdi + 0x200]
  0x35a6e146c  mov        r8d, 0xff
  0x35a6e1472  call       0x35a6e8770
  0x35a6e1477  mov        qword ptr [r14 + rdi + 0x300], 0
  0x35a6e1483  mov        rax, qword ptr [rbx]
  0x35a6e1486  mov        r8, rsi
  0x35a6e1489  mov        rdx, r12
  0x35a6e148c  mov        rcx, rbx
  0x35a6e148f  call       qword ptr [rax + 0x550]
  0x35a6e1495  mov        rax, qword ptr [rbx]
  0x35a6e1498  mov        rdx, rbp
  0x35a6e149b  mov        rcx, rbx
  0x35a6e149e  mov        r8, qword ptr [rsp + 0x28]
  0x35a6e14a3  call       qword ptr [rax + 0x550]
  0x35a6e14a9  mov        rax, qword ptr [rbx]
  0x35a6e14ac  mov        rdx, r15
  0x35a6e14af  mov        rcx, rbx
  0x35a6e14b2  mov        r8, qword ptr [rsp + 0x30]
  0x35a6e14b7  mov        rax, qword ptr [rax + 0x550]
  0x35a6e14be  add        rsp, 0x48
  0x35a6e14c2  pop        rbx
  0x35a6e14c3  pop        rsi
  0x35a6e14c4  pop        rdi
  0x35a6e14c5  pop        rbp
  0x35a6e14c6  pop        r12
  0x35a6e14c8  pop        r13
  0x35a6e14ca  pop        r14
  0x35a6e14cc  pop        r15
  0x35a6e14ce  jmp        rax
  0x35a6e14d1  nop        dword ptr [rax]
  0x35a6e14d8  add        r13d, 1
  0x35a6e14dc  add        r14, 0x50408
  0x35a6e14e3  cmp        edi, r13d
  0x35a6e14e6  je         0x35a6e1420
  0x35a6e14ec  mov        rdx, rsi
  0x35a6e14ef  mov        rcx, r14
  0x35a6e14f2  call       0x35a6e8750
  0x35a6e14f7  test       eax, eax
  0x35a6e14f9  jne        0x35a6e14d8
  0x35a6e14fb  jmp        0x35a6e1483
  0x35a6e14fd  nop        dword ptr [rax]
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
