package ar.edu.ub.p3.persona.modelo;

import ar.edu.ub.p3.persona.excepciones.FamiliarInvalidoException;
import ar.edu.ub.p3.persona.excepciones.PersonaAtributoInvalidoException;

public class Mujer extends Persona {
	public Mujer(Persona padre, Persona madre, String nombre, String dni ) throws FamiliarInvalidoException, PersonaAtributoInvalidoException {
		super(padre, madre, nombre, dni );
	}

	public Mujer(String nombre, String dni ) throws PersonaAtributoInvalidoException {
		super(nombre, dni );
	}
	
	@Override
	protected void agregateComoHijoEn(Persona pariente) {
		pariente.agregarHijo( this );		
	}

}
