package com.sicaa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cont")
public class Cont implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer cont;

	public Integer getCont() {
		return cont;
	}

	public void setCont(Integer cont) {
		this.cont = cont;
	}

}
