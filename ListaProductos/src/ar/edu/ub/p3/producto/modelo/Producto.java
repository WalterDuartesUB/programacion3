package ar.edu.ub.p3.producto.modelo;

import ar.edu.ub.p3.producto.excepcion.PorcentajeInvalidoException;

public class Producto {

	private String pid;
	private String descripcion; 
	private double valor; 
	private int cantidad;
	
	public Producto(String pid, String descripcion, double valor, int cantidad) {
		this.setPid(pid);
		this.setDescripcion(descripcion);
		this.setValor(valor);
		this.setCantidad(cantidad);		
	}

	public String getPid() {
		return pid;
	}

	private void setPid(String pid) {
		this.pid = pid;
	}

	public String getDescripcion() {
		return descripcion;
	}

	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getValor() {
		return valor;
	}

	private void setValor(double valor) {
		this.valor = valor;
	}

	public int getCantidad() {
		return cantidad;
	}

	private void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return String.format("%s - %s - %.2f - %d",	this.getPid(), this.getDescripcion(), this.getValor(), this.getCantidad() );
	}

	public void incrementarValor(double porcentaje) throws PorcentajeInvalidoException{

		if( !this.validarPorcentajeIncremento( porcentaje ) )
			throw new PorcentajeInvalidoException();
		
		this.setValor( Math.round( 100.0 * this.getValor() * ( porcentaje / 100 + 1 ) ) / 100.0 );
	}

	private boolean validarPorcentajeIncremento(double porcentaje) {
		return porcentaje >= 0;
	}
	
}
