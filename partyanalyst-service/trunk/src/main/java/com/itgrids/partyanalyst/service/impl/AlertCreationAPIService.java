package com.itgrids.partyanalyst.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertImpactDAO;
import com.itgrids.partyanalyst.dao.IAlertImpactScopeDAO;
import com.itgrids.partyanalyst.dao.IAlertSeverityDAO;
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
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IAlertCreationAPIService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.MultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

public class AlertCreationAPIService implements IAlertCreationAPIService {

	private static final Logger LOG = Logger.getLogger(AlertService.class);
	
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
	public void sendApiDetailsOfAlertToZoho(Long alertId,String contactId,String deptId){
		 try {
			 
			 Alert alert=null;
			 if(alertId !=null){
				 alert = alertDAO.get(alertId);
			 }
			 
			 if(alert==null && alert.getAlertTypeId() !=1l)
				 return;
			 
			 JSONObject jsObj = new JSONObject();
			 
			 // We Need to Call Search Api and Get ContactId
			 
			 // 
			 
			 
			 jsObj.put("contactId", contactId);
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
			 
			 if(alert.getAlertCategoryId() == 2l || alert.getAlertCategoryId() == 3l){
				 if(alert.getAlertCategoryId() == 2l){
					 customJson.put("News Paper", alert.getEdition().getNewsPaper().getNewsPaper());
					 customJson.put("News Paper Edition", alert.getEdition().getEditionAlias());
					 customJson.put("Edition Type", alert.getEditionType().getEditionType());
					 
					//String imageId = getZohoUploadDocumentId("mytdp.com/NewsReaderImages/"+alert.getImageUrl());
					 
				 }else if(alert.getAlertCategoryId() == 3l){
					 customJson.put("TV News Channel", alert.getTvNewsChannel().getChannelName());
				 }
				 customJson.put("Category Type Id", alert.getAlertCategoryTypeId());
			 }
				 
			 jsObj.put("customFields", customJson);
			 
			 sendApiOfZohoAlert(jsObj,alert.getAlertId());
			
		} catch (Exception e) {
			LOG.error("Exception Occured in sendApiDetailsOfAlertToZoho() in AlertCreationAPIService", e);
		}
	 }
	 
	 
	 
	 public String sendApiOfZohoAlert(JSONObject jsonObj,Long alertId){
		 String result=null;
		 try {
			 
			 WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://desk.zoho.com/api/v1/tickets");

				WebResource.Builder builder = webResource.getRequestBuilder();

				builder.header("Authorization", IConstants.ZOHO_ADMIN_AUTHORIZATION);
				builder.header("orgId", IConstants.ZOHO_ADMIN_ORGID);
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
	 public JSONObject createAlertApi(final JSONObject jsonObject) throws JSONException{
		 JSONObject status=new JSONObject();
		try {
			 status = (JSONObject) transactionTemplate
					.execute(new TransactionCallback() {
						public Object doInTransaction(TransactionStatus status) {
							
							JSONObject result = new JSONObject();
							 DateUtilService date = new DateUtilService();
							 Alert alert = new Alert();
							 
							 try {
								 alert.setTitle(jsonObject.getString("title"));
								 alert.setDescription(jsonObject.has("description") && !jsonObject.getString("description").trim().isEmpty() ?
										 jsonObject.getString("description"):null);
								 
								 
								 if(jsonObject.has("severity") && !jsonObject.getString("severity").trim().isEmpty() ){
									 alert.setAlertSeverityId(alertSeverityDAO.getIdOfName(jsonObject.getString("severity")).get(0));
								 }
								 if(jsonObject.has("alertType") && !jsonObject.getString("alertType").trim().isEmpty()){
									 alert.setAlertTypeId(alertTypeDAO.getIdOfName(jsonObject.getString("alertType")).get(0));
								 }
								 if(jsonObject.has("impactScope") && !jsonObject.getString("impactScope").trim().isEmpty()){
									 alert.setImpactScopeId(alertImpactScopeDAO.getIdOfName(jsonObject.getString("impactScope")).get(0));
								 }
								 if(jsonObject.has("locationLevel") && !jsonObject.getString("locationLevel").trim().isEmpty()){
									 alert.setImpactLevelId(regionScopesDAO.getIdOfName(jsonObject.getString("locationLevel")).get(0));
								 }
								 if(jsonObject.has("alertSource") && !jsonObject.getString("alertSource").trim().isEmpty()){
									 alert.setAlertSourceId(alertSourceDAO.getIdOfName(jsonObject.getString("alertSource")).get(0));
								 }
							} catch (Exception e) {
								LOG.error("Exception Occured in JsonValues Appending in alertCreationApi method in AlertCreationAPIService", e);
							}
							 
							 UserAddress address = setLocationValuesByJson(jsonObject,alert);
							 
							 address=userAddressDAO.save(address);
							 alert.setAddressId(address.getUserAddressId());
							 
							 //alert.setCreatedBy(userId);
							// alert.setUpdatedBy(userId);

							
							 alert.setCreatedTime(date.getCurrentDateAndTime());
							 alert.setUpdatedTime(date.getCurrentDateAndTime());
							 alert.setIsDeleted("N");
							 alert.setAlertStatusId(1l);// default pending status
							 
							 
							 alert.setAlertCategoryId(1L);//default Manual alert (Need to be change)
							 alert.setApiType(IConstants.ZOHO_API_TYPE);
							 
							// alert.setAlertCategoryTypeId(inputVO.getCategoryId());
							 
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
	 
	public UserAddress setLocationValuesByJson(JSONObject json,Alert alert){
		UserAddress address = new UserAddress();
		try{
			
			if(json.has("location") && !json.getString("location").trim().isEmpty()){
				
				
				
				if(alert.getImpactLevelId() == 2l )
				{
					if(json.has("state") && !json.getString("state").trim().isEmpty()){
						address.setState(stateDAO.findByStateName(json.getString("state")).get(0));
					}
					alert.setImpactLevelValue(address.getState().getStateId());
				}
				else if(alert.getImpactLevelId() == 3l)
				{
					if(json.has("state") && !json.getString("state").trim().isEmpty()){
						address.setState(stateDAO.findByStateName(json.getString("state")).get(0));
					}
					if(json.has("district") && !json.getString("district").trim().isEmpty()){
						address.setDistrict(districtDAO.findByDistrictName(json.getString("district")).get(0));
					}
					alert.setImpactLevelValue(address.getDistrict().getDistrictId());
				}else if(alert.getImpactLevelId() == 4l){
					
					if(json.has("state") && !json.getString("state").trim().isEmpty()){
						address.setState(stateDAO.findByStateName(json.getString("state")).get(0));
					}
					if(json.has("district") && !json.getString("district").trim().isEmpty()){
						address.setDistrict(districtDAO.findByDistrictName(json.getString("district")).get(0));
					}
					if(json.has("constituency") && !json.getString("constituency").trim().isEmpty()){
						address.setConstituency(constituencyDAO.getConstituencyInfo(address.getDistrict().getDistrictId(),json.getString("constituency")).get(0));
					}
					
					alert.setImpactLevelValue(address.getConstituency().getConstituencyId());
				}else if(alert.getImpactLevelId() == 5l || alert.getImpactLevelId() == 7l)
				{
					if(json.has("state") && !json.getString("state").trim().isEmpty()){
						address.setState(stateDAO.findByStateName(json.getString("state")).get(0));
					}
					if(json.has("district") && !json.getString("district").trim().isEmpty()){
						address.setDistrict(districtDAO.findByDistrictName(json.getString("district")).get(0));
					}
					if(json.has("constituency") && !json.getString("constituency").trim().isEmpty()){
						address.setConstituency(constituencyDAO.getConstituencyInfo(address.getDistrict().getDistrictId(),json.getString("constituency")).get(0));
					}
					
					if(alert.getImpactLevelId() == 5l){
						address.setTehsil(constituencyTehsilDAO.getTehsilInfoOfConstuencyByTehsilName(address.getConstituency().getConstituencyId(), json.getString("tehsil")).get(0));
						alert.setImpactLevelValue(address.getTehsil().getTehsilId());
					}else if(alert.getImpactLevelId() == 7l){
						address.setLocalElectionBody(localElectionBodyDAO.getLocalElectionBodyByDistrictId(address.getDistrict().getDistrictId(),json.getString("municipality")).get(0));
						alert.setImpactLevelValue(address.getLocalElectionBody().getLocalElectionBodyId());
					}
					
				}else if(alert.getImpactLevelId() == 6l || alert.getImpactLevelId() == 8l){
					if(json.has("state") && !json.getString("state").trim().isEmpty()){
						address.setState(stateDAO.findByStateName(json.getString("state")).get(0));
					}
					if(json.has("district") && !json.getString("district").trim().isEmpty()){
						address.setDistrict(districtDAO.findByDistrictName(json.getString("district")).get(0));
					}
					if(json.has("constituency") && !json.getString("constituency").trim().isEmpty()){
						address.setConstituency(constituencyDAO.getConstituencyInfo(address.getDistrict().getDistrictId(),json.getString("constituency")).get(0));
					}
					
					if(alert.getImpactLevelId() == 6l){
						address.setTehsil(constituencyTehsilDAO.getTehsilInfoOfConstuencyByTehsilName(address.getConstituency().getConstituencyId(), json.getString("tehsil")).get(0));
						address.setPanchayatId(panchayatDAO.getPanchayatInfoOfTehsilByPanchayatName(address.getTehsil().getTehsilId(), json.getString("panchayat")).get(0));
						alert.setImpactLevelValue(address.getPanchayatId());
					}else if(alert.getImpactLevelId() == 8l){
						address.setLocalElectionBody(localElectionBodyDAO.getLocalElectionBodyByDistrictId(address.getDistrict().getDistrictId(),json.getString("municipality")).get(0));
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
}
