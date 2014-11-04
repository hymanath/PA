		package com.itgrids.cadrecardprinting;
		

		import java.net.URL;
		import java.util.ArrayList;
		import java.util.List;
		import java.util.ResourceBundle;

		import javafx.application.Platform;
		import javafx.beans.property.BooleanProperty;
		import javafx.beans.value.ChangeListener;
		import javafx.beans.value.ObservableValue;
		import javafx.collections.FXCollections;
		import javafx.collections.ObservableList;
		import javafx.event.ActionEvent;
		import javafx.event.EventHandler;
		import javafx.fxml.FXML;
		import javafx.fxml.Initializable;
		import javafx.geometry.Pos;
		import javafx.scene.Scene;
		import javafx.scene.control.Button;
		import javafx.scene.control.CheckBox;
		import javafx.scene.control.Label;		
		import javafx.scene.control.ComboBox;
		import javafx.scene.control.ContentDisplay;
		import javafx.scene.control.ProgressBar;
		import javafx.scene.control.RadioButton;
		import javafx.scene.control.SelectionMode;
		import javafx.scene.control.Toggle;
		import javafx.scene.control.ToggleGroup;
		import javafx.scene.control.TableCell;
		import javafx.scene.control.TableColumn;
		import javafx.scene.control.TableView;
		import javafx.scene.control.TextField;	
		import javafx.scene.control.cell.PropertyValueFactory;
		import javafx.scene.image.Image;
		import javafx.scene.image.ImageView;
		import javafx.scene.layout.AnchorPane;
		import javafx.scene.layout.GridPane;
		import javafx.stage.Modality;
		import javafx.stage.Stage;
		import javafx.util.Callback;	
		import com.google.gson.*;
		import com.google.gson.reflect.*;
		
		
		public class FXController implements Initializable{
		
		
		@FXML
		private static TableView<CadrePrintVO> tableView = new TableView<CadrePrintVO>();
		@FXML
		private TableColumn sId = new TableColumn();
		@FXML
		private TableColumn VoterCardNum = new TableColumn();
		@FXML
		private TableColumn VoterName = new TableColumn();
		@FXML
		private TableColumn RelativeName = new TableColumn();
		@FXML
		private TableColumn MemberShipNum = new TableColumn();
		@FXML
		private TableColumn CardPrintStatus = new TableColumn();
		@FXML
		private TableColumn VoterIdTagging = new TableColumn();
		@FXML
		private static TableColumn NFCNumberId = new TableColumn();
		@FXML
		private TableColumn voterIdValue = new TableColumn();
		@FXML
		private TableColumn refId = new TableColumn();
		@FXML
		private TextField NFCNumber;
		@FXML
		private TableColumn linkId = new TableColumn();
		@FXML
		private TableColumn imageId = new TableColumn();
		@FXML
		private TableColumn voterImageId = new TableColumn();		
		@FXML
		private TableColumn typeId = new TableColumn();		
		@FXML
		private AnchorPane anchorPaneId;
		@FXML
		private static CheckBox VoterIdTag ;		
		@FXML
		private ComboBox constCombo;
		@FXML
		private ComboBox mandalCombo;
		@FXML
		private ComboBox panchayatCombo;
		@FXML
		private ComboBox districtCombo;
		@FXML
		private ComboBox regTypeCombo;
		@FXML
		private ProgressBar progressBar;		
		@FXML
		private Label loadingId;
		
		TextField searchId = new TextField();
			
		CadrePrintVO membershipValue = null;
		
		ObservableList<CadrePrintVO> data = null;
		
		String selectedImgPath ;
		
		@SuppressWarnings("unchecked")
		@FXML private void handleButtonAction() {
			
			JerseyClientGet client = new JerseyClientGet();
			
			//New Code
			CadrePrintInputVO inputVO = new CadrePrintInputVO();
			if(panchayatCombo.getSelectionModel().getSelectedItem() != null){
			String name = panchayatCombo.getSelectionModel().getSelectedItem().toString();
			
			Long id = getMatchedName(panchayatList, name);
			String type ="panchayat";
			if(id!=null){
				inputVO.setPanchayatId(id);
			}
			if(id == null){
				name = name.replaceAll("\\D+","");
				id = getMatchedName(boothsList, name);
				type ="booth";				
				if(id!=null){
					inputVO.setBoothId(id);
				}
			}
			}
			String regTypeName = regTypeCombo.getSelectionModel().getSelectedItem().toString();			
			inputVO.setRegType(regTypeName);
			
			if(districtCombo.getSelectionModel().getSelectedItem() !=  null){
				String districtName = districtCombo.getSelectionModel().getSelectedItem().toString();			
				Long districtId = getMatchedName(districtsList, districtName);
				inputVO.setDistrictId(districtId);
			}
			
			if(constCombo.getSelectionModel().getSelectedItem() !=  null){
				String constName = constCombo.getSelectionModel().getSelectedItem().toString();			
				Long constId = getMatchedName(constiList, constName);
				inputVO.setConstituencyId(constId);
			}
			if(mandalCombo.getSelectionModel().getSelectedItem() != null){			
			String mandalName = mandalCombo.getSelectionModel().getSelectedItem().toString();			
			mandalName= mandalName.substring(0, mandalName.length() - 6);
			Long mandalId = getMatchedName(mandalList, mandalName.trim());
			if(mandalId != null){				
				inputVO.setMandalId(mandalId);
			}
			else{
				mandalName= mandalName.substring(0, mandalName.length() - 6);
				Long localBodyId = getMatchedName(localBodiesList, mandalName);
				if(localBodyId!=null){
					inputVO.setLocalBodyId(localBodyId);
				}
				
			}
			}
			final Object obj = client.getCadredetailsBySelection(inputVO);
			
			//System.out.println(id);
			
			
			//Object obj = client.getCadredetailsBySelection(id,"panchayat");
			
			//Old Code
			/*CadrePrintInputVO inputVO = new CadrePrintInputVO();
			String name = panchayatCombo.getSelectionModel().getSelectedItem().toString();
			Long id = getMatchedName(panchayatList, name);
			String type ="panchayat";
			
			if(id == null){
				name = name.replaceAll("\\D+","");
				id = getMatchedName(boothsList, name);
				type ="booth";				
			}
			System.out.println(id);*/
			//Object obj = client.getCadredetailsByPanchayat(3347l,"panchayat");
		
			
			if(obj != null){
				new Thread() {
            	    @Override
            	    public void run() {
			try {
				Gson gson = new Gson();
				TypeToken<List<CadrePrintVO>> token = new TypeToken<List<CadrePrintVO>>(){};				
				List<CadrePrintVO> personList = gson.fromJson(obj.toString(), token.getType());			
				data = FXCollections.observableArrayList(personList);
				System.out.println(data);
			} catch (Exception e) {
					e.printStackTrace();
			}
			
			tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			tableView.setEditable(true);
			sId.setCellValueFactory(new PropertyValueFactory<CadrePrintVO,String>("cadrePrintVOId"));
			VoterCardNum.setCellValueFactory(new PropertyValueFactory<CadrePrintVO, String>("voterCardNo"));
			VoterName.setCellValueFactory(new PropertyValueFactory<CadrePrintVO, String>("voterName"));
			RelativeName.setCellValueFactory(new PropertyValueFactory<CadrePrintVO, String>("relativeName"));
			MemberShipNum.setCellValueFactory(new PropertyValueFactory<CadrePrintVO, String>("firstCode"));
			CardPrintStatus.setCellValueFactory(new PropertyValueFactory<CadrePrintVO, String>("printStatus"));
			voterIdValue.setCellValueFactory(new PropertyValueFactory<CadrePrintVO, String>("voterId"));
			NFCNumberId.setCellValueFactory(new PropertyValueFactory<CadrePrintVO, String>("nfcNumber"));
			NFCNumberId.setEditable(true);
			refId.setCellValueFactory(new PropertyValueFactory<CadrePrintVO, String>("refNumber"));
			VoterIdTagging.setCellValueFactory(new PropertyValueFactory("voterIdTagging1"));
			VoterIdTagging.setCellFactory(new Callback<TableColumn<CadrePrintVO, CheckBox>, TableCell<CadrePrintVO, CheckBox>>() {
			
			public TableCell<CadrePrintVO, CheckBox> call(TableColumn<CadrePrintVO, CheckBox> p) {
			
				return new CheckBoxTableCell<CadrePrintVO, CheckBox>();
			
			}
			
			});
			linkId.setCellValueFactory(new PropertyValueFactory<CadrePrintVO, String>("voterId"));	
			
			Callback<TableColumn<CadrePrintVO, String>, TableCell<CadrePrintVO, String>> buttonCell = new Callback<TableColumn<CadrePrintVO, String>, TableCell<CadrePrintVO, String>>() {
			
			@Override
			public TableCell call(final TableColumn param) {
			    final TableCell cell = new TableCell() {
			    	
			        @Override
			        public void updateItem(final Object item, final boolean empty) {
			            super.updateItem(item, empty);
			            if (empty) {
			                setText(null);
			                setGraphic(null);
			            } 
			            else {
			            	
			                final Button  btn = new Button(); 
			                btn.setId(item.toString());
			                //btn.setText("NFC Link Voter Id");
			                btn.setPrefWidth(300);
			                btn.setOnAction(new EventHandler<ActionEvent>() {
			                    
			                	public void handle(ActionEvent event) {
			                    	
			                    	if(btn.getId().equalsIgnoreCase("activeLinkId")){
			                    	 JerseyClientGet client = new JerseyClientGet(); 
			                    	
			                    	 for(int i=0;i<data.size();i++){
			  							   CadrePrintVO cellData = data.get(i);
			  							   if(cellData.getVoterId().equalsIgnoreCase(item.toString())){
				  							   if(cellData.getVoterIdTagging1().getValue() ){						
				            					 String  status = client.tagCardIdForNFCReader(cellData.getNfcNumber(),new Long (item.toString()));
				            					 msgBox(status);
					  							 cellData.getVoterIdTagging1().setValue(Boolean.FALSE);
					  							 btn.setText("Delink");
					  							 btn.setId("deactiveLinkId");				  							
				  								}else{
				  									msgBox("Please Provide The NFC Number");
				  							   }
			  							   }	  
			                    		}
			            			
			                    	}
			                    	else{
			                    		 JerseyClientGet client = new JerseyClientGet(); 
			                    		 for(int i=0;i<data.size();i++){
			  							   CadrePrintVO cellData = data.get(i);			  							  
			  							   if(cellData.getVoterId().equalsIgnoreCase(item.toString())){
			  								 String  status = client.delinkNFCNumber(cellData.getNfcNumber(),new Long (item.toString()));
			  								 if(status.equalsIgnoreCase("success")){
			  									 cellData.getVoterIdTagging1().setValue(Boolean.FALSE);
				  								 cellData.nfcNumberProperty().set("");			  								
				  								 btn.setText("link");
				  								 btn.setId("activeLinkId");
			  								 }
			  							   }			  							
			                    		 }			               			                   			                    		
			                    	}
			                    	tableView.setItems(data);
			                    }
			                });
			        
			                setGraphic(btn);
			                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
					        JerseyClientGet nfcChecking = new JerseyClientGet();
								String  status = nfcChecking.checkNFCNumberForVoterId(Long.valueOf(item.toString()));
								if(status.equalsIgnoreCase("CardAssigned"))
								{
									btn.setText("Delink");
									btn.setId("deactiveLinkId");
								}
								else{
									btn.setText("link");
									btn.setId("activeLinkId");									
								}                           
			            }				       
			        }
			    };			 
			    return cell;
			}
			};
		
			//final String imagesPath = "http://mytdp.com/voter_images/282/Part99/UXN0978744.jpg";
			linkId.setCellFactory(buttonCell);
			
			typeId.setCellValueFactory(new PropertyValueFactory<CadrePrintVO, String>("photoType"));
			
			//final String mamberPath = "http://mytdp.com/images/cadre_images/";
			imageId.setCellValueFactory(new PropertyValueFactory<CadrePrintVO,String>("image"));
			imageId.setCellFactory(new Callback<TableColumn<CadrePrintVO,String>,TableCell<CadrePrintVO,String>>(){        
		           
		            public TableCell<CadrePrintVO,String> call(TableColumn<CadrePrintVO,String> param) {                
		                TableCell<CadrePrintVO,String> cell = new TableCell<CadrePrintVO,String>(){
		                    
		                	 public void updateItem(final String item, boolean empty) {
		                            super.updateItem(item, empty);
		                            if (empty) {
		        		                setText(null);
		        		                setGraphic(null);
		        		            } 
		        		            else {	                            
		        		            	new Thread() {
		        		            	    @Override
		        		            	    public void run() {
		                               Image folderIcon = new Image(item);
		                               final ImageView image = new ImageView(folderIcon);
		                               image.setFitHeight(50);
		                               image.setFitWidth(50);
		                               
		                               Platform.runLater(new Runnable() {
		                                   @Override
		                                   public void run() {
		                                	   setGraphic(image);
		                                   }
		                                 });
		                             }
		                         }.start();
		                        }
		                	 }
		                    };
		                    return cell;
		            }		            
		        });       
						
			voterImageId.setCellValueFactory(new PropertyValueFactory<CadrePrintVO, String>("voterId"));	
			
			Callback<TableColumn<CadrePrintVO, String>, TableCell<CadrePrintVO, String>> buttonCell1 = new Callback<TableColumn<CadrePrintVO, String>, TableCell<CadrePrintVO, String>>() {

			
			@Override
			public TableCell call(final TableColumn param) {
			    final TableCell cell = new TableCell() {
			    	
			        public void updateItem(final Object item, final boolean empty) {
			            super.updateItem(item, empty);
			            if (empty) {
			                setText(null);
			                setGraphic(null);
			            } 
			            else {
			            	new Thread() {
    		            	    @Override
    		            	    public void run() {
                         
			                final Button  btn = new Button(); 
			                btn.setText("View Images");
			                btn.setPrefWidth(180);
			                btn.setOnAction(new EventHandler<ActionEvent>() {
			                    
			                	public void handle(ActionEvent event) {
			                		//final String mamberPath = "http://mytdp.com/images/cadre_images/";
			                		
			                		final Stage dialogStage = new Stage();
			                		GridPane grd_pan = new GridPane();
			            			grd_pan.setAlignment(Pos.CENTER);
			            			grd_pan.setHgap(10);
			            			grd_pan.setVgap(10);//padding
			            			Scene scene =new Scene(grd_pan,400,300);
			            			dialogStage.setScene(scene);
			            			
			                		for(int i=0;i<data.size();i++){
			  							    CadrePrintVO cellData = data.get(i);	
			  							   if(cellData.getVoterId().equalsIgnoreCase(item.toString())){
			  							    ImageView imageView = new ImageView();  
			  								  
			  						        final ToggleGroup group = new ToggleGroup();
			  						        Label heading = new Label();
				  						    heading.setText("Select the Image to Change");
				  						    grd_pan.add(heading, 0, 1);	
			  						        
				  						    RadioButton rb1 = new RadioButton("");
			  						        rb1.setToggleGroup(group);
			  						        rb1.setUserData(cellData.getImage());
			  						        grd_pan.add(rb1, 0, 2);	
			  						        Image image = new Image(rb1.getUserData().toString());
			  						        imageView.setImage(image);
			  						        grd_pan.add(imageView, 1, 2);
			  						        
			  						        RadioButton rb2 = new RadioButton("");
			  						        rb2.setToggleGroup(group);
			  						        rb2.setUserData(cellData.getImgPath1());
			  						        grd_pan.add(rb2, 0, 3);	
			  						        Image image1 = new Image(rb1.getUserData().toString());
			  						        ImageView imageView1 =  new ImageView();  
			  						        imageView1.setImage(image1);
			  						        grd_pan.add(imageView, 1, 3);
	  						               
			  						        RadioButton rb3 = new RadioButton("");
			  						        rb3.setToggleGroup(group);
			  						        rb3.setUserData(cellData.getImgPath2());
			  						        Image image2 = new Image(rb3.getUserData().toString());
			  						        ImageView imageView2 =  new ImageView();  
			  						        imageView2.setImage(image2);
			  						        grd_pan.add(imageView2, 1, 4);
			  						        grd_pan.add(rb3, 0, 4);		
			  						        
			  						       
			  						        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			  						            public void changed(ObservableValue<? extends Toggle> ov,
			  						                Toggle old_toggle, Toggle new_toggle) {
			  						                    if (group.getSelectedToggle() != null) {
			  						                    /*final Image image = new Image(group.getSelectedToggle().getUserData().toString());
			  						                	icon.setImage(image);*/
			  						                    selectedImgPath = group.getSelectedToggle().getUserData().toString();
			  						                }                
			  						            }
			  						        });
			  							   }
			                		 }
			                		 
			            			dialogStage.show();
			            			
			                    }
			                });

	                           Platform.runLater(new Runnable() {
	                               @Override
	                               public void run() {
	                            	   setGraphic(btn);
	       			                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	                               }
	                             });
	                         }
	                     }.start();
			               
					       
			            }				       
			        }
			    };			 
			    return cell;
			}
			};
			
		
			voterImageId.setCellFactory(buttonCell1);
			
			 
			
					
			/*final String mamberPath = "http://mytdp.com/images/cadre_images/";
			imageId.setCellValueFactory(new PropertyValueFactory<CadrePrintVO,String>("image"));
			imageId.setCellFactory(new Callback<TableColumn<CadrePrintVO,String>,TableCell<CadrePrintVO,String>>(){        
		           
		            public TableCell<CadrePrintVO,String> call(TableColumn<CadrePrintVO,String> param) {                
		                TableCell<CadrePrintVO,String> cell = new TableCell<CadrePrintVO,String>(){
		                    
		                	 public void updateItem(String item, boolean empty) {
		                            super.updateItem(item, empty);
		                            if (empty) {
		        		                setText(null);
		        		                setGraphic(null);
		        		            } 
		        		            else {	                            
		                              // Image folderIcon = new Image(SampleController.class.getResource("img").toString()+"/"+item); 
		                               Image folderIcon = new Image(mamberPath+item);
		                               setGraphic(new ImageView(folderIcon));
		                        }
		                	 }
		                    };
		                    return cell;
		            }		            
		        });        
			*/
			/*voterImageId.setCellValueFactory(new PropertyValueFactory<CadrePrintVO,String>("voterImgPath"));
			voterImageId.setCellFactory(new Callback<TableColumn<CadrePrintVO,String>,TableCell<CadrePrintVO,String>>(){        
		           
		            public TableCell<CadrePrintVO,String> call(TableColumn<CadrePrintVO,String> param) {                
		                TableCell<CadrePrintVO,String> cell = new TableCell<CadrePrintVO,String>(){
		                    
		                	 public void updateItem(String item, boolean empty) {
		                            super.updateItem(item, empty);
		                            if (empty) {
		        		                setText(null);
		        		                setGraphic(null);
		        		            } 
		        		            else {
		                            
		                             
		                               Image folderIcon = new Image(item);
		                               setGraphic(new ImageView(folderIcon));
		                        }
		                	 }
		                    };
		                    return cell;
		            }		            
		        }); */
			 Platform.runLater(new Runnable() {
                 @Override
                 public void run() {
                	 tableView.setItems(data);
                 }
               });
           }
       }.start();	
			
	
			searchId.setLayoutX(210);
			searchId.setLayoutY(30);
			searchId.setPromptText("Search");
			searchId.setPrefWidth(170);
			searchId.setPrefHeight(25);
			anchorPaneId.getChildren().add(0,searchId);
			searchId.textProperty().addListener(new ChangeListener<String>() {
			
			public void changed(ObservableValue<? extends String> arg0,String arg1, String arg2) {
			
				ObservableList<CadrePrintVO> tableValues = FXCollections.observableArrayList();
				ObservableList<TableColumn<CadrePrintVO, ?>> columns = tableView.getColumns();
				tableView.setItems(data);				
				if(searchId.textProperty().get().isEmpty()){
					tableView.setItems(data);
					return;
				}
				for(int i=0;i<tableView.getItems().size();i++){
					CadrePrintVO cellData = data.get(i);
					if(cellData.getFirstCode().toLowerCase().contains(searchId.getText().toLowerCase()) || cellData.getVoterName().toLowerCase().contains(searchId.getText().toLowerCase()) || cellData.getRefNumber().toLowerCase().contains(searchId.getText().toLowerCase())){
						tableValues.add(tableView.getItems().get(i));
					}
				}
				tableView.setItems(tableValues);
			}
			
			});
			}
			else{
				ObservableList<CadrePrintVO> tableValues1 = FXCollections.observableArrayList();
				tableView.setItems(tableValues1);
			}
		
		}
		
		@SuppressWarnings("unused")
		@FXML
		private void checkboxHandleAction(){
		
		/*for ( CadrePrintVO item :tableView.getItems()) {
			item.getVoterIdTagging1().set(VoterIdTag.isSelected());
			CardTest cardTest = new CardTest();
			String cardNo = cardTest.getUniCode();
			if(VoterIdTag.isSelected())
			item.nfcNumberProperty().set(cardNo);
			else
			item.nfcNumberProperty().set("");
			}
			tableView.setItems(tableView.getItems());*/
		}
		
		@SuppressWarnings("unused")
		@FXML private void handleButtonActionForCardSaning() {
			JerseyClientGet client = new JerseyClientGet();
			String[] args = null;
			for ( CadrePrintVO item :data) {
				if(item.getVoterIdTagging1().getValue()){
					System.out.println(item.getVoterId() +" --- "+ item.getNfcNumber());
					String status = client.tagCardIdForNFCReader(item.getNfcNumber(),new Long (item.getVoterId()));
					//msgBox(status);
					
				}
				tableView.setItems(data);
			}
		}
		
		@SuppressWarnings("unused")
		@FXML private void handleButtonActionForCardSPrinting() {
			JerseyClientGet client = new JerseyClientGet();
			String[] args = null;
			System.out.println(selectedImgPath);
			for ( CadrePrintVO item :data) {
				if(!item.getNfcNumber().isEmpty() && item.getPrintStatus().equalsIgnoreCase("Pending")){				
					
					System.out.println(item.getFirstCode());
					Object obj = client.getCadreDetailsForPrinting(item.getFirstCode());
					PrintClass printClass = new PrintClass();
					
					Gson gson = new Gson();
					TypeToken<CadrePrintVO> token = new TypeToken<CadrePrintVO>(){};
					
					CadrePrintVO vo = gson.fromJson(obj.toString(), token.getType());
					printClass.imagePrinting(vo);
				}
			}
		}
		
		public void msgBox(String title){
			final Stage dialogStage = new Stage();
			GridPane grd_pan = new GridPane();
			grd_pan.setAlignment(Pos.CENTER);
			grd_pan.setHgap(10);
			grd_pan.setVgap(10);//pading
			Scene scene =new Scene(grd_pan,200,150);
			dialogStage.setScene(scene);
			dialogStage.setTitle("alert");
			dialogStage.initModality(Modality.WINDOW_MODAL);			
			Label lab_alert= new Label(title);
			grd_pan.add(lab_alert, 0, 1);			
			Button btn_ok = new Button("OK");
			btn_ok.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {			
				dialogStage.hide();			
			}
			});
			grd_pan.add(btn_ok, 0, 2);			
			dialogStage.show();	
		}
		
		List<DataVO> constiList =new ArrayList<DataVO>();
		List<DataVO> mandalList =new ArrayList<DataVO>();
		List<DataVO> panchayatList =new ArrayList<DataVO>();
		List<DataVO> localBodiesList = new ArrayList<DataVO>();
		List<DataVO> boothsList = new ArrayList<DataVO>();
		
		List<DataVO> districtsList = new ArrayList<DataVO>();
		CardPrintingDAO cardPrintingDAO = new CardPrintingDAO();
		@SuppressWarnings("unchecked")
		public void initialize(URL location, ResourceBundle resources) {	
			
			regTypeCombo.setValue("WEB");
			
			districtsList = cardPrintingDAO.getAllDistricts();
			
			for(DataVO list : districtsList)
			{
				districtCombo.getItems().addAll(list.getName());
			}
			new SelectKeyComboBoxListener(districtCombo);	
			
		}
			
		@SuppressWarnings("unused")
		@FXML
		private void getConstituencyHandleAction()
		{
			constCombo.getItems().clear();
			if(districtCombo.getItems().size() > 0 && districtCombo.getItems() != null){
			String districtName = districtCombo.getSelectionModel().getSelectedItem().toString();
			Long districtId = getMatchedName(districtsList, districtName);
	
			constiList = cardPrintingDAO.getAllConstituencies(districtId);
								
			for(DataVO list : constiList)
			{
				constCombo.getItems().addAll(list.getName());
			}
			}
			new SelectKeyComboBoxListener(constCombo);	
			
		}
		
		@SuppressWarnings("unused")
		@FXML
		private void getMandalHandleAction()
		{
			mandalCombo.getItems().clear();
			if(constCombo.getItems().size() > 0 && constCombo.getItems() != null){
				
				String constiName = constCombo.getSelectionModel().getSelectedItem().toString();
				Long constiId = getMatchedName(constiList, constiName);
				mandalList = cardPrintingDAO.getMandalsByConstituency(constiId);
				if(mandalList != null && mandalList.size() > 0)
				{
				for(DataVO list : mandalList)
				{
					mandalCombo.getItems().addAll(list.getName()+" Mandal");
				}
				}
				
				localBodiesList = cardPrintingDAO.getLocalbodysInAConstituency(constiId);
				if(localBodiesList != null && localBodiesList.size() > 0)
				{
				
				for(DataVO list : localBodiesList)
				{
					mandalCombo.getItems().addAll(list.getName()+" Muncipality");
				}
				}
				new SelectKeyComboBoxListener(mandalCombo);	
			}
		}
		
		
		@SuppressWarnings("unused")
		@FXML
		private void getPanchayatHandleAction()
		{
			panchayatCombo.getItems().clear();
			if(mandalCombo.getItems().size() > 0 && mandalCombo.getItems() != null){
				
				String constiName = constCombo.getSelectionModel().getSelectedItem().toString();
				Long constiId = getMatchedName(constiList, constiName);
				String name = mandalCombo.getSelectionModel().getSelectedItem().toString();		
				name= name.substring(0, name.length() - 6);
				Long id = getMatchedName(mandalList, name.trim());
				if(id != null){				
					panchayatList = cardPrintingDAO.getAllPanchayatsInAMandal(id,constiId);					
					if(panchayatList != null && panchayatList.size() > 0){
						panchayatCombo.setPromptText("Panchayat Name");
						panchayatCombo.getItems().clear();
						for(DataVO list : panchayatList)
						{
							panchayatCombo.getItems().addAll(list.getName());
						}
					}
				}
				else{
					name= name.substring(0, name.length() - 6);
					id = getMatchedName(localBodiesList, name);
					
					boothsList = cardPrintingDAO.getAllBoothsInLocalBodies(id);
					
					if(boothsList != null && boothsList.size() > 0){
						panchayatCombo.setPromptText("Booth No");
						for(DataVO list : boothsList)
						{
							panchayatCombo.getItems().addAll("Part-"+list.getName());
						}
					}
				}
				new SelectKeyComboBoxListener(panchayatCombo);	
			}
				
		}
		
		public static class CheckBoxTableCell<S, T> extends TableCell<S, T> {
			
			private final CheckBox checkBox;			
			private ObservableValue<T> ov;
			
			public CheckBoxTableCell() {		
				this.checkBox = new CheckBox();
				this.checkBox.setAlignment(Pos.CENTER);
				setAlignment(Pos.CENTER);
				//setGraphic(checkBox);
			}
			
			@Override 
			public void updateItem(T item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setText(null);
					setGraphic(null);
				}else {
					
					setGraphic(checkBox);
					
					ov = getTableColumn().getCellObservableValue(getIndex());
					
					if(ov instanceof BooleanProperty) {
						checkBox.selectedProperty().bindBidirectional((BooleanProperty) ov);
				
						if( !tableView.getItems().get(getIndex()).getCardNumber().isEmpty())						   
						  {						
							   tableView.getItems().get(getIndex()).nfcNumberProperty().set(tableView.getItems().get(getIndex()).getCardNumber());
						  }
						 if( checkBox.isFocused())						   
						  {
							 if(checkBox.isSelected()){
									tableView.getItems().get(getIndex()).nfcNumberProperty().set("");
									CardReader cardTest = new CardReader();
						          	String cardNo = cardTest.getUniCode();					         
						          	tableView.getItems().get(getIndex()).nfcNumberProperty().set(cardNo);
								}
								else{
									tableView.getItems().get(getIndex()).nfcNumberProperty().set("");
								}
						  }				
						tableView.setItems(tableView.getItems());
					}					
				}
			}
		}
		
		
		
	
		public Long getMatchedName(List<DataVO> resultList,String name){
			try{
				if(resultList != null && resultList.size() > 0){
					for(DataVO vo : resultList){
						if(vo.getName().toString().equalsIgnoreCase(name.toString()))
						return vo.getId();
					}
				}
			}catch (Exception e) {
			
			}
			return null;
		
		}

}
