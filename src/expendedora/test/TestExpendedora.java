package expendedora.test;

import expendedora.model.CambioInsuficienteException;
import expendedora.model.CreditoInsuficienteException;
import expendedora.model.Expendedora;
import expendedora.model.StockInsuficienteException;

public class TestExpendedora {
	public static void main(String[] args) {
		testBasico();
		testIntroducirDinero();
		testCompraCorrecta();
		testCompraSinStock();
		testCompraSinCambio();
		testCompraSinCredito();
		

	}

	public static void testBasico() {
		// Constructor y getter
		System.out.println("========== Constructor y getter .......");
		boolean ok = true;

		String[] nombres = { "Agua", "Cola" };
		double[] precios = { 0.9, 1.25 };
		int[] stocks = { 2, 3 };
		Expendedora e = new Expendedora(10, nombres, precios, stocks);
		System.out.println("\nSe crea una máquina con 10 euros de cambio y dos productos:");
		System.out.println("Agua, precio: 0.9, stock 2 uds");
		System.out.println("Cola, precio: 1.25, stock 3 uds");
		// Cambio
		if (e.getCambioDisponible() != 10) {
			System.out.println("\nError en getCambioDisponible o en el constructor");
			System.out.println("Cambio disponible esperado: " + 10);
			System.out.println("Cambio disponible obtenido: " + e.getCambioDisponible());
			ok = false;
		}
		// Credito
		if (e.getCredito() != 0) {
			System.out.println("\nError en getCredito o en el constructor");
			System.out.println("Credito esperado: " + 0);
			System.out.println("Credito obtenido: " + e.getCredito());
			ok = false;
		}
		// Importe ventas
		if (e.getImporteVentas() != 0) {
			System.out.println("\nError en getImporteVentas o en el constructor");
			System.out.println("Importe ventas esperado: " + 0);
			System.out.println("Importe ventas obtenido: " + e.getImporteVentas());
			ok = false;
		}

		// Nombre producto
		if (!e.getNombre(0).equals("Agua")) {
			System.out.println("\nError en getNombre o en el constructor");
			System.out.println("Nombre producto esperado: " + "Agua");
			System.out.println("Nombre producto obtenido: " + e.getNombre(0));
			ok = false;
		}

		// Precio producto
		if (e.getPrecio(0) != 0.9) {
			System.out.println("\nError en getPrecio o en el constructor");
			System.out.println("Precio producto esperado: " + 0.9);
			System.out.println("Precio producto obtenido: " + e.getPrecio(0));
			ok = false;
		}

		// Stock producto
		if (e.getStock(0) != 2) {
			System.out.println("\nError en getStock o en el constructor");
			System.out.println("Stock producto esperado: " + 2);
			System.out.println("Stock producto obtenido: " + e.getStock(0));
			ok = false;
		}

		// Numero productos
		if (e.getNumeroProductos() != 2) {
			System.out.println("\nError en getNumeroProductos");
			System.out.println("Numero productos esperado: " + 2);
			System.out.println("Numero productos obtenido: " + e.getNumeroProductos());
			ok = false;
		}

		// Los arrays de productos deben ser una copia de los parámetros
		nombres[0] = "200";
		precios[0] = 200;
		stocks[0] = 200;
		if (e.getNombre(0).equals("200") || e.getPrecio(0) == 200 || e.getStock(0) == 200) {
			System.out.println("\nError en el constructor");
			System.out.println(
					"Los arrays nombreProductos, precioProductos y stockProductos deben ser una copia de los que se reciben como parámetro en el constructor");
			ok = false;
		}

		boolean lanza = false;
		try {
			Expendedora e2 = new Expendedora(1, new String[] { "a" }, new double[] { 1, 2 }, new int[] { 2, 3, 4 });
		} catch (IllegalArgumentException ex) {
			lanza = true;
		}
		if (!lanza) {
			System.out.println("\nError en el constructor");
			System.out.println(
					"Se debe lanzar IllegalArgumentException si los arrays que recibe el constructor no tienen el mismo tamanyo");
			ok = false;
		}

		if (ok) {
			System.out.println("\nCORRECTO!!!");
		}
		System.out.println("============================");
	}

	public static void testIntroducirDinero() {
		// Constructor y getter
		System.out.println("\n\n========== Introducir dinero .......");
		System.out.println("Se crea una expendedora y se anyade 1, 5 y 5 euros. El credito resultante deberá ser 6");
		boolean ok = true;

		String[] nombres = { "Agua", "Cola" };
		double[] precios = { 0.9, 1.25 };
		int[] stocks = { 2, 3 };
		Expendedora e = new Expendedora(10, nombres, precios, stocks);

		// Comprobar que acumula el credito
		e.anyadirDinero(1);
		if (e.getCredito() != 1) {
			System.out.println("\nError en anyadirCredito o en getCredito");
			System.out.println("Vale " + e.getCredito());
			System.out.println("Deberia valer " + 1);
			ok = false;
		}
		if (ok) {
			e.anyadirDinero(5);
			if (e.getCredito() != 6) {
				System.out.println("\nError en anyadirCredito o en getCredito");
				System.out.println("Vale " + e.getCredito());
				System.out.println("Deberia valer " + 6);
				ok = false;
			}
		}
		// Comprobar importe máximo
		if (ok) {
			e.anyadirDinero(5);
			if (e.getCredito() != 6) {
				System.out.println("\nError al intentar superar el credito maximo");
				System.out.println("Vale " + e.getCredito());
				System.out.println("Deberia valer " + 6);
				ok = false;
			}
		}
		// Comprobar que se usan dos decimales
		if (ok) {
			e.anyadirDinero(0.451);
			if (e.getCredito() > 6.45) {
				System.out.println("\nError, el credito se debe mantener con dos decimales");
				System.out.println("Vale " + e.getCredito());
				System.out.println("Deberia valer " + 6);
				ok = false;
			}
		}

		if (ok) {
			System.out.println("\nCORRECTO!!!");
		}
	}

	public static void testCompraCorrecta() {
		// Venta correcta
		Expendedora e = new Expendedora(3, new String[] { "a" }, new double[] { 0.7 }, new int[] { 1 });
		e.anyadirDinero(2);
		boolean ok = true;
		System.out.println("\n========== Realizar compra posible .......");
		try {

			e.comprar(0);
			if (e.getCambioDisponible() != 1.7) {
				System.out.println("\nEl cambio disponible tras la compra no es el correcto");
				System.out.println("Vale " + e.getCambioDisponible());
				System.out.println("Deberia valer " + 1.7);
				ok = false;
			}
			if (e.getStock(0) != 0) {
				System.out.println("\nEl stock disponible tras la compra no es el correcto");
				System.out.println("Vale " + e.getStock(0));
				System.out.println("Deberia valer " + 0);
				ok = false;
			}
			if (e.getImporteVentas() != 0.7) {
				System.out.println("\nEl importe de ventas tras la compra no es el correcto");
				System.out.println("Vale " + e.getImporteVentas());
				System.out.println("Deberia valer " + 0.7);
				ok = false;
			}
			if (ok) {
				System.out.println("\nCORRECTO!!!");
			}
		} catch (CreditoInsuficienteException e1) {
			System.out.println("\nHay credito y la expendedora dice que no");
		} catch (CambioInsuficienteException e1) {
			System.out.println("\nHay cambio y la expendedora dice que no");
		} catch (StockInsuficienteException e1) {
			System.out.println("\nHay stock y la expendedora dice que no");
		}
		System.out.println("============================");
	}

	public static void testCompraSinStock() {
		// Venta sin stock
		Expendedora e = new Expendedora(3, new String[] { "a" }, new double[] { 0.7 }, new int[] { 0 });
		e.anyadirDinero(1);
		boolean ok = true;
		System.out.println("\n==========Realizar compra cuando no hay stock .......");
		try {
			e.comprar(0);
			ok = false;
			System.out.println("\nErrorSe ha conseguido realizar la compra sin haber stock");
		} catch (StockInsuficienteException e1) {
		} catch (Exception e1) {
			System.out.println("\nErrorSe ha producido una excepcion incorrecta");
			System.out.println(e1);
			ok = false;
		}
		if (ok) {
			System.out.println("\nCORRECTO!!!");
		}
		System.out.println("============================");
	}

	public static void testCompraSinCambio() {
		// Venta sin cambio
		Expendedora e = new Expendedora(1, new String[] { "a" }, new double[] { 0.7 }, new int[] { 1 });
		e.anyadirDinero(2);
		boolean ok = true;
		System.out.println("\n==========Realizar compra cuando no hay cambio suficiente.......");
		try {
			e.comprar(0);
			ok = false;
			System.out.println("\nErrorSe ha conseguido realizar la compra sin haber cambio suficiente");
		} catch (CambioInsuficienteException e1) {
		} catch (Exception e1) {
			System.out.println("\nErrorSe ha producido una excepcion incorrecta");
			ok = false;
		}
		if (ok) {
			System.out.println("\nCORRECTO!!!");
		}
		System.out.println("============================");
	}

	public static void testCompraSinCredito() {
		// Venta sin cambio
		Expendedora e = new Expendedora(3, new String[] { "a" }, new double[] { 0.7 }, new int[] { 1 });
		boolean ok = true;
		System.out.println("\n==========Realizar compra cuando no hay credito suficiente.......");
		try {
			e.comprar(0);
			ok = false;
			System.out.println("\nErrorSe ha conseguido realizar la compra sin haber credito suficiente");
		} catch (CreditoInsuficienteException e1) {
		} catch (Exception e1) {
			System.out.println("\nErrorSe ha producido una excepcion incorrecta");
			ok = false;
		}
		if (ok) {
			System.out.println("\nCORRECTO!!!");
		}
		System.out.println("============================");
	}
}
