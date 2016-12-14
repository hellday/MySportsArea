package beans;

public class Partenaire {
	private int idPartenaire;
	private int idUser;
	private int idSport;
	private String pr�f�rences;
	private String disponibilit�s;
	
	
	//Constructeur
	
	public Partenaire(int idPartenaire, int idUser, int idSport, String pr�f�rences, String disponibilit�s) {
		super();
		this.idPartenaire = idPartenaire;
		this.idUser = idUser;
		this.idSport = idSport;
		this.pr�f�rences = pr�f�rences;
		this.disponibilit�s = disponibilit�s;
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

	public String getPr�f�rences() {
		return pr�f�rences;
	}

	public void setPr�f�rences(String pr�f�rences) {
		this.pr�f�rences = pr�f�rences;
	}

	public String getDisponibilit�s() {
		return disponibilit�s;
	}

	public void setDisponibilit�s(String disponibilit�s) {
		this.disponibilit�s = disponibilit�s;
	}
	
	@Override
	public String toString() {
		return " [idPartenaire=" + idPartenaire + ", idUser=" + idUser + ", idSport=" + idSport + ", pr�f�rences="
				+ pr�f�rences + ", disponibilit�s=" + disponibilit�s + "]";
	}
	
}
