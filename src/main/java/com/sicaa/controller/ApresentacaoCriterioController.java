package com.sicaa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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


	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(ApresentacaoCriterio apresentacaocriterio) {
		ModelAndView mv = new ModelAndView("CadastroApresentacaoCriterios");
//		if (bindingResult.hasErrors()) {
//			mv.addObject("mensagem", ErrorCustom.getMensagemErro(bindingResult.getAllErrors().get(0)));
//		} else {
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
//		}
		return mv;
	}


	@RequestMapping(params = { "id" })
	public ModelAndView excluir2(@RequestParam(value = "id") Integer id) {
		ModelAndView mvw = new ModelAndView("CadastroApresentacaoCriterios");
		mvw.addObject("apresentacaocriterios", apresentacaocriterios.findAll());
		ApresentacaoCriterio ap = new ApresentacaoCriterio();
//		ap.setId_apresentacao(id);
		mvw.addObject("criterios", criterios.findAll());
		mvw.addObject("apresentacaos", apresentacaos.findAll());
		mvw.addObject("apresentacaocriterio", ap);
		return mvw;
	}

}
