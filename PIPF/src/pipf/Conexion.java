package pipf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";	
	private static final String url = "jdbc:mysql://localhost:3306/dbpipf?useSSL=false";
	private static final String usuario = "root";
	private static final String contrasena = "12345";	
	
	static {
		try {
			Class.forName(CONTROLADOR);
			System.out.println("Controller is okay");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}
	
	public Connection conectar() {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, usuario, contrasena);
			System.out.println("Connection successs");
			
		} catch (SQLException e) {
			System.out.println("Error en la conexion");
			e.printStackTrace();
		}
		
		return conexion;
	}
}
