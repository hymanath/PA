package com.itgrids.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class ImageAndStringConverter {
	
	private static final Logger LOG = Logger.getLogger(ImageAndStringConverter.class);
	
	public String convertImageToBase64String(String imagePath)
	 {
		 try{
			 File file = new File(imagePath);
			 FileInputStream imageInFile = new FileInputStream(file);
	         byte imageData[] = new byte[(int) file.length()];
	         imageInFile.read(imageData);
	         imageInFile.close();
	         return Base64.encodeBase64URLSafeString(imageData);
		 }catch(Exception e)
		 {
			 LOG.error(e);
			 return null;
		 }
	 }
	
	public boolean convertBase64StringToImage(String imageDataString,String imagePath)
	 {
		 try{
			 byte[] imageByteArray = Base64.decodeBase64(imageDataString);
			 FileOutputStream imageOutFile = new FileOutputStream(imagePath);
			 imageOutFile.write(imageByteArray);
			 imageOutFile.close();
			 return true;
		 }catch(Exception e)
		 {
			 LOG.error(e);
			 return false;
		 }
	 }

	public String convertImageFileToBase64String(File file)
	{
     try{
    	// File file = new File(imagePath);
    	 FileInputStream imageInFile = new FileInputStream(file);
         byte imageData[] = new byte[(int) file.length()];
         imageInFile.read(imageData);
         imageInFile.close();
         return Base64.encodeBase64URLSafeString(imageData);
     }catch(Exception e)
     {
    	 LOG.error(e);
    	 return null;
     }
   }
	public String convertXmlFileToBase64String(File file)
	{
     try{
    	// File file = new File(imagePath);
    	// FileInputStream imageInFile = new FileInputStream(file);
         byte imageData[] = loadFile(file);
   
         byte[] encoded = Base64.encodeBase64(imageData);
         String encodedString = new String(encoded);
         return encodedString;
         
     }catch(Exception e)
     {
    	 LOG.error(e);
    	 return null;
     }
   }
	
	private static byte[] loadFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);

	    long length = file.length();
	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }
	    byte[] bytes = new byte[(int)length];
	    
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }

	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }

	    is.close();
	    return bytes;
	}

	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
	{
	    File convFile = new File( multipart.getOriginalFilename());
	    multipart.transferTo(convFile);
	    return convFile;
	}
	
	public File convert(MultipartFile file) throws IllegalStateException, IOException 
	{    
		try {
	    File convFile = new File(file.getOriginalFilename());
	    convFile.createNewFile(); 
	    FileOutputStream fos;
		fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close(); 
	    
	    return convFile;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		return null;
	}
	// base64 string to pdf conversation
	public String convertBase64ToPDF(String base64Str){
		List<String> imagePaths = new LinkedList<String>();
		try{
			
				String destinationPath = "";          //path 
				String [] pathsArr=base64Str.split(",");
				 byte[] imageByteArray=null;
				if(pathsArr != null && pathsArr.length > 1){
					imageByteArray = Base64.decodeBase64(pathsArr[1]);
				}else{
					 imageByteArray = Base64.decodeBase64(base64Str);
				}
				destinationPath = destinationPath+".jpg";
				File file = new File(destinationPath);
				FileOutputStream fop = new FileOutputStream(file);
				fop.write(imageByteArray);
				fop.flush();
				fop.close();
				imagePaths.add(destinationPath);
			
			//convert image to PDF
				Long width=0l;
				Long height=0L;
			String finalFilePath = convertImageToPdf(destinationPath, width , height);
			return finalFilePath;
		}catch(Exception e){
			
		}
		return null;
	}
	public String convertImageToPdf(String imgePath,Long width ,Long height){
		String destinationPath = "";
		try{
			if(destinationPath !=null && destinationPath.trim().length() >0){
		
				destinationPath = destinationPath+".pdf";
				Rectangle pdfSize = new Rectangle(width,height);
				Document document = new Document(pdfSize);
				Font ffont = new Font(Font.FontFamily.UNDEFINED, 24, Font.BOLD);
				 FileOutputStream fos = new FileOutputStream(destinationPath);
			      PdfWriter writer = PdfWriter.getInstance(document, fos);
			      writer.open();
			      document.open();
			      PdfContentByte cb = writer.getDirectContent();
		          Phrase header = new Phrase("   ", ffont);
		          ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
		                  header,
		                  (document.right() - document.left()) / 2 + document.leftMargin(),
		                  document.top() + 10, 0);
			    	  Image image1 = Image.getInstance(imgePath);
				      image1.setSpacingAfter(0);
				      document.add(image1);
				      File removeFile = new File(imgePath);
				      removeFile.delete();
			      
			}
		}catch(Exception e){
			
		}
		return destinationPath;
	}
		
}
