package org.balanpy;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PortadaAplicacion implements Initializable {

	private Stage stage;

	private static final String SCREEN = "/PortadaAplicacion.fxml";

	public void setStage(Stage stage) {

		this.stage = stage;

	}

	BalanpyScreen balanpyScreen = new BalanpyScreen();

	@FXML
	VBox temperaturaExterna;

	@FXML
	VBox higiene;

	@FXML
	VBox vacunas;

	@FXML
	VBox registroMascota;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void temperaturaExterna(MouseEvent event) throws IOException {

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		BalanpyScreen.loadScene(stage, "/temperaturaExterna.fxml", SCREEN);
	}

	public void higiene(MouseEvent event) throws IOException {

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		BalanpyScreen.loadScene(stage, "/higiene.fxml", SCREEN);
	}

	public void vacunas(MouseEvent event) throws IOException {

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		BalanpyScreen.loadScene(stage, "/vacunas.fxml", SCREEN);
	}

	public void registroMascota(MouseEvent event) throws IOException {

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		BalanpyScreen.loadScene(stage, "/ConfiguracionMascota.fxml", SCREEN);
	}

	public void perfilMascota(MouseEvent event) throws IOException {

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		BalanpyScreen.loadScene(stage, "/PerfilMascota.fxml", SCREEN);
	}

}