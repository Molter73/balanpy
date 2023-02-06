package org.balanpy;

import java.awt.Label;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class PerfilUsuario {
	@FXML
	private Label nombre;

	@FXML
	private Label edad;

	@FXML
	private Label telefono;

	@FXML
	private Label email;

	@FXML
	private Label sexo;

	@FXML
	private Label direccion;

	public void HandleDone(MouseEvent event) throws Exception {

		System.out.println("Está funcionando crack");

	}
}
