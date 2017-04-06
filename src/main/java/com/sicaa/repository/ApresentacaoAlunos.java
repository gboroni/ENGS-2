package com.sicaa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sicaa.model.ApresentacaoAluno;

public interface ApresentacaoAlunos extends JpaRepository<ApresentacaoAluno, Long> {
	
	@Modifying
	@Query(value="select aa.id as id, aa.id_aluno as id_aluno, aa.id_apresentacao as id_apresentacao,aa.id_turma as id_turma, al.nome as nome_aluno, t.codigo as codigo_turmafrom Tb_apresentacao_aluno aa  join Tb_Aluno al on aa.id_aluno = al.id  join Tb_Turma t on aa.id_turma = t.id  join tb_apresentacao a on a.id = aa.id_apresentacao where aa.id_apresentacao = ?", nativeQuery=true)
	List<ApresentacaoAluno> findAllAlunosByApresentacao(Integer idApresentacao);
	
}
