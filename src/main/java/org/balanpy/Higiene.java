package org.balanpy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

public interface Higiene {

	// ************************ Baño **************************

	public ArrayList<Date> getBanos(); // GETTER

	public void setBanos(ArrayList<Date> banos); // SETTER

	// ********************* Cepillado ************************

	public ArrayList<Date> getCepillado(); // GETTER

	public void setCepillado(ArrayList<Date> cepillado); // SETTER

	// ******************* Desparasitación ********************

	public Map<String, Date> getDesparasitado(); // GETTER

	public void setDesparasitado(Map<String, Date> desparasitado); // SETTER

	// ************* Productos No Recomendables ***************

	public ArrayList<String> getProductosNR(); // GETTER

	public void setProductosNR(ArrayList<String> productosNR); // SETTER

	// Helper function for persisting user information
	public void saveM() throws StreamReadException, DatabindException, IOException;

	// Helper for reloading the object
	public void reloadM() throws StreamReadException, DatabindException, IOException;
}