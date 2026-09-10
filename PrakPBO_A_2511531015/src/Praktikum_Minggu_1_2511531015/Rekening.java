package Praktikum_Minggu_1_2511531015;

public class Rekening {
String nomorRekening;
String namaPemilik;
double saldo;

public Rekening(String nomor, String nama, double saldoAwal) {
	nomorRekening = nomor;
	namaPemilik = nama;
	saldo = saldoAwal;
	System.out.println("rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp" + saldo);
}

public void setorTunai(double nominal) {
	if (nominal >= 0) {
		saldo += nominal;
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
		if (nominal >= 50.000) {
			saldo += nominal;
			System.out.println("Tarik tunai Rp" + nominal + "berhasil tarik tunai: Rp" + saldo);
			
		}else {
			System.out.println("Gagal: nominal Tarik Tunai harus lebih dari Rp.10.000!");
			
		}
	}
}


