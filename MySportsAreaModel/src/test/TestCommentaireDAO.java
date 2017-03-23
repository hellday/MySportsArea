package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import beans.Commentaire;
import dao.CommentaireDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCommentaireDAO {

	
	@Test 
	public void test1GetAllCommentaires() {

		ArrayList<Commentaire> commentaires = new ArrayList<>();
		try {
			commentaires = CommentaireDAO.getAllCommentaire();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Liste des commentaires (testGetAllCommentaires) :");
		viewAllCommentaires(commentaires);

	}
	
	public void viewAllCommentaires(ArrayList<Commentaire> commentaires){
		ListIterator<Commentaire> list = commentaires.listIterator();
		int i = 1;
		while (list.hasNext()) {
			System.out.println("Commentaire n°" + i + list.next().toString());
			i++;
			
		}System.out.println();
	}
	
	@Test
	public void test2GetCommentaires() {
	CommentaireDAO commentaireDAO = new CommentaireDAO();
	try {
		
			System.out.println("(testGetCommentaires) > ID 1 : ");
			Commentaire commentaires =	commentaireDAO.getCommentaire(1);
			System.out.println(commentaires.toString());
			
	} 
	catch (SQLException e) {
	e.printStackTrace();

	System.out.println("erreur de connexion");
}
	}
}
