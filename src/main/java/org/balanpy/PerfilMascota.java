package org.balanpy;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class PerfilMascota {
	@FXML
	private Text color;

	@FXML
	private Text edad;

	@FXML
	private Text fechaNacimiento;

	@FXML
	private ImageView fotoMascota;

	@FXML
	private Text grupoSanguineo;

	@FXML
	private Text nombreMascota;

	@FXML
	private LineChart<?, ?> pulsaciones;

	@FXML
	private Text raza;

	@FXML
	private Text sexo;

	@FXML
	private Text esterilizado;

	@FXML
	private LineChart<?, ?> temperaturaDiaria;

	private static Mascota mascota;
	public static void setMascota(Mascota m) {
		mascota = m;
	}

	public void initialize() {
		color.setText(mascota.getColor());
		edad.setText(mascota.getEdad().toString() + "años");
		fechaNacimiento.setText(mascota.getFechaNacimiento());
		grupoSanguineo.setText(mascota.getGrupoSanguineo());
		nombreMascota.setText(mascota.getNombre());
		raza.setText(mascota.getRaza());
		sexo.setText(mascota.getSexo());

		try {
			Path imagePath = Paths.get(Configuracion.getRootDir(), mascota.getUUID().toString(), "profile.png");
			fotoMascota.setImage(new Image(imagePath.toUri().toURL().toExternalForm()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void HandleDone(MouseEvent event) throws Exception {

		System.out.println("Está funcionando amigo");

	}

}
