import java.util.*;

import docenze_e_supplenze.*;

public class Esempio {

	public static void main(String[] args) throws EccezioneOraMateriaGiaAssegnata {
				
		System.out.println("/******************************/");
		System.out.println("/**        R1. SCUOLE        **/");
		System.out.println("/******************************/\n");
		
		IstitutoComprensivo ic = new IstitutoComprensivo();

		System.out.println("Configurazione Istituto Comprensivo");
		ic.configuraIstitutoComprensivo("CNIC863002", "Bra 2", "Via Brizio 10, 12042 Bra (CN)");
		String stringaDescrizione = ic.stampaIstitutoComprensivo();
		
		System.out.println("\nInformazioni Istituto Comprensivo:\n");
		System.out.println(stringaDescrizione);
		
		System.out.println("\nAggiunta scuola");
		Scuola scuola = ic.aggiungiScuola("CNAA86403R", "S. Andrea Centro Storico", "Via Beato Valfre' 16, 12042 Bra (CN)");
	
		System.out.println("\nRicerca scuola con codice 'CNAA86403R'");
		scuola = ic.cercaScuolaCodice("CNAA86403R");
		
		System.out.println("\nDettagli scuola:\n");
		System.out.println("Codice: "+scuola.getCodiceScuola());
		System.out.println("Nome: "+scuola.getNomeScuola());
		System.out.println("Indirizzo: "+scuola.getIndirizzoScuola());

		System.out.println("\nAggiunte altre scuole");
		ic.aggiungiScuola("CN1A01200G", "Convitto Provvidenza", "Via Provvidenza 5, 12042 Bra (CN)");
		ic.aggiungiScuola("CNAA86402Q", "Carlo Collodi", "Piazzetta Valfre' 2/A, 12042 Bra (CN)");

		System.out.println("\nElenco scuole (per codice):\n");
		LinkedList<Scuola> listaScuole = new LinkedList<Scuola>(ic.elencoScuolePerCodice());
		for(Scuola s: listaScuole) {
			System.out.println(""+s.getCodiceScuola()+";"+s.getNomeScuola()+";"+s.getIndirizzoScuola());
		}
		
		System.out.println("\nElenco scuole (per nome):\n");
		listaScuole = new LinkedList<Scuola>(ic.elencoScuolePerNome());
		for(Scuola s: listaScuole) {
			System.out.println(""+s.getCodiceScuola()+";"+s.getNomeScuola()+";"+s.getIndirizzoScuola());
		}
		
		System.out.println("\n/******************************/");
		System.out.println("/**        R2. CLASSI        **/");
		System.out.println("/******************************/\n");

		System.out.println("Aggiunta classe alla scuola 'CNAA86403R'");
		String stringaClasse = ic.aggiungiClasseAScuola("CNAA86403R", "Secondario", 1);

		System.out.println("\nCodice classe assegnato:\n");
		System.out.println(stringaClasse);
		
		System.out.println("\nAggiunte altre classi");
		ic.aggiungiClasseAScuola("CNAA86403R", "Primario", 1);
		ic.aggiungiClasseAScuola("CNAA86403R", "Secondario", 1);
		
		System.out.println("\nElenco classi della scuola 'CNAA86403R' a grado 'Secondario' (per codice classe):\n");
		LinkedList<Classe> listaClassi = new LinkedList<Classe>(ic.elencoClassiScuolaPerCodice("CNAA86403R", "Secondario"));
		for(Classe c: listaClassi) {
			System.out.println(""+c.getCodiceClasse());
		}
		
		System.out.println("\nElenco classi della scuola 'CNAA86403R' a grado 'Terziario' (per codice classe):\n");
		listaClassi = new LinkedList<Classe>(ic.elencoClassiScuolaPerCodice("CNAA86403R", "Terziario"));
		for(Classe c: listaClassi) {
			System.out.println(""+c.getCodiceClasse());
		}
		
		System.out.println("\nElenco classi della scuola 'CNAA86403R' (per codice classe):\n");
		listaClassi = new LinkedList<Classe>(ic.elencoClassiScuolaPerCodice("CNAA86403R"));
		for(Classe c: listaClassi) {
			System.out.println(""+c.getCodiceClasse());
		}

		
		System.out.println("\n/******************************/");
		System.out.println("/**    R3. ORE E MATERIE     **/");
		System.out.println("/******************************/\n");

		System.out.println("Assegnata materia 'Matematica', giorno 1 (lunedì), ora 1, classe 'CNAA86403R-Secondario-1-A'");
		ic.assegnaOraMateriaAClasse("CNAA86403R-Secondario-1-A", 1, 1, "Matematica");
		
		System.out.println("\nMateria della classe 'CNAA86403R-Secondario-1-A', giorno 1, ora 1:\n");
		String stringMateria = ic.materiaClasseGiornoOra("CNAA86403R-Secondario-1-A", 1, 1);
		System.out.println(stringMateria);
				
		System.out.println("\nAssegnate altre materie in altri giorni ed ore alla classe");
		ic.assegnaOraMateriaAClasse("CNAA86403R-Secondario-1-A", 1, 2, "Italiano");
		ic.assegnaOraMateriaAClasse("CNAA86403R-Secondario-1-A", 2, 2, "Storia");
		ic.assegnaOraMateriaAClasse("CNAA86403R-Secondario-1-A", 1, 3, "Italiano");
		
		System.out.println("\nMateria della classe 'CNAA86403R-Secondario-1-A', giorno 1, ora 2:\n");
		stringMateria = ic.materiaClasseGiornoOra("CNAA86403R-Secondario-1-A", 1, 2);
		System.out.println(stringMateria);
		
		System.out.println("\nMateria della classe 'CNAA86403R-Secondario-1-A', giorno 2, ora 2:\n");
		stringMateria = ic.materiaClasseGiornoOra("CNAA86403R-Secondario-1-A", 2, 2);
		System.out.println(stringMateria);
		
		System.out.println("\nMateria della classe 'CNAA86403R-Secondario-1-A', giorno 1, ora 3:\n");
		stringMateria = ic.materiaClasseGiornoOra("CNAA86403R-Secondario-1-A", 1, 3);
		System.out.println(stringMateria);
		
		System.out.println("\n/******************************/");
		System.out.println("/**  R4. DOCENTI E SUPPLENTI **/");
		System.out.println("/******************************/\n");

		System.out.println("Aggiunto docente matricola '2222' Mario Rossi");
		Docente docente = ic.aggiungiDocente(2222, "Mario", "Rossi");

		System.out.println("\nAssegnato docente alla classe 'CNAA86403R-Secondario-1-A', materia 'Italiano'");
		ic.assegnaDocenteAClasseMateria(2222, "CNAA86403R-Secondario-1-A", "Italiano");
		
		System.out.println("\nDocente classe 'CNAA86403R-Secondario-1-A', materia 'Ginnastica':");
		docente = ic.docenteClasseMateria("CNAA86403R-Secondario-1-A","Ginnastica");
		if (docente != null)
			System.out.println("\n"+docente.getMatricola()+" "+docente.getNome()+" "+docente.getCognome());
		
		System.out.println("\nDocente classe 'CNAA86403R-Secondario-1-A', materia 'Matematica':");
		docente = ic.docenteClasseMateria("CNAA86403R-Secondario-1-A","Matematica");
		if (docente != null)
			System.out.println("\n"+docente.getMatricola()+" "+docente.getNome()+" "+docente.getCognome());
		
		System.out.println("\nDocente classe 'CNAA86403R-Secondario-1-A', materia 'Italiano':");
		docente = ic.docenteClasseMateria("CNAA86403R-Secondario-1-A","Italiano");
		System.out.println("\n"+docente.getMatricola()+" "+docente.getNome()+" "+docente.getCognome());
		
		
		System.out.println("\nAggiunti altri docenti ed effettuate altre assegnazioni classe-materia");
		ic.aggiungiDocente(3333, "Gianna", "Verdi");
		ic.assegnaDocenteAClasseMateria(3333, "CNAA86403R-Secondario-1-A", "Storia");
		ic.aggiungiDocente(4444, "Luisa", "Neri");
		ic.assegnaDocenteAClasseMateria(4444, "CNAA86403R-Secondario-1-A", "Matematica");
		
		System.out.println("\nDocente classe 'CNAA86403R-Secondario-1-A', materia 'Matematica':");
		Docente docente2 = ic.docenteClasseMateria("CNAA86403R-Secondario-1-A","Matematica");
		if (docente != null)
			System.out.println("\n"+docente2.getMatricola()+" "+docente2.getNome()+" "+docente2.getCognome());
		
		System.out.println("\nAssegnato supplente con matricola '6666' per docente principale '4444'");
		ic.assegnaSupplentePerDocente(4444, 6666, "Gianni", "Bianchi");
		
		System.out.println("\nSupplente assegnato per il docente principale '4444':");
		Docente supplente = ic.supplenteDiDocentePrincipale(4444);
		System.out.println("\n"+supplente.getMatricola()+" "+supplente.getNome()+" "+supplente.getCognome());

		System.out.println("\nDocente principale sostituito dal supplente '6666':");
		Docente docentePrincipale = ic.docentePrincipaleDiSupplente(6666);
		System.out.println("\n"+docentePrincipale.getMatricola()+" "+docentePrincipale.getNome()+" "+docentePrincipale.getCognome());

		System.out.println("\n/******************************/");
		System.out.println("/**        R5. ORARIO        **/");
		System.out.println("/******************************/\n");
		
		System.out.println("Orario classe 'CNAA86403R-Secondario-1-A' (per giorno-ora):\n");

		String stringaOrario = ic.stampaOrarioClasse("CNAA86403R-Secondario-1-A");
		System.out.println(stringaOrario);
		
	}
}


