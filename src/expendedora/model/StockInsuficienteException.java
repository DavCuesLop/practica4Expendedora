package expendedora.model;

public class StockInsuficienteException extends Exception {
	public StockInsuficienteException() {
		super();
		
	}

	public StockInsuficienteException(String mensaje) {
		super(mensaje);
		
	}
}
