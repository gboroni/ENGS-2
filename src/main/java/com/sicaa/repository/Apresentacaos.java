package com.sicaa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sicaa.model.Apresentacao;

public interface Apresentacaos extends JpaRepository<Apresentacao, Long> {
	
	@Modifying
	@Query(value="select a.id as id, a.id_tema as id_tema,a.status as status, a.inicio as inicio, a.fim as fim, t.nome as nome_tema  from tb_apresentacao a join tb_tema t on t.id = a.id_tema", nativeQuery=true)
	List<Apresentacao> findAllApresentacaos();
	
}
