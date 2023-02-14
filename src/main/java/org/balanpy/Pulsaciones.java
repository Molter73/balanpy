package org.balanpy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Pulsaciones {
	private static final String SCREEN = "/Pulsaciones.fxml";
	ArrayList<MascotaImpl> mascotas;

	@FXML
	private ImageView petImage0;

	@FXML
	private ImageView petImage1;

	@FXML
	private ImageView petImage2;

	@FXML
	private Label petName0;

	@FXML
	private Label petName1;

	@FXML
	private Label petName2;

	@FXML
	private LineChart<String, Integer> pulsacionesChart;

	public Pulsaciones() {
		try {
			ObjectMapper om = new ObjectMapper();
			mascotas = om.readValue(Configuracion.getMascotasPath().toFile(),
					new TypeReference<ArrayList<MascotaImpl>>() {
			});
		} catch (IOException e) {
			System.out.println("Failed to load pet data. " + e.getMessage());
			mascotas = new ArrayList<MascotaImpl>();
		}
	}

	private void setProfile(ImageView imageView, Label label, Mascota mascota) throws MalformedURLException {
		Path imagePath = Configuracion.getMascotaProfilePic(mascota);
		label.setText(mascota.getNombre());
		imageView.setImage(new Image(imagePath.toUri().toURL().toExternalForm()));
	}

	public void selectProfile(int id) throws Exception {
		if (mascotas.size() <= id) {
			return;
		}

		DatoPulsaciones pulsaciones = new DatoPulsaciones(mascotas.get(id));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		ArrayList<DatoGenerico> datos = pulsaciones.getDatos();

		pulsacionesChart.getData().clear();

		for (DatoGenerico pulsacion : datos.subList(datos.size() - 40, datos.size())) {
			series.getData()
			.add(new XYChart.Data<String, Integer>(pulsacion.getFecha().format(formatter),
					pulsacion.getDato()));
		}

		series.setName("Pulsaciones en PPM.");
		pulsacionesChart.getData().add(series);
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

	@FXML
	void selectProfile0(MouseEvent event) throws Exception {
		selectProfile(0);
	}

	@FXML
	void selectProfile1(MouseEvent event) throws Exception {
		selectProfile(1);
	}

	@FXML
	void selectProfile2(MouseEvent event) throws Exception {
		selectProfile(2);
	}

}
