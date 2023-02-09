package org.balanpy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public interface ManejoDatos {

	public double obtenerTemperatura(String archivo) throws IOException;

	public int obtenerPulsaciones(String archivo) throws IOException;

}

class DatosMascota implements ManejoDatos {

	@Override
	public double obtenerTemperatura(String archivo) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(archivo));

		String linea = br.readLine();

		br.close();

		return Double.parseDouble(linea);
	}

	@Override
	public int obtenerPulsaciones(String archivo) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(archivo));

		String linea = br.readLine();

		br.close();

		return Integer.parseInt(linea);

	}
}