package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import beans.Sport;
import beans.Utilisateur;
import dao.SportDAO;
import dao.UtilisateurDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSportDAO {

	@Test
	public void test3GetSport() {
	SportDAO sportDAO = new SportDAO();
	try {
		
			System.out.println("(testGetSport) > ID 1 : ");
			Sport sport =	sportDAO.getSport(1);
			System.out.println(sport.toString());
	
	} 
	catch (SQLException e) {
	e.printStackTrace();

	System.out.println("erreur de connexion");
}
	}

	@Test
	public void test2GetAllSport() {

		ArrayList<Sport> sports = new ArrayList<>();
		try {
			sports = SportDAO.getAllSport();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Liste des sports (testGetAllSport) :");
		viewAllSport(sports);

	}
	
	public void viewAllSport(ArrayList<Sport> sports){
		ListIterator<Sport> list = sports.listIterator();
		int i = 1;
		while (list.hasNext()) {
			System.out.println("Sport n°" + i + list.next().toString());
			i++;
			
		}System.out.println();
	}
	
	@Test
	public void test1AddSport() {
		System.out.println("Ajout d'un Sport Test");
		try {
			SportDAO.addSport("sportTest");
			SportDAO.getAllSport();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur de connexion");
		}
	}
	

}
