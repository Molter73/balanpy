package org.balanpy;

import java.time.LocalDateTime;

public class DatoGenerico {

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