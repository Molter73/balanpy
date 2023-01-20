package org.balanpy;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/*
 * * La información de la Mascota se guardará en un fichero
 * JSON con la siguiente información:
 * 
 * mascotas:
[
  {
    "UUID": "123456",
    "fecha_nacimiento": "02/02/2022",
    "numero_chip": "ABCDE12345",
    "raza": "pitbull",
    "sexo": "masculino",
    "peso": 25,
    "color": "blanco",
    "grupo_sanguineo": "A",
    "nombre": "Firulais",
    "archivo_pedigree": "123456/pedigree.txt",
    "archivo_dni": "123456/dni.txt",
    "archivo_esterilizacion": "123456/esterilizacion.txt",
    "temperaturas": "123456/temperaturas.json",
    "alergias": [
      "polen",
      "cereales"
    ],
    "vacunas": {
      "ultima": "02/02/2022",
      "proxima": "02/02/2023"
    },
    "enfermedades": [],
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


public interface Mascota {
	
	//GETTERS
	
	public int getUUID();
	
	public Date getFechaNacimiento();
	
	public String getChip();
	
	public String getRaza();
	
	public String getSexoMascota();
	
	public int getPeso();
	
	public String getColor();
	
	public String getGrupoSanguineo();
	
	public String getNombreMascota();
	
	public String getPathPedigree();
	
	public String getPathDNI();
	
	public String getPathEsterilización();
	
	public String getPathTemperaturas();


	//SETTERS
	
	public void setUUID(int uuid);

	public void setFechaNacimiento(Date fecha_nacimiento);
	
	public void setChip(String chip);

	public void setRaza(String raza);

	public void setSexoMascota(String sexo_mascota);
	
	public void setPeso(int peso);
	
	public void setColor(String color);

	public void setGrupoSanguineo(String grp_sanguineo);

	public void setNombreMascota(String nom_mascota);

	public void setPathPedigree(String pathPedigree);

	public void setPathDNI(String pathDNI);

	public void setPathEsterilizacion(String pathEsterilizacion);

	public void setPathTemperaturas(String pathTemperaturas);

	
	//********************** Alergias *****************************
	
	public ArrayList<String> getAlergias();		//GETTER
	
	public void setAlergias(ArrayList<String> alergias);		//SETTER
	
	
	
	
	//******************** Enfermedades ***************************

	public String[] getEnfermedades();		//GETTER

	public void setEnfermedades(String [] enfermedades);		//SETTER
	
	
	
	//********************** Vacunas ******************************
	
	public Map<String, Date> getVacuna();		//GETTER

	public void setVacunas(Map<String, Date> vacunas);		//SETTER

	
	
	// Special functionality

	// Helper function for persisting user information
	public void save();

	// Helper for reloading the object
	public void reload();
	
	

}
