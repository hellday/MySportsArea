package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Sport;
import common.DBAction;

public class SportDAO {
	public static Sport getSport(String id) throws SQLException {
        DBAction.DBConnexion();
        Sport Resultat  = new Sport();
        String                 req       = ("SELECT * FROM sport WHERE idSport='" + id + "'");

        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Resultat.setNomSport(DBAction.getRes().getString(1));
        }

        DBAction.DBClose();

        return Resultat;
    }   
	
	public static ArrayList getAllSport() throws SQLException {
		return null;
    }   
	
	public static int setSport(int id, String newNomSport) throws SQLException {
		return 1;
    }   

	public static int setSport(int id, Sport sportToAdd) throws SQLException {
		return 1;
    }   

}
