package com.sicaa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
			@RequestParam(value = "id_aluno") Integer id_aluno,
			@RequestParam(value = "id_turma") Integer id_turma) {

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
		ModelAndView mvw = new ModelAndView("CadastroTurmaAlunos");
		mvw.addObject("turmaalunos", turmaalunos.findAll());
		TurmaAluno ap = new TurmaAluno();
		ap.setId_turma(id);
		mvw.addObject("alunos", alunos.findAll());
		mvw.addObject("turmas", turmas.findAllTurmas());
//		mvw.addObject("turmaalunos", turmaalunos.findAllAlunosByTurma(id));
		mvw.addObject("turmaaluno", ap);
		return mvw;
	}

}
