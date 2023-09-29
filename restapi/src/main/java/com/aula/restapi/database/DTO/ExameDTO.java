package com.aula.restapi.database.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ExameDTO {
    private Long idExame;
    private Long idMedico;
    private Long idPaciente;

    @JsonFormat(pattern = "yyyy-MM-dd") // Formato da data esperado
    private Date dataHoraExame;
    private String observacao;
    private String resultado;

    public ExameDTO() {
    }

    public Long getIdExame() {
        return idExame;
    }

    public void setIdExame(Long idExame) {
        this.idExame = idExame;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Date getDataHoraExame() {
        return dataHoraExame;
    }

    public void setDataHoraExame(Date dataHoraExame) {
        this.dataHoraExame = dataHoraExame;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
