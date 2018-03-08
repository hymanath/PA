package com.itgrids.partyanalyst.saml;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.transforms.TransformationException;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.IdResolver;
import org.dbunit.util.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.itgrids.partyanalyst.utils.IConstants;

public class SamlSignatureUtil {

	private static final Logger LOGGER = Logger
			.getLogger(SamlSignatureUtil.class.getName());

	private static final String privateKeyStr = "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";// Provide Your Private Key here
	
	private static final String DSA = "DSA"; 
	private static final String RSA = "RSA"; 

	public static Document signFul(Document samlResponseDoc,String samlReponseIDValue) throws Exception {

		if (samlResponseDoc == null) {
			LOGGER.log(Level.WARNING, "Document is Obtained as null");
			throw new Exception(("errorObtainingElement"));
		}

		PrivateKey privateKey = getPrivateKey(privateKeyStr);
		CertificateFactory fact = CertificateFactory.getInstance("X.509");
		
	    FileInputStream is = new FileInputStream (IConstants.STATIC_CONTENT_FOLDER_URL+"security/SAML/ZOHO/reference/tdpcert.pem");
		//FileInputStream is = new FileInputStream ("E://ZOHO/certificate/tdpcert.pem");
	    
	    X509Certificate cert = (X509Certificate) fact.generateCertificate(is);
		
		NodeList nameIds = samlResponseDoc.getElementsByTagName("saml:Assertion"); 
		if (nameIds == null || nameIds.getLength() == 0) {
			return null;
		}
		Element root = (Element) nameIds.item(0);
		root.setIdAttribute("ID", true); 
		XMLSignature sig = null;
		try {
			Constants.setSignatureSpecNSprefix("ds"); 
		} catch (XMLSecurityException xse1) {
			LOGGER.log(Level.WARNING, "Exception while Signing document", xse1);
			throw new Exception(xse1.getMessage());
		}
		if (samlReponseIDValue == null || samlReponseIDValue.length() == 0) {
			Node nNode = nameIds.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				samlReponseIDValue = eElement.getAttribute("ID");
			}
		}
		String sigAlg = null;
		IdResolver.registerElementById(root, samlReponseIDValue);
		try {
			if ((sigAlg == null) || (sigAlg.trim().length() == 0)) {
				if (privateKey.getAlgorithm().equalsIgnoreCase(DSA)) {
					sigAlg = XMLSignature.ALGO_ID_SIGNATURE_DSA;
				} else {
					if (privateKey.getAlgorithm().equalsIgnoreCase(RSA)) {
						sigAlg = XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1;
					}
				}
			}
			org.apache.xml.security.Init.init();
			sig = new XMLSignature(samlResponseDoc, "", sigAlg,
					"http://www.w3.org/2001/10/xml-exc-c14n#");
		} catch (XMLSecurityException xse2) {
			LOGGER.log(Level.WARNING, "Exception while Signing document", xse2);
			throw new Exception(xse2.getMessage());
		}
		Node firstChild = root.getFirstChild();
		while (firstChild != null
				&& (firstChild.getLocalName() == null || !firstChild
						.getLocalName().equals("Issuer"))) {
			firstChild = firstChild.getNextSibling();
		}
		Node nextSibling = null;
		if (firstChild != null) {
			nextSibling = firstChild.getNextSibling();
		}
		if (nextSibling == null) {
			root.appendChild(sig.getElement());
		} else {
			root.insertBefore(sig.getElement(), nextSibling);
		}
		sig.getSignedInfo().addResourceResolver(new OfflineResolver());
		Transforms transforms = new Transforms(samlResponseDoc);
		try {
			transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
		} catch (TransformationException te1) {
			LOGGER.log(Level.WARNING, "Exception while Signing document", te1);
			throw new Exception(te1.getMessage());
		}
		try {
			String transformAlg = "http://www.w3.org/2001/10/xml-exc-c14n#";// No
																			// I18N
			transforms.addTransform(transformAlg);
		} catch (TransformationException te2) {
			LOGGER.log(Level.WARNING, "Exception while Signing document", te2);
			throw new Exception(te2.getMessage());
		}
		String ref = "#" + samlReponseIDValue;
		try {
			sig.addDocument(ref, transforms, Constants.ALGO_ID_DIGEST_SHA1);
		} catch (XMLSignatureException sige1) {
			LOGGER.log(Level.WARNING, "Exception while Signing document", sige1);
			throw new Exception(sige1.getMessage());
		}
		if (cert != null) {
			try {
				sig.addKeyInfo(cert);
			} catch (XMLSecurityException xse3) {
				LOGGER.log(Level.WARNING, "Exception while Signing document",
						xse3);
				throw new Exception(xse3.getMessage());
			}
		}
		try {
			sig.sign(privateKey);
		} catch (XMLSignatureException sige2) {
			LOGGER.log(Level.WARNING, "Exception while Signing document", sige2);
			throw new Exception(sige2.getMessage());
		}

		return samlResponseDoc;
	}

	private static PrivateKey getPrivateKey(String privateKeyStr)
			throws NoSuchAlgorithmException, InvalidKeySpecException {		
		byte[] privateKeyBytes = Base64.decode(privateKeyStr);
		return KeyFactory.getInstance("RSA").generatePrivate(
				new PKCS8EncodedKeySpec(privateKeyBytes));
		
	}

	private static X509Certificate getCertificate(String publicKey)
			throws CertificateException {
		CertificateFactory cf = CertificateFactory.getInstance("X509");
		X509Certificate cert = (X509Certificate) cf
				.generateCertificate(new ByteArrayInputStream(publicKey
						.getBytes()));
		return cert;
	}

}
