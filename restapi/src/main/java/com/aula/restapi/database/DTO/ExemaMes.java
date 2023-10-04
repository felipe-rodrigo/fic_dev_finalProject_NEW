package com.aula.restapi.database.DTO;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ExemaMes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dataHoraExame;


    public Date getDataHoraExame() {
      return dataHoraExame;
    }

    public void setDataHoraExame(Date dataHoraExame) {
        this.dataHoraExame = dataHoraExame;
    }
}
