package expendedora.controller;

import java.net.URL;
import java.util.ResourceBundle;

import expendedora.model.CambioInsuficienteException;
import expendedora.model.CreditoInsuficienteException;
import expendedora.model.Expendedora;
import expendedora.model.StockInsuficienteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLExpendedoraController implements Initializable {
	Expendedora e;

	@FXML
	private Button btn1;

	@FXML
	private Button btn2;

	@FXML
	private Button btn3;

	@FXML
	private Button btn4;

	@FXML
	private Button btn5;

	@FXML
	private Button btn6;

	@FXML
	private Button btn7;

	@FXML
	private Button btn8;

	@FXML
	private TextField txtCredito;

	@FXML
	private TextField txtCambioDisponible;

	@FXML
	private TextField txtImporteVentas;

	@FXML
	private Button btn5€;

	@FXML
	private Button btn2€;

	@FXML
	private Button btn1€;

	@FXML
	private Button btn05€;

	@FXML
	private Button btn02€;

	@FXML
	private Button btn01€;

	@FXML
	private Label lblCompra;

	@FXML
	private Label lblDevuelve;

	@FXML
	private Button btnDevolver;

	@FXML
	void onActionBtn1(ActionEvent event) {
		seCompraElProducto(0);
		mostrarExpendedora();
	}

	@FXML
	void onActionBtn2(ActionEvent event) {
		seCompraElProducto(1);
		mostrarExpendedora();
	}

	@FXML
	void onActionBtn3(ActionEvent event) {
		seCompraElProducto(2);
		mostrarExpendedora();
	}

	@FXML
	void onActionBtn4(ActionEvent event) {
		seCompraElProducto(3);
		mostrarExpendedora();
	}

	@FXML
	void onActionBtn5(ActionEvent event) {
		seCompraElProducto(4);
		mostrarExpendedora();
	}

	@FXML
	void onActionBtn6(ActionEvent event) {
		seCompraElProducto(5);
		mostrarExpendedora();
	}

	@FXML
	void onActionBtn7(ActionEvent event) {
		seCompraElProducto(6);
		mostrarExpendedora();
	}

	@FXML
	void onActionBtn8(ActionEvent event) {
		seCompraElProducto(7);
		mostrarExpendedora();
	}

	@FXML
	void onActionDevolver(ActionEvent event) {
		double devolver = e.devolverCredito();
		if (devolver != 0) {
			lblCompra.setText("");
			lblDevuelve.setText("Se le han devuelto: " + devolver + "€");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atención");
			alert.setHeaderText("Devolución correcta");
			alert.setContentText("Se le han devuelto: " + devolver + "€");
			alert.showAndWait();
		} else {
			lblCompra.setText("");
			lblDevuelve.setText("");
		}
		mostrarExpendedora();
	}

	@FXML
	void onActionSumar5(ActionEvent event) {
		e.anyadirDinero(5);
		mostrarExpendedora();
	}

	@FXML
	void onActionSumar2(ActionEvent event) {
		e.anyadirDinero(2);
		mostrarExpendedora();
	}

	@FXML
	void onActionSumar1(ActionEvent event) {
		e.anyadirDinero(1);
		mostrarExpendedora();
	}

	@FXML
	void onActionSumar05(ActionEvent event) {
		e.anyadirDinero(0.5);
		mostrarExpendedora();
	}

	@FXML
	void onActionSumar02(ActionEvent event) {
		e.anyadirDinero(0.2);
		mostrarExpendedora();
	}

	@FXML
	void onActionSumar01(ActionEvent event) {
		e.anyadirDinero(0.1);
		mostrarExpendedora();
	}

	public FXMLExpendedoraController() {
		String[] nombres = { "Coca cola", "Bezoya", "Pepsi", "Lay's", "Oreo", "Kit kat", "Mountain Dew", "Sandwich" };
		double[] precios = { 1.5, 1.0, 1.0, 1.5, 1.20, 1.3, 1.4, 1.10 };
		int[] stock = { 6, 14, 16, 10, 5, 7, 9, 7 };
		e = new Expendedora(20, nombres, precios, stock);
	}

	private void mostrarExpendedora() {

		txtCredito.setText(String.valueOf(e.getCredito()));
		txtCambioDisponible.setText(String.valueOf(e.getCambioDisponible()));
		txtImporteVentas.setText(String.valueOf(e.getImporteVentas()));

		btn1.setText(e.getNombre(0) + "\n" + e.getPrecio(0) + " €\n" + e.getStock(0) + " uds.");
		btn2.setText(String.valueOf(e.getNombre(1)) + "\n" + String.valueOf(e.getPrecio(1)) + " €\n"
				+ String.valueOf(e.getStock(1)) + " uds.");
		btn3.setText(String.valueOf(e.getNombre(2)) + "\n" + String.valueOf(e.getPrecio(2)) + " €\n"
				+ String.valueOf(e.getStock(2)) + " uds.");
		btn4.setText(String.valueOf(e.getNombre(3)) + "\n" + String.valueOf(e.getPrecio(3)) + " €\n"
				+ String.valueOf(e.getStock(3)) + " uds.");
		btn5.setText(String.valueOf(e.getNombre(4)) + "\n" + String.valueOf(e.getPrecio(4)) + " €\n"
				+ String.valueOf(e.getStock(4)) + " uds.");
		btn6.setText(String.valueOf(e.getNombre(5)) + "\n" + String.valueOf(e.getPrecio(5)) + " €\n"
				+ String.valueOf(e.getStock(5)) + " uds.");
		btn7.setText(String.valueOf(e.getNombre(6)) + "\n" + String.valueOf(e.getPrecio(6)) + " €\n"
				+ String.valueOf(e.getStock(6)) + " uds.");
		btn8.setText(String.valueOf(e.getNombre(7)) + "\n" + String.valueOf(e.getPrecio(7)) + " €\n"
				+ String.valueOf(e.getStock(7)) + " uds.");
	}

	private void seCompraElProducto(int i) {
		Alert alert = new Alert(AlertType.ERROR);
		try {
			double devolver = e.comprar(i);
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atención");
			alert.setHeaderText("Compra correcta");
			lblCompra.setText("Se ha comprado: " + e.getNombre(i));
			if (devolver != 0) {
				alert.setContentText("Se ha comprado: " + e.getNombre(i) + "\nSe le han devuelto: " + devolver + "€");
				lblDevuelve.setText("Se le han devuelto: " + devolver + "€");
			} else {
				lblDevuelve.setText("");
				alert.setContentText("Se ha comprado: " + e.getNombre(i));
			}
			alert.showAndWait();
		} catch (CreditoInsuficienteException ex) {
			lblCompra.setText("");
			lblDevuelve.setText("");
			alert.setTitle("Alerta");
			alert.setHeaderText("Error en la compra");
			alert.setContentText("No hay suficiente crédito en la expendedora");
			alert.showAndWait();
		} catch (StockInsuficienteException ex) {
			lblCompra.setText("");
			lblDevuelve.setText("");
			alert.setTitle("Alerta");
			alert.setHeaderText("Error en la compra");
			alert.setContentText("No hay suficiente stock del producto que se desea comprar");
			alert.showAndWait();
		} catch (CambioInsuficienteException ex) {
			lblCompra.setText("");
			lblDevuelve.setText("");
			alert.setTitle("Alerta");
			alert.setHeaderText("Error en la compra");
			alert.setContentText("No hay suficiente cambio en la expendedora");
			alert.showAndWait();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mostrarExpendedora();
	}

}
