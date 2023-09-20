package com.aula.restapi.database.controller;

import java.net.URI;

// CLASSE RESPONSÁVEL POR GERENCIAR AS REQUISIÇÕES

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aula.restapi.database.repository.ExameRepository;
import com.aula.restapi.entity.Exame;
import com.aula.restapi.entity.Medico;


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
  // @PostMapping("/adicionar")
  // public void salvarExame(@RequestBody Exame objExame) {
  //   repositorioExame.save(objExame);
  // };

  @PostMapping("/adicionar")
@ResponseStatus(HttpStatus.CREATED)
public ResponseEntity<String> salvarExame(@Validated @RequestBody Exame objExame) {
    try {
        // Verifica se os dados do exame são válidos com base nas anotações de validação.

        // Salva o exame
        Exame novoExame = repositorioExame.save(objExame);

        // Retorna o ID do exame criado no cabeçalho de resposta
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/adicionar/" + novoExame.getIdExame());

        return ResponseEntity.created(new URI("/adicionar/" + novoExame.getIdExame()))
                .headers(headers)
                .body("Exame criado com sucesso. ID: " + novoExame.getIdExame());
    } catch (Exception e) {
        // Trata exceções, por exemplo, problemas no banco de dados.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao salvar o exame: " + e.getMessage());
    }
}


  // MÉTODO DE EDITAR
  // @PutMapping("/editar/{id}")
  // public void alterarExame(@RequestBody Exame objExame) {
  //   if (objExame.getIdExame() > 0) {
  //     repositorioExame.save(objExame);
  //   }
  // };

  @PutMapping("/editar/{id}")
public ResponseEntity<String> alterarExame(@PathVariable Long id, @Validated @RequestBody Exame objExame) {
    try {
        // Verifica se o ID do exame é maior que zero
        if (id <= 0) {
            return ResponseEntity.badRequest().body("ID de exame inválido.");
        }

        // Verifica se o exame com o ID especificado existe
        Optional<Exame> exameOpt = repositorioExame.findById(id);
        if (!exameOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        // Atualiza os campos do exame
        Exame exameExistente = exameOpt.get();
        // Atualize os campos necessários de 'exameExistente' com os dados de 'objExame'

        // Salva as alterações
        Exame exameAtualizado = repositorioExame.save(exameExistente);

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
