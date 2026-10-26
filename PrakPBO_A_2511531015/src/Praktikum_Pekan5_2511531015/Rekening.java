package Praktikum_Pekan5_2511531015;

import java.util.ArrayList;
import Praktikum_Minggu2.Transaksi;

public class Rekening {

    private String nomorRekening;
    private String namaPemilik;

    protected String pinAwal;
    protected double saldo;
    protected ArrayList<Transaksi> riwayatTransaksi1;

    // Constructor
    public Rekening(String nomor, String nama, double saldoAwal, String pin) {
        if (!Double.isFinite(saldoAwal) || saldoAwal < 0) {
            throw new IllegalArgumentException(
                "Saldo awal harus berupa angka valid dan tidak negatif!"
            );
        }

        this.nomorRekening = nomor;
        this.namaPemilik = nama;
        this.saldo = saldoAwal;

        // Menggunakan parameter pin, bukan atribut pinAwal
        if (pin != null && pin.matches("[0-9]{6}")) {
            this.pinAwal = pin;
        } else {
            System.out.println(
                "Peringatan: PIN harus 6 digit angka! "
                + "Menggunakan PIN default 123456."
            );
            this.pinAwal = "123456";
        }

        // Satu daftar digunakan untuk seluruh transaksi
        this.riwayatTransaksi1 = new ArrayList<>();

        System.out.println(
            "Rekening atas nama " + namaPemilik
            + " berhasil dibuat dengan saldo Rp" + saldo
        );
    }

    // Getter nomor rekening
    public String getNomorRekening() {
        return nomorRekening;
    }

    // Getter nama pemilik
    public String getNamaPemilik1() {
        return namaPemilik;
    }

    // Getter yang digunakan oleh Main
    public String getNoRekening() {
        return nomorRekening;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    // Memeriksa PIN
    public boolean otentikasi(String pinYangDiinput) {
        if (this.pinAwal == null || pinYangDiinput == null) {
            return false;
        }

        return this.pinAwal.equals(pinYangDiinput);
    }

    // Menambahkan saldo
    public void setorTunai(double nominal) {
        if (!Double.isFinite(nominal) || nominal <= 0
                || !Double.isFinite(saldo + nominal)) {

            System.out.println(
                "Gagal: nominal setor tidak valid atau harus lebih dari 0!"
            );
            return;
        }

        String idTrx = "TRX-S-" + System.currentTimeMillis();

        Transaksi trxBaru = new Transaksi(
            idTrx,
            "Kredit",
            nominal
        );

        riwayatTransaksi1.add(trxBaru);
        saldo += nominal;

        System.out.println(
            "Setor tunai Rp" + nominal
            + " berhasil. Saldo saat ini: Rp" + saldo
        );
    }

    // Menampilkan informasi rekening
    public void cekInformasi() {
        System.out.println("--- INFO REKENING ---");
        System.out.println("No. Rekening : " + nomorRekening);
        System.out.println("Nama Pemilik : " + namaPemilik);
        System.out.println("Saldo Akhir  : Rp" + saldo);
        System.out.println("---------------------");
    }

    // Mengembalikan true jika penarikan berhasil
    public boolean tarikTunai(double nominal) {
        if (!Double.isFinite(nominal) || nominal <= 0) {
            System.out.println(
                "Gagal: nominal tarik harus berupa angka valid "
                + "dan lebih dari 0!"
            );
            return false;
        }

        if (nominal > saldo) {
            System.out.println(
                "Transaksi gagal: saldo tidak mencukupi. "
                + "Saldo Anda: Rp" + saldo
            );
            return false;
        }

        String idTrx = "TRX-T-" + System.currentTimeMillis();

        riwayatTransaksi1.add(
            new Transaksi(idTrx, "Debit", nominal)
        );

        saldo -= nominal;

        System.out.println(
            "Tarik tunai Rp" + nominal
            + " berhasil. Sisa saldo: Rp" + saldo
        );

        return true;
    }

    // Transfer antar rekening
    public void transfer(
            Rekening tujuan,
            double nominal,
            String pinInput) {

        // 1. Memeriksa PIN pengirim
        if (!otentikasi(pinInput)) {
            System.out.println("Transfer gagal: PIN salah!");
            return;
        }

        // 2. Memeriksa rekening tujuan
        if (tujuan == null) {
            System.out.println(
                "Transfer gagal: rekening tujuan tidak ditemukan!"
            );
            return;
        }

        if (tujuan == this) {
            System.out.println(
                "Transfer gagal: tidak dapat transfer "
                + "ke rekening sendiri!"
            );
            return;
        }

        // 3. Memeriksa nominal dan saldo tujuan
        if (!Double.isFinite(nominal) || nominal <= 0
                || !Double.isFinite(tujuan.saldo + nominal)) {

            System.out.println(
                "Transfer gagal: nominal tidak valid!"
            );
            return;
        }

        // 4. Memanggil penarikan sesuai tipe objek pengirim
        boolean berhasil = this.tarikTunai(nominal);

        // 5. Dana diterima hanya jika penarikan berhasil
        if (berhasil) {
            tujuan.setorTunai(nominal);

            System.out.println("===== TRANSFER BERHASIL =====");
            System.out.println(
                "Dari rekening : " + nomorRekening
            );
            System.out.println(
                "Ke rekening   : " + tujuan.getNomorRekening()
            );
            System.out.println(
                "Nama penerima : " + tujuan.getNamaPemilik()
            );
            System.out.println(
                "Nominal       : Rp" + nominal
            );
            System.out.println(
                "Saldo pengirim: Rp" + saldo
            );
            System.out.println("=============================");
        } else {
            System.out.println(
                "Transfer dibatalkan karena penarikan gagal."
            );
        }
    }

    // Menampilkan seluruh riwayat transaksi
    public void cetakMutasi() {
        if (riwayatTransaksi1.isEmpty()) {
            System.out.println(
                "Belum ada transaksi pada rekening ini."
            );
            return;
        }

        System.out.println("===== RIWAYAT TRANSAKSI =====");

        for (Transaksi transaksi : riwayatTransaksi1) {
            transaksi.cetakDetail();
        }

        System.out.println("============================");
    }

	public void tambahBungaAkhirBulan() {
		// TODO Auto-generated method stub
		
	}
}