package at.aau.connectapp.beans;

public class Impulsvortrag {
	private long id;
	private String name;
	private String beschreibung;
	private String von;
	private String bis;
	private String standNr;
	private Stand stand;
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getVon() {
		return von;
	}
	public void setVon(String von) {
		this.von = von;
	}
	public String getBis() {
		return bis;
	}
	public void setBis(String bis) {
		this.bis = bis;
	}
	public Stand getStand() {
		return stand;
	}
	public void setStand(Stand stand) {
		this.stand = stand;
	}
	public String getStandNr() {
		return standNr;
	}
	public void setStandNr(String standNr) {
		this.standNr = standNr;
	}
	
	

}
