package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.JsonObject;
import com.itgrids.partyanalyst.dao.IAlertCommentDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertDocumentDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.service.IAlertCreationAPIService;
import com.itgrids.partyanalyst.service.IAlertUpdationAPIService;
import com.itgrids.partyanalyst.utils.IConstants;
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
	private IAlertDocumentDAO alertDocumentDAO;
	private IAlertCreationAPIService alertCreationAPIService;
	private IAlertCommentDAO alertCommentDAO;
	
	
	public IAlertCommentDAO getAlertCommentDAO() {
		return alertCommentDAO;
	}
	public void setAlertCommentDAO(IAlertCommentDAO alertCommentDAO) {
		this.alertCommentDAO = alertCommentDAO;
	}
	public IAlertCreationAPIService getAlertCreationAPIService() {
		return alertCreationAPIService;
	}
	public void setAlertCreationAPIService(
			IAlertCreationAPIService alertCreationAPIService) {
		this.alertCreationAPIService = alertCreationAPIService;
	}
	public IAlertDocumentDAO getAlertDocumentDAO() {
		return alertDocumentDAO;
	}
	public void setAlertDocumentDAO(IAlertDocumentDAO alertDocumentDAO) {
		this.alertDocumentDAO = alertDocumentDAO;
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
	
	//sending attachments of alert
	public String sendAttachements(Long alertId){
		try {
			if(alertId != null && alertId > 0l){
				//0.documentId,1.documentPath
				List<Object[]> uploadListObj = alertDocumentDAO.getDocumentsForAlert(alertId);
				if(uploadListObj != null && uploadListObj.size() > 0){
					String alertTicketId = alertDAO.getAlertTicketId(alertId);
					sendAttachementsToZOHO(alertTicketId,uploadListObj);
				}
			}
			return "success";
		} catch (Exception e) {
			LOG.error("Exception raised while sending the attachmentId", e);
			return "failure";
		}
	}
	
	public String sendAttachementsToZOHO(String alertTicketId,List<Object[]> objArr){
		try {
			if(objArr != null && objArr.size() > 0){
				for (Object[] objects : objArr) {
					String fileURL = "http://mytdp.com/Reports/"+objects[1].toString();
					alertCreationAPIService.uploadMultiPartFile("https://desk.zoho.com/api/v1/tickets/"+alertTicketId+"/attachments",new File(fileURL));
				}
				return "success";
			}
		} catch (Exception e) {
			LOG.error("Exception raised while send the alert attachments to ZOHO", e);
			return "failure";
		}
		return null;
	}
	
	//sending alert status
	public String updateAlertStatus(String status,String alertTicketId){
		try {
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
		    		 return "success";
		    	 else
		    		 return "failure";
		     }
		} catch (Exception e) {
			LOG.error("Exception raised while updating alert status", e);
			return "failure";
		}
	}
	
	public String updateAlertComment(Long alertId){
		String status = null;
		try {
			String alertTicketId = alertDAO.getAlertTicketId(alertId);
			if(alertTicketId != null && !alertTicketId.trim().isEmpty()){
				//get the comments of alert
				List<Object[]> objList = alertCommentDAO.getCommentsOfAlert(alertId);
				if(objList != null && objList.size() > 0){
					 for (Object[] objects : objList) {
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
					     jobj.put("content", objects[1].toString());
					     
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
			}
		} catch (Exception e) {
			LOG.error("exception raised while sending comments", e);
			status = "failure";
		}
		return status;
	}
}
