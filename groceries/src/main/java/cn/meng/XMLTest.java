package cn.meng;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;

public class XMLTest {
    public static void main(String[] args) throws Exception {

        String xml = "<WebServiceData>\n" +
                "     <WebServiceParams>\n" +
                "          <BUID>201909201333</BUID>\n" +
                "          <RCNL>WX</RCNL>\n" +
                "          <BUSYS>CSC</BUSYS>\n" +
                "          <ProcessCode>SRD</ProcessCode>\n" +
                "          <OpType>PRV</OpType>\n" +
                "     </WebServiceParams>\n" +
                "     <BusinessInput>\n" +
                "          <ApplicantRole>TBR</ApplicantRole>\n" +
                "          <PolicyID>AG301145</PolicyID>\n" +
                "          <CompanyID>1</CompanyID>\n" +
                "     </BusinessInput>\n" +
                "</WebServiceData>";
        org.dom4j.Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        org.dom4j.Element root = doc.getRootElement();

        System.out.println(root.getName());
        System.out.println(root.element("WebServiceParams").elementTextTrim("BUID"));
        Iterator it = root.elementIterator();
        while (it.hasNext()) {
            org.dom4j.Element element = (org.dom4j.Element) it.next();// 一个Item节点
            System.out.println(element.getName() + " : " + element.getTextTrim());
        }


    }
}
