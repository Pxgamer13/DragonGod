/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class FileList {

    ArrayList<GenericFile> fileList;

    public FileList() {
        fileList = new ArrayList();
    }

    public ArrayList<GenericFile> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<GenericFile> fileList) {
        this.fileList = fileList;
    }

    public byte[] getHeader() {

        int headerSize = (fileList.size() + 2) * 4;
        if (headerSize % 16 == 0) {
            headerSize = headerSize + 4;
        }
        while (headerSize % 16 != 0) {
            headerSize = headerSize + 4;
        }

        byte[] btHeader = new byte[headerSize];

        byte[] btAux = Handler.Handler.valorahex_4_byte(fileList.size());
        btHeader[0] = btAux[0];
        btHeader[1] = btAux[1];
        btHeader[2] = btAux[2];
        btHeader[3] = btAux[3];

        int pos = 4;
        int index = headerSize;

        for (int i = 0; i < fileList.size(); i++) {
            btAux = Handler.Handler.valorahex_4_byte(index);
            btHeader[pos] = btAux[0];
            btHeader[pos + 1] = btAux[1];
            btHeader[pos + 2] = btAux[2];
            btHeader[pos + 3] = btAux[3];

            index = index + fileList.get(i).bt.length;

            pos = pos + 4;

        }

        btAux = Handler.Handler.valorahex_4_byte(index);
        btHeader[pos] = btAux[0];
        btHeader[pos + 1] = btAux[1];
        btHeader[pos + 2] = btAux[2];
        btHeader[pos + 3] = btAux[3];

        return btHeader;

    }

    public byte[] getAllBytes() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            for (int i = 0; i < fileList.size(); i++) {
                outputStream.write(fileList.get(i).bt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();

    }

}
