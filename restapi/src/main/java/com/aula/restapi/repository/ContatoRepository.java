// CLASSE RESPONSÁVEL POR INICIALIZAR 

package com.aula.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aula.restapi.domain.Contato;

public interface ContatoRepository extends JpaRepository<Contato,Long>{
  
}
