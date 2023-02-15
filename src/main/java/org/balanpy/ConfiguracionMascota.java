package org.balanpy;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ConfiguracionMascota implements Initializable{
	private static final String SCREEN = "/ConfiguracionMascota.fxml";

	@FXML
	TextField nombreMascota;
	@FXML
	TextField color;
	@FXML
	TextField raza;
	@FXML
	TextField chip;

	@FXML
	ChoiceBox<String> sexoMascota;
	private static final String[] sexo = {"Macho", "Hembra"};
	@FXML
	ChoiceBox<String> esterilizada;
	private static final String[] estrl = {"Si", "No"};
	@FXML
	ChoiceBox<String> sanguineo;
	private static final String[] gsanguineo = {"DEA-1.1.", "DEA-1.2.", "DEA-3", "DEA-4", "DEA-5", "DEA-6", "DEA-7", "DEA-8"};

	@FXML
	DatePicker nacimiento;

	@FXML
	TextArea alergias;

	@FXML
	TextArea enfermedades;

	@FXML
	ImageView subirMascota;

	@FXML
	private DatePicker polivalente;

	@FXML
	private DatePicker primovacunacion;

	@FXML
	private DatePicker rabia;

	@FXML
	private DatePicker refuerzoPolivalente;

	File profilePic;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//ChoiceBox
		sexoMascota.getItems().addAll(sexo);
		esterilizada.getItems().addAll(estrl);
		sanguineo.getItems().addAll(gsanguineo);
	}

	public void back(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		BalanpyScreen.back(stage, SCREEN);
	}

	public void pickPicture(MouseEvent event) throws MalformedURLException {
		FileChooser fc = new FileChooser();
		profilePic = fc.showOpenDialog(new Stage());

		if (profilePic != null) {
			Image image = new Image(profilePic.toURI().toURL().toExternalForm());
			subirMascota.setImage(image);
		}
	}

	private ArrayList<String> sanitizedList(TextArea text) {
		String[] array = text.getText().split(System.lineSeparator());
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(array));
		list.removeAll(Arrays.asList("", null));

		return list;
	}

	public void save(ActionEvent event) throws StreamWriteException, DatabindException, IOException {
		Mascota mascota = new MascotaImpl();
		UUID uuid = UUID.randomUUID();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		mascota.setUUID(uuid);
		mascota.setNombre(nombreMascota.getText());
		mascota.setColor(color.getText());
		mascota.setRaza(raza.getText());
		mascota.setChip(chip.getText());
		mascota.setSexo(sexoMascota.getValue());
		mascota.setEsterilizado(esterilizada.getValue() == "Si");
		mascota.setGrupoSanguineo(sanguineo.getValue());
		mascota.setFechaNacimiento(nacimiento.getValue().format(df));
		mascota.setAlergias(sanitizedList(alergias));
		mascota.setEnfermedades(sanitizedList(enfermedades));

		Map<String, String> vacunas = new HashMap<>();

		LocalDate pv = primovacunacion.getValue();
		if (pv != null) {
			vacunas.put("primovacunacion", pv.format(df));
		}

		LocalDate pl = polivalente.getValue();
		if (pl != null) {
			vacunas.put("polivalente", pl.format(df));
		}

		LocalDate rp = refuerzoPolivalente.getValue();
		if (rp != null) {
			vacunas.put("polivalente+", rp.format(df));
		}

		LocalDate ra = rabia.getValue();
		if (ra != null) {
			vacunas.put("rabia", ra.format(df));
		}
		mascota.setVacunas(vacunas);

		Path mascotaDir = Paths.get(Configuracion.getRootDir(), uuid.toString());
		Files.createDirectory(mascotaDir);

		Files.write(Configuracion.getMascotaPulsaciones(mascota), "[]".getBytes());
		Files.write(Configuracion.getMascotaTemperaturas(mascota), "[]".getBytes());

		if (profilePic != null) {
			Files.copy(profilePic.toPath(), Configuracion.getMascotaProfilePic(mascota),
					StandardCopyOption.REPLACE_EXISTING);
		}

		ArrayList<MascotaImpl> mascotas = Mascotas.getInstance();
		mascotas.add((MascotaImpl) mascota);
		Mascotas.save();

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		BalanpyScreen.back(stage, SCREEN);
	}
}
