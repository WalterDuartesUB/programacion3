package ar.edu.ub.p3.modelo;

import ar.edu.ub.p3.util.Cola;

public class ColaSupermercado{
	
	private Cola cola;	
	
	public ColaSupermercado(  )
	{		
		this.setCola( new Cola() );
	}
	
	/**
	 * Obtiene una cola de rapida con los clientes con menos de 5 productos
	 * Modifica la cola actual quitando esos clientes
	 * 
	 * @return una ColaSupermercado con los clientes que cumplan el criterio minimo
	 */
		
	public ColaSupermercado obtenerColaRapida()
	{
		ColaSupermercado colaRapida = new ColaSupermercado( );
		ColaSupermercado colaAux = new ColaSupermercado( );
		
		//Quito los clientes que cumplen el criterio
		while( !this.getCola().isVacia() )
		{
			ClienteSupermercado cliente = this.ver();
			
			if( cliente.getCantidadProductos() >= 5 )
				colaAux.poner( cliente );
			else
				colaRapida.poner( cliente );
			
			this.getCola().sacar();
		}
		
		//Restauro la cola original con los clientes que no lo cumplen
		while( !colaAux.isVacia() )
		{
			this.poner( colaAux.ver() );
			colaAux.sacar();
		}
		
		return colaRapida;
	}

	private boolean isVacia() {
		return this.getCola().isVacia();
	}

	private void sacar() {
		this.getCola().sacar();		
	}

	private ClienteSupermercado ver() {
		return  (ClienteSupermercado) this.getCola().ver();
	}

	public void poner(ClienteSupermercado cliente) {
		this.getCola().poner( cliente );
	}

	private Cola getCola() {
		return cola;
	}

	private void setCola(Cola cola) {
		this.cola = cola;
	}
	
	@Override
	public String toString() {
		return this.getCola().toString();
	}
}
