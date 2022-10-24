import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        try {
            String key = "squirrel123";

            FileInputStream fis = new FileInputStream("original.txt");
            FileOutputStream fos = new FileOutputStream("encrypted.txt");
            EncryptGen encryptGen = new EncryptGen();
            encryptGen.encrypt("original.txt","encrypted.txt");

            encryptGen.decryptFile("encrypted.txt","decrypted.txt");


        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}