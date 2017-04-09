package com.sicaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicaa.model.Aluno;
import com.sicaa.model.Docente;

public interface Docentes extends JpaRepository<Docente, Long> {

}
