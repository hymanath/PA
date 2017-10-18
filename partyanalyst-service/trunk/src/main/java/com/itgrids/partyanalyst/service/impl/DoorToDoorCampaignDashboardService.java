package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.itgrids.partyanalyst.dto.CommentsDashBoardVO;
import com.itgrids.partyanalyst.dto.DoorCampaignDashboardVO;
import com.itgrids.partyanalyst.dto.DoorToDoorInputVO;
import com.itgrids.partyanalyst.dto.InputCommentVO;
import com.itgrids.partyanalyst.service.IDoorToDoorCampaignDashboardService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;


public class DoorToDoorCampaignDashboardService implements IDoorToDoorCampaignDashboardService{

	private static final Logger LOG = Logger.getLogger(DoorToDoorCampaignDashboardService.class);
	
	public DoorCampaignDashboardVO getUsersCountsByLocation(DoorToDoorInputVO inputVO){
		DoorCampaignDashboardVO returnvo = new DoorCampaignDashboardVO();
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource resource = client.resource(IConstants.ITDP_LIVE_URL+"getUsersCountsByLocation");
			ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
			
			 if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		Gson gson = new Gson();
	 	    		returnvo = gson.fromJson(output,DoorCampaignDashboardVO.class);
	 	    	}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception Occured in getUsersCountsByLocation in DoorToDoorCampaignDashboardService", e);
		}
		return returnvo;
	}
	
	public DoorCampaignDashboardVO getHouseHoldsCounts(DoorToDoorInputVO inputVO){
		DoorCampaignDashboardVO returnvo = new DoorCampaignDashboardVO();
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource resource = client.resource(IConstants.ITDP_LIVE_URL+"getHouseHoldsCounts");
			ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
			
			 if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		Gson gson = new Gson();
	 	    		returnvo = gson.fromJson(output,DoorCampaignDashboardVO.class);
	 	    	}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception Occured in getHouseHoldsCounts in DoorToDoorCampaignDashboardService", e);
		}
		return returnvo;
	}
	
	public DoorCampaignDashboardVO getGrievancesCounts(DoorToDoorInputVO inputVO){
		DoorCampaignDashboardVO returnvo = new DoorCampaignDashboardVO();
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource resource = client.resource(IConstants.ITDP_LIVE_URL+"getGrievancesCounts");
			ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
			
			 if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		Gson gson = new Gson();
	 	    		returnvo = gson.fromJson(output,DoorCampaignDashboardVO.class);
	 	    	}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception Occured in getGrievancesCounts in DoorToDoorCampaignDashboardService", e);
		}
		return returnvo;
	}
	
	public DoorCampaignDashboardVO getRecentImagesList(DoorToDoorInputVO inputVO){
		DoorCampaignDashboardVO returnvo = new DoorCampaignDashboardVO();
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource resource = client.resource(IConstants.ITDP_LIVE_URL+"getRecentImagesList");
			ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
			
			 if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		Gson gson = new Gson();
	 	    		returnvo = gson.fromJson(output,DoorCampaignDashboardVO.class);
	 	    	}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception Occured in getRecentImagesList in DoorToDoorCampaignDashboardService", e);
		}
		return returnvo;
	}
	
	public List<DoorCampaignDashboardVO> getLocationWiseCountDetails(DoorToDoorInputVO inputVO){
		List<DoorCampaignDashboardVO> returnList = new ArrayList<DoorCampaignDashboardVO>(0);
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource resource = client.resource(IConstants.ITDP_LIVE_URL+"getLocationWiseCountDetails");
			ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
			
			 if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			//List<DoorCampaignDashboardVO> list = new ArrayList<DoorCampaignDashboardVO>(0);
	 	    			Gson gson = new Gson();
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				DoorCampaignDashboardVO dashboardVO = gson.fromJson(jObj.toString(),DoorCampaignDashboardVO.class);
	 	    				//System.out.println(dashboardVO.getConstituencyId());
	 	    				returnList.add(dashboardVO);
	 	    			}
	 	    		}
	 	    	}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception Occured in getLocationWiseCountDetails in DoorToDoorCampaignDashboardService", e);
		}
		return returnList;
	}
	
	public List<DoorCampaignDashboardVO> getDepartmentWiseGrievanceCounts(DoorToDoorInputVO inputVO){
		List<DoorCampaignDashboardVO> returnList = new ArrayList<DoorCampaignDashboardVO>(0);
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource resource = client.resource(IConstants.ITDP_LIVE_URL+"getDepartmentWiseGrievanceCounts");
			ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
			
			 if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			//List<DoorCampaignDashboardVO> list = new ArrayList<DoorCampaignDashboardVO>(0);
	 	    			Gson gson = new Gson();
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				DoorCampaignDashboardVO dashboardVO = gson.fromJson(jObj.toString(),DoorCampaignDashboardVO.class);
	 	    				//System.out.println(dashboardVO.getConstituencyId());
	 	    				returnList.add(dashboardVO);
	 	    			}
	 	    		}
	 	    	}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception Occured in getDepartmentWiseGrievanceCounts in DoorToDoorCampaignDashboardService", e);
		}
		return returnList;
	}
	
	public List<DoorCampaignDashboardVO> getDepartmentIssueWiseGrievanceCounts(DoorToDoorInputVO inputVO){
		List<DoorCampaignDashboardVO> returnList = new ArrayList<DoorCampaignDashboardVO>(0);
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource resource = client.resource(IConstants.ITDP_LIVE_URL+"getDepartmentIssueWiseGrievanceCounts");
			ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
			
			 if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			//List<DoorCampaignDashboardVO> list = new ArrayList<DoorCampaignDashboardVO>(0);
	 	    			Gson gson = new Gson();
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				DoorCampaignDashboardVO dashboardVO = gson.fromJson(jObj.toString(),DoorCampaignDashboardVO.class);
	 	    				//System.out.println(dashboardVO.getConstituencyId());
	 	    				returnList.add(dashboardVO);
	 	    			}
	 	    		}
	 	    	}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception Occured in getDepartmentIssueWiseGrievanceCounts in DoorToDoorCampaignDashboardService", e);
		}
		return returnList;
	}
	
	public List<DoorCampaignDashboardVO> getDepartmentSubIssueWiseGrievanceCounts(DoorToDoorInputVO inputVO){
		List<DoorCampaignDashboardVO> returnList = new ArrayList<DoorCampaignDashboardVO>(0);
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource resource = client.resource(IConstants.ITDP_LIVE_URL+"getDepartmentSubIssueWiseGrievanceCounts");
			ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
			
			 if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			//List<DoorCampaignDashboardVO> list = new ArrayList<DoorCampaignDashboardVO>(0);
	 	    			Gson gson = new Gson();
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				DoorCampaignDashboardVO dashboardVO = gson.fromJson(jObj.toString(),DoorCampaignDashboardVO.class);
	 	    				//System.out.println(dashboardVO.getConstituencyId());
	 	    				returnList.add(dashboardVO);
	 	    			}
	 	    		}
	 	    	}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception Occured in getDepartmentSubIssueWiseGrievanceCounts in DoorToDoorCampaignDashboardService", e);
		}
		return returnList;
	}
	
	public DoorCampaignDashboardVO getLevelWiseCount(DoorToDoorInputVO inputVO){
		DoorCampaignDashboardVO returnvo = new DoorCampaignDashboardVO();
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource resource = client.resource(IConstants.ITDP_LIVE_URL+"getLevelWiseCount");
			ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
			
			 if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		Gson gson = new Gson();
	 	    		returnvo = gson.fromJson(output,DoorCampaignDashboardVO.class);
	 	    	}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception Occured in getLevelWiseCount in DoorToDoorCampaignDashboardService", e);
		}
		return returnvo;
	}
	
	public DoorCampaignDashboardVO getCampaignCountFrMandalPancMuncip(DoorToDoorInputVO inputVO){
		DoorCampaignDashboardVO returnvo = new DoorCampaignDashboardVO();
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource resource = client.resource(IConstants.ITDP_LIVE_URL+"getCampaignCountFrMandalPancMuncip");
			ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
			
			 if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		Gson gson = new Gson();
	 	    		returnvo = gson.fromJson(output,DoorCampaignDashboardVO.class);
	 	    	}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception Occured in getCampaignCountFrMandalPancMuncip in DoorToDoorCampaignDashboardService", e);
		}
		return returnvo;
	}
	
	public DoorCampaignDashboardVO getUserAccessLevelIdsAndValues(DoorToDoorInputVO inputVO){
		DoorCampaignDashboardVO returnvo = new DoorCampaignDashboardVO();
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			
			WebResource resource = client.resource(IConstants.ITDP_LIVE_URL+"getUserAccessLevelIdsAndValues");
			ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
			
			 if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		Gson gson = new Gson();
	 	    		returnvo = gson.fromJson(output,DoorCampaignDashboardVO.class);
	 	    	}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception Occured in getUserAccessLevelIdsAndValues in DoorToDoorCampaignDashboardService", e);
		}
		return returnvo;
	}
	
	public List<DoorCampaignDashboardVO> getUserWiseCountsFrLoginUser(DoorToDoorInputVO inputVO){
		List<DoorCampaignDashboardVO> returnList = new ArrayList<DoorCampaignDashboardVO>(0);
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource resource = client.resource(IConstants.ITDP_LIVE_URL+"getUserWiseCountFrLoginUser");
			ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
			
			 if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			//List<DoorCampaignDashboardVO> list = new ArrayList<DoorCampaignDashboardVO>(0);
	 	    			Gson gson = new Gson();
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				DoorCampaignDashboardVO dashboardVO = gson.fromJson(jObj.toString(),DoorCampaignDashboardVO.class);
	 	    				//System.out.println(dashboardVO.getConstituencyId());
	 	    				returnList.add(dashboardVO);
	 	    			}
	 	    		}
	 	    	}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception Occured in getUserWiseCountsFrLoginUser in DoorToDoorCampaignDashboardService", e);
		}
		return returnList;
	}
	
	public List<DoorCampaignDashboardVO> getAssignedConstituenciesForUser(DoorToDoorInputVO inputVO){
		List<DoorCampaignDashboardVO> returnList = new ArrayList<DoorCampaignDashboardVO>(0);
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource resource = client.resource(IConstants.ITDP_LIVE_URL+"getAssignedConstituenciesForUser");
			ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
			
			 if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			Gson gson = new Gson();
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				DoorCampaignDashboardVO dashboardVO = gson.fromJson(jObj.toString(),DoorCampaignDashboardVO.class);
	 	    				returnList.add(dashboardVO);
	 	    			}
	 	    		}
	 	    	}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception Occured in getAssignedConstituenciesForUser in DoorToDoorCampaignDashboardService", e);
		}
		return returnList;
	}
	
	public String saveConstituencyComments(InputCommentVO inputVO){
		String status = null;
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource resource = client.resource(IConstants.ITDP_LIVE_URL+"saveConstituencyComments");
			ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
			
			 if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 status = response.getEntity(String.class);
	 	    	}
		} catch (Exception e) {
			LOG.error("Exception Occured in saveConstituencyComments in DoorToDoorCampaignDashboardService", e);
		}
		return status;
	}
	
	public List<CommentsDashBoardVO> getLocationWiseComments(InputCommentVO inputVO){
		List<CommentsDashBoardVO> returnList = new ArrayList<CommentsDashBoardVO>(0);
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource resource = client.resource(IConstants.ITDP_LIVE_URL+"getLocationWiseComments");
			ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
			
			 if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			Gson gson = new Gson();
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				CommentsDashBoardVO cmntDashBoardVO = gson.fromJson(jObj.toString(),CommentsDashBoardVO.class);
	 	    				returnList.add(cmntDashBoardVO);
	 	    			}
	 	    		}
	 	    	}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception Occured in getLocationWiseComments in DoorToDoorCampaignDashboardService", e);
		}
		return returnList;
	}
	
	public List<CommentsDashBoardVO> getConstituencyWiseCommentDetails(InputCommentVO inputVO){
		List<CommentsDashBoardVO> returnList = new ArrayList<CommentsDashBoardVO>(0);
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource resource = client.resource(IConstants.ITDP_LIVE_URL+"getConstituencyWiseCommentDetails");
			ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
			
			 if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			Gson gson = new Gson();
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				CommentsDashBoardVO cmntDashBoardVO = gson.fromJson(jObj.toString(),CommentsDashBoardVO.class);
	 	    				returnList.add(cmntDashBoardVO);
	 	    			}
	 	    		}
	 	    	}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception Occured in getConstituencyWiseCommentDetails in DoorToDoorCampaignDashboardService", e);
		}
		return returnList;
	}
}
