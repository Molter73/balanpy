package org.balanpy;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class DatoPulsaciones {

	private static ArrayList<DatoGenerico> datos;

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
}