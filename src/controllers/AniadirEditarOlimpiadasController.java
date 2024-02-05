package controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Alumno;
import model.HistoricoPrestamos;
import model.Libro;
import model.Prestamos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class AniadirEditarOlimpiadasController {

	@FXML
	private Button btnGuardar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Label lblTituloAniadirEditar;

	@FXML
	private Label lblDato1;

	@FXML
	private Label lblDato2;

	@FXML
	private Label lblDato3;

	@FXML
	private Label lblDato4;

	@FXML
	private Label lblDato5;

	@FXML
	private TextField txtFDNI;

	@FXML
	private TextField txtFNombre;

	@FXML
	private TextField txtFApellido1;

	@FXML
	private TextField txtFApellido2;

	@FXML
	private ComboBox<Alumno> cmbxAlumno;

	@FXML
	private ComboBox<Libro> cmbxLibro;

	@FXML
	private DatePicker fechaPrestamo;

	@FXML
	private DatePicker fechaDevolucion;

	@FXML
	private TextField txtFTitulo;

	@FXML
	private TextField txtFAutor;

	@FXML
	private TextField txtFEditorial;

	@FXML
	private ComboBox<String> cmbxEstado;

	@FXML
	private CheckBox chbxBaja;

	@FXML
	private ComboBox<Prestamos> cmbxPrestamos;

	private OlimpiadasController mainController;

	private String obj;

	private Alumno alumno;

	private HistoricoPrestamos historico;

	private Libro libro;

	private Prestamos prestamo;

	/**
	 * Indicará cual es su padre y que objeto le han pasado
	 * 
	 * @param parent
	 */
	public void setParent(OlimpiadasController parent, Alumno alumno, HistoricoPrestamos historico, Libro libro,
			Prestamos prestamo, String objeto) {
		this.mainController = parent;
		this.alumno = alumno;
		this.historico = historico;
		this.libro = libro;
		this.prestamo = prestamo;
		obj = objeto;

		if (objeto.equals("Alumno")) {
			lblTituloAniadirEditar.setText("DATOS ALUMNO");
			lblDato1.setVisible(true);
			lblDato1.setText("DNI:");
			txtFDNI.setVisible(true);
			lblDato2.setVisible(true);
			lblDato2.setText("Nombre:");
			txtFNombre.setVisible(true);
			lblDato3.setVisible(true);
			lblDato3.setText("Apellido1:");
			txtFApellido1.setVisible(true);
			lblDato4.setVisible(true);
			lblDato4.setText("Apellido2:");
			txtFApellido2.setVisible(true);
			if (alumno != null) {
				txtFDNI.setText(alumno.getDni());
				txtFNombre.setText(alumno.getNombre());
				txtFApellido1.setText(alumno.getApellido1());
				txtFApellido2.setText(alumno.getApellido2());
			}
		}

		if (objeto.equals("Libro")) {
			lblTituloAniadirEditar.setText("DATOS LIBRO");
			lblDato1.setVisible(true);
			lblDato1.setText("Titulo:");
			txtFTitulo.setVisible(true);
			lblDato2.setVisible(true);
			lblDato2.setText("Autor:");
			txtFAutor.setVisible(true);
			lblDato3.setVisible(true);
			lblDato3.setText("Editorial:");
			txtFEditorial.setVisible(true);
			lblDato4.setVisible(true);
			lblDato4.setText("Estado:");
			ObservableList<String> obsLst = FXCollections.observableArrayList();
			obsLst.add("Nuevo");
			obsLst.add("Usado nuevo");
			obsLst.add("Usado seminuevo");
			obsLst.add("Usado estropeado");
			obsLst.add("Restaurado");
			cmbxEstado.setItems(obsLst);
			cmbxEstado.setVisible(true);
			lblDato5.setVisible(true);
			lblDato5.setText("Baja:");
			chbxBaja.setVisible(true);
			if (libro != null) {
				txtFTitulo.setText(libro.getTitulo());
				txtFAutor.setText(libro.getAutor());
				txtFEditorial.setText(libro.getEditorial());
				cmbxEstado.getSelectionModel().select(libro.getEstado());
				if (libro.getBaja() == 1) {
					chbxBaja.isSelected();
				}
			}
		}

		if (objeto.equals("Historico")) {
			lblTituloAniadirEditar.setText("DATOS HISTORICO PRESTAMO");
			lblDato1.setVisible(true);
			lblDato1.setText("Prestamo:");
			System.out.println(mainController.getPrestamosGstr().cargarPrestamos());
			cmbxPrestamos.setItems(mainController.getPrestamosGstr().cargarPrestamos());
			cmbxPrestamos.setVisible(true);
			fechaDevolucion.setVisible(true);
			lblDato3.setVisible(true);
			lblDato3.setText("Fecha Devolucion:");
			lblDato4.setVisible(true);
			lblDato4.setText("Estado:");
			ObservableList<String> obsLst = FXCollections.observableArrayList();
			obsLst.add("Nuevo");
			obsLst.add("Usado nuevo");
			obsLst.add("Usado seminuevo");
			obsLst.add("Usado estropeado");
			obsLst.add("Restaurado");
			cmbxEstado.setItems(obsLst);
			cmbxEstado.setVisible(true);
			if (cmbxPrestamos.getSelectionModel().getSelectedItem() != null) {
				cmbxEstado.getSelectionModel()
					.select(cmbxPrestamos.getSelectionModel().getSelectedItem().getLibro().getEstado());
			}
		}

		if (objeto.equals("Prestamo")) {
			lblTituloAniadirEditar.setText("DATOS PRESTAMO");
			lblDato1.setVisible(true);
			lblDato1.setText("Alumno:");
			cmbxAlumno.setItems(mainController.getAlumnoGstr().cargarAlumnos());
			cmbxAlumno.setVisible(true);
			lblDato2.setVisible(true);
			lblDato2.setText("Libro:");
			cmbxLibro.setItems(mainController.getLibroGstr().cargarLibros());
			cmbxLibro.setVisible(true);
			lblDato3.setVisible(true);
			lblDato3.setText("Fecha Prestamo:");
			fechaPrestamo.setVisible(true);
		}
	}
	
	/**
	 * Cierra la pestaña
	 * 
	 * @param event
	 */
	@FXML
	void cancelar(ActionEvent event) {
		Node n = (Node) event.getSource();
		Stage stage = (Stage) n.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * Guarda la informacion del objeto
	 * 
	 * @param event
	 */
	@FXML
	void guardar(ActionEvent event) {
		if (obj.equals("Alumno")) {
			Alumno alum = new Alumno(txtFDNI.getText(), txtFNombre.getText(), txtFApellido1.getText(),
					txtFApellido2.getText());
			if (this.alumno != null) {
				mainController.getAlumnoGstr().editarAlumno(alum);
			} else {
				mainController.getAlumnoGstr().insertAlumno(alum);
			}
			mainController.mostrarTablaAlumno(event);
		}
		if (obj.equals("Libro")) {
			int baja = 0;
			if (chbxBaja.isSelected()) {
				baja = 1;
			}
			Libro libr = new Libro(txtFTitulo.getText(), txtFAutor.getText(), txtFEditorial.getText(),
					cmbxEstado.getSelectionModel().getSelectedItem(), baja);
			if (this.libro != null) {
				libr.setCodigo(this.libro.getCodigo());
				mainController.getLibroGstr().editarLibro(libr);
			} else {
				mainController.getLibroGstr().insertLibro(libr);
			}
			mainController.mostrarTablaLibro(event);
		}
		if (obj.equals("Historico")) {
			Prestamos p = cmbxPrestamos.getSelectionModel().getSelectedItem();
			HistoricoPrestamos his = new HistoricoPrestamos(p.getAlumno(),
					p.getLibro(), p.getFechaPrestamo(),
					fechaDevolucion.getValue());
			his.getLibro().setBaja(0);
			mainController.getLibroGstr().editarLibro(his.getLibro());
			mainController.getHistoricoPrestmosGstr().insertHistoricoPrestamos(his);
			mainController.getPrestamosGstr().eliminarPrestamos(p);
		}
		if (obj.equals("Prestamo")) {
			Prestamos pre = new Prestamos(cmbxAlumno.getSelectionModel().getSelectedItem(),
					cmbxLibro.getSelectionModel().getSelectedItem(), fechaPrestamo.getValue());
			pre.getLibro().setBaja(1);
			mainController.getLibroGstr().editarLibro(pre.getLibro());
			mainController.getPrestamosGstr().insertPrestamos(pre);
		}
		Node n = (Node) event.getSource();
		Stage stage = (Stage) n.getScene().getWindow();
		stage.close();
	}
}
