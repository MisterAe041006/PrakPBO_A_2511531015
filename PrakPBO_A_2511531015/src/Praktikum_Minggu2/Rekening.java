package Praktikum_Minggu_1_2511531015;

import java.util.ArrayList;
import Praktikum_Minggu2.Transaksi;

public class Rekening {
String nomorRekening;
String namaPemilik;
double saldo;

//Implementasi Asosiasi (1 to many)
ArrayList<Transaksi> riwayatTransaksi;

public Rekening(String nomor, String nama, double saldoAwal) {
	nomorRekening = nomor;
	namaPemilik = nama;
	saldo = saldoAwal;
	
	//wajib menginisialisasikan arraylist di dalam construktor agar tidak nullPointerException
	this.riwayatTransaksi = new ArrayList<>();
	
	System.out.println("rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp" + saldo);
}

public void setorTunai(double nominal) {
	if (nominal >= 0) {
		saldo += nominal;
		
		// merekam riwayat (pembuatan objek transaksi di dalam method)
		String idTrx = "TRX-S-" +System.currentTimeMillis();
		Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
		riwayatTransaksi.add(trxBaru);
		
		System.out.println("setor tunai Rp" + nominal + " berhasil. saldo saat ini: Rp" + saldo);
		
	}else  {
		System.out.println("Gagal: nominal setor harus lebih dari 0!");
	}
	}
	public void cekInformasi() {
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening : " + nomorRekening);
		System.out.println("Nama Pemilik : " + namaPemilik);
		System.out.println("Saldo Akhir : Rp" + saldo);
		System.out.println("---------------------");
		
	
}
		public void tarikTunai(double nominal) {

		    if (nominal >= 10000) {

		        if (saldo >= nominal) {

		            // Kurangi saldo
		            saldo -= nominal;

		            // Membuat kode transaksi
		            String kodeTransaksi = "TRX-T-" + System.currentTimeMillis();

		            // Mencatat transaksi sebagai Debit
		            Transaksi transaksi = new Transaksi(
		                kodeTransaksi,
		                "Debit",
		                nominal
		            );

		            ArrayList<Transaksi> daftarTransaksi;
					// Menambahkan transaksi ke daftar transaksi
		            riwayatTransaksi.add(transaksi);

		            System.out.println(
		                "Tarik tunai Rp" + nominal +
		                " berhasil. Saldo sekarang: Rp" + saldo
		            );

		        } else {
		            System.out.println("Gagal: saldo tidak mencukupi!");
		        }

		    } else {
		        System.out.println(
		            "Gagal: nominal tarik tunai harus minimal Rp10.000!"
		        );
		    }
		}

	public String getNoRekening() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNamaPemilik() {
		// TODO Auto-generated method stub
		return null;
	}
	public void cetakMutasi() {

	    if (riwayatTransaksi.isEmpty()) {

	        System.out.println("Belum ada transaksi pada rekening ini");

	    } else {

	        System.out.println("===== RIWAYAT TRANSAKSI =====");

	        for (Transaksi transaksi : riwayatTransaksi) {
	            transaksi.cetakDetail();
	        }

	    }
	}
}


