import org.apache.commons.lang3.ArrayUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class EncryptorRSA implements Encryptor {
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    public PublicKey getPublicKey(){
        return this.publicKey;
    }

    public void setPublicKey(File file) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicKeyBytes = Files.readAllBytes(file.toPath());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        publicKey = keyFactory.generatePublic(publicKeySpec);
    }

    public PrivateKey getPrivateKey(){
        return this.privateKey;
    }

    public void setPrivateKey(File file) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] privateKeyBytes = Files.readAllBytes(file.toPath());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        privateKey = keyFactory.generatePrivate(privateKeySpec);
    }

    public void generateKeyPair(String publicKeyName, String privateKeyName) throws IOException, NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        try (FileOutputStream fos = new FileOutputStream(publicKeyName + ".key")) {
            fos.write(publicKey.getEncoded());
        }
        try (FileOutputStream fos = new FileOutputStream(privateKeyName + ".key")) {
            fos.write(privateKey.getEncoded());
        }
    }

    @Override
    public void encryptFile(File sourceFile, File destinationFile) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        byte[] fileBytes = Files.readAllBytes(sourceFile.toPath());
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] resultBytes = null;
        for (int i = 0; i < fileBytes.length; i += 244) {
            byte[] doFinal = encryptCipher.doFinal(ArrayUtils.subarray(fileBytes, i, i + 244));
            resultBytes = ArrayUtils.addAll(resultBytes, doFinal);
            try (FileOutputStream stream = new FileOutputStream(String.valueOf(destinationFile.toPath()))) {
                stream.write(resultBytes);
            }
        }
    }

    @Override
    public void decryptFile(File sourceFile, File destinationFile) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] fileBytes = Files.readAllBytes(sourceFile.toPath());

        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, publicKey);

        int inputLen = fileBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > 256) {
                cache = decryptCipher.doFinal(fileBytes, offSet, 256);
            } else {
                cache = decryptCipher.doFinal(fileBytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * 256;
        }

        byte[] resultBytes = out.toByteArray();
        try (FileOutputStream stream = new FileOutputStream(String.valueOf(destinationFile.toPath()))) {
            stream.write(resultBytes);
        }
    }
}
