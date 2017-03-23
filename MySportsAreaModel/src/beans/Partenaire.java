package beans;

public class Partenaire {
	private int idPartenaire;
	private int idUser;
	private int idSport;
	private String préférences;
	private String disponibilités;
	
	
	//Constructeur
	
	public Partenaire(int idPartenaire, int idUser, int idSport, String préférences, String disponibilités) {
		super();
		this.idPartenaire = idPartenaire;
		this.idUser = idUser;
		this.idSport = idSport;
		this.préférences = préférences;
		this.disponibilités = disponibilités;
	}
	
	public Partenaire(){
		this(0, 0, 0, "", "");
	}

	//Getter & Setter
	
	public int getIdPartenaire() {
		return idPartenaire;
	}

	public void setIdPartenaire(int idPartenaire) {
		this.idPartenaire = idPartenaire;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdSport() {
		return idSport;
	}

	public void setIdSport(int idSport) {
		this.idSport = idSport;
	}

	public String getpréférences() {
		return préférences;
	}

	public void setPréférences(String préférences) {
		this.préférences = préférences;
	}

	public String getDisponibilités() {
		return disponibilités;
	}

	public void setDisponibilités(String disponibilités) {
		this.disponibilités = disponibilités;
	}
	
	@Override
	public String toString() {
		return " [idPartenaire=" + idPartenaire + ", idUser=" + idUser + ", idSport=" + idSport + ", préférences="
				+ préférences + ", disponibilités=" + disponibilités + "]";
	}
	
}
