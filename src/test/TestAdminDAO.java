package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import beans.Admin;
import dao.AdminDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAdminDAO {

	@Test 
	public void test1GetAllAdmins() {

		ArrayList<Admin> admins = new ArrayList<>();
		try {
			admins = AdminDAO.getAllAdmin();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Liste des admins (testGetAllAdmins) :");
		viewAllAdmins(admins);

	}
	
	public void viewAllAdmins(ArrayList<Admin> admins){
		ListIterator<Admin> list = admins.listIterator();
		int i = 1;
		while (list.hasNext()) {
			System.out.println("Admin n°" + i + list.next().toString());
			i++;
		}System.out.println();
	}
	
	@Test
	public void test2GetAdmin() {
	AdminDAO adminDAO = new AdminDAO();
	try {
		int ok = 1;
		while(ok == 1){
			System.out.println("(testGetAdmin) > Choisissez un ID : ");
			Scanner choix = new Scanner(System.in);
	        int nb = choix.nextInt();
			Admin admins =	adminDAO.getAdmin(nb);
			
			if(admins.getIdAdmin() == 0){
				System.out.println("Cet ID n'existe pas");
			}else {
				System.out.println(admins.toString());
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
