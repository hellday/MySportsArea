package beans;

public class Utilisateur {
	
	private int idUser;
	private String nomUser;
	private String prenomUser;
	private String login;
	private String mdp;
	private String adresseUser;
	private String telUser;
	
	
	
	public Utilisateur(int idUser, String nomUser, String prenomUser, String login, String mdp, String adresseUser,
			String telUser) {
		super();
		this.idUser = idUser;
		this.nomUser = nomUser;
		this.prenomUser = prenomUser;
		this.login = login;
		this.mdp = mdp;
		this.adresseUser = adresseUser;
		this.telUser = telUser;
	}
	
	public Utilisateur(){
		this(0, "", "", "", "", "", "");
	}
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getNomUser() {
		return nomUser;
	}
	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}
	public String getPrenomUser() {
		return prenomUser;
	}
	public void setPrenomUser(String prenomUser) {
		this.prenomUser = prenomUser;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getAdresseUser() {
		return adresseUser;
	}
	public void setAdresseUser(String adresseUser) {
		this.adresseUser = adresseUser;
	}
	public String getTelUser() {
		return telUser;
	}
	public void setTelUser(String telUser) {
		this.telUser = telUser;
	}
	
	@Override
	public String toString() {
		return " [idUser=" + idUser + ", nomUser=" + nomUser + ", prenomUser=" + prenomUser + ", login="
				+ login + ", mdp=" + mdp + ", adresseUser=" + adresseUser + ", telUser=" + telUser
				+ "]";
	}
	
}


