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
	private static Mascota selectedPet = null;

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

	public static void setMascota(Mascota m) {
		selectedPet = m;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//ChoiceBox
		sexoMascota.getItems().addAll(sexo);
		esterilizada.getItems().addAll(estrl);
		sanguineo.getItems().addAll(gsanguineo);

		if (selectedPet == null) {
			return;
		}

		nombreMascota.setText(selectedPet.getNombre());
		color.setText(selectedPet.getColor());
		raza.setText(selectedPet.getRaza());
		chip.setText(selectedPet.getChip());
		sexoMascota.setValue(selectedPet.getSexo());

		if (selectedPet.getEsterilizado()) {
			esterilizada.setValue("Si");
		} else {
			esterilizada.setValue("No");
		}

		sanguineo.setValue(selectedPet.getGrupoSanguineo());

		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		nacimiento.setValue(LocalDate.parse(selectedPet.getFechaNacimiento(), df));

		String tmp = "";
		for (String alergia : selectedPet.getAlergias()) {
			tmp += alergia + "\n";
		}
		alergias.setText(tmp);

		tmp = "";
		for (String enfermedad : selectedPet.getEnfermedades()) {
			tmp += enfermedad + "\n";
		}
		enfermedades.setText(tmp);

		tmp = selectedPet.getVacuna().get("primovacunacion");
		if (tmp != null) {
			primovacunacion.setValue(LocalDate.parse(tmp, df));
		}

		tmp = selectedPet.getVacuna().get("polivalente");
		if (tmp != null) {
			polivalente.setValue(LocalDate.parse(tmp, df));
		}

		tmp = selectedPet.getVacuna().get("polivalente+");
		if (tmp != null) {
			refuerzoPolivalente.setValue(LocalDate.parse(tmp, df));
		}

		tmp = selectedPet.getVacuna().get("rabia");
		if (tmp != null) {
			rabia.setValue(LocalDate.parse(tmp, df));
		}

		File profilePic = Configuracion.getMascotaProfilePic(selectedPet).toFile();
		if (profilePic.isFile()) {
			try {
				Image image = new Image(profilePic.toURI().toURL().toExternalForm());
				subirMascota.setImage(image);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void back(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		selectedPet = null;
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

	public void delete(ActionEvent event) throws StreamWriteException, DatabindException, IOException {
		Mascotas.delete(selectedPet);

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		selectedPet = null;
		BalanpyScreen.loadScene(stage, "/PortadaAplicacion.fxml", SCREEN);
	}

	public void save(ActionEvent event) throws StreamWriteException, DatabindException, IOException {
		MascotaImpl mascota = new MascotaImpl();
		UUID uuid = UUID.randomUUID();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

		ArrayList<MascotaImpl> mascotas = Mascotas.getInstance();
		if (selectedPet == null) {
			mascota.setUUID(uuid);
			mascotas.add(mascota);

			Path mascotaDir = Paths.get(Configuracion.getRootDir(), uuid.toString());
			Files.createDirectory(mascotaDir);

			Files.write(Configuracion.getMascotaPulsaciones(mascota), "[]".getBytes());
			Files.write(Configuracion.getMascotaTemperaturas(mascota), "[]".getBytes());
		} else {
			int index = mascotas.indexOf(selectedPet);
			mascota.setUUID(selectedPet.getUUID());
			mascotas.remove(index);
			mascotas.add(index, mascota);

			PerfilMascota.setMascota(mascota);
		}

		if (profilePic != null) {
			Files.copy(profilePic.toPath(), Configuracion.getMascotaProfilePic(mascota),
					StandardCopyOption.REPLACE_EXISTING);
		}

		Mascotas.save();

		selectedPet = null;
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		BalanpyScreen.back(stage, SCREEN);
	}
}
