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


import com.formacionspringboot.apirest.entity.Empleado;
import com.formacionspringboot.apirest.service.EmpleadoService;

@RestController
@RequestMapping("/api")
public class EmpleadoRestController 
{
	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping("/empleados")
	public List<Empleado> listarEmpleados(){
		return empleadoService.findAll();
	}
	
	@GetMapping("/empleados/{id}")
	public ResponseEntity<?> encontrarDepartamento(@PathVariable Long id) 
	{
		
		Empleado empleado = null;
		
		Map<String, Object> response = new HashMap<>();	
		
		try 
		{
			empleado = empleadoService.findById(id);
		} 
		catch (DataAccessException e) {
			response.put("mensaje", "Error al reallizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(empleado == null)
		{
			response.put("mensaje", "El empleado con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
		
	}
	@PostMapping("/empleado")
	public ResponseEntity<?> guardarEmpleado(@RequestBody Empleado empleado){
		Empleado empleadoNuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		try 
		{
			empleadoNuevo = empleadoService.save(empleado);
		} 
		catch (DataAccessException e) 
		{
			response.put("mensaje", "Error al insertar el empleado");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		
		response.put("mensaje", "El empleado ha sido creado con éxito");
		response.put("empleado", empleadoNuevo);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@PutMapping("/empleado/{id}")
	public ResponseEntity<?> actualizarDepartamento(@RequestBody Empleado empleado, @PathVariable Long id) {
		Empleado empleadoActual = empleadoService.findById(id);
		Map<String, Object> response = new HashMap<>();	
		if (empleadoActual == null) {
			response.put("mensaje", "El empleado con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}	
		try
		{
			empleadoActual.setDni(empleado.getDni());
			empleadoActual.setNombre(empleado.getNombre());
			empleadoActual.setSalario(empleado.getSalario());
			empleadoActual.setTelefono(empleado.getTelefono());
			empleadoActual.setDepartamento(empleado.getDepartamento());			
			empleadoService.save(empleadoActual);
		}
		catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar update");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		response.put("mensaje", "El empleado ha sido actualizado con éxito");
		response.put("empleado", empleadoActual);	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);	
	}
	
	
	@DeleteMapping("/empleado/{id}")
	public ResponseEntity<?> eliminarEmpleado(@PathVariable Long id)
	{	
		Empleado empleadoEliminado = empleadoService.findById(id);
		Map<String, Object> response = new HashMap<>();		
		if (empleadoEliminado == null) {
			response.put("mensaje", "El empleado con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}	
		try {
			empleadoService.delete(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el empleado");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		response.put("mensaje", "El empleado ha sido eliminado con éxito");
		response.put("empleado", empleadoEliminado);	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}
