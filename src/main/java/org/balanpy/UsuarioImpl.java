package org.balanpy;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 *  UsuarioImpl es un singleton para evitar tener que copiar el
 *  objeto cada vez que se cambia de pantalla, recargando los
 *  datos desde disco innecesariamente.
 *
 *  TODO: Las funciones de validación deberían lanzar una excepción
 *  para permitir mejor manejo de error.
 */
public class UsuarioImpl implements Usuario {
	private static final String USUARIO_PATH = "appdata/usuario.json";
	private static final String MASCULINO = "masculino";
	private static final String FEMENINO = "femenino";
	private static final Pattern NIE_PATTERN = Pattern.compile("^\\p{Upper}\\d{7}\\p{Upper}$");
	private static final Pattern DNI_PATTERN = Pattern.compile("^\\d{8}\\p{Upper}$");
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

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
		if (!isValidDNI(dni)) {
			return;
		}
		this.dni = dni;
	}

	@Override
	public void setNombre(String nombre) {
		if (nombre.isEmpty()) {
			return;
		}
		this.nombre = nombre;
	}

	@Override
	public void setTelefono(String telefono) {
		// TODO Validar formato telefono
		this.telefono = telefono;
	}

	@Override
	public void setEmail(String email) {
		if (!isValidEmail(email)) {
			return;
		}
		this.email = email;
	}

	@Override
	public void setSexo(String sexo) {
		if (!isValidSexo(sexo)) {
			return;
		}
		this.sexo = sexo;
	}

	@Override
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public void setPathPPP(String pathPPP) {
		// TODO: Validar que el fichero existe
		this.pathPPP = pathPPP;
	}

	@Override
	public void setPathSeguro(String seguroPath) {
		// TODO: Validar que el fichero existe
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

	public boolean isValid() {
		// Como el dni es nuestro indicador unico para cada usuario,
		// si este está vacío asumimos que el usuario no existe.
		return isValidDNI(dni) && !nombre.isEmpty() && isValidEmail(email) && isValidSexo(sexo);
	}

	public static boolean isValidSexo(String sexo) {
		String sexoCaseInsensitive = sexo.toLowerCase();

		return sexoCaseInsensitive.equals(MASCULINO) || sexoCaseInsensitive.equals(FEMENINO);
	}


	public static boolean isValidDNI(String dni) {
		return DNI_PATTERN.matcher(dni).matches() || NIE_PATTERN.matcher(dni).matches();
	}

	public static boolean isValidEmail(String email) {
		return EMAIL_PATTERN.matcher(email).matches();
	}
}

