package ar.edu.ub.programacion3.util;

import ar.edu.ub.programacion3.exception.DatoInvalidoException;
import ar.edu.ub.programacion3.exception.PilaVaciaException;

public class Pila {
	
	/////////////////////////////////////////////////////////////////////////
	// Clase privada de la pila que representa a un nodo
	
	class Nodo{
		
		private Nodo siguiente;
		private Object dato;
		
		public Nodo(Object dato) {
			this( dato, null );
		}
		
		public Nodo(Object dato, Nodo proximo) {
			this.setDato( dato );
			this.setSiguiente( proximo );
		}		

		public Nodo getSiguiente() {
			return this.siguiente;
		}

		public void setSiguiente(Nodo proximo) {
			this.siguiente = proximo;
		}

		public Object getDato() {
			return this.dato;
		}

		public void setDato(Object dato) {
			this.dato = dato;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	private Nodo tope;

	///////////////////////////////////////////////////////////////////////////
	// metodos publicos
	
	public Pila(){
		this.vaciar();
	}

	public boolean isVacia(){
		return this.getTope() == null;
	}
	
	public void poner( Object dato ) throws DatoInvalidoException{
		
		if( this.validarDato( dato ) )
			throw new DatoInvalidoException();
		
		this.setTope(  new Nodo( dato, this.getTope() ) );
	}
	
	public void sacar( ) throws PilaVaciaException{
		if( this.isVacia() )
			throw new PilaVaciaException();
		
		Nodo aux = this.getTope();
		
		this.setTope( aux.getSiguiente() );

		aux.setSiguiente( null );
	}	

	public Object ver( ) throws PilaVaciaException{
		
		if( this.isVacia() )
			throw new PilaVaciaException();
		
		return this.getTope().getDato();
	}		

	public void vaciar( ){
		while( !this.isVacia() )
			this.sacar();
	}
	
	@Override
	public String toString()
	{
		String pilaStr = "";
		Pila pilaAux = new Pila();
		
		while( !this.isVacia() )
		{
			Object dato = this.ver();					
			pilaStr += dato.toString();			
			pilaAux.poner( dato );
			this.sacar();			
		}
		
		while( !pilaAux.isVacia() )
		{			
			this.poner( pilaAux.ver() );
			pilaAux.sacar();
		}
			
		
		return pilaStr;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// metodos privados
	
	private boolean validarDato(Object dato) {
		return dato == null;
	}
	
	private Nodo getTope() {
		return tope;
	}


	private void setTope(Nodo tope) {
		this.tope = tope;
	}
}


