package com.metal.seyed.controller;

import com.metal.seyed.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;

public class BorderPane1Controller {
    @FXML
    private MenuItem close;
    @FXML
    public void close(){
        MainApp.getPrimarystage().close();

    }
    @FXML
    public void help(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(MainApp.getPrimarystage());
        alert.setTitle("Designers");
        alert.setHeaderText("A Work By : ");
        alert.setContentText("Sajjad Musavi & Hossein Tahan");
        alert.showAndWait();
    }
}
