package com.aula.restapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.Data;

@Entity
@Data
public class Paciente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_paciente")
  private Long idPaciente;

  @Column(nullable = false, length = 80)
  private String nome;

  @Column(nullable = false)
  private Date dataNascimento;

  @Column(nullable = false, length = 80)
  private String endereco;

  @Column(nullable = false, length = 20)
  private String telefone;

  @Column(nullable = false, length = 15)
  private String cartaoSus;
}
