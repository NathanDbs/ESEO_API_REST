package com.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.App;

public class Connexion {
	public static Connection connexion;
	public static Connection getInstance() {
		if(connexion!=null) {
			return connexion;
		}else {
			try {
				Properties prop = new Properties();
				InputStream input = null;
				String filename="application.properties";
				
				input = App.class.getClassLoader().getResourceAsStream(filename);
	    		if(input!=null){
	    			prop.load(input);
	    			
	    			connexion = DriverManager.getConnection(prop.getProperty("BDD.url"), prop.getProperty("BDD.user"), prop.getProperty("BDD.password"));
	    		}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return connexion;
		}
	}
	public void disconnect() {
		try {
			Connexion.getInstance().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
