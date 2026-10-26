package Praktikum_Pekan5_2511531015;

import java.util.UUID;
import Praktikum_Minggu2.Transaksi;

public class RekeningGiro extends Rekening {

    private final double batasOverdraft;

    public RekeningGiro(
            String nomor,
            String nama,
            double saldoAwal,
            String pin,
            double batasOverdraft) {

        super(nomor, nama, saldoAwal, pin);

        if (!Double.isFinite(batasOverdraft) || batasOverdraft < 0) {
            throw new IllegalArgumentException(
                "Batas overdraft harus valid dan tidak negatif!"
            );
        }

        this.batasOverdraft = batasOverdraft;
    }

    public double getBatasOverdraft() {
        return batasOverdraft;
    }

    @Override
    public boolean tarikTunai(double nominal) {
        if (!Double.isFinite(nominal) || nominal <= 0) {
            System.out.println("Gagal: nominal tarik tidak valid!");
            return false;
        }

        double saldoSetelah = saldo - nominal;

        if (!Double.isFinite(saldoSetelah)
                || saldoSetelah < -batasOverdraft) {

            System.out.println(
                "Gagal: penarikan melewati batas overdraft!"
            );
            return false;
        }

        String idTrx = "TRX-G-" + UUID.randomUUID();

        riwayatTransaksi1.add(
            new Transaksi(idTrx, "Debit Giro", nominal)
        );

        saldo = saldoSetelah;

        System.out.println(
            "Tarik giro Rp" + nominal
            + " berhasil. Saldo: Rp" + saldo
        );

        return true;
    }

    @Override
    public void cekInformasi() {
        super.cekInformasi();

        System.out.println(
            "Batas overdraft: Rp" + batasOverdraft
        );
    }
}