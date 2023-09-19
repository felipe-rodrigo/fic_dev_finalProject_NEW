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

import com.aula.restapi.database.repository.UsuarioRepository;
import com.aula.restapi.entity.Usuario;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
  
   @Autowired
  private UsuarioRepository repositorioUsuario;

      @GetMapping("/listar")
  public List<Usuario> listarUsuarios() {
    return repositorioUsuario.findAll();
  }

  // MÉTODO PARA LISTAGEM ESPECÍFICA
  @GetMapping("/listar/{id}")
  public ResponseEntity<Usuario> listarUsuarioPorId(@PathVariable Long id) {

    Optional<Usuario> usuario = repositorioUsuario.findById(id);
    
    if (usuario.isPresent()) {
        return ResponseEntity.ok(usuario.get());
    } else {
        return ResponseEntity.notFound().build();
    }
  }

  // MÉTODO DE SALVAR
  @PostMapping("/adicionar")
  public void salvarUsuario(@RequestBody Usuario objUsuario) {
    repositorioUsuario.save(objUsuario);
  };

  // MÉTODO DE EDITAR
  @PutMapping("/editar/{id}")
  public void alterarUsuario(@RequestBody Usuario objUsuario) {
    if (objUsuario.getIdUsuario() > 0) {
      repositorioUsuario.save(objUsuario);
    }
  };

  // MÉTODO DE DELETAR
  @DeleteMapping("/deletar/{id}")
  public void deletarUsuario(@RequestBody Usuario objUsuario) {
    repositorioUsuario.delete(objUsuario);
  }

}
