package model;

public class HistoricoPrestamos {
	private int idPrestamo;
	private Alumno alumno;
	private Libro libro;
	private String fechaPrestamo;
	private String fechaDevolucion;
	
	public HistoricoPrestamos(int idPrestamo, Alumno alumno, Libro libro, String fechaPrestamo,
			String fechaDevolucion) {
		this.idPrestamo = idPrestamo;
		this.alumno = alumno;
		this.libro = libro;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
	}

	public int getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public Alumno getalumno() {
		return alumno;
	}

	public void setalumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Libro getlibro() {
		return libro;
	}

	public void setlibro(Libro libro) {
		this.libro = libro;
	}

	public String getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public String getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
}
