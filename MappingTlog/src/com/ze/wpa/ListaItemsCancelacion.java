package com.ze.wpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListaItemsCancelacion {

	public boolean validar;
	public int suma;
	public Map<Integer, Boolean> tipoItems = new HashMap<Integer, Boolean>();
	
	public ListaItemsCancelacion() {
		
	}
	public int getSuma() {
		return suma;
	}
	public void setSuma(int suma) {
		this.suma = suma;
	}
	public Map<Integer, Boolean> getTipoItems() {
		return tipoItems;
	}
	public void setTipoItems(Map<Integer, Boolean> tipoItems) {
		this.tipoItems = tipoItems;
	}
	
	public void addItem(Integer posicion, Boolean negativo){
		if (negativo){
			validar =true;
		}
		tipoItems.put(posicion, negativo);
	}
	public boolean isValidar() {
		return validar;
	}
	public void setValidar(boolean validar) {
		this.validar = validar;
	}
	
}
