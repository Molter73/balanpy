package org.balanpy;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	static ArrayList<Integer> temperaturas = new ArrayList<Integer>();

	@FXML
	private GridPane gridPane;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Usuario usuario = UsuarioImpl.getInstance();
		if (usuario.isValid()) {
			BalanpyScreen.loadScene(primaryStage, "/PortadaAplicacion.fxml");
		} else {
			BalanpyScreen.loadScene(primaryStage, "/PantallaInicio.fxml");
		}
	}

	// -----------------------------------------------------------------------------------------

	public void HandleDone(ActionEvent event) throws Exception {
		for (Node node : gridPane.getChildren()) {
			if (node instanceof TextField) {
				TextField tf = (TextField) node;
				String text = tf.getText();

				if (text.isEmpty()) {
					continue;
				}

				Integer temp = Integer.parseInt(text);
				temperaturas.add(temp);
			}
		}

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = BalanpyScreen.loadScene(stage, "/MainMenu.fxml");

		Label temperaturaMaxima = (Label) scene.lookup("#temperaturaMaxima");
		temperaturaMaxima.setText(Balanpy.getTemperaturaMaxima(temperaturas).toString());

		Label temperaturaBaja = (Label) scene.lookup("#temperaturaBaja");
		temperaturaBaja.setText(Balanpy.getTemperaturaBaja(temperaturas).toString());

		Label temperaturaAlta = (Label) scene.lookup("#temperaturaAlta");
		temperaturaAlta.setText(Balanpy.getTemperaturaAlta(temperaturas).toString());

		Label temperaturaMedia = (Label) scene.lookup("#temperaturaMedia");
		temperaturaMedia.setText(Balanpy.getTemperaturaMedia(temperaturas).toString());
	}

	// -----------------------------------------------------------------------------------------

	public static void main(String[] args) {
		launch();
	}
}