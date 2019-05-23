package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import com.example.demo.models.Profesor;

public interface ProfesoresRepository extends CassandraRepository<Profesor, UUID> {

	@Query(allowFiltering=true)
	Profesor findByid(UUID id);
	
	@Query(allowFiltering=true)
	Profesor findByNumeroDocumento(String numerodocumento);
}
