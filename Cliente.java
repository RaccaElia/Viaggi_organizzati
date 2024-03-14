package viaggi_organizzati;

import java.util.LinkedList;

public class Cliente implements Comparable<Cliente>{

	private String codice;
	private String nome;
	private String cognome;
	private String dataNascita;
	LinkedList<String> prenotazioni = new LinkedList<String>();
	
	public String getCodice() {
		return codice;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getDataDiNascita() {
		return dataNascita;
	}

	public Cliente(String codice, String nome, String cognome, String dataNascita) {
		this.codice = codice;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
	}

	@Override
	public int compareTo(Cliente o) {
		return this.codice.compareTo(o.codice);
	}
	
}
