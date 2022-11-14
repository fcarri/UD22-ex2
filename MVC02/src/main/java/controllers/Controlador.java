package controllers;

import javax.swing.*;

import models.Modelo;
import views.Vista;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;  


public class Controlador  extends JFrame implements ActionListener{
	
	public Connection conn = null;
	
	private Modelo modelo;
	private Vista vista;	
	private int id;
	private String nombre;	
	private String apellidos;
	private String direccion;
	private int id_v;
	private String titulo;
	private String director;
	private int DNI;
	private Date fecha;
	
	private String db = "Clientes";
	private String table = "cliente";
	private String table2 = "videos";
	
	public Controlador(Modelo modelo, Vista vista) {
	
		this.modelo = modelo;
		this.vista = vista;
		this.vista.boton1.addActionListener(this);
		this.vista.boton2.addActionListener(this);
		this.id = 1;
		this.id_v = 1;
	}
	
	public void iniciarVista() {
		
		vista.setTitle("BD");
		vista.pack();
		vista.setBounds(400,200,450,750);
		vista.setDefaultCloseOperation(EXIT_ON_CLOSE);
		vista.setVisible(true);
		
	}
	
	public void connexio() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://192.168.1.77:"
					+ "3306?useTimezone=true&serverTimezoneUTC","remote","<FCSboot1972>");			
			System.out.print("Se ha conectado con la base de datos\n");   
			
		}catch(SQLException|ClassNotFoundException ex) {
			System.out.print("No se ha podido conectar con la base de datos\n");
			System.out.print(ex);
						
		}	 
		
		createBD(db, conn);
		createTable(db, table);
		createTable2(db, table2);
		
	}
			
	public void actionPerformed(ActionEvent evento) {
		
		
		if(evento.getSource() == vista.boton1) {	
			
			nombre = vista.fNombre.getText();
			modelo.setNombre(nombre);
			apellidos = vista.fApellidos.getText();
			modelo.setApellidos(apellidos);
			direccion = vista.fDireccion.getText();
			modelo.setDireccion(direccion);
		
			try {
				DNI = Integer.parseInt(vista.fDNI.getText());
				modelo.setDNI(DNI);
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null,"Introducir sólo números");
				System.exit(0);
			}		
			
			try {
				fecha = Date.valueOf(vista.fFecha.getText());
				modelo.setFecha(fecha);
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null,"Introducir fecha en formato indicado");
				System.exit(0);				
			}	
			
			titulo = vista.fTitulo.getText();
			modelo.setTitulo(titulo);
			director = vista.fDirector.getText();
			modelo.setDirector(director);		
			modelo.setId(id);
			modelo.setId_v(id_v);
			
			System.out.println(""+modelo.getId());
			System.out.println(""+modelo.getId_v());
			
			insertData(db, table, id, nombre, apellidos, direccion,
				DNI, fecha);
			
			insertData2(db, table2, id_v, titulo, director, id);
			
			id++;
			id_v++;
			
			System.out.println(""+modelo.getId());
			System.out.println(""+modelo.getId_v());
			System.out.println(""+modelo.getNombre());
			System.out.println(""+modelo.getApellidos());
			System.out.println(""+modelo.getDireccion());
			System.out.println(""+modelo.getDNI());
			System.out.println(""+modelo.getFecha());	
			System.out.println(""+modelo.getTitulo());
			System.out.println(""+modelo.getDirector());
				
		
		}
		
		if(evento.getSource() == vista.boton2) {	
			
			System.out.println("Finalitzar");
			System.exit(0);
		}
		
	}
	
	public void closeConnection(Connection conn) {				
		
		try {
			conn.close();
			System.out.println("Conexion cerrada");
			
		}catch(SQLException ex){
			
			System.out.println("No se ha podido cerrar la conexion");
		}	
		
	}
	
	public void createBD(String db, Connection conn) {
		
		try {
			
			String Query0 = "DROP DATABASE IF EXISTS "+db+";";
			Statement st0=conn.createStatement();
			st0.executeUpdate(Query0);
			
			String Query = "CREATE DATABASE "+db;
			Statement st = conn.createStatement();
			st.executeUpdate(Query);
			System.out.println("Base de datos creada.");
			
			
		}catch(SQLException ex) {
			Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE,null,ex);
			System.out.println("No se ha podido crear la base de datos.");
			closeConnection(conn);
		}
		
	}
	
	public void createTable(String db,String name) {
		try {
			
			
			String Querydb = "USE "+db+";";
			Statement stdb = conn.createStatement();
			stdb.executeUpdate(Querydb);
			
			String Query = "CREATE TABLE "+name+" (id int(11) not null auto_increment, "
					+ "nombre varchar(250) default null, "
					+ "apellidos varchar(250) default null,"
					+ "direccion varchar(250) default null, "
					+ "DNI int default null, "
					+ "fecha DATE default null,"
					+ "primary key(id))";
			Statement st=conn.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada con exito");						
			
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
			System.out.println("Error creando tabla");
			closeConnection(conn);
			
		}
	}
	
	public void createTable2(String db,String name) {
		try {
			
			
			String Querydb = "USE "+db+";";
			Statement stdb = conn.createStatement();
			stdb.executeUpdate(Querydb);
			
			String Query = "CREATE TABLE "+name+" (id_v int(11) not null auto_increment, "
					+ "titulo varchar(255) default null, "					
					+ "director varchar(250) default null, "
					+ "cli_id int(11) default null, "					
					+ "primary key(id_v),"
					+ "constraint videos_fk foreign key (cli_id) references cliente (id)"
					+ ")";
			Statement st=conn.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada con exito");
			
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
			System.out.println("Error creando tabla");
			closeConnection(conn);
			
		}
	}
	
	public void insertData (String db, String table, int id, String nombre,
			String apellidos, String direccion, int DNI, Date fecha) {
		
		try {
			String Querydb = "USE "+db+";";
			Statement stdb=conn.createStatement();
			stdb.executeUpdate(Querydb);
			
			String Query = "INSERT INTO "+table+ 
					" values "+"("+
					modelo.getId()+","+"'"+
					modelo.getNombre()+"'"+","+"'"+
					modelo.getApellidos()+"'"+","+"'"+
					modelo.getDireccion()+"'"+","+
					modelo.getDNI()+","+" date "+"'"+
					modelo.getFecha()+"'"+")";
													
			Statement st=conn.createStatement();
			st.executeUpdate(Query);
			System.out.println("Datos insertados con exito");
			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			closeConnection(conn);
		}
	}
	
	public void insertData2 (String db, String table2, int id_v, String titulo,
			String director, int id) {
		
		try {
			String Querydb = "USE "+db+";";
			Statement stdb=conn.createStatement();
			stdb.executeUpdate(Querydb);
			
			String Query = "INSERT INTO "+table2+ 
					" values "+"("+
					modelo.getId_v()+","+"'"+
					modelo.getTitulo()+"'"+","+"'"+	
					modelo.getDirector()+"'"+","+						
					modelo.getId()+")";
													
			Statement st=conn.createStatement();
			st.executeUpdate(Query);
			System.out.println("Datos insertados con exito");
			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			closeConnection(conn);
		}
	}
	
	
	

}
