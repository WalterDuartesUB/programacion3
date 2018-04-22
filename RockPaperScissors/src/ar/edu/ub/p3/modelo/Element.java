package ar.edu.ub.p3.modelo;

public abstract class Element {

	public static final Element ROCK = Element.createRock();
	public static final Element PAPER = Element.createPaper();
	public static final Element SCISSORS = Element.createScissors();
	
	/**
	 * compara dos elementos y dice quien gana
	 * @param element element contra el cual comparar
	 * @return el Element que gane entre this y el parametro element
	 */
	public abstract Element compare( Element element );
	
	//Comparaciones fijas
	protected abstract Element compare( Rock aRock );
	protected abstract Element compare( Scissors aScissors);
	protected abstract Element compare( Paper aPaper );
	
	//Fuerzo que tengan un toString
	public abstract String toString();
	
	//Constructores estaticos
	private static Element createRock() {
		return new Rock();
	}

	private static Element createScissors() {
		return new Scissors();
	}

	private static Element createPaper() {
		return new Paper();
	}
	
}
