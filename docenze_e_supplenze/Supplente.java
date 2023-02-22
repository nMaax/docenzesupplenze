package docenze_e_supplenze;

public class Supplente extends Docente {
	
	Docente docentePrincipale;
	
	public Supplente(int matricola, String nome, String cognome) {
		super(matricola, nome, cognome);
	}
	
	public Supplente(int matricola, String nome, String cognome, Docente docentePrincipale) {
		super(matricola, nome, cognome, docentePrincipale.getMateria());
		this.docentePrincipale = docentePrincipale;
	}
	
	public Supplente(int matricola, String nome, String cognome, String materia, Docente docentePrincipale) {
		super(matricola, nome, cognome, materia);
		this.docentePrincipale = docentePrincipale;
	}

	public int getMatricolaDocentePrincipale() {
		return docentePrincipale.getMatricola();
	}
	
	public void setDocentePrincipale(Docente docente) {
		this.docentePrincipale = docente;
	}
	
}
