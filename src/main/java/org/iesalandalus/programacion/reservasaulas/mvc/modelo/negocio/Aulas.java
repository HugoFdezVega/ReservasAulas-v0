package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {
	private int capacidad;
	private int tamano;
	Aula[] coleccionAulas;

//Getters
	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

//Este getter devuelve una copia profunda mediante un método específico para ello, para evitar aliasing
	public Aula[] get() {
		return copiaProfundaAulas();
	}

//Constructor con parámetros
	public Aulas(int capacidad) {
		if (capacidad < 1) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		coleccionAulas = new Aula[capacidad];
		this.capacidad = capacidad;
		this.tamano = 0;
	}

//	Método que crea una copia profunda del array y lo devuelve para así evitar aliasing.
	private Aula[] copiaProfundaAulas() {
		int indice = 0;
		Aula[] copiaProfunda = new Aula[tamano];
		for (int i = indice; i <= tamano - 1; ++i) {
			copiaProfunda[indice] = new Aula(coleccionAulas[i]);
			indice++;
		}
		return copiaProfunda;
	}

//	Creamos buscarIndice, que recorrerá el array comprobando si hay algun aula igual a la del parámetro. 
//	Si es así, nos devuelve su índice, y si no, develve tamano+1
	private int buscarIndice(Aula aula) {
		boolean encontrado = false;
		int resultado = 0;
		for (int i = 0; i <= tamano - 1; i++) {
			if (aula.equals(coleccionAulas[i])) {
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

//	Creamos insertar, que primero busca con buscarIndice, que buscará si el aula existe y nos dará su índice. Si no, dos dará tamano+1, así que pasamos el índice
//	obtenido por tamanoSuperado. Si nos da true, significa que el resultado de buscarIndice ha sido tamano+1, ergo no existe ese aula y tenemos que insertarla en el
//	siguiente índice libre, que sería tamano
	public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}
		int posibleHueco = buscarIndice(aula);
		if (capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más aulas.");
		} else if (tamanoSuperado(posibleHueco)) {
			coleccionAulas[tamano] = new Aula(aula);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");
		}
	}

//Creamos el método buscar, que mediante buscarIndice comprobará si hay algún aula igual a la del parámetro. Si es así, buscarIndice nos dará su índice, por lo
//que devolvemos una copia del aula. Si no, buscarIndice nos retornará tamano+1, cosa que comprobaremos mediante tamanoSuperado, y en ese caso devolvemos null
	public Aula buscar(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}
		int indice = buscarIndice(aula);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Aula(coleccionAulas[indice]);
		}
	}

//	Creamos este método, que recorre el array desde el índice que le damos y desde ahí pisa ese aula con su aula de la derecha durante todo el tamaño
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < tamano - 1; ++i) {
			coleccionAulas[i] = new Aula(coleccionAulas[i + 1]);
		}
	}

//Creamos el método borrar, que busca el índice del aula pasada como parámetro con buscarIndice. Comprobamos si el índice resultante supera el tamano
//mediante tamanoSuperado. Si es así, significa que no ha encontrado ninguna cita coincidente y devolvemos excepcion. Si es así, pasamos el método 
//desplazarUnaPosicionHaciaIzquierda desde el índice donde ha encontrado la coincidencia para borrar el aula y reordenar el array, disminuyendo tamano después.
	public void borrar(Aula aula) throws OperationNotSupportedException {
		int indice = 0;
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}
		indice = buscarIndice(aula);
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			coleccionAulas[tamano - 1] = null;
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}
	}

//Creamos el método representar que creará un array de tipo String en el que guardaremos los .toString de las aulas para su posterior uso.
	public String[] representar() {
		String[] representacion = new String[tamano];
		int indice = 0;
		for (int i = indice; i <= tamano - 1; ++i) {
			representacion[indice] = coleccionAulas[i].toString();
			indice++;
		}
		return representacion;
	}

}
