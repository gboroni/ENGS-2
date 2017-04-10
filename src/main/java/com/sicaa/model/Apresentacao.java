package com.sicaa.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Tb_Apresentacao")
public class Apresentacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private Integer id_tema;

	@Column
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date inicio;

	@Column
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date fim;

	@Column
	private Boolean status;
	
	private String nome_tema;
	
	private BigInteger avaliacoes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getId_tema() {
		return id_tema;
	}

	public void setId_tema(Integer id_tema) {
		this.id_tema = id_tema;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getNome_tema() {
		return nome_tema;
	}

	public void setNome_tema(String nome_tema) {
		this.nome_tema = nome_tema;
	}

	public BigInteger getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(BigInteger avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	

}
