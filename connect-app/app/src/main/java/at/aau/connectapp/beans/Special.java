package at.aau.connectapp.beans;

public class Special {
	private long id;
	private String beschreibung;
	private String standNR;
	private Stand stand;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public Stand getStand() {
		return stand;
	}
	public void setStand(Stand stand) {
		this.stand = stand;
	}
	public String getStandNR() {
		return standNR;
	}
	public void setStandNR(String standNR) {
		this.standNR = standNR;
	}

}
