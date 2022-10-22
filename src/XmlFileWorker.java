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

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int no=2;
        String root = "SMS";
        DocumentBuilderFactory documentBuilderFactory =DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder =documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        Element rootElement = document.createElement(root);

        document.appendChild(rootElement);
        String element ="Number";
        System.out.print("Enter the Number: ");
        String data = bf.readLine();
        Element em = document.createElement(element);
        em.appendChild(document.createTextNode(data));
        rootElement.appendChild(em);

        String element1 ="message";
        System.out.print("Enter the SMS: ");
        String data1 = bf.readLine();
        Element em1 = document.createElement(element1);
        em1.appendChild(document.createTextNode(data1));
        rootElement.appendChild(em1);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result =  new StreamResult(new StringWriter());
        transformer.transform(source, result);

        //writing to file
        FileOutputStream fop = null;
        File file;
        try {

            file = new File(filename);
            fop = new FileOutputStream(file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            else{

            }

            // get the content in bytes
            String xmlString = result.getWriter().toString();
            System.out.println(xmlString);
            byte[] contentInBytes = xmlString.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

