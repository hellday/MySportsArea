package beans;

public class Lieux {
	private int idLieux;
	private String idSport;
	private String nomLieux;
	private String description;
	private float latitude, longitude;
	private String status;
	private String typeLieux;
	
	
	//Constructeur
	public Lieux(int idLieux, String idSport, String nomLieux, String description, float latitude, float longitude, String status, String typeLieux) {
		super();
		this.idLieux = idLieux;
		this.idSport = idSport;
		this.nomLieux = nomLieux;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.status = status;
		this.typeLieux = typeLieux;
	}
	
	public Lieux(){
		this(0, "", "", "", 0, 0, "", "");
	}


	//Getter & Setter
	public int getIdLieux() {
		return idLieux;
	}


	public void setIdLieux(int idLieux) {
		this.idLieux = idLieux;
	}


	public String getIdSport() {
		return idSport;
	}


	public void setIdSport(String idSport) {
		this.idSport = idSport;
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


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTypeLieux(String typeLieux) {
		this.typeLieux = typeLieux;
	}
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return " [idLieux=" + idLieux + ", idSport=" + idSport + ", nomLieux=" + nomLieux + ", description=" + description + ", latitude="
				+ latitude + ", longitude=" + longitude + ", status=" + status + ", typeLieux=" + typeLieux + "]";
	}
	
	
	

}
