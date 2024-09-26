package model.dao;

import java.sql.SQLException;

import db.Connect;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	public static SellerDao createSellerDao() throws ClassNotFoundException, SQLException {
		return new SellerDaoJDBC(Connect.criarConexao());
	}

}
