package com.sicaa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.sicaa.repository.Convidados;

import com.sicaa.model.Convidado;
import com.sicaa.repository.Convidados;

@Controller
@RequestMapping("/convidados")
public class ConvidadosController {

	@Autowired
	private Convidados convidados;

	@GetMapping("/novo")
	public ModelAndView listar() {

		ModelAndView mvw = new ModelAndView("CadastroConvidados");
		mvw.addObject("convidados", convidados.findAll());
		mvw.addObject(new Convidado());
		return mvw;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Convidado convidado) {
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

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Convidado convidado) {
		ModelAndView mv = new ModelAndView("CadastroConvidados");
		mv.addObject(convidado);
		return mv;
	}

	
	
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id) {
		convidados.delete(id);
		
		//attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
		return "redirect:/convidados";
	}
	
	


//	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
//		convidados.delete(id);
//		attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
//		return "redirect:/convidados";
//	}


}
