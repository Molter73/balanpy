package org.balanpy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

public interface Mascota {

	// GETTERS

	public UUID getUUID();

	public Date getFechaNacimiento();

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

	public void setFechaNacimiento(Date fechaNacimiento);

	public void setChip(String chip);

	public void setRaza(String raza);

	public void setSexo(String sexo);

	public void setPeso(int peso);

	public void setColor(String color);

	public void setGrupoSanguineo(String grupo_sanguineo);

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

	public Map<String, Date> getVacuna(); // GETTER

	public void setVacunas(Map<String, Date> vacunas); // SETTER

	// Helper function for persisting user information
	public void saveM() throws StreamReadException, DatabindException, IOException;

	// Helper for reloading the object
	public void reloadM() throws StreamReadException, DatabindException, IOException;
}