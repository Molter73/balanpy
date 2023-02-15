package org.balanpy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

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
		for (Mascota mascota: Mascotas.getInstance()) {
			File profilePic = Configuracion.getMascotaProfilePic(mascota).toFile();
			profilePic.delete();

			File temperaturas = Configuracion.getMascotaTemperaturas(mascota).toFile();
			temperaturas.delete();

			File pulsaciones = Configuracion.getMascotaPulsaciones(mascota).toFile();
			pulsaciones.delete();

			File m = Paths.get(Configuracion.getRootDir(), mascota.getUUID().toString()).toFile();
			m.delete();
		}

		File mascotas = Configuracion.getMascotasPath().toFile();
		mascotas.delete();

		UsuarioImpl.deleteUser();

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		BalanpyScreen.loadScene(stage, "/PantallaInicio.fxml", SCREEN);
	}

	public void HandleDone(MouseEvent event) throws Exception {

		System.out.println("Está funcionando crack");

	}

}
