package com.itgrids.partyanalyst.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAlertAssignedDAO;
import com.itgrids.partyanalyst.dao.IAlertCandidateDAO;
import com.itgrids.partyanalyst.dao.IAlertCategoryDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertDocumentDAO;
import com.itgrids.partyanalyst.dao.IAlertImpactScopeDAO;
import com.itgrids.partyanalyst.dao.IAlertSeverityDAO;
import com.itgrids.partyanalyst.dao.IAlertStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertTypeDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyTehsilDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.impl.IAlertSourceDAO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IAlertCreationAPIService;
import com.itgrids.partyanalyst.service.IAlertUpdationAPIService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.MultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

public class AlertCreationAPIService implements IAlertCreationAPIService {

	private static final Logger LOG = Logger.getLogger(AlertCreationAPIService.class);
	
	private IAlertDAO alertDAO;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private ITehsilDAO tehsilDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IConstituencyDAO constituencyDAO;
	private IPanchayatDAO panchayatDAO;
	private TransactionTemplate transactionTemplate = null;
	
	private IAlertSeverityDAO alertSeverityDAO;
	private IAlertTypeDAO alertTypeDAO;
	private IRegionScopesDAO regionScopesDAO;
	private IUserAddressDAO userAddressDAO ;
	private IAlertImpactScopeDAO alertImpactScopeDAO;
	private IConstituencyTehsilDAO constituencyTehsilDAO;
	private IAlertSourceDAO alertSourceDAO;
	private IAlertAssignedDAO alertAssignedDAO;
	private IAlertDocumentDAO alertDocumentDAO;
	private IAlertStatusDAO alertStatusDAO;
	private IAlertCategoryDAO alertCategoryDAO;
	private IAlertUpdationAPIService alertUpdationAPIService;
	private IAlertCandidateDAO alertCandidateDAO;

	public void setAlertUpdationAPIService(IAlertUpdationAPIService alertUpdationAPIService) {
		this.alertUpdationAPIService = alertUpdationAPIService;
	}
	public void setAlertCandidateDAO(IAlertCandidateDAO alertCandidateDAO) {
		this.alertCandidateDAO = alertCandidateDAO;
	}

	public void setAlertCategoryDAO(IAlertCategoryDAO alertCategoryDAO) {
		this.alertCategoryDAO = alertCategoryDAO;
	}

	public void setAlertStatusDAO(IAlertStatusDAO alertStatusDAO) {
		this.alertStatusDAO = alertStatusDAO;
	}

	public void setAlertDocumentDAO(IAlertDocumentDAO alertDocumentDAO) {
		this.alertDocumentDAO = alertDocumentDAO;
	}

	public void setAlertAssignedDAO(IAlertAssignedDAO alertAssignedDAO) {
		this.alertAssignedDAO = alertAssignedDAO;
	}

	public void setAlertImpactScopeDAO(IAlertImpactScopeDAO alertImpactScopeDAO) {
		this.alertImpactScopeDAO = alertImpactScopeDAO;
	}

	public void setAlertSourceDAO(IAlertSourceDAO alertSourceDAO) {
		this.alertSourceDAO = alertSourceDAO;
	}

	public void setConstituencyTehsilDAO(
			IConstituencyTehsilDAO constituencyTehsilDAO) {
		this.constituencyTehsilDAO = constituencyTehsilDAO;
	}

	public void setAlertSeverityDAO(IAlertSeverityDAO alertSeverityDAO) {
		this.alertSeverityDAO = alertSeverityDAO;
	}

	public void setAlertTypeDAO(IAlertTypeDAO alertTypeDAO) {
		this.alertTypeDAO = alertTypeDAO;
	}

	public void setRegionScopesDAO(IRegionScopesDAO regionScopesDAO) {
		this.regionScopesDAO = regionScopesDAO;
	}

	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setAlertDAO(IAlertDAO alertDAO) {
		this.alertDAO = alertDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	
	
	// Creation Of AlertApi At Zoho End
	public void sendApiDetailsOfAlertToZoho(Long alertId,String dummyContactId,String deptId){
		 try {
			 
			 Alert alert=null;
			 if(alertId !=null){
				 alert = alertDAO.get(alertId);
			 }
			 
			 if(alert==null && alert.getAlertTypeId() !=1l)
				 return;
			 
			 JSONObject jsObj = new JSONObject();
			 JSONArray uploadArr = new JSONArray();
			 
			 
			 jsObj.put("subject", alert.getTitle());
			 jsObj.put("departmentId", deptId);
			 jsObj.put("description", alert.getDescription() !=null ? alert.getDescription():"");
			 jsObj.put("priority", alert.getAlertSeverity().getSeverity());
			 jsObj.put("category", alert.getAlertCategory().getCategory());
			 
			 
			 JSONObject customJson = new JSONObject();
			 customJson.put("Alert ID", alert.getAlertId());
			 customJson.put("Location Level",alert.getRegionScopes().getScope());
			 customJson.put("Location", getLocationName(alert.getImpactLevelId(), alert.getImpactLevelValue()).getString("location"));
			 
			 //setLocationFields(customJson,alert.getImpactLevelId(),alert.getUserAddress());
			 
			 customJson.put("Alert Source", alert.getAlertSource().getSource());
			 customJson.put("Impact Scope", alert.getAlertImpactScope().getImpactScope());
			 customJson.put("Alert Type", alert.getAlertType().getAlertType());
			 
			 String imageUrlId =null;
			 String contactId=null;
			 if(alert.getAlertCategoryId() == 2l || alert.getAlertCategoryId() == 3l){
				 if(alert.getAlertCategoryId() == 2l){
					 customJson.put("News Paper", alert.getEdition().getNewsPaper().getNewsPaper());
					 customJson.put("News Paper Edition", alert.getEdition().getEditionAlias());
					 customJson.put("Edition Type", alert.getEditionType().getEditionType());
					 
					 String articleImageId = getZohoUploadDocumentId(IConstants.STATIC_CONTENT_FOLDER_URL+"NewsReaderImages/"+alert.getImageUrl());
					 //String articleImageId = getZohoUploadDocumentId("C:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/NewsReaderImages/"+alert.getImageUrl());
					
					if(articleImageId !=null)
						uploadArr.put(articleImageId);						
					 
				 }else if(alert.getAlertCategoryId() == 3l){
					 customJson.put("TV News Channel", alert.getTvNewsChannel().getChannelName());
					 String timing = getBulletinPointTiming(alert.getAlertCategoryTypeId());
					 if(timing !=null && !timing.trim().isEmpty())
						 customJson.put("Timing",timing);
				 }
				 
				 customJson.put("Category Type Id", alert.getAlertCategoryTypeId());
				 
			 }else if(alert.getAlertCategoryId() == 1l){
				 //0.documentId,1.documentPath
					List<Object[]> uploadListObj = alertDocumentDAO.getDocumentsForAlert(alert.getAlertId());
					if(uploadListObj !=null && uploadListObj.size()>0){
						uploadArr = setUploadValuesToJsonArray(uploadListObj,uploadArr);
					}
					//Directly Assigning Scenario
					List<Long> tdpCadreIdList = alertAssignedDAO.getAlertAssignedCandidateInfo(alert.getAlertId());
					
					if(tdpCadreIdList !=null && tdpCadreIdList.size()>0){
						contactId = alertUpdationAPIService.callForZohoContact(tdpCadreIdList);
					}
			 }
			 
			 
				 
			 if(uploadArr !=null && uploadArr.length()>0){
				 jsObj.put("uploads", uploadArr);
			 }
			
			 jsObj.put("contactId", dummyContactId);
			 
			 if(contactId != null){
				 customJson.put("assignees", contactId);
				 jsObj.put("status", "Notified");
			 }
			
			 // Custom Fields Adding
			 jsObj.put("customFields", customJson);
			
			 // Involved candidates
			setZohoAlertInvolvedCandidateDetails(alert.getAlertId(),customJson);
			 
			sendApiOfZohoAlert(jsObj,alert.getAlertId());
			
		} catch (Exception e) {
			LOG.error("Exception Occured in sendApiDetailsOfAlertToZoho() in AlertCreationAPIService", e);
		}
	 }
	
	public String getBulletinPointTiming(Long bulletinId){
		try {
			
			 WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.COMMUNITY_NEWS_PORTAL_PATH+"webservice/getBulletinPointTime/"+bulletinId);

				WebResource.Builder builder = webResource.getRequestBuilder();
			
				ClientResponse response = builder.get(ClientResponse.class);
				
				if (response.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				} else {
					String output = response.getEntity(String.class);
					if (output != null && !output.isEmpty()) {
						JSONObject returnJson = new JSONObject(output);
						if(returnJson !=null && returnJson.has("Timing")){
							return returnJson.getString("Timing")+" Min";
						}
					}
				}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getBulletinPointTiming() in AlertCreationAPIService", e);
		}
		return null;
	}
	
	public void setZohoAlertInvolvedCandidateDetails(Long alertId,JSONObject customJson){
		try {
			
			if(alertId == null)
				return;
			
			//0.firstName,2.news candidate,3.organization
			List<Object[]> listObj = alertCandidateDAO.getAlertInvolvedCandidateInfo(alertId);
			
			if(listObj !=null && listObj.size()>0){
				String involvedMembers=null;
				for (Object[] objects : listObj) {
					if(objects[0] !=null && !objects[0].toString().trim().isEmpty()){
						involvedMembers = involvedMembers == null ? objects[0].toString().trim():involvedMembers+";"+objects[0].toString().trim();						
					}else if(objects[2] !=null && !objects[2].toString().trim().isEmpty()){
						involvedMembers = involvedMembers == null ? objects[2].toString().trim():involvedMembers+";"+objects[2].toString().trim();	
					}else if(objects[3] !=null && !objects[3].toString().trim().isEmpty()){
						involvedMembers = involvedMembers == null ? objects[3].toString().trim():involvedMembers+";"+objects[3].toString().trim();	
					}
				}
				if(involvedMembers !=null)
					customJson.put("Involved Members", involvedMembers);
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in setZohoAlertInvolvedCandidateDetails() in AlertCreationAPIService", e);
		}
	}
	 
	 public JSONArray setUploadValuesToJsonArray(List<Object[]> uploadListObj,JSONArray uploadArr){
		 try {
			
			 if(uploadListObj !=null && uploadListObj.size()>0){
				 for (Object[] objects : uploadListObj) {	
					 String attachment=getZohoUploadDocumentId(IConstants.STATIC_CONTENT_FOLDER_URL+"Reports/"+objects[1].toString());
					 //String attachment=getZohoUploadDocumentId("D:/static_content/Reports/"+objects[1].toString());
					 if(attachment !=null)
						 uploadArr.put(attachment);					 
				}
			 }
			 
		} catch (Exception e) {
			LOG.error("Exception Occured in setUploadValuesToJsonArray() in AlertCreationAPIService", e);
		}
		 return uploadArr;
	 }
	 
	 public String sendApiOfZohoAlert(JSONObject jsonObj,Long alertId){
		 String result=null;
		 try {
			 
			 WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://desk.zoho.com/api/v1/tickets");

				WebResource.Builder builder = webResource.getRequestBuilder();

				builder.header("Authorization", IConstants.ZOHO_ADMIN_AUTHORIZATION);
				builder.header("orgId", IConstants.ZOHO_ADMIN_ORGID);
				builder.header("sourceId",IConstants.ZOHO_SOURCEID);
				builder.accept("application/json");
				builder.type("application/json");
				
				ClientResponse response = builder.post(ClientResponse.class,jsonObj);
				
				if (response.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				} else {
					String output = response.getEntity(String.class);
					if (output != null && !output.isEmpty()) {
						
						JSONObject returnJson = new JSONObject(output);
						System.out.println(returnJson);
						LOG.error("Alert Created in Zoho  and Return Json Object is : "+returnJson);
						
						String updateticketStatus  = updateZohoAlertDetails(returnJson,alertId);
						return updateticketStatus;
						
					}				
				}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in sendApiOfZohoAlert() in AlertCreationAPIService class ", e);
		}
		 return result;
	 }
	 
	 public String updateZohoAlertDetails(JSONObject returnJson ,Long alertId){
		 String result = null;
		 try {
			 if(returnJson == null)
				 return null;
			 
			 if(returnJson.has("id") && !returnJson.getString("id").isEmpty()){
				 int value = alertDAO.updateZohoAlertDetails(returnJson.getString("id"),alertId);
				 if(value>0){
					 result ="success";
				 }else{
					 result="failure";
				 }
			 }
		} catch (Exception e) {
			LOG.error("Exception Occured in sendApiOfZohoAlert() in AlertCreationAPIService", e);
			 result="failure";
		}
		 return result;
	 }
	 
	 public JSONObject getLocationName(Long levelId,Long levelValue)
	 {		
		 JSONObject json = new JSONObject();
			try{
				
				if(levelId == 2l)
				{
					json.put("location", stateDAO.get(levelValue).getStateName());
				}
				else if(levelId == 3l)
				{
					json.put("location",districtDAO.get(levelValue).getDistrictName());
					
				}
				
				else if(levelId == 4l)
				{			
					json.put("location",constituencyDAO.get(levelValue).getName());
				}else if(levelId == 5l){
					json.put("location",tehsilDAO.get(levelValue).getTehsilName());
				}else if(levelId == 7l){
					json.put("location",localElectionBodyDAO.get(levelValue).getName());	
				}else if(levelId ==  6l)
				{
					json.put("location",panchayatDAO.get(levelValue).getPanchayatName());
				}
				else if(levelId == 8l)
				{
					json.put("location",constituencyDAO.get(levelValue).getName());
				}
				
				//userAddress = userAddressDAO.save(userAddress);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return json;
		}
	 
	 	public JSONObject setLocationFields(JSONObject customJson,Long levelId,UserAddress address)
		{
			try{
				
				if(levelId == 2l)
				{
					customJson.put("State",address.getState().getStateName());				
				}else if(levelId == 3l)
				{
					customJson.put("State",address.getState().getStateName());
					customJson.put("District",address.getDistrict().getDistrictName());				
				}else if(levelId == 4l)
				{
					customJson.put("State",address.getState().getStateName());
					customJson.put("District",address.getDistrict().getDistrictName());
					customJson.put("Constituency",address.getConstituency().getName());
				}else if(levelId == 5l || levelId == 7l)
				{
					customJson.put("State",address.getState().getStateName());
					customJson.put("District",address.getDistrict().getDistrictName());
					customJson.put("Constituency",address.getConstituency().getName());
					if(levelId ==  5l)
						customJson.put("Tehsil",address.getTehsil().getTehsilName());
					else
						customJson.put("Municipality",address.getLocalElectionBody().getName());	
				}else if(levelId == 6l || levelId == 8l)
				{
					customJson.put("State",address.getState().getStateName());
					customJson.put("District",address.getDistrict().getDistrictName());
					customJson.put("Constituency",address.getConstituency().getName());
					if(levelId ==  6l)
					{
						customJson.put("Tehsil",address.getTehsil().getTehsilName());
						customJson.put("Panchayat",address.getPanchayat().getPanchayatName());
					}
					else
					{
						customJson.put("Municipality",address.getLocalElectionBody().getName());	
						customJson.put("Ward",address.getWard().getName());
					}
				}
				
			}
			catch(Exception e){
				LOG.error("Exception Occured in setLocationFields() in AlertCreationAPIService Class ", e);
			}
			return customJson;
		}
	 public String getZohoUploadDocumentId(String imageUrl){
		 String attachmentId = null;
		try {
			
			String jsonStr = uploadMultiPartFile("https://desk.zoho.com/api/v1/uploads",new File(imageUrl));
			if(jsonStr !=null && !jsonStr.isEmpty()){
				JSONObject json = new JSONObject(jsonStr);
				if(json !=null){
					attachmentId = json.getString("id");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return attachmentId;
	 }
	 
	 public String uploadMultiPartFile(String URL,File uploadFile) 
	    {
	    	try{
		        final ClientConfig config = new DefaultClientConfig();
		        final Client client = Client.create(config);
		        final WebResource resource = client.resource(URL);
		        
		        FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file",uploadFile,MediaType.APPLICATION_OCTET_STREAM_TYPE);
		        fileDataBodyPart.setContentDisposition(FormDataContentDisposition.name("file").fileName(uploadFile.getName()).build());
		
		        final MultiPart multiPart = new FormDataMultiPart().bodyPart(fileDataBodyPart);
		        multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
		        
		        ClientResponse response = resource.type("multipart/form-data")
		        		.header("Authorization",IConstants.ZOHO_ADMIN_AUTHORIZATION)
		        		.header("orgId",IConstants.ZOHO_ADMIN_ORGID)
		        		.header("sourceId",IConstants.ZOHO_SOURCEID)
		        		.post(ClientResponse.class,multiPart);
		        String result = getStringFromInputStream(response.getEntityInputStream());
		        System.out.println(result);
		        client.destroy();
		        return result;
	    	}catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	return null;
	    }
	 
	 private String getStringFromInputStream(InputStream is) 
	    {
	    	try{
	        BufferedReader br = null;
	        final StringBuilder sb = new StringBuilder();
	        String line;
	        try {
	            br = new BufferedReader(new InputStreamReader(is));
	            while ((line = br.readLine()) != null) {
	                sb.append(line);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return sb.toString();
	    	}catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	return null;
	    }
	 
	 // Expose Alert Creation Api From Zoho End	 
	 public JSONObject createAlertApi(final JSONObject jsonObject,final String apiType) throws JSONException{
		 JSONObject status=new JSONObject();
		try {
			 status = (JSONObject) transactionTemplate
					.execute(new TransactionCallback() {
						public Object doInTransaction(TransactionStatus status) {
							
							JSONObject result = new JSONObject();
							 DateUtilService date = new DateUtilService();
							 Alert alert = new Alert();
							 try {
								 alert.setTitle(getValueByKey(jsonObject,"title"));
								 alert.setDescription(getValueByKey(jsonObject,"description"));
								 
								 alert.setZohoTicketId(getValueByKey(jsonObject,"zohoTicketId"));
								 
								 alert.setAlertSeverityId(getValueByKey(jsonObject,"severity")!=null && getValueByKey(jsonObject,"severity") != "null" ?
											 alertSeverityDAO.getIdOfName(jsonObject.getString("severity")).get(0):null);
								 alert.setAlertTypeId(getValueByKey(jsonObject,"alertType")!=null && getValueByKey(jsonObject,"alertType")!= "null" ?  
										 alertTypeDAO.getIdOfName(jsonObject.getString("alertType")).get(0):null);
								 alert.setImpactScopeId(getValueByKey(jsonObject,"impactScope")!=null && getValueByKey(jsonObject,"impactScope")!= "null" ?
										 alertImpactScopeDAO.getIdOfName(jsonObject.getString("impactScope")).get(0):null);
								 alert.setImpactLevelId(getValueByKey(jsonObject,"locationLevel")!=null && getValueByKey(jsonObject,"locationLevel") !="null" ?
										 regionScopesDAO.getIdOfName(jsonObject.getString("locationLevel")).get(0):null);
								 alert.setAlertSourceId(getValueByKey(jsonObject,"alertSource")!=null && getValueByKey(jsonObject,"alertSource") != "null" ?
										 alertSourceDAO.getIdOfName(jsonObject.getString("alertSource")).get(0):null);
								 alert.setAlertStatusId(getValueByKey(jsonObject,"status")!=null && getValueByKey(jsonObject,"status") !="null" ?
										 alertStatusDAO.getIdOfName(jsonObject.getString("status")):null);
								 alert.setAlertCategoryId(getValueByKey(jsonObject,"category")!=null && getValueByKey(jsonObject,"category") !="null" ? 
										 alertCategoryDAO.getIdOfName(jsonObject.getString("category")):null);
								 
							} catch (Exception e) {
								LOG.error("Exception Occured in JsonValues Appending in alertCreationApi method in AlertCreationAPIService", e);
							}
							 
							 UserAddress address = setLocationValuesByJson(jsonObject,alert);
							 
							 address=userAddressDAO.save(address);
							 alert.setAddressId(address.getUserAddressId());
							 
							 alert.setCreatedTime(date.getCurrentDateAndTime());
							 alert.setUpdatedTime(date.getCurrentDateAndTime());
							 alert.setIsDeleted("N");
							 
							 alert.setApiType(apiType);
							 
							 alert = alertDAO.save(alert);
							 try {
								 result.put("message", "success");
								 result.put("uniqueKey", alert.getAlertId().toString());
							 } catch (Exception e) {
								e.printStackTrace();
							}
							 return result;
						}
					}
				); 
		} catch (Exception e) {
			status.put("message", "failure");
			status.put("exceptionMsg", "Exception occured.Please Contact Admin");
			LOG.error("Exception Occured in createAlertApi() in AlertCreationAPIService Class ", e);
		}
		return status;
	}
	 
	 public String getValueByKey(JSONObject json,String key) throws JSONException {
			if(json!=null && json.has(key) && !json.getString(key).isEmpty()) {
				return json.getString(key);
			}
			return null;
	}
	 
	public UserAddress setLocationValuesByJson(JSONObject json,Alert alert){
		UserAddress address = new UserAddress();
		try{
			if(alert.getImpactLevelId() !=null){
				if(alert.getImpactLevelId() == 2l )
				{
					address.setState(getValueByKey(json,"state") !=null && getValueByKey(json,"state") != "null" ? 
							 stateDAO.findByStateName(json.getString("state")).get(0):null);
					alert.setImpactLevelValue(address.getState() !=null ? address.getState().getStateId():null);
				}
				else if(alert.getImpactLevelId() == 3l)
				{
					address.setState(getValueByKey(json,"state") !=null && getValueByKey(json,"state") != "null" ? 
							 stateDAO.findByStateName(json.getString("state")).get(0):null);
					address.setDistrict(getValueByKey(json,"district") !=null && getValueByKey(json,"district") !="null" ?
							districtDAO.findByDistrictName(json.getString("district")).get(0):null);
					
					alert.setImpactLevelValue(address.getDistrict() !=null ? address.getDistrict().getDistrictId():null);
					
				}else if(alert.getImpactLevelId() == 4l){
					
					address.setState(getValueByKey(json,"state") !=null && getValueByKey(json,"state") != "null" ? 
							 stateDAO.findByStateName(json.getString("state")).get(0):null);
					address.setDistrict(getValueByKey(json,"district") !=null && getValueByKey(json,"district") !="null" ?
							districtDAO.findByDistrictName(json.getString("district")).get(0):null);
					address.setConstituency(getValueByKey(json,"constituency") !=null && getValueByKey(json,"constituency") != "null" ?
							constituencyDAO.getConstituencyInfo(address.getDistrict() !=null ? address.getDistrict().getDistrictId():null,json.getString("constituency")).get(0):null);
					
					alert.setImpactLevelValue(address.getConstituency() !=null ? address.getConstituency().getConstituencyId():null);
					
				}else if(alert.getImpactLevelId() == 5l || alert.getImpactLevelId() == 7l)
				{
					address.setState(getValueByKey(json,"state") !=null && getValueByKey(json,"state") != "null" ? 
							 stateDAO.findByStateName(json.getString("state")).get(0):null);
					address.setDistrict(getValueByKey(json,"district") !=null && getValueByKey(json,"district") !="null" ?
							districtDAO.findByDistrictName(json.getString("district")).get(0):null);
					address.setConstituency(getValueByKey(json,"constituency") !=null && getValueByKey(json,"constituency") != "null" ?
							constituencyDAO.getConstituencyInfo(address.getDistrict() !=null ? address.getDistrict().getDistrictId():null,json.getString("constituency")).get(0):null);
					
					if(alert.getImpactLevelId() == 5l){
						address.setTehsil(constituencyTehsilDAO.getTehsilInfoOfConstuencyByTehsilName(address.getConstituency() !=null ? address.getConstituency().getConstituencyId():null, json.getString("tehsil")).get(0));
						alert.setImpactLevelValue(address.getTehsil() !=null ? address.getTehsil().getTehsilId():null);
					}else if(alert.getImpactLevelId() == 7l){
						address.setLocalElectionBody(localElectionBodyDAO.getLocalElectionBodyByDistrictId(address.getDistrict() !=null ? address.getDistrict().getDistrictId():null,json.getString("municipality")).get(0));
						alert.setImpactLevelValue(address.getLocalElectionBody() !=null ?address.getLocalElectionBody().getLocalElectionBodyId():null);
					}
					
				}else if(alert.getImpactLevelId() == 6l || alert.getImpactLevelId() == 8l){
					address.setState(getValueByKey(json,"state") !=null && getValueByKey(json,"state") != "null" ? 
							 stateDAO.findByStateName(json.getString("state")).get(0):null);
					address.setDistrict(getValueByKey(json,"district") !=null && getValueByKey(json,"district") !="null" ?
							districtDAO.findByDistrictName(json.getString("district")).get(0):null);
					address.setConstituency(getValueByKey(json,"constituency") !=null && getValueByKey(json,"constituency") != "null" ?
							constituencyDAO.getConstituencyInfo(address.getDistrict() !=null ? address.getDistrict().getDistrictId():null,json.getString("constituency")).get(0):null);
					
					if(alert.getImpactLevelId() == 6l){
						address.setTehsil(constituencyTehsilDAO.getTehsilInfoOfConstuencyByTehsilName(address.getConstituency() !=null ? address.getConstituency().getConstituencyId():null, json.getString("tehsil")).get(0));
						address.setPanchayatId(panchayatDAO.getPanchayatInfoOfTehsilByPanchayatName(address.getTehsil() !=null ? address.getTehsil().getTehsilId():null, json.getString("panchayat")).get(0));
						alert.setImpactLevelValue(address.getPanchayatId());
					}else if(alert.getImpactLevelId() == 8l){
						address.setLocalElectionBody(localElectionBodyDAO.getLocalElectionBodyByDistrictId(address.getDistrict() !=null ? address.getDistrict().getDistrictId():null,json.getString("municipality")).get(0));
						address.setWard(constituencyDAO.getWardInfoOfLocalBody(address.getLocalElectionBody().getLocalElectionBodyId(), json.getString("ward")).get(0));
						alert.setImpactLevelValue(address.getWard().getConstituencyId());
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}	
		
		return address;
	}
	
	public String getContactIdForCadre(Long cadreId){
	    try {
	      if(cadreId != null && cadreId > 0l){
	        ClientConfig clientConfig = new DefaultClientConfig();
	           clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	           Client client = Client.create(clientConfig);
	           WebResource webResource = client.resource("https://desk.zoho.com/api/v1/search?searchStr="+cadreId+"&module=contacts");         
	           WebResource.Builder builder = webResource.getRequestBuilder();               
	           builder.accept("application/json");
	           builder.type("application/json");
	           builder.header("orgId", IConstants.ZOHO_ADMIN_ORGID);
	           builder.header("Authorization","Zoho-authtoken "+IConstants.ZOHO_ADMIN_AUTHORIZATION);

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
	      LOG.error("excption raised in getContactIdForCadre methos in AlertCreationAPIService Class ", e);
	    }
	    return null;
	  }
}
