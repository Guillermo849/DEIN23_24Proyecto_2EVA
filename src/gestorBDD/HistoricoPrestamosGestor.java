package gestorBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import conexion.ConexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Alumno;
import model.HistoricoPrestamos;
import model.Libro;

public class HistoricoPrestamosGestor {

	private ConexionBDD conexion;
	
	/**
	 * Devuelve una lista con los HistoricoPrestamoss de la Base de Datos
	 * 
	 * @return
	 */
	public ObservableList<HistoricoPrestamos> cargarHistoricoPrestamoss() {

		ObservableList<HistoricoPrestamos> HistoricoPrestamoss = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBDD();
			String consulta = "SELECT * FROM historico_prestamo";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idPrestamo = rs.getInt("id_prestamo");
				Alumno alumno = obtenerAlumno(rs.getString("dni_alumno"));
				Libro libro = obtenerLibro(rs.getInt("codigo_libro"));
				java.sql.Date sqlDate = rs.getDate("fecha_prestamo");
				LocalDate fechaPrestamo = sqlDate.toLocalDate();
				sqlDate = rs.getDate("fecha_devolucion");
				LocalDate fechaDevolucion = sqlDate.toLocalDate();
				HistoricoPrestamos hp = new HistoricoPrestamos(idPrestamo, alumno, libro, fechaPrestamo,
						fechaDevolucion);
				HistoricoPrestamoss.add(hp);
			}
			rs.close();
			conexion.CloseConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return HistoricoPrestamoss;
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
			alumno = new Alumno(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido1"),
					rs.getString("apellido2"));
			rs.close();
			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alumno;
	}
	
	/**
	 * Devuelve el Libro que le corresponde
	 * @param idLibro
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
			libro = new Libro(rs.getInt("codigo"), rs.getString("titulo"), rs.getString("autor"),
					rs.getString("editorial"), rs.getString("estado"), rs.getInt("baja"));
			rs.close();
			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return libro;
	}

	/**
	 * Inserta un nuevo HistoricoPrestamos a la Base de Datos
	 * 
	 * @param HistoricoPrestamos
	 */
	public void insertHistoricoPrestamos(HistoricoPrestamos historicoPrestamo) {
		try {
			conexion = new ConexionBDD();
			String consulta = "INSERT INTO historico_prestamo(dni_alumno,codigo_libro,fecha_prestamo,fecha_devolucion) VALUES(?,?,?,?);";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);

			pstmt.setString(1, historicoPrestamo.getAlumno().getDni());
			pstmt.setInt(2, historicoPrestamo.getLibro().getCodigo());
			java.sql.Date sqlD = java.sql.Date.valueOf(historicoPrestamo.getFechaPrestamo());
			pstmt.setDate(3, sqlD);
			java.sql.Date sqlDD = java.sql.Date.valueOf(historicoPrestamo.getFechaDevolucion());
			pstmt.setDate(4, sqlDD);
			pstmt.executeUpdate();
			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Modifica un HistoricoPrestamos existente en la base de datos
	 * 
	 * @param HistoricoPrestamos
	 */
	public void editarHistoricoPrestamos(HistoricoPrestamos historicoPrestamo) {
		try {
			conexion = new ConexionBDD();
			String consulta = "UPDATE historico_prestamo SET dni_alumno=? ,codigo_libro=? ,fecha_prestamo=? ,fecha_devolucion=? WHERE id_prestamo ="
					+ historicoPrestamo.getIdPrestamo() + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, historicoPrestamo.getAlumno().getDni());
			pstmt.setInt(2, historicoPrestamo.getLibro().getCodigo());
			java.sql.Date sqlD = java.sql.Date.valueOf(historicoPrestamo.getFechaPrestamo());
			pstmt.setDate(3, sqlD);
			sqlD = java.sql.Date.valueOf(historicoPrestamo.getFechaDevolucion());
			pstmt.setDate(4, sqlD);
			pstmt.executeUpdate();

			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina un HistoricoPrestamos de la Base de Datos
	 * 
	 * @param HistoricoPrestamos
	 */
	public void eliminarHistoricoPrestamos(HistoricoPrestamos historicoPrestamo) {

		try {
			conexion = new ConexionBDD();
			String consulta = "DELETE FROM historico_prestamo WHERE id_prestamo = ?;";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setInt(1, historicoPrestamo.getIdPrestamo());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
