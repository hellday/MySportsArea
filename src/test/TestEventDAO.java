package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import beans.Event;
import dao.EventDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEventDAO {

	@Test
	public void testGetAllEvents() {

		ArrayList<Event> events = new ArrayList<>();
		try {
			events = EventDAO.getAllEvent();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Liste des Events (testGetAllEvents) :");
		viewAllEvents(events);

	}
	
	public void viewAllEvents(ArrayList<Event> events){
		ListIterator<Event> list = events.listIterator();
		int i = 1;
		while (list.hasNext()) {
			System.out.println("Event n°" + i + list.next().toString());
			i++;
			
		}System.out.println();
	}
	
	@Test
	public void testGetEvent() {
	EventDAO eventDAO = new EventDAO();
	try {
		int ok = 1;
		while(ok == 1){
			System.out.println("(testGetEvent) > Choisissez un ID : ");
			Scanner choix = new Scanner(System.in);
	        int nb = choix.nextInt();
			Event events =	eventDAO.getEvent(nb);
			
			if(events.getIdLieux() == 0){
				System.out.println("Cet ID n'existe pas");
			}else {
				System.out.println(events.toString());
				ok = 0;
			}
		}
		
	} 
	catch (SQLException e) {
	e.printStackTrace();

	System.out.println("erreur de connexion");
}
	}
	
}
