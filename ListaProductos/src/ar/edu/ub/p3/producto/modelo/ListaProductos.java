package ar.edu.ub.p3.producto.modelo;

import java.util.LinkedList;
import java.util.List;

import ar.edu.ub.p3.producto.excepcion.PosicionInvalidaException;

public class ListaProductos {
	
	private List<Producto> lista = null;
	
	public ListaProductos(){
		this.setLista( new LinkedList<Producto>() );	
	}

	public void add(Producto producto) {
		this.getLista().add( producto );		
	}

	public Integer obtenerProducto(String pid) {
		Integer cantidadProducto = 0;
		
		for( Producto producto : this.getLista() )
			if( producto.getPid().compareTo( pid ) == 0 )
				return producto.getCantidad();
		
		return cantidadProducto;
	}

	public ListaProductos obtenerProductosConStockInferiorOIgualA(int cantidad) {
		
		ListaProductos productosInferiores = new ListaProductos();
		
		for( Producto producto : this.getLista() )
			if( producto.getCantidad() <= cantidad )
				productosInferiores.add(producto);
				
		return productosInferiores;
	}

	public ListaProductos obtenerProductosConStockSuperiorOIgualA(int cantidad) {
		ListaProductos productosSuperiores = new ListaProductos();
		
		for( Producto producto : this.getLista() )
			if( producto.getCantidad() >= cantidad )
				productosSuperiores.add(producto);
				
		return productosSuperiores;
	}

	@Override
	public String toString() {
		
		String listaStr = "";
		
		for( Producto producto : this.getLista() )		
			listaStr += producto + ", ";
		
		return listaStr;
	}

	public Producto at(int posicion) throws PosicionInvalidaException{
		
		if( !this.validarPosicion( posicion ) )
			throw new PosicionInvalidaException();
		
		return this.getLista().get( posicion - 1);
	}

	private boolean validarPosicion(int posicion) {
		return posicion >= 1 && posicion <= this.getLista().size();
	}

	private List<Producto> getLista() {
		return lista;
	}

	private void setLista(List<Producto> lista) {
		this.lista = lista;
	}	
}
