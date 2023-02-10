package org.balanpy;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ManejoDatos {

	private ArrayList<Double> temperaturas;

	private ArrayList<Integer> pulsaciones;

	public ManejoDatos() {

		temperaturas = new ArrayList<>();

		pulsaciones = new ArrayList<>();

	}

	class DatoGenerico {

		private LocalDateTime fecha;

		private Integer dato;

		public LocalDateTime getFecha() {
			return fecha;
		}

		public void setFecha(LocalDateTime fecha) {
			this.fecha = fecha;
		}

		public Integer getDato() {
			return dato;
		}

		public void setDato(Integer dato) {
			this.dato = dato;
		}

	}

	class Temperaturas {

		private ArrayList<DatoGenerico> datos;

		public ArrayList<DatoGenerico> getDatos() {
			return datos;
		}

		public void setDatos(ArrayList<DatoGenerico> datos) {
			this.datos = datos;
		}

		public void imprimeDatos(ArrayList<DatoGenerico> datos) {

			for (DatoGenerico d : datos) {

				System.out.println(d + "º.");

			}

		}

	}

	class Pulsaciones {

		private ArrayList<DatoGenerico> datos;

		public ArrayList<DatoGenerico> getDatos() {
			return datos;
		}

		public void setDatos(ArrayList<DatoGenerico> datos) {
			this.datos = datos;
		}

		public void imprimeDatos(ArrayList<DatoGenerico> datos) {

			for (DatoGenerico d : datos) {

				System.out.println(d + "ppm.");

			}

		}
	}

}
