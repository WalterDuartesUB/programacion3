package ar.edu.ub.p3.persona.testcases;

import java.util.Arrays;

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
			String nombreHijo = "hijo";
			String dniHijo = "H1";
			PersonaSexo sexoHijo = PersonaSexo.MASCULINO;
			
			Persona hijo = new Persona( padre, madre, nombreHijo, dniHijo, sexoHijo );
					
			//Verifico los datos de creacion del hijo
			assertEquals( nombreHijo, hijo.getNombre() );			
			assertEquals( dniHijo, hijo.getDni() );			
			assertEquals( sexoHijo, hijo.getSexo() );
			
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
		Persona padre = new Persona( "Padre", "H1", PersonaSexo.MASCULINO );
		Persona madre = new Persona( "Madre", "M1", PersonaSexo.FEMENINO );
		
		try 
		{
			String nombreHijo = "hija";
			String dniHijo = "H1";
			PersonaSexo sexoHijo = PersonaSexo.FEMENINO;
			
			Persona hija = new Persona( padre, madre, nombreHijo, dniHijo, sexoHijo );
						
			//Verifico los datos de creacion del hijo
			assertEquals( nombreHijo, hija.getNombre() );			
			assertEquals( dniHijo, hija.getDni() );			
			assertEquals( sexoHijo, hija.getSexo() );
			
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

		Persona padre = new Persona( "Padre", "H1", PersonaSexo.MASCULINO );
		Persona madre = new Persona( "Madre", "M1", PersonaSexo.FEMENINO );
		
		try 
		{
			Persona hija = new Persona( padre, madre, "hijo", "H1", PersonaSexo.FEMENINO );
			Persona otroHija = new Persona( padre, madre, "hermana", "He1", PersonaSexo.FEMENINO );
			
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
		Persona abuelo = new Persona( "Abuelo", "AM1", PersonaSexo.MASCULINO );
		Persona abuela = new Persona( "Abuela", "AF1", PersonaSexo.FEMENINO );
		
		Persona otroAbuelo = new Persona( "Abuelo 2", "AM2", PersonaSexo.MASCULINO );
		Persona otraAbuela = new Persona( "Abuela 2", "AF2", PersonaSexo.FEMENINO );
					
		try 
		{
			
			Persona padre = new Persona( abuelo, abuela, "Padre", "H1", PersonaSexo.MASCULINO );
			Persona madre = new Persona( otroAbuelo, otraAbuela, "Madre", "M1", PersonaSexo.FEMENINO );
			
			Persona tio1 = new Persona( abuelo, abuela, "Tio 1", "T1", PersonaSexo.MASCULINO );
			Persona tio2 = new Persona( abuelo, abuela, "Tio 2", "T2", PersonaSexo.MASCULINO );
			
			Persona hijo = new Persona( padre, madre, "hijo", "H1", PersonaSexo.MASCULINO );
						
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
		Persona abuelo = new Persona( "Abuelo", "AM1", PersonaSexo.MASCULINO );
		Persona abuela = new Persona( "Abuela", "AF1", PersonaSexo.FEMENINO );
		
		Persona otroAbuelo = new Persona( "Abuelo 2", "AM2", PersonaSexo.MASCULINO );
		Persona otraAbuela = new Persona( "Abuela 2", "AF2", PersonaSexo.FEMENINO );
					
		try 
		{
			
			Persona padre = new Persona( abuelo, abuela, "Padre", "H1", PersonaSexo.MASCULINO );
			Persona madre = new Persona( otroAbuelo, otraAbuela, "Madre", "M1", PersonaSexo.FEMENINO );
			
			Persona tia1 = new Persona( abuelo, abuela, "tia 1", "T1", PersonaSexo.FEMENINO );
			Persona tia2 = new Persona( abuelo, abuela, "tia 2", "T2", PersonaSexo.FEMENINO );
			
			Persona hijo = new Persona( padre, madre, "hijo", "H1", PersonaSexo.FEMENINO );
						
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
		Persona abuelo = new Persona( "Abuelo", "AM1", PersonaSexo.MASCULINO );
		Persona abuela = new Persona( "Abuela", "AF1", PersonaSexo.FEMENINO );
		
		Persona otroAbuelo = new Persona( "Abuelo 2", "AM2", PersonaSexo.MASCULINO );
		Persona otraAbuela = new Persona( "Abuela 2", "AF2", PersonaSexo.FEMENINO );
					
		try 
		{
			
			Persona padre = new Persona( abuelo, abuela, "Padre", "H1", PersonaSexo.MASCULINO );
			Persona madre = new Persona( otroAbuelo, otraAbuela, "Madre", "M1", PersonaSexo.FEMENINO );
			
			Persona tio1 = new Persona( abuelo, abuela, "Tio 1", "T1", PersonaSexo.MASCULINO );
			Persona tio2 = new Persona( abuelo, abuela, "Tio 2", "T2", PersonaSexo.MASCULINO );
			
			Persona tia1 = new Persona( abuelo, abuela, "tia 1", "TF1", PersonaSexo.FEMENINO );
			
			Persona hijo = new Persona( padre, madre, "hijo", "H1", PersonaSexo.MASCULINO );
						
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
		//TODO: falta implementar el caso de prueba		
	}
	
	public void testPersonaObtenerPrimas()
	{
		//TODO: falta implementar el caso de prueba		
	}
	
	public void testPersonaObtenerSobrinos()
	{
		//TODO: falta implementar el caso de prueba		
	}
	
	public void testPersonaObtenerSobrinas()
	{
		//TODO: falta implementar el caso de prueba
	}
		
}
