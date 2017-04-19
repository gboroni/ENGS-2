package com.sicaa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sicaa.model.Apresentacao;

public interface Apresentacaos extends JpaRepository<Apresentacao, Long> {
	
	@Modifying
	@Query(value="select a.id as id, a.id_tema as id_tema,a.codigo as codigo, a.inicio as inicio, a.fim as fim, t.nome as nome_tema, a.avaliacoes as avaliacoes  from tb_apresentacao a join tb_tema t on t.id = a.id_tema", nativeQuery=true)
	List<Apresentacao> findAllApresentacaos();
	
	@Modifying
	@Query(value="select a.id as id, a.id_tema as id_tema, a.inicio as inicio, a.fim as fim , t.nome as nome_tema,a.codigo as codigo, (select  count(DISTINCT avaliador)  from Tb_Avaliacao where id_apresentacao = a.id ) as avaliacoes from Tb_Apresentacao a JOIN Tb_Tema t on a.id_tema = t.id where (select  count( DISTINCT avaliador)  from Tb_Avaliacao where id_apresentacao = a.id ) > 0", nativeQuery=true)
	List<Apresentacao> findAllApresentacaosAvaliadas();
	
	@Modifying
	@Query(value="select a.id as id, a.id_tema as id_tema,a.codigo as codigo, a.inicio as inicio, a.fim as fim, t.nome as nome_tema, a.avaliacoes as avaliacoes  from tb_apresentacao a join tb_tema t on t.id = a.id_tema where codigo = ?", nativeQuery=true)
	List<Apresentacao> findApresentacaoByCodigo(String codigo);
	
}
