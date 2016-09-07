package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.CoreDashBoardVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
import com.itgrids.partyanalyst.service.INewsCoreDashBoardService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class NewsCoreDashBoardService implements INewsCoreDashBoardService{
	private final static Logger LOG = Logger.getLogger(CoreDashboardMainService.class);
	public ICoreDashboardGenericService coreDashboardGenericService;
	

	public ICoreDashboardGenericService getCoreDashboardGenericService() {
		return coreDashboardGenericService;
	}
	public void setCoreDashboardGenericService(
			ICoreDashboardGenericService coreDashboardGenericService) {
		this.coreDashboardGenericService = coreDashboardGenericService;
	}



	public List<List<UserTypeVO>> getUserTypeWiseNewsCounts(Long userId,Long activityMemberId,Long userTypeId,String state,String fromDate,String toDate,Long benefitId){
		List<List<UserTypeVO>> userTypesList = null;
		try {
			
			 //calling generic method.
			 ActivityMemberVO activityMemberVO = new ActivityMemberVO();
		     activityMemberVO.setUserId(userId);
		     activityMemberVO.setActivityMemberId(activityMemberId);
		     activityMemberVO.setUserTypeId(userTypeId);
		     activityMemberVO = coreDashboardGenericService.getChildActivityMembersAndLocationsNew(activityMemberVO);
		     Map<Long,Map<Long,UserTypeVO>> userTypesMap = activityMemberVO.getUserTypesMap();
		     Map<Long,Set<Long>> locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
		     activityMemberVO.setState(state);
		     activityMemberVO.setFromDate(fromDate);
		     activityMemberVO.setToDate(toDate);
		     
		     //Calling Request.
			 //Client client = Client.create();
		     ClientConfig clientConfig = new DefaultClientConfig();
		     
		     clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
			 
	         WebResource webResource = client.resource("https://mytdp.com/CommunityNewsPortal/webservice/getUserTypeWiseNewsCounts");
	         
			 //WebResource webResource = client.resource("https://mytdp.com/CommunityNewsPortal/webservice/getUserTypeWiseNewsCounts/"+locationLevelIdsMap+"/"+state+"/"+fromDate+"/"+toDate+"");
		     
			 //ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
	         
	         ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, activityMemberVO);
	         
	 	      if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 
	 	    	  List<CoreDashBoardVO> wsResultList = new ArrayList<CoreDashBoardVO>();
	 	    	  
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
		 	    		
		 	    		 for(int i=0;i<finalArray.length();i++){
		 	    			CoreDashBoardVO vo = new CoreDashBoardVO();
		 	    			JSONObject tmp = (JSONObject) finalArray.get(i);
		 	    			
		 	    			vo.setId(tmp.getLong("id"));
		 	    			vo.setName(tmp.getString("name"));
		 	    			
		 	    			String s1 = tmp.getString("coreDashBoardVOList");
		 	    			JSONArray inArr = new JSONArray(s1);
		 	    			
		 	    			if(inArr != null && inArr.length() > 0){
		 	    				for (int j = 0; j < inArr.length(); j++) {
		 	    					JSONObject tmp1 = (JSONObject) inArr.get(j);
		 	    					
		 	    					CoreDashBoardVO invo = new CoreDashBoardVO();
		 	    					invo.setId(tmp1.getLong("id"));
		 	    					invo.setPositiveCount(tmp1.getLong("positiveCountMain"));
		 	    					invo.setNegativCount(tmp1.getLong("negativCountMain"));
		 	    					invo.setNeutralCount(tmp1.getLong("neutralCountMain"));
		 	    					vo.getCoreDashBoardVOList().add(invo);
								}
		 	    			}
		 	    			
		 	    			wsResultList.add(vo);
		 	    		 }
	 	    		}
	 	    		
	 	    	}
	 	    	
	 	    	if(wsResultList != null && wsResultList.size() > 0){
	 	    		setResultToUserBase(userTypesMap,wsResultList);	
	 	    		
	 	    		//set result map to list
	 	    		if(userTypesMap != null && userTypesMap.size() > 0){
	 			    	 userTypesList = new ArrayList<List<UserTypeVO>>();
	 			    	 for(Long userType:userTypesMap.keySet()){
	 			    		 Map<Long,UserTypeVO> membersMap = userTypesMap.get(userType);
	 			    		 userTypesList.add(new ArrayList<UserTypeVO>(membersMap.values()));
	 			    	 }
	 			     }
	 	    		
	 	    		if(benefitId == 1l){//sort based on positive
	 	    			if(userTypesList != null && userTypesList.size()>0){
	 	    			   for(List<UserTypeVO> membersList : userTypesList){
	 	    				   if(membersList != null){  
	 	    					  Collections.sort(membersList,positiveSorting);
	 	    				   }
	 	    			   }
	 	    		   }
	 	    		}else if(benefitId == 2l){//sort based in negative
	 	    			if(userTypesList != null && userTypesList.size()>0){
		 	    			   for(List<UserTypeVO> membersList : userTypesList){
		 	    				   if(membersList != null){  
		 	    					  Collections.sort(membersList,negativeSoring);
		 	    				   }
		 	    			   }
		 	    		   }
	 	    		}
	 	    		
	 	    	}
	 	    	
	 	    	
	 	   }
	 	      
		} catch (Exception e) {
			LOG.error("Exception raised at coreDashboardMainService", e);
		}
		return userTypesList;
	}
	
	public static Comparator<UserTypeVO> positiveSorting = new Comparator<UserTypeVO>() {
	     public int compare(UserTypeVO member2, UserTypeVO member1) {

	        Double perc2 = member2.getPositivePercentage();
	        Double perc1 = member1.getPositivePercentage();
	         return perc2.compareTo(perc1);
	    }
	}; 
	
	public static Comparator<UserTypeVO> negativeSoring = new Comparator<UserTypeVO>() {
	     public int compare(UserTypeVO member2, UserTypeVO member1) {

	        Double perc2 = member2.getNegativePercentage();
	        Double perc1 = member1.getNegativePercentage();
	         return perc2.compareTo(perc1);
	    }
	}; 
  
	public void setResultToUserBase(Map<Long, Map<Long, UserTypeVO>> userTypesMap,List<CoreDashBoardVO> wsResultList){
		if(userTypesMap != null && userTypesMap.size() > 0){
 			for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypesMap.entrySet()) {
 				Map<Long, UserTypeVO> userMap = entry.getValue();
 				for (Entry<Long, UserTypeVO> entryUser : userMap.entrySet()) {
					UserTypeVO userVO = entryUser.getValue();
					if(userVO.getLocationLevelId() != null && userVO.getLocationLevelId()>0l && userVO.getLocationValuesSet() != null && userVO.getLocationValuesSet().size() > 0){
						for (Long locationId : userVO.getLocationValuesSet()) {
							CoreDashBoardVO matchedLocationLevelvo = getMatchedLocationVO(wsResultList,userVO.getLocationLevelId());//location level vo
							if(matchedLocationLevelvo != null){
								CoreDashBoardVO matchedLocationVO = getMatchedLocationVO(matchedLocationLevelvo.getCoreDashBoardVOList(),locationId);//location vo
								if(matchedLocationVO != null){
									userVO.setPositiveCount(userVO.getPositiveCount()+matchedLocationVO.getPositiveCount());
									userVO.setNegativeCount(userVO.getNegativeCount()+matchedLocationVO.getNegativCount());
								}
							}
							
						}
					}
					userVO.setTotalCount(userVO.getPositiveCount()+userVO.getNegativeCount());
					if(userVO.getTotalCount() > 0l){
						userVO.setPositivePercentage(caclPercantage(userVO.getPositiveCount(),userVO.getTotalCount()));
						userVO.setNegativePercentage(caclPercantage(userVO.getNegativeCount(),userVO.getTotalCount()));
					}
						
				}
			}
 		}
	}
	
	public Double caclPercantage(Long subCount,Long totalCount){
		Double d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d;
	}
	
	public CoreDashBoardVO getMatchedLocationVO(List<CoreDashBoardVO> wsResultList,Long locationLevelId){
		if(wsResultList != null && wsResultList.size() > 0){
			for (CoreDashBoardVO coreDashBoardVO : wsResultList) {
				if(coreDashBoardVO.getId().equals(locationLevelId)){
					return coreDashBoardVO;
				}
			}
		}
		return null;
	}
	
	
}
