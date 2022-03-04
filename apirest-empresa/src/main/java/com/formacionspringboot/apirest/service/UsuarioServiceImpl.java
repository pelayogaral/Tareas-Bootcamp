package com.formacionspringboot.apirest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspringboot.apirest.dao.UsuarioDAO;
import com.formacionspringboot.apirest.entity.Usuario;
@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService{

	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
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
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException
	{
		Usuario usuario = usuarioDAO.findByUser(user);
		if(usuario == null) 
		{
			logger.error("Error en el login: no existe el usuario "+user+" en el sistema");
			throw new UsernameNotFoundException("Error en el login: noexiste el usuario "+user+" en el sistema");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: "+authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(usuario.getUser(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

}
