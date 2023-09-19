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

import com.aula.restapi.database.repository.PacienteRepository;
import com.aula.restapi.entity.Paciente;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {
  
   @Autowired
  private PacienteRepository repositorioPaciente;

    @GetMapping("/listar")
  public List<Paciente> listarPacientes() {
    return repositorioPaciente.findAll();
  }

  // MÉTODO PARA LISTAGEM ESPECÍFICA
  @GetMapping("/listar/{id}")
  public ResponseEntity<Paciente> listarPacientePorId(@PathVariable Long id) {

    Optional<Paciente> paciente = repositorioPaciente.findById(id);
    
    if (paciente.isPresent()) {
        return ResponseEntity.ok(paciente.get());
    } else {
        return ResponseEntity.notFound().build();
    }
  }

  // MÉTODO DE SALVAR
  @PostMapping("/adicionar")
  public void salvarPaciente(@RequestBody Paciente objPaciente) {
    repositorioPaciente.save(objPaciente);
  };

  // MÉTODO DE EDITAR
  @PutMapping("/editar/{id}")
  public void alterarPaciente(@RequestBody Paciente objPaciente) {
    if (objPaciente.getIdPaciente() > 0) {
      repositorioPaciente.save(objPaciente);
    }
  };

  // MÉTODO DE DELETAR
  @DeleteMapping("/deletar/{id}")
  public void deletarPaciente(@RequestBody Paciente objPaciente) {
    repositorioPaciente.delete(objPaciente);
  }

}
