package org.balanpy;

import java.util.ArrayList;
import java.util.Map;

public interface Higiene {

	// ************************ Baño **************************

	public String getBano(); // GETTER

	public void setBano(String bano); // SETTER

	// ********************* Cepillado ************************

	public String getCepillado(); // GETTER

	public void setCepillado(String cepillado); // SETTER

	// ******************* Desparasitación ********************

	public Map<String, String> getDesparasitado(); // GETTER

	public void setDesparasitado(Map<String, String> desparasitado); // SETTER

	// ************* Productos No Recomendables ***************

	public ArrayList<String> getProductosNR(); // GETTER

	public void setProductosNR(ArrayList<String> productosNR); // SETTER
}