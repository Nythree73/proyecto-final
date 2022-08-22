package pipf;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class log {
	
	public static boolean userCheck(String user, String pass) {
		Conexion conexion = new Conexion();
		
		Connection con = null;
		Statement stm = null;
		ResultSet res = null;
		final String uname = "swkdcbwec";
		final String upass = "123";
		final boolean result = (Boolean) null;
		
		
		try {
			con = conexion.conectar();
			stm = con.createStatement();
			res = stm.executeQuery(
						"SELECT EXISTS("
						+ "SELECT true "
						+ "FROM dbpipf.usuarios "
						+ "WHERE Usuario = '"+uname+"' "
						+ "AND clave = '"+upass+"'"
						+ ")"
					);
			//System.out.println(res.getBoolean(1));
			while(res.next()) {
				System.out.println(res.getBoolean(1));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	private JFrame ventana1;
	private JFrame ventana2;
	private JTextField usuariotf;
	private JPasswordField contraseñapf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					log window = new log();
					window.ventana1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public log() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ventana1 = new JFrame();
		ventana1.setBounds(100, 100, 660, 450);
		ventana1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana1.getContentPane().setLayout(null);
				
		JLabel titulo = new JLabel("LOGIN");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titulo.setBounds(287, 10, 86, 42);
		ventana1.getContentPane().add(titulo);
		
		JLabel usuario = new JLabel("Usuario");
		usuario.setBounds(122, 77, 46, 14);
		ventana1.getContentPane().add(usuario);
		
		JLabel contraseña = new JLabel("Contrase\u00F1a");
		contraseña.setBounds(122, 120, 76, 14);
		ventana1.getContentPane().add(contraseña);
		
		usuariotf = new JTextField();
		usuariotf.setBounds(208, 74, 234, 20);
		ventana1.getContentPane().add(usuariotf);
		usuariotf.setColumns(10);
		
		JButton botonentrar = new JButton("Entrar");
		botonentrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				JLabel avisologin = new JLabel("Contraseña incorrecta o usuario inexistente.");
				avisologin.setForeground(new Color(255, 0, 0));
				avisologin.setBounds(120, 216, 234, 13);
				avisologin.setVisible(false);
				ventana1.getContentPane().add(avisologin);
				
				avisologin.setVisible(true);
				
			}
			
			
			
		});
		botonentrar.setBounds(208, 165, 111, 23);
		botonentrar.setFocusable(false);
		ventana1.getContentPane().add(botonentrar);
		
		
		JButton botonregistrarse = new JButton("Registrarse");
		botonregistrarse.setFocusable(false);
		botonregistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana1.setVisible(false);
				ventana2.setVisible(true);
			}
		});
		
		botonregistrarse.setBounds(331, 165, 111, 23);
		ventana1.getContentPane().add(botonregistrarse);
		
		contraseñapf = new JPasswordField();
		contraseñapf.setBounds(208, 117, 234, 20);
		ventana1.getContentPane().add(contraseñapf);
		
		JLabel avisologin = new JLabel("Contraseña incorrecta o usuario inexistente.");
		avisologin.setForeground(new Color(255, 0, 0));
		avisologin.setBounds(208, 215, 234, 13);
		avisologin.setVisible(false);
		ventana1.getContentPane().add(avisologin);
		
		ventana2 = new JFrame();
		ventana2.setBounds(100, 100, 660, 450);
		ventana2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana2.getContentPane().setLayout(null);
		ventana2.setVisible(false);
		
		JLabel titulo2 = new JLabel("REGISTER");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titulo.setBounds(287, 10, 86, 42);
		ventana1.getContentPane().add(titulo2);
	}
}