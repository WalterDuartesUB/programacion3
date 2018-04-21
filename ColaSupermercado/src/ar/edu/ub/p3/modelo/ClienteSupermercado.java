package ar.edu.ub.p3.modelo;

public class ClienteSupermercado {
	private Integer ubicacion;
	private Integer cantidadProductos;
	
	public ClienteSupermercado(Integer ubicacion, Integer cantidadProductos) {
		this.setUbicacion(ubicacion);
		this.setCantidadProductos(cantidadProductos);
	}
	
	public Integer getUbicacion() {
		return ubicacion;
	}
	private void setUbicacion(Integer ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Integer getCantidadProductos() {
		return cantidadProductos;
	}
	private void setCantidadProductos(Integer cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}

	
	@Override
	public String toString() {
		return this.getUbicacion() + " " + this.getCantidadProductos();
	}
}
