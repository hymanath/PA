package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.itgrids.partyanalyst.dao.IAlertAssignedDAO;
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
	private DateUtilService dateutilService;
	private IAlertCommentDAO alertCommentDAO;
	private IAlertTrackingDAO alertTrackingDAO;
	private IZohoTdpCadreContactDAO zohoTdpCadreContactDAO;
	private IAlertDocumentDAO alertDocumentDAO;
	
	
	
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
	public DateUtilService getDateutilService() {
		return dateutilService;
	}
	public void setDateutilService(DateUtilService dateutilService) {
		this.dateutilService = dateutilService;
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
	
	//contact-id of candidate
	public String sendAssignedCandidateCantactId(Long cadreId,Long alertId){
		String status=null;
		try {
				//getCandidate contactId	
			 	String contactId = getContactIdForCadre(cadreId);
		       	 if(contactId != null && !contactId.trim().isEmpty()){
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
			   		     builder.accept("application/json");
			   		     builder.type("application/json");
			   		     
			   		     JSONObject jobj = new JSONObject();
		       			 jobj.put("contactId", contactId);
		       			 
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
			       	 }
			       	status="success";
			     
			
		} catch (Exception e) {
			LOG.error("exception raised while getting the contact-id for the cadreId", e);
			status="failure";
		}
		return status;
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
	

	public JSONObject saveZohoAlertComment(JSONObject playLoadObj){
		JSONObject obj = new JSONObject();
		try {
			if(playLoadObj.has("content") && playLoadObj.has("ticketId")){
				String comment = playLoadObj.getString("content");
				String alertTicketId = playLoadObj.getString("ticketId");
				List<Long> alertIds = alertDAO.getAlertId(alertTicketId);
				if(alertIds != null && alertIds.size() > 0){
					AlertComment alertComment = new AlertComment();
					alertComment.setAlertId(alertIds.get(0));
					alertComment.setComments(comment);
					alertComment.setInsertedBy(1l);
					alertComment.setInsertedTime(dateutilService.getCurrentDateAndTime());
					alertComment.setIsDeleted("N");
					alertComment = alertCommentDAO.save(alertComment);
					
					AlertTracking alertTracking = new AlertTracking();
					alertTracking.setAlertId(alertIds.get(0));
					alertTracking.setAlertCommentId(alertComment.getAlertCommentId());
					alertTracking.setAlertTrackingActionId(2l);
					alertTracking.setInsertedBy(1l);
					alertTracking.setInsertedTime(dateutilService.getCurrentDateAndTime());
					alertTrackingDAO.save(alertTracking);
				}
			}
			obj.put("message", "success");
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
	
	public JSONObject saveAlertAssign(JSONObject playLoadObj){
		JSONObject obj = new JSONObject();
		try {
			if(playLoadObj.has("ticketId")){
				List<Long> alertIds = alertDAO.getAlertId(playLoadObj.getString("ticketId"));
				if(alertIds != null && alertIds.size() > 0){
					String contactId = playLoadObj.getString("contactId");
					if(contactId != null && !contactId.trim().isEmpty()){
						List<Long> cadreIds = zohoTdpCadreContactDAO.getTdpCadreId(contactId);
						if(cadreIds != null && cadreIds.size() > 0){
							AlertAssigned alertAssigned = new AlertAssigned();
							alertAssigned.setAlertId(alertIds.get(0));
							alertAssigned.setTdpCadreId(cadreIds.get(0));
							alertAssigned.setInsertedTime(dateutilService.getCurrentDateAndTime());
							alertAssigned.setUpdatedTime(dateutilService.getCurrentDateAndTime());
							alertAssigned.setCreatedBy(1l);
							alertAssigned.setIsDeleted("N");
							alertAssigned.setSmsStatus("N");
							alertAssignedDAO.save(alertAssigned);
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
			if(playLoadObj.has("ticketId")){
				List<Long> alertIds = alertDAO.getAlertId(playLoadObj.getString("ticketId"));
				if(alertIds != null && alertIds.size() > 0){
					List<Long> statusIds = alertStatusDAO.getStatusId(playLoadObj.getString("status").trim());
					if(statusIds != null && statusIds.size() > 0){
						Alert alert = alertDAO.get(alertIds.get(0));
						alert.setAlertStatusId(statusIds.get(0));
						alert.setUpdatedBy(1l);
						alert.setUpdatedTime(dateutilService.getCurrentDateAndTime());
						alert = alertDAO.save(alert);
						
						AlertTracking alertTracking = new AlertTracking();
						alertTracking.setAlertId(alert.getAlertId());
						alertTracking.setAlertStatusId(alert.getAlertStatusId());
						alertTracking.setAlertTrackingActionId(1l);
						alertTracking.setAlertSourceId(alert.getAlertSourceId());
						alertTracking.setInsertedBy(1l);
						alertTracking.setInsertedTime(dateutilService.getCurrentDateAndTime());
						alertTracking = alertTrackingDAO.save(alertTracking);
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
   				alertDocument.setInsertedTime(dateutilService.getCurrentDateAndTime());
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
