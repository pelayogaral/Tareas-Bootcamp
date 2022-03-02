package com.formacionspringboot.app.tarea.service;

import com.formacionspringboot.app.tarea.model.Cliente;
import com.formacionspringboot.app.tarea.model.Factura;
import com.formacionspringboot.app.tarea.model.Producto;
import com.formacionspringboot.app.tarea.model.Venta_Dia;

public interface TareaService {
	public Cliente mostrarClientes();
	public Factura mostrarFacturas();
	public Producto mostrarProductos();
	public Venta_Dia mostrarVentas();

}
