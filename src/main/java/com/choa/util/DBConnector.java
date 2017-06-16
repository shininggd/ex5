package com.choa.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnector {
	
	public static Connection getConnect(){
		Connection con=null;
		
		try {
			Context init = new InitialContext();
			DataSource dataSource = (DataSource)init.lookup("java:comp/env/jdbc/choa");
			con = dataSource.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
		
	}
	
	public static void disConnect(ResultSet rs, PreparedStatement pst, Connection con){
		try {
			rs.close();
			DBConnector.disConnect(pst, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void disConnect(PreparedStatement pst, Connection con){
		try {
			pst.close();
			DBConnector.disConnect(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void disConnect(Connection con){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
