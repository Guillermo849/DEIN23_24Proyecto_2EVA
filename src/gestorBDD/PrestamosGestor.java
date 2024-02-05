package gestorBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import conexion.ConexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Alumno;
import model.Libro;
import model.Prestamos;

public class PrestamosGestor {
	private ConexionBDD conexion;

	/**
	 * Devuelve una lista con los Prestamoss de la Base de Datos
	 * 
	 * @return
	 */
	public ObservableList<Prestamos> cargarPrestamos() {

		ObservableList<Prestamos> prestamos = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBDD();
			String consulta = "SELECT * FROM prestamo";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idPrestamo = rs.getInt("id_prestamo");
				Alumno alumno = obtenerAlumno(rs.getString("dni_alumno"));
				Libro libro = obtenerLibro(rs.getInt("codigo_libro"));
				java.sql.Date sqlDate = rs.getDate("fecha_prestamo");
                LocalDate fechaPrestamo = sqlDate.toLocalDate();
				Prestamos p = new Prestamos(idPrestamo, alumno, libro, fechaPrestamo);
				prestamos.add(p);
			}
			rs.close();
			conexion.CloseConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prestamos;
	}
	
	/**
	 * Devuelve el Alumno que le corresponde
	 * @param dni
	 * @return
	 */
	private Alumno obtenerAlumno(String dni) {
		Alumno alumno = null;
		try {
			conexion = new ConexionBDD();
			String consulta = "SELECT * FROM alumno WHERE dni LIKE '" + dni + "';";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			alumno  = new Alumno(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"));
			rs.close();
			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alumno;
	}
	
	/**
	 * Devuelve el Alumno que le corresponde
	 * @param dni
	 * @return
	 */
	private Libro obtenerLibro(int idLibro) {
		Libro libro = null;
		try {
			conexion = new ConexionBDD();
			String consulta = "SELECT * FROM libro WHERE codigo = " + idLibro + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			libro  = new Libro(rs.getInt("codigo"), rs.getString("titulo"), rs.getString("autor"), rs.getString("editorial"), rs.getString("estado"), rs.getInt("baja"));
			rs.close();
			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return libro;
	}

	/**
	 * Inserta un nuevo Prestamos a la Base de Datos
	 * 
	 * @param Prestamos
	 */
	public void insertPrestamos(Prestamos prestamo) {
		try {
			conexion = new ConexionBDD();
			String consulta = "INSERT INTO prestamo(dni_alumno,codigo_libro,fecha_prestamo) VALUES(?,?,?);";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			
			pstmt.setString(1, prestamo.getAlumno().getDni());
			pstmt.setInt(2, prestamo.getLibro().getCodigo());
			java.sql.Date sqlD = java.sql.Date.valueOf(prestamo.getFechaPrestamo());
			pstmt.setDate(3, sqlD);
			pstmt.executeUpdate();
			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Modifica un Prestamos existente en la base de datos
	 * 
	 * @param Prestamos
	 */
	public void editarPrestamos(Prestamos prestamo) {
		try {
			conexion = new ConexionBDD();
			String consulta = "UPDATE prestamo SET dni_alumno=? ,codigo_libro=? ,fecha_prestamo=? WHERE id_prestamo ="
					+ prestamo.getIdPrestamo() + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, prestamo.getAlumno().getDni());
			pstmt.setInt(2, prestamo.getLibro().getCodigo());
			java.sql.Date sqlD = java.sql.Date.valueOf(prestamo.getFechaPrestamo());
			pstmt.setDate(3, sqlD);
			pstmt.executeUpdate();

			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina un Prestamos de la Base de Datos
	 * 
	 * @param Prestamos
	 */
	public void eliminarPrestamos(Prestamos prestamo) {

		try {
			conexion = new ConexionBDD();
			String consulta = "DELETE FROM prestamo WHERE id_prestamo = ?;";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setInt(1, prestamo.getIdPrestamo());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
