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
	public void test2GetAllLieux() {

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
			System.out.println("Lieu n�"+ i + list.next().toString());
			i++;
			
		}System.out.println();
	}
	
	@Test
	public void test3GetLieux() {
	LieuxDAO lieuxDAO = new LieuxDAO();
	try {
		
			System.out.println("(testGetLieux) > ID 1 : ");
			Lieux lieux =	lieuxDAO.getLieux(1);
			System.out.println(lieux.toString());
		
	} 
	catch (SQLException e) {
	e.printStackTrace();

	System.out.println("erreur de connexion");
}
	}
	
	@Test
	public void test1AddLieux() {
		System.out.println("Ajout d'un lieux Test");
		try {
			LieuxDAO.addLieux("2","lieuxtest", "testDescription", 50.0f ,50.0f ,"Stade");
			LieuxDAO.getAllLieux();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur de connexion");
		}
	}

	
	
	
}
