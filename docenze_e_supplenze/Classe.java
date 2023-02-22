package docenze_e_supplenze;

import java.util.*;

public class Classe implements Comparable<Classe>{
	
	Scuola scuola;
	String grado;
	int anno;
	char sezione;
	
	TreeMap<Integer, LinkedList<String>> orario = new TreeMap<Integer, LinkedList<String>>();
	
	public Classe(Scuola scuola, String grado, int anno, char sezione) {
		super();
		this.scuola = scuola;
		this.grado = grado;
		this.anno = anno;
		this.sezione = sezione;
		
		for (int i = 1; i <= 6; i++) {
			orario.put(i, new LinkedList<String>());
			// 5+1 perche voglio accedere alla ora in formato 1-based e non 0-based
			for (int j = 1; j <= 5+1; j++) {
				orario.get(i).add(null);
			}
		}
		
	}

	public String getCodiceClasse() {
		return scuola.getCodiceScuola() + "-" + grado + "-" + anno  + "-" + sezione;
	}

	public Scuola getScuola() {
		return scuola;
	}

	public String getGrado() {
		return grado;
	}

	public int getAnno() {
		return anno;
	}

	public char getSezione() {
		return sezione;
	}
	
	public String getMateriaOrario(int giorno, int ora) {
		String materia;
		try {
			materia = orario.get(giorno).get(ora);;
		} catch (Exception e) {
			materia = null;
		}
		return materia;
	}
	
	public void assegnaMateriaOrario(String materia, int giorno, int ora) {
		orario.get(giorno).add(ora, materia);
	}
	
	public String[] stampaRigheOrario() {
		String[] output = new String[6];
		for (int i = 0; i<6; i++) {
			for (int j=0; j<5; j++) {
				int giorno = i+1;
				int ora = j+1;
				output[i] = giorno + "," + ora + "," + orario.get(giorno).get(ora);
			}
		}
		return output;
	}
	
	@Override
	public int compareTo(Classe o) {
		return this.getCodiceClasse().compareTo(o.getCodiceClasse());
	}

}
