package com.sicaa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.sicaa.repository.Convidados;
import com.sicaa.model.Convidado;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/convidados")
public class ConvidadosController {
	
	@Autowired
	private Convidados convidados;
	
	@GetMapping("/novo")
	public ModelAndView listar(){
		
		ModelAndView mvw = new ModelAndView("CadastroConvidados");
		mvw.addObject("convidados", convidados.findAll());
		mvw.addObject(new Convidado());
		return mvw;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Convidado convidado){
		this.convidados.save(convidado);
		return "redirect:/convidados";
	}
	
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Convidado> todosConvidados = convidados.findAll();
		ModelAndView mv = new ModelAndView("ListaConvidados");
		mv.addObject("convidados", todosConvidados);
		return mv;
	}
}
