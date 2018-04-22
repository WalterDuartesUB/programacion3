package ar.edu.ub.p3.modelo;

public abstract class Element {

	public static final Element ROCK = new Rock();
	public static final Element PAPER = new Paper();
	public static final Element SCISSORS = new Scissors();
	
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
	
}
