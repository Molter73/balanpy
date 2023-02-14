package org.balanpy;

import java.io.IOException;
import java.nio.file.Path;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LecturaArchivosGen {

	public static void openFile(Path file) throws IOException {
		ArchivosGenericosController.setPath(file);

		// Carga la vista de archivo
		FXMLLoader loader = new FXMLLoader(LecturaArchivosGen.class.getResource("/FileView.fxml"));

		VBox root = loader.load();

		// Configura la escena y el stage
		Scene scene = new Scene(root);
		Stage stage = new Stage();

		stage.setScene(scene);

		stage.setTitle("Archivo: " + file.toString());

		stage.initModality(Modality.APPLICATION_MODAL);

		stage.showAndWait();
	}
}