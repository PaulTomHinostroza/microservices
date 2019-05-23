package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Profesor;
import com.example.demo.repository.ProfesoresRepository;

@Service
public class ProfesoresService {

	@Autowired
	private ProfesoresRepository repository;
	
	//Crear 
	public Profesor save(Profesor Profesor)
	{
		if(this.exists_create(Profesor))
		{
			return null;
		}
		return repository.save(Profesor);
	}
	
	//Listar 
	public List<Profesor> findAll()
	{
		return repository.findAll();
	}
	
	//Update 
	public Profesor update(UUID id,Profesor Profesor)
	{
		Profesor _exists_numerodocumento = this.findByNumeroDocumento(Profesor.getNumeroDocumento());
				
		if(_exists_numerodocumento!=null)
		{
			if(_exists_numerodocumento.getId().equals(id))
			{
				return repository.save(Profesor);
			}			
			return null;
		}
		
		return repository.save(Profesor);
	}
	
	//Delete 
	public void deleteById(UUID id)
	{
		repository.deleteById(id);
	}
	
	//Search Repository
	public Profesor findByNumeroDocumento(String numerodocumento)
	{
		Profesor found=null;
		found = repository.findByNumeroDocumento(numerodocumento);
		return found;
	}
	public Profesor findByid(UUID id)
	{
		Profesor found=null;
		found = repository.findByid(id);
		return found;
	}
	
	//Existe validacion - create
	public Boolean exists_create(Profesor Profesor)
	{
		Boolean _exists_create=false;
		if(this.findByNumeroDocumento(Profesor.getNumeroDocumento())!=null)
		{
			_exists_create = true;
		}
		return _exists_create;
	}
}
