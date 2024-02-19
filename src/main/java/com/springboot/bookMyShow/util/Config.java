package com.springboot.bookMyShow.util;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class Config implements WebMvcConfigurer{
	
	@Bean
	public OpenAPI swaggerDocOpenApi()
	{
		Server DevlopServer=new Server();
		DevlopServer.setUrl("localhost:8080");
		DevlopServer.setDescription("Development Server");
		
		Contact c= new Contact();
		c.setEmail("kuttyarun0601@gmail.com");
		c.setName("Kumar");
		c.setUrl("https://github.com/KuttyArun0601/BookMyShow");
		
		
		License l= new License();
		l.setName("License");
		l.setUrl("license providers");
		
		Info i=new Info();
		i.setContact(c);
		i.setLicense(l);
		i.setDescription("Book-My-Show : Mr.Arun's project");
		
		i.setTermsOfService("https://www.termsandconditionsgenerator.com/payment-terms-and-conditions/");
		i.setTitle("Book-My-Show");
		i.setVersion("2.0");
		
		
		
		OpenAPI op=new OpenAPI();
		op.info(i);
		op.servers(Arrays.asList(DevlopServer));
		return op;
		
	}

}
