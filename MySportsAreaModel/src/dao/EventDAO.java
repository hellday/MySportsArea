package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Admin;
import beans.Event;
import common.DBAction;

public class EventDAO {
	public static Event getEvent(int nb) throws SQLException {
        DBAction.DBConnexion();
        Event Resultat  = new Event();
        String                 req       = ("SELECT * FROM event WHERE idEvent='" + nb + "'");

        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Resultat.setIdEvent(DBAction.getRes().getInt(1));
        	Resultat.setIdSport(DBAction.getRes().getInt(2));
        	Resultat.setIdLieux(DBAction.getRes().getInt(3));
        	Resultat.setNomEvent(DBAction.getRes().getString(4));
        	Resultat.setDateEvent(DBAction.getRes().getString(5));
        	Resultat.setDescriptionEvent(DBAction.getRes().getString(6));
        	Resultat.setTypeEvent(DBAction.getRes().getString(7));
        }

        DBAction.DBClose();

        return Resultat;
    }   
	
	public static ArrayList getAllEvent() throws SQLException {
		
		DBAction.DBConnexion();
		ArrayList<Event> events = new ArrayList<Event>();
		String req = ("SELECT * FROM event");
        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Event Resultat = new Event(DBAction.getRes().getInt(1), 
        			DBAction.getRes().getInt(2), 
        			DBAction.getRes().getInt(3), 
        			DBAction.getRes().getString(4),
        			DBAction.getRes().getString(5),
        			DBAction.getRes().getString(6),
        			DBAction.getRes().getString(7));
        	events.add(Resultat);
        }

        DBAction.DBClose();

        return events;
        
    }   
	
	public static int setEvent(int id, int newIdSport, int newIdLieux, String newNomEvent, String newDateEvent, String newDescriptionEvent, String newTypeEvent) throws SQLException {
		return 1;
    }   

	public static int setEvent(int id, Event eventToAdd) throws SQLException {
		return 1;
    }   

}
