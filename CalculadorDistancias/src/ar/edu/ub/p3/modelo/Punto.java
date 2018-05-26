package ar.edu.ub.p3.modelo;

public class Punto {
	private double x;
	private double y;
	public Punto(double x, double y) {	
		this.setX(x);
		this.setY(y);
	}
	public Punto(String x, String y) {
		this( Double.valueOf(x), Double.valueOf(y));
	}
	
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}

	public double calcularDistancia( Punto otroPunto ) {
		return Math.hypot( otroPunto.getX() - getX(), otroPunto.getY() - getY() );		
	}
}
