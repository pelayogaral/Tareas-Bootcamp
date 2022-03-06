package com.formacionspringboot.apirest.service;

import java.util.List;

import com.formacionspringboot.apirest.entity.Coche;

public interface CocheService 
{
	public List<Coche> findAll();
	public Coche findById(Long id);
	public Coche save(Coche coche);
	public void delete (Long id);

}
