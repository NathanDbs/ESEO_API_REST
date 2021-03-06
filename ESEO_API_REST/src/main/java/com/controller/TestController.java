package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.Ville;

@RestController
public class TestController {
	@RequestMapping(value = "/testget", method = RequestMethod.GET)
	@ResponseBody
	public List<Ville> get(@RequestParam(required = false, value = "value") String value) {
		List<Ville> listeVilles = new ArrayList<Ville>();
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection connexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/maven?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris", "root", "");
			Statement stmt = connexion.createStatement();
			String sql_query = "SELECT * FROM `ville_france`";
			System.out.println("sql_query" + sql_query);
			ResultSet result = stmt.executeQuery(sql_query);

			while (result.next()) {
				/*
				 * System.out.println("***********************");
				 * System.out.println(result.getString("Code_commune_INSEE"));
				 * System.out.println(result.getString("Nom_commune"));
				 * System.out.println(result.getString("Code_postal"));
				 * System.out.println(result.getString("Libelle_acheminement"));
				 */
				listeVilles.add(new Ville(result.getString("Code_commune_INSEE"), result.getString("Nom_commune"),
						result.getString("Code_postal"), result.getString("Latitude"), result.getString("Longitude")));
			}

			result.close();
			stmt.close();
			connexion.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return listeVilles;
	}

	@RequestMapping(value = "/testput", method = RequestMethod.PUT)
	@ResponseBody
	public void put(@RequestParam(required = false, value = "value") String value) {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection connexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/maven?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris", "root", "");
			Statement statement = connexion.createStatement();
			String query = "INSERT INTO `ville_france`(`Code_commune_INSEE`, `Nom_commune`, `Code_postal`, `Libelle_acheminement`, `Ligne_5`, `Latitude`, `Longitude`) VALUES ('44211', 'SAINT PHILBERT DE GRAND LIEU ', '44310', 'SAINT PHILBERT DE GRAND LIEU ', '', '47.0333', '-1.6333');";
			statement.execute(query);
			statement.close();
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/testpost", method = RequestMethod.POST)
	@ResponseBody
	public void post(@RequestParam(required = false, value = "value") String nomCommune, String codeCommune) {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection connexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/maven?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris", "root", "");
			Statement statement = connexion.createStatement();
			String query = "UPDATE `ville_france` SET `Nom_commune`='" + nomCommune + "' WHERE `Code_commune_INSEE`="
					+ codeCommune + ";";
			statement.execute(query);
			statement.close();
			connexion.close();

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/testdelete", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@RequestParam(required = false, value = "value") String value) {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection connexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/maven?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris", "root", "");
			Statement statement = connexion.createStatement();
			String query = "DELETE FROM `ville_france` WHERE `Code_commune_INSEE`=44211;";

			statement.execute(query);

			// result.close();
			statement.close();
			connexion.close();

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/testgetfiltre", method = RequestMethod.GET)
	@ResponseBody
	public List<Ville> getfiltre(@RequestParam(required = false, value = "value") String value) {
		List<Ville> listeVilles = new ArrayList<Ville>();
		System.out.println("rentre ici");
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection connexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/maven?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris", "root", "");
			Statement statement = connexion.createStatement();
			String query = "SELECT * FROM `ville_france` WHERE `Nom_commune`=\"";
			query = query + value + "\";";
			System.out.println("query" + query);
			ResultSet result = statement.executeQuery(query);

			while (result.next()) {
				/*
				 * System.out.println("*****************");
				 * System.out.println(result.getString("Code_commune_INSEE"));
				 * System.out.println(result.getString("Nom_commune"));
				 * System.out.println(result.getString("Code_postal"));
				 * System.out.println(result.getString("Libelle_acheminement"));
				 * System.out.println("*****************");
				 */
				listeVilles.add(new Ville(result.getString("Code_commune_INSEE"), result.getString("Nom_commune"),
						result.getString("Code_postal"), result.getString("Latitude"), result.getString("Longitude")));
			}
			result.close();
			statement.close();
			connexion.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeVilles;
	}

}
