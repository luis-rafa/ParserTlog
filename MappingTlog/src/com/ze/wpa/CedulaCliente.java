package com.ze.wpa;

public class CedulaCliente {
	String idString;
	String CodDato;
	String campoNoEspecificado;
	String cedulaCliente;
	String campoNoEspecificado2;
	String campoNoEspecificado3;
	String campoNoEspecificado4;
	String campoNoEspecificado5;
	String campoNoEspecificado6;

	public CedulaCliente() {
		// TODO Auto-generated constructor stub
	}
	public CedulaCliente(String[] subcadenas) {
		 idString = subcadenas[0];
		 CodDato = subcadenas[1];
		 campoNoEspecificado = subcadenas[2];
		 cedulaCliente = subcadenas[3].replaceAll("[^\\d.]", "");
		 campoNoEspecificado2 = subcadenas[4];
		 campoNoEspecificado3 = subcadenas[5];
		 campoNoEspecificado4 = subcadenas[6];
		 campoNoEspecificado5 = subcadenas[7];
		 if (subcadenas.length >8){
			 campoNoEspecificado6 = subcadenas[8].replaceAll("222C22","").replaceAll("220D0A", "");
		 }else{
			 campoNoEspecificado5 =campoNoEspecificado5.replaceAll("222C22","").replaceAll("220D0A", "");
		 }
		 
	}
	public String toXmlString() {
		StringBuffer strXml = new  StringBuffer("");
		strXml.append("<CedulaCliente>");
		strXml.append("<TYPE>"+idString+"</TYPE>");
		strXml.append("<CodDato>"+CodDato+"</CodDato>");
		strXml.append("<DATA1>"+campoNoEspecificado+"</DATA1>");
		strXml.append("<NumeroId>"+cedulaCliente+"</NumeroId>");
		strXml.append("<DATA2>"+campoNoEspecificado2+"</DATA2>");
		strXml.append("<DATA3>"+campoNoEspecificado3+"</DATA3>");
		strXml.append("<DATA4>"+campoNoEspecificado4+"</DATA4>");
		strXml.append("<DATA5>"+campoNoEspecificado5+"</DATA5>");
		strXml.append("<DATA6>"+campoNoEspecificado6+"</DATA6>");
        strXml.append("</CedulaCliente>");
		return strXml.toString();
	}
	
}
