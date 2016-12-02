package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Admin;
import common.DBAction;

public class AdminDAO {
	public static Admin getAdmin(String id) throws SQLException {
        DBAction.DBConnexion();
        Admin Resultat  = new Admin();
        String                 req       = ("SELECT * FROM admin WHERE idAdmin='" + id + "'");

        try {
            DBAction.setRes(DBAction.getStm().executeQuery(req));
        } catch (SQLException ex) {
        	System.out.println(ex.getErrorCode());
        }

        while (DBAction.getRes().next()) {
        	Resultat.setPseudo(DBAction.getRes().getString(1));
        	Resultat.setLogAdmin(DBAction.getRes().getString(2));
        	Resultat.setMdpAdmin(DBAction.getRes().getString(3));
        }

        DBAction.DBClose();

        return Resultat;
    }   
	
	public static ArrayList getAllAdmin() throws SQLException {
		return null;
    }   
	
	public static int setAdmin(int id, String newPseudo, String newLogAdmin, String newMdpAdmin) throws SQLException {
		return 1;
    }   

	public static int setAdmin(int id, Admin adminToAdd) throws SQLException {
		return 1;
    }   

}
