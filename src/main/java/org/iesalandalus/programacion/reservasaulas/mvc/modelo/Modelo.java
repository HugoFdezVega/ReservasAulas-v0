package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Aulas;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Profesores;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Reservas;

public class Modelo {

//Asignamos la capacidad que queramos y creamos Aulas, Profesores y Reservas pasándosela como parámetro para que en dichas clases se
//creen los arrays respectivos con los que trabajaremos
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
		
//Método representarAulas, que obtiene un array String de las aulas. Este método comprobará si dicho array solo contiene nulos y, de ser
//así, devolverá nulo para que dicho resultado se trate más arriba
	public String[] representarAulas() {
		String[] listaAulas=aulas.representar();
		boolean vacio=true;
		for(String s:listaAulas) {
			if(s!=null) {
				vacio=false;
			}
		}
		if(vacio==true) {
			return null;
		}
		return listaAulas;
	}

//Método buscarAula, que busca un aula dada como parámetro y comprueba si es nula. Si lo es, significa que el aula no se encontró y 
//retorna nulo y si no, retorna una copia del aula encontrada.
	public Aula buscarAula(Aula aula){
		Aula aulaEncontrada=aulas.buscar(aula);
		if(aulaEncontrada==null) {
			return null;
		}
		else {
			return new Aula(aulaEncontrada);
		}
	}
	
//Método que inserta un aula pasada como parámetro propagando excepciones
	public void insertarAula(Aula aula) throws OperationNotSupportedException {
		aulas.insertar(aula);
	}
	
//Método que borra un aula pasada como parámetro propagando excepciones
	public void borrarAula(Aula aula) throws OperationNotSupportedException{
		aulas.borrar(aula);
	}
	
	public Profesor[] getProfesores() {
		return profesores.get();
	}
	
	public int getNumProfesores() {
		return profesores.getTamano();
	}
	
//Método que hace el equivalente de representarAulas pero con profesores
	public String[] representarProfesores() {
		String[] listaProfesores=profesores.representar();
		boolean vacio=true;
		for(String s:listaProfesores) {
			if(s!=null) {
				vacio=false;
			}
		}
		if(vacio==true) {
			return null;
		}
		return listaProfesores;
	}

//Método que hace el equivalente de buscarAulas pero con profesores
	public Profesor buscarProfesor(Profesor profesor){
		Profesor profesorEncontrado=profesores.buscar(profesor);
		if(profesorEncontrado==null) {
			return null;
		}
		return new Profesor(profesorEncontrado);
	}
	
//Método que inserta un profesor pasado como parámetro propagando excepciones
	public void insertarProfesor (Profesor profesor) throws OperationNotSupportedException {
		profesores.insertar(profesor);
	}
	
//Método que borra un prpfesor pasado como parámetro propagando excepciones
	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {
		profesores.borrar(profesor);
	}
	
	public Reserva[] getReservas() {
		return reservas.get();
	}
	
	public int getNumReservas() {
		return reservas.getTamano();
	}
	
//Método que hace el equivalente de representarAulas pero con reservas
	public String[] representarReservas() {
		String[] listaReservas=reservas.representar();
		boolean vacio=true;
		for(String s:listaReservas) {
			if(s!=null) {
				vacio=false;
			}
		}
		if(vacio==true) {
			return null;
		}
		return listaReservas;
	}

//Método que hace el equivalente de buscarAulas pero con reservas
	public Reserva buscarReserva(Reserva reserva){
		Reserva reservaEncontrada=reservas.buscar(reserva);
		if(reservaEncontrada==null) {
			return null;
		}
		return new Reserva(reservaEncontrada);
	}
	
	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {
		reservas.insertar(reserva);
	}
	
	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {
		reservas.borrar(reserva);
	}
	
//Método que hace el equivalente de representarAulas pero con un array de tipo Reserva
	public Reserva[] getReservasAula(Aula aula){
		Reserva[] reservasAula=reservas.getReservasAula(aula);
		boolean vacio=true;
		for(Reserva r:reservasAula) {
			if(r!=null) {
				vacio=false;
			}
		}
		if(vacio==true) {
			return null;
		}
		return reservasAula;
	}
	
//Método que hace el equivalente de representarAulas pero con un array de tipo Reserva y para profesores
	public Reserva[] getReservasProfesor(Profesor profesor){
		Reserva[] reservasProfesor=reservas.getReservasProfesor(profesor);
		boolean vacio=true;
		for(Reserva r:reservasProfesor) {
			if(r!=null) {
				vacio=false;
			}
		}
		if(vacio==true) {
			return null;
		}
		return reservasProfesor;
	}
	
//Método que hace el equivalente de representarAulas pero con un array de tipo Reserva y para permamencias
	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		Reserva[] reservasPermanencia=reservas.getReservasPermanencia(permanencia);
		boolean vacio=true;
		for(Reserva r:reservasPermanencia) {
			if(r!=null) {
				vacio=false;
			}
		}
		if(vacio==true) {
			return null;
		}
		return reservasPermanencia;
	}
	
//Método que correrá el método homónimo de Reservas y devolverá true si está disponible y false de lo contrario
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		return reservas.consultarDisponibilidad(aula, permanencia);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
