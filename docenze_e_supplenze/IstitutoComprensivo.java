package docenze_e_supplenze;

import java.util.*;

public class IstitutoComprensivo {
	
	String codiceIstituto;
	String nomeIstituto;
	String indirizzoIstituto;
	
	LinkedList<Scuola> scuole = new LinkedList<Scuola>();
	LinkedList<Classe> classi = new  LinkedList<Classe>();
	LinkedList<Docente> docenti = new LinkedList<Docente>();
	LinkedList<Supplente> supplenti = new LinkedList<Supplente>();
	
	TreeMap<String, LinkedList<Classe>> mappaCodiceScuolaToClassi = new TreeMap<String, LinkedList<Classe>>();
	TreeMap<String, LinkedList<Docente>> mappaCodiceClasseToDocenti = new TreeMap<String, LinkedList<Docente>>();
	
	TreeMap<Integer, Integer> mappaMatricolaDocenteToMatricolaSupplente = new TreeMap<Integer, Integer>();
	TreeMap<Integer, Integer> mappaMatricolaSupplenteToMatricolaDocente = new TreeMap<Integer, Integer>();

	public void configuraIstitutoComprensivo(String codiceIstituto, String nomeIstituto, String indirizzoIstituto) {
		this.codiceIstituto = codiceIstituto;
		this.nomeIstituto = nomeIstituto;
		this.indirizzoIstituto = indirizzoIstituto;
	}
	
	public String stampaIstitutoComprensivo() {
		return codiceIstituto + ";" + nomeIstituto + ";" + indirizzoIstituto; 
	}
	
	public Scuola aggiungiScuola(String codiceScuola, String nomeScuola, String indirizzoScuola) {
		Scuola scuola = cercaScuolaCodice(codiceScuola);
		if (scuola == null) {
			Scuola nuovaScuola = new Scuola(codiceScuola, nomeScuola, indirizzoScuola);
			scuole.add(nuovaScuola);
		} else {
			scuola.aggiornaScuola(nomeScuola, indirizzoScuola);
		}
		scuola = cercaScuolaCodice(codiceScuola);
		return scuola;
	}
	
	public Scuola cercaScuolaCodice(String codiceScuola) {
		for(Scuola scuola : scuole) {
			if (scuola.getCodiceScuola().equals(codiceScuola)) {
				return scuola;
			}
		}
		return null; 
	}
	
	public Collection<Scuola> elencoScuolePerCodice(){
		LinkedList<Scuola> cache = new LinkedList<Scuola>(scuole);
		Collections.sort(cache);
		return cache;
	}
	
	public Collection<Scuola> elencoScuolePerNome(){
		LinkedList<Scuola> cache = new LinkedList<Scuola>(scuole);
		Collections.sort(cache, new comparatoreScuoleNome());
		return cache;
	}
	
	public String aggiungiClasseAScuola(String codiceScuola, String grado, int anno) {
		
		/*
		 * Estraggo l'ultima classe di quella scuola, a quel grado, a quel anno
		 * 
		 * Se la lista è vuota assegno A
		 * Altrimenti in base al carattere passo al successivo
		 * 
		 * Creo la nuova classe e la aggiungo alle strutture dati
		 * */
		
		Scuola scuola = cercaScuolaCodice(codiceScuola);
		if (scuola == null) return null;
		
		LinkedList<Classe> elencoClassiScuolaGradoAnno = elencoClassiScuolaPerCodice(codiceScuola, grado, anno);
		if (elencoClassiScuolaGradoAnno == null) return null;
		
		char nuovaSezione;
		if (elencoClassiScuolaGradoAnno.size() == 0) {
			nuovaSezione = 'A';
		} else {
			Classe ultimaClasse = elencoClassiScuolaGradoAnno.getLast();
			char ultimaSezione = ultimaClasse.getSezione();
			if (ultimaSezione == 'A') {
				nuovaSezione = 'B';
			} else if (ultimaSezione == 'B') {
				nuovaSezione = 'C';
			} else {
				nuovaSezione = 'D';
			}
		}
		
		Classe nuovaClasse = new Classe(scuola, grado, anno, nuovaSezione);
		
		classi.add(nuovaClasse);
		
		if (!mappaCodiceScuolaToClassi.containsKey(codiceScuola)) {
			mappaCodiceScuolaToClassi.put(codiceScuola, new LinkedList<Classe>());
		}
		mappaCodiceScuolaToClassi.get(codiceScuola).add(nuovaClasse);
		
		mappaCodiceClasseToDocenti.put(nuovaClasse.getCodiceClasse(), new LinkedList<Docente>());
		return nuovaClasse.getCodiceClasse();
	}
	
	public Collection<Classe> elencoClassiScuolaPerCodice(String codiceScuola){
		if (cercaScuolaCodice(codiceScuola) == null) return null;
		LinkedList<Classe> cache = new LinkedList<Classe>(mappaCodiceScuolaToClassi.get(codiceScuola));
		Collections.sort(cache);
		return cache;
	}
	
	public Collection<Classe> elencoClassiScuolaPerCodice(String codiceScuola, String grado){
		if (cercaScuolaCodice(codiceScuola) == null) return null;
		LinkedList<Classe> cache = new LinkedList<Classe>();
		if (!mappaCodiceScuolaToClassi.containsKey(codiceScuola)) {
			mappaCodiceScuolaToClassi.put(codiceScuola, new LinkedList<Classe>());
		}
		for (Classe classe : mappaCodiceScuolaToClassi.get(codiceScuola)) {
			if (classe.getGrado().equals(grado)) {
				cache.add(classe);
			}
		}
		Collections.sort(cache);
		return cache;
	}
	
	public Collection<Classe> elencoClassiScuolaPerCodice(String codiceScuola, int anno){
		if (cercaScuolaCodice(codiceScuola) == null) return null;
		LinkedList<Classe> cache = new LinkedList<Classe>();
		if (!mappaCodiceScuolaToClassi.containsKey(codiceScuola)) {
			mappaCodiceScuolaToClassi.put(codiceScuola, new LinkedList<Classe>());
		}
		for (Classe classe : mappaCodiceScuolaToClassi.get(codiceScuola)) {
			if (classe.getAnno() == anno) {
				cache.add(classe);
			}
		}
		Collections.sort(cache);
		return cache;
	}
	
	public LinkedList<Classe> elencoClassiScuolaPerCodice(String codiceScuola, String grado, int anno){
		if (cercaScuolaCodice(codiceScuola) == null) return null;
		LinkedList<Classe> cache = new LinkedList<Classe>();
		if (!mappaCodiceScuolaToClassi.containsKey(codiceScuola)) {
			mappaCodiceScuolaToClassi.put(codiceScuola, new LinkedList<Classe>());
		}
		for (Classe classe : mappaCodiceScuolaToClassi.get(codiceScuola)) {
			if (classe.getGrado().equals(grado) && classe.getAnno() == anno) {
				cache.add(classe);
			}
		}
		Collections.sort(cache);
		return cache;
	}
	
	public void assegnaOraMateriaAClasse(String codiceClasse, int giorno, int ora, String materia) throws EccezioneOraMateriaGiaAssegnata {
		Classe classe = cercaClasse(codiceClasse);
		if (!(classe == null || giorno < 1 || giorno > 6 || ora < 1 || ora > 5)) {
			
			String materiaGiaAssegnata = classe.getMateriaOrario(giorno, ora);
			if (materiaGiaAssegnata != null) throw new EccezioneOraMateriaGiaAssegnata();
			
			classe.assegnaMateriaOrario(materia, giorno, ora);
		}
	}
	
	public String materiaClasseGiornoOra(String codiceClasse, int giorno, int ora) {
		Classe classe = cercaClasse(codiceClasse);
		if (classe == null) return null;
		return classe.getMateriaOrario(giorno, ora);
	}
	
	public Classe cercaClasse(String codiceClasse) {
		for (Classe classe : classi) {
			if (classe.getCodiceClasse().equals(codiceClasse)) {
				return classe;
			}
		}
		return null;
	}
	
	public Docente aggiungiDocente(int matricola, String nome, String cognome) {
		Docente docente = cercaDocente(matricola);
		if (docente != null) return docente;
		
		Docente nuovoDocente = new Docente(matricola, nome, cognome);
		docenti.add(nuovoDocente);
		return nuovoDocente;
	}
	
	public Docente cercaDocente(int matricola) {
		for (Docente docente : docenti) {
			if (docente.getMatricola() == matricola) {
				return docente;
			}
		}
		return null;
	}
	
	public void assegnaDocenteAClasseMateria(int matricola, String codiceClasse, String materia) {
		if (mappaCodiceClasseToDocenti.containsKey(codiceClasse)) {
			Docente docente = cercaDocente(matricola);
			docente.setMateria(materia);
			mappaCodiceClasseToDocenti.get(codiceClasse).add(docente);
		}
	}
	
	public Docente docenteClasseMateria(String codiceClasse, String materia) {
		if (cercaClasse(codiceClasse) == null) return null;
		if (!mappaCodiceClasseToDocenti.containsKey(codiceClasse)) return null;
		
		LinkedList<Docente> docentiClasse = new LinkedList<Docente>(mappaCodiceClasseToDocenti.get(codiceClasse));
		for (Docente docente : docentiClasse) {
			if (docente.getMateria().equals(materia)) {
				if (mappaMatricolaDocenteToMatricolaSupplente.containsKey(docente.getMatricola())) {
					return cercaSupplente(mappaMatricolaDocenteToMatricolaSupplente.get(docente.getMatricola()));
				}
				return docente;
			}
		}
		return null;
	}

	public Supplente assegnaSupplentePerDocente(int matricolaDocentePrincipale, int matricolaSupplente, String nomeSupplente, String cognomeSupplente) {
		Supplente supplente = cercaSupplente(matricolaSupplente);
		Docente docenteDaSostituire = cercaDocente(matricolaDocentePrincipale);
		if (docenteDaSostituire == null) return null;
		
		if (supplente == null) {
			Supplente nuovoSupplente = new Supplente(matricolaSupplente, nomeSupplente, cognomeSupplente, docenteDaSostituire);
			supplenti.add(nuovoSupplente);
			supplente = nuovoSupplente;
		}
		
		mappaMatricolaDocenteToMatricolaSupplente.put(matricolaDocentePrincipale, matricolaSupplente);
		mappaMatricolaSupplenteToMatricolaDocente.put(matricolaSupplente, matricolaDocentePrincipale);
		
		return supplente;
	}
	
	public Supplente cercaSupplente(int matricolaSupplente) {
		for (Supplente supplente : supplenti) {
			if (supplente.getMatricola() == matricolaSupplente) {
				return supplente;
			}
		}
		return null;
	}
	
	public Supplente supplenteDiDocentePrincipale(int matricolaDocentePrincipale) {
		Supplente supplente;
		try {
			supplente = cercaSupplente(mappaMatricolaDocenteToMatricolaSupplente.get(matricolaDocentePrincipale));
		} catch (Exception e) {
			supplente = null;
		}
		return supplente;
	}

	public Docente docentePrincipaleDiSupplente(int matricolaSupplente) {
		Docente docente;
		try {
			docente = cercaDocente(mappaMatricolaSupplenteToMatricolaDocente.get(matricolaSupplente));
		} catch (Exception e) {
			docente = null;
		}
		return docente;
	}

	public String stampaOrarioClasse(String codiceClasse) {
		Classe classe = cercaClasse(codiceClasse);
		String[] righeOrario = classe.stampaRigheOrario();
		String output = "";
		for (String giornoOrario : righeOrario) {
			String[] componentsOfOraio = giornoOrario.split(",");
			Docente docente = null;
			if (componentsOfOraio != null) {
				String materia = giornoOrario.split(",")[2];
				docente = docenteClasseMateria(codiceClasse, materia);
				output += giornoOrario + "," + docente.getMatricola() + "\n"; 
			} else {
				output += giornoOrario + "," + "null" + "\n";
			}
		}
		return output;
	}
	
}





