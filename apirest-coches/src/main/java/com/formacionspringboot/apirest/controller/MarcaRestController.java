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

import com.formacionspringboot.apirest.entity.Marca;
import com.formacionspringboot.apirest.service.MarcaService;

@RestController
@RequestMapping("/api")
public class MarcaRestController 
{
	@Autowired
	MarcaService servicio;
	
	@GetMapping("/marcas")
	public List<Marca> listarModelos()
	{
		return (List<Marca>)servicio.findAll();
	}
	
	@GetMapping("/marcas/{id}")
	public ResponseEntity<?> datosModelo(@PathVariable Long id) 
	{
		Map<String, Object> response = new HashMap<>();
		Marca marca = null;
		try 
		{
			marca = servicio.findById(id);
		} 
		catch (DataAccessException e) {
			response.put("mensaje", "Error al reallizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(marca == null)
		{
			response.put("mensaje", "La marca con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		
		return new ResponseEntity<Marca>(marca, HttpStatus.OK);
	}
	@PostMapping("/marca")
	public ResponseEntity<?> guardarModelo(@RequestBody Marca marca){
		Marca marcaNueva = null;
		Map<String, Object> response = new HashMap<>();
		
		try 
		{
			marcaNueva = servicio.save(marca);
		} 
		catch (DataAccessException e) 
		{
			response.put("mensaje", "Error al insertar la marca");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		
		response.put("mensaje", "La marca ha sido creada con éxito");
		response.put("marca",marcaNueva);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@PutMapping("/marca/{id}")
	public ResponseEntity<?> actualizarModelo(@RequestBody Marca marca, @PathVariable Long id) {
		Marca marcaActual = servicio.findById(id);
		Map<String, Object> response = new HashMap<>();	
		if (marcaActual == null) {
			response.put("mensaje", "La marca con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}	
		try{
			marcaActual.setNombre(marca.getNombre());
			servicio.save(marcaActual);
		}
		catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar update");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		response.put("mensaje", "La marca ha sido actualizada con éxito");
		response.put("marca", marcaActual);	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
	}
	
	
	@DeleteMapping("/marca/{id}")
	public ResponseEntity<?> eliminarMarca(@PathVariable Long id)
	{	
		Marca marcaEliminada = servicio.findById(id);
		Map<String, Object> response = new HashMap<>();		
		if (marcaEliminada == null) {
			response.put("mensaje", "El marca con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}	
		try {
			servicio.delete(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la marca");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		response.put("mensaje", "El marca ha sido eliminada con éxito");
		response.put("marca", marcaEliminada);	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
