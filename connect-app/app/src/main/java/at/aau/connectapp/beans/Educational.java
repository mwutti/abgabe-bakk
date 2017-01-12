package at.aau.connectapp.beans;

public class Educational extends Aussteller{
	private String person;
	private String abschluss;
	private String zulassung;
	private String studiengaenge;
	private String standorte;
	
	private String photo_name;
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	
	public String getAbschluss() {
		return abschluss;
	}
	public void setAbschluss(String abschluss) {
		this.abschluss = abschluss;
	}
	public String getZulassung() {
		return zulassung;
	}
	public void setZulassung(String zulassung) {
		this.zulassung = zulassung;
	}
	public String getStudiengaenge() {
		return studiengaenge;
	}
	public void setStudiengaenge(String studiengaenge) {
		this.studiengaenge = studiengaenge;
	}
	public String getStandorte() {
		return standorte;
	}
	public void setStandorte(String standorte) {
		this.standorte = standorte;
	}
	
	public String getPhoto_name() {
		return photo_name;
	}
	public void setPhoto_name(String photo_name) {
		this.photo_name = photo_name;
	}
}
