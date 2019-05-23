package com.example.demo.models;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Table("profesor")
public class Profesor {

@PrimaryKey
	
	private UUID id=UUID.randomUUID();
	
	@NotNull(message="Campo Obligatorio")
	@Size(max=100)
	private String nombre;
	
	@NotNull(message="Campo Obligatorio")
	@Size(max=50)
	private String apellidoPaterno;
	
	@NotNull(message="Campo Obligatorio")
	@Size(max=50)
	private String apellidoMaterno;
	
	@NotNull(message="Campo Obligatorio")
	@Size(max=20)
	private String tipoDocumento;
	
	@NotNull(message="Campo Obligatorio")
	@Size(max=20)
	private String numeroDocumento;
	
	@NotNull(message="Campo Obligatorio")
	@Size(max=1)
	private String sexo;
	
	@NotNull(message="Campo Obligatorio")
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date fechaNacimiento;
	
	@NotNull(message="Campo Obligatorio")
	@Size(max=200)
	private String direccion;
	
	@NotNull(message="Campo Obligatorio")
	@Size(max=15)
	private String telefono;
	
	@NotNull(message="Campo Obligatorio")
	private UUID idUbigeo;	
}
