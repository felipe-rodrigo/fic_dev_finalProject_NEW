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

  @Column(name = "nome", nullable = false, length = 80)
  private String nome;

  @Column(name = "data_nascimento", nullable = false)
  private Date dataNascimento;

  @Column(name = "endereco", nullable = false, length = 80)
  private String endereco;

  @Column(name = "telefone", nullable = false, length = 20)
  private String telefone;

  @Column(name = "cartao_sus", nullable = false, length = 15)
  private String cartaoSus;
}
