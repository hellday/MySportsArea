package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import beans.Lieux;
import beans.Partenaire;
import dao.LieuxDAO;
import dao.PartenaireDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPartenaireDAO {

	@Test
	public void test1GetAllPartenaires() {

		ArrayList<Partenaire> partenaires = new ArrayList<>();
		try {
			partenaires = PartenaireDAO.getAllPartenaire();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Liste des partenaires (testGetAllPartenaires) :");
		viewAllPartenaires(partenaires);

	}
	
	public void viewAllPartenaires(ArrayList<Partenaire> partenaires){
		ListIterator<Partenaire> list = partenaires.listIterator();
		int i = 1;
		while (list.hasNext()) {
			System.out.println("Partenaire n°" + i + list.next().toString());
			i++;
			
		}System.out.println();
	}
	
	@Test
	public void test2GetPartenaires() {
	PartenaireDAO partenaireDAO = new PartenaireDAO();
	try {
		
			System.out.println("(testGetPartenaire) > ID 1 : ");
			Partenaire partenaires =	partenaireDAO.getPartenaire(1);
			System.out.println(partenaires.toString());
			
	} 
	catch (SQLException e) {
	e.printStackTrace();

	System.out.println("erreur de connexion");
}
	}
	
}
