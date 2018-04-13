import java.util.Arrays;

import ar.edu.ub.p3.persona.excepciones.FamiliarNotFoundException;
import ar.edu.ub.p3.persona.modelo.Persona;
import ar.edu.ub.p3.persona.modelo.Persona.PersonaSexo;

public class Aplicacion
{

	public static void main(String[] args) throws FamiliarNotFoundException
	{
		/*
		Persona padre = new Persona( "Padre", "123", PersonaSexo.MASCULINO );
		Persona madre = new Persona( "Madre", "321", PersonaSexo.FEMENINO ); 
		
//		Persona padreNuera = new Persona( "Padre N", "N 123", PersonaSexo.MASCULINO );
//		Persona madreNuera = new Persona( "Madre N", "N 321", PersonaSexo.FEMENINO );		

		Persona hijo = new Persona( padre, madre, "Hijo", "456", PersonaSexo.MASCULINO );
		Persona hijo2 = new Persona( padre, madre, "Hijo2", "44456", PersonaSexo.MASCULINO );
		Persona hija = new Persona( padre, madre, "Hija", "3456", PersonaSexo.FEMENINO );
		
//		Persona nuera = new Persona( padreNuera, madreNuera, "Nuera", "14545321", PersonaSexo.FEMENINO );
		Persona nuera = new Persona( "Nuera", "14545321", PersonaSexo.FEMENINO );
		Persona nieto = new Persona( hijo, nuera, "Nieto", "1111456", PersonaSexo.MASCULINO );
		
		System.out.println( hijo );
		System.out.println( hijo.getPadre() );
		System.out.println( hijo.getMadre() );
		
		System.out.println( Arrays.toString( hijo.getPadre().getHijos() ) );
		System.out.println( Arrays.toString( hijo.getPadre().getHijas() ) );
		
		System.out.println( Arrays.toString( hijo.getMadre().getHijos() ) );
		System.out.println( Arrays.toString( hijo.getMadre().getHijas() ) );
		
		System.out.println( hijo.soyYo( hijo ) );
		System.out.println( hijo.soyYo( hijo2 ) );
		System.out.println( hijo.soyYo( padre ) );
		System.out.println( hijo.soyYo( madre ) );
		
		///////////////////////////////////////////////////////////////////////
		//
		
		System.out.println("Pruebas de hermanos");
		
		System.out.println( Arrays.toString( hijo.getHermanos() ) );
		System.out.println( Arrays.toString( hijo.getHermanas() ) );

		
		System.out.println( Arrays.toString( hijo2.getHermanos() ) );
		System.out.println( Arrays.toString( hijo2.getHermanas() ) );
		
		
		System.out.println( Arrays.toString( hija.getHermanos() ) );
		System.out.println( Arrays.toString( hija.getHermanas() ) );
		
		///////////////////////////////////////////////////////////////////////
		//
		
		System.out.println("Pruebas de tios");		
		
		System.out.println( Arrays.toString( nieto.getTios() ) );
		System.out.println( Arrays.toString( nieto.getTias() ) );
		
		*/
	}

}
