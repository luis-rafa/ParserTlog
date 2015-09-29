package com.ze.wpa;

import java.awt.dnd.Autoscroll;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author ze
 *
 */
public class Transaccion {
	
	
	List<Cadena> cadenas;
	private TransactionHeader transactionHeader;
	private List<ItemEntry> items;
	private ManageOveride managerOveride;
	private List<ManageOveride> listaAutorizaciones01;
	private List<ManageOveride> listaAutorizaciones21;
	private List<Tender> tenders;
	private List<Tender> tenderCorrections;
	private Change change;
	//private List<Tarjetas> tarjetas;
	private List<Descuentos> descuentos;
	private List<Promociones> promociones;
	private Factura factura;
	private CedulaCliente cedulaCliente;
	private Vendedor vendedor;
	//private TEFDE1 tefde1;
	//private TEFDE2 tefde2;
	//private TEFDE3 tefde3;
	private StroreClosing storeClosing;
	public boolean isStoreClosing;
	public boolean verificarCancelaciones;
	public boolean isStoreClosing() {
		return isStoreClosing;
	}
	public void setStoreClosing(boolean isStoreClosing) {
		this.isStoreClosing = isStoreClosing;
	}

	boolean transaccionValida;
	public Transaccion(String strTransaccion) {
		
		cadenas = new ArrayList<Cadena>();				
		// dividir las transacciones en Strings 
		for (String cadena: strTransaccion.split("222C22")){
			// transaccion con un solo String 
			if (cadena.endsWith("220D0A")){
				cadenas.add(new Cadena(cadena));
			}else{
				// Strings dentro de una transaccion el split quita el sufijo por eso se vuelve a agregar
				cadenas.add(new Cadena(cadena+"222C22"));
			}
			
		}
		// Generar las estructuras 
		items = new ArrayList<ItemEntry>();
		tenders = new ArrayList<Tender>();
		listaAutorizaciones01 = new ArrayList<ManageOveride>();
		listaAutorizaciones21 = new ArrayList<ManageOveride>();
		tenderCorrections = new ArrayList<Tender>();
//		tarjetas = new ArrayList<Tarjetas>();
		descuentos = new ArrayList<Descuentos>();
		promociones = new ArrayList<Promociones>();
		generarTransaccion();
		
	}
	TransactionHeader getTransactionHeader() {
	
		
		return transactionHeader;
	}
	void setTransactionHeader(TransactionHeader transactionHeader) {
		this.transactionHeader = transactionHeader;
	}
	
	void generarTransaccion(){
		ItemEntry item;
		boolean itemAbierto=false;
		boolean rfidAbierto=false;
		boolean devolucionAbierta = false;
		boolean tef1Abierto= false;
		boolean tef2Abierto= false;
		boolean tef3Abierto= false;
		boolean tarjetaAbierto= false;
		TEFDE1 tef1Temp=null;
		TEFDE2 tef2Temp=null;
		TEFDE3 tef3Temp=null;
		List<Tarjetas> tarjetaTemp=new ArrayList<Tarjetas>();;
		String serialRfid=null;
		String rfidEan=null;
		Devoluciones devolucion =null;
		RFID rfitemp = null;
		
		// Clasificacion de las cadenas 
		for (Cadena cadena :cadenas){
			
	
			
			
			if (cadena.tipo.equals("00")){
				transactionHeader = new TransactionHeader(cadena.subCadenas);
				
				
				
			}
		
			
			
			if (cadena.tipo.equalsIgnoreCase("01")){
				item = new ItemEntry(cadena.subCadenas);
				if (rfidAbierto){
					if (item.codigoEan.equalsIgnoreCase(rfidEan)){
						item.SerialRFID = serialRfid;
						
					}
					item.setRfid(rfitemp);
					rfidAbierto =false;
					serialRfid =null;
					rfidEan=null;
					rfitemp = null;
				}
				
				if (devolucionAbierta){
					item.setDevolucion(devolucion);
					devolucionAbierta = false;
				}
				if (!item.codigoEan.equalsIgnoreCase("9999999111112")){
					items.add(item);
				}
				
				
			}
			
			if (cadena.tipo.equalsIgnoreCase("02")){
				item =items.get(items.size()-1);
				item.setItemEntryExt(cadena.subCadenas);
				item.cantidadVendida =item.getItemEntreExt().getCantidadVendida();
				items.set(items.size()-1, item);
			}
			if (cadena.tipo.equalsIgnoreCase("10")){
				ManageOveride autorizacion = new ManageOveride(cadena.subCadenas);
				if (autorizacion.tieneAlgunaAutorizacion("01")){
					listaAutorizaciones01.add(autorizacion);
				}else if(autorizacion.tieneAlgunaAutorizacion("21")){
					listaAutorizaciones21.add(autorizacion);
				}
				managerOveride = autorizacion;
				
				
			}
			if (cadena.tipo.equalsIgnoreCase("05")){
				Tender tender = new Tender(cadena.subCadenas);
				if (tender.getTipoMedioPago().equalsIgnoreCase("41") ||
						tender.getTipoMedioPago().equalsIgnoreCase("31") ||
						tender.getTipoMedioPago().equalsIgnoreCase("12")
						){
//					Tarjetas tarjeta = tarjetas.get(tarjetas.size()-1);
//					tarjeta.setTender(tender);
//					tarjetas.set(tarjetas.size()-1, tarjeta);
					
					// Modificaciones 4/08/2015
//					if (tarjetaAbierto && tarjetaTemp!=null){
//						tender.setTarjeta(tarjetaTemp);
//						tarjetaAbierto = false;
//						tarjetaTemp = null;
//					}
					if (tarjetaTemp.size() >0){
						tender.setTarjeta(tarjetaTemp.get(0));
						tarjetaTemp.remove(0);
					}
//					Fin modificaciones 4/08/2015					
					if (tef1Abierto && tef1Temp!=null){
						tender.setTefde1(tef1Temp);
						tef1Abierto = false;
						tef1Temp = null;
					}
					if (tef2Abierto && tef2Temp!=null){
						tender.setTefde2(tef2Temp);
						tef2Abierto = false;
						tef2Temp = null;
					}
					if (tef3Abierto && tef3Temp!=null){
						tender.setTefde3(tef3Temp);
						tef3Abierto = false;
						tef3Temp = null;
					}
				}
				
				tenders.add(tender);
				
					
				
			}
			if (cadena.tipo.equalsIgnoreCase("06")){
				Tender tenderCor = new Tender(cadena.subCadenas);
				if (tenderCor.getTipoMedioPago().equalsIgnoreCase("41") ||
						tenderCor.getTipoMedioPago().equalsIgnoreCase("31") ||
						tenderCor.getTipoMedioPago().equalsIgnoreCase("12")
						){
//					Tarjetas tarjeta = tarjetas.get(tarjetas.size()-1);
//					tarjeta.setTenderCorrection(tenderCor);
//					tarjetas.set(tarjetas.size()-1, tarjeta);
					// Modificaciones 4/08/2015
					if (tarjetaTemp.size() >0){
						tenderCor.setTarjeta(tarjetaTemp.get(0));
						
						tarjetaTemp.remove(0);
					}
					//	Fin Modificaciones 4/08/2015
					
				}
				tenderCorrections.add(tenderCor);
				
				
			}
			if (cadena.tipo.equalsIgnoreCase("09")){
				change = new Change(cadena.subCadenas);
				
			}	
			if (cadena.tipo.equalsIgnoreCase("21")){
				storeClosing = new StroreClosing(cadena.subCadenas);
			setStoreClosing(true);
				
			}
			if (cadena.tipo.equalsIgnoreCase("11")){
				
				String subtipo = cadena.subCadenas[5];
				if (subtipo.equalsIgnoreCase("01")){
					tef1Temp = new TEFDE1(cadena.subCadenas);
					tef1Abierto = true;
				}
				if (subtipo.equalsIgnoreCase("02")){
					tef2Temp = new TEFDE2(cadena.subCadenas);
					tef2Abierto = true;
				}
				if (subtipo.equalsIgnoreCase("03")){
					tef3Temp = new TEFDE3(cadena.subCadenas);
					tef3Abierto = true;
				}
			}
			if (cadena.tipo.equalsIgnoreCase("99")){
				String tipoUserExit = cadena.subCadenas[1];
				
				if (tipoUserExit.equalsIgnoreCase("5200")){
					rfidAbierto = true;
					 rfitemp = new RFID(cadena.subCadenas);
					rfidEan=rfitemp.getCodigoEAN();
					serialRfid=rfitemp.getSerial();
					
				}
				if (tipoUserExit.equalsIgnoreCase("67")){
					devolucion = new Devoluciones(cadena.subCadenas);
					devolucionAbierta =true;
					
				}
				if (tipoUserExit.equalsIgnoreCase("17")){
					vendedor = new Vendedor(cadena.subCadenas);
				}
				if (tipoUserExit.equalsIgnoreCase("189011")){
					cedulaCliente = new CedulaCliente(cadena.subCadenas);
				}
				if (tipoUserExit.equalsIgnoreCase("51000000")){
					
					tarjetaTemp.add(new Tarjetas(cadena.subCadenas));	
					tarjetaAbierto = true;
				}
				if (tipoUserExit.equalsIgnoreCase("00")){
					 factura = new Factura(cadena.subCadenas);
					
				}
				if (tipoUserExit.equalsIgnoreCase("11")){
					vendedor = new Vendedor(cadena.subCadenas);
				}
				if (tipoUserExit.equalsIgnoreCase("189001")){
					Descuentos descuento = new Descuentos(cadena.subCadenas);
					descuentos.add(descuento);
				}
				if (tipoUserExit.equalsIgnoreCase("189015")){
					Promociones promocion = new Promociones(cadena.subCadenas);
					promociones.add(promocion);
				}
			}
			}
			
		// Revisar los items y agregales las cancelaciones
		
		validarItemAutorizaciones();
		 // si existe un item negativo se deben verificar las validaciones 
		validarItemCancelaciones();
		
		
	
			
		}
		
		
	
	public StroreClosing getStoreClosing() {
		return storeClosing;
	}
	public void setStoreClosing(StroreClosing storeClosing) {
		this.storeClosing = storeClosing;
	}
	
	
	private void validarItemCancelaciones(){
		Map<String, ArrayList<Integer>> mapa = new HashMap<String, ArrayList<Integer>>();
		// Buscar item por mismo ean y meterlos a una map EAN,ArralistIndiceItems
		for (int i=0; i<items.size() ;i++){
			String ean = items.get(i).getCodigoEan();
			ArrayList<Integer> elementoMapa =mapa.get(ean);
			if (elementoMapa ==null){
				elementoMapa = new ArrayList<Integer>();
				elementoMapa.add(i);
				
			}else{
				elementoMapa.add(i);
			}
			mapa.put(ean, elementoMapa);
		}
		// Iterador de los mapas (Ean, lista de posicione de los items)
		Iterator it =mapa.keySet().iterator();
		// Arreglo para eliminar items al final
		ArrayList<Integer> itemsAEliminar = new ArrayList<Integer>();
		// recorer todos los maps de Ean contra lista de itemes que tienen el mismo EAN
		while(it.hasNext()){
			String ean = (String)it.next();
			// Lista con todos las posiciones de los items que tiene el Ean seleccionado
			ArrayList<Integer>  arregloItems = mapa.get(ean);
			int suma =0;
			// Mapa para encontrar el tipo de item y el signo 
//			Map<Integer, Boolean> tipoItems = new HashMap<Integer, Boolean>();
			Map<Integer,ListaItemsCancelacion> mapaTipoItems = new HashMap<Integer, ListaItemsCancelacion>();
			// Bandera para encontrar cancelaciones
			boolean validarCancelaciones=false;
			// Crear dos mapas para llevar la lista de items por precio 
			Map<Integer, Map<String,ListaItemsCancelacion>> mapaItemGrupo11=new HashMap<Integer, Map<String,ListaItemsCancelacion>>();
			Map<Integer, Map<String,ListaItemsCancelacion>> mapaItemGrupo4= new HashMap<Integer, Map<String,ListaItemsCancelacion>>();
			// Recorre los items dentro del arreglo para realizar la suma de los Tipo 1 contra los tipo cero
			for (Integer i : arregloItems){
				// Tipo de item 
				int tipoItem =items.get(i).tipoItem;
				// cantidad con signo
				if (tipoItem ==1 || tipoItem == 0){
					ListaItemsCancelacion listaItemsTipo1;
					int sumaTmp;
					String marcadores1Binarios =String.format("%16s", Integer.toBinaryString(items.get(i).marcadoresTipo1)).replace(' ', '0');
					if (items.get(i).isNegativo || marcadores1Binarios.charAt(2) =='0'){
						if (mapaTipoItems.get(8) !=null){
						listaItemsTipo1 = mapaTipoItems.get(8);
						sumaTmp=listaItemsTipo1.getSuma();
						sumaTmp +=items.get(i).getCantidadVerdera();
						listaItemsTipo1.setSuma(sumaTmp);
						listaItemsTipo1.addItem(i, items.get(i).isNegativo);
						}else{
						listaItemsTipo1 = new ListaItemsCancelacion();
						listaItemsTipo1.addItem(i, items.get(i).isNegativo);
						sumaTmp = items.get(i).getCantidadVerdera();
						listaItemsTipo1.setSuma(sumaTmp);
						mapaTipoItems.put(8, listaItemsTipo1);
						}
					}
				}else if (tipoItem ==11 || tipoItem == 10){
					ListaItemsCancelacion listaItemsTipo2;
					int sumaTmp;
//					if (mapaTipoItems.get(10) !=null){
//						listaItemsTipo2 = mapaTipoItems.get(10);
//						sumaTmp=listaItemsTipo2.getSuma();
//						sumaTmp +=items.get(i).getCantidadVerdera();
//						listaItemsTipo2.setSuma(sumaTmp);
//						listaItemsTipo2.addItem(i, items.get(i).isNegativo);
//					}else{
//						listaItemsTipo2 = new ListaItemsCancelacion();
//						listaItemsTipo2.addItem(i, items.get(i).isNegativo);
//						sumaTmp = items.get(i).getCantidadVerdera();
//						listaItemsTipo2.setSuma(sumaTmp);
//						mapaTipoItems.put(10, listaItemsTipo2);
//					}
					String precio = items.get(i).getValordelitem();
					if (mapaItemGrupo4.get(10) !=null){
						Map<String, ListaItemsCancelacion> mapa4 =	mapaItemGrupo4.get(10);
						ListaItemsCancelacion listaItemsTipo4;
						if (mapa4.get(precio)!=null){
							 listaItemsTipo4 =mapa4.get(precio);
							sumaTmp=listaItemsTipo4.getSuma();
							sumaTmp +=items.get(i).getCantidadVerdera();
							listaItemsTipo4.setSuma(sumaTmp);
							listaItemsTipo4.addItem(i, items.get(i).isNegativo);
						}else{
							listaItemsTipo4 = new ListaItemsCancelacion();
							listaItemsTipo4.addItem(i, items.get(i).isNegativo);
							sumaTmp = items.get(i).getCantidadVerdera();
							listaItemsTipo4.setSuma(sumaTmp);
							mapa4.put(precio, listaItemsTipo4);
						}
					}else{
						Map<String, ListaItemsCancelacion> mapa4 = new HashMap<String, ListaItemsCancelacion>();
						ListaItemsCancelacion listaItemsTipo4 = new ListaItemsCancelacion();
						listaItemsTipo4.addItem(i, items.get(i).isNegativo);
						sumaTmp = items.get(i).getCantidadVerdera();
						listaItemsTipo4.setSuma(sumaTmp);
						mapa4.put(precio, listaItemsTipo4);
						mapaItemGrupo4.put(10, mapa4);
						
					}
				}else if (tipoItem == 3){
					Integer subtipo = Character.getNumericValue(items.get(i).MarcadoresTipo3.charAt(0));
					String marcadores1Binarios =String.format("%16s", Integer.toBinaryString(items.get(i).marcadoresTipo1)).replace(' ', '0');
					if (items.get(i).isNegativo || marcadores1Binarios.charAt(2) =='0'){
						ListaItemsCancelacion listaItemsTipo3;
						int sumaTmp;
						if (mapaTipoItems.get(subtipo) !=null){
							listaItemsTipo3 = mapaTipoItems.get(subtipo);
							sumaTmp=listaItemsTipo3.getSuma();
							sumaTmp +=items.get(i).getCantidadVerdera();
							listaItemsTipo3.setSuma(sumaTmp);
							listaItemsTipo3.addItem(i, items.get(i).isNegativo);
						}else{
							listaItemsTipo3 = new ListaItemsCancelacion();
							listaItemsTipo3.addItem(i, items.get(i).isNegativo);
							sumaTmp = items.get(i).getCantidadVerdera();
							listaItemsTipo3.setSuma(sumaTmp);
							mapaTipoItems.put(subtipo, listaItemsTipo3);
						}
					}
				}else if (tipoItem == 4){
					Integer subtipo = Character.getNumericValue(items.get(i).MarcadoresTipo3.charAt(0));
					subtipo=subtipo+10;
					String marcadores1Binarios =String.format("%16s", Integer.toBinaryString(items.get(i).marcadoresTipo1)).replace(' ', '0');
					if (items.get(i).isNegativo || marcadores1Binarios.charAt(2) =='1'){
//						ListaItemsCancelacion listaItemsTipo4;
//						int sumaTmp;
//						if (mapaTipoItems.get(subtipo) !=null){
//							listaItemsTipo4 = mapaTipoItems.get(subtipo);
//							sumaTmp=listaItemsTipo4.getSuma();
//							sumaTmp +=items.get(i).getCantidadVerdera();
//							listaItemsTipo4.setSuma(sumaTmp);
//							listaItemsTipo4.addItem(i, items.get(i).isNegativo);
//						}else{
//							listaItemsTipo4 = new ListaItemsCancelacion();
//							listaItemsTipo4.addItem(i, items.get(i).isNegativo);
//							sumaTmp = items.get(i).getCantidadVerdera();
//							listaItemsTipo4.setSuma(sumaTmp);
//							mapaTipoItems.put(subtipo, listaItemsTipo4);
//						}
						String precio = items.get(i).getValordelitem();
						if (mapaItemGrupo4.get(subtipo) !=null){
							Map<String, ListaItemsCancelacion> mapa4 =	mapaItemGrupo4.get(subtipo);
							ListaItemsCancelacion listaItemsTipo4;
							if (mapa4.get(precio)!=null){
								 listaItemsTipo4 =mapa4.get(precio);
								int sumaTmp=listaItemsTipo4.getSuma();
								sumaTmp +=items.get(i).getCantidadVerdera();
								listaItemsTipo4.setSuma(sumaTmp);
								listaItemsTipo4.addItem(i, items.get(i).isNegativo);
							}else{
								listaItemsTipo4 = new ListaItemsCancelacion();
								listaItemsTipo4.addItem(i, items.get(i).isNegativo);
								int sumaTmp = items.get(i).getCantidadVerdera();
								listaItemsTipo4.setSuma(sumaTmp);
								mapa4.put(precio, listaItemsTipo4);
							}
						}else{
							Map<String, ListaItemsCancelacion> mapa4 = new HashMap<String, ListaItemsCancelacion>();
							ListaItemsCancelacion listaItemsTipo4 = new ListaItemsCancelacion();
							listaItemsTipo4.addItem(i, items.get(i).isNegativo);
							int sumaTmp = items.get(i).getCantidadVerdera();
							listaItemsTipo4.setSuma(sumaTmp);
							mapa4.put(precio, listaItemsTipo4);
							mapaItemGrupo4.put(subtipo, mapa4);
							
						}
					}
				}
//				suma += items.get(i).getCantidadVerdera();
//				tipoItems.put(i, items.get(i).isNegativo);
//				if (items.get(i).isNegativo){
//					// si hay un negativo implica que se deben validar las cancelaciones 
//					validarCancelaciones = true;
//				}
			}
			//Modificaciones para las cancelaciones de cambio de precio
			// Mapas de subtipos que contiene mapa de precios 
			Iterator<Integer> iteradorGrupo4 = mapaItemGrupo4.keySet().iterator();
			// int indicador adicional para simular  subtipos en el mapatipoItems
			int contador = 20;
			while (iteradorGrupo4.hasNext()){
				Integer subg=iteradorGrupo4.next();
				//Iterados de Mapas de precios
			    Iterator<String> iteradorPrecios =	mapaItemGrupo4.get(subg).keySet().iterator();
			    while (iteradorPrecios.hasNext()){
			    	String precio =iteradorPrecios.next();
			    	mapaTipoItems.put(contador, mapaItemGrupo4.get(subg).get(precio)) ;
			    	contador += 1;
			    }
			}
			
			
			// Listado de los grupos de cancelaciones 
			
		   Iterator<Integer> iteradorGrupo = mapaTipoItems.keySet().iterator();
		   while (iteradorGrupo.hasNext()){
			   Integer tipoGrupo = iteradorGrupo.next();
			   ListaItemsCancelacion itemsCancelar =mapaTipoItems.get(tipoGrupo);
			   if(itemsCancelar.validar){
				   itemsAEliminar.addAll(calcularBorrado(itemsCancelar.getSuma(), itemsCancelar.getTipoItems()));
			   }
		   }
			
			Collections.sort(itemsAEliminar,Collections.reverseOrder());
//			if (validarCancelaciones){
				// Eliminar todos los items positivos 
//				if (suma ==0){
//					Iterator<Integer> ite = tipoItems.keySet().iterator();
//					while(ite.hasNext()){
//						Integer posicion =ite.next();
//							if (!tipoItems.get(posicion)){
//									itemsAEliminar.add(posicion);
//							}
//					}
//				}//// Suma mayor a cero Eliminar los items que no cumplan con la cantidad de Elimar 
//				else if (suma >0){
//					Iterator<Integer> ite = tipoItems.keySet().iterator();
//					// flag para verificar los items que se deben borrar si la suma de los positivos y los negativos esta cubierta
//					boolean sumaFinalizada= false;
//					// Recorre los items positivo para Modificar las cantidad y llenar la lista de borrado
//					int sumaAcumulada=0;
//					while(ite.hasNext()){
//					Integer posicion =ite.next();
//					
//					if (!tipoItems.get(posicion)){
//						// No modificar los items que tienen cantidad igual a la suma de items finales
//						if (!sumaFinalizada){
//							ItemEntry itemAnalizado = items.get(posicion);
//							sumaAcumulada += Integer.parseInt(itemAnalizado.getCantidadVendida());
//							if (sumaAcumulada == suma){
//								sumaFinalizada = true;
//							}else if (sumaAcumulada > suma){
//								int cantidadActualItem = Integer.parseInt(itemAnalizado.getCantidadVendida());
//								int valorUnitario = Integer.parseInt(itemAnalizado.Valordelitem)/cantidadActualItem;
//								int cantidadCalculada = cantidadActualItem - (sumaAcumulada-suma);
//								itemAnalizado.setValordelitem(Integer.toString(valorUnitario*cantidadCalculada));
//								itemAnalizado.setCantidadVendida(Integer.toString(cantidadCalculada));
//								items.set(posicion, itemAnalizado);
//								sumaFinalizada = true;
//							}
//						}else{
//							itemsAEliminar.add(posicion);
//						}
//					}
//				}
//			}
			
			
//			}
			
			
		}
		
		for (Integer itemElminar : itemsAEliminar){
			items.remove(items.get(itemElminar));
		}
		
	}
	private void validarItemAutorizaciones() {
		ManageOveride autobackup=null;
		for (int i=0; i<items.size() ;i++){
			ItemEntry item = items.get(i);
			if (item.necesitaAutorizacion){
				
				int encontrado=-1;
				for (int j=0; j < listaAutorizaciones01.size();j++){
					 if (listaAutorizaciones01.get(j).tieneAlgunaAutorizacion(item.tipoAutorizacion)){
						 encontrado = j;
						 j= listaAutorizaciones01.size();
					 }
				 }
				if (encontrado != -1){
					ManageOveride autorizacion= listaAutorizaciones01.get(encontrado);
					item.setManagerOverride(autorizacion);
					items.set(i, item);
					listaAutorizaciones01.remove(encontrado);
				}
			 
				encontrado=-1;
			 	for (int j=0; j < listaAutorizaciones21.size();j++){
						 if (listaAutorizaciones21.get(j).tieneAlgunaAutorizacion(item.tipoAutorizacion) ){
							 encontrado = j;
							 j= listaAutorizaciones21.size();
						 }
				}
			 	
			 	if (encontrado != -1){
					ManageOveride autorizacion= listaAutorizaciones21.get(encontrado);
					item.setManagerOverride(autorizacion);
					items.set(i, item);
					 
					listaAutorizaciones21.remove(encontrado);
					if (listaAutorizaciones21.size()==0){
						autobackup =autorizacion;
					}
				}else if(item.tipoAutorizacion.equalsIgnoreCase("21") && item.getManagerOverride() == null){
					item.setManagerOverride(autobackup);
				}
			 
			}
			if (item.isNegativo){
				verificarCancelaciones = true;
			}
			
			
		}
		
		
	}
	
	void validarTransaccion(){
		// Todas las transacciones tiene header menos el store closing
		
		if (transactionHeader != null){
			int numTransa = Integer.parseInt(transactionHeader.numString);
			// numero de cadenas excluyendo el header
			int numerodeCadenas =cadenas.size() -1;
			if ((transactionHeader.tipoTransaccion.equalsIgnoreCase("00") ||
					transactionHeader.tipoTransaccion.equalsIgnoreCase("02") 
					 ) && numTransa == numerodeCadenas ){
				transaccionValida = true;
			}else if (transactionHeader.tipoTransaccion.equalsIgnoreCase("17")){
				transaccionValida = true;
			}
			else{
				transaccionValida = false;
			}
		}else{
			// Validar para ser strore closing 
			transaccionValida =true;
		}
		
		
	}
	public ArrayList<Integer> calcularBorrado(int suma, Map<Integer, Boolean> tipoItems){
		ArrayList<Integer> posicionesItemsBorrar = new ArrayList<Integer>();
		if (suma ==0){
			Iterator<Integer> ite = tipoItems.keySet().iterator();
			while(ite.hasNext()){
				Integer posicion =ite.next();
					if (!tipoItems.get(posicion)){
						posicionesItemsBorrar.add(posicion);
					}
			}
		}//// Suma mayor a cero Eliminar los items que no cumplan con la cantidad de Elimar 
		else if (suma >0){
			Iterator<Integer> ite = tipoItems.keySet().iterator();
			// flag para verificar los items que se deben borrar si la suma de los positivos y los negativos esta cubierta
			boolean sumaFinalizada= false;
			// Recorre los items positivo para Modificar las cantidad y llenar la lista de borrado
			int sumaAcumulada=0;
			while(ite.hasNext()){
			Integer posicion =ite.next();
			
			if (!tipoItems.get(posicion)){
				// No modificar los items que tienen cantidad igual a la suma de items finales
				if (!sumaFinalizada){
					ItemEntry itemAnalizado = items.get(posicion);
					sumaAcumulada += Integer.parseInt(itemAnalizado.getCantidadVendida());
					if (sumaAcumulada == suma){
						sumaFinalizada = true;
					}else if (sumaAcumulada > suma){
						int cantidadActualItem = Integer.parseInt(itemAnalizado.getCantidadVendida());
						int valorUnitario = Integer.parseInt(itemAnalizado.Valordelitem)/cantidadActualItem;
						int cantidadCalculada = cantidadActualItem - (sumaAcumulada-suma);
						itemAnalizado.setValordelitem(Integer.toString(valorUnitario*cantidadCalculada));
						itemAnalizado.setCantidadVendida(Integer.toString(cantidadCalculada));
						items.set(posicion, itemAnalizado);
						sumaFinalizada = true;
					}
				}else{
					posicionesItemsBorrar.add(posicion);
				}
			}
		}
	}
		return posicionesItemsBorrar;
	}
	// Generar XML
	public String toXmlString(){
		StringBuffer strXml= new StringBuffer("");
		
		strXml.append("<Transaccion>");
		if (transactionHeader!=null){
			strXml.append(transactionHeader.toXmlString());
		}
		
		for (ItemEntry item : items){
			strXml.append(item.toXmlString());
		}
		
		for (Tender tender : tenders){
			strXml.append(tender.toXmlString('T'));
		}
		for (Tender tenderCor : tenderCorrections){
			strXml.append(tenderCor.toXmlString('C'));
		}
		if (change != null){
			strXml.append(change.toXmlString());
		}
//		for (Tarjetas tarjeta : tarjetas){
//			strXml.append(tarjeta.toXmlString());
//		}
		for (Descuentos descuento : descuentos){
			strXml.append(descuento.toXmlString());
		}
		for (Promociones promocion : promociones){
			strXml.append(promocion.toXmlString());
		}
		if (factura != null){
			strXml.append(factura.toXmlString());
		}
		if (cedulaCliente != null){
			strXml.append(cedulaCliente.toXmlString());
		}
		if (vendedor != null){
			strXml.append(vendedor.toXmlString());
		}
//		if (tefde1 != null){
//			strXml.append(tefde1.toXmlString());
//		}
//		if (tefde2 != null){
//			strXml.append(tefde2.toXmlString());
//		}
//		if (tefde3 != null){
//			strXml.append(tefde3.toXmlString());
//		}
		
		if (managerOveride!=null){
			strXml.append(managerOveride.toXmlString());
		}
		strXml.append("</Transaccion>");
		return strXml.toString();
	}
}
