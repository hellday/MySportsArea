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
	public void test4GetAllAdmins() {

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
	public void test5GetAdmin() {
	AdminDAO adminDAO = new AdminDAO();
	try {
		
			System.out.println("(testGetAdmin) > ID 1 : ");
			Admin admins =	adminDAO.getAdmin(1);
			System.out.println(admins.toString());
		
	} 
	catch (SQLException e) {
	e.printStackTrace();

	System.out.println("erreur de connexion");
}
	}
	
	@Test
	public void test1AddAdmin() {
		System.out.println("Ajout d'un admin Test");
		try {
			AdminDAO.addAdmin("pseudoTest","logTest","mdpTest");
			AdminDAO.getAllAdmin();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur de connexion");
		}
	}
	
	@Test
	public void test3DeleteAdmin() {
		System.out.println("Suppression d'un admin");
		try {
			AdminDAO.deleteUser(2);
			AdminDAO.getAllAdmin();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur de connexion");
		}
	}
	
	@Test
	public void test2UpdateAdmin() {
		System.out.println("Modification d'un admin");
		try {
			AdminDAO.updateAdmin(3, "newPseudo", "newLogin", "newMdp");
			AdminDAO.getAllAdmin();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur de connexion");
		}
	}
}
