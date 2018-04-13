package ar.edu.ub.p3.persona.modelo;

import ar.edu.ub.p3.persona.excepciones.FamiliarInvalidoException;
import ar.edu.ub.p3.persona.excepciones.FamiliarNotFoundException;
import ar.edu.ub.p3.persona.excepciones.PersonaAtributoInvalidoException;

public class Persona
{
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	public enum PersonaSexo{MASCULINO, FEMENINO};
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	private Persona     padre = null;
	private Persona     madre = null;
	private Persona[]   hijos = new Persona[0];
	private Persona[]   hijas = new Persona[0] ;
	private Persona     pareja = null;
	
	private String      nombre;
	private String      dni;
	private PersonaSexo sexo;

	///////////////////////////////////////////////////////////////////////////
	//
	
	public Persona(Persona padre, Persona madre, String nombre, String dni, PersonaSexo sexo) throws FamiliarInvalidoException, PersonaAtributoInvalidoException
	{	
		this( nombre, dni, sexo);
		
		setPadre(padre);
		setMadre(madre);
		
		padre.agregarHijo( this );
		madre.agregarHijo( this );
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	public Persona(String nombre, String dni, PersonaSexo sexo) throws PersonaAtributoInvalidoException
	{
		setNombre(nombre);
		setDni(dni);
		setSexo(sexo);	
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	public boolean soyYo( Persona unaPersona )
	{
		return getDni().compareTo( unaPersona.getDni() ) == 0;
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	@Override
	public String toString()
	{	
		return getDni() + " " + getNombre() + " " + getSexo().toString(); 
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
		
	private void agregarHijo(Persona persona)
	{
		if( persona.esMasculino() )
			setHijos( agregar( getHijos(), persona ) );		
		else
			setHijas( agregar( getHijas(), persona ) );
		
	}

	private boolean esMasculino()
	{
		return getSexo() == PersonaSexo.MASCULINO;
	}
	
	private Persona[] agregar(Persona[] hijos, Persona persona)
	{
		Persona[] nuevosHijos = new Persona[ hijos.length + 1 ];
		
		//Copio los hijos
		copiar( hijos, nuevosHijos );
		
		//Agrego la persona al final
		nuevosHijos[ nuevosHijos.length - 1 ] = persona;
				
		return nuevosHijos;
	}
	
	private Persona[] agregar(Persona[] parientesOrigen, Persona[] parientesDestino)
	{
		parientesDestino = parientesDestino.clone();
		
		for( Persona pariente : parientesOrigen )
			if( !soyYo( pariente ) && !pariente.existePersonaEn( parientesDestino ) )
				parientesDestino = agregar( parientesDestino, pariente );
		
		return parientesDestino;
	}	

	public boolean existePersonaEn(Persona[] parientes )
	{
		for( Persona pariente : parientes )
			if( pariente.soyYo( this ) )
				return true;
			
		return false;
	}

	///////////////////////////////////////////////////////////////////////////
	//
	
	private void copiar(Persona[] origen, Persona[] destino)
	{
		for( int posicion = 0; posicion < origen.length; posicion ++ )
			destino[posicion] = origen[posicion];		
	}

	///////////////////////////////////////////////////////////////////////////
	//
	
	public Persona getPadre() throws FamiliarNotFoundException
	{
		if( padre == null )
			throw new FamiliarNotFoundException( String.format("La persona %s no tiene padre.", this));
		
		return padre;
	}
	
	private void setPadre(Persona padre) throws FamiliarInvalidoException
	{
		if( padre == null )
			throw new FamiliarInvalidoException("madre");
		
		this.padre = padre;
	}
	
	public Persona getMadre() throws FamiliarNotFoundException
	{
		if( padre == null )
			throw new FamiliarNotFoundException( String.format("La persona %s no tiene madre.", this));
		
		return madre;
	}
	
	private void setMadre(Persona madre) throws FamiliarInvalidoException
	{
		if( madre == null )
			throw new FamiliarInvalidoException("madre");
		
		this.madre = madre;
	}
	
	public Persona[] getHijos()
	{
		return hijos;
	}
	
	private void setHijos(Persona[] hijos)
	{
		this.hijos = hijos;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	private void setNombre(String nombre) throws PersonaAtributoInvalidoException
	{
		if( !this.validarNombre( nombre ) )
			throw new PersonaAtributoInvalidoException();
			
		this.nombre = nombre;
	}
	
	private boolean validarNombre(String nombre) {
		return !( nombre == null || nombre.trim().isEmpty() );
	}

	public String getDni()
	{
		return dni;
	}
	
	private void setDni(String dni) throws PersonaAtributoInvalidoException
	{
		if( !this.validarDni( dni ) )
			throw new PersonaAtributoInvalidoException();
		
		this.dni = dni;
	}
	
	private boolean validarDni(String dni) {
		return !( dni == null || dni.trim().isEmpty() );
	}

	public PersonaSexo getSexo()
	{
		return sexo;
	}
	
	private void setSexo(PersonaSexo sexo) throws PersonaAtributoInvalidoException
	{
		if( !this.validarSexo( sexo ) )
			throw new PersonaAtributoInvalidoException();
		
		this.sexo = sexo;
	}
	
	private boolean validarSexo(PersonaSexo sexo) {
		return sexo != null;
	}

	public Persona getPareja()
	{
		return pareja;
	}
	
	public void setPareja(Persona pareja)
	{
		this.pareja = pareja;
	}

	public Persona[] getHijas()
	{
		return hijas;
	}

	private void setHijas(Persona[] hijas)
	{
		this.hijas = hijas;
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	public Persona[] getHermanos()
	{
		Persona[] hermanos = new Persona[0];
		
		try
		{
			hermanos = agregar( getPadre().getHijos(), hermanos);
		}
		catch (FamiliarNotFoundException e) 
		{
		}
		
		try 
		{
			hermanos = agregar( getMadre().getHijos(), hermanos);
		}
		catch (FamiliarNotFoundException e) 
		{
		}
		
		return hermanos;
	}

	public Persona[] getHermanas()
	{
		Persona[] hermanas = new Persona[0];
	
		try 
		{
			hermanas = agregar( getPadre().getHijas(), hermanas);
		}
		catch (FamiliarNotFoundException e) 
		{
		}
		
		try 
		{
			hermanas = agregar( getMadre().getHijas(), hermanas);
			
		}
		catch (FamiliarNotFoundException e) 
		{
		}		
		
		return hermanas;
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	public Persona[] getTios()
	{
		Persona[] tios = new Persona[0];
		try 
		{
			tios = agregar( getPadre().getHermanos(), tios);
		}
		catch (FamiliarNotFoundException e) 
		{
		}		
		
		try 
		{
			tios = agregar( getMadre().getHermanos(), tios);
		}
		catch (FamiliarNotFoundException e) 
		{
		}					
		
		return tios;
	}
	
	public Persona[] getTias()
	{
		Persona[] tias = new Persona[0];
		
		try 
		{
			tias = agregar( getPadre().getHermanas(), tias);
		}
		catch (FamiliarNotFoundException e) 
		{
		}		
		
		try 
		{			
			tias = agregar( getMadre().getHermanas(), tias);
		}
		catch (FamiliarNotFoundException e) 
		{
		}		
		
		return tias;
	}
	
}
