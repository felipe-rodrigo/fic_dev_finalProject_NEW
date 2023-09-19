package com.aula.restapi.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aula.restapi.entity.Exame;

public interface ExameRepository extends JpaRepository<Exame,Long>{
  
}