package ar.edu.ub.p3.persona.testcases;

import java.util.Arrays;

import ar.edu.ub.p3.persona.excepciones.FamiliarInvalidoException;
import ar.edu.ub.p3.persona.excepciones.FamiliarNotFoundException;
import ar.edu.ub.p3.persona.excepciones.ParejaInvalidaException;
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
		Persona abuelo = new Hombre( "abuelo", "a1");
		Persona abuela = new Mujer( "abuela", "a2");		

		
		try 
		{
			Persona tio = new Hombre( abuelo, abuela, "Tio", "pe1");
			Persona padre = new Hombre( abuelo, abuela, "padre", "p1");
			Persona madre = new Mujer( "madre", "m1");
			
			Persona sobrino = new Hombre( padre, madre, "nieto 1", "n1");
			Persona sobrina = new Mujer( padre, madre, "nieta 1", "n2");
			Persona sobrino3 = new Hombre( padre, madre, "nieto 2", "n3");

			//
			
			assertTrue(sobrino.existePersonaEn( tio.getSobrinos( ) ) );
			assertTrue(sobrino3.existePersonaEn( tio.getSobrinos( ) ) );
			assertFalse(sobrina.existePersonaEn( tio.getSobrinos( ) ) );
			
			//
			
			assertTrue( abuelo.getSobrinos().length == 0 );
			assertTrue( abuela.getSobrinos().length == 0 );
			
			assertTrue( padre.getSobrinos().length == 0 );
			assertTrue( madre.getSobrinos().length == 0 );
			
			assertTrue( sobrino.getSobrinos().length == 0 );
			assertTrue( sobrina.getSobrinos().length == 0 );
			assertTrue( sobrino3.getSobrinos().length == 0 );
		} 
		catch (PersonaAtributoInvalidoException | FamiliarInvalidoException e) 
		{
			assertTrue( false );
		}

	}
	
	public void testPersonaObtenerSobrinas()
	{
		Persona abuelo = new Hombre( "abuelo", "a1");
		Persona abuela = new Mujer( "abuela", "a2");		

		
		try 
		{
			Persona tio = new Hombre( abuelo, abuela, "Tio", "pe1");
			Persona padre = new Hombre( abuelo, abuela, "padre", "p1");
			Persona madre = new Mujer( "madre", "m1");
			
			Persona sobrino = new Hombre( padre, madre, "nieto 1", "n1");
			Persona sobrina = new Mujer( padre, madre, "nieta 1", "n2");
			Persona sobrino3 = new Hombre( padre, madre, "nieto 2", "n3");

			//
			
			assertFalse(sobrino.existePersonaEn( tio.getSobrinas( ) ) );
			assertFalse(sobrino3.existePersonaEn( tio.getSobrinas( ) ) );
			assertTrue(sobrina.existePersonaEn( tio.getSobrinas( ) ) );
			
			//
			
			assertTrue( abuelo.getSobrinas().length == 0 );
			assertTrue( abuela.getSobrinas().length == 0 );
			
			assertTrue( padre.getSobrinas().length == 0 );
			assertTrue( madre.getSobrinas().length == 0 );
			
			assertTrue( sobrino.getSobrinas().length == 0 );
			assertTrue( sobrina.getSobrinas().length == 0 );
			assertTrue( sobrino3.getSobrinas().length == 0 );
		} 
		catch (PersonaAtributoInvalidoException | FamiliarInvalidoException e) 
		{
			assertTrue( false );
		}
	}
	
	public void testPersonaObtenerAbuelos()
	{
		Persona abuelo = new Hombre( "abuelo", "a1");
		Persona abuela = new Mujer( "abuela", "a2");		
		Persona otroAbuelo = new Hombre( "otro abuelo", "oa1");
		Persona otraAbuela = new Mujer( "otra abuela", "oa2");
		
		try 
		{
			Persona padre = new Hombre( abuelo, abuela, "padre", "p1");
			Persona madre = new Mujer( otroAbuelo, otraAbuela, "madre", "m1");
			
			Persona hijo = new Hombre( padre, madre, "nieto 1", "n1");
			Persona hija = new Mujer( padre, madre, "nieta 1", "n2");
			Persona hijo3 = new Hombre( padre, madre, "nieto 2", "n3");
			
			//
			
			assertTrue( abuelo.existePersonaEn( hijo.getAbuelos() ) );
			assertTrue( abuelo.existePersonaEn( hija.getAbuelos() ) );
			assertTrue( abuelo.existePersonaEn( hijo3.getAbuelos() ) );
			
			assertTrue( otroAbuelo.existePersonaEn( hijo.getAbuelos() ) );
			assertTrue( otroAbuelo.existePersonaEn( hija.getAbuelos() ) );
			assertTrue( otroAbuelo.existePersonaEn( hijo3.getAbuelos() ) );
			
			//
			
			assertFalse( abuela.existePersonaEn( hijo.getAbuelos() ) );
			assertFalse( abuela.existePersonaEn( hija.getAbuelos() ) );
			assertFalse( abuela.existePersonaEn( hijo3.getAbuelos() ) );
			
			assertFalse( otraAbuela.existePersonaEn( hijo.getAbuelos() ) );
			assertFalse( otraAbuela.existePersonaEn( hija.getAbuelos() ) );
			assertFalse( otraAbuela.existePersonaEn( hijo3.getAbuelos() ) );
			
			//
			
			assertTrue(  padre.getAbuelos().length == 0 );
			assertTrue(  madre.getAbuelos().length == 0 );
			
			assertTrue(  abuelo.getAbuelos().length == 0 );
			assertTrue(  abuela.getAbuelos().length == 0 );
			assertTrue(  otroAbuelo.getAbuelos().length == 0 );
			assertTrue(  otraAbuela.getAbuelos().length == 0 );

		} 
		catch (PersonaAtributoInvalidoException | FamiliarInvalidoException e) 
		{
			assertTrue( false );
		}
	}
	
	public void testPersonaObtenerAbuelas()
	{
		Persona abuelo = new Hombre( "abuelo", "a1");
		Persona abuela = new Mujer( "abuela", "a2");		
		Persona otroAbuelo = new Hombre( "otro abuelo", "oa1");
		Persona otraAbuela = new Mujer( "otra abuela", "oa2");
		
		try 
		{
			Persona padre = new Hombre( abuelo, abuela, "padre", "p1");
			Persona madre = new Mujer( otroAbuelo, otraAbuela, "madre", "m1");
			
			Persona hijo = new Hombre( padre, madre, "nieto 1", "n1");
			Persona hija = new Mujer( padre, madre, "nieta 1", "n2");
			Persona hijo3 = new Hombre( padre, madre, "nieto 2", "n3");
			
			//
			
			assertFalse( abuelo.existePersonaEn( hijo.getAbuelas() ) );
			assertFalse( abuelo.existePersonaEn( hija.getAbuelas() ) );
			assertFalse( abuelo.existePersonaEn( hijo3.getAbuelas() ) );
			
			assertFalse( otroAbuelo.existePersonaEn( hijo.getAbuelas() ) );
			assertFalse( otroAbuelo.existePersonaEn( hija.getAbuelas() ) );
			assertFalse( otroAbuelo.existePersonaEn( hijo3.getAbuelas() ) );
			
			//
			
			assertTrue( abuela.existePersonaEn( hijo.getAbuelas() ) );
			assertTrue( abuela.existePersonaEn( hija.getAbuelas() ) );
			assertTrue( abuela.existePersonaEn( hijo3.getAbuelas() ) );
			
			assertTrue( otraAbuela.existePersonaEn( hijo.getAbuelas() ) );
			assertTrue( otraAbuela.existePersonaEn( hija.getAbuelas() ) );
			assertTrue( otraAbuela.existePersonaEn( hijo3.getAbuelas() ) );
			
			//
			
			assertTrue(  padre.getAbuelas().length == 0 );
			assertTrue(  madre.getAbuelas().length == 0 );
			
			assertTrue(  abuelo.getAbuelas().length == 0 );
			assertTrue(  abuela.getAbuelas().length == 0 );
			
			assertTrue(  otroAbuelo.getAbuelas().length == 0 );
			assertTrue(  otraAbuela.getAbuelas().length == 0 );

		} 
		catch (PersonaAtributoInvalidoException | FamiliarInvalidoException e) 
		{
			assertTrue( false );
		}
	}
		
	public void testPersonaObtenerNietos()
	{
		Persona abuelo = new Hombre( "abuelo", "a1");
		Persona abuela = new Mujer( "abuela", "a2");		
		Persona otroAbuelo = new Hombre( "otro abuelo", "oa1");
		Persona otraAbuela = new Mujer( "otra abuela", "oa2");
		
		try 
		{
			Persona padre = new Hombre( abuelo, abuela, "padre", "p1");
			Persona madre = new Mujer( otroAbuelo, otraAbuela, "madre", "m1");
			
			Persona hijo = new Hombre( padre, madre, "nieto 1", "n1");
			Persona hija = new Mujer( padre, madre, "nieta 1", "n2");
			Persona hijo3 = new Hombre( padre, madre, "nieto 2", "n3");
			
			//
			
			assertTrue( hijo.existePersonaEn( abuelo.getNietos() ) );
			assertTrue( hijo3.existePersonaEn( abuelo.getNietos() ) );
			assertFalse( hija.existePersonaEn( abuelo.getNietos() ) );
			
			//
			
			assertTrue( hijo.existePersonaEn( abuela.getNietos() ) );
			assertTrue( hijo3.existePersonaEn( abuela.getNietos() ) );
			assertFalse( hija.existePersonaEn( abuela.getNietos() ) );
			
			//
			
			assertTrue( hijo.existePersonaEn( otroAbuelo.getNietos() ) );
			assertTrue( hijo3.existePersonaEn( otroAbuelo.getNietos() ) );
			assertFalse( hija.existePersonaEn( otroAbuelo.getNietos() ) );
			
			//
			
			assertTrue( hijo.existePersonaEn( otraAbuela.getNietos() ) );
			assertTrue( hijo3.existePersonaEn( otraAbuela.getNietos() ) );
			assertFalse( hija.existePersonaEn( otraAbuela.getNietos() ) );
			
			//
			
			assertTrue(  padre.getNietos().length == 0 );
			assertTrue(  madre.getNietos().length == 0 );
			
			assertTrue(  hijo.getNietos().length == 0 );
			assertTrue(  hija.getNietos().length == 0 );
			assertTrue(  hijo3.getNietos().length == 0 );

		} 
		catch (PersonaAtributoInvalidoException | FamiliarInvalidoException e) 
		{
			assertTrue( false );
		}

	}
	
	public void testPersonaObtenerNietas()
	{
		Persona abuelo = new Hombre( "abuelo", "a1");
		Persona abuela = new Mujer( "abuela", "a2");
		
		Persona otroAbuelo = new Hombre( "otro abuelo", "oa1");
		Persona otraAbuela = new Mujer( "otra abuela", "oa2");
		
		try 
		{
			Persona padre = new Hombre( abuelo, abuela, "padre", "p1");
			Persona madre = new Mujer( otroAbuelo, otraAbuela, "madre", "m1");
			
			Persona hijo = new Hombre( padre, madre, "nieto 1", "n1");
			Persona hija = new Mujer( padre, madre, "nieta 1", "n2");
			Persona hijo3 = new Hombre( padre, madre, "nieto 2", "n3");
			
			//
			
			assertFalse( hijo.existePersonaEn( abuelo.getNietas() ) );
			assertFalse( hijo3.existePersonaEn( abuelo.getNietas() ) );
			assertTrue( hija.existePersonaEn( abuelo.getNietas() ) );
			
			//
			
			assertFalse( hijo.existePersonaEn( abuela.getNietas() ) );
			assertFalse( hijo3.existePersonaEn( abuela.getNietas() ) );
			assertTrue( hija.existePersonaEn( abuela.getNietas() ) );
			
			//
			
			assertFalse( hijo.existePersonaEn( otroAbuelo.getNietas() ) );
			assertFalse( hijo3.existePersonaEn( otroAbuelo.getNietas() ) );
			assertTrue( hija.existePersonaEn( otroAbuelo.getNietas() ) );
			
			//
			
			assertFalse( hijo.existePersonaEn( otraAbuela.getNietas() ) );
			assertFalse( hijo3.existePersonaEn( otraAbuela.getNietas() ) );
			assertTrue( hija.existePersonaEn( otraAbuela.getNietas() ) );
			
			//
			
			assertTrue(  padre.getNietos().length == 0 );
			assertTrue(  madre.getNietos().length == 0 );
			
			assertTrue(  hijo.getNietos().length == 0 );
			assertTrue(  hija.getNietos().length == 0 );
			assertTrue(  hijo3.getNietos().length == 0 );		
			
		} 
		catch (PersonaAtributoInvalidoException | FamiliarInvalidoException e) 
		{
			assertTrue( false );
		}
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
		Persona suegro = new Hombre( "Suegro", "s1" );
		Persona suegra = new Mujer( "Suegra", "s2" );
		
		Persona persona = new Hombre( "Persona", "p1" );
		try 
		{
			Persona pareja = new Mujer( suegro, suegra, "Pareja", "p2" );
		
			persona.setPareja( pareja );
			
			assertEquals( persona, pareja.getPareja() );
			assertEquals( pareja, persona.getPareja() );
			
			assertTrue( suegro.existePersonaEn( persona.getSuegros() ) );
			assertFalse( suegra.existePersonaEn( pareja.getSuegros() ) );
			
			assertTrue( persona.getSuegros().length == 1 );
			assertTrue( suegro.getSuegros().length == 0 );
			assertTrue( suegra.getSuegros().length == 0 );
			assertTrue( pareja.getSuegros().length == 0 );
		} 
		catch (PersonaAtributoInvalidoException | FamiliarInvalidoException  | ParejaInvalidaException e) 
		{		
			assertTrue( false );
		}
		
	}
	
	public void testPersonaObtenerSuegras()
	{
		Persona suegro = new Hombre( "Suegro", "s1" );
		Persona suegra = new Mujer( "Suegra", "s2" );
		
		Persona persona = new Hombre( "Persona", "p1" );
		try 
		{
			Persona pareja = new Mujer( suegro, suegra, "Pareja", "p2" );
		
			persona.setPareja( pareja );
			
			assertEquals( persona, pareja.getPareja() );
			assertEquals( pareja, persona.getPareja() );
			
			assertFalse( suegro.existePersonaEn( persona.getSuegras() ) );
			assertTrue( suegra.existePersonaEn( pareja.getSuegras() ) );
			
			assertTrue( persona.getSuegras().length == 1 );
			assertTrue( suegro.getSuegras().length == 0 );
			assertTrue( suegra.getSuegras().length == 0 );
			assertTrue( pareja.getSuegras().length == 0 );
		} 
		catch (PersonaAtributoInvalidoException | FamiliarInvalidoException  | ParejaInvalidaException e) 
		{		
			assertTrue( false );
		}
	}
	
	public void testPersonaObtenerPareja()
	{
		Persona hombre = new Hombre("hombre", "h1");
		Persona mujer = new Mujer("mujer", "M1");
		
		try 
		{
			mujer.setPareja( hombre );
			
			//
			
			assertEquals( mujer, hombre.getPareja() );
			assertEquals( hombre, mujer.getPareja() );
		} 
		catch (ParejaInvalidaException e) 
		{
			assertTrue( false );			
		}	
	}
	
	public void testPersonaObtenerParejaSiMismo()
	{
		Persona mujer = new Mujer("mujer", "M1");
		
		try 
		{
			mujer.setPareja( mujer );
			
			assertTrue( false );
		} 
		catch (ParejaInvalidaException e) 
		{
			assertTrue( true );			
		}	
	}
	
	public void testPersonaObtenerParejaNull()
	{
		Persona mujer = new Mujer("mujer", "M1");
		
		try 
		{
			mujer.setPareja( null );
			
			assertTrue( false );
		} 
		catch (ParejaInvalidaException e) 
		{
			assertTrue( true );			
		}	
	}
	
}
