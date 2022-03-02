package com.formacionspringboot.app.tarea.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspringboot.app.tarea.model.Cliente;
import com.formacionspringboot.app.tarea.model.Factura;
import com.formacionspringboot.app.tarea.model.Producto;
import com.formacionspringboot.app.tarea.model.Venta_Dia;
import com.formacionspringboot.app.tarea.service.TareaService;


@RestController
public class TareaController {
	@Autowired
	TareaService servicio;
	
	@GetMapping("/Clientes")
	public Cliente clientes() {	
		return servicio.mostrarClientes();
		
	}
	@GetMapping("/Productos")
	public Producto productos() {	
		return servicio.mostrarProductos();
		
	}
	@GetMapping("/Ventas")
	public Venta_Dia ventas() {	
		return servicio.mostrarVentas();
		
	}
	@GetMapping("/Facturas")
	public Factura facturas() {	
		return servicio.mostrarFacturas();
		
	}
}
