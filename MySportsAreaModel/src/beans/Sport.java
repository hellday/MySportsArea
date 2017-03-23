package beans;

public class Sport {
	private int idSport;
	private String nomSport;
	
	//Constructeur
	public Sport(int idSport, String nomSport) {
		super();
		this.idSport = idSport;
		this.nomSport = nomSport;
	}
	
	public Sport(){
		this(0,"");
	}

	//Getter & Setter
	public int getIdSport() {
		return idSport;
	}

	public void setIdSport(int idSport) {
		this.idSport = idSport;
	}

	public String getNomSport() {
		return nomSport;
	}

	public void setNomSport(String nomSport) {
		this.nomSport = nomSport;
	}
	
	@Override
	public String toString() {
		return " [idSport=" + idSport + ", nomSport=" + nomSport + "]";
	}
	
	

}
