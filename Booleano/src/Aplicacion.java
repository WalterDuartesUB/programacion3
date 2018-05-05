import ar.edu.ub.p3.modelo.Booleano;

public class Aplicacion {

	public static void main(String[] args) {
		System.out.println( "V: " + Booleano.VERDADERO );
		System.out.println( "F: " + Booleano.FALSO );
		
		System.out.println();
		System.out.println("NOT");		
		System.out.println( "!V: " + Booleano.VERDADERO.not() );
		System.out.println( "!F: " + Booleano.FALSO.not() );
		
		System.out.println();
		System.out.println("AND");
		System.out.println( "V & V: " + Booleano.VERDADERO.and( Booleano.VERDADERO ) );
		System.out.println( "V & F: " + Booleano.VERDADERO.and( Booleano.FALSO ) );
		System.out.println( "F & V: " + Booleano.FALSO.and( Booleano.VERDADERO ) );
		System.out.println( "F & F: " + Booleano.FALSO.and( Booleano.FALSO ) );
		
		System.out.println();
		System.out.println("OR");
		System.out.println( "V | V: " + Booleano.VERDADERO.or( Booleano.VERDADERO ) );
		System.out.println( "V | F: " + Booleano.VERDADERO.or( Booleano.FALSO ) );
		System.out.println( "F | V: " + Booleano.FALSO.or( Booleano.VERDADERO ) );
		System.out.println( "F | F: " + Booleano.FALSO.or( Booleano.FALSO ) );
		
		System.out.println();
		System.out.println("XOR");
		System.out.println( "V x V: " + Booleano.VERDADERO.xor( Booleano.VERDADERO ) );
		System.out.println( "V x F: " + Booleano.VERDADERO.xor( Booleano.FALSO ) );
		System.out.println( "F x V: " + Booleano.FALSO.xor( Booleano.VERDADERO ) );
		System.out.println( "F x F: " + Booleano.FALSO.xor( Booleano.FALSO ) );
		
		System.out.println();
		System.out.println("equals");
		System.out.println( "V = V: " + Booleano.VERDADERO.equals( Booleano.VERDADERO ) );
		System.out.println( "V = F: " + Booleano.VERDADERO.equals( Booleano.FALSO ) );
		System.out.println( "F = V: " + Booleano.FALSO.equals( Booleano.VERDADERO ) );
		System.out.println( "F = F: " + Booleano.FALSO.equals( Booleano.FALSO ) );		

	}

}
