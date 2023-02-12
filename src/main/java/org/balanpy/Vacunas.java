package org.balanpy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Vacunas {

	private static final Path MASCOTAS_PATH = Paths.get(Configuracion.getRootDir(), "mascotas.json");

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

	@FXML
	TextArea registroVacunas;

	public Vacunas() {

		try {
			ObjectMapper om = new ObjectMapper();

			mascotas = om.readValue(MASCOTAS_PATH.toFile(), new TypeReference<ArrayList<MascotaImpl>>() {

			});

		} catch (IOException e) {

			System.out.println("Failed to load pet data. " + e.getMessage());

			mascotas = new ArrayList<MascotaImpl>();

		}
	}

	public void selectProfile(int id) throws Exception {

		registroVacunas.setText(vacunasToString(mascotas.get(id)));

	}

	public void selectProfile0(MouseEvent event) throws Exception {

		selectProfile(0);
	}

	public void selectProfile1(MouseEvent event) throws Exception {

		selectProfile(1);

	}

	public void selectProfile2(MouseEvent event) throws Exception {

		selectProfile(2);

	}

	private void setProfile(ImageView imageView, Label label, Mascota mascota) throws MalformedURLException {

		Path imagePath = Paths.get(Configuracion.getRootDir(), mascota.getUUID().toString(), "profile.png");

		label.setText(mascota.getNombre());

		imageView.setImage(new Image(imagePath.toUri().toURL().toExternalForm()));

	}

	private String vacunasToString(Mascota mascota) {

		String returnVacunas = "";

		Map<String, String> vacunaMascota = mascota.getVacuna();

		for (Map.Entry<String, String> v : vacunaMascota.entrySet()) {

			returnVacunas += "Nombre de la Vacuna: " + v.getKey() + "\n" + "Fecha Vacunación: " + v.getValue() + "\n\n";

		}

		return returnVacunas;

	}

	public void initialize() throws MalformedURLException {
		if (mascotas.isEmpty()) {
			// Nothing to do here.
			return;
		}

		if (mascotas.size() >= 1) {

			setProfile(petImage0, petName0, mascotas.get(0));

			registroVacunas.setText(vacunasToString(mascotas.get(0)));
		}

		if (mascotas.size() >= 2) {
			setProfile(petImage1, petName1, mascotas.get(1));

			registroVacunas.setText(vacunasToString(mascotas.get(1)));

		}

		if (mascotas.size() >= 3) {

			setProfile(petImage2, petName2, mascotas.get(2));

			registroVacunas.setText(vacunasToString(mascotas.get(2)));

		}
	}

}
