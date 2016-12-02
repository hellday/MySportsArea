package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Lieux;
import common.DBAction;

public class LieuxDAO {
	public static Lieux getLieux(String id) throws SQLException {
        DBAction.DBConnexion();
        Lieux Resultat  = new Lieux();
        String                 req       = ("SELECT * FROM lieux WHERE idLieux='" + id + "'");

        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Resultat.setIdSport(DBAction.getRes().getInt(1));
        	Resultat.setNomLieux(DBAction.getRes().getString(2));
        	Resultat.setLatitude(DBAction.getRes().getFloat(3));
        	Resultat.setLongitude(DBAction.getRes().getFloat(4));
        	Resultat.setTypeLieux(DBAction.getRes().getString(5));
        }

        DBAction.DBClose();

        return Resultat;
    }   
	
	public static ArrayList getAllLieux() throws SQLException {
		return null;
    }   
	
	public static int setLieux(int id, int newIdSport, String newNomLieux, float newLatitude, float newLongitude, String newTypeLieux) throws SQLException {
		return 1;
    }   

	public static int setLieux(int id, Lieux lieuxToAdd) throws SQLException {
		return 1;
    }   

}
