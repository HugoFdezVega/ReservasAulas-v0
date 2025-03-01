package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;

public class Vista {
	Controlador controlador;

	private final static String ERROR = "No existen reservas para el parámetro proporcionado";
	private final static String NOMBRE_VALIDO = "Manolo";
	private final static String CORREO_VALIDO = "manolo@eldelbombo.com";

//Constructor de la vista, que corre el método setVista del enum Opcion para que éste sepa quién es la Vista y ejecute las opciones en
//consecuencia. 
	public Vista() {
		Opcion.setVista(this);
	}

//Método que setea el controlador una vez se instancie
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

//Método comenzar, que muestra el menú, nos da a elegir una opción, pasa dicha opción por el método getOpcionSegunOrdinal para validar que el ordenal
//sea correcto, lo transforma en una Opcion y luego la ejecuta. Todo ello se repetirá mientras la Opcion elegida no sea SALIR
	public void comenzar() {
		int ordinalOpcion = 0;
		do {
			try {
				Consola.mostrarMenu();
				ordinalOpcion = Consola.elegirOpcion();
				Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
				opcion.ejecutar();
			} catch (NullPointerException | IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

//Método que llama al método terminar del controlador y da un mensaje de despedida
	public void salir() {
		System.out.println("¡Hasta otra!");
		controlador.terminar();
	}

//Método que intenta llamar al método homónimo del controlador pasándo como parámetro el método adecuado de la consola. Captura todos los errores posibles,
//los muestra y se queda dentro del bucle hasta que no existen. Luego nos da un mensaje de realización correcta.
	public void insertarAula() {
		boolean problema = false;
		do {
			try {
				controlador.insertarAula(Consola.leerAula());
				problema = false;
			} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
				System.out.println(e.getMessage());
				problema = true;
			}
		} while (problema == true);
		System.out.println("Aula insertada correctamente.");
	}

//Método que intenta llamar al método homónimo del controlador pasándo como parámetro el método adecuado de la consola. Captura todos los errores posibles,
//los muestra y se queda dentro del bucle hasta que no existen. Luego nos da un mensaje de realización correcta.
	public void borrarAula() {
		boolean problema = false;
		String[] listaAulas = controlador.representarAulas();
		if (listaAulas == null) {
			System.out.println("No existen aulas que borrar");
		} else {
			do {
				try {
					controlador.borrarAula(Consola.leerAula());
					problema = false;
				} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
					System.out.println(e.getMessage());
					problema = true;
				}
			} while (problema == true);
			System.out.println("Aula borrada correctamente.");
		}
	}

//Método que crea un Aula y luego ejecuta el método homónimo del controlador pasando como parámetro el método adecuado de la consola. Captura todos los
//errores posibles, los muestra y queda dentro del bucle hasta que no existen. Luego comprueba si el retorno es nulo (no existe) y muestra un mensaje en
//consecuencia. Si el aula existe, la muestra con su .toString
	public void buscarAula() {
		Aula aula = null;
		boolean problema = false;
		String[] listaAulas = controlador.representarAulas();
		if (listaAulas == null) {
			System.out.println("No existen aulas que buscar");
		} else {
			do {
				try {
					aula = controlador.buscarAula(Consola.leerAula());
					problema = false;
				} catch (NullPointerException | IllegalArgumentException e) {
					System.out.println(e.getMessage());
					problema = true;
				}
			} while (problema == true);
			if (aula == null) {
				System.out.println("El aula buscada no existe");
			} else {
				System.out.println(aula.toString());
			}
		}
	}

//Método que crea un array String y ejecuta el método homónimo del controlador. Captura todos los errores posibles y los muestra de existir. Si el retorno es
//nulo (no existe), muestra un mensaje en consecuencia. Si no, recorre el array mostrando los resultados con el .toString de la clase
	public void listarAulas() {
		String[] listaAulas = null;
		try {
			listaAulas = controlador.representarAulas();
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (listaAulas == null) {
			System.out.println("No hay aulas que mostrar");
		} else {
			for (String r : listaAulas) {
				if (r != null) {
					System.out.println(r.toString());
				}
			}
		}
	}

//Método que intenta llamar al método homónimo del controlador pasándo como parámetro el método adecuado de la consola. Captura todos los errores posibles,
//los muestra y se queda dentro del bucle hasta que no existen. Luego nos da un mensaje de realización correcta.
	public void insertarProfesor() {
		boolean problema = false;
		do {
			try {
				controlador.insertarProfesor(Consola.leerProfesor());
				problema = false;
			} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
				System.out.println(e.getMessage());
				problema = true;
			}
		} while (problema == true);
		System.out.println("Profesor insertado correctamente.");
	}

//Método que intenta llamar al método homónimo del controlador pasándo como parámetro el método adecuado de la consola. Captura todos los errores posibles,
//los muestra y se queda dentro del bucle hasta que no existen. Luego nos da un mensaje de realización correcta.
	public void borrarProfesor() {
		String[] listaProfesores = controlador.representarProfesores();
		if (listaProfesores == null) {
			System.out.println("No existen profesores que borrar");
		} else {
			boolean problema = false;
			do {
				try {
					Profesor profesorABorrar = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
					controlador.borrarProfesor(profesorABorrar);
					problema = false;
				} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
					System.out.println(e.getMessage());
					problema = true;
				}
			} while (problema == true);
			System.out.println("Profesor borrado correctamente.");
		}
	}

//Método que crea un Aula y luego ejecuta el método homónimo del controlador pasando como parámetro el método adecuado de la consola. Captura todos los
//errores posibles, los muestra y queda dentro del bucle hasta que no existen. Luego comprueba si el retorno es nulo (no existe) y muestra un mensaje en
//consecuencia. Si el aula existe, la muestra con su .toString
	public void buscarProfesor() {
		Profesor profesor = null;
		boolean problema = false;
		String[] listaProfesores = controlador.representarProfesores();
		if (listaProfesores == null) {
			System.out.println("No existen profesores que buscar");
		} else {
			do {
				try {
					Profesor profesorABuscar = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
					profesor = controlador.buscarProfesor(profesorABuscar);
					problema = false;
				} catch (NullPointerException | IllegalArgumentException e) {
					System.out.println(e.getMessage());
					problema = true;
				}
			} while (problema == true);
			if (profesor == null) {
				System.out.println("El profesor buscado no existe");
			} else {
				System.out.println(profesor.toString());
			}
		}
	}

//Método que crea un array String y ejecuta el método homónimo del controlador. Captura todos los errores posibles y los muestra de existir. Si el retorno es
//nulo (no existe), muestra un mensaje en consecuencia. Si no, recorre el array mostrando los resultados con el .toString de la clase
	public void listarProfesores() {
		String[] listaProfesores = null;
		try {
			listaProfesores = controlador.representarProfesores();
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (listaProfesores == null) {
			System.out.println("No hay profesores que mostrar");
		} else {
			for (String r : listaProfesores) {
				if (r != null) {
					System.out.println(r.toString());
				}
			}
		}
	}

//Método que llama al método homónimo del controlador y le pasa como parámetro el método leerReserva (explicado a continuación), que a su vez recibe como
//parámetro el método adecuado de la consola. Captura todos los posibles errores y continúa en el bucle hasta que no existan, y luego nos da un mensaje de
//realización correcta.
	public void realizarReserva() {
		Reserva reservaARealizar = null;
		try {
			reservaARealizar = leerReserva(Consola.leerProfesor());
			if (controlador.buscarProfesor(reservaARealizar.getProfesor()) == null || controlador.buscarAula(reservaARealizar.getAula()) == null) {
				System.out.println(
						"El profesor y/o aula introducidos no existen. Por favor, creélos antes de intentar realizar una reserva con ellos.");
			} else {
				controlador.realizarReserva(reservaARealizar);
				System.out.println("Reserva realizada correctamente.");
			}
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

//Método que nos va a crear una Reserva para que la utilicemos. Para ello, recibe un Profesor como parámetro y luego va pidiendo los distintos atributos
//que conforman una Reserva mediante sus métodos de Consola. Una vez tiene los datos, crea la Permanencia y la pasa como parámetro junto con los demás
//atributos para crear la Reserva. Captura los errores y continúa en el bucle hasta que no existan y por último retorna una copia de la Reserva
	private Reserva leerReserva(Profesor profesor) {
		boolean problema = false;
		Reserva reserva = null;
		Aula aula = null;
		Tramo tramo = null;
		LocalDate dia = null;
		Permanencia permanencia = null;
		do {
			try {
				aula = Consola.leerAula();
				tramo = Consola.leerTramo();
				dia = Consola.leerDia();
				permanencia = new Permanencia(dia, tramo);
				reserva = new Reserva(profesor, aula, permanencia);
				problema = false;
			} catch (NullPointerException | IllegalArgumentException e) {
				System.out.println(e.getMessage());
				problema = true;
			}
		} while (problema == true);
		return new Reserva(reserva);
	}

//Método que también hace uso del leerReserva anterior para generar una reserva que pasaremos como parámetro al método homónimo del controlador. Dado que
//solo nos interesa la Permanencia y el Aula para encontrar coincidencias entre las reservas, el profesor lo creamos haciendo uso de su constructor de dos
//parámetros y con los atributos estáticos de la clase Vista. Capturamos los errores y continuamos dentro del bucle hasta que no existen y finalmente
//devolvemos un mensaje de realización correcta.
	public void anularReserva() {
		boolean problema = false;
		Reserva reserva = null;
		Profesor profesor = new Profesor(NOMBRE_VALIDO, CORREO_VALIDO);
		String[] listaReservas = controlador.representarReservas();
		if (listaReservas == null) {
			System.out.println("No existen reserrvas que borrar");
		} else {

			do {
				try {
					reserva = leerReserva(profesor);
					controlador.anularReserva(reserva);
					problema = false;
				} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
					System.out.println(e.getMessage());
					problema = true;
				}
			} while (problema == true);
			System.out.println("Reserva anulada correctamente.");
		}
	}

//Método que crea un array String y ejecuta el método homónimo del controlador. Captura todos los errores posibles y los muestra de existir. Si el retorno es
//nulo (no existe), muestra un mensaje en consecuencia. Si no, recorre el array mostrando los resultados con el .toString de la clase
	public void listarReservas() {
		String[] listaReservas = null;
		try {
			listaReservas = controlador.representarReservas();
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (listaReservas == null) {
			System.out.println("No hay reservas que mostrar");
		} else {
			for (String r : listaReservas) {
				if (r != null) {
					System.out.println(r.toString());
				}
			}
		}
	}

//Método similar a listaReservas pero solo para un parámetro dado, que obtendremos mediante el método adecuado de la consola
	public void listarReservasAula() {
		Reserva[] listaReservasAula = null;
		try {
			listaReservasAula = controlador.getReservasAula(Consola.leerAula());
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (listaReservasAula == null) {
			System.out.println(ERROR);
		} else {
			for (Reserva r : listaReservasAula) {
				if (r != null) {
					System.out.println(r.toString());
				}
			}
		}
	}

//Método similar a listaReservas pero solo para un parámetro dado, que obtendremos mediante el método adecuado de la consola
	public void listarReservasProfesor() {
		Reserva[] listaReservasProfesor = null;
		try {
			Profesor profesorABuscar = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
			listaReservasProfesor = controlador.getReservasProfesor(profesorABuscar);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (listaReservasProfesor == null) {
			System.out.println(ERROR);
		} else {
			for (Reserva r : listaReservasProfesor) {
				if (r != null) {
					System.out.println(r.toString());
				}
			}
		}
	}

//Método similar a listaReservas pero solo para un parámetro dado. En este caso, tendremos que pedir el día y el tramo para crear una Permanencia que
//pasar como parámetro al método del controlador.
	public void listarReservasPermanencia() {
		Reserva[] listaReservasPermanencia = null;
		Permanencia permanencia = null;
		Tramo tramo = null;
		LocalDate dia = null;
		try {
			tramo = Consola.leerTramo();
			dia = Consola.leerDia();
			permanencia = new Permanencia(dia, tramo);
			listaReservasPermanencia = controlador.getReservasPermanencia(permanencia);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (listaReservasPermanencia == null) {
			System.out.println(ERROR);
		} else {
			for (Reserva r : listaReservasPermanencia) {
				if (r != null) {
					System.out.println(r.toString());
				}
			}
		}
	}

//Método que pide mediante consola un tramo y un día para crear una permanencia, luego pide un aula mediante consola y pasa el aula y la permanencia como
//parámetros para el método homónimo del controlador, que devolverá true si el aula está disponible o false de lo contrario. Se retornan mensajes adecuados.
	public void consultarDisponibilidad() {
		boolean disponible = true;
		Permanencia permanencia = null;
		Tramo tramo = null;
		LocalDate dia = null;
		Aula aula=null;
		try {
			tramo = Consola.leerTramo();
			dia = Consola.leerDia();
			permanencia = new Permanencia(dia, tramo);
			aula=Consola.leerAula();
			disponible = controlador.consultarDisponibilidad(aula, permanencia);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		if (controlador.buscarAula(aula)==null) {
			System.out.println("El aula introducida no existe");
		}
		else if (disponible == true) {
			System.out.println("El aula se encuentra disponible para el tramo y día introducidos");
		} else {
			System.out.println("El aula NO se encuentra disponible para el tramo y día introducidos");
		}
	}

}
