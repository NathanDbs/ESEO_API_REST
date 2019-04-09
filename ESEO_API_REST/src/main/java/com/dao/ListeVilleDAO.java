package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.blo.Ville;



public class ListeVilleDAO {
private String tableName = "ville_france";
	
	public ListeVilleDAO() {
		super();
	}
	
	public boolean creationVille(Ville ville) {
		
		
		boolean init = false;
		try {
			Connection conn = Connexion.getInstance();
			
			String query = "INSERT INTO " + tableName ;
			query += "(Code_commune_INSEE, Nom_commune, Code_postal, Libelle_acheminement, Ligne_5, Latitude, Longitude)";
			query += " VALUES(?,?,?,?,?,?,?)";
			
			PreparedStatement prepare = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			prepare.setString(1, ville.getCodeCommuneINSEE());
			prepare.setString(2, ville.getNomCommune());
			prepare.setString(3, ville.getCodePostal());
			prepare.setString(4, ville.getLibelleAcheminement());
			prepare.setString(5, ville.getLigne5());
			prepare.setString(6, ville.getLatitude());
			prepare.setString(7, ville.getLongitude());
			
			
			prepare.executeUpdate();
			init = true;
			
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return init;
	}
	
	public Ville [] listeVilles() {
	      Ville [] villes = null;
		
		try {
			Connection conn = Connexion.getInstance();
			String query = "SELECT * FROM "+tableName;

			
			PreparedStatement prepare = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet res = prepare.executeQuery();
			res.last();
			int size = res.getRow();
			res.beforeFirst();
			
			villes = new Ville [size];
			
			int i = 0;
			while (res.next()) {
				Ville ville = new Ville();
				
				ville.setCodeCommuneINSEE(res.getString("Code_commune_INSEE"));
				ville.setNomCommune(res.getString("Nom_commune"));
				ville.setCodePostal(res.getString("Code_postal"));
				ville.setLibelleAcheminement(res.getString("Libelle_acheminement"));
				ville.setLigne5(res.getString("Ligne_5"));
				ville.setLatitude(res.getString("Latitude"));
				ville.setLongitude(res.getString("Longitude"));
				
				villes[i] = ville;
				i++;
			}
			
			res.close();
			prepare.close();
			
			
			
		}catch (Exception e) {
		      e.printStackTrace();


	    }
		return villes;
	}
	public boolean modifier(String CodeCommuneInsee, int filtre, String newValue) {
		boolean init=false;
		 String filtreRequete;
	      switch(filtre)
	    	        {
	                case 1:
	                	filtreRequete="Code_commune_INSEE";
	                break;
	                case 2:
	                	filtreRequete="Nom_commune";
	                break;
	                case 3:
	                	filtreRequete="Code_postal";
	                break;
	                case 4:
	                	filtreRequete="Libelle_acheminement";
	                break;
	                case 5:
	                	filtreRequete="Ligne_5";
	                break;
	                case 6:
	                	filtreRequete="Latitude";
	                break;
	                case 7:
	                	filtreRequete="Longitude";
	                break;
	                default:
	                	filtreRequete="";
	                break;
	            }
		
		try {
			Connection conn = Connexion.getInstance();
			String query="";
			if(filtreRequete!="") {
				query = "UPDATE "+tableName+" SET "+filtreRequete+" = "+newValue+" WHERE Code_commune_INSEE="+CodeCommuneInsee;
			}
			
			PreparedStatement prepare = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			prepare.executeUpdate();
			init = true;
			
			
			
			prepare.close();
			
			
			
		}catch (Exception e) {
		      e.printStackTrace();


	    }
		return init;
	}
	
	public Ville [] listeVillesAvecFiltre(int filtre, String value) {
	      Ville [] villes = null;
	      
	      String filtreRequete;
	      switch(filtre)
	    	        {
	                case 1:
	                	filtreRequete="Code_commune_INSEE";
	                break;
	                case 2:
	                	filtreRequete="Nom_commune";
	                break;
	                case 3:
	                	filtreRequete="Code_postal";
	                break;
	                case 4:
	                	filtreRequete="Libelle_acheminement";
	                break;
	                case 5:
	                	filtreRequete="Ligne_5";
	                break;
	                case 6:
	                	filtreRequete="Latitude";
	                break;
	                case 7:
	                	filtreRequete="Longitude";
	                break;
	                default:
	                	filtreRequete="";
	                break;
	            }
		
		try {
			Connection conn = Connexion.getInstance();
			String query;
			if(filtreRequete=="") {
				query = "SELECT * FROM "+tableName;
			}else {
				query = "SELECT * FROM "+tableName+" WHERE "+filtreRequete+" = "+value;
			}
			

			
			PreparedStatement prepare = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet res = prepare.executeQuery();
			res.last();
			int size = res.getRow();
			res.beforeFirst();
			
			villes = new Ville [size];
			
			int i = 0;
			while (res.next()) {
				Ville ville = new Ville();
				
				ville.setCodeCommuneINSEE(res.getString("Code_commune_INSEE"));
				ville.setNomCommune(res.getString("Nom_commune"));
				ville.setCodePostal(res.getString("Code_postal"));
				ville.setLibelleAcheminement(res.getString("Libelle_acheminement"));
				ville.setLigne5(res.getString("Ligne_5"));
				ville.setLatitude(res.getString("Latitude"));
				ville.setLongitude(res.getString("Longitude"));
				
				villes[i] = ville;
				i++;
			}
			
			res.close();
			prepare.close();
			
			
			
		}catch (Exception e) {
		      e.printStackTrace();


	    }
		return villes;
	}
	
	public boolean suppressionVille(String codeCommuneInsee) {
		
		
		boolean init = false;
		try {
			Connection conn = Connexion.getInstance();
			
			String query = "DELETE FROM " + tableName ;
			query += " WHERE Code_commune_INSEE=?";
			
			
			PreparedStatement prepare = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			prepare.setString(1, codeCommuneInsee);
			
			
			prepare.executeUpdate();
			init = true;
			
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return init;
	}
}
