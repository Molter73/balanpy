package org.balanpy;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HigieneImpl implements Higiene {

	private static final Path HIGIENE_PATH = Paths.get(System.getenv("BALANPY_CONFIG_DIR"), "mascotas.json");

	private ArrayList<Date> banos = new ArrayList();

	private ArrayList<Date> cepillado = new ArrayList();

	private Map<String, Date> desparasitado = new HashMap<>();

	private ArrayList<String> productosNR = new ArrayList();

	private static HigieneImpl instance;
	private static ObjectMapper om = new ObjectMapper();

	private HigieneImpl() {
	}

	public static HigieneImpl getInstance() {
		if (instance == null) {
			try {
				instance = om.readValue(HIGIENE_PATH.toFile(), HigieneImpl.class);
			} catch (IOException e) {
				System.out.println("Failed to load user data.");
				instance = new HigieneImpl();
			}
		}

		return instance;
	}

	// GETTERS

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

	// SETTERS

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

	@Override
	public void saveM() throws StreamReadException, DatabindException, IOException {
		try {
			om.writeValue(HIGIENE_PATH.toFile(), instance);
		} catch (IOException e) {
			// try to rollback to the saved values
			reloadM();
		}
	}

	@Override
	public void reloadM() throws StreamReadException, DatabindException, IOException {
		instance = om.readValue(HIGIENE_PATH.toFile(), HigieneImpl.class);
	}

}
