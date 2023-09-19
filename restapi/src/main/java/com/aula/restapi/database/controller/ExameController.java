package com.aula.restapi.database.controller;

// CLASSE RESPONSÁVEL POR GERENCIAR AS REQUISIÇÕES

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aula.restapi.database.repository.ExameRepository;
import com.aula.restapi.entity.Exame;


@RestController
@RequestMapping("/exames")
public class ExameController {
  
   @Autowired
  private ExameRepository repositorioExame;

  // MÉTODO PARA LISTAGEM GERAL
  @GetMapping("/listar")
  public List<Exame> listarExames() {
    return repositorioExame.findAll();
  }

  // MÉTODO PARA LISTAGEM ESPECÍFICA
  @GetMapping("/listar/{id}")
  public ResponseEntity<Exame> listarExamePorId(@PathVariable Long id) {

    Optional<Exame> exame = repositorioExame.findById(id);
    
    if (exame.isPresent()) {
        return ResponseEntity.ok(exame.get());
    } else {
        return ResponseEntity.notFound().build();
    }
  }

  // MÉTODO DE SALVAR
  @PostMapping("/adicionar")
  public void salvarExame(@RequestBody Exame objExame) {
    repositorioExame.save(objExame);
  };

  // MÉTODO DE EDITAR
  @PutMapping("/editar/{id}")
  public void alterarExame(@RequestBody Exame objExame) {
    if (objExame.getIdExame() > 0) {
      repositorioExame.save(objExame);
    }
  };

  // MÉTODO DE DELETAR
  @DeleteMapping("/deletar/{id}")
  public void deletarExame(@RequestBody Exame objExame) {
    repositorioExame.delete(objExame);
  }

}
