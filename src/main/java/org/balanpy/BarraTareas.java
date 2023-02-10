package org.balanpy;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class BarraTareas implements Initializable {

	private static final double SCREEN_SCALING = 0.6;

	private static final Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	private static final double WINDOW_WIDTH = screenBounds.getWidth() * SCREEN_SCALING;
	private static final double WINDOW_HEIGHT = screenBounds.getHeight() * SCREEN_SCALING;

	@FXML
	VBox perfilUsuario;
	@FXML
	VBox portada;
	@FXML
	VBox pulsaciones;
	@FXML
	VBox temperatura;
	@FXML
	VBox veterinario;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void PortadaAplicacion(MouseEvent event) throws Exception {

		try {

			if ((VBox) event.getSource() == portada) {

				Stage stage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/PortadaAplicacion.fxml"));
				Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
				stage.setScene(scene);
				stage.show();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	public void Pulsaciones(MouseEvent event) throws Exception {

		try {

			if ((VBox) event.getSource() == pulsaciones) {

				Stage stage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/Pulsaciones.fxml"));
				Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
				stage.setScene(scene);
				stage.show();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	public void Temperaturas(MouseEvent event) throws Exception {

		try {

			if ((VBox) event.getSource() == temperatura) {

				Stage stage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/Clima.fxml"));
				Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
				stage.setScene(scene);
				stage.show();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	public void PerfilVeterinario(MouseEvent event) throws Exception {

		try {

			if ((VBox) event.getSource() == veterinario) {

				Stage stage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/PerfilVeterinario.fxml"));
				Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
				stage.setScene(scene);
				stage.show();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	public void PerfilUsuario(MouseEvent event) throws Exception {

		try {

			if ((VBox) event.getSource() == perfilUsuario) {

				Stage stage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/PerfilUsuario.fxml"));
				Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
				stage.setScene(scene);
				stage.show();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
