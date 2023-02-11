package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.balanpy.DatoPulsaciones;
import org.junit.jupiter.api.Test;

class DatoPulsacionesTest {

	@Test
	void testImprimeDatos() {

		int[] datosPulsaciones = { 132, 135, 136, 137, 129, 124 };

		String[] expDatos = { "132 ppm.", "135 ppm.", "136 ppm.", "137 ppm.", "129 ppm.", "124 ppm." };

		for (int i = 0; i < datosPulsaciones.length; i++) {

			assertEquals(expDatos[i], DatoPulsaciones.imprimeDatos(datosPulsaciones[i]));

		}

	}
}
