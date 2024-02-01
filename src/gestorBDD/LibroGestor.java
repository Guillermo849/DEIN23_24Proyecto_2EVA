package gestorBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Libro;

public class LibroGestor {
	private ConexionBDD conexion;

	/**
	 * Devuelve una lista con los libros de la Base de Datos
	 * 
	 * @return
	 */
	public ObservableList<Libro> cargarLibros() {

		ObservableList<Libro> libros = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBDD();
			String consulta = "SELECT * FROM libro";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int codigo = rs.getInt("codigo");
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String editorial = rs.getString("editorial");
				String estado = rs.getString("estado");
				int baja = rs.getInt("baja");
				Libro l = new Libro(codigo, titulo, autor, editorial, estado, baja);
				libros.add(l);
			}
			rs.close();
			conexion.CloseConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return libros;
	}

	/**
	 * Inserta un nuevo libro a la Base de Datos
	 * 
	 * @param libro
	 */
	public void insertLibro(Libro libro) {
		try {
			conexion = new ConexionBDD();
			String consulta = "INSERT INTO libro(titulo,autor,editorial,estado,baja) VALUES(?,?,?,?,?);";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
			pstmt.setString(1, libro.getTitulo());
			pstmt.setString(2, libro.getAutor());
			pstmt.setString(3, libro.getEditorial());
			pstmt.setString(4, libro.getEstado());
			pstmt.setInt(5, libro.getBaja());

			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Modifica un libro existente en la base de datos
	 * 
	 * @param libro
	 */
	public void editarLibro(Libro libro) {
		try {
			conexion = new ConexionBDD();
			String consulta = "UPDATE libro SET titulo=? ,autor=? ,editorial=? ,estado=? ,baja=? WHERE codigo ="
					+ libro.getCodigo() + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, libro.getTitulo());
			pstmt.setString(2, libro.getAutor());
			pstmt.setString(3, libro.getEditorial());
			pstmt.setString(4, libro.getEstado());
			pstmt.setInt(5, libro.getBaja());
			pstmt.executeUpdate();

			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina un libro de la Base de Datos
	 * 
	 * @param libro
	 */
	public void eliminarLibro(Libro libro) {

		try {
			conexion = new ConexionBDD();
			String consulta = "DELETE FROM libro WHERE codigo = ?;";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setInt(1, libro.getCodigo());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
