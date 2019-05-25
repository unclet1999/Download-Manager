package com.metal.seyed.model;

import com.metal.seyed.MainApp;
import javafx.beans.property.*;
import javafx.scene.control.Alert;
import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;



public class Download extends Thread implements Comparable<Download> {

    public int compareTo(Download x){
        if(this.getUrl().sameFile(x.getUrl())){
            return 0;
        }
        else
            return -1;
    }

    private String name;

    private URL url;

    private FileOutputStream file;

    private ReadableByteChannel readableByteChannel;

    private boolean isDownloading;

    private boolean isDownloaded;

    private FileChannel fileChannel;

    private File fileSave;

    private StringProperty downloadName;

    private StringProperty extention;

    private StringProperty sizeProperty;

    private StringProperty isDownloadingProperty;

    public void setDownloading(boolean downloading) {
        isDownloading = downloading;
    }

    public StringProperty downloadNameProperty() {
        return downloadName;
    }

    public String getExtention() {
        return extention.get();
    }

    public StringProperty extentionProperty() {
        return extention;
    }

    public String getSizeProperty() {
        return sizeProperty.get();
    }

    public StringProperty sizePropertyProperty() {
        return sizeProperty;
    }

    public String getIsDownloadingProperty() {
        return isDownloadingProperty.get();
    }

    public StringProperty isDownloadingPropertyProperty() {
        return isDownloadingProperty;
    }

    public Download(URL url) throws Exception {
        try {
            this.url = url;
            nameByurl();
            isDownloaded = false;
            this.file = new FileOutputStream(this.name);
            this.fileChannel = file.getChannel();
            this.readableByteChannel = Channels.newChannel(url.openStream());
            this.fileSave = new File(this.name);
            this.downloadName = new SimpleStringProperty(this.name);
            getSize();
            getExtentionNameProperty();
            CheckDownloading();
            this.fileSave = new File("C:\\Users\\Uncle\\Documents\\BlackMetalDownloadManager\\"+this.name);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null , e.getMessage());
        }catch (IOException e){
            JOptionPane.showMessageDialog(null , e.getMessage());
        }
    }

    public long CurrentSize(){
        return this.fileSave.length();
    }

    public String getDownloadName() {
        return name;
    }

    public void setDownloadName(String name) {
        this.name = name;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void nameByurl(){
        this.name = this.url.toString().substring(this.url.toString().lastIndexOf("/") + 1 );
    }

    @Override
    public void run(){
        try {
            isDownloading = true;
            this.file.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            isDownloaded = true;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(MainApp.getPrimarystage());
            alert.setTitle("Download Complete");
            alert.setContentText("Done");
            alert.setHeaderText(this.name);
            alert.showAndWait();
            this.stop();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null , e.getMessage());
        }
    }


    public long getSize()throws IOException{
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setRequestMethod("HEAD");
        long size = httpConnection.getContentLengthLong();
        long dividable;
        long temp1 = 1024*1024*1024;
        if (size < 1024){
            this.sizeProperty = new SimpleStringProperty("" + (size) + " Bytes");
        }
        else if(size >= 1024 && size<1024*1024){
            dividable = 1024;
            this.sizeProperty = new SimpleStringProperty("" + (size/dividable) + " KB");
        }
        else if(size >= 1024*1024 && size<temp1){
            dividable = 1024*1024;
            this.sizeProperty = new SimpleStringProperty("" + (size/dividable) + " MB");
        }
        else if(size >= temp1){
            dividable = 1024*1024*1024;
            this.sizeProperty = new SimpleStringProperty("" + (size/dividable) + " GB");
        }
        else {
            this.sizeProperty = new SimpleStringProperty("" + (size) + " Bytes");
        }
        return size;
    }

    public void getExtentionNameProperty(){
        this.extention = new SimpleStringProperty(new String(this.name.substring(this.name.lastIndexOf("."))));
    }

    public void CheckDownloading()throws Exception{
        if (isDownloading){
            this.isDownloadingProperty = new SimpleStringProperty("Is Downloading");
        }else if(this.getSize() == fileSave.length()){
            this.isDownloadingProperty = new SimpleStringProperty("Download Complete");
        }
        else {
            this.isDownloadingProperty = new SimpleStringProperty("Not Downloading");
        }

    }
//    public static boolean validateUrl(URL x){
//        String[] customSchemes = { "sftp", "scp", "https" };
//
//        if(){
//
//            return true;
//        }
//        else {
//            return false;
//        }
//    }

}