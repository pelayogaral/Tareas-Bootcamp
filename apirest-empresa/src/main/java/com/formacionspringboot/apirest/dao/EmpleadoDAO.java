package com.formacionspringboot.apirest.dao;




import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.formacionspringboot.apirest.entity.Empleado;
@Repository
public interface EmpleadoDAO extends CrudRepository <Empleado, Long> 
{

}
