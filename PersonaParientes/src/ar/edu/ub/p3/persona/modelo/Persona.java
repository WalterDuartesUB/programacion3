package ar.edu.ub.p3.persona.modelo;

import java.util.Arrays;

import ar.edu.ub.p3.persona.excepciones.FamiliarInvalidoException;
import ar.edu.ub.p3.persona.excepciones.FamiliarNotFoundException;
import ar.edu.ub.p3.persona.excepciones.ParejaInvalidaException;
import ar.edu.ub.p3.persona.excepciones.PersonaAtributoInvalidoException;

public abstract class Persona
{

	/////////////////////////////////////////////////////////////////////////
	//
	
	abstract class PersonaAgregar{
		public PersonaAgregar()
		{
			
		}
		
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
	
	final class PersonaAgregarHijos extends PersonaAgregar{

		@Override
		public Persona[] getParientes(Persona persona) {
			return persona.getHijos();
		}
		
	}
	
	final class PersonaAgregarHijas extends PersonaAgregar{

		@Override
		public Persona[] getParientes(Persona persona) {
			return persona.getHijas();
		}
		
	}	
	
	/////////////////////////////////////////////////////////////////////////
	//
	
	final class PersonaAgregarHermanos extends PersonaAgregar{
		
		@Override
		public Persona[] getParientes(Persona persona) {
			return persona.getHermanos();
		}
		
	}
	
	final class PersonaAgregarHermanas extends PersonaAgregar{
		
		@Override
		public Persona[] getParientes(Persona persona) {
			return persona.getHermanas();
		}
		
	}	
	
	/////////////////////////////////////////////////////////////////////////
	//
	
	final class PersonaAgregarPadres extends PersonaAgregar{
		
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
	
	final class PersonaAgregarMadres extends PersonaAgregar{
		
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
	
	final class Familia
	{
		private Persona[] familiares = new Persona[0];
		
		public Familia( Persona persona )
		{
			agregar( persona );
		}
/*
		public void agregarFamiliar(Persona persona) 
		{
			setFamiliares( agregar( new Persona[] { persona } , getFamiliares()) );			
		}

		public boolean existeFamiliaEn(Familia[] familias) 
		{
			for( Familia flia : familias )
				if( soyYo( flia ) )
					return true;
			return false;
		}

		private boolean soyYo(Familia flia) 
		{
			return this == flia;
		}
*/
		private Persona[] getFamiliares() 
		{
			return familiares;
		}

		private void setFamiliares(Persona[] familiares) 
		{
			this.familiares = familiares;
		}
		
		@Override
		public String toString() 
		{
			return Arrays.toString( this.getFamiliares() );
		}

		public void agregar(Familia familia) 
		{
			for( Persona persona : familia.getFamiliares() )
				agregar( persona );			
		}

		public void agregar(Persona persona) 
		{
			setFamiliares( persona.agregar( getFamiliares(), persona ) );
		}
		
		public void limpiar() {
			for( int posicion = 0; posicion < this.getFamiliares().length; posicion++)
				this.getFamiliares()[posicion] = null;
			
			this.setFamiliares( new Persona[0]);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////
	//
	/////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////////////////////////////////////////////////
	//
	
//	private Familia[]   familias = new Familia[0];
	private Familia		familia = null;
	
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
		this( nombre, dni, false );
					
		//Mi familia es la de mi padre
		setFamilia( padre.getFamilia() );
		
		//A mi familia, agrego los familiares de la familia de mi madre
		this.getFamilia().agregar( madre.getFamilia() );
		
		//A mi familia, me agrego yo
		this.getFamilia().agregar( this );
		
		//A mi madre, le digo que su familia es mi familia
		madre.setFamilia( this.getFamilia() );
		
		///////////////////////////////////////////////////////////////////////
		//Asigno mi padre y mi madre
		
		setPadre(padre);
		setMadre(madre);
		
/*		
		//Reorganizo las familias		
		agregarFamilias( padre.getFamilias() );
		agregarFamilias( madre.getFamilias() );
		
		padre.agregarFamilias( this.getFamilias() );
		madre.agregarFamilias( this.getFamilias() );
		
		padre.agregarFamilias( madre.getFamilias() );
		madre.agregarFamilias( padre.getFamilias() );
*/	
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	public Persona(String nombre, String dni ) throws PersonaAtributoInvalidoException
	{
		this( nombre, dni, true );
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	private Persona(String nombre, String dni, boolean debeCrearFamilia ) throws PersonaAtributoInvalidoException
	{
		setNombre(nombre);
		setDni(dni);
		
		if( debeCrearFamilia )
			setFamilia( new Familia( this ) );
	}
	
	public Familia getFamilia() 
	{
		return familia;
	}

	private void setFamilia(Familia familia) 
	{				
		this.familia = familia;
		
		try 
		{
			if( this.getPadre() != null )			
				this.getPadre().setFamilia( familia );
			
			if( this.getMadre() != null )
			{
				this.getMadre().getFamilia().limpiar();
				this.getMadre().setFamilia( familia );
			}
		} 
		catch (FamiliarNotFoundException e) 
		{

		}		
	}
	
/*	
	///////////////////////////////////////////////////////////////////////////
	//
	
	private void agregarFamilia(Familia familia) 
	{
		agregarFamilias( new Familia[] { familia } );
	}

	private void agregarFamilias(Familia[] familias) 
	{
		Familia[] nuevasFamilias = this.getFamilias();
		
		for( Familia flia : familias )
			if( !flia.existeFamiliaEn( nuevasFamilias ) )
			{
				flia.agregarFamiliar( this );
				nuevasFamilias = agregar( nuevasFamilias, flia );
			}
		
		setFamilias( nuevasFamilias );
	}	

	private Familia[] agregar(Familia[] hijos, Familia persona)
	{
		Familia[] nuevosHijos = new Familia[ hijos.length + 1 ];
		
		//Copio los hijos
		copiar( hijos, nuevosHijos );
		
		//Agrego la persona al final
		nuevosHijos[ nuevosHijos.length - 1 ] = persona;
				
		return nuevosHijos;
	}
	
	private Familia[] getFamilias() 
	{
		return familias;
	}

	private void setFamilias(Familia[] familias) 
	{
		this.familias = familias;
	}
*/
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
		
		// Si ya existe en la lista, no redimensiono		
		if( persona.existePersonaEn( hijos ) )
			return hijos;
			
		//Agrego a la lista el hijo nuevo
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
/*	
	private void copiar(Familia[] origen, Familia[] destino)
	{
		for( int posicion = 0; posicion < origen.length; posicion ++ )
			destino[posicion] = origen[posicion];		
	}
*/
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
	
	/**
	 * Empareja un Persona con otra Persona
	 * @param pareja un Persona a emparejar
	 * @throws ParejaInvalidaException cuando pareja es null 
	 * @throws ParejaInvalidaException cuando soyYo(pareja) es true 
	 * @throws ParejaInvalidaException cuando soyFamiliarDe(pareja) es true 
	 */
	
	public void setPareja(Persona pareja) throws ParejaInvalidaException
	{		
		Persona.emparejar( this, pareja );
	}

	private boolean validarPareja(Persona pareja)
	{		
		return pareja != null && !this.soyYo( pareja ) && ( this.tenemosHijosEnComun( pareja ) || !this.soyFamiliarDe( pareja ) );
	}

	private boolean tenemosHijosEnComun(Persona persona) 
	{
		//Me fijo si la persona es padre o madre de alguno de mis hijos
		Persona[] hijos = agregar( this.getHijos(), this.getHijas() );
		
		try 
		{		
			for( Persona hijo : hijos )
				if( hijo.getMadre() == persona || hijo.getPadre() == persona )
					return true;		
		} 
		catch (FamiliarNotFoundException e) 
		{
		}
		
		return false;
	}

	private boolean soyFamiliarDe(Persona persona) 
	{		
		return this.existePersonaEn( persona.getFamilia().getFamiliares() );		
	}

	private static void emparejar(Persona persona, Persona pareja) throws ParejaInvalidaException
	{
		if( pareja == null || persona == null )
			throw new ParejaInvalidaException();
		
		if( !persona.validarPareja( pareja ) )
			throw new ParejaInvalidaException();
		
		//Quito la pareja anterior de mi
		if( persona.getPareja() != null )
			persona.getPareja().pareja = null;
		
		//Quito la pareja anterior de mi pareja
		if( pareja.getPareja() != null )
			pareja.getPareja().pareja = null;
		
		//Me uno a mi pareja
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
	
	public Persona[] getCuniados()
	{
		return agregarHermanos( this.getPareja() );
	}
	
	
	public Persona[] getCuniadas()
	{
		return agregarHermanas( this.getPareja() );
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	private Persona[] agregarHermanas(Persona persona )
	{
		return agregarHermanas( new Persona[] { persona } );
	}
		
	private Persona[] agregarHermanas(Persona[] parientes )
	{
		return new PersonaAgregarHermanas().agregarParientes( parientes, new Persona[0]);
	}
	
	private Persona[] agregarHermanos(Persona persona )
	{
		return agregarHermanos( new Persona[] { persona } );
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
