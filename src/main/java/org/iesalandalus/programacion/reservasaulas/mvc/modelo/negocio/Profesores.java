package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

public class Profesores {
	private int capacidad;
	private int tamano;
	Profesor[] coleccionProfesores;

//	Getters

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	// Este getter devuelve una copia profunda mediante un método específico para
	// ello, para evitar aliasing
	public Profesor[] get() {
		return copiaProfundaProfesores();
	}

//	Método que crea una copia profunda del array y lo devuelve para así evitar aliasing.
	private Profesor[] copiaProfundaProfesores() {
		int indice = 0;
		Profesor[] copiaProfunda = new Profesor[tamano];
		for (int i = indice; i <= tamano - 1; ++i) {
			copiaProfunda[indice] = new Profesor(coleccionProfesores[i]);
			indice++;
		}
		return copiaProfunda;
	}

//Constructor con parámetros
	public Profesores (int capacidad) {
		if(capacidad<1) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		coleccionProfesores=new Profesor[capacidad];
		this.capacidad=capacidad;
		this.tamano=0;
	}
	
//	Creamos buscarIndice, que recorrerá el array comprobando si hay algún profesor igual a la del parámetro. 
//	Si es así, nos devuelve su índice, y si no, develve tamano+1
	private int buscarIndice(Profesor profesor) {
		boolean encontrado = false;
		int resultado = 0;
		for (int i = 0; i <= tamano-1; i++) {
			if (profesor.equals(coleccionProfesores[i])) {
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
	
//	Creamos insertar, que primero busca con buscarIndice, que buscará si el profesor existe y nos dará su índice. Si no, dos dará tamano+1, así que pasamos el índice
//	obtenido por tamanoSuperado. Si nos da true, significa que el resultado de buscarIndice ha sido tamano+1, ergo no existe ese profesor y tenemos que insertarlo en el
//	siguiente índice libre, que sería tamano
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}
		int posibleHueco = buscarIndice(profesor);
		if (capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más profesores.");
		} else if (tamanoSuperado(posibleHueco)) {
			coleccionProfesores[tamano] = new Profesor(profesor);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese nombre.");
		}
	}
	
	
//Creamos el método buscar, que mediante buscarIndice comprobará si hay algún profesor igual al del parámetro. Si es así, buscarIndice nos dará su índice, por lo
//que devolvemos una copia del profesor. Si no, buscarIndice nos retornará tamano+1, cosa que comprobaremos mediante tamanoSuperado, y en ese caso devolvemos null
	public Profesor buscar (Profesor profesor) {
		if (profesor==null) {
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		}
		int indice=buscarIndice(profesor);
		if(tamanoSuperado(indice)) {
			return null;
		}
		else {
			return new Profesor(coleccionProfesores[indice]);
		}
	}
	
//	Creamos este método, que recorre el array desde el índice que le damos y desde ahí pisa ese profesor con su profesor de la derecha durante todo el tamaño
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < tamano-1; ++i) {
			coleccionProfesores[i] = new Profesor(coleccionProfesores[i + 1]);
		}
	}
	
// Creamos el método borrar, que busca el índice del profesor pasado como parámetro con buscarIndice. Comprobamos si el índice resultante supera el tamano
// mediante tamanoSuperado. Si es así, significa que no ha encontrado ningún profesor coincidente y devolvemos excepcion. Si es así, pasamos el método
// desplazarUnaPosicionHaciaIzquierda desde el índice donde ha encontrado la coincidencia para borrar el profesor y reordenar el array, disminuyendo tamano después.
	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		int indice = 0;
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		}
		indice = buscarIndice(profesor);
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			coleccionProfesores[tamano - 1] = null;
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
		}
	}
	
//Creamos el método representar que creará un array de tipo String en el que guardaremos los .toString de los profesores para su posterior uso.
	public String[] representar() {
		String[] representacion = new String[tamano];
		int indice = 0;
		for (int i = indice; i <= tamano - 1; ++i) {
			representacion[indice] = coleccionProfesores[i].toString();
			indice++;
		}
		return representacion;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
