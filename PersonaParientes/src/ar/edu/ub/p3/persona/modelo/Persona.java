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
	/////////////////////////////////////////////////////////////////////////
	
	
	///////////////////////////////////////////////////////////////////////////
	//
	
//	public enum PersonaSexo{MASCULINO, FEMENINO};
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	private Persona     padre = null;
	private Persona     madre = null;
	private Persona[]   hijos = new Persona[0];
	private Persona[]   hijas = new Persona[0] ;
	private Persona     pareja = null;
	
	private String      nombre;
	private String      dni;
//	private PersonaSexo sexo;

	///////////////////////////////////////////////////////////////////////////
	//
	
//	public Persona(Persona padre, Persona madre, String nombre, String dni, PersonaSexo sexo) throws FamiliarInvalidoException, PersonaAtributoInvalidoException
	public Persona(Persona padre, Persona madre, String nombre, String dni ) throws FamiliarInvalidoException, PersonaAtributoInvalidoException
	{	
//		this( nombre, dni, sexo);
		this( nombre, dni );
				
		setPadre(padre);
		setMadre(madre);
		
//		padre.agregarHijo( this );
//		madre.agregarHijo( this );
		
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	public Persona(String nombre, String dni ) throws PersonaAtributoInvalidoException
//	public Persona(String nombre, String dni, PersonaSexo sexo) throws PersonaAtributoInvalidoException
	{
		setNombre(nombre);
		setDni(dni);
//		setSexo(sexo);		
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
//		return getDni() + " " + getNombre() + " " + getSexo().toString(); 
		return getDni() + " " + getNombre(); 
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
/*		
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
*/
	
	private Persona[] agregar(Persona[] hijos, Persona persona)
	{
		Persona[] nuevosHijos = new Persona[ hijos.length + 1 ];
		
		//Copio los hijos
		copiar( hijos, nuevosHijos );
		
		//Agrego la persona al final
		nuevosHijos[ nuevosHijos.length - 1 ] = persona;
				
		return nuevosHijos;
	}
	
	private Persona[] agregar(Persona[] parientesOrigen, Persona[] parientesExistentes)
	{
//		parientesExistentes = parientesExistentes.clone();
		
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
	
	protected void setPadre(Persona padre) throws FamiliarInvalidoException
	{
		if( padre == null )
			throw new FamiliarInvalidoException("padre");
		
		this.padre = padre;
	}
	
	public Persona getMadre() throws FamiliarNotFoundException
	{
		if( madre == null )
			throw new FamiliarNotFoundException( String.format("La persona %s no tiene madre.", this));
		
		return madre;
	}
	
	protected void setMadre(Persona madre) throws FamiliarInvalidoException
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
/*
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
*/
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
	
		Persona[] parientes = new Persona[0];		
		
		return this.existePersonaEn(parientes);
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
			hermanos =  agregarHijos( agregarParejas( getMadre() ), agregarHijos( agregarParejas( getPadre() ) ) );			
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
			hermanas =  agregarHijas( agregarParejas( getMadre() ), agregarHijas( agregarParejas( getPadre() ) ) );
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
		return agregarHijos( agregarParejas( getHermanas() ), agregarHijos( agregarParejas( getHermanos() ) ) );
	}
	
	public Persona[] getSobrinas()
	{
		return agregarHijas( agregarParejas( getHermanas() ), agregarHijas( agregarParejas( getHermanos() ) ) );
	}

	///////////////////////////////////////////////////////////////////////////
	//
	
	public Persona[] getTios()
	{
		Persona[] 	tios = null;
		
		try
		{
			tios =  agregar( agregarHermanos( getMadre(), agregarHermanos( getPadre() ) ), obtenerParejas( agregarHermanas( getMadre(), agregarHermanas( getPadre() ) ) ) );
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
			tias =  agregar( agregarHermanas( getMadre(), agregarHermanas( getPadre() ) ), obtenerParejas( agregarHermanos( getMadre(), agregarHermanos( getPadre() ) ) ) );
		}
		catch (FamiliarNotFoundException e)
		{
			tias = new Persona[0];
		}
		
		return tias;
	}
	
	public Persona[] getPrimos()
	{
		return agregarHijos( agregarParejas( getTias() ), agregarHijos( agregarParejas( getTios() ) ) );
	}
	
	
	public Persona[] getPrimas()
	{
		return agregarHijas( agregarParejas( getTias() ), agregarHijas( agregarParejas( getTios() ) ) );
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

	private Persona[] agregarHermanas(Persona[] parientesOrigen, Persona[] parientesExistentes)
	{
		return new PersonaAgregarHermanas().agregarParientes(parientesOrigen, parientesExistentes);
	}

	private Persona[] agregarHermanas(Persona persona, Persona[] parientesExistentes)
	{
		return agregarHermanas( new Persona[] { persona }, parientesExistentes );
	}
	
	private Persona[] agregarHermanas(Persona persona)
	{
		return agregarHermanas( new Persona[] { persona }, new Persona[0]);
	}
	
	private Persona[] agregarHermanos(Persona[] parientesOrigen, Persona[] parientesExistentes)
	{
		return new PersonaAgregarHermanos().agregarParientes(parientesOrigen, parientesExistentes);
	}

	private Persona[] agregarHermanos(Persona persona, Persona[] parientesExistentes)
	{
		return agregarHermanos( new Persona[] { persona }, parientesExistentes );
	}
	
	private Persona[] agregarHermanos(Persona persona)
	{
		return agregarHermanos( new Persona[] { persona }, new Persona[0]);
	}	

	///////////////////////////////////////////////////////////////////////////
	//
	
	private Persona[] agregarParejas(Persona[] parientes)
	{
		Persona[] parejas = agregar( parientes, new Persona[0] );
		
		for( Persona persona : parientes )
			if( persona.getPareja() != null )
				parejas = agregar( parejas, persona.getPareja() );
		
		return parejas;
	}
	
	private Persona[] agregarParejas(Persona persona)
	{
		return agregarParejas( new Persona[] { persona } );		
	}	
	
	private Persona[] obtenerParejas(Persona[] personas)
	{
		Persona[] parejas = new Persona[0];
		
		for( Persona persona : personas )
			if( persona.getPareja() != null )
				parejas = agregar( parejas, persona.getPareja() );
		
		return parejas;
	}

	protected void agregarHijo(Hombre hombre) {
		setHijos( agregar( getHijos(), hombre ) );		
	}
	
	protected void agregarHijo(Mujer hombre) {
		setHijas( agregar( getHijas(), hombre ) );		
	}

	///////////////////////////////////////////////////////////////////////////
	//
	
}
