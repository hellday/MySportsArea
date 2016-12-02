package beans;

public class Event {
	private int idEvent;
	private int idSport;
	private int idLieux;
	private String nomEvent;
	private String dateEvent;
	private String descriptionEvent;
	private String typeEvent;
	
	
	//Constructeur
	
	
	public Event(){
		this(0, 0, 0, "", "", "", "");
	}


	public Event(int idEvent, int idSport, int idLieux, String nomEvent, String dateEvent, String descriptionEvent,
			String typeEvent) {
		super();
		this.idEvent = idEvent;
		this.idSport = idSport;
		this.idLieux = idLieux;
		this.nomEvent = nomEvent;
		this.dateEvent = dateEvent;
		this.descriptionEvent = descriptionEvent;
		this.typeEvent = typeEvent;
	}

	
	//Getter & Setter
	public int getIdEvent() {
		return idEvent;
	}


	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}


	public int getIdSport() {
		return idSport;
	}


	public void setIdSport(int idSport) {
		this.idSport = idSport;
	}


	public int getIdLieux() {
		return idLieux;
	}


	public void setIdLieux(int idLieux) {
		this.idLieux = idLieux;
	}


	public String getNomEvent() {
		return nomEvent;
	}


	public void setNomEvent(String nomEvent) {
		this.nomEvent = nomEvent;
	}


	public String getDateEvent() {
		return dateEvent;
	}


	public void setDateEvent(String dateEvent) {
		this.dateEvent = dateEvent;
	}


	public String getDescriptionEvent() {
		return descriptionEvent;
	}


	public void setDescriptionEvent(String descriptionEvent) {
		this.descriptionEvent = descriptionEvent;
	}


	public String getTypeEvent() {
		return typeEvent;
	}


	public void setTypeEvent(String typeEvent) {
		this.typeEvent = typeEvent;
	}
	
	
	
	
}
