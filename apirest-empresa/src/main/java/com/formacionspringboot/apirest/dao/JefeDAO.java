package com.formacionspringboot.apirest.dao;




import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.formacionspringboot.apirest.entity.Jefe;
@Repository
public interface JefeDAO extends CrudRepository <Jefe, Long> 
{


}
