package io.github.alexanderheim;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ByteUtility {

    public static byte[] readBytes(String filepath){
        try {
            Path fileLocation = Paths.get(filepath);
            return Files.readAllBytes(fileLocation);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeBytes(String filepath, byte[] bytes){
        try (FileOutputStream stream = new FileOutputStream(filepath)) {
            stream.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String addSpaceToHexString(String hex){

        if(hex.length()%2 != 0){
            System.out.println("[ERROR] Hex String Length is not a multiple of 2!");
        }
        String s = "";
        for(int i = 0; i < hex.length(); i+= 2){
            s += hex.charAt(i);
            s += hex.charAt(i+1);
            s += " ";
        }
        return s.trim();
    }

    public static String removeSpaceFromHexString(String hex){
        return hex.replaceAll("\\s+","");
    }
}
