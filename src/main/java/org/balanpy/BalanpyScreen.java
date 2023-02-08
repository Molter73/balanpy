package org.balanpy;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BalanpyScreen {
	public static final double WINDOW_WIDTH = 600;
	public static final double WINDOW_HEIGHT = 800;

	private static String previousScreen = "/PantallaInicio.fxml";

	public static Scene loadScene(Stage stage, String sceneName, String origin) throws IOException {
		Parent root = FXMLLoader.load(BalanpyScreen.class.getResource(sceneName));
		Scene scene = new Scene(root, BalanpyScreen.WINDOW_WIDTH, BalanpyScreen.WINDOW_HEIGHT);

		stage.setScene(scene);
		stage.setTitle("Balanpy");
		stage.show();

		previousScreen = origin;
		return scene;
	}

	public static Scene back(Stage stage, String origin) throws IOException {
		Scene scene = loadScene(stage, previousScreen, previousScreen);
		previousScreen = origin;
		return scene;
	}

	public static void quickPopUp(Stage primaryStage, String msg) {
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(primaryStage);

		VBox dialogVbox = new VBox(20);
		dialogVbox.getChildren().add(new Text(msg));

		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		dialog.setScene(dialogScene);
		dialog.show();
	}
}
