package beans;

public class Admin {
	private int idAdmin;
	private String pseudo;
	private String logAdmin;
	private String mdpAdmin;
	
	//Constructeur
	
	public Admin(int idAdmin, String pseudo, String logAdmin, String mdpAdmin) {
		super();
		this.idAdmin = idAdmin;
		this.pseudo = pseudo;
		this.logAdmin = logAdmin;
		this.mdpAdmin = mdpAdmin;
	}



	public Admin(){
		this(0, "", "", "");
	}

	//Getter & Setter

	public int getIdAdmin() {
		return idAdmin;
	}



	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}



	public String getPseudo() {
		return pseudo;
	}



	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}



	public String getLogAdmin() {
		return logAdmin;
	}



	public void setLogAdmin(String logAdmin) {
		this.logAdmin = logAdmin;
	}



	public String getMdpAdmin() {
		return mdpAdmin;
	}



	public void setMdpAdmin(String mdpAdmin) {
		this.mdpAdmin = mdpAdmin;
	}

	
	
	
	
	

}