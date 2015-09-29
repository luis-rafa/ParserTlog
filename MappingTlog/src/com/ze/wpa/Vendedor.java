package com.ze.wpa;

public class Vendedor {

	
	String idString;
	String codDato;
	String cedulaVendedor;

	public Vendedor(String[] subCadenas) {
		idString = subCadenas[0];
		codDato = subCadenas[1];
		cedulaVendedor = subCadenas[2].replaceAll("222C22", "").replaceAll("220D0A", "").replaceAll("[^\\d.]", "");;
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

	public String getCedulaVendedor() {
		return cedulaVendedor;
	}

	public void setCedulaVendedor(String cedulaVendedor) {
		this.cedulaVendedor = cedulaVendedor;
	}

	public String toXmlString() {
		StringBuffer strXml = new  StringBuffer("");
		strXml.append("<Vendedor>");
		strXml.append("<TYPE>"+idString+"</TYPE>");
		strXml.append("<CodDato>"+codDato+"</CodDato>");
		strXml.append("<CedulaVen>"+cedulaVendedor+"</CedulaVen>");
		
        strXml.append("</Vendedor>");
		return strXml.toString();
	}

}
