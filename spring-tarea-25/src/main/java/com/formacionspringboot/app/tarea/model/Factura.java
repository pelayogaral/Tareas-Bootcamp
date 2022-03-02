package com.formacionspringboot.app.tarea.model;

import org.springframework.stereotype.Component;

@Component
public class Factura {

	private int cod_factura, cod_cliente, cod_producto, iva;
	private double sub_total, total;
	
	private Cliente cliente;
	private Producto producto;
	
	public int getCod_factura() {
		return cod_factura;
	}
	public void setCod_factura(int cod_factura) {
		this.cod_factura = cod_factura;
	}
	public int getCod_cliente() {
		return cod_cliente;
	}
	public void setCod_cliente(int cod_cliente) {
		this.cod_cliente = cod_cliente;
	}
	public int getCod_producto() {
		return cod_producto;
	}
	public void setCod_producto(int cod_producto) {
		this.cod_producto = cod_producto;
	}
	public int getIva() {
		return iva;
	}
	public void setIva(int iva) {
		this.iva = iva;
	}
	public double getSub_total() {
		return sub_total;
	}
	public void setSub_total(double sub_total) {
		this.sub_total = sub_total;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
}
