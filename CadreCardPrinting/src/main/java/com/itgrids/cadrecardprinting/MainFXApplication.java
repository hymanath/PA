package com.itgrids.cadrecardprinting;



import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class MainFXApplication extends Application{
	
	
	@Override
			   
	   	public void start(Stage primaryStage) throws IOException {		
		
			URL location = getClass().getResource("/com/itgrids/cadrecardprinting/NFCController.fxml");	
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(location);
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			AnchorPane root = (AnchorPane) fxmlLoader.load();
			Scene scene = new Scene(root);
	        primaryStage.setTitle("TDP MemberShip Drive");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }
 
		public static void main(String[] args) {
		    Application.launch(args);    	
		}



}

