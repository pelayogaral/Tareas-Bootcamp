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

import com.formacionspringboot.apirest.entity.Jefe;
import com.formacionspringboot.apirest.service.JefeService;

@RestController
@RequestMapping("/api")
public class JefeRestController 
{
	@Autowired
	JefeService jefeService;
	
	@GetMapping("/jefes")
	public List<Jefe> listarJefes(){
		return (List<Jefe>)jefeService.findAll();
	}
	@GetMapping("/jefes/{id}")
	public ResponseEntity<?> encontrarJefe(@PathVariable Long id)
	{
		Map<String, Object> response = new HashMap<>();
		Jefe jefe = null;
		try 
		{
			jefe = jefeService.findById(id);
		} 
		catch (DataAccessException e) 
		{
			response.put("mensaje", "Error al reallizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(jefe == null)
		{
			response.put("mensaje", "El jefe con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Jefe>(jefe, HttpStatus.OK);
	}
	@PostMapping("/jefe")
	public ResponseEntity<?> guardarJefe(@RequestBody Jefe jefe){
		Jefe jefeNuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		try 
		{
			jefeNuevo = jefeService.save(jefe);
		} 
		catch (DataAccessException e) 
		{
			response.put("mensaje", "Error al insertar el jefe");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		
		response.put("mensaje", "El jefe ha sido creado con éxito");
		response.put("jefe", jefeNuevo);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@PutMapping("/jefe/{id}")
	public ResponseEntity<?> actualizarJefe(@RequestBody Jefe jefe, @PathVariable Long id) {
		Jefe jefeActual = jefeService.findById(id);
		Map<String, Object> response = new HashMap<>();	
		if (jefeActual == null) {
			response.put("mensaje", "El jefe con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}	
		try
		{
			jefeActual.setDni(jefe.getDni());
			jefeActual.setNombre(jefe.getNombre());
			jefeActual.setSalario(jefe.getSalario());
			jefeActual.setTelefono(jefe.getTelefono());
			jefeActual.setDepartamento(jefe.getDepartamento());			
			jefeService.save(jefeActual);
		}
		catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar update");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		response.put("mensaje", "El jefe ha sido actualizado con éxito");
		response.put("jefe", jefeActual);	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);	
	}
	
	
	@DeleteMapping("/jefe/{id}")
	public ResponseEntity<?> eliminarJefe(@PathVariable Long id)
	{	
		Jefe jefeEliminado = jefeService.findById(id);
		Map<String, Object> response = new HashMap<>();		
		if (jefeEliminado == null) {
			response.put("mensaje", "El jefe con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}	
		try {
			jefeService.delete(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el jefe");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		response.put("mensaje", "El jefe ha sido eliminado con éxito");
		response.put("jefe", jefeEliminado);	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
