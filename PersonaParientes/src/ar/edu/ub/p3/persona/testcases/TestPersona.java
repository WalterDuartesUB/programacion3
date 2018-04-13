package ar.edu.ub.p3.persona.testcases;

import ar.edu.ub.p3.persona.excepciones.FamiliarInvalidoException;
import ar.edu.ub.p3.persona.excepciones.FamiliarNotFoundException;
import ar.edu.ub.p3.persona.excepciones.PersonaAtributoInvalidoException;
import ar.edu.ub.p3.persona.modelo.Persona;
import ar.edu.ub.p3.persona.modelo.Persona.PersonaSexo;
import junit.framework.TestCase;

public class TestPersona extends TestCase
{
	public void testPersonaNombreNull()
	{		
		try {
			Persona persona = new Persona( null, "P1", PersonaSexo.MASCULINO );
			assertTrue( false );
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( true );
		}
	}
	
	public void testPersonaNombreVacio()
	{		
		try {
			Persona persona = new Persona( " ", "P1", PersonaSexo.MASCULINO );
			assertTrue( false );
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( true );
		}
	}
	
	public void testPersonaDniNull()
	{		
		try {
			Persona persona = new Persona( "persona", null, PersonaSexo.MASCULINO );
			assertTrue( false );
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( true );
		}
	}
	
	public void testPersonaDniVacio()
	{		
		try {
			Persona persona = new Persona( "persona", " ", PersonaSexo.MASCULINO );
			assertTrue( false );
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( true );
		}
	}
	
	public void testPersonaSexoNull()
	{		
		try {
			Persona persona = new Persona( "persona", "P1", null );
			assertTrue( false );
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( true );
		}
	}
	
	public void testPersonaPadreNull()
	{
		Persona padre = null;
		Persona madre = new Persona( "Madre", "M1", PersonaSexo.FEMENINO );
		
		try {
			Persona hijo = new Persona( padre, madre, "Hijo", "H1", PersonaSexo.MASCULINO );
			assertTrue( false );
		} catch (FamiliarInvalidoException e) {
			assertTrue( true );
		}
	}
	
	public void testPersonaMadreNull()
	{
		Persona padre = new Persona( "Madre", "M1", PersonaSexo.FEMENINO );
		Persona madre = null;
		
		try {
			Persona hijo = new Persona( padre, madre, "Hijo", "H1", PersonaSexo.MASCULINO );
			assertTrue( false );
		} catch (FamiliarInvalidoException e) {
			assertTrue( true );
		}	
	}
	
	public void testHijoNombreNull()
	{		
	
		Persona padre = new Persona( "Padre", "H1", PersonaSexo.MASCULINO );
		Persona madre = new Persona( "Madre", "M1", PersonaSexo.FEMENINO );
		
		try {
			Persona hijo = new Persona( padre, madre, null, "H1", PersonaSexo.MASCULINO );
			assertTrue( false );
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( true );
		} catch (FamiliarInvalidoException e) {
			assertTrue( false );
		}
	}
	
	public void testHijoNombreVacio()
	{		
	
		Persona padre = new Persona( "Padre", "H1", PersonaSexo.MASCULINO );
		Persona madre = new Persona( "Madre", "M1", PersonaSexo.FEMENINO );
		
		try {
			Persona hijo = new Persona( padre, madre, " ", "H1", PersonaSexo.MASCULINO );
			assertTrue( false );
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( true );
		} catch (FamiliarInvalidoException e) {
			assertTrue( false );
		}
	}
	
	public void testHijoDniNull()
	{	

		Persona padre = new Persona( "Padre", "H1", PersonaSexo.MASCULINO );
		Persona madre = new Persona( "Madre", "M1", PersonaSexo.FEMENINO );	
		
		try 
		{
			Persona hijo = new Persona( padre, madre, "hijo", null, PersonaSexo.MASCULINO );
			assertTrue( false );
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( true );
		} catch (FamiliarInvalidoException e) {
			assertTrue( false );
		}
	}
	
	public void testHijoDniVacio()
	{	
		Persona padre = new Persona( "Padre", "H1", PersonaSexo.MASCULINO );
		Persona madre = new Persona( "Madre", "M1", PersonaSexo.FEMENINO );
		
		try 
		{
			Persona hijo = new Persona( padre, madre, "hijo", " ", PersonaSexo.MASCULINO );
			assertTrue( false );
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( true );
		} catch (FamiliarInvalidoException e) {
			assertTrue( false );
		}
	}
	
	public void testHijoSexoNull()
	{		
	
		Persona padre = new Persona( "Padre", "H1", PersonaSexo.MASCULINO );
		Persona madre = new Persona( "Madre", "M1", PersonaSexo.FEMENINO );
		
		try 
		{
			Persona hijo = new Persona( padre, madre, "hijo", "H1", null );
			assertTrue( false );
		} catch (PersonaAtributoInvalidoException e) {
			assertTrue( true );
		} catch (FamiliarInvalidoException e) {
			assertTrue( false );
		}
	}
	
	public void testPersonaCrearHijo()
	{
		Persona padre = new Persona( "Padre", "H1", PersonaSexo.MASCULINO );
		Persona madre = new Persona( "Madre", "M1", PersonaSexo.FEMENINO );
		
		try 
		{
			Persona hijo = new Persona( padre, madre, "hijo", "H1", PersonaSexo.MASCULINO );
			
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
		Persona padre = new Persona( "Padre", "H1", PersonaSexo.MASCULINO );
		Persona madre = new Persona( "Madre", "M1", PersonaSexo.FEMENINO );
		
		try 
		{
			Persona hija = new Persona( padre, madre, "hijo", "H1", PersonaSexo.FEMENINO );
			
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
		Persona padre = new Persona( "Padre", "H1", PersonaSexo.MASCULINO );
		Persona madre = new Persona( "Madre", "M1", PersonaSexo.FEMENINO );
		
		try 
		{
			Persona hijo = new Persona( padre, madre, "hijo", "H1", PersonaSexo.MASCULINO );
			Persona otroHijo = new Persona( padre, madre, "hermano", "He1", PersonaSexo.MASCULINO );
			
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
		
	}		
	
	public void testPersonaObtenerTios()
	{
		
	}		
	
	public void testPersonaObtenerTias()
	{
		
	}		
}
