package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.balanpy.DatoTemperaturas;
import org.junit.jupiter.api.Test;

public class DatoTemperaturasTest {

	@Test
	void testImprimeDatos() {

		int[] datosTemperatura = { 365, 374, 372, 346, 367, 356 };

		String[] expDatos = { "36,5º.", "37,4º.", "37,2º.", "34,6º.", "36,7º.", "35,6º." };

		for (int i = 0; i < datosTemperatura.length; i++) {

			assertEquals(expDatos[i], DatoTemperaturas.imprimeDatos(datosTemperatura[i]));

		}

	}
}
