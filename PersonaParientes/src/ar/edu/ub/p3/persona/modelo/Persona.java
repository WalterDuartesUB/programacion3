package ar.edu.ub.p3.persona.modelo;

import ar.edu.ub.p3.persona.excepciones.FamiliarInvalidoException;
import ar.edu.ub.p3.persona.excepciones.FamiliarNotFoundException;
import ar.edu.ub.p3.persona.excepciones.ParejaInvalidaException;
import ar.edu.ub.p3.persona.excepciones.PersonaAtributoInvalidoException;

public abstract class Persona
{

	/////////////////////////////////////////////////////////////////////////
	//
	
	abstract class PersonaAgregar{
		public abstract Persona[] getParientes(Persona persona);
		
		public Persona[] agregarParientes( Persona[] parientesOrigen, Persona[] parientesExistentes )
		{
			for( Persona pariente : parientesOrigen )
				if( pariente != null )
					parientesExistentes = agregar( this.getParientes( pariente ), parientesExistentes);
			
			return parientesExistentes;
		}
	}
	
	/////////////////////////////////////////////////////////////////////////
	//
	
	class PersonaAgregarHijos extends PersonaAgregar{

		@Override
		public Persona[] getParientes(Persona persona) {
			return persona.getHijos();
		}
		
	}
	
	class PersonaAgregarHijas extends PersonaAgregar{

		@Override
		public Persona[] getParientes(Persona persona) {
			return persona.getHijas();
		}
		
	}	
	
	/////////////////////////////////////////////////////////////////////////
	//
	
	class PersonaAgregarHermanos extends PersonaAgregar{
		
		@Override
		public Persona[] getParientes(Persona persona) {
			return persona.getHermanos();
		}
		
	}
	
	class PersonaAgregarHermanas extends PersonaAgregar{
		
		@Override
		public Persona[] getParientes(Persona persona) {
			return persona.getHermanas();
		}
		
	}	
	
	/////////////////////////////////////////////////////////////////////////
	//
	
	class PersonaAgregarPadres extends PersonaAgregar{
		
		@Override
		public Persona[] getParientes(Persona persona) {
			
			try 
			{
				return new Persona[] { persona.getPadre() };
			} 
			catch (FamiliarNotFoundException e) {
			}
			
			return new Persona[0];
		}
		
	}	
	
	class PersonaAgregarMadres extends PersonaAgregar{
		
		@Override
		public Persona[] getParientes(Persona persona) {
			
			try 
			{
				return new Persona[] { persona.getMadre() };
			} 
			catch (FamiliarNotFoundException e) {
			}
			
			return new Persona[0];
		}
		
	}	
	
	/////////////////////////////////////////////////////////////////////////
	//
	/////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	private Persona     padre = null;
	private Persona     madre = null;
	private Persona[]   hijos = new Persona[0];
	private Persona[]   hijas = new Persona[0] ;
	private Persona     pareja = null;
	
	private String      nombre;
	private String      dni;

	///////////////////////////////////////////////////////////////////////////
	//
	
	public Persona(Persona padre, Persona madre, String nombre, String dni ) throws FamiliarInvalidoException, PersonaAtributoInvalidoException
	{	
		this( nombre, dni );
				
		setPadre(padre);
		setMadre(madre);
		
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	public Persona(String nombre, String dni ) throws PersonaAtributoInvalidoException
	{
		setNombre(nombre);
		setDni(dni);
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
		return getDni() + " " + getNombre(); 
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	private Persona[] agregar(Persona[] hijos, Persona persona)
	{
		Persona[] nuevosHijos = new Persona[ hijos.length + 1 ];
		
		//Copio los hijos
		copiar( hijos, nuevosHijos );
		
		//Agrego la persona al final
		nuevosHijos[ nuevosHijos.length - 1 ] = persona;
				
		return nuevosHijos;
	}
	
	private Persona[] agregar(Persona unaPersona, Persona otraPersona) {
		
		Persona[] personas = new Persona[0];
		
		personas = agregar( personas, unaPersona );
		personas = agregar( personas, otraPersona );
		
		return personas;
	}
	
	private Persona[] agregar(Persona[] parientesOrigen, Persona[] parientesExistentes)
	{
		
		for( Persona pariente : parientesOrigen )
			if( !soyYo( pariente ) && !pariente.existePersonaEn( parientesExistentes ) )
				parientesExistentes = agregar( parientesExistentes, pariente );
		
		return parientesExistentes;
	}
	
	private Persona[] agregarHijos(Persona[] parientesOrigen )
	{
		return agregarHijos(parientesOrigen, new Persona[0]);
		
	}
	
	private Persona[] agregarHijos(Persona[] parientesOrigen, Persona[] parientesExistentes)
	{
		return new PersonaAgregarHijos().agregarParientes( parientesOrigen, parientesExistentes );
	}
	
	private Persona[] agregarHijas(Persona[] parientesOrigen, Persona[] parientesExistentes)
	{			
		return new PersonaAgregarHijas().agregarParientes( parientesOrigen, parientesExistentes );
	}
	
	private Persona[] agregarHijas(Persona[] parientesOrigen )
	{
		return agregarHijas(parientesOrigen, new Persona[0]);
	}
	
	private Persona[] agregarPadres(Persona  persona )
	{
		return agregarPadres( new Persona[] { persona } );
	}
	
	private Persona[] agregarPadres(Persona[] parientesOrigen )
	{
		return agregarPadres(parientesOrigen, new Persona[0]);
		
	}
	
	private Persona[] agregarPadres(Persona[] parientesOrigen, Persona[] parientesExistentes)
	{
		return new PersonaAgregarPadres().agregarParientes( parientesOrigen, parientesExistentes );
	}
	
	private Persona[] agregarMadres(Persona persona )
	{
		return agregarMadres( new Persona[] { persona } );
	}
	
	private Persona[] agregarMadres(Persona[] parientesOrigen )
	{
		return agregarMadres(parientesOrigen, new Persona[0]);
		
	}
	
	private Persona[] agregarMadres(Persona[] parientesOrigen, Persona[] parientesExistentes)
	{
		return new PersonaAgregarMadres().agregarParientes( parientesOrigen, parientesExistentes );
	}		
	
	///////////////////////////////////////////////////////////////////////////
	//
	
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
			throw new FamiliarInvalidoException("padre");
		
		this.padre = padre;
		
		this.agregateComoHijoEn( padre );
	}

	public Persona getMadre() throws FamiliarNotFoundException
	{
		if( madre == null )
			throw new FamiliarNotFoundException( String.format("La persona %s no tiene madre.", this));
		
		return madre;
	}
	
	private void setMadre(Persona madre) throws FamiliarInvalidoException
	{
		if( madre == null )
			throw new FamiliarInvalidoException("madre");
		
		this.madre = madre;
		
		this.agregateComoHijoEn( madre );
	}
	
	protected abstract void agregateComoHijoEn(Persona pariente);
	
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
	
	public Persona getPareja()
	{
		return pareja;
	}
	
	public void setPareja(Persona pareja) throws ParejaInvalidaException
	{		
		Persona.emparejar( this, pareja );
	}

	private boolean validarPareja(Persona pareja)
	{		
		return pareja != null && !this.soyYo( pareja ) && !this.soyFamiliarDe( pareja );
	}

	private boolean soyFamiliarDe(Persona persona) 
	{
		//Siempre puedo emparejar
		return false;
		
//		Persona[] parientes = new Persona[0];				
//		return this.existePersonaEn(parientes);
		
	}

	private static void emparejar(Persona persona, Persona pareja) throws ParejaInvalidaException
	{
		if( pareja == null || persona == null )
			throw new ParejaInvalidaException();
		
		if( !persona.validarPareja( pareja ) )
			throw new ParejaInvalidaException();
		
		persona.pareja = pareja;
		pareja.pareja = persona;		
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
		Persona[] 	hermanos = null;
		
		try
		{			
			hermanos =  agregarHijos( agregar( getPadre(), getMadre() ) );			
		}
		catch (FamiliarNotFoundException e)
		{
			hermanos = new Persona[0];
		}
		
		return hermanos;
	}

	public Persona[] getHermanas()
	{
		Persona[] 	hermanas = null;
		
		try
		{
			hermanas =  agregarHijas( agregar( getPadre(), getMadre() ) );
		}
		catch (FamiliarNotFoundException e)
		{
			hermanas = new Persona[0];
		}
		
		return hermanas;
	}

	///////////////////////////////////////////////////////////////////////////
	//
	
	public Persona[] getSobrinos()
	{
		return agregarHijos( agregar( getHermanas(), getHermanos() ) );
	}
	
	public Persona[] getSobrinas()
	{
		return agregarHijas( agregar( getHermanas(), getHermanos() ) );
	}

	///////////////////////////////////////////////////////////////////////////
	//,
	
	public Persona[] getTios()
	{
		Persona[] 	tios = null;
		
		try
		{
			tios = agregarHermanos( agregar( getMadre(), getPadre() ) );
		}
		catch (FamiliarNotFoundException e)
		{
			tios = new Persona[0];
		}
		
		return tios;
	}
	
	public Persona[] getTias()
	{
		Persona[] 	tias = null;
		
		try
		{
			tias = agregarHermanas( agregar( getMadre(), getPadre() ) );
		}
		catch (FamiliarNotFoundException e)
		{
			tias = new Persona[0];
		}
		
		return tias;
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	public Persona[] getPrimos()
	{
		return agregarHijos( agregar( getTias(), getTios() ) );
	}
	
	
	public Persona[] getPrimas()
	{
		return agregarHijas( agregar( getTias(), getTios() ) );
	}
	
	///////////////////////////////////////////////////////////////////////////
	//	
	
	public Persona[] getNietos()
	{
		return agregarHijos( agregar( this.getHijas(), this.getHijos() ) );
	}
	
	public Persona[] getNietas() {
		return agregarHijas( agregar( this.getHijas(), this.getHijos() ) );
	}	
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	public Persona[] getAbuelos()
	{
		try {
			return agregarPadres( agregar( this.getPadre(), this.getMadre() ) );
		} catch (FamiliarNotFoundException e) {
		}
		
		return new Persona[0];
	}
	
	public Persona[] getAbuelas()
	{
		try {
			return agregarMadres( agregar( this.getPadre(), this.getMadre() ) );
		} catch (FamiliarNotFoundException e) {
		}
		
		return new Persona[0];
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	public Persona[] getSuegros()
	{
		return agregarPadres( this.getPareja() );
	}
	
	public Persona[] getSuegras()
	{
		return agregarMadres( this.getPareja() );
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	private Persona[] agregarHermanas(Persona[] parientes )
	{
		return new PersonaAgregarHermanas().agregarParientes( parientes, new Persona[0]);
	}
	
	private Persona[] agregarHermanos(Persona[] parientes )
	{
		return new PersonaAgregarHermanos().agregarParientes( parientes, new Persona[0]);
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	protected void agregarHijo(Hombre hombre) {
		setHijos( agregar( getHijos(), hombre ) );		
	}
	
	protected void agregarHijo(Mujer hombre) {
		setHijas( agregar( getHijas(), hombre ) );		
	}

	///////////////////////////////////////////////////////////////////////////
	//
	
}
