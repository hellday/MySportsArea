package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Lieux;
import beans.Utilisateur;
import common.DBAction;

public class LieuxDAO {
	public static Lieux getLieux(int nb) throws SQLException {
        DBAction.DBConnexion();
        Lieux Resultat  = new Lieux();
        String                 req       = ("SELECT * FROM lieux WHERE idLieux='" + nb + "'");

        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Resultat.setIdLieux(DBAction.getRes().getInt(1));
        	Resultat.setIdSport(DBAction.getRes().getInt(2));
        	Resultat.setNomLieux(DBAction.getRes().getString(3));
        	Resultat.setLatitude(DBAction.getRes().getFloat(4));
        	Resultat.setLongitude(DBAction.getRes().getFloat(5));
        	Resultat.setTypeLieux(DBAction.getRes().getString(6));
        }

        DBAction.DBClose();

        return Resultat;
    }   
	
	public static ArrayList getAllLieux() throws SQLException {
		
		DBAction.DBConnexion();
		ArrayList<Lieux> lieux = new ArrayList<Lieux>();
		String req = ("SELECT * FROM lieux");
        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Lieux Resultat = new Lieux(DBAction.getRes().getInt(1), 
        			DBAction.getRes().getInt(2), 
        			DBAction.getRes().getString(3), 
        			DBAction.getRes().getFloat(4), 
        			DBAction.getRes().getFloat(5), 
        			DBAction.getRes().getString(6));
        	lieux.add(Resultat);
        }

        DBAction.DBClose();

        return lieux;
    }   
	
	// Ajoute un Lieux
				public static void addLieux(String idSport, String nomLieux, float latitude, float longitude, String typeLieux) throws SQLException {
					DBAction.DBConnexion();
					
					String req = ("INSERT INTO lieux (idSport, nomLieux, latitude, longitude, typeLieux)  VALUES ('"
							+ idSport + "','" + nomLieux + "','" + latitude + "','" + longitude + "','" + typeLieux + "')");

					try {
						DBAction.getStm().executeUpdate(req);
					} catch (SQLException ex) {
						System.out.println("catch" + ex.getErrorCode());
					}
					DBAction.DBClose();
				}
	
	public static int setLieux(int id, int newIdSport, String newNomLieux, float newLatitude, float newLongitude, String newTypeLieux) throws SQLException {
		return 1;
    }   

	public static int setLieux(int id, Lieux lieuxToAdd) throws SQLException {
		return 1;
    }   

}
