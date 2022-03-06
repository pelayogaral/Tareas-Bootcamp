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

import com.formacionspringboot.apirest.entity.Coche;
import com.formacionspringboot.apirest.service.CocheService;

@RestController
@RequestMapping("/api")
public class CocheRestController 
{
	@Autowired
	CocheService servicio;
	
	@GetMapping("/coches")
	public List<Coche> listarCoches()
	{
		return (List<Coche>)servicio.findAll();
	}
	
	@GetMapping("/coches/{id}")
	public ResponseEntity<?> datosCoche(@PathVariable Long id) 
	{
		Map<String, Object> response = new HashMap<>();
		Coche coche = null;
		try 
		{
			coche = servicio.findById(id);
		} 
		catch (DataAccessException e) {
			response.put("mensaje", "Error al reallizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(coche == null)
		{
			response.put("mensaje", "El coche con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		
		return new ResponseEntity<Coche>(coche, HttpStatus.OK);
	}
	@PostMapping("/coche")
	public ResponseEntity<?> guardarCoche(@RequestBody Coche coche){
		Coche cocheNuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		try 
		{
			cocheNuevo = servicio.save(cocheNuevo);
		} 
		catch (DataAccessException e) 
		{
			response.put("mensaje", "Error al insertar el coche");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		
		response.put("mensaje", "El coche ha sido creado con éxito");
		response.put("coche", cocheNuevo);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@PutMapping("/coche/{id}")
	public ResponseEntity<?> actualizarCoche(@RequestBody Coche coche, @PathVariable Long id) {
		Coche cocheActual = servicio.findById(id);
		Map<String, Object> response = new HashMap<>();	
		if (cocheActual == null) {
			response.put("mensaje", "El coche con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}	
		try{
			cocheActual.setColor(coche.getColor());
			cocheActual.setMatricula(coche.getMatricula());	
			cocheActual.setCilindrada(coche.getCilindrada());		
			cocheActual.setVelocidad(coche.getVelocidad());
			servicio.save(cocheActual);
		}
		catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar update");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		response.put("mensaje", "El coche ha sido actualizado con éxito");
		response.put("coche", cocheActual);	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
	}
	
	
	@DeleteMapping("/coche/{id}")
	public ResponseEntity<?> eliminarCoche(@PathVariable Long id)
	{	
		Coche cocheEliminado = servicio.findById(id);
		Map<String, Object> response = new HashMap<>();		
		if (cocheEliminado == null) {
			response.put("mensaje", "El coche con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}	
		try {
			servicio.delete(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el coche");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		response.put("mensaje", "El coche ha sido eliminado con éxito");
		response.put("coche", cocheEliminado);	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
