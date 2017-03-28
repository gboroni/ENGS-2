package com.sicaa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sicaa.model.Turma;

public interface Turmas extends JpaRepository<Turma, Long> {
	
	@Modifying
	@Query(value="select t.id as id, t.codigo as codigo, d.id as id_disciplina, d.nome as nome_disciplina from tb_turma t inner join tb_disciplina d on t.id_disciplina = d.id", nativeQuery=true)
	List<Turma> findAllTurmas();
	
	@Modifying
	@Query(value="select t.id as id, t.codigo as codigo, d.id as id_disciplina, d.nome as nome_disciplina from tb_turma t inner join tb_disciplina d on t.id_disciplina = d.id", nativeQuery=true)
	List<Turma> findAll();
	
}
