package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {
	private int capacidad;
	private int tamano;
	Reserva[] coleccionReservas;
//	private Profesores profesores;

//	Getters
	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

// Este getter devuelve una copia profunda mediante un método específico para ello, para evitar aliasing
	public Reserva[] get() {
		return copiaProfundaReservas();
	}

//	Método que crea una copia profunda del array y lo devuelve para así evitar aliasing.
	private Reserva[] copiaProfundaReservas() {
		int indice = 0;
		Reserva[] copiaProfunda = new Reserva[tamano];
		for (int i = indice; i <= tamano - 1; ++i) {
			copiaProfunda[indice] = new Reserva(coleccionReservas[i]);
			indice++;
		}
		return copiaProfunda;
	}

// Constructor con parámetros
	public Reservas(int capacidad) {
		if (capacidad < 1) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		coleccionReservas = new Reserva[capacidad];
		this.capacidad = capacidad;
		this.tamano = 0;
	}

//	Creamos buscarIndice, que recorrerá el array comprobando si hay alguna reserva igual a la del parámetro. 
//	Si es así, nos devuelve su índice, y si no, develve tamano+1
	private int buscarIndice(Reserva reserva) {
		boolean encontrado = false;
		int resultado = 0;
		for (int i = 0; i <= tamano - 1; i++) {
			if (reserva.equals(coleccionReservas[i])) {
				encontrado = true;
				resultado = i;
			}
		}
		if (encontrado) {
			return resultado;
		} else {
			return tamano + 1;
		}
	}

//	Creamos los métodos tamanoSuperado y capacidadSuperada. Tamaño nos indica el número de elementos en el array, así como la posición en la que insertar el siguiente.
//	Capacidad hace referencia a los espacios totales del array. Si tamaño iguala o supera a  capacidad, el array estará lleno y no se podrán insertar más elementos
	private boolean tamanoSuperado(int indice) {
		if (indice > tamano) {
			return true;
		}
		return false;
	}

	private boolean capacidadSuperada(int tamano) {
		if (tamano >= capacidad) {
			return true;
		}
		return false;
	}

//	Creamos insertar, que primero busca con buscarIndice, que buscará si la reserva existe y nos dará su índice. Si no, dos dará tamano+1, así que pasamos el índice
//	obtenido por tamanoSuperado. Si nos da true, significa que el resultado de buscarIndice ha sido tamano+1, ergo no existe esa reserva y tenemos que insertarla en el
//	siguiente índice libre, que sería tamano
	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		}
		int posibleHueco = buscarIndice(reserva);
		if (capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
		} else if (tamanoSuperado(posibleHueco)) {
			coleccionReservas[tamano] = new Reserva(reserva);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un reserva con ese nombre.");
		}
	}

//Creamos el método buscar, que mediante buscarIndice comprobará si hay alguna reserva igual a la del parámetro. Si es así, buscarIndice nos dará su índice, por lo
//que devolvemos una copia de la reserva. Si no, buscarIndice nos retornará tamano+1, cosa que comprobaremos mediante tamanoSuperado, y en ese caso devolvemos null
	public Reserva buscar(Reserva reserva) {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}
		int indice = buscarIndice(reserva);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Reserva(coleccionReservas[indice]);
		}
	}

//	Creamos este método, que recorre el array desde el índice que le damos y desde ahí pisa ese profesor con su profesor de la derecha durante todo el tamaño
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < tamano - 1; ++i) {
			coleccionReservas[i] = new Reserva(coleccionReservas[i + 1]);
		}
	}

// Creamos el método borrar, que busca el índice de la reserva pasada como parámetro con buscarIndice. Comprobamos si el índice resultante supera el tamano
// mediante tamanoSuperado. Si es así, significa que no ha encontrado ninguna reserva coincidente y devolvemos excepcion. Si es así, pasamos el método
// desplazarUnaPosicionHaciaIzquierda desde el índice donde ha encontrado la coincidencia para borrar la reserva y reordenar el array, disminuyendo tamano después.
	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		int indice = 0;
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
		}
		indice = buscarIndice(reserva);
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			coleccionReservas[tamano - 1] = null;
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna reserva con ese nombre.");
		}
	}

//Creamos el método representar que creará un array de tipo String en el que guardaremos los .toString de los profesores para su posterior uso.
	public String[] representar() {
		String[] representacion = new String[tamano];
		int indice = 0;
		for (int i = indice; i <= tamano - 1; ++i) {
			representacion[indice] = coleccionReservas[i].toString();
			indice++;
		}
		return representacion;
	}

//Creamos getReservasProfesor, que ante un profesor dado recorrerá el array de Reservas buscando todas aquellas en las que coincida el profesor con el
//profesor dado. Creará un nuevo array con las coincidencias y lo devolverá.
	public Reserva[] getReservasProfesor(Profesor profesor) {
		Reserva[] reservasProfesor = new Reserva[capacidad];
		int indice = 0;
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}
//		else if(profesores.buscar(profesor)==null) {
//			reservasProfesor=null;
//		}
		for (int i = 0; i <= tamano - 1; i++) {
			if (profesor.equals(coleccionReservas[i].getProfesor())) {
				reservasProfesor[indice] = new Reserva(coleccionReservas[i]);
				indice++;
			}
		}
		return reservasProfesor;
	}

//Creamos getReservasAula, que ante un aula dado recorrerá el array de Reservas buscando todas aquellas en las que coincida el aula con el
//aula dado. Creará un nuevo array con las coincidencias y lo devolverá.
	public Reserva[] getReservasAula(Aula aula) {
		Reserva[] reservasAula = new Reserva[capacidad];
		int indice = 0;
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}
//			else if(profesores.buscar(profesor)==null) {
//				reservasProfesor=null;
//			}
		for (int i = 0; i <= tamano - 1; i++) {
			if (aula.equals(coleccionReservas[i].getAula())) {
				reservasAula[indice] = new Reserva(coleccionReservas[i]);
				indice++;
			}
		}
		return reservasAula;
	}

//Creamos getReservasPermanencia, que ante una permanencia dado recorrerá el array de Reservas buscando todas aquellas en las que coincida la permanencia con la
//permanencia dada. Creará un nuevo array con las coincidencias y lo devolverá.
	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		Reserva[] reservasPermanencia = new Reserva[capacidad];
		int indice = 0;
		if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}
//				else if(profesores.buscar(profesor)==null) {
//					reservasProfesor=null;
//				}
		for (int i = 0; i <= tamano - 1; i++) {
			if (permanencia.equals(coleccionReservas[i].getPermanencia())) {
				reservasPermanencia[indice] = new Reserva(coleccionReservas[i]);
				indice++;
			}
		}
		return reservasPermanencia;
	}

//Creamos este método, que ante un aula y una permanencia, recorrerá el arrat de reservas comprobando si existe coincidencias con ambas y si es así, nos dará false, 
//de lo cotrario nos dará true.
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		boolean disponibilidad = true;
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		} else if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}
		for (int i = 0; i <= tamano - 1; i++) {
			if (aula.equals(coleccionReservas[i].getAula()) && permanencia.equals(coleccionReservas[i].getPermanencia())) {
				disponibilidad = false;
			}
		}
		return disponibilidad;
	}

}
