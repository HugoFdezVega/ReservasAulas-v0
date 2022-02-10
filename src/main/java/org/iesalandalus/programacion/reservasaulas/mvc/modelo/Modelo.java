package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Aulas;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Profesores;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Reservas;

public class Modelo {

	Controlador controlador;
	static final private int CAPACIDAD=10;
	
	Aulas aulas=new Aulas (CAPACIDAD);
	Profesores profesores=new Profesores(CAPACIDAD);
	Reservas reservas=new Reservas(CAPACIDAD);
	
	public Modelo(){

	}
	
	public Aula[] getAulas(){
		return aulas.get();
	}
		
	public int getNumAulas() {
		return aulas.getTamano();
	}
		
	public String[] representarAulas() {
		return aulas.representar();
	}
	
	public Aula buscarAula(Aula aula){
		Aula aulaEncontrada=aulas.buscar(aula);
		return new Aula(aulaEncontrada);
	}
	
	public void insertarAula(Aula aula) throws OperationNotSupportedException {
		aulas.insertar(aula);
	}
	
	public void borrarAula(Aula aula) throws OperationNotSupportedException{
		aulas.borrar(aula);
	}
	
	public Profesor[] getProfesores() {
		return profesores.get();
	}
	
	public int getNumProfesores() {
		return profesores.getTamano();
	}
	
	public String[] representarProfesores() {
		return profesores.representar();
	}
	
	public Profesor buscarProfesor(Profesor profesor){
		Profesor profesorEncontrado=profesores.buscar(profesor);
		return new Profesor(profesorEncontrado);
	}
	
	public void insertarProfesor (Profesor profesor) throws OperationNotSupportedException {
		profesores.insertar(profesor);
	}
	
	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {
		profesores.borrar(profesor);
	}
	
	public Reserva[] getReservas() {
		return reservas.get();
	}
	
	public int getNumReservas() {
		return reservas.getTamano();
	}
	
	public String[] representarReservas() {
		return reservas.representar();
	}
	
	public Reserva buscarReserva(Reserva reserva){
		Reserva reservaEncontrada=reservas.buscar(reserva);
		return new Reserva(reservaEncontrada);
	}
	
	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {
		reservas.insertar(reserva);
	}
	
	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {
		reservas.borrar(reserva);
	}
	
	public Reserva[] getReservasAula(Aula aula){
		return reservas.getReservasAula(aula);
	}
	
	public Reserva[] getReservasProfesor(Profesor profesor){
		return reservas.getReservasProfesor(profesor);
	}
	
	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		return reservas.getReservasPermanencia(permanencia);
	}
	
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		return reservas.consultarDisponibilidad(aula, permanencia);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
