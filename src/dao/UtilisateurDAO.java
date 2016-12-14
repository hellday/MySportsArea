package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Utilisateur;
import common.DBAction;

public class UtilisateurDAO {
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
	
	public static int setUser(int id, String newNom, String newPrenom, String newLogin, String newMdp, String newAdresseUser, String newTelUser) throws SQLException {
		return 1;
    }   

	public static int setUser(int id, Utilisateur userToAdd) throws SQLException {
		return 1;
    }   
	
	

}