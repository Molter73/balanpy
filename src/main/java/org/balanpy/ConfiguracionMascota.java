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
	TextField nombre_mascota;
	@FXML
	TextField color;
	@FXML
	TextField raza;
	@FXML
	TextField chip;
	
	@FXML
	ChoiceBox<String> sexo_mascota;
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
	ListView<String> vacunas;
	String[] lista_v = {};	
	
	@FXML
	ListView<String> alergias;
	String[] lista_a = {};
	
	@FXML
	ListView<String> enfermedades;
	String[] lista_e = {};
	
	
	@FXML
	Button esterilizacion;
	@FXML
	Button dni_mascota;
	@FXML
	Button pedigree;
	@FXML
	Button eliminar_mascota;
	@FXML
	Button atras;
	@FXML
	Button guardar_mascota;
	
	@FXML
	ImageView subir_mascota;

	
	public void HandleDone(MouseEvent event) throws Exception {
		System.out.println("Está funcionando crack");	
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//ChoiceBox
		sexo_mascota.getItems().addAll(sexo);
		esterilizada.getItems().addAll(estrl);
		sanguineo.getItems().addAll(gsanguineo);
		
		//ListView
	}
	
	public void getDate(ActionEvent event) {
		LocalDate fecha_n = nacimiento.getValue();
		System.out.println(fecha_n.toString());
	}

}
