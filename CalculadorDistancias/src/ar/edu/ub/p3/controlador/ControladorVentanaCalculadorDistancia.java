package ar.edu.ub.p3.controlador;

import ar.edu.ub.p3.modelo.Punto;

public class ControladorVentanaCalculadorDistancia {

	public double calcularDistancia(String x1, String y1, String x2, String y2) {
		return new Punto( x1, y1 ).calcularDistancia( new Punto(x2, y2) );
	}

}
