package com.ze.wpa;

public class Promociones {

	String idString;
	String codDato;
	String codigoPromocion;
	String valorPromocion;
	String codigoCampana;

	public Promociones(String[] subCadenas) {
		 idString=subCadenas[0];
		 codDato=subCadenas[1];
		 codigoPromocion=subCadenas[2].replace('F', '0');
		 valorPromocion=subCadenas[3].replaceAll("222C22", "").replaceAll("220D0A", "").replace('F', '0');
		 if (subCadenas.length >4){
			 codigoCampana=subCadenas[4].replaceAll("222C22", "").replaceAll("220D0A", "");;
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

	public String getCodigoPromocion() {
		return codigoPromocion;
	}

	public void setCodigoPromocion(String codigoPromocion) {
		this.codigoPromocion = codigoPromocion;
	}

	public String getValorPromocion() {
		return valorPromocion;
	}

	public void setValorPromocion(String valorPromocion) {
		this.valorPromocion = valorPromocion;
	}

	public String getCodigoCampana() {
		return codigoCampana;
	}

	public void setCodigoCampana(String codigoCampana) {
		this.codigoCampana = codigoCampana;
	}

	public String toXmlString() {
		StringBuffer strXml = new  StringBuffer("");
		strXml.append("<Promociones>");
		strXml.append("<TYPE>"+idString+"</TYPE>");
		strXml.append("<CodDato>"+codDato+"</CodDato>");
		strXml.append("<CodPromocion>"+codigoPromocion+"</CodPromocion>");
		strXml.append("<VlrPromocion>"+valorPromocion+"</VlrPromocion>");
		if (codigoCampana !=null){
			strXml.append("<CodCampana>"+codigoCampana+"</CodCampana>");
		}
		
        strXml.append("</Promociones>");
		return strXml.toString();
	}

}
