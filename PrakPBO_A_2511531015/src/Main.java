package Praktikum_Minggu_1_2511531015;

import java.util.ArrayList;
import java.util.Scanner;

import Praktikum_Pekan4_2511531015.RekeningGiro;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Menggunakan ArrayList untuk menyimpan daftar seluruh rekening
        ArrayList<Rekening> daftarRekening = new ArrayList<>();
        Rekening akunAktif = null; // Menunjuk ke akun yang sedang digunakan
        
        boolean isRunning = true;
        
        System.out.println("=== SISTEM PERBANKAN MINI ===");
        
        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Buka rekening baru");
            System.out.println("2. Setor tunai");
            System.out.println("3. Tarik tunai");
            System.out.println("4. Cek informasi rekening");
            System.out.println("5. Ganti akun aktif");
            System.out.println("6. Cetak Mutasi(Riwayat)");
            System.out.println("7. Simulasi Akhir Bulan (Khusus Tabungan)");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            
            int pilihan = input.nextInt();
            input.nextLine(); // Membersihkan buffer newline
            
            switch (pilihan) {
            case 1:
                System.out.print("Masukkan no rekening: ");
                String no = input.nextLine();
                System.out.print("Masukkan nama pemilik: ");
                String nama = input.nextLine();
                System.out.print("Masukkan saldo awal: ");
                double saldo = input.nextDouble();
                input.nextLine(); // Consume newline setelah nextDouble() agar input PIN tidak terlewati
                
                // Proses validasi input PIN 6 digit angka
                String pin;
                while (true) {
                    System.out.print("Masukkan PIN (6 digit angka): ");
                    pin = input.nextLine().trim();

                    // Menggunakan RegEx untuk mengecek apakah persis 6 digit angka
                    if (pin.matches("\\d{6}")) {
                        break;
                    }
                    System.out.println("Error: PIN harus terdiri dari tepat 6 digit angka (contoh: 123456)!");
                }
                
                // --- PENAMBAHAN UNTUK TUGAS 1 ---
                System.out.println("\nPilih Produk:");
                System.out.println("1. Tabungan Umum");
                System.out.println("2. Giro Bisnis");
                System.out.print("Pilihan Produk (1/2): ");
                int pilihanProduk = input.nextInt();

                Rekening rekeningBaru = null;

                if (pilihanProduk == 1) {
                    System.out.print("Masukkan suku bunga (%): ");
                    double sukuBunga = input.nextDouble();
                    input.nextLine(); // Clear buffer
                    
                    // Instansiasi RekeningTabungan
                    rekeningBaru = new RekeningGiro(no, nama, saldo, pin, sukuBunga);

                } else if (pilihanProduk == 2) {
                    System.out.print("Masukkan batas overdraft: ");
                    double batasOverdraft = input.nextDouble();
                    input.nextLine(); // Clear buffer
                    
                    // Instansiasi RekeningGiro
                    rekeningBaru = new RekeningGiro(no, nama, saldo, pin, batasOverdraft);

                } else {
                    System.out.println("Pilihan tidak valid. Rekening gagal dibuat.");
                    break;
                }
                
                // Simpan ke ArrayList (Upcasting: RekeningTabungan / RekeningGiro disimpan ke tipe Rekening)
                daftarRekening.add(rekeningBaru);
                
                // Otomatis jadikan rekening yang baru dibuat sebagai akun aktif
                akunAktif = rekeningBaru;
                System.out.println("Rekening berhasil dibuka dan saat ini aktif!");
                break;
                
                case 2:
                    if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, Anda belum memiliki/memilih nomor rekening!");
                    } else {
                        System.out.print("Masukkan nominal setor: ");
                        double setor = input.nextDouble();
                        akunAktif.setorTunai(setor);
                    }
                    break;
                    
                case 3:
                	if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, Anda belum memiliki/memilih nomor rekening!");
                    } else {
                        // Minta user memasukkan PIN terlebih dahulu
                        System.out.print("Masukkan PIN Anda: ");
                        String pinInput = input.nextLine();

                        // Verifikasi PIN menggunakan method otentikasi
                        if (akunAktif.otentikasi(pinInput)) {
                            System.out.print("Masukkan nominal tarik tunai: ");
                            double tarik = input.nextDouble();
                            input.nextLine(); // Membersihkan buffer setelah input double
                            akunAktif.tarikTunai(tarik);
                        } else {
                            System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                        }
                    }
                    
                case 4:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum memilih rekening!");
                    } else {
                        akunAktif.cekInformasi();
                    }
                    break;
                    
                case 5:
                    if (daftarRekening.isEmpty()) {
                        System.out.println("Error: Belum ada rekening yang terdaftar!");
                    } else {
                        System.out.print("Masukkan nomor rekening yang dicari: ");
                        String noCari = input.nextLine();
                        
                        Rekening akunDitemukan = null;
                        for (Rekening rek : daftarRekening) {
                            if (rek.getNoRekening().equalsIgnoreCase(noCari)) {
                                akunDitemukan = rek;
                                break;
                            }
                        }
                        
                        if (akunDitemukan != null) {
                            akunAktif = akunDitemukan;
                            System.out.println("Berhasil mengganti ke akun milik: " + akunAktif.getNamaPemilik());
                        } else {
                            System.out.println("Error: Nomor rekening tidak ditemukan!");
                        }
                    }
                    break;
                    
                case 6:
                	if (akunAktif == null) {
                        System.out.println("Belum ada akun aktif");
                    } else {
                        // Minta user memasukkan PIN terlebih dahulu
                        System.out.print("Masukkan PIN Anda: ");
                        String pinInput = input.nextLine();

                        // Verifikasi PIN menggunakan method otentikasi
                        if (akunAktif.otentikasi(pinInput)) {
                            akunAktif.cetakMutasi();
                        } else {
                            System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                        }
                    }
                    break;
                    
                case 7:
                    // 1. Cek apakah ada akun yang sedang aktif
                    if (akunAktif == null) {
                        System.out.println("Gagal: Belum ada akun yang aktif. Silakan pilih atau buka rekening terlebih dahulu.");
                        break;
                    }

                    // 2. Periksa apakah akunAktif merupakan objek dari RekeningTabungan menggunakan instanceof
                    if (akunAktif instanceof RekeningGiro) {
                        // 3. Downcasting dari Rekening ke RekeningTabungan
                        RekeningGiro tabunganAktif = (RekeningGiro) akunAktif;
                        
                        // Memanggil method khusus milik RekeningTabungan
                        tabunganAktif.tambahBungaAkhirBulan();
                        
                        System.out.println("Simulasi akhir bulan berhasil dilakukan untuk Rekening Tabungan!");
                    } else {
                        // 4. Jika akunAktif bukan RekeningTabungan (misalnya RekeningGiro)
                        System.out.println("Gagal: Fitur bunga akhir bulan hanya berlaku untuk Rekening Tabungan.");
                    }
                    break;
                	
                case 0:
                    isRunning = false;
                    System.out.println("Sistem ditutup. Terima kasih!");
                    break;
                    
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        input.close();
    }
}