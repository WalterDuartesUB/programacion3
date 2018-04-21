import ar.edu.ub.p3.modelo.ClienteSupermercado;
import ar.edu.ub.p3.modelo.ColaSupermercado;

public class Aplicacion {
	public static void main(String[] args) {
		ColaSupermercado colaA = new ColaSupermercado();
				
		//Agrego clientes a la cola
		colaA.poner( new ClienteSupermercado( 0, 8 ) );
		colaA.poner( new ClienteSupermercado( 1, 1 ) );
		colaA.poner( new ClienteSupermercado( 2, 9 ) );
		colaA.poner( new ClienteSupermercado( 3, 2 ) );
		colaA.poner( new ClienteSupermercado( 4, 10 ) );
		colaA.poner( new ClienteSupermercado( 5, 3 ) );
				
		System.out.println( colaA );
		
		//Pido la cola B
		ColaSupermercado colaB = colaA.obtenerColaRapida();
		
		System.out.println( colaA );
		System.out.println( colaB );
	}
}
