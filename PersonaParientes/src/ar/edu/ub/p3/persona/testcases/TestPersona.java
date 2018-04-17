package ar.edu.ub.p3.persona.testcases;

import java.util.Arrays;

import ar.edu.ub.p3.persona.excepciones.FamiliarInvalidoException;
import ar.edu.ub.p3.persona.excepciones.FamiliarNotFoundException;
import ar.edu.ub.p3.persona.excepciones.PersonaAtributoInvalidoException;
import ar.edu.ub.p3.persona.modelo.Hombre;
import ar.edu.ub.p3.persona.modelo.Mujer;
import ar.edu.ub.p3.persona.modelo.Persona;
import junit.framework.TestCase;

public class TestPersona extends TestCase
{
	public void testPersonaNombreNull()
	{		
		try {
			Persona persona = new Hombre( null, "P1"  );
			assertTrue( false );
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( true );
		}
	}
	
	public void testPersonaNombreVacio()
	{		
		try {
			Persona persona = new Hombre( " ", "P1");
			assertTrue( false );
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( true );
		}
	}
	
	public void testPersonaDniNull()
	{		
		try {
			Persona persona = new Hombre( "persona", null);
			assertTrue( false );
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( true );
		}
	}
	
	public void testPersonaDniVacio()
	{		
		try {
			Persona persona = new Hombre( "persona", " ");
			assertTrue( false );
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( true );
		}
	}
	
	public void testPersonaPadreNull()
	{
		Persona padre = null;
		Persona madre = new Mujer( "Madre", "M1");
		
		try {
			Persona hijo = new Hombre( padre, madre, "Hijo", "H1");
			assertTrue( false );
		} catch (FamiliarInvalidoException e) {
			assertTrue( true );
		}
	}
	
	public void testPersonaMadreNull()
	{
		Persona padre = new Mujer( "Madre", "M1" );
		Persona madre = null;
		
		try {
			Persona hijo = new Hombre( padre, madre, "Hijo", "H1");
			assertTrue( false );
		} catch (FamiliarInvalidoException e) {
			assertTrue( true );
		}	
	}
	
	public void testHijoNombreNull()
	{		
	
		Persona padre = new Hombre( "Padre", "H1");
		Persona madre = new Mujer( "Madre", "M1");
		
		try {
			Persona hijo = new Hombre( padre, madre, null, "H1");
			assertTrue( false );
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( true );
		} catch (FamiliarInvalidoException e) {
			assertTrue( false );
		}
	}
	
	public void testHijoNombreVacio()
	{		
	
		Persona padre = new Hombre( "Padre", "H1");
		Persona madre = new Mujer( "Madre", "M1");
		
		try {
			Persona hijo = new Hombre( padre, madre, " ", "H1");
			assertTrue( false );
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( true );
		} catch (FamiliarInvalidoException e) {
			assertTrue( false );
		}
	}
	
	public void testHijoDniNull()
	{	

		Persona padre = new Hombre( "Padre", "H1");
		Persona madre = new Mujer( "Madre", "M1");	
		
		try 
		{
			Persona hijo = new Hombre( padre, madre, "hijo", null);
			assertTrue( false );
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( true );
		} catch (FamiliarInvalidoException e) {
			assertTrue( false );
		}
	}
	
	public void testHijoDniVacio()
	{	
		Persona padre = new Hombre( "Padre", "H1");
		Persona madre = new Mujer( "Madre", "M1");
		
		try 
		{
			Persona hijo = new Hombre( padre, madre, "hijo", " ");
			assertTrue( false );
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( true );
		} catch (FamiliarInvalidoException e) {
			assertTrue( false );
		}
	}	
	
	public void testPersonaCrearHijo()
	{
		Persona padre = new Hombre( "Padre", "H1");
		Persona madre = new Mujer( "Madre", "M1");
		
		try 
		{
			String nombreHijo = "hijo";
			String dniHijo = "H1";			
			
			Persona hijo = new Hombre( padre, madre, nombreHijo, dniHijo );
					
			//Verifico los datos de creacion del hijo
			assertEquals( nombreHijo, hijo.getNombre() );			
			assertEquals( dniHijo, hijo.getDni() );						
			
			//Veo los padres
			assertEquals( padre, hijo.getPadre() );
			assertEquals( madre, hijo.getMadre() );
			
			//Veo SI SOY hijo de mis padres
			assertTrue( hijo.existePersonaEn( padre.getHijos() ) );
			assertTrue( hijo.existePersonaEn( madre.getHijos() ) );
			
			//Veo QUE NO soy hijA de mis padres
			assertFalse( hijo.existePersonaEn( padre.getHijas() ) );
			assertFalse( hijo.existePersonaEn( madre.getHijas() ) );
			
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( false );
		} catch (FamiliarInvalidoException e) {
			assertTrue( false );
		} catch (FamiliarNotFoundException e) {
			assertTrue( false );
		}		
	}		
	
	public void testPersonaCrearHija()
	{
		Persona padre = new Hombre( "Padre", "H1");
		Persona madre = new Mujer( "Madre", "M1");
		
		try 
		{
			String nombreHijo = "hija";
			String dniHijo = "H1";			
			
			Persona hija = new Mujer( padre, madre, nombreHijo, dniHijo  );
						
			//Verifico los datos de creacion del hijo
			assertEquals( nombreHijo, hija.getNombre() );			
			assertEquals( dniHijo, hija.getDni() );						
			
			//Veo los padres
			assertEquals( padre, hija.getPadre() );
			assertEquals( madre, hija.getMadre() );
			
			//Veo SI SOY hijo de mis padres
			assertTrue( hija.existePersonaEn( padre.getHijas() ) );
			assertTrue( hija.existePersonaEn( madre.getHijas() ) );
			
			//Veo QUE NO soy hijA de mis padres
			assertFalse( hija.existePersonaEn( padre.getHijos() ) );
			assertFalse( hija.existePersonaEn( madre.getHijos() ) );
			
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( false );
		} catch (FamiliarInvalidoException e) {
			assertTrue( false );
		} catch (FamiliarNotFoundException e) {
			assertTrue( false );
		}	
	}
	
	public void testPersonaObtenerHermanos()
	{
		Persona padre = new Hombre( "Padre", "H1");
		Persona madre = new Mujer( "Madre", "M1");
		
		try 
		{
			Persona hijo = new Hombre( padre, madre, "hijo", "H1");
			Persona otroHijo = new Hombre( padre, madre, "otro Hijo", "He1");
			
			//Verifico ser hijo de mi padre
			assertEquals( padre, hijo.getPadre() );
			assertEquals( madre, hijo.getMadre() );

			//Verifico que mi hermano tambien sea hijo de mis padres
			assertEquals( padre, otroHijo.getPadre() );
			assertEquals( madre, otroHijo.getMadre() );			

			//Veo que el hermano de mi hermano sea yo
			assertTrue( hijo.existePersonaEn( otroHijo.getHermanos() ) );
			
			//Veo que en mis hermanos figure mi hermano
			assertTrue( otroHijo.existePersonaEn( hijo.getHermanos() ) );
			
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( false );
		} catch (FamiliarInvalidoException e) {
			assertTrue( false );
		} catch (FamiliarNotFoundException e) {
			assertTrue( false );
		}		
	}		
	
	public void testPersonaObtenerHermanas()
	{

		Persona padre = new Hombre( "Padre", "H1");
		Persona madre = new Mujer( "Madre", "M1");
		
		try 
		{
			Persona hija = new Mujer( padre, madre, "hijo", "H1");
			Persona otroHija = new Mujer( padre, madre, "hermana", "He1");
			
			//Verifico ser hijo de mi padre
			assertEquals( padre, hija.getPadre() );
			assertEquals( madre, hija.getMadre() );

			//Verifico que mi hermano tambien sea hijo de mis padres
			assertEquals( padre, otroHija.getPadre() );
			assertEquals( madre, otroHija.getMadre() );			

			//Veo que el hermano de mi hermano sea yo
			assertTrue( hija.existePersonaEn( otroHija.getHermanas() ) );
			
			//Veo que en mis hermanos figure mi hermano
			assertTrue( otroHija.existePersonaEn( hija.getHermanas() ) );
			
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( false );
		} catch (FamiliarInvalidoException e) {
			assertTrue( false );
		} catch (FamiliarNotFoundException e) {
			assertTrue( false );
		}	
	}		
	
	public void testPersonaObtenerTios()
	{
		Persona abuelo = new Hombre( "Abuelo", "AM1");
		Persona abuela = new Mujer( "Abuela", "AF1");
							
		try 
		{
			
			Persona padre = new Hombre( abuelo, abuela, "Padre", "H1");
			Persona madre = new Mujer( "Madre", "M1");
			
			Persona tio1 = new Hombre( abuelo, abuela, "Tio 1", "T1");
			Persona tio2 = new Hombre( abuelo, abuela, "Tio 2", "T2");
			
			Persona hijo = new Hombre( padre, madre, "hijo", "H1");
						
			//
			
			assertTrue( tio1.existePersonaEn( hijo.getTios() ) );
			assertTrue( tio2.existePersonaEn( hijo.getTios() ) );
			assertFalse( padre.existePersonaEn( hijo.getTios() ) );
						
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( false );
		} catch (FamiliarInvalidoException e) {
			assertTrue( false );
		}
	}		
	
	public void testPersonaObtenerTias()
	{
		Persona abuelo = new Hombre( "Abuelo", "AM1");
		Persona abuela = new Mujer( "Abuela", "AF1");
					
		try 
		{
			
			Persona padre = new Hombre( abuelo, abuela, "Padre", "H1");
			Persona madre = new Mujer( "Madre", "M1");
			
			Persona tia1 = new Mujer( abuelo, abuela, "tia 1", "T1");
			Persona tia2 = new Mujer( abuelo, abuela, "tia 2", "T2");
			
			Persona hijo = new Hombre( padre, madre, "hijo", "H1");
						
			//
			
			assertTrue( tia1.existePersonaEn( hijo.getTias() ) );
			assertTrue( tia2.existePersonaEn( hijo.getTias() ) );
			assertFalse( madre.existePersonaEn( hijo.getTias() ) );
						
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( false );
		} catch (FamiliarInvalidoException e) {
			assertTrue( false );
		}
	}
	
	public void testPersonaObtenerTiosYTias()
	{
		Persona abuelo = new Hombre( "Abuelo", "AM1");
		Persona abuela = new Mujer( "Abuela", "AF1");
					
		try 
		{
			
			Persona padre = new Hombre( abuelo, abuela, "Padre", "H1");
			Persona madre = new Mujer( "Madre", "M1");
			
			Persona tio1 = new Hombre( abuelo, abuela, "Tio 1", "T1");
			Persona tio2 = new Hombre( abuelo, abuela, "Tio 2", "T2");
			
			Persona tia1 = new Mujer( abuelo, abuela, "tia 1", "TF1");
			
			Persona hijo = new Hombre( padre, madre, "hijo", "H1");
						
			//El tio solo existe como tio			
			assertFalse( tia1.existePersonaEn( hijo.getTios() ) );
			assertTrue( tio1.existePersonaEn( hijo.getTios() ) );
			assertTrue( tio2.existePersonaEn( hijo.getTios() ) );
			
			//Papa no es un tio
			assertFalse( padre.existePersonaEn( hijo.getTios() ) );
			
			//La tia solo existe como tia			
			assertTrue( tia1.existePersonaEn( hijo.getTias() ) );
			assertFalse( tio1.existePersonaEn( hijo.getTias() ) );
			assertFalse( tio2.existePersonaEn( hijo.getTias() ) );
			
			//Mama no es una tia
			assertFalse( madre.existePersonaEn( hijo.getTias() ) );
						
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( false );
		} catch (FamiliarInvalidoException e) {
			assertTrue( false );
		}
	}		
	
	public void testPersonaObtenerPrimos()
	{
		Persona abuelo = new Hombre( "Abuelo", "AM1");
		Persona abuela = new Mujer( "Abuela", "AF1");
							
		try 
		{
			
			Persona padre = new Hombre( abuelo, abuela, "Padre", "H1");
			Persona madre = new Mujer( "Madre", "M1");
			
			Persona tio1 = new Hombre( abuelo, abuela, "Tio 1", "T1");
			Persona tia1 = new Mujer( "tia 1", "TF1");
			Persona tia2 = new Mujer( "tia politica 2", "TPF1");
			
			Persona tio2 = new Hombre( abuelo, abuela, "Tio 2", "T2");					
			
			Persona hijo = new Hombre( padre, madre, "hijo", "H1");
			
			Persona primo1 = new Hombre( tio1, tia1, "primo 1 tio 1", "P1T1");
			Persona primo2 = new Mujer( tio1, tia1, "prima 2 tio 1", "P2T1");
			Persona primo3 = new Hombre( tio1, tia1, "primo 3 tio 1", "P3T1");
			
			Persona primo4 = new Hombre( tio2, tia2, "primo 1 tio 2", "P1T1");
			Persona primo5 = new Mujer( tio2, tia2, "prima 2 tio 2", "P2T1");
						
			//Veo que mis primos existan en donde corresponde
			assertTrue( primo1.existePersonaEn( hijo.getPrimos() ) );
			assertTrue( primo3.existePersonaEn( hijo.getPrimos() ) );
			assertTrue( primo4.existePersonaEn( hijo.getPrimos() ) );
			
			assertFalse( primo2.existePersonaEn( hijo.getPrimos() ) );
			assertFalse( primo5.existePersonaEn( hijo.getPrimos() ) );
						
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( false );
		} catch (FamiliarInvalidoException e) {
			assertTrue( false );
		}
	}
	
	public void testPersonaObtenerPrimas()
	{
		Persona abuelo = new Hombre( "Abuelo", "AM1");
		Persona abuela = new Mujer( "Abuela", "AF1");
							
		try 
		{
			
			Persona padre = new Hombre( abuelo, abuela, "Padre", "H1");
			Persona madre = new Mujer( "Madre", "M1");
			
			Persona tio1 = new Hombre( abuelo, abuela, "Tio 1", "T1");
			Persona tia1 = new Mujer( "tia 1", "TF1");
			Persona tia2 = new Mujer( "tia politica 2", "TPF1");
			
			Persona tio2 = new Hombre( abuelo, abuela, "Tio 2", "T2");					
			
			Persona hijo = new Hombre( padre, madre, "hijo", "H1");
			
			Persona primo1 = new Hombre( tio1, tia1, "primo 1 tio 1", "P1T1");
			Persona prima2 = new Mujer( tio1, tia1, "prima 2 tio 1", "PM2T1");
			Persona primo3 = new Hombre( tio1, tia1, "primo 3 tio 1", "P3T1");
			
			Persona primo4 = new Hombre( tio2, tia2, "primo 1 tio 2", "P1T1");
			Persona primo5 = new Mujer( tio2, tia2, "prima 2 tio 2", "P2T1");
						
			//Veo que mis primos existan en donde corresponde
			assertFalse( primo1.existePersonaEn( hijo.getPrimas() ) );
			assertFalse( primo3.existePersonaEn( hijo.getPrimas() ) );
			assertFalse( primo4.existePersonaEn( hijo.getPrimas() ) );
			
			assertTrue( prima2.existePersonaEn( hijo.getPrimas() ) );
			assertTrue( primo5.existePersonaEn( hijo.getPrimas() ) );
						
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( false );
		} catch (FamiliarInvalidoException e) {
			assertTrue( false );
		}
	}

	public void testPersonaObtenerSobrinos()
	{
		//TODO: falta implementar el caso de prueba
		assertTrue( false );
	}
	
	public void testPersonaObtenerSobrinas()
	{
		//TODO: falta implementar el caso de prueba
		assertTrue( false );
	}
	
	public void testPersonaObtenerAbuelos()
	{
		//TODO: falta implementar el caso de prueba
		assertTrue( false );
	}
	
	public void testPersonaObtenerAbuelas()
	{
		//TODO: falta implementar el caso de prueba
		assertTrue( false );
	}
		
	public void testPersonaObtenerNietos()
	{
		//TODO: falta implementar el caso de prueba
		assertTrue( false );
	}
	
	public void testPersonaObtenerNietas()
	{
		//TODO: falta implementar el caso de prueba
		assertTrue( false );
	}
	
	public void testPersonaObtenerCuniados()
	{
		//TODO: falta implementar el caso de prueba
		assertTrue( false );
	}
	
	public void testPersonaObtenerCuniadas()
	{
		//TODO: falta implementar el caso de prueba
		assertTrue( false );
	}
	
	public void testPersonaObtenerSuegros()
	{
		//TODO: falta implementar el caso de prueba
		assertTrue( false );
	}
	
	public void testPersonaObtenerSuegras()
	{
		//TODO: falta implementar el caso de prueba
		assertTrue( false );
	}
	
	public void testPersonaObtenerPareja()
	{
		//TODO: falta implementar el caso de prueba
		assertTrue( false );
	}
	
}
