package com.springboot.clienteapp.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ciudades")
public class Ciudad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String ciudad;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	@Override
	public String toString() {
		return "Ciudad [id=" + id + ", ciudad=" + ciudad + "]";
	}
	
	
}
