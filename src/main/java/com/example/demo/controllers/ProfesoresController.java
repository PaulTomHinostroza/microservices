package com.example.demo.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Profesor;
import com.example.demo.service.ProfesoresService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/profesores")
public class ProfesoresController {

	@Autowired
	private ProfesoresService service;
	
	//Registrar 
	@RequestMapping(value="",method=RequestMethod.POST)
	public ResponseEntity<Object> create(@Valid @RequestBody Profesor Profesor)
	{
		Profesor responseSave;
		responseSave = service.save(Profesor);
		
		if(responseSave!=null)
		{
			Profesor responseId = service.findByid(responseSave.getId());
			
			if(responseId!=null)
			{
				return new ResponseEntity<Object>(responseSave,HttpStatus.OK);
			}
			return new ResponseEntity<Object>("No esta registrado !",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>("Ya existe datos!",HttpStatus.BAD_REQUEST);
	}
	
	//Listar 
	@RequestMapping(value="",method=RequestMethod.GET)
	public ResponseEntity<Object> findAll()
	{
		List<Profesor> responseList;
		responseList = service.findAll();
		if(responseList.isEmpty())
		{
			return new ResponseEntity<Object>("No existe ningun dato !",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(responseList,HttpStatus.OK);
	}
	
	//Editar 
	@RequestMapping(value="",method=RequestMethod.PATCH)
	public ResponseEntity<Object> update(@Valid @RequestBody Profesor Profesor)
	{
		Profesor responseUpdate;
		responseUpdate = service.update(Profesor.getId(),Profesor);
		
		if(responseUpdate!=null)
		{
			return new ResponseEntity<Object>(responseUpdate,HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Ya existen datos registrados !",HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	public ResponseEntity<Object> updateAll(@Valid @RequestBody Profesor Profesor)
	{
		Profesor responseUpdate;
		responseUpdate = service.update(Profesor.getId(),Profesor);
		
		if(responseUpdate!=null)
		{
			return new ResponseEntity<Object>(responseUpdate,HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Ya existen datos registrados !",HttpStatus.BAD_REQUEST);
	}
	//Eliminar 
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteById(@PathVariable UUID id)
	{
		Profesor responseId=service.findByid(id);
	    if(responseId==null)
	    {
	    	return new ResponseEntity<Object>("No se pudo Eliminar !",HttpStatus.BAD_REQUEST);
	    }
	    service.deleteById(id);
	    return new ResponseEntity<Object>("Eliminado !",HttpStatus.OK);	
	}
	
	//Search
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Object> findById(@PathVariable UUID id)
	{
		Profesor responseId=service.findByid(id);
	    if(responseId==null)
	    {
	    	return new ResponseEntity<Object>("No se encontraron datos !",HttpStatus.BAD_REQUEST);
	    }
	    
	    return new ResponseEntity<Object>(responseId,HttpStatus.OK);
	}
}
