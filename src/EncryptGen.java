import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.*;

public class EncryptGen {

    private static final Key key;

    static {
        try {
            key = getKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static Key getKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
        keyGenerator.init(128);
        Key secretKey = keyGenerator.generateKey();
        return secretKey;
    }

    public static  void encrypt(String oldFileName,String newFileName)  {

        try {
            Cipher cipher = Cipher.getInstance("Blowfish/CFB/NoPadding");
            FileInputStream in = new FileInputStream(oldFileName);
            FileOutputStream out = new FileOutputStream(newFileName);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            CipherInputStream cis = new CipherInputStream(in, cipher);
            doCopy(cis,out);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void decryptFile(String oldFileName, String newFileName){

        try {
            FileInputStream in = new FileInputStream(oldFileName);
            FileOutputStream out = new FileOutputStream(newFileName);
            Cipher cipher = Cipher.getInstance("Blowfish/CFB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            CipherOutputStream cos = new CipherOutputStream(out, cipher);
            BufferedReader br = br = new BufferedReader(new FileReader(oldFileName));

            String cipherText;

            while((cipherText = br.readLine()) != null){
                byte[] input = cipherText.getBytes();
                byte[] decipheredText = cipher.update(cipherText.getBytes());
                cos.write(decipheredText);
            }
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void doCopy(InputStream is, OutputStream os)
            throws IOException {
        byte[] bytes = new byte[1024];
        int numBytes;
        while ((numBytes = is.read(bytes)) != -1) {
            os.write(bytes, 0, numBytes);
        }
        os.flush();
        os.close();
        is.close();
    }
}
