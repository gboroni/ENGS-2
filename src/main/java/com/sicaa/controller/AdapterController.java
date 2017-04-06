package com.sicaa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicaa.model.Response;
import com.sicaa.repository.Alunos;


@RestController
@RequestMapping("/api")
public class AdapterController {

	@Autowired
	private Alunos alunos;

	@GetMapping("/login")
	public Response listar()  {

		return new Response(0,"",alunos.findAll());
	}

	

}
