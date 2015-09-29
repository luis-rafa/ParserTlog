package com.ze.wpa;

import java.util.List;

public class Tender {
	String tipo;
	String tipoMedioPago;
	String Importe;
	String tenderCashingFeeAmount;
	String customerAccountNumber;
	String accountStatusCode;
	public Tarjetas tarjeta;
	public TEFDE1 tefde1;
	public TEFDE2 tefde2;
	public TEFDE3 tefde3;
	public Tender() {
		// TODO Auto-generated constructor stub
	}
	public Tender(String[] subcadenas) {
		 tipo=subcadenas[0];
		 tipoMedioPago=subcadenas[1];
		 Importe=subcadenas[2].replace('F', '0');
		 if (Importe.equalsIgnoreCase("")){
			 Importe="0";
		 }
		 tenderCashingFeeAmount=subcadenas[3].replace('F', '0');;
		 if (tenderCashingFeeAmount.equalsIgnoreCase("")){
			 tenderCashingFeeAmount ="0";
		 }
		 customerAccountNumber=subcadenas[4].replace('F', '0');;
		 accountStatusCode=subcadenas[5].replaceAll("222C22", "").replaceAll("220D0A", "");;
		
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTipoMedioPago() {
		return tipoMedioPago;
	}
	public void setTipoMedioPago(String tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}
	public String getImporte() {
		return Importe;
	}
	public void setImporte(String importe) {
		Importe = importe;
	}
	public String getTenderCashingFeeAmount() {
		return tenderCashingFeeAmount;
	}
	public void setTenderCashingFeeAmount(String tenderCashingFeeAmount) {
		this.tenderCashingFeeAmount = tenderCashingFeeAmount;
	}
	public String getCustomerAccountNumber() {
		return customerAccountNumber;
	}
	public void setCustomerAccountNumber(String customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}
	public String getAccountStatusCode() {
		return accountStatusCode;
	}
	public void setAccountStatusCode(String accountStatusCode) {
		this.accountStatusCode = accountStatusCode;
	}
	public String toXmlString() {
		StringBuffer strXml = new  StringBuffer("");
		strXml.append("<Tender>");
		strXml.append("<TYPE>"+tipo+"</TYPE>");
		strXml.append("<TENDTYPE>"+tipoMedioPago+"</TENDTYPE>");
		strXml.append("<AMTTENDE>"+Importe+"</AMTTENDE>"); 
		strXml.append("<AMTTNFEE>"+tenderCashingFeeAmount+"</AMTTNFEE>"); 
		strXml.append("<CUSTOMER>"+customerAccountNumber+"</CUSTOMER>"); 
		strXml.append("<STATUS>"+accountStatusCode+"</STATUS>"); 
         strXml.append("</Tender>");
		return strXml.toString();
	}
	public String toXmlString(char c) {
		StringBuffer strXml = new  StringBuffer("");
		if (c =='T'){
			strXml.append("<Tender>");
		}else{
			strXml.append("<TenderCorrection>");
		}
		
		strXml.append("<TYPE>"+tipo+"</TYPE>");
		strXml.append("<TENDTYPE>"+tipoMedioPago+"</TENDTYPE>");
		strXml.append("<AMTTENDE>"+Importe+"</AMTTENDE>"); 
		strXml.append("<AMTTNFEE>"+tenderCashingFeeAmount+"</AMTTNFEE>"); 
		strXml.append("<CUSTOMER>"+customerAccountNumber+"</CUSTOMER>"); 
		strXml.append("<STATUS>"+accountStatusCode+"</STATUS>"); 
		if (tarjeta !=null){
			strXml.append(tarjeta.toXmlString());
		}
		
         
        if (c =='T'){
        	if (tefde1 != null){
    			strXml.append(tefde1.toXmlString());
    		}
    		if (tefde2 != null){
    			strXml.append(tefde2.toXmlString());
    		}
    		if (tefde3 != null){
    			strXml.append(tefde3.toXmlString());
    		}
        	 strXml.append("</Tender>");
 		}else{
 			 strXml.append("</TenderCorrection>");
 		}
		return strXml.toString();
	}
	public Tarjetas getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(Tarjetas tarjeta) {
		this.tarjeta = tarjeta;
	}
	public TEFDE1 getTefde1() {
		return tefde1;
	}
	public void setTefde1(TEFDE1 tefde1) {
		this.tefde1 = tefde1;
	}
	public TEFDE2 getTefde2() {
		return tefde2;
	}
	public void setTefde2(TEFDE2 tefde2) {
		this.tefde2 = tefde2;
	}
	public TEFDE3 getTefde3() {
		return tefde3;
	}
	public void setTefde3(TEFDE3 tefde3) {
		this.tefde3 = tefde3;
	}
}
