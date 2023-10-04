package com.aula.restapi.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aula.restapi.database.service.DashboardService;


@RestController
@RequestMapping("/dashboard")
public class DashboardController {

  @Autowired
  private DashboardService dashboardService;

  @GetMapping("/exames")
  public List<Object[]> contarExamesPorMes() {
      return dashboardService.contarExamesPorMes();
  }
}
