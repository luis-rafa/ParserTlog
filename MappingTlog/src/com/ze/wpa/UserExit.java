package com.ze.wpa;

public class UserExit {

	public enum tipoUserExit{
		Factura,Vendedor,Descuentos,CedulaCliente,Promociones,Tarjetas,RFID,Devoluciones

	}
	public UserExit(tipoUserExit tipo) {
		switch (tipo) {
		case Factura:
			
			break;

		default:
			break;
		}
		
	}
}
