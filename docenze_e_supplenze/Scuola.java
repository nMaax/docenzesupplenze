package docenze_e_supplenze;

public class Scuola implements Comparable<Scuola> {
	
	String codiceScuola;
	String nomeScuola;
	String indirizzoScuola;
	
	public Scuola(String codiceScuola, String nomeScuola, String indirizzoScuola) {
		super();
		this.codiceScuola = codiceScuola;
		this.nomeScuola = nomeScuola;
		this.indirizzoScuola = indirizzoScuola;
	}

	public String getCodiceScuola() {
		return codiceScuola;
	}

	public String getNomeScuola() {
		return nomeScuola;
	}

	public String getIndirizzoScuola() {
		return indirizzoScuola;
	}
	
	public void aggiornaScuola(String nuovoNome, String nuovoIndirizzo) {
		this.nomeScuola = nuovoNome;
		this.indirizzoScuola = nuovoIndirizzo;
	}

	@Override
	public int compareTo(Scuola o) {
		return this.codiceScuola.compareTo(o.getCodiceScuola());
	}
	
}
