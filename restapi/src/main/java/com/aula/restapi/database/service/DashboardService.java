package com.aula.restapi.database.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;

@Service
public class DashboardService {

    @Autowired
    private EntityManager entityManager;

    public List<Object[]> contarExamesPorMes() {
        String hql = "SELECT " +
             "CASE EXTRACT(MONTH FROM e.dataHoraExame) " +
             "   WHEN 1 THEN 'Janeiro' " +
             "   WHEN 2 THEN 'Fevereiro' " +
             "   WHEN 3 THEN 'Março' " +
             "   WHEN 4 THEN 'Abril' " +
             "   WHEN 5 THEN 'Maio' " +
             "   WHEN 6 THEN 'Junho' " +
             "   WHEN 7 THEN 'Julho' " +
             "   WHEN 8 THEN 'Agosto' " +
             "   WHEN 9 THEN 'Setembro' " +
             "   WHEN 10 THEN 'Outubro' " +
             "   WHEN 11 THEN 'Novembro' " +
             "   WHEN 12 THEN 'Dezembro' " +
             "END AS nome_mes, " +
             "EXTRACT(MONTH FROM e.dataHoraExame) AS mes, " +
             "COUNT(*) as total " +
             "FROM Exame e " +
             "GROUP BY nome_mes, mes " +
             "ORDER BY mes ASC";

                     
    
        Session session = entityManager.unwrap(Session.class);
        Query<Object[]> query = session.createQuery(hql);
        return query.getResultList();
    }
}
