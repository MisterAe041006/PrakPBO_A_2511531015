package Praktikum_Minggu_1_2511531015;

import java.util.ArrayList;
import java.util.Scanner;

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
                    
                    // Buat objek baru lalu tambahkan ke ArrayList
                    Rekening rekeningBaru = new Rekening(no, nama, saldo);
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
                        System.out.print("Masukkan nominal tarik tunai: ");
                        double tarik = input.nextDouble();
                        akunAktif.tarikTunai(tarik);
                    }
                    break;
                    
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