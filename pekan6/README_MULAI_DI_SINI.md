# Praktikum 6 – Abstraction (contoh proyek Java siap VS Code)

## Langkah membuka semua kode
1. Ekstrak ZIP terlebih dahulu (klik kanan → **Extract All...** di Windows).
2. Buka **Visual Studio Code**.
3. **Drag folder `Praktikum6_Abstraction_Siap_VSCode_2511531015` hasil ekstraksi** ke jendela VS Code, atau pilih **File → Open Folder...**. Jangan hanya membuka file ZIP atau folder `src` saja.
4. Pada panel Explorer, perluas folder `src` lalu `shop` untuk melihat semua file Java.
5. Buka `src/shop/app/ShopApp.java` untuk contoh **L1–L5**, klik **Run** di atas `main`.
6. Buka `src/shop/app/TugasApp.java` untuk contoh **T1–T2**, klik **Run**.
7. `src/review/fixed/BadShopRefactored.java` adalah contoh refactoring untuk mempelajari **T3**.

Butuh **JDK 21 atau lebih baru** dan VS Code dengan extension **Extension Pack for Java**.

## Struktur

```text
Praktikum6_Abstraction_Siap_VSCode_2511531015/
├── .vscode/
│   ├── settings.json
│   └── launch.json
├── src/
│   ├── shop/
│   │   ├── StudentInfo.java
│   │   ├── app/              (ShopApp, TugasApp)
│   │   ├── model/            (Product, PhysicalProduct, DigitalProduct, Address, Order)
│   │   ├── shipping/         (kurir, Shippable)
│   │   ├── payment/          (interface dan metode pembayaran)
│   │   ├── discount/         (Discountable)
│   │   ├── service/          (CheckoutService)
│   │   └── notification/     (T2: Email, SMS, WhatsApp, dan service)
│   └── review/fixed/        (contoh T3)
├── UML_Notifikasi.md
├── Laporan_T3_T4.md
└── README_MULAI_DI_SINI.md
```

## Jalankan via terminal (opsional)

Bila tombol Run tidak tampil, dari terminal PowerShell **di folder proyek** jalankan:

```powershell
New-Item -ItemType Directory -Force -Path out | Out-Null
Get-ChildItem -Recurse src -Filter *.java | Select-Object -ExpandProperty FullName | Set-Content sources.txt
javac -d out '@sources.txt'
java -cp out shop.app.ShopApp
java -cp out shop.app.TugasApp
```

## Keterbatasan dan aturan modul
- Paket ini adalah **contoh pembelajaran**, bukan berkas ZIP resmi dosen.
- File `SelfCheck.java`, `TugasCheck.java`, `NimParams.java`, dan `BadShop.java` dari paket resmi **belum tersedia**, sehingga pengujian pemeriksa resmi belum dapat diklaim.
- Contoh T3 dibuat dari gejala yang tertulis di modul, bukan refactoring atas berkas `BadShop.java` asli.
- Kode pada folder ini mencerminkan keadaan **akhir sesudah T1/T2**; bukan snapshot terpisah setiap latihan.
- Modul melarang penggunaan kode yang dihasilkan AI dalam pengumpulan praktikum. Gunakan sebagai contoh untuk dipelajari dan tulis sendiri solusi pengumpulan sesuai aturan dosen.
