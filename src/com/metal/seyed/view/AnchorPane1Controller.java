package com.metal.seyed.view;

import com.metal.seyed.MainApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.sun.deploy.config.Config.READ_TIMEOUT;
import static com.sun.xml.internal.ws.developer.JAXWSProperties.CONNECT_TIMEOUT;


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
    private ProgressBar progressBar1 = new ProgressBar();

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
            }else {


//                  JavaIO Method
//                    BufferedInputStream in = new BufferedInputStream(new URL(t).openStream());
//                    String tempName = new URL(t).toString().substring(t.lastIndexOf("/") + 1);
//                    Files.copy(in, Paths.get(tempName), StandardCopyOption.REPLACE_EXISTING);
//                    FileOutputStream out = new FileOutputStream(tempName);
//                    byte dataBuffer[] = new byte[1024];
//                    int bytesRead;
//                    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
//                        out.write(dataBuffer, 0, bytesRead);
//                    }




//                ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(getURL.getText()).openStream());
//                String tempName = new URL(t).toString().substring(t.lastIndexOf("/") + 1);
//                FileOutputStream out = new FileOutputStream( tempName );
//                FileChannel fileChannel = out.getChannel();
//                out.getChannel().transferFrom(readableByteChannel , 0 ,Long.MAX_VALUE);

                long existingFileSize = x.length();
                if (existingFileSize < size) {
                    httpConnection.setRequestProperty(
                            "Range",
                            "bytes=" + existingFileSize + "-" + size
                    );
                }
                String tempName = new URL(t).toString().substring(t.lastIndexOf("/") + 1);




            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null , e.getMessage() );
        }
    }


}
