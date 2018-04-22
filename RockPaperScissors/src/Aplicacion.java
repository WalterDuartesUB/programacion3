import static ar.edu.ub.p3.modelo.Element.ROCK;
import static ar.edu.ub.p3.modelo.Element.PAPER;
import static ar.edu.ub.p3.modelo.Element.SCISSORS;

import ar.edu.ub.p3.modelo.Element;

public class Aplicacion {
	public static void main(String[] args) {
				
		comparar( ROCK, PAPER );
		comparar( ROCK, SCISSORS );
		comparar( ROCK, ROCK );
		System.out.println();
		
		comparar( PAPER, PAPER );
		comparar( PAPER, SCISSORS );
		comparar( PAPER, ROCK );
		System.out.println();
		
		comparar( SCISSORS, PAPER );
		comparar( SCISSORS, SCISSORS );
		comparar( SCISSORS, ROCK );			
	}

	private static void comparar(Element element, Element otherElement) {
		System.out.println( String.format("%s vs %s = %s", element, otherElement, element.compare(otherElement)));
		
	}
}
