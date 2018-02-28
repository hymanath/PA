package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAlertAssignedDAO;
import com.itgrids.partyanalyst.dao.IAlertCommentAssigneeDAO;
import com.itgrids.partyanalyst.dao.IAlertCommentDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertDocumentDAO;
import com.itgrids.partyanalyst.dao.IAlertStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertTrackingDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.IZohoTdpCadreContactDAO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertAssigned;
import com.itgrids.partyanalyst.model.AlertComment;
import com.itgrids.partyanalyst.model.AlertCommentAssignee;
import com.itgrids.partyanalyst.model.AlertDocument;
import com.itgrids.partyanalyst.model.AlertTracking;
import com.itgrids.partyanalyst.service.IAlertCreationAPIService;
import com.itgrids.partyanalyst.service.IAlertUpdationAPIService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class AlertUpdationAPIService implements IAlertUpdationAPIService{
	private static final Logger LOG = Logger.getLogger(AlertUpdationAPIService.class);
	private ITdpCadreDAO tdpCadreDAO;
	private IAlertDAO alertDAO;
	private IAlertCreationAPIService alertCreationAPIService;
	private IAlertAssignedDAO alertAssignedDAO;
	private IAlertStatusDAO alertStatusDAO;
	private DateUtilService dateUtilService;
	private IAlertCommentDAO alertCommentDAO;
	private IAlertTrackingDAO alertTrackingDAO;
	private IZohoTdpCadreContactDAO zohoTdpCadreContactDAO;
	private IAlertDocumentDAO alertDocumentDAO;
	private TransactionTemplate transactionTemplate = null;
	private IAlertCommentAssigneeDAO alertCommentAssigneeDAO = null;;
	
	
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public void setAlertCommentAssigneeDAO(
			IAlertCommentAssigneeDAO alertCommentAssigneeDAO) {
		this.alertCommentAssigneeDAO = alertCommentAssigneeDAO;
	}
	public IAlertDocumentDAO getAlertDocumentDAO() {
		return alertDocumentDAO;
	}
	public void setAlertDocumentDAO(IAlertDocumentDAO alertDocumentDAO) {
		this.alertDocumentDAO = alertDocumentDAO;
	}
	public IZohoTdpCadreContactDAO getZohoTdpCadreContactDAO() {
		return zohoTdpCadreContactDAO;
	}
	public void setZohoTdpCadreContactDAO(
			IZohoTdpCadreContactDAO zohoTdpCadreContactDAO) {
		this.zohoTdpCadreContactDAO = zohoTdpCadreContactDAO;
	}
	public IAlertTrackingDAO getAlertTrackingDAO() {
		return alertTrackingDAO;
	}
	public void setAlertTrackingDAO(IAlertTrackingDAO alertTrackingDAO) {
		this.alertTrackingDAO = alertTrackingDAO;
	}
	public IAlertCommentDAO getAlertCommentDAO() {
		return alertCommentDAO;
	}
	public void setAlertCommentDAO(IAlertCommentDAO alertCommentDAO) {
		this.alertCommentDAO = alertCommentDAO;
	}
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public IAlertStatusDAO getAlertStatusDAO() {
		return alertStatusDAO;
	}
	public void setAlertStatusDAO(IAlertStatusDAO alertStatusDAO) {
		this.alertStatusDAO = alertStatusDAO;
	}
	public IAlertAssignedDAO getAlertAssignedDAO() {
		return alertAssignedDAO;
	}
	public void setAlertAssignedDAO(IAlertAssignedDAO alertAssignedDAO) {
		this.alertAssignedDAO = alertAssignedDAO;
	}
	public IAlertCreationAPIService getAlertCreationAPIService() {
		return alertCreationAPIService;
	}
	public void setAlertCreationAPIService(
			IAlertCreationAPIService alertCreationAPIService) {
		this.alertCreationAPIService = alertCreationAPIService;
	}
	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}
	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	public IAlertDAO getAlertDAO() {
		return alertDAO;
	}
	public void setAlertDAO(IAlertDAO alertDAO) {
		this.alertDAO = alertDAO;
	}

	public 	String  getMembershipNoForCadreIds(List<Long> cadreIds,Long alertId){
		List<Object[]> objList = tdpCadreDAO.getCadreNameByTdpCadreIds(cadreIds);
		if(objList != null && objList.size() > 0){
			//return sendAssignedCandidateCantactId(objList.get(0)[3].toString(),alertId);
		}
		return null;
	}
	
	public Long getZohoStatusDetailsOfAlert(Long alertId){
		Long presentStatus = null; 
		try {
			
			String alertTicketId = alertDAO.getAlertTicketId(alertId);
      		 if(alertTicketId != null && !alertTicketId.trim().isEmpty()){
      			 
      			 ClientConfig clientConfig = new DefaultClientConfig();
     		     clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
     		     Client client = Client.create(clientConfig);
     		     
     			 WebResource webResource = client.resource("https://desk.zoho.com/api/v1/tickets/"+alertTicketId);
     			 
     			 WebResource.Builder builder = webResource.getRequestBuilder();			         
     		     builder.header("Authorization",IConstants.ZOHO_ADMIN_AUTHORIZATION);
     		     builder.header("orgId",IConstants.ZOHO_ADMIN_ORGID);
     		     builder.accept("application/json");
     		     builder.type("application/json");
     		     
     		    ClientResponse response = builder.get(ClientResponse.class);
     		    
     		   if (response.getStatus() != 200) {
		        	 throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		         } else {
		        	 String output = response.getEntity(String.class);
		        	 JSONObject jobjIn= new JSONObject(output);
		        	 
		        	 if(jobjIn.has("status")){
		        		 presentStatus = alertStatusDAO.getIdOfName(jobjIn.getString("status"));
		        	 }
		        	 
		         }
      		 }
		} catch (Exception e) {
			LOG.error("exception Occured in getStatusDetailsOfAlert in AlertUpdationAPIService Class ", e);
		}
		return presentStatus;
	}
	
	//contact-id of candidate
	public String sendAssignedCandidateCantactId(List<Long> cadreIds,Long alertId){
		String status=null;
		try {
			
			JSONObject jobj = new JSONObject();
  		    JSONObject customObj = new JSONObject();
  		    String contactId="";
			
  		   // Checking the Present Status Of 
  		   Long zohoPresentStatusId = getZohoStatusDetailsOfAlert(alertId);
  		   
  		   if(zohoPresentStatusId !=null && zohoPresentStatusId.longValue() == 1L ){
  				jobj.put("status", "Notified");
  		   }
  		   
	   		//get OverAll Assignee Cadre To Update at Zoho end(If already there its replace by Latest assignees list) 
	   		List<Long> latestAllAlertTdpCadreIds = alertAssignedDAO.getAssignedTdpCadreIdsByAlertId(alertId);

	   			//getCandidate contactId	
		 		contactId = callForZohoContact(latestAllAlertTdpCadreIds);
		 	
	       	// if(contactId != null && !contactId.trim().isEmpty()){
	       		 String alertTicketId = alertDAO.getAlertTicketId(alertId);
	       		 if(alertTicketId != null && !alertTicketId.trim().isEmpty()){
	       			 //send candidate contact Id to ZOHO
	       			 ClientConfig clientConfig = new DefaultClientConfig();
		   		     clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		   		     Client client = Client.create(clientConfig);
		   			 WebResource webResource = client.resource("https://desk.zoho.com/api/v1/tickets/"+alertTicketId);
		   			 
		   			 WebResource.Builder builder = webResource.getRequestBuilder();			         
		   		     builder.header("Authorization",IConstants.ZOHO_ADMIN_AUTHORIZATION);
		   		     builder.header("orgId",IConstants.ZOHO_ADMIN_ORGID);
		   		     builder.header("sourceId",IConstants.ZOHO_SOURCEID);
		   		     builder.accept("application/json");
		   		     builder.type("application/json");
		   		     
		   		     
	       			 jobj.put("contactId", IConstants.ZOHO_ADMIN_CONTACTID);
	       			 
	       			 customObj.put("assignees", contactId);
	       			 jobj.put("customFields", customObj);
	       			 
		   		     ClientResponse response = builder.put(ClientResponse.class,jobj);
		   		     
		       			 if (response.getStatus() != 200) {
				        	 throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				         } else {
				        	 String output = response.getEntity(String.class);
					    	 JSONObject jobjIn= new JSONObject(output);
				        	 if(jobjIn.has("ticketNumber"))
				        		 status="success";
				        	 else
				        		 status="failure";
				         }
		       		 }
		       	 //}
	       	 
		       	status="success";
			     
		} catch (Exception e) {
			LOG.error("exception raised while getting the contact-id for the cadreId", e);
			status="failure";
		}
		return status;
	}
	
	public String callForZohoContact(List<Long> cadreIds){
		String contact="";
		try {
			if(cadreIds !=null && cadreIds.size()>0){
				for (Long cadreId : cadreIds) {
					String contactId = getContactIdForCadre(cadreId);
					if(contactId !=null && !contactId.trim().isEmpty()){
						contact = contact == ""?contactId:contact+";"+contactId;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Excption raised in callForZohoContact method for AlertUpdationAPIService Class ", e);
		}
		return contact;
	}
	
	public String getContactIdForCadre(Long cadreId){
		try {
			if(cadreId != null && cadreId > 0l){
				ClientConfig clientConfig = new DefaultClientConfig();
			     clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			     Client client = Client.create(clientConfig);
			     WebResource webResource = client.resource("https://desk.zoho.com/api/v1/search?searchStr="+cadreId+"&module=contacts");				 
			     WebResource.Builder builder = webResource.getRequestBuilder();			         
			     builder.accept("application/json").type("application/json").header("orgId", IConstants.ZOHO_ADMIN_ORGID)
			     			.header("Authorization","Zoho-authtoken "+IConstants.ZOHO_ADMIN_AUTHORIZATION);

			     ClientResponse response = builder.get(ClientResponse.class);
			         
			     if (response.getStatus() != 200) {
			    	 throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			     } else {
			    	 String output = response.getEntity(String.class);
			    	 JSONObject obj= new JSONObject(output);
			    	 if(obj !=null){
			    		 JSONArray finalArray = obj.getJSONArray("data");
			    		 if(finalArray!=null && finalArray.length()>0){
			 	    		JSONObject jobj = (JSONObject) finalArray.get(0);
				 	    	 return  jobj.get("id").toString();
				 	    }
			    	 }
			     }
			}
		} catch (Exception e) {
			LOG.error("excption raised while getting contact id for cadre", e);
		}
		return null;
	}
	
	public String sendAlertUpdationDetails(Long alertId,List<String> fileNames,String comment){
		try {
			if(alertId != null && alertId > 0l){
				String alertTicketId = alertDAO.getAlertTicketId(alertId);
				if(fileNames != null && fileNames.size() > 0){
					sendAttachements(alertTicketId,fileNames);
				}
				if(comment != null && !comment.trim().isEmpty()){
					updateAlertComment(alertTicketId,comment);
				}
				updateAlertStatus(alertId);
			}
			
			return "success";
		} catch (Exception e) {
			return "failure";
		}
	}
	
	//sending attachments of alert
	public String sendAttachements(String alertTicketId,List<String> fileNames){
		try {
			if(fileNames != null && fileNames.size() > 0){
				for (String string : fileNames) {
					sendAttachementsToZOHO(alertTicketId,string);
				}
			}
			return "success";
		} catch (Exception e) {
			LOG.error("Exception raised while sending the attachmentId", e);
			return "failure";
		}
	}
	
	public String sendAttachementsToZOHO(String alertTicketId,String fileName){
		String status = "";
		try {
			String fileURL = IConstants.STATIC_CONTENT_FOLDER_URL+"Reports/"+fileName;
			String resultString  = alertCreationAPIService.uploadMultiPartFile("https://desk.zoho.com/api/v1/tickets/"+alertTicketId+"/attachments",new File(fileURL));
			if(resultString != null && !resultString.trim().isEmpty()){
				if(resultString.contains("id"))
					status = "success";
				else
					status = "failure";
				}
		} catch (Exception e) {
			LOG.error("Exception raised while send the alert attachments to ZOHO", e);
			status = "failure";
		}
		return status;
	}
	
	//sending alert status
	public String updateAlertStatus(Long alertId){
		String result="";
		try {
			String alertTicketId = alertDAO.getAlertTicketId(alertId);
			if(alertTicketId != null && !alertTicketId.trim().isEmpty()){
				Object[] objArr = alertDAO.getAlertStatus(alertId);
				if(objArr != null && objArr.length > 0){
					String status = objArr[1].toString();
					
					ClientConfig clientConfig = new DefaultClientConfig();
				     clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
				     Client client = Client.create(clientConfig);
					 WebResource webResource = client.resource("https://desk.zoho.com/api/v1/tickets/"+alertTicketId);
				         
				     WebResource.Builder builder = webResource.getRequestBuilder();			         
				     builder.header("Authorization",IConstants.ZOHO_ADMIN_AUTHORIZATION);
				     builder.header("orgId",IConstants.ZOHO_ADMIN_ORGID);
				     builder.header("sourceId",IConstants.ZOHO_SOURCEID);
				     builder.accept("application/json");
				     builder.type("application/json");
				     
				     JSONObject jobj = new JSONObject();
				     jobj.put("status", status);

				     ClientResponse response = builder.put(ClientResponse.class,jobj);
				         
				     if (response.getStatus() != 200) {
				    	 throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				     } else {
				    	 String output = response.getEntity(String.class);
				    	 JSONObject obj= new JSONObject(output);
				    	 if(obj.has("ticketNumber"))
				    		 result = "success";
				    	 else
				    		 result = "failure";
				     }
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised while updating alert status", e);
			result = "failure";
		}
		return result;
	}
	
	public String updateAlertComment(String alertTicketId,String comment){
		String status = null;
		try {
			if(alertTicketId != null && !alertTicketId.trim().isEmpty()){
				if(comment != null && !comment.trim().isEmpty()){
					ClientConfig clientConfig = new DefaultClientConfig();
					clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
					Client client = Client.create(clientConfig);
					WebResource webResource = client.resource("https://desk.zoho.com/api/v1/tickets/"+alertTicketId+"/comments");
					        
					WebResource.Builder builder = webResource.getRequestBuilder();			         
					builder.header("Authorization","Zoho-authtoken "+IConstants.ZOHO_ADMIN_AUTHORIZATION);
					builder.header("orgId",IConstants.ZOHO_ADMIN_ORGID);
					builder.header("sourceId",IConstants.ZOHO_SOURCEID);
					builder.accept("application/json");
					builder.type("application/json");
					     
				    JSONObject jobj = new JSONObject();
					jobj.put("isPublic", "true");
					jobj.put("content", comment);
					     
					ClientResponse response = builder.post(ClientResponse.class,jobj);
					         
					if (response.getStatus() != 200) {
					   	 throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
					} else {
					   	 String output = response.getEntity(String.class);
					   	 JSONObject obj= new JSONObject(output);
					   	 if(obj.has("id")) status = "success";
					   	 else status = "failure";
					}
				}
			}
		} catch (Exception e) {
			LOG.error("exception raised while sending comments", e);
			status = "failure";
		}
		return status;
	}
	

	public JSONObject saveZohoAlertComment(final JSONObject playLoadObj){
		final JSONObject obj = new JSONObject();
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status){
					
					if(playLoadObj.has("content") && playLoadObj.has("ticketId")){
						
						Long tdpCadreId =null;
						Long tdpUserId =null;
						JSONObject contactJson = null; 
						try {
							if(playLoadObj.has("commenterId") && !playLoadObj.getString("commenterId").trim().isEmpty()){
								contactJson = getTdpCadreIdOfZohoUser(playLoadObj.getString("commenterId").trim() !=null ? playLoadObj.getString("commenterId").trim():null);
								if(contactJson !=null){
									tdpCadreId = contactJson.getLong("tdpCadreId");
									tdpUserId = contactJson.getLong("tdpUserId");
								}
							}
							obj.put("message", "success");
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						String comment=null;
						String alertTicketId=null;
						Long statusId = null;
						try {
							
							if(playLoadObj.getString("content").contains("\n\n")){
								comment = playLoadObj.getString("content").split("\n\n")[1];
								
								if(playLoadObj.getString("content").split("\n\n")[0] !=null && !playLoadObj.getString("content").split("\n\n")[0].trim().isEmpty()){
									
									String latestStatus  = playLoadObj.getString("content").split("\n\n")[0].split(":")[1] !=null ? playLoadObj.getString("content").split("\n\n")[0].split(":")[1].trim():null;
									
									if(latestStatus !=null && !latestStatus.isEmpty()){
										statusId = alertStatusDAO.getIdOfName(latestStatus.trim());
									}
									
								}
							}else{
								comment = playLoadObj.getString("content");
							}
							alertTicketId = playLoadObj.getString("ticketId");
							
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						List<Long> alertIds = alertDAO.getAlertId(alertTicketId);
						if(alertIds != null && alertIds.size() > 0){
							
							AlertComment alertComment = new AlertComment();
							AlertTracking alertTracking = new AlertTracking();
							
							alertComment.setAlertId(alertIds.get(0));
							alertComment.setComments(comment);
							
							if(tdpUserId !=null){
								alertComment.setInsertedBy(tdpUserId);
								alertTracking.setInsertedBy(tdpUserId);
							}else{
								alertComment.setInsertedBy(1l);
								alertTracking.setInsertedBy(1l);
							}
								
							alertComment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							alertComment.setIsDeleted("N");
							alertComment = alertCommentDAO.save(alertComment);
							
							alertTracking.setAlertId(alertIds.get(0));
							alertTracking.setAlertCommentId(alertComment.getAlertCommentId());
														
							if(statusId !=null){
								alertTracking.setAlertTrackingActionId(1l);
								alertTracking.setAlertStatusId(statusId);
							}else{
								alertTracking.setAlertTrackingActionId(2l);
							}
								
							
							alertTracking.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							alertTrackingDAO.save(alertTracking);
							
							if(tdpCadreId !=null)
								saveAlertCommentAssignee(alertComment.getAlertCommentId(),tdpCadreId);
														
						}
					}
					
			  }				
			});
			
		} catch (Exception e) {
			try {
				obj.put("message", "failure");
				LOG.error("Exception occured at saveZohoAlertComment()", e);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			
		}
		return obj;
	}
	
	public String saveAlertCommentAssignee(Long alertCommentId,Long tdpCadreId){
		try {
			
			AlertCommentAssignee alertCommentAssignee = new AlertCommentAssignee();
			alertCommentAssignee.setAlertCommentId(alertCommentId);
			alertCommentAssignee.setAssignTdpCadreId(tdpCadreId);
			alertCommentAssigneeDAO.save(alertCommentAssignee);
			
			return "success";
			
		} catch (Exception e) {
			LOG.error("Exception occured at saveAlertCommentAssignee() in AlertUpdationApiService Class", e);
		}
		return null;
	}
	public JSONObject getTdpCadreIdOfZohoUser(String zohoUserId){
		JSONObject finalJson = null;
		try {
			List<Object[]> cadreObj =  zohoTdpCadreContactDAO.getZohoContactDetails(zohoUserId);
			if(cadreObj !=null && cadreObj.size()>0){
				finalJson= new JSONObject();
				Object[] cadre= cadreObj.get(0);
						
				finalJson.put("tdpCadreId", (Long)cadre[0]);
				finalJson.put("tdpUserId", cadre[1] !=null ? (Long)cadre[1]:null);
				
				return finalJson;
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getTdpCadreIdOfZohoUser() in AlertUpdationService Class ", e);
		}
		return null;
	}
	
	public JSONObject saveAlertAssign(JSONObject playLoadObj){
		JSONObject obj = new JSONObject();
		try {
			
			JSONObject contactJson = new JSONObject();
			
			if(playLoadObj.has("id")){
				List<Long> alertIds = alertDAO.getAlertId(playLoadObj.getString("id"));
				if(alertIds != null && alertIds.size() > 0){
					
					String[]  assigneesArr = null;
					if(playLoadObj.has("customFields") && playLoadObj.getJSONObject("customFields").has("assignees")){						
						String assignees = playLoadObj.getJSONObject("customFields").getString("assignees");
						
						if(assignees !=null && !assignees.trim().isEmpty()){
							assigneesArr = assignees.split(";");
						}
					}
					
					//String contactId = playLoadObj.getString("contactId");
					
					List<String> contactsList = null;
					List<Long> existCadreIds = new ArrayList<Long>(0);
					if(assigneesArr !=null && assigneesArr.length>0){
						
						contactsList = Arrays.asList(assigneesArr);
						
						List<Long> cadreIds = zohoTdpCadreContactDAO.getTdpCadresIdOfContacts(contactsList);
						
						if(cadreIds !=null && cadreIds.size()>0){
							existCadreIds = alertAssignedDAO.checkCadreExistsForAlert(cadreIds,alertIds.get(0));
							for(Long cadreId : cadreIds)
							 {
								 if(!existCadreIds.contains(cadreId))
								 {
									 AlertAssigned alertAssigned = new AlertAssigned();
									 Long tdpUserId=null;
										
									 try {
											if(playLoadObj.has("modifiedBy") && !playLoadObj.getString("modifiedBy").trim().isEmpty()){
												contactJson = getTdpCadreIdOfZohoUser(playLoadObj.getString("modifiedBy").trim() !=null ? playLoadObj.getString("modifiedBy").trim():null);
												if(contactJson !=null){
													tdpUserId = contactJson.getLong("tdpUserId");
												}
											}
										} catch (Exception e) {
											e.printStackTrace();
										}
									 	alertAssigned.setAlertId(alertIds.get(0));
										alertAssigned.setTdpCadreId(cadreId);
										if(tdpUserId !=null)
											alertAssigned.setCreatedBy(tdpUserId);
										else
											alertAssigned.setCreatedBy(1l);
										
										alertAssigned.setInsertedTime(dateUtilService.getCurrentDateAndTime());
										alertAssigned.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
										alertAssigned.setIsDeleted("N");
										alertAssigned.setSmsStatus("N");
										alertAssigned = alertAssignedDAO.save(alertAssigned);
									 
								 }
							 }
							alertAssignedDAO.flushAndclearSession();
						}
					}
					
					
				}
				
			}
			
			
			obj.put("message", "success");
		} catch (Exception e) {
			try {
				obj.put("message", "failure");
				LOG.error("Exception occured at saveAlertAssign()", e);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return obj;
	}
	public JSONObject saveAlertStatus(JSONObject playLoadObj){
		JSONObject obj = new JSONObject();
		try {
			if(playLoadObj.has("id")){
				List<Long> alertIds = alertDAO.getAlertId(playLoadObj.getString("id"));
				if(alertIds != null && alertIds.size() > 0){
					List<Long> statusIds = alertStatusDAO.getStatusId(playLoadObj.getString("status").trim());
					if(statusIds != null && statusIds.size() > 0){
						Alert alert = alertDAO.get(alertIds.get(0));
						
						JSONObject contactJson = new JSONObject();
						Long tdpUserId = null;
						
						try {
							if(playLoadObj.has("modifiedBy") && !playLoadObj.getString("modifiedBy").trim().isEmpty()){
								contactJson = getTdpCadreIdOfZohoUser(playLoadObj.getString("modifiedBy").trim() !=null ? playLoadObj.getString("modifiedBy").trim():null);
								if(contactJson !=null){
									tdpUserId = contactJson.getLong("tdpUserId");
								}
							}
							obj.put("message", "success");
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						if(tdpUserId !=null){
							alert.setUpdatedBy(tdpUserId);
						}else{
							alert.setUpdatedBy(1l);
						}
						
						alert.setAlertStatusId(statusIds.get(0));
						alert.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						alert = alertDAO.save(alert);
					}
				}
			}
			obj.put("message", "success");
		} catch (Exception e) {
			try {
				obj.put("message", "failure");
				LOG.error("Exception occured at saveAlertStatus()", e);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return obj;
	}
	
	public JSONObject saveAlertDocument(String inpuUrl,Long alertId){
		JSONObject obj = new JSONObject();
		try {
			URL website = new URL(inpuUrl);
			byte[] ba1 = new byte[2048];
				int baLength;
				InputStream is1 = website.openStream();
				String ouptputURl = IConstants.STATIC_CONTENT_FOLDER_PATH+"/Reports/"+IConstants.TOUR_DOCUMENTS;
				Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
				String ext = inpuUrl.split(".")[1];
				FileOutputStream fos1 = new FileOutputStream(ouptputURl+"/"+randomNumber+"."+ext);
				while ((baLength = is1.read(ba1)) != -1) {
   					fos1.write(ba1, 0, baLength);
   				}
   			
   				fos1.flush();
   				fos1.close();
   				is1.close();
   				
   				
   				AlertDocument alertDocument = new AlertDocument();
   				alertDocument.setAlertId(alertId);
   				alertDocument.setDocumentPath(IConstants.TOUR_DOCUMENTS+"/"+randomNumber+"."+ext);
   				alertDocument.setIsDeleted("N");
   				alertDocument.setInsertedBy(1l);
   				alertDocument.setInsertedTime(dateUtilService.getCurrentDateAndTime());
   				alertDocumentDAO.save(alertDocument);
			obj.put("message", "success");
		} catch (Exception e) {
			try {
				obj.put("message", "failure");
				LOG.error("Exception occured at saveAlertDocument()", e);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return obj;
	}
}
