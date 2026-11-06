# Contoh Bahan Laporan T1–T4

> **Perhatian T3:** tabel masalah di bawah adalah *hipotesis* berdasarkan keluaran BadShop pada halaman 21–22 modul, **bukan pemeriksaan source `BadShop.java` asli**. Cocokkan lokasi/metode dan penyebabnya setelah membaca file aslinya.

## T1 — Jawaban konsep

1. `EWalletPayment` hanya mewarisi `RefundablePayment.policy()` sehingga tidak ada persaingan definisi default yang harus diselesaikan.
2. `CreditCardPayment` menerapkan dua interface yang menyediakan signature `policy()` sama; compiler tidak bisa tahu kebijakan mana yang diprioritaskan. Override eksplisit mencegah pilihan ambigu.
3. Untuk T1.a, mengubah method menjadi abstract memaksa setiap implementasi `PaymentMethod` memberi implementasi `adminFee`, termasuk anonymous class. Catat jumlah error sebenarnya di komputer Anda dari hasil kompilasi (tidak bisa dipastikan tanpa tool ZIP dan struktur kode lokal).

## T2 — Jawaban konsep

1. Interface `NotificationChannel` menyediakan kontrak seragam tanpa memaksakan pewarisan; abstract class `AbstractChannel` mengumpulkan validasi, template alur, dan pencatatan pengiriman bersama.
2. Tanpa `final`, subclass dapat mengganti `send` dan melewati validasi atau pencatatan sehingga perilaku antar kanal tidak konsisten.
3. `List<NotificationChannel>` mendukung implementasi lain seperti anonymous class log, yang bukan subclass `AbstractChannel`. `List<AbstractChannel>` akan menolaknya.

## T3 — Kandidat temuan untuk diverifikasi

| No. | Lokasi yang perlu diperiksa | Indikasi masalah | Dampak yang terlihat | Usulan perbaikan |
|---|---|---|---|---|
| 1 | Kalkulasi ongkir kurir Ekspres | Rumus tidak dipakai / override keliru (dugaan) | Ongkir 2 kg menjadi Rp0 | Taruh rumus di `ShippingService.calculateCost` final; subclass hanya menentukan tarif |
| 2 | Tipe alamat/pesanan | Mutabilitas atau referensi alamat dipakai bersama (dugaan) | Alamat ORD-1 berubah mengikuti ORD-2 | Gunakan `final class Address` dengan field final, dan setiap order menyimpan alamatnya sendiri |
| 3 | `GiftVoucher.ship()` | Voucher diberi kemampuan pengiriman yang tidak didukung | `UnsupportedOperationException` menghentikan aplikasi | Pisahkan jenis Product vs kemampuan Shippable; voucher tidak Shippable |
| 4 | Penggunaan tipe nilai uang | Jika memakai floating-point untuk rupiah | Angka saldo tercetak `70000.0` dan potensi pembulatan | Pakai `long` untuk rupiah |
| 5 | Pewarisan/payment/checkout | Jika tergantung class konkret, bukan kontrak | Metode baru sulit dimasukkan | Simpan dan gunakan `PaymentMethod`, bukan tipe pembayaran khusus |
| 6 | Perancangan API | Jika tiap subclass bebas menimpa perhitungan tarif | Aturan bisnis ongkir tidak konsisten | Terapkan final method + protected abstract ratePerKg |

Contoh implementasi yang memperbaiki gejala ongkir, alamat, dan voucher ada di `src/review/fixed/BadShopRefactored.java`. Setelah tersedia file `BadShop.java` asli, lakukan refactor berdasar kode itu, bukan semata keluaran di modul.

## T4 — Tabel 1: Abstract class atau interface?

| Pertanyaan desain | Pilihan | Alasan | Contoh |
|---|---|---|---|
| Subclass berbagi field dan konstruktor | Abstract class | Bisa menampung state dan constructor bersama | `Product` menyimpan sku, nama, harga |
| Satu class perlu beberapa kemampuan | Interface | Bisa implements beberapa interface | `CreditCardPayment` implements `RefundablePayment` dan `InstallmentPayment` |
| Rumus bersama tidak boleh diubah subclass | Abstract class + final method | Template perhitungan dikunci, tarif masih bervariasi | `ShippingService.calculateCost()` |
| Kontrak perlu ditambah method di kemudian hari | Interface + default method | Memberi implementasi bawaan untuk kode lama | `PaymentMethod.adminFee()` |

## T4 — Tabel 2: Nested class

| Kondisi | Jenis | Alasan | Contoh |
|---|---|---|---|
| Pembantu tidak membaca data objek luar | Static nested | Tidak membutuhkan referensi `Order` | `Order.Line` |
| Objek harus membaca field objek luar | Inner | Struk membutuhkan ID dan alamat Order | `Order.Receipt` |
| Implementasi sekali pakai tanpa nama | Anonymous | Menghemat class terpisah untuk logika singkat | Voucher di `ShopApp`; log konsol di `TugasApp` |
