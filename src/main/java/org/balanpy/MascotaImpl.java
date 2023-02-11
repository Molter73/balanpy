package org.balanpy;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

	private Date fechaNacimiento;

	@JsonProperty(value = "CHIP")
	private String chip = "";

	private String raza = "";

	private String sexo = "";

	private int peso = 0;

	private String color = "";

	private String grupoSanguineo = "";

	private String nombre = "";

	@JsonProperty(value = "archivo_pedigree")
	private String pathPedigree = "";

	@JsonProperty(value = "archivo_dni")
	private String pathDNI = "";

	@JsonProperty(value = "archivo_esterilizacion")
	private String pathEsterilizacion = "";

	@JsonProperty(value = "temperaturas")
	private String pathTemperaturas = "";

	private ArrayList<String> alergias = new ArrayList<>();

	private ArrayList<String> enfermedades = new ArrayList<>();

	private Map<String, Date> vacunas = new HashMap<>();

	// -----------------------------------------------------------------------------

	private HigieneImpl higiene = new HigieneImpl();

	public HigieneImpl getHigiene() {
		return higiene;
	}

	// -----------------------------------------------------------------------------

	private MascotaImpl() {

	}

	// --------------- GETTERS MASCOTAS -------------------------------------------

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
		return grupoSanguineo;
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

	// --------------- SETTERS MASCOTAS -------------------------------------------

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

	public class HigieneImpl implements Higiene {

		private ArrayList<Date> banos = new ArrayList<>();

		private ArrayList<Date> cepillado = new ArrayList<>();

		private Map<String, Date> desparasitado = new HashMap<>();

		private ArrayList<String> productosNR = new ArrayList<>();

		private HigieneImpl() {

		}

		// --------------- GETTERS HIGIENE -------------------------------------------

		@Override
		public ArrayList<Date> getBanos() {
			return banos;
		}

		@Override
		public ArrayList<Date> getCepillado() {
			return cepillado;
		}

		@Override
		public Map<String, Date> getDesparasitado() {
			return desparasitado;
		}

		@Override
		public ArrayList<String> getProductosNR() {
			return productosNR;
		}

		// --------------- SETTERS HIGIENE -------------------------------------------

		@Override
		public void setBanos(ArrayList<Date> banos) {
			this.banos = banos;
		}

		@Override
		public void setCepillado(ArrayList<Date> cepillado) {
			this.cepillado = cepillado;
		}

		@Override
		public void setDesparasitado(Map<String, Date> desparasitado) {
			this.desparasitado = desparasitado;
		}

		@Override
		public void setProductosNR(ArrayList<String> productosNR) {
			this.productosNR = productosNR;
		}

	}
}