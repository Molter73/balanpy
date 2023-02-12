package org.balanpy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HigieneImpl implements Higiene {

	@JsonProperty(value = "ultimo_bano")
	private String bano;

	@JsonProperty(value = "ultimo_cepillado")
	private String cepillado;

	@JsonProperty(value = "ultimo_desparasitado")
	private Map<String, String> desparasitado = new HashMap<>();

	@JsonProperty(value = "productos_no_recomendables")
	private ArrayList<String> productosNR = new ArrayList<>();

	public HigieneImpl() {

	}

	// --------------- GETTERS HIGIENE -------------------------------------------

	@Override
	public String getBano() {
		return bano;
	}

	@Override
	public String getCepillado() {
		return cepillado;
	}

	@Override
	public Map<String, String> getDesparasitado() {
		return desparasitado;
	}

	@Override
	public ArrayList<String> getProductosNR() {
		return productosNR;
	}

	// --------------- SETTERS HIGIENE -------------------------------------------

	@Override
	public void setBano(String bano) {
		this.bano = bano;
	}

	@Override
	public void setCepillado(String cepillado) {
		this.cepillado = cepillado;
	}

	@Override
	public void setDesparasitado(Map<String, String> desparasitado) {
		this.desparasitado = desparasitado;
	}

	@Override
	public void setProductosNR(ArrayList<String> productosNR) {
		this.productosNR = productosNR;
	}

}
