package com.formacionspringboot.app.tarea.model;

import org.springframework.stereotype.Component;

@Component
public class Producto {

	private int cod_producto;
	private double precio_producto;
	private String nombre_producto;
	
	public int getCod_producto() {
		return cod_producto;
	}
	public void setCod_producto(int cod_producto) {
		this.cod_producto = cod_producto;
	}
	public double getPrecio_producto() {
		return precio_producto;
	}
	public void setPrecio_producto(double precio_producto) {
		this.precio_producto = precio_producto;
	}
	public String getNombre_producto() {
		return nombre_producto;
	}
	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}
	
	
}
