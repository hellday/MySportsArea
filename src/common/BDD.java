package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDD {

	public static void main(String[] args) {
		//sauverEnBase("Test");
		lireEnBase();
	}
	
	public static void connexionBase(){
		
	}

	
	
	public static void lireEnBase() {

		// Information d'accès à la base de données
		String url = "jdbc:mysql://localhost/msa";
		String login = "root";
		String passwd = "";
		Connection con =null;
		Statement req =null;
		ResultSet res =null;
		
		try {

			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2 : récupération de la connexion
			con = DriverManager.getConnection(url, login, passwd);

			// Etape 3 : Création d'un statement
			req = con.createStatement();

			String sql = "SELECT * FROM utilisateur";

			// Etape 4 : exécution requête
			res = req.executeQuery(sql);

			// Si récup données alors étapes 5 (parcours Resultset)

			while (res.next()) {
				System.out.println(res.getInt(1) + " " + res.getString(2) + " " + res.getString(3) + " " + res.getString(4) + " " + res.getString(5) + " " + res.getString(6));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
			// Etape 6 : libérer ressources de la mémoire.
				con.close();
				req.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void sauverEnBase(String personne) {

		// Information d'accès à la base de données
		String url = "jdbc:mysql://localhost/msa";
		String login = "root";
		String passwd = "";
		Connection con = null;
		Statement req = null;

		try {

			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2 : récupération de la connexion
			con = DriverManager.getConnection(url, login, passwd);

			// Etape 3 : Création d'un statement
			req = con.createStatement();

			String sql = "INSERT INTO `admin` (`personne`) VALUES ('"
					+ personne + "')";

			// Etape 4 : exécution requête
			req.executeUpdate(sql);


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
			// Etape 6 : libérer ressources de la mémoire.
				con.close();
				req.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	}