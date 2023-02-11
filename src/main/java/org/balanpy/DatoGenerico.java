package org.balanpy;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DatoGenerico {

	private LocalDateTime fecha;

	private Integer dato;

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Integer getDato() {
		return dato;
	}

	public void setDato(Integer dato) {
		this.dato = dato;
	}

}

class Temperaturas {

	private ArrayList<DatoGenerico> datos;

	private static ObjectMapper om = new ObjectMapper();

	private static Temperaturas instance;

	private static final Path TEMP_PATH = Paths.get(System.getenv("BALANPY_CONFIG_DIR"), "temperaturas.json");

	public static Temperaturas getInstance() {

		if (instance == null) {

			try {

				instance = om.readValue(TEMP_PATH.toFile(), Temperaturas.class);

			} catch (IOException e) {

				System.out.println("Failed to load user data. " + e.getMessage());

				instance = new Temperaturas();
			}
		}

		return instance;
	}

	public ArrayList<DatoGenerico> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<DatoGenerico> datos) {
		this.datos = datos;
	}

	public String imprimeDatos(DatoGenerico dato) {

		String out = datos.toString() + "º.";

		return out;

	}

}

class Pulsaciones {

	private ArrayList<DatoGenerico> datos;

	private static ObjectMapper om = new ObjectMapper();

	private static Pulsaciones instance;

	private static final Path PULS_PATH = Paths.get(System.getenv("BALANPY_CONFIG_DIR"), "pulsaciones.json");

	public static Pulsaciones getInstance() {

		if (instance == null) {

			try {

				instance = om.readValue(PULS_PATH.toFile(), Pulsaciones.class);

			} catch (IOException e) {

				System.out.println("Failed to load user data. " + e.getMessage());

				instance = new Pulsaciones();
			}
		}

		return instance;
	}

	public ArrayList<DatoGenerico> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<DatoGenerico> datos) {
		this.datos = datos;
	}

	public String imprimeDatos(DatoGenerico dato) {

		String out = datos.toString() + " ppm.";

		return out;

	}
}
