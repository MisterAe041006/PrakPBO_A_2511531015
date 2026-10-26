package Praktikum_Pekan5_2511531015;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);

    private static final ArrayList<Rekening> daftarRekening =
        new ArrayList<>();

    private static Rekening akunAktif = null;


    public static void main(String[] args) {
        System.out.println("=== SISTEM PERBANKAN MINI ===");

        while (true) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Buka rekening baru");
            System.out.println("2. Setor tunai");
            System.out.println("3. Tarik tunai");
            System.out.println("4. Cek informasi rekening");
            System.out.println("5. Ganti akun aktif");
            System.out.println("6. Cetak mutasi");
            System.out.println("7. Simulasi akhir bulan");
            System.out.println("8. Transfer ke rekening lain");
            System.out.println("0. Keluar");

            String pilihan = baca("Pilih menu: ");

            try {
                if (pilihan.equals("0")) {
                    System.out.println(
                        "Sistem ditutup. Terima kasih!"
                    );
                    break;
                }

                if (pilihan.equals("1")) {
                    buatRekening();
                    continue;
                }

                if (pilihan.equals("5")) {
                    gantiAkun();
                    continue;
                }

                if (akunAktif == null) {
                    System.out.println(
                        "Silakan buat atau pilih rekening dahulu!"
                    );
                    continue;
                }

                switch (pilihan) {
                    case "2":
                        double setor = bacaNominal(
                            "Masukkan nominal setor: "
                        );

                        akunAktif.setorTunai(setor);
                        break;

                    case "3":
                        String pinTarik = baca(
                            "Masukkan PIN pengirim: "
                        );

                        if (akunAktif.otentikasi(pinTarik)) {
                            double tarik = bacaNominal(
                                "Masukkan nominal tarik: "
                            );

                            // Java memilih implementasi penarikan
                            // sesuai objek tabungan atau giro.
                            akunAktif.tarikTunai(tarik);
                        } else {
                            System.out.println(
                                "Akses ditolak: PIN salah!"
                            );
                        }
                        break;

                    case "4":
                        akunAktif.cekInformasi();
                        break;

                    case "6":
                        String pinMutasi = baca(
                            "Masukkan PIN: "
                        );

                        if (akunAktif.otentikasi(pinMutasi)) {
                            akunAktif.cetakMutasi();
                        } else {
                            System.out.println(
                                "Akses ditolak: PIN salah!"
                            );
                        }
                        break;

                    case "7":
                        if (akunAktif instanceof Rekening) {
                            Rekening tabungan =
                                (Rekening) akunAktif;

                            tabungan.tambahBungaAkhirBulan();
                        } else {
                            System.out.println(
                                "Fitur bunga hanya untuk tabungan!"
                            );
                        }
                        break;

                    case "8":
                        menuTransfer();
                        break;

                    default:
                        System.out.println(
                            "Pilihan menu tidak tersedia!"
                        );
                }
            } catch (IllegalArgumentException e) {
                System.out.println(
                    "Input gagal: " + e.getMessage()
                );
            }
        }

        input.close();
    }


    private static String baca(String pesan) {
        System.out.print(pesan);
        return input.nextLine().trim();
    }

    
    private static double bacaNominal(String pesan) {
        return Double.parseDouble(baca(pesan));
    }

    
    private static Rekening cariRekening(String nomor) {
        for (Rekening rekening : daftarRekening) {
            if (rekening.getNoRekening().equals(nomor)) {
                return rekening;
            }
        }

        return null;
    }

   
    private static void buatRekening() {
        String nomor = baca("Masukkan nomor rekening: ");

        if (cariRekening(nomor) != null) {
            System.out.println(
                "Gagal: nomor rekening sudah digunakan!"
            );
            return;
        }

        String nama = baca("Masukkan nama pemilik: ");

        double saldoAwal = bacaNominal(
            "Masukkan saldo awal: "
        );

        String pin = baca("Masukkan PIN 6 digit angka: ");

        // Memastikan PIN valid sebelum memanggil constructor.
        if (!pin.matches("[0-9]{6}")) {
            System.out.println(
                "Gagal: PIN harus terdiri dari 6 digit angka!"
            );
            return;
        }

        System.out.println("\nPilih produk:");
        System.out.println("1. Tabungan Umum");
        System.out.println("2. Giro Bisnis");

        String produk = baca("Pilihan produk: ");

        Rekening rekeningBaru;

        if (produk.equals("1")) {
            double sukuBunga = bacaNominal(
                "Masukkan suku bunga bulanan (%): "
            );

            rekeningBaru = new RekeningTabungan(
                nomor,
                nama,
                saldoAwal,
                pin,
                sukuBunga
            );

        } else if (produk.equals("2")) {
            double batasOverdraft = bacaNominal(
                "Masukkan batas overdraft: "
            );

            rekeningBaru = new RekeningGiro(
                nomor,
                nama,
                saldoAwal,
                pin,
                batasOverdraft
            );

        } else {
            System.out.println(
                "Gagal: pilihan produk tidak valid!"
            );
            return;
        }

        daftarRekening.add(rekeningBaru);
        akunAktif = rekeningBaru;

        System.out.println(
            "Rekening berhasil dibuat dan menjadi akun aktif!"
        );
    }

    
    private static void gantiAkun() {
        String nomor = baca(
            "Masukkan nomor rekening yang ingin digunakan: "
        );

        Rekening rekening = cariRekening(nomor);

        if (rekening == null) {
            System.out.println(
                "Gagal: nomor rekening tidak ditemukan!"
            );
            return;
        }

        String pin = baca("Masukkan PIN rekening: ");

        if (rekening.otentikasi(pin)) {
            akunAktif = rekening;

            System.out.println(
                "Akun aktif sekarang milik: "
                + akunAktif.getNamaPemilik()
            );
        } else {
            System.out.println(
                "Gagal: PIN salah. Akun aktif tidak diganti."
            );
        }
    }

    
    private static void menuTransfer() {
        String nomorTujuan = baca(
            "Masukkan nomor rekening tujuan: "
        );

        Rekening akunTujuan = cariRekening(nomorTujuan);

        if (akunTujuan == null) {
            System.out.println(
                "Gagal: rekening tujuan tidak ditemukan!"
            );
            return;
        }

        double nominal = bacaNominal(
            "Masukkan nominal transfer: "
        );

        String pin = baca("Masukkan PIN pengirim: ");

        akunAktif.transfer(
            akunTujuan,
            nominal,
            pin
        );
    }
}