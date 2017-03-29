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

import com.sicaa.model.ApresentacaoCriterio;
import com.sicaa.model.ErrorCustom;
import com.sicaa.repository.ApresentacaoCriterios;
import com.sicaa.repository.Apresentacaos;
import com.sicaa.repository.Criterios;

@Controller
@RequestMapping("/apresentacaocriterios")
public class ApresentacaoCriterioController {

	@Autowired
	private ApresentacaoCriterios apresentacaocriterios;
	
	@Autowired
	private Apresentacaos apresentacaos;
	
	@Autowired
	private Criterios criterios;

	@GetMapping("/novo")
	public ModelAndView listar() {

		ModelAndView mvw = new ModelAndView("CadastroApresentacaoCriterios");
		mvw.addObject("apresentacaocriterios", apresentacaocriterios.findAll());
		mvw.addObject(new ApresentacaoCriterio());
		return mvw;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(ApresentacaoCriterio apresentacaocriterio, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("CadastroApresentacaoCriterios");
		if (bindingResult.hasErrors()) {
			mv.addObject("mensagem", ErrorCustom.getMensagemErro(bindingResult.getAllErrors().get(0)));
		} else {
			List<String> msg = new ArrayList<String>();
			if (apresentacaocriterio.getId_apresentacao() == null || apresentacaocriterio.getId_apresentacao() <= 0) {
				msg.add("*" + "Selecione uma apresentação!");
				;
			}
			if (apresentacaocriterio.getId_criterio() == null || apresentacaocriterio.getId_criterio() <= 0) {
				msg.add("*" + "Selecione um critério!");
				;
			}
			if (msg.size() == 0) {
				this.apresentacaocriterios.save(apresentacaocriterio);
				msg.add("Salvo com sucesso!");
//				mv = this.pesquisar();
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
		List<ApresentacaoCriterio> todosApresentacaoCriterios = apresentacaocriterios.findAll();
		ModelAndView mv = new ModelAndView("ListaApresentacaoCriterios");
		mv.addObject("apresentacaocriterios", todosApresentacaoCriterios);
		return mv;
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") ApresentacaoCriterio apresentacaocriterio) {
		ModelAndView mv = new ModelAndView("CadastroApresentacaoCriterios");
		mv.addObject(apresentacaocriterio);
		return mv;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id) {
		apresentacaocriterios.delete(id);
		// attributes.addFlashAttribute("mensagem", "Título excluído com
		// sucesso!");
		return "redirect:/apresentacaocriterios";
	}

	@RequestMapping(params = { "id", "action" })
	public ModelAndView excluir2(@RequestParam(value = "id") long id, @RequestParam(value = "action") String action) {
		if (action.equals("delete"))
			apresentacaocriterios.delete(id);
		ModelAndView mv = new ModelAndView("ListaApresentacaoCriterios");
		List<String> msg = new ArrayList<>();
		msg.add("Excluido com sucesso!");
		mv = this.pesquisar();
		mv.addObject("mensagem", msg);
		return mv;
	}

}
