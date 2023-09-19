package com.aula.restapi.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aula.restapi.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente,Long>{
  
}