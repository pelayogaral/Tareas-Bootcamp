package com.formacionspringboot.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title ="API Empresa",version ="1.0",description ="Crud completo de Empleados, Jefes y Departamentos"))
public class ApirestEmpresaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestEmpresaApplication.class, args);
	}

}
