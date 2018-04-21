package ar.edu.ub.p3.util;

import ar.edu.ub.p3.util.exception.ColaVaciaException;

public class Cola {
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	class Nodo{
		private Nodo   siguiente;
		private Object dato;
		
		public Nodo( Object dato )
		{
			this(dato, null);
		}
		
		public Nodo(Object dato, Nodo siguiente) {
			setSiguiente(siguiente);
			setDato(dato);
		}
		public Nodo getSiguiente() {
			return siguiente;
		}
		private void setSiguiente(Nodo siguiente) {
			this.siguiente = siguiente;
		}
		public Object getDato() {
			return dato;
		}
		private void setDato(Object dato) {
			this.dato = dato;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	private Nodo entrada;
	private Nodo salida;

	///////////////////////////////////////////////////////////////////////////
	//
	
	public Cola() {
		setEntrada( null );
		setSalida( null );
	}

	private Nodo getEntrada() {
		return entrada;
	}

	private void setEntrada(Nodo entrada) {
		this.entrada = entrada;
	}

	private Nodo getSalida() {
		return salida;
	}

	private void setSalida(Nodo salida) {
		this.salida = salida;
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	public void poner( Object dato ) {
		
		Nodo nodo = new Nodo( dato );
		
		if( isVacia() ) {
			this.setSalida(nodo);			
		}
		else
			this.getEntrada().setSiguiente( nodo );
		
		this.setEntrada(nodo);
		
	}

	public Object ver() throws ColaVaciaException {
		
		if( this.isVacia() )
			throw new ColaVaciaException();
		
		return this.getSalida().getDato();
	}
	
	public void sacar() throws ColaVaciaException {
		
		if( this.isVacia() )
			throw new ColaVaciaException();
		
		Nodo nodo = this.getSalida();
		
		this.setSalida( nodo.getSiguiente() );
		
		if( this.getSalida() == null )
			this.setEntrada(null);
		
		nodo.setSiguiente( null );
	}	
	
	public boolean isVacia() {
		return this.getEntrada() == null;
	}
	
	@Override
	public String toString() {
		
		String colaStr = "";
		Cola   colaAux = new Cola();
		
		while( !this.isVacia() )
		{
			Object dato = this.ver();
			
			colaStr += dato.toString() + ", ";			
			colaAux.poner( dato );
			this.sacar();			
		}
		
		while( !colaAux.isVacia() )
		{
			this.poner( colaAux.ver() );
			colaAux.sacar();
		}
		
		return colaStr;
	}
	

}