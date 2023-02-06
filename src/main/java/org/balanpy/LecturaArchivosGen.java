package org.balanpy;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LecturaArchivosGen {

	@FXML
	private Label fileNameLabel;

	@FXML
	private TextArea fileContentTextArea;

	public void openFile(File file) throws IOException {

		// Obtiene el contenido del archivo
		String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), StandardCharsets.UTF_8);

		// Carga la vista de archivo
		FXMLLoader loader = new FXMLLoader(getClass().getResource("file_view.fxml"));

		loader.setController(this);

		AnchorPane root = loader.load();

		// Configura la escena y el stage
		Scene scene = new Scene(root);
		Stage stage = new Stage();

		stage.setScene(scene);

		stage.setTitle("Archivo: " + file.getName());

		stage.initModality(Modality.APPLICATION_MODAL);

		// Muestra el contenido del archivo
		fileNameLabel.setText(file.getName());

		fileContentTextArea.setText(content);

		stage.showAndWait();
	}
}

/*
 * Como usar la clase
 *
 * File file = new File("sample.txt"); LecturaArchivosGen fileOpener = new
 * LecturaArchivosGen(); fileOpener.openFile(file);
 */