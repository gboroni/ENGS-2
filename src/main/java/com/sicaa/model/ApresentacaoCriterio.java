package com.sicaa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tb_Apresentacao_Criterio")
public class ApresentacaoCriterio  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	private Integer id_criterio;
	
	@Column
	private Integer id_apresentacao;
	
	private String descricao_criterio;
	
	private Integer peso_criterio;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getId_criterio() {
		return id_criterio;
	}

	public void setId_criterio(Integer id_criterio) {
		this.id_criterio = id_criterio;
	}

	public Integer getId_apresentacao() {
		return id_apresentacao;
	}

	public void setId_apresentacao(Integer id_apresentacao) {
		this.id_apresentacao = id_apresentacao;
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
