package com.metal.seyed.view;

import com.metal.seyed.MainApp;
import com.metal.seyed.model.Download;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.lang.Thread;

public class AnchorPane1Controller extends Thread{

    public ArrayList<Download> parallelDownload = new ArrayList<Download>();

    @FXML
    private TableView<Download> tableView;

    @FXML
    private TableColumn<Download , String> tableColumn1;

    @FXML
    private TableColumn<Download , String> tableColumn3;

    @FXML
    private TableColumn<Download , String> tableColumn2;

    @FXML
    private TableColumn<Download , String> tableColumn4;

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
    private ProgressBar progressBar1 = new ProgressBar(0);

    @FXML
    private ProgressIndicator progressIndicator1 = new ProgressIndicator(0);

    @FXML
    public void setProgressIndicator1(ActionEvent event){
        //if ()
        progressIndicator1.setProgress(getPercent());
    }

    public double getPercent(){
        try {
            int index = tableView.getSelectionModel().getSelectedIndex();
            double x =(double) (this.parallelDownload.get(index).CurrentSize()/this.parallelDownload.get(index).getSize() );
            return x;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    private MainApp mainApp;

    @FXML
    public void ShowProgressbar1(){
        progressBar1.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(newValue.doubleValue() == 1){
                    ///
                }
            }
        });
    }

    @FXML
    public void ShowProgressIndicator1(){
        progressIndicator1.indeterminateProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

            }
        });
    }

//    @FXML
//    public void PauseDownload(){
//
//        Thread p = new Thread(){
//            @Override
//            public void run(){
//                int index = tableView.getSelectionModel().getSelectedIndex();
//                Download t = MainApp.getDownloads().get(index);
//                for(int i=0;i<parallelDownload.size();i++){
//                    if(t.compareTo(parallelDownload.get(i))==0){
//                        parallelDownload.get(i).sleep(100000000);
//                    }
//                }
//            }
//        }.start();
//    }

    @FXML
    public void StopDownload(){
        try {
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            for (int i=0;i<parallelDownload.size();i++){
                if (parallelDownload.get(i).compareTo(MainApp.getDownloads().get(selectedIndex)) == 0){
                    parallelDownload.get(i).stop();
                    parallelDownload.remove(i);
                    MainApp.getDownloads().get(selectedIndex).setDownloading(false);
                    MainApp.getDownloads().get(selectedIndex).CheckDownloading();
                }
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null , e.getMessage());
        }
    }

    @FXML
    public void RemoveDownload(){
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Nothing Selected");
            alert.setContentText("F**k !");
            alert.showAndWait();
        }
        else {
            for (int i=0;i<parallelDownload.size();i++){
                if(parallelDownload.get(i).compareTo(MainApp.getDownloads().get(selectedIndex)) == 0){
                    parallelDownload.remove(i);
                }
            }
            MainApp.getDownloads().remove(selectedIndex);
        }
        //tableView.getItems().remove(selectedIndex);
    }

    @FXML
    public void StartDownload(){
        try {
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            if(selectedIndex == -1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Nothing Selected");
                alert.setContentText("F**k !");
                alert.showAndWait();
            }
            else{
//                boolean q = parallelDownload.contains(MainApp.getDownloads().get(selectedIndex));
//                if (q){
//                    int k = -1;
//                    for (int i=0;i<parallelDownload.size();i++){
//                        if(MainApp.getDownloads().get(selectedIndex).compareTo(parallelDownload.get(i))==0){
//                            k = i;
//                        }
//                    }
//                    if (k != -1){
//                        parallelDownload.get(k).resume();
//                    }
//                }
//                else {
                    MainApp.getDownloads().get(selectedIndex).setDownloading(true);
                    MainApp.getDownloads().get(selectedIndex).CheckDownloading();
                    Download x = new Download(MainApp.getDownloads().get(selectedIndex).getUrl());
                    parallelDownload.add(x);
//            MainApp.getDownloads().get(selectedIndex).setDownloading(false);
//            MainApp.getDownloads().get(selectedIndex).CheckDownloading();
//            initialize();
//            setMainApp(mainApp);
                    x.start();
//                }
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null , e.getMessage());
        }
    }

    @FXML
    private void initialize(){
        tableColumn1.setCellValueFactory( cellData -> cellData.getValue().downloadNameProperty() );
        tableColumn2.setCellValueFactory( cellData -> cellData.getValue().sizePropertyProperty() );
        tableColumn3.setCellValueFactory( cellData -> cellData.getValue().extentionProperty() );
        tableColumn4.setCellValueFactory( cellData -> cellData.getValue().isDownloadingPropertyProperty() );
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        tableView.setItems(MainApp.getDownloads());
    }

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
                MainApp.getDownloads().add(new Download(new URL(t)));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(MainApp.getPrimarystage());
                alert.setTitle("Here We Go");
                alert.setHeaderText("Download Added to Queue");
                alert.setContentText("Enjoy it!");
                alert.showAndWait();
                this.getURL.setText("");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null , e.getMessage() );
        }
    }
}
