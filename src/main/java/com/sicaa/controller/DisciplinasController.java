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

import com.sicaa.model.Cont;
import com.sicaa.model.Disciplina;
import com.sicaa.model.ErrorCustom;
import com.sicaa.repository.Contador;
import com.sicaa.repository.Disciplinas;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinasController {

	@Autowired
	private Disciplinas disciplinas;
	
	@Autowired
	private Contador contador;

	@GetMapping("/novo")
	public ModelAndView listar() {

		ModelAndView mvw = new ModelAndView("CadastroDisciplinas");
		mvw.addObject("disciplinas", disciplinas.findAll());
		mvw.addObject(new Disciplina());
		return mvw;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Disciplina disciplina, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("CadastroDisciplinas");
		if (bindingResult.hasErrors()) {
			mv.addObject("mensagem", ErrorCustom.getMensagemErro(bindingResult.getAllErrors().get(0)));
		} else {
			List<String> msg = new ArrayList<String>();
			if (disciplina.getNome().trim().equals("")) {
				msg.add("*" + "O nome da disciplina não pode ser vazio!");
				;
			}
			if (msg.size() == 0) {
				this.disciplinas.save(disciplina);
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
		List<Disciplina> todosDisciplinas = disciplinas.findAll();
		ModelAndView mv = new ModelAndView("ListaDisciplinas");
		mv.addObject("disciplinas", todosDisciplinas);
		return mv;
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Disciplina disciplina) {
		ModelAndView mv = new ModelAndView("CadastroDisciplinas");
		mv.addObject(disciplina);
		return mv;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id) {
		disciplinas.delete(id);
		// attributes.addFlashAttribute("mensagem", "Título excluído com
		// sucesso!");
		return "redirect:/disciplinas";
	}

	@RequestMapping(params = { "id", "action" })
	public ModelAndView excluir2(@RequestParam(value = "id") long id, @RequestParam(value = "action") String action) {
		ModelAndView mv = new ModelAndView("ListaDisciplinas");
		Cont c = contador.findTurmaByDisciplina((int)id);
		if (c.getCont() > 0) {
			mv = this.pesquisar();
			List<String> msg = new ArrayList<String>();
			msg.add("*" + "Não é possível remover a disciplina pois ela contém turmas cadastradas!");
			mv.addObject("mensagem_erro", msg);
			return mv;
		}
		if (action.equals("delete"))
			disciplinas.delete(id);
		List<String> msg = new ArrayList<>();
		msg.add("Excluido com sucesso!");
		mv = this.pesquisar();
		mv.addObject("mensagem", msg);
		return mv;
	}

}
