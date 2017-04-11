package com.sicaa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sicaa.model.ApresentacaoCriterio;
import com.sicaa.model.Cont;
import com.sicaa.model.TurmaAluno;
import com.sicaa.repository.ApresentacaoCriterios;
import com.sicaa.repository.Apresentacaos;
import com.sicaa.repository.Contador;
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
	
	@Autowired
	private Contador contador;

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
			Cont c = contador.findApresentacaoCriterio(id_apresentacao, id_criterio);
			if (c.getCont() > 0) {
				msg.add("*" + "Este critério já foi adicionado nesta turma");
			}
		}
		if (msg.size() == 0) {
			this.apresentacaocriterios.save(apresentacaocriterio);
			msg.add("Salvo com sucesso!");
			mv = this.redirect(id_apresentacao);
			mv.addObject("mensagem_sucesso", msg);
			return mv;
		} else {
			mv = this.novo(id_apresentacao);
			mv.addObject("mensagem", msg);
		}
		return mv;
	}

	@RequestMapping(params = { "id" })
	public ModelAndView novo(@RequestParam(value = "id") Integer id) {
		ModelAndView mvw = new ModelAndView("CadastroApresentacaoCriterios");
		ApresentacaoCriterio ap = new ApresentacaoCriterio();
		ap.setId_apresentacao(id);
		mvw.addObject("criterios", criterios.findAll());
		mvw.addObject("apresentacaos", apresentacaos.findAll());
		mvw.addObject("apcriterios", apresentacaocriterios.findAllCriteriosByApresentacao(id));
		mvw.addObject("apresentacaocriterio", ap);
		return mvw;
	}

	@RequestMapping(params = { "id", "mensagem_sucesso" })
	public ModelAndView novo(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "mensagem_sucesso") String mensagem_sucesso) {
		ModelAndView mvw = new ModelAndView("CadastroApresentacaoCriterios");
		ApresentacaoCriterio ap = new ApresentacaoCriterio();
		ap.setId_apresentacao(id);
		mvw.addObject("criterios", criterios.findAll());
		mvw.addObject("apresentacaos", apresentacaos.findAll());
		mvw.addObject("apcriterios", apresentacaocriterios.findAllCriteriosByApresentacao(id));
		mvw.addObject("mensagem_sucesso", mensagem_sucesso);
		mvw.addObject("apresentacaocriterio", ap);
		return mvw;
	}

	@RequestMapping(params = { "id", "id_apresentacao", "action" })
	public ModelAndView excluir2(@RequestParam(value = "id") long id,
			@RequestParam(value = "id_apresentacao") Integer id_apresentacao,
			@RequestParam(value = "action") String action) {
		if (action.equals("delete"))
			apresentacaocriterios.delete(id);
		ModelAndView mvw = novo(id_apresentacao);
		List<String> msg = new ArrayList<String>();
		msg.add("Critério removido com sucesso!");
		mvw.addObject("mensagem_sucesso", msg);

		return mvw;
	}

	public ModelAndView redirect(Integer id) {
		ModelAndView mvw = new ModelAndView("redirect:/apresentacaocriterios/");
		mvw.addObject("id", id);
		return mvw;
	}

}
