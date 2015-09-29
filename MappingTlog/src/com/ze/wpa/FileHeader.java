package com.ze.wpa;

public class FileHeader {

	String indentificadorTienda;
	String fechaArchivo;
	public FileHeader() {
	
	}
	public String getIndentificadorTienda() {
		return indentificadorTienda;
	}
	public void setIndentificadorTienda(String indentificadorTienda) {
		this.indentificadorTienda = indentificadorTienda;
	}
	public String getFechaArchivo() {
		return fechaArchivo;
	}
	public void setFechaArchivo(String fechaArchivo) {
		this.fechaArchivo = fechaArchivo;
	}
	
	public String toStringXml(){
		String xml ="";
		xml="<Archivo><Tienda>"+getIndentificadorTienda() + "</Tienda><Fecha>"+ getFechaArchivo() +"</Fecha></Archivo>" ;
		
	      
	      
	   
		
		
		return xml;
	}
	
}
