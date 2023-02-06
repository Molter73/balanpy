package org.balanpy;

import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Inicio {

	public Inicio() {
	}

	public void crearUsuario(MouseEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		BalanpyScreen.loadScene(stage, "/EditarPerfilUsuario.fxml");
	}

	public void HandleDone(MouseEvent event) throws Exception {

		System.out.println("Está funcionando crack");

	}

}
