package org.balanpy;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 *  UsuarioImpl es un singleton para evitar tener que copiar el
 *  objeto cada vez que se cambia de pantalla, recargando los
 *  datos desde disco innecesariamente.
 */
public class UsuarioImpl implements Usuario {
	public static final String USUARIO_PATH = "appdata/usuario.json";

	@JsonProperty(value = "DNI")
	private String dni = "";
	private String nombre = "";
	private String telefono = "";
	private String email = "";
	private String sexo = "";
	private String direccion = "";
	@JsonProperty(value = "archivo_ppp")
	private String pathPPP = "";
	@JsonProperty(value = "archivo_seguro")
	private String pathSeguro = "";

	private static UsuarioImpl instance;
	private static ObjectMapper om = new ObjectMapper();

	private UsuarioImpl() {
	}

	public static UsuarioImpl getInstance() {
		if (instance == null) {
			try {
				instance = om.readValue(new File(USUARIO_PATH), UsuarioImpl.class);
			} catch (IOException e) {
				System.out.println("Failed to load user data.");
				instance = new UsuarioImpl();
			}
		}

		return instance;
	}

	@Override
	public String getDNI() {
		return dni;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String getTelefono() {
		return telefono;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getSexo() {
		return sexo;
	}

	@Override
	public String getDireccion() {
		return direccion;
	}

	@Override
	public String getPathPPP() {
		return pathPPP;
	}

	@Override
	public String getPathSeguro() {
		return pathSeguro;
	}

	@Override
	public void setDNI(String dni) {
		// TODO Validar formato DNI
		this.dni = dni;
	}

	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void setTelefono(String telefono) {
		// TODO Validar formato telefono
		this.telefono = telefono;
	}

	@Override
	public void setEmail(String email) {
		// TODO Validar formato email
		this.email = email;
	}

	@Override
	public void setSexo(String sexo) {
		// TODO Validar sexo
		this.sexo = sexo;
	}

	@Override
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public void setPathPPP(String pathPPP) {
		this.pathPPP = pathPPP;
	}

	@Override
	public void setPathSeguro(String seguroPath) {
		this.pathSeguro = seguroPath;
	}

	@Override
	public void save() throws StreamReadException, DatabindException, IOException {
		try {
			om.writeValue(new File(USUARIO_PATH), instance);
		} catch (IOException e) {
			// try to rollback to the saved values
			reload();
		}
	}

	@Override
	public void reload() throws StreamReadException, DatabindException, IOException {
		instance = om.readValue(new File(USUARIO_PATH), UsuarioImpl.class);
	}

	public boolean isEmpty() {
		// Como el dni es nuestro indicador unico para cada usuario,
		// si este está vacío asumimos que el usuario no existe.
		return dni.isEmpty();
	}
}
