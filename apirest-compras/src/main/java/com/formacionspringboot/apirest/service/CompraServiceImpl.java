package com.formacionspringboot.apirest.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspringboot.apirest.dao.CompraDAO;
import com.formacionspringboot.apirest.entity.Compra;

@Service
public class CompraServiceImpl implements CompraService{

	@Autowired
	CompraDAO compraDAO;
	
	
	@Override
	@Transactional(readOnly=true) 
	public List<Compra> findAll() {
		
		return (List<Compra>) compraDAO.findAll();
	}

	@Override
	@Transactional(readOnly=true) 
	public Compra findById(Long id) {
		
		return compraDAO.findById(id).orElse(null); 
	}

	@Override
	public Compra save(Compra compra) {	
		return compraDAO.save(compra);
	}

	@Override
	public void delete(Long id) {
		compraDAO.deleteById(id);		
	}

	@Override
	public Compra findByFecha(Date fecha) {

		return compraDAO.findByFecha(fecha);
	}




}
