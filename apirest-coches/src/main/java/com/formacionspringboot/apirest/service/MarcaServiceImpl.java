package com.formacionspringboot.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspringboot.apirest.dao.MarcaDAO;
import com.formacionspringboot.apirest.entity.Marca;

@Service
public class MarcaServiceImpl implements MarcaService
{
	@Autowired
	MarcaDAO marcaDAO;
	
	@Override
	public List<Marca> findAll() 
	{
		return (List<Marca>)marcaDAO.findAll();
	}

	@Override
	public Marca findById(Long id) 
	{
		return marcaDAO.findById(id).orElse(null);
	}

	@Override
	public Marca save(Marca marca) 
	{
		return marcaDAO.save(marca);
	}

	@Override
	public void delete(Long id) 
	{
		marcaDAO.deleteById(id);		
	}

}
