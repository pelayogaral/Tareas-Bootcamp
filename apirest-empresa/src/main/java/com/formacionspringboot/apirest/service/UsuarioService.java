package com.formacionspringboot.apirest.service;

import java.util.List;

import com.formacionspringboot.apirest.entity.Usuario;

public interface UsuarioService 
{
	public List<Usuario> findAll();
	public Usuario findById(Long id);
	public Usuario save(Usuario usuario);
	public void delete (Long id);
	public Usuario findByUser(String username);
}
