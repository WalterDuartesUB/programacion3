import java.util.Arrays;

import ar.edu.ub.p3.persona.excepciones.FamiliarInvalidoException;
import ar.edu.ub.p3.persona.excepciones.FamiliarNotFoundException;
import ar.edu.ub.p3.persona.excepciones.ParejaInvalidaException;
import ar.edu.ub.p3.persona.excepciones.PersonaAtributoInvalidoException;
import ar.edu.ub.p3.persona.modelo.Hombre;
import ar.edu.ub.p3.persona.modelo.Mujer;
import ar.edu.ub.p3.persona.modelo.Persona;

public class Aplicacion
{

	public static void main(String[] args) throws FamiliarNotFoundException, PersonaAtributoInvalidoException, FamiliarInvalidoException, ParejaInvalidaException
	{
		Persona abuelo = new Hombre( "abuelo", "a1");
		Persona abuela = new Mujer( "abuela", "a2");
		
		Persona otroAbuelo = new Hombre( "abuelo2", "a11");
		Persona otraAbuela = new Mujer( "abuela2 ", "a21");		
				
		Persona tio = new Hombre( abuelo, abuela, "tio", "t1");
		Persona padre = new Hombre( abuelo, abuela, "padre", "p1");
	
		Persona madre = new Mujer( otroAbuelo, otraAbuela, "madre", "m1");
		Persona amante = new Mujer( "amante", "m2");
		
		Persona hijo = new Hombre( padre, madre, "nieto 1", "n1");
		Persona hijoExtraMatrimonial = new Hombre( padre, amante, "nieto 2", "n2");

		System.out.println( hijo.getFamilia() );
		System.out.println( madre.getFamilia() );
		System.out.println( padre.getFamilia() );		
		System.out.println( tio.getFamilia() );
		System.out.println( abuelo.getFamilia() );
		System.out.println( abuela.getFamilia() );
		
		System.out.println();
		
		System.out.println( amante.getFamilia() );
		
		
		madre.setPareja( amante );
	}

}
