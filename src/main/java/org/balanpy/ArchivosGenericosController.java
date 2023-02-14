package org.balanpy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ArchivosGenericosController {
	@FXML
	private Label fileNameLabel;

	@FXML
	private TextArea fileContentTextArea;

	private static Path file;

	public static void setPath(Path f) {
		file = f;
	}

	public void initialize() throws IOException {
		// Muestra el contenido del archivo
		fileNameLabel.setText(file.toString());

		// Obtiene el contenido del archivo
		String content = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
		fileContentTextArea.setText(content);
	}
}
