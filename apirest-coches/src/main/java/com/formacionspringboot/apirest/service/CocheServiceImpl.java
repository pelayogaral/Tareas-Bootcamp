package com.formacionspringboot.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspringboot.apirest.dao.CocheDAO;
import com.formacionspringboot.apirest.entity.Coche;

@Service
public class CocheServiceImpl implements CocheService
{

	@Autowired
	CocheDAO cocheDAO;
	
	@Override
	public List<Coche> findAll() 
	{
		return (List<Coche>)cocheDAO.findAll();
	}

	@Override
	public Coche findById(Long id) 
	{
		return cocheDAO.findById(id).orElse(null);
	}

	@Override
	public Coche save(Coche coche) 
	{
		return cocheDAO.save(coche);
	}

	@Override
	public void delete(Long id) 
	{
		cocheDAO.deleteById(id);
	}

}
