package com.formacionspringboot.apirest.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	private static final String[] AUTH_WHITELIST = {
            //Es para permitir acceder al Swagger
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            //Son los listados autorizados
            "/api/coches",
            "/api/marcas",
            "/api/modelos",
            "/api/usuarios"
    };
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(AUTH_WHITELIST)
		.permitAll()
		.anyRequest()
		.authenticated();
	}

	
}