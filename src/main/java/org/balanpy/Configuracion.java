package org.balanpy;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Configuracion {
	private static String rootDir = System.getenv("BALANPY_CONFIG_DIR");

	public static String getRootDir() {
		if (rootDir == null) {
			return "target";
		}
		return rootDir;
	}

	public static Path getMascotasPath() {
		return Paths.get(getRootDir(), "mascotas.json");
	}

	public static Path getMascotaProfilePic(Mascota mascota) {
		return Paths.get(getRootDir(), mascota.getUUID().toString(), "profile.png");
	}

	public static Path getMascotaPulsaciones(Mascota mascota) {
		return Paths.get(getRootDir(), mascota.getUUID().toString(), "pulsaciones.json");
	}
}
