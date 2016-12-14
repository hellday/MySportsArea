package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import beans.Lieux;
import dao.LieuxDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestLieuxDAO {

	@Test
	public void testGetAllLieux() {

		ArrayList<Lieux> lieux = new ArrayList<>();
		try {
			lieux = LieuxDAO.getAllLieux();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Liste des lieux (testGetAllLieux) :");
		viewAllLieux(lieux);

	}
	
	public void viewAllLieux(ArrayList<Lieux> lieux){
		ListIterator<Lieux> list = lieux.listIterator();
		int i = 1;
		while (list.hasNext()) {
			System.out.println("Lieu n°"+ i + list.next().toString());
			i++;
			
		}System.out.println();
	}
	
	@Test
	public void testGetLieux() {
	LieuxDAO lieuxDAO = new LieuxDAO();
	try {
		int ok = 1;
		while(ok == 1){
			System.out.println("(testGetLieux) > Choisissez un ID : ");
			Scanner choix = new Scanner(System.in);
	        int nb = choix.nextInt();
			Lieux lieux =	lieuxDAO.getLieux(nb);
			
			if(lieux.getIdLieux() == 0){
				System.out.println("Cet ID n'existe pas");
			}else {
				System.out.println(lieux.toString());
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
