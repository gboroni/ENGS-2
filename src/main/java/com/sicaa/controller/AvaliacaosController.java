package com.sicaa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sicaa.model.Apresentacao;
import com.sicaa.repository.Apresentacaos;
import com.sicaa.repository.Avaliacaos;

@Controller
@RequestMapping("/avaliacaos")
public class AvaliacaosController {

	@Autowired
	private Avaliacaos avaliacaos;
	
	@Autowired
	private Apresentacaos apresentacaos;
	
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Apresentacao> todosAvaliacaos = apresentacaos.findAllApresentacaosAvaliadas();
		ModelAndView mv = new ModelAndView("ListaAvaliacaos");
		mv.addObject("avaliacaosAvaliadas", todosAvaliacaos);
		return mv;
	}


}
