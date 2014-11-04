package com.itgrids.cadrecardprinting;

import java.net.URL;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;


// 
public class CardTest123 extends Application{
    
   private Scene scene;
   MyBrowser myBrowser;
    
   Label labelFromJavascript;

  
  /* public static void main(String[] args) {
       launch(args);
   }*/
    
   @Override
   public void start(Stage primaryStage) {
       primaryStage.setTitle("");
        
     
       myBrowser = new MyBrowser();
       scene = new Scene(myBrowser, 640, 480);
        
      // primaryStage.setScene(scene);
       //primaryStage.show();
       
      
   }
    
   class MyBrowser extends Region{
        
	   CardReader sa= new CardReader();
 	   String val = sa.callWebService();
       HBox toolbar;
       VBox toolbox;
        
       WebView webView = new WebView();
       WebEngine webEngine = webView.getEngine();
        
       public MyBrowser(){/*
            
           final URL urlHello = getClass().getResource("C:\\Users\\Client\\Desktop\\WSClient\\WSClient\\src\\main\\webapp\\html\\hello.html");
           webEngine.load(urlHello.toExternalForm());
            
           webEngine.getLoadWorker().stateProperty().addListener(
                   new ChangeListener<State>(){
                        
                       @Override
                       public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
                           if(newState == State.SUCCEEDED){
                               JSObject window = (JSObject)webEngine.executeScript("window");
                               window.setMember("app", new JavaApplication());
                           }
                       }
                   });
            
            
           JSObject window = (JSObject)webEngine.executeScript("window");
           window.setMember("app", new JavaApplication());
            
        
           Button buttonEnter = new Button("Get Value");
           buttonEnter.setOnAction(new EventHandler<ActionEvent>(){
                
               @Override
               public void handle(ActionEvent arg0) {
            	 
            	 
                   webEngine.executeScript( " getData(' "+val+ " ') " );
               }
           });
            
        
            
           toolbar = new HBox();
     
           toolbar.setSpacing(10);
       
           toolbar.getChildren().addAll( buttonEnter);
            
           toolbox = new VBox();
        
           toolbox.getChildren().addAll(toolbar);
        
           getChildren().add(toolbox);
           getChildren().add(webView);
            
       */}
        
       @Override
       protected void layoutChildren(){/*
           double w = getWidth();
           double h = getHeight();
           double toolboxHeight = toolbox.prefHeight(w);
           layoutInArea(webView, 0, 0, w, h-toolboxHeight, 0, HPos.CENTER, VPos.CENTER);
           layoutInArea(toolbox, 0, h-toolboxHeight, w, toolboxHeight, 0, HPos.CENTER, VPos.CENTER);
       */}
        
   }
    
   public class JavaApplication {
       public void callFromJavascript(String msg) {
           labelFromJavascript.setText("Click from Javascript: " + msg);
       }
   }
    
}