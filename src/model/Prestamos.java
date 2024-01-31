package model;

public class Prestamos {
	private int idPrestamo;
	private String dniAlumno;
	private int codigoLibro;
	private String fechaPrestamo;
	
	public Prestamos(int idPrestamo, String dniAlumno, int codigoLibro, String fechaPrestamo) {
		this.idPrestamo = idPrestamo;
		this.dniAlumno = dniAlumno;
		this.codigoLibro = codigoLibro;
		this.fechaPrestamo = fechaPrestamo;
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
}
