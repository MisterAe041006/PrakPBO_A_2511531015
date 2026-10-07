package Praktikum_Pekan4_2511531015;

import Praktikum_Minggu2.Transaksi;
import Praktikum_Minggu_1_2511531015.Rekening;

public class RekeningTabungan extends Rekening {

	//atribut spesifik yang hanya dimiliki oleh tabungan
	private double sukuBunga;
	
	//constructor subclass
	public RekeningTabungan(String nomor, String nama, double saldoAwal, String pin) {
		
		// super() memanggil constructor kelas idnuk (rekening). WAJIB berada di baris pertama!
		super(nomor, nama, saldoAwal, pin );
		this.sukuBunga= sukuBunga;
		
	}
	public void tambahBungaAkhirBulan(){
		double nominalBunga = saldo * (sukuBunga / 100);
		saldo += nominalBunga;
		
		String idTrx = "TRX-B" + System.currentTimeMillis();
		riwayatTransaksi1.add(new Transaksi(idTrx, "Bunga", nominalBunga));
		
		System.out.println("Bunga " + sukuBunga + "% Berhasil ditambahkan: Rp" + nominalBunga);
		
		
	}
}
