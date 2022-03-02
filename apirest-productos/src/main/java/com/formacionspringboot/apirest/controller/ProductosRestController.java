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

import com.formacionspringboot.apirest.entity.Producto;
import com.formacionspringboot.apirest.service.ProductoService;

@RestController
@RequestMapping("/api")
public class ProductosRestController {

	@Autowired
	private ProductoService servicio;
	
	@GetMapping("/productos")//Al ser una peticion de recogida de datos usamos GET
	public List<Producto> index()
	{
		return servicio.findAll();
	}
	@GetMapping("/productos/{id}")//Esta vez es solo un dato, por ello usamos el {id}
	public ResponseEntity<?> findClienteById(@PathVariable Long id) //Con @PathVariable indico que llevamos una variable en la peticion
	{
		Producto producto = null;
		Map<String,Object> response = new HashMap<>();//Realizamos el Map para recoger el mensaje de error
		
		//Debido a que la peticion API REST Puede fallar utilizamos el try catch para recoger la excepción de error que pueda dar
		try
		{
			producto = servicio.findById(id);//Buscamos por id	
		}
		catch(DataAccessException e) 
		{
			//Si ha fallado la petición, hacemos un mensaje personalizado de error en la petición
			response.put("mensaje", "Error al realizar la consulta a base de datos");
			//Y tambien el mensaje completo del error y el motivo de ello
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(producto == null)
		{
			//Si el producto solicitado no existe, indicamos con un mensaje de error el fallo que ha cometido el cliente en su petición API REST
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
		
	}
	@PostMapping("/producto")//@PostMapping debido a que es una petición POST que envia datos de creación
	public ResponseEntity<?> saveCliente(@RequestBody Producto producto) //@RequestBody indica que la petición tendra un cuerpo con datos (En nuestro caso a traves del Postman con formato JSON)
	{
		Producto productoCreado = null;
		Map<String,Object> response = new HashMap<>();
		
		try
		{
			productoCreado = servicio.save(producto);		
		}
		catch(DataAccessException e) 
		{
			response.put("mensaje", "Error al realizar una insert a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ".concat(productoCreado.getCodigo().toString()).concat(" ha sido creado con éxito"));
		response.put("producto", productoCreado);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/producto/{id}")//Usamos @PutMapping para indicar que es una petición APIREST de actualización de datos
	public ResponseEntity<?> updateProducto(@RequestBody Producto producto, @PathVariable Long id)
	{
		Producto productoActual = servicio.findById(id);//Recogemos el producto que vamos a actualizar para comprobar que existe
		Map<String,Object> response = new HashMap<>();
		if(productoActual == null)
		{
			response.put("mensaje", "No se puede editar el producto, el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}		
		try 
		{
			productoActual.setCodigo(producto.getCodigo());
			productoActual.setTipo(producto.getTipo());
			productoActual.setCantidad(producto.getCantidad());
			productoActual.setPrecio(producto.getPrecio());
			productoActual.setMarca(producto.getMarca());
			productoActual.setFecha_ingreso(producto.getFecha_ingreso());
			productoActual.setDescripcion(producto.getDescripcion());
		
			servicio.save(productoActual);
		}
		catch(DataAccessException e)
		{
			response.put("mensaje", "Error al realizar un update a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ".concat(productoActual.getCodigo().toString()).concat(" ha sido actualizado con éxito"));
		response.put("cliente", productoActual);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/producto/{id}")//Usamos @DeleteMapping para indicar que es una petición de DELETE
	public ResponseEntity<?> deleteCliente(@PathVariable Long id)
	{
		Producto productoEliminado = servicio.findById(id);//Recogemos que producto va a ser eliminado para comprobar que existe y luego mostrar sus datos
		Map<String,Object> response = new HashMap<>();
		if(productoEliminado == null)
		{
			response.put("mensaje", "No se puede eliminar el producto, el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}		
		try 
		{
			servicio.delete(id);
		}
		catch(DataAccessException e)
		{
			response.put("mensaje", "Error al realizar un delete a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ".concat(productoEliminado.getCodigo().toString()).concat(" ha sido eliminado con éxito"));
		response.put("producto", productoEliminado);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}


}
