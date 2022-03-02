package com.formacionspringboot.app.tarea.model;

import org.springframework.stereotype.Component;

@Component
public class Venta_Dia {

	private int cod_factura;
	private double total_venta;
	private String ven_diaria;
	
	private Factura factura;

	public String getVen_diaria() {
		return ven_diaria;
	}

	public void setVen_diaria(String ven_diaria) {
		this.ven_diaria = ven_diaria;
	}

	public int getCod_factura() {
		return cod_factura;
	}

	public void setCod_factura(int cod_factura) {
		this.cod_factura = cod_factura;
	}

	public double getTotal_venta() {
		return total_venta;
	}

	public void setTotal_venta(double total_venta) {
		this.total_venta = total_venta;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
}
