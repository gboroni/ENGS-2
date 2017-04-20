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

import com.sicaa.model.Aluno;
import com.sicaa.model.ErrorCustom;
import com.sicaa.repository.Alunos;

@Controller
@RequestMapping("/alunos")
public class AlunosController {

	@Autowired
	private Alunos alunos;

	@GetMapping("/novo")
	public ModelAndView listar() {

		ModelAndView mvw = new ModelAndView("CadastroAlunos");
		mvw.addObject("alunos", alunos.findAll());
		mvw.addObject(new Aluno());
		return mvw;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Aluno aluno, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("CadastroAlunos");
		if (bindingResult.hasErrors()) {
			mv.addObject("mensagem", ErrorCustom.getMensagemErro(bindingResult.getAllErrors().get(0)));
		} else {
			List<String> msg = new ArrayList<String>();
			if (aluno.getNome().trim().equals("")) {
				msg.add("*" + "O nome do aluno não pode ser vazio!");
				;
			}
			if (aluno.getMatricula().trim().equals("")) {
				msg.add("*" + "A matrícula do aluno não pode ser vazia!");
			}
			
			if (msg.size() == 0 && aluno.getId() == 0){
				List<Aluno> a = alunos.findAlunosByMatricula(aluno.getMatricula());
				if (a != null && !a.isEmpty()){
					msg.add("*" + "Este aluno já existe!");
				}
			}
			if (msg.size() == 0) {
				
				this.alunos.save(aluno);
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
		List<Aluno> todosAlunos = alunos.findAll();
		ModelAndView mv = new ModelAndView("ListaAlunos");
		mv.addObject("alunos", todosAlunos);
		return mv;
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Aluno aluno) {
		ModelAndView mv = new ModelAndView("CadastroAlunos");
		mv.addObject(aluno);
		return mv;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id) {
		alunos.delete(id);
		// attributes.addFlashAttribute("mensagem", "Título excluído com
		// sucesso!");
		return "redirect:/alunos";
	}

	@RequestMapping(params = { "id", "action" })
	public ModelAndView excluir2(@RequestParam(value = "id") long id, @RequestParam(value = "action") String action) {
		if (action.equals("delete"))
			alunos.delete(id);
		ModelAndView mv = new ModelAndView("ListaAlunos");
		List<String> msg = new ArrayList<>();
		msg.add("Excluido com sucesso!");
		mv = this.pesquisar();
		mv.addObject("mensagem", msg);
		return mv;
	}

}
