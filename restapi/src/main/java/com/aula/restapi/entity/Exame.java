package com.aula.restapi.entity;

import lombok.Data;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Data
@Entity
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exame")
    private Long idExame;

    @ManyToOne
    @JoinColumn(name = "fk_id_medico", referencedColumnName = "id_medico")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "fk_id_paciente", referencedColumnName = "id_paciente")
    private Paciente paciente;

    @Column(name = "data_hora_exame", nullable = false)
    private Date dataHoraExame;

    @Column(name = "obs", nullable = false)
    private String observacao;

    @Column(name = "resultado", nullable = false)
    private String resultado;
}
