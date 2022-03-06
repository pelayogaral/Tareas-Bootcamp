package com.formacionspringboot.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspringboot.apirest.dao.ModeloDAO;
import com.formacionspringboot.apirest.entity.Modelo;

@Service
public class ModeloServiceImpl implements ModeloService
{

	@Autowired
	ModeloDAO modeloDAO;
	
	@Override
	public List<Modelo>findAll() 
	{
		return (List<Modelo>)modeloDAO.findAll();
	}

	@Override
	public Modelo findById(Long id) 
	{
		return modeloDAO.findById(id).orElse(null);
	}

	@Override
	public Modelo save(Modelo modelo) 
	{
		return modeloDAO.save(modelo);
	}

	@Override
	public void delete(Long id) 
	{
		modeloDAO.deleteById(id);
	}

}
