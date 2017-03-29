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

	@RequestMapping(params = { "id", "id_apresentacao", "id_criterio" })
	public ModelAndView salvar(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "id_apresentacao") Integer id_apresentacao,
			@RequestParam(value = "id_criterio") Integer id_criterio) {

		ApresentacaoCriterio apresentacaocriterio = new ApresentacaoCriterio();
		apresentacaocriterio.setId(id);
		apresentacaocriterio.setId_apresentacao(id_apresentacao);
		apresentacaocriterio.setId_criterio(id_criterio);

		ModelAndView mv = new ModelAndView("CadastroApresentacaoCriterios");

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
			mv = this.novo(id);
			mv.addObject("mensagem_sucesso", msg);
			return mv;
		} else {
			mv.addObject("mensagem", msg);
		}
		return mv;
	}

	@RequestMapping(params = { "id" })
	public ModelAndView novo(@RequestParam(value = "id") Integer id) {
		ModelAndView mvw = new ModelAndView("CadastroApresentacaoCriterios");
		mvw.addObject("apresentacaocriterios", apresentacaocriterios.findAll());
		ApresentacaoCriterio ap = new ApresentacaoCriterio();
		ap.setId_apresentacao(id);
		mvw.addObject("criterios", criterios.findAll());
		mvw.addObject("apresentacaos", apresentacaos.findAll());
		mvw.addObject("apresentacaocriterios", apresentacaocriterios.findAllCriteriosByApresentacao(id));
		mvw.addObject("apresentacaocriterio", ap);
		return mvw;
	}

}
