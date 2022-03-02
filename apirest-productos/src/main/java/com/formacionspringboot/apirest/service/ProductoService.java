package com.formacionspringboot.apirest.service;

import java.util.List;

import com.formacionspringboot.apirest.entity.Producto;

public interface ProductoService {

	public List<Producto> findAll();
	public Producto findById(Long id);
	public Producto save(Producto producto);
	public void delete(Long id);
}
