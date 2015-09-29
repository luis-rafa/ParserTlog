package com.ze.wpa;

public class Change {
	String tipo;
	String tenderDesignation;
	String changeamount;
	
	public Change() {
		// TODO Auto-generated constructor stub
	}
	public Change(String[] subcadenas) {
		tipo = subcadenas[0];
		tenderDesignation = subcadenas[1];
		changeamount = subcadenas[2].replaceAll("222C22","").replaceAll("220D0A", "").replace('F', '0');
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTenderDesignation() {
		return tenderDesignation;
	}
	public void setTenderDesignation(String tenderDesignation) {
		this.tenderDesignation = tenderDesignation;
	}
	public String getChangeamount() {
		return changeamount;
	}
	public void setChangeamount(String changeamount) {
		this.changeamount = changeamount;
	}
	public String toXmlString(){
		StringBuffer strXml = new  StringBuffer("");
		strXml.append("<Change>");
		strXml.append("<TYPE>"+tipo+"</TYPE>");
		strXml.append("<TENDTYPE>"+tenderDesignation+"</TENDTYPE>");
		strXml.append("<AMTCHANGE>"+changeamount+"</AMTCHANGE>");
        strXml.append("</Change>");
    
     return strXml.toString();
	}
}
