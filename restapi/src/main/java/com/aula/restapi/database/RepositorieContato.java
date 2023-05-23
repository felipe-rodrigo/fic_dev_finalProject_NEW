// CLASSE RESPONSÁVEL POR INICIALIZAR 

package com.aula.restapi.database;

import com.aula.restapi.entidade.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorieContato extends JpaRepository<Contato,Long>{
  
}
