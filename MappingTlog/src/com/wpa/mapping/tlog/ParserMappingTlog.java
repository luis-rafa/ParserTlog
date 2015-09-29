package com.wpa.mapping.tlog;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.sap.aii.mapping.api.AbstractTransformation;
import com.sap.aii.mapping.api.DynamicConfiguration;
import com.sap.aii.mapping.api.DynamicConfigurationKey;
import com.sap.aii.mapping.api.StreamTransformationConstants;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mapping.api.TransformationInput;
import com.sap.aii.mapping.api.TransformationOutput;
import com.ze.wpa.FileHeader;
import com.ze.wpa.Tlog;




public class ParserMappingTlog extends AbstractTransformation{

	public ParserMappingTlog() {
		
	}
	private static final DynamicConfigurationKey KEY_FILENAME =
	         DynamicConfigurationKey.create("http://sap.com/xi/XI/System/File","FileName");
	private Map param;

	
	@Override
	public void transform(TransformationInput arg0, TransformationOutput arg1)
			throws StreamTransformationException {
		// Logear inicio del mapeo
		getTrace().addInfo("TLOG Mapping Called");
		InputStream is =arg0.getInputPayload().getInputStream();
		int bytesCounter =0;		
		int value = 0;
		StringBuilder sbHex = new StringBuilder();
	
		
		try {
			while ((value = is.read()) != -1) {    
			    //convert to Hex
			     sbHex.append(String.format("%02X", value));
 			}
			// Listado de transacciones 
			ArrayList<String> lista = new ArrayList<String>();
			
			// leer las transacciones de atras hacia adelante
			int posFinal = sbHex.lastIndexOf("220D0A");
			int posInicial;
			for (int i = sbHex.length(); (i = sbHex.lastIndexOf("220D0A", i - 1)) != -1; ) {
			    posInicial =i;
			    if (posFinal !=posInicial){
			    	lista.add(sbHex.substring(posInicial+8,posFinal+6));
			    	posFinal = posInicial;
			    }
			}
			Collections.reverse(lista);
			
			Tlog tlog = new Tlog();
			
			FileHeader archivo = new FileHeader();
			
			String indentificadorTienda;
			try {
				param = arg0.getInputHeader().getAll(); 
				DynamicConfiguration conf = (DynamicConfiguration)param.get("DynamicConfiguration");
				//Obtener los datos del nombre del archivo
				String[] datosNombre = conf.get(KEY_FILENAME).split("\\.");
				indentificadorTienda = datosNombre[1];
			} catch (Exception e) {
				indentificadorTienda ="001";
				e.printStackTrace();
			}
			archivo.setIndentificadorTienda(indentificadorTienda);
			tlog.setArchivo(archivo);
			for (int i=0; i < lista.size();i++){
				tlog.addTransaccion(lista.get(i));
			}
		
			
			
			arg1.getOutputPayload().getOutputStream().write(tlog.toStringXML().getBytes());
			
			
			
		} catch (IOException e) {
			getTrace().addInfo("Error " + e.getLocalizedMessage());
		}
	}

}
