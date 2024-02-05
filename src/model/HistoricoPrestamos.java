package model;

import java.time.LocalDate;

public class HistoricoPrestamos {
	private int idPrestamo;
	private Alumno alumno;
	private Libro libro;
	private LocalDate fechaPrestamo;
	private LocalDate fechaDevolucion;
	
	public HistoricoPrestamos(int idPrestamo, Alumno alumno, Libro libro, LocalDate fechaPrestamo,
			LocalDate fechaDevolucion) {
		this.idPrestamo = idPrestamo;
		this.alumno = alumno;
		this.libro = libro;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
	}
	
	public HistoricoPrestamos(Alumno alumno, Libro libro, LocalDate fechaPrestamo,
			LocalDate fechaDevolucion) {
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

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
}
