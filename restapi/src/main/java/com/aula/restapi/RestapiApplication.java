package com.aula.restapi;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestapiApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(RestapiApplication.class, args);
        // Connection umaConexao = null;
        // try {
        //     Class.forName("org.postgresql.Driver");
        //     umaConexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/exames", "postgres", "123456");
        //     ResultSet resultClient = umaConexao.createStatement().executeQuery("SELECT * FROM CLIENT");
        //     while (resultClient.next()) {
        //         System.out.println("Nome: " + resultClient.getString("nome"));
        //     }
        // } catch (ClassNotFoundException notific) {
        //     System.out.println("Driver do banco de dados não encontrado.");
        // } catch (SQLException ex) {
        //     System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        // } finally {
        //     if (umaConexao != null) {
        //         umaConexao.close();
        //     }
        // }
    }
}
