package Praktikum_Minggu_1_2511531015;
import java.util.Scanner; 
public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Rekening akunAktif = null; //Objek belum diinisialisasikan (null)
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBAIKAN MINI ===");
		
		while (isRunning) {
			System.out.println("\nMenu Utama:");
			System.out.println("1. Buka rekening baru");
			System.out.println("2. setor tunai");
			System.out.println("3. tarik tunai");
			System.out.println("4. cek informasi rekening");
			System.out.println("0. keluar");
			System.out.println("pilih menu: ");
			
			int pilihan = input.nextInt();
			input.nextLine();
			
			switch (pilihan) {
			case 1:
				System.out.print("masukkan no rekening: ");
				String no = input.nextLine();
				System.out.print("masukkkan nama pemilik: ");
				String nama = input.nextLine();
				System.out.print("masukkan saldo awal:");
				double saldo = input.nextDouble();
				
				akunAktif = new Rekening(no, nama, saldo);
				break;
				
			case 2:
				if(akunAktif == null) {
					System.out.println("Error: mohon maaf, anda belum memiliki nomor rekening!");
			
				}else {
					System.out.print("masukkan nominal setor: ");
					double setor = input.nextDouble();
					akunAktif.setorTunai(setor);
				}
				break;
			case 3:
				if(akunAktif == null) {
					System.out.println("Transaksi gagal: saldo tidak mencukupi. Saldo anda: Rp[nominal]");
				}else {
					System.out.print("masukkan nominal tarik tunai: ");
				}
				double tarik = input.nextDouble();
				akunAktif.tarikTunai(tarik);
			
				break;
				
			case 4:
				if (akunAktif == null) {
					System.out.println("Error: anda belum membuka rekening!");
				}else {
					akunAktif.cekInformasi();
				}
				break;
				
			case 0:
				isRunning = false;
				System.out.println("Sistem ditutup. Terima kasihh!");
				break;
				
				default:
					System.out.println("Pilihan tidak valid!");
				
				
			}
		}
		input.close();
	}

}
