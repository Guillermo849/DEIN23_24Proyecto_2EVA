package gestorBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Alumno;

public class AlumnoGestor {

	private ConexionBDD conexion;

	/**
	 * Devuelve una lista con los alumnos de la Base de Datos
	 * 
	 * @return
	 */
	public ObservableList<Alumno> cargarAlumnos() {

		ObservableList<Alumno> alumnos = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBDD();
			String consulta = "SELECT * FROM alumno";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String apellido1 = rs.getString("apellido1");
				String apellido2 = rs.getString("apellido2");
				Alumno a = new Alumno(dni, nombre, apellido1, apellido2);
				alumnos.add(a);
			}
			rs.close();
			conexion.CloseConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alumnos;
	}

	/**
	 * Inserta un nuevo alumno a la Base de Datos
	 * 
	 * @param alumno
	 */
	public void insertDeporte(Alumno alumno) {
		try {
			conexion = new ConexionBDD();
			String consulta = "INSERT INTO alumno VALUES('" + alumno.getDni() + "','" + alumno.getNombre() + "','"
					+ alumno.getApellido1() + "','" + alumno.getApellido2() + "');";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();

			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Modifica un alumno existente en la base de datos
	 * 
	 * @param alumno
	 */
	public void editarDeportista(Alumno alumno) {
		try {
			conexion = new ConexionBDD();
			String consulta = "UPDATE alumno SET nombre = ?, apellido1 = ?, apellido2 = ? WHERE dni LIKE '" + alumno.getDni() +"';";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, alumno.getNombre());
			pstmt.setString(2, alumno.getApellido1());
			pstmt.setString(3, alumno.getApellido2());
			pstmt.executeUpdate();

			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina un alumno de la Base de Datos y cualquier dato relacionado a esté en
	 * otras tablas.
	 * 
	 * @param alumno
	 */
	public void eliminarDeporte(Alumno alumno) {

		try {
			conexion = new ConexionBDD();
			String consulta = "DELETE FROM alumno WHERE dni LIKE ?;";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, alumno.getDni());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
