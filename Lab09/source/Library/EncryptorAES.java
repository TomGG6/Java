import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class EncryptorAES implements Encryptor{
    private static SecretKey secretKey;

    public SecretKey getSecretKey(){
        return this.secretKey;
    }

    public void generateSecretKey(String keyName) throws NoSuchAlgorithmException, IOException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey key = keyGenerator.generateKey();
        try (FileOutputStream fos = new FileOutputStream(keyName + ".key")) {
            fos.write(key.getEncoded());
        }
        System.out.println(key.getFormat());
    }

    public static void setSecretKey(File file) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] secretKeyBytes = Files.readAllBytes(file.toPath());
        secretKey = new SecretKeySpec(secretKeyBytes, "AES");
    }

    @Override
    public void encryptFile(File sourceFile, File destinationFile) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        byte[] fileBytes = Files.readAllBytes(sourceFile.toPath());
        Cipher encryptionCipher = Cipher.getInstance("AES");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] resultBytes = encryptionCipher.doFinal(fileBytes);
        try (FileOutputStream stream = new FileOutputStream(String.valueOf(destinationFile.toPath()))) {
            stream.write(resultBytes);
        }
    }

    @Override
    public void decryptFile(File sourceFile, File destinationFile) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        byte[] fileBytes = Files.readAllBytes(sourceFile.toPath());
        Cipher decryptionCipher = Cipher.getInstance("AES");
        decryptionCipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] resultBytes = decryptionCipher.doFinal(fileBytes);
        try (FileOutputStream stream = new FileOutputStream(String.valueOf(destinationFile.toPath()))) {
            stream.write(resultBytes);
        }
    }
}
