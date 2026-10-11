import pefile
import capstone
import os
import struct

DLL_PATH = r"f:\TEALAX\alem\pagon_native.dll"
OUT_DIR  = r"f:\TEALAX\alem\decompile_output"
os.makedirs(OUT_DIR, exist_ok=True)

pe = pefile.PE(DLL_PATH)

arch = capstone.CS_ARCH_X86
mode = capstone.CS_MODE_64
md = capstone.Cs(arch, mode)
md.detail = True

raw_data = open(DLL_PATH, "rb").read()

# ── Helper: RVA → file offset ──────────────────────────────────────────────
def rva_to_offset(rva):
    for section in pe.sections:
        s_rva  = section.VirtualAddress
        s_size = section.Misc_VirtualSize
        if s_rva <= rva < s_rva + s_size:
            return rva - s_rva + section.PointerToRawData
    return None

# ── 1. GENEL BİLGİLER ──────────────────────────────────────────────────────
with open(os.path.join(OUT_DIR, "00_info.txt"), "w", encoding="utf-8") as f:
    f.write("=== PE GENEL BİLGİ ===\n\n")
    f.write(f"Dosya      : {DLL_PATH}\n")
    f.write(f"Machine    : {hex(pe.FILE_HEADER.Machine)}\n")
    f.write(f"TimeDateStamp: {pe.FILE_HEADER.TimeDateStamp}\n")
    f.write(f"Sections   : {pe.FILE_HEADER.NumberOfSections}\n")
    f.write(f"ImageBase  : {hex(pe.OPTIONAL_HEADER.ImageBase)}\n")
    f.write(f"EntryPoint : {hex(pe.OPTIONAL_HEADER.AddressOfEntryPoint)}\n\n")

    f.write("=== SECTIONS ===\n")
    for s in pe.sections:
        name = s.Name.decode(errors='replace').strip('\x00')
        f.write(f"  {name:10s}  RVA={hex(s.VirtualAddress)}  Size={s.SizeOfRawData}  Flags={hex(s.Characteristics)}\n")

print("[+] 00_info.txt yazıldı")

# ── 2. EXPORT EDİLEN FONKSİYONLAR ─────────────────────────────────────────
exports = []
with open(os.path.join(OUT_DIR, "01_exports.txt"), "w", encoding="utf-8") as f:
    f.write("=== EXPORT FONKSİYONLARI ===\n\n")
    if hasattr(pe, 'DIRECTORY_ENTRY_EXPORT'):
        for exp in pe.DIRECTORY_ENTRY_EXPORT.symbols:
            name = exp.name.decode(errors='replace') if exp.name else f"ord_{exp.ordinal}"
            rva  = exp.address
            f.write(f"  [{exp.ordinal:4d}]  RVA={hex(rva)}  {name}\n")
            exports.append((name, rva))
    else:
        f.write("  (export tablosu yok)\n")

print(f"[+] 01_exports.txt yazıldı  ({len(exports)} export)")

# ── 3. IMPORT EDİLEN FONKSİYONLAR ─────────────────────────────────────────
with open(os.path.join(OUT_DIR, "02_imports.txt"), "w", encoding="utf-8") as f:
    f.write("=== IMPORT FONKSİYONLARI ===\n\n")
    if hasattr(pe, 'DIRECTORY_ENTRY_IMPORT'):
        for entry in pe.DIRECTORY_ENTRY_IMPORT:
            dll_name = entry.dll.decode(errors='replace')
            f.write(f"\n[{dll_name}]\n")
            for imp in entry.imports:
                iname = imp.name.decode(errors='replace') if imp.name else f"ordinal_{imp.ordinal}"
                f.write(f"    {iname}\n")
    else:
        f.write("  (import tablosu yok)\n")

print("[+] 02_imports.txt yazıldı")

# ── 4. STRINGS ─────────────────────────────────────────────────────────────
def extract_strings(data, min_len=5):
    result = []
    cur = b""
    offset = 0
    start = 0
    for b in data:
        c = bytes([b])
        if 32 <= b <= 126:
            if not cur:
                start = offset
            cur += c
        else:
            if len(cur) >= min_len:
                result.append((start, cur.decode('ascii', errors='replace')))
            cur = b""
        offset += 1
    return result

strings = extract_strings(raw_data)
with open(os.path.join(OUT_DIR, "03_strings.txt"), "w", encoding="utf-8") as f:
    f.write("=== STRİNG TABLOSU ===\n\n")
    for off, s in strings:
        f.write(f"  0x{off:08X}  {s}\n")

print(f"[+] 03_strings.txt yazıldı  ({len(strings)} string)")

# ── 5. DİSASSEMBLY (tüm executable section'lar) ───────────────────────────
asm_file = open(os.path.join(OUT_DIR, "04_disassembly.asm"), "w", encoding="utf-8")
asm_file.write("; === TAM DİSASSEMBLY (x64) ===\n\n")

# Export isimlerini RVA'ya göre indexle
export_map = {rva: name for name, rva in exports}

for section in pe.sections:
    flags = section.Characteristics
    # Executable flag: 0x20000000
    if not (flags & 0x20000000):
        continue
    sec_name = section.Name.decode(errors='replace').strip('\x00')
    sec_rva  = section.VirtualAddress
    sec_data = section.get_data()
    base     = pe.OPTIONAL_HEADER.ImageBase + sec_rva

    asm_file.write(f"\n; ════════════════════════════════════════════\n")
    asm_file.write(f"; SECTION: {sec_name}  (RVA={hex(sec_rva)}  VA={hex(base)})\n")
    asm_file.write(f"; ════════════════════════════════════════════\n\n")

    # Export etiketleri ekle
    for rva, fname in sorted(export_map.items()):
        offset_in_sec = rva - sec_rva
        if 0 <= offset_in_sec < len(sec_data):
            # Etiketi sonradan işaretlemek için bir dict tut
            pass

    # Tüm section'ı disassemble et
    label_offsets = {}
    for rva, fname in export_map.items():
        off = rva - sec_rva
        if 0 <= off < len(sec_data):
            label_offsets[off] = fname

    last_written = -1
    for i, insn in enumerate(md.disasm(sec_data, base)):
        rel_off = insn.address - base
        if rel_off in label_offsets:
            asm_file.write(f"\n; ── EXPORT: {label_offsets[rel_off]} ──\n")
            asm_file.write(f"{label_offsets[rel_off]}:\n")
        asm_file.write(f"  {hex(insn.address)}  {insn.mnemonic:<10} {insn.op_str}\n")

asm_file.close()
print("[+] 04_disassembly.asm yazıldı")

# ── 6. EXPORT FONKSİYONLARINI AYRI AYRI DİSASSEMBLE ET ───────────────────
func_dir = os.path.join(OUT_DIR, "functions")
os.makedirs(func_dir, exist_ok=True)

for section in pe.sections:
    if not (section.Characteristics & 0x20000000):
        continue
    sec_rva  = section.VirtualAddress
    sec_data = section.get_data()
    base_va  = pe.OPTIONAL_HEADER.ImageBase

    for fname, rva in exports:
        off = rva - sec_rva
        if not (0 <= off < len(sec_data)):
            continue

        chunk = sec_data[off:off+512]   # max 512 byte / fonksiyon
        lines = []
        for insn in md.disasm(chunk, base_va + rva):
            lines.append(f"  {hex(insn.address)}  {insn.mnemonic:<10} {insn.op_str}")
            # ret görürsek dur
            if insn.mnemonic in ("ret", "retn"):
                break

        safe_name = fname.replace("?", "_").replace("@", "_").replace("<", "_").replace(">", "_")
        out_path = os.path.join(func_dir, f"{safe_name}.asm")
        with open(out_path, "w", encoding="utf-8") as ff:
            ff.write(f"; === {fname} ===\n; RVA: {hex(rva)}\n\n")
            ff.write("\n".join(lines))
            ff.write("\n")

print(f"[+] functions/ altına {len(exports)} ayrı fonksiyon dosyası yazıldı")

# ── 7. ÖZET ───────────────────────────────────────────────────────────────
print("\n=== TAMAMLANDI ===")
print(f"Çıktı dizini: {OUT_DIR}")
print(f"  00_info.txt          - PE genel bilgi + section listesi")
print(f"  01_exports.txt       - Export fonksiyon tablosu")
print(f"  02_imports.txt       - Import tablosu")
print(f"  03_strings.txt       - String tablosu ({len(strings)} adet)")
print(f"  04_disassembly.asm   - Tam x64 disassembly")
print(f"  functions/           - Her export için ayrı .asm dosyası")
