package org.balanpy;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MascotaImpl implements Mascota {

	private static final String MACHO = "macho";
	private static final String HEMBRA = "hembra";
	private static final String DEA11 = "DEA11";
	private static final String DEA12 = "DEA12";
	private static final String DEA3 = "DEA3";
	private static final String DEA4 = "DEA4";
	private static final String DEA5 = "DEA5";
	private static final String DEA6 = "DEA6";
	private static final String DEA7 = "DEA7";
	private static final String DEA8 = "DEA8";

	// ------------------------------ MASCOTA -----------------------------------
	@JsonProperty(value = "UUID")
	private UUID uuid;

	@JsonProperty(value = "fecha_nacimiento")
	private String fechaNacimiento;

	@JsonProperty(value = "CHIP")
	private String chip = "";

	private String raza = "";

	private String sexo = "";

	private int peso = 0;

	private String color = "";

	@JsonProperty(value = "grupo_sanguineo")
	private String grupoSanguineo = "";

	private String nombre = "";

	private Boolean esterilizado = false;

	private ArrayList<String> alergias = new ArrayList<>();

	private ArrayList<String> enfermedades = new ArrayList<>();

	private Map<String, String> vacunas = new HashMap<>();

	// -----------------------------------------------------------------------------

	private HigieneImpl higiene = new HigieneImpl();

	@Override
	public HigieneImpl getHigiene() {
		return higiene;
	}

	// -----------------------------------------------------------------------------

	public MascotaImpl() {

	}

	// --------------- GETTERS MASCOTAS -------------------------------------------

	@Override
	public UUID getUUID() {
		return uuid;
	}

	@Override
	public String getFechaNacimiento() {
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
		return grupoSanguineo;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public Boolean getEsterilizado() {
		return esterilizado;
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
	public Map<String, String> getVacuna() {
		return vacunas;
	}

	@Override
	@JsonIgnore
	public Integer getEdad() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate now = LocalDate.now();
		LocalDate nacimiento = LocalDate.parse(fechaNacimiento, df);

		return Period.between(nacimiento, now).getYears();
	}

	// --------------- SETTERS MASCOTAS -------------------------------------------

	@Override
	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public void setFechaNacimiento(String fechaNacimiento) {
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
	public void setGrupoSanguineo(String grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	@Override
	public void setNombre(String nombre) {
		if (nombre.isEmpty()) {
			return;
		}
		this.nombre = nombre;
	}

	@Override
	public void setEsterilizado(Boolean e) {
		esterilizado = e;
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
	public void setVacunas(Map<String, String> vacunas) {
		this.vacunas = vacunas;
	}

	// ----------------------------------------------------------------------------

	public static boolean isValidSexo(String sexo) {
		String sexoCaseInsensitive = sexo.toLowerCase();

		return sexoCaseInsensitive.equals(MACHO) || sexoCaseInsensitive.equals(HEMBRA);
	}

	public static boolean isValidGrupoSanguineo(String grupoSanguineo) {

		return grupoSanguineo.equals(DEA11) || grupoSanguineo.equals(DEA12) || grupoSanguineo.equals(DEA3)
				|| grupoSanguineo.equals(DEA4) || grupoSanguineo.equals(DEA5) || grupoSanguineo.equals(DEA6)
				|| grupoSanguineo.equals(DEA7) || grupoSanguineo.equals(DEA8);
	}

}