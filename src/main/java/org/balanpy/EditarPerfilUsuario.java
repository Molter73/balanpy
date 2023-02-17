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
	private final static String SCREEN = "/EditarPerfilUsuario.fxml";
	private Usuario usuario = UsuarioImpl.getInstance();

	@FXML
	TextField nombre;

	@FXML
	TextField dni;

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

		if (!usuario.isValid()) {
			return;
		}
		nombre.setText(usuario.getNombre());
		dni.setText(usuario.getDNI());
		email.setText(usuario.getEmail());
		sexo.setValue(usuario.getSexo());
		edad.setText(usuario.getEdad().toString());
		telefono.setText(usuario.getTelefono());
		direccion.setText(usuario.getDireccion());
	}

	public void guardar(ActionEvent event) throws StreamReadException, DatabindException, IOException {
		try {
			usuario.setNombre(nombre.getText());
			usuario.setDNI(dni.getText());
			usuario.setEmail(email.getText());
			usuario.setSexo(sexo.getValue());
			usuario.setEdad(Integer.parseInt(edad.getText()));
			usuario.setTelefono(telefono.getText());
			usuario.setDireccion(direccion.getText());

			usuario.save();

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			BalanpyScreen.loadScene(stage, "/PortadaAplicacion.fxml", SCREEN);
		} catch (Exception e) {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			BalanpyScreen.quickPopUp(stage, e.getMessage());
			usuario = usuario.reload();
		}
	}

	public void atras(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		BalanpyScreen.back(stage, SCREEN);
	}

	public void delete(ActionEvent event) throws Exception {
		Mascotas.deleteAll();

		UsuarioImpl.deleteUser();

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		BalanpyScreen.loadScene(stage, "/PantallaInicio.fxml", SCREEN);
	}

	public void HandleDone(MouseEvent event) throws Exception {

		System.out.println("Está funcionando crack");

	}

}
