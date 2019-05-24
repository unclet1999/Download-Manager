package com.metal.seyed.model;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Download {

    private String name;

    private URL url;


    public Download(URL url) {
        this.url = url;
        nameByurl();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void nameByurl(){
        this.name = this.url.toString().substring(url.toString().lastIndexOf("/") + 1 );
    }



    public void download(){
        try {
            BufferedInputStream in = new BufferedInputStream(new URL(this.url.toString()).openStream());
            Files.copy(in, Paths.get(this.name), StandardCopyOption.REPLACE_EXISTING);
            FileOutputStream out = new FileOutputStream(this.name);
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                out.write(dataBuffer, 0, bytesRead);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null , e.getMessage());
        }
    }


    public long getSize() throws IOException{
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setRequestMethod("HEAD");
        long size = httpConnection.getContentLengthLong();
        return size;
    }

}
