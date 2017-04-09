package com.sicaa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sicaa.model.ApresentacaoAluno;
import com.sicaa.model.TurmaAluno;
import com.sicaa.repository.ApresentacaoAlunos;
import com.sicaa.repository.Apresentacaos;
import com.sicaa.repository.TurmaAlunos;
import com.sicaa.repository.Turmas;

@Controller
@RequestMapping("/apresentacaoalunos")
public class ApresentacaoAlunosController {

	@Autowired
	private ApresentacaoAlunos apresentacaoalunos;

	@Autowired
	private Apresentacaos apresentacaos;

	@Autowired
	private Turmas turmas;

	@Autowired
	private TurmaAlunos alunos;

	@RequestMapping(params = { "id", "id_apresentacao", "id_aluno", "id_turma" })
	public ModelAndView salvar(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "id_apresentacao") Integer id_apresentacao,
			@RequestParam(value = "id_aluno") Integer id_aluno, @RequestParam(value = "id_turma") Integer id_turma) {

		ApresentacaoAluno apresentacaoaluno = new ApresentacaoAluno();
		apresentacaoaluno.setId(id);
		apresentacaoaluno.setId_apresentacao(id_apresentacao);
		apresentacaoaluno.setId_aluno(id_aluno);
		apresentacaoaluno.setId_turma(id_turma);
		;

		ModelAndView mv = new ModelAndView("CadastroApresentacaoAlunos");

		List<String> msg = new ArrayList<String>();
		if (apresentacaoaluno.getId_apresentacao() == null || apresentacaoaluno.getId_apresentacao() <= 0) {
			msg.add("*" + "Selecione uma apresentação!");
		}
		if (apresentacaoaluno.getId_turma() == null || apresentacaoaluno.getId_turma() <= 0) {
			apresentacaoaluno.setId_turma(0);
			msg.add("*" + "Selecione uma Turma!");
		}
		if (apresentacaoaluno.getId_aluno() == null || apresentacaoaluno.getId_aluno() <= 0) {
			apresentacaoaluno.setId_aluno(0);
			msg.add("*" + "Selecione um Aluno!");
		}
		
		if (apresentacaoalunos.findAlunoByApresentacao(apresentacaoaluno.getId_apresentacao(), apresentacaoaluno.getId_aluno()) > 0) {
			msg.add("*" + "Este aluno ja esta cadastrado nessa apresentacao!");
			
		}
		
		if (msg.size() == 0) {
			this.apresentacaoalunos.save(apresentacaoaluno);
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
		ModelAndView mvw = new ModelAndView("CadastroApresentacaoAlunos");
		ApresentacaoAluno ap = new ApresentacaoAluno();
		ap.setId_apresentacao(id);
		mvw.addObject("turmas", turmas.findAllTurmas());
		mvw.addObject("apresentacaos", apresentacaos.findAll());
		mvw.addObject("apalunos", apresentacaoalunos.findAllAlunosByApresentacao(id));
		mvw.addObject("apresentacaoaluno", ap);
		return mvw;
	}

	@RequestMapping(params = { "id", "id_turma" })
	public ModelAndView novo(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "id_turma") Integer id_turma) {
		ModelAndView mvw = new ModelAndView("CadastroApresentacaoAlunos");
		ApresentacaoAluno ap = new ApresentacaoAluno();
		ap.setId_apresentacao(id);
		ap.setId_turma(id_turma);
		
		mvw.addObject("turmas", turmas.findAllTurmas());
		mvw.addObject("apresentacaos", apresentacaos.findAll());
		mvw.addObject("apalunos", apresentacaoalunos.findAllAlunosByApresentacao(id));
		mvw.addObject("apresentacaoaluno", ap);
		return mvw;
	}

	@RequestMapping(params = { "id", "mensagem_sucesso" })
	public ModelAndView novo(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "mensagem_sucesso") String mensagem_sucesso) {
		ModelAndView mvw = new ModelAndView("CadastroApresentacaoAlunos");
		ApresentacaoAluno ap = new ApresentacaoAluno();
		ap.setId_apresentacao(id);
		mvw.addObject("turmas", turmas.findAllTurmas());
		mvw.addObject("apresentacaos", apresentacaos.findAll());
		mvw.addObject("apalunos", apresentacaoalunos.findAllAlunosByApresentacao(id));
		mvw.addObject("mensagem_sucesso", mensagem_sucesso);
		mvw.addObject("apresentacaoaluno", ap);
		return mvw;
	}

	@RequestMapping(params = { "id", "id_apresentacao", "action" })
	public ModelAndView excluir2(@RequestParam(value = "id") long id,
			@RequestParam(value = "id_apresentacao") Integer id_apresentacao,
			@RequestParam(value = "action") String action) {
		if (action.equals("delete"))
			apresentacaoalunos.delete(id);
		ModelAndView mvw = novo(id_apresentacao);
		List<String> msg = new ArrayList<String>();
		msg.add("Aluno removido com sucesso!");
		mvw.addObject("mensagem_sucesso", msg);

		return mvw;
	}

	public ModelAndView redirect(Integer id) {
		ModelAndView mvw = new ModelAndView("redirect:/apresentacaoalunos/");
		mvw.addObject("id", id);
		return mvw;
	}
	
	@RequestMapping(params = {"id_turma" })
	public @ResponseBody List<TurmaAluno> getDataList(@RequestParam(value = "id_turma") Integer id_turma) {

	    return alunos.findAllAlunosByTurma(id_turma);
	}

}
