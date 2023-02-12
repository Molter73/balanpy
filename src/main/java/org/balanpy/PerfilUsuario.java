package org.balanpy;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PerfilUsuario {
	private static final String SCREEN = "/PerfilUsuario.fxml";

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

	public void inicialize() {

		Usuario usuario = UsuarioImpl.getInstance();

		nombre.setText(usuario.getNombre());
		edad.setText(String.valueOf(usuario.getEdad()));
		telefono.setText(usuario.getTelefono());
		email.setText(usuario.getEmail());
		sexo.setText(usuario.getSexo());
		direccion.setText(usuario.getDireccion());

	}

	public void editarPerfil(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		BalanpyScreen.loadScene(stage, "/EditarPerfilUsuario.fxml", SCREEN);
	}

	public void HandleDone(MouseEvent event) throws Exception {

		System.out.println("Está funcionando crack");

	}
}
