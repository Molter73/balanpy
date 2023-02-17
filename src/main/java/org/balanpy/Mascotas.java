package org.balanpy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mascotas {
	private static ArrayList<MascotaImpl> mascotas;
	private static final Path MASCOTAS_PATH = Paths.get(Configuracion.getRootDir(), "mascotas.json");

	private Mascotas() {
	}

	public static ArrayList<MascotaImpl> getInstance() {
		if (mascotas == null) {
			load();
		}
		return mascotas;
	}

	public static void save() throws StreamWriteException, DatabindException, IOException {
		ObjectMapper om = new ObjectMapper();
		om.writeValue(Configuracion.getMascotasPath().toFile(), mascotas);
	}

	public static ArrayList<MascotaImpl> load() {
		try {
			ObjectMapper om = new ObjectMapper();
			mascotas = om.readValue(MASCOTAS_PATH.toFile(), new TypeReference<ArrayList<MascotaImpl>>() {
			});
		} catch (IOException e) {
			System.out.println("Failed to load pet data. " + e.getMessage());
			mascotas = new ArrayList<MascotaImpl>();
		}
		return mascotas;
	}

	public static void delete(Mascota mascota) throws StreamWriteException, DatabindException, IOException {
		File profilePic = Configuracion.getMascotaProfilePic(mascota).toFile();
		profilePic.delete();

		File temperaturas = Configuracion.getMascotaTemperaturas(mascota).toFile();
		temperaturas.delete();

		File pulsaciones = Configuracion.getMascotaPulsaciones(mascota).toFile();
		pulsaciones.delete();

		File m = Paths.get(Configuracion.getRootDir(), mascota.getUUID().toString()).toFile();
		m.delete();

		mascotas.remove(mascotas.indexOf(mascota));

		save();
	}

	public static void deleteAll() {
		for (Mascota mascota : mascotas) {
			File profilePic = Configuracion.getMascotaProfilePic(mascota).toFile();
			profilePic.delete();

			File temperaturas = Configuracion.getMascotaTemperaturas(mascota).toFile();
			temperaturas.delete();

			File pulsaciones = Configuracion.getMascotaPulsaciones(mascota).toFile();
			pulsaciones.delete();

			File m = Paths.get(Configuracion.getRootDir(), mascota.getUUID().toString()).toFile();
			m.delete();
		}
		File config = Configuracion.getMascotasPath().toFile();
		config.delete();

		mascotas = null;
	}
}
