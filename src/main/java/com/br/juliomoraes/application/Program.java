package com.br.juliomoraes.application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.br.juliomoraes.connection.SingleConnection;
import com.br.juliomoraes.exceptions.DbException;

public class Program {

	public static void main(String[] args) {
		
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			connection = SingleConnection.getConnection();
			st = connection.createStatement();
			
			rs = st.executeQuery("SELECT * FROM tb_departamento");
			
			while(rs.next()) {
				System.out.println(rs.getInt("id") + " - " + rs.getString("nome"));
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			SingleConnection.closeResultSet(rs);
			SingleConnection.closeStatement(st);
			SingleConnection.closeConnection();
		}
	}

}
