package com.sicaa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sicaa.model.Avaliacao;

public interface Avaliacaos extends JpaRepository<Avaliacao, Long> {
	
	@Modifying
	@Query(value="select  1 as id_apresentacao, null as data, 1 as id_apresentacao_aluno,  1 as id_apresentacao_criterio, 1 as id, av.avaliador, sum(av.nota*c.peso)/sum(c.peso) as nota from tb_avaliacao av join tb_apresentacao_criterio ac on ac.id = av.id_apresentacao_criterio join tb_criterio c on ac.id_criterio = c.id where av.id_apresentacao_aluno = ? group by  av.avaliador", nativeQuery=true)
	List<Avaliacao> findAllAvaliadoresByApresentacao(Integer id_apresentacao_aluno);
	
	
}
