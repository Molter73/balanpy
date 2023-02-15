package org.balanpy;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HigieneController {

	private static final Path MASCOTAS_PATH = Paths.get(Configuracion.getRootDir(), "mascotas.json");

	ArrayList<MascotaImpl> mascotas;

	@FXML
	private Button configHigiene;

	@FXML
	private Button pdfHigiene;

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
	private TextField ultimoBano;

	@FXML
	private TextField ultimoCepillado;

	@FXML
	private TextArea productosNR;

	@FXML
	private TextArea ultimaDesp;

	public HigieneController() {
		mascotas = Mascotas.getInstance();
	}

	public void selectProfile(int id) throws Exception {
		if (mascotas.size() <= id) {
			return;
		}

		Mascota mascota = mascotas.get(id);

		Higiene higiene = mascota.getHigiene();

		ultimoBano.setText(higiene.getBano());

		ultimoCepillado.setText(higiene.getCepillado());

		ultimaDesp.setText(desparasitadoToString(mascota));

		productosNR.setText(productosToString(mascota));

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

	private String desparasitadoToString(Mascota mascota) {

		String returnDesparasitado = "";

		Map<String, String> desparasitadoMascota = mascota.getHigiene().getDesparasitado();

		for (Map.Entry<String, String> v : desparasitadoMascota.entrySet()) {

			returnDesparasitado += "Tipo: " + v.getKey() + "\n" + "Fecha Desparasitación: " + v.getValue() + "\n\n";

		}

		return returnDesparasitado;

	}

	private String productosToString(Mascota mascota) {

		String returnProductos = "";

		ArrayList<String> productosMascota = mascota.getHigiene().getProductosNR();

		for (String producto : productosMascota) {

			returnProductos += "Producto: " + producto + "\n";

		}

		return returnProductos;

	}

	public void initialize() throws Exception {

		if (mascotas.isEmpty()) {
			// Nothing to do here.
			return;
		}

		if (mascotas.size() >= 1) {

			setProfile(petImage0, petName0, mascotas.get(0));

			selectProfile(0);

		}

		if (mascotas.size() >= 2) {

			setProfile(petImage1, petName1, mascotas.get(1));

			selectProfile(1);

		}

		if (mascotas.size() >= 3) {

			setProfile(petImage2, petName2, mascotas.get(2));

			selectProfile(2);

		}
	}
}