package org.balanpy;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ConfiguracionMascota implements Initializable{
	
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
	private static final String[] SEXO = {"Macho", "Hembra"};
	@FXML
	ChoiceBox<String> esterilizada;
	private static final String[] ESTERILIZADA = {"Si", "No"};
	@FXML
	ChoiceBox<String> grupoSanguineo;
	private static final String[] GRUPO_SANGUINEO = {"DEA-1.1.", "DEA-1.2.", "DEA-3", "DEA-4", "DEA-5", "DEA-6", "DEA-7", "DEA-8"};
	
	@FXML
	DatePicker nacimiento;
	
	@FXML
	ListView<String> VACUNAS;
	String[] listaVacunas = {};	
	
	@FXML
	ListView<String> ALERGIAS;
	String[] listaAlergias = {};
	
	@FXML
	ListView<String> ENFERMEDADES;
	String[] listaEnfermedades = {};
	
	
	@FXML
	Button esterilizacion;
	@FXML
	Button dniMascota;
	@FXML
	Button pedigree;
	@FXML
	Button eliminarMascota;
	@FXML
	Button atras;
	@FXML
	Button guardarMascota;
	
	@FXML
	ImageView subirMascota;

	
	public void HandleDone(MouseEvent event) throws Exception {
		System.out.println("Está funcionando crack");	
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//ChoiceBox
		sexoMascota.getItems().addAll(SEXO);
		esterilizada.getItems().addAll(ESTERILIZADA);
		grupoSanguineo.getItems().addAll(GRUPO_SANGUINEO);
		
		//ListView
	}
	
	public void getDate(ActionEvent event) {
		LocalDate fecha_n = nacimiento.getValue();
		System.out.println(fecha_n.toString());
	}

}
