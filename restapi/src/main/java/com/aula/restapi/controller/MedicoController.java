package com.aula.restapi.controller;

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

import com.aula.restapi.database.repository.MedicoRepository;
import com.aula.restapi.entity.Medico;


@RestController
@RequestMapping("/medico")
public class MedicoController {
  @Autowired
  private MedicoRepository repositorioMedico;

  // @GetMapping
  // public List<Medico> listarMedicos() {
  //   return repositorioMedico.findAll();
  // };

  // MÉTODO PARA LISTAGEM GERAL
  @GetMapping("/medicos")
  public List<Medico> listarMedicos() {
    return repositorioMedico.findAll();
  }

  // MÉTODO PARA LISTAGEM ESPECÍFICA
  @GetMapping("/medicos/{id}")
  public ResponseEntity<Medico> listarMedicoPorId(@PathVariable Long id) {

    Optional<Medico> medico = repositorioMedico.findById(id);
    
    if (medico.isPresent()) {
        return ResponseEntity.ok(medico.get());
    } else {
        return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public void salvarMedico(@RequestBody Medico objMedico) {
    repositorioMedico.save(objMedico);
  };

  @PutMapping
  public void alterarMedico(@RequestBody Medico objMedico) {
    if (objMedico.getIdMedico() > 0) {
      repositorioMedico.save(objMedico);
    }
  };

  @DeleteMapping 
  public void deletarMedico(@RequestBody Medico objMedico) {
    repositorioMedico.delete(objMedico);
  }
}

