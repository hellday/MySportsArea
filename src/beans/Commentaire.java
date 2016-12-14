package beans;

public class Commentaire {
	private int idCommentaire;
	private int idUser;
	private String contenu;
	private int idLieux;
	
	//Constructeur
	
	public Commentaire(int idCommentaire, int idUser, String contenu, int idLieux) {
		super();
		this.idCommentaire = idCommentaire;
		this.idUser = idUser;
		this.contenu = contenu;
		this.idLieux = idLieux;
	}
	
	public Commentaire(){
		this(0, 0, "", 0);
	}

	//Getter & Setter
	
	public int getIdCommentaire() {
		return idCommentaire;
	}

	public void setIdCommentaire(int idCommentaire) {
		this.idCommentaire = idCommentaire;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public int getIdLieux() {
		return idLieux;
	}

	public void setIdLieux(int idLieux) {
		this.idLieux = idLieux;
	}
	
	@Override
	public String toString() {
		return " [idCommentaire=" + idCommentaire + ", idUser=" + idUser + ", contenu=" + contenu + ", idLieux="
				+ idLieux + "]";
	}
	
	
	
}
