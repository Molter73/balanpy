package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.balanpy.UsuarioImpl;
import org.junit.jupiter.api.Test;

class UsuarioImplTest {

	@Test
	void testValidacionSexo() {
		String[] sexosValidos = { "Masculino", "Femenino", "masculino", "femenino", "MASCULINO", "FEMENINO",
				"MaScUlInO", "FeMeNiNo" };

		for (String sexo : sexosValidos) {
			assertTrue(UsuarioImpl.isValidSexo(sexo));
		}

		String[] sexosInvalidos = { "m", "f", "masc", "FeMen", "caballo", "cualquiercosa" };

		for (String sexo : sexosInvalidos) {
			assertFalse(UsuarioImpl.isValidSexo(sexo));
		}
	}

	@Test
	void testValidacionDNI() {
		String[] dniValidos = { "12345678A", "Y1234567N" };

		for (String dni : dniValidos) {
			assertTrue(UsuarioImpl.isValidDNI(dni));
		}

		String[] dniInvalidos = { "12345678a", "y1234567n", "12345789", "cualquiercosa", "" };

		for (String dni : dniInvalidos) {
			assertFalse(UsuarioImpl.isValidDNI(dni));
		}
	}

	@Test
	void testValidacionEmail() {
		String[] emailValidos = { "a@a.com", "someone@aol.com.es" };

		for (String email : emailValidos) {
			assertTrue(UsuarioImpl.isValidEmail(email));
		}

		String[] emailInvalidos = { "google.com", "cualquiercosa", "a@amazon" };

		for (String email : emailInvalidos) {
			assertFalse(UsuarioImpl.isValidEmail(email));
		}
	}
}
