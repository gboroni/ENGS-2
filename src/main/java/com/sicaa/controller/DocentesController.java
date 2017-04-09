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

import com.sicaa.model.Docente;
import com.sicaa.model.ErrorCustom;

import com.sicaa.repository.Docentes;

@Controller
@RequestMapping("/docentes")
public class DocentesController {

	@Autowired
	private Docentes docentes;

	@GetMapping("/novo")
	public ModelAndView listar() {

		ModelAndView mvw = new ModelAndView("CadastroDocentes");
		mvw.addObject("docentes", docentes.findAll());
		mvw.addObject(new Docente());
		return mvw;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Docente docente, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("CadastroDocentes");
		if (bindingResult.hasErrors()) {
			mv.addObject("mensagem", ErrorCustom.getMensagemErro(bindingResult.getAllErrors().get(0)));
		} else {
			List<String> msg = new ArrayList<String>();
			if (docente.getNome().trim().equals("")) {
				msg.add("*" + "O nome do aluno não pode ser vazio!");
				;
			}
			if (docente.getMatricula().trim().equals("")) {
				msg.add("*" + "A matrícula do aluno não pode ser vazia!");
			}
			if (msg.size() == 0) {
				this.docentes.save(docente);
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
		List<Docente> todosDocentes = docentes.findAll();
		ModelAndView mv = new ModelAndView("ListaDocentes");
		mv.addObject("docentes", todosDocentes);
		return mv;
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Docente docente) {
		ModelAndView mv = new ModelAndView("CadastroDocentes");
		mv.addObject(docente);
		return mv;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id) {
		docentes.delete(id);
		// attributes.addFlashAttribute("mensagem", "Título excluído com
		// sucesso!");
		return "redirect:/docentes";
	}

	@RequestMapping(params = { "id", "action" })
	public ModelAndView excluir2(@RequestParam(value = "id") long id, @RequestParam(value = "action") String action) {
		if (action.equals("delete"))
			docentes.delete(id);
		ModelAndView mv = new ModelAndView("ListaDocentes");
		List<String> msg = new ArrayList<>();
		msg.add("Excluido com sucesso!");
		mv = this.pesquisar();
		mv.addObject("mensagem", msg);
		return mv;
	}
}
