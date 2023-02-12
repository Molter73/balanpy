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

		ultimoBano.setText(ultimoBanoToString(mascotas.get(id)));

		ultimoCepillado.setText(ultimoCepilladoToString(mascotas.get(id)));

		ultimaDesp.setText(desparasitadoToString(mascotas.get(id)));

		productosNR.setText(productosToString(mascotas.get(id)));

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

	private String ultimoBanoToString(Mascota mascota) {

		String bano = mascota.getHigiene().getBano();

		return bano;

	}

	private String ultimoCepilladoToString(Mascota mascota) {

		String capillado = mascota.getHigiene().getCepillado();

		return capillado;

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

	public void initialize() throws MalformedURLException {

		if (mascotas.isEmpty()) {
			// Nothing to do here.
			return;
		}

		if (mascotas.size() >= 1) {

			setProfile(petImage0, petName0, mascotas.get(0));

			ultimoBano.setText(ultimoBanoToString(mascotas.get(0)));

			ultimoCepillado.setText(ultimoCepilladoToString(mascotas.get(0)));

			ultimaDesp.setText(desparasitadoToString(mascotas.get(0)));

			productosNR.setText(productosToString(mascotas.get(0)));

		}

		if (mascotas.size() >= 2) {

			setProfile(petImage1, petName1, mascotas.get(1));

			ultimoBano.setText(ultimoBanoToString(mascotas.get(1)));

			ultimoCepillado.setText(ultimoCepilladoToString(mascotas.get(1)));

			ultimaDesp.setText(desparasitadoToString(mascotas.get(1)));

			productosNR.setText(productosToString(mascotas.get(1)));

		}

		if (mascotas.size() >= 3) {

			setProfile(petImage2, petName2, mascotas.get(2));

			ultimoBano.setText(ultimoBanoToString(mascotas.get(2)));

			ultimoCepillado.setText(ultimoCepilladoToString(mascotas.get(2)));

			ultimaDesp.setText(desparasitadoToString(mascotas.get(2)));

			productosNR.setText(productosToString(mascotas.get(2)));

		}
	}
}