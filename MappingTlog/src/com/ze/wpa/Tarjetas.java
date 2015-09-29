package com.ze.wpa;

public class Tarjetas {
	String idString;
	String CodDato;
	String TEF;
	String compraTarjeta;
	String numeroRecibo;
	String tipoDatos;
	String franquicia;
	String banco;
	String tipoTarjeta;
	String valorImporte;
	String valorImpuesto;
	Tender tender;
	Tender tenderCorrection;
	
	public Tarjetas() {
		// TODO Auto-generated constructor stub
	}
	public Tarjetas(String[] subcadenas) {
		 idString=subcadenas[0];
		 CodDato=subcadenas[1];
		 TEF=subcadenas[2];
		 compraTarjeta=subcadenas[3];
		 numeroRecibo=subcadenas[4];
		 tipoDatos=subcadenas[5];
		 franquicia=Tlog.convertHexToString(subcadenas[6]);
		 banco=subcadenas[7];
		 tipoTarjeta=subcadenas[8];
		 valorImporte=subcadenas[9];
		 valorImporte=valorImporte.replaceAll("F", "0").replaceAll("[^\\d.]", "");
		 valorImpuesto=subcadenas[10].replaceAll("222C22", "").replaceAll("220D0A", "");
		 //valorImpuesto=valorImpuesto.replaceAll("F", "0");
		 valorImpuesto=valorImpuesto.replaceAll("[^\\d.]", "");
	}
	public String getIdString() {
		return idString;
	}
	public void setIdString(String idString) {
		this.idString = idString;
	}
	public String getCodDato() {
		return CodDato;
	}
	public void setCodDato(String codDato) {
		CodDato = codDato;
	}
	public String getTEF() {
		return TEF;
	}
	public void setTEF(String tEF) {
		TEF = tEF;
	}
	public String getCompraTarjeta() {
		return compraTarjeta;
	}
	public void setCompraTarjeta(String compraTarjeta) {
		this.compraTarjeta = compraTarjeta;
	}
	public String getNumeroRecibo() {
		return numeroRecibo;
	}
	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}
	public String getTipoDatos() {
		return tipoDatos;
	}
	public void setTipoDatos(String tipoDatos) {
		this.tipoDatos = tipoDatos;
	}
	public String getFranquicia() {
		return franquicia;
	}
	public void setFranquicia(String franquicia) {
		this.franquicia = franquicia;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
	public String getValorImporte() {
		return valorImporte;
	}
	public void setValorImporte(String valorImporte) {
		this.valorImporte = valorImporte;
	}
	public String getValorImpuesto() {
		return valorImpuesto;
	}
	public void setValorImpuesto(String valorImpuesto) {
		this.valorImpuesto = valorImpuesto;
	}
	public Tender getTender() {
		return tender;
	}
	public void setTender(Tender tender) {
		this.tender = tender;
	}
	public Tender getTenderCorrection() {
		return tenderCorrection;
	}
	public void setTenderCorrection(Tender tenderCorrection) {
		this.tenderCorrection = tenderCorrection;
	}
	public String toXmlString() {
		StringBuffer strXml = new  StringBuffer("");
		strXml.append("<Tarjeta>");
		strXml.append("<TYPE>"+idString+"</TYPE>");
		strXml.append("<CodDato>"+CodDato+"</CodDato>");
		strXml.append("<TEF>"+TEF+"</TEF>");
		strXml.append("<CompraTarjeta>"+compraTarjeta+"</CompraTarjeta>"); 
		strXml.append("<NumRecibo>"+numeroRecibo+"</NumRecibo>");
		strXml.append("<DE>"+tipoDatos+"</DE>");
		strXml.append("<Franquicia>"+franquicia+"</Franquicia>");
		strXml.append("<Banco>"+banco+"</Banco>"); 
		strXml.append("<TipoTarjeta>"+tipoTarjeta+"</TipoTarjeta>");
		strXml.append("<Importe>"+valorImporte+"</Importe>");
		strXml.append("<VlrImpuesto>"+valorImpuesto+"</VlrImpuesto>");

    	strXml.append("</Tarjeta>");
		
		return strXml.toString();
	}
}
