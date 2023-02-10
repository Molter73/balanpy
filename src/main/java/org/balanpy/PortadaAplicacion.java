package org.balanpy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PortadaAplicacion {
	private static final Path MASCOTAS_PATH = Paths.get(Configuracion.ROOT_DIR, "mascotas.json");
	ArrayList<MascotaImpl> mascotas;

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

	public PortadaAplicacion() {
		try {
			ObjectMapper om = new ObjectMapper();
			mascotas = om.readValue(MASCOTAS_PATH.toFile(), new TypeReference<ArrayList<MascotaImpl>>() {
			});
		} catch (IOException e) {
			System.out.println("Failed to load pet data. " + e.getMessage());
			mascotas = new ArrayList<MascotaImpl>();
		}
	}

	private void setProfile(ImageView imageView, Label label, Mascota mascota) throws MalformedURLException {
		Path imagePath = Paths.get(Configuracion.ROOT_DIR, mascota.getUUID().toString(), "profile.png");
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

	public void HandleDone(MouseEvent event) throws Exception {

		System.out.println("Está funcionando crack");

	}
}
