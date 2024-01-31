package conexion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;
import java.util.Properties;

/**
 * Clase que se encargará de conectarnos a la base de datos
 */

public class ConexionBDD {
	private Connection conexion;
	private Properties prop;

	/**
	 * Constructor de la clase que crea la conexion a la Base de Datos
	 */
	public ConexionBDD() {
		prop = new Properties();
		try (FileInputStream fis = new FileInputStream(
				new File(this.getClass().getResource("/properties/propiedades.properties").getPath()))) {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			String host = prop.getProperty("db.url");
			String baseDatos = prop.getProperty("db.name");
			String usuario = prop.getProperty("db.user");
			String password = prop.getProperty("db.pwd");
			String cadenaConexion = "jdbc:mysql://" + host + ":3306/" + baseDatos;
			conexion = DriverManager.getConnection(cadenaConexion, usuario, password);
			conexion.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Devuelve la conexion establecida
	 * 
	 * @return
	 */
	public Connection getConexion() {
		return conexion;
	}

	/**
	 * Cierra la conexion con la Base de Datos
	 */
	public void CloseConexion() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
