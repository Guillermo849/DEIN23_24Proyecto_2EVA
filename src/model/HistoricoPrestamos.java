package model;

public class HistoricoPrestamos {
	private int idPrestamo;
	private String dniAlumno;
	private int codigoLibro;
	private String fechaPrestamo;
	private String fechaDevolucion;
	
	public HistoricoPrestamos(int idPrestamo, String dniAlumno, int codigoLibro, String fechaPrestamo,
			String fechaDevolucion) {
		this.idPrestamo = idPrestamo;
		this.dniAlumno = dniAlumno;
		this.codigoLibro = codigoLibro;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
	}

	public int getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public String getDniAlumno() {
		return dniAlumno;
	}

	public void setDniAlumno(String dniAlumno) {
		this.dniAlumno = dniAlumno;
	}

	public int getCodigoLibro() {
		return codigoLibro;
	}

	public void setCodigoLibro(int codigoLibro) {
		this.codigoLibro = codigoLibro;
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
