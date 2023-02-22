package docenze_e_supplenze;

public class Docente {
	
	int matricola;
	String nome;
	String cognome;
	String materia;
	
	public Docente(int matricola, String nome, String cognome) {
		super();
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
	}
	
	public Docente(int matricola, String nome, String cognome, String materia) {
		super();
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.materia = materia;
	}

	public int getMatricola() {
		return matricola;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}
	
	public String getMateria() {
		return materia;
	}
	
	public void setMateria(String materia) {
		this.materia = materia;
	}
	
}
