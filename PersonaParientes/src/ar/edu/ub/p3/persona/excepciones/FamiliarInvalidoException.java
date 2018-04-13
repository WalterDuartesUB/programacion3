package ar.edu.ub.p3.persona.excepciones;

public class FamiliarInvalidoException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public FamiliarInvalidoException( String pariente )
	{
		super( String.format("Debe tener un %s para poder crear una Persona", pariente ) );
	}

}
