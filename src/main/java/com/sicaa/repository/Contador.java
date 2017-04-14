package com.sicaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sicaa.model.Cont;

public interface Contador extends JpaRepository<Cont, Long> {
	
	@Query(value="select count(*) as cont from tb_aluno a join tb_turma_aluno ta on a.id = ta.id_aluno where ta.id_turma = ?1 and ta.id_aluno = ?2", nativeQuery=true)
	Cont findAlunoByTurma(Integer idTurma, Integer idAluno);
	
	@Query(value="SELECT count(*) as cont from tb_criterio ct join tb_apresentacao_criterio ac on ct.id = ac.id_criterio WHERE ac.id_apresentacao = ?1 and ac.id_criterio = ?2", nativeQuery=true)
	Cont findApresentacaoCriterio(Integer id_apresentacao, Integer id_criterio );
	
	@Query(value="SELECT count(*) as cont from tb_aluno a join tb_apresentacao_aluno al on al.id_aluno = a.id WHERE al.id_apresentacao = ?1 and al.id_aluno = ?2", nativeQuery=true)
	Cont findApresentacaoAluno(Integer id_apresentacao, Integer id_aluno );
}
