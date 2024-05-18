/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.GSC;

import Data.GenericFile;

/**
 *
 * @author Usuario
 */
public class GS extends GenericFile {

    GSList gsList;

    public GS(String filePath) {
        super(filePath);
        load();
    }

    public GS(byte[] bt) {
        super(bt);
        load();
    }

    public String getTag() {
        if (bt[2] == 67 && bt[3] == 70) {
            return "MAIN";
        }

        if (bt[2] == 72 && bt[3] == 68) {
            return "HD";
        }

        if (bt[2] == 67 && bt[3] == 68) {
            return "CD";
        }

        if (bt[2] == 65 && bt[3] == 67 && bt[12] == (byte) (255 & 0xFF)) {
            return "START_PARAM";
        }

        if (bt[2] == 65 && bt[3] == 67 && bt[12] == (byte) (232 & 0xFF) && bt[13] == 3) {
            return "UNKNOWN";
        }

        if (bt[2] == 65 && bt[3] == 67 && bt[12] == (byte) (254 & 0xFF)) {
            return "UNKNOWN";
        }

        if (bt[2] == 65 && bt[3] == 67 && bt[12] == (byte) (253 & 0xFF)) {
            return "DAT_UNLOCKABLES";
        }

        if (bt[2] == 65 && bt[3] == 67 && bt[12] == (byte) (252 & 0xFF)) {
            return "DAT_BATTLE_SETTINGS";
        }

        if (bt[2] == 65 && bt[3] == 67 && bt[12] == (byte) (251 & 0xFF)) {
            return "DAT_DIALOGUE_ACTORS";
        }

        if (bt[2] == 65 && bt[3] == 67 && bt[13] == 39) {
            return "DAT_CUTSCENE";
        }

        if (bt[2] == 68 && bt[3] == 84) {
            return "DATA";
        }

        return "";
    }

    public void load() {

        boolean containsSubFiles;

        if (bt[16] == 71 && bt[17] == 83) {
            containsSubFiles = true;
        } else {
            containsSubFiles = false;
        }

        if (!containsSubFiles) {
            return;
        }

        gsList = new GSList();

        int size = 16 + (int) Handler.Handler.getvalorpuntero_4(bt[8], bt[9], bt[10], bt[11]);

        int pos = 16;

        while (pos < size) {

            int currentGSSize = 32 + (int) Handler.Handler.getvalorpuntero_4(bt[pos + 8], bt[pos + 9], bt[pos + 10], bt[pos + 11]);

            byte[] btCurrentGS = new byte[currentGSSize];

            for (int i = 0; i < btCurrentGS.length; i++) {

                btCurrentGS[i] = bt[pos];
                pos++;

            }

            GS currentGS = new GS(btCurrentGS);
            System.out.println(currentGS.getTag());
            gsList.getGsList().add(currentGS);

        }

    }

}
