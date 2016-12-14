package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Admin;
import beans.Commentaire;
import common.DBAction;

public class AdminDAO {
	public static Admin getAdmin(int id) throws SQLException {
        DBAction.DBConnexion();
        Admin Resultat  = new Admin();
        String                 req       = ("SELECT * FROM admin WHERE idAdmin='" + id + "'");

        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Resultat.setIdAdmin(DBAction.getRes().getInt(1));
        	Resultat.setPseudo(DBAction.getRes().getString(2));
        	Resultat.setLogAdmin(DBAction.getRes().getString(3));
        	Resultat.setMdpAdmin(DBAction.getRes().getString(4));
        }

        DBAction.DBClose();

        return Resultat;
    }   
	
	public static ArrayList getAllAdmin() throws SQLException {
		
		DBAction.DBConnexion();
		ArrayList<Admin> admins = new ArrayList<Admin>();
		String req = ("SELECT * FROM admin");
        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Admin Resultat = new Admin(DBAction.getRes().getInt(1), 
        			DBAction.getRes().getString(2), 
        			DBAction.getRes().getString(3), 
        			DBAction.getRes().getString(4));
        	admins.add(Resultat);
        }

        DBAction.DBClose();

        return admins;
		
    }   
	
	public static int setAdmin(int id, String newPseudo, String newLogAdmin, String newMdpAdmin) throws SQLException {
		return 1;
    }   

	public static int setAdmin(int id, Admin adminToAdd) throws SQLException {
		return 1;
    }   

}
