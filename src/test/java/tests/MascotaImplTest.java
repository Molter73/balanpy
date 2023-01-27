package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.balanpy.MascotaImpl;
import org.junit.jupiter.api.Test;

class MascotaImplTest {

	@Test
	void testValidacionSexo() {
		String[] sexosValidos = { "Macho", "Hembra", "macho", "hembra", "MACHO", "HEMBRA", "MaChO", "HeMbRa" };

		for (String sexo : sexosValidos) {
			assertTrue(MascotaImpl.isValidSexo(sexo));
		}

		String[] sexosInvalidos = { "m", "h", "mac", "Hem", "caballo", "cualquiercosa" };

		for (String sexo : sexosInvalidos) {
			assertFalse(MascotaImpl.isValidSexo(sexo));
		}
	}

	@Test
	void testValidacionGS() {
		String[] gruposSanguineosValidos = { "DEA11", "DEA12", "DEA3", "DEA4", "DEA5", "DEA6", "DEA7", "DEA8" };

		for (String grupoSanguineo : gruposSanguineosValidos) {
			assertTrue(MascotaImpl.isValidGrupoSanguineo(grupoSanguineo));
		}

		String[] gruposSanguineosInvalidos = { "d", "d11", "da9", "dae1", "dise1", "dea12" };

		for (String grupoSanguineo : gruposSanguineosInvalidos) {
			assertFalse(MascotaImpl.isValidGrupoSanguineo(grupoSanguineo));
		}
	}

}