package com.sicaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicaa.model.ApresentacaoCriterio;

public interface ApresentacaoCriterios extends JpaRepository<ApresentacaoCriterio, Long> {
	
//	@Modifying
//	@Query(value="select t.id as id, t.codigo as codigo, d.id as id_disciplina, d.nome as nome_disciplina from tb_turmas t inner join tb_disciplinas d on t.id_disciplina = d.id", nativeQuery=true)
//	List<Turma> findAllTurmas();
	
}
