package at.aau.connectapp.beans;

public class Company extends Aussteller {
	private String person;
	private String branche;
	private String standorte;
	private String mitarbeiter;
	private String einstellungen;
	private String einsatzbereiche;
	private String photo_name;
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getBranche() {
		return branche;
	}
	public void setBranche(String branche) {
		this.branche = branche;
	}
	public String getStandorte() {
		return standorte;
	}
	public void setStandorte(String standorte) {
		this.standorte = standorte;
	}
	public String getMitarbeiter() {
		return mitarbeiter;
	}
	public void setMitarbeiter(String mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}
	public String getEinstellungen() {
		return einstellungen;
	}
	public void setEinstellungen(String einstellungen) {
		this.einstellungen = einstellungen;
	}
	public String getEinsatzbereiche() {
		return einsatzbereiche;
	}
	public void setEinsatzbereiche(String einsatzbereiche) {
		this.einsatzbereiche = einsatzbereiche;
	}
	public String getPhoto_name() {
		return photo_name;
	}
	public void setPhoto_name(String photo_name) {
		this.photo_name = photo_name;
	}	
}
