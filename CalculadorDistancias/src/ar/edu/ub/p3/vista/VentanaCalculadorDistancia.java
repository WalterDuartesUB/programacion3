package ar.edu.ub.p3.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ar.edu.ub.p3.controlador.ControladorVentanaCalculadorDistancia;

public class VentanaCalculadorDistancia extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7084045600023774055L;
	private ControladorVentanaCalculadorDistancia controladorVentanaCalculadorDistancia;
	
	private JTextField txtX1;
	private JTextField txtY1;
	private JTextField txtX2;
	private JTextField txtY2;
	
	public VentanaCalculadorDistancia(ControladorVentanaCalculadorDistancia controladorVentanaCalculadorDistancia) {
		
		this.setControladorVentanaCalculadorDistancia(controladorVentanaCalculadorDistancia);
		
		/////////////////////////////////////////////////////////////////////		
		//Asigno las propiedades a la ventana
		
		this.inicializarVentana();
		
		/////////////////////////////////////////////////////////////////////		
		//Creo el contenido de la ventana
		
		this.crearControles();
		
		/////////////////////////////////////////////////////////////////////
		//Muestro la ventana
		
		this.setVisible( true );
		
	}
	private void crearControles() {
		this.setLayout( new BorderLayout() );

		//Creo un panel para contener los dos punto
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout( new GridLayout(2, 1));
		this.add( panelCentral, BorderLayout.CENTER );
		
		/////////////////////////////////////////////////////////////////////
		// Creo el panel para el punto 1
		
		this.crearPanelPunto1(panelCentral);
		
		///////////////////////////////////////////////////////////////////////
		// Creo el panel para el punto 2
		this.crearPanelPunto2(panelCentral);
		
		//Creo el panel con el boton
		this.crearPanelBoton();
	}
	private void crearPanelPunto2(JPanel panelCentral) {
		JPanel panelPunto = new JPanel();
		panelPunto.setLayout( new FlowLayout() );		
		panelPunto.add( new JLabel("Punto2 X:"));		
		this.setTxtX2( crearTextFieldNumerico() );
		panelPunto.add( this.getTxtX2() );
		
		panelPunto.add( new JLabel("Y:"));
		this.setTxtY2( crearTextFieldNumerico() );
		panelPunto.add( this.getTxtY2() );
		
		panelCentral.add(panelPunto);
	}
	private void crearPanelPunto1(JPanel panelCentral) {
		JPanel panelPunto = new JPanel();
		panelPunto.setLayout( new FlowLayout() );		
		panelPunto.add( new JLabel("Punto1 X:"));		
		this.setTxtX1( crearTextFieldNumerico() );
		panelPunto.add( this.getTxtX1() );
		
		panelPunto.add( new JLabel("Y:"));
		this.setTxtY1( crearTextFieldNumerico() );
		panelPunto.add( this.getTxtY1() );
		
		panelCentral.add(panelPunto);
	}
	private JTextField crearTextFieldNumerico() {
		return new JTextField(5);
	}
	private void crearPanelBoton() {
		JButton btnCalcular = new JButton("Calcular Distancia");
		JPanel panelBoton = new JPanel();
		panelBoton.setLayout( new FlowLayout() );
		panelBoton.add(btnCalcular);
		btnCalcular.addActionListener( this::onClickBtnCalcularDistancia );
		
		this.add( panelBoton, BorderLayout.SOUTH);
	}
	private void inicializarVentana() {
		this.setSize(300, 150);
		this.setLocation(300, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Calculador de distancia entre dos puntos");
	}
	private ControladorVentanaCalculadorDistancia getControladorVentanaCalculadorDistancia() {
		return controladorVentanaCalculadorDistancia;
	}
	private void setControladorVentanaCalculadorDistancia(ControladorVentanaCalculadorDistancia controladorVentanaCalculadorDistancia) {
		this.controladorVentanaCalculadorDistancia = controladorVentanaCalculadorDistancia;
	}
	
	private void onClickBtnCalcularDistancia (ActionEvent ae) {
		String x1 = this.getTxtX1().getText();
		String y1 = this.getTxtY1().getText();
		String x2 = this.getTxtX2().getText();
		String y2 = this.getTxtY2().getText();
		
		double distancia = this.getControladorVentanaCalculadorDistancia().calcularDistancia(x1, y1, x2, y2 );
		
		JOptionPane.showMessageDialog(this, String.format("La distancia entre los dos puntos es %f", distancia));
	}
	private JTextField getTxtX1() {
		return txtX1;
	}
	private void setTxtX1(JTextField txtX1) {
		this.txtX1 = txtX1;
	}
	private JTextField getTxtY1() {
		return txtY1;
	}
	private void setTxtY1(JTextField txtY1) {
		this.txtY1 = txtY1;
	}
	private JTextField getTxtX2() {
		return txtX2;
	}
	private void setTxtX2(JTextField txtX2) {
		this.txtX2 = txtX2;
	}
	private JTextField getTxtY2() {
		return txtY2;
	}
	private void setTxtY2(JTextField txtY2) {
		this.txtY2 = txtY2;
	}
}
