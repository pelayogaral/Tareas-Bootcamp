package com.formacionspringboot.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspringboot.apirest.dao.EmpleadoDAO;
import com.formacionspringboot.apirest.entity.Empleado;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

	@Autowired
	EmpleadoDAO empleadoDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAll() 
	{	
		return (List<Empleado>) empleadoDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Empleado findById(Long id) 
	{
		return empleadoDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Empleado save(Empleado empleado) 
	{
		return empleadoDAO.save(empleado);
	}

	@Override
	@Transactional
	public void delete(Long id) 
	{
		empleadoDAO.deleteById(id);
	}


}
