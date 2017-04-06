package com.sicaa.model;

public class Response {

	private Integer erro;

	private String mensagem;

	private Object result;
	

	public Response(Integer erro, String mensagem, Object result) {
		super();
		this.erro = erro;
		this.mensagem = mensagem;
		this.result = result;
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

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
