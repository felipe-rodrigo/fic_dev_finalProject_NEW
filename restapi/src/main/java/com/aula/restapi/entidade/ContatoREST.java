// CLASSE RESPONSÁVEL POR GERENCIAR AS REQUISIÇÕES

package com.aula.restapi.entidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aula.restapi.database.RepositorieContato;


@RestController
@RequestMapping("/contato")
public class ContatoREST {
  @Autowired
  private RepositorieContato repositorioInjetado;

  @GetMapping
  public List<Contato> listar() {
    return repositorioInjetado.findAll();
  };

  @PostMapping
  public void salvar(@RequestBody Contato contato) {
    repositorioInjetado.save(contato);
  };

  @PutMapping
  public void alterar(@RequestBody Contato contato) {
    if (contato.getId() > 0) {
      repositorioInjetado.save(contato);
    }
  };

  @DeleteMapping 
  public void excluir(@RequestBody Contato contato) {
    repositorioInjetado.delete(contato);
  }
}
