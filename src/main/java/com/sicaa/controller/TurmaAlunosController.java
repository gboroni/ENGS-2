package com.sicaa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sicaa.model.TurmaAluno;
import com.sicaa.repository.Alunos;
import com.sicaa.repository.TurmaAlunos;
import com.sicaa.repository.Turmas;

@Controller
@RequestMapping("/turmaalunos")
public class TurmaAlunosController {

	@Autowired
	private TurmaAlunos turmaalunos;

	@Autowired
	private Alunos alunos;

	@Autowired
	private Turmas turmas;

	@RequestMapping(params = { "id", "id_aluno", "id_turma" })
	public ModelAndView salvar(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "id_aluno") Integer id_aluno, @RequestParam(value = "id_turma") Integer id_turma,
			RedirectAttributes redirectAttributes) {

		TurmaAluno turmaaluno = new TurmaAluno();
		turmaaluno.setId(id);
		turmaaluno.setId_aluno(id_aluno);
		turmaaluno.setId_turma(id_turma);

		ModelAndView mv = new ModelAndView("CadastroTurmaAlunos");

		List<String> msg = new ArrayList<String>();
		if (turmaaluno.getId_aluno() == null || turmaaluno.getId_aluno() <= 0) {
			msg.add("*" + "Selecione um aluno para adicionar!");
			;
		}
		if (turmaaluno.getId_turma() == null || turmaaluno.getId_turma() <= 0) {
			msg.add("*" + "Selecione uma turma!");
			;
		}
		if (msg.size() == 0) {
			this.turmaalunos.save(turmaaluno);
			msg.add("Salvo com sucesso!");
			mv = this.redirect(id_turma);
			mv.addObject("mensagem_sucesso", msg);
			return mv;
		} else {
			mv = this.novo(id_turma);
			mv.addObject("id", id_turma);
			mv.addObject("mensagem", msg);
			mv.addObject("talunos", turmaalunos.findAllAlunosByTurma(id_turma));
		}
		return mv;
	}

	public ModelAndView redirect(Integer id) {
		ModelAndView mvw = new ModelAndView("redirect:/turmaalunos/");
		TurmaAluno ap = new TurmaAluno();
		ap.setId_turma(id);
		mvw.addObject("id", id);
		return mvw;
	}

	@RequestMapping(params = { "id", "mensagem_sucesso" })
	public ModelAndView novo(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "mensagem_sucesso") String mensagem_sucesso) {
		ModelAndView mvw = new ModelAndView("CadastroTurmaAlunos");
		TurmaAluno ap = new TurmaAluno();
		ap.setId_turma(id);
		mvw.addObject("alunos", alunos.findAll());
		mvw.addObject("turmas", turmas.findAllTurmas());
		mvw.addObject("talunos", turmaalunos.findAllAlunosByTurma(id));
		mvw.addObject("mensagem_sucesso", mensagem_sucesso);
		mvw.addObject("turmaaluno", ap);
		return mvw;
	}

	@RequestMapping(params = { "id" })
	public ModelAndView novo(@RequestParam(value = "id") Integer id) {
		ModelAndView mvw = new ModelAndView("CadastroTurmaAlunos");
		TurmaAluno ap = new TurmaAluno();
		ap.setId_turma(id);
		mvw.addObject("alunos", alunos.findAll());
		mvw.addObject("turmas", turmas.findAllTurmas());
		mvw.addObject("talunos", turmaalunos.findAllAlunosByTurma(id));
		mvw.addObject("turmaaluno", ap);
		return mvw;
	}
	
	@RequestMapping(params = { "id","id_turma", "action" })
	public ModelAndView excluir2(@RequestParam(value = "id") long id, @RequestParam(value = "id_turma") Integer id_turma , @RequestParam(value = "action") String action) {
		if (action.equals("delete"))
			turmaalunos.delete(id);
		ModelAndView mv = redirect(id_turma);
		List<String> msg = new ArrayList<>();
		msg.add("Excluido com sucesso!");
		mv.addObject("mensagem_sucesso", msg);
		return mv;
	}

}
