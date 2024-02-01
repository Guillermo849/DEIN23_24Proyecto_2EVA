package gestorBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
				int idPrestamo = rs.getInt("codigo");
				String dniAlumno = rs.getString("titulo");
				int codigoLibro = rs.getInt("baja");
				String fechaPrestamo = rs.getString("autor");
				
				Prestamos p = new Prestamos(idPrestamo, dniAlumno, codigoLibro, fechaPrestamo);
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
	 * Inserta un nuevo Prestamos a la Base de Datos
	 * 
	 * @param Prestamos
	 */
	public void insertPrestamos(Prestamos prestamo) {
		try {
			conexion = new ConexionBDD();
			String consulta = "INSERT INTO prestamo(dni_alumno,codigo_libro,fecha_prestamo) VALUES(?,?,?);";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
			pstmt.setString(1, prestamo.getDniAlumno());
			pstmt.setInt(2, prestamo.getCodigoLibro());
			pstmt.setString(3, prestamo.getFechaPrestamo());

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
			pstmt.setString(1, prestamo.getDniAlumno());
			pstmt.setInt(2, prestamo.getCodigoLibro());
			pstmt.setString(3, prestamo.getFechaPrestamo());
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
			String consulta = "DELETE FROM prestamo WHERE codigo = ?;";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setInt(1, prestamo.getIdPrestamo());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
