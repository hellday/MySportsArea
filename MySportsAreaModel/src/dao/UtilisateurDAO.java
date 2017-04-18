package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Utilisateur;
import common.DBAction;

public class UtilisateurDAO {
	//Affiche un Utilisateur via son ID
	public static Utilisateur getUser(int id) throws SQLException {
        DBAction.DBConnexion();
        Utilisateur Resultat  = new Utilisateur();
        String                 req       = ("SELECT * FROM utilisateur WHERE idUser='" + id + "'");

        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Resultat.setIdUser(DBAction.getRes().getInt(1));
        	Resultat.setNomUser(DBAction.getRes().getString(2));
        	Resultat.setPrenomUser(DBAction.getRes().getString(3));
        	Resultat.setLogin(DBAction.getRes().getString(4));
        	Resultat.setMdp(DBAction.getRes().getString(5));
        	Resultat.setAdresseUser(DBAction.getRes().getString(6));
        	Resultat.setTelUser(DBAction.getRes().getString(7));
        }

        DBAction.DBClose();

        return Resultat;
    }   
	
	public static Utilisateur getUserByLogin(String login) throws SQLException {
        DBAction.DBConnexion();
        Utilisateur Resultat  = new Utilisateur();
        String                 req       = ("SELECT * FROM utilisateur WHERE login='" + login + "'");

        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        if (DBAction.getRes().next()) {
        	Resultat.setIdUser(DBAction.getRes().getInt(1));
        	Resultat.setNomUser(DBAction.getRes().getString(2));
        	Resultat.setPrenomUser(DBAction.getRes().getString(3));
        	Resultat.setLogin(DBAction.getRes().getString(4));
        	Resultat.setMdp(DBAction.getRes().getString(5));
        	Resultat.setAdresseUser(DBAction.getRes().getString(6));
        	Resultat.setTelUser(DBAction.getRes().getString(7));
        }

        DBAction.DBClose();

        return Resultat;
    }   
	
	//Affiche tout les Utilisateurs
	public static ArrayList getAllUser() throws SQLException {
		DBAction.DBConnexion();
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		String req = ("SELECT * FROM utilisateur");
        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Utilisateur Resultat = new Utilisateur(DBAction.getRes().getInt(1), 
        			DBAction.getRes().getString(2), 
        			DBAction.getRes().getString(3), 
        			DBAction.getRes().getString(4), 
        			DBAction.getRes().getString(5), 
        			DBAction.getRes().getString(6), 
        			DBAction.getRes().getString(7));
        	users.add(Resultat);
        }

        DBAction.DBClose();

        return users;
    }     
	
	// Ajoute un Utilisateur
		public static void addUser(String nomUser, String prenomUser, String login, String mdp, String adresseUser, String telUser) throws SQLException {
			DBAction.DBConnexion();
			
			String req = ("INSERT INTO utilisateur (nomUser, prenomUser, login, mdp, adresseUser, telUser)  VALUES ('"
					+ nomUser + "','" + prenomUser + "','" + login + "','" + mdp + "','" + adresseUser + "','"
					+ telUser + "')");

			try {
				DBAction.getStm().executeUpdate(req);
			} catch (SQLException ex) {
				System.out.println("catch" + ex.getErrorCode());
			}
			DBAction.DBClose();
		}
		
		//Supprime un Utilisateur via son ID
		public static void deleteUser(int idUser) throws SQLException {
			DBAction.DBConnexion();
			
			String req = ("DELETE FROM utilisateur WHERE idUser ='"+ idUser +"'");
			try {
				DBAction.getStm().executeUpdate(req);
				System.out.println("L'utilisateur avec l'id="+idUser+" a été supprimé.");
			} catch (SQLException ex) {
				System.out.println(req);
				System.out.println("Requête non valide " + ex.getErrorCode());
			}
			DBAction.DBClose();
		}
		
		//Modification d'un Utilisateur via son ID
				public static void updateUser(int idUser, String login, String mdp) throws SQLException {
					DBAction.DBConnexion();
					
					String req = ("UPDATE utilisateur SET login='"+ login +"', mdp='"+ mdp +"' WHERE idUser ='"+ idUser +"'");
					try {
						DBAction.getStm().executeUpdate(req);
						System.out.println("Le login et le mdp de l'utilisateur avec l'id "+idUser+"a été modifié.");
					} catch (SQLException ex) {
						System.out.println(req);
						System.out.println("Requête non valide " + ex.getErrorCode());
					}
					DBAction.DBClose();
				}
	
	public static int setUser(int id, String newNom, String newPrenom, String newLogin, String newMdp, String newAdresseUser, String newTelUser) throws SQLException {
		return 1;
    }   

	public static int setUser(int id, Utilisateur userToAdd) throws SQLException {
		return 1;
    }   
	
	

}