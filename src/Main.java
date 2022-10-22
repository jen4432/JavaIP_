import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        XmlFileWorker file = new XmlFileWorker();
        ArrayList list = file.read("in.xml");
        file.write(list,"out.xml");

    }
}