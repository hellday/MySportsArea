package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Utilisateur;
import common.DBAction;

public class UtilisateurDAO {
	public static Utilisateur getUser(String id) throws SQLException {
        DBAction.DBConnexion();
        Utilisateur Resultat  = new Utilisateur();
        String                 req       = ("SELECT * FROM utilisateur WHERE idUser='" + id + "'");

        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Resultat.setNomUser(DBAction.getRes().getString(1));
        	Resultat.setPrenomUser(DBAction.getRes().getString(2));
        	Resultat.setLogin(DBAction.getRes().getString(3));
        	Resultat.setMdp(DBAction.getRes().getString(4));
        	Resultat.setAdresseUser(DBAction.getRes().getString(5));
        	Resultat.setTelUser(DBAction.getRes().getString(6));
        }

        DBAction.DBClose();

        return Resultat;
    }   
	
	public static ArrayList getAllUser() throws SQLException {
		return null;
    }   
	
	public static int setUser(int id, String newNom, String newPrenom, String newLogin, String newMdp, String newAdresseUser, String newTelUser) throws SQLException {
		return 1;
    }   

	public static int setUser(int id, Utilisateur userToAdd) throws SQLException {
		return 1;
    }   

}