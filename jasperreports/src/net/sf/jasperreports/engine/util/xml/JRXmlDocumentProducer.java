/**
 * 
 */
package net.sf.jasperreports.engine.util.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.util.JRXmlUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Produces a <code>org.w3c.dom.Document</code> based on a <code>java.io.File</code>, <code>java.io.InputStream</code> or a <code>java.lang.String</code> uri
 * 
 * @author Narcis Marcu (narcism@users.sourceforge.net)
 * @version $Id: JRXmlDocumentProducer.java 4147 2011-01-17 09:23:18Z narcism $
 */
public class JRXmlDocumentProducer {
	
	private File file;
	
	private InputStream inputStream;
	
	private String uri;
	
	private DocumentBuilderFactory documentBuilderFactory;
	
	
	public JRXmlDocumentProducer() {
	}

	public JRXmlDocumentProducer(File file) {
		this.file = file;
	}

	public JRXmlDocumentProducer(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public JRXmlDocumentProducer(String uri) {
		this.uri = uri;
	}
	
	public DocumentBuilderFactory getDocumentBuilderFactory() {
		return documentBuilderFactory;
	}
	
	
	public void setDocumentBuilderFactory(DocumentBuilderFactory documentBuilderFactory) {
		this.documentBuilderFactory = documentBuilderFactory;
	}
	
	
	public void setFile(File file) {
		this.file = file;
	}
	
	
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	
	public Document getDocument() throws JRException  {
		try {
			if (file != null) {
				return getDocumentBuilder().parse(file);
			} else if (inputStream != null ) {
				return getDocumentBuilder().parse(inputStream);
			} else if (uri != null) {
				return getDocumentBuilder().parse(uri);
			}
		} catch (SAXException e) {
			throw new JRException("Failed to parse the xml document", e);
		} catch (IOException e) {
			throw new JRException("Failed to parse the xml document", e);
		}
		return null;
	}
	
	
	public Document getDocument(Node sourceNode) throws JRException {
		Document doc = getDocumentBuilder().newDocument();

		Node source;
		if (sourceNode.getNodeType() == Node.DOCUMENT_NODE) {
			source = ((Document) sourceNode).getDocumentElement();
		} else {
			source = sourceNode;
		}

		Node node = doc.importNode(source, true);
		doc.appendChild(node);
		
		return doc;
	}
	
	
	protected DocumentBuilder getDocumentBuilder() throws JRException {
		try{
			if (documentBuilderFactory != null) {
				return documentBuilderFactory.newDocumentBuilder();
			} else {
				return JRXmlUtils.createDocumentBuilder();
			}
		} catch (ParserConfigurationException e)
		{
			throw new JRException("Failed to create a document builder", e);
		}
	}
	
}
