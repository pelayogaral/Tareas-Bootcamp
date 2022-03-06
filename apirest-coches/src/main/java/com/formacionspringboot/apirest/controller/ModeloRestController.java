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

import com.formacionspringboot.apirest.entity.Modelo;
import com.formacionspringboot.apirest.service.ModeloService;

@RestController
@RequestMapping("/api")
public class ModeloRestController 
{
	@Autowired
	ModeloService servicio;
	
	@GetMapping("/modelos")
	public List<Modelo> listarModelos()
	{
		return (List<Modelo>)servicio.findAll();
	}
	
	@GetMapping("/modelos/{id}")
	public ResponseEntity<?> datosModelo(@PathVariable Long id) 
	{
		Map<String, Object> response = new HashMap<>();
		Modelo modelo = null;
		try 
		{
			modelo = servicio.findById(id);
		} 
		catch (DataAccessException e) {
			response.put("mensaje", "Error al reallizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(modelo == null)
		{
			response.put("mensaje", "El modelo con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		
		return new ResponseEntity<Modelo>(modelo, HttpStatus.OK);
	}
	@PostMapping("/modelo")
	public ResponseEntity<?> guardarModelo(@RequestBody Modelo modelo){
		Modelo modeloNuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		try 
		{
			modeloNuevo = servicio.save(modelo);
		} 
		catch (DataAccessException e) 
		{
			response.put("mensaje", "Error al insertar el modelo");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		
		response.put("mensaje", "El modelo ha sido creado con éxito");
		response.put("modelo", modeloNuevo);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@PutMapping("/modelo/{id}")
	public ResponseEntity<?> actualizarModelo(@RequestBody Modelo modelo, @PathVariable Long id) {
		Modelo modeloActual = servicio.findById(id);
		Map<String, Object> response = new HashMap<>();	
		if (modeloActual == null) {
			response.put("mensaje", "El modelo con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}	
		try{
			modeloActual.setNombre(modelo.getNombre());
			servicio.save(modeloActual);
		}
		catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar update");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		response.put("mensaje", "El modelo ha sido actualizado con éxito");
		response.put("modelo", modeloActual);	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
	}
	
	
	@DeleteMapping("/modelo/{id}")
	public ResponseEntity<?> eliminarModelo(@PathVariable Long id)
	{	
		Modelo modeloEliminado = servicio.findById(id);
		Map<String, Object> response = new HashMap<>();		
		if (modeloEliminado == null) {
			response.put("mensaje", "El modelo con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}	
		try {
			servicio.delete(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el modelo");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		response.put("mensaje", "El modelo ha sido eliminado con éxito");
		response.put("modelo", modeloEliminado);	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
