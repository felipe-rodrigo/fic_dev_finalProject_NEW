// CLASSE RESPONSÁVEL POR INICIALIZAR 

package com.aula.restapi.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aula.restapi.entity.Contato;

public interface ContatoRepository extends JpaRepository<Contato,Long>{
  
}
