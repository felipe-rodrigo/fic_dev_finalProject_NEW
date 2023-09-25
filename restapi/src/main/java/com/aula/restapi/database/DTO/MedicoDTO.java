package com.aula.restapi.database.DTO;

public class MedicoDTO {

    private Long idMedico;
    private String nome;
    private String crm;

    public MedicoDTO() {
    }

    public MedicoDTO(Long idMedico, String nome, String crm) {
        this.idMedico = idMedico;
        this.nome = nome;
        this.crm = crm;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}
