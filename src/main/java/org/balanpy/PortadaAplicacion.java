package org.balanpy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PortadaAplicacion {

	private static final String SCREEN = "/PortadaAplicacion.fxml";

	ArrayList<MascotaImpl> mascotas = Mascotas.getInstance();

	@FXML
	ImageView petImage0;

	@FXML
	ImageView petImage1;

	@FXML
	ImageView petImage2;

	@FXML
	Label petName0;

	@FXML
	Label petName1;

	@FXML
	Label petName2;

	BalanpyScreen balanpyScreen = new BalanpyScreen();

	public PortadaAplicacion() {
	}

	private void setProfile(ImageView imageView, Label label, Mascota mascota) throws MalformedURLException {
		Path imagePath = Paths.get(Configuracion.getRootDir(), mascota.getUUID().toString(), "profile.png");
		label.setText(mascota.getNombre());
		imageView.setImage(new Image(imagePath.toUri().toURL().toExternalForm()));
	}

	public void initialize() throws MalformedURLException {
		if (mascotas.isEmpty()) {
			// Nothing to do here.
			return;
		}

		// Esto sería mucho mejor si fuese un loop que agrega perfiles dinamicamente
		// pero la verdad no tengo tiempo de implementarlo ahora mismo.
		if (mascotas.size() >= 1) {
			setProfile(petImage0, petName0, mascotas.get(0));
		}

		if (mascotas.size() >= 2) {
			setProfile(petImage1, petName1, mascotas.get(1));
		}

		if (mascotas.size() >= 3) {
			setProfile(petImage2, petName2, mascotas.get(2));
		}
	}

	public void selectProfile(MouseEvent event, int id) throws Exception {
		if (mascotas.size() >= (id + 1)) {
			PerfilMascota.setMascota(mascotas.get(id));
			switchScene(event, "/PerfilMascota.fxml");

		} else {
			switchScene(event, "/ConfiguracionMascota.fxml");
		}
	}

	public void selectProfile0(MouseEvent event) throws Exception {

		selectProfile(event, 0);
	}

	public void selectProfile1(MouseEvent event) throws Exception {

		selectProfile(event, 1);

	}

	public void selectProfile2(MouseEvent event) throws Exception {

		selectProfile(event, 2);

	}

	public void HandleDone(MouseEvent event) throws Exception {

		System.out.println("Está funcionando crack");

	}

	private void switchScene(MouseEvent event, String scene) throws IOException {

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		BalanpyScreen.loadScene(stage, scene, SCREEN);
	}

	public void temperaturaExterna(MouseEvent event) throws IOException {

		switchScene(event, "/Clima.fxml");

	}

	public void higiene(MouseEvent event) throws IOException {

		switchScene(event, "/Higiene.fxml");

	}

	public void vacunas(MouseEvent event) throws IOException {

		switchScene(event, "/Vacunas.fxml");

	}

	public void registroMascota(MouseEvent event) throws IOException {

		switchScene(event, "/ConfiguracionMascota.fxml");

	}

	public void perfilMascota(MouseEvent event) throws IOException {

		switchScene(event, "/PerfilMascota.fxml");

	}

}
