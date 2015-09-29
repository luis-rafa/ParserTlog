package com.ze.wpa;

public class Factura {

	String idString;
	String codDato;
	String numeroTerminal;
	String prefijoFiscal;
	String IDIAN;
	String numeroFactura;

	public Factura(String[] subCadenas) {
		 idString=subCadenas[0];
		 codDato=subCadenas[1];
		 numeroTerminal=subCadenas[2];
		 prefijoFiscal=Tlog.convertHexToString(subCadenas[3]);
		 IDIAN=subCadenas[4];
		 numeroFactura=subCadenas[5].replaceAll("222C22","").replaceAll("220D0A", "").replaceAll("[^\\d.]", "");
	}

	public String toXmlString() {
		StringBuffer strXml = new  StringBuffer("");
		strXml.append("<Factura>");
		strXml.append("<TYPE>"+idString+"</TYPE>");
		strXml.append("<CodDato>"+codDato+"</CodDato>");
		strXml.append("<Terminal>"+numeroTerminal+"</Terminal>");
		strXml.append("<PrefFiscal>"+prefijoFiscal+"</PrefFiscal>");
		strXml.append("<IDIAN>"+IDIAN+"</IDIAN>");
		strXml.append("<NumFactura>"+numeroFactura+"</NumFactura>");
        strXml.append("</Factura>");
		return strXml.toString();
	}

}
