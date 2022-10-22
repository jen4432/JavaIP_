import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XmlFileWorker {

    public ArrayList read(String fileName)  {
        File file = new File(fileName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        ArrayList list = new ArrayList();
        try{
            doc = dbf.newDocumentBuilder().parse(file);
            NodeList nodeList = doc.getElementsByTagName("element");
            for(int i = 0; i < nodeList.getLength();i++){
                list.add(nodeList.item(i).getTextContent());
            }

        }catch (Exception e){
            System.out.println("Error:" + e.toString());
        }
        return list;
    }

    public void write(ArrayList list,String filename) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        String root = "root";
        DocumentBuilderFactory documentBuilderFactory =DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder =documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        Element rootElement = document.createElement(root);

        document.appendChild(rootElement);

        for(int i = 0; i < list.size();i++){
            String element ="element";
            Element em = document.createElement(element);
            em.appendChild(document.createTextNode(list.get(i).toString()));
            rootElement.appendChild(em);
        }

        try {
            OutputStream fileOutputStream = new FileOutputStream(filename);
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fileOutputStream);
            transformer.transform(source, result);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

