package org.balanpy;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DatoPulsaciones {

	private static ArrayList<DatoGenerico> datos;

	private static final Path PULS_PATH = Paths.get(System.getenv("BALANPY_CONFIG_DIR"), "pulsaciones.json");

	DatoPulsaciones(Mascota mascota) {

		ObjectMapper om = new ObjectMapper();

		Path temperaturasPath = Paths.get(Configuracion.getRootDir(), mascota.getUUID().toString(), "pulsaciones.json");

		try {

			datos = om.readValue(temperaturasPath.toFile(), new TypeReference<ArrayList<DatoGenerico>>() {

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
}