package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.itgrids.partyanalyst.dao.IActivityDocumentDAO;
import com.itgrids.partyanalyst.dao.IActivityInfoDocumentDAO;
import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityQuestionAnswerDAO;
import com.itgrids.partyanalyst.dao.IActivityQuestionAnswerInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityQuestionnaireDAO;
import com.itgrids.partyanalyst.dao.IActivityScopeDAO;
import com.itgrids.partyanalyst.dao.IAffiliatedMemberDAO;
import com.itgrids.partyanalyst.dto.AffiliatedVo;
import com.itgrids.partyanalyst.dto.DalithaTejamInputVo;
import com.itgrids.partyanalyst.dto.DalithaTejamVO;
import com.itgrids.partyanalyst.dto.EventLocationVO;
import com.itgrids.partyanalyst.service.IDalithaTejamDashBoardService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class DalithaTejamDashBoardService implements IDalithaTejamDashBoardService {
	
	public static Logger Log = Logger.getLogger(CustomVoterGroupAnalysisService.class);
	
	private IActivityInfoDocumentDAO activityInfoDocumentDAO;

	private CommonMethodsUtilService commonMethodsUtilService;

	private IAffiliatedMemberDAO affiliatedMemberDAO;
	
	private IActivityLocationInfoDAO activityLocationInfoDAO;

	private IActivityScopeDAO activityScopeDAO;
	
	private IActivityDocumentDAO activityDocumentDAO;
	
	private IActivityQuestionnaireDAO activityQuestionnaireDAO;
	
	private IActivityQuestionAnswerDAO activityQuestionAnswerDAO;
	
	private CoreDashboardService coreDashboardService;
	
	private IActivityQuestionAnswerInfoDAO activityQuestionAnswerInfoDAO;
	
	
	public IActivityQuestionAnswerInfoDAO getActivityQuestionAnswerInfoDAO() {
		return activityQuestionAnswerInfoDAO;
	}

	public void setActivityQuestionAnswerInfoDAO(
			IActivityQuestionAnswerInfoDAO activityQuestionAnswerInfoDAO) {
		this.activityQuestionAnswerInfoDAO = activityQuestionAnswerInfoDAO;
	}

	public CoreDashboardService getCoreDashboardService() {
		return coreDashboardService;
	}

	public void setCoreDashboardService(CoreDashboardService coreDashboardService) {
		this.coreDashboardService = coreDashboardService;
	}

	public IActivityQuestionAnswerDAO getActivityQuestionAnswerDAO() {
		return activityQuestionAnswerDAO;
	}

	public void setActivityQuestionAnswerDAO(
			IActivityQuestionAnswerDAO activityQuestionAnswerDAO) {
		this.activityQuestionAnswerDAO = activityQuestionAnswerDAO;
	}

	public IActivityQuestionnaireDAO getActivityQuestionnaireDAO() {
		return activityQuestionnaireDAO;
	}

	public void setActivityQuestionnaireDAO(
			IActivityQuestionnaireDAO activityQuestionnaireDAO) {
		this.activityQuestionnaireDAO = activityQuestionnaireDAO;
	}

	public IActivityDocumentDAO getActivityDocumentDAO() {
		return activityDocumentDAO;
	}

	public void setActivityDocumentDAO(IActivityDocumentDAO activityDocumentDAO) {
		this.activityDocumentDAO = activityDocumentDAO;
	}

	public IActivityScopeDAO getActivityScopeDAO() {
		return activityScopeDAO;
	}

	public void setActivityScopeDAO(IActivityScopeDAO activityScopeDAO) {
		this.activityScopeDAO = activityScopeDAO;
	}

	public IActivityLocationInfoDAO getActivityLocationInfoDAO() {
		return activityLocationInfoDAO;
	}

	public void setActivityLocationInfoDAO(
			IActivityLocationInfoDAO activityLocationInfoDAO) {
		this.activityLocationInfoDAO = activityLocationInfoDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public IActivityInfoDocumentDAO getActivityInfoDocumentDAO() {
		return activityInfoDocumentDAO;
	}

	public void setActivityInfoDocumentDAO(
			IActivityInfoDocumentDAO activityInfoDocumentDAO) {
		this.activityInfoDocumentDAO = activityInfoDocumentDAO;
	}
	
	public IAffiliatedMemberDAO getAffiliatedMemberDAO() {
		return affiliatedMemberDAO;
	}

	public void setAffiliatedMemberDAO(IAffiliatedMemberDAO affiliatedMemberDAO) {
		this.affiliatedMemberDAO = affiliatedMemberDAO;
	}

	public List<DalithaTejamVO> getLatestImages(DalithaTejamInputVo inputVo){
		List<DalithaTejamVO> finalList = new ArrayList<DalithaTejamVO>();
		try{
			Date fromDate=null, toDate=null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(inputVo.getFromDate()!= null && inputVo.getToDate() !=null && inputVo.getFromDate().length()>0 && inputVo.getToDate().length() >0){
				fromDate = sdf.parse(inputVo.getFromDate());
				toDate = sdf.parse(inputVo.getToDate());
			}
			List<Object[]> imagesList= activityInfoDocumentDAO.getlatestimages(fromDate,toDate,inputVo.getLocationScopeIds(),inputVo.getLocationValues(),inputVo.getActivityId());
			
			for (Object[] objects : imagesList) {
				DalithaTejamVO dalithaTejamVO=new DalithaTejamVO();
				
				dalithaTejamVO.setImageId(commonMethodsUtilService.getLongValueForObject(objects[0]));
				dalithaTejamVO.setImagePath(commonMethodsUtilService.getStringValueForObject(objects[1]));
				dalithaTejamVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(objects[2]));
				dalithaTejamVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(objects[3]));
				dalithaTejamVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objects[4]));
				dalithaTejamVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(objects[5]));
				dalithaTejamVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(objects[6]));
				dalithaTejamVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(objects[7]));
				dalithaTejamVO.setVillageId(commonMethodsUtilService.getLongValueForObject(objects[8]));
				dalithaTejamVO.setVillageName(commonMethodsUtilService.getStringValueForObject(objects[9]));
				
				finalList.add(dalithaTejamVO);
			}
		}catch(Exception e){
			Log.error("Error Occured in getLatestImages of DalithaTejamDashBoardService "+e);
		}
		return finalList;
	}
	
	@Override
	public List<DalithaTejamVO> getDateWiseCount(DalithaTejamInputVo inputVo){
		List<DalithaTejamVO> finalList = new ArrayList<DalithaTejamVO>();
		try{
			Date fromDate=null, toDate=null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(inputVo.getFromDate()!= null && inputVo.getToDate() !=null && inputVo.getFromDate().length()>0 && inputVo.getToDate().length() >0){
				fromDate = sdf.parse(inputVo.getFromDate());
				toDate = sdf.parse(inputVo.getToDate());
			}
			List<Object[]> registeredCount =new ArrayList<Object[]>();
			registeredCount = affiliatedMemberDAO.getDayWisrRegisteredCount(fromDate,toDate,inputVo.getLocationScopeId(),inputVo.getLocationValue(),"register");
			List<Object[]> loanCount = affiliatedMemberDAO.getDayWisrRegisteredCount(fromDate,toDate,inputVo.getLocationScopeId(),inputVo.getLocationValue(),"loan");
			List<Object[]> visitedViilages = affiliatedMemberDAO.getDayWisrVisitedCount(fromDate,toDate,inputVo.getLocationScopeId(),inputVo.getLocationValue());
			
			if(commonMethodsUtilService.isListOrSetValid(registeredCount)){
				registeredCount.addAll(loanCount);
			}else{
				registeredCount.addAll(loanCount);
			}
			DalithaTejamVO vo = new DalithaTejamVO();
			for (Object[] objects : registeredCount) {
				vo.setTodayRegistred(commonMethodsUtilService.getLongValueForObject(objects[0])+vo.getTodayRegistred());
				vo.setTodayLoanApplied(commonMethodsUtilService.getLongValueForObject(objects[1])+vo.getTodayLoanApplied());
				vo.setDistrictId(commonMethodsUtilService.getLongValueForObject(objects[2]));
			}
			finalList.add(vo);
			for (Object[] objects : visitedViilages) {
				for (DalithaTejamVO finalVo : finalList) {
					if(commonMethodsUtilService.getLongValueForObject(objects[1])==finalVo.getDistrictId()){
						finalVo.setTodayvisted(commonMethodsUtilService.getLongValueForObject(objects[0])+finalVo.getTodayvisted());
					}
				}
				
			}
			
		}catch(Exception e){
			Log.error("Error Occured in getDateWiseCount of DalithaTejamDashBoardService "+e);
		}
		return finalList;
		
	}

	@Override
	public List<EventLocationVO> DalithTejamLocationWiseData(String fromDate,String toDate, Long locationScopeId, Long activityId,
			Long locationValue) {
		
		List<EventLocationVO> finalList = new ArrayList<EventLocationVO>();
		try{
			
			Date startDate=null, endDate=null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(fromDate!= null && toDate !=null && fromDate.length()>0 && toDate.length() >0){
				startDate = sdf.parse(fromDate);
				endDate = sdf.parse(toDate);
			}
			 
			Map<Long,EventLocationVO> locationMap = new HashMap<Long,EventLocationVO>();
			List<Object[]> activityScopeList= activityScopeDAO.getActivityScopeIdByActivityAndLevelId(activityId);
			// 0-total,1-conduct,2-locationUId,3-locationName
			Long activityScopeId=0l;
			for (Object[] objects : activityScopeList) {
				activityScopeId = commonMethodsUtilService.getLongValueForObject(objects[0]);
			}
			List<Object[]> questionList =activityQuestionnaireDAO.getActivityQuestionsOptionsDetails(activityScopeId);
			 //0-qId,1-question,2-optionTypId,3-type,4-hasremark 5-activityOptionId,6-opt 7-qusnaryId,8-mandatry
			Map<Long, EventLocationVO> questionaryMap = new HashMap<Long, EventLocationVO>();
			
			for (Object[] objects : questionList) {
				EventLocationVO questioNVo =questionaryMap.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
				if(questioNVo == null){
					questioNVo = new EventLocationVO();
					EventLocationVO optionVo = new EventLocationVO();
					questioNVo.setQuestionId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					questioNVo.setQuestionName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					questioNVo.setOptionId(commonMethodsUtilService.getLongValueForObject(objects[2]));
					questioNVo.setOptionType(commonMethodsUtilService.getStringValueForObject(objects[3]));
					optionVo.setOptionId(commonMethodsUtilService.getLongValueForObject(objects[5]));
					optionVo.setOptionName(commonMethodsUtilService.getStringValueForObject(objects[6]));
					questioNVo.getOptionList().add(optionVo);
					questionaryMap.put(commonMethodsUtilService.getLongValueForObject(objects[0]), questioNVo);
				}else{
					EventLocationVO optionVo = new EventLocationVO();
					optionVo.setOptionId(commonMethodsUtilService.getLongValueForObject(objects[5]));
					optionVo.setOptionName(commonMethodsUtilService.getStringValueForObject(objects[6]));
					questioNVo.getOptionList().add(optionVo);
				}
				
			}
			
			//totalCount
			List<Object[]> VillageList =activityLocationInfoDAO.getLocationwiseCoductedCount(activityScopeId,locationScopeId,"total",startDate,endDate,locationValue);
			
			//conduct
			List<Object[]> VillageConductedList =activityLocationInfoDAO.getLocationwiseCoductedCount(activityScopeId,locationScopeId,"conduct",startDate,endDate,locationValue);
			
			
			if(commonMethodsUtilService.isListOrSetValid(VillageList)){
				VillageList.addAll(VillageConductedList);
			}
			
			for (Object[] objects : VillageList) {
				EventLocationVO locationVo =locationMap.get(commonMethodsUtilService.getLongValueForObject(objects[2]));
				if(locationVo == null){
					locationVo = new EventLocationVO();
					locationVo.setLocationId(commonMethodsUtilService.getLongValueForObject(objects[2]));
					locationVo.setLocationName(commonMethodsUtilService.getStringValueForObject(objects[3]));
					locationVo.setTotalCount(commonMethodsUtilService.getLongValueForObject(objects[0]));
					locationVo.setConductedCount(commonMethodsUtilService.getLongValueForObject(objects[1]));
					if(locationScopeId == 4l){
						locationVo.setDistrictId(commonMethodsUtilService.getLongValueForObject(objects[4]));
						locationVo.setDistrictName(commonMethodsUtilService.getStringValueForObject(objects[5]));
						
					}
					locationVo.getQuestionList().addAll(getQuestionTemplate(questionaryMap));//getting template
					locationMap.put(commonMethodsUtilService.getLongValueForObject(objects[2]), locationVo);
				}else{
					locationVo.setTotalCount(commonMethodsUtilService.getLongValueForObject(objects[0])+locationVo.getTotalCount());
					locationVo.setConductedCount(commonMethodsUtilService.getLongValueForObject(objects[1])+locationVo.getConductedCount());
				}
				
			}
			
			List<Object[]> vilageanswerList = activityQuestionAnswerDAO.getCountanswereddetails(activityScopeId, locationScopeId,startDate,endDate,locationValue);
			
			//0-qid,1-question,2-optionId,3-optionType,4-optionName 5-optionId,6-count, 7-memcount,8-locationId
			for (Object[] param : vilageanswerList) {
				EventLocationVO Vo = locationMap.get(commonMethodsUtilService.getLongValueForObject(param[8]));
				if(Vo != null){
						if(Vo.getQuestionList() !=null && Vo.getQuestionList().size() >0){
							EventLocationVO mathedVo = getMatchedVo(Vo.getQuestionList(),commonMethodsUtilService.getLongValueForObject(param[0]));
							if(mathedVo != null){
								if(mathedVo.getOptionList() != null && mathedVo.getOptionList().size() >0){
									EventLocationVO mathedOptionVo = getMatchedOPtonVo(mathedVo.getOptionList(),commonMethodsUtilService.getLongValueForObject(param[5]));
									if(mathedOptionVo !=null){
										if(mathedOptionVo.getOptionId().longValue() == 0l && mathedVo.getOptionId()==4l ){
											mathedOptionVo.setCount(mathedOptionVo.getCount()+commonMethodsUtilService.getLongValueForObject(param[7]));
										}else{
											mathedOptionVo.setCount(mathedOptionVo.getCount()+commonMethodsUtilService.getLongValueForObject(param[6]));
										}
									}
								}
							}
						}
					}
			}
			List<Object[]> ivrstatusData= activityLocationInfoDAO.getIvrStatusForLocation(activityScopeId,locationScopeId,startDate,endDate,locationValue);
			//0-count,1-status,2-question 3-qid,4-locationId
			for (Object[] objects : ivrstatusData) {
				EventLocationVO locationVo =locationMap.get(commonMethodsUtilService.getLongValueForObject(objects[4]));
				if(locationVo != null){
					EventLocationVO mathedQuestionVo = getMatchedVo(locationVo.getQuestionList(),commonMethodsUtilService.getLongValueForObject(objects[3]));
					if(mathedQuestionVo != null){
						if(mathedQuestionVo.getOptionList() != null && mathedQuestionVo.getOptionList().size() >0){
							Long option =null;
							if(commonMethodsUtilService.getStringValueForObject(objects[1]).trim().equalsIgnoreCase("Y")){
								option=1l;
							}else if(commonMethodsUtilService.getStringValueForObject(objects[1]).trim().equalsIgnoreCase("N")){
								option =2l;
							}
							EventLocationVO mathedOptionVo = getMatchedOPtonVo(mathedQuestionVo.getOptionList(),option);
							if(mathedOptionVo !=null){
								mathedOptionVo.setCount(mathedOptionVo.getCount()+commonMethodsUtilService.getLongValueForObject(objects[0]));
								mathedQuestionVo.setCount(mathedQuestionVo.getCount()+commonMethodsUtilService.getLongValueForObject(objects[0]));
							}
						}
					}else{
						mathedQuestionVo = new EventLocationVO();
						EventLocationVO optionVo1 =new EventLocationVO();
						EventLocationVO optionVo2 =new EventLocationVO();
						mathedQuestionVo.setQuestionId(commonMethodsUtilService.getLongValueForObject(objects[3]));
						mathedQuestionVo.setQuestionName(commonMethodsUtilService.getStringValueForObject(objects[2]));
						optionVo1.setOptionId(1l);
						optionVo1.setOptionName("Yes");
						optionVo2.setOptionId(2l);
						optionVo2.setOptionName("No");
						if(commonMethodsUtilService.getStringValueForObject(objects[1]).trim().equalsIgnoreCase("Y")){
							optionVo1.setCount(commonMethodsUtilService.getLongValueForObject(objects[0]));
						}else if(commonMethodsUtilService.getStringValueForObject(objects[1]).trim().equalsIgnoreCase("N")){
							optionVo2.setCount(commonMethodsUtilService.getLongValueForObject(objects[0]));
						}
						mathedQuestionVo.getOptionList().add(optionVo1);
						mathedQuestionVo.getOptionList().add(optionVo2);
						locationVo.getQuestionList().add(mathedQuestionVo);
					}
				}
			}
			//inchargeAttendCount
			if(locationScopeId ==4l){
				List<Object[]> inchargeMLAUniqueCountList= activityQuestionAnswerInfoDAO.getInchargeMLAAttendCount(activityScopeId,locationScopeId,startDate,endDate);
			
				for (Object[] objects : inchargeMLAUniqueCountList) {
					EventLocationVO locationVo =locationMap.get(commonMethodsUtilService.getLongValueForObject(objects[1]));
					if(locationVo != null){
						locationVo.setCount(commonMethodsUtilService.getLongValueForObject(objects[0]));
					}
				}
			}
			//newsCount
			 ClientConfig clientConfig = new DefaultClientConfig();
		     clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
	         WebResource webResource = client.resource("http://localhost:8446/CommunityNewsPortal/webservice/getCountDaysBasedOnCategory/1156/"+fromDate+"/"+toDate+"/"+locationScopeId);
	   
	         ClientResponse response = webResource.accept("application/json").type("application/json").get(ClientResponse.class);
	          if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
				  if(output != null && !output.isEmpty() && output.length()>0){
					  JSONArray finalArray = new JSONArray(output);
					  if(finalArray!=null && finalArray.length()>0){
		 	    			for(int k=0;k<finalArray.length();k++){
		 	    				JSONObject obj = (JSONObject) finalArray.get(k);
		 	    				Long locationId=0l;
		 	    				if(obj.has("id") && obj.getLong("id")==334l){
		 	    					locationId = 	517l;
		 	    				}else{
		 	    					locationId = commonMethodsUtilService.getLongValueForObject(obj.getLong("id"));
		 	    				}
		 	    				EventLocationVO locationVo =locationMap.get(locationId);
		 	    				if(locationVo != null){
		 	    					locationVo.setNewsCount(locationVo.getNewsCount()+commonMethodsUtilService.getLongValueForObject(obj.getLong("count")));
		 	    				}
		 	    			}
					  }
				  }
		   
	 	      }
	          //imageCount
			List<Object[]> imagecountList = activityDocumentDAO.getLocationWiseCount(activityScopeId,locationScopeId,startDate,endDate);
			for (Object[] objects : imagecountList) {
				EventLocationVO locationVo =locationMap.get(commonMethodsUtilService.getLongValueForObject(objects[1]));
				if(locationVo != null){
					locationVo.setImageCount(commonMethodsUtilService.getLongValueForObject(objects[0]));
				}
			}
			if( locationScopeId !=2l){
				 List<AffiliatedVo> activityParticipatentCount =coreDashboardService.getAffilliatedMemberCount(fromDate,toDate,activityId,locationScopeId,"table");
				for (AffiliatedVo affiliatedVo : activityParticipatentCount) {
					EventLocationVO locationVo =locationMap.get(affiliatedVo.getLocationId());
					if(locationVo != null){
						locationVo.setTotalPopulation(affiliatedVo.getTotalMembers());
						locationVo.setCoveredPopulation(affiliatedVo.getTotalCovered());
						locationVo.setTotalRegistered(affiliatedVo.getTotalRegistration());
						locationVo.setTotalLoanApplied(affiliatedVo.getTotalLoanApplied());
					}
					
				}
			}else{
				locationScopeId=3l;
				List<AffiliatedVo> activityParticipatentCount =coreDashboardService.getAffilliatedMemberCount(fromDate,toDate,activityId,locationScopeId,"table");
				for (AffiliatedVo affiliatedVo : activityParticipatentCount) {
					EventLocationVO locationVo =locationMap.get(1l);
					if(locationVo != null){
						locationVo.setTotalPopulation(locationVo.getTotalPopulation()+affiliatedVo.getTotalMembers());
						locationVo.setCoveredPopulation(locationVo.getConductedCount()+affiliatedVo.getTotalCovered());
						locationVo.setTotalRegistered(locationVo.getTotalRegistered()+affiliatedVo.getTotalRegistration());
						locationVo.setTotalLoanApplied(locationVo.getTotalPopulation()+affiliatedVo.getTotalLoanApplied());
					}
					
				}
			}
	 		for (Long lcoationId : locationMap.keySet()) {
				EventLocationVO vo=  locationMap.get(lcoationId);
				if(vo != null && vo.getTotalCount()>0 ){
					vo.setPercentage((double) ((vo.getConductedCount()/vo.getTotalCount())*100));
					for (EventLocationVO questionVO : vo.getQuestionList()) {
						if(questionVO.getQuestionName().trim().contains("Average Public Attended?")){
							for (EventLocationVO optionVo : questionVO.getOptionList()) {
								if(vo.getConductedCount() !=null && vo.getConductedCount()>0){
									optionVo.setPercentage((double) (optionVo.getCount()/vo.getConductedCount()));
								}
							}
						}else if(questionVO.getQuestionName().trim().contains("Average Gramma Sabha Conducted Time (Duration)?")){
							for (EventLocationVO optionVo : questionVO.getOptionList()) {
								if(vo.getConductedCount() !=null && vo.getConductedCount()>0){
									optionVo.setPercentage((double) (optionVo.getCount()/(60*vo.getConductedCount())));
								}
							}
						}
					}
					finalList.add(vo);
				}
			}
	 		for (EventLocationVO finalVo : finalList) {
	 			Collections.sort(finalVo.getQuestionList(), new Comparator<EventLocationVO>(){
	 	 		   public int compare(EventLocationVO o1, EventLocationVO o2){
	 	 		      return (int) (o1.getQuestionId() - o2.getQuestionId());
	 	 		   }
	 	 		});
				
			}
	 		
		}catch(Exception e){
			 Log.error("Error occured at activitiesLocationWiseData() in setAttendedDataToMap class",e); 
		}
		return finalList;
		
	}
	
	private EventLocationVO getMatchedOPtonVo(List<EventLocationVO> optionList, Long optionId) {
		try {
			if (optionList == null || optionList.size() == 0 || optionId == null)
				return null;
			for (EventLocationVO vo : optionList) {
				if (vo.getOptionId().longValue() == optionId.longValue())
					return vo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public EventLocationVO getMatchedVo(List<EventLocationVO> returnList,Long id){
		try {
			if (returnList == null || returnList.size() == 0 || id == null)
				return null;
			for (EventLocationVO vo : returnList) {
				if (vo.getQuestionId().longValue() == id.longValue())
					return vo;
			}
		}
	  catch(Exception e)
	  {
	    e.printStackTrace();
	  }
	  return null;
	}
	
	public List<EventLocationVO> getQuestionTemplate(Map<Long,EventLocationVO> questionMap) {
		  List<EventLocationVO> questionList = new ArrayList<EventLocationVO>();
			try {
				
				EventLocationVO mathedQuestionVo = new EventLocationVO();
				EventLocationVO optionVo1 =new EventLocationVO();
				EventLocationVO optionVo2 =new EventLocationVO();
				mathedQuestionVo.setQuestionId(23l);
				mathedQuestionVo.setQuestionName("IVR Call Status (Conducted or Not)?");
				optionVo1.setOptionId(1l);
				optionVo1.setOptionName("Yes");
				optionVo2.setOptionId(2l);
				optionVo2.setOptionName("No");
				
				mathedQuestionVo.getOptionList().add(optionVo1);
				mathedQuestionVo.getOptionList().add(optionVo2);
				questionList.add(mathedQuestionVo);
				
				for (Entry<Long, EventLocationVO> questionEntry : questionMap.entrySet()) {
					EventLocationVO questioNVo = new EventLocationVO();
					questioNVo.setQuestionId(questionEntry.getValue().getQuestionId());
					questioNVo.setQuestionName(questionEntry.getValue().getQuestionName());
					questioNVo.setOptionId(questionEntry.getValue().getOptionId());
					questioNVo.setOptionType(questionEntry.getValue().getOptionType());
					if (questionEntry.getValue().getOptionList() != null) {
						for (EventLocationVO VO : questionEntry.getValue().getOptionList()) {
							EventLocationVO optionVo = new EventLocationVO();
							optionVo.setOptionId(VO.getOptionId());
							optionVo.setOptionName(VO.getOptionName());
							questioNVo.getOptionList().add(optionVo);
						}
					}
					questionList.add(questioNVo);
				}
			} catch (Exception e) {
			   Log.error("Error occured at getQuestionTemplate() in setAttendedDataToMap class",e); 
		   }
		   return questionList;
	  }
}
