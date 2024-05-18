/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Handler;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;

/**
 *
 * @author Usuario
 */
public class MemHandler {

    static final int PROCESS_WM_READ = 0x0010;

    public static byte[] readBytesFromMem(String processName, int BUFFER_SIZE, long memoryAddress) {

        //    int BUFFER_SIZE = 64; // Increased buffer size
        Kernel32 kernel32 = Kernel32.INSTANCE;
        long processId = FindProcessId("pcsx2-qtx64-avx2.exe", kernel32);
        WinNT.HANDLE processHandle = kernel32.OpenProcess(PROCESS_WM_READ, false, (int) processId);
        if (processHandle == WinBase.INVALID_HANDLE_VALUE) {
            System.out.println("Failed to open process. Error code: " + kernel32.GetLastError());
            return null;
        }


        Pointer baseAddress = new Pointer(memoryAddress);
        Memory buffer = new Memory(BUFFER_SIZE);
        IntByReference lpNumberOfBytesRead = new IntByReference();

        if (!kernel32.ReadProcessMemory(processHandle, baseAddress, buffer, BUFFER_SIZE, lpNumberOfBytesRead)) {
            System.out.println("Failed to read process memory. Error code: " + kernel32.GetLastError());
            return null;
        }

        System.out.println("Read " + lpNumberOfBytesRead.getValue() + " bytes from process memory.");

        byte[] byteArray = buffer.getByteArray(0, lpNumberOfBytesRead.getValue());

        String hexString = byteArrayToHexString(buffer.getByteArray(0, lpNumberOfBytesRead.getValue()));
        System.out.println(hexString); // Imprime la cadena hexadecimal
        // Print out the data read, assuming it's Unicode text
        //  System.out.println(new String(buffer.getByteArray(0, lpNumberOfBytesRead.getValue()), StandardCharsets.UTF_16LE));

        kernel32.CloseHandle(processHandle);

        return byteArray;

    }

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b)); // Convierte cada byte a su representación en formato hexadecimal
        }
        return sb.toString();
    }

    static long FindProcessId(String processName, Kernel32 kernel32) {
        // This Reference will contain the processInfo that will be parsed to recover the ProcessId
        Tlhelp32.PROCESSENTRY32.ByReference processInfo = new Tlhelp32.PROCESSENTRY32.ByReference();

        // This Handle allows us to parse the process map
        WinNT.HANDLE processesSnapshot = kernel32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinDef.DWORD(0L));
        if (processesSnapshot == kernel32.INVALID_HANDLE_VALUE) {

            System.err.println("INVALID_HANDLE_VALUE");

            return 0L;
        }

        try {// This will parse all the processes to find the process id corresponding to the process name
            kernel32.Process32First(processesSnapshot, processInfo);
            if (processName.equals(Native.toString(processInfo.szExeFile))) {

                System.out.println("Process " + processName + " found : " + processInfo.th32ProcessID.longValue());

                return processInfo.th32ProcessID.longValue();
            }

            while (kernel32.Process32Next(processesSnapshot, processInfo)) {
                if (processName.equals(Native.toString(processInfo.szExeFile))) {

                    System.out.println("Process " + processName + " found : " + processInfo.th32ProcessID.longValue());

                    return processInfo.th32ProcessID.longValue();
                }
            }

            System.out.println("Did not found the requested Process: " + processName);

            return 0L;
        } finally {
            kernel32.CloseHandle(processesSnapshot);
        }
    }

}
