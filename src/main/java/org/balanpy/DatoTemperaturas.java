package org.balanpy;

import java.io.IOException;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class DatoTemperaturas {

	private ArrayList<DatoGenerico> datos;

	DatoTemperaturas(Mascota mascota) {
		ObjectMapper om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());

		Path temperaturasPath = Configuracion.getMascotaTemperaturas(mascota);

		try {

			datos = om.readValue(temperaturasPath.toFile(), new TypeReference<ArrayList<DatoGenerico>>() {

			});

		} catch (IOException e) {

			System.out.println("Failed to load temperature data. " + e.getMessage());

			datos = new ArrayList<DatoGenerico>();
		}
	}

	public ArrayList<DatoGenerico> getDatos() {
		return datos;
	}

	public static String imprimeDato(Integer dato) {

		Integer parteEntera = dato / 10;

		Integer decimales = dato % 10;

		return parteEntera.toString() + "," + decimales.toString() + "º.";

	}

	public void fillGraph(LineChart<String, Double> chart) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		XYChart.Series<String, Double> series = new XYChart.Series<>();

		chart.getData().clear();

		for (DatoGenerico temperatura : datos.subList(datos.size() - 40, datos.size())) {
			series.getData().add(new XYChart.Data<String, Double>(temperatura.getFecha().format(formatter),
					temperatura.getDato() / 10.0));
		}

		series.setName("Temperatura en ºC");
		chart.getData().add(series);
	}
}