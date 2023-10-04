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

import com.aula.restapi.database.DTO.MedicoDTO;
import com.aula.restapi.database.repository.MedicoRepository;
import com.aula.restapi.entity.Medico;


@RestController
@RequestMapping("/medicos")
public class MedicoController {
  @Autowired
  private MedicoRepository repositorioMedico;

  // @GetMapping
  // public List<Medico> listarMedicos() {
  //   return repositorioMedico.findAll();
  // };

  // MÉTODO PARA LISTAGEM GERAL
  @GetMapping("/listar")
  public List<Medico> listarMedicos() {
    return repositorioMedico.findAll();
  }

  // MÉTODO PARA LISTAGEM ESPECÍFICA
  @GetMapping("/listar/{id}")
  public ResponseEntity<Medico> listarMedicoPorId(@PathVariable Long id) {

    Optional<Medico> medico = repositorioMedico.findById(id);
    
    if (medico.isPresent()) {
        return ResponseEntity.ok(medico.get());
    } else {
        return ResponseEntity.notFound().build();
    }
  }

  // MÉTODO DE SALVAR
  // @PostMapping("/adicionar")
  // public void salvarMedico(@RequestBody Medico objMedico) {
  //   repositorioMedico.save(objMedico);
  // };

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarMedico(@RequestBody MedicoDTO medicoDTO) {
        try {
            // Criar um objeto Medico a partir dos dados do DTO
            Medico novoMedico = new Medico();
            novoMedico.setIdMedico(medicoDTO.getIdMedico());
            novoMedico.setNome(medicoDTO.getNome());
            novoMedico.setCrm(medicoDTO.getCrm());

            // Salvar o novo médico no banco de dados
            Medico medicoSalvo = repositorioMedico.save(novoMedico);

            return ResponseEntity.ok("Médico adicionado com sucesso. ID do médico: " + medicoSalvo.getIdMedico());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao adicionar o médico: " + e.getMessage());
        }
    }

  // MÉTODO DE EDITAR
  @PutMapping("/editar/{id}")
  public void editarMedico(@PathVariable Long id, @RequestBody Medico medicoDTO) {
    Optional<Medico> resultado = repositorioMedico.findById(id);
    //System.out.println(medicoDTO.getNome());
    if (resultado.isPresent()) {
      Medico medico = resultado.get();
      //System.out.println(medico.getNome());
      if (medicoDTO.getNome() != null) {
      medico.setNome(medicoDTO.getNome());
      }
      if (medicoDTO.getCrm() != null) {
        medico.setCrm(medicoDTO.getCrm());
      }
      repositorioMedico.save(medico);
    }
    // if (medicoDTO.getIdMedico() > 0) {
    //   repositorioMedico.save(medicoDTO);
    // }
  };

  // MÉTODO DE DELETAR
  // @DeleteMapping("/deletar/{id}")
  // public void deletarMedico(@RequestBody Medico objMedico) {
  //   repositorioMedico.delete(objMedico);
  // }

  @DeleteMapping("/deletar/{id}")
public ResponseEntity<String> deletarMedico(@PathVariable Long id) {
    try {
        // Verificar se o médico com o ID especificado existe
        Optional<Medico> medicoOpt = repositorioMedico.findById(id);
        if (!medicoOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        // Verificar permissões de autorização (opcional)

        // Excluir o médico
        repositorioMedico.deleteById(id);
        
        return ResponseEntity.noContent().build(); // Retorna "204 No Content" em caso de sucesso.
    } catch (Exception e) {
        // Tratar exceções, por exemplo, problemas no banco de dados.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao excluir o médico: " + e.getMessage());
    }
}

}

