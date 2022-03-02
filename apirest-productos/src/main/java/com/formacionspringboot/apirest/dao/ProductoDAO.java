package com.formacionspringboot.apirest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspringboot.apirest.entity.Producto;

@Repository
public interface ProductoDAO extends CrudRepository<Producto, Long> {

}
