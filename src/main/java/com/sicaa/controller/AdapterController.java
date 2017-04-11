package com.sicaa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicaa.model.ApresentacaoAluno;
import com.sicaa.model.ApresentacaoCriterio;
import com.sicaa.model.Response;
import com.sicaa.model.ResponseApresentacao;
import com.sicaa.repository.Alunos;
import com.sicaa.repository.ApresentacaoAlunos;
import com.sicaa.repository.ApresentacaoCriterios;

@RestController
@RequestMapping("/api")
public class AdapterController {

	@Autowired
	private Alunos alunos;

	@Autowired
	private ApresentacaoCriterios apresentacaocriterios;

	@Autowired
	private ApresentacaoAlunos apresentacaoalunos;

	@GetMapping("/login")
	public Response listar() {

		return new Response(0, "", alunos.findAll());
	}

	@GetMapping("/apresentacao/{id}")
	public ResponseApresentacao criteriosApresentacao(@PathVariable(value = "id") Integer id) {
		List<ApresentacaoCriterio> apresentacaoCriterio = new ArrayList<ApresentacaoCriterio>();
		apresentacaoCriterio = apresentacaocriterios.findAllCriteriosByApresentacao(id);

		List<ApresentacaoAluno> apresentacaoAluno = new ArrayList<ApresentacaoAluno>();
		apresentacaoAluno = apresentacaoalunos.findAllAlunosByApresentacao(id);

		return new ResponseApresentacao(0, "", apresentacaoAluno, apresentacaoCriterio);
	}

	@GetMapping("/avaliacao/{criterios}/{notas}/{aluno}/{avaliador}/{apresentacao}")
	public ResponseApresentacao avaliar(@PathVariable(value = "criterios") String criterios,
			@PathVariable(value = "notas") String notas, @PathVariable(value = "aluno") Integer aluno,
			@PathVariable(value = "avaliador") String avaliador,
			@PathVariable(value = "apresentacao") Integer apresentacao) {

		String[] criterioArr = criterios.split(",");
		for (String string : criterioArr) {
			System.out.println(string);
		}

		String[] notasArr = notas.split(",");
		for (String string : notasArr) {
			System.out.println(string);
		}

		return new ResponseApresentacao(0, "", "", "");
	}

}
