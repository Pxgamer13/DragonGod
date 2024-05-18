/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Handler;

import Data.FileList;
import Data.GameCharacter;
import Data.GenericFile;
import Data.Pak;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

/**
 *
 * @author Usuario
 */
public class Handler {
    
    static DecimalFormat df = new DecimalFormat("##.###");;

    public static String readFileToString(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return "";
        }
    }

    public static FileList loadPakCharacterDir(String ruta) {

        FileList fileList = new FileList();
        File files = new File(ruta);
        File[] ficheros = files.listFiles();

        for (int x = 0; x < ficheros.length; x++) {
            if (ficheros[x].isDirectory() == false) {
                String rute = ficheros[x].getPath();

                if (rute.toLowerCase().endsWith(".pak")) {

                    fileList.getFileList().add(new GameCharacter(rute));

                }

            }

        }

        return fileList;

    }

    public static long getvalorpuntero_4(byte a, byte b, byte c, byte d) {

        String va  = Integer.toHexString(a & 0xFF);
        String vb = Integer.toHexString(b & 0xFF);
        String vc = Integer.toHexString(c & 0xFF);
        String vd = Integer.toHexString(d & 0xFF);

        if (va.length() == 1) {
            va  = "0" + va;
        }
        if (vb.length() == 1) {
            vb = "0" + vb;
        }
        if (vc.length() == 1) {
            vc = "0" + vc;
        }
        if (vd.length() == 1) {
            vd = "0" + vd;
        }

        String h = "";
        h = h + vd + vc + vb + va;
        long valorpuntero = Long.parseLong(h, 16);
        return valorpuntero;
    }

    public static String valorahex_4(int valor) {
        String hexstring = Integer.toHexString(valor);
        if (hexstring.length() == 7) {
            hexstring = "0" + hexstring;
        }
        if (hexstring.length() == 6) {
            hexstring = "00" + hexstring;
        }
        if (hexstring.length() == 5) {
            hexstring = "000" + hexstring;
        }
        if (hexstring.length() == 4) {
            hexstring = "0000" + hexstring;
        }
        if (hexstring.length() == 3) {
            hexstring = "00000" + hexstring;
        }
        if (hexstring.length() == 2) {
            hexstring = "000000" + hexstring;
        }
        if (hexstring.length() == 1) {
            hexstring = "0000000" + hexstring;
        }

        return hexstring;

    }

    public static byte[] valorahex_4_byte(int valor) {
        String hexstring = Integer.toHexString(valor);
        if (hexstring.length() == 7) {
            hexstring = "0" + hexstring;
        }
        if (hexstring.length() == 6) {
            hexstring = "00" + hexstring;
        }
        if (hexstring.length() == 5) {
            hexstring = "000" + hexstring;
        }
        if (hexstring.length() == 4) {
            hexstring = "0000" + hexstring;
        }
        if (hexstring.length() == 3) {
            hexstring = "00000" + hexstring;
        }
        if (hexstring.length() == 2) {
            hexstring = "000000" + hexstring;
        }
        if (hexstring.length() == 1) {
            hexstring = "0000000" + hexstring;
        }

        byte[] valorsempai = new byte[4];
        long valuno = Long.parseLong(hexstring.substring(0, 2), 16);
        long valdos = Long.parseLong(hexstring.substring(2, 4), 16);
        long valtres = Long.parseLong(hexstring.substring(4, 6), 16);
        long valcuatro = Long.parseLong(hexstring.substring(6, 8), 16);
        valorsempai[3] = (byte) valuno;
        valorsempai[2] = (byte) valdos;
        valorsempai[1] = (byte) valtres;
        valorsempai[0] = (byte) valcuatro;
        return valorsempai;

    }

    public static byte[] valorahex_4_String(String hexstring) {
        if (hexstring.length() == 7) {
            hexstring = "0" + hexstring;
        }
        if (hexstring.length() == 6) {
            hexstring = "00" + hexstring;
        }
        if (hexstring.length() == 5) {
            hexstring = "000" + hexstring;
        }
        if (hexstring.length() == 4) {
            hexstring = "0000" + hexstring;
        }
        if (hexstring.length() == 3) {
            hexstring = "00000" + hexstring;
        }
        if (hexstring.length() == 2) {
            hexstring = "000000" + hexstring;
        }
        if (hexstring.length() == 1) {
            hexstring = "0000000" + hexstring;
        }

        byte[] valorsempai = new byte[4];
        long valuno = Long.parseLong(hexstring.substring(0, 2), 16);
        long valdos = Long.parseLong(hexstring.substring(2, 4), 16);
        long valtres = Long.parseLong(hexstring.substring(4, 6), 16);
        long valcuatro = Long.parseLong(hexstring.substring(6, 8), 16);
        valorsempai[0] = (byte) valuno;
        valorsempai[1] = (byte) valdos;
        valorsempai[2] = (byte) valtres;
        valorsempai[3] = (byte) valcuatro;
        return valorsempai;

    }
    
    
      public static Float hex_a_float(byte a, byte b, byte c, byte d) {
        String va  = Integer.toHexString(a & 0xFF);
        String vb = Integer.toHexString(b & 0xFF);
        String vc = Integer.toHexString(c & 0xFF);
        String vd = Integer.toHexString(d & 0xFF);
        if (va.length() == 1) {
            va  = "0" + va;
        }
        if (vb.length() == 1) {
            vb = "0" + vb;
        }
        if (vc.length() == 1) {
            vc = "0" + vc;
        }
        if (vd.length() == 1) {
            vd = "0" + vd;
        }

        String valor_hexadecimal = "";
        valor_hexadecimal = valor_hexadecimal + vd + vc + vb + va;

        Long aux = Long.parseLong(valor_hexadecimal, 16);

        Float valor_float = Float.intBitsToFloat(aux.intValue());

        return valor_float;

    }

    public static byte[] float_a_hex(Float v) {

        v = new Float(df.format(v).replace(",", "."));

        int aux = Float.floatToIntBits(v.floatValue());

        long y = aux & 0x00000000ffffffffL;

        byte[] auxbytes = valorahex_4_byte((int) y);

        return auxbytes;
    }
    

}
