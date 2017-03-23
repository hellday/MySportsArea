package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Sport;
import common.DBAction;

public class SportDAO {
	public static Sport getSport(int id) throws SQLException {
        DBAction.DBConnexion();
        Sport Resultat  = new Sport();
        String                 req       = ("SELECT * FROM sport WHERE idSport='" + id + "'");

        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Resultat.setIdSport(DBAction.getRes().getInt(1));
        	Resultat.setNomSport(DBAction.getRes().getString(2));
        }

        DBAction.DBClose();

        return Resultat;
    }   
	
	public static ArrayList getAllSport() throws SQLException {
		DBAction.DBConnexion();
		ArrayList<Sport> sports = new ArrayList<Sport>();
		String req = ("SELECT * FROM sport");
        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Sport Resultat = new Sport(DBAction.getRes().getInt(1), DBAction.getRes().getString(2));
        	sports.add(Resultat);
        }

        DBAction.DBClose();

        return sports;
    }   
	
	// Ajoute un Sport
				public static void addSport(String nomSport) throws SQLException {
					DBAction.DBConnexion();
					
					String req = ("INSERT INTO sport (nomSport)  VALUES ('" + nomSport +"')");

					try {
						DBAction.getStm().executeUpdate(req);
					} catch (SQLException ex) {
						System.out.println("catch" + ex.getErrorCode());
					}
					DBAction.DBClose();
				}
	
	public static int setSport(int id, String newNomSport) throws SQLException {
		return 1;
    }   

	public static int setSport(int id, Sport sportToAdd) throws SQLException {
		return 1;
    }   

}
