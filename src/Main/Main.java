/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Data.FileList;
import Data.GSC.GS;
import Data.GameCharacter;
import Data.GenericFile;
import Data.Pak;
import Handler.CamHandler;
import Handler.MemHandler;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 *
 * @author Usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*
        GS gs = new GS("C:\\GSC-B-00.gsc");
        System.out.println(gs.getTag());
        System.exit(0);

        //    MemHandler.readBytesFromMem("pcsx2-qtx64-avx2.exe", 64, 0x7FF6502A8884L);
        byte[] cameraBytes = CamHandler.getCameraBytes();
        GenericFile f = new GenericFile(cameraBytes);
        f.saveFile("D:\\camera.pro");
         */
        // TODO code application logic here
        //Lista todos los .pak de un directorio
        FileList dir = Handler.Handler.loadPakCharacterDir("C:\\Users\\Usuario\\Desktop\\T\\costume");

        Iterator<GenericFile> iterator = dir.getFileList().iterator();
        while (iterator.hasNext()) {
            GameCharacter p = (GameCharacter) iterator.next();
            //elimina todos los idiomas
            p.removeTxts();

            //reconstruye indices
            p.rebuild();

            //guarda el archivo sobrescribiendo el original
            p.saveFile(p.getFilePath());
        }

    }

}
