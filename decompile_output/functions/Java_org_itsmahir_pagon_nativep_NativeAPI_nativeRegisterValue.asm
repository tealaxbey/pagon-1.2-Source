; === Java_org_itsmahir_pagon_nativep_NativeAPI_nativeRegisterValue ===
; RVA: 0x1680

  0x35a6e1680  push       r15
  0x35a6e1682  push       r14
  0x35a6e1684  push       r13
  0x35a6e1686  push       r12
  0x35a6e1688  push       rbp
  0x35a6e1689  push       rdi
  0x35a6e168a  push       rsi
  0x35a6e168b  push       rbx
  0x35a6e168c  sub        rsp, 0x78
  0x35a6e1690  lea        r13, [rip + 0xb9a9]
  0x35a6e1697  xor        r15d, r15d
  0x35a6e169a  mov        rax, qword ptr [rsp + 0xf0]
  0x35a6e16a2  mov        r14, qword ptr [rsp + 0xe8]
  0x35a6e16aa  mov        rbx, qword ptr [rsp + 0xe0]
  0x35a6e16b2  mov        qword ptr [rsp + 0x58], rax
  0x35a6e16b7  mov        rax, qword ptr [rcx]
  0x35a6e16ba  mov        r12, rcx
  0x35a6e16bd  mov        rdi, r9
  0x35a6e16c0  mov        qword ptr [rsp + 0x40], r9
  0x35a6e16c5  mov        rdx, r8
  0x35a6e16c8  mov        qword ptr [rsp + 0x38], r8
  0x35a6e16cd  xor        r8d, r8d
  0x35a6e16d0  mov        qword ptr [rsp + 0x50], r14
  0x35a6e16d5  mov        qword ptr [rsp + 0x48], rbx
  0x35a6e16da  call       qword ptr [rax + 0x548]
  0x35a6e16e0  mov        rdx, rdi
  0x35a6e16e3  xor        r8d, r8d
  0x35a6e16e6  mov        rcx, r12
  0x35a6e16e9  mov        rsi, rax
  0x35a6e16ec  mov        rax, qword ptr [r12]
  0x35a6e16f0  call       qword ptr [rax + 0x548]
  0x35a6e16f6  xor        r8d, r8d
  0x35a6e16f9  mov        rdx, rbx
  0x35a6e16fc  mov        rcx, r12
  0x35a6e16ff  mov        rdi, rax
  0x35a6e1702  mov        rax, qword ptr [r12]
  0x35a6e1706  call       qword ptr [rax + 0x548]
  0x35a6e170c  mov        rdx, r14
  0x35a6e170f  xor        r8d, r8d
  0x35a6e1712  mov        rcx, r12
  0x35a6e1715  mov        qword ptr [rsp + 0x30], rax
  0x35a6e171a  mov        rax, qword ptr [r12]
  0x35a6e171e  mov        r14, r13
  0x35a6e1721  call       qword ptr [rax + 0x548]
  0x35a6e1727  mov        ebp, dword ptr [rip + 0xb8f3]
  0x35a6e172d  mov        qword ptr [rsp + 0x28], rax
  0x35a6e1732  test       ebp, ebp
  0x35a6e1734  jg         0x35a6e1750
  0x35a6e1736  jmp        0x35a6e17c2
  0x35a6e173b  nop        dword ptr [rax + rax]
  0x35a6e1740  add        r15d, 1
  0x35a6e1744  add        r14, 0x50408
  0x35a6e174b  cmp        r15d, ebp
  0x35a6e174e  je         0x35a6e17c2
  0x35a6e1750  mov        rdx, rsi
  0x35a6e1753  mov        rcx, r14
  0x35a6e1756  call       0x35a6e8750
  0x35a6e175b  test       eax, eax
  0x35a6e175d  jne        0x35a6e1740
  0x35a6e175f  movsxd     r9, r15d
  0x35a6e1762  mov        ebx, eax
  0x35a6e1764  imul       r10, r9, 0x50408
  0x35a6e176b  mov        r15d, dword ptr [r13 + r10 + 0x304]
  0x35a6e1773  cmp        r15d, 0x3f
  0x35a6e1777  jg         0x35a6e17c2
  0x35a6e1779  test       r15d, r15d
  0x35a6e177c  jle        0x35a6e183c
  0x35a6e1782  lea        r14, [r13 + r10 + 0x308]
  0x35a6e178a  xor        ebp, ebp
  0x35a6e178c  mov        dword ptr [rsp + 0x60], ebx
  0x35a6e1790  mov        qword ptr [rsp + 0x68], r10
  0x35a6e1795  mov        ebx, ebp
  0x35a6e1797  mov        rbp, r14
  0x35a6e179a  mov        r14, r9
  0x35a6e179d  jmp        0x35a6e17b3
  0x35a6e179f  nop        
  0x35a6e17a0  add        ebx, 1
  0x35a6e17a3  add        rbp, 0x100
  0x35a6e17aa  cmp        r15d, ebx
  0x35a6e17ad  je         0x35a6e1830
  0x35a6e17b3  mov        rdx, rdi
  0x35a6e17b6  mov        rcx, rbp
  0x35a6e17b9  call       0x35a6e8750
  0x35a6e17be  test       eax, eax
  0x35a6e17c0  jne        0x35a6e17a0
  0x35a6e17c2  mov        rax, qword ptr [r12]
  0x35a6e17c6  mov        rdx, qword ptr [rsp + 0x38]
  0x35a6e17cb  mov        r8, rsi
  0x35a6e17ce  mov        rcx, r12
  0x35a6e17d1  call       qword ptr [rax + 0x550]
  0x35a6e17d7  mov        rax, qword ptr [r12]
  0x35a6e17db  mov        r8, rdi
  0x35a6e17de  mov        rcx, r12
  0x35a6e17e1  mov        rdx, qword ptr [rsp + 0x40]
  0x35a6e17e6  call       qword ptr [rax + 0x550]
  0x35a6e17ec  mov        rax, qword ptr [r12]
  0x35a6e17f0  mov        r8, qword ptr [rsp + 0x30]
  0x35a6e17f5  mov        rcx, r12
  0x35a6e17f8  mov        rdx, qword ptr [rsp + 0x48]
  0x35a6e17fd  call       qword ptr [rax + 0x550]
  0x35a6e1803  mov        rax, qword ptr [r12]
  0x35a6e1807  mov        r8, qword ptr [rsp + 0x28]
  0x35a6e180c  mov        rcx, r12
  0x35a6e180f  mov        rdx, qword ptr [rsp + 0x50]
  0x35a6e1814  mov        rax, qword ptr [rax + 0x550]
  0x35a6e181b  add        rsp, 0x78
  0x35a6e181f  pop        rbx
  0x35a6e1820  pop        rsi
  0x35a6e1821  pop        rdi
  0x35a6e1822  pop        rbp
  0x35a6e1823  pop        r12
  0x35a6e1825  pop        r13
  0x35a6e1827  pop        r14
  0x35a6e1829  pop        r15
  0x35a6e182b  jmp        rax
  0x35a6e182e  nop        
  0x35a6e1830  mov        ebx, dword ptr [rsp + 0x60]
  0x35a6e1834  mov        r10, qword ptr [rsp + 0x68]
  0x35a6e1839  mov        r9, r14
  0x35a6e183c  lea        ecx, [r15 + 1]
  0x35a6e1840  movsxd     rbp, r15d
  0x35a6e1843  lea        r15, [r10 + 8]
  0x35a6e1847  mov        rdx, rdi
  0x35a6e184a  imul       rax, r9, 0x50408
  0x35a6e1851  mov        r8d, 0xff
  0x35a6e1857  mov        qword ptr [rsp + 0x68], r9
  0x35a6e185c  mov        qword ptr [rsp + 0x60], r10
  0x35a6e1861  mov        dword ptr [r13 + rax + 0x304], ecx
  0x35a6e1869  lea        rax, [rbp + 3]
  0x35a6e186d  shl        rax, 8
  0x35a6e1871  lea        rcx, [r15 + rax]
  0x35a6e1875  mov        r14, rax
  0x35a6e1878  add        rcx, r13
  0x35a6e187b  call       0x35a6e8770
