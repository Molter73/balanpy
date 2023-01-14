package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.balanpy.Balanpy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BalanpyTest {
	static ArrayList<Integer> temperaturas = new ArrayList<Integer>();

	@BeforeAll
	static void setTemperaturas() {
		temperaturas.add(36);
		temperaturas.add(37);
		temperaturas.add(40);
		temperaturas.add(34);
	}

	@Test
	void testTemperaturaAlta() {
		Integer temperaturasMayoresA38 = 1;

		assertEquals(temperaturasMayoresA38, Balanpy.getTemperaturaAlta(temperaturas));
	}

	@Test
	void testTemperaturaBaja() {
		Integer temperaturasMenoresA35 = 1;

		assertEquals(temperaturasMenoresA35, Balanpy.getTemperaturaBaja(temperaturas));
	}

	@Test
	void testTemperaturaMedia() {
		Double temperaturaMedia = (double) 36.75;

		assertEquals(temperaturaMedia, Balanpy.getTemperaturaMedia(temperaturas));
	}

	@Test
	void testTemperaturaMaxima() {
		Integer temperaturaMaxima = 40;

		assertEquals(temperaturaMaxima, Balanpy.getTemperaturaMaxima(temperaturas));
	}
}
