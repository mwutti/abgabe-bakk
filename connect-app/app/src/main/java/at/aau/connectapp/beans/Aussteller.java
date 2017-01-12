package at.aau.connectapp.beans;



public class Aussteller implements Comparable<Aussteller>{

	private int id;	;
	private String type;
	private String name;
	private String beschreibung;	
	private String adresse;
	private String telefon;
	private String internet;	
	private String email;
	private String stand_nummer;
	private String logo_name;
	private Integer is_favorite;
	private Stand stand;
	
	public Stand getStand() {
		return stand;
	}
	public void setStand(Stand stand) {
		this.stand = stand;
	}
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
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getInternet() {
		return internet;
	}
	public void setInternet(String internet) {
		this.internet = internet;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStand_nummer() {
		return stand_nummer;
	}
	public void setStand_nummer(String stand_nummer) {
		this.stand_nummer = stand_nummer;
	}
	public String getLogo_name() {
		return logo_name;
	}
	public void setLogo_name(String logo_name) {
		this.logo_name = logo_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public int compareTo(Aussteller another) {
		// TODO Auto-generated method stub
		return this.getName().toLowerCase().compareTo(another.getName().toLowerCase());
	}
	public Integer getIs_favorite() {
		return is_favorite;
	}
	public void setIs_favorite(Integer is_favorite) {
		this.is_favorite = is_favorite;
	}


}
