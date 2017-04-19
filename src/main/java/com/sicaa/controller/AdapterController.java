package com.sicaa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicaa.model.Apresentacao;
import com.sicaa.model.ApresentacaoAluno;
import com.sicaa.model.ApresentacaoCriterio;
import com.sicaa.model.Avaliacao;
import com.sicaa.model.Response;
import com.sicaa.model.ResponseApresentacao;
import com.sicaa.repository.Alunos;
import com.sicaa.repository.ApresentacaoAlunos;
import com.sicaa.repository.ApresentacaoCriterios;
import com.sicaa.repository.Apresentacaos;
import com.sicaa.repository.Avaliacaos;

@RestController
@RequestMapping("/api")
public class AdapterController {

	@Autowired
	private Alunos alunos;

	@Autowired
	private ApresentacaoCriterios apresentacaocriterios;

	@Autowired
	private ApresentacaoAlunos apresentacaoalunos;
	
	@Autowired
	private Avaliacaos avaliacaos;
	
	@Autowired
	private Apresentacaos apresentacaos;

	@GetMapping("/login")
	public Response listar() {

		return new Response(0, "", alunos.findAll());
	}

	@GetMapping("/apresentacao/{id}")
	public ResponseApresentacao criteriosApresentacao(@PathVariable(value = "id") String id) {
		List<ApresentacaoCriterio> apresentacaoCriterio = new ArrayList<ApresentacaoCriterio>();
		
		List<Apresentacao> ap = apresentacaos.findApresentacaoByCodigo(id);
		
		if (ap != null && !ap.isEmpty()){
			Apresentacao apresentacao = ap.get(0);
			apresentacaoCriterio = apresentacaocriterios.findAllCriteriosByApresentacao((int)apresentacao.getId());

			List<ApresentacaoAluno> apresentacaoAluno = new ArrayList<ApresentacaoAluno>();
			apresentacaoAluno = apresentacaoalunos.findAllAlunosByApresentacao((int)apresentacao.getId());
			return new ResponseApresentacao(0, "", apresentacaoAluno, apresentacaoCriterio);
			
		}else {
			return new ResponseApresentacao(1, "Apresentação não encontrada", null, null);
			
		}
		
	}

	@GetMapping("/avaliacao/{criterios}/{notas}/{aluno}/{avaliador}/{apresentacao}")
	public ResponseApresentacao avaliar(@PathVariable(value = "criterios") String criterios,
			@PathVariable(value = "notas") String notas, @PathVariable(value = "aluno") Integer aluno,
			@PathVariable(value = "avaliador") String avaliador,
			@PathVariable(value = "apresentacao") Integer apresentacao) {
		
		Avaliacao avaliacao = new Avaliacao();

		String[] criterioArr = criterios.split(",");
		String[] notasArr = notas.split(",");
		for (int cont = 0; cont<criterioArr.length; cont++) {
//			System.out.println(Double.valueOf(notasArr[cont]));
//			System.out.println(Integer.valueOf(criterioArr[cont]));
			avaliacao = new Avaliacao();
			avaliacao.setId_apresentacao_criterio(Integer.valueOf(criterioArr[cont]));
			avaliacao.setData(new Date());
			avaliacao.setAvaliador(avaliador);
			avaliacao.setNota(Double.valueOf(notasArr[cont]));
			avaliacao.setId_apresentacao_aluno(aluno);
			avaliacao.setId_apresentacao(apresentacao);
			avaliacaos.save(avaliacao);
		}
		
		return new ResponseApresentacao(0, "Avaliacao feita com sucesso!", "", "");
	}

}
