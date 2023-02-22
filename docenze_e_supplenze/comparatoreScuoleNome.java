package docenze_e_supplenze;

import java.util.Comparator;

public class comparatoreScuoleNome implements Comparator<Scuola>{

	@Override
	public int compare(Scuola s1, Scuola s2) {
		return s1.getNomeScuola().compareTo(s2.getNomeScuola());
	}
	
}
