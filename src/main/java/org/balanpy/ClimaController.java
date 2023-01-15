package org.balanpy;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class ClimaController {
	@FXML
	Label avisoPaseo;

	@FXML
	Label temperatura;

	static final private Color WARNING = Color.RED;
	static final private Color OK = Color.GREEN;

	@FXML
	public void initialize() throws UnsupportedOperationException, IOException {
		ClimaClient clima = new ClimaClient();

		setAvisoPaseo(clima.getTemperaturaActual());
	}

	public void setAvisoPaseo(Double temp) {
		temperatura.setText(temp + " ºC");

		if (temp < 15 || temp > 30) {
			avisoPaseo.setText("Condiciones adversas, permanezca en interiores");
			avisoPaseo.setTextFill(WARNING);
		} else {
			avisoPaseo.setText("Condiciones ideales para pasear");
			avisoPaseo.setTextFill(OK);
		}
	}
}
