package com.itgrids.cadrecardprinting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringEscapeUtils;

public class PrintClass {
 
	 /* public static void main(String[] args) {
	        CadrePrintVO cadrePrintVO = new CadrePrintVO();
	        imagePrinting(cadrePrintVO);
	    }*/
	    public static void imagePrinting(CadrePrintVO cadrePrintVO)
	    {
	        PrintDemo printDemo = new PrintDemo();
	        try {
	           
	             Font  telegraficoFont = null;
	             Font f1 = null;
	             try {
	                  File font_file = new File("D:/telugu.TTF");
	                     f1 = Font.createFont(Font.TRUETYPE_FONT, font_file);
	                      
	             } catch (Exception e) {
	                
	             }
	             telegraficoFont =  f1.deriveFont(Font.BOLD, 45);
	           
	            String path = "D:/Empty.jpg";
	            File file = new File(path);
	            BufferedImage bufferedImage = ImageIO.read(file);
	        
	            Graphics g = bufferedImage.getGraphics();

	            g.setFont(telegraficoFont);
	            g.setColor(Color.BLACK);
	            g.drawString(cadrePrintVO.getVoterName(), 290, 210);
	            g.dispose();
	         if(cadrePrintVO.getConstituencyType().equalsIgnoreCase("RURAL"))
	         {
	        	 telegraficoFont =  f1.deriveFont(Font.BOLD, 35);
		            Graphics g1 = bufferedImage.getGraphics();
		            g1.setFont(telegraficoFont);
		            g1.setColor(Color.BLACK);
		            g1.drawString(cadrePrintVO.getVillage()  +" "+ StringEscapeUtils.unescapeJava("\u0C17\u0C4D\u0C30\u0C3E\u0C2E\u0C02")+" "+  cadrePrintVO.getMandal() + ""+ StringEscapeUtils.unescapeJava("\u0C2E\u0C02\u0C21\u0C32\u0C02")+"   ", 290, 260);
		            g1.dispose();
		            Graphics g2 = bufferedImage.getGraphics();
		            telegraficoFont =  f1.deriveFont(Font.BOLD, 35);
		            g2.setFont(telegraficoFont);
		            g2.setColor(Color.BLACK);
		            g2.drawString(cadrePrintVO.getConstituency()  +" ("+ StringEscapeUtils.unescapeJava("\u0C28\u0C3F")+") "+  "  "+ cadrePrintVO.getDistrict() + " "+StringEscapeUtils.unescapeJava("\u0C1C\u0C3F\u0C32\u0C4D\u0C32\u0C3E"), 290, 310);
		            g2.dispose();
	         }
	         else if(cadrePrintVO.getConstituencyType().equalsIgnoreCase("RURAL-URBAN"))
	         {
	        	    telegraficoFont =  f1.deriveFont(Font.BOLD, 35);
		            Graphics g1 = bufferedImage.getGraphics();
		            g1.setFont(telegraficoFont);
		            g1.setColor(Color.BLACK);
		            if(cadrePrintVO.getVillage() != null)
		            {
		            	g1.drawString(cadrePrintVO.getVillage()  +" "+ StringEscapeUtils.unescapeJava("\u0C17\u0C4D\u0C30\u0C3E\u0C2E\u0C02")+" "+  cadrePrintVO.getMandal() + ""+ StringEscapeUtils.unescapeJava("\u0C2E\u0C02\u0C21\u0C32\u0C02")+"   ", 290, 260);
		            }
		            else
		            {
		            	  g1.drawString(cadrePrintVO.getMandal() + " "+StringEscapeUtils.unescapeJava("\u0C2E\u0C41\u0C28\u0C4D\u0C38\u0C3F\u0C2A\u0C3E\u0C32\u0C3F\u0C1F\u0C40"), 290, 260);
		            }
		          
		            g1.dispose();
		            Graphics g2 = bufferedImage.getGraphics();
		            telegraficoFont =  f1.deriveFont(Font.BOLD, 35);
		            g2.setFont(telegraficoFont);
		            g2.setColor(Color.BLACK);
		            g2.drawString(cadrePrintVO.getConstituency()  +" ("+ StringEscapeUtils.unescapeJava("\u0C28\u0C3F")+") "+  " "+ cadrePrintVO.getDistrict() + " "+StringEscapeUtils.unescapeJava("\u0C1C\u0C3F\u0C32\u0C4D\u0C32\u0C3E"), 290, 310);
		            g2.dispose();	          
	         }
	         else if(cadrePrintVO.getConstituencyType().equalsIgnoreCase("URBAN"))
	         {
		            Graphics g2 = bufferedImage.getGraphics();
		            telegraficoFont =  f1.deriveFont(Font.BOLD, 35);
		            g2.setFont(telegraficoFont);
		            g2.setColor(Color.BLACK);
		            g2.drawString(cadrePrintVO.getConstituency()  +" ("+ StringEscapeUtils.unescapeJava("\u0C28\u0C3F")+") "+  " "+ cadrePrintVO.getDistrict() + " "+StringEscapeUtils.unescapeJava("\u0C1C\u0C3F\u0C32\u0C4D\u0C32\u0C3E"), 290, 260);
		            g2.dispose();
	         }
	           BufferedImage img = ImageIO.read(new URL(cadrePrintVO.getVoterImgPath()));
	          
	            BufferedImage b2 = resizeImage(img,1);
	          
	            Graphics2D grph = (Graphics2D) bufferedImage.getGraphics();
	            grph.scale(1.1, 1.1);
	            grph.drawImage(b2, 89, 67, null);
	           
	            grph.drawImage(returnCreatedImage(cadrePrintVO.getFirstCode()), 246, 76, null);
	            grph.dispose();
	           RenderedImage  ri = bufferedImage;
	         
	         
	           printDemo.print(ri);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	           
	    }
	   
	    private static BufferedImage returnCreatedImage(String code)
	    {
	        code = code.substring(3, code.length());
	        int width = 218;
	        int height = 70;

	        // Constructs a BufferedImage of one of the predefined image types.
	        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	      
	        // Create a graphics which can be used to draw into the buffered image
	        Graphics2D g2d = bufferedImage.createGraphics();
	       
	        Color rgb = new Color(230,0,0);
	        // fill all the image with white
	        g2d.setColor(rgb);
	        g2d.fillRect(0, 0, width, height);

	        Font f = new Font("Verdana", Font.BOLD, 30);
	        // create a string with yellow
	        g2d.setColor(Color.yellow);
	        g2d.setFont(f);
	        g2d.drawString(code, 14, 33);
	       
	        Font f1 = new Font("Verdana", Font.BOLD, 25);
	        g2d.setFont(f1);
	       
	        g2d.drawString("  2014-2016", 14, 60);

	        // Disposes of this graphics context and releases any system resources that it is using.
	        g2d.dispose();
	       
	        return bufferedImage;
	    }
	  
	    private static BufferedImage resizeImage(BufferedImage originalImage, int type)
	    {
	      //  BufferedImage croppedImage = null;
	        
	        
	    	BufferedImage resizedImage = null;
	        try {
	        	 resizedImage = new BufferedImage(155, 187, type);
	        	Graphics2D g = resizedImage.createGraphics();
		    	g.drawImage(originalImage, 0, 0, 155, 187, null);
		    	g.dispose();
			} catch (Exception e) {
				resizedImage = new BufferedImage(155, 187, type);
	                Graphics2D g = resizedImage.createGraphics();
	                g.drawImage(originalImage, 0, 0, 160, 200, null);
	                g.dispose();
			}
	    	
	     
	    	return resizedImage;
	       /* try {
	             croppedImage = originalImage.getSubimage( 0, 0, 155, 187);
	        } catch (Exception e) {
	                croppedImage = new BufferedImage(155, 187, type);
	                Graphics2D g = croppedImage.createGraphics();
	                g.drawImage(originalImage, 0, 0, 160, 200, null);
	                g.dispose();
	        }
	      
	       
	       
	        return croppedImage;*/

	       // return resizedImage;
	    }
	}