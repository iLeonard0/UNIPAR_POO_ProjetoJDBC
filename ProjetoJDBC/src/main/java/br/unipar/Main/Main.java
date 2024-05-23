package br.unipar.Main;

import java.sql.*;

public class Main {

    private static final String url = "jdbc:postgresql://localhost:5432/Exemplo1";
    private static final String user = "postgres";
    private static final String password = "admin123";

    public static void main(String[] args) {
        criarTabelaUsuario();
        InserirUsuario("Taffe2", "12345", "Fabio", "1890-01-01");
        listarTodosUsuarios();
    }


    public static Connection connection() throws SQLException {

        return DriverManager.getConnection(url,user,password);
    }

    public static void criarTabelaUsuario() {
        try {
            Connection conn = connection();

            Statement statement = conn.createStatement();
            String sql = " CREATE TABLE IF NOT EXISTS usuarios ("
                    + "codigo SERIAL PRIMARY KEY,"
                    + "username VARCHAR(50) NOT NULL UNIQUE,"
                    + "password VARCHAR(300) NOT NULL,"
                    + "nome VARCHAR(50) NOT NULL,"
                    + "nascimento DATE"
                    + ");";

            statement.executeUpdate(sql);

            System.out.println();
            System.out.println("Tabela criada com sucesso!");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void InserirUsuario(String username, String password, String nome, String nascimento) {
        try {
            //Abre conexão
            Connection conn = connection();

            //Prepara a execução de um SQL
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT into usuarios (username, password, nome, nascimento)"
                        + "VALUES (?,?,?,?)"

                    );
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, nome);
            preparedStatement.setDate(4, java.sql.Date.valueOf(nascimento));

            preparedStatement.executeUpdate();

            System.out.println("Usuário Inserido!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void listarTodosUsuarios(){

        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM usuarios");
            while(result.next()){
                System.out.println(result.getInt("codigo"));
                System.out.println(result.getString("username"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
