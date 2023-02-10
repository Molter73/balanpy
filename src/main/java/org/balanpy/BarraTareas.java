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

	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;

	}

	BalanpyScreen balanpyScreen = new BalanpyScreen();
	String currentScene = BalanpyScreen.getSceneNameS();

	@FXML
	VBox perfilUsuario;
	@FXML
	VBox portada;
	@FXML
	VBox pulsaciones;
	@FXML
	VBox temperatura;
	@FXML
	VBox veterinario;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void PortadaAplicacion(MouseEvent event) throws Exception {

		try {

			if (currentScene != "/PortadaAplicacion.fxml") {

				BalanpyScreen.loadScene(stage, "/PortadaAplicacion.fxml", currentScene);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	public void Pulsaciones(MouseEvent event) throws Exception {

		try {

			if (currentScene != "/Pulsaciones.fxml") {

				BalanpyScreen.loadScene(stage, "/Pulsaciones.fxml", currentScene);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	public void Temperaturas(MouseEvent event) throws Exception {

		try {

			if (currentScene != "/Clima.fxml") {

				BalanpyScreen.loadScene(stage, "/Clima.fxml", currentScene);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	public void PerfilVeterinario(MouseEvent event) throws Exception {

		try {

			if (currentScene != "/PerfilVeterinario.fxml") {

				BalanpyScreen.loadScene(stage, "/PerfilVeterinario.fxml", currentScene);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	public void PerfilUsuario(MouseEvent event) throws Exception {

		try {

			if (currentScene != "/PerfilUsuario.fxml") {

				BalanpyScreen.loadScene(stage, "/PerfilUsuario.fxml", currentScene);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
