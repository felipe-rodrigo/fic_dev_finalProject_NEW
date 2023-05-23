// CLASSE DE DEFINIÇÃO DA ENTIDADE

package com.aula.restapi.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Contato {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String nome;
  @Column(nullable = false)
  private String telefone;
  @Column(nullable = false)
  private String email;
  private String urlAvatar;

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public String getTelefone() {
    return telefone;
  }
  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getUrlAvatar() {
    return urlAvatar;
  }
  public void setUrlAvatar(String urlAvatar) {
    this.urlAvatar = urlAvatar;
  }


}



// @Entity
// public class Caneta {
//   @Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
//   public int id;

//   @Column(nullable = false)
//   public String modelo;
  
//   @Column(nullable = false)
//   private float ponta;


//   public String getModelo() {
//     return modelo;
//   }

//   public void setModelo(String m) {
//     this.modelo = m;
//   }

//   public String getPonta() {
//     return this.ponta;
//   }

//   public void setPonta(String p) {
//     this.ponta = this.p;
//   }

//   public void status() {
//     System.out.println("SOBRE A CANETA");
//     System.out.println("Modelo: " + this.modelo);
//     System.out.println("Ponta: " + this.ponta);
//   }
// }