package org.iesalandalus.programacion.reservasaulas.mvc.controlador;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.Vista;

public class Controlador {
	Modelo modelo;
	Vista vista;
	
	public Controlador(Modelo modelo, Vista vista) 
	{
		if (modelo == null) {
			throw new IllegalArgumentException("ERROR: El modelo no puede ser nulo.");
		}
		if (vista == null) {
			throw new IllegalArgumentException("ERROR: La vista no puede ser nula.");
		}
		this.modelo = modelo;
		this.vista = vista;
		this.vista.setControlador(this);
	}
	
	public void comenzar() {
		vista.comenzar();
	}
	
	public void terminar() {
		System.out.println("¡Hasta otra!");
	}
	
	public void insertarAula(Aula aula) throws OperationNotSupportedException {
		modelo.insertarAula(aula);
	}
	
	public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {
		modelo.insertarProfesor(profesor);
	}
	
	public void borrarAula(Aula aula) throws OperationNotSupportedException {
		modelo.borrarAula(aula);
	}
	
	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {
		modelo.borrarProfesor(profesor);
	}
	
	public Aula buscarAula(Aula aula) {
		Aula aulaBuscada=modelo.buscarAula(aula);
		return new Aula(aulaBuscada);
	}
	
	public Profesor buscarProfesor(Profesor profesor) {
		Profesor profesorBuscado=modelo.buscarProfesor(profesor);
		return new Profesor(profesorBuscado);
	}
	
	public String[] representarAulas() {
		String[] listaAulas=modelo.representarAulas();
		return listaAulas;
	}
	
	public String[] representarProfesores() {
		String[] listaProfesores=modelo.representarProfesores();
		return listaProfesores;
	}
	
	public String[] representarReservas() {
		String[] listaReservas=modelo.representarReservas();
		return listaReservas;
	}
	
	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {
		modelo.realizarReserva(reserva);
	}
	
	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {
		modelo.anularReserva(reserva);
	}
	
	public Reserva[] getReservasAula(Aula aula) {
		Reserva[] reservasAula=modelo.getReservasAula(aula);
		return reservasAula;
	}
	
	public Reserva[] getReservasProfesor(Profesor profesor) {
		Reserva[] reservasProfesor=modelo.getReservasProfesor(profesor);
		return reservasProfesor;
	}
	
	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		Reserva[] reservasPermanencia=modelo.getReservasPermanencia(permanencia);
		return reservasPermanencia;
	}
	
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		boolean disponibilidad=modelo.consultarDisponibilidad(aula, permanencia);
		return disponibilidad;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
