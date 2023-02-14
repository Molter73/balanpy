package org.balanpy;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private final static String FAILSAFE_SCREEN = "/PantallaInicio.fxml";

	@Override
	public void start(Stage primaryStage) throws Exception {
		Usuario usuario = UsuarioImpl.getInstance();
		if (usuario.isValid()) {
			BalanpyScreen.loadScene(primaryStage, "/PerfilUsuario.fxml", FAILSAFE_SCREEN);
		} else {
			BalanpyScreen.loadScene(primaryStage, "/PantallaInicio.fxml", FAILSAFE_SCREEN);
		}
	}

	// -----------------------------------------------------------------------------------------

	public static void main(String[] args) {
		launch();
	}
}