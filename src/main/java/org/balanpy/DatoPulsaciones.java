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

public class DatoPulsaciones {

	private ArrayList<DatoGenerico> datos;

	DatoPulsaciones(Mascota mascota) {
		ObjectMapper om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());

		Path pulsacionesPath = Configuracion.getMascotaPulsaciones(mascota);
		try {

			datos = om.readValue(pulsacionesPath.toFile(), new TypeReference<ArrayList<DatoGenerico>>() {

			});

		} catch (IOException e) {

			System.out.println("Failed to load pulsations data. " + e.getMessage());

			datos = new ArrayList<DatoGenerico>();
		}
	}

	public ArrayList<DatoGenerico> getDatos() {
		return datos;
	}

	public static String imprimeDato(Integer dato) {

		return dato.toString() + " ppm.";

	}

	public void fillGraph(LineChart<String, Integer> chart) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		XYChart.Series<String, Integer> series = new XYChart.Series<>();

		chart.getData().clear();

		for (DatoGenerico pulsacion : datos.subList(datos.size() - 40, datos.size())) {
			series.getData().add(
					new XYChart.Data<String, Integer>(pulsacion.getFecha().format(formatter), pulsacion.getDato()));
		}

		series.setName("Pulsaciones en PPM.");
		chart.getData().add(series);
	}
}