package org.balanpy;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Balanpy {
	static int MAX=4;
	final static int tempSup=38;
	final static int tempSup2=35;

	@FXML
	private Label temperaturaMaxima;

	@FXML
	private Label temperaturaBaja;

	@FXML
	private Label temperaturaAlta;

	@FXML
	private Label temperaturaMedia;

	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	static public Integer getTemperaturaAlta(ArrayList<Integer> array)
	{

		int contador=0;
		for(int i=0;i<array.size();i++)
		{
			if(array.get(i)>tempSup)
				contador=contador+1;

		}

		return contador;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	static public Integer getTemperaturaBaja(ArrayList<Integer> array)
	{

		int contador2=0;
		for(int i=0;i<array.size();i++)
		{

			if(array.get(i)<=tempSup2)
				contador2=contador2+1;
		}

		return contador2;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public Double getTemperaturaMedia(ArrayList<Integer> array)
	{
		/*int MAX=4;*/
		double media=0;
		double suma=0;
		double promedioTemp=0;
		for(int i=0;i<array.size();i++)
		{
			suma += array.get(i);
			media++;
		}
		return suma/media;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	static public Integer getTemperaturaMaxima(ArrayList<Integer> array)
	{
		ArrayList<Integer> array2 = new ArrayList<>(array);

		int k=0;
		for(int i=1;i<array2.size();i++)
		{
			for(int j=0;j<array2.size()-i;j++)
			{
				if(array2.get(j)>array2.get(j+1))
				{
					k=array2.get(j+1);
					array2.set(j+1, array2.get(j));
					array2.set(j, k);
				}
			}
		}
		return array2.get(array2.size()-1);
	}

	//-----------------------------------------------------------------------------------------

}



