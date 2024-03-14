package viaggi_organizzati;

import java.util.*;

public class TourOperator {

	LinkedList<Viaggio> viaggi = new LinkedList<Viaggio>();
	TreeMap<Viaggio, TreeMap<Integer, String>> programmaViaggi = new TreeMap<Viaggio, TreeMap<Integer,String>>();
	LinkedList<Partenza> partenze = new LinkedList<Partenza>();
	LinkedList<Cliente> clienti = new LinkedList<Cliente>();
	int numClienti = 0;
	TreeMap<String, LinkedList<Cliente>> prenotazioni = new TreeMap<String, LinkedList<Cliente>>();
	
	public Viaggio definisciViaggio(String identificativoViaggio, String descrizione, int durata) {
		for(Viaggio v: viaggi)	{
			if(v.getIdentificativo().equals(identificativoViaggio))	{
				v.setDescrizione(descrizione);
				v.setDurata(durata);
				return v;
			}
		}
		Viaggio ris = new Viaggio(identificativoViaggio, descrizione, durata);
		viaggi.add(ris);
		programmaViaggi.put(ris, null);
		return ris;
	}
	
	public Viaggio cercaViaggioDatoIdentificativo(String identificativoViaggio) {
		for(Viaggio v: viaggi)	{
			if(v.getIdentificativo().equals(identificativoViaggio))	{
				return v;
			}
		}
		return null;
	}
	
	public Collection<Viaggio> cercaInDescrizioneViaggio(String daCercare) {
		if (daCercare.equals("")) {
			LinkedList<Viaggio> ris = new LinkedList<Viaggio>(viaggi);
			Collections.sort(ris);
			return ris;
		}
		LinkedList<Viaggio> ris = new LinkedList<Viaggio>();
		for(Viaggio v:viaggi)	{
			if(v.getDescrizione().toLowerCase().contains(daCercare.toLowerCase()))	{
				ris.add(v);
			}
		}
		Collections.sort(ris);
		return ris;
	}

	public Collection<Viaggio> elencoViaggiPerDurata() {
		LinkedList<Viaggio> listaTemp = new LinkedList<Viaggio>(viaggi);
		Collections.sort(listaTemp, new ComparatorViaggiDurata());
		return listaTemp;
	}
	
	public boolean descriviGiorno(String identificativoViaggio, int numeroGiorno, String descrizioneGiorno) {
		if(cercaViaggioDatoIdentificativo(identificativoViaggio) == null)	{
			return false;
		}
		Viaggio v = cercaViaggioDatoIdentificativo(identificativoViaggio);
		if(numeroGiorno>v.getDurata())	{
			return false;
		}
		if(programmaViaggi.get(v)==null)
			return false;
		if(programmaViaggi.get(v).get(Integer.valueOf(numeroGiorno)) != null)	{
			return false;
		}
		programmaViaggi.get(v).put(numeroGiorno, descrizioneGiorno);
		return true;
	}
	
	public String programmaViaggio(String identificativoViaggio) {
		Viaggio v = cercaViaggioDatoIdentificativo(identificativoViaggio);
		String ris = "";
		if (v != null) {
			for(Integer giorno: programmaViaggi.get(v).keySet())	{
				ris+=giorno+" "+programmaViaggi.get(v).get(giorno)+"\n";
			}
		}
		return ris.substring(0, ris.length()-1);
	}
	
	public void aggiungiPartenza(String identificativoViaggio, String dataPartenza, String aeroportoPartenza, double prezzo) {
		boolean flag = false;
		for(Partenza p: partenze)	{
			if (p.data.equals(dataPartenza) && p.siglaAeroporto.equals(aeroportoPartenza)) {
				flag = true;
			}
		}
		if(flag == false)	{
			Partenza partenza = new Partenza(identificativoViaggio, dataPartenza, aeroportoPartenza, prezzo);
			partenze.add(partenza);
		}
	}
	
	public String partenzeViaggioPerPrezzoData(String identificativoViaggio){
		LinkedList<Partenza> listaTemp = new LinkedList<Partenza>();
		for(Partenza p: partenze)	{
			if(p.identificativoViaggio.equals(identificativoViaggio))	{
				listaTemp.add(p);
			}
		}
		Collections.sort(listaTemp, new ComparatorPartenzePrezzoData());
		String ris = "";
		for(Partenza p: listaTemp)	{
			ris+=p.data+" "+p.siglaAeroporto+" "+p.prezzoPersona+"\n";
		}
		return ris.substring(0, ris.length()-1);
	}
	
	public String partenzeViaggioPerDataPrezzo(String identificativoViaggio){
		LinkedList<Partenza> listaTemp = new LinkedList<Partenza>();
		for(Partenza p: partenze)	{
			if(p.identificativoViaggio.equals(identificativoViaggio))	{
				listaTemp.add(p);
			}
		}
		Collections.sort(listaTemp, new ComparatorPartenzeDataPrezzo());
		String ris = "";
		for(Partenza p: listaTemp)	{
			ris+=p.data+" "+p.siglaAeroporto+" "+p.prezzoPersona+"\n";
		}
		return ris.substring(0, ris.length()-1);
	}
	
	public Cliente nuovoCliente(String nome, String cognome, String dataNascita, String email) {
		numClienti++;
		String codice;
		if(numClienti<10)	{
			codice = "C0000"+numClienti;
		}
		else if (numClienti<100) {
			codice = "C000"+numClienti;
		}
		else {
			codice = "C00"+numClienti;
		}
		Adulto ris = new Adulto(codice, nome, cognome, dataNascita, email);
		clienti.add(ris);
		return ris;
	}
	
	public Cliente nuovoCliente(String nome, String cognome, String dataNascita) {
		numClienti++;
		String codice;
		if(numClienti<10)	{
			codice = "C0000"+numClienti;
		}
		else if (numClienti<100) {
			codice = "C000"+numClienti;
		}
		else {
			codice = "C00"+numClienti;
		}
		Bambino ris = new Bambino(codice, nome, cognome, dataNascita);
		clienti.add(ris);
		return ris;
	}
	
	public Cliente cercaCliente(String codiceCliente)	{
		for(Cliente c: clienti)	{
			if(c.getCodice().equals(codiceCliente))	{
				return c;
			}
		}
		return null;
	}
	
	public Collection<Cliente> elencoClienti() {
		LinkedList<Cliente> listaTemp = new LinkedList<Cliente>(clienti);
		Collections.sort(listaTemp);
		return listaTemp;
	}

	public Collection<Cliente> elencoClientiAdulti() {
		LinkedList<Cliente> listaTemp = new LinkedList<Cliente>();
		for(Cliente c: clienti)	{
			if(c instanceof Adulto)	{
				listaTemp.add(c);
			}
		}
		Collections.sort(listaTemp);
		return listaTemp;
	}
	
	public String prenotaViaggio(String codiceClienteIntestatario, String identificativoViaggio, String dataPartenza, String aeroportoPartenza) throws EccezioneDataPartenzaNonEsistente, EccezioneAeroportoPartenzaNonEsistente {
		if(cercaCliente(codiceClienteIntestatario) == null)	{
			return null;
		}
		Cliente cliente = cercaCliente(codiceClienteIntestatario);
		if(cliente instanceof Adulto)	{
			boolean flagViaggio = false;
			for(Viaggio v: viaggi)	{
				if(v.getIdentificativo().equals(identificativoViaggio))		{
					flagViaggio = true;
				}
			}
			if(flagViaggio == false)	{
				return null;
			}
			boolean dataFlag = false;
			boolean aereoFlag = false;
			for(Partenza p: partenze)	{
				if(p.identificativoViaggio.equals(identificativoViaggio))	{
					if(p.data.equals(dataPartenza))	{
						dataFlag = true;
						if(p.siglaAeroporto.equals(aeroportoPartenza))	{
							aereoFlag = true;
						}
					}
				}
			}
			if(dataFlag == false)	{
				throw new EccezioneDataPartenzaNonEsistente();
			}
			if(aereoFlag == false)	{
				throw new EccezioneAeroportoPartenzaNonEsistente();
			}
			String codicePrenotazione = codiceClienteIntestatario+"-"+identificativoViaggio+"-"+dataPartenza+"-"+aeroportoPartenza;
			LinkedList<Cliente> clienteIntestatario = new LinkedList<Cliente>();
			clienteIntestatario.add(cliente);
			prenotazioni.put(codicePrenotazione, clienteIntestatario);
			cliente.prenotazioni.add(codicePrenotazione);
		}
		return null;
	}

	public void aggiungiClienteAPrenotazione(String codicePrenotazione, String codiceCliente) {
		for(String codice: prenotazioni.keySet())	{
			if(codice.equals(codicePrenotazione))	{
				boolean flag = false;
				for(Cliente c: prenotazioni.get(codice))	{
					if(c.getCodice().equals(codiceCliente))	{
						flag = true;
					}
				}
				if(flag==false)	{
					if(cercaCliente(codiceCliente)!=null)	{
						prenotazioni.get(codice).add(cercaCliente(codiceCliente));
						cercaCliente(codiceCliente).prenotazioni.add(codicePrenotazione);
					}	
				}
			}
		}
	}
	
	public Collection<Cliente> elencoClientiPrenotazione(String codicePrenotazione){
		return prenotazioni.get(codicePrenotazione);
	}
	
	public Collection<String> elencoPrenotazioniCliente(String codiceCliente){
		if(cercaCliente(codiceCliente)!=null)	{
			Cliente cliente = cercaCliente(codiceCliente);
			LinkedList<String> ris = new LinkedList<String>(cliente.prenotazioni);
			Collections.sort(ris);
			return ris;
		}
		return null;
	}

	
	public double incassoPerPrenotazione(String codicePrenotazione) {
		double tot = 0.0;
		LinkedList<Cliente> listaClienti = prenotazioni.get(codicePrenotazione);
		String codiceViaggio = codicePrenotazione.split("-")[1];
		Partenza partenza = null;
		for(Partenza p:partenze)	{
			if(p.identificativoViaggio.equals(codiceViaggio))
				partenza = p;
		}
		for(Cliente c: listaClienti)	{
			if(c instanceof Adulto)
				tot+=partenza.prezzoPersona;
			if(c instanceof Bambino)
				tot+=partenza.prezzoPersona/2;
		}
		return tot;
	}
	
	public double incassoPerViaggio(String identificativoViaggio) {
		return -1.0;
	}
}





