package com.example;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BirthdayInvitationGenerator extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        String fxmlDocPath = "src/main/resources/com/example/form1.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        VBox root = (VBox) loader.load(fxmlStream);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Birthday invitation generator");
        stage.show();

    }
}