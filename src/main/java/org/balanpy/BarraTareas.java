package org.balanpy;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BarraTareas implements Initializable {

	String currentScene;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		currentScene = BalanpyScreen.getcurrentScreen();
	}

	private void switchScene(MouseEvent event, String scene) {

		try {

			if (currentScene != scene) {
				Stage stage = (Stage) ((VBox) event.getSource()).getScene().getWindow();
				BalanpyScreen.loadScene(stage, scene, currentScene);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	public void portadaAplicacion(MouseEvent event) throws Exception {

		switchScene(event, "/PortadaAplicacion.fxml");

	}

////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	public void pulsaciones(MouseEvent event) throws Exception {

		switchScene(event, "/Pulsaciones.fxml");

	}

////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	public void temperaturas(MouseEvent event) throws Exception {

		switchScene(event, "/Clima.fxml");

	}

////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	public void perfilVeterinario(MouseEvent event) throws Exception {

		switchScene(event, "/PerfilVeterinario.fxml");

	}

////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	public void perfilUsuario(MouseEvent event) throws Exception {

		switchScene(event, "/PerfilUsuario.fxml");

	}

}
