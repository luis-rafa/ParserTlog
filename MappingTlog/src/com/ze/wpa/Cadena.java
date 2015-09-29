package com.ze.wpa;

public class Cadena {
	
//	public enum TipoCadena{
//		HEADER("00"),ITEMENTRY("00"),ITEMEXT("00"),TENDER("00"),TENDERCOR("00"),FOODSTAMP("00"),AUTORIZACION("00"),TEF("00"),STORE("00");
//		public String value;
//		private TipoCadena(String value){
//			this.value = value;
//		}
//	
//
//	}
	String tipo;
	String[] subCadenas;
	public Cadena(String strCadena) {
		
		subCadenas = strCadena.split("3A");
		tipo = subCadenas[0];
	}
}
