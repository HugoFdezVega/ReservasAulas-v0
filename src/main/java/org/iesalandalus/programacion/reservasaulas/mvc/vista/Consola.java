package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private final static DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	Opcion[] opciones = Opcion.values();
	Tramo[] tramos = Tramo.values();

	private Consola() {

	}

	public void mostrarMenu() {
		for (Opcion o : opciones) {
			o.toString();
		}
	}

	public void mostrarCabecera(String cabecera) {
		System.out.println(cabecera);
	}

	public int elegirOpcion() {
		System.out.println("Por favor, elija una opción del menú");
		int eleccion = Entrada.entero();
		return eleccion;
	}

	public Aula leerAula() {
		Aula aula = new Aula(leerNombreAula());
		return new Aula(aula);
	}

	public String leerNombreAula() {
		System.out.println("Introduzca el nombre del aula");
		String nombreAula = Entrada.cadena();
		return nombreAula;
	}

	public Profesor leerProfesor() {
		String nombreProfesor = leerNombreProfesor();
		System.out.println("Introduzca el correo del profesor");
		String correoProfesor = Entrada.cadena();
		System.out.println("Introduzca el teléfono del profesor");
		String telefonoProfesor = Entrada.cadena();
		Profesor profesor = new Profesor(nombreProfesor, correoProfesor, telefonoProfesor);
		return new Profesor(profesor);
	}

	public String leerNombreProfesor() {
		System.out.println("Introduzca el nombre del profesor");
		String nombreProfesor = Entrada.cadena();
		return nombreProfesor;
	}

	public Tramo leerTramo() {
		Tramo tramoFinal = null;
		boolean problema = false;
		do {
			System.out.println("Elija un tramo horario:");
			for (Tramo t : tramos) {
				System.out.println(t.toString());
			}
			String tramoElegido = Entrada.cadena();
			if (tramoElegido.equalsIgnoreCase(tramos[0].toString())) {
				tramoFinal = Tramo.MANANA;
				problema=false;
			} else if (tramoElegido.equalsIgnoreCase(tramos[1].toString())) {
				tramoFinal = Tramo.TARDE;
				problema=false;
			} else {
				System.out.println("ERROR: El tramo introducido no es válido");
				problema=true;
			}
		} while (problema == true);
		return tramoFinal;
	}

	public LocalDate leerDia() {
		LocalDate fechaFinal = null;
		boolean problema = false;
		do {
			try {
				System.out.println("Introduzca una fecha(formato dd/mm/aaaa):");
				String fechaIntroducida = Entrada.cadena();
				fechaFinal = LocalDate.parse(fechaIntroducida, FORMATO_DIA);
				problema = false;

			} catch (DateTimeParseException e) {
				System.out.println("ERROR: Formato incorrecto");
				problema = true;
			}
		} while (problema == true);
		return fechaFinal;
	}

}
