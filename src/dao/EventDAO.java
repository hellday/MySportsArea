package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Event;
import common.DBAction;

public class EventDAO {
	public static Event getEvent(String id) throws SQLException {
        DBAction.DBConnexion();
        Event Resultat  = new Event();
        String                 req       = ("SELECT * FROM event WHERE idEvent='" + id + "'");

        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Resultat.setIdSport(DBAction.getRes().getInt(1));
        	Resultat.setIdLieux(DBAction.getRes().getInt(2));
        	Resultat.setNomEvent(DBAction.getRes().getString(3));
        	Resultat.setDateEvent(DBAction.getRes().getString(4));
        	Resultat.setDescriptionEvent(DBAction.getRes().getString(5));
        	Resultat.setTypeEvent(DBAction.getRes().getString(6));
        }

        DBAction.DBClose();

        return Resultat;
    }   
	
	public static ArrayList getAllEvent() throws SQLException {
		return null;
    }   
	
	public static int setEvent(int id, int newIdSport, int newIdLieux, String newNomEvent, String newDateEvent, String newDescriptionEvent, String newTypeEvent) throws SQLException {
		return 1;
    }   

	public static int setEvent(int id, Event eventToAdd) throws SQLException {
		return 1;
    }   

}
