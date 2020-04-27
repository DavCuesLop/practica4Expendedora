package expendedora.test;

import java.util.InputMismatchException;
import java.util.Scanner;

import expendedora.model.CambioInsuficienteException;
import expendedora.model.CreditoInsuficienteException;
import expendedora.model.Expendedora;
import expendedora.model.StockInsuficienteException;

public class InterfazExpendedoraTexto {
	static Scanner tec = new Scanner(System.in);

	public static void main(String[] args) {
		// Creamos la expendedora con su estado inicial
		Expendedora e = new Expendedora(10, new String[] {"Agua 1L","Cola","Naraja" }, new double[] { 0.9, 1, 1.2}, new int[] { 2, 3, 4 });
		
		realizarSimulacion(e);
	}
	public static void realizarSimulacion(Expendedora e){
		// Opciones del menu
		String[] opciones = { "1.- Introducir dinero", "2.- Solicitar devolucion", "3.- Comprar producto",
				"0.- Salir" };

		// Mostrar repetidamente el menu y actuar segun lo elegido por el
		// usuario
		
		int opcionElegida;
		do {
			System.out.println(e);
			System.out.println();
			opcionElegida = menu(opciones);
			switch (opcionElegida) {
			case 0:
				System.out.println("Fin del programa");
				break;
			case 1: 
				introducirDinero(e);
				break;
			case 2:
				solicitarDevolucion(e);
				break;
			case 3: 
				comprarProducto(e);
				break;
			}
			
		} while (opcionElegida != 0);
	}

	/**
	 * Muestra por pantalla un menú con las opciones indicadas en un array Si el
	 * usuario elije una opcion NO valida el menu se vuelve a mostrar. Cuando el
	 * usuario elije una opcion valida, el metodo la devuelve. Las opciones
	 * validas iran de la 0 al numero de opciones - 1
	 * 
	 * @param opciones
	 *            Array de String con las opciones que se mostraran
	 * @return la opcion elegida por el usuario
	 */
	public static int menu(String[] opciones) {
		int opcion;
		do {
			// Mostramos el menu:
			for (int i = 0; i < opciones.length; i++) {
				System.out.println(opciones[i]);
			}

			opcion = leerEntero();
			if (opcion < 0 || opcion >= opciones.length)
				System.out.println("Opcion incorrecta");

		} while (opcion < 0 || opcion >= opciones.length);
		return opcion;
	}

	/**
	 * Lee un entero y lo devuelve. El método no termina hasta que consigue leer
	 * un entero correctamente.
	 * 
	 * @return el entero leido
	 */

	public static int leerEntero() {
		int num = 0;
		boolean ok = false;
		do {
			try {
				num = tec.nextInt();
				tec.nextLine(); // vacía buffer
				ok = true;
			} catch (InputMismatchException e) {
				System.out.println("Debe introducir un entero");
				tec.nextLine(); // vacía buffer
			}
		} while (!ok);
		return num;
	}

	/**
	 * Lee un numero real y lo devuelve. El método no termina hasta que consigue
	 * leer un nº real correctamente.
	 * 
	 * @return el real leido
	 */
	public static double leerReal() {
		double num = 0;
		boolean ok = false;
		do {
			try {
				num = tec.nextDouble();
				tec.nextLine(); // vacía buffer
				ok = true;
			} catch (InputMismatchException e) {
				System.out.println("Debe introducir un numero");
				tec.nextLine(); // vacía buffer
			}
		} while (!ok);
		return num;
	}
	
	public static void introducirDinero(Expendedora e){
		System.out.println("Importe: ");
		double importe = leerReal();
		e.anyadirDinero(importe);
	}
	public static void solicitarDevolucion(Expendedora e){
		double importe = e.devolverCredito();
		System.out.println("\nLe devolvemos su dinero: " + importe + " euros\n");
	}
	public static void comprarProducto(Expendedora e){
		String[] productos = new String[e.getNumeroProductos()];
		for(int i = 0; i < e.getNumeroProductos(); i++){
			productos[i] = i + ".- " + e.getNombre(i);
		}
		System.out.println("Productos: ");
		int producto = menu(productos);
		
		try{
			e.comprar(producto);
		} catch (CreditoInsuficienteException ex){
			System.out.println("\n"+ex.getMessage() + "\n");
		}catch (StockInsuficienteException ex){
			System.out.println("\n"+ex.getMessage() + "\n");
		}catch (CambioInsuficienteException ex){
			System.out.println("\n"+ex.getMessage() + "\n");
		}
		
	}
}
