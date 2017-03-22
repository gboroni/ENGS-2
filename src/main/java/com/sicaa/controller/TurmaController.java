package com.sicaa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sicaa.model.Turma;
import com.sicaa.repository.Turmas;

@Controller
public class TurmaController {
	
	@Autowired
	private Turmas turmas;
	
	@GetMapping("/turmas")
	public ModelAndView listar(){
		
		ModelAndView mvw = new ModelAndView("Turmas");
		mvw.addObject("turmas", turmas.findAll());
		mvw.addObject(new Turma());
		return mvw;
	}
	
	@PostMapping("/turmas")
	public String salvar(Turma convidado){
		this.turmas.save(convidado);
		return "redirect:/turmas";
	}
}
