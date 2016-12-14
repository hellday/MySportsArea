package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import beans.Utilisateur;
import dao.UtilisateurDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUtilisateurDAO {

	@Test
	public void testGetUser() {
	UtilisateurDAO userDAO = new UtilisateurDAO();
	try {
		int ok = 1;
		while(ok == 1){
			System.out.println("(testGetUser) > Choisissez un ID : ");
			Scanner choix = new Scanner(System.in);
	        int nb = choix.nextInt();
			Utilisateur user =	userDAO.getUser(nb);
			
			if(user.getIdUser() == 0){
				System.out.println("Cet ID n'existe pas");
			}else {
				System.out.println(user.toString());
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
	public void testGetAllUser() {

		ArrayList<Utilisateur> users = new ArrayList<>();
		try {
			users = UtilisateurDAO.getAllUser();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Liste des utilisateurs (testGetAllUser) :");
		viewAllUser(users);

	}
	
	public void viewAllUser(ArrayList<Utilisateur> users){
		ListIterator<Utilisateur> list = users.listIterator();
		int i = 1;
		while (list.hasNext()) {
			System.out.println("Utilisateur n°" + i + list.next().toString());
			i++;
			
		}System.out.println();
	}

	

}
