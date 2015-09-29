package com.ze.wpa;

public class RFID {
	String idString ;
	String codDato;
	String codigoEAN;
	String serial;
	String campoNoEspecificado;
	String campoNoEspecificado2;
	
	public RFID(String[] subcadenas) {
		idString = subcadenas[0];
		codDato = subcadenas[1];
		codigoEAN = subcadenas[2];
		serial = subcadenas[3];
		campoNoEspecificado = subcadenas[4];
		campoNoEspecificado2 = subcadenas[5].replaceAll("222C22", "").replaceAll("220D0A", "");
	}
	public String getIdString() {
		return idString;
	}
	public void setIdString(String idString) {
		this.idString = idString;
	}
	public String getCodDato() {
		return codDato;
	}
	public void setCodDato(String codDato) {
		this.codDato = codDato;
	}
	public String getCodigoEAN() {
		return codigoEAN;
	}
	public void setCodigoEAN(String codigoEAN) {
		this.codigoEAN = codigoEAN;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getCampoNoEspecificado() {
		return campoNoEspecificado;
	}
	public void setCampoNoEspecificado(String campoNoEspecificado) {
		this.campoNoEspecificado = campoNoEspecificado;
	}
	public String getCampoNoEspecificado2() {
		return campoNoEspecificado2;
	}
	public void setCampoNoEspecificado2(String campoNoEspecificado2) {
		this.campoNoEspecificado2 = campoNoEspecificado2;
	}

	public String toXmlString(){
		StringBuffer strXml= new StringBuffer("");
		 strXml.append("<RFID>");
		 strXml.append("<TYPE>"+idString+"</TYPE>"); 
		 strXml.append("<CodDato>"+codDato+"</CodDato>");   
		 strXml.append("<CodEAN>"+codigoEAN+"</CodEAN>");
		 strXml.append("<Serial>"+serial+"</Serial>");  
		 strXml.append("<DATA1>"+campoNoEspecificado+"</DATA1>");   
		 strXml.append("<DATA2>"+campoNoEspecificado2+"</DATA2>");   
		 strXml.append("</RFID>");
		 return strXml.toString();
	}
}
