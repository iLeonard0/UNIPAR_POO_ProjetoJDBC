package br.unipar.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private static final String url = "jdbc:postgresql://localhost:5432/Exemplo1";
    private static final String user = "postgres";
    private static final String password = "admin123";

    public static void main(String[] args) {
        criarTabelaUsuario();
    }

    public static Connection connection() throws SQLException {

        return DriverManager.getConnection(url,user,password);
    }

    public static void criarTabelaUsuario() {
        try {
            Connection conn = connection();

            Statement statement = conn.createStatement();
            String sql = " CREATE TABLE IF NOT EXISTS usuario ("
                    + "codigo SERIAL PRIMARY KEY,"
                    + "username VARCHAR(50) NOT NULL UNIQUE,"
                    + "password VARCHAR(300) NOT NULL,"
                    + "nome VARCHAR(50) NOT NULL,"
                    + "nascimento DATE"
                    + ");";

            statement.executeUpdate(sql);

            System.out.println();
            System.out.println("Tabela criada com sucesso");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


}