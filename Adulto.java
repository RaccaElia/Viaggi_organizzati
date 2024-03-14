package viaggi_organizzati;

public class Adulto extends Cliente{

	private String email;

	public String getEmail() {
		return email;
	}
	
	public Adulto(String codice, String nome, String cognome, String dataNascita, String email) {
		super(codice, nome, cognome, dataNascita);
		this.email = email;
	}
}
