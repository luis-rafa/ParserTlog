package com.ze.wpa;

import java.lang.reflect.Field;

public class TransactionHeader {
	
	
	String tipo;
	String terminal;
	String transnum;
	String dateTime;
	String tipoTransaccion;
	String numString;
	String operator;
	String password;
	String grospos;
	String grossneg;
	String ringTime;
	String tenderTime;
	String special;
	String inactive;
	int indicadoresCabecera;
	
	public TransactionHeader() {
		
		
	}
	public TransactionHeader(String[] subCadenas ) {
		tipo = subCadenas[0].replace('F', '0');
		terminal = subCadenas[1].replace('F', '0').replaceAll("[^\\d.]", "");
		transnum = subCadenas[2].replace('F', '0').replaceAll("[^\\d.]", "");
		dateTime= subCadenas[3].replace('F', '0');
		tipoTransaccion= subCadenas[4].replace('F', '0');
		
		numString= subCadenas[5].replace('F', '0');
		if (numString.equalsIgnoreCase("")){
			numString = "0";
		}
		operator= subCadenas[6].replace('F', '0').replaceAll("[^\\d.]", "");
		password= subCadenas[7].replace('F', '0');
		grospos= subCadenas[8].replace('F', '0');
		grossneg= subCadenas[9].replace('F', '0');
		ringTime= subCadenas[10].replace('F', '0');
		tenderTime= subCadenas[11].replace('F', '0');
		special= subCadenas[12].replace('F', '0');
		inactive= subCadenas[13].replace('F', '0');
		String finalCadena = subCadenas[14].replaceAll("222C22", "").replaceAll("220D0A", "");
		if (finalCadena.length() >0){
			indicadoresCabecera = Integer.parseInt(finalCadena.replace('F', '0'));
		}else{
			indicadoresCabecera=0;
		}
	}
	@Override
	public String toString() {
		String strTransactionHeader =  "";
		for (Field campo: TransactionHeader.class.getDeclaredFields()){
			try {
				strTransactionHeader += campo.get(this)+",";
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return strTransactionHeader;
	}
	public String toXmlString() {
		StringBuffer strXml = new StringBuffer("");
		strXml.append("<TransactionHeader>");
		strXml.append("<TYPE>"+tipo+"</TYPE>");
		strXml.append("<TERMINAL>"+terminal+"</TERMINAL>");
		strXml.append("<TRANSNUM>"+transnum+"</TRANSNUM>");
		strXml.append("<DATETIME>"+dateTime+"</DATETIME>");
		strXml.append("<TRANTYPE>"+tipoTransaccion+"</TRANTYPE>");
		strXml.append("<NUMSTRIN>"+numString+"</NUMSTRIN>");
		strXml.append("<OPERATOR>"+operator+"</OPERATOR>");
		strXml.append("<PASSWORD>"+password+"</PASSWORD>");
		strXml.append("<GROSSPOS>"+grospos+"</GROSSPOS>");
		strXml.append("<GROSSNEG>"+grossneg+"</GROSSNEG>");
		strXml.append("<RINGTIME>"+ringTime+"</RINGTIME>");
		strXml.append("<TENDERTI>"+tenderTime+"</TENDERTI>");
		strXml.append("<SPECIAL>"+special+"</SPECIAL>");
		strXml.append("<INACTIVE>"+inactive+"</INACTIVE>");
		strXml.append("<INDICADORES>");
		String numeroBinary =String.format("%32s", Integer.toBinaryString(indicadoresCabecera)).replace(' ', '0');
		numeroBinary = new StringBuffer(numeroBinary).reverse().toString();
		for (int i=0; i < numeroBinary.length() ;i++){
			
			String valorbit = numeroBinary.charAt(i) == '1' ? "true" :"false";
			if (i <10){
				strXml.append("<BIT0"+i+">"+valorbit+"</BIT0"+i+">");
			}
			else{
				strXml.append("<BIT"+i+">"+valorbit+"</BIT"+i+">");
			}
		}
		strXml.append("</INDICADORES>");
		strXml.append("</TransactionHeader>");
		return strXml.toString();
	}
	
}
