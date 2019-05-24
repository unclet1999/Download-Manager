package com.metal.seyed.view;

import com.metal.seyed.MainApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class AnchorPane1Controller {

    @FXML
    private TitledPane document;

    @FXML
    private TitledPane compressed;

    @FXML
    private TitledPane media;

    @FXML
    private TitledPane others;

    @FXML
    private TitledPane pdf;

    @FXML
    private TitledPane doc;

    @FXML
    private TitledPane docx;

    @FXML
    private TitledPane zip;

    @FXML
    private TitledPane rar;

    @FXML
    private TitledPane mp3;

    @FXML
    private TitledPane mp4;

    @FXML
    private Tab downloading;

    @FXML
    private Tab queue;

    @FXML
    private Button addURL;

    @FXML
    private TextField getURL;

    @FXML
    public void getURLfromTextField(ActionEvent event){
        try {
            String t = this.getURL.getText();
            if (t.equals("") ){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(MainApp.getPrimarystage());
                alert.setTitle("Error");
                alert.setHeaderText("Text Field is Empty");
                alert.setContentText("Please Enter A URL");
                alert.showAndWait();
            }

            BufferedInputStream in = new BufferedInputStream(new URL(t).openStream());
            String tempName = new URL(t).toString().substring(t.lastIndexOf("/") + 1);
            Files.copy(in, Paths.get(tempName), StandardCopyOption.REPLACE_EXISTING);
            FileOutputStream out = new FileOutputStream(tempName);
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while( (bytesRead = in.read(dataBuffer , 0 , 1024)) != -1 ){
                out.write(dataBuffer , 0 , bytesRead);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null , e.getMessage() );
        }
    }


}
