package com.sicaa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sicaa.model.Aluno;
import com.sicaa.model.Turma;
import com.sicaa.repository.Alunos;

@Controller
public class AlunoController {
	
	@Autowired
	private Alunos alunos;
	
	@GetMapping("/alunos")
	public ModelAndView listar(){
		
		ModelAndView mvw = new ModelAndView("Alunos");
		mvw.addObject("alunos", alunos.findAll());
		mvw.addObject(new Aluno());
		return mvw;
	}
	
	@PostMapping("/alunos")
	public String salvar(Aluno convidado){
		this.alunos.save(convidado);
		return "redirect:/alunos";
	}
}
