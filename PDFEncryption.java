package cyber;

import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class PDFEncryption {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int KEY_SIZE = 128;

    public static void encryptFile(File inputFile, File outputFile, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(new byte[16]));
        FileInputStream inputStream = new FileInputStream(inputFile);
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byte[] encryptedBytes = cipher.update(buffer, 0, bytesRead);
            outputStream.write(encryptedBytes);
        }
        byte[] finalEncryptedBytes = cipher.doFinal();
        outputStream.write(finalEncryptedBytes);
        inputStream.close();
        outputStream.close();
    }

    

    public static void main(String[] args) throws Exception {
        try {
    	String key = "ThisIsASecretKey";
        File inputFile = new File("C:\\Users\\youss\\OneDrive\\Desktop\\os section assignments.pdf");

        File encryptedFile = new File("encrypted.pdf");
       

        // Encrypt the PDF file
        encryptFile(inputFile, encryptedFile, key);
        System.out.println("encrypted successfully");
	}
	catch (Exception e) {
	      System.out.println("Error while encrypting: ");
	
}
  
    }

}