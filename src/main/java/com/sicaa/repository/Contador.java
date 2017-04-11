package com.sicaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sicaa.model.Cont;

public interface Contador extends JpaRepository<Cont, Long> {
	
	@Query(value="select count(*) as cont from tb_aluno a join tb_turma_aluno ta on a.id = ta.id_aluno where ta.id_turma = ?1 and ta.id_aluno = ?2", nativeQuery=true)
	Cont findAlunoByTurma(Integer idTurma, Integer idAluno);
	
}