package Praktikum_Pekan5_2511531015;

import java.util.UUID;
import Praktikum_Minggu2.Transaksi;

public class RekeningTabungan extends Rekening {

    private final double sukuBunga;

    public RekeningTabungan(
            String nomor,
            String nama,
            double saldoAwal,
            String pin,
            double sukuBunga) {

        super(nomor, nama, saldoAwal, pin);

        if (!Double.isFinite(sukuBunga) || sukuBunga < 0) {
            throw new IllegalArgumentException(
                "Suku bunga harus valid dan tidak negatif!"
            );
        }

        this.sukuBunga = sukuBunga;
    }

    public void tambahBungaAkhirBulan() {
        double nominalBunga = saldo * (sukuBunga / 100);

        if (!Double.isFinite(nominalBunga)
                || !Double.isFinite(saldo + nominalBunga)) {

            System.out.println(
                "Gagal: hasil perhitungan bunga tidak valid!"
            );
            return;
        }

        String idTrx = "TRX-B-" + UUID.randomUUID();

        riwayatTransaksi1.add(
            new Transaksi(idTrx, "Bunga", nominalBunga)
        );

        saldo += nominalBunga;

        System.out.println(
            "Bunga " + sukuBunga
            + "% berhasil ditambahkan: Rp" + nominalBunga
        );

        System.out.println("Saldo sekarang: Rp" + saldo);
    }
}