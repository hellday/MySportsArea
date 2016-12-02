package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Commentaire;
import common.DBAction;

public class CommentaireDAO {
	public static Commentaire getCommentaire(String id) throws SQLException {
        DBAction.DBConnexion();
        Commentaire Resultat  = new Commentaire();
        String                 req       = ("SELECT * FROM commentaire WHERE idCommentaire='" + id + "'");

        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Resultat.setIdUser(DBAction.getRes().getInt(1));
        	Resultat.setContenu(DBAction.getRes().getString(2));
        	Resultat.setIdLieux(DBAction.getRes().getInt(3));
        }

        DBAction.DBClose();

        return Resultat;
    }   
	
	public static ArrayList getAllCommentaire() throws SQLException {
		return null;
    }   
	
	public static int setCommentaire(int id, int newIdUser, String newContenu, int newIdLieux) throws SQLException {
		return 1;
    }   

	public static int setCommentaire(int id, Commentaire commentaireToAdd) throws SQLException {
		return 1;
    }   

}
