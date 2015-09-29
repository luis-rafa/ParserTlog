package com.ze.wpa;

public class TEFDE1 {

	String idString;
	String CodDato;
	String Aplicacion;
	String funcion;
	String numeroReciboVoucher;
	String tipoDE;
	String campoNoespecificado;
	String fechaPosteo;
	String campoNoespecificado2;
	String valorPagar;
	String IVA;
	String fechaHoraTx;
	String campoNoespecificado3;
	String campoNoespecificado4;

	public TEFDE1(String[] subCadenas) {
		 idString= subCadenas[0];
		 CodDato= subCadenas[1];
		 Aplicacion= subCadenas[2];
		 funcion= subCadenas[3];
		 numeroReciboVoucher= subCadenas[4];
		 tipoDE= subCadenas[5];
		 campoNoespecificado= subCadenas[6];
		 fechaPosteo= subCadenas[7];
		 campoNoespecificado2= subCadenas[8];
		 valorPagar= subCadenas[9];
		 IVA= subCadenas[10];
		 fechaHoraTx= subCadenas[11];
		 campoNoespecificado3= subCadenas[12];
		 campoNoespecificado4= subCadenas[13].replaceAll("222C22", "").replaceAll("220D0A", "");;
	}

	public String toXmlString() {
		StringBuffer strXml = new  StringBuffer("");
		strXml.append("<TEFDE1>");
		strXml.append("<TYPE>"+idString+"</TYPE>");
		strXml.append("<CodDato>"+CodDato+"</CodDato>");
		strXml.append("<Aplicacion>"+Aplicacion+"</Aplicacion>");
		strXml.append("<Funcion>"+funcion+"</Funcion>");
		strXml.append("<NroRecibo>"+numeroReciboVoucher+"</NroRecibo>");
		strXml.append("<DE>"+tipoDE+"</DE>");
		strXml.append("<DATA1>"+campoNoespecificado+"</DATA1>");
		strXml.append("<FechaPosteo>"+fechaPosteo+"</FechaPosteo>");
		strXml.append("<DATA2>"+campoNoespecificado2+"</DATA2>");
		strXml.append("<Valor>"+valorPagar+"</Valor>");
		strXml.append("<IVA>"+IVA+"</IVA>");
		strXml.append("<FechaTx>"+fechaHoraTx+"</FechaTx>");
		strXml.append("<DATA3>"+campoNoespecificado3+"</DATA3>");
		strXml.append("<DATA4>"+campoNoespecificado4+"</DATA4>");
        strXml.append("</TEFDE1>");
		return strXml.toString();
	}

}
