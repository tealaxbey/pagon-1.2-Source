; === TAM DİSASSEMBLY (x64) ===


; ════════════════════════════════════════════
; SECTION: .text  (RVA=0x1000  VA=0x35a6e1000)
; ════════════════════════════════════════════

  0x35a6e1000  lea        rcx, [rip + 0xbff9]
  0x35a6e1007  jmp        0x35a6e8200
  0x35a6e100c  nop        dword ptr [rax]
  0x35a6e1010  push       rbp
  0x35a6e1011  push       r14
  0x35a6e1013  push       r13
  0x35a6e1015  push       r12
  0x35a6e1017  push       rdi
  0x35a6e1018  push       rsi
  0x35a6e1019  push       rbx
  0x35a6e101a  sub        rsp, 0x20
  0x35a6e101e  lea        rbp, [rsp + 0x20]
  0x35a6e1023  mov        rdi, rcx
  0x35a6e1026  mov        r12, r8
  0x35a6e1029  test       edx, edx
  0x35a6e102b  jne        0x35a6e10b0
  0x35a6e1031  mov        eax, dword ptr [rip + 0xbfe1]
  0x35a6e1037  test       eax, eax
  0x35a6e1039  jle        0x35a6e1099
  0x35a6e103b  sub        eax, 1
  0x35a6e103e  mov        rbx, qword ptr [rip + 0x96bb]
  0x35a6e1045  xor        r12d, r12d
  0x35a6e1048  mov        edi, 1
  0x35a6e104d  mov        dword ptr [rip + 0xbfc5], eax
  0x35a6e1053  mov        r13, qword ptr [rip + 0x282e156]
  0x35a6e105a  jmp        0x35a6e1068
  0x35a6e105c  nop        dword ptr [rax]
  0x35a6e1060  mov        ecx, 0x3e8
  0x35a6e1065  call       r13
  0x35a6e1068  mov        rax, r12
  0x35a6e106b  lock cmpxchg qword ptr [rbx], rdi
  0x35a6e1070  mov        rsi, rax
  0x35a6e1073  test       rax, rax
  0x35a6e1076  jne        0x35a6e1060
  0x35a6e1078  mov        rdi, qword ptr [rip + 0x9691]
  0x35a6e107f  mov        eax, dword ptr [rdi]
  0x35a6e1081  cmp        eax, 2
  0x35a6e1084  je         0x35a6e1150
  0x35a6e108a  mov        ecx, 0x1f
  0x35a6e108f  call       0x35a6e86d8
  0x35a6e1094  mov        edx, 1
  0x35a6e1099  mov        eax, edx
  0x35a6e109b  add        rsp, 0x20
  0x35a6e109f  pop        rbx
  0x35a6e10a0  pop        rsi
  0x35a6e10a1  pop        rdi
  0x35a6e10a2  pop        r12
  0x35a6e10a4  pop        r13
  0x35a6e10a6  pop        r14
  0x35a6e10a8  pop        rbp
  0x35a6e10a9  ret        
  0x35a6e10aa  nop        word ptr [rax + rax]
  0x35a6e10b0  cmp        edx, 1
  0x35a6e10b3  jne        0x35a6e1094
  0x35a6e10b5  mov        rax, qword ptr gs:[0x30]
  0x35a6e10be  mov        rbx, qword ptr [rip + 0x963b]
  0x35a6e10c5  mov        rsi, qword ptr [rax + 8]
  0x35a6e10c9  xor        r13d, r13d
  0x35a6e10cc  mov        r14, qword ptr [rip + 0x282e0dd]
  0x35a6e10d3  jmp        0x35a6e10e9
  0x35a6e10d5  nop        dword ptr [rax]
  0x35a6e10d8  cmp        rsi, rax
  0x35a6e10db  je         0x35a6e1170
  0x35a6e10e1  mov        ecx, 0x3e8
  0x35a6e10e6  call       r14
  0x35a6e10e9  mov        rax, r13
  0x35a6e10ec  lock cmpxchg qword ptr [rbx], rsi
  0x35a6e10f1  test       rax, rax
  0x35a6e10f4  jne        0x35a6e10d8
  0x35a6e10f6  xor        r13d, r13d
  0x35a6e10f9  mov        rsi, qword ptr [rip + 0x9610]
  0x35a6e1100  mov        eax, dword ptr [rsi]
  0x35a6e1102  cmp        eax, 1
  0x35a6e1105  je         0x35a6e11d0
  0x35a6e110b  mov        eax, dword ptr [rsi]
  0x35a6e110d  test       eax, eax
  0x35a6e110f  je         0x35a6e1190
  0x35a6e1111  mov        eax, dword ptr [rsi]
  0x35a6e1113  cmp        eax, 1
  0x35a6e1116  je         0x35a6e11b0
  0x35a6e111c  test       r13d, r13d
  0x35a6e111f  je         0x35a6e1180
  0x35a6e1121  mov        rax, qword ptr [rip + 0x9598]
  0x35a6e1128  mov        rax, qword ptr [rax]
  0x35a6e112b  test       rax, rax
  0x35a6e112e  je         0x35a6e113d
  0x35a6e1130  mov        r8, r12
  0x35a6e1133  mov        edx, 2
  0x35a6e1138  mov        rcx, rdi
  0x35a6e113b  call       rax
  0x35a6e113d  add        dword ptr [rip + 0xbed4], 1
  0x35a6e1144  mov        edx, 1
  0x35a6e1149  jmp        0x35a6e1099
  0x35a6e114e  nop        
  0x35a6e1150  lea        rcx, [rip + 0xbea9]
  0x35a6e1157  call       0x35a6e8300
  0x35a6e115c  mov        dword ptr [rdi], 0
  0x35a6e1162  xchg       qword ptr [rbx], rsi
  0x35a6e1165  jmp        0x35a6e1094
  0x35a6e116a  nop        word ptr [rax + rax]
  0x35a6e1170  mov        r13d, 1
  0x35a6e1176  jmp        0x35a6e10f9
  0x35a6e1178  nop        dword ptr [rax + rax]
  0x35a6e1180  xor        eax, eax
  0x35a6e1182  xchg       qword ptr [rbx], rax
  0x35a6e1185  jmp        0x35a6e1121
  0x35a6e1187  nop        word ptr [rax + rax]
  0x35a6e1190  mov        rdx, qword ptr [rip + 0x95c9]
  0x35a6e1197  mov        rcx, qword ptr [rip + 0x95b2]
  0x35a6e119e  mov        dword ptr [rsi], 1
  0x35a6e11a4  call       0x35a6e86e8
  0x35a6e11a9  jmp        0x35a6e1111
  0x35a6e11ae  nop        
  0x35a6e11b0  mov        rdx, qword ptr [rip + 0x9589]
  0x35a6e11b7  mov        rcx, qword ptr [rip + 0x9572]
  0x35a6e11be  call       0x35a6e86e8
  0x35a6e11c3  mov        dword ptr [rsi], 2
  0x35a6e11c9  jmp        0x35a6e111c
  0x35a6e11ce  nop        
  0x35a6e11d0  mov        ecx, 0x1f
  0x35a6e11d5  call       0x35a6e86d8
  0x35a6e11da  jmp        0x35a6e1111
  0x35a6e11df  nop        
  0x35a6e11e0  push       rbp
  0x35a6e11e1  push       r13
  0x35a6e11e3  push       r12
  0x35a6e11e5  push       rdi
  0x35a6e11e6  push       rsi
  0x35a6e11e7  push       rbx
  0x35a6e11e8  sub        rsp, 0x28
  0x35a6e11ec  lea        rbp, [rsp + 0x20]
  0x35a6e11f1  mov        r12, qword ptr [rip + 0x94f8]
  0x35a6e11f8  mov        rsi, rcx
  0x35a6e11fb  mov        dword ptr [r12], edx
  0x35a6e11ff  mov        ebx, edx
  0x35a6e1201  mov        rdi, r8
  0x35a6e1204  test       edx, edx
  0x35a6e1206  jne        0x35a6e1268
  0x35a6e1208  mov        eax, dword ptr [rip + 0xbe0a]
  0x35a6e120e  test       eax, eax
  0x35a6e1210  je         0x35a6e1248
  0x35a6e1212  call       0x35a6e2760
  0x35a6e1217  mov        r8, rdi
  0x35a6e121a  xor        edx, edx
  0x35a6e121c  mov        rcx, rsi
  0x35a6e121f  call       0x35a6e31c0
  0x35a6e1224  mov        r8, rdi
  0x35a6e1227  mov        edx, ebx
  0x35a6e1229  mov        rcx, rsi
  0x35a6e122c  call       0x35a6e31b0
  0x35a6e1231  mov        r8, rdi
  0x35a6e1234  mov        edx, ebx
  0x35a6e1236  mov        rcx, rsi
  0x35a6e1239  mov        r13d, eax
  0x35a6e123c  call       0x35a6e1010
  0x35a6e1241  test       eax, eax
  0x35a6e1243  jne        0x35a6e124b
  0x35a6e1245  nop        dword ptr [rax]
  0x35a6e1248  xor        r13d, r13d
  0x35a6e124b  mov        eax, r13d
  0x35a6e124e  mov        dword ptr [r12], 0xffffffff
  0x35a6e1256  add        rsp, 0x28
  0x35a6e125a  pop        rbx
  0x35a6e125b  pop        rsi
  0x35a6e125c  pop        rdi
  0x35a6e125d  pop        r12
  0x35a6e125f  pop        r13
  0x35a6e1261  pop        rbp
  0x35a6e1262  ret        
  0x35a6e1263  nop        dword ptr [rax + rax]
  0x35a6e1268  call       0x35a6e2760
  0x35a6e126d  lea        eax, [rbx - 1]
  0x35a6e1270  mov        r8, rdi
  0x35a6e1273  mov        edx, ebx
  0x35a6e1275  mov        rcx, rsi
  0x35a6e1278  cmp        eax, 1
  0x35a6e127b  ja         0x35a6e12b8
  0x35a6e127d  call       0x35a6e1010
  0x35a6e1282  test       eax, eax
  0x35a6e1284  je         0x35a6e1248
  0x35a6e1286  mov        r8, rdi
  0x35a6e1289  mov        edx, ebx
  0x35a6e128b  mov        rcx, rsi
  0x35a6e128e  call       0x35a6e31b0
  0x35a6e1293  test       eax, eax
  0x35a6e1295  je         0x35a6e12d0
  0x35a6e1297  cmp        ebx, 1
  0x35a6e129a  je         0x35a6e12f0
  0x35a6e129c  mov        r8, rdi
  0x35a6e129f  mov        edx, 2
  0x35a6e12a4  mov        rcx, rsi
  0x35a6e12a7  call       0x35a6e31c0
  0x35a6e12ac  mov        r13d, eax
  0x35a6e12af  jmp        0x35a6e124b
  0x35a6e12b1  nop        dword ptr [rax]
  0x35a6e12b8  call       0x35a6e31c0
  0x35a6e12bd  mov        r13d, eax
  0x35a6e12c0  cmp        ebx, 3
  0x35a6e12c3  jne        0x35a6e124b
  0x35a6e12c5  jmp        0x35a6e1224
  0x35a6e12ca  nop        word ptr [rax + rax]
  0x35a6e12d0  cmp        ebx, 1
  0x35a6e12d3  jne        0x35a6e1248
  0x35a6e12d9  mov        r8, rdi
  0x35a6e12dc  xor        edx, edx
  0x35a6e12de  mov        rcx, rsi
  0x35a6e12e1  call       0x35a6e1010
  0x35a6e12e6  jmp        0x35a6e1248
  0x35a6e12eb  nop        dword ptr [rax + rax]
  0x35a6e12f0  call       0x35a6e2490
  0x35a6e12f5  mov        r8, rdi
  0x35a6e12f8  mov        edx, 1
  0x35a6e12fd  mov        rcx, rsi
  0x35a6e1300  call       0x35a6e31c0
  0x35a6e1305  mov        r13d, eax
  0x35a6e1308  test       eax, eax
  0x35a6e130a  jne        0x35a6e124b
  0x35a6e1310  mov        r8, rdi
  0x35a6e1313  xor        edx, edx
  0x35a6e1315  mov        rcx, rsi
  0x35a6e1318  call       0x35a6e31c0
  0x35a6e131d  mov        r8, rdi
  0x35a6e1320  xor        edx, edx
  0x35a6e1322  mov        rcx, rsi
  0x35a6e1325  call       0x35a6e31b0
  0x35a6e132a  mov        r8, rdi
  0x35a6e132d  xor        edx, edx
  0x35a6e132f  mov        rcx, rsi
  0x35a6e1332  call       0x35a6e1010
  0x35a6e1337  jmp        0x35a6e124b
  0x35a6e133c  nop        dword ptr [rax]
  0x35a6e1340  mov        rax, qword ptr [rip + 0x9399]
  0x35a6e1347  mov        dword ptr [rax], 0
  0x35a6e134d  jmp        0x35a6e11e0
  0x35a6e1352  nop        word ptr cs:[rax + rax]
  0x35a6e135d  nop        dword ptr [rax]
  0x35a6e1360  mov        rdx, rcx
  0x35a6e1363  lea        rcx, [rip + 0xbc96]
  0x35a6e136a  jmp        0x35a6e8220
  0x35a6e136f  nop        
  0x35a6e1370  lea        rcx, [rip + 9]
  0x35a6e1377  jmp        0x35a6e1360
  0x35a6e137c  nop        dword ptr [rax]
  0x35a6e1380  ret        
  0x35a6e1381  nop        
  0x35a6e1382  nop        
  0x35a6e1383  nop        
  0x35a6e1384  nop        
  0x35a6e1385  nop        
  0x35a6e1386  nop        
  0x35a6e1387  nop        
  0x35a6e1388  nop        
  0x35a6e1389  nop        
  0x35a6e138a  nop        
  0x35a6e138b  nop        
  0x35a6e138c  nop        
  0x35a6e138d  nop        
  0x35a6e138e  nop        
  0x35a6e138f  nop        

; ── EXPORT: Java_org_itsmahir_pagon_nativep_NativeAPI_nativeRegisterModule ──
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeRegisterModule:
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

; ── EXPORT: Java_org_itsmahir_pagon_nativep_NativeAPI_nativeSetModuleEnabled ──
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeSetModuleEnabled:
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

; ── EXPORT: Java_org_itsmahir_pagon_nativep_NativeAPI_nativeIsModuleEnabled ──
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeIsModuleEnabled:
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
  0x35a6e1646  nop        word ptr cs:[rax + rax]
  0x35a6e1650  mov        rax, qword ptr [r13]
  0x35a6e1654  mov        r8, rdi
  0x35a6e1657  mov        rdx, r14
  0x35a6e165a  mov        rcx, r13
  0x35a6e165d  call       qword ptr [rax + 0x550]
  0x35a6e1663  xor        eax, eax
  0x35a6e1665  add        rsp, 0x20
  0x35a6e1669  pop        rbx
  0x35a6e166a  pop        rsi
  0x35a6e166b  pop        rdi
  0x35a6e166c  pop        rbp
  0x35a6e166d  pop        r12
  0x35a6e166f  pop        r13
  0x35a6e1671  pop        r14
  0x35a6e1673  ret        
  0x35a6e1674  nop        word ptr cs:[rax + rax]
  0x35a6e167f  nop        

; ── EXPORT: Java_org_itsmahir_pagon_nativep_NativeAPI_nativeRegisterValue ──
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeRegisterValue:
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
  0x35a6e1880  mov        rdx, qword ptr [rsp + 0x30]
  0x35a6e1885  lea        rcx, [r15 + r14 + 0x4000]
  0x35a6e188d  mov        r8d, 0xff
  0x35a6e1893  add        rcx, r13
  0x35a6e1896  call       0x35a6e8770
  0x35a6e189b  mov        rdx, qword ptr [rsp + 0x28]
  0x35a6e18a0  lea        rcx, [r15 + r14 + 0x8000]
  0x35a6e18a8  mov        r8d, 0xff
  0x35a6e18ae  add        rcx, r13
  0x35a6e18b1  call       0x35a6e8770
  0x35a6e18b6  mov        rdx, qword ptr [rsp + 0x28]
  0x35a6e18bb  lea        rcx, [r15 + r14 + 0xc000]
  0x35a6e18c3  mov        r8d, 0xff
  0x35a6e18c9  add        rcx, r13
  0x35a6e18cc  call       0x35a6e8770
  0x35a6e18d1  mov        r14, qword ptr [rsp + 0x58]
  0x35a6e18d6  test       r14, r14
  0x35a6e18d9  je         0x35a6e17c2
  0x35a6e18df  mov        rax, qword ptr [r12]
  0x35a6e18e3  mov        rdx, r14
  0x35a6e18e6  mov        rcx, r12
  0x35a6e18e9  call       qword ptr [rax + 0x558]
  0x35a6e18ef  mov        r9, qword ptr [rsp + 0x68]
  0x35a6e18f4  mov        ecx, 0x10
  0x35a6e18f9  imul       rdx, r9, 0x14102
  0x35a6e1900  cmp        eax, ecx
  0x35a6e1902  cmovle     ecx, eax
  0x35a6e1905  lea        rdx, [rdx + rbp + 0x40c0]
  0x35a6e190d  mov        dword ptr [r13 + rdx*4 + 8], ecx
  0x35a6e1912  test       eax, eax
  0x35a6e1914  jle        0x35a6e17c2
  0x35a6e191a  mov        r10, qword ptr [rsp + 0x60]
  0x35a6e191f  mov        rax, rbp
  0x35a6e1922  mov        qword ptr [rsp + 0x60], rdi
  0x35a6e1927  shl        rax, 0xc
  0x35a6e192b  mov        qword ptr [rsp + 0x58], rsi
  0x35a6e1930  mov        esi, ebx
  0x35a6e1932  mov        rbx, rdx
  0x35a6e1935  lea        r15, [rax + r10 + 0x10408]
  0x35a6e193d  add        r15, r13
  0x35a6e1940  mov        rax, qword ptr [r12]
  0x35a6e1944  mov        r8d, esi
  0x35a6e1947  mov        rdx, r14
  0x35a6e194a  mov        rcx, r12
  0x35a6e194d  add        esi, 1
  0x35a6e1950  call       qword ptr [rax + 0x568]
  0x35a6e1956  xor        r8d, r8d
  0x35a6e1959  mov        rcx, r12
  0x35a6e195c  mov        rbp, rax
  0x35a6e195f  mov        rax, qword ptr [r12]
  0x35a6e1963  mov        rdx, rbp
  0x35a6e1966  call       qword ptr [rax + 0x548]
  0x35a6e196c  mov        rcx, r15
  0x35a6e196f  mov        r8d, 0xff
  0x35a6e1975  add        r15, 0x100
  0x35a6e197c  mov        rdx, rax
  0x35a6e197f  mov        rdi, rax
  0x35a6e1982  call       0x35a6e8770
  0x35a6e1987  mov        rax, qword ptr [r12]
  0x35a6e198b  mov        r8, rdi
  0x35a6e198e  mov        rdx, rbp
  0x35a6e1991  mov        rcx, r12
  0x35a6e1994  call       qword ptr [rax + 0x550]
  0x35a6e199a  cmp        dword ptr [r13 + rbx*4 + 8], esi
  0x35a6e199f  jg         0x35a6e1940
  0x35a6e19a1  mov        rsi, qword ptr [rsp + 0x58]
  0x35a6e19a6  mov        rdi, qword ptr [rsp + 0x60]
  0x35a6e19ab  jmp        0x35a6e17c2

; ── EXPORT: Java_org_itsmahir_pagon_nativep_NativeAPI_nativeSetValue ──
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeSetValue:
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

; ── EXPORT: Java_org_itsmahir_pagon_nativep_NativeAPI_nativeGetValue ──
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeGetValue:
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
  0x35a6e1c5c  nop        dword ptr [rax]
  0x35a6e1c60  mov        rax, qword ptr [rsi]
  0x35a6e1c63  mov        rdx, qword ptr [rsp + 0x20]
  0x35a6e1c68  mov        r8, rdi
  0x35a6e1c6b  mov        rcx, rsi
  0x35a6e1c6e  call       qword ptr [rax + 0x550]
  0x35a6e1c74  mov        rax, qword ptr [rsi]
  0x35a6e1c77  mov        r8, r13
  0x35a6e1c7a  mov        rcx, rsi
  0x35a6e1c7d  mov        rdx, qword ptr [rsp + 0x28]
  0x35a6e1c82  call       qword ptr [rax + 0x550]
  0x35a6e1c88  mov        rax, qword ptr [rsi]
  0x35a6e1c8b  lea        rdx, [rip + 0x836e]
  0x35a6e1c92  mov        rcx, rsi
  0x35a6e1c95  mov        rax, qword ptr [rax + 0x538]
  0x35a6e1c9c  add        rsp, 0x38
  0x35a6e1ca0  pop        rbx
  0x35a6e1ca1  pop        rsi
  0x35a6e1ca2  pop        rdi
  0x35a6e1ca3  pop        rbp
  0x35a6e1ca4  pop        r12
  0x35a6e1ca6  pop        r13
  0x35a6e1ca8  pop        r14
  0x35a6e1caa  pop        r15
  0x35a6e1cac  jmp        rax
  0x35a6e1caf  nop        

; ── EXPORT: Java_org_itsmahir_pagon_nativep_NativeAPI_nativeOnEnable ──
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeOnEnable:
  0x35a6e1cb0  ret        
  0x35a6e1cb1  nop        word ptr cs:[rax + rax]
  0x35a6e1cbc  nop        dword ptr [rax]

; ── EXPORT: Java_org_itsmahir_pagon_nativep_NativeAPI_nativeOnDisable ──
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeOnDisable:
  0x35a6e1cc0  ret        
  0x35a6e1cc1  nop        word ptr cs:[rax + rax]
  0x35a6e1ccc  nop        dword ptr [rax]

; ── EXPORT: Java_org_itsmahir_pagon_nativep_NativeAPI_nativeOnTick ──
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeOnTick:
  0x35a6e1cd0  ret        
  0x35a6e1cd1  nop        word ptr cs:[rax + rax]
  0x35a6e1cdc  nop        dword ptr [rax]

; ── EXPORT: Java_org_itsmahir_pagon_nativep_NativeAPI_nativeOnRender ──
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeOnRender:
  0x35a6e1ce0  ret        
  0x35a6e1ce1  nop        word ptr cs:[rax + rax]
  0x35a6e1cec  nop        dword ptr [rax]

; ── EXPORT: Java_org_itsmahir_pagon_nativep_NativeAPI_nativeGetAllModules ──
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeGetAllModules:
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
  0x35a6e1d83  nop        word ptr cs:[rax + rax]
  0x35a6e1d8e  nop        

; ── EXPORT: Java_org_itsmahir_pagon_nativep_NativeAPI_nativeDecrypt ──
Java_org_itsmahir_pagon_nativep_NativeAPI_nativeDecrypt:
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
  0x35a6e1e84  nop        
  0x35a6e1e85  nop        
  0x35a6e1e86  nop        
  0x35a6e1e87  nop        
  0x35a6e1e88  nop        
  0x35a6e1e89  nop        
  0x35a6e1e8a  nop        
  0x35a6e1e8b  nop        
  0x35a6e1e8c  nop        
  0x35a6e1e8d  nop        
  0x35a6e1e8e  nop        
  0x35a6e1e8f  nop        
  0x35a6e1e90  sub        rsp, 0x38
  0x35a6e1e94  lea        rdx, [rip + 0x81c5]
  0x35a6e1e9b  mov        qword ptr [rsp + 0x50], r8
  0x35a6e1ea0  lea        r8, [rsp + 0x50]
  0x35a6e1ea5  mov        qword ptr [rsp + 0x58], r9
  0x35a6e1eaa  mov        qword ptr [rsp + 0x28], r8
  0x35a6e1eaf  call       0x35a6e31d0
  0x35a6e1eb4  add        rsp, 0x38
  0x35a6e1eb8  ret        
  0x35a6e1eb9  nop        dword ptr [rax]

; ── EXPORT: Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeOnEnable ──
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeOnEnable:
  0x35a6e1ec0  push       rsi
  0x35a6e1ec1  push       rbx
  0x35a6e1ec2  sub        rsp, 0x28
  0x35a6e1ec6  cmp        qword ptr [rip + 0x282b5ba], 0
  0x35a6e1ece  mov        rbx, rcx
  0x35a6e1ed1  je         0x35a6e1ef0
  0x35a6e1ed3  mov        dword ptr [rip + 0x282b567], 0
  0x35a6e1edd  mov        dword ptr [rip + 0x282b559], 2
  0x35a6e1ee7  add        rsp, 0x28
  0x35a6e1eeb  pop        rbx
  0x35a6e1eec  pop        rsi
  0x35a6e1eed  ret        
  0x35a6e1eee  nop        
  0x35a6e1ef0  mov        rax, qword ptr [rcx]
  0x35a6e1ef3  lea        rdx, [rip + 0x818e]
  0x35a6e1efa  call       qword ptr [rax + 0x30]
  0x35a6e1efd  mov        rsi, rax
  0x35a6e1f00  test       rax, rax
  0x35a6e1f03  je         0x35a6e2098
  0x35a6e1f09  mov        rax, qword ptr [rbx]
  0x35a6e1f0c  mov        rdx, rsi
  0x35a6e1f0f  mov        rcx, rbx
  0x35a6e1f12  call       qword ptr [rax + 0xa8]
  0x35a6e1f18  mov        rdx, rsi
  0x35a6e1f1b  mov        rcx, rbx
  0x35a6e1f1e  mov        qword ptr [rip + 0x282b563], rax
  0x35a6e1f25  mov        rax, qword ptr [rbx]
  0x35a6e1f28  call       qword ptr [rax + 0xb8]
  0x35a6e1f2e  mov        rax, qword ptr [rbx]
  0x35a6e1f31  mov        rcx, rbx
  0x35a6e1f34  mov        rdx, qword ptr [rip + 0x282b54d]
  0x35a6e1f3b  lea        r9, [rip + 0x8180]
  0x35a6e1f42  lea        r8, [rip + 0x817d]
  0x35a6e1f49  call       qword ptr [rax + 0x388]
  0x35a6e1f4f  mov        rdx, qword ptr [rip + 0x282b532]
  0x35a6e1f56  lea        r9, [rip + 0x8165]
  0x35a6e1f5d  mov        rcx, rbx
  0x35a6e1f60  mov        qword ptr [rip + 0x282b519], rax
  0x35a6e1f67  mov        rax, qword ptr [rbx]
  0x35a6e1f6a  lea        r8, [rip + 0x8164]
  0x35a6e1f71  call       qword ptr [rax + 0x388]
  0x35a6e1f77  mov        rdx, qword ptr [rip + 0x282b50a]
  0x35a6e1f7e  lea        r9, [rip + 0x813d]
  0x35a6e1f85  mov        rcx, rbx
  0x35a6e1f88  mov        qword ptr [rip + 0x282b4e9], rax
  0x35a6e1f8f  mov        rax, qword ptr [rbx]
  0x35a6e1f92  lea        r8, [rip + 0x8149]
  0x35a6e1f99  call       qword ptr [rax + 0x388]
  0x35a6e1f9f  mov        rdx, qword ptr [rip + 0x282b4e2]
  0x35a6e1fa6  lea        r9, [rip + 0x8144]
  0x35a6e1fad  mov        rcx, rbx
  0x35a6e1fb0  mov        qword ptr [rip + 0x282b4b9], rax
  0x35a6e1fb7  mov        rax, qword ptr [rbx]
  0x35a6e1fba  lea        r8, [rip + 0x8135]
  0x35a6e1fc1  call       qword ptr [rax + 0x388]
  0x35a6e1fc7  mov        rdx, qword ptr [rip + 0x282b4ba]
  0x35a6e1fce  lea        r9, [rip + 0x80ed]
  0x35a6e1fd5  mov        rcx, rbx
  0x35a6e1fd8  mov        qword ptr [rip + 0x282b489], rax
  0x35a6e1fdf  mov        rax, qword ptr [rbx]
  0x35a6e1fe2  lea        r8, [rip + 0x811a]
  0x35a6e1fe9  call       qword ptr [rax + 0x388]
  0x35a6e1fef  mov        rdx, qword ptr [rip + 0x282b492]
  0x35a6e1ff6  lea        r9, [rip + 0x80c5]
  0x35a6e1ffd  mov        rcx, rbx
  0x35a6e2000  mov        qword ptr [rip + 0x282b459], rax
  0x35a6e2007  mov        rax, qword ptr [rbx]
  0x35a6e200a  lea        r8, [rip + 0x8100]
  0x35a6e2011  call       qword ptr [rax + 0x388]
  0x35a6e2017  mov        rdx, qword ptr [rip + 0x282b46a]
  0x35a6e201e  lea        r9, [rip + 0x809d]
  0x35a6e2025  mov        rcx, rbx
  0x35a6e2028  mov        qword ptr [rip + 0x282b429], rax
  0x35a6e202f  mov        rax, qword ptr [rbx]
  0x35a6e2032  lea        r8, [rip + 0x80e3]
  0x35a6e2039  call       qword ptr [rax + 0x388]
  0x35a6e203f  mov        rax, qword ptr [rbx]
  0x35a6e2042  mov        rcx, rbx
  0x35a6e2045  mov        rdx, qword ptr [rip + 0x282b43c]
  0x35a6e204c  lea        r9, [rip + 0x806f]
  0x35a6e2053  lea        r8, [rip + 0x80cf]
  0x35a6e205a  call       qword ptr [rax + 0x388]
  0x35a6e2060  mov        rdx, qword ptr [rip + 0x282b421]
  0x35a6e2067  lea        r9, [rip + 0x8054]
  0x35a6e206e  mov        rcx, rbx
  0x35a6e2071  mov        qword ptr [rip + 0x282b3d8], rax
  0x35a6e2078  mov        rax, qword ptr [rbx]
  0x35a6e207b  lea        r8, [rip + 0x80ae]
  0x35a6e2082  call       qword ptr [rax + 0x388]
  0x35a6e2088  mov        qword ptr [rip + 0x282b3b9], rax
  0x35a6e208f  jmp        0x35a6e1ed3
  0x35a6e2094  nop        dword ptr [rax]
  0x35a6e2098  mov        ecx, 2
  0x35a6e209d  call       qword ptr [rip + 0x6fdd]
  0x35a6e20a3  lea        rdx, [rip + 0x7fb6]
  0x35a6e20aa  mov        rcx, rax
  0x35a6e20ad  call       0x35a6e1e90
  0x35a6e20b2  jmp        0x35a6e1ed3
  0x35a6e20b7  nop        word ptr [rax + rax]

; ── EXPORT: Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeOnDisable ──
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeOnDisable:
  0x35a6e20c0  mov        dword ptr [rip + 0x282b37a], 0
  0x35a6e20ca  mov        dword ptr [rip + 0x282b36c], 0
  0x35a6e20d4  ret        
  0x35a6e20d5  nop        word ptr cs:[rax + rax]

; ── EXPORT: Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleTick ──
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleTick:
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
  0x35a6e2175  nop        word ptr cs:[rax + rax]

; ── EXPORT: Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleMove ──
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleMove:
  0x35a6e2180  ret        
  0x35a6e2181  nop        word ptr cs:[rax + rax]
  0x35a6e218c  nop        dword ptr [rax]

; ── EXPORT: Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleTravel ──
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleTravel:
  0x35a6e2190  push       r12
  0x35a6e2192  push       rbp
  0x35a6e2193  push       rdi
  0x35a6e2194  push       rsi
  0x35a6e2195  push       rbx
  0x35a6e2196  sub        rsp, 0x20
  0x35a6e219a  mov        edi, dword ptr [rsp + 0x70]
  0x35a6e219e  mov        r10d, dword ptr [rsp + 0x78]
  0x35a6e21a3  mov        eax, dword ptr [rsp + 0x80]
  0x35a6e21aa  mov        edx, dword ptr [rsp + 0x88]
  0x35a6e21b1  mov        esi, dword ptr [rsp + 0x90]
  0x35a6e21b8  mov        ebp, dword ptr [rsp + 0x98]
  0x35a6e21bf  mov        r11d, dword ptr [rsp + 0xa0]
  0x35a6e21c7  mov        rbx, rcx
  0x35a6e21ca  mov        rcx, qword ptr [rip + 0x282b2b7]
  0x35a6e21d1  test       rcx, rcx
  0x35a6e21d4  je         0x35a6e21e0
  0x35a6e21d6  test       r8b, r8b
  0x35a6e21d9  jne        0x35a6e21f0
  0x35a6e21db  cmp        esi, -1
  0x35a6e21de  jne        0x35a6e21f0
  0x35a6e21e0  add        rsp, 0x20
  0x35a6e21e4  pop        rbx
  0x35a6e21e5  pop        rsi
  0x35a6e21e6  pop        rdi
  0x35a6e21e7  pop        rbp
  0x35a6e21e8  pop        r12
  0x35a6e21ea  ret        
  0x35a6e21eb  nop        dword ptr [rax + rax]
  0x35a6e21f0  test       dl, dl
  0x35a6e21f2  setne      r12b
  0x35a6e21f6  sete       dl
  0x35a6e21f9  test       al, al
  0x35a6e21fb  mov        eax, r12d
  0x35a6e21fe  cmovne     eax, edx
  0x35a6e2201  or         al, r10b
  0x35a6e2204  je         0x35a6e2300
  0x35a6e220a  test       r9b, r9b
  0x35a6e220d  jne        0x35a6e22cd
  0x35a6e2213  test       r11b, r11b
  0x35a6e2216  mov        rax, qword ptr [rbx]
  0x35a6e2219  sete       r9b
  0x35a6e221d  test       bpl, bpl
  0x35a6e2220  setne      dl
  0x35a6e2223  mov        rax, qword ptr [rax + 0x468]
  0x35a6e222a  and        r9d, edx
  0x35a6e222d  or         r11b, r8b
  0x35a6e2230  mov        ebp, r9d
  0x35a6e2233  jne        0x35a6e2328
  0x35a6e2239  mov        rdx, rcx
  0x35a6e223c  mov        r8, qword ptr [rip + 0x282b225]
  0x35a6e2243  mov        rcx, rbx
  0x35a6e2246  mov        r9d, esi
  0x35a6e2249  call       rax
  0x35a6e224b  mov        rax, qword ptr [rbx]
  0x35a6e224e  mov        r8, qword ptr [rip + 0x282b20b]
  0x35a6e2255  mov        rcx, rbx
  0x35a6e2258  mov        rdx, qword ptr [rip + 0x282b229]
  0x35a6e225f  call       qword ptr [rax + 0x468]
  0x35a6e2265  test       bpl, bpl
  0x35a6e2268  je         0x35a6e22b0
  0x35a6e226a  mov        eax, dword ptr [rip + 0x282b1d0]
  0x35a6e2270  add        eax, 1
  0x35a6e2273  mov        dword ptr [rip + 0x282b1c7], eax
  0x35a6e2279  test       dil, dil
  0x35a6e227c  jne        0x35a6e22b0
  0x35a6e227e  cmp        eax, 2
  0x35a6e2281  jle        0x35a6e22b0
  0x35a6e2283  mov        rax, qword ptr [rbx]
  0x35a6e2286  mov        r8, qword ptr [rip + 0x282b1cb]
  0x35a6e228d  mov        rcx, rbx
  0x35a6e2290  mov        rdx, qword ptr [rip + 0x282b1f1]
  0x35a6e2297  call       qword ptr [rax + 0x468]
  0x35a6e229d  mov        dword ptr [rip + 0x282b199], 0
  0x35a6e22a7  nop        word ptr [rax + rax]
  0x35a6e22b0  mov        rax, qword ptr [rbx]
  0x35a6e22b3  mov        r8, qword ptr [rip + 0x282b1ae]
  0x35a6e22ba  mov        r9d, esi
  0x35a6e22bd  mov        rcx, rbx
  0x35a6e22c0  mov        rdx, qword ptr [rip + 0x282b1c1]
  0x35a6e22c7  call       qword ptr [rax + 0x468]
  0x35a6e22cd  test       dil, dil
  0x35a6e22d0  je         0x35a6e21e0
  0x35a6e22d6  mov        rax, qword ptr [rbx]
  0x35a6e22d9  mov        r8, qword ptr [rip + 0x282b170]
  0x35a6e22e0  mov        rcx, rbx
  0x35a6e22e3  mov        rdx, qword ptr [rip + 0x282b19e]
  0x35a6e22ea  mov        rax, qword ptr [rax + 0x468]
  0x35a6e22f1  add        rsp, 0x20
  0x35a6e22f5  pop        rbx
  0x35a6e22f6  pop        rsi
  0x35a6e22f7  pop        rdi
  0x35a6e22f8  pop        rbp
  0x35a6e22f9  pop        r12
  0x35a6e22fb  jmp        rax
  0x35a6e22fe  nop        
  0x35a6e2300  mov        rax, qword ptr [rbx]
  0x35a6e2303  mov        r8, qword ptr [rip + 0x282b176]
  0x35a6e230a  mov        rdx, rcx
  0x35a6e230d  mov        rcx, rbx
  0x35a6e2310  mov        rax, qword ptr [rax + 0x468]
  0x35a6e2317  add        rsp, 0x20
  0x35a6e231b  pop        rbx
  0x35a6e231c  pop        rsi
  0x35a6e231d  pop        rdi
  0x35a6e231e  pop        rbp
  0x35a6e231f  pop        r12
  0x35a6e2321  jmp        rax
  0x35a6e2324  nop        dword ptr [rax]
  0x35a6e2328  mov        rdx, rcx
  0x35a6e232b  mov        r8, qword ptr [rip + 0x282b12e]
  0x35a6e2332  mov        rcx, rbx
  0x35a6e2335  call       rax
  0x35a6e2337  test       bpl, bpl
  0x35a6e233a  je         0x35a6e22cd
  0x35a6e233c  mov        eax, dword ptr [rip + 0x282b0fe]
  0x35a6e2342  add        eax, 1
  0x35a6e2345  mov        dword ptr [rip + 0x282b0f5], eax
  0x35a6e234b  test       dil, dil
  0x35a6e234e  jne        0x35a6e22d6
  0x35a6e2350  cmp        eax, 2
  0x35a6e2353  jle        0x35a6e21e0
  0x35a6e2359  mov        rax, qword ptr [rbx]
  0x35a6e235c  mov        r8, qword ptr [rip + 0x282b0f5]
  0x35a6e2363  mov        rcx, rbx
  0x35a6e2366  mov        rdx, qword ptr [rip + 0x282b11b]
  0x35a6e236d  call       qword ptr [rax + 0x468]
  0x35a6e2373  mov        dword ptr [rip + 0x282b0c3], 0
  0x35a6e237d  jmp        0x35a6e21e0
  0x35a6e2382  nop        word ptr cs:[rax + rax]
  0x35a6e238d  nop        dword ptr [rax]

; ── EXPORT: Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleJump ──
Java_org_itsmahir_pagon_nativep_FakeFlyNative_nativeHandleJump:
  0x35a6e2390  mov        rdx, qword ptr [rip + 0x282b0f1]
  0x35a6e2397  test       rdx, rdx
  0x35a6e239a  je         0x35a6e23b0
  0x35a6e239c  mov        rax, qword ptr [rcx]
  0x35a6e239f  mov        r8, qword ptr [rip + 0x282b0d2]
  0x35a6e23a6  mov        rax, qword ptr [rax + 0x468]
  0x35a6e23ad  jmp        rax
  0x35a6e23b0  ret        
  0x35a6e23b1  nop        
  0x35a6e23b2  nop        
  0x35a6e23b3  nop        
  0x35a6e23b4  nop        
  0x35a6e23b5  nop        
  0x35a6e23b6  nop        
  0x35a6e23b7  nop        
  0x35a6e23b8  nop        
  0x35a6e23b9  nop        
  0x35a6e23ba  nop        
  0x35a6e23bb  nop        
  0x35a6e23bc  nop        
  0x35a6e23bd  nop        
  0x35a6e23be  nop        
  0x35a6e23bf  nop        
  0x35a6e23c0  push       rbp
  0x35a6e23c1  mov        rbp, rsp
  0x35a6e23c4  sub        rsp, 0x20
  0x35a6e23c8  mov        rax, qword ptr [rip + 0x6c41]
  0x35a6e23cf  mov        rax, qword ptr [rax]
  0x35a6e23d2  test       rax, rax
  0x35a6e23d5  je         0x35a6e23fd
  0x35a6e23d7  nop        word ptr [rax + rax]
  0x35a6e23e0  call       rax
  0x35a6e23e2  mov        rax, qword ptr [rip + 0x6c27]
  0x35a6e23e9  lea        rdx, [rax + 8]
  0x35a6e23ed  mov        rax, qword ptr [rax + 8]
  0x35a6e23f1  mov        qword ptr [rip + 0x6c18], rdx
  0x35a6e23f8  test       rax, rax
  0x35a6e23fb  jne        0x35a6e23e0
  0x35a6e23fd  add        rsp, 0x20
  0x35a6e2401  pop        rbp
  0x35a6e2402  ret        
  0x35a6e2403  nop        word ptr cs:[rax + rax]
  0x35a6e240e  nop        
  0x35a6e2410  push       rbp
  0x35a6e2411  push       rsi
  0x35a6e2412  push       rbx
  0x35a6e2413  sub        rsp, 0x20
  0x35a6e2417  lea        rbp, [rsp + 0x20]
  0x35a6e241c  mov        rdx, qword ptr [rip + 0x826d]
  0x35a6e2423  mov        rax, qword ptr [rdx]
  0x35a6e2426  mov        ecx, eax
  0x35a6e2428  cmp        eax, -1
  0x35a6e242b  je         0x35a6e2470
  0x35a6e242d  test       ecx, ecx
  0x35a6e242f  je         0x35a6e2453
  0x35a6e2431  mov        eax, ecx
  0x35a6e2433  sub        ecx, 1
  0x35a6e2436  lea        rbx, [rdx + rax*8]
  0x35a6e243a  sub        rax, rcx
  0x35a6e243d  lea        rsi, [rdx + rax*8 - 8]
  0x35a6e2442  nop        word ptr [rax + rax]
  0x35a6e2448  call       qword ptr [rbx]
  0x35a6e244a  sub        rbx, 8
  0x35a6e244e  cmp        rbx, rsi
  0x35a6e2451  jne        0x35a6e2448
  0x35a6e2453  lea        rcx, [rip - 0x9a]
  0x35a6e245a  add        rsp, 0x20
  0x35a6e245e  pop        rbx
  0x35a6e245f  pop        rsi
  0x35a6e2460  pop        rbp
  0x35a6e2461  jmp        0x35a6e1360
  0x35a6e2466  nop        word ptr cs:[rax + rax]
  0x35a6e2470  xor        eax, eax
  0x35a6e2472  nop        word ptr [rax + rax]
  0x35a6e2478  lea        r8d, [rax + 1]
  0x35a6e247c  mov        ecx, eax
  0x35a6e247e  cmp        qword ptr [rdx + r8*8], 0
  0x35a6e2483  mov        rax, r8
  0x35a6e2486  jne        0x35a6e2478
  0x35a6e2488  jmp        0x35a6e242d
  0x35a6e248a  nop        word ptr [rax + rax]
  0x35a6e2490  mov        eax, dword ptr [rip + 0x282affa]
  0x35a6e2496  test       eax, eax
  0x35a6e2498  je         0x35a6e24a0
  0x35a6e249a  ret        
  0x35a6e249b  nop        dword ptr [rax + rax]
  0x35a6e24a0  mov        dword ptr [rip + 0x282afe6], 1
  0x35a6e24aa  jmp        0x35a6e2410
  0x35a6e24af  nop        
  0x35a6e24b0  push       rbp
  0x35a6e24b1  mov        rbp, rsp
  0x35a6e24b4  sub        rsp, 0x20
  0x35a6e24b8  cmp        edx, 3
  0x35a6e24bb  je         0x35a6e24d0
  0x35a6e24bd  test       edx, edx
  0x35a6e24bf  je         0x35a6e24d0
  0x35a6e24c1  mov        eax, 1
  0x35a6e24c6  add        rsp, 0x20
  0x35a6e24ca  pop        rbp
  0x35a6e24cb  ret        
  0x35a6e24cc  nop        dword ptr [rax]
  0x35a6e24d0  call       0x35a6e2c60
  0x35a6e24d5  mov        eax, 1
  0x35a6e24da  add        rsp, 0x20
  0x35a6e24de  pop        rbp
  0x35a6e24df  ret        
  0x35a6e24e0  push       rbp
  0x35a6e24e1  push       rsi
  0x35a6e24e2  push       rbx
  0x35a6e24e3  sub        rsp, 0x20
  0x35a6e24e7  lea        rbp, [rsp + 0x20]
  0x35a6e24ec  mov        rax, qword ptr [rip + 0x818d]
  0x35a6e24f3  cmp        dword ptr [rax], 2
  0x35a6e24f6  je         0x35a6e24fe
  0x35a6e24f8  mov        dword ptr [rax], 2
  0x35a6e24fe  cmp        edx, 2
  0x35a6e2501  je         0x35a6e2518
  0x35a6e2503  cmp        edx, 1
  0x35a6e2506  je         0x35a6e2550
  0x35a6e2508  mov        eax, 1
  0x35a6e250d  add        rsp, 0x20
  0x35a6e2511  pop        rbx
  0x35a6e2512  pop        rsi
  0x35a6e2513  pop        rbp
  0x35a6e2514  ret        
  0x35a6e2515  nop        dword ptr [rax]
  0x35a6e2518  lea        rbx, [rip + 0x282db31]
  0x35a6e251f  lea        rsi, [rip + 0x282db2a]
  0x35a6e2526  cmp        rbx, rsi
  0x35a6e2529  je         0x35a6e2508
  0x35a6e252b  nop        dword ptr [rax + rax]
  0x35a6e2530  mov        rax, qword ptr [rbx]
  0x35a6e2533  test       rax, rax
  0x35a6e2536  je         0x35a6e253a
  0x35a6e2538  call       rax
  0x35a6e253a  add        rbx, 8
  0x35a6e253e  cmp        rbx, rsi
  0x35a6e2541  jne        0x35a6e2530
  0x35a6e2543  mov        eax, 1
  0x35a6e2548  add        rsp, 0x20
  0x35a6e254c  pop        rbx
  0x35a6e254d  pop        rsi
  0x35a6e254e  pop        rbp
  0x35a6e254f  ret        
  0x35a6e2550  call       0x35a6e2c60
  0x35a6e2555  mov        eax, 1
  0x35a6e255a  add        rsp, 0x20
  0x35a6e255e  pop        rbx
  0x35a6e255f  pop        rsi
  0x35a6e2560  pop        rbp
  0x35a6e2561  ret        
  0x35a6e2562  nop        word ptr cs:[rax + rax]
  0x35a6e256d  nop        dword ptr [rax]
  0x35a6e2570  xor        eax, eax
  0x35a6e2572  ret        
  0x35a6e2573  nop        
  0x35a6e2574  nop        
  0x35a6e2575  nop        
  0x35a6e2576  nop        
  0x35a6e2577  nop        
  0x35a6e2578  nop        
  0x35a6e2579  nop        
  0x35a6e257a  nop        
  0x35a6e257b  nop        
  0x35a6e257c  nop        
  0x35a6e257d  nop        
  0x35a6e257e  nop        
  0x35a6e257f  nop        
  0x35a6e2580  push       rbp
  0x35a6e2581  push       rsi
  0x35a6e2582  push       rbx
  0x35a6e2583  sub        rsp, 0x30
  0x35a6e2587  lea        rbp, [rsp + 0x30]
  0x35a6e258c  mov        rbx, rcx
  0x35a6e258f  lea        rax, [rbp + 0x28]
  0x35a6e2593  mov        ecx, 2
  0x35a6e2598  mov        qword ptr [rbp + 0x28], rdx
  0x35a6e259c  mov        qword ptr [rbp + 0x30], r8
  0x35a6e25a0  mov        qword ptr [rbp + 0x38], r9
  0x35a6e25a4  mov        qword ptr [rbp - 8], rax
  0x35a6e25a8  call       0x35a6e7fe0
  0x35a6e25ad  mov        r8d, 0x1b
  0x35a6e25b3  mov        edx, 1
  0x35a6e25b8  lea        rcx, [rip + 0x7be1]
  0x35a6e25bf  mov        r9, rax
  0x35a6e25c2  call       0x35a6e8720
  0x35a6e25c7  mov        rsi, qword ptr [rbp - 8]
  0x35a6e25cb  mov        ecx, 2
  0x35a6e25d0  call       0x35a6e7fe0
  0x35a6e25d5  mov        rdx, rbx
  0x35a6e25d8  mov        rcx, rax
  0x35a6e25db  mov        r8, rsi
  0x35a6e25de  call       0x35a6e8778
  0x35a6e25e3  call       0x35a6e8700
  0x35a6e25e8  nop        
  0x35a6e25e9  nop        dword ptr [rax]
  0x35a6e25f0  push       rbp
  0x35a6e25f1  push       rdi
  0x35a6e25f2  push       rsi
  0x35a6e25f3  push       rbx
  0x35a6e25f4  sub        rsp, 0x58
  0x35a6e25f8  lea        rbp, [rsp + 0x50]
  0x35a6e25fd  movsxd     rsi, dword ptr [rip + 0x282aed0]
  0x35a6e2604  mov        rbx, rcx
  0x35a6e2607  test       esi, esi
  0x35a6e2609  jle        0x35a6e2720
  0x35a6e260f  mov        rax, qword ptr [rip + 0x282aec2]
  0x35a6e2616  xor        r9d, r9d
  0x35a6e2619  add        rax, 0x18
  0x35a6e261d  nop        dword ptr [rax]
  0x35a6e2620  mov        r8, qword ptr [rax]
  0x35a6e2623  cmp        rbx, r8
  0x35a6e2626  jb         0x35a6e263b
  0x35a6e2628  mov        rdx, qword ptr [rax + 8]
  0x35a6e262c  mov        edx, dword ptr [rdx + 8]
  0x35a6e262f  add        r8, rdx
  0x35a6e2632  cmp        rbx, r8
  0x35a6e2635  jb         0x35a6e26c3
  0x35a6e263b  add        r9d, 1
  0x35a6e263f  add        rax, 0x28
  0x35a6e2643  cmp        r9d, esi
  0x35a6e2646  jne        0x35a6e2620
  0x35a6e2648  mov        rcx, rbx
  0x35a6e264b  call       0x35a6e2e90
  0x35a6e2650  mov        rdi, rax
  0x35a6e2653  test       rax, rax
  0x35a6e2656  je         0x35a6e2742
  0x35a6e265c  mov        rax, qword ptr [rip + 0x282ae75]
  0x35a6e2663  lea        rbx, [rsi + rsi*4]
  0x35a6e2667  shl        rbx, 3
  0x35a6e266b  add        rax, rbx
  0x35a6e266e  mov        qword ptr [rax + 0x20], rdi
  0x35a6e2672  mov        dword ptr [rax], 0
  0x35a6e2678  call       0x35a6e2fd0
  0x35a6e267d  mov        edx, dword ptr [rdi + 0xc]
  0x35a6e2680  mov        r8d, 0x30
  0x35a6e2686  lea        rcx, [rax + rdx]
  0x35a6e268a  mov        rax, qword ptr [rip + 0x282ae47]
  0x35a6e2691  lea        rdx, [rbp - 0x30]
  0x35a6e2695  mov        qword ptr [rax + rbx + 0x18], rcx
  0x35a6e269a  call       qword ptr [rip + 0x282cb28]
  0x35a6e26a0  test       rax, rax
  0x35a6e26a3  je         0x35a6e2727
  0x35a6e26a9  mov        eax, dword ptr [rbp - 0xc]
  0x35a6e26ac  lea        edx, [rax - 4]
  0x35a6e26af  and        edx, 0xfffffffb
  0x35a6e26b2  je         0x35a6e26bc
  0x35a6e26b4  lea        edx, [rax - 0x40]
  0x35a6e26b7  and        edx, 0xffffffbf
  0x35a6e26ba  jne        0x35a6e26d0
  0x35a6e26bc  add        dword ptr [rip + 0x282ae11], 1
  0x35a6e26c3  add        rsp, 0x58
  0x35a6e26c7  pop        rbx
  0x35a6e26c8  pop        rsi
  0x35a6e26c9  pop        rdi
  0x35a6e26ca  pop        rbp
  0x35a6e26cb  ret        
  0x35a6e26cc  nop        dword ptr [rax]
  0x35a6e26d0  cmp        eax, 2
  0x35a6e26d3  mov        rcx, qword ptr [rbp - 0x30]
  0x35a6e26d7  mov        rdx, qword ptr [rbp - 0x18]
  0x35a6e26db  mov        r8d, 0x40
  0x35a6e26e1  mov        eax, 4
  0x35a6e26e6  cmove      r8d, eax
  0x35a6e26ea  add        rbx, qword ptr [rip + 0x282ade7]
  0x35a6e26f1  mov        qword ptr [rbx + 8], rcx
  0x35a6e26f5  mov        r9, rbx
  0x35a6e26f8  mov        qword ptr [rbx + 0x10], rdx
  0x35a6e26fc  call       qword ptr [rip + 0x282cabe]
  0x35a6e2702  test       eax, eax
  0x35a6e2704  jne        0x35a6e26bc
  0x35a6e2706  call       qword ptr [rip + 0x282ca7c]
  0x35a6e270c  lea        rcx, [rip + 0x7b05]
  0x35a6e2713  mov        edx, eax
  0x35a6e2715  call       0x35a6e2580
  0x35a6e271a  nop        word ptr [rax + rax]
  0x35a6e2720  xor        esi, esi
  0x35a6e2722  jmp        0x35a6e2648
  0x35a6e2727  mov        rax, qword ptr [rip + 0x282adaa]
  0x35a6e272e  mov        edx, dword ptr [rdi + 8]
  0x35a6e2731  lea        rcx, [rip + 0x7aa8]
  0x35a6e2738  mov        r8, qword ptr [rax + rbx + 0x18]
  0x35a6e273d  call       0x35a6e2580
  0x35a6e2742  mov        rdx, rbx
  0x35a6e2745  lea        rcx, [rip + 0x7a74]
  0x35a6e274c  call       0x35a6e2580
  0x35a6e2751  nop        
  0x35a6e2752  nop        word ptr cs:[rax + rax]
  0x35a6e275d  nop        dword ptr [rax]
  0x35a6e2760  push       rbp
  0x35a6e2761  push       r15
  0x35a6e2763  push       r14
  0x35a6e2765  push       r13
  0x35a6e2767  push       r12
  0x35a6e2769  push       rdi
  0x35a6e276a  push       rsi
  0x35a6e276b  push       rbx
  0x35a6e276c  sub        rsp, 0x48
  0x35a6e2770  lea        rbp, [rsp + 0x40]
  0x35a6e2775  mov        r12d, dword ptr [rip + 0x282ad54]
  0x35a6e277c  test       r12d, r12d
  0x35a6e277f  je         0x35a6e2798
  0x35a6e2781  lea        rsp, [rbp + 8]
  0x35a6e2785  pop        rbx
  0x35a6e2786  pop        rsi
  0x35a6e2787  pop        rdi
  0x35a6e2788  pop        r12
  0x35a6e278a  pop        r13
  0x35a6e278c  pop        r14
  0x35a6e278e  pop        r15
  0x35a6e2790  pop        rbp
  0x35a6e2791  ret        
  0x35a6e2792  nop        word ptr [rax + rax]
  0x35a6e2798  mov        dword ptr [rip + 0x282ad2e], 1
  0x35a6e27a2  call       0x35a6e2f10
  0x35a6e27a7  cdqe       
  0x35a6e27a9  lea        rax, [rax + rax*4]
  0x35a6e27ad  lea        rax, [rax*8 + 0xf]
  0x35a6e27b5  and        rax, 0xfffffffffffffff0
  0x35a6e27b9  call       0x35a6e3170
  0x35a6e27be  mov        r13, qword ptr [rip + 0x7edb]
  0x35a6e27c5  mov        rbx, qword ptr [rip + 0x7ee4]
  0x35a6e27cc  mov        dword ptr [rip + 0x282acfe], 0
  0x35a6e27d6  sub        rsp, rax
  0x35a6e27d9  lea        rax, [rsp + 0x30]
  0x35a6e27de  mov        qword ptr [rip + 0x282acf3], rax
  0x35a6e27e5  mov        rax, r13
  0x35a6e27e8  sub        rax, rbx
  0x35a6e27eb  cmp        rax, 7
  0x35a6e27ef  jle        0x35a6e2781
  0x35a6e27f1  mov        edx, dword ptr [rbx]
  0x35a6e27f3  cmp        rax, 0xb
  0x35a6e27f7  jg         0x35a6e2900
  0x35a6e27fd  mov        eax, dword ptr [rbx]
  0x35a6e27ff  test       eax, eax
  0x35a6e2801  jne        0x35a6e2a70
  0x35a6e2807  mov        eax, dword ptr [rbx + 4]
  0x35a6e280a  test       eax, eax
  0x35a6e280c  jne        0x35a6e2a70
  0x35a6e2812  mov        edx, dword ptr [rbx + 8]
  0x35a6e2815  cmp        edx, 1
  0x35a6e2818  jne        0x35a6e2ab0
  0x35a6e281e  add        rbx, 0xc
  0x35a6e2822  cmp        rbx, r13
  0x35a6e2825  jae        0x35a6e2781
  0x35a6e282b  mov        r14, qword ptr [rip + 0x7e9e]
  0x35a6e2832  mov        r15d, 0xffffffff
  0x35a6e2838  jmp        0x35a6e289f
  0x35a6e283a  nop        word ptr [rax + rax]
  0x35a6e2840  cmp        ecx, 8
  0x35a6e2843  je         0x35a6e2920
  0x35a6e2849  cmp        ecx, 0x10
  0x35a6e284c  jne        0x35a6e2aa2
  0x35a6e2852  movzx      esi, word ptr [rdi]
  0x35a6e2855  and        edx, 0xc0
  0x35a6e285b  test       si, si
  0x35a6e285e  jns        0x35a6e2a30
  0x35a6e2864  or         rsi, 0xffffffffffff0000
  0x35a6e286b  sub        rsi, rax
  0x35a6e286e  add        rsi, r9
  0x35a6e2871  test       edx, edx
  0x35a6e2873  jne        0x35a6e2887
  0x35a6e2875  cmp        rsi, -0x8000
  0x35a6e287c  jl         0x35a6e28e3
  0x35a6e287e  cmp        rsi, 0xffff
  0x35a6e2885  jg         0x35a6e28e3
  0x35a6e2887  mov        rcx, rdi
  0x35a6e288a  call       0x35a6e25f0
  0x35a6e288f  mov        word ptr [rdi], si
  0x35a6e2892  add        rbx, 0xc
  0x35a6e2896  cmp        rbx, r13
  0x35a6e2899  jae        0x35a6e2970
  0x35a6e289f  mov        eax, dword ptr [rbx]
  0x35a6e28a1  mov        edx, dword ptr [rbx + 8]
  0x35a6e28a4  mov        edi, dword ptr [rbx + 4]
  0x35a6e28a7  add        rax, r14
  0x35a6e28aa  movzx      ecx, dl
  0x35a6e28ad  mov        r9, qword ptr [rax]
  0x35a6e28b0  add        rdi, r14
  0x35a6e28b3  cmp        ecx, 0x20
  0x35a6e28b6  je         0x35a6e29c8
  0x35a6e28bc  jbe        0x35a6e2840
  0x35a6e28be  cmp        ecx, 0x40
  0x35a6e28c1  jne        0x35a6e2aa2
  0x35a6e28c7  mov        rsi, qword ptr [rdi]
  0x35a6e28ca  mov        ecx, edx
  0x35a6e28cc  sub        rsi, rax
  0x35a6e28cf  add        rsi, r9
  0x35a6e28d2  and        ecx, 0xc0
  0x35a6e28d8  jne        0x35a6e2a20
  0x35a6e28de  test       rsi, rsi
  0x35a6e28e1  js         0x35a6e2892
  0x35a6e28e3  mov        qword ptr [rsp + 0x20], rsi
  0x35a6e28e8  mov        edx, ecx
  0x35a6e28ea  mov        r8, rdi
  0x35a6e28ed  lea        rcx, [rip + 0x79b4]
  0x35a6e28f4  call       0x35a6e2580
  0x35a6e28f9  nop        dword ptr [rax]
  0x35a6e2900  test       edx, edx
  0x35a6e2902  jne        0x35a6e2a70
  0x35a6e2908  mov        eax, dword ptr [rbx + 4]
  0x35a6e290b  mov        edx, eax
  0x35a6e290d  or         edx, dword ptr [rbx + 8]
  0x35a6e2910  jne        0x35a6e280a
  0x35a6e2916  add        rbx, 0xc
  0x35a6e291a  jmp        0x35a6e27fd
  0x35a6e291f  nop        
  0x35a6e2920  movzx      esi, byte ptr [rdi]
  0x35a6e2923  and        edx, 0xc0
  0x35a6e2929  test       sil, sil
  0x35a6e292c  jns        0x35a6e2a58
  0x35a6e2932  or         rsi, 0xffffffffffffff00
  0x35a6e2939  sub        rsi, rax
  0x35a6e293c  add        rsi, r9
  0x35a6e293f  test       edx, edx
  0x35a6e2941  jne        0x35a6e2952
  0x35a6e2943  cmp        rsi, 0xff
  0x35a6e294a  jg         0x35a6e28e3
  0x35a6e294c  cmp        rsi, -0x80
  0x35a6e2950  jl         0x35a6e28e3
  0x35a6e2952  mov        rcx, rdi
  0x35a6e2955  add        rbx, 0xc
  0x35a6e2959  call       0x35a6e25f0
  0x35a6e295e  mov        byte ptr [rdi], sil
  0x35a6e2961  cmp        rbx, r13
  0x35a6e2964  jb         0x35a6e289f
  0x35a6e296a  nop        word ptr [rax + rax]
  0x35a6e2970  mov        edx, dword ptr [rip + 0x282ab5e]
  0x35a6e2976  test       edx, edx
  0x35a6e2978  jle        0x35a6e2781
  0x35a6e297e  mov        rsi, qword ptr [rip + 0x282c83b]
  0x35a6e2985  xor        ebx, ebx
  0x35a6e2987  lea        rdi, [rbp - 4]
  0x35a6e298b  nop        dword ptr [rax + rax]
  0x35a6e2990  mov        rax, qword ptr [rip + 0x282ab41]
  0x35a6e2997  add        rax, rbx
  0x35a6e299a  mov        r8d, dword ptr [rax]
  0x35a6e299d  test       r8d, r8d
  0x35a6e29a0  je         0x35a6e29af
  0x35a6e29a2  mov        rdx, qword ptr [rax + 0x10]
  0x35a6e29a6  mov        rcx, qword ptr [rax + 8]
  0x35a6e29aa  mov        r9, rdi
  0x35a6e29ad  call       rsi
  0x35a6e29af  add        r12d, 1
  0x35a6e29b3  add        rbx, 0x28
  0x35a6e29b7  cmp        r12d, dword ptr [rip + 0x282ab16]
  0x35a6e29be  jl         0x35a6e2990
  0x35a6e29c0  jmp        0x35a6e2781
  0x35a6e29c5  nop        dword ptr [rax]
  0x35a6e29c8  mov        esi, dword ptr [rdi]
  0x35a6e29ca  and        edx, 0xc0
  0x35a6e29d0  test       esi, esi
  0x35a6e29d2  jns        0x35a6e2a48
  0x35a6e29d4  movabs     r11, 0xffffffff00000000
  0x35a6e29de  or         rsi, r11
  0x35a6e29e1  sub        rsi, rax
  0x35a6e29e4  add        rsi, r9
  0x35a6e29e7  test       edx, edx
  0x35a6e29e9  jne        0x35a6e2a07
  0x35a6e29eb  cmp        rsi, r15
  0x35a6e29ee  jg         0x35a6e28e3
  0x35a6e29f4  movabs     rax, 0xffffffff7fffffff
  0x35a6e29fe  cmp        rsi, rax
  0x35a6e2a01  jle        0x35a6e28e3
  0x35a6e2a07  mov        rcx, rdi
  0x35a6e2a0a  call       0x35a6e25f0
  0x35a6e2a0f  mov        dword ptr [rdi], esi
  0x35a6e2a11  jmp        0x35a6e2892
  0x35a6e2a16  nop        word ptr cs:[rax + rax]
  0x35a6e2a20  mov        rcx, rdi
  0x35a6e2a23  call       0x35a6e25f0
  0x35a6e2a28  mov        qword ptr [rdi], rsi
  0x35a6e2a2b  jmp        0x35a6e2892
  0x35a6e2a30  sub        rsi, rax
  0x35a6e2a33  add        rsi, r9
  0x35a6e2a36  test       edx, edx
  0x35a6e2a38  je         0x35a6e2875
  0x35a6e2a3e  jmp        0x35a6e2887
  0x35a6e2a43  nop        dword ptr [rax + rax]
  0x35a6e2a48  sub        rsi, rax
  0x35a6e2a4b  add        rsi, r9
  0x35a6e2a4e  test       edx, edx
  0x35a6e2a50  je         0x35a6e29eb
  0x35a6e2a52  jmp        0x35a6e2a07
  0x35a6e2a54  nop        dword ptr [rax]
  0x35a6e2a58  sub        rsi, rax
  0x35a6e2a5b  add        rsi, r9
  0x35a6e2a5e  test       edx, edx
  0x35a6e2a60  je         0x35a6e2943
  0x35a6e2a66  jmp        0x35a6e2952
  0x35a6e2a6b  nop        dword ptr [rax + rax]
  0x35a6e2a70  cmp        rbx, r13
  0x35a6e2a73  jae        0x35a6e2781
  0x35a6e2a79  mov        r14, qword ptr [rip + 0x7c50]
  0x35a6e2a80  mov        esi, dword ptr [rbx + 4]
  0x35a6e2a83  mov        edi, dword ptr [rbx]
  0x35a6e2a85  add        rbx, 8
  0x35a6e2a89  add        rsi, r14
  0x35a6e2a8c  add        edi, dword ptr [rsi]
  0x35a6e2a8e  mov        rcx, rsi
  0x35a6e2a91  call       0x35a6e25f0
  0x35a6e2a96  mov        dword ptr [rsi], edi
  0x35a6e2a98  cmp        rbx, r13
  0x35a6e2a9b  jb         0x35a6e2a80
  0x35a6e2a9d  jmp        0x35a6e2970
  0x35a6e2aa2  mov        edx, ecx
  0x35a6e2aa4  lea        rcx, [rip + 0x77cd]
  0x35a6e2aab  call       0x35a6e2580
  0x35a6e2ab0  lea        rcx, [rip + 0x7789]
  0x35a6e2ab7  call       0x35a6e2580
  0x35a6e2abc  nop        
  0x35a6e2abd  nop        
  0x35a6e2abe  nop        
  0x35a6e2abf  nop        
  0x35a6e2ac0  push       rbp
  0x35a6e2ac1  push       r13
  0x35a6e2ac3  push       r12
  0x35a6e2ac5  push       rdi
  0x35a6e2ac6  push       rsi
  0x35a6e2ac7  push       rbx
  0x35a6e2ac8  sub        rsp, 0x28
  0x35a6e2acc  lea        rbp, [rsp + 0x20]
  0x35a6e2ad1  lea        r13, [rip + 0x282aa28]
  0x35a6e2ad8  mov        rcx, r13
  0x35a6e2adb  call       qword ptr [rip + 0x282c69f]
  0x35a6e2ae1  mov        rbx, qword ptr [rip + 0x282a9f8]
  0x35a6e2ae8  test       rbx, rbx
  0x35a6e2aeb  je         0x35a6e2b25
  0x35a6e2aed  mov        r12, qword ptr [rip + 0x282c6c4]
  0x35a6e2af4  mov        rdi, qword ptr [rip + 0x282c68d]
  0x35a6e2afb  nop        dword ptr [rax + rax]
  0x35a6e2b00  mov        ecx, dword ptr [rbx]
  0x35a6e2b02  call       r12
  0x35a6e2b05  mov        rsi, rax
  0x35a6e2b08  call       rdi
  0x35a6e2b0a  test       rsi, rsi
  0x35a6e2b0d  je         0x35a6e2b1c
  0x35a6e2b0f  test       eax, eax
  0x35a6e2b11  jne        0x35a6e2b1c
  0x35a6e2b13  mov        rax, qword ptr [rbx + 8]
  0x35a6e2b17  mov        rcx, rsi
  0x35a6e2b1a  call       rax
  0x35a6e2b1c  mov        rbx, qword ptr [rbx + 0x10]
  0x35a6e2b20  test       rbx, rbx
  0x35a6e2b23  jne        0x35a6e2b00
  0x35a6e2b25  mov        rcx, r13
  0x35a6e2b28  add        rsp, 0x28
  0x35a6e2b2c  pop        rbx
  0x35a6e2b2d  pop        rsi
  0x35a6e2b2e  pop        rdi
  0x35a6e2b2f  pop        r12
  0x35a6e2b31  pop        r13
  0x35a6e2b33  pop        rbp
  0x35a6e2b34  jmp        qword ptr [rip + 0x282c665]
  0x35a6e2b3b  nop        dword ptr [rax + rax]
  0x35a6e2b40  push       rbp
  0x35a6e2b41  push       rdi
  0x35a6e2b42  push       rsi
  0x35a6e2b43  push       rbx
  0x35a6e2b44  sub        rsp, 0x28
  0x35a6e2b48  lea        rbp, [rsp + 0x20]
  0x35a6e2b4d  mov        eax, dword ptr [rip + 0x282a995]
  0x35a6e2b53  mov        edi, ecx
  0x35a6e2b55  mov        rsi, rdx
  0x35a6e2b58  test       eax, eax
  0x35a6e2b5a  jne        0x35a6e2b70
  0x35a6e2b5c  xor        eax, eax
  0x35a6e2b5e  add        rsp, 0x28
  0x35a6e2b62  pop        rbx
  0x35a6e2b63  pop        rsi
  0x35a6e2b64  pop        rdi
  0x35a6e2b65  pop        rbp
  0x35a6e2b66  ret        
  0x35a6e2b67  nop        word ptr [rax + rax]
  0x35a6e2b70  mov        edx, 0x18
  0x35a6e2b75  mov        ecx, 1
  0x35a6e2b7a  call       0x35a6e8708
  0x35a6e2b7f  mov        rbx, rax
  0x35a6e2b82  test       rax, rax
  0x35a6e2b85  je         0x35a6e2bba
  0x35a6e2b87  mov        qword ptr [rax + 8], rsi
  0x35a6e2b8b  lea        rsi, [rip + 0x282a96e]
  0x35a6e2b92  mov        dword ptr [rax], edi
  0x35a6e2b94  mov        rcx, rsi
  0x35a6e2b97  call       qword ptr [rip + 0x282c5e3]
  0x35a6e2b9d  mov        rax, qword ptr [rip + 0x282a93c]
  0x35a6e2ba4  mov        rcx, rsi
  0x35a6e2ba7  mov        qword ptr [rip + 0x282a932], rbx
  0x35a6e2bae  mov        qword ptr [rbx + 0x10], rax
  0x35a6e2bb2  call       qword ptr [rip + 0x282c5e8]
  0x35a6e2bb8  jmp        0x35a6e2b5c
  0x35a6e2bba  or         eax, 0xffffffff
  0x35a6e2bbd  jmp        0x35a6e2b5e
  0x35a6e2bbf  nop        
  0x35a6e2bc0  push       rbp
  0x35a6e2bc1  push       rsi
  0x35a6e2bc2  push       rbx
  0x35a6e2bc3  sub        rsp, 0x20
  0x35a6e2bc7  lea        rbp, [rsp + 0x20]
  0x35a6e2bcc  mov        eax, dword ptr [rip + 0x282a916]
  0x35a6e2bd2  mov        ebx, ecx
  0x35a6e2bd4  test       eax, eax
  0x35a6e2bd6  jne        0x35a6e2be8
  0x35a6e2bd8  xor        eax, eax
  0x35a6e2bda  add        rsp, 0x20
  0x35a6e2bde  pop        rbx
  0x35a6e2bdf  pop        rsi
  0x35a6e2be0  pop        rbp
  0x35a6e2be1  ret        
  0x35a6e2be2  nop        word ptr [rax + rax]
  0x35a6e2be8  lea        rsi, [rip + 0x282a911]
  0x35a6e2bef  mov        rcx, rsi
  0x35a6e2bf2  call       qword ptr [rip + 0x282c588]
  0x35a6e2bf8  mov        rcx, qword ptr [rip + 0x282a8e1]
  0x35a6e2bff  test       rcx, rcx
  0x35a6e2c02  je         0x35a6e2c33
  0x35a6e2c04  xor        edx, edx
  0x35a6e2c06  jmp        0x35a6e2c1b
  0x35a6e2c08  nop        dword ptr [rax + rax]
  0x35a6e2c10  mov        rdx, rcx
  0x35a6e2c13  test       rax, rax
  0x35a6e2c16  je         0x35a6e2c33
  0x35a6e2c18  mov        rcx, rax
  0x35a6e2c1b  mov        eax, dword ptr [rcx]
  0x35a6e2c1d  cmp        eax, ebx
  0x35a6e2c1f  mov        rax, qword ptr [rcx + 0x10]
  0x35a6e2c23  jne        0x35a6e2c10
  0x35a6e2c25  test       rdx, rdx
  0x35a6e2c28  je         0x35a6e2c50
  0x35a6e2c2a  mov        qword ptr [rdx + 0x10], rax
  0x35a6e2c2e  call       0x35a6e8718
  0x35a6e2c33  mov        rcx, rsi
  0x35a6e2c36  call       qword ptr [rip + 0x282c564]
  0x35a6e2c3c  xor        eax, eax
  0x35a6e2c3e  add        rsp, 0x20
  0x35a6e2c42  pop        rbx
  0x35a6e2c43  pop        rsi
  0x35a6e2c44  pop        rbp
  0x35a6e2c45  ret        
  0x35a6e2c46  nop        word ptr cs:[rax + rax]
  0x35a6e2c50  mov        qword ptr [rip + 0x282a889], rax
  0x35a6e2c57  jmp        0x35a6e2c2e
  0x35a6e2c59  nop        dword ptr [rax]
  0x35a6e2c60  push       rbp
  0x35a6e2c61  push       rbx
  0x35a6e2c62  sub        rsp, 0x28
  0x35a6e2c66  lea        rbp, [rsp + 0x20]
  0x35a6e2c6b  cmp        edx, 2
  0x35a6e2c6e  je         0x35a6e2d20
  0x35a6e2c74  ja         0x35a6e2ca0
  0x35a6e2c76  test       edx, edx
  0x35a6e2c78  je         0x35a6e2cc0
  0x35a6e2c7a  mov        eax, dword ptr [rip + 0x282a868]
  0x35a6e2c80  test       eax, eax
  0x35a6e2c82  je         0x35a6e2d40
  0x35a6e2c88  mov        dword ptr [rip + 0x282a856], 1
  0x35a6e2c92  mov        eax, 1
  0x35a6e2c97  add        rsp, 0x28
  0x35a6e2c9b  pop        rbx
  0x35a6e2c9c  pop        rbp
  0x35a6e2c9d  ret        
  0x35a6e2c9e  nop        
  0x35a6e2ca0  cmp        edx, 3
  0x35a6e2ca3  jne        0x35a6e2c92
  0x35a6e2ca5  mov        eax, dword ptr [rip + 0x282a83d]
  0x35a6e2cab  test       eax, eax
  0x35a6e2cad  je         0x35a6e2c92
  0x35a6e2caf  call       0x35a6e2ac0
  0x35a6e2cb4  jmp        0x35a6e2c92
  0x35a6e2cb6  nop        word ptr cs:[rax + rax]
  0x35a6e2cc0  mov        eax, dword ptr [rip + 0x282a822]
  0x35a6e2cc6  test       eax, eax
  0x35a6e2cc8  jne        0x35a6e2d38
  0x35a6e2cca  mov        eax, dword ptr [rip + 0x282a818]
  0x35a6e2cd0  cmp        eax, 1
  0x35a6e2cd3  jne        0x35a6e2c92
  0x35a6e2cd5  mov        rbx, qword ptr [rip + 0x282a804]
  0x35a6e2cdc  test       rbx, rbx
  0x35a6e2cdf  je         0x35a6e2cf9
  0x35a6e2ce1  nop        dword ptr [rax]
  0x35a6e2ce8  mov        rcx, rbx
  0x35a6e2ceb  mov        rbx, qword ptr [rbx + 0x10]
  0x35a6e2cef  call       0x35a6e8718
  0x35a6e2cf4  test       rbx, rbx
  0x35a6e2cf7  jne        0x35a6e2ce8
  0x35a6e2cf9  lea        rcx, [rip + 0x282a800]
  0x35a6e2d00  mov        qword ptr [rip + 0x282a7d5], 0
  0x35a6e2d0b  mov        dword ptr [rip + 0x282a7d3], 0
  0x35a6e2d15  call       qword ptr [rip + 0x282c45d]
  0x35a6e2d1b  jmp        0x35a6e2c92
  0x35a6e2d20  call       0x35a6e3160
  0x35a6e2d25  mov        eax, 1
  0x35a6e2d2a  add        rsp, 0x28
  0x35a6e2d2e  pop        rbx
  0x35a6e2d2f  pop        rbp
  0x35a6e2d30  ret        
  0x35a6e2d31  nop        dword ptr [rax]
  0x35a6e2d38  call       0x35a6e2ac0
  0x35a6e2d3d  jmp        0x35a6e2cca
  0x35a6e2d3f  nop        
  0x35a6e2d40  lea        rcx, [rip + 0x282a7b9]
  0x35a6e2d47  call       qword ptr [rip + 0x282c443]
  0x35a6e2d4d  jmp        0x35a6e2c88
  0x35a6e2d52  nop        
  0x35a6e2d53  nop        
  0x35a6e2d54  nop        
  0x35a6e2d55  nop        
  0x35a6e2d56  nop        
  0x35a6e2d57  nop        
  0x35a6e2d58  nop        
  0x35a6e2d59  nop        
  0x35a6e2d5a  nop        
  0x35a6e2d5b  nop        
  0x35a6e2d5c  nop        
  0x35a6e2d5d  nop        
  0x35a6e2d5e  nop        
  0x35a6e2d5f  nop        
  0x35a6e2d60  xor        eax, eax
  0x35a6e2d62  cmp        word ptr [rcx], 0x5a4d
  0x35a6e2d67  jne        0x35a6e2d78
  0x35a6e2d69  movsxd     rdx, dword ptr [rcx + 0x3c]
  0x35a6e2d6d  add        rcx, rdx
  0x35a6e2d70  cmp        dword ptr [rcx], 0x4550
  0x35a6e2d76  je         0x35a6e2d80
  0x35a6e2d78  ret        
  0x35a6e2d79  nop        dword ptr [rax]
  0x35a6e2d80  xor        eax, eax
  0x35a6e2d82  cmp        word ptr [rcx + 0x18], 0x20b
  0x35a6e2d88  sete       al
  0x35a6e2d8b  ret        
  0x35a6e2d8c  nop        dword ptr [rax]
  0x35a6e2d90  movsxd     rax, dword ptr [rcx + 0x3c]
  0x35a6e2d94  add        rcx, rax
  0x35a6e2d97  movzx      eax, word ptr [rcx + 0x14]
  0x35a6e2d9b  movzx      r8d, word ptr [rcx + 6]
  0x35a6e2da0  lea        rax, [rcx + rax + 0x18]
  0x35a6e2da5  test       r8w, r8w
  0x35a6e2da9  je         0x35a6e2ddd
  0x35a6e2dab  lea        ecx, [r8 - 1]
  0x35a6e2daf  lea        rcx, [rcx + rcx*4]
  0x35a6e2db3  lea        r9, [rax + rcx*8 + 0x28]
  0x35a6e2db8  nop        dword ptr [rax + rax]
  0x35a6e2dc0  mov        r8d, dword ptr [rax + 0xc]
  0x35a6e2dc4  mov        rcx, r8
  0x35a6e2dc7  cmp        rdx, r8
  0x35a6e2dca  jb         0x35a6e2dd4
  0x35a6e2dcc  add        ecx, dword ptr [rax + 8]
  0x35a6e2dcf  cmp        rdx, rcx
  0x35a6e2dd2  jb         0x35a6e2ddf
  0x35a6e2dd4  add        rax, 0x28
  0x35a6e2dd8  cmp        rax, r9
  0x35a6e2ddb  jne        0x35a6e2dc0
  0x35a6e2ddd  xor        eax, eax
  0x35a6e2ddf  ret        
  0x35a6e2de0  push       rbp
  0x35a6e2de1  push       rdi
  0x35a6e2de2  push       rsi
  0x35a6e2de3  push       rbx
  0x35a6e2de4  sub        rsp, 0x28
  0x35a6e2de8  lea        rbp, [rsp + 0x20]
  0x35a6e2ded  mov        rsi, rcx
  0x35a6e2df0  call       0x35a6e8760
  0x35a6e2df5  cmp        rax, 8
  0x35a6e2df9  ja         0x35a6e2e78
  0x35a6e2dfb  mov        rdx, qword ptr [rip + 0x78ce]
  0x35a6e2e02  xor        ebx, ebx
  0x35a6e2e04  cmp        word ptr [rdx], 0x5a4d
  0x35a6e2e09  jne        0x35a6e2e66
  0x35a6e2e0b  movsxd     rax, dword ptr [rdx + 0x3c]
  0x35a6e2e0f  add        rax, rdx
  0x35a6e2e12  cmp        dword ptr [rax], 0x4550
  0x35a6e2e18  jne        0x35a6e2e66
  0x35a6e2e1a  cmp        word ptr [rax + 0x18], 0x20b
  0x35a6e2e20  jne        0x35a6e2e66
  0x35a6e2e22  movzx      edx, word ptr [rax + 0x14]
  0x35a6e2e26  lea        rbx, [rax + rdx + 0x18]
  0x35a6e2e2b  movzx      edx, word ptr [rax + 6]
  0x35a6e2e2f  test       dx, dx
  0x35a6e2e32  je         0x35a6e2e78
  0x35a6e2e34  lea        eax, [rdx - 1]
  0x35a6e2e37  lea        rax, [rax + rax*4]
  0x35a6e2e3b  lea        rdi, [rbx + rax*8 + 0x28]
  0x35a6e2e40  jmp        0x35a6e2e51
  0x35a6e2e42  nop        word ptr [rax + rax]
  0x35a6e2e48  add        rbx, 0x28
  0x35a6e2e4c  cmp        rbx, rdi
  0x35a6e2e4f  je         0x35a6e2e78
  0x35a6e2e51  mov        r8d, 8
  0x35a6e2e57  mov        rdx, rsi
  0x35a6e2e5a  mov        rcx, rbx
  0x35a6e2e5d  call       0x35a6e8768
  0x35a6e2e62  test       eax, eax
  0x35a6e2e64  jne        0x35a6e2e48
  0x35a6e2e66  mov        rax, rbx
  0x35a6e2e69  add        rsp, 0x28
  0x35a6e2e6d  pop        rbx
  0x35a6e2e6e  pop        rsi
  0x35a6e2e6f  pop        rdi
  0x35a6e2e70  pop        rbp
  0x35a6e2e71  ret        
  0x35a6e2e72  nop        word ptr [rax + rax]
  0x35a6e2e78  xor        ebx, ebx
  0x35a6e2e7a  mov        rax, rbx
  0x35a6e2e7d  add        rsp, 0x28
  0x35a6e2e81  pop        rbx
  0x35a6e2e82  pop        rsi
  0x35a6e2e83  pop        rdi
  0x35a6e2e84  pop        rbp
  0x35a6e2e85  ret        
  0x35a6e2e86  nop        word ptr cs:[rax + rax]
  0x35a6e2e90  mov        rdx, qword ptr [rip + 0x7839]
  0x35a6e2e97  xor        eax, eax
  0x35a6e2e99  cmp        word ptr [rdx], 0x5a4d
  0x35a6e2e9e  jne        0x35a6e2eb0
  0x35a6e2ea0  movsxd     r8, dword ptr [rdx + 0x3c]
  0x35a6e2ea4  add        r8, rdx
  0x35a6e2ea7  cmp        dword ptr [r8], 0x4550
  0x35a6e2eae  je         0x35a6e2eb8
  0x35a6e2eb0  ret        
  0x35a6e2eb1  nop        dword ptr [rax]
  0x35a6e2eb8  cmp        word ptr [r8 + 0x18], 0x20b
  0x35a6e2ebf  jne        0x35a6e2eb0
  0x35a6e2ec1  movzx      eax, word ptr [r8 + 0x14]
  0x35a6e2ec6  sub        rcx, rdx
  0x35a6e2ec9  lea        rax, [r8 + rax + 0x18]
  0x35a6e2ece  movzx      r8d, word ptr [r8 + 6]
  0x35a6e2ed3  test       r8w, r8w
  0x35a6e2ed7  je         0x35a6e2f0d
  0x35a6e2ed9  lea        edx, [r8 - 1]
  0x35a6e2edd  lea        rdx, [rdx + rdx*4]
  0x35a6e2ee1  lea        r9, [rax + rdx*8 + 0x28]
  0x35a6e2ee6  nop        word ptr cs:[rax + rax]
  0x35a6e2ef0  mov        r8d, dword ptr [rax + 0xc]
  0x35a6e2ef4  mov        rdx, r8
  0x35a6e2ef7  cmp        rcx, r8
  0x35a6e2efa  jb         0x35a6e2f04
  0x35a6e2efc  add        edx, dword ptr [rax + 8]
  0x35a6e2eff  cmp        rcx, rdx
  0x35a6e2f02  jb         0x35a6e2eb0
  0x35a6e2f04  add        rax, 0x28
  0x35a6e2f08  cmp        rax, r9
  0x35a6e2f0b  jne        0x35a6e2ef0
  0x35a6e2f0d  xor        eax, eax
  0x35a6e2f0f  ret        
  0x35a6e2f10  mov        rax, qword ptr [rip + 0x77b9]
  0x35a6e2f17  xor        ecx, ecx
  0x35a6e2f19  cmp        word ptr [rax], 0x5a4d
  0x35a6e2f1e  jne        0x35a6e2f2f
  0x35a6e2f20  movsxd     rdx, dword ptr [rax + 0x3c]
  0x35a6e2f24  add        rax, rdx
  0x35a6e2f27  cmp        dword ptr [rax], 0x4550
  0x35a6e2f2d  je         0x35a6e2f38
  0x35a6e2f2f  mov        eax, ecx
  0x35a6e2f31  ret        
  0x35a6e2f32  nop        word ptr [rax + rax]
  0x35a6e2f38  cmp        word ptr [rax + 0x18], 0x20b
  0x35a6e2f3e  jne        0x35a6e2f2f
  0x35a6e2f40  movzx      ecx, word ptr [rax + 6]
  0x35a6e2f44  mov        eax, ecx
  0x35a6e2f46  ret        
  0x35a6e2f47  nop        word ptr [rax + rax]
  0x35a6e2f50  mov        r8, qword ptr [rip + 0x7779]
  0x35a6e2f57  xor        eax, eax
  0x35a6e2f59  cmp        word ptr [r8], 0x5a4d
  0x35a6e2f5f  jne        0x35a6e2f70
  0x35a6e2f61  movsxd     rdx, dword ptr [r8 + 0x3c]
  0x35a6e2f65  add        rdx, r8
  0x35a6e2f68  cmp        dword ptr [rdx], 0x4550
  0x35a6e2f6e  je         0x35a6e2f78
  0x35a6e2f70  ret        
  0x35a6e2f71  nop        dword ptr [rax]
  0x35a6e2f78  cmp        word ptr [rdx + 0x18], 0x20b
  0x35a6e2f7e  jne        0x35a6e2f70
  0x35a6e2f80  movzx      eax, word ptr [rdx + 0x14]
  0x35a6e2f84  movzx      r8d, word ptr [rdx + 6]
  0x35a6e2f89  lea        rax, [rdx + rax + 0x18]
  0x35a6e2f8e  test       r8w, r8w
  0x35a6e2f92  je         0x35a6e2fc0
  0x35a6e2f94  lea        edx, [r8 - 1]
  0x35a6e2f98  lea        rdx, [rdx + rdx*4]
  0x35a6e2f9c  lea        rdx, [rax + rdx*8 + 0x28]
  0x35a6e2fa1  nop        dword ptr [rax]
  0x35a6e2fa8  test       byte ptr [rax + 0x27], 0x20
  0x35a6e2fac  je         0x35a6e2fb7
  0x35a6e2fae  test       rcx, rcx
  0x35a6e2fb1  je         0x35a6e2f70
  0x35a6e2fb3  sub        rcx, 1
  0x35a6e2fb7  add        rax, 0x28
  0x35a6e2fbb  cmp        rdx, rax
  0x35a6e2fbe  jne        0x35a6e2fa8
  0x35a6e2fc0  xor        eax, eax
  0x35a6e2fc2  ret        
  0x35a6e2fc3  nop        word ptr cs:[rax + rax]
  0x35a6e2fce  nop        
  0x35a6e2fd0  mov        rax, qword ptr [rip + 0x76f9]
  0x35a6e2fd7  xor        edx, edx
  0x35a6e2fd9  cmp        word ptr [rax], 0x5a4d
  0x35a6e2fde  jne        0x35a6e2fef
  0x35a6e2fe0  movsxd     rcx, dword ptr [rax + 0x3c]
  0x35a6e2fe4  add        rcx, rax
  0x35a6e2fe7  cmp        dword ptr [rcx], 0x4550
  0x35a6e2fed  je         0x35a6e2ff8
  0x35a6e2fef  mov        rax, rdx
  0x35a6e2ff2  ret        
  0x35a6e2ff3  nop        dword ptr [rax + rax]
  0x35a6e2ff8  cmp        word ptr [rcx + 0x18], 0x20b
  0x35a6e2ffe  cmove      rdx, rax
  0x35a6e3002  mov        rax, rdx
  0x35a6e3005  ret        
  0x35a6e3006  nop        word ptr cs:[rax + rax]
  0x35a6e3010  mov        rdx, qword ptr [rip + 0x76b9]
  0x35a6e3017  xor        eax, eax
  0x35a6e3019  cmp        word ptr [rdx], 0x5a4d
  0x35a6e301e  jne        0x35a6e3030
  0x35a6e3020  movsxd     r8, dword ptr [rdx + 0x3c]
  0x35a6e3024  add        r8, rdx
  0x35a6e3027  cmp        dword ptr [r8], 0x4550
  0x35a6e302e  je         0x35a6e3038
  0x35a6e3030  ret        
  0x35a6e3031  nop        dword ptr [rax]
  0x35a6e3038  cmp        word ptr [r8 + 0x18], 0x20b
  0x35a6e303f  jne        0x35a6e3030
  0x35a6e3041  sub        rcx, rdx
  0x35a6e3044  movzx      r9d, word ptr [r8 + 6]
  0x35a6e3049  movzx      edx, word ptr [r8 + 0x14]
  0x35a6e304e  lea        rdx, [r8 + rdx + 0x18]
  0x35a6e3053  test       r9w, r9w
  0x35a6e3057  je         0x35a6e3030
  0x35a6e3059  lea        eax, [r9 - 1]
  0x35a6e305d  lea        rax, [rax + rax*4]
  0x35a6e3061  lea        r9, [rdx + rax*8 + 0x28]
  0x35a6e3066  nop        word ptr cs:[rax + rax]
  0x35a6e3070  mov        r8d, dword ptr [rdx + 0xc]
  0x35a6e3074  mov        rax, r8
  0x35a6e3077  cmp        rcx, r8
  0x35a6e307a  jb         0x35a6e3084
  0x35a6e307c  add        eax, dword ptr [rdx + 8]
  0x35a6e307f  cmp        rcx, rax
  0x35a6e3082  jb         0x35a6e3090
  0x35a6e3084  add        rdx, 0x28
  0x35a6e3088  cmp        rdx, r9
  0x35a6e308b  jne        0x35a6e3070
  0x35a6e308d  xor        eax, eax
  0x35a6e308f  ret        
  0x35a6e3090  mov        eax, dword ptr [rdx + 0x24]
  0x35a6e3093  not        eax
  0x35a6e3095  shr        eax, 0x1f
  0x35a6e3098  ret        
  0x35a6e3099  nop        dword ptr [rax]
  0x35a6e30a0  mov        r11, qword ptr [rip + 0x7629]
  0x35a6e30a7  xor        r8d, r8d
  0x35a6e30aa  cmp        word ptr [r11], 0x5a4d
  0x35a6e30b0  mov        r10d, ecx
  0x35a6e30b3  jne        0x35a6e30c4
  0x35a6e30b5  movsxd     rcx, dword ptr [r11 + 0x3c]
  0x35a6e30b9  add        rcx, r11
  0x35a6e30bc  cmp        dword ptr [rcx], 0x4550
  0x35a6e30c2  je         0x35a6e30d0
  0x35a6e30c4  mov        rax, r8
  0x35a6e30c7  ret        
  0x35a6e30c8  nop        dword ptr [rax + rax]
  0x35a6e30d0  cmp        word ptr [rcx + 0x18], 0x20b
  0x35a6e30d6  jne        0x35a6e30c4
  0x35a6e30d8  mov        eax, dword ptr [rcx + 0x90]
  0x35a6e30de  test       eax, eax
  0x35a6e30e0  je         0x35a6e30c4
  0x35a6e30e2  movzx      edx, word ptr [rcx + 0x14]
  0x35a6e30e6  movzx      r9d, word ptr [rcx + 6]
  0x35a6e30eb  lea        rdx, [rcx + rdx + 0x18]
  0x35a6e30f0  test       r9w, r9w
  0x35a6e30f4  je         0x35a6e30c4
  0x35a6e30f6  lea        ecx, [r9 - 1]
  0x35a6e30fa  lea        rcx, [rcx + rcx*4]
  0x35a6e30fe  lea        r9, [rdx + rcx*8 + 0x28]
  0x35a6e3103  nop        dword ptr [rax + rax]
  0x35a6e3108  mov        r8d, dword ptr [rdx + 0xc]
  0x35a6e310c  mov        rcx, r8
  0x35a6e310f  cmp        rax, r8
  0x35a6e3112  jb         0x35a6e311c
  0x35a6e3114  add        ecx, dword ptr [rdx + 8]
  0x35a6e3117  cmp        rax, rcx
  0x35a6e311a  jb         0x35a6e3130
  0x35a6e311c  add        rdx, 0x28
  0x35a6e3120  cmp        r9, rdx
  0x35a6e3123  jne        0x35a6e3108
  0x35a6e3125  xor        r8d, r8d
  0x35a6e3128  mov        rax, r8
  0x35a6e312b  ret        
  0x35a6e312c  nop        dword ptr [rax]
  0x35a6e3130  add        rax, r11
  0x35a6e3133  jmp        0x35a6e3140
  0x35a6e3135  nop        dword ptr [rax]
  0x35a6e3138  sub        r10d, 1
  0x35a6e313c  add        rax, 0x14
  0x35a6e3140  mov        ecx, dword ptr [rax + 4]
  0x35a6e3143  test       ecx, ecx
  0x35a6e3145  jne        0x35a6e314e
  0x35a6e3147  mov        edx, dword ptr [rax + 0xc]
  0x35a6e314a  test       edx, edx
  0x35a6e314c  je         0x35a6e3125
  0x35a6e314e  test       r10d, r10d
  0x35a6e3151  jg         0x35a6e3138
  0x35a6e3153  mov        r8d, dword ptr [rax + 0xc]
  0x35a6e3157  add        r8, r11
  0x35a6e315a  mov        rax, r8
  0x35a6e315d  ret        
  0x35a6e315e  nop        
  0x35a6e315f  nop        
  0x35a6e3160  fninit     
  0x35a6e3162  ret        
  0x35a6e3163  nop        
  0x35a6e3164  nop        
  0x35a6e3165  nop        
  0x35a6e3166  nop        
  0x35a6e3167  nop        
  0x35a6e3168  nop        
  0x35a6e3169  nop        
  0x35a6e316a  nop        
  0x35a6e316b  nop        
  0x35a6e316c  nop        
  0x35a6e316d  nop        
  0x35a6e316e  nop        
  0x35a6e316f  nop        
  0x35a6e3170  push       rcx
  0x35a6e3171  push       rax
  0x35a6e3172  cmp        rax, 0x1000
  0x35a6e3178  lea        rcx, [rsp + 0x18]
  0x35a6e317d  jb         0x35a6e3198
  0x35a6e317f  sub        rcx, 0x1000
  0x35a6e3186  or         qword ptr [rcx], 0
  0x35a6e318a  sub        rax, 0x1000
  0x35a6e3190  cmp        rax, 0x1000
  0x35a6e3196  ja         0x35a6e317f
  0x35a6e3198  sub        rcx, rax
  0x35a6e319b  or         qword ptr [rcx], 0
  0x35a6e319f  pop        rax
  0x35a6e31a0  pop        rcx
  0x35a6e31a1  ret        
  0x35a6e31a2  nop        
  0x35a6e31a3  nop        
  0x35a6e31a4  nop        
  0x35a6e31a5  nop        
  0x35a6e31a6  nop        
  0x35a6e31a7  nop        
  0x35a6e31a8  nop        
  0x35a6e31a9  nop        
  0x35a6e31aa  nop        
  0x35a6e31ab  nop        
  0x35a6e31ac  nop        
  0x35a6e31ad  nop        
  0x35a6e31ae  nop        
  0x35a6e31af  nop        
  0x35a6e31b0  mov        eax, 1
  0x35a6e31b5  ret        
  0x35a6e31b6  nop        
  0x35a6e31b7  nop        
  0x35a6e31b8  nop        
  0x35a6e31b9  nop        
  0x35a6e31ba  nop        
  0x35a6e31bb  nop        
  0x35a6e31bc  nop        
  0x35a6e31bd  nop        
  0x35a6e31be  nop        
  0x35a6e31bf  nop        
  0x35a6e31c0  mov        eax, 1
  0x35a6e31c5  ret        
  0x35a6e31c6  nop        
  0x35a6e31c7  nop        
  0x35a6e31c8  nop        
  0x35a6e31c9  nop        
  0x35a6e31ca  nop        
  0x35a6e31cb  nop        
  0x35a6e31cc  nop        
  0x35a6e31cd  nop        
  0x35a6e31ce  nop        
  0x35a6e31cf  nop        
  0x35a6e31d0  push       rbp
  0x35a6e31d1  push       rdi
  0x35a6e31d2  push       rsi
  0x35a6e31d3  push       rbx
  0x35a6e31d4  sub        rsp, 0x38
  0x35a6e31d8  lea        rbp, [rsp + 0x30]
  0x35a6e31dd  mov        rdi, r8
  0x35a6e31e0  mov        rbx, rcx
  0x35a6e31e3  mov        rsi, rdx
  0x35a6e31e6  call       0x35a6e7f00
  0x35a6e31eb  mov        qword ptr [rsp + 0x20], rdi
  0x35a6e31f0  mov        r9, rsi
  0x35a6e31f3  xor        r8d, r8d
  0x35a6e31f6  mov        rdx, rbx
  0x35a6e31f9  mov        ecx, 0x6000
  0x35a6e31fe  call       0x35a6e4dd0
  0x35a6e3203  mov        rcx, rbx
  0x35a6e3206  mov        esi, eax
  0x35a6e3208  call       0x35a6e7f70
  0x35a6e320d  mov        eax, esi
  0x35a6e320f  add        rsp, 0x38
  0x35a6e3213  pop        rbx
  0x35a6e3214  pop        rsi
  0x35a6e3215  pop        rdi
  0x35a6e3216  pop        rbp
  0x35a6e3217  ret        
  0x35a6e3218  nop        
  0x35a6e3219  nop        
  0x35a6e321a  nop        
  0x35a6e321b  nop        
  0x35a6e321c  nop        
  0x35a6e321d  nop        
  0x35a6e321e  nop        
  0x35a6e321f  nop        
  0x35a6e3220  push       rbp
  0x35a6e3221  mov        rbp, rsp
  0x35a6e3224  sub        rsp, 0x60
  0x35a6e3228  mov        rax, qword ptr [rdx]
  0x35a6e322b  mov        edx, dword ptr [rdx + 8]
  0x35a6e322e  mov        r11d, edx
  0x35a6e3231  mov        r10d, ecx
  0x35a6e3234  mov        qword ptr [rbp - 0x10], rax
  0x35a6e3238  mov        rcx, rdx
  0x35a6e323b  mov        dword ptr [rbp - 8], edx
  0x35a6e323e  and        r11w, 0x7fff
  0x35a6e3244  jne        0x35a6e32b0
  0x35a6e3246  mov        rdx, rax
  0x35a6e3249  shr        rdx, 0x20
  0x35a6e324d  or         eax, edx
  0x35a6e324f  je         0x35a6e32e0
  0x35a6e3255  test       edx, edx
  0x35a6e3257  jns        0x35a6e32f0
  0x35a6e325d  lea        edx, [r11 - 0x403e]
  0x35a6e3264  mov        eax, 1
  0x35a6e3269  movsx      edx, dx
  0x35a6e326c  mov        dword ptr [rbp - 0x1c], eax
  0x35a6e326f  and        ecx, 0x8000
  0x35a6e3275  mov        rax, qword ptr [rbp + 0x30]
  0x35a6e3279  mov        dword ptr [rax], ecx
  0x35a6e327b  lea        rax, [rbp - 0x18]
  0x35a6e327f  lea        rcx, [rip + 0x5dba]
  0x35a6e3286  mov        qword ptr [rsp + 0x30], r9
  0x35a6e328b  lea        r9, [rbp - 0x1c]
  0x35a6e328f  mov        dword ptr [rsp + 0x28], r8d
  0x35a6e3294  lea        r8, [rbp - 0x10]
  0x35a6e3298  mov        qword ptr [rsp + 0x38], rax
  0x35a6e329d  mov        dword ptr [rsp + 0x20], r10d
  0x35a6e32a2  call       0x35a6e5a50
  0x35a6e32a7  add        rsp, 0x60
  0x35a6e32ab  pop        rbp
  0x35a6e32ac  ret        
  0x35a6e32ad  nop        dword ptr [rax]
  0x35a6e32b0  cmp        r11w, 0x7fff
  0x35a6e32b6  jne        0x35a6e325d
  0x35a6e32b8  mov        rdx, rax
  0x35a6e32bb  shr        rdx, 0x20
  0x35a6e32bf  and        edx, 0x7fffffff
  0x35a6e32c5  or         edx, eax
  0x35a6e32c7  je         0x35a6e3300
  0x35a6e32c9  mov        dword ptr [rbp - 0x1c], 4
  0x35a6e32d0  xor        edx, edx
  0x35a6e32d2  xor        ecx, ecx
  0x35a6e32d4  jmp        0x35a6e3275
  0x35a6e32d6  nop        word ptr cs:[rax + rax]
  0x35a6e32e0  xor        eax, eax
  0x35a6e32e2  xor        edx, edx
  0x35a6e32e4  jmp        0x35a6e326c
  0x35a6e32e6  nop        word ptr cs:[rax + rax]
  0x35a6e32f0  mov        eax, 2
  0x35a6e32f5  mov        edx, 0xffffbfc3
  0x35a6e32fa  jmp        0x35a6e326c
  0x35a6e32ff  nop        
  0x35a6e3300  mov        eax, 3
  0x35a6e3305  xor        edx, edx
  0x35a6e3307  jmp        0x35a6e326c
  0x35a6e330c  nop        dword ptr [rax]
  0x35a6e3310  push       rbp
  0x35a6e3311  push       rbx
  0x35a6e3312  sub        rsp, 0x28
  0x35a6e3316  lea        rbp, [rsp + 0x20]
  0x35a6e331b  mov        rbx, rdx
  0x35a6e331e  mov        edx, dword ptr [rdx + 8]
  0x35a6e3321  test       dh, 0x40
  0x35a6e3324  jne        0x35a6e332e
  0x35a6e3326  mov        eax, dword ptr [rbx + 0x24]
  0x35a6e3329  cmp        dword ptr [rbx + 0x28], eax
  0x35a6e332c  jle        0x35a6e3340
  0x35a6e332e  mov        rax, qword ptr [rbx]
  0x35a6e3331  and        dh, 0x20
  0x35a6e3334  jne        0x35a6e3350
  0x35a6e3336  movsxd     rdx, dword ptr [rbx + 0x24]
  0x35a6e333a  mov        byte ptr [rax + rdx], cl
  0x35a6e333d  mov        eax, dword ptr [rbx + 0x24]
  0x35a6e3340  add        eax, 1
  0x35a6e3343  mov        dword ptr [rbx + 0x24], eax
  0x35a6e3346  add        rsp, 0x28
  0x35a6e334a  pop        rbx
  0x35a6e334b  pop        rbp
  0x35a6e334c  ret        
  0x35a6e334d  nop        dword ptr [rax]
  0x35a6e3350  mov        rdx, rax
  0x35a6e3353  call       0x35a6e8710
  0x35a6e3358  mov        eax, dword ptr [rbx + 0x24]
  0x35a6e335b  add        eax, 1
  0x35a6e335e  mov        dword ptr [rbx + 0x24], eax
  0x35a6e3361  add        rsp, 0x28
  0x35a6e3365  pop        rbx
  0x35a6e3366  pop        rbp
  0x35a6e3367  ret        
  0x35a6e3368  nop        dword ptr [rax + rax]
  0x35a6e3370  push       rbp
  0x35a6e3371  push       r15
  0x35a6e3373  push       r14
  0x35a6e3375  push       r13
  0x35a6e3377  push       r12
  0x35a6e3379  push       rdi
  0x35a6e337a  push       rsi
  0x35a6e337b  push       rbx
  0x35a6e337c  sub        rsp, 0x58
  0x35a6e3380  lea        rbp, [rsp + 0x50]
  0x35a6e3385  lea        rax, [rbp - 0x18]
  0x35a6e3389  lea        rdi, [rbp - 0x10]
  0x35a6e338d  mov        esi, edx
  0x35a6e338f  mov        rbx, r8
  0x35a6e3392  xor        edx, edx
  0x35a6e3394  mov        r12, rcx
  0x35a6e3397  mov        r8, rax
  0x35a6e339a  mov        rcx, rdi
  0x35a6e339d  mov        qword ptr [rbp - 0x28], rax
  0x35a6e33a1  call       0x35a6e80a0
  0x35a6e33a6  mov        eax, dword ptr [rbx + 0x10]
  0x35a6e33a9  cmp        esi, eax
  0x35a6e33ab  mov        edx, eax
  0x35a6e33ad  cmovle     edx, esi
  0x35a6e33b0  test       eax, eax
  0x35a6e33b2  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e33b5  cmovns     esi, edx
  0x35a6e33b8  cmp        eax, esi
  0x35a6e33ba  jg         0x35a6e34ab
  0x35a6e33c0  mov        dword ptr [rbx + 0xc], 0xffffffff
  0x35a6e33c7  lea        r13d, [rsi - 1]
  0x35a6e33cb  test       esi, esi
  0x35a6e33cd  jle        0x35a6e3505
  0x35a6e33d3  xor        esi, esi
  0x35a6e33d5  add        r13d, 1
  0x35a6e33d9  nop        dword ptr [rax]
  0x35a6e33e0  movzx      edx, word ptr [r12 + rsi*2]
  0x35a6e33e5  mov        r8, qword ptr [rbp - 0x28]
  0x35a6e33e9  mov        rcx, rdi
  0x35a6e33ec  call       0x35a6e80a0
  0x35a6e33f1  test       eax, eax
  0x35a6e33f3  jle        0x35a6e348d
  0x35a6e33f9  sub        eax, 1
  0x35a6e33fc  mov        r15, rdi
  0x35a6e33ff  lea        r14, [rdi + rax + 1]
  0x35a6e3404  jmp        0x35a6e3425
  0x35a6e3406  nop        word ptr cs:[rax + rax]
  0x35a6e3410  movsxd     rdx, dword ptr [rbx + 0x24]
  0x35a6e3414  mov        byte ptr [rax + rdx], cl
  0x35a6e3417  mov        eax, dword ptr [rbx + 0x24]
  0x35a6e341a  add        eax, 1
  0x35a6e341d  mov        dword ptr [rbx + 0x24], eax
  0x35a6e3420  cmp        r15, r14
  0x35a6e3423  je         0x35a6e345c
  0x35a6e3425  mov        edx, dword ptr [rbx + 8]
  0x35a6e3428  add        r15, 1
  0x35a6e342c  test       dh, 0x40
  0x35a6e342f  jne        0x35a6e3439
  0x35a6e3431  mov        eax, dword ptr [rbx + 0x24]
  0x35a6e3434  cmp        dword ptr [rbx + 0x28], eax
  0x35a6e3437  jle        0x35a6e341a
  0x35a6e3439  movsx      ecx, byte ptr [r15 - 1]
  0x35a6e343e  mov        rax, qword ptr [rbx]
  0x35a6e3441  and        dh, 0x20
  0x35a6e3444  je         0x35a6e3410
  0x35a6e3446  mov        rdx, rax
  0x35a6e3449  call       0x35a6e8710
  0x35a6e344e  mov        eax, dword ptr [rbx + 0x24]
  0x35a6e3451  add        eax, 1
  0x35a6e3454  mov        dword ptr [rbx + 0x24], eax
  0x35a6e3457  cmp        r15, r14
  0x35a6e345a  jne        0x35a6e3425
  0x35a6e345c  add        rsi, 1
  0x35a6e3460  mov        eax, r13d
  0x35a6e3463  sub        eax, esi
  0x35a6e3465  test       eax, eax
  0x35a6e3467  jg         0x35a6e33e0
  0x35a6e346d  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e3470  lea        edx, [rax - 1]
  0x35a6e3473  mov        dword ptr [rbx + 0xc], edx
  0x35a6e3476  test       eax, eax
  0x35a6e3478  jle        0x35a6e349a
  0x35a6e347a  nop        word ptr [rax + rax]
  0x35a6e3480  mov        rdx, rbx
  0x35a6e3483  mov        ecx, 0x20
  0x35a6e3488  call       0x35a6e3310
  0x35a6e348d  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e3490  lea        edx, [rax - 1]
  0x35a6e3493  mov        dword ptr [rbx + 0xc], edx
  0x35a6e3496  test       eax, eax
  0x35a6e3498  jg         0x35a6e3480
  0x35a6e349a  add        rsp, 0x58
  0x35a6e349e  pop        rbx
  0x35a6e349f  pop        rsi
  0x35a6e34a0  pop        rdi
  0x35a6e34a1  pop        r12
  0x35a6e34a3  pop        r13
  0x35a6e34a5  pop        r14
  0x35a6e34a7  pop        r15
  0x35a6e34a9  pop        rbp
  0x35a6e34aa  ret        
  0x35a6e34ab  sub        eax, esi
  0x35a6e34ad  mov        dword ptr [rbx + 0xc], eax
  0x35a6e34b0  test       byte ptr [rbx + 9], 4
  0x35a6e34b4  jne        0x35a6e34f0
  0x35a6e34b6  sub        eax, 1
  0x35a6e34b9  mov        dword ptr [rbx + 0xc], eax
  0x35a6e34bc  nop        dword ptr [rax]
  0x35a6e34c0  mov        rdx, rbx
  0x35a6e34c3  mov        ecx, 0x20
  0x35a6e34c8  call       0x35a6e3310
  0x35a6e34cd  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e34d0  lea        edx, [rax - 1]
  0x35a6e34d3  mov        dword ptr [rbx + 0xc], edx
  0x35a6e34d6  test       eax, eax
  0x35a6e34d8  jne        0x35a6e34c0
  0x35a6e34da  lea        r13d, [rsi - 1]
  0x35a6e34de  test       esi, esi
  0x35a6e34e0  jg         0x35a6e33d3
  0x35a6e34e6  jmp        0x35a6e348d
  0x35a6e34e8  nop        dword ptr [rax + rax]
  0x35a6e34f0  lea        r13d, [rsi - 1]
  0x35a6e34f4  test       esi, esi
  0x35a6e34f6  jg         0x35a6e33d3
  0x35a6e34fc  sub        dword ptr [rbx + 0xc], 1
  0x35a6e3500  jmp        0x35a6e3480
  0x35a6e3505  mov        dword ptr [rbx + 0xc], 0xfffffffe
  0x35a6e350c  jmp        0x35a6e349a
  0x35a6e350e  nop        
  0x35a6e3510  push       rbp
  0x35a6e3511  push       rdi
  0x35a6e3512  push       rsi
  0x35a6e3513  push       rbx
  0x35a6e3514  sub        rsp, 0x28
  0x35a6e3518  lea        rbp, [rsp + 0x20]
  0x35a6e351d  mov        eax, dword ptr [r8 + 0x10]
  0x35a6e3521  mov        edi, edx
  0x35a6e3523  cmp        edx, eax
  0x35a6e3525  mov        edx, eax
  0x35a6e3527  mov        rsi, rcx
  0x35a6e352a  cmovle     edx, edi
  0x35a6e352d  test       eax, eax
  0x35a6e352f  mov        eax, dword ptr [r8 + 0xc]
  0x35a6e3533  mov        rbx, r8
  0x35a6e3536  cmovns     edi, edx
  0x35a6e3539  cmp        eax, edi
  0x35a6e353b  jg         0x35a6e35f8
  0x35a6e3541  mov        dword ptr [r8 + 0xc], 0xffffffff
  0x35a6e3549  lea        edx, [rdi - 1]
  0x35a6e354c  test       edi, edi
  0x35a6e354e  je         0x35a6e35e5
  0x35a6e3554  mov        eax, dword ptr [rbx + 8]
  0x35a6e3557  lea        edi, [rdx + 1]
  0x35a6e355a  add        rdi, rsi
  0x35a6e355d  jmp        0x35a6e3578
  0x35a6e355f  nop        
  0x35a6e3560  movsxd     rax, dword ptr [rbx + 0x24]
  0x35a6e3564  mov        byte ptr [rdx + rax], cl
  0x35a6e3567  mov        edx, dword ptr [rbx + 0x24]
  0x35a6e356a  add        edx, 1
  0x35a6e356d  mov        dword ptr [rbx + 0x24], edx
  0x35a6e3570  cmp        rsi, rdi
  0x35a6e3573  je         0x35a6e35b1
  0x35a6e3575  mov        eax, dword ptr [rbx + 8]
  0x35a6e3578  add        rsi, 1
  0x35a6e357c  test       ah, 0x40
  0x35a6e357f  jne        0x35a6e3589
  0x35a6e3581  mov        edx, dword ptr [rbx + 0x24]
  0x35a6e3584  cmp        dword ptr [rbx + 0x28], edx
  0x35a6e3587  jle        0x35a6e356a
  0x35a6e3589  movsx      ecx, byte ptr [rsi - 1]
  0x35a6e358d  mov        rdx, qword ptr [rbx]
  0x35a6e3590  test       ah, 0x20
  0x35a6e3593  je         0x35a6e3560
  0x35a6e3595  call       0x35a6e8710
  0x35a6e359a  mov        edx, dword ptr [rbx + 0x24]
  0x35a6e359d  jmp        0x35a6e356a
  0x35a6e359f  nop        
  0x35a6e35a0  movsxd     rax, dword ptr [rbx + 0x24]
  0x35a6e35a4  mov        byte ptr [rdx + rax], 0x20
  0x35a6e35a8  mov        edx, dword ptr [rbx + 0x24]
  0x35a6e35ab  add        edx, 1
  0x35a6e35ae  mov        dword ptr [rbx + 0x24], edx
  0x35a6e35b1  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e35b4  lea        edx, [rax - 1]
  0x35a6e35b7  mov        dword ptr [rbx + 0xc], edx
  0x35a6e35ba  test       eax, eax
  0x35a6e35bc  jle        0x35a6e35ec
  0x35a6e35be  mov        eax, dword ptr [rbx + 8]
  0x35a6e35c1  test       ah, 0x40
  0x35a6e35c4  jne        0x35a6e35ce
  0x35a6e35c6  mov        edx, dword ptr [rbx + 0x24]
  0x35a6e35c9  cmp        dword ptr [rbx + 0x28], edx
  0x35a6e35cc  jle        0x35a6e35ab
  0x35a6e35ce  mov        rdx, qword ptr [rbx]
  0x35a6e35d1  test       ah, 0x20
  0x35a6e35d4  je         0x35a6e35a0
  0x35a6e35d6  mov        ecx, 0x20
  0x35a6e35db  call       0x35a6e8710
  0x35a6e35e0  mov        edx, dword ptr [rbx + 0x24]
  0x35a6e35e3  jmp        0x35a6e35ab
  0x35a6e35e5  mov        dword ptr [rbx + 0xc], 0xfffffffe
  0x35a6e35ec  add        rsp, 0x28
  0x35a6e35f0  pop        rbx
  0x35a6e35f1  pop        rsi
  0x35a6e35f2  pop        rdi
  0x35a6e35f3  pop        rbp
  0x35a6e35f4  ret        
  0x35a6e35f5  nop        dword ptr [rax]
  0x35a6e35f8  sub        eax, edi
  0x35a6e35fa  mov        dword ptr [r8 + 0xc], eax
  0x35a6e35fe  mov        edx, eax
  0x35a6e3600  mov        eax, dword ptr [r8 + 8]
  0x35a6e3604  test       ah, 4
  0x35a6e3607  jne        0x35a6e3640
  0x35a6e3609  lea        eax, [rdx - 1]
  0x35a6e360c  mov        dword ptr [r8 + 0xc], eax
  0x35a6e3610  mov        rdx, rbx
  0x35a6e3613  mov        ecx, 0x20
  0x35a6e3618  call       0x35a6e3310
  0x35a6e361d  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e3620  lea        edx, [rax - 1]
  0x35a6e3623  mov        dword ptr [rbx + 0xc], edx
  0x35a6e3626  test       eax, eax
  0x35a6e3628  jne        0x35a6e3610
  0x35a6e362a  lea        edx, [rdi - 1]
  0x35a6e362d  test       edi, edi
  0x35a6e362f  jne        0x35a6e3554
  0x35a6e3635  jmp        0x35a6e35b1
  0x35a6e363a  nop        word ptr [rax + rax]
  0x35a6e3640  lea        edx, [rdi - 1]
  0x35a6e3643  test       edi, edi
  0x35a6e3645  jne        0x35a6e3557
  0x35a6e364b  sub        dword ptr [rbx + 0xc], 1
  0x35a6e364f  jmp        0x35a6e35c1
  0x35a6e3654  nop        word ptr cs:[rax + rax]
  0x35a6e365f  nop        
  0x35a6e3660  push       rbp
  0x35a6e3661  push       rsi
  0x35a6e3662  push       rbx
  0x35a6e3663  sub        rsp, 0x20
  0x35a6e3667  lea        rbp, [rsp + 0x20]
  0x35a6e366c  lea        rax, [rip + 0x6c8d]
  0x35a6e3673  mov        rbx, rcx
  0x35a6e3676  test       rcx, rcx
  0x35a6e3679  mov        rsi, rdx
  0x35a6e367c  movsxd     rdx, dword ptr [rdx + 0x10]
  0x35a6e3680  cmove      rbx, rax
  0x35a6e3684  mov        rcx, rbx
  0x35a6e3687  test       edx, edx
  0x35a6e3689  js         0x35a6e36a8
  0x35a6e368b  call       0x35a6e7ea0
  0x35a6e3690  mov        r8, rsi
  0x35a6e3693  mov        edx, eax
  0x35a6e3695  mov        rcx, rbx
  0x35a6e3698  add        rsp, 0x20
  0x35a6e369c  pop        rbx
  0x35a6e369d  pop        rsi
  0x35a6e369e  pop        rbp
  0x35a6e369f  jmp        0x35a6e3510
  0x35a6e36a4  nop        dword ptr [rax]
  0x35a6e36a8  call       0x35a6e8760
  0x35a6e36ad  jmp        0x35a6e3690
  0x35a6e36af  nop        
  0x35a6e36b0  push       rbp
  0x35a6e36b1  mov        rbp, rsp
  0x35a6e36b4  sub        rsp, 0x30
  0x35a6e36b8  mov        r10d, dword ptr [r8 + 8]
  0x35a6e36bc  mov        dword ptr [r8 + 0x10], 0xffffffff
  0x35a6e36c4  test       ecx, ecx
  0x35a6e36c6  jne        0x35a6e3720
  0x35a6e36c8  mov        eax, 0x2b
  0x35a6e36cd  test       r10d, 0x100
  0x35a6e36d4  jne        0x35a6e3725
  0x35a6e36d6  test       r10b, 0x40
  0x35a6e36da  je         0x35a6e3738
  0x35a6e36dc  mov        eax, 0x20
  0x35a6e36e1  lea        r9, [rbp - 3]
  0x35a6e36e5  lea        r11, [rbp - 4]
  0x35a6e36e9  mov        byte ptr [rbp - 4], al
  0x35a6e36ec  and        r10d, 0x20
  0x35a6e36f0  xor        ecx, ecx
  0x35a6e36f2  movzx      eax, byte ptr [rdx + rcx]
  0x35a6e36f6  and        eax, 0xffffffdf
  0x35a6e36f9  or         eax, r10d
  0x35a6e36fc  mov        byte ptr [r9 + rcx], al
  0x35a6e3700  add        rcx, 1
  0x35a6e3704  cmp        rcx, 3
  0x35a6e3708  jne        0x35a6e36f2
  0x35a6e370a  lea        rdx, [r9 + 3]
  0x35a6e370e  mov        rcx, r11
  0x35a6e3711  sub        edx, r11d
  0x35a6e3714  call       0x35a6e3510
  0x35a6e3719  nop        
  0x35a6e371a  add        rsp, 0x30
  0x35a6e371e  pop        rbp
  0x35a6e371f  ret        
  0x35a6e3720  mov        eax, 0x2d
  0x35a6e3725  mov        byte ptr [rbp - 4], al
  0x35a6e3728  lea        r9, [rbp - 3]
  0x35a6e372c  lea        r11, [rbp - 4]
  0x35a6e3730  jmp        0x35a6e36ec
  0x35a6e3732  nop        word ptr [rax + rax]
  0x35a6e3738  lea        r11, [rbp - 4]
  0x35a6e373c  mov        r9, r11
  0x35a6e373f  jmp        0x35a6e36ec
  0x35a6e3741  nop        word ptr cs:[rax + rax]
  0x35a6e374c  nop        dword ptr [rax]
  0x35a6e3750  push       rbp
  0x35a6e3751  push       r15
  0x35a6e3753  push       r14
  0x35a6e3755  push       r13
  0x35a6e3757  push       r12
  0x35a6e3759  push       rdi
  0x35a6e375a  push       rsi
  0x35a6e375b  push       rbx
  0x35a6e375c  sub        rsp, 0x38
  0x35a6e3760  lea        rbp, [rsp + 0x30]
  0x35a6e3765  mov        r13d, ecx
  0x35a6e3768  mov        rbx, r8
  0x35a6e376b  cmp        ecx, 0x6f
  0x35a6e376e  je         0x35a6e3a40
  0x35a6e3774  mov        r14d, dword ptr [r8 + 0x10]
  0x35a6e3778  xor        eax, eax
  0x35a6e377a  mov        edi, dword ptr [r8 + 8]
  0x35a6e377e  test       r14d, r14d
  0x35a6e3781  cmovns     eax, r14d
  0x35a6e3785  add        eax, 0x12
  0x35a6e3788  test       edi, 0x1000
  0x35a6e378e  je         0x35a6e3878
  0x35a6e3794  mov        ecx, 4
  0x35a6e3799  cmp        word ptr [rbx + 0x20], 0
  0x35a6e379e  je         0x35a6e37b4
  0x35a6e37a0  mov        r8d, eax
  0x35a6e37a3  mov        r9d, 0xaaaaaaab
  0x35a6e37a9  imul       r8, r9
  0x35a6e37ad  shr        r8, 0x21
  0x35a6e37b1  add        eax, r8d
  0x35a6e37b4  mov        r15d, dword ptr [rbx + 0xc]
  0x35a6e37b8  cmp        r15d, eax
  0x35a6e37bb  cmovge     eax, r15d
  0x35a6e37bf  cdqe       
  0x35a6e37c1  add        rax, 0xf
  0x35a6e37c5  and        rax, 0xfffffffffffffff0
  0x35a6e37c9  call       0x35a6e3170
  0x35a6e37ce  xor        r9d, r9d
  0x35a6e37d1  sub        rsp, rax
  0x35a6e37d4  cmp        r13d, 0x6f
  0x35a6e37d8  setne      r9b
  0x35a6e37dc  lea        r12, [rsp + 0x20]
  0x35a6e37e1  lea        r9d, [r9*8 + 7]
  0x35a6e37e9  test       rdx, rdx
  0x35a6e37ec  jne        0x35a6e38ae
  0x35a6e37f2  nop        word ptr [rax + rax]
  0x35a6e37f8  and        edi, 0xfffff7ff
  0x35a6e37fe  mov        dword ptr [rbx + 8], edi
  0x35a6e3801  test       r14d, r14d
  0x35a6e3804  jle        0x35a6e3ad0
  0x35a6e380a  lea        edi, [r14 - 1]
  0x35a6e380e  mov        rsi, r12
  0x35a6e3811  add        edi, 1
  0x35a6e3814  mov        rcx, rsi
  0x35a6e3817  mov        edx, 0x30
  0x35a6e381c  movsxd     rdi, edi
  0x35a6e381f  mov        r8, rdi
  0x35a6e3822  add        rsi, rdi
  0x35a6e3825  call       0x35a6e8740
  0x35a6e382a  cmp        rsi, r12
  0x35a6e382d  je         0x35a6e3ad3
  0x35a6e3833  mov        rax, rsi
  0x35a6e3836  sub        rax, r12
  0x35a6e3839  mov        edx, eax
  0x35a6e383b  cmp        eax, r15d
  0x35a6e383e  jl         0x35a6e3af7
  0x35a6e3844  mov        dword ptr [rbx + 0xc], 0xffffffff
  0x35a6e384b  cmp        r13d, 0x6f
  0x35a6e384f  jne        0x35a6e3bf4
  0x35a6e3855  cmp        r12, rsi
  0x35a6e3858  jae        0x35a6e3a2e
  0x35a6e385e  mov        edi, dword ptr [rbx + 8]
  0x35a6e3861  mov        r13d, 0xfffffffe
  0x35a6e3867  mov        r15d, 0xffffffff
  0x35a6e386d  jmp        0x35a6e39a0
  0x35a6e3872  nop        word ptr [rax + rax]
  0x35a6e3878  mov        r15d, dword ptr [rbx + 0xc]
  0x35a6e387c  cmp        r15d, eax
  0x35a6e387f  cmovge     eax, r15d
  0x35a6e3883  cdqe       
  0x35a6e3885  add        rax, 0xf
  0x35a6e3889  and        rax, 0xfffffffffffffff0
  0x35a6e388d  call       0x35a6e3170
  0x35a6e3892  mov        ecx, 4
  0x35a6e3897  mov        r9d, 0xf
  0x35a6e389d  sub        rsp, rax
  0x35a6e38a0  lea        r12, [rsp + 0x20]
  0x35a6e38a5  test       rdx, rdx
  0x35a6e38a8  je         0x35a6e37f8
  0x35a6e38ae  mov        qword ptr [rbp - 8], r12
  0x35a6e38b2  mov        r10d, r13d
  0x35a6e38b5  mov        rsi, r12
  0x35a6e38b8  and        r10d, 0x20
  0x35a6e38bc  nop        dword ptr [rax]
  0x35a6e38c0  mov        eax, r9d
  0x35a6e38c3  mov        r11, rsi
  0x35a6e38c6  add        rsi, 1
  0x35a6e38ca  and        eax, edx
  0x35a6e38cc  lea        r8d, [rax + 0x30]
  0x35a6e38d0  add        eax, 0x37
  0x35a6e38d3  or         eax, r10d
  0x35a6e38d6  mov        r12d, r8d
  0x35a6e38d9  cmp        r8b, 0x39
  0x35a6e38dd  cmovbe     eax, r12d
  0x35a6e38e1  shr        rdx, cl
  0x35a6e38e4  mov        byte ptr [rsi - 1], al
  0x35a6e38e7  test       rdx, rdx
  0x35a6e38ea  jne        0x35a6e38c0
  0x35a6e38ec  mov        r12, qword ptr [rbp - 8]
  0x35a6e38f0  cmp        rsi, r12
  0x35a6e38f3  je         0x35a6e37f8
  0x35a6e38f9  test       r14d, r14d
  0x35a6e38fc  jle        0x35a6e3a70
  0x35a6e3902  mov        rdx, rsi
  0x35a6e3905  mov        eax, r14d
  0x35a6e3908  sub        rdx, r12
  0x35a6e390b  sub        eax, edx
  0x35a6e390d  test       eax, eax
  0x35a6e390f  jg         0x35a6e3be0
  0x35a6e3915  cmp        r13d, 0x6f
  0x35a6e3919  je         0x35a6e3b48
  0x35a6e391f  cmp        edx, r15d
  0x35a6e3922  jge        0x35a6e3bb0
  0x35a6e3928  sub        r15d, edx
  0x35a6e392b  mov        dword ptr [rbx + 0xc], r15d
  0x35a6e392f  test       edi, 0x800
  0x35a6e3935  jne        0x35a6e3b80
  0x35a6e393b  test       r14d, r14d
  0x35a6e393e  js         0x35a6e3bf9
  0x35a6e3944  lea        r13d, [r15 - 1]
  0x35a6e3948  test       edi, 0x400
  0x35a6e394e  jne        0x35a6e3b70
  0x35a6e3954  mov        r15d, r13d
  0x35a6e3957  nop        word ptr [rax + rax]
  0x35a6e3960  mov        rdx, rbx
  0x35a6e3963  mov        ecx, 0x20
  0x35a6e3968  call       0x35a6e3310
  0x35a6e396d  sub        r15d, 1
  0x35a6e3971  jae        0x35a6e3960
  0x35a6e3973  mov        r13d, 0xfffffffe
  0x35a6e3979  cmp        r12, rsi
  0x35a6e397c  jb         0x35a6e399d
  0x35a6e397e  jmp        0x35a6e3a2e
  0x35a6e3983  nop        dword ptr [rax + rax]
  0x35a6e3988  movsxd     rax, dword ptr [rbx + 0x24]
  0x35a6e398c  mov        byte ptr [rdx + rax], cl
  0x35a6e398f  mov        eax, dword ptr [rbx + 0x24]
  0x35a6e3992  add        eax, 1
  0x35a6e3995  mov        dword ptr [rbx + 0x24], eax
  0x35a6e3998  cmp        r12, rsi
  0x35a6e399b  jae        0x35a6e39d5
  0x35a6e399d  mov        edi, dword ptr [rbx + 8]
  0x35a6e39a0  sub        rsi, 1
  0x35a6e39a4  test       edi, 0x4000
  0x35a6e39aa  jne        0x35a6e39b4
  0x35a6e39ac  mov        eax, dword ptr [rbx + 0x24]
  0x35a6e39af  cmp        dword ptr [rbx + 0x28], eax
  0x35a6e39b2  jle        0x35a6e3992
  0x35a6e39b4  and        edi, 0x2000
  0x35a6e39ba  movsx      ecx, byte ptr [rsi]
  0x35a6e39bd  mov        rdx, qword ptr [rbx]
  0x35a6e39c0  je         0x35a6e3988
  0x35a6e39c2  call       0x35a6e8710
  0x35a6e39c7  mov        eax, dword ptr [rbx + 0x24]
  0x35a6e39ca  add        eax, 1
  0x35a6e39cd  mov        dword ptr [rbx + 0x24], eax
  0x35a6e39d0  cmp        r12, rsi
  0x35a6e39d3  jb         0x35a6e399d
  0x35a6e39d5  test       r15d, r15d
  0x35a6e39d8  jg         0x35a6e39f7
  0x35a6e39da  jmp        0x35a6e3a2e
  0x35a6e39dc  nop        dword ptr [rax]
  0x35a6e39e0  movsxd     rax, dword ptr [rbx + 0x24]
  0x35a6e39e4  mov        byte ptr [rdx + rax], 0x20
  0x35a6e39e8  mov        eax, dword ptr [rbx + 0x24]
  0x35a6e39eb  add        eax, 1
  0x35a6e39ee  mov        dword ptr [rbx + 0x24], eax
  0x35a6e39f1  sub        r13d, 1
  0x35a6e39f5  jb         0x35a6e3a2e
  0x35a6e39f7  mov        edi, dword ptr [rbx + 8]
  0x35a6e39fa  test       edi, 0x4000
  0x35a6e3a00  jne        0x35a6e3a0a
  0x35a6e3a02  mov        eax, dword ptr [rbx + 0x24]
  0x35a6e3a05  cmp        dword ptr [rbx + 0x28], eax
  0x35a6e3a08  jle        0x35a6e39eb
  0x35a6e3a0a  and        edi, 0x2000
  0x35a6e3a10  mov        rdx, qword ptr [rbx]
  0x35a6e3a13  je         0x35a6e39e0
  0x35a6e3a15  mov        ecx, 0x20
  0x35a6e3a1a  call       0x35a6e8710
  0x35a6e3a1f  mov        eax, dword ptr [rbx + 0x24]
  0x35a6e3a22  add        eax, 1
  0x35a6e3a25  mov        dword ptr [rbx + 0x24], eax
  0x35a6e3a28  sub        r13d, 1
  0x35a6e3a2c  jae        0x35a6e39f7
  0x35a6e3a2e  lea        rsp, [rbp + 8]
  0x35a6e3a32  pop        rbx
  0x35a6e3a33  pop        rsi
  0x35a6e3a34  pop        rdi
  0x35a6e3a35  pop        r12
  0x35a6e3a37  pop        r13
  0x35a6e3a39  pop        r14
  0x35a6e3a3b  pop        r15
  0x35a6e3a3d  pop        rbp
  0x35a6e3a3e  ret        
  0x35a6e3a3f  nop        
  0x35a6e3a40  mov        r14d, dword ptr [r8 + 0x10]
  0x35a6e3a44  xor        eax, eax
  0x35a6e3a46  mov        edi, dword ptr [r8 + 8]
  0x35a6e3a4a  test       r14d, r14d
  0x35a6e3a4d  cmovns     eax, r14d
  0x35a6e3a51  add        eax, 0x18
  0x35a6e3a54  test       edi, 0x1000
  0x35a6e3a5a  je         0x35a6e3a98
  0x35a6e3a5c  mov        ecx, 3
  0x35a6e3a61  jmp        0x35a6e3799
  0x35a6e3a66  nop        word ptr cs:[rax + rax]
  0x35a6e3a70  cmp        r13d, 0x6f
  0x35a6e3a74  je         0x35a6e3b48
  0x35a6e3a7a  mov        rax, rsi
  0x35a6e3a7d  sub        rax, r12
  0x35a6e3a80  cmp        eax, r15d
  0x35a6e3a83  jge        0x35a6e3bb0
  0x35a6e3a89  sub        r15d, eax
  0x35a6e3a8c  mov        dword ptr [rbx + 0xc], r15d
  0x35a6e3a90  jmp        0x35a6e392f
  0x35a6e3a95  nop        dword ptr [rax]
  0x35a6e3a98  mov        r15d, dword ptr [rbx + 0xc]
  0x35a6e3a9c  cmp        r15d, eax
  0x35a6e3a9f  cmovge     eax, r15d
  0x35a6e3aa3  cdqe       
  0x35a6e3aa5  add        rax, 0xf
  0x35a6e3aa9  and        rax, 0xfffffffffffffff0
  0x35a6e3aad  call       0x35a6e3170
  0x35a6e3ab2  mov        ecx, 3
  0x35a6e3ab7  mov        r9d, 7
  0x35a6e3abd  sub        rsp, rax
  0x35a6e3ac0  lea        r12, [rsp + 0x20]
  0x35a6e3ac5  jmp        0x35a6e38a5
  0x35a6e3aca  nop        word ptr [rax + rax]
  0x35a6e3ad0  mov        rsi, r12
  0x35a6e3ad3  test       r14d, r14d
  0x35a6e3ad6  je         0x35a6e3833
  0x35a6e3adc  lea        rdx, [rsi + 1]
  0x35a6e3ae0  mov        byte ptr [rsi], 0x30
  0x35a6e3ae3  mov        rax, rdx
  0x35a6e3ae6  mov        rsi, rdx
  0x35a6e3ae9  sub        rax, r12
  0x35a6e3aec  mov        edx, eax
  0x35a6e3aee  cmp        eax, r15d
  0x35a6e3af1  jge        0x35a6e3844
  0x35a6e3af7  mov        edi, dword ptr [rbx + 8]
  0x35a6e3afa  sub        r15d, edx
  0x35a6e3afd  mov        dword ptr [rbx + 0xc], r15d
  0x35a6e3b01  cmp        r13d, 0x6f
  0x35a6e3b05  jne        0x35a6e392f
  0x35a6e3b0b  test       r14d, r14d
  0x35a6e3b0e  jns        0x35a6e3944
  0x35a6e3b14  mov        eax, edi
  0x35a6e3b16  and        eax, 0x600
  0x35a6e3b1b  cmp        eax, 0x200
  0x35a6e3b20  jne        0x35a6e3944
  0x35a6e3b26  movsxd     r15, r15d
  0x35a6e3b29  mov        edx, 0x30
  0x35a6e3b2e  mov        rcx, rsi
  0x35a6e3b31  mov        r8, r15
  0x35a6e3b34  call       0x35a6e8740
  0x35a6e3b39  lea        rax, [rsi + r15]
  0x35a6e3b3d  mov        r15d, 0xffffffff
  0x35a6e3b43  jmp        0x35a6e3b98
  0x35a6e3b45  nop        dword ptr [rax]
  0x35a6e3b48  test       edi, 0x800
  0x35a6e3b4e  jne        0x35a6e3be8
  0x35a6e3b54  mov        rax, rsi
  0x35a6e3b57  sub        rax, r12
  0x35a6e3b5a  mov        edx, eax
  0x35a6e3b5c  cmp        r15d, eax
  0x35a6e3b5f  jg         0x35a6e3afa
  0x35a6e3b61  mov        dword ptr [rbx + 0xc], 0xffffffff
  0x35a6e3b68  jmp        0x35a6e3855
  0x35a6e3b6d  nop        dword ptr [rax]
  0x35a6e3b70  cmp        r12, rsi
  0x35a6e3b73  jb         0x35a6e39a0
  0x35a6e3b79  jmp        0x35a6e39fa
  0x35a6e3b7e  nop        
  0x35a6e3b80  sub        r15d, 2
  0x35a6e3b84  test       r15d, r15d
  0x35a6e3b87  jg         0x35a6e3c44
  0x35a6e3b8d  mov        byte ptr [rsi], r13b
  0x35a6e3b90  lea        rax, [rsi + 2]
  0x35a6e3b94  mov        byte ptr [rsi + 1], 0x30
  0x35a6e3b98  cmp        r12, rax
  0x35a6e3b9b  jae        0x35a6e3a2e
  0x35a6e3ba1  mov        edi, dword ptr [rbx + 8]
  0x35a6e3ba4  lea        r13d, [r15 - 1]
  0x35a6e3ba8  mov        rsi, rax
  0x35a6e3bab  jmp        0x35a6e39a0
  0x35a6e3bb0  mov        dword ptr [rbx + 0xc], 0xffffffff
  0x35a6e3bb7  and        edi, 0x800
  0x35a6e3bbd  mov        rax, rsi
  0x35a6e3bc0  mov        r15d, 0xffffffff
  0x35a6e3bc6  je         0x35a6e3b98
  0x35a6e3bc8  mov        byte ptr [rsi], r13b
  0x35a6e3bcb  lea        rax, [rsi + 2]
  0x35a6e3bcf  mov        r15d, 0xffffffff
  0x35a6e3bd5  mov        byte ptr [rsi + 1], 0x30
  0x35a6e3bd9  jmp        0x35a6e3b98
  0x35a6e3bdb  nop        dword ptr [rax + rax]
  0x35a6e3be0  lea        edi, [rax - 1]
  0x35a6e3be3  jmp        0x35a6e3811
  0x35a6e3be8  mov        byte ptr [rsi], 0x30
  0x35a6e3beb  lea        rsi, [r11 + 2]
  0x35a6e3bef  jmp        0x35a6e382a
  0x35a6e3bf4  mov        edi, dword ptr [rbx + 8]
  0x35a6e3bf7  jmp        0x35a6e3bb7
  0x35a6e3bf9  mov        eax, edi
  0x35a6e3bfb  and        eax, 0x600
  0x35a6e3c00  cmp        eax, 0x200
  0x35a6e3c05  jne        0x35a6e3944
  0x35a6e3c0b  movsxd     r15, r15d
  0x35a6e3c0e  mov        edx, 0x30
  0x35a6e3c13  mov        rcx, rsi
  0x35a6e3c16  mov        r8, r15
  0x35a6e3c19  call       0x35a6e8740
  0x35a6e3c1e  and        edi, 0x800
  0x35a6e3c24  lea        rax, [rsi + r15]
  0x35a6e3c28  je         0x35a6e3b3d
  0x35a6e3c2e  mov        byte ptr [rax], r13b
  0x35a6e3c31  mov        r15d, 0xffffffff
  0x35a6e3c37  add        rax, 2
  0x35a6e3c3b  mov        byte ptr [rax - 1], 0x30
  0x35a6e3c3f  jmp        0x35a6e3b98
  0x35a6e3c44  test       r14d, r14d
  0x35a6e3c47  js         0x35a6e3c59
  0x35a6e3c49  mov        byte ptr [rsi], r13b
  0x35a6e3c4c  add        rsi, 2
  0x35a6e3c50  mov        byte ptr [rsi - 1], 0x30
  0x35a6e3c54  jmp        0x35a6e3944
  0x35a6e3c59  mov        eax, edi
  0x35a6e3c5b  and        eax, 0x600
  0x35a6e3c60  cmp        eax, 0x200
  0x35a6e3c65  jne        0x35a6e3c49
  0x35a6e3c67  jmp        0x35a6e3c0b
  0x35a6e3c69  nop        dword ptr [rax]
  0x35a6e3c70  push       rbp
  0x35a6e3c71  push       r15
  0x35a6e3c73  push       r14
  0x35a6e3c75  push       r13
  0x35a6e3c77  push       r12
  0x35a6e3c79  push       rdi
  0x35a6e3c7a  push       rsi
  0x35a6e3c7b  push       rbx
  0x35a6e3c7c  sub        rsp, 0x28
  0x35a6e3c80  lea        rbp, [rsp + 0x20]
  0x35a6e3c85  xor        eax, eax
  0x35a6e3c87  mov        r14d, dword ptr [rdx + 0x10]
  0x35a6e3c8b  mov        edi, dword ptr [rdx + 8]
  0x35a6e3c8e  test       r14d, r14d
  0x35a6e3c91  cmovns     eax, r14d
  0x35a6e3c95  mov        rbx, rdx
  0x35a6e3c98  add        eax, 0x17
  0x35a6e3c9b  test       edi, 0x1000
  0x35a6e3ca1  je         0x35a6e3cae
  0x35a6e3ca3  cmp        word ptr [rdx + 0x20], 0
  0x35a6e3ca8  jne        0x35a6e3f10
  0x35a6e3cae  mov        esi, dword ptr [rbx + 0xc]
  0x35a6e3cb1  cmp        esi, eax
  0x35a6e3cb3  cmovge     eax, esi
  0x35a6e3cb6  cdqe       
  0x35a6e3cb8  add        rax, 0xf
  0x35a6e3cbc  and        rax, 0xfffffffffffffff0
  0x35a6e3cc0  call       0x35a6e3170
  0x35a6e3cc5  sub        rsp, rax
  0x35a6e3cc8  lea        r12, [rsp + 0x20]
  0x35a6e3ccd  test       dil, 0x80
  0x35a6e3cd1  je         0x35a6e3ce3
  0x35a6e3cd3  test       rcx, rcx
  0x35a6e3cd6  js         0x35a6e3f50
  0x35a6e3cdc  and        dil, 0x7f
  0x35a6e3ce0  mov        dword ptr [rbx + 8], edi
  0x35a6e3ce3  test       rcx, rcx
  0x35a6e3ce6  je         0x35a6e4000
  0x35a6e3cec  movabs     r11, 0x8000000000000003
  0x35a6e3cf6  mov        r10d, edi
  0x35a6e3cf9  mov        r8, r12
  0x35a6e3cfc  movabs     r9, 0xcccccccccccccccd
  0x35a6e3d06  and        r10d, 0x1000
  0x35a6e3d0d  nop        dword ptr [rax]
  0x35a6e3d10  cmp        r12, r8
  0x35a6e3d13  je         0x35a6e3d40
  0x35a6e3d15  test       r10d, r10d
  0x35a6e3d18  je         0x35a6e3d40
  0x35a6e3d1a  cmp        word ptr [rbx + 0x20], 0
  0x35a6e3d1f  je         0x35a6e3d40
  0x35a6e3d21  mov        rax, r8
  0x35a6e3d24  sub        rax, r12
  0x35a6e3d27  and        rax, r11
  0x35a6e3d2a  cmp        rax, 3
  0x35a6e3d2e  jne        0x35a6e3d40
  0x35a6e3d30  mov        byte ptr [r8], 0x2c
  0x35a6e3d34  add        r8, 1
  0x35a6e3d38  nop        dword ptr [rax + rax]
  0x35a6e3d40  mov        rax, rcx
  0x35a6e3d43  lea        r13, [r8 + 1]
  0x35a6e3d47  mul        r9
  0x35a6e3d4a  mov        rax, rcx
  0x35a6e3d4d  shr        rdx, 3
  0x35a6e3d51  lea        r15, [rdx + rdx*4]
  0x35a6e3d55  add        r15, r15
  0x35a6e3d58  sub        rax, r15
  0x35a6e3d5b  add        eax, 0x30
  0x35a6e3d5e  mov        byte ptr [r8], al
  0x35a6e3d61  cmp        rcx, 9
  0x35a6e3d65  jbe        0x35a6e3d70
  0x35a6e3d67  mov        rcx, rdx
  0x35a6e3d6a  mov        r8, r13
  0x35a6e3d6d  jmp        0x35a6e3d10
  0x35a6e3d6f  nop        
  0x35a6e3d70  test       r14d, r14d
  0x35a6e3d73  jle        0x35a6e3da0
  0x35a6e3d75  mov        rax, r13
  0x35a6e3d78  mov        r8d, r14d
  0x35a6e3d7b  sub        rax, r12
  0x35a6e3d7e  sub        r8d, eax
  0x35a6e3d81  test       r8d, r8d
  0x35a6e3d84  jle        0x35a6e3f30
  0x35a6e3d8a  movsxd     r15, r8d
  0x35a6e3d8d  mov        rcx, r13
  0x35a6e3d90  mov        edx, 0x30
  0x35a6e3d95  mov        r8, r15
  0x35a6e3d98  add        r13, r15
  0x35a6e3d9b  call       0x35a6e8740
  0x35a6e3da0  cmp        r12, r13
  0x35a6e3da3  sete       al
  0x35a6e3da6  test       r14d, r14d
  0x35a6e3da9  je         0x35a6e3db3
  0x35a6e3dab  test       al, al
  0x35a6e3dad  jne        0x35a6e3ff2
  0x35a6e3db3  test       esi, esi
  0x35a6e3db5  jle        0x35a6e3df0
  0x35a6e3db7  mov        rax, r13
  0x35a6e3dba  sub        rax, r12
  0x35a6e3dbd  sub        esi, eax
  0x35a6e3dbf  mov        dword ptr [rbx + 0xc], esi
  0x35a6e3dc2  test       esi, esi
  0x35a6e3dc4  jle        0x35a6e3df0
  0x35a6e3dc6  test       edi, 0x1c0
  0x35a6e3dcc  jne        0x35a6e3f60
  0x35a6e3dd2  test       r14d, r14d
  0x35a6e3dd5  js         0x35a6e3f6f
  0x35a6e3ddb  test       edi, 0x400
  0x35a6e3de1  je         0x35a6e3fb8
  0x35a6e3de7  nop        word ptr [rax + rax]
  0x35a6e3df0  test       dil, 0x80
  0x35a6e3df4  je         0x35a6e3ed0
  0x35a6e3dfa  mov        byte ptr [r13], 0x2d
  0x35a6e3dff  lea        rsi, [r13 + 1]
  0x35a6e3e03  cmp        r12, rsi
  0x35a6e3e06  jb         0x35a6e3e28
  0x35a6e3e08  jmp        0x35a6e3e5d
  0x35a6e3e0a  nop        word ptr [rax + rax]
  0x35a6e3e10  movsxd     rax, dword ptr [rbx + 0x24]
  0x35a6e3e14  mov        byte ptr [rdx + rax], cl
  0x35a6e3e17  mov        eax, dword ptr [rbx + 0x24]
  0x35a6e3e1a  add        eax, 1
  0x35a6e3e1d  mov        dword ptr [rbx + 0x24], eax
  0x35a6e3e20  cmp        r12, rsi
  0x35a6e3e23  je         0x35a6e3e5d
  0x35a6e3e25  mov        edi, dword ptr [rbx + 8]
  0x35a6e3e28  sub        rsi, 1
  0x35a6e3e2c  test       edi, 0x4000
  0x35a6e3e32  jne        0x35a6e3e3c
  0x35a6e3e34  mov        eax, dword ptr [rbx + 0x24]
  0x35a6e3e37  cmp        dword ptr [rbx + 0x28], eax
  0x35a6e3e3a  jle        0x35a6e3e1a
  0x35a6e3e3c  and        edi, 0x2000
  0x35a6e3e42  movsx      ecx, byte ptr [rsi]
  0x35a6e3e45  mov        rdx, qword ptr [rbx]
  0x35a6e3e48  je         0x35a6e3e10
  0x35a6e3e4a  call       0x35a6e8710
  0x35a6e3e4f  mov        eax, dword ptr [rbx + 0x24]
  0x35a6e3e52  add        eax, 1
  0x35a6e3e55  mov        dword ptr [rbx + 0x24], eax
  0x35a6e3e58  cmp        r12, rsi
  0x35a6e3e5b  jne        0x35a6e3e25
  0x35a6e3e5d  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e3e60  jmp        0x35a6e3e7c
  0x35a6e3e62  nop        word ptr [rax + rax]
  0x35a6e3e68  movsxd     rax, dword ptr [rbx + 0x24]
  0x35a6e3e6c  mov        byte ptr [rdx + rax], 0x20
  0x35a6e3e70  mov        edx, dword ptr [rbx + 0x24]
  0x35a6e3e73  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e3e76  add        edx, 1
  0x35a6e3e79  mov        dword ptr [rbx + 0x24], edx
  0x35a6e3e7c  mov        edx, eax
  0x35a6e3e7e  sub        eax, 1
  0x35a6e3e81  mov        dword ptr [rbx + 0xc], eax
  0x35a6e3e84  test       edx, edx
  0x35a6e3e86  jle        0x35a6e3eb8
  0x35a6e3e88  mov        ecx, dword ptr [rbx + 8]
  0x35a6e3e8b  test       ch, 0x40
  0x35a6e3e8e  jne        0x35a6e3e98
  0x35a6e3e90  mov        edx, dword ptr [rbx + 0x24]
  0x35a6e3e93  cmp        dword ptr [rbx + 0x28], edx
  0x35a6e3e96  jle        0x35a6e3e76
  0x35a6e3e98  mov        rdx, qword ptr [rbx]
  0x35a6e3e9b  and        ch, 0x20
  0x35a6e3e9e  je         0x35a6e3e68
  0x35a6e3ea0  mov        ecx, 0x20
  0x35a6e3ea5  call       0x35a6e8710
  0x35a6e3eaa  mov        edx, dword ptr [rbx + 0x24]
  0x35a6e3ead  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e3eb0  jmp        0x35a6e3e76
  0x35a6e3eb2  nop        word ptr [rax + rax]
  0x35a6e3eb8  lea        rsp, [rbp + 8]
  0x35a6e3ebc  pop        rbx
  0x35a6e3ebd  pop        rsi
  0x35a6e3ebe  pop        rdi
  0x35a6e3ebf  pop        r12
  0x35a6e3ec1  pop        r13
  0x35a6e3ec3  pop        r14
  0x35a6e3ec5  pop        r15
  0x35a6e3ec7  pop        rbp
  0x35a6e3ec8  ret        
  0x35a6e3ec9  nop        dword ptr [rax]
  0x35a6e3ed0  test       edi, 0x100
  0x35a6e3ed6  je         0x35a6e3ef0
  0x35a6e3ed8  mov        byte ptr [r13], 0x2b
  0x35a6e3edd  lea        rsi, [r13 + 1]
  0x35a6e3ee1  jmp        0x35a6e3e03
  0x35a6e3ee6  nop        word ptr cs:[rax + rax]
  0x35a6e3ef0  mov        rsi, r13
  0x35a6e3ef3  test       dil, 0x40
  0x35a6e3ef7  je         0x35a6e3e03
  0x35a6e3efd  mov        byte ptr [r13], 0x20
  0x35a6e3f02  add        rsi, 1
  0x35a6e3f06  jmp        0x35a6e3e03
  0x35a6e3f0b  nop        dword ptr [rax + rax]
  0x35a6e3f10  mov        edx, eax
  0x35a6e3f12  mov        r8d, 0xaaaaaaab
  0x35a6e3f18  imul       rdx, r8
  0x35a6e3f1c  shr        rdx, 0x21
  0x35a6e3f20  add        eax, edx
  0x35a6e3f22  jmp        0x35a6e3cae
  0x35a6e3f27  nop        word ptr [rax + rax]
  0x35a6e3f30  cmp        r12, r13
  0x35a6e3f33  jne        0x35a6e3db3
  0x35a6e3f39  mov        rax, r12
  0x35a6e3f3c  mov        byte ptr [rax], 0x30
  0x35a6e3f3f  lea        r13, [rax + 1]
  0x35a6e3f43  jmp        0x35a6e3db3
  0x35a6e3f48  nop        dword ptr [rax + rax]
  0x35a6e3f50  neg        rcx
  0x35a6e3f53  jmp        0x35a6e3cec
  0x35a6e3f58  nop        dword ptr [rax + rax]
  0x35a6e3f60  sub        esi, 1
  0x35a6e3f63  mov        dword ptr [rbx + 0xc], esi
  0x35a6e3f66  test       r14d, r14d
  0x35a6e3f69  jns        0x35a6e3ddb
  0x35a6e3f6f  mov        eax, edi
  0x35a6e3f71  and        eax, 0x600
  0x35a6e3f76  cmp        eax, 0x200
  0x35a6e3f7b  jne        0x35a6e3ddb
  0x35a6e3f81  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e3f84  lea        edx, [rax - 1]
  0x35a6e3f87  mov        dword ptr [rbx + 0xc], edx
  0x35a6e3f8a  test       eax, eax
  0x35a6e3f8c  jle        0x35a6e3df0
  0x35a6e3f92  movsxd     rsi, eax
  0x35a6e3f95  mov        rcx, r13
  0x35a6e3f98  mov        edx, 0x30
  0x35a6e3f9d  mov        r8, rsi
  0x35a6e3fa0  add        r13, rsi
  0x35a6e3fa3  call       0x35a6e8740
  0x35a6e3fa8  mov        dword ptr [rbx + 0xc], 0xffffffff
  0x35a6e3faf  jmp        0x35a6e3df0
  0x35a6e3fb4  nop        dword ptr [rax]
  0x35a6e3fb8  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e3fbb  lea        edx, [rax - 1]
  0x35a6e3fbe  mov        dword ptr [rbx + 0xc], edx
  0x35a6e3fc1  test       eax, eax
  0x35a6e3fc3  jle        0x35a6e3df0
  0x35a6e3fc9  nop        dword ptr [rax]
  0x35a6e3fd0  mov        rdx, rbx
  0x35a6e3fd3  mov        ecx, 0x20
  0x35a6e3fd8  call       0x35a6e3310
  0x35a6e3fdd  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e3fe0  lea        edx, [rax - 1]
  0x35a6e3fe3  mov        dword ptr [rbx + 0xc], edx
  0x35a6e3fe6  test       eax, eax
  0x35a6e3fe8  jg         0x35a6e3fd0
  0x35a6e3fea  mov        edi, dword ptr [rbx + 8]
  0x35a6e3fed  jmp        0x35a6e3df0
  0x35a6e3ff2  mov        rax, r13
  0x35a6e3ff5  jmp        0x35a6e3f3c
  0x35a6e3ffa  nop        word ptr [rax + rax]
  0x35a6e4000  mov        r13, r12
  0x35a6e4003  mov        r8d, r14d
  0x35a6e4006  mov        eax, 1
  0x35a6e400b  test       r14d, r14d
  0x35a6e400e  jg         0x35a6e3d8a
  0x35a6e4014  jmp        0x35a6e3da6
  0x35a6e4019  nop        dword ptr [rax]
  0x35a6e4020  push       rbp
  0x35a6e4021  push       r12
  0x35a6e4023  push       rdi
  0x35a6e4024  push       rsi
  0x35a6e4025  push       rbx
  0x35a6e4026  sub        rsp, 0x30
  0x35a6e402a  lea        rbp, [rsp + 0x30]
  0x35a6e402f  cmp        dword ptr [rcx + 0x14], -3
  0x35a6e4033  mov        rbx, rcx
  0x35a6e4036  je         0x35a6e4110
  0x35a6e403c  movzx      edx, word ptr [rcx + 0x18]
  0x35a6e4040  test       dx, dx
  0x35a6e4043  je         0x35a6e40f0
  0x35a6e4049  movsxd     rax, dword ptr [rbx + 0x14]
  0x35a6e404d  mov        rdi, rsp
  0x35a6e4050  add        rax, 0xf
  0x35a6e4054  and        rax, 0xfffffffffffffff0
  0x35a6e4058  call       0x35a6e3170
  0x35a6e405d  sub        rsp, rax
  0x35a6e4060  lea        r8, [rbp - 8]
  0x35a6e4064  mov        qword ptr [rbp - 8], 0
  0x35a6e406c  lea        rsi, [rsp + 0x20]
  0x35a6e4071  mov        rcx, rsi
  0x35a6e4074  call       0x35a6e80a0
  0x35a6e4079  test       eax, eax
  0x35a6e407b  jle        0x35a6e4150
  0x35a6e4081  sub        eax, 1
  0x35a6e4084  lea        r12, [rsi + rax + 1]
  0x35a6e4089  jmp        0x35a6e40a5
  0x35a6e408b  nop        dword ptr [rax + rax]
  0x35a6e4090  movsxd     rdx, dword ptr [rbx + 0x24]
  0x35a6e4094  mov        byte ptr [rax + rdx], cl
  0x35a6e4097  mov        eax, dword ptr [rbx + 0x24]
  0x35a6e409a  add        eax, 1
  0x35a6e409d  mov        dword ptr [rbx + 0x24], eax
  0x35a6e40a0  cmp        r12, rsi
  0x35a6e40a3  je         0x35a6e40db
  0x35a6e40a5  mov        edx, dword ptr [rbx + 8]
  0x35a6e40a8  add        rsi, 1
  0x35a6e40ac  test       dh, 0x40
  0x35a6e40af  jne        0x35a6e40b9
  0x35a6e40b1  mov        eax, dword ptr [rbx + 0x24]
  0x35a6e40b4  cmp        dword ptr [rbx + 0x28], eax
  0x35a6e40b7  jle        0x35a6e409a
  0x35a6e40b9  movsx      ecx, byte ptr [rsi - 1]
  0x35a6e40bd  mov        rax, qword ptr [rbx]
  0x35a6e40c0  and        dh, 0x20
  0x35a6e40c3  je         0x35a6e4090
  0x35a6e40c5  mov        rdx, rax
  0x35a6e40c8  call       0x35a6e8710
  0x35a6e40cd  mov        eax, dword ptr [rbx + 0x24]
  0x35a6e40d0  add        eax, 1
  0x35a6e40d3  mov        dword ptr [rbx + 0x24], eax
  0x35a6e40d6  cmp        r12, rsi
  0x35a6e40d9  jne        0x35a6e40a5
  0x35a6e40db  mov        rsp, rdi
  0x35a6e40de  mov        rsp, rbp
  0x35a6e40e1  pop        rbx
  0x35a6e40e2  pop        rsi
  0x35a6e40e3  pop        rdi
  0x35a6e40e4  pop        r12
  0x35a6e40e6  pop        rbp
  0x35a6e40e7  ret        
  0x35a6e40e8  nop        dword ptr [rax + rax]
  0x35a6e40f0  mov        rdx, rbx
  0x35a6e40f3  mov        ecx, 0x2e
  0x35a6e40f8  call       0x35a6e3310
  0x35a6e40fd  nop        
  0x35a6e40fe  mov        rsp, rbp
  0x35a6e4101  pop        rbx
  0x35a6e4102  pop        rsi
  0x35a6e4103  pop        rdi
  0x35a6e4104  pop        r12
  0x35a6e4106  pop        rbp
  0x35a6e4107  ret        
  0x35a6e4108  nop        dword ptr [rax + rax]
  0x35a6e4110  mov        qword ptr [rbp - 8], 0
  0x35a6e4118  lea        rsi, [rbp - 8]
  0x35a6e411c  call       0x35a6e8728
  0x35a6e4121  lea        rcx, [rbp - 0xa]
  0x35a6e4125  mov        r9, rsi
  0x35a6e4128  mov        r8d, 0x10
  0x35a6e412e  mov        rdx, qword ptr [rax]
  0x35a6e4131  call       0x35a6e84c0
  0x35a6e4136  test       eax, eax
  0x35a6e4138  jle        0x35a6e4168
  0x35a6e413a  movzx      edx, word ptr [rbp - 0xa]
  0x35a6e413e  mov        word ptr [rbx + 0x18], dx
  0x35a6e4142  mov        dword ptr [rbx + 0x14], eax
  0x35a6e4145  jmp        0x35a6e4040
  0x35a6e414a  nop        word ptr [rax + rax]
  0x35a6e4150  mov        rdx, rbx
  0x35a6e4153  mov        ecx, 0x2e
  0x35a6e4158  call       0x35a6e3310
  0x35a6e415d  mov        rsp, rdi
  0x35a6e4160  jmp        0x35a6e40de
  0x35a6e4165  nop        dword ptr [rax]
  0x35a6e4168  movzx      edx, word ptr [rbx + 0x18]
  0x35a6e416c  jmp        0x35a6e4142
  0x35a6e416e  nop        
  0x35a6e4170  push       rbp
  0x35a6e4171  push       r12
  0x35a6e4173  push       rdi
  0x35a6e4174  push       rsi
  0x35a6e4175  push       rbx
  0x35a6e4176  sub        rsp, 0x20
  0x35a6e417a  lea        rbp, [rsp + 0x20]
  0x35a6e417f  mov        eax, dword ptr [r9 + 0xc]
  0x35a6e4183  mov        r12d, ecx
  0x35a6e4186  mov        rdi, rdx
  0x35a6e4189  mov        esi, r8d
  0x35a6e418c  mov        rbx, r9
  0x35a6e418f  test       r8d, r8d
  0x35a6e4192  jle        0x35a6e42e0
  0x35a6e4198  cmp        r8d, eax
  0x35a6e419b  jg         0x35a6e4200
  0x35a6e419d  mov        edx, dword ptr [r9 + 0x10]
  0x35a6e41a1  sub        eax, r8d
  0x35a6e41a4  cmp        eax, edx
  0x35a6e41a6  jle        0x35a6e44b0
  0x35a6e41ac  sub        eax, edx
  0x35a6e41ae  mov        dword ptr [rbx + 0xc], eax
  0x35a6e41b1  test       edx, edx
  0x35a6e41b3  jle        0x35a6e44e0
  0x35a6e41b9  sub        eax, 1
  0x35a6e41bc  mov        dword ptr [rbx + 0xc], eax
  0x35a6e41bf  test       esi, esi
  0x35a6e41c1  jle        0x35a6e41d0
  0x35a6e41c3  test       byte ptr [rbx + 9], 0x10
  0x35a6e41c7  jne        0x35a6e44c7
  0x35a6e41cd  nop        dword ptr [rax]
  0x35a6e41d0  test       eax, eax
  0x35a6e41d2  jle        0x35a6e4213
  0x35a6e41d4  test       r12d, r12d
  0x35a6e41d7  jne        0x35a6e43b8
  0x35a6e41dd  mov        edx, dword ptr [rbx + 8]
  0x35a6e41e0  test       edx, 0x1c0
  0x35a6e41e6  je         0x35a6e4498
  0x35a6e41ec  lea        ecx, [rax - 1]
  0x35a6e41ef  mov        dword ptr [rbx + 0xc], ecx
  0x35a6e41f2  test       ecx, ecx
  0x35a6e41f4  je         0x35a6e421f
  0x35a6e41f6  test       dh, 6
  0x35a6e41f9  jne        0x35a6e421f
  0x35a6e41fb  jmp        0x35a6e43d3
  0x35a6e4200  mov        dword ptr [r9 + 0xc], 0xffffffff
  0x35a6e4208  test       byte ptr [r9 + 9], 0x10
  0x35a6e420d  jne        0x35a6e4440
  0x35a6e4213  test       r12d, r12d
  0x35a6e4216  jne        0x35a6e4310
  0x35a6e421c  mov        edx, dword ptr [rbx + 8]
  0x35a6e421f  test       dh, 1
  0x35a6e4222  jne        0x35a6e4400
  0x35a6e4228  and        edx, 0x40
  0x35a6e422b  je         0x35a6e4240
  0x35a6e422d  mov        rdx, rbx
  0x35a6e4230  mov        ecx, 0x20
  0x35a6e4235  call       0x35a6e3310
  0x35a6e423a  nop        word ptr [rax + rax]
  0x35a6e4240  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e4243  test       eax, eax
  0x35a6e4245  jle        0x35a6e425c
  0x35a6e4247  mov        edx, dword ptr [rbx + 8]
  0x35a6e424a  and        edx, 0x600
  0x35a6e4250  cmp        edx, 0x200
  0x35a6e4256  je         0x35a6e4418
  0x35a6e425c  test       esi, esi
  0x35a6e425e  jle        0x35a6e4370
  0x35a6e4264  nop        dword ptr [rax]
  0x35a6e4268  movzx      eax, byte ptr [rdi]
  0x35a6e426b  mov        ecx, 0x30
  0x35a6e4270  test       al, al
  0x35a6e4272  je         0x35a6e427b
  0x35a6e4274  add        rdi, 1
  0x35a6e4278  movsx      ecx, al
  0x35a6e427b  mov        rdx, rbx
  0x35a6e427e  call       0x35a6e3310
  0x35a6e4283  sub        esi, 1
  0x35a6e4286  je         0x35a6e42b8
  0x35a6e4288  test       byte ptr [rbx + 9], 0x10
  0x35a6e428c  je         0x35a6e4268
  0x35a6e428e  cmp        word ptr [rbx + 0x20], 0
  0x35a6e4293  je         0x35a6e4268
  0x35a6e4295  imul       eax, esi, 0xaaaaaaab
  0x35a6e429b  cmp        eax, 0x55555555
  0x35a6e42a0  ja         0x35a6e4268
  0x35a6e42a2  lea        rcx, [rbx + 0x20]
  0x35a6e42a6  mov        r8, rbx
  0x35a6e42a9  mov        edx, 1
  0x35a6e42ae  call       0x35a6e3370
  0x35a6e42b3  jmp        0x35a6e4268
  0x35a6e42b5  nop        dword ptr [rax]
  0x35a6e42b8  mov        eax, dword ptr [rbx + 0x10]
  0x35a6e42bb  test       eax, eax
  0x35a6e42bd  jg         0x35a6e4328
  0x35a6e42bf  test       byte ptr [rbx + 9], 8
  0x35a6e42c3  jne        0x35a6e4388
  0x35a6e42c9  sub        eax, 1
  0x35a6e42cc  mov        dword ptr [rbx + 0x10], eax
  0x35a6e42cf  add        rsp, 0x20
  0x35a6e42d3  pop        rbx
  0x35a6e42d4  pop        rsi
  0x35a6e42d5  pop        rdi
  0x35a6e42d6  pop        r12
  0x35a6e42d8  pop        rbp
  0x35a6e42d9  ret        
  0x35a6e42da  nop        word ptr [rax + rax]
  0x35a6e42e0  test       eax, eax
  0x35a6e42e2  jle        0x35a6e4500
  0x35a6e42e8  mov        edx, dword ptr [r9 + 0x10]
  0x35a6e42ec  sub        eax, 1
  0x35a6e42ef  cmp        eax, edx
  0x35a6e42f1  jg         0x35a6e41ac
  0x35a6e42f7  mov        dword ptr [rbx + 0xc], 0xffffffff
  0x35a6e42fe  test       r12d, r12d
  0x35a6e4301  je         0x35a6e421c
  0x35a6e4307  nop        word ptr [rax + rax]
  0x35a6e4310  mov        rdx, rbx
  0x35a6e4313  mov        ecx, 0x2d
  0x35a6e4318  call       0x35a6e3310
  0x35a6e431d  jmp        0x35a6e4240
  0x35a6e4322  nop        word ptr [rax + rax]
  0x35a6e4328  mov        rcx, rbx
  0x35a6e432b  call       0x35a6e4020
  0x35a6e4330  jmp        0x35a6e4353
  0x35a6e4332  nop        word ptr [rax + rax]
  0x35a6e4338  movzx      eax, byte ptr [rdi]
  0x35a6e433b  mov        ecx, 0x30
  0x35a6e4340  test       al, al
  0x35a6e4342  je         0x35a6e434b
  0x35a6e4344  add        rdi, 1
  0x35a6e4348  movsx      ecx, al
  0x35a6e434b  mov        rdx, rbx
  0x35a6e434e  call       0x35a6e3310
  0x35a6e4353  mov        eax, dword ptr [rbx + 0x10]
  0x35a6e4356  lea        edx, [rax - 1]
  0x35a6e4359  mov        dword ptr [rbx + 0x10], edx
  0x35a6e435c  test       eax, eax
  0x35a6e435e  jg         0x35a6e4338
  0x35a6e4360  add        rsp, 0x20
  0x35a6e4364  pop        rbx
  0x35a6e4365  pop        rsi
  0x35a6e4366  pop        rdi
  0x35a6e4367  pop        r12
  0x35a6e4369  pop        rbp
  0x35a6e436a  ret        
  0x35a6e436b  nop        dword ptr [rax + rax]
  0x35a6e4370  mov        rdx, rbx
  0x35a6e4373  mov        ecx, 0x30
  0x35a6e4378  call       0x35a6e3310
  0x35a6e437d  mov        eax, dword ptr [rbx + 0x10]
  0x35a6e4380  test       eax, eax
  0x35a6e4382  jle        0x35a6e452f
  0x35a6e4388  mov        rcx, rbx
  0x35a6e438b  call       0x35a6e4020
  0x35a6e4390  test       esi, esi
  0x35a6e4392  je         0x35a6e4353
  0x35a6e4394  mov        eax, dword ptr [rbx + 0x10]
  0x35a6e4397  add        eax, esi
  0x35a6e4399  mov        dword ptr [rbx + 0x10], eax
  0x35a6e439c  nop        dword ptr [rax]
  0x35a6e43a0  mov        rdx, rbx
  0x35a6e43a3  mov        ecx, 0x30
  0x35a6e43a8  call       0x35a6e3310
  0x35a6e43ad  add        esi, 1
  0x35a6e43b0  jne        0x35a6e43a0
  0x35a6e43b2  jmp        0x35a6e4353
  0x35a6e43b4  nop        dword ptr [rax]
  0x35a6e43b8  lea        edx, [rax - 1]
  0x35a6e43bb  mov        dword ptr [rbx + 0xc], edx
  0x35a6e43be  test       edx, edx
  0x35a6e43c0  je         0x35a6e4310
  0x35a6e43c6  test       dword ptr [rbx + 8], 0x600
  0x35a6e43cd  jne        0x35a6e4310
  0x35a6e43d3  sub        eax, 2
  0x35a6e43d6  mov        dword ptr [rbx + 0xc], eax
  0x35a6e43d9  nop        dword ptr [rax]
  0x35a6e43e0  mov        rdx, rbx
  0x35a6e43e3  mov        ecx, 0x20
  0x35a6e43e8  call       0x35a6e3310
  0x35a6e43ed  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e43f0  lea        edx, [rax - 1]
  0x35a6e43f3  mov        dword ptr [rbx + 0xc], edx
  0x35a6e43f6  test       eax, eax
  0x35a6e43f8  jg         0x35a6e43e0
  0x35a6e43fa  jmp        0x35a6e4213
  0x35a6e43ff  nop        
  0x35a6e4400  mov        rdx, rbx
  0x35a6e4403  mov        ecx, 0x2b
  0x35a6e4408  call       0x35a6e3310
  0x35a6e440d  jmp        0x35a6e4240
  0x35a6e4412  nop        word ptr [rax + rax]
  0x35a6e4418  sub        eax, 1
  0x35a6e441b  mov        dword ptr [rbx + 0xc], eax
  0x35a6e441e  nop        
  0x35a6e4420  mov        rdx, rbx
  0x35a6e4423  mov        ecx, 0x30
  0x35a6e4428  call       0x35a6e3310
  0x35a6e442d  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e4430  lea        edx, [rax - 1]
  0x35a6e4433  mov        dword ptr [rbx + 0xc], edx
  0x35a6e4436  test       eax, eax
  0x35a6e4438  jg         0x35a6e4420
  0x35a6e443a  jmp        0x35a6e425c
  0x35a6e443f  nop        
  0x35a6e4440  cmp        word ptr [r9 + 0x20], 0
  0x35a6e4446  je         0x35a6e4213
  0x35a6e444c  mov        eax, 0xffffffff
  0x35a6e4451  mov        edx, 0xaaaaaaab
  0x35a6e4456  lea        r8d, [rsi + 2]
  0x35a6e445a  imul       r8, rdx
  0x35a6e445e  mov        edx, eax
  0x35a6e4460  shr        r8, 0x21
  0x35a6e4464  lea        ecx, [r8 - 1]
  0x35a6e4468  sub        ecx, eax
  0x35a6e446a  cmp        r8d, 1
  0x35a6e446e  jne        0x35a6e4488
  0x35a6e4470  jmp        0x35a6e41d0
  0x35a6e4475  nop        dword ptr [rax]
  0x35a6e4478  sub        edx, 1
  0x35a6e447b  mov        eax, ecx
  0x35a6e447d  add        eax, edx
  0x35a6e447f  mov        dword ptr [rbx + 0xc], edx
  0x35a6e4482  je         0x35a6e4528
  0x35a6e4488  test       edx, edx
  0x35a6e448a  jg         0x35a6e4478
  0x35a6e448c  jmp        0x35a6e4213
  0x35a6e4491  nop        dword ptr [rax]
  0x35a6e4498  and        dh, 6
  0x35a6e449b  jne        0x35a6e4240
  0x35a6e44a1  sub        eax, 1
  0x35a6e44a4  jmp        0x35a6e43d6
  0x35a6e44a9  nop        dword ptr [rax]
  0x35a6e44b0  mov        dword ptr [r9 + 0xc], 0xffffffff
  0x35a6e44b8  mov        eax, 0xffffffff
  0x35a6e44bd  test       byte ptr [rbx + 9], 0x10
  0x35a6e44c1  je         0x35a6e41d0
  0x35a6e44c7  cmp        word ptr [rbx + 0x20], 0
  0x35a6e44cc  je         0x35a6e41d0
  0x35a6e44d2  jmp        0x35a6e4451
  0x35a6e44d7  nop        word ptr [rax + rax]
  0x35a6e44e0  mov        edx, dword ptr [rbx + 8]
  0x35a6e44e3  test       dh, 8
  0x35a6e44e6  jne        0x35a6e41b9
  0x35a6e44ec  test       esi, esi
  0x35a6e44ee  jle        0x35a6e41d4
  0x35a6e44f4  and        dh, 0x10
  0x35a6e44f7  jne        0x35a6e44c7
  0x35a6e44f9  jmp        0x35a6e41d4
  0x35a6e44fe  nop        
  0x35a6e4500  jne        0x35a6e42f7
  0x35a6e4506  mov        eax, dword ptr [r9 + 0x10]
  0x35a6e450a  test       eax, eax
  0x35a6e450c  jns        0x35a6e42f7
  0x35a6e4512  neg        eax
  0x35a6e4514  mov        dword ptr [r9 + 0xc], eax
  0x35a6e4518  test       byte ptr [r9 + 9], 8
  0x35a6e451d  jne        0x35a6e41b9
  0x35a6e4523  jmp        0x35a6e41d4
  0x35a6e4528  mov        eax, edx
  0x35a6e452a  jmp        0x35a6e41d0
  0x35a6e452f  test       byte ptr [rbx + 9], 8
  0x35a6e4533  jne        0x35a6e4388
  0x35a6e4539  test       esi, esi
  0x35a6e453b  jne        0x35a6e4397
  0x35a6e4541  jmp        0x35a6e42c9
  0x35a6e4546  nop        word ptr cs:[rax + rax]
  0x35a6e4550  push       rbp
  0x35a6e4551  push       rdi
  0x35a6e4552  push       rsi
  0x35a6e4553  push       rbx
  0x35a6e4554  sub        rsp, 0x28
  0x35a6e4558  lea        rbp, [rsp + 0x20]
  0x35a6e455d  mov        r10d, 1
  0x35a6e4563  sub        r8d, 1
  0x35a6e4567  mov        r11d, ecx
  0x35a6e456a  mov        rbx, r9
  0x35a6e456d  movsxd     rsi, r8d
  0x35a6e4570  sar        r8d, 0x1f
  0x35a6e4574  imul       rcx, rsi, 0x66666667
  0x35a6e457b  sar        rcx, 0x22
  0x35a6e457f  sub        ecx, r8d
  0x35a6e4582  je         0x35a6e45a3
  0x35a6e4584  nop        dword ptr [rax]
  0x35a6e4588  movsxd     rax, ecx
  0x35a6e458b  sar        ecx, 0x1f
  0x35a6e458e  add        r10d, 1
  0x35a6e4592  imul       rax, rax, 0x66666667
  0x35a6e4599  sar        rax, 0x22
  0x35a6e459d  sub        eax, ecx
  0x35a6e459f  mov        ecx, eax
  0x35a6e45a1  jne        0x35a6e4588
  0x35a6e45a3  mov        eax, dword ptr [rbx + 0x2c]
  0x35a6e45a6  cmp        eax, -1
  0x35a6e45a9  jne        0x35a6e45b7
  0x35a6e45ab  mov        dword ptr [rbx + 0x2c], 2
  0x35a6e45b2  mov        eax, 2
  0x35a6e45b7  cmp        r10d, eax
  0x35a6e45ba  mov        r8d, dword ptr [rbx + 0xc]
  0x35a6e45be  mov        r9, rbx
  0x35a6e45c1  cmovge     eax, r10d
  0x35a6e45c5  lea        ecx, [rax + 2]
  0x35a6e45c8  mov        edi, eax
  0x35a6e45ca  mov        eax, r8d
  0x35a6e45cd  sub        eax, ecx
  0x35a6e45cf  cmp        r8d, ecx
  0x35a6e45d2  mov        ecx, 0xffffffff
  0x35a6e45d7  mov        r8d, 1
  0x35a6e45dd  cmovle     eax, ecx
  0x35a6e45e0  mov        ecx, r11d
  0x35a6e45e3  mov        dword ptr [rbx + 0xc], eax
  0x35a6e45e6  call       0x35a6e4170
  0x35a6e45eb  mov        ecx, dword ptr [rbx + 8]
  0x35a6e45ee  mov        eax, dword ptr [rbx + 0x2c]
  0x35a6e45f1  mov        rdx, rbx
  0x35a6e45f4  mov        dword ptr [rbx + 0x10], eax
  0x35a6e45f7  mov        eax, ecx
  0x35a6e45f9  and        ecx, 0x20
  0x35a6e45fc  or         eax, 0x1c0
  0x35a6e4601  or         ecx, 0x45
  0x35a6e4604  mov        dword ptr [rbx + 8], eax
  0x35a6e4607  call       0x35a6e3310
  0x35a6e460c  lea        r10d, [rdi + 1]
  0x35a6e4610  add        dword ptr [rbx + 0xc], r10d
  0x35a6e4614  mov        rdx, rbx
  0x35a6e4617  mov        rcx, rsi
  0x35a6e461a  add        rsp, 0x28
  0x35a6e461e  pop        rbx
  0x35a6e461f  pop        rsi
  0x35a6e4620  pop        rdi
  0x35a6e4621  pop        rbp
  0x35a6e4622  jmp        0x35a6e3c70
  0x35a6e4627  nop        word ptr [rax + rax]
  0x35a6e4630  push       rbp
  0x35a6e4631  push       rsi
  0x35a6e4632  push       rbx
  0x35a6e4633  sub        rsp, 0x50
  0x35a6e4637  lea        rbp, [rsp + 0x50]
  0x35a6e463c  mov        r8d, dword ptr [rdx + 0x10]
  0x35a6e4640  fld        xword ptr [rcx]
  0x35a6e4642  mov        rbx, rdx
  0x35a6e4645  test       r8d, r8d
  0x35a6e4648  js         0x35a6e46a0
  0x35a6e464a  add        r8d, 1
  0x35a6e464e  lea        rax, [rbp - 8]
  0x35a6e4652  lea        rdx, [rbp - 0x20]
  0x35a6e4656  mov        ecx, 2
  0x35a6e465b  fstp       xword ptr [rbp - 0x20]
  0x35a6e465e  lea        r9, [rbp - 4]
  0x35a6e4662  mov        qword ptr [rsp + 0x20], rax
  0x35a6e4667  call       0x35a6e3220
  0x35a6e466c  mov        r8d, dword ptr [rbp - 4]
  0x35a6e4670  mov        rsi, rax
  0x35a6e4673  cmp        r8d, 0xffff8000
  0x35a6e467a  je         0x35a6e46b0
  0x35a6e467c  mov        ecx, dword ptr [rbp - 8]
  0x35a6e467f  mov        r9, rbx
  0x35a6e4682  mov        rdx, rax
  0x35a6e4685  call       0x35a6e4550
  0x35a6e468a  mov        rcx, rsi
  0x35a6e468d  call       0x35a6e58a0
  0x35a6e4692  nop        
  0x35a6e4693  add        rsp, 0x50
  0x35a6e4697  pop        rbx
  0x35a6e4698  pop        rsi
  0x35a6e4699  pop        rbp
  0x35a6e469a  ret        
  0x35a6e469b  nop        dword ptr [rax + rax]
  0x35a6e46a0  mov        dword ptr [rdx + 0x10], 6
  0x35a6e46a7  mov        r8d, 7
  0x35a6e46ad  jmp        0x35a6e464e
  0x35a6e46af  nop        
  0x35a6e46b0  mov        ecx, dword ptr [rbp - 8]
  0x35a6e46b3  mov        r8, rbx
  0x35a6e46b6  mov        rdx, rax
  0x35a6e46b9  call       0x35a6e36b0
  0x35a6e46be  mov        rcx, rsi
  0x35a6e46c1  call       0x35a6e58a0
  0x35a6e46c6  nop        
  0x35a6e46c7  add        rsp, 0x50
  0x35a6e46cb  pop        rbx
  0x35a6e46cc  pop        rsi
  0x35a6e46cd  pop        rbp
  0x35a6e46ce  ret        
  0x35a6e46cf  nop        
  0x35a6e46d0  push       rbp
  0x35a6e46d1  push       rsi
  0x35a6e46d2  push       rbx
  0x35a6e46d3  sub        rsp, 0x50
  0x35a6e46d7  lea        rbp, [rsp + 0x50]
  0x35a6e46dc  mov        r8d, dword ptr [rdx + 0x10]
  0x35a6e46e0  fld        xword ptr [rcx]
  0x35a6e46e2  mov        rbx, rdx
  0x35a6e46e5  test       r8d, r8d
  0x35a6e46e8  jns        0x35a6e46f7
  0x35a6e46ea  mov        dword ptr [rdx + 0x10], 6
  0x35a6e46f1  mov        r8d, 6
  0x35a6e46f7  lea        rax, [rbp - 8]
  0x35a6e46fb  lea        rdx, [rbp - 0x20]
  0x35a6e46ff  mov        ecx, 3
  0x35a6e4704  fstp       xword ptr [rbp - 0x20]
  0x35a6e4707  lea        r9, [rbp - 4]
  0x35a6e470b  mov        qword ptr [rsp + 0x20], rax
  0x35a6e4710  call       0x35a6e3220
  0x35a6e4715  mov        r8d, dword ptr [rbp - 4]
  0x35a6e4719  mov        rsi, rax
  0x35a6e471c  cmp        r8d, 0xffff8000
  0x35a6e4723  je         0x35a6e4790
  0x35a6e4725  mov        ecx, dword ptr [rbp - 8]
  0x35a6e4728  mov        rdx, rax
  0x35a6e472b  mov        r9, rbx
  0x35a6e472e  call       0x35a6e4170
  0x35a6e4733  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e4736  jmp        0x35a6e4754
  0x35a6e4738  nop        dword ptr [rax + rax]
  0x35a6e4740  movsxd     rax, dword ptr [rbx + 0x24]
  0x35a6e4744  mov        byte ptr [rdx + rax], 0x20
  0x35a6e4748  mov        edx, dword ptr [rbx + 0x24]
  0x35a6e474b  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e474e  add        edx, 1
  0x35a6e4751  mov        dword ptr [rbx + 0x24], edx
  0x35a6e4754  mov        edx, eax
  0x35a6e4756  sub        eax, 1
  0x35a6e4759  mov        dword ptr [rbx + 0xc], eax
  0x35a6e475c  test       edx, edx
  0x35a6e475e  jle        0x35a6e479e
  0x35a6e4760  mov        ecx, dword ptr [rbx + 8]
  0x35a6e4763  test       ch, 0x40
  0x35a6e4766  jne        0x35a6e4770
  0x35a6e4768  mov        edx, dword ptr [rbx + 0x24]
  0x35a6e476b  cmp        dword ptr [rbx + 0x28], edx
  0x35a6e476e  jle        0x35a6e474e
  0x35a6e4770  mov        rdx, qword ptr [rbx]
  0x35a6e4773  and        ch, 0x20
  0x35a6e4776  je         0x35a6e4740
  0x35a6e4778  mov        ecx, 0x20
  0x35a6e477d  call       0x35a6e8710
  0x35a6e4782  mov        edx, dword ptr [rbx + 0x24]
  0x35a6e4785  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e4788  jmp        0x35a6e474e
  0x35a6e478a  nop        word ptr [rax + rax]
  0x35a6e4790  mov        ecx, dword ptr [rbp - 8]
  0x35a6e4793  mov        r8, rbx
  0x35a6e4796  mov        rdx, rax
  0x35a6e4799  call       0x35a6e36b0
  0x35a6e479e  mov        rcx, rsi
  0x35a6e47a1  call       0x35a6e58a0
  0x35a6e47a6  nop        
  0x35a6e47a7  add        rsp, 0x50
  0x35a6e47ab  pop        rbx
  0x35a6e47ac  pop        rsi
  0x35a6e47ad  pop        rbp
  0x35a6e47ae  ret        
  0x35a6e47af  nop        
  0x35a6e47b0  push       rbp
  0x35a6e47b1  push       rdi
  0x35a6e47b2  push       rsi
  0x35a6e47b3  push       rbx
  0x35a6e47b4  sub        rsp, 0x58
  0x35a6e47b8  lea        rbp, [rsp + 0x50]
  0x35a6e47bd  mov        r8d, dword ptr [rdx + 0x10]
  0x35a6e47c1  fld        xword ptr [rcx]
  0x35a6e47c3  mov        rbx, rdx
  0x35a6e47c6  test       r8d, r8d
  0x35a6e47c9  js         0x35a6e48b8
  0x35a6e47cf  je         0x35a6e48a0
  0x35a6e47d5  lea        rax, [rbp - 8]
  0x35a6e47d9  lea        rdx, [rbp - 0x20]
  0x35a6e47dd  mov        ecx, 2
  0x35a6e47e2  fstp       xword ptr [rbp - 0x20]
  0x35a6e47e5  lea        r9, [rbp - 4]
  0x35a6e47e9  mov        qword ptr [rsp + 0x20], rax
  0x35a6e47ee  call       0x35a6e3220
  0x35a6e47f3  mov        edi, dword ptr [rbp - 4]
  0x35a6e47f6  mov        rsi, rax
  0x35a6e47f9  cmp        edi, 0xffff8000
  0x35a6e47ff  je         0x35a6e48d0
  0x35a6e4805  mov        eax, dword ptr [rbx + 8]
  0x35a6e4808  and        eax, 0x800
  0x35a6e480d  cmp        edi, -3
  0x35a6e4810  jl         0x35a6e4860
  0x35a6e4812  mov        edx, dword ptr [rbx + 0x10]
  0x35a6e4815  cmp        edi, edx
  0x35a6e4817  jg         0x35a6e4860
  0x35a6e4819  test       eax, eax
  0x35a6e481b  je         0x35a6e48e0
  0x35a6e4821  sub        edx, edi
  0x35a6e4823  mov        dword ptr [rbx + 0x10], edx
  0x35a6e4826  mov        ecx, dword ptr [rbp - 8]
  0x35a6e4829  mov        r9, rbx
  0x35a6e482c  mov        r8d, edi
  0x35a6e482f  mov        rdx, rsi
  0x35a6e4832  call       0x35a6e4170
  0x35a6e4837  jmp        0x35a6e484d
  0x35a6e4839  nop        dword ptr [rax]
  0x35a6e4840  mov        rdx, rbx
  0x35a6e4843  mov        ecx, 0x20
  0x35a6e4848  call       0x35a6e3310
  0x35a6e484d  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e4850  lea        edx, [rax - 1]
  0x35a6e4853  mov        dword ptr [rbx + 0xc], edx
  0x35a6e4856  test       eax, eax
  0x35a6e4858  jg         0x35a6e4840
  0x35a6e485a  jmp        0x35a6e4883
  0x35a6e485c  nop        dword ptr [rax]
  0x35a6e4860  test       eax, eax
  0x35a6e4862  jne        0x35a6e4898
  0x35a6e4864  mov        rcx, rsi
  0x35a6e4867  call       0x35a6e8760
  0x35a6e486c  sub        eax, 1
  0x35a6e486f  mov        dword ptr [rbx + 0x10], eax
  0x35a6e4872  mov        ecx, dword ptr [rbp - 8]
  0x35a6e4875  mov        r9, rbx
  0x35a6e4878  mov        r8d, edi
  0x35a6e487b  mov        rdx, rsi
  0x35a6e487e  call       0x35a6e4550
  0x35a6e4883  mov        rcx, rsi
  0x35a6e4886  call       0x35a6e58a0
  0x35a6e488b  nop        
  0x35a6e488c  add        rsp, 0x58
  0x35a6e4890  pop        rbx
  0x35a6e4891  pop        rsi
  0x35a6e4892  pop        rdi
  0x35a6e4893  pop        rbp
  0x35a6e4894  ret        
  0x35a6e4895  nop        dword ptr [rax]
  0x35a6e4898  mov        eax, dword ptr [rbx + 0x10]
  0x35a6e489b  sub        eax, 1
  0x35a6e489e  jmp        0x35a6e486f
  0x35a6e48a0  mov        dword ptr [rdx + 0x10], 1
  0x35a6e48a7  mov        r8d, 1
  0x35a6e48ad  jmp        0x35a6e47d5
  0x35a6e48b2  nop        word ptr [rax + rax]
  0x35a6e48b8  mov        dword ptr [rdx + 0x10], 6
  0x35a6e48bf  mov        r8d, 6
  0x35a6e48c5  jmp        0x35a6e47d5
  0x35a6e48ca  nop        word ptr [rax + rax]
  0x35a6e48d0  mov        ecx, dword ptr [rbp - 8]
  0x35a6e48d3  mov        r8, rbx
  0x35a6e48d6  mov        rdx, rax
  0x35a6e48d9  call       0x35a6e36b0
  0x35a6e48de  jmp        0x35a6e4883
  0x35a6e48e0  mov        rcx, rsi
  0x35a6e48e3  call       0x35a6e8760
  0x35a6e48e8  sub        eax, edi
  0x35a6e48ea  mov        dword ptr [rbx + 0x10], eax
  0x35a6e48ed  jns        0x35a6e4826
  0x35a6e48f3  mov        edx, dword ptr [rbx + 0xc]
  0x35a6e48f6  test       edx, edx
  0x35a6e48f8  jle        0x35a6e4826
  0x35a6e48fe  add        eax, edx
  0x35a6e4900  mov        dword ptr [rbx + 0xc], eax
  0x35a6e4903  jmp        0x35a6e4826
  0x35a6e4908  nop        dword ptr [rax + rax]
  0x35a6e4910  push       rbp
  0x35a6e4911  push       r14
  0x35a6e4913  push       r13
  0x35a6e4915  push       r12
  0x35a6e4917  push       rdi
  0x35a6e4918  push       rsi
  0x35a6e4919  push       rbx
  0x35a6e491a  sub        rsp, 0x50
  0x35a6e491e  lea        rbp, [rsp + 0x50]
  0x35a6e4923  mov        r10d, dword ptr [r8 + 0x10]
  0x35a6e4927  mov        r9, rcx
  0x35a6e492a  mov        eax, edx
  0x35a6e492c  mov        rbx, r8
  0x35a6e492f  test       dx, dx
  0x35a6e4932  jne        0x35a6e493d
  0x35a6e4934  test       rcx, rcx
  0x35a6e4937  je         0x35a6e4a28
  0x35a6e493d  lea        r8d, [rax - 3]
  0x35a6e4941  cmp        r10d, 0xe
  0x35a6e4945  jbe        0x35a6e49e0
  0x35a6e494b  movsx      r12, r8w
  0x35a6e494f  mov        edx, 0x10
  0x35a6e4954  test       r9, r9
  0x35a6e4957  je         0x35a6e4d60
  0x35a6e495d  mov        r10d, dword ptr [rbx + 8]
  0x35a6e4961  lea        rdi, [rbp - 0x20]
  0x35a6e4965  mov        rsi, rdi
  0x35a6e4968  mov        r11d, r10d
  0x35a6e496b  mov        r13d, r10d
  0x35a6e496e  and        r11d, 0x20
  0x35a6e4972  and        r13d, 0x800
  0x35a6e4979  jmp        0x35a6e49a6
  0x35a6e497b  nop        dword ptr [rax + rax]
  0x35a6e4980  cmp        rdi, rcx
  0x35a6e4983  jb         0x35a6e4990
  0x35a6e4985  mov        esi, dword ptr [rbx + 0x10]
  0x35a6e4988  test       esi, esi
  0x35a6e498a  js         0x35a6e4d08
  0x35a6e4990  add        eax, 0x30
  0x35a6e4993  mov        byte ptr [rcx], al
  0x35a6e4995  lea        rsi, [rcx + 1]
  0x35a6e4999  shr        r9, 4
  0x35a6e499d  sub        edx, 1
  0x35a6e49a0  je         0x35a6e4b90
  0x35a6e49a6  mov        eax, r9d
  0x35a6e49a9  and        eax, 0xf
  0x35a6e49ac  cmp        edx, 1
  0x35a6e49af  je         0x35a6e4b60
  0x35a6e49b5  mov        ecx, dword ptr [rbx + 0x10]
  0x35a6e49b8  test       ecx, ecx
  0x35a6e49ba  jle        0x35a6e49c2
  0x35a6e49bc  sub        ecx, 1
  0x35a6e49bf  mov        dword ptr [rbx + 0x10], ecx
  0x35a6e49c2  mov        rcx, rsi
  0x35a6e49c5  test       eax, eax
  0x35a6e49c7  je         0x35a6e4980
  0x35a6e49c9  cmp        eax, 9
  0x35a6e49cc  jbe        0x35a6e4990
  0x35a6e49ce  add        eax, 0x37
  0x35a6e49d1  or         eax, r11d
  0x35a6e49d4  jmp        0x35a6e4993
  0x35a6e49d6  nop        word ptr cs:[rax + rax]
  0x35a6e49e0  mov        ecx, 0xe
  0x35a6e49e5  mov        edx, 4
  0x35a6e49ea  shr        r9, 1
  0x35a6e49ed  sub        ecx, r10d
  0x35a6e49f0  shl        ecx, 2
  0x35a6e49f3  shl        rdx, cl
  0x35a6e49f6  add        rdx, r9
  0x35a6e49f9  jns        0x35a6e4d50
  0x35a6e49ff  mov        ecx, 0xf
  0x35a6e4a04  shr        rdx, 3
  0x35a6e4a08  lea        r8d, [rax + 1]
  0x35a6e4a0c  sub        ecx, r10d
  0x35a6e4a0f  movsx      r12, r8w
  0x35a6e4a13  shl        ecx, 2
  0x35a6e4a16  shr        rdx, cl
  0x35a6e4a19  mov        r9, rdx
  0x35a6e4a1c  lea        edx, [r10 + 1]
  0x35a6e4a20  jmp        0x35a6e495d
  0x35a6e4a25  nop        dword ptr [rax]
  0x35a6e4a28  cmp        r10d, 0xe
  0x35a6e4a2c  ja         0x35a6e4d38
  0x35a6e4a32  mov        ecx, 0xe
  0x35a6e4a37  mov        edx, 4
  0x35a6e4a3c  xor        r12d, r12d
  0x35a6e4a3f  xor        r8d, r8d
  0x35a6e4a42  sub        ecx, r10d
  0x35a6e4a45  shl        ecx, 2
  0x35a6e4a48  shl        rdx, cl
  0x35a6e4a4b  mov        ecx, 0xf
  0x35a6e4a50  add        rdx, rdx
  0x35a6e4a53  sub        ecx, r10d
  0x35a6e4a56  shl        ecx, 2
  0x35a6e4a59  shr        rdx, cl
  0x35a6e4a5c  mov        r9, rdx
  0x35a6e4a5f  test       rdx, rdx
  0x35a6e4a62  jne        0x35a6e4a1c
  0x35a6e4a64  test       r10d, r10d
  0x35a6e4a67  jne        0x35a6e4a1c
  0x35a6e4a69  mov        r10d, dword ptr [rbx + 8]
  0x35a6e4a6d  lea        rdi, [rbp - 0x20]
  0x35a6e4a71  mov        rax, rdi
  0x35a6e4a74  test       r10d, 0x800
  0x35a6e4a7b  je         0x35a6e4a85
  0x35a6e4a7d  mov        byte ptr [rbp - 0x20], 0x2e
  0x35a6e4a81  lea        rax, [rbp - 0x1f]
  0x35a6e4a85  mov        r9d, dword ptr [rbx + 0xc]
  0x35a6e4a89  mov        byte ptr [rax], 0x30
  0x35a6e4a8c  lea        rsi, [rax + 1]
  0x35a6e4a90  mov        r13d, 2
  0x35a6e4a96  test       r9d, r9d
  0x35a6e4a99  jg         0x35a6e4bac
  0x35a6e4a9f  test       r10b, 0x80
  0x35a6e4aa3  jne        0x35a6e4c70
  0x35a6e4aa9  test       r10d, 0x100
  0x35a6e4ab0  jne        0x35a6e4d20
  0x35a6e4ab6  and        r10d, 0x40
  0x35a6e4aba  jne        0x35a6e4d70
  0x35a6e4ac0  mov        rdx, rbx
  0x35a6e4ac3  mov        ecx, 0x30
  0x35a6e4ac8  call       0x35a6e3310
  0x35a6e4acd  mov        ecx, dword ptr [rbx + 8]
  0x35a6e4ad0  mov        rdx, rbx
  0x35a6e4ad3  and        ecx, 0x20
  0x35a6e4ad6  or         ecx, 0x58
  0x35a6e4ad9  call       0x35a6e3310
  0x35a6e4ade  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e4ae1  test       eax, eax
  0x35a6e4ae3  jle        0x35a6e4b12
  0x35a6e4ae5  test       byte ptr [rbx + 9], 2
  0x35a6e4ae9  je         0x35a6e4b12
  0x35a6e4aeb  sub        eax, 1
  0x35a6e4aee  mov        dword ptr [rbx + 0xc], eax
  0x35a6e4af1  nop        dword ptr [rax]
  0x35a6e4af8  mov        rdx, rbx
  0x35a6e4afb  mov        ecx, 0x30
  0x35a6e4b00  call       0x35a6e3310
  0x35a6e4b05  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e4b08  lea        edx, [rax - 1]
  0x35a6e4b0b  mov        dword ptr [rbx + 0xc], edx
  0x35a6e4b0e  test       eax, eax
  0x35a6e4b10  jg         0x35a6e4af8
  0x35a6e4b12  lea        r14, [rbp - 0x22]
  0x35a6e4b16  cmp        rdi, rsi
  0x35a6e4b19  jb         0x35a6e4b3a
  0x35a6e4b1b  jmp        0x35a6e4c95
  0x35a6e4b20  movzx      eax, word ptr [rbx + 0x20]
  0x35a6e4b24  mov        word ptr [rbp - 0x22], ax
  0x35a6e4b28  test       ax, ax
  0x35a6e4b2b  jne        0x35a6e4cf0
  0x35a6e4b31  cmp        rsi, rdi
  0x35a6e4b34  je         0x35a6e4c95
  0x35a6e4b3a  movsx      ecx, byte ptr [rsi - 1]
  0x35a6e4b3e  sub        rsi, 1
  0x35a6e4b42  cmp        ecx, 0x2e
  0x35a6e4b45  je         0x35a6e4ce0
  0x35a6e4b4b  cmp        ecx, 0x2c
  0x35a6e4b4e  je         0x35a6e4b20
  0x35a6e4b50  mov        rdx, rbx
  0x35a6e4b53  call       0x35a6e3310
  0x35a6e4b58  jmp        0x35a6e4b31
  0x35a6e4b5a  nop        word ptr [rax + rax]
  0x35a6e4b60  cmp        rdi, rsi
  0x35a6e4b63  jb         0x35a6e4b78
  0x35a6e4b65  test       r13d, r13d
  0x35a6e4b68  jne        0x35a6e4b78
  0x35a6e4b6a  mov        ecx, dword ptr [rbx + 0x10]
  0x35a6e4b6d  test       ecx, ecx
  0x35a6e4b6f  jle        0x35a6e4d88
  0x35a6e4b75  nop        dword ptr [rax]
  0x35a6e4b78  mov        byte ptr [rsi], 0x2e
  0x35a6e4b7b  lea        rcx, [rsi + 1]
  0x35a6e4b7f  jmp        0x35a6e49c5
  0x35a6e4b84  test       ecx, ecx
  0x35a6e4b86  jne        0x35a6e4b90
  0x35a6e4b88  mov        byte ptr [rsi], 0x30
  0x35a6e4b8b  add        rsi, 1
  0x35a6e4b8f  nop        
  0x35a6e4b90  cmp        rsi, rdi
  0x35a6e4b93  je         0x35a6e4da0
  0x35a6e4b99  mov        r9d, dword ptr [rbx + 0xc]
  0x35a6e4b9d  mov        r13d, 2
  0x35a6e4ba3  test       r9d, r9d
  0x35a6e4ba6  jle        0x35a6e4a9f
  0x35a6e4bac  mov        edx, dword ptr [rbx + 0x10]
  0x35a6e4baf  mov        rcx, rsi
  0x35a6e4bb2  movsx      eax, r8w
  0x35a6e4bb6  movsx      r8, r8w
  0x35a6e4bba  sub        rcx, rdi
  0x35a6e4bbd  lea        r11d, [rdx + rcx]
  0x35a6e4bc1  test       edx, edx
  0x35a6e4bc3  mov        edx, r10d
  0x35a6e4bc6  cmovg      ecx, r11d
  0x35a6e4bca  and        edx, 0x1c0
  0x35a6e4bd0  cmp        edx, 1
  0x35a6e4bd3  sbb        ecx, -6
  0x35a6e4bd6  imul       r8, r8, 0x66666667
  0x35a6e4bdd  sar        eax, 0x1f
  0x35a6e4be0  mov        r11d, ecx
  0x35a6e4be3  sar        r8, 0x22
  0x35a6e4be7  sub        r8d, eax
  0x35a6e4bea  je         0x35a6e4c1d
  0x35a6e4bec  nop        dword ptr [rax]
  0x35a6e4bf0  movsxd     rax, r8d
  0x35a6e4bf3  mov        edx, r8d
  0x35a6e4bf6  add        r11d, 1
  0x35a6e4bfa  imul       rax, rax, 0x66666667
  0x35a6e4c01  sar        edx, 0x1f
  0x35a6e4c04  sar        rax, 0x22
  0x35a6e4c08  sub        eax, edx
  0x35a6e4c0a  mov        r8d, eax
  0x35a6e4c0d  jne        0x35a6e4bf0
  0x35a6e4c0f  mov        r13d, r11d
  0x35a6e4c12  sub        r13d, ecx
  0x35a6e4c15  add        r13d, 2
  0x35a6e4c19  movsx      r13d, r13w
  0x35a6e4c1d  cmp        r9d, r11d
  0x35a6e4c20  jle        0x35a6e4d10
  0x35a6e4c26  sub        r9d, r11d
  0x35a6e4c29  test       r10d, 0x600
  0x35a6e4c30  jne        0x35a6e4d16
  0x35a6e4c36  sub        r9d, 1
  0x35a6e4c3a  mov        dword ptr [rbx + 0xc], r9d
  0x35a6e4c3e  nop        
  0x35a6e4c40  mov        rdx, rbx
  0x35a6e4c43  mov        ecx, 0x20
  0x35a6e4c48  call       0x35a6e3310
  0x35a6e4c4d  mov        eax, dword ptr [rbx + 0xc]
  0x35a6e4c50  lea        edx, [rax - 1]
  0x35a6e4c53  mov        dword ptr [rbx + 0xc], edx
  0x35a6e4c56  test       eax, eax
  0x35a6e4c58  jg         0x35a6e4c40
  0x35a6e4c5a  mov        r10d, dword ptr [rbx + 8]
  0x35a6e4c5e  test       r10b, 0x80
  0x35a6e4c62  je         0x35a6e4aa9
  0x35a6e4c68  nop        dword ptr [rax + rax]
  0x35a6e4c70  mov        rdx, rbx
  0x35a6e4c73  mov        ecx, 0x2d
  0x35a6e4c78  call       0x35a6e3310
  0x35a6e4c7d  jmp        0x35a6e4ac0
  0x35a6e4c82  nop        word ptr [rax + rax]
  0x35a6e4c88  mov        rdx, rbx
  0x35a6e4c8b  mov        ecx, 0x30
  0x35a6e4c90  call       0x35a6e3310
  0x35a6e4c95  mov        eax, dword ptr [rbx + 0x10]
  0x35a6e4c98  lea        edx, [rax - 1]
  0x35a6e4c9b  mov        dword ptr [rbx + 0x10], edx
  0x35a6e4c9e  test       eax, eax
  0x35a6e4ca0  jg         0x35a6e4c88
  0x35a6e4ca2  mov        ecx, dword ptr [rbx + 8]
  0x35a6e4ca5  mov        rdx, rbx
  0x35a6e4ca8  and        ecx, 0x20
  0x35a6e4cab  or         ecx, 0x50
  0x35a6e4cae  call       0x35a6e3310
  0x35a6e4cb3  add        dword ptr [rbx + 0xc], r13d
  0x35a6e4cb7  mov        rdx, rbx
  0x35a6e4cba  mov        rcx, r12
  0x35a6e4cbd  or         dword ptr [rbx + 8], 0x1c0
  0x35a6e4cc4  add        rsp, 0x50
  0x35a6e4cc8  pop        rbx
  0x35a6e4cc9  pop        rsi
  0x35a6e4cca  pop        rdi
  0x35a6e4ccb  pop        r12
  0x35a6e4ccd  pop        r13
  0x35a6e4ccf  pop        r14
  0x35a6e4cd1  pop        rbp
  0x35a6e4cd2  jmp        0x35a6e3c70
  0x35a6e4cd7  nop        word ptr [rax + rax]
  0x35a6e4ce0  mov        rcx, rbx
  0x35a6e4ce3  call       0x35a6e4020
  0x35a6e4ce8  jmp        0x35a6e4b31
  0x35a6e4ced  nop        dword ptr [rax]
  0x35a6e4cf0  mov        r8, rbx
  0x35a6e4cf3  mov        edx, 1
  0x35a6e4cf8  mov        rcx, r14
  0x35a6e4cfb  call       0x35a6e3370
  0x35a6e4d00  jmp        0x35a6e4b31
  0x35a6e4d05  nop        dword ptr [rax]
  0x35a6e4d08  mov        rsi, rcx
  0x35a6e4d0b  jmp        0x35a6e4999
  0x35a6e4d10  mov        r9d, 0xffffffff
  0x35a6e4d16  mov        dword ptr [rbx + 0xc], r9d
  0x35a6e4d1a  jmp        0x35a6e4a9f
  0x35a6e4d1f  nop        
  0x35a6e4d20  mov        rdx, rbx
  0x35a6e4d23  mov        ecx, 0x2b
  0x35a6e4d28  call       0x35a6e3310
  0x35a6e4d2d  jmp        0x35a6e4ac0
  0x35a6e4d32  nop        word ptr [rax + rax]
  0x35a6e4d38  test       r10d, r10d
  0x35a6e4d3b  jle        0x35a6e4db0
  0x35a6e4d3d  xor        r12d, r12d
  0x35a6e4d40  xor        r8d, r8d
  0x35a6e4d43  xor        r9d, r9d
  0x35a6e4d46  mov        edx, 0x10
  0x35a6e4d4b  jmp        0x35a6e495d
  0x35a6e4d50  movsx      r12, r8w
  0x35a6e4d54  jmp        0x35a6e4a4b
  0x35a6e4d59  nop        dword ptr [rax]
  0x35a6e4d60  test       r10d, r10d
  0x35a6e4d63  jg         0x35a6e495d
  0x35a6e4d69  jmp        0x35a6e4a69
  0x35a6e4d6e  nop        
  0x35a6e4d70  mov        rdx, rbx
  0x35a6e4d73  mov        ecx, 0x20
  0x35a6e4d78  call       0x35a6e3310
  0x35a6e4d7d  jmp        0x35a6e4ac0
  0x35a6e4d82  nop        word ptr [rax + rax]
  0x35a6e4d88  test       eax, eax
  0x35a6e4d8a  je         0x35a6e4b84
  0x35a6e4d90  mov        rcx, rsi
  0x35a6e4d93  jmp        0x35a6e49c9
  0x35a6e4d98  nop        dword ptr [rax + rax]
  0x35a6e4da0  mov        eax, dword ptr [rbx + 0x10]
  0x35a6e4da3  test       eax, eax
  0x35a6e4da5  jg         0x35a6e4a7d
  0x35a6e4dab  jmp        0x35a6e4a71
  0x35a6e4db0  mov        r10d, dword ptr [r8 + 8]
  0x35a6e4db4  xor        r12d, r12d
  0x35a6e4db7  xor        r8d, r8d
  0x35a6e4dba  lea        rdi, [rbp - 0x20]
  0x35a6e4dbe  jmp        0x35a6e4a71
  0x35a6e4dc3  nop        word ptr cs:[rax + rax]
  0x35a6e4dce  nop        
  0x35a6e4dd0  push       rbp
  0x35a6e4dd1  push       r15
  0x35a6e4dd3  push       r14
  0x35a6e4dd5  push       r13
  0x35a6e4dd7  push       r12
  0x35a6e4dd9  push       rdi
  0x35a6e4dda  push       rsi
  0x35a6e4ddb  push       rbx
  0x35a6e4ddc  sub        rsp, 0xb8
  0x35a6e4de3  lea        rbp, [rsp + 0xb0]
  0x35a6e4deb  mov        r14, qword ptr [rbp + 0x70]
  0x35a6e4def  mov        edi, ecx
  0x35a6e4df1  mov        r12, rdx
  0x35a6e4df4  mov        ebx, r8d
  0x35a6e4df7  mov        rsi, r9
  0x35a6e4dfa  call       0x35a6e86e0
  0x35a6e4dff  and        edi, 0x6000
  0x35a6e4e05  xor        edx, edx
  0x35a6e4e07  mov        dword ptr [rbp - 8], ebx
  0x35a6e4e0a  movabs     rcx, 0xfffffffdffffffff
  0x35a6e4e14  mov        word ptr [rbp - 0x18], dx
  0x35a6e4e18  mov        eax, dword ptr [rax]
  0x35a6e4e1a  lea        rbx, [rsi + 1]
  0x35a6e4e1e  mov        qword ptr [rbp - 0x20], rcx
  0x35a6e4e22  xor        ecx, ecx
  0x35a6e4e24  mov        word ptr [rbp - 0x10], cx
  0x35a6e4e28  movsx      ecx, byte ptr [rsi]
  0x35a6e4e2b  mov        qword ptr [rbp - 0x30], r12
  0x35a6e4e2f  mov        dword ptr [rbp - 0x28], edi
  0x35a6e4e32  mov        edx, ecx
  0x35a6e4e34  mov        dword ptr [rbp - 0x24], 0xffffffff
  0x35a6e4e3b  mov        dword ptr [rbp - 0x14], 0
  0x35a6e4e42  mov        dword ptr [rbp - 0xc], 0
  0x35a6e4e49  mov        dword ptr [rbp - 4], 0xffffffff
  0x35a6e4e50  test       ecx, ecx
  0x35a6e4e52  je         0x35a6e4f53
  0x35a6e4e58  lea        rsi, [rbp - 0x24]
  0x35a6e4e5c  mov        dword ptr [rbp - 0x6c], eax
  0x35a6e4e5f  lea        r13, [rip + 0x54ba]
  0x35a6e4e66  mov        r15, rbx
  0x35a6e4e69  mov        qword ptr [rbp - 0x68], rsi
  0x35a6e4e6d  jmp        0x35a6e4ea9
  0x35a6e4e6f  nop        
  0x35a6e4e70  mov        eax, dword ptr [rbp - 0x28]
  0x35a6e4e73  mov        esi, dword ptr [rbp - 0xc]
  0x35a6e4e76  test       ah, 0x40
  0x35a6e4e79  jne        0x35a6e4e80
  0x35a6e4e7b  cmp        dword ptr [rbp - 8], esi
  0x35a6e4e7e  jle        0x35a6e4e90
  0x35a6e4e80  mov        r8, qword ptr [rbp - 0x30]
  0x35a6e4e84  test       ah, 0x20
  0x35a6e4e87  jne        0x35a6e4ef0
  0x35a6e4e89  movsxd     rax, esi
  0x35a6e4e8c  mov        byte ptr [r8 + rax], dl
  0x35a6e4e90  add        esi, 1
  0x35a6e4e93  mov        dword ptr [rbp - 0xc], esi
  0x35a6e4e96  movzx      edx, byte ptr [r15]
  0x35a6e4e9a  add        r15, 1
  0x35a6e4e9e  movsx      ecx, dl
  0x35a6e4ea1  test       ecx, ecx
  0x35a6e4ea3  je         0x35a6e4f50
  0x35a6e4ea9  cmp        ecx, 0x25
  0x35a6e4eac  jne        0x35a6e4e70
  0x35a6e4eae  movzx      edx, byte ptr [r15]
  0x35a6e4eb2  mov        dword ptr [rbp - 0x28], edi
  0x35a6e4eb5  mov        qword ptr [rbp - 0x24], 0xffffffffffffffff
  0x35a6e4ebd  test       dl, dl
  0x35a6e4ebf  je         0x35a6e4f50
  0x35a6e4ec5  mov        r10, qword ptr [rbp - 0x68]
  0x35a6e4ec9  mov        rsi, r15
  0x35a6e4ecc  xor        r11d, r11d
  0x35a6e4ecf  xor        ebx, ebx
  0x35a6e4ed1  lea        eax, [rdx - 0x20]
  0x35a6e4ed4  lea        r12, [rsi + 1]
  0x35a6e4ed8  movsx      ecx, dl
  0x35a6e4edb  cmp        al, 0x5a
  0x35a6e4edd  ja         0x35a6e4f00
  0x35a6e4edf  movzx      eax, al
  0x35a6e4ee2  movsxd     rax, dword ptr [r13 + rax*4]
  0x35a6e4ee7  add        rax, r13
  0x35a6e4eea  jmp        rax
  0x35a6e4eec  nop        dword ptr [rax]
  0x35a6e4ef0  mov        rdx, r8
  0x35a6e4ef3  call       0x35a6e8710
  0x35a6e4ef8  jmp        0x35a6e4e90
  0x35a6e4efa  nop        word ptr [rax + rax]
  0x35a6e4f00  sub        edx, 0x30
  0x35a6e4f03  cmp        dl, 9
  0x35a6e4f06  ja         0x35a6e510a
  0x35a6e4f0c  cmp        ebx, 3
  0x35a6e4f0f  ja         0x35a6e510a
  0x35a6e4f15  test       ebx, ebx
  0x35a6e4f17  jne        0x35a6e55ef
  0x35a6e4f1d  mov        ebx, 1
  0x35a6e4f22  test       r10, r10
  0x35a6e4f25  je         0x35a6e4f40
  0x35a6e4f27  mov        eax, dword ptr [r10]
  0x35a6e4f2a  test       eax, eax
  0x35a6e4f2c  js         0x35a6e5691
  0x35a6e4f32  lea        eax, [rax + rax*4]
  0x35a6e4f35  lea        eax, [rcx + rax*2 - 0x30]
  0x35a6e4f39  mov        dword ptr [r10], eax
  0x35a6e4f3c  nop        dword ptr [rax]
  0x35a6e4f40  movzx      edx, byte ptr [rsi + 1]
  0x35a6e4f44  mov        rsi, r12
  0x35a6e4f47  test       dl, dl
  0x35a6e4f49  jne        0x35a6e4ed1
  0x35a6e4f4b  nop        dword ptr [rax + rax]
  0x35a6e4f50  mov        ecx, dword ptr [rbp - 0xc]
  0x35a6e4f53  mov        eax, ecx
  0x35a6e4f55  add        rsp, 0xb8
  0x35a6e4f5c  pop        rbx
  0x35a6e4f5d  pop        rsi
  0x35a6e4f5e  pop        rdi
  0x35a6e4f5f  pop        r12
  0x35a6e4f61  pop        r13
  0x35a6e4f63  pop        r14
  0x35a6e4f65  pop        r15
  0x35a6e4f67  pop        rbp
  0x35a6e4f68  ret        
  0x35a6e4f69  nop        dword ptr [rax]
  0x35a6e4f70  and        dword ptr [rbp - 0x28], 0xfffffeff
  0x35a6e4f77  cmp        r11d, 3
  0x35a6e4f7b  je         0x35a6e56bf
  0x35a6e4f81  cmp        r11d, 2
  0x35a6e4f85  je         0x35a6e576c
  0x35a6e4f8b  mov        edx, dword ptr [r14]
  0x35a6e4f8e  movzx      eax, dx
  0x35a6e4f91  cmp        r11d, 1
  0x35a6e4f95  je         0x35a6e4fa8
  0x35a6e4f97  mov        r8d, edx
  0x35a6e4f9a  cmp        r11d, 5
  0x35a6e4f9e  movzx      edx, dl
  0x35a6e4fa1  mov        rax, r8
  0x35a6e4fa4  cmove      rax, rdx
  0x35a6e4fa8  mov        qword ptr [rbp - 0x40], rax
  0x35a6e4fac  cmp        ecx, 0x75
  0x35a6e4faf  je         0x35a6e5701
  0x35a6e4fb5  lea        r8, [rbp - 0x30]
  0x35a6e4fb9  mov        rdx, rax
  0x35a6e4fbc  call       0x35a6e3750
  0x35a6e4fc1  jmp        0x35a6e5250
  0x35a6e4fc6  nop        word ptr cs:[rax + rax]
  0x35a6e4fd0  movzx      edx, byte ptr [rsi + 1]
  0x35a6e4fd4  mov        r11d, 3
  0x35a6e4fda  mov        rsi, r12
  0x35a6e4fdd  mov        ebx, 4
  0x35a6e4fe2  jmp        0x35a6e4f47
  0x35a6e4fe7  or         dword ptr [rbp - 0x28], 0x80
  0x35a6e4fee  lea        rsi, [r14 + 8]
  0x35a6e4ff2  cmp        r11d, 3
  0x35a6e4ff6  je         0x35a6e5712
  0x35a6e4ffc  movsxd     rcx, dword ptr [r14]
  0x35a6e4fff  cmp        r11d, 2
  0x35a6e5003  je         0x35a6e501b
  0x35a6e5005  cmp        r11d, 1
  0x35a6e5009  je         0x35a6e5680
  0x35a6e500f  movsx      rdx, cl
  0x35a6e5013  cmp        r11d, 5
  0x35a6e5017  cmove      rcx, rdx
  0x35a6e501b  lea        rdx, [rbp - 0x30]
  0x35a6e501f  mov        r14, rsi
  0x35a6e5022  mov        r15, r12
  0x35a6e5025  call       0x35a6e3c70
  0x35a6e502a  jmp        0x35a6e4e96
  0x35a6e502f  test       ebx, ebx
  0x35a6e5031  jne        0x35a6e503c
  0x35a6e5033  cmp        dword ptr [rbp - 0x28], edi
  0x35a6e5036  je         0x35a6e565a
  0x35a6e503c  mov        rdx, qword ptr [r14]
  0x35a6e503f  lea        rsi, [r14 + 8]
  0x35a6e5043  lea        r8, [rbp - 0x30]
  0x35a6e5047  mov        ecx, 0x78
  0x35a6e504c  mov        r14, rsi
  0x35a6e504f  mov        r15, r12
  0x35a6e5052  call       0x35a6e3750
  0x35a6e5057  jmp        0x35a6e4e96
  0x35a6e505c  movzx      edx, byte ptr [rsi + 1]
  0x35a6e5060  cmp        dl, 0x68
  0x35a6e5063  je         0x35a6e56c7
  0x35a6e5069  mov        rsi, r12
  0x35a6e506c  mov        r11d, 1
  0x35a6e5072  mov        ebx, 4
  0x35a6e5077  jmp        0x35a6e4f47
  0x35a6e507c  mov        ecx, dword ptr [rbp - 0x6c]
  0x35a6e507f  mov        r15, r12
  0x35a6e5082  call       0x35a6e8758
  0x35a6e5087  lea        rdx, [rbp - 0x30]
  0x35a6e508b  mov        rcx, rax
  0x35a6e508e  call       0x35a6e3660
  0x35a6e5093  jmp        0x35a6e4e96
  0x35a6e5098  mov        rcx, qword ptr [r14]
  0x35a6e509b  movsxd     rdx, dword ptr [rbp - 0xc]
  0x35a6e509f  cmp        r11d, 5
  0x35a6e50a3  je         0x35a6e56df
  0x35a6e50a9  cmp        r11d, 1
  0x35a6e50ad  je         0x35a6e5764
  0x35a6e50b3  cmp        r11d, 2
  0x35a6e50b7  je         0x35a6e50c3
  0x35a6e50b9  cmp        r11d, 3
  0x35a6e50bd  je         0x35a6e5689
  0x35a6e50c3  mov        dword ptr [rcx], edx
  0x35a6e50c5  jmp        0x35a6e5250
  0x35a6e50ca  movzx      edx, byte ptr [rsi + 1]
  0x35a6e50ce  cmp        dl, 0x6c
  0x35a6e50d1  je         0x35a6e573c
  0x35a6e50d7  mov        rsi, r12
  0x35a6e50da  mov        r11d, 2
  0x35a6e50e0  mov        ebx, 4
  0x35a6e50e5  jmp        0x35a6e4f47
  0x35a6e50ea  movzx      edx, byte ptr [rsi + 1]
  0x35a6e50ee  cmp        dl, 0x36
  0x35a6e50f1  je         0x35a6e571a
  0x35a6e50f7  cmp        dl, 0x33
  0x35a6e50fa  jne        0x35a6e563d
  0x35a6e5100  cmp        byte ptr [rsi + 2], 0x32
  0x35a6e5104  je         0x35a6e5787
  0x35a6e510a  lea        rdx, [rbp - 0x30]
  0x35a6e510e  mov        ecx, 0x25
  0x35a6e5113  call       0x35a6e3310
  0x35a6e5118  jmp        0x35a6e4e96
  0x35a6e511d  nop        dword ptr [rax]
  0x35a6e5120  movzx      edx, byte ptr [rsi + 1]
  0x35a6e5124  or         dword ptr [rbp - 0x28], 4
  0x35a6e5128  mov        rsi, r12
  0x35a6e512b  mov        ebx, 4
  0x35a6e5130  jmp        0x35a6e4f47
  0x35a6e5135  mov        eax, dword ptr [rbp - 0x28]
  0x35a6e5138  mov        rdx, qword ptr [r14]
  0x35a6e513b  or         eax, 0x20
  0x35a6e513e  mov        dword ptr [rbp - 0x28], eax
  0x35a6e5141  test       al, 4
  0x35a6e5143  je         0x35a6e5322
  0x35a6e5149  mov        r8, qword ptr [rdx]
  0x35a6e514c  mov        r9d, dword ptr [rdx + 8]
  0x35a6e5150  mov        r10, r8
  0x35a6e5153  movsx      r11d, r9w
  0x35a6e5157  mov        rdx, r9
  0x35a6e515a  shr        r10, 0x20
  0x35a6e515e  lea        esi, [r11 + r11]
  0x35a6e5162  and        r10d, 0x7fffffff
  0x35a6e5169  movzx      esi, si
  0x35a6e516c  or         r10d, r8d
  0x35a6e516f  mov        ecx, r10d
  0x35a6e5172  neg        ecx
  0x35a6e5174  or         ecx, r10d
  0x35a6e5177  shr        ecx, 0x1f
  0x35a6e517a  or         ecx, esi
  0x35a6e517c  mov        esi, 0xfffe
  0x35a6e5181  sub        esi, ecx
  0x35a6e5183  shr        esi, 0x10
  0x35a6e5186  jne        0x35a6e55ff
  0x35a6e518c  test       r9w, r9w
  0x35a6e5190  js         0x35a6e5650
  0x35a6e5196  and        dx, 0x7fff
  0x35a6e519b  je         0x35a6e562a
  0x35a6e51a1  cmp        dx, 0x7fff
  0x35a6e51a6  jne        0x35a6e51b1
  0x35a6e51a8  test       r10d, r10d
  0x35a6e51ab  je         0x35a6e57bd
  0x35a6e51b1  sub        dx, 0x3fff
  0x35a6e51b6  mov        rcx, r8
  0x35a6e51b9  jmp        0x35a6e558f
  0x35a6e51be  lea        eax, [r11 - 2]
  0x35a6e51c2  mov        dword ptr [rbp - 0x20], 0xffffffff
  0x35a6e51c9  mov        edx, dword ptr [r14]
  0x35a6e51cc  lea        rsi, [r14 + 8]
  0x35a6e51d0  cmp        eax, 1
  0x35a6e51d3  jbe        0x35a6e53c3
  0x35a6e51d9  mov        byte ptr [rbp - 0x40], dl
  0x35a6e51dc  lea        rcx, [rbp - 0x40]
  0x35a6e51e0  lea        r8, [rbp - 0x30]
  0x35a6e51e4  mov        edx, 1
  0x35a6e51e9  call       0x35a6e3510
  0x35a6e51ee  mov        r14, rsi
  0x35a6e51f1  mov        r15, r12
  0x35a6e51f4  jmp        0x35a6e4e96
  0x35a6e51f9  lea        eax, [r11 - 2]
  0x35a6e51fd  mov        rcx, qword ptr [r14]
  0x35a6e5200  lea        rsi, [r14 + 8]
  0x35a6e5204  cmp        eax, 1
  0x35a6e5207  jbe        0x35a6e55a1
  0x35a6e520d  lea        rdx, [rbp - 0x30]
  0x35a6e5211  mov        r14, rsi
  0x35a6e5214  mov        r15, r12
  0x35a6e5217  call       0x35a6e3660
  0x35a6e521c  jmp        0x35a6e4e96
  0x35a6e5221  mov        eax, dword ptr [rbp - 0x28]
  0x35a6e5224  mov        rdx, qword ptr [r14]
  0x35a6e5227  or         eax, 0x20
  0x35a6e522a  mov        dword ptr [rbp - 0x28], eax
  0x35a6e522d  test       al, 4
  0x35a6e522f  je         0x35a6e5449
  0x35a6e5235  fld        xword ptr [rdx]
  0x35a6e5237  lea        rcx, [rbp - 0x60]
  0x35a6e523b  lea        rdx, [rbp - 0x30]
  0x35a6e523f  fstp       xword ptr [rbp - 0x60]
  0x35a6e5242  call       0x35a6e47b0
  0x35a6e5247  nop        word ptr [rax + rax]
  0x35a6e5250  add        r14, 8
  0x35a6e5254  mov        r15, r12
  0x35a6e5257  jmp        0x35a6e4e96
  0x35a6e525c  mov        eax, dword ptr [rbp - 0x28]
  0x35a6e525f  mov        rdx, qword ptr [r14]
  0x35a6e5262  or         eax, 0x20
  0x35a6e5265  mov        dword ptr [rbp - 0x28], eax
  0x35a6e5268  test       al, 4
  0x35a6e526a  je         0x35a6e541f
  0x35a6e5270  fld        xword ptr [rdx]
  0x35a6e5272  lea        rcx, [rbp - 0x60]
  0x35a6e5276  lea        rdx, [rbp - 0x30]
  0x35a6e527a  fstp       xword ptr [rbp - 0x60]
  0x35a6e527d  call       0x35a6e46d0
  0x35a6e5282  jmp        0x35a6e5250
  0x35a6e5284  mov        eax, dword ptr [rbp - 0x28]
  0x35a6e5287  mov        rdx, qword ptr [r14]
  0x35a6e528a  or         eax, 0x20
  0x35a6e528d  mov        dword ptr [rbp - 0x28], eax
  0x35a6e5290  test       al, 4
  0x35a6e5292  je         0x35a6e53f5
  0x35a6e5298  fld        xword ptr [rdx]
  0x35a6e529a  lea        rcx, [rbp - 0x60]
  0x35a6e529e  lea        rdx, [rbp - 0x30]
  0x35a6e52a2  fstp       xword ptr [rbp - 0x60]
  0x35a6e52a5  call       0x35a6e4630
  0x35a6e52aa  jmp        0x35a6e5250
  0x35a6e52ac  test       ebx, ebx
  0x35a6e52ae  jne        0x35a6e4f40
  0x35a6e52b4  movzx      edx, byte ptr [rsi + 1]
  0x35a6e52b8  or         dword ptr [rbp - 0x28], 0x40
  0x35a6e52bc  mov        rsi, r12
  0x35a6e52bf  jmp        0x35a6e4f47
  0x35a6e52c4  test       ebx, ebx
  0x35a6e52c6  jne        0x35a6e4f40
  0x35a6e52cc  movzx      edx, byte ptr [rsi + 1]
  0x35a6e52d0  or         dword ptr [rbp - 0x28], 0x400
  0x35a6e52d7  mov        rsi, r12
  0x35a6e52da  jmp        0x35a6e4f47
  0x35a6e52df  cmp        ebx, 1
  0x35a6e52e2  jbe        0x35a6e56a3
  0x35a6e52e8  movzx      edx, byte ptr [rsi + 1]
  0x35a6e52ec  mov        ebx, 4
  0x35a6e52f1  mov        rsi, r12
  0x35a6e52f4  jmp        0x35a6e4f47
  0x35a6e52f9  test       ebx, ebx
  0x35a6e52fb  jne        0x35a6e55e1
  0x35a6e5301  movzx      edx, byte ptr [rsi + 1]
  0x35a6e5305  or         dword ptr [rbp - 0x28], 0x200
  0x35a6e530c  mov        rsi, r12
  0x35a6e530f  jmp        0x35a6e4f47
  0x35a6e5314  mov        eax, dword ptr [rbp - 0x28]
  0x35a6e5317  mov        rdx, qword ptr [r14]
  0x35a6e531a  test       al, 4
  0x35a6e531c  jne        0x35a6e5149
  0x35a6e5322  mov        r8, rdx
  0x35a6e5325  mov        ecx, edx
  0x35a6e5327  shr        r8, 0x20
  0x35a6e532b  neg        ecx
  0x35a6e532d  mov        r9d, r8d
  0x35a6e5330  or         ecx, edx
  0x35a6e5332  and        r9d, 0x7fffffff
  0x35a6e5339  shr        ecx, 0x1f
  0x35a6e533c  or         ecx, r9d
  0x35a6e533f  mov        r9d, 0x7ff00000
  0x35a6e5345  cmp        r9d, ecx
  0x35a6e5348  js         0x35a6e55ff
  0x35a6e534e  mov        qword ptr [rbp - 0x80], rdx
  0x35a6e5352  fld        qword ptr [rbp - 0x80]
  0x35a6e5355  fstp       xword ptr [rbp - 0x80]
  0x35a6e5358  mov        rcx, qword ptr [rbp - 0x78]
  0x35a6e535c  test       cx, cx
  0x35a6e535f  jns        0x35a6e5366
  0x35a6e5361  or         al, 0x80
  0x35a6e5363  mov        dword ptr [rbp - 0x28], eax
  0x35a6e5366  mov        eax, r8d
  0x35a6e5369  and        r8d, 0x7ff00000
  0x35a6e5370  and        eax, 0xfffff
  0x35a6e5375  cmp        r8d, 0x7ff00000
  0x35a6e537c  setne      r9b
  0x35a6e5380  or         eax, edx
  0x35a6e5382  setne      dl
  0x35a6e5385  or         r9b, dl
  0x35a6e5388  jne        0x35a6e5555
  0x35a6e538e  or         eax, r8d
  0x35a6e5391  je         0x35a6e5555
  0x35a6e5397  and        ecx, 0x8000
  0x35a6e539d  lea        r8, [rbp - 0x30]
  0x35a6e53a1  lea        rdx, [rip + 0x4f72]
  0x35a6e53a8  call       0x35a6e36b0
  0x35a6e53ad  jmp        0x35a6e5250
  0x35a6e53b2  nop        word ptr [rax + rax]
  0x35a6e53b8  mov        dword ptr [rbp - 0x20], 0xffffffff
  0x35a6e53bf  lea        rsi, [r14 + 8]
  0x35a6e53c3  mov        eax, dword ptr [r14]
  0x35a6e53c6  lea        rcx, [rbp - 0x40]
  0x35a6e53ca  lea        r8, [rbp - 0x30]
  0x35a6e53ce  mov        edx, 1
  0x35a6e53d3  mov        r14, rsi
  0x35a6e53d6  mov        r15, r12
  0x35a6e53d9  mov        word ptr [rbp - 0x40], ax
  0x35a6e53dd  call       0x35a6e3370
  0x35a6e53e2  jmp        0x35a6e4e96
  0x35a6e53e7  mov        eax, dword ptr [rbp - 0x28]
  0x35a6e53ea  mov        rdx, qword ptr [r14]
  0x35a6e53ed  test       al, 4
  0x35a6e53ef  jne        0x35a6e5298
  0x35a6e53f5  mov        qword ptr [rbp - 0x80], rdx
  0x35a6e53f9  fld        qword ptr [rbp - 0x80]
  0x35a6e53fc  lea        rdx, [rbp - 0x30]
  0x35a6e5400  lea        rcx, [rbp - 0x60]
  0x35a6e5404  fstp       xword ptr [rbp - 0x60]
  0x35a6e5407  call       0x35a6e4630
  0x35a6e540c  jmp        0x35a6e5250
  0x35a6e5411  mov        eax, dword ptr [rbp - 0x28]
  0x35a6e5414  mov        rdx, qword ptr [r14]
  0x35a6e5417  test       al, 4
  0x35a6e5419  jne        0x35a6e5270
  0x35a6e541f  mov        qword ptr [rbp - 0x80], rdx
  0x35a6e5423  fld        qword ptr [rbp - 0x80]
  0x35a6e5426  lea        rdx, [rbp - 0x30]
  0x35a6e542a  lea        rcx, [rbp - 0x60]
  0x35a6e542e  fstp       xword ptr [rbp - 0x60]
  0x35a6e5431  call       0x35a6e46d0
  0x35a6e5436  jmp        0x35a6e5250
  0x35a6e543b  mov        eax, dword ptr [rbp - 0x28]
  0x35a6e543e  mov        rdx, qword ptr [r14]
  0x35a6e5441  test       al, 4
  0x35a6e5443  jne        0x35a6e5235
  0x35a6e5449  mov        qword ptr [rbp - 0x80], rdx
  0x35a6e544d  fld        qword ptr [rbp - 0x80]
  0x35a6e5450  lea        rdx, [rbp - 0x30]
  0x35a6e5454  lea        rcx, [rbp - 0x60]
  0x35a6e5458  fstp       xword ptr [rbp - 0x60]
  0x35a6e545b  call       0x35a6e47b0
  0x35a6e5460  jmp        0x35a6e5250
  0x35a6e5465  lea        rdx, [rbp - 0x30]
  0x35a6e5469  mov        ecx, 0x25
  0x35a6e546e  mov        r15, r12
  0x35a6e5471  call       0x35a6e3310
  0x35a6e5476  jmp        0x35a6e4e96
  0x35a6e547b  test       ebx, ebx
  0x35a6e547d  jne        0x35a6e4f40
  0x35a6e5483  lea        r9, [rbp - 0x40]
  0x35a6e5487  mov        qword ptr [rbp - 0x88], r10
  0x35a6e548e  mov        dword ptr [rbp - 0x70], r11d
  0x35a6e5492  or         dword ptr [rbp - 0x28], 0x1000
  0x35a6e5499  mov        qword ptr [rbp - 0x80], r9
  0x35a6e549d  mov        dword ptr [rbp - 0x40], 0
  0x35a6e54a4  call       0x35a6e8728
  0x35a6e54a9  mov        r9, qword ptr [rbp - 0x80]
  0x35a6e54ad  lea        rcx, [rbp - 0x42]
  0x35a6e54b1  mov        r8d, 0x10
  0x35a6e54b7  mov        rdx, qword ptr [rax + 8]
  0x35a6e54bb  call       0x35a6e84c0
  0x35a6e54c0  mov        r11d, dword ptr [rbp - 0x70]
  0x35a6e54c4  mov        r10, qword ptr [rbp - 0x88]
  0x35a6e54cb  test       eax, eax
  0x35a6e54cd  jle        0x35a6e54d7
  0x35a6e54cf  movzx      edx, word ptr [rbp - 0x42]
  0x35a6e54d3  mov        word ptr [rbp - 0x10], dx
  0x35a6e54d7  movzx      edx, byte ptr [rsi + 1]
  0x35a6e54db  mov        dword ptr [rbp - 0x14], eax
  0x35a6e54de  mov        rsi, r12
  0x35a6e54e1  jmp        0x35a6e4f47
  0x35a6e54e6  test       r10, r10
  0x35a6e54e9  je         0x35a6e52e8
  0x35a6e54ef  test       ebx, 0xfffffffd
  0x35a6e54f5  jne        0x35a6e5616
  0x35a6e54fb  mov        eax, dword ptr [r14]
  0x35a6e54fe  lea        rcx, [r14 + 8]
  0x35a6e5502  mov        dword ptr [r10], eax
  0x35a6e5505  test       eax, eax
  0x35a6e5507  js         0x35a6e5774
  0x35a6e550d  movzx      edx, byte ptr [rsi + 1]
  0x35a6e5511  mov        r14, rcx
  0x35a6e5514  mov        rsi, r12
  0x35a6e5517  xor        r10d, r10d
  0x35a6e551a  jmp        0x35a6e4f47
  0x35a6e551f  test       ebx, ebx
  0x35a6e5521  jne        0x35a6e4f40
  0x35a6e5527  movzx      edx, byte ptr [rsi + 1]
  0x35a6e552b  or         dword ptr [rbp - 0x28], 0x100
  0x35a6e5532  mov        rsi, r12
  0x35a6e5535  jmp        0x35a6e4f47
  0x35a6e553a  test       ebx, ebx
  0x35a6e553c  jne        0x35a6e4f40
  0x35a6e5542  movzx      edx, byte ptr [rsi + 1]
  0x35a6e5546  or         dword ptr [rbp - 0x28], 0x800
  0x35a6e554d  mov        rsi, r12
  0x35a6e5550  jmp        0x35a6e4f47
  0x35a6e5555  mov        edx, ecx
  0x35a6e5557  mov        rax, qword ptr [rbp - 0x80]
  0x35a6e555b  and        dx, 0x7fff
  0x35a6e5560  je         0x35a6e5754
  0x35a6e5566  cmp        dx, 0x3c00
  0x35a6e556b  jg         0x35a6e566e
  0x35a6e5571  movsx      r8d, dx
  0x35a6e5575  mov        ecx, 0x3c01
  0x35a6e557a  sub        ecx, r8d
  0x35a6e557d  shr        rax, cl
  0x35a6e5580  add        ecx, edx
  0x35a6e5582  lea        edx, [rcx - 0x3ffc]
  0x35a6e5588  shr        rax, 3
  0x35a6e558c  mov        rcx, rax
  0x35a6e558f  lea        r8, [rbp - 0x30]
  0x35a6e5593  call       0x35a6e4910
  0x35a6e5598  jmp        0x35a6e5250
  0x35a6e559d  lea        rsi, [r14 + 8]
  0x35a6e55a1  mov        r14, qword ptr [r14]
  0x35a6e55a4  lea        rax, [rip + 0x4d5d]
  0x35a6e55ab  test       r14, r14
  0x35a6e55ae  cmove      r14, rax
  0x35a6e55b2  mov        eax, dword ptr [rbp - 0x20]
  0x35a6e55b5  test       eax, eax
  0x35a6e55b7  js         0x35a6e56e6
  0x35a6e55bd  movsxd     rdx, eax
  0x35a6e55c0  mov        rcx, r14
  0x35a6e55c3  call       0x35a6e7ed0
  0x35a6e55c8  lea        r8, [rbp - 0x30]
  0x35a6e55cc  mov        rcx, r14
  0x35a6e55cf  mov        edx, eax
  0x35a6e55d1  call       0x35a6e3370
  0x35a6e55d6  mov        r14, rsi
  0x35a6e55d9  mov        r15, r12
  0x35a6e55dc  jmp        0x35a6e4e96
  0x35a6e55e1  cmp        ebx, 3
  0x35a6e55e4  ja         0x35a6e510a
  0x35a6e55ea  mov        ecx, 0x30
  0x35a6e55ef  cmp        ebx, 2
  0x35a6e55f2  mov        eax, 3
  0x35a6e55f7  cmove      ebx, eax
  0x35a6e55fa  jmp        0x35a6e4f22
  0x35a6e55ff  lea        r8, [rbp - 0x30]
  0x35a6e5603  lea        rdx, [rip + 0x4d0c]
  0x35a6e560a  xor        ecx, ecx
  0x35a6e560c  call       0x35a6e36b0
  0x35a6e5611  jmp        0x35a6e5250
  0x35a6e5616  movzx      edx, byte ptr [rsi + 1]
  0x35a6e561a  xor        r10d, r10d
  0x35a6e561d  mov        rsi, r12
  0x35a6e5620  mov        ebx, 4
  0x35a6e5625  jmp        0x35a6e4f47
  0x35a6e562a  test       r8, r8
  0x35a6e562d  mov        eax, 0xffffc002
  0x35a6e5632  mov        rcx, r8
  0x35a6e5635  cmovne     edx, eax
  0x35a6e5638  jmp        0x35a6e558f
  0x35a6e563d  mov        rsi, r12
  0x35a6e5640  mov        r11d, 3
  0x35a6e5646  mov        ebx, 4
  0x35a6e564b  jmp        0x35a6e4f47
  0x35a6e5650  or         al, 0x80
  0x35a6e5652  mov        dword ptr [rbp - 0x28], eax
  0x35a6e5655  jmp        0x35a6e5196
  0x35a6e565a  mov        eax, edi
  0x35a6e565c  mov        dword ptr [rbp - 0x20], 0x10
  0x35a6e5663  or         ah, 2
  0x35a6e5666  mov        dword ptr [rbp - 0x28], eax
  0x35a6e5669  jmp        0x35a6e503c
  0x35a6e566e  test       dx, dx
  0x35a6e5671  je         0x35a6e5754
  0x35a6e5677  mov        ecx, edx
  0x35a6e5679  jmp        0x35a6e5582
  0x35a6e567e  nop        
  0x35a6e5680  movsx      rcx, cx
  0x35a6e5684  jmp        0x35a6e501b
  0x35a6e5689  mov        qword ptr [rcx], rdx
  0x35a6e568c  jmp        0x35a6e5250
  0x35a6e5691  sub        ecx, 0x30
  0x35a6e5694  movzx      edx, byte ptr [rsi + 1]
  0x35a6e5698  mov        rsi, r12
  0x35a6e569b  mov        dword ptr [r10], ecx
  0x35a6e569e  jmp        0x35a6e4f47
  0x35a6e56a3  movzx      edx, byte ptr [rsi + 1]
  0x35a6e56a7  mov        dword ptr [rbp - 0x20], 0
  0x35a6e56ae  mov        rsi, r12
  0x35a6e56b1  lea        r10, [rbp - 0x20]
  0x35a6e56b5  mov        ebx, 2
  0x35a6e56ba  jmp        0x35a6e4f47
  0x35a6e56bf  mov        rax, qword ptr [r14]
  0x35a6e56c2  jmp        0x35a6e4fa8
  0x35a6e56c7  movzx      edx, byte ptr [rsi + 2]
  0x35a6e56cb  mov        r11d, 5
  0x35a6e56d1  add        rsi, 2
  0x35a6e56d5  mov        ebx, 4
  0x35a6e56da  jmp        0x35a6e4f47
  0x35a6e56df  mov        byte ptr [rcx], dl
  0x35a6e56e1  jmp        0x35a6e5250
  0x35a6e56e6  mov        rcx, r14
  0x35a6e56e9  call       0x35a6e8780
  0x35a6e56ee  lea        r8, [rbp - 0x30]
  0x35a6e56f2  mov        rcx, r14
  0x35a6e56f5  mov        edx, eax
  0x35a6e56f7  call       0x35a6e3370
  0x35a6e56fc  jmp        0x35a6e55d6
  0x35a6e5701  lea        rdx, [rbp - 0x30]
  0x35a6e5705  mov        rcx, rax
  0x35a6e5708  call       0x35a6e3c70
  0x35a6e570d  jmp        0x35a6e5250
  0x35a6e5712  mov        rcx, qword ptr [r14]
  0x35a6e5715  jmp        0x35a6e501b
  0x35a6e571a  cmp        byte ptr [rsi + 2], 0x34
  0x35a6e571e  jne        0x35a6e510a
  0x35a6e5724  movzx      edx, byte ptr [rsi + 3]
  0x35a6e5728  mov        r11d, 3
  0x35a6e572e  add        rsi, 3
  0x35a6e5732  mov        ebx, 4
  0x35a6e5737  jmp        0x35a6e4f47
  0x35a6e573c  movzx      edx, byte ptr [rsi + 2]
  0x35a6e5740  mov        r11d, 3
  0x35a6e5746  add        rsi, 2
  0x35a6e574a  mov        ebx, 4
  0x35a6e574f  jmp        0x35a6e4f47
  0x35a6e5754  test       rax, rax
  0x35a6e5757  mov        ecx, 0xfffffc05
  0x35a6e575c  cmovne     edx, ecx
  0x35a6e575f  jmp        0x35a6e5588
  0x35a6e5764  mov        word ptr [rcx], dx
  0x35a6e5767  jmp        0x35a6e5250
  0x35a6e576c  mov        eax, dword ptr [r14]
  0x35a6e576f  jmp        0x35a6e4fa8
  0x35a6e5774  test       ebx, ebx
  0x35a6e5776  jne        0x35a6e579f
  0x35a6e5778  or         dword ptr [rbp - 0x28], 0x400
  0x35a6e577f  neg        dword ptr [rbp - 0x24]
  0x35a6e5782  jmp        0x35a6e550d
  0x35a6e5787  movzx      edx, byte ptr [rsi + 3]
  0x35a6e578b  mov        r11d, 2
  0x35a6e5791  add        rsi, 3
  0x35a6e5795  mov        ebx, 4
  0x35a6e579a  jmp        0x35a6e4f47
  0x35a6e579f  movzx      edx, byte ptr [rsi + 1]
  0x35a6e57a3  mov        r14, rcx
  0x35a6e57a6  mov        rsi, r12
  0x35a6e57a9  xor        r10d, r10d
  0x35a6e57ac  mov        dword ptr [rbp - 0x20], 0xffffffff
  0x35a6e57b3  mov        ebx, 2
  0x35a6e57b8  jmp        0x35a6e4f47
  0x35a6e57bd  mov        ecx, r11d
  0x35a6e57c0  lea        r8, [rbp - 0x30]
  0x35a6e57c4  lea        rdx, [rip + 0x4b4f]
  0x35a6e57cb  and        ecx, 0x8000
  0x35a6e57d1  call       0x35a6e36b0
  0x35a6e57d6  jmp        0x35a6e5250
  0x35a6e57db  nop        
  0x35a6e57dc  nop        
  0x35a6e57dd  nop        
  0x35a6e57de  nop        
  0x35a6e57df  nop        
  0x35a6e57e0  push       rbp
  0x35a6e57e1  push       rbx
  0x35a6e57e2  sub        rsp, 0x28
  0x35a6e57e6  lea        rbp, [rsp + 0x20]
  0x35a6e57eb  xor        ebx, ebx
  0x35a6e57ed  cmp        ecx, 0x1b
  0x35a6e57f0  jle        0x35a6e580c
  0x35a6e57f2  mov        eax, 4
  0x35a6e57f7  nop        word ptr [rax + rax]
  0x35a6e5800  add        eax, eax
  0x35a6e5802  add        ebx, 1
  0x35a6e5805  lea        edx, [rax + 0x17]
  0x35a6e5808  cmp        edx, ecx
  0x35a6e580a  jl         0x35a6e5800
  0x35a6e580c  mov        ecx, ebx
  0x35a6e580e  call       0x35a6e7300
  0x35a6e5813  mov        dword ptr [rax], ebx
  0x35a6e5815  add        rax, 4
  0x35a6e5819  add        rsp, 0x28
  0x35a6e581d  pop        rbx
  0x35a6e581e  pop        rbp
  0x35a6e581f  ret        
  0x35a6e5820  push       rbp
  0x35a6e5821  push       rdi
  0x35a6e5822  push       rsi
  0x35a6e5823  push       rbx
  0x35a6e5824  sub        rsp, 0x28
  0x35a6e5828  lea        rbp, [rsp + 0x20]
  0x35a6e582d  mov        rdi, rcx
  0x35a6e5830  mov        rsi, rdx
  0x35a6e5833  cmp        r8d, 0x1b
  0x35a6e5837  jle        0x35a6e5898
  0x35a6e5839  mov        eax, 4
  0x35a6e583e  xor        ebx, ebx
  0x35a6e5840  add        eax, eax
  0x35a6e5842  add        ebx, 1
  0x35a6e5845  lea        edx, [rax + 0x17]
  0x35a6e5848  cmp        r8d, edx
  0x35a6e584b  jg         0x35a6e5840
  0x35a6e584d  mov        ecx, ebx
  0x35a6e584f  call       0x35a6e7300
  0x35a6e5854  lea        rdx, [rdi + 1]
  0x35a6e5858  mov        dword ptr [rax], ebx
  0x35a6e585a  movzx      ecx, byte ptr [rdi]
  0x35a6e585d  lea        r8, [rax + 4]
  0x35a6e5861  mov        byte ptr [rax + 4], cl
  0x35a6e5864  mov        rax, r8
  0x35a6e5867  test       cl, cl
  0x35a6e5869  je         0x35a6e5881
  0x35a6e586b  nop        dword ptr [rax + rax]
  0x35a6e5870  movzx      ecx, byte ptr [rdx]
  0x35a6e5873  add        rax, 1
  0x35a6e5877  add        rdx, 1
  0x35a6e587b  mov        byte ptr [rax], cl
  0x35a6e587d  test       cl, cl
  0x35a6e587f  jne        0x35a6e5870
  0x35a6e5881  test       rsi, rsi
  0x35a6e5884  je         0x35a6e5889
  0x35a6e5886  mov        qword ptr [rsi], rax
  0x35a6e5889  mov        rax, r8
  0x35a6e588c  add        rsp, 0x28
  0x35a6e5890  pop        rbx
  0x35a6e5891  pop        rsi
  0x35a6e5892  pop        rdi
  0x35a6e5893  pop        rbp
  0x35a6e5894  ret        
  0x35a6e5895  nop        dword ptr [rax]
  0x35a6e5898  xor        ebx, ebx
  0x35a6e589a  jmp        0x35a6e584d
  0x35a6e589c  nop        dword ptr [rax]
  0x35a6e58a0  mov        edx, 1
  0x35a6e58a5  mov        rax, rcx
  0x35a6e58a8  mov        ecx, dword ptr [rcx - 4]
  0x35a6e58ab  shl        edx, cl
  0x35a6e58ad  movd       xmm0, ecx
  0x35a6e58b1  lea        rcx, [rax - 4]
  0x35a6e58b5  movd       xmm1, edx
  0x35a6e58b9  punpckldq  xmm0, xmm1
  0x35a6e58bd  movq       qword ptr [rax + 4], xmm0
  0x35a6e58c2  jmp        0x35a6e7400
  0x35a6e58c7  nop        word ptr [rax + rax]
  0x35a6e58d0  push       rbp
  0x35a6e58d1  push       r15
  0x35a6e58d3  push       r14
  0x35a6e58d5  push       r13
  0x35a6e58d7  push       r12
  0x35a6e58d9  push       rdi
  0x35a6e58da  push       rsi
  0x35a6e58db  push       rbx
  0x35a6e58dc  sub        rsp, 0x38
  0x35a6e58e0  lea        rbp, [rsp + 0x30]
  0x35a6e58e5  xor        eax, eax
  0x35a6e58e7  mov        esi, dword ptr [rdx + 0x14]
  0x35a6e58ea  mov        r13, rcx
  0x35a6e58ed  mov        r11, rdx
  0x35a6e58f0  cmp        dword ptr [rcx + 0x14], esi
  0x35a6e58f3  jl         0x35a6e59e3
  0x35a6e58f9  sub        esi, 1
  0x35a6e58fc  lea        rbx, [rdx + 0x18]
  0x35a6e5900  lea        r12, [rcx + 0x18]
  0x35a6e5904  xor        edx, edx
  0x35a6e5906  movsxd     r10, esi
  0x35a6e5909  shl        r10, 2
  0x35a6e590d  lea        rdi, [rbx + r10]
  0x35a6e5911  add        r10, r12
  0x35a6e5914  mov        eax, dword ptr [rdi]
  0x35a6e5916  mov        r8d, dword ptr [r10]
  0x35a6e5919  lea        ecx, [rax + 1]
  0x35a6e591c  mov        eax, r8d
  0x35a6e591f  div        ecx
  0x35a6e5921  mov        dword ptr [rbp - 8], eax
  0x35a6e5924  mov        dword ptr [rbp - 4], eax
  0x35a6e5927  cmp        r8d, ecx
  0x35a6e592a  jb         0x35a6e5986
  0x35a6e592c  mov        r15d, eax
  0x35a6e592f  mov        r9, rbx
  0x35a6e5932  mov        r8, r12
  0x35a6e5935  xor        r14d, r14d
  0x35a6e5938  xor        ecx, ecx
  0x35a6e593a  nop        word ptr [rax + rax]
  0x35a6e5940  mov        eax, dword ptr [r9]
  0x35a6e5943  mov        edx, dword ptr [r8]
  0x35a6e5946  add        r9, 4
  0x35a6e594a  add        r8, 4
  0x35a6e594e  imul       rax, r15
  0x35a6e5952  add        rax, r14
  0x35a6e5955  mov        r14, rax
  0x35a6e5958  mov        eax, eax
  0x35a6e595a  sub        rdx, rax
  0x35a6e595d  shr        r14, 0x20
  0x35a6e5961  mov        rax, rdx
  0x35a6e5964  sub        rax, rcx
  0x35a6e5967  mov        rcx, rax
  0x35a6e596a  mov        dword ptr [r8 - 4], eax
  0x35a6e596e  shr        rcx, 0x20
  0x35a6e5972  and        ecx, 1
  0x35a6e5975  cmp        rdi, r9
  0x35a6e5978  jae        0x35a6e5940
  0x35a6e597a  mov        r9d, dword ptr [r10]
  0x35a6e597d  test       r9d, r9d
  0x35a6e5980  je         0x35a6e5a2b
  0x35a6e5986  mov        rdx, r11
  0x35a6e5989  mov        rcx, r13
  0x35a6e598c  call       0x35a6e7a20
  0x35a6e5991  test       eax, eax
  0x35a6e5993  js         0x35a6e59e0
  0x35a6e5995  mov        rcx, r12
  0x35a6e5998  xor        edx, edx
  0x35a6e599a  nop        word ptr [rax + rax]
  0x35a6e59a0  mov        eax, dword ptr [rcx]
  0x35a6e59a2  mov        r8d, dword ptr [rbx]
  0x35a6e59a5  add        rbx, 4
  0x35a6e59a9  add        rcx, 4
  0x35a6e59ad  sub        rax, r8
  0x35a6e59b0  sub        rax, rdx
  0x35a6e59b3  mov        rdx, rax
  0x35a6e59b6  mov        dword ptr [rcx - 4], eax
  0x35a6e59b9  shr        rdx, 0x20
  0x35a6e59bd  and        edx, 1
  0x35a6e59c0  cmp        rdi, rbx
  0x35a6e59c3  jae        0x35a6e59a0
  0x35a6e59c5  movsxd     rax, esi
  0x35a6e59c8  lea        rax, [r12 + rax*4]
  0x35a6e59cc  mov        ecx, dword ptr [rax]
  0x35a6e59ce  test       ecx, ecx
  0x35a6e59d0  je         0x35a6e5a01
  0x35a6e59d2  mov        eax, dword ptr [rbp - 8]
  0x35a6e59d5  add        eax, 1
  0x35a6e59d8  mov        dword ptr [rbp - 4], eax
  0x35a6e59db  nop        dword ptr [rax + rax]
  0x35a6e59e0  mov        eax, dword ptr [rbp - 4]
  0x35a6e59e3  add        rsp, 0x38
  0x35a6e59e7  pop        rbx
  0x35a6e59e8  pop        rsi
  0x35a6e59e9  pop        rdi
  0x35a6e59ea  pop        r12
  0x35a6e59ec  pop        r13
  0x35a6e59ee  pop        r14
  0x35a6e59f0  pop        r15
  0x35a6e59f2  pop        rbp
  0x35a6e59f3  ret        
  0x35a6e59f4  nop        dword ptr [rax]
  0x35a6e59f8  mov        edx, dword ptr [rax]
  0x35a6e59fa  test       edx, edx
  0x35a6e59fc  jne        0x35a6e5a0a
  0x35a6e59fe  sub        esi, 1
  0x35a6e5a01  sub        rax, 4
  0x35a6e5a05  cmp        r12, rax
  0x35a6e5a08  jb         0x35a6e59f8
  0x35a6e5a0a  mov        eax, dword ptr [rbp - 8]
  0x35a6e5a0d  mov        dword ptr [r13 + 0x14], esi
  0x35a6e5a11  add        eax, 1
  0x35a6e5a14  mov        dword ptr [rbp - 4], eax
  0x35a6e5a17  jmp        0x35a6e59e0
  0x35a6e5a19  nop        dword ptr [rax]
  0x35a6e5a20  mov        r8d, dword ptr [r10]
  0x35a6e5a23  test       r8d, r8d
  0x35a6e5a26  jne        0x35a6e5a34
  0x35a6e5a28  sub        esi, 1
  0x35a6e5a2b  sub        r10, 4
  0x35a6e5a2f  cmp        r12, r10
  0x35a6e5a32  jb         0x35a6e5a20
  0x35a6e5a34  mov        dword ptr [r13 + 0x14], esi
  0x35a6e5a38  mov        rdx, r11
  0x35a6e5a3b  mov        rcx, r13
  0x35a6e5a3e  call       0x35a6e7a20
  0x35a6e5a43  test       eax, eax
  0x35a6e5a45  jns        0x35a6e5995
  0x35a6e5a4b  jmp        0x35a6e59e0
  0x35a6e5a4d  nop        
  0x35a6e5a4e  nop        
  0x35a6e5a4f  nop        
  0x35a6e5a50  push       rbp
  0x35a6e5a51  push       r15
  0x35a6e5a53  push       r14
  0x35a6e5a55  push       r13
  0x35a6e5a57  push       r12
  0x35a6e5a59  push       rdi
  0x35a6e5a5a  push       rsi
  0x35a6e5a5b  push       rbx
  0x35a6e5a5c  sub        rsp, 0xc8
  0x35a6e5a63  lea        rbp, [rsp + 0xc0]
  0x35a6e5a6b  mov        eax, dword ptr [rbp + 0x70]
  0x35a6e5a6e  mov        edi, dword ptr [r9]
  0x35a6e5a71  mov        dword ptr [rbp - 0x28], eax
  0x35a6e5a74  mov        eax, dword ptr [rbp + 0x78]
  0x35a6e5a77  mov        r12, rcx
  0x35a6e5a7a  mov        rsi, r8
  0x35a6e5a7d  mov        dword ptr [rbp - 0x30], edx
  0x35a6e5a80  mov        r15, r9
  0x35a6e5a83  mov        dword ptr [rbp - 0x34], eax
  0x35a6e5a86  mov        rax, qword ptr [rbp + 0x80]
  0x35a6e5a8d  mov        qword ptr [rbp - 0x18], rax
  0x35a6e5a91  mov        rax, qword ptr [rbp + 0x88]
  0x35a6e5a98  mov        qword ptr [rbp - 0x20], rax
  0x35a6e5a9c  mov        eax, edi
  0x35a6e5a9e  and        eax, 0xffffffcf
  0x35a6e5aa1  mov        dword ptr [r9], eax
  0x35a6e5aa4  mov        eax, edi
  0x35a6e5aa6  and        eax, 7
  0x35a6e5aa9  cmp        eax, 3
  0x35a6e5aac  je         0x35a6e5d78
  0x35a6e5ab2  mov        ebx, edi
  0x35a6e5ab4  and        ebx, 4
  0x35a6e5ab7  mov        dword ptr [rbp - 0x40], ebx
  0x35a6e5aba  jne        0x35a6e5cf0
  0x35a6e5ac0  test       eax, eax
  0x35a6e5ac2  je         0x35a6e5d38
  0x35a6e5ac8  mov        r13d, dword ptr [rcx]
  0x35a6e5acb  mov        eax, 0x20
  0x35a6e5ad0  xor        ecx, ecx
  0x35a6e5ad2  cmp        r13d, 0x20
  0x35a6e5ad6  jle        0x35a6e5aea
  0x35a6e5ad8  nop        dword ptr [rax + rax]
  0x35a6e5ae0  add        eax, eax
  0x35a6e5ae2  add        ecx, 1
  0x35a6e5ae5  cmp        r13d, eax
  0x35a6e5ae8  jg         0x35a6e5ae0
  0x35a6e5aea  call       0x35a6e7300
  0x35a6e5aef  lea        r8d, [r13 - 1]
  0x35a6e5af3  sar        r8d, 5
  0x35a6e5af7  mov        rbx, rax
  0x35a6e5afa  lea        rdx, [rax + 0x18]
  0x35a6e5afe  mov        rax, rsi
  0x35a6e5b01  movsxd     r8, r8d
  0x35a6e5b04  lea        rcx, [rsi + r8*4]
  0x35a6e5b08  nop        dword ptr [rax + rax]
  0x35a6e5b10  mov        r9d, dword ptr [rax]
  0x35a6e5b13  add        rax, 4
  0x35a6e5b17  add        rdx, 4
  0x35a6e5b1b  mov        dword ptr [rdx - 4], r9d
  0x35a6e5b1f  cmp        rcx, rax
  0x35a6e5b22  jae        0x35a6e5b10
  0x35a6e5b24  lea        rdx, [rsi + 1]
  0x35a6e5b28  add        rcx, 1
  0x35a6e5b2c  lea        rax, [r8*4 + 4]
  0x35a6e5b34  cmp        rcx, rdx
  0x35a6e5b37  mov        edx, 4
  0x35a6e5b3c  cmovb      rax, rdx
  0x35a6e5b40  mov        r14, rax
  0x35a6e5b43  add        rax, rbx
  0x35a6e5b46  sar        r14, 2
  0x35a6e5b4a  jmp        0x35a6e5b5d
  0x35a6e5b4c  nop        dword ptr [rax]
  0x35a6e5b50  sub        rax, 4
  0x35a6e5b54  test       r14d, r14d
  0x35a6e5b57  je         0x35a6e5da0
  0x35a6e5b5d  mov        r11d, dword ptr [rax + 0x14]
  0x35a6e5b61  mov        edx, r14d
  0x35a6e5b64  sub        r14d, 1
  0x35a6e5b68  test       r11d, r11d
  0x35a6e5b6b  je         0x35a6e5b50
  0x35a6e5b6d  movsxd     r14, r14d
  0x35a6e5b70  mov        dword ptr [rbx + 0x14], edx
  0x35a6e5b73  shl        edx, 5
  0x35a6e5b76  bsr        eax, dword ptr [rbx + r14*4 + 0x18]
  0x35a6e5b7c  xor        eax, 0x1f
  0x35a6e5b7f  sub        edx, eax
  0x35a6e5b81  mov        r14d, edx
  0x35a6e5b84  mov        rcx, rbx
  0x35a6e5b87  call       0x35a6e7180
  0x35a6e5b8c  mov        ecx, dword ptr [rbp - 0x30]
  0x35a6e5b8f  mov        dword ptr [rbp - 4], eax
  0x35a6e5b92  mov        dword ptr [rbp - 0x58], ecx
  0x35a6e5b95  test       eax, eax
  0x35a6e5b97  jne        0x35a6e5db0
  0x35a6e5b9d  mov        r10d, dword ptr [rbx + 0x14]
  0x35a6e5ba1  test       r10d, r10d
  0x35a6e5ba4  je         0x35a6e5d30
  0x35a6e5baa  lea        rdx, [rbp - 4]
  0x35a6e5bae  mov        rcx, rbx
  0x35a6e5bb1  call       0x35a6e7c40
  0x35a6e5bb6  mov        eax, dword ptr [rbp - 0x58]
  0x35a6e5bb9  pxor       xmm1, xmm1
  0x35a6e5bbd  movq       r10, xmm0
  0x35a6e5bc2  mov        rdx, r10
  0x35a6e5bc5  lea        r8d, [rax + r14]
  0x35a6e5bc9  mov        eax, r10d
  0x35a6e5bcc  shr        rdx, 0x20
  0x35a6e5bd0  lea        ecx, [r8 - 1]
  0x35a6e5bd4  and        edx, 0xfffff
  0x35a6e5bda  cvtsi2sd   xmm1, ecx
  0x35a6e5bde  mulsd      xmm1, qword ptr [rip + 0x48d2]
  0x35a6e5be6  or         edx, 0x3ff00000
  0x35a6e5bec  mov        r9, rdx
  0x35a6e5bef  shl        r9, 0x20
  0x35a6e5bf3  or         rax, r9
  0x35a6e5bf6  mov        r9d, 1
  0x35a6e5bfc  sub        r9d, r8d
  0x35a6e5bff  movq       xmm0, rax
  0x35a6e5c04  test       ecx, ecx
  0x35a6e5c06  subsd      xmm0, qword ptr [rip + 0x4892]
  0x35a6e5c0e  mulsd      xmm0, qword ptr [rip + 0x4892]
  0x35a6e5c16  cmovns     r9d, ecx
  0x35a6e5c1a  addsd      xmm0, qword ptr [rip + 0x488e]
  0x35a6e5c22  sub        r9d, 0x435
  0x35a6e5c29  addsd      xmm0, xmm1
  0x35a6e5c2d  test       r9d, r9d
  0x35a6e5c30  jle        0x35a6e5c47
  0x35a6e5c32  pxor       xmm1, xmm1
  0x35a6e5c36  cvtsi2sd   xmm1, r9d
  0x35a6e5c3b  mulsd      xmm1, qword ptr [rip + 0x487d]
  0x35a6e5c43  addsd      xmm0, xmm1
  0x35a6e5c47  pxor       xmm1, xmm1
  0x35a6e5c4b  cvttsd2si  r11d, xmm0
  0x35a6e5c50  comisd     xmm1, xmm0
  0x35a6e5c54  ja         0x35a6e6100
  0x35a6e5c5a  mov        r9d, ecx
  0x35a6e5c5d  mov        eax, eax
  0x35a6e5c5f  shl        r9d, 0x14
  0x35a6e5c63  add        edx, r9d
  0x35a6e5c66  mov        edx, edx
  0x35a6e5c68  shl        rdx, 0x20
  0x35a6e5c6c  or         rax, rdx
  0x35a6e5c6f  mov        qword ptr [rbp - 0x98], rax
  0x35a6e5c76  mov        r9, rax
  0x35a6e5c79  mov        r10, rax
  0x35a6e5c7c  mov        eax, r14d
  0x35a6e5c7f  sub        eax, ecx
  0x35a6e5c81  lea        edx, [rax - 1]
  0x35a6e5c84  mov        dword ptr [rbp - 0x50], edx
  0x35a6e5c87  cmp        r11d, 0x16
  0x35a6e5c8b  ja         0x35a6e5dd0
  0x35a6e5c91  mov        rcx, qword ptr [rip + 0x4a88]
  0x35a6e5c98  movsxd     rdx, r11d
  0x35a6e5c9b  movq       xmm5, r9
  0x35a6e5ca0  movsd      xmm0, qword ptr [rcx + rdx*8]
  0x35a6e5ca5  comisd     xmm0, xmm5
  0x35a6e5ca9  ja         0x35a6e6160
  0x35a6e5caf  mov        dword ptr [rbp - 0x7c], 0
  0x35a6e5cb6  mov        dword ptr [rbp - 0x68], 0
  0x35a6e5cbd  test       eax, eax
  0x35a6e5cbf  jg         0x35a6e5cd8
  0x35a6e5cc1  mov        edx, 1
  0x35a6e5cc6  mov        dword ptr [rbp - 0x50], 0
  0x35a6e5ccd  sub        edx, eax
  0x35a6e5ccf  mov        dword ptr [rbp - 0x68], edx
  0x35a6e5cd2  nop        word ptr [rax + rax]
  0x35a6e5cd8  add        dword ptr [rbp - 0x50], r11d
  0x35a6e5cdc  mov        dword ptr [rbp - 0x70], r11d
  0x35a6e5ce0  mov        dword ptr [rbp - 0x74], 0
  0x35a6e5ce7  jmp        0x35a6e5e14
  0x35a6e5cec  nop        dword ptr [rax]
  0x35a6e5cf0  xor        esi, esi
  0x35a6e5cf2  cmp        eax, 4
  0x35a6e5cf5  jne        0x35a6e5d5b
  0x35a6e5cf7  mov        rax, qword ptr [rbp - 0x18]
  0x35a6e5cfb  mov        rdx, qword ptr [rbp - 0x20]
  0x35a6e5cff  mov        r8d, 3
  0x35a6e5d05  lea        rcx, [rip + 0x478d]
  0x35a6e5d0c  mov        dword ptr [rax], 0xffff8000
  0x35a6e5d12  add        rsp, 0xc8
  0x35a6e5d19  pop        rbx
  0x35a6e5d1a  pop        rsi
  0x35a6e5d1b  pop        rdi
  0x35a6e5d1c  pop        r12
  0x35a6e5d1e  pop        r13
  0x35a6e5d20  pop        r14
  0x35a6e5d22  pop        r15
  0x35a6e5d24  pop        rbp
  0x35a6e5d25  jmp        0x35a6e5820
  0x35a6e5d2a  nop        word ptr [rax + rax]
  0x35a6e5d30  mov        rcx, rbx
  0x35a6e5d33  call       0x35a6e7400
  0x35a6e5d38  mov        rax, qword ptr [rbp - 0x18]
  0x35a6e5d3c  mov        rdx, qword ptr [rbp - 0x20]
  0x35a6e5d40  mov        r8d, 1
  0x35a6e5d46  lea        rcx, [rip + 0x4750]
  0x35a6e5d4d  mov        dword ptr [rax], 1
  0x35a6e5d53  call       0x35a6e5820
  0x35a6e5d58  mov        rsi, rax
  0x35a6e5d5b  mov        rax, rsi
  0x35a6e5d5e  add        rsp, 0xc8
  0x35a6e5d65  pop        rbx
  0x35a6e5d66  pop        rsi
  0x35a6e5d67  pop        rdi
  0x35a6e5d68  pop        r12
  0x35a6e5d6a  pop        r13
  0x35a6e5d6c  pop        r14
  0x35a6e5d6e  pop        r15
  0x35a6e5d70  pop        rbp
  0x35a6e5d71  ret        
  0x35a6e5d72  nop        word ptr [rax + rax]
  0x35a6e5d78  mov        rax, qword ptr [rbp - 0x18]
  0x35a6e5d7c  mov        rdx, qword ptr [rbp - 0x20]
  0x35a6e5d80  mov        r8d, 8
  0x35a6e5d86  lea        rcx, [rip + 0x4703]
  0x35a6e5d8d  mov        dword ptr [rax], 0xffff8000
  0x35a6e5d93  jmp        0x35a6e5d12
  0x35a6e5d98  nop        dword ptr [rax + rax]
  0x35a6e5da0  mov        dword ptr [rbx + 0x14], 0
  0x35a6e5da7  jmp        0x35a6e5b84
  0x35a6e5dac  nop        dword ptr [rax]
  0x35a6e5db0  mov        edx, eax
  0x35a6e5db2  mov        rcx, rbx
  0x35a6e5db5  call       0x35a6e7070
  0x35a6e5dba  mov        eax, dword ptr [rbp - 4]
  0x35a6e5dbd  mov        edx, dword ptr [rbp - 0x30]
  0x35a6e5dc0  add        edx, eax
  0x35a6e5dc2  sub        r14d, eax
  0x35a6e5dc5  mov        dword ptr [rbp - 0x58], edx
  0x35a6e5dc8  jmp        0x35a6e5b9d
  0x35a6e5dcd  nop        dword ptr [rax]
  0x35a6e5dd0  mov        dword ptr [rbp - 0x7c], 1
  0x35a6e5dd7  mov        r9d, dword ptr [rbp - 0x50]
  0x35a6e5ddb  mov        dword ptr [rbp - 0x68], 0
  0x35a6e5de2  test       r9d, r9d
  0x35a6e5de5  jns        0x35a6e5df8
  0x35a6e5de7  mov        edx, 1
  0x35a6e5dec  mov        dword ptr [rbp - 0x50], 0
  0x35a6e5df3  sub        edx, eax
  0x35a6e5df5  mov        dword ptr [rbp - 0x68], edx
  0x35a6e5df8  test       r11d, r11d
  0x35a6e5dfb  jns        0x35a6e5cd8
  0x35a6e5e01  mov        eax, r11d
  0x35a6e5e04  sub        dword ptr [rbp - 0x68], r11d
  0x35a6e5e08  neg        eax
  0x35a6e5e0a  mov        dword ptr [rbp - 0x70], r11d
  0x35a6e5e0e  xor        r11d, r11d
  0x35a6e5e11  mov        dword ptr [rbp - 0x74], eax
  0x35a6e5e14  mov        eax, dword ptr [rbp - 0x28]
  0x35a6e5e17  cmp        eax, 9
  0x35a6e5e1a  ja         0x35a6e6070
  0x35a6e5e20  cmp        eax, 5
  0x35a6e5e23  jg         0x35a6e6120
  0x35a6e5e29  add        r8d, 0x3fd
  0x35a6e5e30  xor        eax, eax
  0x35a6e5e32  cmp        r8d, 0x7f7
  0x35a6e5e39  setbe      al
  0x35a6e5e3c  mov        dword ptr [rbp - 0x8c], eax
  0x35a6e5e42  mov        eax, dword ptr [rbp - 0x28]
  0x35a6e5e45  cmp        eax, 4
  0x35a6e5e48  je         0x35a6e6494
  0x35a6e5e4e  cmp        eax, 5
  0x35a6e5e51  je         0x35a6e68af
  0x35a6e5e57  mov        dword ptr [rbp - 0x78], 0
  0x35a6e5e5e  cmp        eax, 2
  0x35a6e5e61  je         0x35a6e649b
  0x35a6e5e67  cmp        eax, 3
  0x35a6e5e6a  jne        0x35a6e6090
  0x35a6e5e70  mov        ecx, dword ptr [rbp - 0x34]
  0x35a6e5e73  mov        eax, dword ptr [rbp - 0x70]
  0x35a6e5e76  add        eax, ecx
  0x35a6e5e78  lea        edx, [rax + 1]
  0x35a6e5e7b  mov        dword ptr [rbp - 0x90], eax
  0x35a6e5e81  mov        eax, 1
  0x35a6e5e86  test       edx, edx
  0x35a6e5e88  mov        dword ptr [rbp - 0x48], edx
  0x35a6e5e8b  cmovg      eax, edx
  0x35a6e5e8e  mov        ecx, eax
  0x35a6e5e90  mov        qword ptr [rbp - 0x88], r10
  0x35a6e5e97  mov        dword ptr [rbp - 0x80], r11d
  0x35a6e5e9b  mov        dword ptr [rbp - 4], eax
  0x35a6e5e9e  call       0x35a6e57e0
  0x35a6e5ea3  mov        r11d, dword ptr [rbp - 0x80]
  0x35a6e5ea7  mov        r10, qword ptr [rbp - 0x88]
  0x35a6e5eae  mov        qword ptr [rbp - 0x60], rax
  0x35a6e5eb2  mov        eax, dword ptr [r12 + 0xc]
  0x35a6e5eb7  sub        eax, 1
  0x35a6e5eba  mov        dword ptr [rbp - 0x38], eax
  0x35a6e5ebd  je         0x35a6e5ee4
  0x35a6e5ebf  mov        ecx, dword ptr [rbp - 0x38]
  0x35a6e5ec2  mov        eax, 2
  0x35a6e5ec7  test       ecx, ecx
  0x35a6e5ec9  cmovns     eax, ecx
  0x35a6e5ecc  and        edi, 8
  0x35a6e5ecf  mov        dword ptr [rbp - 0x38], eax
  0x35a6e5ed2  mov        edx, eax
  0x35a6e5ed4  je         0x35a6e62b0
  0x35a6e5eda  mov        eax, 3
  0x35a6e5edf  sub        eax, edx
  0x35a6e5ee1  mov        dword ptr [rbp - 0x38], eax
  0x35a6e5ee4  mov        ecx, dword ptr [rbp - 0x48]
  0x35a6e5ee7  movzx      edi, byte ptr [rbp - 0x8c]
  0x35a6e5eee  cmp        ecx, 0xe
  0x35a6e5ef1  setbe      al
  0x35a6e5ef4  and        dil, al
  0x35a6e5ef7  je         0x35a6e62b0
  0x35a6e5efd  mov        eax, dword ptr [rbp - 0x70]
  0x35a6e5f00  or         eax, dword ptr [rbp - 0x38]
  0x35a6e5f03  jne        0x35a6e62b0
  0x35a6e5f09  mov        r8d, dword ptr [rbp - 0x7c]
  0x35a6e5f0d  mov        dword ptr [rbp - 4], 0
  0x35a6e5f14  movsd      xmm0, qword ptr [rbp - 0x98]
  0x35a6e5f1c  test       r8d, r8d
  0x35a6e5f1f  je         0x35a6e5f33
  0x35a6e5f21  movsd      xmm4, qword ptr [rip + 0x45a7]
  0x35a6e5f29  comisd     xmm4, xmm0
  0x35a6e5f2d  ja         0x35a6e6c9d
  0x35a6e5f33  movupd     xmm1, xmm0
  0x35a6e5f37  mov        ecx, dword ptr [rbp - 0x48]
  0x35a6e5f3a  addsd      xmm1, xmm0
  0x35a6e5f3e  addsd      xmm1, qword ptr [rip + 0x45a2]
  0x35a6e5f46  movq       rdx, xmm1
  0x35a6e5f4b  mov        rax, rdx
  0x35a6e5f4e  mov        edx, edx
  0x35a6e5f50  shr        rax, 0x20
  0x35a6e5f54  sub        eax, 0x3400000
  0x35a6e5f59  shl        rax, 0x20
  0x35a6e5f5d  or         rdx, rax
  0x35a6e5f60  test       ecx, ecx
  0x35a6e5f62  je         0x35a6e627f
  0x35a6e5f68  mov        eax, dword ptr [rbp - 0x48]
  0x35a6e5f6b  xor        r8d, r8d
  0x35a6e5f6e  mov        rcx, qword ptr [rip + 0x47ab]
  0x35a6e5f75  movq       xmm2, rdx
  0x35a6e5f7a  lea        edx, [rax - 1]
  0x35a6e5f7d  movsxd     rdx, edx
  0x35a6e5f80  movsd      xmm3, qword ptr [rcx + rdx*8]
  0x35a6e5f85  mov        edx, dword ptr [rbp - 0x78]
  0x35a6e5f88  test       edx, edx
  0x35a6e5f8a  je         0x35a6e68bb
  0x35a6e5f90  movsd      xmm1, qword ptr [rip + 0x4578]
  0x35a6e5f98  cvttsd2si  ecx, xmm0
  0x35a6e5f9c  mov        rdi, qword ptr [rbp - 0x60]
  0x35a6e5fa0  divsd      xmm1, xmm3
  0x35a6e5fa4  lea        rdx, [rdi + 1]
  0x35a6e5fa8  subsd      xmm1, xmm2
  0x35a6e5fac  pxor       xmm2, xmm2
  0x35a6e5fb0  cvtsi2sd   xmm2, ecx
  0x35a6e5fb4  add        ecx, 0x30
  0x35a6e5fb7  mov        byte ptr [rdi], cl
  0x35a6e5fb9  subsd      xmm0, xmm2
  0x35a6e5fbd  comisd     xmm1, xmm0
  0x35a6e5fc1  ja         0x35a6e6e9d
  0x35a6e5fc7  movsd      xmm4, qword ptr [rip + 0x4501]
  0x35a6e5fcf  movsd      xmm3, qword ptr [rip + 0x4501]
  0x35a6e5fd7  jmp        0x35a6e601d
  0x35a6e5fd9  nop        dword ptr [rax]
  0x35a6e5fe0  mov        edi, dword ptr [rbp - 4]
  0x35a6e5fe3  lea        ecx, [rdi + 1]
  0x35a6e5fe6  mov        dword ptr [rbp - 4], ecx
  0x35a6e5fe9  cmp        ecx, eax
  0x35a6e5feb  jge        0x35a6e62a8
  0x35a6e5ff1  mulsd      xmm0, xmm3
  0x35a6e5ff5  pxor       xmm2, xmm2
  0x35a6e5ff9  add        rdx, 1
  0x35a6e5ffd  mulsd      xmm1, xmm3
  0x35a6e6001  cvttsd2si  ecx, xmm0
  0x35a6e6005  cvtsi2sd   xmm2, ecx
  0x35a6e6009  add        ecx, 0x30
  0x35a6e600c  mov        byte ptr [rdx - 1], cl
  0x35a6e600f  subsd      xmm0, xmm2
  0x35a6e6013  comisd     xmm1, xmm0
  0x35a6e6017  ja         0x35a6e6e9d
  0x35a6e601d  movupd     xmm2, xmm4
  0x35a6e6021  subsd      xmm2, xmm0
  0x35a6e6025  comisd     xmm1, xmm2
  0x35a6e6029  jbe        0x35a6e5fe0
  0x35a6e602b  movzx      ecx, byte ptr [rdx - 1]
  0x35a6e602f  mov        rsi, qword ptr [rbp - 0x60]
  0x35a6e6033  jmp        0x35a6e6048
  0x35a6e6035  nop        dword ptr [rax]
  0x35a6e6038  cmp        rax, rsi
  0x35a6e603b  je         0x35a6e6d97
  0x35a6e6041  movzx      ecx, byte ptr [rax - 1]
  0x35a6e6045  mov        rdx, rax
  0x35a6e6048  lea        rax, [rdx - 1]
  0x35a6e604c  cmp        cl, 0x39
  0x35a6e604f  je         0x35a6e6038
  0x35a6e6051  mov        qword ptr [rbp - 0x60], rdx
  0x35a6e6055  add        ecx, 1
  0x35a6e6058  mov        byte ptr [rax], cl
  0x35a6e605a  lea        eax, [r8 + 1]
  0x35a6e605e  mov        dword ptr [rbp - 0x34], eax
  0x35a6e6061  mov        dword ptr [rbp - 0x40], 0x20
  0x35a6e6068  jmp        0x35a6e6226
  0x35a6e606d  nop        dword ptr [rax]
  0x35a6e6070  add        r8d, 0x3fd
  0x35a6e6077  xor        eax, eax
  0x35a6e6079  mov        dword ptr [rbp - 0x28], 0
  0x35a6e6080  cmp        r8d, 0x7f7
  0x35a6e6087  setbe      al
  0x35a6e608a  mov        dword ptr [rbp - 0x8c], eax
  0x35a6e6090  pxor       xmm0, xmm0
  0x35a6e6094  mov        qword ptr [rbp - 0x48], r10
  0x35a6e6098  cvtsi2sd   xmm0, r13d
  0x35a6e609d  mulsd      xmm0, qword ptr [rip + 0x4423]
  0x35a6e60a5  mov        dword ptr [rbp - 0x34], r11d
  0x35a6e60a9  cvttsd2si  ecx, xmm0
  0x35a6e60ad  add        ecx, 3
  0x35a6e60b0  mov        dword ptr [rbp - 4], ecx
  0x35a6e60b3  call       0x35a6e57e0
  0x35a6e60b8  mov        r11d, dword ptr [rbp - 0x34]
  0x35a6e60bc  mov        r10, qword ptr [rbp - 0x48]
  0x35a6e60c0  mov        qword ptr [rbp - 0x60], rax
  0x35a6e60c4  mov        eax, dword ptr [r12 + 0xc]
  0x35a6e60c9  sub        eax, 1
  0x35a6e60cc  mov        dword ptr [rbp - 0x38], eax
  0x35a6e60cf  je         0x35a6e6170
  0x35a6e60d5  mov        dword ptr [rbp - 0x34], 0
  0x35a6e60dc  mov        dword ptr [rbp - 0x78], 1
  0x35a6e60e3  mov        dword ptr [rbp - 0x90], 0xffffffff
  0x35a6e60ed  mov        dword ptr [rbp - 0x48], 0xffffffff
  0x35a6e60f4  jmp        0x35a6e5ebf
  0x35a6e60f9  nop        dword ptr [rax]
  0x35a6e6100  pxor       xmm1, xmm1
  0x35a6e6104  cvtsi2sd   xmm1, r11d
  0x35a6e6109  ucomisd    xmm1, xmm0
  0x35a6e610d  jp         0x35a6e6115
  0x35a6e610f  je         0x35a6e5c5a
  0x35a6e6115  sub        r11d, 1
  0x35a6e6119  jmp        0x35a6e5c5a
  0x35a6e611e  nop        
  0x35a6e6120  mov        dword ptr [rbp - 0x8c], 0
  0x35a6e612a  sub        eax, 4
  0x35a6e612d  mov        dword ptr [rbp - 0x28], eax
  0x35a6e6130  cmp        eax, 4
  0x35a6e6133  je         0x35a6e6494
  0x35a6e6139  cmp        eax, 5
  0x35a6e613c  je         0x35a6e68af
  0x35a6e6142  mov        dword ptr [rbp - 0x78], 0
  0x35a6e6149  cmp        eax, 2
  0x35a6e614c  je         0x35a6e649b
  0x35a6e6152  mov        dword ptr [rbp - 0x28], 3
  0x35a6e6159  jmp        0x35a6e5e70
  0x35a6e615e  nop        
  0x35a6e6160  mov        dword ptr [rbp - 0x7c], 0
  0x35a6e6167  sub        r11d, 1
  0x35a6e616b  jmp        0x35a6e5dd7
  0x35a6e6170  mov        eax, dword ptr [rbp - 0x58]
  0x35a6e6173  mov        dword ptr [rbp - 0x34], 0
  0x35a6e617a  test       eax, eax
  0x35a6e617c  js         0x35a6e6e1e
  0x35a6e6182  mov        dword ptr [rbp - 0x78], 1
  0x35a6e6189  mov        dword ptr [rbp - 0x90], 0xffffffff
  0x35a6e6193  mov        dword ptr [rbp - 0x48], 0xffffffff
  0x35a6e619a  nop        word ptr [rax + rax]
  0x35a6e61a0  mov        eax, dword ptr [rbp - 0x70]
  0x35a6e61a3  cmp        dword ptr [r12 + 0x14], eax
  0x35a6e61a8  jl         0x35a6e62bb
  0x35a6e61ae  mov        rdx, qword ptr [rip + 0x456b]
  0x35a6e61b5  mov        r12d, dword ptr [rbp - 0x34]
  0x35a6e61b9  cdqe       
  0x35a6e61bb  mov        rsi, rax
  0x35a6e61be  movsd      xmm2, qword ptr [rdx + rax*8]
  0x35a6e61c3  test       r12d, r12d
  0x35a6e61c6  jns        0x35a6e697c
  0x35a6e61cc  mov        eax, dword ptr [rbp - 0x48]
  0x35a6e61cf  test       eax, eax
  0x35a6e61d1  jg         0x35a6e697c
  0x35a6e61d7  jne        0x35a6e64bb
  0x35a6e61dd  mulsd      xmm2, qword ptr [rip + 0x430b]
  0x35a6e61e5  comisd     xmm2, xmmword ptr [rbp - 0x98]
  0x35a6e61ed  jae        0x35a6e64bb
  0x35a6e61f3  add        esi, 2
  0x35a6e61f6  xor        r9d, r9d
  0x35a6e61f9  xor        edi, edi
  0x35a6e61fb  mov        dword ptr [rbp - 0x34], esi
  0x35a6e61fe  mov        rsi, qword ptr [rbp - 0x60]
  0x35a6e6202  add        qword ptr [rbp - 0x60], 1
  0x35a6e6207  mov        byte ptr [rsi], 0x31
  0x35a6e620a  mov        dword ptr [rbp - 0x40], 0x20
  0x35a6e6211  mov        rcx, r9
  0x35a6e6214  call       0x35a6e7400
  0x35a6e6219  test       rdi, rdi
  0x35a6e621c  je         0x35a6e6226
  0x35a6e621e  mov        rcx, rdi
  0x35a6e6221  call       0x35a6e7400
  0x35a6e6226  mov        rcx, rbx
  0x35a6e6229  call       0x35a6e7400
  0x35a6e622e  mov        ebx, dword ptr [rbp - 0x34]
  0x35a6e6231  mov        rax, qword ptr [rbp - 0x60]
  0x35a6e6235  mov        rdi, qword ptr [rbp - 0x18]
  0x35a6e6239  mov        byte ptr [rax], 0
  0x35a6e623c  mov        dword ptr [rdi], ebx
  0x35a6e623e  mov        rbx, qword ptr [rbp - 0x20]
  0x35a6e6242  test       rbx, rbx
  0x35a6e6245  je         0x35a6e624a
  0x35a6e6247  mov        qword ptr [rbx], rax
  0x35a6e624a  mov        eax, dword ptr [rbp - 0x40]
  0x35a6e624d  or         dword ptr [r15], eax
  0x35a6e6250  jmp        0x35a6e5d5b
  0x35a6e6255  movupd     xmm1, xmm0
  0x35a6e6259  addsd      xmm1, xmm0
  0x35a6e625d  addsd      xmm1, qword ptr [rip + 0x4283]
  0x35a6e6265  movq       rdx, xmm1
  0x35a6e626a  mov        rax, rdx
  0x35a6e626d  mov        edx, edx
  0x35a6e626f  shr        rax, 0x20
  0x35a6e6273  sub        eax, 0x3400000
  0x35a6e6278  shl        rax, 0x20
  0x35a6e627c  or         rdx, rax
  0x35a6e627f  subsd      xmm0, qword ptr [rip + 0x4269]
  0x35a6e6287  movq       xmm1, rdx
  0x35a6e628c  comisd     xmm0, xmm1
  0x35a6e6290  ja         0x35a6e6d3c
  0x35a6e6296  xorpd      xmm1, xmmword ptr [rip + 0x4262]
  0x35a6e629e  comisd     xmm1, xmm0
  0x35a6e62a2  ja         0x35a6e64bb
  0x35a6e62a8  mov        dword ptr [rbp - 0x38], 0
  0x35a6e62af  nop        
  0x35a6e62b0  mov        eax, dword ptr [rbp - 0x58]
  0x35a6e62b3  test       eax, eax
  0x35a6e62b5  jns        0x35a6e61a0
  0x35a6e62bb  mov        edi, dword ptr [rbp - 0x78]
  0x35a6e62be  test       edi, edi
  0x35a6e62c0  je         0x35a6e64e0
  0x35a6e62c6  mov        edi, dword ptr [rbp - 0x58]
  0x35a6e62c9  sub        r13d, r14d
  0x35a6e62cc  mov        edx, dword ptr [r12 + 4]
  0x35a6e62d1  lea        eax, [r13 + 1]
  0x35a6e62d5  mov        ecx, edi
  0x35a6e62d7  mov        dword ptr [rbp - 4], eax
  0x35a6e62da  sub        ecx, r13d
  0x35a6e62dd  cmp        ecx, edx
  0x35a6e62df  jge        0x35a6e6870
  0x35a6e62e5  mov        ecx, dword ptr [rbp - 0x28]
  0x35a6e62e8  lea        eax, [rcx - 3]
  0x35a6e62eb  and        eax, 0xfffffffd
  0x35a6e62ee  je         0x35a6e687a
  0x35a6e62f4  mov        eax, edi
  0x35a6e62f6  mov        edi, dword ptr [rbp - 0x48]
  0x35a6e62f9  sub        eax, edx
  0x35a6e62fb  add        eax, 1
  0x35a6e62fe  cmp        ecx, 1
  0x35a6e6301  setg       cl
  0x35a6e6304  test       edi, edi
  0x35a6e6306  mov        dword ptr [rbp - 4], eax
  0x35a6e6309  setg       dl
  0x35a6e630c  test       cl, dl
  0x35a6e630e  je         0x35a6e6318
  0x35a6e6310  cmp        eax, edi
  0x35a6e6312  jg         0x35a6e6f79
  0x35a6e6318  mov        edi, dword ptr [rbp - 0x68]
  0x35a6e631b  add        dword ptr [rbp - 0x50], eax
  0x35a6e631e  mov        r8d, dword ptr [rbp - 0x74]
  0x35a6e6322  add        eax, edi
  0x35a6e6324  mov        r13d, edi
  0x35a6e6327  mov        dword ptr [rbp - 0x68], eax
  0x35a6e632a  mov        ecx, 1
  0x35a6e632f  mov        dword ptr [rbp - 0x80], r8d
  0x35a6e6333  mov        dword ptr [rbp - 0x58], r11d
  0x35a6e6337  call       0x35a6e7530
  0x35a6e633c  mov        dword ptr [rbp - 0x78], 1
  0x35a6e6343  mov        r11d, dword ptr [rbp - 0x58]
  0x35a6e6347  mov        r8d, dword ptr [rbp - 0x80]
  0x35a6e634b  mov        rdi, rax
  0x35a6e634e  test       r13d, r13d
  0x35a6e6351  jle        0x35a6e6371
  0x35a6e6353  mov        ecx, dword ptr [rbp - 0x50]
  0x35a6e6356  test       ecx, ecx
  0x35a6e6358  jle        0x35a6e6371
  0x35a6e635a  cmp        r13d, ecx
  0x35a6e635d  mov        eax, ecx
  0x35a6e635f  cmovle     eax, r13d
  0x35a6e6363  sub        dword ptr [rbp - 0x68], eax
  0x35a6e6366  sub        ecx, eax
  0x35a6e6368  mov        dword ptr [rbp - 4], eax
  0x35a6e636b  sub        r13d, eax
  0x35a6e636e  mov        dword ptr [rbp - 0x50], ecx
  0x35a6e6371  mov        r10d, dword ptr [rbp - 0x74]
  0x35a6e6375  test       r10d, r10d
  0x35a6e6378  je         0x35a6e63a2
  0x35a6e637a  mov        r9d, dword ptr [rbp - 0x78]
  0x35a6e637e  test       r9d, r9d
  0x35a6e6381  je         0x35a6e638c
  0x35a6e6383  test       r8d, r8d
  0x35a6e6386  jne        0x35a6e6b0f
  0x35a6e638c  mov        edx, dword ptr [rbp - 0x74]
  0x35a6e638f  mov        rcx, rbx
  0x35a6e6392  mov        dword ptr [rbp - 0x58], r11d
  0x35a6e6396  call       0x35a6e7760
  0x35a6e639b  mov        r11d, dword ptr [rbp - 0x58]
  0x35a6e639f  mov        rbx, rax
  0x35a6e63a2  mov        ecx, 1
  0x35a6e63a7  mov        dword ptr [rbp - 0x58], r11d
  0x35a6e63ab  call       0x35a6e7530
  0x35a6e63b0  mov        r11d, dword ptr [rbp - 0x58]
  0x35a6e63b4  mov        r9, rax
  0x35a6e63b7  test       r11d, r11d
  0x35a6e63ba  jne        0x35a6e6808
  0x35a6e63c0  cmp        dword ptr [rbp - 0x28], 1
  0x35a6e63c4  jle        0x35a6e6840
  0x35a6e63ca  mov        r12d, 0x1f
  0x35a6e63d0  mov        eax, dword ptr [rbp - 0x50]
  0x35a6e63d3  sub        r12d, eax
  0x35a6e63d6  mov        eax, dword ptr [rbp - 0x68]
  0x35a6e63d9  sub        r12d, 4
  0x35a6e63dd  and        r12d, 0x1f
  0x35a6e63e1  add        eax, r12d
  0x35a6e63e4  mov        dword ptr [rbp - 4], r12d
  0x35a6e63e8  mov        edx, r12d
  0x35a6e63eb  test       eax, eax
  0x35a6e63ed  jle        0x35a6e640f
  0x35a6e63ef  mov        edx, eax
  0x35a6e63f1  mov        rcx, rbx
  0x35a6e63f4  mov        qword ptr [rbp - 0x58], r9
  0x35a6e63f8  mov        dword ptr [rbp - 0x30], r11d
  0x35a6e63fc  call       0x35a6e78f0
  0x35a6e6401  mov        edx, dword ptr [rbp - 4]
  0x35a6e6404  mov        r9, qword ptr [rbp - 0x58]
  0x35a6e6408  mov        r11d, dword ptr [rbp - 0x30]
  0x35a6e640c  mov        rbx, rax
  0x35a6e640f  mov        eax, dword ptr [rbp - 0x50]
  0x35a6e6412  add        eax, edx
  0x35a6e6414  mov        edx, eax
  0x35a6e6416  test       eax, eax
  0x35a6e6418  jle        0x35a6e642d
  0x35a6e641a  mov        rcx, r9
  0x35a6e641d  mov        dword ptr [rbp - 0x30], r11d
  0x35a6e6421  call       0x35a6e78f0
  0x35a6e6426  mov        r11d, dword ptr [rbp - 0x30]
  0x35a6e642a  mov        r9, rax
  0x35a6e642d  mov        ecx, dword ptr [rbp - 0x7c]
  0x35a6e6430  cmp        dword ptr [rbp - 0x28], 2
  0x35a6e6434  setg       r14b
  0x35a6e6438  test       ecx, ecx
  0x35a6e643a  jne        0x35a6e66e0
  0x35a6e6440  mov        eax, dword ptr [rbp - 0x48]
  0x35a6e6443  test       eax, eax
  0x35a6e6445  jg         0x35a6e64f0
  0x35a6e644b  test       r14b, r14b
  0x35a6e644e  je         0x35a6e64f0
  0x35a6e6454  mov        eax, dword ptr [rbp - 0x48]
  0x35a6e6457  test       eax, eax
  0x35a6e6459  jne        0x35a6e64c0
  0x35a6e645b  mov        rcx, r9
  0x35a6e645e  xor        r8d, r8d
  0x35a6e6461  mov        edx, 5
  0x35a6e6466  call       0x35a6e7470
  0x35a6e646b  mov        rcx, rbx
  0x35a6e646e  mov        rdx, rax
  0x35a6e6471  mov        qword ptr [rbp - 0x28], rax
  0x35a6e6475  call       0x35a6e7a20
  0x35a6e647a  mov        r9, qword ptr [rbp - 0x28]
  0x35a6e647e  test       eax, eax
  0x35a6e6480  jle        0x35a6e64c0
  0x35a6e6482  mov        eax, dword ptr [rbp - 0x70]
  0x35a6e6485  mov        rsi, qword ptr [rbp - 0x60]
  0x35a6e6489  add        eax, 2
  0x35a6e648c  mov        dword ptr [rbp - 0x34], eax
  0x35a6e648f  jmp        0x35a6e6202
  0x35a6e6494  mov        dword ptr [rbp - 0x78], 1
  0x35a6e649b  mov        eax, dword ptr [rbp - 0x34]
  0x35a6e649e  mov        ecx, 1
  0x35a6e64a3  test       eax, eax
  0x35a6e64a5  cmovg      ecx, eax
  0x35a6e64a8  mov        dword ptr [rbp - 0x90], ecx
  0x35a6e64ae  mov        eax, ecx
  0x35a6e64b0  mov        dword ptr [rbp - 0x48], ecx
  0x35a6e64b3  mov        dword ptr [rbp - 0x34], ecx
  0x35a6e64b6  jmp        0x35a6e5e90
  0x35a6e64bb  xor        r9d, r9d
  0x35a6e64be  xor        edi, edi
  0x35a6e64c0  mov        eax, dword ptr [rbp - 0x34]
  0x35a6e64c3  mov        rsi, qword ptr [rbp - 0x60]
  0x35a6e64c7  mov        dword ptr [rbp - 0x40], 0x10
  0x35a6e64ce  neg        eax
  0x35a6e64d0  mov        dword ptr [rbp - 0x34], eax
  0x35a6e64d3  jmp        0x35a6e6211
  0x35a6e64d8  nop        dword ptr [rax + rax]
  0x35a6e64e0  mov        r8d, dword ptr [rbp - 0x74]
  0x35a6e64e4  mov        r13d, dword ptr [rbp - 0x68]
  0x35a6e64e8  xor        edi, edi
  0x35a6e64ea  jmp        0x35a6e634e
  0x35a6e64ef  nop        
  0x35a6e64f0  mov        eax, dword ptr [rbp - 0x70]
  0x35a6e64f3  add        eax, 1
  0x35a6e64f6  mov        dword ptr [rbp - 0x34], eax
  0x35a6e64f9  mov        eax, dword ptr [rbp - 0x78]
  0x35a6e64fc  test       eax, eax
  0x35a6e64fe  je         0x35a6e6760
  0x35a6e6504  lea        edx, [r12 + r13]
  0x35a6e6508  test       edx, edx
  0x35a6e650a  jle        0x35a6e6527
  0x35a6e650c  mov        rcx, rdi
  0x35a6e650f  mov        qword ptr [rbp - 0x50], r9
  0x35a6e6513  mov        dword ptr [rbp - 0x30], r11d
  0x35a6e6517  call       0x35a6e78f0
  0x35a6e651c  mov        r9, qword ptr [rbp - 0x50]
  0x35a6e6520  mov        r11d, dword ptr [rbp - 0x30]
  0x35a6e6524  mov        rdi, rax
  0x35a6e6527  mov        r13, rdi
  0x35a6e652a  test       r11d, r11d
  0x35a6e652d  jne        0x35a6e6cfb
  0x35a6e6533  mov        r10, qword ptr [rbp - 0x60]
  0x35a6e6537  mov        qword ptr [rbp - 0x70], r15
  0x35a6e653b  mov        eax, 1
  0x35a6e6540  mov        qword ptr [rbp - 0x30], r9
  0x35a6e6544  mov        qword ptr [rbp - 0x50], rsi
  0x35a6e6548  mov        r15, r10
  0x35a6e654b  jmp        0x35a6e65ea
  0x35a6e6550  mov        rcx, rdx
  0x35a6e6553  call       0x35a6e7400
  0x35a6e6558  mov        edx, 1
  0x35a6e655d  test       r12d, r12d
  0x35a6e6560  js         0x35a6e6bc8
  0x35a6e6566  or         r12d, dword ptr [rbp - 0x28]
  0x35a6e656a  jne        0x35a6e6579
  0x35a6e656c  mov        rax, qword ptr [rbp - 0x50]
  0x35a6e6570  test       byte ptr [rax], 1
  0x35a6e6573  je         0x35a6e6bc8
  0x35a6e6579  lea        r12, [r15 + 1]
  0x35a6e657d  mov        r14, r12
  0x35a6e6580  test       edx, edx
  0x35a6e6582  jle        0x35a6e658e
  0x35a6e6584  cmp        dword ptr [rbp - 0x38], 2
  0x35a6e6588  jne        0x35a6e6d51
  0x35a6e658e  mov        byte ptr [r12 - 1], sil
  0x35a6e6593  mov        eax, dword ptr [rbp - 0x48]
  0x35a6e6596  cmp        dword ptr [rbp - 4], eax
  0x35a6e6599  je         0x35a6e6d81
  0x35a6e659f  mov        rcx, rbx
  0x35a6e65a2  xor        r8d, r8d
  0x35a6e65a5  mov        edx, 0xa
  0x35a6e65aa  call       0x35a6e7470
  0x35a6e65af  xor        r8d, r8d
  0x35a6e65b2  mov        edx, 0xa
  0x35a6e65b7  mov        rcx, rdi
  0x35a6e65ba  mov        rbx, rax
  0x35a6e65bd  cmp        rdi, r13
  0x35a6e65c0  je         0x35a6e66d0
  0x35a6e65c6  call       0x35a6e7470
  0x35a6e65cb  mov        rcx, r13
  0x35a6e65ce  xor        r8d, r8d
  0x35a6e65d1  mov        edx, 0xa
  0x35a6e65d6  mov        rdi, rax
  0x35a6e65d9  call       0x35a6e7470
  0x35a6e65de  mov        r13, rax
  0x35a6e65e1  mov        eax, dword ptr [rbp - 4]
  0x35a6e65e4  mov        r15, r12
  0x35a6e65e7  add        eax, 1
  0x35a6e65ea  mov        rdx, qword ptr [rbp - 0x30]
  0x35a6e65ee  mov        rcx, rbx
  0x35a6e65f1  mov        dword ptr [rbp - 4], eax
  0x35a6e65f4  call       0x35a6e58d0
  0x35a6e65f9  mov        rdx, rdi
  0x35a6e65fc  mov        rcx, rbx
  0x35a6e65ff  mov        r14d, eax
  0x35a6e6602  lea        esi, [rax + 0x30]
  0x35a6e6605  call       0x35a6e7a20
  0x35a6e660a  mov        rcx, qword ptr [rbp - 0x30]
  0x35a6e660e  mov        rdx, r13
  0x35a6e6611  mov        r12d, eax
  0x35a6e6614  call       0x35a6e7a70
  0x35a6e6619  mov        rdx, rax
  0x35a6e661c  mov        eax, dword ptr [rax + 0x10]
  0x35a6e661f  test       eax, eax
  0x35a6e6621  jne        0x35a6e6550
  0x35a6e6627  mov        rcx, rbx
  0x35a6e662a  mov        qword ptr [rbp - 0x68], rdx
  0x35a6e662e  call       0x35a6e7a20
  0x35a6e6633  mov        rcx, qword ptr [rbp - 0x68]
  0x35a6e6637  mov        dword ptr [rbp - 0x58], eax
  0x35a6e663a  call       0x35a6e7400
  0x35a6e663f  mov        edx, dword ptr [rbp - 0x58]
  0x35a6e6642  mov        eax, dword ptr [rbp - 0x28]
  0x35a6e6645  or         edx, eax
  0x35a6e6647  jne        0x35a6e6a4e
  0x35a6e664d  mov        rax, qword ptr [rbp - 0x50]
  0x35a6e6651  mov        eax, dword ptr [rax]
  0x35a6e6653  mov        dword ptr [rbp - 0x58], eax
  0x35a6e6656  and        eax, 1
  0x35a6e6659  or         eax, dword ptr [rbp - 0x38]
  0x35a6e665c  jne        0x35a6e655d
  0x35a6e6662  mov        r10, r15
  0x35a6e6665  mov        r9, qword ptr [rbp - 0x30]
  0x35a6e6669  mov        r15, qword ptr [rbp - 0x70]
  0x35a6e666d  mov        r8d, esi
  0x35a6e6670  cmp        esi, 0x39
  0x35a6e6673  je         0x35a6e6ff1
  0x35a6e6679  test       r12d, r12d
  0x35a6e667c  jle        0x35a6e703e
  0x35a6e6682  mov        dword ptr [rbp - 0x40], 0x20
  0x35a6e6689  lea        r8d, [r14 + 0x31]
  0x35a6e668d  mov        byte ptr [r10], r8b
  0x35a6e6690  mov        rsi, rdi
  0x35a6e6693  lea        r12, [r10 + 1]
  0x35a6e6697  mov        rdi, r13
  0x35a6e669a  nop        word ptr [rax + rax]
  0x35a6e66a0  mov        rcx, r9
  0x35a6e66a3  call       0x35a6e7400
  0x35a6e66a8  test       rdi, rdi
  0x35a6e66ab  je         0x35a6e6a8c
  0x35a6e66b1  test       rsi, rsi
  0x35a6e66b4  je         0x35a6e66c3
  0x35a6e66b6  cmp        rsi, rdi
  0x35a6e66b9  je         0x35a6e66c3
  0x35a6e66bb  mov        rcx, rsi
  0x35a6e66be  call       0x35a6e7400
  0x35a6e66c3  mov        rsi, qword ptr [rbp - 0x60]
  0x35a6e66c7  mov        qword ptr [rbp - 0x60], r12
  0x35a6e66cb  jmp        0x35a6e621e
  0x35a6e66d0  call       0x35a6e7470
  0x35a6e66d5  mov        rdi, rax
  0x35a6e66d8  mov        r13, rax
  0x35a6e66db  jmp        0x35a6e65e1
  0x35a6e66e0  mov        rdx, r9
  0x35a6e66e3  mov        rcx, rbx
  0x35a6e66e6  mov        dword ptr [rbp - 0x50], r11d
  0x35a6e66ea  mov        qword ptr [rbp - 0x30], r9
  0x35a6e66ee  call       0x35a6e7a20
  0x35a6e66f3  mov        r9, qword ptr [rbp - 0x30]
  0x35a6e66f7  mov        r11d, dword ptr [rbp - 0x50]
  0x35a6e66fb  test       eax, eax
  0x35a6e66fd  jns        0x35a6e6440
  0x35a6e6703  mov        eax, dword ptr [rbp - 0x70]
  0x35a6e6706  xor        r8d, r8d
  0x35a6e6709  mov        edx, 0xa
  0x35a6e670e  mov        rcx, rbx
  0x35a6e6711  mov        qword ptr [rbp - 0x48], r9
  0x35a6e6715  sub        eax, 1
  0x35a6e6718  mov        dword ptr [rbp - 0x30], r11d
  0x35a6e671c  mov        dword ptr [rbp - 0x50], eax
  0x35a6e671f  call       0x35a6e7470
  0x35a6e6724  mov        edx, dword ptr [rbp - 0x78]
  0x35a6e6727  mov        r9, qword ptr [rbp - 0x48]
  0x35a6e672b  mov        rbx, rax
  0x35a6e672e  mov        eax, dword ptr [rbp - 0x90]
  0x35a6e6734  test       eax, eax
  0x35a6e6736  setle      al
  0x35a6e6739  and        r14d, eax
  0x35a6e673c  test       edx, edx
  0x35a6e673e  jne        0x35a6e6f0c
  0x35a6e6744  test       r14b, r14b
  0x35a6e6747  jne        0x35a6e6e34
  0x35a6e674d  mov        eax, dword ptr [rbp - 0x70]
  0x35a6e6750  mov        dword ptr [rbp - 0x34], eax
  0x35a6e6753  mov        eax, dword ptr [rbp - 0x90]
  0x35a6e6759  mov        dword ptr [rbp - 0x48], eax
  0x35a6e675c  nop        dword ptr [rax]
  0x35a6e6760  mov        r14, qword ptr [rbp - 0x60]
  0x35a6e6764  mov        r12d, dword ptr [rbp - 0x48]
  0x35a6e6768  mov        eax, 1
  0x35a6e676d  mov        rsi, r9
  0x35a6e6770  jmp        0x35a6e6791
  0x35a6e6772  nop        word ptr [rax + rax]
  0x35a6e6778  mov        rcx, rbx
  0x35a6e677b  xor        r8d, r8d
  0x35a6e677e  mov        edx, 0xa
  0x35a6e6783  call       0x35a6e7470
  0x35a6e6788  mov        rbx, rax
  0x35a6e678b  mov        eax, dword ptr [rbp - 4]
  0x35a6e678e  add        eax, 1
  0x35a6e6791  mov        rdx, rsi
  0x35a6e6794  mov        rcx, rbx
  0x35a6e6797  mov        dword ptr [rbp - 4], eax
  0x35a6e679a  add        r14, 1
  0x35a6e679e  call       0x35a6e58d0
  0x35a6e67a3  lea        r8d, [rax + 0x30]
  0x35a6e67a7  mov        byte ptr [r14 - 1], r8b
  0x35a6e67ab  cmp        dword ptr [rbp - 4], r12d
  0x35a6e67af  jl         0x35a6e6778
  0x35a6e67b1  mov        r9, rsi
  0x35a6e67b4  xor        esi, esi
  0x35a6e67b6  mov        ecx, dword ptr [rbp - 0x38]
  0x35a6e67b9  test       ecx, ecx
  0x35a6e67bb  je         0x35a6e6b6a
  0x35a6e67c1  mov        eax, dword ptr [rbx + 0x14]
  0x35a6e67c4  cmp        ecx, 2
  0x35a6e67c7  je         0x35a6e6baa
  0x35a6e67cd  cmp        eax, 1
  0x35a6e67d0  jg         0x35a6e6a56
  0x35a6e67d6  mov        eax, dword ptr [rbx + 0x18]
  0x35a6e67d9  test       eax, eax
  0x35a6e67db  jne        0x35a6e6a56
  0x35a6e67e1  test       eax, eax
  0x35a6e67e3  setne      al
  0x35a6e67e6  movzx      eax, al
  0x35a6e67e9  shl        eax, 4
  0x35a6e67ec  mov        dword ptr [rbp - 0x40], eax
  0x35a6e67ef  nop        
  0x35a6e67f0  mov        r12, r14
  0x35a6e67f3  sub        r14, 1
  0x35a6e67f7  cmp        byte ptr [r14], 0x30
  0x35a6e67fb  je         0x35a6e67f0
  0x35a6e67fd  jmp        0x35a6e66a0
  0x35a6e6802  nop        word ptr [rax + rax]
  0x35a6e6808  mov        edx, r11d
  0x35a6e680b  mov        rcx, rax
  0x35a6e680e  call       0x35a6e7760
  0x35a6e6813  cmp        dword ptr [rbp - 0x28], 1
  0x35a6e6817  mov        r9, rax
  0x35a6e681a  jle        0x35a6e6ac2
  0x35a6e6820  xor        r11d, r11d
  0x35a6e6823  mov        eax, dword ptr [r9 + 0x14]
  0x35a6e6827  sub        eax, 1
  0x35a6e682a  cdqe       
  0x35a6e682c  bsr        r12d, dword ptr [r9 + rax*4 + 0x18]
  0x35a6e6832  xor        r12d, 0x1f
  0x35a6e6836  jmp        0x35a6e63d0
  0x35a6e683b  nop        dword ptr [rax + rax]
  0x35a6e6840  cmp        r14d, 1
  0x35a6e6844  jne        0x35a6e63ca
  0x35a6e684a  mov        eax, dword ptr [r12 + 4]
  0x35a6e684f  add        eax, 1
  0x35a6e6852  cmp        dword ptr [rbp - 0x30], eax
  0x35a6e6855  jle        0x35a6e63ca
  0x35a6e685b  add        dword ptr [rbp - 0x68], 1
  0x35a6e685f  mov        r11d, 1
  0x35a6e6865  add        dword ptr [rbp - 0x50], 1
  0x35a6e6869  jmp        0x35a6e63ca
  0x35a6e686e  nop        
  0x35a6e6870  cmp        dword ptr [rbp - 0x28], 1
  0x35a6e6874  jle        0x35a6e6318
  0x35a6e687a  mov        ecx, dword ptr [rbp - 0x48]
  0x35a6e687d  mov        edi, dword ptr [rbp - 0x74]
  0x35a6e6880  lea        eax, [rcx - 1]
  0x35a6e6883  cmp        edi, eax
  0x35a6e6885  jl         0x35a6e6a99
  0x35a6e688b  mov        r8d, edi
  0x35a6e688e  sub        r8d, eax
  0x35a6e6891  test       ecx, ecx
  0x35a6e6893  jns        0x35a6e6ef4
  0x35a6e6899  mov        r13d, dword ptr [rbp - 0x68]
  0x35a6e689d  mov        eax, dword ptr [rbp - 0x48]
  0x35a6e68a0  mov        dword ptr [rbp - 4], 0
  0x35a6e68a7  sub        r13d, eax
  0x35a6e68aa  jmp        0x35a6e632a
  0x35a6e68af  mov        dword ptr [rbp - 0x78], 1
  0x35a6e68b6  jmp        0x35a6e5e70
  0x35a6e68bb  movupd     xmm4, xmm2
  0x35a6e68bf  movq       xmm0, r10
  0x35a6e68c4  mov        rdx, qword ptr [rbp - 0x60]
  0x35a6e68c8  xor        r9d, r9d
  0x35a6e68cb  mulsd      xmm4, xmm3
  0x35a6e68cf  mov        dword ptr [rbp - 4], 1
  0x35a6e68d6  movsd      xmm2, qword ptr [rip + 0x3bfa]
  0x35a6e68de  movupd     xmm1, xmm0
  0x35a6e68e2  jmp        0x35a6e68f7
  0x35a6e68e4  nop        dword ptr [rax]
  0x35a6e68e8  mulsd      xmm1, xmm2
  0x35a6e68ec  add        r10d, 1
  0x35a6e68f0  mov        r9d, edi
  0x35a6e68f3  mov        dword ptr [rbp - 4], r10d
  0x35a6e68f7  cvttsd2si  ecx, xmm1
  0x35a6e68fb  test       ecx, ecx
  0x35a6e68fd  je         0x35a6e690e
  0x35a6e68ff  pxor       xmm3, xmm3
  0x35a6e6903  mov        r9d, edi
  0x35a6e6906  cvtsi2sd   xmm3, ecx
  0x35a6e690a  subsd      xmm1, xmm3
  0x35a6e690e  add        rdx, 1
  0x35a6e6912  add        ecx, 0x30
  0x35a6e6915  mov        byte ptr [rdx - 1], cl
  0x35a6e6918  mov        r10d, dword ptr [rbp - 4]
  0x35a6e691c  cmp        r10d, eax
  0x35a6e691f  jne        0x35a6e68e8
  0x35a6e6921  test       r9b, r9b
  0x35a6e6924  je         0x35a6e6ed5
  0x35a6e692a  movsd      xmm0, qword ptr [rip + 0x3bde]
  0x35a6e6932  movupd     xmm2, xmm4
  0x35a6e6936  addsd      xmm2, xmm0
  0x35a6e693a  comisd     xmm1, xmm2
  0x35a6e693e  ja         0x35a6e6ecc
  0x35a6e6944  subsd      xmm0, xmm4
  0x35a6e6948  comisd     xmm0, xmm1
  0x35a6e694c  ja         0x35a6e6f44
  0x35a6e6952  mov        eax, dword ptr [rbp - 0x58]
  0x35a6e6955  test       eax, eax
  0x35a6e6957  js         0x35a6e6ede
  0x35a6e695d  mov        eax, dword ptr [r12 + 0x14]
  0x35a6e6962  test       eax, eax
  0x35a6e6964  js         0x35a6e6ede
  0x35a6e696a  mov        rax, qword ptr [rip + 0x3daf]
  0x35a6e6971  mov        dword ptr [rbp - 0x38], 0
  0x35a6e6978  movsd      xmm2, qword ptr [rax]
  0x35a6e697c  movsd      xmm0, qword ptr [rbp - 0x98]
  0x35a6e6984  mov        rsi, qword ptr [rbp - 0x60]
  0x35a6e6988  mov        dword ptr [rbp - 4], 1
  0x35a6e698f  movupd     xmm1, xmm0
  0x35a6e6993  lea        rdx, [rsi + 1]
  0x35a6e6997  divsd      xmm1, xmm2
  0x35a6e699b  cvttsd2si  eax, xmm1
  0x35a6e699f  pxor       xmm1, xmm1
  0x35a6e69a3  cvtsi2sd   xmm1, eax
  0x35a6e69a7  lea        ecx, [rax + 0x30]
  0x35a6e69aa  mov        byte ptr [rsi], cl
  0x35a6e69ac  mov        esi, dword ptr [rbp - 0x70]
  0x35a6e69af  add        esi, 1
  0x35a6e69b2  mulsd      xmm1, xmm2
  0x35a6e69b6  mov        dword ptr [rbp - 0x34], esi
  0x35a6e69b9  subsd      xmm0, xmm1
  0x35a6e69bd  pxor       xmm1, xmm1
  0x35a6e69c1  ucomisd    xmm0, xmm1
  0x35a6e69c5  jp         0x35a6e69cd
  0x35a6e69c7  je         0x35a6e6b5d
  0x35a6e69cd  movsd      xmm4, qword ptr [rip + 0x3b03]
  0x35a6e69d5  pxor       xmm3, xmm3
  0x35a6e69d9  jmp        0x35a6e6a1c
  0x35a6e69db  nop        dword ptr [rax + rax]
  0x35a6e69e0  mulsd      xmm0, xmm4
  0x35a6e69e4  add        ecx, 1
  0x35a6e69e7  add        rdx, 1
  0x35a6e69eb  mov        dword ptr [rbp - 4], ecx
  0x35a6e69ee  movupd     xmm1, xmm0
  0x35a6e69f2  divsd      xmm1, xmm2
  0x35a6e69f6  cvttsd2si  eax, xmm1
  0x35a6e69fa  pxor       xmm1, xmm1
  0x35a6e69fe  cvtsi2sd   xmm1, eax
  0x35a6e6a02  lea        ecx, [rax + 0x30]
  0x35a6e6a05  mov        byte ptr [rdx - 1], cl
  0x35a6e6a08  mulsd      xmm1, xmm2
  0x35a6e6a0c  subsd      xmm0, xmm1
  0x35a6e6a10  ucomisd    xmm0, xmm3
  0x35a6e6a14  jp         0x35a6e6a1c
  0x35a6e6a16  je         0x35a6e6b5d
  0x35a6e6a1c  mov        ecx, dword ptr [rbp - 4]
  0x35a6e6a1f  mov        esi, dword ptr [rbp - 0x48]
  0x35a6e6a22  cmp        ecx, esi
  0x35a6e6a24  jne        0x35a6e69e0
  0x35a6e6a26  mov        esi, dword ptr [rbp - 0x38]
  0x35a6e6a29  test       esi, esi
  0x35a6e6a2b  je         0x35a6e6e48
  0x35a6e6a31  cmp        esi, 1
  0x35a6e6a34  je         0x35a6e6f68
  0x35a6e6a3a  mov        rsi, qword ptr [rbp - 0x60]
  0x35a6e6a3e  mov        dword ptr [rbp - 0x40], 0x10
  0x35a6e6a45  mov        qword ptr [rbp - 0x60], rdx
  0x35a6e6a49  jmp        0x35a6e6226
  0x35a6e6a4e  mov        edx, dword ptr [rbp - 0x58]
  0x35a6e6a51  jmp        0x35a6e655d
  0x35a6e6a56  mov        rdx, qword ptr [rbp - 0x60]
  0x35a6e6a5a  jmp        0x35a6e6a69
  0x35a6e6a5c  nop        dword ptr [rax]
  0x35a6e6a60  cmp        r14, rdx
  0x35a6e6a63  je         0x35a6e6af8
  0x35a6e6a69  mov        r12, r14
  0x35a6e6a6c  lea        r14, [r14 - 1]
  0x35a6e6a70  movzx      eax, byte ptr [r12 - 1]
  0x35a6e6a76  cmp        al, 0x39
  0x35a6e6a78  je         0x35a6e6a60
  0x35a6e6a7a  add        eax, 1
  0x35a6e6a7d  mov        dword ptr [rbp - 0x40], 0x20
  0x35a6e6a84  mov        byte ptr [r14], al
  0x35a6e6a87  jmp        0x35a6e66a0
  0x35a6e6a8c  mov        rsi, qword ptr [rbp - 0x60]
  0x35a6e6a90  mov        qword ptr [rbp - 0x60], r12
  0x35a6e6a94  jmp        0x35a6e6226
  0x35a6e6a99  mov        edi, dword ptr [rbp - 0x74]
  0x35a6e6a9c  mov        edx, eax
  0x35a6e6a9e  mov        dword ptr [rbp - 0x74], eax
  0x35a6e6aa1  xor        r8d, r8d
  0x35a6e6aa4  sub        edx, edi
  0x35a6e6aa6  mov        edi, dword ptr [rbp - 0x48]
  0x35a6e6aa9  add        dword ptr [rbp - 0x50], edi
  0x35a6e6aac  add        r11d, edx
  0x35a6e6aaf  mov        edx, dword ptr [rbp - 0x68]
  0x35a6e6ab2  mov        dword ptr [rbp - 4], edi
  0x35a6e6ab5  add        edi, edx
  0x35a6e6ab7  mov        r13d, edx
  0x35a6e6aba  mov        dword ptr [rbp - 0x68], edi
  0x35a6e6abd  jmp        0x35a6e632a
  0x35a6e6ac2  cmp        r14d, 1
  0x35a6e6ac6  jne        0x35a6e6820
  0x35a6e6acc  mov        eax, dword ptr [r12 + 4]
  0x35a6e6ad1  mov        edx, dword ptr [rbp - 0x30]
  0x35a6e6ad4  add        eax, 1
  0x35a6e6ad7  cmp        eax, edx
  0x35a6e6ad9  jge        0x35a6e6820
  0x35a6e6adf  add        dword ptr [rbp - 0x68], 1
  0x35a6e6ae3  mov        r11d, 1
  0x35a6e6ae9  add        dword ptr [rbp - 0x50], 1
  0x35a6e6aed  jmp        0x35a6e6823
  0x35a6e6af2  nop        word ptr [rax + rax]
  0x35a6e6af8  mov        rax, qword ptr [rbp - 0x60]
  0x35a6e6afc  add        dword ptr [rbp - 0x34], 1
  0x35a6e6b00  mov        dword ptr [rbp - 0x40], 0x20
  0x35a6e6b07  mov        byte ptr [rax], 0x31
  0x35a6e6b0a  jmp        0x35a6e66a0
  0x35a6e6b0f  mov        edx, r8d
  0x35a6e6b12  mov        rcx, rdi
  0x35a6e6b15  mov        dword ptr [rbp - 0x88], r11d
  0x35a6e6b1c  mov        dword ptr [rbp - 0x80], r8d
  0x35a6e6b20  call       0x35a6e7760
  0x35a6e6b25  mov        rdx, rbx
  0x35a6e6b28  mov        rcx, rax
  0x35a6e6b2b  mov        rdi, rax
  0x35a6e6b2e  call       0x35a6e75f0
  0x35a6e6b33  mov        rcx, rbx
  0x35a6e6b36  mov        qword ptr [rbp - 0x58], rax
  0x35a6e6b3a  call       0x35a6e7400
  0x35a6e6b3f  mov        r8d, dword ptr [rbp - 0x80]
  0x35a6e6b43  sub        dword ptr [rbp - 0x74], r8d
  0x35a6e6b47  mov        rbx, qword ptr [rbp - 0x58]
  0x35a6e6b4b  mov        r11d, dword ptr [rbp - 0x88]
  0x35a6e6b52  je         0x35a6e63a2
  0x35a6e6b58  jmp        0x35a6e638c
  0x35a6e6b5d  mov        rsi, qword ptr [rbp - 0x60]
  0x35a6e6b61  mov        qword ptr [rbp - 0x60], rdx
  0x35a6e6b65  jmp        0x35a6e6226
  0x35a6e6b6a  mov        rcx, rbx
  0x35a6e6b6d  mov        edx, 1
  0x35a6e6b72  mov        qword ptr [rbp - 0x28], r9
  0x35a6e6b76  mov        dword ptr [rbp - 0x30], r8d
  0x35a6e6b7a  call       0x35a6e78f0
  0x35a6e6b7f  mov        rdx, qword ptr [rbp - 0x28]
  0x35a6e6b83  mov        rcx, rax
  0x35a6e6b86  mov        rbx, rax
  0x35a6e6b89  call       0x35a6e7a20
  0x35a6e6b8e  mov        r9, qword ptr [rbp - 0x28]
  0x35a6e6b92  test       eax, eax
  0x35a6e6b94  jg         0x35a6e6a56
  0x35a6e6b9a  jne        0x35a6e6baa
  0x35a6e6b9c  mov        r8d, dword ptr [rbp - 0x30]
  0x35a6e6ba0  and        r8d, 1
  0x35a6e6ba4  jne        0x35a6e6a56
  0x35a6e6baa  cmp        dword ptr [rbx + 0x14], 1
  0x35a6e6bae  mov        dword ptr [rbp - 0x40], 0x10
  0x35a6e6bb5  jg         0x35a6e67f0
  0x35a6e6bbb  mov        eax, dword ptr [rbx + 0x18]
  0x35a6e6bbe  jmp        0x35a6e67e1
  0x35a6e6bc3  nop        dword ptr [rax + rax]
  0x35a6e6bc8  mov        r11d, dword ptr [rbp - 0x38]
  0x35a6e6bcc  mov        r10, r15
  0x35a6e6bcf  mov        r9, qword ptr [rbp - 0x30]
  0x35a6e6bd3  mov        r8d, esi
  0x35a6e6bd6  mov        r15, qword ptr [rbp - 0x70]
  0x35a6e6bda  test       r11d, r11d
  0x35a6e6bdd  je         0x35a6e6da9
  0x35a6e6be3  cmp        dword ptr [rbx + 0x14], 1
  0x35a6e6be7  jle        0x35a6e6fa5
  0x35a6e6bed  cmp        dword ptr [rbp - 0x38], 2
  0x35a6e6bf1  je         0x35a6e6e08
  0x35a6e6bf7  mov        qword ptr [rbp - 0x30], r15
  0x35a6e6bfb  mov        r14d, r8d
  0x35a6e6bfe  mov        r15, r10
  0x35a6e6c01  mov        qword ptr [rbp - 0x28], r9
  0x35a6e6c05  jmp        0x35a6e6c58
  0x35a6e6c07  nop        word ptr [rax + rax]
  0x35a6e6c10  mov        byte ptr [r12 - 1], r14b
  0x35a6e6c15  xor        r8d, r8d
  0x35a6e6c18  mov        rcx, r13
  0x35a6e6c1b  mov        edx, 0xa
  0x35a6e6c20  mov        r15, r12
  0x35a6e6c23  call       0x35a6e7470
  0x35a6e6c28  cmp        rdi, r13
  0x35a6e6c2b  mov        rcx, rbx
  0x35a6e6c2e  mov        edx, 0xa
  0x35a6e6c33  cmove      rdi, rax
  0x35a6e6c37  xor        r8d, r8d
  0x35a6e6c3a  mov        rsi, rax
  0x35a6e6c3d  call       0x35a6e7470
  0x35a6e6c42  mov        rdx, qword ptr [rbp - 0x28]
  0x35a6e6c46  mov        r13, rsi
  0x35a6e6c49  mov        rcx, rax
  0x35a6e6c4c  mov        rbx, rax
  0x35a6e6c4f  call       0x35a6e58d0
  0x35a6e6c54  lea        r14d, [rax + 0x30]
  0x35a6e6c58  mov        rcx, qword ptr [rbp - 0x28]
  0x35a6e6c5c  mov        rdx, r13
  0x35a6e6c5f  lea        r12, [r15 + 1]
  0x35a6e6c63  call       0x35a6e7a20
  0x35a6e6c68  test       eax, eax
  0x35a6e6c6a  jg         0x35a6e6c10
  0x35a6e6c6c  mov        r10, r15
  0x35a6e6c6f  mov        r9, qword ptr [rbp - 0x28]
  0x35a6e6c73  mov        r15, qword ptr [rbp - 0x30]
  0x35a6e6c77  mov        r8d, r14d
  0x35a6e6c7a  cmp        r14d, 0x39
  0x35a6e6c7e  je         0x35a6e6ff5
  0x35a6e6c84  mov        dword ptr [rbp - 0x40], 0x20
  0x35a6e6c8b  mov        rsi, rdi
  0x35a6e6c8e  add        r8d, 1
  0x35a6e6c92  mov        rdi, r13
  0x35a6e6c95  mov        byte ptr [r10], r8b
  0x35a6e6c98  jmp        0x35a6e66a0
  0x35a6e6c9d  test       ecx, ecx
  0x35a6e6c9f  je         0x35a6e6255
  0x35a6e6ca5  mov        ecx, dword ptr [rbp - 0x90]
  0x35a6e6cab  test       ecx, ecx
  0x35a6e6cad  jle        0x35a6e62a8
  0x35a6e6cb3  mulsd      xmm0, qword ptr [rip + 0x381d]
  0x35a6e6cbb  movsd      xmm1, qword ptr [rip + 0x381d]
  0x35a6e6cc3  mov        r8d, 0xffffffff
  0x35a6e6cc9  mulsd      xmm1, xmm0
  0x35a6e6ccd  movq       r10, xmm0
  0x35a6e6cd2  addsd      xmm1, qword ptr [rip + 0x380e]
  0x35a6e6cda  movq       rdx, xmm1
  0x35a6e6cdf  mov        rax, rdx
  0x35a6e6ce2  mov        edx, edx
  0x35a6e6ce4  shr        rax, 0x20
  0x35a6e6ce8  sub        eax, 0x3400000
  0x35a6e6ced  shl        rax, 0x20
  0x35a6e6cf1  or         rdx, rax
  0x35a6e6cf4  mov        eax, ecx
  0x35a6e6cf6  jmp        0x35a6e5f6e
  0x35a6e6cfb  mov        ecx, dword ptr [rdi + 8]
  0x35a6e6cfe  mov        qword ptr [rbp - 0x30], r9
  0x35a6e6d02  call       0x35a6e7300
  0x35a6e6d07  lea        rdx, [rdi + 0x10]
  0x35a6e6d0b  lea        rcx, [rax + 0x10]
  0x35a6e6d0f  mov        r12, rax
  0x35a6e6d12  movsxd     rax, dword ptr [rdi + 0x14]
  0x35a6e6d16  lea        r8, [rax*4 + 8]
  0x35a6e6d1e  call       0x35a6e8738
  0x35a6e6d23  mov        edx, 1
  0x35a6e6d28  mov        rcx, r12
  0x35a6e6d2b  call       0x35a6e78f0
  0x35a6e6d30  mov        r9, qword ptr [rbp - 0x30]
  0x35a6e6d34  mov        r13, rax
  0x35a6e6d37  jmp        0x35a6e6533
  0x35a6e6d3c  mov        dword ptr [rbp - 0x34], 2
  0x35a6e6d43  mov        rsi, qword ptr [rbp - 0x60]
  0x35a6e6d47  xor        r9d, r9d
  0x35a6e6d4a  xor        edi, edi
  0x35a6e6d4c  jmp        0x35a6e6202
  0x35a6e6d51  mov        r10, r15
  0x35a6e6d54  mov        r9, qword ptr [rbp - 0x30]
  0x35a6e6d58  mov        r15, qword ptr [rbp - 0x70]
  0x35a6e6d5c  mov        r8d, esi
  0x35a6e6d5f  cmp        esi, 0x39
  0x35a6e6d62  je         0x35a6e6ff5
  0x35a6e6d68  add        r8d, 1
  0x35a6e6d6c  mov        rsi, rdi
  0x35a6e6d6f  mov        dword ptr [rbp - 0x40], 0x20
  0x35a6e6d76  mov        rdi, r13
  0x35a6e6d79  mov        byte ptr [r10], r8b
  0x35a6e6d7c  jmp        0x35a6e66a0
  0x35a6e6d81  mov        r8d, esi
  0x35a6e6d84  mov        r9, qword ptr [rbp - 0x30]
  0x35a6e6d88  mov        rsi, rdi
  0x35a6e6d8b  mov        r15, qword ptr [rbp - 0x70]
  0x35a6e6d8f  mov        rdi, r13
  0x35a6e6d92  jmp        0x35a6e67b6
  0x35a6e6d97  mov        qword ptr [rbp - 0x60], rdx
  0x35a6e6d9b  add        r8d, 1
  0x35a6e6d9f  mov        ecx, 0x31
  0x35a6e6da4  jmp        0x35a6e6058
  0x35a6e6da9  test       edx, edx
  0x35a6e6dab  jle        0x35a6e6dfe
  0x35a6e6dad  mov        rcx, rbx
  0x35a6e6db0  mov        edx, 1
  0x35a6e6db5  mov        qword ptr [rbp - 0x28], r9
  0x35a6e6db9  mov        qword ptr [rbp - 0x40], r10
  0x35a6e6dbd  mov        dword ptr [rbp - 0x30], r8d
  0x35a6e6dc1  call       0x35a6e78f0
  0x35a6e6dc6  mov        rdx, qword ptr [rbp - 0x28]
  0x35a6e6dca  mov        rcx, rax
  0x35a6e6dcd  mov        rbx, rax
  0x35a6e6dd0  call       0x35a6e7a20
  0x35a6e6dd5  mov        r9, qword ptr [rbp - 0x28]
  0x35a6e6dd9  mov        r8d, dword ptr [rbp - 0x30]
  0x35a6e6ddd  test       eax, eax
  0x35a6e6ddf  mov        r10, qword ptr [rbp - 0x40]
  0x35a6e6de3  jle        0x35a6e700b
  0x35a6e6de9  cmp        r8d, 0x39
  0x35a6e6ded  je         0x35a6e7023
  0x35a6e6df3  mov        dword ptr [rbp - 0x38], 0x20
  0x35a6e6dfa  lea        r8d, [r14 + 0x31]
  0x35a6e6dfe  cmp        dword ptr [rbx + 0x14], 1
  0x35a6e6e02  jle        0x35a6e6fd4
  0x35a6e6e08  mov        rsi, rdi
  0x35a6e6e0b  mov        dword ptr [rbp - 0x40], 0x10
  0x35a6e6e12  mov        rdi, r13
  0x35a6e6e15  lea        r12, [r10 + 1]
  0x35a6e6e19  jmp        0x35a6e6c95
  0x35a6e6e1e  mov        dword ptr [rbp - 0x90], 0xffffffff
  0x35a6e6e28  mov        dword ptr [rbp - 0x48], 0xffffffff
  0x35a6e6e2f  jmp        0x35a6e62c6
  0x35a6e6e34  mov        eax, dword ptr [rbp - 0x50]
  0x35a6e6e37  mov        dword ptr [rbp - 0x70], eax
  0x35a6e6e3a  mov        eax, dword ptr [rbp - 0x90]
  0x35a6e6e40  mov        dword ptr [rbp - 0x48], eax
  0x35a6e6e43  jmp        0x35a6e6454
  0x35a6e6e48  addsd      xmm0, xmm0
  0x35a6e6e4c  movzx      ecx, byte ptr [rdx - 1]
  0x35a6e6e50  comisd     xmm0, xmm2
  0x35a6e6e54  ja         0x35a6e6fc7
  0x35a6e6e5a  ucomisd    xmm0, xmm2
  0x35a6e6e5e  mov        rsi, qword ptr [rbp - 0x60]
  0x35a6e6e62  mov        r8d, dword ptr [rbp - 0x70]
  0x35a6e6e66  jp         0x35a6e6e72
  0x35a6e6e68  jne        0x35a6e6e72
  0x35a6e6e6a  test       al, 1
  0x35a6e6e6c  jne        0x35a6e6048
  0x35a6e6e72  mov        dword ptr [rbp - 0x40], 0x10
  0x35a6e6e79  nop        dword ptr [rax]
  0x35a6e6e80  mov        rax, rdx
  0x35a6e6e83  lea        rdx, [rdx - 1]
  0x35a6e6e87  cmp        byte ptr [rax - 1], 0x30
  0x35a6e6e8b  je         0x35a6e6e80
  0x35a6e6e8d  mov        qword ptr [rbp - 0x60], rax
  0x35a6e6e91  lea        eax, [r8 + 1]
  0x35a6e6e95  mov        dword ptr [rbp - 0x34], eax
  0x35a6e6e98  jmp        0x35a6e6226
  0x35a6e6e9d  pxor       xmm1, xmm1
  0x35a6e6ea1  xor        eax, eax
  0x35a6e6ea3  mov        ecx, 1
  0x35a6e6ea8  mov        rsi, qword ptr [rbp - 0x60]
  0x35a6e6eac  ucomisd    xmm0, xmm1
  0x35a6e6eb0  mov        qword ptr [rbp - 0x60], rdx
  0x35a6e6eb4  setp       al
  0x35a6e6eb7  cmovne     eax, ecx
  0x35a6e6eba  shl        eax, 4
  0x35a6e6ebd  mov        dword ptr [rbp - 0x40], eax
  0x35a6e6ec0  lea        eax, [r8 + 1]
  0x35a6e6ec4  mov        dword ptr [rbp - 0x34], eax
  0x35a6e6ec7  jmp        0x35a6e6226
  0x35a6e6ecc  mov        rsi, qword ptr [rbp - 0x60]
  0x35a6e6ed0  jmp        0x35a6e6048
  0x35a6e6ed5  movupd     xmm1, xmm0
  0x35a6e6ed9  jmp        0x35a6e692a
  0x35a6e6ede  mov        dword ptr [rbp - 0x38], 0
  0x35a6e6ee5  mov        r8d, dword ptr [rbp - 0x74]
  0x35a6e6ee9  xor        edi, edi
  0x35a6e6eeb  mov        r13d, dword ptr [rbp - 0x68]
  0x35a6e6eef  jmp        0x35a6e634e
  0x35a6e6ef4  mov        edi, dword ptr [rbp - 0x68]
  0x35a6e6ef7  mov        eax, ecx
  0x35a6e6ef9  add        dword ptr [rbp - 0x50], ecx
  0x35a6e6efc  mov        dword ptr [rbp - 4], ecx
  0x35a6e6eff  add        eax, edi
  0x35a6e6f01  mov        r13d, edi
  0x35a6e6f04  mov        dword ptr [rbp - 0x68], eax
  0x35a6e6f07  jmp        0x35a6e632a
  0x35a6e6f0c  xor        r8d, r8d
  0x35a6e6f0f  mov        rcx, rdi
  0x35a6e6f12  mov        edx, 0xa
  0x35a6e6f17  call       0x35a6e7470
  0x35a6e6f1c  test       r14b, r14b
  0x35a6e6f1f  mov        r9, qword ptr [rbp - 0x48]
  0x35a6e6f23  mov        rdi, rax
  0x35a6e6f26  jne        0x35a6e6e34
  0x35a6e6f2c  mov        eax, dword ptr [rbp - 0x70]
  0x35a6e6f2f  mov        r11d, dword ptr [rbp - 0x30]
  0x35a6e6f33  mov        dword ptr [rbp - 0x34], eax
  0x35a6e6f36  mov        eax, dword ptr [rbp - 0x90]
  0x35a6e6f3c  mov        dword ptr [rbp - 0x48], eax
  0x35a6e6f3f  jmp        0x35a6e6504
  0x35a6e6f44  pxor       xmm0, xmm0
  0x35a6e6f48  xor        eax, eax
  0x35a6e6f4a  mov        ecx, 1
  0x35a6e6f4f  mov        rsi, qword ptr [rbp - 0x60]
  0x35a6e6f53  ucomisd    xmm1, xmm0
  0x35a6e6f57  setp       al
  0x35a6e6f5a  cmovne     eax, ecx
  0x35a6e6f5d  shl        eax, 4
  0x35a6e6f60  mov        dword ptr [rbp - 0x40], eax
  0x35a6e6f63  jmp        0x35a6e6e80
  0x35a6e6f68  movzx      ecx, byte ptr [rdx - 1]
  0x35a6e6f6c  mov        rsi, qword ptr [rbp - 0x60]
  0x35a6e6f70  mov        r8d, dword ptr [rbp - 0x70]
  0x35a6e6f74  jmp        0x35a6e6048
  0x35a6e6f79  mov        edi, dword ptr [rbp - 0x48]
  0x35a6e6f7c  mov        edx, dword ptr [rbp - 0x74]
  0x35a6e6f7f  lea        eax, [rdi - 1]
  0x35a6e6f82  cmp        edx, eax
  0x35a6e6f84  jl         0x35a6e6a99
  0x35a6e6f8a  sub        edx, eax
  0x35a6e6f8c  mov        eax, dword ptr [rbp - 0x68]
  0x35a6e6f8f  add        dword ptr [rbp - 0x50], edi
  0x35a6e6f92  mov        dword ptr [rbp - 4], edi
  0x35a6e6f95  mov        r8d, edx
  0x35a6e6f98  mov        r13d, eax
  0x35a6e6f9b  add        eax, edi
  0x35a6e6f9d  mov        dword ptr [rbp - 0x68], eax
  0x35a6e6fa0  jmp        0x35a6e632a
  0x35a6e6fa5  mov        ecx, dword ptr [rbx + 0x18]
  0x35a6e6fa8  test       ecx, ecx
  0x35a6e6faa  jne        0x35a6e6bed
  0x35a6e6fb0  test       edx, edx
  0x35a6e6fb2  jg         0x35a6e6dad
  0x35a6e6fb8  mov        rsi, rdi
  0x35a6e6fbb  lea        r12, [r10 + 1]
  0x35a6e6fbf  mov        rdi, r13
  0x35a6e6fc2  jmp        0x35a6e6c95
  0x35a6e6fc7  mov        rsi, qword ptr [rbp - 0x60]
  0x35a6e6fcb  mov        r8d, dword ptr [rbp - 0x70]
  0x35a6e6fcf  jmp        0x35a6e6048
  0x35a6e6fd4  mov        edx, dword ptr [rbx + 0x18]
  0x35a6e6fd7  mov        rsi, rdi
  0x35a6e6fda  mov        rdi, r13
  0x35a6e6fdd  test       edx, edx
  0x35a6e6fdf  je         0x35a6e702f
  0x35a6e6fe1  mov        dword ptr [rbp - 0x40], 0x10
  0x35a6e6fe8  lea        r12, [r10 + 1]
  0x35a6e6fec  jmp        0x35a6e6c95
  0x35a6e6ff1  lea        r12, [r10 + 1]
  0x35a6e6ff5  mov        rsi, rdi
  0x35a6e6ff8  mov        rdi, r13
  0x35a6e6ffb  mov        byte ptr [r10], 0x39
  0x35a6e6fff  mov        rdx, qword ptr [rbp - 0x60]
  0x35a6e7003  mov        r14, r12
  0x35a6e7006  jmp        0x35a6e6a69
  0x35a6e700b  jne        0x35a6e7017
  0x35a6e700d  test       r8b, 1
  0x35a6e7011  jne        0x35a6e6de9
  0x35a6e7017  mov        dword ptr [rbp - 0x38], 0x20
  0x35a6e701e  jmp        0x35a6e6dfe
  0x35a6e7023  mov        rsi, rdi
  0x35a6e7026  lea        r12, [r10 + 1]
  0x35a6e702a  mov        rdi, r13
  0x35a6e702d  jmp        0x35a6e6ffb
  0x35a6e702f  mov        eax, dword ptr [rbp - 0x38]
  0x35a6e7032  lea        r12, [r10 + 1]
  0x35a6e7036  mov        dword ptr [rbp - 0x40], eax
  0x35a6e7039  jmp        0x35a6e6c95
  0x35a6e703e  cmp        dword ptr [rbx + 0x14], 1
  0x35a6e7042  mov        dword ptr [rbp - 0x40], 0x10
  0x35a6e7049  jg         0x35a6e668d
  0x35a6e704f  xor        eax, eax
  0x35a6e7051  cmp        dword ptr [rbx + 0x18], 0
  0x35a6e7055  setne      al
  0x35a6e7058  shl        eax, 4
  0x35a6e705b  mov        dword ptr [rbp - 0x40], eax
  0x35a6e705e  jmp        0x35a6e668d
  0x35a6e7063  nop        
  0x35a6e7064  nop        
  0x35a6e7065  nop        
  0x35a6e7066  nop        
  0x35a6e7067  nop        
  0x35a6e7068  nop        
  0x35a6e7069  nop        
  0x35a6e706a  nop        
  0x35a6e706b  nop        
  0x35a6e706c  nop        
  0x35a6e706d  nop        
  0x35a6e706e  nop        
  0x35a6e706f  nop        
  0x35a6e7070  push       rbp
  0x35a6e7071  push       r13
  0x35a6e7073  push       r12
  0x35a6e7075  push       rdi
  0x35a6e7076  push       rsi
  0x35a6e7077  push       rbx
  0x35a6e7078  lea        rbp, [rsp]
  0x35a6e707c  movsxd     rbx, dword ptr [rcx + 0x14]
  0x35a6e7080  mov        r12d, edx
  0x35a6e7083  mov        r10, rcx
  0x35a6e7086  sar        r12d, 5
  0x35a6e708a  cmp        ebx, r12d
  0x35a6e708d  jg         0x35a6e70b0
  0x35a6e708f  mov        dword ptr [r10 + 0x14], 0
  0x35a6e7097  mov        dword ptr [r10 + 0x18], 0
  0x35a6e709f  pop        rbx
  0x35a6e70a0  pop        rsi
  0x35a6e70a1  pop        rdi
  0x35a6e70a2  pop        r12
  0x35a6e70a4  pop        r13
  0x35a6e70a6  pop        rbp
  0x35a6e70a7  ret        
  0x35a6e70a8  nop        dword ptr [rax + rax]
  0x35a6e70b0  lea        r13, [rcx + 0x18]
  0x35a6e70b4  movsxd     r12, r12d
  0x35a6e70b7  lea        r11, [r13 + rbx*4]
  0x35a6e70bc  lea        rsi, [r13 + r12*4]
  0x35a6e70c1  and        edx, 0x1f
  0x35a6e70c4  je         0x35a6e7128
  0x35a6e70c6  mov        r9d, dword ptr [rsi]
  0x35a6e70c9  mov        edi, 0x20
  0x35a6e70ce  mov        ecx, edx
  0x35a6e70d0  lea        r8, [rsi + 4]
  0x35a6e70d4  sub        edi, edx
  0x35a6e70d6  shr        r9d, cl
  0x35a6e70d9  cmp        r8, r11
  0x35a6e70dc  jae        0x35a6e7168
  0x35a6e70e2  mov        rsi, r13
  0x35a6e70e5  nop        dword ptr [rax]
  0x35a6e70e8  mov        eax, dword ptr [r8]
  0x35a6e70eb  mov        ecx, edi
  0x35a6e70ed  add        rsi, 4
  0x35a6e70f1  add        r8, 4
  0x35a6e70f5  shl        eax, cl
  0x35a6e70f7  mov        ecx, edx
  0x35a6e70f9  or         eax, r9d
  0x35a6e70fc  mov        dword ptr [rsi - 4], eax
  0x35a6e70ff  mov        r9d, dword ptr [r8 - 4]
  0x35a6e7103  shr        r9d, cl
  0x35a6e7106  cmp        r8, r11
  0x35a6e7109  jb         0x35a6e70e8
  0x35a6e710b  sub        rbx, r12
  0x35a6e710e  lea        rax, [r13 + rbx*4 - 4]
  0x35a6e7113  mov        dword ptr [rax], r9d
  0x35a6e7116  test       r9d, r9d
  0x35a6e7119  je         0x35a6e7146
  0x35a6e711b  add        rax, 4
  0x35a6e711f  jmp        0x35a6e7146
  0x35a6e7121  nop        dword ptr [rax]
  0x35a6e7128  mov        rdi, r13
  0x35a6e712b  cmp        rsi, r11
  0x35a6e712e  jae        0x35a6e708f
  0x35a6e7134  nop        dword ptr [rax]
  0x35a6e7138  movsd      dword ptr [rdi], dword ptr [rsi]
  0x35a6e7139  cmp        rsi, r11
  0x35a6e713c  jb         0x35a6e7138
  0x35a6e713e  sub        rbx, r12
  0x35a6e7141  lea        rax, [r13 + rbx*4]
  0x35a6e7146  sub        rax, r13
  0x35a6e7149  sar        rax, 2
  0x35a6e714d  mov        dword ptr [r10 + 0x14], eax
  0x35a6e7151  test       eax, eax
  0x35a6e7153  je         0x35a6e7097
  0x35a6e7159  pop        rbx
  0x35a6e715a  pop        rsi
  0x35a6e715b  pop        rdi
  0x35a6e715c  pop        r12
  0x35a6e715e  pop        r13
  0x35a6e7160  pop        rbp
  0x35a6e7161  ret        
  0x35a6e7162  nop        word ptr [rax + rax]
  0x35a6e7168  mov        dword ptr [r10 + 0x18], r9d
  0x35a6e716c  test       r9d, r9d
  0x35a6e716f  je         0x35a6e708f
  0x35a6e7175  mov        rax, r13
  0x35a6e7178  jmp        0x35a6e711b
  0x35a6e717a  nop        word ptr [rax + rax]
  0x35a6e7180  movsxd     rdx, dword ptr [rcx + 0x14]
  0x35a6e7184  lea        rax, [rcx + 0x18]
  0x35a6e7188  lea        rcx, [rax + rdx*4]
  0x35a6e718c  xor        edx, edx
  0x35a6e718e  cmp        rax, rcx
  0x35a6e7191  jb         0x35a6e71a4
  0x35a6e7193  jmp        0x35a6e71b7
  0x35a6e7195  nop        dword ptr [rax]
  0x35a6e7198  add        rax, 4
  0x35a6e719c  add        edx, 0x20
  0x35a6e719f  cmp        rax, rcx
  0x35a6e71a2  jae        0x35a6e71b7
  0x35a6e71a4  mov        r8d, dword ptr [rax]
  0x35a6e71a7  test       r8d, r8d
  0x35a6e71aa  je         0x35a6e7198
  0x35a6e71ac  cmp        rax, rcx
  0x35a6e71af  jae        0x35a6e71b7
  0x35a6e71b1  tzcnt      eax, dword ptr [rax]
  0x35a6e71b5  add        edx, eax
  0x35a6e71b7  mov        eax, edx
  0x35a6e71b9  ret        
  0x35a6e71ba  nop        
  0x35a6e71bb  nop        
  0x35a6e71bc  nop        
  0x35a6e71bd  nop        
  0x35a6e71be  nop        
  0x35a6e71bf  nop        
  0x35a6e71c0  push       rbp
  0x35a6e71c1  push       rdi
  0x35a6e71c2  push       rsi
  0x35a6e71c3  push       rbx
  0x35a6e71c4  sub        rsp, 0x28
  0x35a6e71c8  lea        rbp, [rsp + 0x20]
  0x35a6e71cd  mov        eax, dword ptr [rip + 0x2826d1d]
  0x35a6e71d3  mov        esi, ecx
  0x35a6e71d5  cmp        eax, 2
  0x35a6e71d8  je         0x35a6e72a0
  0x35a6e71de  test       eax, eax
  0x35a6e71e0  je         0x35a6e7218
  0x35a6e71e2  cmp        eax, 1
  0x35a6e71e5  jne        0x35a6e720b
  0x35a6e71e7  mov        rbx, qword ptr [rip + 0x2827fc2]
  0x35a6e71ee  nop        
  0x35a6e71f0  mov        ecx, 1
  0x35a6e71f5  call       rbx
  0x35a6e71f7  mov        eax, dword ptr [rip + 0x2826cf3]
  0x35a6e71fd  cmp        eax, 1
  0x35a6e7200  je         0x35a6e71f0
  0x35a6e7202  cmp        eax, 2
  0x35a6e7205  je         0x35a6e72a0
  0x35a6e720b  add        rsp, 0x28
  0x35a6e720f  pop        rbx
  0x35a6e7210  pop        rsi
  0x35a6e7211  pop        rdi
  0x35a6e7212  pop        rbp
  0x35a6e7213  ret        
  0x35a6e7214  nop        dword ptr [rax]
  0x35a6e7218  mov        eax, 1
  0x35a6e721d  xchg       dword ptr [rip + 0x2826ccd], eax
  0x35a6e7223  test       eax, eax
  0x35a6e7225  jne        0x35a6e7278
  0x35a6e7227  lea        rbx, [rip + 0x2826cd2]
  0x35a6e722e  mov        rdi, qword ptr [rip + 0x2827f5b]
  0x35a6e7235  mov        rcx, rbx
  0x35a6e7238  call       rdi
  0x35a6e723a  lea        rcx, [rbx + 0x28]
  0x35a6e723e  call       rdi
  0x35a6e7240  lea        rcx, [rip + 0x69]
  0x35a6e7247  call       0x35a6e1360
  0x35a6e724c  mov        dword ptr [rip + 0x2826c9a], 2
  0x35a6e7256  mov        rcx, rsi
  0x35a6e7259  neg        rcx
  0x35a6e725c  and        ecx, 0x28
  0x35a6e725f  add        rcx, rbx
  0x35a6e7262  add        rsp, 0x28
  0x35a6e7266  pop        rbx
  0x35a6e7267  pop        rsi
  0x35a6e7268  pop        rdi
  0x35a6e7269  pop        rbp
  0x35a6e726a  jmp        qword ptr [rip + 0x2827f0f]
  0x35a6e7271  nop        dword ptr [rax]
  0x35a6e7278  lea        rbx, [rip + 0x2826c81]
  0x35a6e727f  cmp        eax, 2
  0x35a6e7282  je         0x35a6e724c
  0x35a6e7284  mov        eax, dword ptr [rip + 0x2826c66]
  0x35a6e728a  cmp        eax, 1
  0x35a6e728d  je         0x35a6e71e7
  0x35a6e7293  jmp        0x35a6e7202
  0x35a6e7298  nop        dword ptr [rax + rax]
  0x35a6e72a0  lea        rbx, [rip + 0x2826c59]
  0x35a6e72a7  jmp        0x35a6e7256
  0x35a6e72a9  nop        dword ptr [rax]
  0x35a6e72b0  push       rbp
  0x35a6e72b1  push       rbx
  0x35a6e72b2  sub        rsp, 0x28
  0x35a6e72b6  lea        rbp, [rsp + 0x20]
  0x35a6e72bb  mov        eax, 3
  0x35a6e72c0  xchg       dword ptr [rip + 0x2826c2a], eax
  0x35a6e72c6  cmp        eax, 2
  0x35a6e72c9  je         0x35a6e72d8
  0x35a6e72cb  add        rsp, 0x28
  0x35a6e72cf  pop        rbx
  0x35a6e72d0  pop        rbp
  0x35a6e72d1  ret        
  0x35a6e72d2  nop        word ptr [rax + rax]
  0x35a6e72d8  mov        rbx, qword ptr [rip + 0x2827e99]
  0x35a6e72df  lea        rcx, [rip + 0x2826c1a]
  0x35a6e72e6  call       rbx
  0x35a6e72e8  lea        rcx, [rip + 0x2826c39]
  0x35a6e72ef  mov        rax, rbx
  0x35a6e72f2  add        rsp, 0x28
  0x35a6e72f6  pop        rbx
  0x35a6e72f7  pop        rbp
  0x35a6e72f8  jmp        rax
  0x35a6e72fb  nop        dword ptr [rax + rax]
  0x35a6e7300  push       rbp
  0x35a6e7301  push       rsi
  0x35a6e7302  push       rbx
  0x35a6e7303  sub        rsp, 0x30
  0x35a6e7307  lea        rbp, [rsp + 0x30]
  0x35a6e730c  mov        ebx, ecx
  0x35a6e730e  xor        ecx, ecx
  0x35a6e7310  call       0x35a6e71c0
  0x35a6e7315  cmp        ebx, 9
  0x35a6e7318  jg         0x35a6e7358
  0x35a6e731a  lea        rdx, [rip + 0x2826b7f]
  0x35a6e7321  movsxd     rcx, ebx
  0x35a6e7324  mov        rax, qword ptr [rdx + rcx*8]
  0x35a6e7328  test       rax, rax
  0x35a6e732b  je         0x35a6e73a8
  0x35a6e732d  mov        r8, qword ptr [rax]
  0x35a6e7330  cmp        dword ptr [rip + 0x2826bb9], 2
  0x35a6e7337  mov        qword ptr [rdx + rcx*8], r8
  0x35a6e733b  jne        0x35a6e7391
  0x35a6e733d  mov        qword ptr [rbp - 8], rax
  0x35a6e7341  lea        rcx, [rip + 0x2826bb8]
  0x35a6e7348  call       qword ptr [rip + 0x2827e52]
  0x35a6e734e  mov        rax, qword ptr [rbp - 8]
  0x35a6e7352  jmp        0x35a6e7391
  0x35a6e7354  nop        dword ptr [rax]
  0x35a6e7358  mov        ecx, ebx
  0x35a6e735a  mov        esi, 1
  0x35a6e735f  shl        esi, cl
  0x35a6e7361  lea        eax, [rsi - 1]
  0x35a6e7364  cdqe       
  0x35a6e7366  lea        rcx, [rax*4 + 0x27]
  0x35a6e736e  shr        rcx, 3
  0x35a6e7372  mov        ecx, ecx
  0x35a6e7374  shl        rcx, 3
  0x35a6e7378  call       0x35a6e8730
  0x35a6e737d  test       rax, rax
  0x35a6e7380  je         0x35a6e7399
  0x35a6e7382  cmp        dword ptr [rip + 0x2826b67], 2
  0x35a6e7389  mov        dword ptr [rax + 8], ebx
  0x35a6e738c  mov        dword ptr [rax + 0xc], esi
  0x35a6e738f  je         0x35a6e733d
  0x35a6e7391  mov        qword ptr [rax + 0x10], 0
  0x35a6e7399  add        rsp, 0x30
  0x35a6e739d  pop        rbx
  0x35a6e739e  pop        rsi
  0x35a6e739f  pop        rbp
  0x35a6e73a0  ret        
  0x35a6e73a1  nop        dword ptr [rax]
  0x35a6e73a8  mov        ecx, ebx
  0x35a6e73aa  mov        esi, 1
  0x35a6e73af  lea        r8, [rip + 0x28261ea]
  0x35a6e73b6  shl        esi, cl
  0x35a6e73b8  lea        eax, [rsi - 1]
  0x35a6e73bb  cdqe       
  0x35a6e73bd  lea        rcx, [rax*4 + 0x27]
  0x35a6e73c5  mov        rax, qword ptr [rip + 0x1c94]
  0x35a6e73cc  shr        rcx, 3
  0x35a6e73d0  mov        rdx, rax
  0x35a6e73d3  sub        rdx, r8
  0x35a6e73d6  sar        rdx, 3
  0x35a6e73da  add        rdx, rcx
  0x35a6e73dd  cmp        rdx, 0x120
  0x35a6e73e4  ja         0x35a6e7374
  0x35a6e73e6  lea        rdx, [rax + rcx*8]
  0x35a6e73ea  mov        qword ptr [rip + 0x1c6f], rdx
  0x35a6e73f1  jmp        0x35a6e7382
  0x35a6e73f3  nop        word ptr cs:[rax + rax]
  0x35a6e73fe  nop        
  0x35a6e7400  push       rbp
  0x35a6e7401  push       rbx
  0x35a6e7402  sub        rsp, 0x28
  0x35a6e7406  lea        rbp, [rsp + 0x20]
  0x35a6e740b  mov        rbx, rcx
  0x35a6e740e  test       rcx, rcx
  0x35a6e7411  je         0x35a6e744e
  0x35a6e7413  cmp        dword ptr [rcx + 8], 9
  0x35a6e7417  jle        0x35a6e7428
  0x35a6e7419  add        rsp, 0x28
  0x35a6e741d  pop        rbx
  0x35a6e741e  pop        rbp
  0x35a6e741f  jmp        0x35a6e8718
  0x35a6e7424  nop        dword ptr [rax]
  0x35a6e7428  xor        ecx, ecx
  0x35a6e742a  call       0x35a6e71c0
  0x35a6e742f  movsxd     rdx, dword ptr [rbx + 8]
  0x35a6e7433  lea        rax, [rip + 0x2826a66]
  0x35a6e743a  cmp        dword ptr [rip + 0x2826aaf], 2
  0x35a6e7441  mov        rcx, qword ptr [rax + rdx*8]
  0x35a6e7445  mov        qword ptr [rax + rdx*8], rbx
  0x35a6e7449  mov        qword ptr [rbx], rcx
  0x35a6e744c  je         0x35a6e7458
  0x35a6e744e  add        rsp, 0x28
  0x35a6e7452  pop        rbx
  0x35a6e7453  pop        rbp
  0x35a6e7454  ret        
  0x35a6e7455  nop        dword ptr [rax]
  0x35a6e7458  lea        rcx, [rip + 0x2826aa1]
  0x35a6e745f  add        rsp, 0x28
  0x35a6e7463  pop        rbx
  0x35a6e7464  pop        rbp
  0x35a6e7465  jmp        qword ptr [rip + 0x2827d34]
  0x35a6e746c  nop        dword ptr [rax]
  0x35a6e7470  push       rbp
  0x35a6e7471  push       r12
  0x35a6e7473  push       rdi
  0x35a6e7474  push       rsi
  0x35a6e7475  push       rbx
  0x35a6e7476  sub        rsp, 0x20
  0x35a6e747a  lea        rbp, [rsp + 0x20]
  0x35a6e747f  mov        edi, dword ptr [rcx + 0x14]
  0x35a6e7482  mov        rbx, rcx
  0x35a6e7485  movsxd     rsi, r8d
  0x35a6e7488  movsxd     rdx, edx
  0x35a6e748b  xor        ecx, ecx
  0x35a6e748d  nop        dword ptr [rax]
  0x35a6e7490  mov        eax, dword ptr [rbx + rcx*4 + 0x18]
  0x35a6e7494  imul       rax, rdx
  0x35a6e7498  add        rax, rsi
  0x35a6e749b  mov        dword ptr [rbx + rcx*4 + 0x18], eax
  0x35a6e749f  mov        rsi, rax
  0x35a6e74a2  add        rcx, 1
  0x35a6e74a6  shr        rsi, 0x20
  0x35a6e74aa  cmp        edi, ecx
  0x35a6e74ac  jg         0x35a6e7490
  0x35a6e74ae  mov        r12, rbx
  0x35a6e74b1  test       rsi, rsi
  0x35a6e74b4  je         0x35a6e74cb
  0x35a6e74b6  cmp        dword ptr [rbx + 0xc], edi
  0x35a6e74b9  jle        0x35a6e74e0
  0x35a6e74bb  movsxd     rax, edi
  0x35a6e74be  add        edi, 1
  0x35a6e74c1  mov        r12, rbx
  0x35a6e74c4  mov        dword ptr [rbx + rax*4 + 0x18], esi
  0x35a6e74c8  mov        dword ptr [rbx + 0x14], edi
  0x35a6e74cb  mov        rax, r12
  0x35a6e74ce  add        rsp, 0x20
  0x35a6e74d2  pop        rbx
  0x35a6e74d3  pop        rsi
  0x35a6e74d4  pop        rdi
  0x35a6e74d5  pop        r12
  0x35a6e74d7  pop        rbp
  0x35a6e74d8  ret        
  0x35a6e74d9  nop        dword ptr [rax]
  0x35a6e74e0  mov        eax, dword ptr [rbx + 8]
  0x35a6e74e3  lea        ecx, [rax + 1]
  0x35a6e74e6  call       0x35a6e7300
  0x35a6e74eb  mov        r12, rax
  0x35a6e74ee  test       rax, rax
  0x35a6e74f1  je         0x35a6e74cb
  0x35a6e74f3  lea        rcx, [rax + 0x10]
  0x35a6e74f7  movsxd     rax, dword ptr [rbx + 0x14]
  0x35a6e74fb  lea        rdx, [rbx + 0x10]
  0x35a6e74ff  lea        r8, [rax*4 + 8]
  0x35a6e7507  call       0x35a6e8738
  0x35a6e750c  mov        rcx, rbx
  0x35a6e750f  mov        rbx, r12
  0x35a6e7512  call       0x35a6e7400
  0x35a6e7517  movsxd     rax, edi
  0x35a6e751a  add        edi, 1
  0x35a6e751d  mov        r12, rbx
  0x35a6e7520  mov        dword ptr [rbx + rax*4 + 0x18], esi
  0x35a6e7524  mov        dword ptr [rbx + 0x14], edi
  0x35a6e7527  jmp        0x35a6e74cb
  0x35a6e7529  nop        dword ptr [rax]
  0x35a6e7530  push       rbp
  0x35a6e7531  push       rbx
  0x35a6e7532  sub        rsp, 0x38
  0x35a6e7536  lea        rbp, [rsp + 0x30]
  0x35a6e753b  mov        ebx, ecx
  0x35a6e753d  xor        ecx, ecx
  0x35a6e753f  call       0x35a6e71c0
  0x35a6e7544  mov        rax, qword ptr [rip + 0x282695d]
  0x35a6e754b  test       rax, rax
  0x35a6e754e  je         0x35a6e7580
  0x35a6e7550  mov        rdx, qword ptr [rax]
  0x35a6e7553  cmp        dword ptr [rip + 0x2826996], 2
  0x35a6e755a  mov        qword ptr [rip + 0x2826947], rdx
  0x35a6e7561  je         0x35a6e75c8
  0x35a6e7563  mov        rdx, qword ptr [rip + 0x3106]
  0x35a6e756a  mov        dword ptr [rax + 0x18], ebx
  0x35a6e756d  mov        qword ptr [rax + 0x10], rdx
  0x35a6e7571  add        rsp, 0x38
  0x35a6e7575  pop        rbx
  0x35a6e7576  pop        rbp
  0x35a6e7577  ret        
  0x35a6e7578  nop        dword ptr [rax + rax]
  0x35a6e7580  mov        rax, qword ptr [rip + 0x1ad9]
  0x35a6e7587  lea        rcx, [rip + 0x2826012]
  0x35a6e758e  mov        rdx, rax
  0x35a6e7591  sub        rdx, rcx
  0x35a6e7594  sar        rdx, 3
  0x35a6e7598  add        rdx, 5
  0x35a6e759c  cmp        rdx, 0x120
  0x35a6e75a3  jbe        0x35a6e75e0
  0x35a6e75a5  mov        ecx, 0x28
  0x35a6e75aa  call       0x35a6e8730
  0x35a6e75af  test       rax, rax
  0x35a6e75b2  je         0x35a6e7571
  0x35a6e75b4  mov        rdx, qword ptr [rip + 0x30ad]
  0x35a6e75bb  cmp        dword ptr [rip + 0x282692e], 2
  0x35a6e75c2  mov        qword ptr [rax + 8], rdx
  0x35a6e75c6  jne        0x35a6e7563
  0x35a6e75c8  mov        qword ptr [rbp - 8], rax
  0x35a6e75cc  lea        rcx, [rip + 0x282692d]
  0x35a6e75d3  call       qword ptr [rip + 0x2827bc7]
  0x35a6e75d9  mov        rax, qword ptr [rbp - 8]
  0x35a6e75dd  jmp        0x35a6e7563
  0x35a6e75df  nop        
  0x35a6e75e0  lea        rdx, [rax + 0x28]
  0x35a6e75e4  mov        qword ptr [rip + 0x1a75], rdx
  0x35a6e75eb  jmp        0x35a6e75b4
  0x35a6e75ed  nop        dword ptr [rax]
  0x35a6e75f0  push       rbp
  0x35a6e75f1  push       r15
  0x35a6e75f3  push       r14
  0x35a6e75f5  push       r13
  0x35a6e75f7  push       r12
  0x35a6e75f9  push       rdi
  0x35a6e75fa  push       rsi
  0x35a6e75fb  push       rbx
  0x35a6e75fc  sub        rsp, 0x38
  0x35a6e7600  lea        rbp, [rsp + 0x30]
  0x35a6e7605  movsxd     r14, dword ptr [rcx + 0x14]
  0x35a6e7609  movsxd     r13, dword ptr [rdx + 0x14]
  0x35a6e760d  mov        r9, rcx
  0x35a6e7610  mov        r15, rdx
  0x35a6e7613  cmp        r14d, r13d
  0x35a6e7616  jl         0x35a6e7627
  0x35a6e7618  mov        eax, r13d
  0x35a6e761b  mov        r15, rcx
  0x35a6e761e  movsxd     r13, r14d
  0x35a6e7621  mov        r9, rdx
  0x35a6e7624  movsxd     r14, eax
  0x35a6e7627  mov        ecx, dword ptr [r15 + 8]
  0x35a6e762b  lea        ebx, [r13 + r14]
  0x35a6e7630  cmp        dword ptr [r15 + 0xc], ebx
  0x35a6e7634  jge        0x35a6e7639
  0x35a6e7636  add        ecx, 1
  0x35a6e7639  mov        qword ptr [rbp + 0x50], r9
  0x35a6e763d  call       0x35a6e7300
  0x35a6e7642  mov        rdi, rax
  0x35a6e7645  test       rax, rax
  0x35a6e7648  je         0x35a6e7743
  0x35a6e764e  lea        r12, [rax + 0x18]
  0x35a6e7652  movsxd     rax, ebx
  0x35a6e7655  mov        r9, qword ptr [rbp + 0x50]
  0x35a6e7659  lea        rsi, [r12 + rax*4]
  0x35a6e765d  cmp        r12, rsi
  0x35a6e7660  jae        0x35a6e768a
  0x35a6e7662  mov        rax, rsi
  0x35a6e7665  xor        edx, edx
  0x35a6e7667  mov        rcx, r12
  0x35a6e766a  mov        qword ptr [rbp + 0x50], r9
  0x35a6e766e  sub        rax, rdi
  0x35a6e7671  sub        rax, 0x19
  0x35a6e7675  shr        rax, 2
  0x35a6e7679  lea        r8, [rax*4 + 4]
  0x35a6e7681  call       0x35a6e8740
  0x35a6e7686  mov        r9, qword ptr [rbp + 0x50]
  0x35a6e768a  add        r9, 0x18
  0x35a6e768e  lea        r11, [r15 + 0x18]
  0x35a6e7692  lea        r14, [r9 + r14*4]
  0x35a6e7696  lea        r13, [r11 + r13*4]
  0x35a6e769a  cmp        r9, r14
  0x35a6e769d  jae        0x35a6e7728
  0x35a6e76a3  mov        rax, r13
  0x35a6e76a6  lea        r10, [r15 + 0x19]
  0x35a6e76aa  sub        rax, r15
  0x35a6e76ad  sub        rax, 0x19
  0x35a6e76b1  shr        rax, 2
  0x35a6e76b5  cmp        r13, r10
  0x35a6e76b8  lea        rdx, [rax*4 + 4]
  0x35a6e76c0  mov        eax, 4
  0x35a6e76c5  cmovae     rax, rdx
  0x35a6e76c9  mov        qword ptr [rbp - 8], rax
  0x35a6e76cd  jmp        0x35a6e76d9
  0x35a6e76cf  nop        
  0x35a6e76d0  add        r12, 4
  0x35a6e76d4  cmp        r9, r14
  0x35a6e76d7  jae        0x35a6e7728
  0x35a6e76d9  mov        r10d, dword ptr [r9]
  0x35a6e76dc  add        r9, 4
  0x35a6e76e0  test       r10d, r10d
  0x35a6e76e3  je         0x35a6e76d0
  0x35a6e76e5  mov        rcx, r12
  0x35a6e76e8  mov        rdx, r11
  0x35a6e76eb  xor        r8d, r8d
  0x35a6e76ee  nop        
  0x35a6e76f0  mov        eax, dword ptr [rdx]
  0x35a6e76f2  mov        r15d, dword ptr [rcx]
  0x35a6e76f5  add        rdx, 4
  0x35a6e76f9  add        rcx, 4
  0x35a6e76fd  imul       rax, r10
  0x35a6e7701  add        rax, r15
  0x35a6e7704  add        rax, r8
  0x35a6e7707  mov        r8, rax
  0x35a6e770a  mov        dword ptr [rcx - 4], eax
  0x35a6e770d  shr        r8, 0x20
  0x35a6e7711  cmp        rdx, r13
  0x35a6e7714  jb         0x35a6e76f0
  0x35a6e7716  mov        rax, qword ptr [rbp - 8]
  0x35a6e771a  add        r12, 4
  0x35a6e771e  mov        dword ptr [r12 + rax - 4], r8d
  0x35a6e7723  cmp        r9, r14
  0x35a6e7726  jb         0x35a6e76d9
  0x35a6e7728  test       ebx, ebx
  0x35a6e772a  jg         0x35a6e7735
  0x35a6e772c  jmp        0x35a6e7740
  0x35a6e772e  nop        
  0x35a6e7730  sub        ebx, 1
  0x35a6e7733  je         0x35a6e7740
  0x35a6e7735  mov        eax, dword ptr [rsi - 4]
  0x35a6e7738  sub        rsi, 4
  0x35a6e773c  test       eax, eax
  0x35a6e773e  je         0x35a6e7730
  0x35a6e7740  mov        dword ptr [rdi + 0x14], ebx
  0x35a6e7743  mov        rax, rdi
  0x35a6e7746  add        rsp, 0x38
  0x35a6e774a  pop        rbx
  0x35a6e774b  pop        rsi
  0x35a6e774c  pop        rdi
  0x35a6e774d  pop        r12
  0x35a6e774f  pop        r13
  0x35a6e7751  pop        r14
  0x35a6e7753  pop        r15
  0x35a6e7755  pop        rbp
  0x35a6e7756  ret        
  0x35a6e7757  nop        word ptr [rax + rax]
  0x35a6e7760  push       rbp
  0x35a6e7761  push       r12
  0x35a6e7763  push       rdi
  0x35a6e7764  push       rsi
  0x35a6e7765  push       rbx
  0x35a6e7766  sub        rsp, 0x20
  0x35a6e776a  lea        rbp, [rsp + 0x20]
  0x35a6e776f  mov        eax, edx
  0x35a6e7771  mov        rsi, rcx
  0x35a6e7774  mov        ebx, edx
  0x35a6e7776  and        eax, 3
  0x35a6e7779  jne        0x35a6e7840
  0x35a6e777f  sar        ebx, 2
  0x35a6e7782  mov        r12, rsi
  0x35a6e7785  je         0x35a6e77da
  0x35a6e7787  mov        rdi, qword ptr [rip + 0x2825df2]
  0x35a6e778e  test       rdi, rdi
  0x35a6e7791  je         0x35a6e7870
  0x35a6e7797  mov        r12, rsi
  0x35a6e779a  jmp        0x35a6e77af
  0x35a6e779c  nop        dword ptr [rax]
  0x35a6e77a0  sar        ebx, 1
  0x35a6e77a2  je         0x35a6e77da
  0x35a6e77a4  mov        rsi, qword ptr [rdi]
  0x35a6e77a7  test       rsi, rsi
  0x35a6e77aa  je         0x35a6e77f0
  0x35a6e77ac  mov        rdi, rsi
  0x35a6e77af  test       bl, 1
  0x35a6e77b2  je         0x35a6e77a0
  0x35a6e77b4  mov        rdx, rdi
  0x35a6e77b7  mov        rcx, r12
  0x35a6e77ba  call       0x35a6e75f0
  0x35a6e77bf  mov        rsi, rax
  0x35a6e77c2  test       rax, rax
  0x35a6e77c5  je         0x35a6e7868
  0x35a6e77cb  mov        rcx, r12
  0x35a6e77ce  mov        r12, rsi
  0x35a6e77d1  call       0x35a6e7400
  0x35a6e77d6  sar        ebx, 1
  0x35a6e77d8  jne        0x35a6e77a4
  0x35a6e77da  mov        rax, r12
  0x35a6e77dd  add        rsp, 0x20
  0x35a6e77e1  pop        rbx
  0x35a6e77e2  pop        rsi
  0x35a6e77e3  pop        rdi
  0x35a6e77e4  pop        r12
  0x35a6e77e6  pop        rbp
  0x35a6e77e7  ret        
  0x35a6e77e8  nop        dword ptr [rax + rax]
  0x35a6e77f0  mov        ecx, 1
  0x35a6e77f5  call       0x35a6e71c0
  0x35a6e77fa  mov        rsi, qword ptr [rdi]
  0x35a6e77fd  test       rsi, rsi
  0x35a6e7800  je         0x35a6e7820
  0x35a6e7802  cmp        dword ptr [rip + 0x28266e7], 2
  0x35a6e7809  jne        0x35a6e77ac
  0x35a6e780b  lea        rcx, [rip + 0x2826716]
  0x35a6e7812  call       qword ptr [rip + 0x2827988]
  0x35a6e7818  jmp        0x35a6e77ac
  0x35a6e781a  nop        word ptr [rax + rax]
  0x35a6e7820  mov        rdx, rdi
  0x35a6e7823  mov        rcx, rdi
  0x35a6e7826  call       0x35a6e75f0
  0x35a6e782b  mov        qword ptr [rdi], rax
  0x35a6e782e  mov        rsi, rax
  0x35a6e7831  test       rax, rax
  0x35a6e7834  je         0x35a6e7868
  0x35a6e7836  mov        qword ptr [rax], 0
  0x35a6e783d  jmp        0x35a6e7802
  0x35a6e783f  nop        
  0x35a6e7840  sub        eax, 1
  0x35a6e7843  lea        rdx, [rip + 0x2cd6]
  0x35a6e784a  xor        r8d, r8d
  0x35a6e784d  cdqe       
  0x35a6e784f  mov        edx, dword ptr [rdx + rax*4]
  0x35a6e7852  call       0x35a6e7470
  0x35a6e7857  mov        rsi, rax
  0x35a6e785a  test       rax, rax
  0x35a6e785d  jne        0x35a6e777f
  0x35a6e7863  nop        dword ptr [rax + rax]
  0x35a6e7868  xor        r12d, r12d
  0x35a6e786b  jmp        0x35a6e77da
  0x35a6e7870  mov        ecx, 1
  0x35a6e7875  call       0x35a6e71c0
  0x35a6e787a  mov        rdi, qword ptr [rip + 0x2825cff]
  0x35a6e7881  test       rdi, rdi
  0x35a6e7884  je         0x35a6e78a5
  0x35a6e7886  cmp        dword ptr [rip + 0x2826663], 2
  0x35a6e788d  jne        0x35a6e7797
  0x35a6e7893  lea        rcx, [rip + 0x282668e]
  0x35a6e789a  call       qword ptr [rip + 0x2827900]
  0x35a6e78a0  jmp        0x35a6e7797
  0x35a6e78a5  mov        ecx, 1
  0x35a6e78aa  call       0x35a6e7300
  0x35a6e78af  mov        rdi, rax
  0x35a6e78b2  test       rax, rax
  0x35a6e78b5  je         0x35a6e78d5
  0x35a6e78b7  movabs     rax, 0x27100000001
  0x35a6e78c1  mov        qword ptr [rip + 0x2825cb8], rdi
  0x35a6e78c8  mov        qword ptr [rdi + 0x14], rax
  0x35a6e78cc  mov        qword ptr [rdi], 0
  0x35a6e78d3  jmp        0x35a6e7886
  0x35a6e78d5  mov        qword ptr [rip + 0x2825ca0], 0
  0x35a6e78e0  jmp        0x35a6e7868
  0x35a6e78e2  nop        word ptr cs:[rax + rax]
  0x35a6e78ed  nop        dword ptr [rax]
  0x35a6e78f0  push       rbp
  0x35a6e78f1  push       r15
  0x35a6e78f3  push       r14
  0x35a6e78f5  push       r13
  0x35a6e78f7  push       r12
  0x35a6e78f9  push       rdi
  0x35a6e78fa  push       rsi
  0x35a6e78fb  push       rbx
  0x35a6e78fc  sub        rsp, 0x28
  0x35a6e7900  lea        rbp, [rsp + 0x20]
  0x35a6e7905  mov        r13, rcx
  0x35a6e7908  mov        esi, edx
  0x35a6e790a  mov        ecx, dword ptr [rcx + 8]
  0x35a6e790d  mov        r14d, edx
  0x35a6e7910  mov        ebx, dword ptr [r13 + 0x14]
  0x35a6e7914  sar        esi, 5
  0x35a6e7917  mov        eax, dword ptr [r13 + 0xc]
  0x35a6e791b  add        ebx, esi
  0x35a6e791d  lea        r12d, [rbx + 1]
  0x35a6e7921  cmp        r12d, eax
  0x35a6e7924  jle        0x35a6e793a
  0x35a6e7926  nop        word ptr cs:[rax + rax]
  0x35a6e7930  add        eax, eax
  0x35a6e7932  add        ecx, 1
  0x35a6e7935  cmp        r12d, eax
  0x35a6e7938  jg         0x35a6e7930
  0x35a6e793a  call       0x35a6e7300
  0x35a6e793f  mov        r15, rax
  0x35a6e7942  test       rax, rax
  0x35a6e7945  je         0x35a6e79ee
  0x35a6e794b  lea        rdi, [rax + 0x18]
  0x35a6e794f  test       esi, esi
  0x35a6e7951  jle        0x35a6e7967
  0x35a6e7953  shl        rsi, 2
  0x35a6e7957  mov        rcx, rdi
  0x35a6e795a  xor        edx, edx
  0x35a6e795c  mov        r8, rsi
  0x35a6e795f  add        rdi, rsi
  0x35a6e7962  call       0x35a6e8740
  0x35a6e7967  movsxd     rax, dword ptr [r13 + 0x14]
  0x35a6e796b  lea        rsi, [r13 + 0x18]
  0x35a6e796f  lea        r8, [rsi + rax*4]
  0x35a6e7973  and        r14d, 0x1f
  0x35a6e7977  je         0x35a6e7a08
  0x35a6e797d  mov        r10d, 0x20
  0x35a6e7983  mov        r9, rdi
  0x35a6e7986  xor        edx, edx
  0x35a6e7988  sub        r10d, r14d
  0x35a6e798b  nop        dword ptr [rax + rax]
  0x35a6e7990  mov        eax, dword ptr [rsi]
  0x35a6e7992  mov        ecx, r14d
  0x35a6e7995  add        r9, 4
  0x35a6e7999  add        rsi, 4
  0x35a6e799d  shl        eax, cl
  0x35a6e799f  mov        ecx, r10d
  0x35a6e79a2  or         eax, edx
  0x35a6e79a4  mov        dword ptr [r9 - 4], eax
  0x35a6e79a8  mov        edx, dword ptr [rsi - 4]
  0x35a6e79ab  shr        edx, cl
  0x35a6e79ad  cmp        rsi, r8
  0x35a6e79b0  jb         0x35a6e7990
  0x35a6e79b2  mov        rax, r8
  0x35a6e79b5  lea        rcx, [r13 + 0x19]
  0x35a6e79b9  sub        rax, r13
  0x35a6e79bc  sub        rax, 0x19
  0x35a6e79c0  shr        rax, 2
  0x35a6e79c4  cmp        r8, rcx
  0x35a6e79c7  mov        ecx, 4
  0x35a6e79cc  lea        rax, [rax*4 + 4]
  0x35a6e79d4  cmovb      rax, rcx
  0x35a6e79d8  mov        dword ptr [rdi + rax], edx
  0x35a6e79db  test       edx, edx
  0x35a6e79dd  jne        0x35a6e79e2
  0x35a6e79df  mov        r12d, ebx
  0x35a6e79e2  mov        dword ptr [r15 + 0x14], r12d
  0x35a6e79e6  mov        rcx, r13
  0x35a6e79e9  call       0x35a6e7400
  0x35a6e79ee  mov        rax, r15
  0x35a6e79f1  add        rsp, 0x28
  0x35a6e79f5  pop        rbx
  0x35a6e79f6  pop        rsi
  0x35a6e79f7  pop        rdi
  0x35a6e79f8  pop        r12
  0x35a6e79fa  pop        r13
  0x35a6e79fc  pop        r14
  0x35a6e79fe  pop        r15
  0x35a6e7a00  pop        rbp
  0x35a6e7a01  ret        
  0x35a6e7a02  nop        word ptr [rax + rax]
  0x35a6e7a08  movsd      dword ptr [rdi], dword ptr [rsi]
  0x35a6e7a09  cmp        rsi, r8
  0x35a6e7a0c  jae        0x35a6e79df
  0x35a6e7a0e  movsd      dword ptr [rdi], dword ptr [rsi]
  0x35a6e7a0f  cmp        rsi, r8
  0x35a6e7a12  jb         0x35a6e7a08
  0x35a6e7a14  jmp        0x35a6e79df
  0x35a6e7a16  nop        word ptr cs:[rax + rax]
  0x35a6e7a20  movsxd     rax, dword ptr [rdx + 0x14]
  0x35a6e7a24  mov        r9d, dword ptr [rcx + 0x14]
  0x35a6e7a28  sub        r9d, eax
  0x35a6e7a2b  jne        0x35a6e7a64
  0x35a6e7a2d  lea        r8, [rax*4]
  0x35a6e7a35  add        rcx, 0x18
  0x35a6e7a39  lea        rax, [rcx + r8]
  0x35a6e7a3d  lea        rdx, [rdx + r8 + 0x18]
  0x35a6e7a42  jmp        0x35a6e7a4d
  0x35a6e7a44  nop        dword ptr [rax]
  0x35a6e7a48  cmp        rcx, rax
  0x35a6e7a4b  jae        0x35a6e7a64
  0x35a6e7a4d  sub        rax, 4
  0x35a6e7a51  sub        rdx, 4
  0x35a6e7a55  mov        r10d, dword ptr [rdx]
  0x35a6e7a58  cmp        dword ptr [rax], r10d
  0x35a6e7a5b  je         0x35a6e7a48
  0x35a6e7a5d  sbb        r9d, r9d
  0x35a6e7a60  or         r9d, 1
  0x35a6e7a64  mov        eax, r9d
  0x35a6e7a67  ret        
  0x35a6e7a68  nop        dword ptr [rax + rax]
  0x35a6e7a70  push       rbp
  0x35a6e7a71  push       r14
  0x35a6e7a73  push       r13
  0x35a6e7a75  push       r12
  0x35a6e7a77  push       rdi
  0x35a6e7a78  push       rsi
  0x35a6e7a79  push       rbx
  0x35a6e7a7a  sub        rsp, 0x20
  0x35a6e7a7e  lea        rbp, [rsp + 0x20]
  0x35a6e7a83  movsxd     rax, dword ptr [rdx + 0x14]
  0x35a6e7a87  mov        rbx, rcx
  0x35a6e7a8a  mov        rsi, rdx
  0x35a6e7a8d  cmp        dword ptr [rcx + 0x14], eax
  0x35a6e7a90  jne        0x35a6e7be8
  0x35a6e7a96  lea        rdx, [rax*4]
  0x35a6e7a9e  lea        rcx, [rcx + 0x18]
  0x35a6e7aa2  lea        rax, [rcx + rdx]
  0x35a6e7aa6  lea        rdx, [rsi + rdx + 0x18]
  0x35a6e7aab  jmp        0x35a6e7ab9
  0x35a6e7aad  nop        dword ptr [rax]
  0x35a6e7ab0  cmp        rcx, rax
  0x35a6e7ab3  jae        0x35a6e7c00
  0x35a6e7ab9  sub        rax, 4
  0x35a6e7abd  sub        rdx, 4
  0x35a6e7ac1  mov        edi, dword ptr [rdx]
  0x35a6e7ac3  cmp        dword ptr [rax], edi
  0x35a6e7ac5  je         0x35a6e7ab0
  0x35a6e7ac7  mov        edi, 1
  0x35a6e7acc  jb         0x35a6e7ad9
  0x35a6e7ace  mov        rax, rsi
  0x35a6e7ad1  xor        edi, edi
  0x35a6e7ad3  mov        rsi, rbx
  0x35a6e7ad6  mov        rbx, rax
  0x35a6e7ad9  mov        ecx, dword ptr [rsi + 8]
  0x35a6e7adc  call       0x35a6e7300
  0x35a6e7ae1  mov        r9, rax
  0x35a6e7ae4  test       rax, rax
  0x35a6e7ae7  je         0x35a6e7bd3
  0x35a6e7aed  mov        dword ptr [rax + 0x10], edi
  0x35a6e7af0  movsxd     rax, dword ptr [rsi + 0x14]
  0x35a6e7af4  lea        r13, [rsi + 0x18]
  0x35a6e7af8  lea        r12, [r9 + 0x18]
  0x35a6e7afc  mov        ecx, 0x18
  0x35a6e7b01  xor        edx, edx
  0x35a6e7b03  mov        r10, rax
  0x35a6e7b06  lea        r11, [r13 + rax*4]
  0x35a6e7b0b  movsxd     rax, dword ptr [rbx + 0x14]
  0x35a6e7b0f  lea        r8, [rbx + rax*4 + 0x18]
  0x35a6e7b14  nop        dword ptr [rax]
  0x35a6e7b18  mov        edi, dword ptr [rbx + rcx]
  0x35a6e7b1b  mov        eax, dword ptr [rsi + rcx]
  0x35a6e7b1e  sub        rax, rdi
  0x35a6e7b21  sub        rax, rdx
  0x35a6e7b24  mov        dword ptr [r9 + rcx], eax
  0x35a6e7b28  mov        rdx, rax
  0x35a6e7b2b  add        rcx, 4
  0x35a6e7b2f  mov        edi, eax
  0x35a6e7b31  shr        rdx, 0x20
  0x35a6e7b35  lea        rax, [rcx + rbx]
  0x35a6e7b39  and        edx, 1
  0x35a6e7b3c  cmp        rax, r8
  0x35a6e7b3f  jb         0x35a6e7b18
  0x35a6e7b41  lea        rax, [rbx + 0x19]
  0x35a6e7b45  mov        ecx, 4
  0x35a6e7b4a  cmp        r8, rax
  0x35a6e7b4d  setae      sil
  0x35a6e7b51  sub        r8, rbx
  0x35a6e7b54  lea        r14, [r8 - 0x19]
  0x35a6e7b58  shr        r14, 2
  0x35a6e7b5c  test       sil, sil
  0x35a6e7b5f  lea        rax, [r14*4 + 4]
  0x35a6e7b67  cmove      rax, rcx
  0x35a6e7b6b  add        r13, rax
  0x35a6e7b6e  lea        r8, [r12 + rax]
  0x35a6e7b72  mov        rbx, r8
  0x35a6e7b75  mov        rcx, r13
  0x35a6e7b78  cmp        r13, r11
  0x35a6e7b7b  jae        0x35a6e7c20
  0x35a6e7b81  nop        dword ptr [rax]
  0x35a6e7b88  mov        eax, dword ptr [rcx]
  0x35a6e7b8a  add        rcx, 4
  0x35a6e7b8e  add        rbx, 4
  0x35a6e7b92  sub        rax, rdx
  0x35a6e7b95  mov        rdx, rax
  0x35a6e7b98  mov        dword ptr [rbx - 4], eax
  0x35a6e7b9b  mov        edi, eax
  0x35a6e7b9d  shr        rdx, 0x20
  0x35a6e7ba1  and        edx, 1
  0x35a6e7ba4  cmp        rcx, r11
  0x35a6e7ba7  jb         0x35a6e7b88
  0x35a6e7ba9  sub        r11, 1
  0x35a6e7bad  sub        r11, r13
  0x35a6e7bb0  and        r11, 0xfffffffffffffffc
  0x35a6e7bb4  lea        rax, [r8 + r11]
  0x35a6e7bb8  test       edi, edi
  0x35a6e7bba  jne        0x35a6e7bcf
  0x35a6e7bbc  nop        dword ptr [rax]
  0x35a6e7bc0  mov        edx, dword ptr [rax - 4]
  0x35a6e7bc3  sub        rax, 4
  0x35a6e7bc7  sub        r10d, 1
  0x35a6e7bcb  test       edx, edx
  0x35a6e7bcd  je         0x35a6e7bc0
  0x35a6e7bcf  mov        dword ptr [r9 + 0x14], r10d
  0x35a6e7bd3  mov        rax, r9
  0x35a6e7bd6  add        rsp, 0x20
  0x35a6e7bda  pop        rbx
  0x35a6e7bdb  pop        rsi
  0x35a6e7bdc  pop        rdi
  0x35a6e7bdd  pop        r12
  0x35a6e7bdf  pop        r13
  0x35a6e7be1  pop        r14
  0x35a6e7be3  pop        rbp
  0x35a6e7be4  ret        
  0x35a6e7be5  nop        dword ptr [rax]
  0x35a6e7be8  mov        edi, 1
  0x35a6e7bed  jns        0x35a6e7ace
  0x35a6e7bf3  jmp        0x35a6e7ad9
  0x35a6e7bf8  nop        dword ptr [rax + rax]
  0x35a6e7c00  xor        ecx, ecx
  0x35a6e7c02  call       0x35a6e7300
  0x35a6e7c07  mov        r9, rax
  0x35a6e7c0a  test       rax, rax
  0x35a6e7c0d  je         0x35a6e7bd3
  0x35a6e7c0f  mov        qword ptr [rax + 0x14], 1
  0x35a6e7c17  jmp        0x35a6e7bd3
  0x35a6e7c19  nop        dword ptr [rax]
  0x35a6e7c20  xor        eax, eax
  0x35a6e7c22  shl        r14, 2
  0x35a6e7c26  test       sil, sil
  0x35a6e7c29  cmove      r14, rax
  0x35a6e7c2d  lea        rax, [r12 + r14]
  0x35a6e7c31  jmp        0x35a6e7bb8
  0x35a6e7c33  nop        word ptr cs:[rax + rax]
  0x35a6e7c3e  nop        
  0x35a6e7c40  push       rbp
  0x35a6e7c41  push       rdi
  0x35a6e7c42  push       rsi
  0x35a6e7c43  push       rbx
  0x35a6e7c44  lea        rbp, [rsp]
  0x35a6e7c48  movsxd     rax, dword ptr [rcx + 0x14]
  0x35a6e7c4c  lea        r11, [rcx + 0x18]
  0x35a6e7c50  lea        r10, [r11 + rax*4]
  0x35a6e7c54  mov        r9d, dword ptr [r10 - 4]
  0x35a6e7c58  lea        rsi, [r10 - 4]
  0x35a6e7c5c  bsr        ecx, r9d
  0x35a6e7c60  mov        edi, ecx
  0x35a6e7c62  mov        ecx, 0x20
  0x35a6e7c67  xor        edi, 0x1f
  0x35a6e7c6a  mov        r8d, ecx
  0x35a6e7c6d  sub        r8d, edi
  0x35a6e7c70  mov        dword ptr [rdx], r8d
  0x35a6e7c73  cmp        edi, 0xa
  0x35a6e7c76  jle        0x35a6e7cf0
  0x35a6e7c78  lea        ebx, [rdi - 0xb]
  0x35a6e7c7b  cmp        r11, rsi
  0x35a6e7c7e  jae        0x35a6e7cd0
  0x35a6e7c80  mov        edx, dword ptr [r10 - 8]
  0x35a6e7c84  test       ebx, ebx
  0x35a6e7c86  je         0x35a6e7cd7
  0x35a6e7c88  sub        ecx, ebx
  0x35a6e7c8a  mov        eax, r9d
  0x35a6e7c8d  mov        esi, edx
  0x35a6e7c8f  mov        r8d, ecx
  0x35a6e7c92  mov        ecx, ebx
  0x35a6e7c94  shl        eax, cl
  0x35a6e7c96  mov        ecx, r8d
  0x35a6e7c99  shr        esi, cl
  0x35a6e7c9b  mov        ecx, ebx
  0x35a6e7c9d  or         eax, esi
  0x35a6e7c9f  shl        edx, cl
  0x35a6e7ca1  lea        rcx, [r10 - 8]
  0x35a6e7ca5  or         eax, 0x3ff00000
  0x35a6e7caa  shl        rax, 0x20
  0x35a6e7cae  cmp        r11, rcx
  0x35a6e7cb1  jae        0x35a6e7ce3
  0x35a6e7cb3  mov        r9d, dword ptr [r10 - 0xc]
  0x35a6e7cb7  mov        ecx, r8d
  0x35a6e7cba  shr        r9d, cl
  0x35a6e7cbd  or         edx, r9d
  0x35a6e7cc0  or         rax, rdx
  0x35a6e7cc3  movq       xmm0, rax
  0x35a6e7cc8  pop        rbx
  0x35a6e7cc9  pop        rsi
  0x35a6e7cca  pop        rdi
  0x35a6e7ccb  pop        rbp
  0x35a6e7ccc  ret        
  0x35a6e7ccd  nop        dword ptr [rax]
  0x35a6e7cd0  xor        edx, edx
  0x35a6e7cd2  cmp        edi, 0xb
  0x35a6e7cd5  jne        0x35a6e7d30
  0x35a6e7cd7  mov        eax, r9d
  0x35a6e7cda  or         eax, 0x3ff00000
  0x35a6e7cdf  shl        rax, 0x20
  0x35a6e7ce3  or         rax, rdx
  0x35a6e7ce6  movq       xmm0, rax
  0x35a6e7ceb  pop        rbx
  0x35a6e7cec  pop        rsi
  0x35a6e7ced  pop        rdi
  0x35a6e7cee  pop        rbp
  0x35a6e7cef  ret        
  0x35a6e7cf0  mov        ecx, 0xb
  0x35a6e7cf5  mov        eax, r9d
  0x35a6e7cf8  xor        r8d, r8d
  0x35a6e7cfb  sub        ecx, edi
  0x35a6e7cfd  shr        eax, cl
  0x35a6e7cff  or         eax, 0x3ff00000
  0x35a6e7d04  shl        rax, 0x20
  0x35a6e7d08  cmp        r11, rsi
  0x35a6e7d0b  jae        0x35a6e7d14
  0x35a6e7d0d  mov        r8d, dword ptr [r10 - 8]
  0x35a6e7d11  shr        r8d, cl
  0x35a6e7d14  lea        ecx, [rdi + 0x15]
  0x35a6e7d17  mov        edx, r9d
  0x35a6e7d1a  shl        edx, cl
  0x35a6e7d1c  or         edx, r8d
  0x35a6e7d1f  or         rax, rdx
  0x35a6e7d22  movq       xmm0, rax
  0x35a6e7d27  pop        rbx
  0x35a6e7d28  pop        rsi
  0x35a6e7d29  pop        rdi
  0x35a6e7d2a  pop        rbp
  0x35a6e7d2b  ret        
  0x35a6e7d2c  nop        dword ptr [rax]
  0x35a6e7d30  mov        eax, r9d
  0x35a6e7d33  mov        ecx, ebx
  0x35a6e7d35  xor        edx, edx
  0x35a6e7d37  shl        eax, cl
  0x35a6e7d39  or         eax, 0x3ff00000
  0x35a6e7d3e  shl        rax, 0x20
  0x35a6e7d42  or         rax, rdx
  0x35a6e7d45  movq       xmm0, rax
  0x35a6e7d4a  pop        rbx
  0x35a6e7d4b  pop        rsi
  0x35a6e7d4c  pop        rdi
  0x35a6e7d4d  pop        rbp
  0x35a6e7d4e  ret        
  0x35a6e7d4f  nop        
  0x35a6e7d50  push       rbp
  0x35a6e7d51  push       rsi
  0x35a6e7d52  push       rbx
  0x35a6e7d53  sub        rsp, 0x30
  0x35a6e7d57  lea        rbp, [rsp + 0x20]
  0x35a6e7d5c  movups     xmmword ptr [rbp], xmm6
  0x35a6e7d60  mov        ecx, 1
  0x35a6e7d65  mov        rsi, rdx
  0x35a6e7d68  movupd     xmm6, xmm0
  0x35a6e7d6c  mov        rbx, r8
  0x35a6e7d6f  call       0x35a6e7300
  0x35a6e7d74  mov        rdx, rax
  0x35a6e7d77  test       rax, rax
  0x35a6e7d7a  je         0x35a6e7e14
  0x35a6e7d80  movq       rax, xmm6
  0x35a6e7d85  mov        rcx, rax
  0x35a6e7d88  shr        rcx, 0x20
  0x35a6e7d8c  mov        r9d, ecx
  0x35a6e7d8f  shr        ecx, 0x14
  0x35a6e7d92  and        r9d, 0xfffff
  0x35a6e7d99  mov        r8d, r9d
  0x35a6e7d9c  or         r8d, 0x100000
  0x35a6e7da3  and        ecx, 0x7ff
  0x35a6e7da9  cmovne     r9d, r8d
  0x35a6e7dad  mov        r10d, ecx
  0x35a6e7db0  test       eax, eax
  0x35a6e7db2  je         0x35a6e7e28
  0x35a6e7db4  xor        r8d, r8d
  0x35a6e7db7  tzcnt      r8d, eax
  0x35a6e7dbc  mov        ecx, r8d
  0x35a6e7dbf  shr        eax, cl
  0x35a6e7dc1  test       r8d, r8d
  0x35a6e7dc4  je         0x35a6e7ddd
  0x35a6e7dc6  mov        ecx, 0x20
  0x35a6e7dcb  mov        r11d, r9d
  0x35a6e7dce  sub        ecx, r8d
  0x35a6e7dd1  shl        r11d, cl
  0x35a6e7dd4  mov        ecx, r8d
  0x35a6e7dd7  or         eax, r11d
  0x35a6e7dda  shr        r9d, cl
  0x35a6e7ddd  mov        dword ptr [rdx + 0x18], eax
  0x35a6e7de0  cmp        r9d, 1
  0x35a6e7de4  mov        eax, 1
  0x35a6e7de9  sbb        eax, -1
  0x35a6e7dec  mov        dword ptr [rdx + 0x1c], r9d
  0x35a6e7df0  mov        dword ptr [rdx + 0x14], eax
  0x35a6e7df3  test       r10d, r10d
  0x35a6e7df6  jne        0x35a6e7e47
  0x35a6e7df8  movsxd     rcx, eax
  0x35a6e7dfb  sub        r8d, 0x432
  0x35a6e7e02  bsr        ecx, dword ptr [rdx + rcx*4 + 0x14]
  0x35a6e7e07  shl        eax, 5
  0x35a6e7e0a  mov        dword ptr [rsi], r8d
  0x35a6e7e0d  xor        ecx, 0x1f
  0x35a6e7e10  sub        eax, ecx
  0x35a6e7e12  mov        dword ptr [rbx], eax
  0x35a6e7e14  movups     xmm6, xmmword ptr [rbp]
  0x35a6e7e18  mov        rax, rdx
  0x35a6e7e1b  add        rsp, 0x30
  0x35a6e7e1f  pop        rbx
  0x35a6e7e20  pop        rsi
  0x35a6e7e21  pop        rbp
  0x35a6e7e22  ret        
  0x35a6e7e23  nop        dword ptr [rax + rax]
  0x35a6e7e28  xor        ecx, ecx
  0x35a6e7e2a  mov        eax, 1
  0x35a6e7e2f  tzcnt      ecx, r9d
  0x35a6e7e34  mov        dword ptr [rdx + 0x14], eax
  0x35a6e7e37  shr        r9d, cl
  0x35a6e7e3a  lea        r8d, [rcx + 0x20]
  0x35a6e7e3e  mov        dword ptr [rdx + 0x18], r9d
  0x35a6e7e42  test       r10d, r10d
  0x35a6e7e45  je         0x35a6e7df8
  0x35a6e7e47  lea        eax, [r10 + r8 - 0x433]
  0x35a6e7e4f  mov        dword ptr [rsi], eax
  0x35a6e7e51  mov        eax, 0x35
  0x35a6e7e56  sub        eax, r8d
  0x35a6e7e59  mov        dword ptr [rbx], eax
  0x35a6e7e5b  movups     xmm6, xmmword ptr [rbp]
  0x35a6e7e5f  mov        rax, rdx
  0x35a6e7e62  add        rsp, 0x30
  0x35a6e7e66  pop        rbx
  0x35a6e7e67  pop        rsi
  0x35a6e7e68  pop        rbp
  0x35a6e7e69  ret        
  0x35a6e7e6a  nop        word ptr [rax + rax]
  0x35a6e7e70  mov        rax, rcx
  0x35a6e7e73  lea        rcx, [rdx + 1]
  0x35a6e7e77  movzx      edx, byte ptr [rdx]
  0x35a6e7e7a  mov        byte ptr [rax], dl
  0x35a6e7e7c  test       dl, dl
  0x35a6e7e7e  je         0x35a6e7e91
  0x35a6e7e80  movzx      edx, byte ptr [rcx]
  0x35a6e7e83  add        rax, 1
  0x35a6e7e87  add        rcx, 1
  0x35a6e7e8b  mov        byte ptr [rax], dl
  0x35a6e7e8d  test       dl, dl
  0x35a6e7e8f  jne        0x35a6e7e80
  0x35a6e7e91  ret        
  0x35a6e7e92  nop        
  0x35a6e7e93  nop        
  0x35a6e7e94  nop        
  0x35a6e7e95  nop        
  0x35a6e7e96  nop        
  0x35a6e7e97  nop        
  0x35a6e7e98  nop        
  0x35a6e7e99  nop        
  0x35a6e7e9a  nop        
  0x35a6e7e9b  nop        
  0x35a6e7e9c  nop        
  0x35a6e7e9d  nop        
  0x35a6e7e9e  nop        
  0x35a6e7e9f  nop        
  0x35a6e7ea0  xor        r8d, r8d
  0x35a6e7ea3  mov        rax, rcx
  0x35a6e7ea6  test       rdx, rdx
  0x35a6e7ea9  jne        0x35a6e7ebf
  0x35a6e7eab  jmp        0x35a6e7ec4
  0x35a6e7ead  nop        dword ptr [rax]
  0x35a6e7eb0  add        rax, 1
  0x35a6e7eb4  mov        r8, rax
  0x35a6e7eb7  sub        r8, rcx
  0x35a6e7eba  cmp        r8, rdx
  0x35a6e7ebd  jae        0x35a6e7ec4
  0x35a6e7ebf  cmp        byte ptr [rax], 0
  0x35a6e7ec2  jne        0x35a6e7eb0
  0x35a6e7ec4  mov        rax, r8
  0x35a6e7ec7  ret        
  0x35a6e7ec8  nop        
  0x35a6e7ec9  nop        
  0x35a6e7eca  nop        
  0x35a6e7ecb  nop        
  0x35a6e7ecc  nop        
  0x35a6e7ecd  nop        
  0x35a6e7ece  nop        
  0x35a6e7ecf  nop        
  0x35a6e7ed0  xor        r8d, r8d
  0x35a6e7ed3  mov        rax, rdx
  0x35a6e7ed6  test       rdx, rdx
  0x35a6e7ed9  jne        0x35a6e7ee9
  0x35a6e7edb  jmp        0x35a6e7ef4
  0x35a6e7edd  nop        dword ptr [rax]
  0x35a6e7ee0  add        r8, 1
  0x35a6e7ee4  cmp        rax, r8
  0x35a6e7ee7  je         0x35a6e7ef4
  0x35a6e7ee9  cmp        word ptr [rcx + r8*2], 0
  0x35a6e7eef  jne        0x35a6e7ee0
  0x35a6e7ef1  mov        rax, r8
  0x35a6e7ef4  ret        
  0x35a6e7ef5  nop        
  0x35a6e7ef6  nop        
  0x35a6e7ef7  nop        
  0x35a6e7ef8  nop        
  0x35a6e7ef9  nop        
  0x35a6e7efa  nop        
  0x35a6e7efb  nop        
  0x35a6e7efc  nop        
  0x35a6e7efd  nop        
  0x35a6e7efe  nop        
  0x35a6e7eff  nop        
  0x35a6e7f00  push       rbp
  0x35a6e7f01  push       rbx
  0x35a6e7f02  sub        rsp, 0x28
  0x35a6e7f06  lea        rbp, [rsp + 0x20]
  0x35a6e7f0b  mov        rbx, rcx
  0x35a6e7f0e  xor        ecx, ecx
  0x35a6e7f10  call       0x35a6e7fe0
  0x35a6e7f15  cmp        rbx, rax
  0x35a6e7f18  jb         0x35a6e7f29
  0x35a6e7f1a  mov        ecx, 0x13
  0x35a6e7f1f  call       0x35a6e7fe0
  0x35a6e7f24  cmp        rbx, rax
  0x35a6e7f27  jbe        0x35a6e7f40
  0x35a6e7f29  lea        rcx, [rbx + 0x30]
  0x35a6e7f2d  add        rsp, 0x28
  0x35a6e7f31  pop        rbx
  0x35a6e7f32  pop        rbp
  0x35a6e7f33  jmp        qword ptr [rip + 0x2827246]
  0x35a6e7f3a  nop        word ptr [rax + rax]
  0x35a6e7f40  xor        ecx, ecx
  0x35a6e7f42  call       0x35a6e7fe0
  0x35a6e7f47  mov        rdx, rax
  0x35a6e7f4a  mov        rax, rbx
  0x35a6e7f4d  sub        rax, rdx
  0x35a6e7f50  sar        rax, 4
  0x35a6e7f54  imul       eax, eax, 0xaaaaaaab
  0x35a6e7f5a  lea        ecx, [rax + 0x10]
  0x35a6e7f5d  call       0x35a6e86f0
  0x35a6e7f62  or         dword ptr [rbx + 0x18], 0x8000
  0x35a6e7f69  add        rsp, 0x28
  0x35a6e7f6d  pop        rbx
  0x35a6e7f6e  pop        rbp
  0x35a6e7f6f  ret        
  0x35a6e7f70  push       rbp
  0x35a6e7f71  push       rbx
  0x35a6e7f72  sub        rsp, 0x28
  0x35a6e7f76  lea        rbp, [rsp + 0x20]
  0x35a6e7f7b  mov        rbx, rcx
  0x35a6e7f7e  xor        ecx, ecx
  0x35a6e7f80  call       0x35a6e7fe0
  0x35a6e7f85  cmp        rbx, rax
  0x35a6e7f88  jb         0x35a6e7f99
  0x35a6e7f8a  mov        ecx, 0x13
  0x35a6e7f8f  call       0x35a6e7fe0
  0x35a6e7f94  cmp        rbx, rax
  0x35a6e7f97  jbe        0x35a6e7fb0
  0x35a6e7f99  lea        rcx, [rbx + 0x30]
  0x35a6e7f9d  add        rsp, 0x28
  0x35a6e7fa1  pop        rbx
  0x35a6e7fa2  pop        rbp
  0x35a6e7fa3  jmp        qword ptr [rip + 0x28271f6]
  0x35a6e7faa  nop        word ptr [rax + rax]
  0x35a6e7fb0  and        dword ptr [rbx + 0x18], 0xffff7fff
  0x35a6e7fb7  xor        ecx, ecx
  0x35a6e7fb9  call       0x35a6e7fe0
  0x35a6e7fbe  sub        rbx, rax
  0x35a6e7fc1  sar        rbx, 4
  0x35a6e7fc5  imul       ebx, ebx, 0xaaaaaaab
  0x35a6e7fcb  lea        ecx, [rbx + 0x10]
  0x35a6e7fce  add        rsp, 0x28
  0x35a6e7fd2  pop        rbx
  0x35a6e7fd3  pop        rbp
  0x35a6e7fd4  jmp        0x35a6e86f8
  0x35a6e7fd9  nop        
  0x35a6e7fda  nop        
  0x35a6e7fdb  nop        
  0x35a6e7fdc  nop        
  0x35a6e7fdd  nop        
  0x35a6e7fde  nop        
  0x35a6e7fdf  nop        
  0x35a6e7fe0  push       rbp
  0x35a6e7fe1  push       rbx
  0x35a6e7fe2  sub        rsp, 0x28
  0x35a6e7fe6  lea        rbp, [rsp + 0x20]
  0x35a6e7feb  mov        ebx, ecx
  0x35a6e7fed  call       0x35a6e86d0
  0x35a6e7ff2  mov        ecx, ebx
  0x35a6e7ff4  lea        rdx, [rcx + rcx*2]
  0x35a6e7ff8  shl        rdx, 4
  0x35a6e7ffc  add        rax, rdx
  0x35a6e7fff  add        rsp, 0x28
  0x35a6e8003  pop        rbx
  0x35a6e8004  pop        rbp
  0x35a6e8005  ret        
  0x35a6e8006  nop        
  0x35a6e8007  nop        
  0x35a6e8008  nop        
  0x35a6e8009  nop        
  0x35a6e800a  nop        
  0x35a6e800b  nop        
  0x35a6e800c  nop        
  0x35a6e800d  nop        
  0x35a6e800e  nop        
  0x35a6e800f  nop        
  0x35a6e8010  push       rbp
  0x35a6e8011  mov        rbp, rsp
  0x35a6e8014  sub        rsp, 0x50
  0x35a6e8018  mov        rax, rcx
  0x35a6e801b  mov        word ptr [rbp + 0x18], dx
  0x35a6e801f  mov        ecx, r8d
  0x35a6e8022  test       r8d, r8d
  0x35a6e8025  jne        0x35a6e8040
  0x35a6e8027  cmp        dx, 0xff
  0x35a6e802c  ja         0x35a6e8080
  0x35a6e802e  mov        byte ptr [rax], dl
  0x35a6e8030  mov        eax, 1
  0x35a6e8035  add        rsp, 0x50
  0x35a6e8039  pop        rbp
  0x35a6e803a  ret        
  0x35a6e803b  nop        dword ptr [rax + rax]
  0x35a6e8040  lea        rdx, [rbp - 4]
  0x35a6e8044  mov        dword ptr [rsp + 0x28], r9d
  0x35a6e8049  lea        r8, [rbp + 0x18]
  0x35a6e804d  mov        r9d, 1
  0x35a6e8053  mov        qword ptr [rsp + 0x38], rdx
  0x35a6e8058  xor        edx, edx
  0x35a6e805a  mov        dword ptr [rbp - 4], 0
  0x35a6e8061  mov        qword ptr [rsp + 0x30], 0
  0x35a6e806a  mov        qword ptr [rsp + 0x20], rax
  0x35a6e806f  call       qword ptr [rip + 0x282715b]
  0x35a6e8075  test       eax, eax
  0x35a6e8077  je         0x35a6e8080
  0x35a6e8079  mov        edx, dword ptr [rbp - 4]
  0x35a6e807c  test       edx, edx
  0x35a6e807e  je         0x35a6e8035
  0x35a6e8080  call       0x35a6e86e0
  0x35a6e8085  mov        dword ptr [rax], 0x2a
  0x35a6e808b  mov        eax, 0xffffffff
  0x35a6e8090  add        rsp, 0x50
  0x35a6e8094  pop        rbp
  0x35a6e8095  ret        
  0x35a6e8096  nop        word ptr cs:[rax + rax]
  0x35a6e80a0  push       rbp
  0x35a6e80a1  push       rdi
  0x35a6e80a2  push       rsi
  0x35a6e80a3  push       rbx
  0x35a6e80a4  sub        rsp, 0x38
  0x35a6e80a8  lea        rbp, [rsp + 0x30]
  0x35a6e80ad  test       rcx, rcx
  0x35a6e80b0  mov        rbx, rcx
  0x35a6e80b3  lea        rax, [rbp - 5]
  0x35a6e80b7  mov        esi, edx
  0x35a6e80b9  cmove      rbx, rax
  0x35a6e80bd  call       0x35a6e86c8
  0x35a6e80c2  mov        edi, eax
  0x35a6e80c4  call       0x35a6e86c0
  0x35a6e80c9  movzx      edx, si
  0x35a6e80cc  mov        r9d, edi
  0x35a6e80cf  mov        rcx, rbx
  0x35a6e80d2  mov        r8d, eax
  0x35a6e80d5  call       0x35a6e8010
  0x35a6e80da  cdqe       
  0x35a6e80dc  add        rsp, 0x38
  0x35a6e80e0  pop        rbx
  0x35a6e80e1  pop        rsi
  0x35a6e80e2  pop        rdi
  0x35a6e80e3  pop        rbp
  0x35a6e80e4  ret        
  0x35a6e80e5  nop        word ptr cs:[rax + rax]
  0x35a6e80f0  push       rbp
  0x35a6e80f1  push       r15
  0x35a6e80f3  push       r14
  0x35a6e80f5  push       r13
  0x35a6e80f7  push       r12
  0x35a6e80f9  push       rdi
  0x35a6e80fa  push       rsi
  0x35a6e80fb  push       rbx
  0x35a6e80fc  sub        rsp, 0x38
  0x35a6e8100  lea        rbp, [rsp + 0x30]
  0x35a6e8105  xor        r14d, r14d
  0x35a6e8108  mov        r12, rdx
  0x35a6e810b  mov        r15, rcx
  0x35a6e810e  mov        rdi, r8
  0x35a6e8111  call       0x35a6e86c0
  0x35a6e8116  mov        ebx, eax
  0x35a6e8118  call       0x35a6e86c8
  0x35a6e811d  mov        r13, qword ptr [r12]
  0x35a6e8121  mov        esi, eax
  0x35a6e8123  test       r13, r13
  0x35a6e8126  je         0x35a6e817a
  0x35a6e8128  test       r15, r15
  0x35a6e812b  je         0x35a6e8190
  0x35a6e812d  test       rdi, rdi
  0x35a6e8130  jne        0x35a6e815c
  0x35a6e8132  jmp        0x35a6e81d0
  0x35a6e8137  nop        word ptr [rax + rax]
  0x35a6e8140  cdqe       
  0x35a6e8142  add        r15, rax
  0x35a6e8145  add        r14, rax
  0x35a6e8148  cmp        byte ptr [r15 - 1], 0
  0x35a6e814d  je         0x35a6e81e0
  0x35a6e8153  add        r13, 2
  0x35a6e8157  cmp        r14, rdi
  0x35a6e815a  jae        0x35a6e81d0
  0x35a6e815c  movzx      edx, word ptr [r13]
  0x35a6e8161  mov        r9d, esi
  0x35a6e8164  mov        r8d, ebx
  0x35a6e8167  mov        rcx, r15
  0x35a6e816a  call       0x35a6e8010
  0x35a6e816f  test       eax, eax
  0x35a6e8171  jg         0x35a6e8140
  0x35a6e8173  mov        r14, 0xffffffffffffffff
  0x35a6e817a  mov        rax, r14
  0x35a6e817d  add        rsp, 0x38
  0x35a6e8181  pop        rbx
  0x35a6e8182  pop        rsi
  0x35a6e8183  pop        rdi
  0x35a6e8184  pop        r12
  0x35a6e8186  pop        r13
  0x35a6e8188  pop        r14
  0x35a6e818a  pop        r15
  0x35a6e818c  pop        rbp
  0x35a6e818d  ret        
  0x35a6e818e  nop        
  0x35a6e8190  lea        rdi, [rbp - 5]
  0x35a6e8194  jmp        0x35a6e81b6
  0x35a6e8196  nop        word ptr cs:[rax + rax]
  0x35a6e81a0  movsxd     rdx, eax
  0x35a6e81a3  sub        eax, 1
  0x35a6e81a6  cdqe       
  0x35a6e81a8  add        r14, rdx
  0x35a6e81ab  cmp        byte ptr [rbp + rax - 5], 0
  0x35a6e81b0  je         0x35a6e81f0
  0x35a6e81b2  add        r13, 2
  0x35a6e81b6  movzx      edx, word ptr [r13]
  0x35a6e81bb  mov        r9d, esi
  0x35a6e81be  mov        r8d, ebx
  0x35a6e81c1  mov        rcx, rdi
  0x35a6e81c4  call       0x35a6e8010
  0x35a6e81c9  test       eax, eax
  0x35a6e81cb  jg         0x35a6e81a0
  0x35a6e81cd  jmp        0x35a6e8173
  0x35a6e81cf  nop        
  0x35a6e81d0  mov        qword ptr [r12], r13
  0x35a6e81d4  jmp        0x35a6e817a
  0x35a6e81d6  nop        word ptr cs:[rax + rax]
  0x35a6e81e0  mov        qword ptr [r12], 0
  0x35a6e81e8  sub        r14, 1
  0x35a6e81ec  jmp        0x35a6e817a
  0x35a6e81ee  nop        
  0x35a6e81f0  sub        r14, 1
  0x35a6e81f4  jmp        0x35a6e817a
  0x35a6e81f6  nop        
  0x35a6e81f7  nop        
  0x35a6e81f8  nop        
  0x35a6e81f9  nop        
  0x35a6e81fa  nop        
  0x35a6e81fb  nop        
  0x35a6e81fc  nop        
  0x35a6e81fd  nop        
  0x35a6e81fe  nop        
  0x35a6e81ff  nop        
  0x35a6e8200  test       rcx, rcx
  0x35a6e8203  je         0x35a6e8217
  0x35a6e8205  pxor       xmm0, xmm0
  0x35a6e8209  xor        eax, eax
  0x35a6e820b  mov        qword ptr [rcx + 0x10], 0
  0x35a6e8213  movups     xmmword ptr [rcx], xmm0
  0x35a6e8216  ret        
  0x35a6e8217  mov        eax, 0xffffffff
  0x35a6e821c  ret        
  0x35a6e821d  nop        dword ptr [rax]
  0x35a6e8220  push       rbp
  0x35a6e8221  push       r12
  0x35a6e8223  push       rdi
  0x35a6e8224  push       rsi
  0x35a6e8225  push       rbx
  0x35a6e8226  sub        rsp, 0x20
  0x35a6e822a  lea        rbp, [rsp + 0x20]
  0x35a6e822f  mov        rbx, rcx
  0x35a6e8232  mov        rdi, rdx
  0x35a6e8235  test       rcx, rcx
  0x35a6e8238  je         0x35a6e82f0
  0x35a6e823e  mov        ecx, 8
  0x35a6e8243  call       0x35a6e86f0
  0x35a6e8248  cmp        qword ptr [rbx], 0
  0x35a6e824c  je         0x35a6e82b8
  0x35a6e824e  mov        rdx, qword ptr [rbx + 8]
  0x35a6e8252  mov        rax, qword ptr [rbx + 0x10]
  0x35a6e8256  cmp        rdx, rax
  0x35a6e8259  je         0x35a6e8280
  0x35a6e825b  lea        rax, [rdx + 8]
  0x35a6e825f  mov        ecx, 8
  0x35a6e8264  mov        qword ptr [rbx + 8], rax
  0x35a6e8268  mov        qword ptr [rdx], rdi
  0x35a6e826b  call       0x35a6e86f8
  0x35a6e8270  xor        eax, eax
  0x35a6e8272  add        rsp, 0x20
  0x35a6e8276  pop        rbx
  0x35a6e8277  pop        rsi
  0x35a6e8278  pop        rdi
  0x35a6e8279  pop        r12
  0x35a6e827b  pop        rbp
  0x35a6e827c  ret        
  0x35a6e827d  nop        dword ptr [rax]
  0x35a6e8280  mov        rcx, qword ptr [rbx]
  0x35a6e8283  mov        rsi, rdx
  0x35a6e8286  sub        rsi, rcx
  0x35a6e8289  mov        r12, rsi
  0x35a6e828c  sar        r12, 3
  0x35a6e8290  shl        r12, 4
  0x35a6e8294  mov        rdx, r12
  0x35a6e8297  call       0x35a6e8748
  0x35a6e829c  test       rax, rax
  0x35a6e829f  je         0x35a6e82e6
  0x35a6e82a1  mov        qword ptr [rbx], rax
  0x35a6e82a4  lea        rdx, [rax + rsi]
  0x35a6e82a8  add        rax, r12
  0x35a6e82ab  mov        qword ptr [rbx + 0x10], rax
  0x35a6e82af  jmp        0x35a6e825b
  0x35a6e82b1  nop        dword ptr [rax]
  0x35a6e82b8  mov        edx, 8
  0x35a6e82bd  mov        ecx, 0x20
  0x35a6e82c2  call       0x35a6e8708
  0x35a6e82c7  mov        qword ptr [rbx], rax
  0x35a6e82ca  mov        rdx, rax
  0x35a6e82cd  test       rax, rax
  0x35a6e82d0  je         0x35a6e82e6
  0x35a6e82d2  mov        qword ptr [rbx + 8], rax
  0x35a6e82d6  lea        rax, [rax + 0x100]
  0x35a6e82dd  mov        qword ptr [rbx + 0x10], rax
  0x35a6e82e1  jmp        0x35a6e8256
  0x35a6e82e6  mov        ecx, 8
  0x35a6e82eb  call       0x35a6e86f8
  0x35a6e82f0  or         eax, 0xffffffff
  0x35a6e82f3  jmp        0x35a6e8272
  0x35a6e82f8  nop        dword ptr [rax + rax]
  0x35a6e8300  push       rbp
  0x35a6e8301  push       rdi
  0x35a6e8302  push       rsi
  0x35a6e8303  push       rbx
  0x35a6e8304  sub        rsp, 0x28
  0x35a6e8308  lea        rbp, [rsp + 0x20]
  0x35a6e830d  mov        rdi, rcx
  0x35a6e8310  mov        ecx, 8
  0x35a6e8315  call       0x35a6e86f0
  0x35a6e831a  mov        rsi, qword ptr [rdi]
  0x35a6e831d  pxor       xmm0, xmm0
  0x35a6e8321  mov        rbx, qword ptr [rdi + 8]
  0x35a6e8325  mov        qword ptr [rdi + 0x10], 0
  0x35a6e832d  mov        ecx, 8
  0x35a6e8332  movups     xmmword ptr [rdi], xmm0
  0x35a6e8335  call       0x35a6e86f8
  0x35a6e833a  test       rsi, rsi
  0x35a6e833d  je         0x35a6e8363
  0x35a6e833f  sub        rbx, 8
  0x35a6e8343  cmp        rbx, rsi
  0x35a6e8346  jb         0x35a6e835b
  0x35a6e8348  mov        rax, qword ptr [rbx]
  0x35a6e834b  test       rax, rax
  0x35a6e834e  je         0x35a6e833f
  0x35a6e8350  call       rax
  0x35a6e8352  sub        rbx, 8
  0x35a6e8356  cmp        rbx, rsi
  0x35a6e8359  jae        0x35a6e8348
  0x35a6e835b  mov        rcx, rsi
  0x35a6e835e  call       0x35a6e8718
  0x35a6e8363  xor        eax, eax
  0x35a6e8365  add        rsp, 0x28
  0x35a6e8369  pop        rbx
  0x35a6e836a  pop        rsi
  0x35a6e836b  pop        rdi
  0x35a6e836c  pop        rbp
  0x35a6e836d  ret        
  0x35a6e836e  nop        
  0x35a6e836f  nop        
  0x35a6e8370  push       rbp
  0x35a6e8371  push       rdi
  0x35a6e8372  push       rbx
  0x35a6e8373  sub        rsp, 0x40
  0x35a6e8377  lea        rbp, [rsp + 0x40]
  0x35a6e837c  mov        rdi, rcx
  0x35a6e837f  mov        rbx, rdx
  0x35a6e8382  test       rdx, rdx
  0x35a6e8385  je         0x35a6e8445
  0x35a6e838b  test       r8, r8
  0x35a6e838e  je         0x35a6e84b0
  0x35a6e8394  mov        eax, dword ptr [r9]
  0x35a6e8397  movzx      edx, byte ptr [rdx]
  0x35a6e839a  mov        dword ptr [r9], 0
  0x35a6e83a1  mov        dword ptr [rbp - 4], eax
  0x35a6e83a4  test       dl, dl
  0x35a6e83a6  je         0x35a6e8440
  0x35a6e83ac  cmp        dword ptr [rbp + 0x48], 1
  0x35a6e83b0  jbe        0x35a6e8420
  0x35a6e83b2  test       al, al
  0x35a6e83b4  jne        0x35a6e8450
  0x35a6e83ba  mov        qword ptr [rbp + 0x38], r9
  0x35a6e83be  mov        ecx, dword ptr [rbp + 0x40]
  0x35a6e83c1  mov        qword ptr [rbp + 0x30], r8
  0x35a6e83c5  call       qword ptr [rip + 0x2826dcd]
  0x35a6e83cb  test       eax, eax
  0x35a6e83cd  je         0x35a6e8420
  0x35a6e83cf  mov        r8, qword ptr [rbp + 0x30]
  0x35a6e83d3  mov        r9, qword ptr [rbp + 0x38]
  0x35a6e83d7  cmp        r8, 1
  0x35a6e83db  je         0x35a6e84aa
  0x35a6e83e1  mov        qword ptr [rsp + 0x20], rdi
  0x35a6e83e6  mov        r9d, 2
  0x35a6e83ec  mov        r8, rbx
  0x35a6e83ef  mov        dword ptr [rsp + 0x28], 1
  0x35a6e83f7  mov        ecx, dword ptr [rbp + 0x40]
  0x35a6e83fa  mov        edx, 8
  0x35a6e83ff  call       qword ptr [rip + 0x2826da3]
  0x35a6e8405  test       eax, eax
  0x35a6e8407  je         0x35a6e8498
  0x35a6e840d  mov        eax, 2
  0x35a6e8412  add        rsp, 0x40
  0x35a6e8416  pop        rbx
  0x35a6e8417  pop        rdi
  0x35a6e8418  pop        rbp
  0x35a6e8419  ret        
  0x35a6e841a  nop        word ptr [rax + rax]
  0x35a6e8420  mov        eax, dword ptr [rbp + 0x40]
  0x35a6e8423  test       eax, eax
  0x35a6e8425  jne        0x35a6e8470
  0x35a6e8427  movzx      eax, byte ptr [rbx]
  0x35a6e842a  mov        word ptr [rdi], ax
  0x35a6e842d  mov        eax, 1
  0x35a6e8432  add        rsp, 0x40
  0x35a6e8436  pop        rbx
  0x35a6e8437  pop        rdi
  0x35a6e8438  pop        rbp
  0x35a6e8439  ret        
  0x35a6e843a  nop        word ptr [rax + rax]
  0x35a6e8440  xor        edx, edx
  0x35a6e8442  mov        word ptr [rcx], dx
  0x35a6e8445  xor        eax, eax
  0x35a6e8447  add        rsp, 0x40
  0x35a6e844b  pop        rbx
  0x35a6e844c  pop        rdi
  0x35a6e844d  pop        rbp
  0x35a6e844e  ret        
  0x35a6e844f  nop        
  0x35a6e8450  mov        byte ptr [rbp - 3], dl
  0x35a6e8453  mov        r9d, 2
  0x35a6e8459  lea        r8, [rbp - 4]
  0x35a6e845d  mov        dword ptr [rsp + 0x28], 1
  0x35a6e8465  mov        qword ptr [rsp + 0x20], rcx
  0x35a6e846a  jmp        0x35a6e83f7
  0x35a6e846c  nop        dword ptr [rax]
  0x35a6e8470  mov        qword ptr [rsp + 0x20], rdi
  0x35a6e8475  mov        ecx, dword ptr [rbp + 0x40]
  0x35a6e8478  mov        r8, rbx
  0x35a6e847b  mov        edx, 8
  0x35a6e8480  mov        dword ptr [rsp + 0x28], 1
  0x35a6e8488  mov        r9d, 1
  0x35a6e848e  call       qword ptr [rip + 0x2826d14]
  0x35a6e8494  test       eax, eax
  0x35a6e8496  jne        0x35a6e842d
  0x35a6e8498  call       0x35a6e86e0
  0x35a6e849d  mov        dword ptr [rax], 0x2a
  0x35a6e84a3  mov        eax, 0xffffffff
  0x35a6e84a8  jmp        0x35a6e8447
  0x35a6e84aa  movzx      eax, byte ptr [rbx]
  0x35a6e84ad  mov        byte ptr [r9], al
  0x35a6e84b0  mov        eax, 0xfffffffe
  0x35a6e84b5  jmp        0x35a6e8447
  0x35a6e84b7  nop        word ptr [rax + rax]
  0x35a6e84c0  push       rbp
  0x35a6e84c1  push       r13
  0x35a6e84c3  push       r12
  0x35a6e84c5  push       rdi
  0x35a6e84c6  push       rsi
  0x35a6e84c7  push       rbx
  0x35a6e84c8  sub        rsp, 0x48
  0x35a6e84cc  lea        rbp, [rsp + 0x40]
  0x35a6e84d1  xor        eax, eax
  0x35a6e84d3  mov        rbx, rcx
  0x35a6e84d6  test       rcx, rcx
  0x35a6e84d9  mov        word ptr [rbp - 2], ax
  0x35a6e84dd  lea        rax, [rbp - 2]
  0x35a6e84e1  mov        rsi, r9
  0x35a6e84e4  cmove      rbx, rax
  0x35a6e84e8  mov        rdi, rdx
  0x35a6e84eb  mov        r12, r8
  0x35a6e84ee  call       0x35a6e86c8
  0x35a6e84f3  mov        r13d, eax
  0x35a6e84f6  call       0x35a6e86c0
  0x35a6e84fb  test       rsi, rsi
  0x35a6e84fe  mov        dword ptr [rsp + 0x28], r13d
  0x35a6e8503  mov        r8, r12
  0x35a6e8506  mov        dword ptr [rsp + 0x20], eax
  0x35a6e850a  lea        r9, [rip + 0x2825a57]
  0x35a6e8511  mov        rdx, rdi
  0x35a6e8514  mov        rcx, rbx
  0x35a6e8517  cmovne     r9, rsi
  0x35a6e851b  call       0x35a6e8370
  0x35a6e8520  cdqe       
  0x35a6e8522  add        rsp, 0x48
  0x35a6e8526  pop        rbx
  0x35a6e8527  pop        rsi
  0x35a6e8528  pop        rdi
  0x35a6e8529  pop        r12
  0x35a6e852b  pop        r13
  0x35a6e852d  pop        rbp
  0x35a6e852e  ret        
  0x35a6e852f  nop        
  0x35a6e8530  push       rbp
  0x35a6e8531  push       r15
  0x35a6e8533  push       r14
  0x35a6e8535  push       r13
  0x35a6e8537  push       r12
  0x35a6e8539  push       rdi
  0x35a6e853a  push       rsi
  0x35a6e853b  push       rbx
  0x35a6e853c  sub        rsp, 0x48
  0x35a6e8540  lea        rbp, [rsp + 0x40]
  0x35a6e8545  lea        rax, [rip + 0x2825a18]
  0x35a6e854c  mov        rsi, r9
  0x35a6e854f  test       r9, r9
  0x35a6e8552  mov        r15, rcx
  0x35a6e8555  mov        rbx, rdx
  0x35a6e8558  cmove      rsi, rax
  0x35a6e855c  mov        rdi, r8
  0x35a6e855f  call       0x35a6e86c0
  0x35a6e8564  mov        r13d, eax
  0x35a6e8567  call       0x35a6e86c8
  0x35a6e856c  mov        r12d, eax
  0x35a6e856f  test       rbx, rbx
  0x35a6e8572  je         0x35a6e8640
  0x35a6e8578  mov        rdx, qword ptr [rbx]
  0x35a6e857b  test       rdx, rdx
  0x35a6e857e  je         0x35a6e8640
  0x35a6e8584  test       r15, r15
  0x35a6e8587  je         0x35a6e85f8
  0x35a6e8589  xor        r14d, r14d
  0x35a6e858c  test       rdi, rdi
  0x35a6e858f  jne        0x35a6e85af
  0x35a6e8591  jmp        0x35a6e85de
  0x35a6e8593  nop        dword ptr [rax + rax]
  0x35a6e8598  mov        rdx, qword ptr [rbx]
  0x35a6e859b  cdqe       
  0x35a6e859d  add        r15, 2
  0x35a6e85a1  add        r14, rax
  0x35a6e85a4  add        rdx, rax
  0x35a6e85a7  mov        qword ptr [rbx], rdx
  0x35a6e85aa  cmp        r14, rdi
  0x35a6e85ad  jae        0x35a6e85de
  0x35a6e85af  mov        dword ptr [rsp + 0x28], r12d
  0x35a6e85b4  mov        r8, rdi
  0x35a6e85b7  mov        r9, rsi
  0x35a6e85ba  mov        rcx, r15
  0x35a6e85bd  mov        dword ptr [rsp + 0x20], r13d
  0x35a6e85c2  sub        r8, r14
  0x35a6e85c5  call       0x35a6e8370
  0x35a6e85ca  test       eax, eax
  0x35a6e85cc  jg         0x35a6e8598
  0x35a6e85ce  cmp        r14, rdi
  0x35a6e85d1  jae        0x35a6e85de
  0x35a6e85d3  test       eax, eax
  0x35a6e85d5  jne        0x35a6e85de
  0x35a6e85d7  mov        qword ptr [rbx], 0
  0x35a6e85de  mov        rax, r14
  0x35a6e85e1  add        rsp, 0x48
  0x35a6e85e5  pop        rbx
  0x35a6e85e6  pop        rsi
  0x35a6e85e7  pop        rdi
  0x35a6e85e8  pop        r12
  0x35a6e85ea  pop        r13
  0x35a6e85ec  pop        r14
  0x35a6e85ee  pop        r15
  0x35a6e85f0  pop        rbp
  0x35a6e85f1  ret        
  0x35a6e85f2  nop        word ptr [rax + rax]
  0x35a6e85f8  xor        eax, eax
  0x35a6e85fa  mov        r15d, r12d
  0x35a6e85fd  lea        rdi, [rbp - 2]
  0x35a6e8601  xor        r14d, r14d
  0x35a6e8604  mov        word ptr [rbp - 2], ax
  0x35a6e8608  jmp        0x35a6e8618
  0x35a6e860a  nop        word ptr [rax + rax]
  0x35a6e8610  cdqe       
  0x35a6e8612  mov        rdx, qword ptr [rbx]
  0x35a6e8615  add        r14, rax
  0x35a6e8618  mov        dword ptr [rsp + 0x28], r12d
  0x35a6e861d  add        rdx, r14
  0x35a6e8620  mov        r9, rsi
  0x35a6e8623  mov        r8, r15
  0x35a6e8626  mov        dword ptr [rsp + 0x20], r13d
  0x35a6e862b  mov        rcx, rdi
  0x35a6e862e  call       0x35a6e8370
  0x35a6e8633  test       eax, eax
  0x35a6e8635  jg         0x35a6e8610
  0x35a6e8637  jmp        0x35a6e85de
  0x35a6e8639  nop        dword ptr [rax]
  0x35a6e8640  xor        r14d, r14d
  0x35a6e8643  jmp        0x35a6e85de
  0x35a6e8645  nop        word ptr cs:[rax + rax]
  0x35a6e8650  push       rbp
  0x35a6e8651  push       r12
  0x35a6e8653  push       rdi
  0x35a6e8654  push       rsi
  0x35a6e8655  push       rbx
  0x35a6e8656  sub        rsp, 0x40
  0x35a6e865a  lea        rbp, [rsp + 0x40]
  0x35a6e865f  xor        eax, eax
  0x35a6e8661  mov        rsi, rcx
  0x35a6e8664  mov        rdi, rdx
  0x35a6e8667  mov        rbx, r8
  0x35a6e866a  mov        word ptr [rbp - 2], ax
  0x35a6e866e  call       0x35a6e86c8
  0x35a6e8673  mov        r12d, eax
  0x35a6e8676  call       0x35a6e86c0
  0x35a6e867b  test       rbx, rbx
  0x35a6e867e  mov        dword ptr [rsp + 0x28], r12d
  0x35a6e8683  mov        r8, rdi
  0x35a6e8686  lea        rdx, [rip + 0x28258d3]
  0x35a6e868d  mov        dword ptr [rsp + 0x20], eax
  0x35a6e8691  lea        rcx, [rbp - 2]
  0x35a6e8695  cmove      rbx, rdx
  0x35a6e8699  mov        rdx, rsi
  0x35a6e869c  mov        r9, rbx
  0x35a6e869f  call       0x35a6e8370
  0x35a6e86a4  cdqe       
  0x35a6e86a6  add        rsp, 0x40
  0x35a6e86aa  pop        rbx
  0x35a6e86ab  pop        rsi
  0x35a6e86ac  pop        rdi
  0x35a6e86ad  pop        r12
  0x35a6e86af  pop        rbp
  0x35a6e86b0  ret        
  0x35a6e86b1  nop        
  0x35a6e86b2  nop        
  0x35a6e86b3  nop        
  0x35a6e86b4  nop        
  0x35a6e86b5  nop        
  0x35a6e86b6  nop        
  0x35a6e86b7  nop        
  0x35a6e86b8  nop        
  0x35a6e86b9  nop        
  0x35a6e86ba  nop        
  0x35a6e86bb  nop        
  0x35a6e86bc  nop        
  0x35a6e86bd  nop        
  0x35a6e86be  nop        
  0x35a6e86bf  nop        
  0x35a6e86c0  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e86c6  nop        
  0x35a6e86c7  nop        
  0x35a6e86c8  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e86ce  nop        
  0x35a6e86cf  nop        
  0x35a6e86d0  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e86d6  nop        
  0x35a6e86d7  nop        
  0x35a6e86d8  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e86de  nop        
  0x35a6e86df  nop        
  0x35a6e86e0  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e86e6  nop        
  0x35a6e86e7  nop        
  0x35a6e86e8  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e86ee  nop        
  0x35a6e86ef  nop        
  0x35a6e86f0  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e86f6  nop        
  0x35a6e86f7  nop        
  0x35a6e86f8  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e86fe  nop        
  0x35a6e86ff  nop        
  0x35a6e8700  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e8706  nop        
  0x35a6e8707  nop        
  0x35a6e8708  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e870e  nop        
  0x35a6e870f  nop        
  0x35a6e8710  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e8716  nop        
  0x35a6e8717  nop        
  0x35a6e8718  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e871e  nop        
  0x35a6e871f  nop        
  0x35a6e8720  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e8726  nop        
  0x35a6e8727  nop        
  0x35a6e8728  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e872e  nop        
  0x35a6e872f  nop        
  0x35a6e8730  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e8736  nop        
  0x35a6e8737  nop        
  0x35a6e8738  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e873e  nop        
  0x35a6e873f  nop        
  0x35a6e8740  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e8746  nop        
  0x35a6e8747  nop        
  0x35a6e8748  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e874e  nop        
  0x35a6e874f  nop        
  0x35a6e8750  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e8756  nop        
  0x35a6e8757  nop        
  0x35a6e8758  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e875e  nop        
  0x35a6e875f  nop        
  0x35a6e8760  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e8766  nop        
  0x35a6e8767  nop        
  0x35a6e8768  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e876e  nop        
  0x35a6e876f  nop        
  0x35a6e8770  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e8776  nop        
  0x35a6e8777  nop        
  0x35a6e8778  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e877e  nop        
  0x35a6e877f  nop        
  0x35a6e8780  jmp        qword ptr [rip + 0x2826b1a]
  0x35a6e8786  nop        
  0x35a6e8787  nop        
  0x35a6e8788  nop        dword ptr [rax + rax]
  0x35a6e8790  jmp        qword ptr [rip + 0x2826a3a]
  0x35a6e8796  nop        
  0x35a6e8797  nop        
  0x35a6e8798  jmp        qword ptr [rip + 0x2826a2a]
  0x35a6e879e  nop        
  0x35a6e879f  nop        
  0x35a6e87a0  jmp        qword ptr [rip + 0x2826a1a]
  0x35a6e87a6  nop        
  0x35a6e87a7  nop        
  0x35a6e87a8  jmp        qword ptr [rip + 0x2826a0a]
  0x35a6e87ae  nop        
  0x35a6e87af  nop        
  0x35a6e87b0  jmp        qword ptr [rip + 0x28269fa]
  0x35a6e87b6  nop        
  0x35a6e87b7  nop        
  0x35a6e87b8  jmp        qword ptr [rip + 0x28269ea]
  0x35a6e87be  nop        
  0x35a6e87bf  nop        
  0x35a6e87c0  jmp        qword ptr [rip + 0x28269da]
  0x35a6e87c6  nop        
  0x35a6e87c7  nop        
  0x35a6e87c8  jmp        qword ptr [rip + 0x28269ca]
  0x35a6e87ce  nop        
  0x35a6e87cf  nop        
  0x35a6e87d0  jmp        qword ptr [rip + 0x28269ba]
  0x35a6e87d6  nop        
  0x35a6e87d7  nop        
  0x35a6e87d8  jmp        qword ptr [rip + 0x28269aa]
  0x35a6e87de  nop        
  0x35a6e87df  nop        
  0x35a6e87e0  jmp        qword ptr [rip + 0x282699a]
  0x35a6e87e6  nop        
  0x35a6e87e7  nop        
  0x35a6e87e8  jmp        qword ptr [rip + 0x282698a]
  0x35a6e87ee  nop        
  0x35a6e87ef  nop        
  0x35a6e87f0  jmp        0x35a6e1370
  0x35a6e87f5  nop        
  0x35a6e87f6  nop        
  0x35a6e87f7  nop        
  0x35a6e87f8  nop        
  0x35a6e87f9  nop        
  0x35a6e87fa  nop        
  0x35a6e87fb  nop        
  0x35a6e87fc  nop        
  0x35a6e87fd  nop        
  0x35a6e87fe  nop        
  0x35a6e87ff  nop        
