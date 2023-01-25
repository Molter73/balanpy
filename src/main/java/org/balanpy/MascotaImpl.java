package org.balanpy;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 *  MascotaImpl es un singleton para evitar tener que copiar el
 *  objeto cada vez que se cambia de pantalla, recargando los
 *  datos desde disco innecesariamente.
 *
 *  TODO: Las funciones de validación deberían lanzar una excepción
 *  para permitir mejor manejo de error.
 */

public class MascotaImpl implements Mascota {

	private static final Path MASCOTA_PATH = Paths.get(System.getenv("BALANPY_CONFIG_DIR"), "mascotas.json");
	private static final String MACHO = "macho";
	private static final String HEMBRA = "hembra";
	private static final String DEA11 = "DEA-1.1.";
	private static final String DEA12 = "DEA-1.2.";
	private static final String DEA3 = "DEA-3.";
	private static final String DEA4 = "DEA-4.";
	private static final String DEA5 = "DEA-5.";
	private static final String DEA6 = "DEA-6.";
	private static final String DEA7 = "DEA-7.";
	private static final String DEA8 = "DEA-8.";

	@JsonProperty(value = "UUID")
	private UUID uuid;

	private Date fechaNacimiento;

	@JsonProperty(value = "CHIP")
	private String chip = "";

	private String raza = "";

	private String sexo = "";

	private int peso = 0;

	private String color = "";

	private String grupo_sanguineo = "";

	private String nombre = "";

	@JsonProperty(value = "archivo_pedigree")
	private String pathPedigree = "";

	@JsonProperty(value = "archivo_dni")
	private String pathDNI = "";

	@JsonProperty(value = "archivo_esterilizacion")
	private String pathEsterilizacion = "";

	@JsonProperty(value = "temperaturas")
	private String pathTemperaturas = "";

	private ArrayList<String> alergias = new ArrayList();

	private ArrayList<String> enfermedades = new ArrayList();

	private Map<String, Date> vacunas = new HashMap<>();

	private static MascotaImpl instance;
	private static ObjectMapper om = new ObjectMapper();

	private MascotaImpl() {
	}

	public static MascotaImpl getInstance() {
		if (instance == null) {
			try {
				instance = om.readValue(MASCOTA_PATH.toFile(), MascotaImpl.class);
			} catch (IOException e) {
				System.out.println("Failed to load user data.");
				instance = new MascotaImpl();
			}
		}

		return instance;
	}

	// GETTERS

	@Override
	public UUID getUUID() {
		return uuid;
	}

	@Override
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	@Override
	public String getChip() {
		return chip;
	}

	@Override
	public String getRaza() {
		return raza;
	}

	@Override
	public String getSexo() {
		return sexo;
	}

	@Override
	public int getPeso() {
		return peso;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public String getGrupoSanguineo() {
		return grupo_sanguineo;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String getPathPedigree() {
		return pathPedigree;
	}

	@Override
	public String getPathDNI() {
		return pathDNI;
	}

	@Override
	public String getPathEsterilización() {
		return pathEsterilizacion;
	}

	@Override
	public String getPathTemperaturas() {
		return pathTemperaturas;
	}

	@Override
	public ArrayList<String> getAlergias() {
		return alergias;
	}

	@Override
	public ArrayList<String> getEnfermedades() {
		return enfermedades;
	}

	@Override
	public Map<String, Date> getVacuna() {
		return vacunas;
	}

	// SETTERS

	@Override
	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public void setChip(String chip) {
		this.chip = chip;
	}

	@Override
	public void setRaza(String raza) {
		this.raza = raza;
	}

	@Override
	public void setSexo(String sexo) {
		if (!isValidSexo(sexo)) {
			return;
		}
		this.sexo = sexo;
	}

	@Override
	public void setPeso(int peso) {
		this.peso = peso;
	}

	@Override
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void setGrupoSanguineo(String grupo_sanguineo) {
		if (!isValidGrupoSanguineo(grupo_sanguineo)) {
			return;
		}
		this.grupo_sanguineo = grupo_sanguineo;
	}

	@Override
	public void setNombre(String nombre) {
		if (nombre.isEmpty()) {
			return;
		}
		this.nombre = nombre;
	}

	@Override
	public void setPathPedigree(String pathPedigree) {
		// TODO: Validar que el fichero existe
		this.pathPedigree = pathPedigree;
	}

	@Override
	public void setPathDNI(String pathDNI) {
		// TODO: Validar que el fichero existe
		this.pathDNI = pathDNI;
	}

	@Override
	public void setPathEsterilizacion(String pathEsterilizacion) {
		// TODO: Validar que el fichero existe
		this.pathEsterilizacion = pathEsterilizacion;
	}

	@Override
	public void setPathTemperaturas(String pathTemperaturas) {
		// TODO: Validar que el fichero existe
		this.pathTemperaturas = pathTemperaturas;
	}

	@Override
	public void setAlergias(ArrayList<String> alergias) {
		this.alergias = alergias;
	}

	@Override
	public void setEnfermedades(ArrayList<String> enfermedades) {
		this.enfermedades = enfermedades;
	}

	@Override
	public void setVacunas(Map<String, Date> vacunas) {
		this.vacunas = vacunas;
	}

	@Override
	public void saveM() throws StreamReadException, DatabindException, IOException {
		try {
			om.writeValue(MASCOTA_PATH.toFile(), instance);
		} catch (IOException e) {
			// try to rollback to the saved values
			reloadM();
		}
	}

	@Override
	public void reloadM() throws StreamReadException, DatabindException, IOException {
		instance = om.readValue(MASCOTA_PATH.toFile(), MascotaImpl.class);
	}

	public static boolean isValidSexo(String sexo) {
		String sexoCaseInsensitive = sexo.toLowerCase();

		return sexoCaseInsensitive.equals(MACHO) || sexoCaseInsensitive.equals(HEMBRA);
	}

	public static boolean isValidGrupoSanguineo(String grupo_sanguineo) {
		String grCaseInsensitive = grupo_sanguineo.toLowerCase();

		return grCaseInsensitive.equals(DEA11) || grCaseInsensitive.equals(DEA12) || grCaseInsensitive.equals(DEA3)
				|| grCaseInsensitive.equals(DEA4) || grCaseInsensitive.equals(DEA5) || grCaseInsensitive.equals(DEA6)
				|| grCaseInsensitive.equals(DEA7) || grCaseInsensitive.equals(DEA8);
	}

}
