package gestorBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.HistoricoPrestamos;

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
				String dniAlumno = rs.getString("dni_alumno");
				int codigoLibro = rs.getInt("codigo_libro");
				String fechaPrestamo = rs.getString("fecha_prestamo");
				String fechaDevolucion = rs.getString("fecha_devolucion");
				HistoricoPrestamos hp = new HistoricoPrestamos(idPrestamo, dniAlumno, codigoLibro, fechaPrestamo,
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
	 * Inserta un nuevo HistoricoPrestamos a la Base de Datos
	 * 
	 * @param HistoricoPrestamos
	 */
	public void insertHistoricoPrestamos(HistoricoPrestamos historicoPrestamo) {
		try {
			conexion = new ConexionBDD();
			String consulta = "INSERT INTO historico_prestamo(dni_alumno,codigo_libro,fecha_prestamo,fecha_devolucion) VALUES(?,?,?,?);";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
			pstmt.setString(1, historicoPrestamo.getDniAlumno());
			pstmt.setInt(2, historicoPrestamo.getCodigoLibro());
			pstmt.setString(3, historicoPrestamo.getFechaPrestamo());
			pstmt.setString(4, historicoPrestamo.getFechaDevolucion());
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
			pstmt.setString(1, historicoPrestamo.getDniAlumno());
			pstmt.setInt(2, historicoPrestamo.getCodigoLibro());
			pstmt.setString(3, historicoPrestamo.getFechaPrestamo());
			pstmt.setString(4, historicoPrestamo.getFechaDevolucion());
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
