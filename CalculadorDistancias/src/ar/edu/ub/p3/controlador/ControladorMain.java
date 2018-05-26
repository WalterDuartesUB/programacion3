package ar.edu.ub.p3.controlador;

import ar.edu.ub.p3.vista.VentanaCalculadorDistancia;

public class ControladorMain {
	public static void main(String[] args) {
		new VentanaCalculadorDistancia( new ControladorVentanaCalculadorDistancia() );
	}
}
