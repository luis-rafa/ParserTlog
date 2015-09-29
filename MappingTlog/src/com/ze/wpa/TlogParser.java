package com.ze.wpa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TlogParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			File archivo = new File("LG150716.027");
			try {
				FileInputStream is = new FileInputStream(archivo);
				System.out.println("Numero de bytes"+is.available());
				System.out.println(is.toString());
				int bytesCounter =0;		
				int value = 0;
				StringBuilder sbHex = new StringBuilder();
				StringBuilder sbText = new StringBuilder();
				StringBuilder sbResult = new StringBuilder();
				while ((value = is.read()) != -1) {    
				    //convert to hex value with "X" formatter
			         sbHex.append(String.format("%02X", value));
			 
				}
			 
			
			 
			      // PrintStream print = new PrintStream("File.hex");
//				System.out.println(sbHex);
				ArrayList<String> lista = new ArrayList<String>();
				int posFinal = sbHex.lastIndexOf("220D0A");
				int posInicial;
				for (int i = sbHex.length(); (i = sbHex.lastIndexOf("220D0A", i - 1)) != -1; ) {
				    posInicial =i;
				    if (posFinal !=posInicial){
				    	lista.add(sbHex.substring(posInicial+8,posFinal+6));
				    	posFinal = posInicial;
				    }
				}
				
			Tlog tlog = new Tlog();
			Collections.reverse(lista);
			FileHeader arcTest = new FileHeader();
			String[] nombreArchivo = archivo.getName().split("\\.");
			arcTest.setIndentificadorTienda(nombreArchivo[1]);
			
			tlog.archivo = arcTest;
			for (int i=0; i < lista.size();i++){
				
			
				
				tlog.addTransaccion(lista.get(i));
			
				
			}
//			for (Transaccion tran: tlog.getTransacciones()){
//				if (tran.getTransactionHeader() !=null){
//					System.out.println(tran.getTransactionHeader().toString());
//				}
//			}
				
				PrintWriter out = new PrintWriter("filename.txt");
				System.out.println(tlog.toStringXML());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Archivo no encontrado ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getLocalizedMessage());
			}
			
	}

}
