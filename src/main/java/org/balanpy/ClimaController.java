package org.balanpy;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class ClimaController {
	@FXML
	Label avisoPaseo;

	@FXML
	Label temperatura;

	@FXML
	LineChart<String, Double> tempChart;

	static final private Color WARNING = Color.RED;
	static final private Color OK = Color.GREEN;

	@FXML
	public void initialize() throws UnsupportedOperationException, IOException {
		ClimaClient clima = new ClimaClient();

		setAvisoPaseo(clima.getTemperaturaActual());
		fillTempGraph(clima.getTemperaturasPorHora());
	}

	private void setAvisoPaseo(Double temp) {
		temperatura.setText(temp + " ºC");

		if (temp < 15 || temp > 30) {
			avisoPaseo.setText("Condiciones adversas, permanezca en interiores");
			avisoPaseo.setTextFill(WARNING);
		} else {
			avisoPaseo.setText("Condiciones ideales para pasear");
			avisoPaseo.setTextFill(OK);
		}
	}

	private void fillTempGraph(ArrayList<Pair<String, Double>> temperaturas) {
		XYChart.Series<String, Double> series = new XYChart.Series<>();

		for (Pair<String, Double> temp : temperaturas) {
			series.getData().add(new XYChart.Data<String, Double>(temp.getKey(), temp.getValue()));
		}

		series.setName("Temperatura en ºC");
		tempChart.getData().add(series);
	}
}
