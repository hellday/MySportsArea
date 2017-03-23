package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Lieux;
import beans.Partenaire;
import common.DBAction;

public class PartenaireDAO {
	public static Partenaire getPartenaire(int nb) throws SQLException {
        DBAction.DBConnexion();
        Partenaire Resultat  = new Partenaire();
        String                 req       = ("SELECT * FROM partenaire WHERE idPartenaire='" + nb + "'");

        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Resultat.setIdPartenaire(DBAction.getRes().getInt(1));
        	Resultat.setIdUser(DBAction.getRes().getInt(2));
        	Resultat.setIdSport(DBAction.getRes().getInt(3));
        	Resultat.setPréférences(DBAction.getRes().getString(4));
        	Resultat.setDisponibilités(DBAction.getRes().getString(5));
        }

        DBAction.DBClose();

        return Resultat;
    }   
	
	public static ArrayList getAllPartenaire() throws SQLException {
		
		DBAction.DBConnexion();
		ArrayList<Partenaire> partenaires = new ArrayList<Partenaire>();
		String req = ("SELECT * FROM partenaire");
        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Partenaire Resultat = new Partenaire(DBAction.getRes().getInt(1), 
        			DBAction.getRes().getInt(2), 
        			DBAction.getRes().getInt(3), 
        			DBAction.getRes().getString(4), 
        			DBAction.getRes().getString(5));
        	partenaires.add(Resultat);
        }

        DBAction.DBClose();

        return partenaires;
		
    }   
	
	public static int setPartenaire(int id, int newIdUser, int newIdSport, String newPréférences, String newDisponibilités) throws SQLException {
		return 1;
    }   

	public static int setPartenaire(int id, Partenaire partenaireToAdd) throws SQLException {
		return 1;
    }   

}