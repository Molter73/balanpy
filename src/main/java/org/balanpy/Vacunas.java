package org.balanpy;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Vacunas {

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

	@FXML
	TextArea registroVacunas;

	public Vacunas() {
	}

	public void selectProfile(int id) throws Exception {
		if (mascotas.size() > id) {
			registroVacunas.setText(vacunasToString(mascotas.get(id)));
		}
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
