package com.ze.wpa;

import java.util.ArrayList;
import java.util.List;

public class ManageOveride {
	String tipo;
	String claveAutorizacion;
	List<String> overrideReason;

	
	public ManageOveride() {
		// TODO Auto-generated constructor stub
	}
	public ManageOveride(String[] subcadenas) {
		
		tipo = subcadenas[0];
		claveAutorizacion = subcadenas[1].replace('F', '0');
		// si la cadena trae razones sacamos todas las razones en el string
		if (subcadenas.length >2){
			String strAutorizaciones =  subcadenas[2].replaceAll("222C22", "").replaceAll("220D0A", "");
			overrideReason = new ArrayList<String>();
	
			for (int i=0 ; i < strAutorizaciones.length(); i =i+2){
				overrideReason.add(strAutorizaciones.substring(i,i+2));
			}
		}else{ // si la cadena no trae razones quitamos el fin de transaccion y el fin de cadena y se coloca 00 por defecto como raz—n 
			claveAutorizacion = claveAutorizacion.replaceAll("222C22", "").replaceAll("220D0A", "");
			overrideReason = new ArrayList<String>();
			overrideReason.add("00");
		}
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getClaveAutorizacion() {
		return claveAutorizacion;
	}
	public void setClaveAutorizacion(String claveAutorizacion) {
		this.claveAutorizacion = claveAutorizacion;
	}
	
	public boolean tieneAlgunaAutorizacion(String autorizacion){
		boolean resultado=false;
		for (String reason : overrideReason){
			if (reason.equalsIgnoreCase(autorizacion)){
				resultado = true;
			}
		}
		return resultado;
	}
	public String toXmlString(){
		StringBuffer strXml = new StringBuffer("");
		strXml.append("<ManagerOverride>");
		strXml.append("<TYPE>"+tipo+"</TYPE>");
		strXml.append("<OVERRIDE>"+claveAutorizacion+"</OVERRIDE>");
		strXml.append("<ReasonOverride>");
		for (String reason : overrideReason){
			strXml.append("<REASON>"+reason+"</REASON>");
		}
        strXml.append("</ReasonOverride>");
        strXml.append("</ManagerOverride>");
        return strXml.toString();
	}
	public List<String> getOverrideReason() {
		return overrideReason;
	}
	public void setOverrideReason(List<String> overrideReason) {
		this.overrideReason = overrideReason;
	}
}
