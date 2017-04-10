package com.sicaa.model;

public class ResponseApresentacao {

	private Integer erro;

	private String mensagem;

	private Object alunos;

	private Object criterios;

	public ResponseApresentacao(Integer erro, String mensagem, Object alunos, Object criterios) {
		super();
		this.erro = erro;
		this.mensagem = mensagem;
		this.alunos = alunos;
		this.criterios = criterios;
	}

	public Integer getErro() {
		return erro;
	}

	public void setErro(Integer erro) {
		this.erro = erro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Object getAlunos() {
		return alunos;
	}

	public void setAlunos(Object alunos) {
		this.alunos = alunos;
	}

	public Object getCriterios() {
		return criterios;
	}

	public void setCriterios(Object criterios) {
		this.criterios = criterios;
	}

}
