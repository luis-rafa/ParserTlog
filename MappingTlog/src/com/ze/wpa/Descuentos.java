package com.ze.wpa;

public class Descuentos {

	String idString;
	String codDato;
	String codigoEAN;
	String valorDescuento;
	String codigosPromocion;

	public Descuentos(String[] subCadenas) {
		 idString = subCadenas[0];
		 codDato =subCadenas[1];
		 codigoEAN=Tlog.calcularEan(subCadenas[2]);
		 valorDescuento=subCadenas[3].replaceAll("222C22", "").replaceAll("220D0A", "").replace('F', '0');
		 if (valorDescuento.equalsIgnoreCase("")){
			 valorDescuento ="0";
		 }
		 if (subCadenas.length >4){
			 codigosPromocion=subCadenas[4].replaceAll("222C22", "").replaceAll("220D0A", "");
		 }
		
		
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

	public String getValorDescuento() {
		return valorDescuento;
	}

	public void setValorDescuento(String valorDescuento) {
		this.valorDescuento = valorDescuento;
	}

	public String getCodigosPromocion() {
		return codigosPromocion;
	}

	public void setCodigosPromocion(String codigosPromocion) {
		this.codigosPromocion = codigosPromocion;
	}

	public String toXmlString() {
		StringBuffer strXml = new  StringBuffer("");
		strXml.append("<Descuentos>");
		strXml.append("<TYPE>"+idString+"</TYPE>");
		strXml.append("<CodDato>"+codDato+"</CodDato>");
		strXml.append("<EAN>"+codigoEAN+"</EAN>");
		strXml.append("<VlrDescuento>"+valorDescuento+"</VlrDescuento>");
		if (codigosPromocion !=null){
			strXml.append("<CodPromocion>"+codigosPromocion+"</CodPromocion>");
		}
        strXml.append("</Descuentos>");
		return strXml.toString();
	}

}
