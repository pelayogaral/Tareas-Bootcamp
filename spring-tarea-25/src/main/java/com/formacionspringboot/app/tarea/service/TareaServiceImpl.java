package com.formacionspringboot.app.tarea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspringboot.app.tarea.model.Cliente;
import com.formacionspringboot.app.tarea.model.Factura;
import com.formacionspringboot.app.tarea.model.Producto;
import com.formacionspringboot.app.tarea.model.Venta_Dia;
import com.formacionspringboot.app.tarea.model.Cliente;
import com.formacionspringboot.app.tarea.model.Factura;
import com.formacionspringboot.app.tarea.model.Producto;
import com.formacionspringboot.app.tarea.model.Venta_Dia;
@Service
public class TareaServiceImpl implements TareaService{
	@Autowired
	Cliente cliente;	
	@Autowired
	Factura factura;	
	@Autowired
	Venta_Dia venta;	
	@Autowired
	Producto producto;
	
	@Override
	public Cliente mostrarClientes() {
		cliente.setCod_cliente(1);
		cliente.setNombres("Pelayo");
		cliente.setApellidos("García Álvarez");
		cliente.setTelefono(666666666);
		return cliente;
	}

	@Override
	public Factura mostrarFacturas() {
		//Cliente
		cliente.setCod_cliente(1);
		cliente.setNombres("Pelayo");
		cliente.setApellidos("García Álvarez");
		cliente.setTelefono(666666666);
		//Producto
		producto.setCod_producto(1);
		producto.setNombre_producto("Manzana");
		producto.setPrecio_producto(2.50);
		//Factura
		factura.setCliente(cliente);
		factura.setCod_cliente(cliente.getCod_cliente());
		factura.setProducto(producto);
		factura.setCod_producto(producto.getCod_producto());
		factura.setCod_factura(1);
		factura.setIva(21);
		factura.setSub_total(2.50);
		double total = (factura.getIva()*factura.getSub_total());
		factura.setTotal(total);
		return factura;
	}

	@Override
	public Producto mostrarProductos() {
		producto.setCod_producto(1);
		producto.setNombre_producto("Manzana");
		producto.setPrecio_producto(2.50);
		return producto;
	}

	@Override
	public Venta_Dia mostrarVentas() {	
		//Cliente
		cliente.setCod_cliente(1);
		cliente.setNombres("Pelayo");
		cliente.setApellidos("García Álvarez");
		cliente.setTelefono(666666666);
		//Producto
		producto.setCod_producto(1);
		producto.setNombre_producto("Manzana");
		producto.setPrecio_producto(2.50);
		//Factura
		factura.setCliente(cliente);
		factura.setCod_cliente(cliente.getCod_cliente());
		factura.setProducto(producto);
		factura.setCod_producto(producto.getCod_producto());
		factura.setCod_factura(1);
		factura.setIva(21);
		factura.setSub_total(2.50);
		double total = (factura.getIva()*factura.getSub_total());
		factura.setTotal(total);
		//Venta
		venta.setCod_factura(1);
		venta.setVen_diaria("13 de Agosto del 2020");
		venta.setTotal_venta(7.50);
		venta.setFactura(factura);
		return venta;
	}

	
}
