package com.sicaa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sicaa.model.Criterio;
import com.sicaa.model.ErrorCustom;
import com.sicaa.repository.Criterios;

@Controller
@RequestMapping("/criterios")
public class CriteriosController {

	@Autowired
	private Criterios criterios;

	@GetMapping("/novo")
	public ModelAndView listar() {

		ModelAndView mvw = new ModelAndView("CadastroCriterios");
		mvw.addObject("criterios", criterios.findAll());
		mvw.addObject(new Criterio());
		return mvw;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Criterio criterio, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("CadastroCriterios");
		if (bindingResult.hasErrors()) {
			mv.addObject("mensagem", ErrorCustom.getMensagemErro(bindingResult.getAllErrors().get(0)));
		} else {
			List<String> msg = new ArrayList<String>();
			if (criterio.getDescricao().trim().equals("")) {
				msg.add("*" + "A descrição não pode ser vazia!");
				;
			}
			if (criterio.getPeso() == null || criterio.getPeso() <= 0) {
				msg.add("*" + "Um critério não pode ter peso abaixo de 1!");
			}
			if (msg.size() == 0) {
				this.criterios.save(criterio);
				msg.add("Salvo com sucesso!");
				mv = this.pesquisar();
				mv.addObject("mensagem", msg);
				return mv;
			} else {
				mv.addObject("mensagem", msg);
			}
		}
		return mv;
	}

	@RequestMapping
	public ModelAndView pesquisar() {
		List<Criterio> todosCriterios = criterios.findAll();
		ModelAndView mv = new ModelAndView("ListaCriterios");
		mv.addObject("criterios", todosCriterios);
		return mv;
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Criterio criterio) {
		ModelAndView mv = new ModelAndView("CadastroCriterios");
		mv.addObject(criterio);
		return mv;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id) {
		criterios.delete(id);
		// attributes.addFlashAttribute("mensagem", "Título excluído com
		// sucesso!");
		return "redirect:/criterios";
	}

	@RequestMapping(params = { "id", "action" })
	public ModelAndView excluir2(@RequestParam(value = "id") long id, @RequestParam(value = "action") String action) {
		if (action.equals("delete"))
			criterios.delete(id);
		ModelAndView mv = new ModelAndView("ListaCriterios");
		List<String> msg = new ArrayList<>();
		msg.add("Excluido com sucesso!");
		mv = this.pesquisar();
		mv.addObject("mensagem", msg);
		return mv;
	}

}
