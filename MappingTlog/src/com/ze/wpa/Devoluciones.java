package com.ze.wpa;

public class Devoluciones {
	public String idString;
	public String codDato;
	public String motivodeDevolucion;
	public String codigoEAN;
	public String precioArticulo;

	
	public Devoluciones() {
		// TODO Auto-generated constructor stub
	}
	public Devoluciones(String[] subcadenas) {
		idString = subcadenas[0];
		codDato = subcadenas[1].replace('F','0');
		motivodeDevolucion = subcadenas[2].replace('F','0');
		codigoEAN = subcadenas[3];
		precioArticulo = subcadenas[4].replaceAll("222C22","").replaceAll("220D0A", "").replace('F','0');
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
	public String getMotivodeDevolucion() {
		return motivodeDevolucion;
	}
	public void setMotivodeDevolucion(String motivodeDevolucion) {
		this.motivodeDevolucion = motivodeDevolucion;
	}
	public String getCodigoEAN() {
		return codigoEAN;
	}
	public void setCodigoEAN(String codigoEAN) {
		this.codigoEAN = codigoEAN;
	}
	public String getPrecioArticulo() {
		return precioArticulo;
	}
	public void setPrecioArticulo(String precioArticulo) {
		this.precioArticulo = precioArticulo;
	}
	public String toXmlString(){
		StringBuffer strXml = new  StringBuffer("");
		strXml.append("<Devoluciones>");
		strXml.append("<TYPE>"+idString+"</TYPE>");
		strXml.append("<CodDato>"+codDato+"</CodDato>");
		strXml.append("<MotivoDevolucion>"+motivodeDevolucion+"</MotivoDevolucion>");
		strXml.append("<ItemCode>"+codigoEAN+"</ItemCode>");
		strXml.append("<precioArticulo>"+precioArticulo+"</precioArticulo>");
		strXml.append("</Devoluciones>");
		return strXml.toString();
	}
}
