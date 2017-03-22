package com.sicaa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sicaa.model.Disciplina;
import com.sicaa.repository.Disciplinas;

@Controller
public class DisciplinaController {
	
	@Autowired
	private Disciplinas disciplinas;
	
	@GetMapping("/disciplinas")
	public ModelAndView listar(){
		ModelAndView mvw = new ModelAndView("Disciplinas");
		mvw.addObject("disciplinas", disciplinas.findAll());
		mvw.addObject(new Disciplina());
		return mvw;
	}
	
	@PostMapping("/disciplinas")
	public String salvar(Disciplina convidado){
		this.disciplinas.save(convidado);
		return "redirect:/disciplinas";
	}
		
}
