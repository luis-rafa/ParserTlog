package com.ze.wpa;

public class TEFDE2 {

	String idString;
	String CodDato;
	String Aplicacion;
	String funcion;
	String numeroReciboVoucher;
	String tipoDE;
	String codigoComercio;
	String codigoTermnal;
	String codigoRespuesta;
	String fechaPosteo;
	String codigoPosteo;
	String nroTarjeta;
	String  nroAutorizacion;
	String valor;
	String IVA;
	String fechaProceso;
	String propina;
	String RRN;
	String cuotas;
	String tipoVariedadPago;
	String campoNoespecificado;

	public TEFDE2(String[] subCadenas) {
		 idString= subCadenas[0];
		 CodDato= subCadenas[1];
		 Aplicacion= subCadenas[2];
		 funcion= subCadenas[3];
		 numeroReciboVoucher= subCadenas[4];
		 tipoDE= subCadenas[5];
		 codigoComercio= subCadenas[6];
		 codigoTermnal= subCadenas[7];
		 codigoRespuesta= subCadenas[8];
		 fechaPosteo= subCadenas[9];
		 codigoPosteo= subCadenas[10];
		 nroTarjeta= subCadenas[11];
		  nroAutorizacion= subCadenas[12];
		 valor= subCadenas[13];
		 IVA= subCadenas[14];
		 fechaProceso= subCadenas[15];
		 propina= subCadenas[16];
		 RRN= subCadenas[17];
		 cuotas= subCadenas[18];
		 tipoVariedadPago= subCadenas[19];
		 campoNoespecificado= subCadenas[20].replaceAll("222C22", "").replaceAll("220D0A", "");;
	}

	public String toXmlString() {
		StringBuffer strXml = new  StringBuffer("");
		strXml.append("<TEFDE2>");
		strXml.append("<TYPE>"+idString+"</TYPE>");
		strXml.append("<CodDato>"+CodDato+"</CodDato>");
		strXml.append("<Aplicacion>"+Aplicacion+"</Aplicacion>");
		strXml.append("<Funcion>"+funcion+"</Funcion>");
		strXml.append("<NroRecibo>"+numeroReciboVoucher+"</NroRecibo>");
		strXml.append("<DE>"+tipoDE+"</DE>");
		strXml.append("<CodComercio>"+codigoComercio+"</CodComercio>");
		strXml.append("<CodTerminal>"+codigoTermnal+"</CodTerminal>");
		strXml.append("<CodRespuesta>"+codigoRespuesta+"</CodRespuesta>");
		strXml.append("<FechaPosteo>"+fechaPosteo+"</FechaPosteo>");
		strXml.append("<CodProceso>"+codigoPosteo+"</CodProceso>");
		strXml.append("<NroTarjeta>"+nroTarjeta+"</NroTarjeta>");
		strXml.append("<NroAutorizacion>"+nroAutorizacion+"</NroAutorizacion>");
		strXml.append("<Valor>"+valor+"</Valor>");
		strXml.append("<IVA>"+IVA+"</IVA>");
		strXml.append("<FechaProceso>"+fechaProceso+"</FechaProceso>");
		strXml.append("<Propina>"+propina+"</Propina>");
		strXml.append("<RRN>"+RRN+"</RRN>");
		strXml.append("<NroCuotas>"+cuotas+"</NroCuotas>");
		strXml.append("<TipoPago>"+tipoVariedadPago+"</TipoPago>");
		strXml.append("<DATA1>"+campoNoespecificado+"</DATA1>");
        strXml.append("</TEFDE2>");
		return strXml.toString();
	}

}
