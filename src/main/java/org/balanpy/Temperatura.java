package org.balanpy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Temperatura {
	
	@FXML
	Button descargarTemperatura;
	
	public void HandleDone(MouseEvent event) throws Exception {

		System.out.println("Está funcionando amigo");
	}
}
