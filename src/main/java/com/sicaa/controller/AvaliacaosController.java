package com.sicaa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sicaa.model.Apresentacao;
import com.sicaa.repository.ApresentacaoAlunos;
import com.sicaa.repository.Apresentacaos;
import com.sicaa.repository.Avaliacaos;

@Controller
@RequestMapping("/avaliacaos")
public class AvaliacaosController {

	@Autowired
	private Avaliacaos avaliacaos;
	
	@Autowired
	private Apresentacaos apresentacaos;
	
	@Autowired
	private ApresentacaoAlunos alunos;
	
	
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Apresentacao> todosAvaliacaos = apresentacaos.findAllApresentacaosAvaliadas();
		ModelAndView mv = new ModelAndView("ListaAvaliacaos");
		mv.addObject("avaliacaosAvaliadas", todosAvaliacaos);
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("ListaAvaliacaoAlunos");
		mv.addObject("apalunos",alunos.findAllAlunosByApresentacao(id));
		return mv;
	}
	
	@RequestMapping("{id}/{id_apresentacao_aluno}")
	public ModelAndView edicao(@PathVariable("id") Integer id, @PathVariable("id_apresentacao_aluno") Integer id_apresentacao_aluno) {
		ModelAndView mv = new ModelAndView("ListaAlunoAvaliadores");
		mv.addObject("avaliadores",avaliacaos.findAllAvaliadoresByApresentacao(id_apresentacao_aluno));
		return mv;
	}


}
