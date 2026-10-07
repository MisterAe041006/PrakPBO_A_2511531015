package Praktikum_Pekan4_2511531015;

import Praktikum_Minggu_1_2511531015.Rekening;

public class RekeningGiro extends Rekening {

	private double batasOverdraft;
	
	public RekeningGiro(String nomor, String nama, double saldoAwal, String pin, double batasOverdraft) {
		super(nomor, nama, saldoAwal, pin);
		this.batasOverdraft = batasOverdraft;
	}
		public double getBatasOverdraft() {
			return batasOverdraft;
			
		}
		public void tambahBungaAkhirBulan() {
			// TODO Auto-generated method stub
			
		}
	}
	
