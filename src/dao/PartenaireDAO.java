package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Partenaire;
import common.DBAction;

public class PartenaireDAO {
	public static Partenaire getPartenaire(String id) throws SQLException {
        DBAction.DBConnexion();
        Partenaire Resultat  = new Partenaire();
        String                 req       = ("SELECT * FROM partenaire WHERE idPartenaire='" + id + "'");

        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Resultat.setIdUser(DBAction.getRes().getInt(1));
        	Resultat.setIdSport(DBAction.getRes().getInt(2));
        	Resultat.setPréférences(DBAction.getRes().getString(3));
        	Resultat.setDisponibilités(DBAction.getRes().getString(4));
        }

        DBAction.DBClose();

        return Resultat;
    }   
	
	public static ArrayList getAllPartenaire() throws SQLException {
		return null;
    }   
	
	public static int setPartenaire(int id, int newIdUser, int newIdSport, String newPréférences, String newDisponibilités) throws SQLException {
		return 1;
    }   

	public static int setPartenaire(int id, Partenaire partenaireToAdd) throws SQLException {
		return 1;
    }   

}