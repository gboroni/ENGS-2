package com.sicaa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sicaa.model.ApresentacaoCriterio;

public interface ApresentacaoCriterios extends JpaRepository<ApresentacaoCriterio, Long> {
	
	@Modifying
	@Query(value="select ac.id as id, ac.id_criterio  as id_criterio, ac.id_apresentacao as id_apresentacao, c.descricao as descricao_criterio, c.peso as peso_criterio  from Tb_apresentacao_criterio ac inner join Tb_Criterio c on ac.id_criterio = c.id where ac.id = ?", nativeQuery=true)
	List<ApresentacaoCriterio> findAllCriteriosByApresentacao(Integer idApresentacao);
	
}
