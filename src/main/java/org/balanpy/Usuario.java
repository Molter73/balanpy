package org.balanpy;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

/*
 * La información del usuario se guardará en un fichero
 * JSON con la siguiente información:

usuario: {
  "DNI": "29863077A",
  "nombre": "Jorge Fulanito",
  "telefono": "+3412345678",
  "email": "jfulanito@hotmail.com",
  "sexo": "masculino",
  "direccion": "Calle False 123",
  "archivo_ppp": "29863077A/ppp.txt",
  "archivo_seguro": "29863077A/seguro.txt"
}

 */
public interface Usuario {
	// getters
	public String getDNI();

	public Integer getEdad();

	public String getNombre();

	public String getTelefono();

	public String getEmail();

	public String getSexo();

	public String getDireccion();

	public String getPathPPP();

	public String getPathSeguro();

	// setters
	public void setDNI(String dni) throws UsuarioException;

	public void setEdad(Integer edad);

	public void setNombre(String nombre) throws UsuarioException;

	public void setTelefono(String telefono);

	public void setEmail(String email) throws UsuarioException;

	public void setSexo(String sexo) throws UsuarioException;

	public void setDireccion(String direccion);

	public void setPathPPP(String pathPPP);

	public void setPathSeguro(String seguroPath);

	// Special functionality

	// Helper function for persisting user information
	public void save() throws StreamReadException, DatabindException, IOException;

	// Helper for reloading the object
	public Usuario reload() throws StreamReadException, DatabindException, IOException;

	// Checks if the user has valid data
	public boolean isValid();
}