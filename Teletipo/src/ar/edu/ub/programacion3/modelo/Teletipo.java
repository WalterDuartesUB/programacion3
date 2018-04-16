package ar.edu.ub.programacion3.modelo;

import ar.edu.ub.programacion3.util.Pila;

public class Teletipo
{

	private static final String CARACTER_FIN_MENSAJE_DEFAULT = "*";
	private static final String CARACTER_RESET_DEFAULT = "&";
	private static final String CARACTER_RETROCESO_DEFAULT = "/";
	
	private String caracterRetroceso;
	private String caracterReset;
	private String caracterFinMensaje;

	private String mensaje;
	
	/////////////////////////////////////////////////////////////////////
	//
	
	public Teletipo()
	{
		this( CARACTER_RETROCESO_DEFAULT, CARACTER_RESET_DEFAULT, CARACTER_FIN_MENSAJE_DEFAULT );
	}
	
	/////////////////////////////////////////////////////////////////////
	//
	
	public Teletipo(String caracterRetroceso, String caracterReset, String caracterFinMensaje)
	{
		this.setCaracterReset(caracterReset);
		this.setCaracterRetroceso(caracterRetroceso);
		this.setCaracterFinMensaje(caracterFinMensaje);
	}

	/////////////////////////////////////////////////////////////////////
	//
	
	public String interpretar(String mensajeAInterpretar)
	{
		
		Pila pila = new Pila();
		
		///////////////////////////////////////////////////////////////////////
		//Recorro el input y opero con la pila segun corresponda
		
		while( !mensajeAInterpretar.isEmpty() ) 
		{
			String caracter = mensajeAInterpretar.substring( 0, 1);
			
			if( this.esCaracterFinMensaje( caracter ) )
				break;
			
			this.interpretarCaracter( caracter, pila );
			
			//Borro el caracter que lei
			mensajeAInterpretar = mensajeAInterpretar.substring( 1 );
		}
		
		///////////////////////////////////////////////////////////////////////
		// Extraigo el mensaje de la pila
		
		this.setMensaje( getMensaje( pila ) );
		
		//
		
		return this.getMensaje();
	}

	///////////////////////////////////////////////////////////////////
	//
	
	private String getMensaje(Pila pila)
	{
		String msg = "";
		
		while( !pila.isVacia() )
		{			
			msg = pila.ver() + msg;
			pila.sacar();
		}		
		
		return msg;
	}

	private String getMensaje()
	{
		return this.mensaje;
	}

	/////////////////////////////////////////////////////////////////////
	//	
	
	private boolean esCaracterFinMensaje(String caracter)
	{
		return caracter.compareTo( this.getCaracterFinMensaje() ) == 0;  
	}

	/////////////////////////////////////////////////////////////////////
	//	
	
	private void interpretarCaracter(String caracter, Pila pila )
	{
		// Tengo que vaciar la pila?
		if( this.esCaracterReset(caracter)  )
			pila.vaciar();			
		
		//Tengo que quitar el ultimo caracter
		else if( this.esCaracterRetroceso(caracter)  )
		{
			if( !pila.isVacia() )
			
				pila.sacar();
		}
		else			
			pila.poner( caracter );		
	}

	/////////////////////////////////////////////////////////////////////
	//	
	
	private boolean esCaracterRetroceso(String caracter)
	{
		return caracter.compareTo( this.getCaracterRetroceso() ) == 0;
	}
	
	/////////////////////////////////////////////////////////////////////
	//
	
	private boolean esCaracterReset(String caracter)
	{
		return caracter.compareTo( this.getCaracterReset() ) == 0;
	}
	
	/////////////////////////////////////////////////////////////////////
	//
	
	public String getCaracterRetroceso()
	{
		return this.caracterRetroceso;
	}
	
	/////////////////////////////////////////////////////////////////////
	//
	
	public String getCaracterReset()
	{
		return this.caracterReset;
	}
	
	/////////////////////////////////////////////////////////////////////
	//
	
	public String getCaracterFinMensaje()
	{
		return this.caracterFinMensaje;
	}

	/////////////////////////////////////////////////////////////////////
	//
	
	private void setCaracterRetroceso(String caracterRetroceso)
	{
		this.caracterRetroceso = caracterRetroceso;
	}

	/////////////////////////////////////////////////////////////////////
	//	
	
	private void setCaracterReset(String caracterReset)
	{
		this.caracterReset = caracterReset;
	}

	/////////////////////////////////////////////////////////////////////
	//
	
	private void setCaracterFinMensaje(String caracterFinMensaje)
	{
		this.caracterFinMensaje = caracterFinMensaje;
	}

	/////////////////////////////////////////////////////////////////////
	//	

	private void setMensaje(String mensaje)
	{
		this.mensaje = mensaje;
	}

}
