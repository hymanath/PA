package com.itgrids.cadrecardprinting;

import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

	
public class SelectKeyComboBoxListener implements EventHandler<KeyEvent> {
 
    private ComboBox comboBox;
    private StringBuilder sb = new StringBuilder();
    
    @SuppressWarnings("unchecked")
	public SelectKeyComboBoxListener(ComboBox comboBox) {
        this.comboBox = comboBox;
        this.comboBox.setOnKeyPressed(SelectKeyComboBoxListener.this);
        sb.delete(0, sb.length());
        this.comboBox.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE && sb.length() > 0) {
                    sb.delete(0, sb.length());
                }
            }
        });
      // add a  listener such that if not in focus, reset the filtered typed keys
      this.comboBox.showingProperty().addListener(new ChangeListener(){
    	
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue instanceof Boolean && !((Boolean) newValue).booleanValue())
                    sb.delete(0, sb.length());
                else {
                    ListView lv = ((ComboBoxListViewSkin) SelectKeyComboBoxListener.this.comboBox.getSkin()).getListView();
                    lv.scrollTo(lv.getSelectionModel().getSelectedIndex());
                }
            }
        });
      
    
      this.comboBox.focusedProperty().addListener(new ChangeListener() {
      	
          public void changed(ObservableValue observable, Object oldValue, Object newValue) {
              if (newValue instanceof Boolean && !((Boolean) newValue).booleanValue())
                  sb.delete(0, sb.length());
              else {
                  ListView lv = ((ComboBoxListViewSkin) SelectKeyComboBoxListener.this.comboBox.getSkin()).getListView();
                  lv.scrollTo(lv.getSelectionModel().getSelectedIndex());
              }
          }
      });
    this.comboBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {         	
                ListView lv = ((ComboBoxListViewSkin) SelectKeyComboBoxListener.this.comboBox.getSkin()).getListView();
                lv.scrollTo(lv.getSelectionModel().getSelectedIndex());
            }            
        });
    }
 

	@Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.UP || event.getCode() == KeyCode.TAB) {
        	return;
        }
        else if (event.getCode() == KeyCode.BACK_SPACE && sb.length() > 0) {
            sb.deleteCharAt(sb.length()-1);
        }
       else {  	 
            sb.append(event.getText());         
        }
 
        if (sb.length() == 0){
        	  sb.delete(0, sb.length());
        	  return;
        }
          
       
        boolean found = false;
        ObservableList items = comboBox.getItems();
        for (int i=0; i<items.size(); i++) {
            if (event.getCode() != KeyCode.BACK_SPACE && items.get(i).toString().toLowerCase().startsWith(sb.toString().toLowerCase())) {
                ListView lv = ((ComboBoxListViewSkin) comboBox.getSkin()).getListView();
                lv.getSelectionModel().clearAndSelect(i);          
                lv.scrollTo(lv.getSelectionModel().getSelectedIndex());
                found = true;                
                break;
            }
        }
        
               
        if (!found && sb.length() > 0)
          sb.deleteCharAt(sb.length() - 1);
    }
}
