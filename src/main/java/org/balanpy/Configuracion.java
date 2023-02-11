package org.balanpy;

public class Configuracion {
	private static String rootDir = System.getenv("BALANPY_CONFIG_DIR");

	public static String getRootDir() {
		if (rootDir == null) {
			return "target";
		}
		return rootDir;
	}
}
