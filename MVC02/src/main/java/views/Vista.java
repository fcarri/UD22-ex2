package views;

import javax.swing.*;


public class Vista extends JFrame {
	
	private JPanel contentPane;	
	public JButton boton1, boton2;
	public JLabel etiqueta1, etiqueta2, jNombre, jApellidos, jDireccion, jDNI, jFecha, jTitulo, jDirector;
	public JTextField fNombre, fApellidos, fDireccion, fDNI, fFecha, fTitulo, fDirector;
	
	
	
	public Vista() {
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		etiqueta1 = new JLabel("DATOS CLIENTE");
		etiqueta1.setBounds(40, 20, 300, 20);
		contentPane.add(etiqueta1);
		
		jNombre = new JLabel("Nombre cliente: ");
		jNombre.setBounds(40, 60, 200, 25);
		contentPane.add(jNombre);
		
		jApellidos = new JLabel("Apellidos: ");
		jApellidos.setBounds(40, 120, 200, 25);
		contentPane.add(jApellidos);
		
		jDireccion = new JLabel("Dirección: ");
		jDireccion.setBounds(40, 180, 200, 25);
		contentPane.add(jDireccion);
		
		jDNI = new JLabel("DNI: ");
		jDNI.setBounds(40, 240, 200, 25);
		contentPane.add(jDNI);
		
		jFecha = new JLabel("Fecha (yyyy-mm-dd): ");
		jFecha.setBounds(40, 300, 200, 25);
		contentPane.add(jFecha);
		
		etiqueta2 = new JLabel("DATOS VIDEO");
		etiqueta2.setBounds(40, 380, 300, 20);
		contentPane.add(etiqueta2);
		
		jTitulo = new JLabel("Titulo película: ");
		jTitulo.setBounds(40, 420, 200, 25);
		contentPane.add(jTitulo);
		
		jDirector = new JLabel("Director película: ");
		jDirector.setBounds(40, 480, 200, 25);
		contentPane.add(jDirector);
						
		fNombre = new JTextField();
		fNombre.setBounds(40,80,200,25);
		contentPane.add(fNombre);	
						
		fApellidos = new JTextField();
		fApellidos.setBounds(40,140,350,25);
		contentPane.add(fApellidos);	
		
		fDireccion = new JTextField();
		fDireccion.setBounds(40,200,350,25);
		contentPane.add(fDireccion);

		fDNI = new JTextField();
		fDNI.setBounds(40,260,80,25);
		contentPane.add(fDNI);
		
		fFecha = new JTextField();
		fFecha.setBounds(40,320,80,25);
		contentPane.add(fFecha);
		
		fTitulo = new JTextField();
		fTitulo.setBounds(40,440,300,25);
		contentPane.add(fTitulo);
		
		fDirector = new JTextField();
		fDirector.setBounds(40,500,300,25);
		contentPane.add(fDirector);
		
		boton1 = new JButton("Introducir datos");
		boton1.setBounds(120,600,200,23);
		contentPane.add(boton1);	
		
		boton2 = new JButton("Finalizar");
		boton2.setBounds(120,650,200,23);
		contentPane.add(boton2);	
		  							
	}
		
	
}

