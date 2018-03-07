package com.itgrids.partyanalyst.saml;

import java.io.File;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.itgrids.partyanalyst.utils.ImageAndStringConverter;

public class SamlUtilService {

	public static void main(String[] arr)
	{
		try{
		SamlSignatureUtil samlSignatureUtil = new SamlSignatureUtil();
		ImageAndStringConverter converter = new ImageAndStringConverter();
		
		File fXmlFile = new File("C:/Users/Administrator/Desktop/SAML.xml");
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		Document doc2 = samlSignatureUtil.signFul(doc,"R992fdbeb2a4873b04c105a8a5f74c1a267ed0f22");
		
		DOMSource source = new DOMSource(doc2);
	    FileWriter writer = new FileWriter(new File("C:/Users/Administrator/Desktop/SAML3.xml"));
	    StreamResult result = new StreamResult(writer);

	    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    Transformer transformer = transformerFactory.newTransformer();
	    transformer.transform(source, result);
	    
	    System.out.println(converter.convertImageFileToBase64String(new File("C:/Users/Administrator/Desktop/SAML3.xml")));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
