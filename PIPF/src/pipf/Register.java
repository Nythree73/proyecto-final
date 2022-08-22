package pipf;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("unused")
public class Register {
	private static DefaultTableModel model = new DefaultTableModel();
	
	public static boolean registrarCliente(String nombre, String apellido, String telefono, String email, String usuario) {
		Conexion conexion = new Conexion();
		Connection con = null;
		PreparedStatement stm = null;
		String query = "insert into users ( nick, pass, fname, lname ) values ( ?, ?, ?, ? )";
		
		try {
			con = conexion.conectar();
			stm = con.prepareStatement(query);
			stm.setString(1, nombre);
			stm.setString(2, apellido);
			stm.setString(3, telefono);
			stm.setString(4, email);
			stm.setString(5, usuario);
			stm.execute();
			con.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
		
	public static boolean registrarUsuario(String nick, String pass, String fname, String lname) {
		Conexion conexion = new Conexion();
		Connection con = null;
		PreparedStatement stm = null;
		String query = "insert into users ( nick, pass, fname, lname ) values ( ?, ?, ?, ? )";
		
		try {
			con = conexion.conectar();
			stm = con.prepareStatement(query);
			stm.setString(1, nick);
			stm.setString(2, pass);
			stm.setString(3, fname);
			stm.setString(4, lname);
			stm.execute();
			System.out.println(stm);
			//System.out.println(res.getBoolean(1));
			//while(res.next()) {
			//	System.out.println(res.getBoolean(1));
				
			//}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	public static boolean loguearUsuario(String nick, String pass) {
		Conexion conexion = new Conexion();
		System.out.println(nick + " " + pass);
		Connection con = null;
		Statement stm = null;
		ResultSet res = null;
		Boolean result = null;
		
		try {
			con = conexion.conectar();
			stm = con.createStatement();
			res = stm.executeQuery(
						"SELECT EXISTS("
						+ "SELECT true "
						+ "FROM users "
						+ "WHERE nick = '"+nick+"' "
						+ "AND pass = '"+pass+"'"
						+ ")"
					);
			
			while (res.next()) {
				result = res.getBoolean(1);
			}
			con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static void getUsuarios() {
		Conexion conexion = new Conexion();
		Connection con = null;
		Statement stm = null;
		ResultSet res = null;
		Boolean result = null;
		Array usuarios = null;
		
		
		try {
			con = conexion.conectar();
			stm = con.createStatement();
			res = stm.executeQuery("select * from clients");
			
			while (res.next()) {
				Object[] fila = new Object[6];
				fila[0] = res.getInt(1);
				fila[1] = res.getString(2);
				fila[2] = res.getString(3);
				fila[3] = res.getString(4);
				fila[4] = res.getString(5);
				fila[5] = res.getString(6);
				model.addRow(fila);
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private JFrame frame;
	private JTextField tfusuariologin;
	private JPasswordField tfcontrasenaregistrar;
	private JTextField tfnombreregistrar;
	private JTextField tfapellidosregistrar;
	private JTextField tfusuarioregistrar;
	private JPasswordField pfcontrasenalogin;
	private JTable ClientsTable;
	private JTable usuariosTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		
		frame.setBounds(100, 100, 710, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		final JPanel register = new JPanel();
		register.setVisible(false);
		final JPanel login = new JPanel();
		JLabel titulologin = new JLabel("INICIAR SESION");
		JLabel usuariologin = new JLabel("Usuario");
		final JLabel contraseñalogin = new JLabel("Contraseña");
		final JPanel seleccion = new JPanel();
		final JPanel productos = new JPanel();
		final JLabel avisologin = new JLabel("Contraseña incorrecta o usuario inexistente.");
		tfusuariologin = new JTextField();
		pfcontrasenalogin = new JPasswordField();
		final JButton entrarlogin = new JButton("Entrar");
		
		
		entrarlogin.setFocusable(false);
		entrarlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean loginResult = loguearUsuario(
													tfusuariologin.getText(),
													String.valueOf(pfcontrasenalogin.getPassword()
												)
										);
				if (loginResult) {
					login.setVisible(false);
					register.setVisible(false);
					seleccion.setVisible(true);
				} else {
					avisologin.setVisible(true);
				}
				
				
			}
		});
		final JPanel usuarios = new JPanel();
		
		
		usuarios.setVisible(false);
		usuarios.setBounds(0, 0, 696, 438);
		frame.getContentPane().add(usuarios);
		usuarios.setLayout(null);
		
		JButton atrasusuarios = new JButton("Atras");
		atrasusuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuarios.setVisible(false);
				seleccion.setVisible(true);
			}
		});
		
		atrasusuarios.setBounds(601, 10, 85, 21);
		usuarios.add(atrasusuarios);
		
		JButton nuevousuario = new JButton("Nuevo");
		nuevousuario.setBounds(50, 360, 110, 45);
		usuarios.add(nuevousuario);
		
		JButton actualizarusuario = new JButton("Actualizar");
		actualizarusuario.setBounds(200, 360, 110, 45);
		usuarios.add(actualizarusuario);
		
		JButton eliminarusuario = new JButton("Eliminar");
		eliminarusuario.setBounds(350, 360, 110, 45);
		usuarios.add(eliminarusuario);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 586, 343);
		usuarios.add(scrollPane);
		
		usuariosTable = new JTable();
		usuariosTable.setModel(model); 
		model.addColumn("Id");
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("Telefono");
		model.addColumn("Correo");
		model.addColumn("Usuario");
		getUsuarios();
		scrollPane.setViewportView(usuariosTable);
		
		
		JButton registrarlogin = new JButton("Registrarse");
		registrarlogin.setFocusable(false);
		login.setBounds(0, 0, 696, 438);
		frame.getContentPane().add(login);
		login.setLayout(null);
		
		
		titulologin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titulologin.setBounds(270, 10, 225, 45);
		login.add(titulologin);
		
		
		usuariologin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usuariologin.setBounds(115, 100, 120, 40);
		login.add(usuariologin);
		
		
		contraseñalogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contraseñalogin.setBounds(115, 200, 120, 40);
		login.add(contraseñalogin);
		
		
		avisologin.setVisible(false);
		avisologin.setForeground(new Color(255, 0, 0));
		avisologin.setBounds(242, 380, 300, 13);
		login.add(avisologin);
		
		
		tfusuariologin.setBounds(270, 110, 285, 20);
		login.add(tfusuariologin);
		tfusuariologin.setColumns(10);
		

		pfcontrasenalogin.setBounds(270, 210, 285, 20);
		login.add(pfcontrasenalogin);
		
		
		entrarlogin.setRequestFocusEnabled(false);
		entrarlogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		entrarlogin.setBounds(115, 300, 120, 40);
		login.add(entrarlogin);
		
		
		registrarlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.setVisible(false);
				register.setVisible(true);
			}
		});
		
		registrarlogin.setRequestFocusEnabled(false);
		registrarlogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		registrarlogin.setBounds(435, 300, 120, 40);
		login.add(registrarlogin);
		JLabel tituloregistrar = new JLabel("REGISTRARSE");
		JLabel nombreregistrar = new JLabel("Nombre");
		JLabel apellidoregistrar = new JLabel("Apellidos");
		JLabel usuarioregistrar = new JLabel("Usuario");
		JLabel contraseñaregistrar = new JLabel("Contraseña");
		
		final JLabel aviso = new JLabel("Nombre de usuario ocupado");
		tfcontrasenaregistrar = new JPasswordField();
		tfnombreregistrar = new JTextField();
		tfapellidosregistrar = new JTextField();
		tfusuarioregistrar = new JTextField();
		
		
		register.setBounds(0, 0, 696, 438);
		frame.getContentPane().add(register);
		register.setLayout(null);
		
		
		tituloregistrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tituloregistrar.setBounds(270, 10, 225, 45);
		register.add(tituloregistrar);
		
		
		nombreregistrar.setBounds(185, 80, 80, 20);
		register.add(nombreregistrar);
		
		
		apellidoregistrar.setBounds(185, 130, 80, 20);
		register.add(apellidoregistrar);
		
		
		usuarioregistrar.setBounds(185, 180, 80, 20);
		register.add(usuarioregistrar);
		
		
		contraseñaregistrar.setBounds(185, 230, 80, 20);
		register.add(contraseñaregistrar);
		
		
		aviso.setVisible(false);
		aviso.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
			}
		});
		aviso.setForeground(new Color(255, 0, 0));
		aviso.setBounds(185, 354, 168, 13);
		register.add(aviso);
		
		
		tfcontrasenaregistrar.setBounds(299, 230, 180, 20);
		register.add(tfcontrasenaregistrar);
		
		tfnombreregistrar.setBounds(299, 80, 180, 20);
		register.add(tfnombreregistrar);
		tfnombreregistrar.setColumns(10);
		
		
		tfapellidosregistrar.setBounds(299, 130, 180, 20);
		register.add(tfapellidosregistrar);
		tfapellidosregistrar.setColumns(10);
		
		
		tfusuarioregistrar.setBounds(299, 180, 180, 20);
		register.add(tfusuarioregistrar);
		tfusuarioregistrar.setColumns(10);
		
		JButton registrarseregistrarse = new JButton("Registrarse");
		registrarseregistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean res = registrarUsuario(
							tfusuarioregistrar.getText(),
							String.valueOf(tfcontrasenaregistrar.getPassword()),
							tfnombreregistrar.getText(),
							tfapellidosregistrar.getText()
						);
				if (res == false) {
					aviso.setVisible(true);
				} else {
					tfusuariologin.setText(tfusuarioregistrar.getText());
					pfcontrasenalogin.setText(String.valueOf(tfcontrasenaregistrar.getPassword()));
					entrarlogin.doClick();
				}
			}
		});
		registrarseregistrarse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		registrarseregistrarse.setBounds(180, 300, 120, 25);
		register.add(registrarseregistrarse);
		
		JButton iniciarsesionregistrarse = new JButton("Iniciar sesion");
		iniciarsesionregistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register.setVisible(false);
				login.setVisible(true);
				
			}
		});
		iniciarsesionregistrarse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		iniciarsesionregistrarse.setBounds(332, 300, 144, 25);
		register.add(iniciarsesionregistrarse);		
		
		seleccion.setVisible(false);
		seleccion.setBounds(0, 0, 696, 438);
		frame.getContentPane().add(seleccion);
		seleccion.setLayout(null);
		
		final JButton usuariosseleccion = new JButton("Usuarios");
		usuariosseleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				seleccion.setVisible(false);
				usuarios.setVisible(true);
			}
		});
		usuariosseleccion.setFocusable(false);
		usuariosseleccion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usuariosseleccion.setBounds(106, 109, 210, 80);
		seleccion.add(usuariosseleccion);
		
		final JButton productosseleccion = new JButton("Productos");
		productosseleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				seleccion.setVisible(false);
				productos.setVisible(true);
			}
		});
		productosseleccion.setFocusable(false);
		productosseleccion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		productosseleccion.setBounds(377, 109, 210, 80);
		seleccion.add(productosseleccion);
		
		JButton cerrarsesionseleccion = new JButton("Cerrar Sesion");
		cerrarsesionseleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avisologin.setVisible(false);
				seleccion.setVisible(false);
				login.setVisible(true);
			}
		});
		cerrarsesionseleccion.setFocusable(false);
		cerrarsesionseleccion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cerrarsesionseleccion.setBounds(106, 246, 480, 50);
		seleccion.add(cerrarsesionseleccion);
		
		productos.setVisible(false);
		productos.setBounds(0, 0, 696, 438);
		frame.getContentPane().add(productos);
		productos.setLayout(null);
		
		JButton atrasproductos = new JButton("Atras");
		atrasproductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productos.setVisible(false);
				seleccion.setVisible(true);
			}
		});
		atrasproductos.setBounds(601, 10, 85, 21);
		productos.add(atrasproductos);
		
		JButton nuevoproducto = new JButton("Nuevo");
		nuevoproducto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nuevoproducto.setBounds(50, 360, 110, 45);
		productos.add(nuevoproducto);
		
		JButton actualizarproducto = new JButton("Actualizar");
		actualizarproducto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		actualizarproducto.setBounds(200, 360, 110, 45);
		productos.add(actualizarproducto);
		
		JButton eliminarproducto = new JButton("Eliminar");
		eliminarproducto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		eliminarproducto.setBounds(350, 360, 110, 45);
		productos.add(eliminarproducto);
	}
}
