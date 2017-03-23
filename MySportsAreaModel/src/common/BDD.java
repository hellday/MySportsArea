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

		// Information d'acc�s � la base de donn�es
		String url = "jdbc:mysql://localhost/msa";
		String login = "root";
		String passwd = "";
		Connection con =null;
		Statement req =null;
		ResultSet res =null;
		
		try {

			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2 : r�cup�ration de la connexion
			con = DriverManager.getConnection(url, login, passwd);

			// Etape 3 : Cr�ation d'un statement
			req = con.createStatement();

			String sql = "SELECT * FROM utilisateur";

			// Etape 4 : ex�cution requ�te
			res = req.executeQuery(sql);

			// Si r�cup donn�es alors �tapes 5 (parcours Resultset)

			while (res.next()) {
				System.out.println(res.getInt(1) + " " + res.getString(2) + " " + res.getString(3) + " " + res.getString(4) + " " + res.getString(5) + " " + res.getString(6));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
			// Etape 6 : lib�rer ressources de la m�moire.
				con.close();
				req.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void sauverEnBase(String personne) {

		// Information d'acc�s � la base de donn�es
		String url = "jdbc:mysql://localhost/msa";
		String login = "root";
		String passwd = "";
		Connection con = null;
		Statement req = null;

		try {

			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2 : r�cup�ration de la connexion
			con = DriverManager.getConnection(url, login, passwd);

			// Etape 3 : Cr�ation d'un statement
			req = con.createStatement();

			String sql = "INSERT INTO `admin` (`personne`) VALUES ('"
					+ personne + "')";

			// Etape 4 : ex�cution requ�te
			req.executeUpdate(sql);


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
			// Etape 6 : lib�rer ressources de la m�moire.
				con.close();
				req.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	}