package com.sicaa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sicaa.model.Aluno;

public interface Alunos extends JpaRepository<Aluno, Long> {
	
	@Modifying
	@Query(value="select * from tb_aluno where matricula = ?", nativeQuery=true)
	List<Aluno> findAlunosByMatricula(String matricula);
	
}
