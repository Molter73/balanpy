package org.balanpy;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
	static ArrayList<Integer> temperaturas = new ArrayList<Integer>();
	private static final double SCREEN_SCALING = 0.6;

	private static final Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	private static final double WINDOW_WIDTH = screenBounds.getWidth() * SCREEN_SCALING;
	private static final double WINDOW_HEIGHT = screenBounds.getHeight() * SCREEN_SCALING;

	@FXML
	private GridPane gridPane;

	@Override
	public void start(Stage primaryStage) throws Exception {
		LoadScene(primaryStage, "/PantallaInicio.fxml");
	}

	//-----------------------------------------------------------------------------------------

	public void HandleDone(ActionEvent event) throws Exception {
		for (Node node : gridPane.getChildren()) {
			if (node instanceof TextField) {
				TextField tf = (TextField)node;
				String text = tf.getText();

				if (text.isEmpty()) {
					continue;
				}

				Integer temp = Integer.parseInt(text);
				temperaturas.add(temp);
			}
		}

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = LoadScene(stage, "/MainMenu.fxml");

		Label temperaturaMaxima = (Label) scene.lookup("#temperaturaMaxima");
		temperaturaMaxima.setText(Balanpy.getTemperaturaMaxima(temperaturas).toString());

		Label temperaturaBaja = (Label) scene.lookup("#temperaturaBaja");
		temperaturaBaja.setText(Balanpy.getTemperaturaBaja(temperaturas).toString());

		Label temperaturaAlta = (Label) scene.lookup("#temperaturaAlta");
		temperaturaAlta.setText(Balanpy.getTemperaturaAlta(temperaturas).toString());

		Label temperaturaMedia = (Label) scene.lookup("#temperaturaMedia");
		temperaturaMedia.setText(Balanpy.getTemperaturaMedia(temperaturas).toString());
	}

	//-----------------------------------------------------------------------------------------

	private Scene LoadScene(Stage stage, String sceneName) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(sceneName));
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		stage.setScene(scene);
		stage.setTitle("Balanpy");
		stage.show();

		return scene;
	}
	//-----------------------------------------------------------------------------------------
	public static void main(String[] args)
	{
		launch();
	}
}
