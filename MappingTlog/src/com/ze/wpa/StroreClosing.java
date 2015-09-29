package com.ze.wpa;

public class StroreClosing {
	String tipo;
	String FechaCierre;
	String closeTypeIndication;
	String Indicat2;
	String campoNoEspecificado;
	public StroreClosing() {
		
	}
	public StroreClosing(String[] subcadenas) {
		tipo= subcadenas[0];
		 FechaCierre= subcadenas[1];;
		 closeTypeIndication= subcadenas[2];
		 Indicat2= subcadenas[3];;
		 campoNoEspecificado= subcadenas[4].replaceAll("222C22", "").replaceAll("220D0A", "");;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getFechaCierre() {
		return FechaCierre;
	}
	public void setFechaCierre(String fechaCierre) {
		FechaCierre = fechaCierre;
	}
	public String getCloseTypeIndication() {
		return closeTypeIndication;
	}
	public void setCloseTypeIndication(String closeTypeIndication) {
		this.closeTypeIndication = closeTypeIndication;
	}
	public String getIndicat2() {
		return Indicat2;
	}
	public void setIndicat2(String indicat2) {
		Indicat2 = indicat2;
	}
	public String getCampoNoEspecificado() {
		return campoNoEspecificado;
	}
	public void setCampoNoEspecificado(String campoNoEspecificado) {
		this.campoNoEspecificado = campoNoEspecificado;
	}
	public String toXmlString(){
		StringBuffer strXml = new  StringBuffer("");
		strXml.append("<StoreClosing>");
		strXml.append("<TYPE>"+tipo+"</TYPE>");
		strXml.append("<DATETIME>"+FechaCierre+"</DATETIME>");
		strXml.append("<INDICAT1>"+closeTypeIndication+"</INDICAT1>");
		strXml.append("<INDICAT2>"+Indicat2+"</INDICAT2>");
		strXml.append("<DATA1>"+campoNoEspecificado+"</DATA1>");
		
        strXml.append("</StoreClosing>");
		return strXml.toString();
	
	}
}
