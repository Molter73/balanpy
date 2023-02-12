package org.balanpy;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DatoTemperaturas {

	private static ArrayList<DatoGenerico> datos;

	private static final Path TEMP_PATH = Paths.get(System.getenv("BALANPY_CONFIG_DIR"), "temperaturas.json");

	DatoTemperaturas(Mascota mascota) {

		ObjectMapper om = new ObjectMapper();

		Path temperaturasPath = Paths.get(Configuracion.getRootDir(), mascota.getUUID().toString(),
				"temperaturas.json");

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

}