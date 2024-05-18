/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import java.io.ByteArrayOutputStream;

/**
 *
 * @author Usuario
 */
public class Pak extends GenericFile {

    int fileAmount;

    FileList fileList;

    public Pak(String filePath) {
        super(filePath);
        load();
    }

    public void load() {
        fileAmount = (int) Handler.Handler.getvalorpuntero_4(bt[0], bt[1], bt[2], bt[3]);

        fileList = new FileList();

        int posIndex = 4;
        int posFile;
        int endPos;
        int fileSize;
        for (int i = 0; i < fileAmount; i++) {

            posFile = (int) Handler.Handler.getvalorpuntero_4(bt[posIndex], bt[posIndex + 1], bt[posIndex + 2], bt[posIndex + 3]);
            endPos = (int) Handler.Handler.getvalorpuntero_4(bt[posIndex + 4], bt[posIndex + 5], bt[posIndex + 6], bt[posIndex + 7]);
            fileSize = endPos - posFile;

            posIndex = posIndex + 4;

            byte[] currentBt = new byte[fileSize];

            for (int y = 0; y < fileSize; y++) {
                currentBt[y] = bt[posFile];
                posFile++;
            }

            fileList.getFileList().add(new GenericFile(currentBt));

        }

    }

    public boolean rebuild() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte[] header = fileList.getHeader();
        byte[] allBytes = fileList.getAllBytes();
        try {
            outputStream.write(header);
            outputStream.write(allBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        bt = outputStream.toByteArray();

        return true;

    }

    public int getFileAmount() {
        return fileAmount;
    }

    public void setFileAmount(int fileAmount) {
        this.fileAmount = fileAmount;
    }

    public FileList getFileList() {
        return fileList;
    }

    public void setFileList(FileList fileList) {
        this.fileList = fileList;
    }

    public boolean removeFile(int filePos) {
        getFileList().getFileList().remove(filePos);

        return true;
    }

}
