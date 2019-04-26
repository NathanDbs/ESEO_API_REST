package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class BddDAO {

	public void returnBDD() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost/maven?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris&useSSL=false","root","");
			
			Statement statement = connexion.createStatement();
			
			String query = "SELECT * FROM ville_france LIMIT 20";
			ResultSet result = statement.executeQuery(query);
			/*
			 * while(result.next()) { System.out.println("*****************");
			 * System.out.println(result.getInt(1));
			 * System.out.println(result.getString(2));
			 * System.out.println(result.getInt(3));
			 * System.out.println(result.getString(4));
			 * System.out.println(result.getString(5));
			 * System.out.println(result.getInt(6)); }
			 */
			
			statement.close();
			connexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
