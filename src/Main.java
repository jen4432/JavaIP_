import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        TxtFileWorker worker = new TxtFileWorker();
        List<String> list =  worker.read("input.txt");
        System.out.println(list.toString() + list.size());
        worker.write((ArrayList) list,"output.txt");

    }
}