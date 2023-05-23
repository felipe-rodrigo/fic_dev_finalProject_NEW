package com.aula.restapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestapiApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(RestapiApplication.class, args);
		Connection umaConexao = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			umaConexao = DriverManager.getConnection("jdbc:mysql://localhost/contato", "user", "password");
			ResultSet resultClient = umaConexao.createStatement().executeQuery("SELECT * FROM CLIENT");
			while (resultClient.next()) {
				System.out.println("Nome: " + resultClient.getString("nome"));
			}
		} catch (ClassNotFoundException notific) {
			System.out.println("Driver do banco de dados não encontrado.");
		}  catch (SQLException ex) {
			System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
		} finally {
			if (umaConexao != null) {
				umaConexao.close();
			}
		}
	}

}
