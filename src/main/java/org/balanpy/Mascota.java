package org.balanpy;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public interface Mascota {

	// GETTERS

	public UUID getUUID();

	public String getFechaNacimiento();

	public String getChip();

	public String getRaza();

	public String getSexo();

	public int getPeso();

	public String getColor();

	public String getGrupoSanguineo();

	public String getNombre();

	public String getPathPedigree();

	public String getPathDNI();

	public String getPathEsterilización();

	public String getPathTemperaturas();

	// SETTERS

	public void setUUID(UUID uuid);

	public void setFechaNacimiento(String fechaNacimiento);

	public void setChip(String chip);

	public void setRaza(String raza);

	public void setSexo(String sexo);

	public void setPeso(int peso);

	public void setColor(String color);

	public void setGrupoSanguineo(String grupoSanguineo);

	public void setNombre(String nombre);

	public void setPathPedigree(String pathPedigree);

	public void setPathDNI(String pathDNI);

	public void setPathEsterilizacion(String pathEsterilizacion);

	public void setPathTemperaturas(String pathTemperaturas);

	// ********************** Alergias *****************************

	public ArrayList<String> getAlergias(); // GETTER

	public void setAlergias(ArrayList<String> alergias); // SETTER

	// ******************** Enfermedades ***************************

	public ArrayList<String> getEnfermedades(); // GETTER

	public void setEnfermedades(ArrayList<String> enfermedades); // SETTER

	// ********************** Vacunas ******************************

	public Map<String, String> getVacuna(); // GETTER

	public void setVacunas(Map<String, String> vacunas); // SETTER

	public HigieneImpl getHigiene();

}