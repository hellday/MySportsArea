package beans;

public class Lieux {
	private int idLieux;
	private int idSport;
	private String nomLieux;
	private float latitude, longitude;
	private String typeLieux;
	
	
	//Constructeur
	public Lieux(int idLieux, int idSport, String nomLieux, float latitude, float longitude, String typeLieux) {
		super();
		this.idLieux = idLieux;
		this.idSport = idSport;
		this.nomLieux = nomLieux;
		this.latitude = latitude;
		this.longitude = longitude;
		this.typeLieux = typeLieux;
	}
	
	public Lieux(){
		this(0, 0, "", 0, 0, "");
	}


	//Getter & Setter
	public int getIdLieux() {
		return idLieux;
	}


	public void setIdLieux(int idLieux) {
		this.idLieux = idLieux;
	}


	public int getIdSport() {
		return idSport;
	}


	public void setIdSport(int idSPort) {
		this.idSport = idSPort;
	}


	public String getNomLieux() {
		return nomLieux;
	}


	public void setNomLieux(String nomLieux) {
		this.nomLieux = nomLieux;
	}


	public float getLatitude() {
		return latitude;
	}


	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}


	public float getLongitude() {
		return longitude;
	}


	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}


	public String getTypeLieux() {
		return typeLieux;
	}


	public void setTypeLieux(String typeLieux) {
		this.typeLieux = typeLieux;
	}
	
	
	

}
