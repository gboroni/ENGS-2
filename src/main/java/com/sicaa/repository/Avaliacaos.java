package com.sicaa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sicaa.model.Avaliacao;

public interface Avaliacaos extends JpaRepository<Avaliacao, Long> {
	
	@Modifying
	@Query(value="select 1 as peso_criterio,'' as descricao_criterio, 1 as id_apresentacao, null as data, av.id_apresentacao_aluno as id_apresentacao_aluno,  1 as id_apresentacao_criterio, 1 as id, av.avaliador, sum(av.nota*c.peso)/sum(c.peso) as nota from tb_avaliacao av join tb_apresentacao_criterio ac on ac.id = av.id_apresentacao_criterio join tb_criterio c on ac.id_criterio = c.id where av.id_apresentacao_aluno = ? group by  av.avaliador, av.id_apresentacao_aluno", nativeQuery=true)
	List<Avaliacao> findAllAvaliadoresByApresentacao(Integer id_apresentacao_aluno);
	
	@Modifying
	@Query(value="select a.id,a.id_apresentacao,a.id_apresentacao_aluno,a.id_apresentacao_criterio,a.nota,a.avaliador,a.data,c.descricao as descricao_criterio, c.peso as peso_criterio from tb_avaliacao a join tb_apresentacao_criterio ac on a.id_apresentacao_criterio = ac.id join tb_criterio c on c.id = ac.id_criterio where id_apresentacao_aluno = :id_apresentacao_aluno and a.avaliador like CAST(:avaliador AS VARCHAR)", nativeQuery=true)
	List<Avaliacao> findDetalheAvaliacaoAluno(@Param("id_apresentacao_aluno") Integer id_apresentacao_aluno,@Param("avaliador") String avaliador);
	
	
}
