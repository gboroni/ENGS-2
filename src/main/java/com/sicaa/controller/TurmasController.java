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

import com.sicaa.model.Turma;
import com.sicaa.model.Cont;
import com.sicaa.model.ErrorCustom;
import com.sicaa.repository.Turmas;
import com.sicaa.repository.Contador;
import com.sicaa.repository.Disciplinas;

@Controller
@RequestMapping("/turmas")
public class TurmasController {

	@Autowired
	private Turmas turmas;
	
	@Autowired
	private Disciplinas disciplinas;
	
	@Autowired
	private Contador contador;

	@GetMapping("/novo")
	public ModelAndView listar() {

		ModelAndView mvw = new ModelAndView("CadastroTurmas");
		mvw.addObject("turmas", turmas.findAllTurmas());
		mvw.addObject("disciplinas", disciplinas.findAll());
		mvw.addObject(new Turma());
		return mvw;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Turma turma, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("CadastroTurmas");
		mv.addObject("disciplinas", disciplinas.findAll());
		if (bindingResult.hasErrors()) {
			mv.addObject("mensagem", ErrorCustom.getMensagemErro(bindingResult.getAllErrors().get(0)));
		} else {
			List<String> msg = new ArrayList<String>();
			if (turma.getCodigo().trim().equals("")) {
				msg.add("*" + "O código da turma não pode ser vazio!");
				;
			}
			if (turma.getId_disciplina() == null || turma.getId_disciplina() <= 0) {
				msg.add("*" + "Selecione uma disciplina!");
			}
			if (msg.size() == 0) {
				this.turmas.save(turma);
				msg.add("Salvo com sucesso!");
				mv = new ModelAndView("redirect:/turmas/");
				mv.addObject("mensagem", msg);
				return mv;
			} else {
				mv.addObject("mensagem", msg);
			}
		}
		return mv;
	}
	
	@RequestMapping(params = { "mensagem" })
	public ModelAndView listarSucesso(@RequestParam(value = "mensagem") String mensagem) {
		List<Turma> todosTurmas = turmas.findAllTurmas();
		ModelAndView mv = new ModelAndView("ListaTurmas");
		mv.addObject("turmas", todosTurmas);
		mv.addObject("mensagem",mensagem);
		return mv;
	}

	@RequestMapping
	public ModelAndView pesquisar() {
		List<Turma> todosTurmas = turmas.findAllTurmas();
		ModelAndView mv = new ModelAndView("ListaTurmas");
		mv.addObject("turmas", todosTurmas);
		return mv;
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Turma turma) {
		ModelAndView mv = new ModelAndView("CadastroTurmas");
		mv.addObject("disciplinas", disciplinas.findAll());
		mv.addObject(turma);
		return mv;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id) {
		turmas.delete(id);
		// attributes.addFlashAttribute("mensagem", "Título excluído com
		// sucesso!");
		return "redirect:/turmas";
	}

	@RequestMapping(params = { "id", "action" })
	public ModelAndView excluir2(@RequestParam(value = "id") long id, @RequestParam(value = "action") String action) {
		ModelAndView mv = new ModelAndView("/ListaTurmas");
		Cont c = contador.findAlunosByTurma((int)id);
		if (c.getCont() > 0) {
			mv = this.pesquisar();
			List<String> msg = new ArrayList<String>();
			msg.add("*" + "Não é possível remover a turma pois ela contém alunos matriculados!");
			mv.addObject("mensagem_erro", msg);
			return mv;
		}
		if (action.equals("delete"))
			turmas.delete(id);
		List<String> msg = new ArrayList<>();
		msg.add("Excluido com sucesso!");
		mv = this.pesquisar();
		mv.addObject("mensagem", msg);
		return mv;
	}

}
