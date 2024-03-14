package viaggi_organizzati;

public class Viaggio implements Comparable<Viaggio>{

	private String identificativo;
	private String descrizione;
	private int durata;
	
	public String getIdentificativo() {
		return identificativo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public int getDurata() {
		return durata;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public Viaggio(String identificativo, String descrizione, int durata) {
		this.identificativo = identificativo;
		this.descrizione = descrizione;
		this.durata = durata;
	}

	@Override
	public int compareTo(Viaggio o) {
		return this.getIdentificativo().compareTo(o.getIdentificativo());
	}
}
