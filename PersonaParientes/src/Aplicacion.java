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
		
		Persona abuelo2 = new Hombre( "abuelo2", "2a1");
		Persona abuela2 = new Mujer( "abuela2", "2a2");
		
		Persona tio = new Hombre( abuelo, abuela, "tio", "t1");
		Persona tio2 = new Hombre( abuelo, abuela, "tio2", "t12");
		Persona tia = new Mujer( abuelo, abuela, "tia2", "t123");
		//Persona padre = new Hombre( abuelo, abuela, "padre", "p1");
		Persona padre = new Hombre( "padre", "p1");
		Persona madre = new Mujer( abuelo2, abuela2, "madre", "m1");
		
		Persona hijo = new Hombre( padre, madre, "nieto 1", "n1");
		Persona hijo2 = new Mujer( padre, madre, "nieta 1", "n2");
		Persona hijo3 = new Hombre( padre, madre, "nieto 2", "n3");
		
		/*
		System.out.println( Arrays.toString( abuelo.getNietos()) );
		System.out.println( Arrays.toString( abuela.getNietos()) );
		
		System.out.println( Arrays.toString( padre.getNietos()) );
		System.out.println( Arrays.toString( madre.getNietos()) );
		*/
		/*
		System.out.println( Arrays.toString( abuelo.getAbuelos() ) );
		
		System.out.println( Arrays.toString( hijo.getAbuelos() ) );
		System.out.println( Arrays.toString( hijo2.getAbuelos() ) );
		System.out.println( Arrays.toString( hijo3.getAbuelos() ) );
		
		
		System.out.println( Arrays.toString( hijo.getAbuelas() ) );
		System.out.println( Arrays.toString( hijo2.getAbuelas() ) );
		System.out.println( Arrays.toString( hijo3.getAbuelas() ) );
		
		*/
		
		madre.setPareja( padre );
		
		System.out.println( Arrays.toString( madre.getCuniados() ) );
		System.out.println( Arrays.toString( madre.getCuniadas() ) );
		
		System.out.println( Arrays.toString( tio2.getCuniados() ) );
		System.out.println( Arrays.toString( tio2.getCuniadas() ) );
	}

}
