package org.balanpy;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/*
 * * La información de la Mascota se guardará en un fichero
 * JSON con la siguiente información:
 * 
    "higiene": {
      "ultimo_bano": "02/02/2022",
      "ultimo_cepillado": "02/02/2022",
      "ultimo_desparasitado": {
        "interno": "02/02/2022",
        "externo": "02/02/2022"
      },
      "productos_no_recomendables": []
    }
  }
]
 */


public interface Higiene {

	//************************ Baño **************************
	
	public ArrayList<Date> getBanos();		//GETTER
	
	public void setBanos(ArrayList<Date> banos);		//SETTER
	
	
	//********************* Cepillado ************************
	
	public ArrayList<Date> getCepillado();	//GETTER
		
	public void setCepillado(ArrayList<Date> cepillado);	//SETTER
	
	
	//******************* Desparasitación ********************

	public Map<String, Date> getDesparasitado();	//GETTER

	public void setDesparasitado(Map<String, Date> desparasitado);	//SETTER
	
	
	//************* Productos No Recomendables ***************
	
	public ArrayList<String> getProductosNR();	//GETTER
			
	public void setProductosNR(ArrayList<String> productosNR);	//SETTER
	
	
	// Special functionality

	// Helper function for persisting user information
	public void save();

	// Helper for reloading the object
	public void reload();
	
	
}











