package org.balanpy;

import javafx.scene.input.MouseEvent;

public class Inicio {
	private UsuarioImpl usuario;

	public Inicio() {
		usuario = UsuarioImpl.getInstance();
	}

	public void HandleDone(MouseEvent event) throws Exception {

		System.out.println("Está funcionando crack");

	}

}
