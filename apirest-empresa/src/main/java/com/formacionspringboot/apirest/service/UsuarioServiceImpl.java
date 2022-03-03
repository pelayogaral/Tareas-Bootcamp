package com.formacionspringboot.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspringboot.apirest.dao.UsuarioDAO;
import com.formacionspringboot.apirest.entity.Usuario;
@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	UsuarioDAO usuarioDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>)usuarioDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return usuarioDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		return usuarioDAO.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDAO.deleteById(id);
		
	}

	@Override
	public Usuario findByUser(String username) {
		
		return usuarioDAO.findByUser(username);
	}

}
