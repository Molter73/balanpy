package org.balanpy;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditarPerfilUsuario {
	private Usuario usuario = UsuarioImpl.getInstance();

	@FXML
	TextField nombre;

	@FXML
	TextField telefono;

	@FXML
	TextField edad;

	@FXML
	TextField email;

	@FXML
	ChoiceBox<String> sexo;

	@FXML
	TextField direccion;

	public void initialize() {
		sexo.getItems().add("Masculino");
		sexo.getItems().add("Femenino");
	}

	public void guardar(ActionEvent event) throws StreamReadException, DatabindException, IOException {
		try {
			usuario.setNombre(nombre.getText());
			usuario.setEmail(email.getText());
			usuario.setSexo(sexo.getValue());
			usuario.setEdad(Integer.parseInt(edad.getText()));
			usuario.setTelefono(telefono.getText());
			usuario.setDireccion(direccion.getText());

			usuario.save();

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			BalanpyScreen.loadScene(stage, "/PortadaAplicacion.fxml");
		} catch (Exception e) {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			BalanpyScreen.quickPopUp(stage, e.getMessage());
			usuario = usuario.reload();
		}
	}

	public void HandleDone(MouseEvent event) throws Exception {

		System.out.println("Está funcionando crack");

	}

}
