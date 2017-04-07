package com.sicaa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tb_Apresentacao_Aluno")
public class ApresentacaoAluno  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	private Integer id_aluno;
	
	@Column
	private Integer id_turma;
	
	@Column
	private Integer id_apresentacao;
	
	private String matricula_aluno;
	
	private String nome_aluno;
	
	private String codigo_turma;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getId_aluno() {
		return id_aluno;
	}

	public void setId_aluno(Integer id_aluno) {
		this.id_aluno = id_aluno;
	}

	public Integer getId_turma() {
		return id_turma;
	}

	public void setId_turma(Integer id_turma) {
		this.id_turma = id_turma;
	}

	public Integer getId_apresentacao() {
		return id_apresentacao;
	}

	public void setId_apresentacao(Integer id_apresentacao) {
		this.id_apresentacao = id_apresentacao;
	}

	public String getNome_aluno() {
		return nome_aluno;
	}

	public void setNome_aluno(String nome_aluno) {
		this.nome_aluno = nome_aluno;
	}

	public String getCodigo_turma() {
		return codigo_turma;
	}

	public void setCodigo_turma(String codigo_turma) {
		this.codigo_turma = codigo_turma;
	}

	public String getMatricula_aluno() {
		return matricula_aluno;
	}

	public void setMatricula_aluno(String matricula_aluno) {
		this.matricula_aluno = matricula_aluno;
	}
	
	
	
}
