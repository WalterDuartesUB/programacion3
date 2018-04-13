package ar.edu.ub.p3.persona.excepciones;

public class FamiliarNotFoundException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public FamiliarNotFoundException(String msg)
	{
		super( msg );
	}
}
