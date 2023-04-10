package cyber;
import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.*;
public class PDFDecreption {
	private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int KEY_SIZE = 128;
    
    public static void decryptFile(File inputFile, File outputFile, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(new byte[16]));
        FileInputStream inputStream = new FileInputStream(inputFile);
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byte[] decryptedBytes = cipher.update(buffer, 0, bytesRead);
            outputStream.write(decryptedBytes);
        }
        byte[] finalDecryptedBytes = cipher.doFinal();
        outputStream.write(finalDecryptedBytes);
        inputStream.close();
        outputStream.close();
    }
    
    
    
    public static void main(String[] args) throws Exception {
    	
    	try{
        String key = "ThisIsASecretKey";
        File inputFile = new File("C:\\Users\\youss\\eclipse-workspace\\youssef\\encrypted.pdf");
        File encryptedFile = new File("encrypted.pdf");
        File decryptedFile = new File("decrypted.pdf");
        // Decrypt the encrypted PDF file
        decryptFile(encryptedFile, decryptedFile, key);
        System.out.println("decrypted successfully");
    	}
    	catch (Exception e) {
  	      System.out.println("Error while decrypting: ");
    	
    }
    }  
}
