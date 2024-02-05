package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gestorBDD.AlumnoGestor;
import gestorBDD.HistoricoPrestamosGestor;
import gestorBDD.LibroGestor;
import gestorBDD.PrestamosGestor;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import model.Alumno;
import model.HistoricoPrestamos;
import model.Libro;
import model.Prestamos;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class OlimpiadasController implements Initializable {

	@FXML
	private Menu menuAniadir;

	@FXML
	private MenuItem mnItemAlumno;

	@FXML
	private MenuItem mnItemPrestamo;

	@FXML
	private MenuItem mnItemHistorico;

	@FXML
	private MenuItem mnItemLibro;

	@FXML
	private Menu menuEditar;

	@FXML
	private MenuItem mnItemEditar;

	@FXML
	private MenuItem mnItemEliminar;

	@FXML
	private Menu menuIdioma;

	@FXML
	private MenuItem mnItemEspaniol;

	@FXML
	private MenuItem mnItemIngles;

	@FXML
	private Menu menuEditar1;

	@FXML
	private MenuItem mnItemInf2;

	@FXML
	private MenuItem mnItemInf3;

	@FXML
	private MenuItem mnItemInf4;

	@FXML
	private Label lblNomTabla;

	@FXML
	private TableView<Alumno> tbViewAlumno;

	@FXML
	private TableColumn<Alumno, String> tbColumnNombreAlumno;

	@FXML
	private TableColumn<Alumno, String> tbColumnApellido1Alumno;

	@FXML
	private TableColumn<Alumno, String> tbColumnApellido2Alumno;

	@FXML
	private TableView<Prestamos> tbViewPrestamo;

	@FXML
	private TableColumn<Prestamos, Alumno> tbColumnAlumno;

	@FXML
	private TableColumn<Prestamos, Libro> tbColumnLibro;

	@FXML
	private TableColumn<Prestamos, String> tbColumnFechPrestamo;

	@FXML
	private TableView<HistoricoPrestamos> tbViewHistorico;

	@FXML
	private TableColumn<HistoricoPrestamos, Alumno> tbColumnAlumnoHistorico;

	@FXML
	private TableColumn<HistoricoPrestamos, Libro> tbColumnLibroHistorico;

	@FXML
	private TableColumn<HistoricoPrestamos, String> tbColumnFechPrestamoHistorico;

	@FXML
	private TableColumn<HistoricoPrestamos, String> tbColumnDevolucion;

	@FXML
	private TableView<Libro> tbViewLibro;

	@FXML
	private TableColumn<Libro, String> tbColumnTitulo;

	@FXML
	private TableColumn<Libro, String> tbColumnAutor;

	@FXML
	private TableColumn<Libro, String> tbColumnEditorial;

	@FXML
	private TableColumn<Libro, String> tbColumnEstado;

	@FXML
	private TableColumn<Libro, Integer> tbColumnBaja;

	@FXML
	private Button btnDeporte;

	@FXML
	private Button btnAlumno;

	@FXML
	private Button btnPrestamo;

	@FXML
	private Button btnHistorico;

	@FXML
	private Button btnLibro;

	@FXML
	private Label lblAlumno;

	@FXML
	private TextField txtFAlumno;

	@FXML
	private Label lblLibro;

	@FXML
	private TextField txtFLibro;

	/**
	 * Gestores de Base de Datos
	 */
	private AlumnoGestor alumnoGstr;
	private HistoricoPrestamosGestor historicoPrestmosGstr;
	private LibroGestor libroGstr;
	private PrestamosGestor prestamosGstr;

	private AniadirEditarOlimpiadasController aniadirEditarOlimpiadasController;

	ObservableList<HistoricoPrestamos> listFiltrado;

	private int indexObjeto;

	private Alumno almn;
	private HistoricoPrestamos hstrPrstm;
	private Libro lbr;
	private Prestamos prstm;

	/**
	 * Getter Objeto Alumno
	 * 
	 * @return
	 */
	public Alumno getAlmn() {
		return almn;
	}

	/**
	 * Getter Objeto Historico
	 * 
	 * @return
	 */
	public HistoricoPrestamos getHstrPrstm() {
		return hstrPrstm;
	}

	/**
	 * Getter Objeto Libro
	 * 
	 * @return
	 */
	public Libro getLbr() {
		return lbr;
	}

	/**
	 * Getter Objeto Prestamo
	 * 
	 * @return
	 */
	public Prestamos getPrstm() {
		return prstm;
	}

	/**
	 * Selecciona un objeto de las tablas
	 * 
	 * @param event
	 */
	@FXML
	void selectObjeto(MouseEvent event) {
		almn = null;
		hstrPrstm = null;
		lbr = null;
		prstm = null;

		if (tbViewAlumno.getSelectionModel().getSelectedItem() != null) {
			indexObjeto = tbViewAlumno.getSelectionModel().getSelectedIndex();
			almn = tbViewAlumno.getItems().get(indexObjeto);
		}
		if (tbViewHistorico.getSelectionModel().getSelectedItem() != null) {
			indexObjeto = tbViewHistorico.getSelectionModel().getSelectedIndex();
			hstrPrstm = tbViewHistorico.getItems().get(indexObjeto);
		}
		if (tbViewLibro.getSelectionModel().getSelectedItem() != null) {
			indexObjeto = tbViewLibro.getSelectionModel().getSelectedIndex();
			lbr = tbViewLibro.getItems().get(indexObjeto);
		}

		if (tbViewPrestamo.getSelectionModel().getSelectedItem() != null) {
			indexObjeto = tbViewPrestamo.getSelectionModel().getSelectedIndex();
			prstm = tbViewPrestamo.getItems().get(indexObjeto);
		}
	}

	/**
	 * Filtra la tabla de historicos por Nombre del alumno
	 * 
	 * @param event
	 */
	@FXML
	void filtrarPorNombre(ActionEvent event) {

		txtFLibro.setText("");

		String nom = txtFAlumno.getText().toString();

		if (nom != null) {
			listFiltrado = FXCollections.observableArrayList();

			for (HistoricoPrestamos his : historicoPrestmosGstr.cargarHistoricoPrestamoss()) {

				if (his.getAlumno().getNombre().length() >= nom.length()) {

					boolean iguales = true;
					char[] nomFiltro = nom.toCharArray();
					char[] nomPersona = his.getAlumno().getNombre().toCharArray();

					for (int i = 0; i < nomFiltro.length; i++) {
						if (nomFiltro[i] != nomPersona[i]) {
							iguales = false;
							break;
						}
					}

					if (iguales == true) {
						listFiltrado.add(his);
					}
				}
			}

			if (!listFiltrado.isEmpty()) {
				tbViewHistorico.setItems(listFiltrado);
			}

		} else {
			tbViewHistorico.setItems(historicoPrestmosGstr.cargarHistoricoPrestamoss());
		}
	}

	/**
	 * Filtra la tabla de historicos por el titulo del Libro
	 * 
	 * @param event
	 */
	@FXML
	void filtrarPorLibro(ActionEvent event) {

		txtFAlumno.setText("");

		String nom = txtFLibro.getText().toString();

		if (nom != null) {
			listFiltrado = FXCollections.observableArrayList();

			for (HistoricoPrestamos his : historicoPrestmosGstr.cargarHistoricoPrestamoss()) {

				if (his.getLibro().getTitulo().length() >= nom.length()) {

					boolean iguales = true;
					char[] nomFiltro = nom.toCharArray();
					char[] nomPersona = his.getLibro().getTitulo().toCharArray();

					for (int i = 0; i < nomFiltro.length; i++) {
						if (nomFiltro[i] != nomPersona[i]) {
							iguales = false;
							break;
						}
					}

					if (iguales == true) {
						listFiltrado.add(his);
					}
				}
			}

			if (!listFiltrado.isEmpty()) {
				tbViewHistorico.setItems(listFiltrado);
			}

		} else {
			tbViewHistorico.setItems(historicoPrestmosGstr.cargarHistoricoPrestamoss());
		}
	}

	/**
	 * Abre la pestaña para añadir un objeto
	 * 
	 * @param tipo
	 */
	private void abrirAniadir(String tipo) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aniadirEditarOlimpiadas.fxml"));
			Parent root = loader.load();
			aniadirEditarOlimpiadasController = loader.getController();
			aniadirEditarOlimpiadasController.setParent(this, null, null, null, null, tipo);

			Stage agregarStage = new Stage();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/flatered.css").toExternalForm());
			agregarStage.setScene(scene);
			agregarStage.setResizable(false);
			agregarStage.setTitle("Olimpiadas");
			agregarStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Abre la pestaña para añadir un Alumno
	 * 
	 * @param event
	 */
	@FXML
	void aniadirAlumno(ActionEvent event) {
		abrirAniadir("Alumno");
	}

	/**
	 * Abre la pestaña para añadir un Historico
	 * 
	 * @param event
	 */
	@FXML
	void aniadirHistorico(ActionEvent event) {
		abrirAniadir("Historico");
	}

	/**
	 * Abre la pestaña para añadir un Libro
	 * 
	 * @param event
	 */
	@FXML
	void aniadirLibro(ActionEvent event) {
		abrirAniadir("Libro");
	}

	/**
	 * Abre la pestaña para añadir un Prestamo
	 * 
	 * @param event
	 */
	@FXML
	void aniadirPrestamo(ActionEvent event) {
		abrirAniadir("Prestamo");
	}

	@FXML
	void cambiarIdiomaAEnglish(ActionEvent event) {

	}

	@FXML
	void cambiarIdiomaAEspaniol(ActionEvent event) {

	}

	@FXML
	void mostrarInf2(ActionEvent event) {

	}

	@FXML
	void mostrarInf3(ActionEvent event) {

	}

	@FXML
	void mostrarInf4(ActionEvent event) {

	}

	/**
	 * Editar los objetos
	 * 
	 * @param event
	 */
	@FXML
	void editarObjeto(ActionEvent event) {
		if (indexObjeto > -1) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aniadirEditarOlimpiadas.fxml"));
				Parent root = loader.load();
				aniadirEditarOlimpiadasController = loader.getController();

				if (almn != null || lbr != null) {
					if (almn != null) {
						aniadirEditarOlimpiadasController.setParent(this, almn, null, null, null, "Alumno");
					}
					if (lbr != null) {
						aniadirEditarOlimpiadasController.setParent(this, null, null, lbr, null, "Libro");
					}
					almn = null;
					lbr = null;
					Stage agregarStage = new Stage();
					agregarStage.setScene(new Scene(root));
					agregarStage.setResizable(false);
					agregarStage.setTitle("Biblioteca");
					agregarStage.showAndWait();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Eliminar Objetos
	 * 
	 * @param event
	 */
	@FXML
	void eliminarObjeto(ActionEvent event) {
		if (indexObjeto > -1) {
			if (almn != null) {
				alumnoGstr.eliminarAlumno(almn);
				mostrarTablaAlumno(event);
			}
			if (hstrPrstm != null) {
				historicoPrestmosGstr.eliminarHistoricoPrestamos(hstrPrstm);
				mostrarTablaHistorico(event);
			}
			if (lbr != null) {
				libroGstr.eliminarLibro(lbr);
				mostrarTablaLibro(event);
			}
			if (prstm != null) {
				prestamosGstr.eliminarPrestamos(prstm);
				mostrarTablaPrestamo(event);
			}
		}
	}

	/**
	 * Muestra la tabla del boton pulsado
	 * 
	 * @param tabla
	 */
	private void mostrarTabla(String tabla) {
		lblAlumno.setVisible(false);
		txtFAlumno.setVisible(false);
		lblLibro.setVisible(false);
		txtFLibro.setVisible(false);
		tbViewAlumno.setVisible(false);
		tbViewHistorico.setVisible(false);
		tbViewLibro.setVisible(false);
		tbViewPrestamo.setVisible(false);

		if (tabla.equals("Alumno"))
			tbViewAlumno.setVisible(true);
		if (tabla.equals("Historico"))
			tbViewHistorico.setVisible(true);
		if (tabla.equals("Libro"))
			tbViewLibro.setVisible(true);
		if (tabla.equals("Prestamo"))
			tbViewPrestamo.setVisible(true);
	}

	/**
	 * Muestra la tabla de Alumnos
	 * 
	 * @param event
	 */
	@FXML
	void mostrarTablaAlumno(ActionEvent event) {
		mostrarTabla("Alumno");
		lblNomTabla.setText("ALUMNOS");
		tbColumnNombreAlumno.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
		tbColumnApellido1Alumno.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getApellido1()));
		tbColumnApellido2Alumno.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getApellido2()));
		tbViewAlumno.setItems(alumnoGstr.cargarAlumnos());
	}

	/**
	 * Muestra la tabla de Historicos
	 * @param event
	 */
	@FXML
	void mostrarTablaHistorico(ActionEvent event) {
		mostrarTabla("Historico");
		lblAlumno.setVisible(true);
		txtFAlumno.setVisible(true);
		lblLibro.setVisible(true);
		txtFLibro.setVisible(true);
		lblNomTabla.setText("HISTORICO PRESTAMOS");
		tbColumnAlumnoHistorico.setCellValueFactory(new PropertyValueFactory<>("Alumno"));
		tbColumnLibroHistorico.setCellValueFactory(new PropertyValueFactory<>("Libro"));
		tbColumnFechPrestamoHistorico
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFechaPrestamo().toString()));
		tbColumnDevolucion
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFechaDevolucion().toString()));
		tbViewHistorico.setItems(historicoPrestmosGstr.cargarHistoricoPrestamoss());
	}
	/**
	 * Muestra la tabla del Libro
	 * @param event
	 */
	@FXML
	void mostrarTablaLibro(ActionEvent event) {
		mostrarTabla("Libro");

		lblNomTabla.setText("LIBROS");
		tbColumnTitulo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitulo()));
		tbColumnAutor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAutor()));
		tbColumnEditorial.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEditorial()));
		tbColumnEstado.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEstado()));
		tbColumnBaja.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("Baja"));
		tbViewLibro.setItems(libroGstr.cargarLibros());
	}
	
	/**
	 * Muestra la tabla de Prestamos
	 * @param event
	 */
	@FXML
	void mostrarTablaPrestamo(ActionEvent event) {
		mostrarTabla("Prestamo");

		lblNomTabla.setText("PRESTAMOS");
		tbColumnAlumno.setCellValueFactory(new PropertyValueFactory<>("Alumno"));
		tbColumnLibro.setCellValueFactory(new PropertyValueFactory<>("Libro"));
		tbColumnFechPrestamo
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFechaPrestamo().toString()));
		tbViewPrestamo.setItems(prestamosGstr.cargarPrestamos());
	}
	
	/**
	 * Devuelve el gestor de Alumnos
	 * 
	 * @return
	 */
	public AlumnoGestor getAlumnoGstr() {
		return alumnoGstr;
	}
	
	/**
	 * Devuelve el gestor de Historicos
	 * 
	 * @return
	 */
	public HistoricoPrestamosGestor getHistoricoPrestmosGstr() {
		return historicoPrestmosGstr;
	}
	
	/**
	 * Devuelve el gestor de Libros
	 * 
	 * @return
	 */
	public LibroGestor getLibroGstr() {
		return libroGstr;
	}
	
	/**
	 * Devuelve el gestor de Prestamos
	 * 
	 * @return
	 */
	public PrestamosGestor getPrestamosGstr() {
		return prestamosGstr;
	}
	
	/**
	 * Inicializa la aplicacion
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		alumnoGstr = new AlumnoGestor();
		historicoPrestmosGstr = new HistoricoPrestamosGestor();
		libroGstr = new LibroGestor();
		prestamosGstr = new PrestamosGestor();

		ActionEvent ae = new ActionEvent();
		mostrarTablaAlumno(ae);
	}
}
