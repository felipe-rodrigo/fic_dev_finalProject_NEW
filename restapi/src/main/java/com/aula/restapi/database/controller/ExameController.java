package com.aula.restapi.database.controller;

// import java.net.URI;

// CLASSE RESPONSÁVEL POR GERENCIAR AS REQUISIÇÕES

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aula.restapi.database.DTO.ExameDTO;
import com.aula.restapi.database.repository.ExameRepository;
import com.aula.restapi.database.repository.MedicoRepository;
import com.aula.restapi.database.repository.PacienteRepository;
import com.aula.restapi.entity.Exame;
import com.aula.restapi.entity.Medico;
import com.aula.restapi.entity.Paciente;


@RestController
@RequestMapping("/exames")
public class ExameController {
  
  @Autowired
  private ExameRepository repositorioExame;

  @Autowired
  private MedicoRepository medicoRepository;

  @Autowired
  private PacienteRepository pacienteRepository;

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
  
 @PostMapping("/adicionar")
public ResponseEntity<String> adicionarExame(@RequestBody ExameDTO exameDTO) {
    try {
        // Criar um objeto Exame a partir dos dados do DTO
        Exame novoExame = new Exame();
        novoExame.setIdExame(exameDTO.getIdExame());

        // Configurar o objeto Medico e Paciente com base nos IDs fornecidos no DTO
        Medico medico = new Medico();
        medico.setIdMedico(exameDTO.getIdMedico());
        novoExame.setMedico(medico);

        Paciente paciente = new Paciente();
        paciente.setIdPaciente(exameDTO.getIdPaciente());
        novoExame.setPaciente(paciente);

        novoExame.setDataHoraExame(exameDTO.getDataHoraExame());
        novoExame.setResultado(exameDTO.getObservacao());
        novoExame.setResultado(exameDTO.getResultado());

        // Salvar o novo exame no banco de dados
        Exame exameSalvo = repositorioExame.save(novoExame);

        return ResponseEntity.ok("Exame adicionado com sucesso. ID do exame: " + exameSalvo.getIdExame());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao adicionar o exame: " + e.getMessage());
    }
}

  // MÉTODO DE EDITAR
  // @PutMapping("/editar/{id}")
  // public void alterarExame(@RequestBody Exame objExame) {
  //   if (objExame.getIdExame() > 0) {
  //     repositorioExame.save(objExame);
  //   }
  // };

@PutMapping("/editar")
public ResponseEntity<String> alterarExame(@Validated @RequestBody ExameDTO objExame) {
    try {
        if (objExame.getIdExame() <= 0) {
            return ResponseEntity.badRequest().body("ID de exame inválido.");
        }

        Optional<Exame> exameOpt = repositorioExame.findById(objExame.getIdExame());
        if (!exameOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Medico> medicoOpt = medicoRepository.findById(objExame.getIdMedico());
        if (!medicoOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Paciente> pacienteOpt = pacienteRepository.findById(objExame.getIdPaciente());
        if (!pacienteOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        Exame exame = exameOpt.get();
        
        Medico medico = medicoOpt.get();
        Paciente paciente = pacienteOpt.get();

        exame.setMedico(medico);
        exame.setPaciente(paciente);
        exame.setDataHoraExame(objExame.getDataHoraExame());
        exame.setObservacao(objExame.getObservacao());
        exame.setResultado(objExame.getResultado());

        Exame exameAtualizado = repositorioExame.save(exame);

        return ResponseEntity.ok("Exame atualizado com sucesso. ID: " + exameAtualizado.getIdExame());
    } catch (Exception e) {
        // Trata exceções, por exemplo, problemas no banco de dados.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao atualizar o exame: " + e.getMessage());
    }
}


  // MÉTODO DE DELETAR
    @DeleteMapping("/deletar/{id}")
public ResponseEntity<String> deletarExame(@PathVariable Long id) {
    try {
        // Verificar se o médico com o ID especificado existe
        Optional<Exame> exameOpt = repositorioExame.findById(id);
        if (!exameOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        repositorioExame.deleteById(id);
        
        return ResponseEntity.noContent().build(); // Retorna "204 No Content" em caso de sucesso.
    } catch (Exception e) {
        // Tratar exceções, por exemplo, problemas no banco de dados.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao excluir o médico: " + e.getMessage());
    }
}


}
