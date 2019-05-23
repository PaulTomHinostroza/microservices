package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ProfesoresTest {

	@Autowired
	MockMvc mockMvc;
	
	//Created
	@Test
	public void Create_Empty_Error() throws Exception
	{
		String body= "{}";
		
		this.mockMvc.perform(
			post("/profesores")
			.contentType(MediaType.APPLICATION_JSON)
			.content(body)			
		).andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void Create_FullData_Ok() throws Exception
	{
		String Body= "{\"id\":\"dfee8182-a25c-4045-a7ec-0b12c0a0ea57\","
				+ "\"nombre\":\"Andres\","
				+ "\"apellidoPaterno\":\"Rojas\","
				+ "\"apellidoMaterno\":\"Quispe\","
				+ "\"tipoDocumento\":\"DNI\","
				+ "\"numeroDocumento\":\"78986598\","
				+ "\"sexo\":\"M\","
				+ "\"fechaNacimiento\":\"1996-15-01\","
				+ "\"direccion\":\"av.las\","
				+ "\"telefono\":\"95685959\","
				+ "\"idUbigeo\":\"dfee8182-a25c-4045-a7ec-0b12c0a0ea57\"}";
		
		this.mockMvc.perform(
			post("/profesores")
			.contentType(MediaType.APPLICATION_JSON)
			.content(Body)			
		).andExpect(status().isOk());		
	}
	
	@Test
	public void Create_DuplicateNumeroDocumento_Error() throws Exception
	{
		String Body= "{\"id\":\"dfee8182-a25c-4045-a7ec-0b12c0a0ea58\","
				+ "\"nombre\":\"Daniel\","
				+ "\"apellidoPaterno\":\"Quispe\","
				+ "\"apellidoMaterno\":\"Mamani\","
				+ "\"tipoDocumento\":\"DNI\","
				+ "\"numeroDocumento\":\"78986598\","
				+ "\"sexo\":\"M\","
				+ "\"fechaNacimiento\":\"1996-15-01\","
				+ "\"direccion\":\"av.las Calmelias 586\","
				+ "\"telefono\":\"986596936\","
				+ "\"idUbigeo\":\"dfee8182-a25c-4045-a7ec-0b12c0a0ea57\"}";
		
		this.mockMvc.perform(
			post("/profesores")
			.contentType(MediaType.APPLICATION_JSON)
			.content(Body)			
		).andExpect(status().isBadRequest());		
	}
	
	//Update
	@Test
	public void Update_FullData_Ok() throws Exception
	{
		String Body= "{\"id\":\"dfee8182-a25c-4045-a7ec-0b12c0a0ea57\","
				+ "\"nombre\":\"Andres Dan\","
				+ "\"apellidoPaterno\":\"Rojas\","
				+ "\"apellidoMaterno\":\"Quispe\","
				+ "\"tipoDocumento\":\"DNI\","
				+ "\"numeroDocumento\":\"78986598\","
				+ "\"sexo\":\"M\","
				+ "\"fechaNacimiento\":\"1996-15-01\","
				+ "\"direccion\":\"av.las Palmas\","
				+ "\"telefono\":\"968596862\","
				+ "\"idUbigeo\":\"dfee8182-a25c-4045-a7ec-0b12c0a0ea57\"}";
		
		this.mockMvc.perform(
			patch("/profesores")
			.contentType(MediaType.APPLICATION_JSON)
			.content(Body)			
		).andExpect(status().isOk());		
	}

	//Listado
	@Test
	public void  List_Profesores_Ok() throws Exception
	{
		this.mockMvc.perform(
			get("/profesores")
			.contentType(MediaType.APPLICATION_JSON)	
		).andExpect(status().isOk());	
	}
	
	//Deleted
	@Test
	public void Delete_Exists_Ok() throws Exception
	{
		this.mockMvc.perform(
			delete("/profesores/{id}", "dfee8182-a25c-4045-a7ec-0b12c0a0ea57")
			.contentType(MediaType.APPLICATION_JSON)	
			.accept(MediaType.APPLICATION_JSON)
		).andExpect(status().isOk());		
	}
	
	@Test
	public void Delete_NotExists_Error() throws Exception
	{
		this.mockMvc.perform(
			delete("/profesores/{id}", "dfee8182-a25c-4045-a7ec-0b12c0a0ea57")
			.contentType(MediaType.APPLICATION_JSON)	
			.accept(MediaType.APPLICATION_JSON)
		).andExpect(status().isBadRequest());		
	}
}
