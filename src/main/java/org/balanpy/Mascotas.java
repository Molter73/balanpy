package org.balanpy;

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
}
