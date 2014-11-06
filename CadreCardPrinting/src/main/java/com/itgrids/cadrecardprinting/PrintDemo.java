package com.itgrids.cadrecardprinting;
 
import java.awt.image.RenderedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.smartcardio.ATR;
import javax.swing.plaf.basic.BasicBorders.MarginBorder;

import com.sun.javafx.scene.layout.region.Margins;


public class PrintDemo {

    public static void print(RenderedImage image)
            throws PrinterException, PrintException, IOException {

        System.out.println("PRINTING...");

        DocFlavor docFlavor = DocFlavor.INPUT_STREAM.PNG;

        PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
        attributes.add(new Copies(1));
        attributes.add(OrientationRequested.LANDSCAPE);
        PrintService printServices[] = PrintServiceLookup.lookupPrintServices(
                docFlavor, attributes);
        if (printServices.length == 0) {
            System.err.println("PrintService for PNG not available!");
            throw new RuntimeException("PrintService for PNG not available!");
        }

        System.out.println("Got PrintService: " + printServices[0].getName());
        attributes.add(new MediaPrintableArea(25.0f,25.0f,25.0f,25.0f,MediaPrintableArea.INCH));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "png", out);
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

        // Show PrintDialog
      //  boolean print = PrinterJob.getPrinterJob().printDialog(attributes);
        boolean print = true;
        if(print) {
            DocPrintJob job = printServices[0].createPrintJob();
            Doc doc = new SimpleDoc(in, docFlavor, null);
            job.print(doc, attributes);
            in.close();
        }

        System.out.println("Done PrintService: " + print);
    }
    
    public static void print1(RenderedImage image)
            throws PrinterException, PrintException, IOException {
    	 boolean print = false;
        final DocFlavor docFlavor = DocFlavor.INPUT_STREAM.PNG;

        final PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
        attributes.add(new Copies(1));
        attributes.add(OrientationRequested.LANDSCAPE);
        final PrintService printServices[] = PrintServiceLookup.lookupPrintServices(
                docFlavor, attributes);
        if (printServices.length == 0) {
            System.err.println("PrintService for PNG not available!");
            throw new RuntimeException("PrintService for PNG not available!");
        }

        attributes.add(new MediaPrintableArea(25.0f,25.0f,25.0f,25.0f,MediaPrintableArea.INCH));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "png", out);
        final ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
       
        Stage stage = new Stage();
    	GridPane grd_pan = new GridPane();
    	
    	Scene scene =new Scene(grd_pan);
    	stage.setScene(scene);
    	Image imageee=  new Image(in);
    	
        ImageView imgView = new ImageView(imageee);  
        imgView.setFitHeight(300);
        imgView.setFitWidth(600);
    	grd_pan.add(imgView,1,1);
    	Button btn = new Button();
    	btn.setText("Print");
    	grd_pan.add(btn,1,4);
    	
        stage.show();
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	 DocPrintJob job = printServices[0].createPrintJob();
                 Doc doc = new SimpleDoc(in, docFlavor, null);
                 try {
					job.print(doc, attributes);
				} catch (PrintException e) {
					e.printStackTrace();
				}
                 try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
    
    }
}
