package com.formacionspringboot.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.formacionspringboot.apirest.entity.Departamento;
import com.formacionspringboot.apirest.service.DepartamentoService;

@RestController
@RequestMapping("/api")
public class DepartamentoRestController 
{
	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping("/departamentos")
	public List<Departamento> listarDepartamentos(){
		return departamentoService.findAll();
	}
	
	@GetMapping("/departamentos/{id}")
	public ResponseEntity<?> encontrarDepartamento(@PathVariable Long id) 
	{
		
		Departamento departamento = null;
		
		Map<String, Object> response = new HashMap<>();	
		
		try 
		{
			departamento = departamentoService.findById(id);
		} 
		catch (DataAccessException e) {
			response.put("mensaje", "Error al reallizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(departamento == null)
		{
			response.put("mensaje", "El departamento con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Departamento>(departamento, HttpStatus.OK);
		
	}
	@PostMapping("/departamento")
	public ResponseEntity<?> guardarDepartamento(@RequestBody Departamento departamento){
		Departamento departamentoNuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		try 
		{
			departamentoNuevo = departamentoService.save(departamento);
		} 
		catch (DataAccessException e) 
		{
			response.put("mensaje", "Error al insertar el departamento");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		
		response.put("mensaje", "El departamento ha sido creado con éxito");
		response.put("departamento", departamentoNuevo);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@PutMapping("/departamento/{id}")
	public ResponseEntity<?> actualizarDepartamento(@RequestBody Departamento departamento, @PathVariable Long id) {
		Departamento departamentoActual = departamentoService.findById(id);
		Map<String, Object> response = new HashMap<>();	
		if (departamentoActual == null) {
			response.put("mensaje", "El departamento con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}	
		try{
			departamentoActual.setNombre(departamento.getNombre());
			departamentoActual.setUbicacion(departamento.getUbicacion());		
			departamentoService.save(departamentoActual);
		}
		catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar update");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		response.put("mensaje", "El departamento ha sido actualizado con éxito");
		response.put("departamento", departamentoActual);	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);	
	}
	
	
	@DeleteMapping("/departamento/{id}")
	public ResponseEntity<?> eliminarDepartamento(@PathVariable Long id)
	{	
		Departamento departamentoEliminado = departamentoService.findById(id);
		Map<String, Object> response = new HashMap<>();		
		if (departamentoEliminado == null) {
			response.put("mensaje", "El departamento con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}	
		try {
			departamentoService.delete(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el departamento");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		response.put("mensaje", "El departamento ha sido eliminado con éxito");
		response.put("departamento", departamentoEliminado);	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
}
