package com.info.apirest.dto;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProductoDto {

	@NotBlank
	@Length(min = 5, max = 50) 
	private String nombre;

	@NotBlank
	@Length(min = 20, max = 200)
	private String descripcion;

	@NotNull 
	@Positive
	private BigDecimal precioUnitario;

	@Length(min = 100, max = 1000)
	private String contenido;

	@NotNull
	private Boolean publicado;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
 
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Boolean isPublicado() {
		return publicado;
	}

	public void setPublicado(Boolean publicado) {
		this.publicado = publicado;
	}
}
