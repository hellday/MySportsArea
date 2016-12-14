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
	public void testGetAllCommentaires() {

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
	public void testGetCommentaires() {
	CommentaireDAO commentaireDAO = new CommentaireDAO();
	try {
		int ok = 1;
		while(ok == 1){
			System.out.println("(testGetCommentaires) > Choisissez un ID : ");
			Scanner choix = new Scanner(System.in);
	        int nb = choix.nextInt();
			Commentaire commentaires =	commentaireDAO.getCommentaire(nb);
			
			if(commentaires.getIdCommentaire() == 0){
				System.out.println("Cet ID n'existe pas");
			}else {
				System.out.println(commentaires.toString());
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
