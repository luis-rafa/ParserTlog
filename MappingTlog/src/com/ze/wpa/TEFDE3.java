package com.ze.wpa;

public class TEFDE3 {

	String idString;
	String CodDato;
	String Aplicacion;
	String funcion;
	String numeroReciboVoucher;
	String tipoDE;
	String baseDevolucionIVA;
	String valorCashback;
	String fechaProceso;
	String valorDonacion;
	String nroReciboAnular;
	String campoNoEspecificado;
	String fechaSistema;
	String BINtarjeta;
	String RRN;
	String fechaVencimientoTarjeta;

	public TEFDE3(String[] subCadenas) {
		 idString= subCadenas[0];
		 CodDato= subCadenas[1];
		 Aplicacion= subCadenas[2];
		 funcion= subCadenas[3];
		 numeroReciboVoucher= subCadenas[4];
		 tipoDE= subCadenas[5];
		 baseDevolucionIVA= subCadenas[6];
		 valorCashback= subCadenas[7];
		 fechaProceso= subCadenas[8];
		 valorDonacion= subCadenas[9];
		 nroReciboAnular= subCadenas[10];
		 campoNoEspecificado= subCadenas[11];
		 fechaSistema= subCadenas[12];
		 BINtarjeta= subCadenas[13];
		 RRN= subCadenas[14];
		 fechaVencimientoTarjeta= subCadenas[15].replaceAll("222C22", "").replaceAll("220D0A", "");;
	}

	public String toXmlString() {
		StringBuffer strXml = new  StringBuffer("");
		strXml.append("<TEFDE3>");
		strXml.append("<TYPE>"+idString+"</TYPE>");
		strXml.append("<CodDato>"+CodDato+"</CodDato>");
		strXml.append("<Aplicacion>"+Aplicacion+"</Aplicacion>");
		strXml.append("<Funcion>"+funcion+"</Funcion>");
		strXml.append("<NroRecibo>"+numeroReciboVoucher+"</NroRecibo>");
		strXml.append("<DE>"+tipoDE+"</DE>");
		strXml.append("<BaseDevolucion>"+baseDevolucionIVA+"</BaseDevolucion>");
		strXml.append("<VrCashback>"+valorCashback+"</VrCashback>");
		strXml.append("<FechaProceso>"+fechaProceso+"</FechaProceso>");
		strXml.append("<VrDonacion>"+valorDonacion+"</VrDonacion>");
		strXml.append("<NroReciboAnular>"+nroReciboAnular+"</NroReciboAnular>");
		strXml.append("<DATA1>"+campoNoEspecificado+"</DATA1>");
		strXml.append("<FechaSistema>"+fechaSistema+"</FechaSistema>");
		strXml.append("<BIN>"+BINtarjeta+"</BIN>");
		strXml.append("<RRN>"+RRN+"</RRN>");
		strXml.append("<FechaVencimiento>"+fechaVencimientoTarjeta+"</FechaVencimiento>");
		
		
        strXml.append("</TEFDE3>");
		return strXml.toString();
	}

}
