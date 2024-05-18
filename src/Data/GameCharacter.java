/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

/**
 *
 * @author Usuario
 */
public class GameCharacter extends Pak {

    public GameCharacter(String filePath) {
        super(filePath);
    }

    public boolean removeLips() {

        int filePosRemove = fileAmount - 200;

        for (int i = 0; i < 200; i++) {

            removeFile(filePosRemove);

        }

        return true;

    }
    
    public boolean removeTxts() {

      
        removeFile(43);
        removeFile(43);
        removeFile(43);
        removeFile(43);
        removeFile(43);
        removeFile(43);
        removeFile(43);
        removeFile(43);
        removeFile(43);

        return true;

    }

}
