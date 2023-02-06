package org.balanpy;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class BalanpyScreen {
	private static final double SCREEN_SCALING = 0.6;

	private static final Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	public static final double WINDOW_WIDTH = screenBounds.getWidth() * SCREEN_SCALING;
	public static final double WINDOW_HEIGHT = screenBounds.getHeight() * SCREEN_SCALING;

	public static Scene loadScene(Stage stage, String sceneName) throws IOException {
		Parent root = FXMLLoader.load(BalanpyScreen.class.getResource(sceneName));
		Scene scene = new Scene(root, BalanpyScreen.WINDOW_WIDTH, BalanpyScreen.WINDOW_HEIGHT);

		stage.setScene(scene);
		stage.setTitle("Balanpy");
		stage.show();

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
