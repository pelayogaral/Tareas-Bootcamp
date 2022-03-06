package com.formacionspringboot.apirest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspringboot.apirest.entity.Modelo;

@Repository
public interface ModeloDAO extends CrudRepository<Modelo,Long>{

}
