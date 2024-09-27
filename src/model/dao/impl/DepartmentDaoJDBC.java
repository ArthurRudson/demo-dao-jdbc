package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.Connect;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
	
	private Connection coon;
	
	public DepartmentDaoJDBC(Connection coon) {
		this.coon = coon;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try {
			Connection con = Connect.criarConexao();
			st = con.prepareStatement(
					"INSERT INTO department " +
					"(Name) " +
					"VALUES " +
					"(?)", 
					 Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs =  st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			}
			else {
				throw new DbException("Unexpected error! No rows affected)");
			}
			
		}
		catch (ClassNotFoundException e) {
			throw new DbException(e.getMessage());
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		try {
			Connection con = Connect.criarConexao();
			st = con.prepareStatement(
					"UPDATE department " +
					"SET Name = ? " +
					"WHERE Id = ?");
			
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			
			st.executeUpdate();
			
		}
		catch (ClassNotFoundException e) {
			throw new DbException(e.getMessage());
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}

	@Override
	public void deleteId(Integer id) {
		PreparedStatement st = null;
		try {
			Connection con = Connect.criarConexao();
			st = con.prepareStatement("DELETE FROM department WHERE Id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
			
		}
		catch (ClassNotFoundException e) {
			throw new DbException(e.getMessage());
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		 ResultSet rs = null;
		try {
			Connection con = Connect.criarConexao();
			st = con.prepareStatement("SELECT * FROM department WHERE Id = ?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				return dep;
	        } 
			else {
	        	return null;
	        }
		}
		catch (ClassNotFoundException e) {
			throw new DbException(e.getMessage());
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs =  null;
		try {
			Connection con = Connect.criarConexao();
			st = con.prepareStatement("SELECT * FROM department ORDER BY Name");
			
			rs = st.executeQuery();
			
			List<Department> list = new ArrayList<>();
			
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				list.add(dep);
			}
			return list;
		}
		catch (ClassNotFoundException e) {
			throw new DbException(e.getMessage());
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
}
