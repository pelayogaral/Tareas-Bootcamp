package com.formacionspringboot.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspringboot.apirest.entity.Usuario;
import com.formacionspringboot.apirest.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioRestController 
{
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public List<Usuario> listarUsuarios(){
		return (List<Usuario>)usuarioService.findAll();
	}
	@PostMapping("/usuario")
	public ResponseEntity<?> encontrarUsuario(@RequestBody Usuario usuario) 
	{	
		Usuario usuarioEntra = usuarioService.findByUser(usuario.getUsername());
		Map<String, Object> response = new HashMap<>();
		if(usuarioEntra.getUsername().equals(usuario.getUsername()) && usuarioEntra.getPassword().equals(usuario.getPassword())) 
		{
			response.put("mensaje", "El usuario ha sido validado con éxito");
			response.put("usuario", usuario);
		}		
		else
		{
			response.put("mensaje", "Usuario o contraseña erronea");
			response.put("usuario", usuario);
		}		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
}
