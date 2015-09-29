package com.ze.wpa;

public class ItemEntryExtension {
	public String tipo;
	public String multipricingGroupNumber;
	public String numberItems;
	public String pricingQuantity;
	public String princiString;
	public String precioUnitario;
	public String cantidadVendida;
	public String campoNoId;

	 public ItemEntryExtension(String[] cadenas) {
		tipo =cadenas[0];
		multipricingGroupNumber =cadenas[1].replace('F', '0');
		numberItems =cadenas[2].replace('F', '0');
		pricingQuantity =cadenas[3].replace('F', '0');
		princiString =cadenas[4].replace('F', '0');
		precioUnitario =cadenas[5].replace('F', '0');
		cantidadVendida =cadenas[6].replace('F', '0');
		campoNoId =cadenas[7].replaceAll("222C22", "").replaceAll("220D0A", "").replace('F', '0');
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMultipricingGroupNumber() {
		return multipricingGroupNumber;
	}

	public void setMultipricingGroupNumber(String multipricingGroupNumber) {
		this.multipricingGroupNumber = multipricingGroupNumber;
	}

	public String getNumberItems() {
		return numberItems;
	}

	public void setNumberItems(String numberItems) {
		this.numberItems = numberItems;
	}

	public String getPricingQuantity() {
		return pricingQuantity;
	}

	public void setPricingQuantity(String pricingQuantity) {
		this.pricingQuantity = pricingQuantity;
	}

	public String getPrinciString() {
		return princiString;
	}

	public void setPrinciString(String princiString) {
		this.princiString = princiString;
	}

	public String getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getCantidadVendida() {
		return cantidadVendida;
	}

	public void setCantidadVendida(String cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}

	public String getCampoNoId() {
		return campoNoId;
	}

	public void setCampoNoId(String campoNoId) {
		this.campoNoId = campoNoId;
	}
	public String toXmlString(){
		StringBuffer strXml = new StringBuffer("");
		strXml.append("<ItemEntryExtension>");
		strXml.append("<TYPE>"+tipo+"</TYPE>");
		strXml.append("<MPGROUP>"+multipricingGroupNumber+"</MPGROUP>");
		strXml.append("<DEALQUAN>"+numberItems+"</DEALQUAN>");
		strXml.append("<SALEQUAN>"+pricingQuantity+"</SALEQUAN>");
		strXml.append("<SALEPRIC>"+princiString+"</SALEPRIC>");
		strXml.append("<PRECIOUNI>"+precioUnitario+"</PRECIOUNI>");
		strXml.append("<CANTIDAD>"+cantidadVendida+"</CANTIDAD>");
		strXml.append("<DATA1>"+campoNoId+"</DATA1>");
        strXml.append("</ItemEntryExtension>");
        return strXml.toString();
	}
}
