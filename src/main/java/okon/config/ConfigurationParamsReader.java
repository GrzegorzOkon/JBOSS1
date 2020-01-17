package okon.config;

import okon.Job;
import okon.exception.AppException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConfigurationParamsReader {
    public static List<Job> readConfigurationParams(File file) {
        Element root = parseXml(file);
        List<Job> result = new LinkedList<>();
        NodeList children = root.getElementsByTagName("result");

        if (children != null && children.getLength() > 0) {
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) child;
                    List<String> rows = new ArrayList<>();
                    for (int j = 0; j < element.getElementsByTagName("row").getLength(); j++) {
                        rows.add(element.getElementsByTagName("row").item(j).getTextContent());
                    }
                    Job job = new Job(rows);
                    result.add(job);
                }
            }
        }
        return result;
    }

    private static Element parseXml(File file) {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document document = docBuilder.parse(file);
            return document.getDocumentElement();
        } catch (Exception e) {
            throw new AppException(e);
        }
    }
}
