package com.sicaa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sicaa.model.TurmaAluno;

public interface TurmaAlunos extends JpaRepository<TurmaAluno, Long> {
	
	@Modifying
	@Query(value="select ta.id as id, ta.id_aluno as id_aluno, ta.id_turma as id_turma, t.codigo as codigo_turma, d.nome as nome_disciplina, a.nome as nome_aluno, a.matricula as matricula_aluno from tb_turma_aluno ta join tb_aluno a on a.id = ta.id_aluno join tb_turma t on t.id = ta.id_turma join tb_disciplina d on t.id_disciplina = d.id where t.id = ?", nativeQuery=true)
	List<TurmaAluno> findAllAlunosByTurma(Integer idTurma);
	
}
