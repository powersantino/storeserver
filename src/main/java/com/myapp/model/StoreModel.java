package com.myapp.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
@Entity(name = "store")
public class StoreModel implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="nome")
	private String nome;
	@Column(name="indirizzo")
	private String indirizzo;
	@Column(name="lat")
	private BigDecimal lat;
	@Column(name="lon")
	private BigDecimal lon;
	@Lob
	@Column(name="foto")	
	private byte[] foto;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public BigDecimal getLat() {
		return lat;
	}
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	public BigDecimal getLon() {
		return lon;
	}
	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}
//	public byte[] getFoto() {
//		return foto;
//	}
	public  byte[] getFoto() {
		return foto;
	}
//	public void setFoto(byte[] foto) {
//		this.foto = foto;
//	}
	public void setFoto( byte[] foto) {
		this.foto = foto;
	}
}
