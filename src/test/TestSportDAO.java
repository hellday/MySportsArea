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
	public void testGetSport() {
	SportDAO sportDAO = new SportDAO();
	try {
		int ok = 1;
		while(ok == 1){
			System.out.println("(testGetSport) > Choisissez un ID : ");
			Scanner choix = new Scanner(System.in);
	        int nb = choix.nextInt();
			Sport sport =	sportDAO.getSport(nb);
			
			if(sport.getIdSport() == 0){
				System.out.println("Cet ID n'existe pas");
			}else {
				System.out.println(sport.toString());
				ok = 0;
			}
		}
		
	} 
	catch (SQLException e) {
	e.printStackTrace();

	System.out.println("erreur de connexion");
}
	}

	@Test
	public void testGetAllSport() {

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
}
