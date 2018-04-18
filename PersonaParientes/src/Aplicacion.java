import java.util.Arrays;

import ar.edu.ub.p3.persona.excepciones.FamiliarInvalidoException;
import ar.edu.ub.p3.persona.excepciones.FamiliarNotFoundException;
import ar.edu.ub.p3.persona.excepciones.PersonaAtributoInvalidoException;
import ar.edu.ub.p3.persona.modelo.Hombre;
import ar.edu.ub.p3.persona.modelo.Mujer;
import ar.edu.ub.p3.persona.modelo.Persona;

public class Aplicacion
{

	public static void main(String[] args) throws FamiliarNotFoundException, PersonaAtributoInvalidoException, FamiliarInvalidoException
	{
		Persona abuelo = new Hombre( "abuelo", "a1");
		Persona abuela = new Mujer( "abuela", "a2");
		
		Persona padre = new Hombre( abuelo, abuela, "padre", "p1");
		Persona madre = new Mujer( "madre", "m1");
		
		Persona hijo = new Hombre( padre, madre, "nieto 1", "n1");
		Persona hijo2 = new Mujer( padre, madre, "nieta 1", "n2");
		Persona hijo3 = new Hombre( padre, madre, "nieto 2", "n3");
		
		System.out.println( Arrays.toString( abuelo.getNietos()) );
		System.out.println( Arrays.toString( abuela.getNietos()) );
		
		System.out.println( Arrays.toString( padre.getNietos()) );
		System.out.println( Arrays.toString( madre.getNietos()) );
	}

}
