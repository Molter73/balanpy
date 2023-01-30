package org.balanpy;

import java.io.FileReader;
import java.io.IOException;

public class LecturaArchivosGen {

	public static void main(String[] args) {

		Leer_Fichero accediendo = new Leer_Fichero();

		accediendo.leeFichero();

	}

}

class Leer_Fichero {

	public void leeFichero() {

		try {

			FileReader entrada = new FileReader(" ");

			int c = entrada.read();

			while (c != -1) {

				c = entrada.read();

				char letra = (char) c;

				System.out.print(letra);

			}

			entrada.close();

		} catch (IOException e) {

			// TODO Bloque catch generado automáticamente

			System.out.println("No se ha encontrado el archivo.");
		}
	}
}