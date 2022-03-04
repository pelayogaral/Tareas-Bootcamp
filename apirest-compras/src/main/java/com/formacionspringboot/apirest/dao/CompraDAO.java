package com.formacionspringboot.apirest.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspringboot.apirest.entity.Compra;
import com.formacionspringboot.apirest.entity.Articulo;
import com.formacionspringboot.apirest.entity.Cliente;

@Repository
public interface CompraDAO extends CrudRepository<Compra, Long>{

	public Compra findByFecha(Date fecha);
}
