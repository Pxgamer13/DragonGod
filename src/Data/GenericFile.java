/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Usuario
 */
public class GenericFile {

    public byte[] bt;

    public String filePath;

    public GenericFile(String filePath) {

        this.filePath = filePath;

        try {

            //  Files.readAllBytes(Paths.get("D:\\wrande.a"));
            bt = Files.readAllBytes(Paths.get(filePath));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GenericFile(byte[] bt) {

        this.bt = bt;
    }

    public void saveFile(String filePath) {
        try {
            FileUtils.writeByteArrayToFile(new File(filePath), bt);
        } catch (IOException e) {
            System.err.println("Error saving byte array to file: " + e.getMessage());
        }

    }

    public byte[] getBt() {
        return bt;
    }

    public void setBt(byte[] bt) {
        this.bt = bt;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    
    
}
