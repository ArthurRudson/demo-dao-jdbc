package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

	static final String URL = "jdbc:postgresql://localhost:5432/postgres"; // incica o caminho do banco de dados
	static final String USER = "postgres"; // aqui vai o nome usuario que vc quer acessar
	static final String PASS = "26841397a@"; // aqui a senha do seu banco

	public static Connection criarConexao() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection connect = DriverManager.getConnection(URL, USER, PASS);
		if (connect != null) {
			return connect;
		}
		return null;
	}

}