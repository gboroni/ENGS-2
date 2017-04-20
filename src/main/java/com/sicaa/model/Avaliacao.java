package com.sicaa.model;

import java.io.Serializable;
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
@Table(name = "Tb_Avaliacao")
public class Avaliacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private Integer id_apresentacao;
	
	@Column
	private Integer id_apresentacao_aluno;
	
	@Column
	private Integer id_apresentacao_criterio;
	
	@Column
	private Double nota;
	
	@Column
	private String avaliador;
		
	@Column
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	private String descricao_criterio;
	
	private Integer peso_criterio;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Integer getId_apresentacao() {
		return id_apresentacao;
	}

	public void setId_apresentacao(Integer id_apresentacao) {
		this.id_apresentacao = id_apresentacao;
	}

	public Integer getId_apresentacao_aluno() {
		return id_apresentacao_aluno;
	}

	public void setId_apresentacao_aluno(Integer id_apresentacao_aluno) {
		this.id_apresentacao_aluno = id_apresentacao_aluno;
	}

	public Integer getId_apresentacao_criterio() {
		return id_apresentacao_criterio;
	}

	public void setId_apresentacao_criterio(Integer id_apresentacao_criterio) {
		this.id_apresentacao_criterio = id_apresentacao_criterio;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public String getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(String avaliador) {
		this.avaliador = avaliador;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao_criterio() {
		return descricao_criterio;
	}

	public void setDescricao_criterio(String descricao_criterio) {
		this.descricao_criterio = descricao_criterio;
	}

	public Integer getPeso_criterio() {
		return peso_criterio;
	}

	public void setPeso_criterio(Integer peso_criterio) {
		this.peso_criterio = peso_criterio;
	} 

}
