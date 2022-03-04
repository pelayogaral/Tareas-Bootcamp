package com.formacionspringboot.apirest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspringboot.apirest.entity.Usuario;

@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Long>
{
	public Usuario findByUser(String user);
	
}
