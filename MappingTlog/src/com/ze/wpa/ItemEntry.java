package com.ze.wpa;

import org.omg.CORBA.portable.IndirectionException;

public class ItemEntry {
	public String tipo;
	public String codigoEan;
	public String Valordelitem;
	public String Departmentnumber;
	public String cuponFamilyNumber;
	public int marcadoresTipo1;
	public int marcadoresTipo2;
	public String MarcadoresTipo3;
	public String cantidadVendida;
	public String SerialRFID;
	public ItemEntryExtension itemEntreExt;
	public Devoluciones devolucion;
	public RFID rfid;
	public ManageOveride managerOverride;
	public boolean necesitaAutorizacion;
	public boolean isNegativo;
	public int tipoItem;
	public String tipoAutorizacion;
	public ItemEntry(String[] cadenas ) {
		tipo = cadenas[0];
		codigoEan = Tlog.calcularEan(cadenas[1]);
		Valordelitem = cadenas[2].replaceAll("F", "0");
		// Colocar 0 por defecto si viene vacio 
		if (Valordelitem.equalsIgnoreCase("")){
			Valordelitem="0";
		}
		Departmentnumber = cadenas[3].replaceAll("F", "0");
		cuponFamilyNumber = cadenas[4].replaceAll("F", "0");
		if (cadenas[5].equalsIgnoreCase("")){
			marcadoresTipo1 =0;
		}else{
			marcadoresTipo1 = Integer.parseInt(cadenas[5].replaceAll("F", "0"));
		}
		if (cadenas[6].equalsIgnoreCase("")){
			marcadoresTipo2=0;
		}else{
			marcadoresTipo2 = Integer.parseInt(cadenas[6].replaceAll("F", "0"));
		}
		
		MarcadoresTipo3 =cadenas[7].replaceAll("222C22", "").replaceAll("220D0A", "");;
		
		cantidadVendida="1";
		SerialRFID=null;
		String marcadores1Binarios =String.format("%16s", Integer.toBinaryString(marcadoresTipo1)).replace(' ', '0');
		String marcadores2Binarios =String.format("%16s", Integer.toBinaryString(marcadoresTipo2)).replace(' ', '0');
		// primer byte (7 6 5 4 3 2 1 0) segundo Byte (15 14 13 12 11 10 9 8 )
		// Bit 5 posicion en  2 
		char bit5 = marcadores1Binarios.charAt(2);
		
		if (bit5 == '1'){
			necesitaAutorizacion = true;
			tipoAutorizacion= "01";
		}else if (MarcadoresTipo3.charAt(0) == '2' || MarcadoresTipo3.charAt(0) == '8')  {
			necesitaAutorizacion = true;
			tipoAutorizacion= "21";
		}
		
		//Calcular cancelaci�n bit15 marcadores 2 = true
		if ( marcadores2Binarios.charAt(8) == '1') {
			isNegativo = true;
			
		}//else if (MarcadoresTipo3.charAt(0)=='2' && marcadores2Binarios.charAt(8) == '1'){
//			isNegativo = true;
//}
		else{
			isNegativo = false;
		}
		
		// Clasificacion de item
		switch (MarcadoresTipo3.charAt(0)) {
		case '8':
			tipoItem =1;
			
			break;
		case '2':			
		case '1':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '9':
			// establecer tipo de item y agregar tipo de autorizacion para los nuevos marcadores
			tipoItem =3;
			necesitaAutorizacion = true;
			if (tipoAutorizacion == null ){
				tipoAutorizacion= "21";
			}
			
			break;
		default:
			tipoItem =0;
			break;
		}
		//Clasificaciones adicionales 
		
		if (bit5=='1' && MarcadoresTipo3.charAt(0)=='8' ){
			tipoItem =11;
		}else if (bit5=='1' && MarcadoresTipo3.charAt(0)=='0'){
			tipoItem =10;
		}else if (bit5 =='1'){
			tipoItem =4;
		}
		
		
		
	}
	
	public int getCantidadVerdera(){
		int cantidad = Integer.parseInt(cantidadVendida);
		if (isNegativo){
			cantidad *=-1;
		}
		
		return cantidad;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValordelitem() {
		return Valordelitem;
	}
	public void setValordelitem(String valordelitem) {
		Valordelitem = valordelitem;
	}
	public String getDepartmentnumber() {
		return Departmentnumber;
	}
	public void setDepartmentnumber(String departmentnumber) {
		Departmentnumber = departmentnumber;
	}
	public String getCuponFamilyNumber() {
		return cuponFamilyNumber;
	}
	public void setCuponFamilyNumber(String cuponFamilyNumber) {
		this.cuponFamilyNumber = cuponFamilyNumber;
	}
	public int getMarcadoresTipo1() {
		return marcadoresTipo1;
	}
	public void setMarcadoresTipo1(int marcadoresTipo1) {
		this.marcadoresTipo1 = marcadoresTipo1;
	}
	public int getMarcadoresTipo2() {
		return marcadoresTipo2;
	}
	public void setMarcadoresTipo2(int marcadoresTipo2) {
		this.marcadoresTipo2 = marcadoresTipo2;
	}
	public String getMarcadoresTipo3() {
		return MarcadoresTipo3;
	}
	public void setMarcadoresTipo3(String marcadoresTipo3) {
		MarcadoresTipo3 = marcadoresTipo3;
	}
	public String getCantidadVendida() {
		return cantidadVendida;
	}
	public void setCantidadVendida(String cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}
	public String getSerialRFID() {
		return SerialRFID;
	}
	public void setSerialRFID(String serialRFID) {
		SerialRFID = serialRFID;
	}
	public void setItemEntryExt (String[] cadenas){
		ItemEntryExtension itemExt = new ItemEntryExtension(cadenas);
		itemEntreExt = itemExt;
		cantidadVendida = itemEntreExt.cantidadVendida;
		
	}
	public Devoluciones getDevolucion() {
		return devolucion;
	}
	public void setDevolucion(Devoluciones devolucion) {
		this.devolucion = devolucion;
	}
	public String toXmlString(){
		StringBuffer strXml = new  StringBuffer("");
		strXml.append("<ItemEntry>");
		strXml.append("<TYPE>"+tipo+"</TYPE>");
		strXml.append("<ITEMCODE>"+codigoEan+"</ITEMCODE>");
		strXml.append("<XPRICE>"+Valordelitem+"</XPRICE>");
		strXml.append("<DEPARTME>"+Departmentnumber+"</DEPARTME>");
		strXml.append("<FAMILYCOUPON>"+cuponFamilyNumber+"</FAMILYCOUPON>");
		strXml.append("<Cantidad>"+cantidadVendida+"</Cantidad>");
		if (SerialRFID != null){
			strXml.append("<SerialRFID>"+SerialRFID+"</SerialRFID>");
		}
		//Convertir el entero en string se formatea para que contenga los ceros a la izquierda
		// se divide en dos bytes y se procesan 
		
		String numeroBinary =String.format("%16s", Integer.toBinaryString(marcadoresTipo1)).replace(' ', '0');
		String primerByte = new StringBuffer(numeroBinary.substring(0,8)).reverse().toString();
		String segundoByte = new StringBuffer(numeroBinary.substring(8)).reverse().toString();
		strXml.append("<Indicadores1>");
		
		for (int i=primerByte.length()-1; i>=0 ;i--){
			
			String valorbit = primerByte.charAt(i) == '1' ? "true" :"false";
			strXml.append("<BIT"+i+">"+valorbit+"</BIT"+i+">");
		}
		for (int i=segundoByte.length()-1; i>=0 ;i--){
			
			String valorbit = segundoByte.charAt(i) == '1' ? "true" :"false";
			strXml.append("<BIT"+(i+8)+">"+valorbit+"</BIT"+(i+8)+">");
		}
		strXml.append("</Indicadores1>");
		strXml.append("<Indicadores2>");
		numeroBinary =String.format("%16s", Integer.toBinaryString(marcadoresTipo2)).replace(' ', '0');
		primerByte = new StringBuffer(numeroBinary.substring(0,8)).reverse().toString();
		segundoByte = new StringBuffer(numeroBinary.substring(8)).reverse().toString();
	for (int i=primerByte.length()-1; i>=0 ;i--){
			
			String valorbit = primerByte.charAt(i) == '1' ? "true" :"false";
			strXml.append("<BIT"+i+">"+valorbit+"</BIT"+i+">");
		}
		for (int i=segundoByte.length()-1; i>=0 ;i--){
			
			String valorbit = segundoByte.charAt(i) == '1' ? "true" :"false";
			strXml.append("<BIT"+(i+8)+">"+valorbit+"</BIT"+(i+8)+">");
		}
	
		strXml.append("</Indicadores2>");
		strXml.append("<Indicadores3>");
		strXml.append("<IndT>"+MarcadoresTipo3.charAt(0)+"</IndT>");
		strXml.append("<IndO>"+MarcadoresTipo3.charAt(1)+"</IndO>");
		strXml.append("</Indicadores3>");
		
		if (itemEntreExt !=null) {
			strXml.append(itemEntreExt.toXmlString());
		}
		
		 if (rfid !=null){
			 strXml.append(rfid.toXmlString());
		 }
		 
		 if (devolucion !=null){
			 strXml.append(devolucion.toXmlString());
		 }
		 if (managerOverride!=null){
			 strXml.append(managerOverride.toXmlString());
		 }
        
           
     strXml.append("</ItemEntry>");
		
		return strXml.toString();
	}
	public String getCodigoEan() {
		return codigoEan;
	}
	public void setCodigoEan(String codigoEan) {
		this.codigoEan = codigoEan;
	}
	public ItemEntryExtension getItemEntreExt() {
		return itemEntreExt;
	}
	public void setItemEntreExt(ItemEntryExtension itemEntreExt) {
		this.itemEntreExt = itemEntreExt;
	}
	public RFID getRfid() {
		return rfid;
	}
	public void setRfid(RFID rfid) {
		this.rfid = rfid;
	}
	public ManageOveride getManagerOverride() {
		return managerOverride;
	}
	public void setManagerOverride(ManageOveride managerOverride) {
		this.managerOverride = managerOverride;
	}
}
