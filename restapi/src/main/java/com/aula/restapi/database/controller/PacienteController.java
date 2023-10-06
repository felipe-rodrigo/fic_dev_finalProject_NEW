package com.aula.restapi.database.controller;

// CLASSE RESPONSÁVEL POR GERENCIAR AS REQUISIÇÕES

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aula.restapi.database.DTO.PacienteDTO;
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
  // @PostMapping("/adicionar")
  // public void salvarPaciente(@RequestBody Paciente objPaciente) {
  //   repositorioPaciente.save(objPaciente);
  // };

     @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarPaciente(@RequestBody PacienteDTO pacienteDTO) {
        try {
            // Criar um objeto Paciente a partir dos dados do DTO
            Paciente novoPaciente = new Paciente();
            novoPaciente.setIdPaciente(pacienteDTO.getIdPaciente());
            novoPaciente.setNome(pacienteDTO.getNome());
            novoPaciente.setDataNascimento(pacienteDTO.getDataNascimento());
            novoPaciente.setEndereco(pacienteDTO.getEndereco());
            novoPaciente.setTelefone(pacienteDTO.getTelefone());
            novoPaciente.setCartaoSus(pacienteDTO.getCartaoSus());

            // Salvar o novo paciente no banco de dados
            Paciente pacienteSalvo = repositorioPaciente.save(novoPaciente);

            return ResponseEntity.ok("Paciente adicionado com sucesso. ID do paciente: " + pacienteSalvo.getIdPaciente());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao adicionar o paciente: " + e.getMessage());
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<Paciente> editarPaciente(@RequestBody PacienteDTO objPaciente) {
      long idPaciente = objPaciente.getIdPaciente();
      Optional<Paciente> pacienteObj = repositorioPaciente.findById(idPaciente);
  
      if (pacienteObj.isPresent()) {
        Paciente paciente = pacienteObj.get();

        paciente.setNome(objPaciente.getNome());
        paciente.setDataNascimento(objPaciente.getDataNascimento());
        paciente.setEndereco(objPaciente.getEndereco());
        paciente.setTelefone(objPaciente.getTelefone());
        paciente.setCartaoSus(objPaciente.getCartaoSus());

        repositorioPaciente.save(paciente);
        return ResponseEntity.ok(paciente);
      } else {
          return ResponseEntity.notFound().build();
      }
    };

      @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarPaciente(@PathVariable Long id) {
      try {
          Optional<Paciente> opt = repositorioPaciente.findById(id);
          if (!opt.isPresent()) {
              return ResponseEntity.notFound().build();
          }
          
          repositorioPaciente.deleteById(id);
          
          return ResponseEntity.noContent().build(); 
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body("Erro ao excluir o médico: " + e.getMessage());
      }
    }

}
