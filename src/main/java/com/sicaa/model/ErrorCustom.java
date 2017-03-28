package com.sicaa.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.ObjectError;

public class ErrorCustom {
	public static List<String> getMensagemErro(ObjectError e){
		List<String> m = new ArrayList<>();
		m.add(e.getCode());
		return m;
	}
}
