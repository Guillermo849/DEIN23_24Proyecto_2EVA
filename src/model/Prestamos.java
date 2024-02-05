package model;

import java.time.LocalDate;

public class Prestamos {
	private int idPrestamo;
	private Alumno alumno;
	private Libro libro;
	private LocalDate fechaPrestamo;
	
	public Prestamos(int idPrestamo, Alumno alumno, Libro libro, LocalDate fechaPrestamo) {
		this.idPrestamo = idPrestamo;
		this.alumno = alumno;
		this.libro = libro;
		this.fechaPrestamo = fechaPrestamo;
	}
	public Prestamos(Alumno alumno, Libro libro, LocalDate fechaPrestamo) {
		this.alumno = alumno;
		this.libro = libro;
		this.fechaPrestamo = fechaPrestamo;
	}

	public int getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(LocalDate fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	
	@Override
	public String toString() {
		return alumno + " - " + libro + " (" + fechaPrestamo + ")";
	}
}
