package com.sicaa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sicaa.repository.Convidados;
import com.sicaa.model.Convidado;

@Controller
public class ConvidadosController {
	
	@Autowired
	private Convidados convidados;
	
	@GetMapping("/convidados")
	public ModelAndView listar(){
		
		ModelAndView mvw = new ModelAndView("ListaConvidados");
		mvw.addObject("convidados", convidados.findAll());
		mvw.addObject(new Convidado());
		return mvw;
	}
	
	@PostMapping("/convidados")
	public String salvar(Convidado convidado){
		this.convidados.save(convidado);
		return "redirect:/convidados";
	}
}
