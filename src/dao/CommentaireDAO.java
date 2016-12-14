package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Commentaire;
import beans.Lieux;
import common.DBAction;

public class CommentaireDAO {
	public static Commentaire getCommentaire(int nb) throws SQLException {
        DBAction.DBConnexion();
        Commentaire Resultat  = new Commentaire();
        String                 req       = ("SELECT * FROM commentaire WHERE idCommentaire='" + nb + "'");

        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Resultat.setIdCommentaire(DBAction.getRes().getInt(1));
        	Resultat.setIdUser(DBAction.getRes().getInt(2));
        	Resultat.setContenu(DBAction.getRes().getString(3));
        	Resultat.setIdLieux(DBAction.getRes().getInt(4));
        }

        DBAction.DBClose();

        return Resultat;
    }   
	
	public static ArrayList getAllCommentaire() throws SQLException {
		
		DBAction.DBConnexion();
		ArrayList<Commentaire> commentaires = new ArrayList<Commentaire>();
		String req = ("SELECT * FROM commentaire");
        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Commentaire Resultat = new Commentaire(DBAction.getRes().getInt(1), 
        			DBAction.getRes().getInt(2), 
        			DBAction.getRes().getString(3), 
        			DBAction.getRes().getInt(4));
        	commentaires.add(Resultat);
        }

        DBAction.DBClose();

        return commentaires;
        
    }   
	
	public static int setCommentaire(int id, int newIdUser, String newContenu, int newIdLieux) throws SQLException {
		return 1;
    }   

	public static int setCommentaire(int id, Commentaire commentaireToAdd) throws SQLException {
		return 1;
    }   

}
