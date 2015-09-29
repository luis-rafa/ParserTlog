package com.ze.wpa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tlog {
	
	public FileHeader archivo;
	public List<Transaccion> transacciones;
	public StroreClosing storeClosing;
	
	public FileHeader getArchivo() {
		return archivo;
	}

	public void setArchivo(FileHeader archivo) {
		this.archivo = archivo;
	}

	public List<Transaccion> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}

	public StroreClosing getStoreClosing() {
		return storeClosing;
	}

	public void setStroreClosing(StroreClosing stroreClosing) {
		this.storeClosing = stroreClosing;
	}

	
	
	public Tlog() {
		transacciones = new ArrayList<Transaccion>();
	}
	
	public void addTransaccion(String strTransaccion){
		// validar los tipos de string
		String tipoValido = strTransaccion.substring(0,2);
		if (validarTransacion(tipoValido)){
			Transaccion transaccion = new Transaccion(strTransaccion);
			// Validar tipos de trnasacciones dentro del string excluir transaction types 
			 transaccion.validarTransaccion();
			 if (transaccion.transaccionValida){
				 // Colocar la primer fecha de la primer cabecera encontrada
				 if (transaccion.getTransactionHeader() != null && archivo.fechaArchivo ==null){
					archivo.setFechaArchivo(transaccion.getTransactionHeader().dateTime);
				 }
				 if (transaccion.isStoreClosing){
						storeClosing = transaccion.getStoreClosing();
				 }
				 transacciones.add(transaccion);
				 
			 }
			
		}
		
		
	}
	
	public boolean validarTransacion(String strTipoTransaccion){
		if (strTipoTransaccion.equals("00") || strTipoTransaccion.equals("01") || strTipoTransaccion.equals("02") ||
				strTipoTransaccion.equals("05") ||strTipoTransaccion.equals("06") || strTipoTransaccion.equals("09") ||  
				strTipoTransaccion.equals("10") || strTipoTransaccion.equals("11") ||strTipoTransaccion.equals("21") || 
				strTipoTransaccion.equals("99")  ){
			return true;
			
		}		
		return false;
	}
	public static String calcularEan(String numeroEan){
		String nuevoEan;
		if (numeroEan.equalsIgnoreCase("9999999111112") || numeroEan.length() <12){
			nuevoEan = numeroEan;
		}
		else{
		 nuevoEan = numeroEan;
		int posicion=1;
		int sumaPar=0;
		int sumaImpar=0;
		for (int i=0;i <numeroEan.length(); i++){
			posicion++;
			if (posicion % 2 == 0){
				sumaImpar += Character.getNumericValue(numeroEan.charAt(i)); 
			}else{
				sumaPar += Character.getNumericValue(numeroEan.charAt(i)); 
			}
			
			
		}
		int sumaTotal = sumaPar*3+sumaImpar;
		int residuo =(sumaTotal % 10);
		int digitoEan;
		if (residuo ==0){
			digitoEan =0;
		}else{
			digitoEan = 10 -residuo ;
		}
		nuevoEan = nuevoEan+""+digitoEan;
		}
		return nuevoEan;
	}
	public  static String convertHexToString(String hex){
		 
		  StringBuilder sb = new StringBuilder();
		  StringBuilder temp = new StringBuilder();
	 
		  
		  for( int i=0; i<hex.length()-1; i+=2 ){
		      //grab the hex in pairs
		      String output = hex.substring(i, (i + 2));
		      //convert hex to decimal
		      int decimal = Integer.parseInt(output, 16);
		      //convert the decimal to character
		      sb.append((char)decimal);
	 
		      temp.append(decimal);
		  }
	 
		  return sb.toString();
	  }
	public String toStringXML(){
		// validar fecha en archivo
		if (archivo.fechaArchivo == null){
			if (storeClosing !=null && storeClosing.FechaCierre !=null){
				archivo.fechaArchivo = storeClosing.FechaCierre;
			}else{
				Date fecha = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmm");
				archivo.fechaArchivo = format.format(fecha);
			}
		}
		StringBuffer strXml=new StringBuffer("");
		strXml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		strXml.append("<ns0:ReplicaTransaccionesTLog xmlns:ns0=\"urn:crystal:pos:transacciones\">");
		strXml.append(archivo.toStringXml());
		
		
		// si hay transacciones diferentes a 
		boolean hayTranacciones =false;
		for (Transaccion tran : transacciones){
			
			
			if (!tran.isStoreClosing){
				if (!hayTranacciones){
					strXml.append("<Transacciones>");
					hayTranacciones =true;
				}
				strXml.append(tran.toXmlString());
			}
		}
		if (hayTranacciones){
			strXml.append("</Transacciones>");
		}
		
		if (storeClosing != null){
			strXml.append(storeClosing.toXmlString());
		}
		strXml.append("</ns0:ReplicaTransaccionesTLog>");
		return strXml.toString();
	}
}
