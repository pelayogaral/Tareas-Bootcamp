package com.formacionspringboot.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspringboot.apirest.dao.ProductoDAO;
import com.formacionspringboot.apirest.entity.Producto;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	ProductoDAO productoDAO;
	
	@Override
	@Transactional(readOnly=true) 
	public List<Producto> findAll() {
		return (List<Producto>) productoDAO.findAll();
	}

	@Override
	@Transactional(readOnly=true) 
	public Producto findById(Long id) 
	{
		return productoDAO.findById(id).orElse(null); //El else es por si no nos devuelve un producto que devuelva null
	}

	@Override
	@Transactional
	public Producto save(Producto producto) 
	{
		return productoDAO.save(producto);//Devuelve el producto guardado
	}

	@Override
	@Transactional
	public void delete(Long id) {
		productoDAO.deleteById(id); //Debido que el delete es un void no devuelve nada, solo elimina
		
	}

}
