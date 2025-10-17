package com.example.wordlejavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.awt.Desktop;
import java.net.URI;
import java.awt.*;
import java.io.IOException;

public class MainController {
    @FXML
    private Button botonJugar;

    @FXML
    private Button botonGithub;

    @FXML
    public void irJuegoView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/wordlejavafx/juego-view.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Juego");
        stage.show();
    }

    public void urlGithub() {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/Manueel8944"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
