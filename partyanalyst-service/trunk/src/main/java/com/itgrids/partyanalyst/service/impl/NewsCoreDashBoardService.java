package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.ChildUserTypeVO;
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



	public List<List<UserTypeVO>> getUserTypeWiseNewsCounts(Long userId,Long activityMemberId,Long userTypeId,String state,String fromDate,String toDate,Long benefitId,List<Long> npIds,List<Long> impactScopeIdsList){
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
		     activityMemberVO.setNpIds(npIds);
		     activityMemberVO.setImpactScopeIds(impactScopeIdsList);
		     
		     //Calling Request.
			 //Client client = Client.create();
		     ClientConfig clientConfig = new DefaultClientConfig();
		     
		     clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
			 
	         WebResource webResource = client.resource("http://mytdp.com/CommunityNewsPortal/webservice/getUserTypeWiseNewsCounts");
	         
			 String jsonInString = new ObjectMapper().writeValueAsString(activityMemberVO);
	         System.out.println(jsonInString);
	         
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
		 	    					invo.setPositiveCountMain(tmp1.getLong("positiveCountMain"));
		 	    					invo.setNegativCountMain(tmp1.getLong("negativCountMain"));
		 	    					invo.setNeutralCountMain(tmp1.getLong("neutralCountMain"));
		 	    					vo.getCoreDashBoardVOList().add(invo);
								}
		 	    			}
		 	    			
		 	    			wsResultList.add(vo);
		 	    		 }
	 	    		}
	 	    		
	 	    	}
	 	    	
	 	    	if(wsResultList != null && wsResultList.size() > 0){
	 	    		setResultToUserBase(userTypesMap,wsResultList);	
	 	    		
	 	    	 	//mixing secreteries and general secreteries.
	 	    		if(userTypesMap!=null && userTypesMap.size()>0){
	 			    	 
	 			    	 Map<Long,UserTypeVO> orgSecAndSecMap = new LinkedHashMap<Long,UserTypeVO>();
	 			    	 
	 			    	 Map<Long,UserTypeVO>  secreteriesMap = null;
	 			    	 if(userTypesMap.containsKey(11l)){
	 			    		 secreteriesMap = userTypesMap.get(11l);
	 			    		 orgSecAndSecMap.putAll(secreteriesMap);
	 			    		 //remove secreteries from Map
	 			    		 userTypesMap.remove(11l); 
	 			    	 }
	 			    	 
	 			    	 Map<Long,UserTypeVO>  organizingSecreteriesMap = null;
	 			    	 if(userTypesMap.containsKey(4l)){
	 			    		 organizingSecreteriesMap = userTypesMap.get(4l);
	 			    		 orgSecAndSecMap.putAll(organizingSecreteriesMap);
	 			    	 }
	 			    	
	 			    	 if(organizingSecreteriesMap!=null && organizingSecreteriesMap.size()>0){
	 			    		 userTypesMap.put(4l, orgSecAndSecMap); 
	 			    	 }
	 			    	 
	 			     }
	 	    		
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
		 	    					  Collections.sort(membersList,negativeSorting);
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
	         return perc1.compareTo(perc2);
	    }
	}; 
	
	public static Comparator<UserTypeVO> negativeSorting = new Comparator<UserTypeVO>() {
	     public int compare(UserTypeVO member2, UserTypeVO member1) {

	        Double perc2 = member2.getNegativePercentage();
	        Double perc1 = member1.getNegativePercentage();
	         return perc1.compareTo(perc2);
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
									userVO.setPositiveCount(userVO.getPositiveCount()+matchedLocationVO.getPositiveCountMain());
									userVO.setNegativeCount(userVO.getNegativeCount()+matchedLocationVO.getNegativCountMain());
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
	
	public List<ChildUserTypeVO> getPartyComparisonChildUserTypeMembers(Long parentActivityMemberId,List<Long> childUserTypeIdArray,String state,String startDate,String endDate,List<Long> npIds,List<Long> impactScopeIds){
		List<ChildUserTypeVO> finalList = new ArrayList<ChildUserTypeVO>(0);//Teja
		try {
			
			//ActivityMemberVO activityMemberVO = coreDashboardGenericService.getSelectedChildUserTypeMembers(parentActivityMemberId,childUserTypeId);
			  ActivityMemberVO activityMemberVO = coreDashboardGenericService.getRequiredSubLevelActivityMembersDetails(parentActivityMemberId,childUserTypeIdArray);
		    Map<Long,UserTypeVO> childActivityMembersMap = activityMemberVO.getActivityMembersMap();
		    Map<Long,Set<Long>> locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
		    activityMemberVO.setState(state);
		     activityMemberVO.setFromDate(startDate);
		     activityMemberVO.setToDate(endDate);
		     activityMemberVO.setNpIds(npIds);
		     activityMemberVO.setImpactScopeIds(impactScopeIds);
		    ClientConfig clientConfig = new DefaultClientConfig();
		     
		     clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
			 
	         WebResource webResource = client.resource("http://mytdp.com/CommunityNewsPortal/webservice/getPartyComparisonChildUserTypeMembers");
	         
			 String jsonInString = new ObjectMapper().writeValueAsString(activityMemberVO);
	         System.out.println(jsonInString);
	         
	         ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, activityMemberVO);
	         
	         if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 
	 	    	  List<CoreDashBoardVO> wsResultList = new ArrayList<CoreDashBoardVO>();
	 	    	  String output = response.getEntity(String.class);
	 	    	 finalList =  getCommanPartyComparisonChildUserTypeMembers(childActivityMembersMap,wsResultList,output);
	 	   }
		} catch (Exception e) {
			LOG.error("Exception riased at getPartyComparisonChildUserTypeMembers service", e);
		}
		return finalList;
	}
	
	public static Comparator<ChildUserTypeVO> positiveSorting1 = new Comparator<ChildUserTypeVO>() {
	     public int compare(ChildUserTypeVO member2, ChildUserTypeVO member1) {

	        Double perc2 = member2.getNeutralCountMainperd();
	        Double perc1 = member1.getNeutralCountMainperd();
	         return perc1.compareTo(perc2);
	    }
	}; 
	
	public void setWSResultToUserBase(Map<Long, UserTypeVO> childActivityMembersMap,List<CoreDashBoardVO> wsResultList,List<ChildUserTypeVO> finalList){
		if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
			for (Entry<Long, UserTypeVO> entry : childActivityMembersMap.entrySet()) {
				ChildUserTypeVO vo = new ChildUserTypeVO();
				UserTypeVO temp = entry.getValue();
				vo.setActivityMemberId(temp.getActivityMemberId());
				vo.setImage(temp.getImage());
				vo.setLocationLevelId(temp.getLocationLevelId());
				vo.setLocationLevelName(temp.getLocationLevelName());
				vo.setLocationValueSet(temp.getLocationValuesSet());
				vo.setName(temp.getName());
				vo.setTdpCadreId(temp.getTdpCadreId()+"");
				vo.setUserTypeId(temp.getUserTypeId());
				vo.setUsertType(temp.getUserType());
				
				//counts setting for parties graph
				if(vo.getLocationValueSet() != null && vo.getLocationValueSet().size() > 0){
					for (Long locationId : vo.getLocationValueSet()) {
						CoreDashBoardVO matchedLocationVo =  getMatchedLocationVO(vo.getLocationLevelId(),locationId,wsResultList);
						if(matchedLocationVo != null && matchedLocationVo.getCoreDashBoardVOList() != null && matchedLocationVo.getCoreDashBoardVOList().size() > 0){//parties wise vos list
							for (CoreDashBoardVO childUserTypeVO : matchedLocationVo.getCoreDashBoardVOList()) {
								ChildUserTypeVO matchedPartyVO = getMatchedPartyVO(childUserTypeVO.getOrganizationId(),vo.getChildUserTypeVOList());
								if(matchedPartyVO == null){
									matchedPartyVO = new ChildUserTypeVO();
									matchedPartyVO.setOrganizationId(childUserTypeVO.getOrganizationId());
									matchedPartyVO.setOrganization(childUserTypeVO.getOrganization());
									matchedPartyVO.setCount(childUserTypeVO.getCount());
									vo.getChildUserTypeVOList().add(matchedPartyVO);
								}else{
									matchedPartyVO.setCount(matchedPartyVO.getCount()+childUserTypeVO.getCount());
								}
							}
						}
						
					}
				}
				
				//counts setting for editions wise
				if(vo.getLocationValueSet() != null && vo.getLocationValueSet().size() > 0){
					for (Long locationId : vo.getLocationValueSet()) {
						CoreDashBoardVO matchedLocationVo = getMatchedLocationLevelVoForEdi(vo.getLocationLevelId(),locationId,wsResultList);
						if(matchedLocationVo != null && matchedLocationVo.getCoreDashBoardVOList() != null && matchedLocationVo.getCoreDashBoardVOList().size() > 0){
							for (CoreDashBoardVO editionVO : matchedLocationVo.getCoreDashBoardVOList()) {
								ChildUserTypeVO matchedEditionVO = getMatchedPartyVO(editionVO.getOrganizationId(),vo.getChildUserTypeVOList1());
								if(matchedEditionVO == null){
									matchedEditionVO = new ChildUserTypeVO();
									matchedEditionVO.setOrganizationId(editionVO.getOrganizationId());
									matchedEditionVO.setOrganization(editionVO.getOrganization());
									matchedEditionVO.setName(editionVO.getName());
									//if(editionVO.getName().equalsIgnoreCase("Main")){
										matchedEditionVO.setPositiveCountMain(editionVO.getPositiveCountMain());
										matchedEditionVO.setNegativeCountMain(editionVO.getNegativCountMain());
									//}else if(editionVO.getName().equalsIgnoreCase("Dist")){
										matchedEditionVO.setPositiveCountDist(editionVO.getPositiveCountDist());
										matchedEditionVO.setNegativeCountDist(editionVO.getNegativCountDist());
									//}
									
									vo.getChildUserTypeVOList1().add(matchedEditionVO);
								}else{
									//if(editionVO.getName().equalsIgnoreCase("Main")){
										matchedEditionVO.setPositiveCountMain(matchedEditionVO.getPositiveCountMain()+editionVO.getPositiveCountMain());
										matchedEditionVO.setNegativeCountMain(matchedEditionVO.getNegativeCountMain()+editionVO.getNegativCountMain());
									//}else if(editionVO.getName().equalsIgnoreCase("Dist")){
										matchedEditionVO.setPositiveCountDist(matchedEditionVO.getPositiveCountDist()+editionVO.getPositiveCountDist());
										matchedEditionVO.setNegativeCountDist(matchedEditionVO.getNegativeCountDist()+editionVO.getNegativCountDist());
									//}
								}
							}
						}
					}
				}
				finalList.add(vo);
			}
		}
	}
	
	public CoreDashBoardVO getMatchedLocationLevelVoForEdi(Long locationLevelId,Long locationId,List<CoreDashBoardVO> wsResultList){
		if(wsResultList != null && wsResultList.size() > 0){
			for (CoreDashBoardVO mainVo : wsResultList) {
				if(mainVo.getId().equals(locationLevelId)){
					if(mainVo.getCoreDashBoardVOList1() != null && mainVo.getCoreDashBoardVOList1().size() > 0){
						for (CoreDashBoardVO subVO : mainVo.getCoreDashBoardVOList1()) {
							if(subVO.getId().equals(locationId)){
								return subVO;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	public ChildUserTypeVO getMatchedPartyVO(Long partyId,List<ChildUserTypeVO> voList){
		if(voList != null && voList.size() > 0){
			for (ChildUserTypeVO childUserTypeVO : voList) {
				if(childUserTypeVO.getOrganizationId().equals(partyId)){
					return childUserTypeVO;
				}
			}
		}
		return null; 
	} 
	
	public CoreDashBoardVO getMatchedLocationVO(Long locationLevelId,Long locationId,List<CoreDashBoardVO> wsResultList){
		if(wsResultList != null && wsResultList.size() > 0){
			for (CoreDashBoardVO coreDashBoardVO : wsResultList) {
				if(coreDashBoardVO.getId().equals(locationLevelId)){
					if(coreDashBoardVO.getCoreDashBoardVOList() != null && coreDashBoardVO.getCoreDashBoardVOList().size() > 0){
						for (CoreDashBoardVO partiesVo : coreDashBoardVO.getCoreDashBoardVOList()) {
							if(partiesVo.getId().equals(locationId)){
								return partiesVo;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	public List<ChildUserTypeVO> getPartyCompareSubLevelMemberDetails(Long activityMemberId,Long userTypeId,String state,String startDate,String endDate,List<Long> npIds,List<Long> impactScopeIdsList){
		List<ChildUserTypeVO> finalVoList = new ArrayList<ChildUserTypeVO>(0);//sandeep
		try {
			
			ActivityMemberVO activityMemberVO = coreDashboardGenericService.getDirectChildActivityMemberCommitteeDetails(activityMemberId,userTypeId);
		    Map<Long,UserTypeVO> childActivityMembersMap = activityMemberVO.getActivityMembersMap();
		    Map<Long,Set<Long>> locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
		    activityMemberVO.setState(state);
		     activityMemberVO.setFromDate(startDate);
		     activityMemberVO.setToDate(endDate);
		     activityMemberVO.setNpIds(npIds);
		     activityMemberVO.setImpactScopeIds(impactScopeIdsList);
		    ClientConfig clientConfig = new DefaultClientConfig();
		     
		     clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
			 
	         WebResource webResource = client.resource("http://mytdp.com/CommunityNewsPortal/webservice/getPartyCompareSubLevelMemberDetails");
	         
			 String jsonInString = new ObjectMapper().writeValueAsString(activityMemberVO);
	         System.out.println(jsonInString);
	         
	         ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, activityMemberVO);
	         
	         if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 List<CoreDashBoardVO> wsResultList = new ArrayList<CoreDashBoardVO>();
	 	    	  String output = response.getEntity(String.class);
	 	    	 finalVoList = getCommanPartyComparisonChildUserTypeMembers(childActivityMembersMap,wsResultList,output);
	 	      }
		    
		    
		} catch (Exception e) {
			LOG.error("Exception raised at getPartyCompareSubLevelMemberDetails", e);
		}
		return finalVoList;
	}
	public List<ChildUserTypeVO> getCommanPartyComparisonChildUserTypeMembers(Map<Long,UserTypeVO> childActivityMembersMap,List<CoreDashBoardVO> wsResultList,String output){
		List<ChildUserTypeVO> finalList = new ArrayList<ChildUserTypeVO>(0);
	try {
		
		if(output != null && !output.isEmpty()){
			JSONArray finalArray = new JSONArray(output);
			if(finalArray!=null && finalArray.length()>0){
	    		
	    		 for(int i=0;i<finalArray.length();i++){
	    			CoreDashBoardVO vo = new CoreDashBoardVO();
	    			JSONObject tmp = (JSONObject) finalArray.get(i);
	    			
	    			vo.setId(tmp.getLong("id"));
	    			
	    			String s1 = tmp.getString("coreDashBoardVOList");
	    			JSONArray inArr = new JSONArray(s1);
	    			
	    			if(inArr != null && inArr.length() > 0){
	    				for (int j = 0; j < inArr.length(); j++) {
	    					JSONObject tmp1 = (JSONObject) inArr.get(j);
	    					
	    					CoreDashBoardVO invo = new CoreDashBoardVO();
	    					invo.setId(tmp1.getLong("id"));
	    					
	    					String s2 = tmp1.getString("coreDashBoardVOList");
	    					JSONArray inArr1 = new JSONArray(s2);
	    					if(inArr1 != null && inArr1.length() > 0){
	    						for (int k = 0; k < inArr1.length(); k++) {
	    							JSONObject tmp2 = (JSONObject) inArr1.get(k);
	    							
	    							CoreDashBoardVO orgvo = new CoreDashBoardVO();
	    							orgvo.setOrganizationId(tmp2.getLong("organizationId"));
	    							orgvo.setOrganization(tmp2.getString("organization"));
	    							orgvo.setCount(tmp2.getLong("count"));
	    							
	    							invo.getCoreDashBoardVOList().add(orgvo);
	    						}
	    						
	    					}
	    					
	    					vo.getCoreDashBoardVOList().add(invo);
						}
	    			}
	    			
	    			
	    			String voList1 = tmp.getString("coreDashBoardVOList1");
	    			JSONArray ediWiseArr = new JSONArray(voList1);
	    			if(inArr != null && ediWiseArr.length() > 0){
	    				for (int j = 0; j < ediWiseArr.length(); j++) {
	    					JSONObject tmp1 = (JSONObject) ediWiseArr.get(j);
	    					
	    					CoreDashBoardVO invo = new CoreDashBoardVO();
	    					invo.setId(tmp1.getLong("id"));
	    					
	    					String ediVOList = tmp1.getString("coreDashBoardVOList");
	    					
	    					JSONArray inArr1 = new JSONArray(ediVOList);
	    					if(inArr1 != null && inArr1.length() > 0){
	    						for (int k = 0; k < inArr1.length(); k++) {
	    							JSONObject tmp2 = (JSONObject) inArr1.get(k);
	    							CoreDashBoardVO ediVO = new CoreDashBoardVO();
									ediVO.setOrganizationId(tmp2.getLong("organizationId"));
									ediVO.setOrganization(tmp2.getString("organization"));
									ediVO.setName(tmp2.getString("name"));
									ediVO.setPositiveCountMain(tmp2.getLong("positiveCountMain"));
									ediVO.setPositiveCountDist(tmp2.getLong("positiveCountDist"));
									ediVO.setNegativCountMain(tmp2.getLong("negativCountMain"));
									ediVO.setNegativCountDist(tmp2.getLong("negativCountDist"));
									ediVO.setNeutralCountMain(tmp2.getLong("neutralCountMain"));
									ediVO.setNeutralCountDist(tmp2.getLong("neutralCountDist"));
									invo.getCoreDashBoardVOList().add(ediVO);
	    						}
	    					}
	    					
	    					vo.getCoreDashBoardVOList1().add(invo);
	    				}
	    			}
	    			
	    			wsResultList.add(vo);
	    		 }
			}
			
		}
		//For Parties Wise Percentages
		if(wsResultList != null && wsResultList.size() > 0){
			setWSResultToUserBase(childActivityMembersMap,wsResultList,finalList);	
			if(finalList != null && finalList.size() > 0){
				for (ChildUserTypeVO chUsrTypVO : finalList) {
					if(chUsrTypVO.getChildUserTypeVOList() != null && chUsrTypVO.getChildUserTypeVOList().size() > 0){
						Long partyTotalCount = 0l;
						for (ChildUserTypeVO childUserTypeVO : chUsrTypVO.getChildUserTypeVOList()) {
							partyTotalCount = partyTotalCount +childUserTypeVO.getCount();
						}
						
						if(partyTotalCount > 0l){
							for (ChildUserTypeVO childUserTypeVO : chUsrTypVO.getChildUserTypeVOList()) {
								if(childUserTypeVO.getOrganizationId() == 872l){
									chUsrTypVO.setNeutralCountMainperd(caclPercantage(childUserTypeVO.getCount(),partyTotalCount));
								}
								childUserTypeVO.setPositiveCountMainPerc(caclPercantage(childUserTypeVO.getCount(),partyTotalCount));
							}
						}
					}
					//For EditionWise Percentages
					if(chUsrTypVO.getChildUserTypeVOList1() != null && chUsrTypVO.getChildUserTypeVOList1().size() > 0){
						
						Long totalMain=0l;
						Long totalDist=0l;
						Long totalPositiveMain=0l;
						Long totalNegativeMain=0l;
						Long totalPositiveDist=0l;
						Long totalNegativeDist=0l;
						
						for (ChildUserTypeVO childUsrVO : chUsrTypVO.getChildUserTypeVOList1()) {
							Long mainTotalCount=0l,distTotalCount=0l;
							mainTotalCount = childUsrVO.getPositiveCountMain()+childUsrVO.getNegativeCountMain();
							distTotalCount = childUsrVO.getPositiveCountDist()+childUsrVO.getNegativeCountDist();
							if(mainTotalCount > 0l){
								childUsrVO.setPositiveCountMainPerc(caclPercantage(childUsrVO.getPositiveCountMain(), mainTotalCount));
								childUsrVO.setNegativeCountDistPerc(caclPercantage(childUsrVO.getNegativeCountMain(), mainTotalCount));
							}
							if(distTotalCount > 0l){
								childUsrVO.setPositiveCountDistPerc(caclPercantage(childUsrVO.getPositiveCountDist(), distTotalCount));
								childUsrVO.setNegativeCountDistPerc(caclPercantage(childUsrVO.getNegativeCountDist(), distTotalCount));
							}
							
							totalMain = totalMain+mainTotalCount;
							totalDist = totalDist+distTotalCount;
							totalPositiveMain = totalPositiveMain + childUsrVO.getPositiveCountMain();
							totalNegativeMain = totalNegativeMain + childUsrVO.getNegativeCountMain();							
							totalPositiveDist = totalPositiveDist + childUsrVO.getPositiveCountDist();
							totalNegativeDist = totalNegativeDist + childUsrVO.getNegativeCountDist();
						}
						
						chUsrTypVO.setNeutralCountMain(totalMain);
						chUsrTypVO.setNeutralCountDist(totalDist);
						chUsrTypVO.setPositiveCountMain(totalPositiveMain);
						chUsrTypVO.setNegativeCountMain(totalNegativeMain);
						chUsrTypVO.setPositiveCountDist(totalPositiveDist);
						chUsrTypVO.setNegativeCountDist(totalNegativeDist);
						
						if(chUsrTypVO.getPositiveCountMain() !=null && chUsrTypVO.getPositiveCountMain().longValue()>0l){
							chUsrTypVO.setPositiveCountMainPerc(caclPercantage(chUsrTypVO.getPositiveCountMain(),chUsrTypVO.getNeutralCountMain()));
						}							
						if(chUsrTypVO.getNegativeCountMain() !=null &&  chUsrTypVO.getNegativeCountMain().longValue()>0l){
							chUsrTypVO.setNegativeCountMainperc(caclPercantage(chUsrTypVO.getNegativeCountMain(),chUsrTypVO.getNeutralCountMain()));
						}
						
						if(chUsrTypVO.getPositiveCountDist() !=null && chUsrTypVO.getPositiveCountDist().longValue()>0l){
							chUsrTypVO.setPositiveCountDistPerc(caclPercantage(chUsrTypVO.getPositiveCountDist(),chUsrTypVO.getNeutralCountDist()));
						}
						
						if(chUsrTypVO.getNegativeCountDist() !=null && chUsrTypVO.getNegativeCountDist().longValue()>0l){
							chUsrTypVO.setNegativeCountDistPerc(caclPercantage(chUsrTypVO.getNegativeCountDist(),chUsrTypVO.getNeutralCountDist()));
						}
						
					}
				}
				
				
				Collections.sort(finalList,positiveSorting1);
			}
		}
	}catch(Exception e){
		LOG.error("Exception raised at getPartyCompareSubLevelMemberDetails", e);
	}
	return finalList;
	}
}
