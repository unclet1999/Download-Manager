package com.metal.seyed;

import com.metal.seyed.model.Download;
import com.metal.seyed.view.AnchorPane1Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    public static ObservableList<Download> downloads = FXCollections.observableArrayList();

    public static ObservableList<Download> getDownloads() {
        return downloads;
    }

    private static Stage primarystage;

    public static Stage getPrimarystage() {
        return primarystage;
    }

    private BorderPane pane1;
    private AnchorPane pane2;

    @Override
    public void start(Stage primarystage) throws IOException {
        this.primarystage = primarystage;
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(MainApp.class.getResource("view/BorderPane1.fxml"));
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(MainApp.class.getResource("view/AnchorPane1.fxml"));
        this.pane1 = (BorderPane) loader1.load();
        this.pane2 = (AnchorPane) loader2.load();
        this.pane1.setCenter(pane2);
        AnchorPane1Controller controller = loader2.getController();
        controller.setMainApp(this);
        primarystage.setScene(new Scene(pane1));
        primarystage.setResizable(false);
        primarystage.setTitle("Black Metal Download Manager");
        primarystage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
