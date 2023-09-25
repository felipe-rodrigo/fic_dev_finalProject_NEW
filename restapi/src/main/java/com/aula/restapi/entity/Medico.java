package com.aula.restapi.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Medico {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_medico")
  private Long idMedico;

  @Column(name = "nome", nullable = false, length = 80)
  private String nome;

  @Column(name = "crm", nullable = false, length = 15)
  private String crm;

  public Medico(){
  }

  @JsonCreator
  public Medico(@JsonProperty("idMedico") Long idMedico, @JsonProperty("nome") String nome, @JsonProperty("crm") String crm){

    this.idMedico = idMedico;
    this.nome = nome;
    this.crm = crm;

  }

}

