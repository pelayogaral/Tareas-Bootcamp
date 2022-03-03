package com.formacionspringboot.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspringboot.apirest.dao.JefeDAO;
import com.formacionspringboot.apirest.entity.Jefe;

@Service
public class JefeServiceImpl implements JefeService{

	@Autowired
	JefeDAO jefeDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Jefe> findAll() 
	{	
		return (List<Jefe>) jefeDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Jefe findById(Long id) 
	{
		return jefeDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Jefe save(Jefe jefe) 
	{
		return jefeDAO.save(jefe);
	}

	@Override
	@Transactional
	public void delete(Long id) 
	{
		jefeDAO.deleteById(id);
	}


}
