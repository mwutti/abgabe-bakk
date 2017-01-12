package at.aau.connectapp.beans;

public class Minimal {
	private int id;
	private String name;
	private String beschreibung;
	private String email;
	private String type;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Minimal(){
		
	}

	public void setType(String type) {
		this.type = type;
		
	}

}
