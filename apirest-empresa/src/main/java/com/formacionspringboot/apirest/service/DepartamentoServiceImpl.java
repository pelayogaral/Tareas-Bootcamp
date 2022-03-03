package com.formacionspringboot.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspringboot.apirest.dao.DepartamentoDAO;
import com.formacionspringboot.apirest.entity.Departamento;

@Service
public class DepartamentoServiceImpl implements DepartamentoService{

	@Autowired
	DepartamentoDAO departamentoDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Departamento> findAll() 
	{	
		return (List<Departamento>) departamentoDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Departamento findById(Long id) 
	{
		return departamentoDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Departamento save(Departamento departamento) 
	{
		return departamentoDAO.save(departamento);
	}

	@Override
	@Transactional
	public void delete(Long id) 
	{
		departamentoDAO.deleteById(id);
	}

}
