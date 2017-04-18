package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Lieux;
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
        	Resultat.setIdSport(DBAction.getRes().getString(2));
        	Resultat.setNomLieux(DBAction.getRes().getString(3));
        	Resultat.setDescription(DBAction.getRes().getString(4));
        	Resultat.setLatitude(DBAction.getRes().getFloat(5));
        	Resultat.setLongitude(DBAction.getRes().getFloat(6));
        	Resultat.setTypeLieux(DBAction.getRes().getString(7));
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
        			DBAction.getRes().getString(2), 
        			DBAction.getRes().getString(3), 
        			DBAction.getRes().getString(4), 
        			DBAction.getRes().getFloat(5), 
        			DBAction.getRes().getFloat(6), 
        			DBAction.getRes().getString(7),
        			DBAction.getRes().getString(8));
        	lieux.add(Resultat);
        }

        DBAction.DBClose();

        return lieux;
    }   
	
	public static ArrayList getAllSpecificLieux(String sport, String status, String type) throws SQLException {
		
		DBAction.DBConnexion();
		
		if (sport.equals("Tous")){
			sport = "!='Tous'";
		}else {
			sport = "='"+sport+"'";
		}
		
		if (status.equals("Tous")){
			status = "!='Tous'";
		}else {
			status = "='"+status+"'";
		}
		
		if (type.equals("Tous")){
			type = "!='Tous'";
		}else {
			type = "='"+type+"'";
		}
		
		ArrayList<Lieux> lieux = new ArrayList<Lieux>();
		String req = ("SELECT idLieux, sport.nomSport, nomLieux, description, latitude, longitude, status, typeLieux FROM lieux INNER JOIN sport ON lieux.idSport = sport.idSport "
				+ "WHERE sport.nomSport " + sport + " AND lieux.status " + status + " AND lieux.typeLieux " + type);
        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Lieux Resultat = new Lieux(DBAction.getRes().getInt(1), 
        			DBAction.getRes().getString(2), 
        			DBAction.getRes().getString(3), 
        			DBAction.getRes().getString(4), 
        			DBAction.getRes().getFloat(5), 
        			DBAction.getRes().getFloat(6), 
        			DBAction.getRes().getString(7),
        			DBAction.getRes().getString(8));
        	lieux.add(Resultat);
        }

        DBAction.DBClose();

        return lieux;
    }   
	
	public static ArrayList getLieuxByTitleAndDescription(String title) throws SQLException {
		
		DBAction.DBConnexion();
		ArrayList<Lieux> lieux = new ArrayList<Lieux>();
		String req = ("SELECT idLieux, sport.nomSport, nomLieux, description, latitude, longitude, status, typeLieux FROM lieux INNER JOIN sport ON lieux.idSport = sport.idSport "
        		+ "WHERE nomLieux='" + title + "'");
        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Lieux Resultat = new Lieux(DBAction.getRes().getInt(1), 
        			DBAction.getRes().getString(2), 
        			DBAction.getRes().getString(3), 
        			DBAction.getRes().getString(4), 
        			DBAction.getRes().getFloat(5), 
        			DBAction.getRes().getFloat(6), 
        			DBAction.getRes().getString(7),
        			DBAction.getRes().getString(8));
        	lieux.add(Resultat);
        }

        DBAction.DBClose();

        return lieux;
    }   
	
	// Ajoute un Lieux
				public static void addLieux(String idSport, String nomLieux, String description, float latitude, float longitude, String typeLieux) throws SQLException {
					DBAction.DBConnexion();
					
					String req = ("INSERT INTO lieux (idSport, nomLieux, description, latitude, longitude, typeLieux)  VALUES ('"
							+ idSport + "','" + nomLieux + "','" + description + "','" + latitude + "','" + longitude + "','" + typeLieux + "')");

					try {
						DBAction.getStm().executeUpdate(req);
					} catch (SQLException ex) {
						System.out.println("catch" + ex.getErrorCode());
					}
					DBAction.DBClose();
				}
	
	public static int setLieux(int id, int newIdSport, String newNomLieux, String newDescription, float newLatitude, float newLongitude, String newTypeLieux) throws SQLException {
		return 1;
    }   

	public static int setLieux(int id, Lieux lieuxToAdd) throws SQLException {
		return 1;
    }   

}
