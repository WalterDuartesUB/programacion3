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
				
		Persona tio = new Hombre( abuelo, abuela, "tio", "t1");
		Persona padre = new Hombre( abuelo, abuela, "padre", "p1");
	
		Persona madre = new Mujer( "madre", "m1");
		Persona madre2 = new Mujer( "madre", "m2");
		
		Persona hijo = new Hombre( padre, madre, "nieto 1", "n1");

		
//		madre.setPareja( hijo );
//		madre.setPareja( tio );
		madre.setPareja( padre );
		madre2.setPareja( madre );
//		madre.setPareja( abuelo );
		//madre.setPareja( abuela );
	
		System.out.println( padre.getPareja() );
		System.out.println( madre.getPareja() );
		System.out.println( madre2.getPareja() );
	}

}
