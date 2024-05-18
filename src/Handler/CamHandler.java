/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Handler;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/**
 *
 * @author Usuario
 */
public class CamHandler {

    public static float[] solveSystem(float[][] coefficients, float[] constants) {
        int n = coefficients.length;
        float[] solution = new float[n];

        // Eliminación gaussiana
        for (int pivot = 0; pivot < n - 1; pivot++) {
            for (int row = pivot + 1; row < n; row++) {
                float factor = coefficients[row][pivot] / coefficients[pivot][pivot];
                for (int col = pivot; col < n; col++) {
                    coefficients[row][col] -= factor * coefficients[pivot][col];
                }
                constants[row] -= factor * constants[pivot];
            }
        }

        // Sustitución hacia atrás
        for (int i = n - 1; i >= 0; i--) {
            float sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += coefficients[i][j] * solution[j];
            }
            solution[i] = (constants[i] - sum) / coefficients[i][i];
        }

        return solution;
    }

    public static byte[] getCameraBytes() {

        byte[] readBytesFromMem = MemHandler.readBytesFromMem("pcsx2-qtx64-avx2.exe", 64, 0x7FF6502A8884L);

        int posMem = 0;

        float[][] vm = new float[4][4];

        for (int x = 0; x < 4; x++) {

            for (int y = 0; y < 4; y++) {

                vm[x][y] = Handler.hex_a_float(readBytesFromMem[posMem], readBytesFromMem[posMem + 1], readBytesFromMem[posMem + 2], readBytesFromMem[posMem + 3]);

                System.out.println("vm[" + x + "][" + y + "]=" + vm[x][y]);

                posMem = posMem + 4;

            }

        }

        float[][] coefficients = new float[3][3];
        coefficients[0][0] = vm[0][0];
        coefficients[0][1] = vm[1][0];
        coefficients[0][2] = vm[2][0];

        coefficients[1][0] = vm[0][1];
        coefficients[1][1] = vm[1][1];
        coefficients[1][2] = vm[2][1];

        coefficients[2][0] = vm[0][2];
        coefficients[2][1] = vm[1][2];
        coefficients[2][2] = vm[2][2];

        float[] constants = {vm[3][0], vm[3][1], vm[3][2]};

        // Resolver el sistema de ecuaciones
        float[] solution = solveSystem(coefficients, constants);

        solution[0] = -solution[0];
        solution[1] = -solution[1];
        solution[2] = -solution[2];
        // Imprimir la solución
        System.out.println("Solución:");
        for (int i = 0; i < solution.length; i++) {
            System.out.println("x" + (i + 1) + " = " + solution[i]);
        }

        double gameX = solution[0];
        double gameY = solution[1];
        double gameZ = solution[2];

        float sx;

        sx = -vm[1][2];

        double asin = Math.asin(sx);

        double x = (asin);

        double dblRotationX = Math.toDegrees(asin);

        double cy = vm[2][2] / Math.cos(x);

        double y = Math.acos(cy);

        double dblRotationY = -Math.toDegrees(y);

        double cx = Math.cos(x);
        double cz = vm[1][1] / cx;

        double z = Math.acos(cz);
        double dblRotationZ = Math.toDegrees(z);

        System.out.println("sx:" + sx);
        System.out.println("x:" + dblRotationX);
        System.out.println("y:" + dblRotationY);
        System.out.println("z:" + (float)dblRotationZ);

        byte[] btX = Handler.float_a_hex((float) gameX);
        byte[] btY = Handler.float_a_hex((float) gameY);
        byte[] btZ = Handler.float_a_hex((float) gameZ);

        byte[] btRX = Handler.float_a_hex((float) dblRotationX);
        byte[] btRY = Handler.float_a_hex((float) dblRotationY);
        byte[] btRZ = Handler.float_a_hex((float) dblRotationZ);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            outputStream.write(btX);
            outputStream.write(btY);
            outputStream.write(btZ);
            outputStream.write(btRX);
            outputStream.write(btRY);
            outputStream.write(btRZ);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();

    }

    // Definición de la clase Vector3
    static class Vector3 {

        float x, y, z;

        public Vector3() {
            this.x = 0;
            this.y = 0;
            this.z = 0;
        }

        public float[] toArray() {
            return new float[]{x, y, z};
        }
    }

    static Vector3 m_position = new Vector3();

}
