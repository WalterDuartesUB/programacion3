package ar.edu.ub.p3.persona.modelo;

import ar.edu.ub.p3.persona.excepciones.FamiliarInvalidoException;
import ar.edu.ub.p3.persona.excepciones.PersonaAtributoInvalidoException;

public class Hombre extends Persona {

	public Hombre(Persona padre, Persona madre, String nombre, String dni ) throws FamiliarInvalidoException, PersonaAtributoInvalidoException {
		super(padre, madre, nombre, dni );
	}

	public Hombre(String nombre, String dni ) throws PersonaAtributoInvalidoException {
		super(nombre, dni );
	}
/*	
	@Override
	protected void setPadre( Persona padre ) throws FamiliarInvalidoException
	{
		super.setPadre(padre);
		
		padre.agregarHijo( this );
	}

	@Override
	protected void setMadre( Persona madre ) throws FamiliarInvalidoException
	{
		super.setMadre( madre );
		
		madre.agregarHijo( this );
	}
	*/

	@Override
	protected void agregateComoHijoEn(Persona pariente) {
		pariente.agregarHijo( this );		
	}
/*
	@Override
	protected void agregarComoMadre(Persona madre) {
		madre.agregarHijo( this );		
	}
*/
}
