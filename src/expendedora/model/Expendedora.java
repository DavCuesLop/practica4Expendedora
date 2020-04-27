package expendedora.model;

public class Expendedora {

	final static double CREDITOMAXIMO = 10;
	private double credito;
	private double cambioDisponible;
	private double importeVentas;
	private String[] nombreProductos;
	private double[] precioProductos;
	private int[] stockProductos;

	public Expendedora(double cambioDisponible, String[] nombreProductos, double[] precioProductos,
			int[] stockProductos) {
		this.credito = 0;
		this.importeVentas = 0;
		this.cambioDisponible = cambioDisponible;
		this.nombreProductos = new String[nombreProductos.length];
		for (int i = 0; i < nombreProductos.length; i++) {
			this.nombreProductos[i] = nombreProductos[i];
		}
		this.precioProductos = new double[precioProductos.length];
		for (int i = 0; i < precioProductos.length; i++) {
			this.precioProductos[i] = precioProductos[i];
		}
		this.stockProductos = new int[stockProductos.length];
		for (int i = 0; i < stockProductos.length; i++) {
			this.stockProductos[i] = stockProductos[i];
		}

		if (nombreProductos.length != precioProductos.length && precioProductos.length != stockProductos.length) {
			throw new IllegalArgumentException();
		}

	}

	public double getCredito() {
		return credito;
	}

	public double getCambioDisponible() {

		return cambioDisponible = Math.round(cambioDisponible * 100) / 100d;
	}

	public double getImporteVentas() {
		return importeVentas = Math.round(importeVentas * 100) / 100d;
	}

	public int getNumeroProductos() {
		return this.nombreProductos.length;
	}

	public String getNombre(int i) {
		return nombreProductos[i];
	}

	public double getPrecio(int i) {
		return precioProductos[i];
	}

	public int getStock(int i) {
		return stockProductos[i];
	}

	public String toString() {

		System.out.println("Credito		 : " + credito + "\nCambio		 : " + cambioDisponible
				+ "\nImporte de ventas: " + importeVentas + "\nProductos: " + getNumeroProductos());
		for (int i = 0; i < getNumeroProductos(); i++) {
			System.out.println("	" + getNombre(i) + " - " + getPrecio(i) + " euros - " + getStock(i) + " uds.");
		}
		return "";
	}

	public void anyadirDinero(double importe) {

		if ((importe + credito) > CREDITOMAXIMO) {

		} else {
			credito = importe + credito;
		}
		credito = Math.round(credito * 100) / 100d;
	}

	public double devolverCredito() {
		double devolver = getCredito();
		credito = 0;
		return devolver;
	}

	public double comprar(int i)
			throws CreditoInsuficienteException, CambioInsuficienteException, StockInsuficienteException {
		double devolver = getCredito() - getPrecio(i);
		devolver = Math.round(devolver * 100) / 100d;
		if (getCredito() >= getPrecio(i)) {
			if (getCambioDisponible() < devolver) {
				throw new CambioInsuficienteException("No hay suficiente cambio");
			} else if (getStock(i) == 0) {
				throw new StockInsuficienteException("No hay suficiente stock del producto");
			}
			stockProductos[i]--;
			importeVentas = importeVentas + getPrecio(i);
			cambioDisponible = cambioDisponible - devolver;
			cambioDisponible = Math.round(cambioDisponible * 100) / 100d;
			credito = 0;
		} else {
			throw new CreditoInsuficienteException("No hay suficiente credito");
		}

		return devolver;
	}

}
