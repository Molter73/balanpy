package org.balanpy;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class DatoTemperaturas {

	private static ArrayList<DatoGenerico> datos;

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

}