package com.itgrids.partyanalyst.exceptionalReport.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
import com.itgrids.partyanalyst.dao.IBoothInchargeCommitteeDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IKaizalaAnswerInfoDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingStatusDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampDetailsInfoDAO;
import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.CommitteeDataVO;
import com.itgrids.partyanalyst.dto.ConsolidatedExceptionalReportVO;
import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.exceptionalReport.service.IConsolidatedExceptionalReportService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.Util;

public class ConsolidatedExceptionalReportService implements IConsolidatedExceptionalReportService {

	private final static Logger LOG = Logger.getLogger(ConsolidatedExceptionalReportService.class);
	
	private CommonMethodsUtilService commonMethodsUtilService;
	private IPartyMeetingStatusDAO partyMeetingStatusDAO;
	private ITdpCommitteeDAO tdpCommitteeDAO;
	private IBoothInchargeCommitteeDAO boothInchargeCommitteeDAO;
	private IConstituencyDAO constituencyDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IKaizalaAnswerInfoDAO kaizalaAnswerInfoDAO;
	private IActivityLocationInfoDAO activityLocationInfoDAO;
	private ITrainingCampDetailsInfoDAO trainingCampDetailsInfoDAO;
	private ITrainingCampAttendanceDAO trainingCampAttendanceDAO;
	
	
	public ITrainingCampAttendanceDAO getTrainingCampAttendanceDAO() {
		return trainingCampAttendanceDAO;
	}
	public void setTrainingCampAttendanceDAO(
			ITrainingCampAttendanceDAO trainingCampAttendanceDAO) {
		this.trainingCampAttendanceDAO = trainingCampAttendanceDAO;
	}
	public ITrainingCampDetailsInfoDAO getTrainingCampDetailsInfoDAO() {
		return trainingCampDetailsInfoDAO;
	}
	public void setTrainingCampDetailsInfoDAO(
			ITrainingCampDetailsInfoDAO trainingCampDetailsInfoDAO) {
		this.trainingCampDetailsInfoDAO = trainingCampDetailsInfoDAO;
	}
	public IActivityLocationInfoDAO getActivityLocationInfoDAO() {
		return activityLocationInfoDAO;
	}
	public void setActivityLocationInfoDAO(
			IActivityLocationInfoDAO activityLocationInfoDAO) {
		this.activityLocationInfoDAO = activityLocationInfoDAO;
	}
	public IKaizalaAnswerInfoDAO getKaizalaAnswerInfoDAO() {
		return kaizalaAnswerInfoDAO;
	}
	public void setKaizalaAnswerInfoDAO(IKaizalaAnswerInfoDAO kaizalaAnswerInfoDAO) {
		this.kaizalaAnswerInfoDAO = kaizalaAnswerInfoDAO;
	}
	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}
	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	public void setTdpCommitteeDAO(ITdpCommitteeDAO tdpCommitteeDAO) {
		this.tdpCommitteeDAO = tdpCommitteeDAO;
	}
	public void setBoothInchargeCommitteeDAO(IBoothInchargeCommitteeDAO boothInchargeCommitteeDAO) {
		this.boothInchargeCommitteeDAO = boothInchargeCommitteeDAO;
	}
	public void setCommonMethodsUtilService(CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
    public void setPartyMeetingStatusDAO(IPartyMeetingStatusDAO partyMeetingStatusDAO) {
		this.partyMeetingStatusDAO = partyMeetingStatusDAO;
	}

    
   public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public static Comparator<ConsolidatedExceptionalReportVO> meetingDecendingCountWiseSorting = new Comparator<ConsolidatedExceptionalReportVO>() {
		     	public int compare(ConsolidatedExceptionalReportVO location2, ConsolidatedExceptionalReportVO location1) {
		     	Double per2 = location2.getPercentage();
		     	Double per1 = location1.getPercentage();
		     	return per1.compareTo(per2);
		     	}
	};
	public static Comparator<ConsolidatedExceptionalReportVO> consolidatedDecendingCountWiseSorting = new Comparator<ConsolidatedExceptionalReportVO>() {
     	public int compare(ConsolidatedExceptionalReportVO location2, ConsolidatedExceptionalReportVO location1) {
     	Long per2 = location2.getSortNo();
     	Long per1 = location1.getSortNo();
     	return per1.compareTo(per2);
     	}
};
	public void calculatePercentage(Map<Long, ConsolidatedExceptionalReportVO> locationMap,ConsolidatedExceptionalReportVO resultVO,String resultType) {
		try {
			if (locationMap != null && locationMap.size() > 0) {
				for (Entry<Long, ConsolidatedExceptionalReportVO> entry : locationMap.entrySet()) {
					if (!resultType.equalsIgnoreCase("constituency")) {
						// calculating overall conducted meeting count
						resultVO.setConductedCount(resultVO.getConductedCount() + entry.getValue().getConductedCount());
						// calculating overall not conducted meeting count
						resultVO.setNotConductedCount(resultVO.getNotConductedCount() + entry.getValue().getNotConductedCount());
						//calculating mayBe count 
						resultVO.setMayBeCount(resultVO.getMayBeCount() + entry.getValue().getMayBeCount());
						// calculating overall meeting count
						resultVO.setTotalCount(resultVO.getTotalCount()+ entry.getValue().getTotalCount());	
					}
						if(commonMethodsUtilService.isListOrSetValid(entry.getValue().getSubList1())){
							for (ConsolidatedExceptionalReportVO meetingLvl: entry.getValue().getSubList1()) {
								meetingLvl.setPercentage(Util.calculatePercantage(meetingLvl.getNotConductedCount(), meetingLvl.getTotalCount()));
							}
						}
					
					
					entry.getValue().setPercentage(Util.calculatePercantage(entry.getValue().getNotConductedCount(), entry.getValue().getTotalCount()));
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occurred  at calculatePercentage() in ConsolidatedExceptionalReportService class",e);
		}
	}
	public  Map<Long,ConsolidatedExceptionalReportVO> setObjectDataToMap(List<Object[]> allConst,Map<Long,ConsolidatedExceptionalReportVO> map){
		try {
			if(commonMethodsUtilService.isListOrSetValid(allConst)){
				map = new HashMap<Long, ConsolidatedExceptionalReportVO>();
				for (Object[] param : allConst) {
					if(commonMethodsUtilService.getLongValueForObject(param[0]) != 0l){
					ConsolidatedExceptionalReportVO locationVO= map.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						if(locationVO==null){
							locationVO = new ConsolidatedExceptionalReportVO();
							locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
							locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
							locationVO.setSubList1(getMeetingLevelsList());
							map.put(commonMethodsUtilService.getLongValueForObject(param[0]), locationVO);
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occurred  at setObjectDataToMap() in ConsolidatedExceptionalReportService class",e);
		}
		return map;
	}
	public ConsolidatedExceptionalReportVO getOverAllConsolidatedViewDetails(InputVO inputVO){
		ConsolidatedExceptionalReportVO resultVO = new ConsolidatedExceptionalReportVO();
		try {
			 Map<Long,ConsolidatedExceptionalReportVO> locationMap =null;
			 Map<Long,ConsolidatedExceptionalReportVO> constituenyDtlsMap =null;
			 List<Object[]> allConst = constituencyDAO.getAllConstituencys();
			 List<Object[]> allParl = delimitationConstituencyAssemblyDetailsDAO.getAllParliamentConstituencyByStateId(Arrays.asList(IConstants.AP_NEW_DISTRICTS_IDS));
			 constituenyDtlsMap = setObjectDataToMap(allConst,constituenyDtlsMap);
			 locationMap = setObjectDataToMap(allParl,locationMap);
			 
			 //Meetings Details
			 getConsolidatedPartyMeetingExceptionReportMeetingLevelWise(inputVO,locationMap,"parliament");
			 getConsolidatedPartyMeetingExceptionReportMeetingLevelWise(inputVO,constituenyDtlsMap,"constituency");
			 
			 //Committee Details
			 getCommiteeOverviewPerformanceDetails(inputVO,locationMap,"parliament","");
			 getCommiteeOverviewPerformanceDetails(inputVO,constituenyDtlsMap,"constituency","");
			 
			 //Affiliated Committee Details
			 getCommiteeOverviewPerformanceDetails(inputVO,locationMap,"parliament","affiliated");
			 getCommiteeOverviewPerformanceDetails(inputVO,constituenyDtlsMap,"constituency","affiliated");
			 
			 //Booth Committee Details
			 getBoothInchargeCommitteePerformanceDetails(inputVO,locationMap,"parliament");
			 getBoothInchargeCommitteePerformanceDetails(inputVO,constituenyDtlsMap,"constituency");
		
			 //Kaizala Details
			 getConstituencyWisePoorPerformance(1l,"parliament",locationMap);
			 getConstituencyWisePoorPerformance(1l,"constituency",constituenyDtlsMap);
			 
			 //Dalitha tejam details
			 getActivityPerformanceDetailsLocationWise(inputVO,locationMap,"parliament");
			 getActivityPerformanceDetailsLocationWise(inputVO,constituenyDtlsMap,"constituency");
			 
			 //Training Camp Parliament Details 
			 getListOfParliamentsWithPoorPerformance(inputVO,locationMap);
			 resultVO.setSubList2(new ArrayList<ConsolidatedExceptionalReportVO>(locationMap.values()));
				//sorting list
				if (commonMethodsUtilService.isListOrSetValid(resultVO.getSubList2())) {
					java.util.Collections.sort(resultVO.getSubList2(), consolidatedDecendingCountWiseSorting);
					/*for (ConsolidatedExceptionalReportVO lovationVO : resultVO.getSubList2()) {
						if(commonMethodsUtilService.isListOrSetValid(lovationVO.getSubList1())){
							java.util.Collections.sort(lovationVO.getSubList1(), meetingDecendingCountWiseSorting);
						}
					}*/
				}
				//calculatePercentage(constituenyDtlsMap, resultVO,"constituency");
				resultVO.setSubList1(new ArrayList<ConsolidatedExceptionalReportVO>(constituenyDtlsMap.values()));
				//sorting list
				if (commonMethodsUtilService.isListOrSetValid(resultVO.getSubList1())) {
					java.util.Collections.sort(resultVO.getSubList1(), consolidatedDecendingCountWiseSorting);
					/*for (ConsolidatedExceptionalReportVO lovationVO : resultVO.getSubList1()) {
						if(commonMethodsUtilService.isListOrSetValid(lovationVO.getSubList1())){
							java.util.Collections.sort(lovationVO.getSubList1(), meetingDecendingCountWiseSorting);
						}
					}*/
				}
		} catch (Exception e) {
			LOG.error("Exception occurred  at getOverAllConsolidatedViewDetails() in ConsolidatedExceptionalReportService class",e);
		}
		return resultVO;
	}
	/**
	   * @param InputVO inputVO
	   * @return Map<Long,ConsolidatedExceptionalReportVO> 
	   * @author Hymavathi 
	   * @Description :This Service Method is used to get party meeting location level wise report. 
	   * @Date 26-FEB-2018
	   */
	public Map<Long,ConsolidatedExceptionalReportVO> getConsolidatedPartyMeetingExceptionReportMeetingLevelWise(InputVO inputVO, 
			Map<Long,ConsolidatedExceptionalReportVO> locationMap,String resultType) {
		//ConsolidatedExceptionalReportVO resultVO = new ConsolidatedExceptionalReportVO();
		try {
			
			List<Date> dateList = Util.getDates(inputVO.getFromDateStr(), inputVO.getToDateStr(), new SimpleDateFormat("dd/MM/yyyy"));
			
			inputVO.setFromDate(dateList.get(0));
			inputVO.setToDate(dateList.get(1));
			List<Long> typeIds = new ArrayList<Long>();
			List<Long> meetingLvlIds = new ArrayList<Long>();
			//parliament wise data
			//inputVO.setResultType("parliament");
			inputVO.setLocationLevel("Constituency level Meetings");
			typeIds.add(3l);
			inputVO.setPartyMeetingtypeIds(typeIds);
			meetingLvlIds.clear();
			meetingLvlIds.add(3l);
			inputVO.setPartyMeetingLevelIds(meetingLvlIds);
			if(resultType != null && resultType.equalsIgnoreCase("parliament")){
				List<Object[]>  parlConStList = partyMeetingStatusDAO.getPartyMeetingStatusWiseCount(inputVO,resultType);
				locationMap = getConsolidatedLocationWiseMeetingCounductedDtls(parlConStList, inputVO,locationMap,resultType);
			//inputVO.setResultType("constituency");
			}else if(resultType != null && resultType.equalsIgnoreCase("constituency")){
				List<Object[]>  constList = partyMeetingStatusDAO.getPartyMeetingStatusWiseCount(inputVO,resultType);
				locationMap = getConsolidatedLocationWiseMeetingCounductedDtls(constList, inputVO,locationMap,"constituency");
			}
			
			inputVO.setLocationLevel("Mandal/Town/Division level Meetings");
			typeIds.clear();
			typeIds.add(15l);
			inputVO.setPartyMeetingtypeIds(typeIds);
			meetingLvlIds.clear();
			meetingLvlIds.add(4l);
			meetingLvlIds.add(5l);
			meetingLvlIds.add(6l);
			inputVO.setPartyMeetingLevelIds(meetingLvlIds);
			if(resultType != null && resultType.equalsIgnoreCase("parliament")){
				List<Object[]>  parlMandalList = partyMeetingStatusDAO.getPartyMeetingStatusWiseCount(inputVO,resultType);
				locationMap = getConsolidatedLocationWiseMeetingCounductedDtls(parlMandalList, inputVO,locationMap,resultType);
			}else if(resultType != null && resultType.equalsIgnoreCase("constituency")){
				List<Object[]>  mandalList = partyMeetingStatusDAO.getPartyMeetingStatusWiseCount(inputVO,resultType);
				locationMap = getConsolidatedLocationWiseMeetingCounductedDtls(mandalList, inputVO,locationMap,resultType);
			}
			inputVO.setLocationLevel("Village/Ward level Meetings");
			typeIds.clear();
			typeIds.add(14l);
			inputVO.setPartyMeetingtypeIds(typeIds);
			meetingLvlIds.clear();
			meetingLvlIds.add(7l);
			meetingLvlIds.add(8l);
			inputVO.setPartyMeetingLevelIds(meetingLvlIds);
			if(resultType != null && resultType.equalsIgnoreCase("parliament")){
				List<Object[]>  parlVillageList = partyMeetingStatusDAO.getPartyMeetingStatusWiseCount(inputVO,resultType);
				locationMap = getConsolidatedLocationWiseMeetingCounductedDtls(parlVillageList, inputVO,locationMap,resultType);
			}else if(resultType != null && resultType.equalsIgnoreCase("constituency")){
				List<Object[]>  villageList = partyMeetingStatusDAO.getPartyMeetingStatusWiseCount(inputVO,resultType);
				locationMap = getConsolidatedLocationWiseMeetingCounductedDtls(villageList, inputVO,locationMap,resultType);
			}
			// calculatePercentage(locationMap, resultVO,"parliament");
			/*resultVO.setSubList2(new ArrayList<ConsolidatedExceptionalReportVO>(locationMap.values()));
			//sorting list
			if (commonMethodsUtilService.isListOrSetValid(resultVO.getSubList2())) {
				for (ConsolidatedExceptionalReportVO lovationVO : resultVO.getSubList2()) {
					if(commonMethodsUtilService.isListOrSetValid(lovationVO.getSubList1())){
						java.util.Collections.sort(lovationVO.getSubList1(), meetingDecendingCountWiseSorting);
					}
				}
			}
			//calculatePercentage(constituenyDtlsMap, resultVO,"constituency");
			resultVO.setSubList1(new ArrayList<ConsolidatedExceptionalReportVO>(constituenyDtlsMap.values()));
			//sorting list
			if (commonMethodsUtilService.isListOrSetValid(resultVO.getSubList1())) {
				for (ConsolidatedExceptionalReportVO lovationVO : resultVO.getSubList1()) {
					if(commonMethodsUtilService.isListOrSetValid(lovationVO.getSubList1())){
						java.util.Collections.sort(lovationVO.getSubList1(), meetingDecendingCountWiseSorting);
					}
				}
			}*/
			//calculating overall percentage
			//resultVO.setConductedPercentage(Util.calculatePercantage(resultVO.getConductedCount(),resultVO.getTotalCount()));
			//resultVO.setNotConductedPercentage(Util.calculatePercantage(resultVO.getNotConductedCount(),resultVO.getTotalCount()));
		} catch (Exception e) {
			LOG.error("Exception occurred  at getPartyMeetingExceptionReportMeetingLevelWise() in PartyMeetingExceptionalReportService class",e);
		}
		return locationMap;
	}
	
	 public Map<Long,ConsolidatedExceptionalReportVO> getConsolidatedLocationWiseMeetingCounductedDtls(List<Object[]> objList,InputVO inputVO,
			 Map<Long,ConsolidatedExceptionalReportVO> locationMap,String resultType) {
   	 
   	  try {
   		  
   		   if (objList != null && objList.size() > 0 ) {
   			inputVO.setResultType(resultType);
   			   for (Object[] param : objList) {
   				   
   				   Long locationId = 0l;
   				   String partyMeetingStatus = "";
   				   Long totalMeetingCnt = 0l;
					   if (inputVO.getResultType().equalsIgnoreCase("parliament")) {
						   locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
						   partyMeetingStatus = commonMethodsUtilService.getStringValueForObject(param[2]);
						   totalMeetingCnt = commonMethodsUtilService.getLongValueForObject(param[3]);
					   } else {
						   locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
						   partyMeetingStatus = commonMethodsUtilService.getStringValueForObject(param[6]);
						   totalMeetingCnt = commonMethodsUtilService.getLongValueForObject(param[7]);
					   }
					   //in the case of constituency level meeting we are sending only not conducted constituency name
					   if (inputVO.getLocationLevel().equalsIgnoreCase("constituency") && inputVO.getResultType().equalsIgnoreCase("constituency")) {
   					   if (!partyMeetingStatus.equalsIgnoreCase("N")) {
   						   continue;
   					   }
   				   }
					   if (!locationMap.containsKey(locationId)) {
						   ConsolidatedExceptionalReportVO locationVO = new ConsolidatedExceptionalReportVO();
						   //locationVO.setAddressVO(getAddressDetails(param, inputVO.getResultType()));
						   locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
						   locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
						  // locationVO.setSubList1(getMeetingLevelsList());
						   locationMap.put(locationVO.getLocationId(), locationVO);
					   }
					   ConsolidatedExceptionalReportVO locationVO = locationMap.get(locationId);
					   if (locationVO != null ) {
						   locationVO.setAddressVO(getAddressDetails(param, inputVO.getResultType(),"meeting"));
						   ConsolidatedExceptionalReportVO meetingLvlVO = getMatchedVO(inputVO.getLocationLevel(),locationVO.getSubList1()); 
						  if(meetingLvlVO != null){
							   meetingLvlVO.setTotalCount(meetingLvlVO.getTotalCount()+totalMeetingCnt);
								   if (partyMeetingStatus.equalsIgnoreCase("Y")) {
									   meetingLvlVO.setConductedCount(meetingLvlVO.getConductedCount()+totalMeetingCnt); 
									} else if (partyMeetingStatus.equalsIgnoreCase("N") || partyMeetingStatus.equalsIgnoreCase("NU")) {
										meetingLvlVO.setNotConductedCount(meetingLvlVO.getNotConductedCount()+totalMeetingCnt);
									}else if (partyMeetingStatus.equalsIgnoreCase("M")) {
										meetingLvlVO.setMayBeCount(locationVO.getMayBeCount()+totalMeetingCnt); 
									 }
								   meetingLvlVO.setPercentage(Util.calculatePercantage(meetingLvlVO.getNotConductedCount(), meetingLvlVO.getTotalCount()));
								   if(meetingLvlVO.getPercentage() != null && meetingLvlVO.getPercentage() >0){
									   locationVO.setSortNo(locationVO.getSortNo() +1l);
								   }
								   locationVO.setPercentage1(locationVO.getPercentage1()+meetingLvlVO.getPercentage());
							   locationVO.setTotalCount(locationVO.getTotalCount()+totalMeetingCnt);
								   if (partyMeetingStatus.equalsIgnoreCase("Y")) {
									   locationVO.setConductedCount(locationVO.getConductedCount()+totalMeetingCnt); 
									} else if (partyMeetingStatus.equalsIgnoreCase("N") || partyMeetingStatus.equalsIgnoreCase("NU")) {
									   locationVO.setNotConductedCount(locationVO.getNotConductedCount()+totalMeetingCnt);
									}else if (partyMeetingStatus.equalsIgnoreCase("M")) {
										locationVO.setMayBeCount(locationVO.getMayBeCount()+totalMeetingCnt); 
								    }
						   }
					   }
				   }
   		   }
   		  
   	  } catch (Exception e) {
   		  LOG.error("Exception occurred  at getLocationWiseMeetingCounductedDtls() in PartyMeetingExceptionalReportService class",e);
   	  }
   	  return locationMap;
    }
	 
	 public List<ConsolidatedExceptionalReportVO> getMeetingLevelsList(){
		 List<ConsolidatedExceptionalReportVO> list = new ArrayList<ConsolidatedExceptionalReportVO>();
		 try {
			 ConsolidatedExceptionalReportVO constituencyLvl = new ConsolidatedExceptionalReportVO();
			 constituencyLvl.setLocationId(3l);
			 constituencyLvl.setLocationName("Constituency level Meetings");
			 list.add(constituencyLvl);
			 ConsolidatedExceptionalReportVO mandalTownDivLvl = new ConsolidatedExceptionalReportVO();
			 mandalTownDivLvl.setLocationId(4l);
			 mandalTownDivLvl.setLocationName("Mandal/Town/Division level Meetings");
			 list.add(mandalTownDivLvl);
			 ConsolidatedExceptionalReportVO villageWardLvl = new ConsolidatedExceptionalReportVO();
			 villageWardLvl.setLocationId(7l);
			 villageWardLvl.setLocationName("Village/Ward level Meetings");
			 list.add(villageWardLvl);
			 ConsolidatedExceptionalReportVO mandalTownDivLvlComm = new ConsolidatedExceptionalReportVO();
			 mandalTownDivLvlComm.setLocationId(5l);
			 mandalTownDivLvlComm.setLocationName("Mandal/Town/Division level Committees");
			 list.add(mandalTownDivLvlComm);
			 ConsolidatedExceptionalReportVO villageWardLvlCommi = new ConsolidatedExceptionalReportVO();
			 villageWardLvlCommi.setLocationId(4l);
			 villageWardLvlCommi.setLocationName("Village/Ward level Committees");
			 list.add(villageWardLvlCommi);
			 ConsolidatedExceptionalReportVO affiliatedComm = new ConsolidatedExceptionalReportVO();
			 affiliatedComm.setLocationId(7l);
			 affiliatedComm.setLocationName("Affiliated Committees");
			 list.add(affiliatedComm);
			 ConsolidatedExceptionalReportVO boothComm = new ConsolidatedExceptionalReportVO();
			 boothComm.setLocationId(7l);
			 boothComm.setLocationName("Booth Committees");
			 list.add(boothComm);
			 ConsolidatedExceptionalReportVO dalithaTejam = new ConsolidatedExceptionalReportVO();
			 dalithaTejam.setLocationId(7l);
			 dalithaTejam.setLocationName("Dalitha Tejam");
			 list.add(dalithaTejam);
			 ConsolidatedExceptionalReportVO kaizala = new ConsolidatedExceptionalReportVO();
			 kaizala.setLocationId(7l);
			 kaizala.setLocationName("Kaizala");
			 list.add(kaizala);
			 ConsolidatedExceptionalReportVO trainingCamp = new ConsolidatedExceptionalReportVO();
			 trainingCamp.setLocationId(7l);
			 trainingCamp.setLocationName("Training Camp");
			 list.add(trainingCamp);
			 
		} catch (Exception e) {
			LOG.error("Exception occurred  at getMeetingLevelsList() in PartyMeetingExceptionalReportService class",e);
		}
		 return list;
	 }
	 public ConsolidatedExceptionalReportVO getMatchedVO(String levelName,List<ConsolidatedExceptionalReportVO> list){
		 ConsolidatedExceptionalReportVO returnVO = null;
			try {
				if(levelName != null &&  commonMethodsUtilService.isListOrSetValid(list)){
					for (ConsolidatedExceptionalReportVO vo : list) {
						if(vo.getLocationName() != null && vo.getLocationName().equalsIgnoreCase(levelName))
							return vo;
					}
				}
			} catch (Exception e) {
				LOG.error("Exception raised into PmRequestDetailsService of getMatchedVO",e);
			}
			return returnVO;
		}
		
		/**
		   * @param InputVO inputVO
		   * @return CommitteeDataVO 
		   * @author Hymavathi G 
		   * @Description :This Service Method is used to get committee performance details. 
		   * @Date 27-FEB-2018
		   */
		public Map<Long,ConsolidatedExceptionalReportVO> getCommiteeOverviewPerformanceDetails(InputVO inputVO,
				Map<Long,ConsolidatedExceptionalReportVO> locationMap,String locationLevel,String committeeType) {
			//CommitteeDataVO resultVO = new CommitteeDataVO();
			 try {
				  //inputVO.setTdpCommitteeLevelIds(getTdpCommitteeLevelIds(inputVO));//getting tdpCommitee level
				  //preparing parliament wise committee performance data
				 if(committeeType != null && !committeeType.equalsIgnoreCase("affiliated")){
				  inputVO.setTdpCommitteeLevelIds(new ArrayList<Long>());
				  inputVO.setTdpBasicCommitteeIds(new ArrayList<Long>());
				  inputVO.getTdpBasicCommitteeIds().add(1l);
				  inputVO.setTdpCommitteeEnrollmentId(2l);
				  inputVO.getTdpCommitteeLevelIds().add(5l);
				  inputVO.getTdpCommitteeLevelIds().add(7l);
				  inputVO.getTdpCommitteeLevelIds().add(9l);
				  //inputVO.setResultType("Mandal/Town/Division level Committees");
				  if(locationLevel != null && locationLevel.equalsIgnoreCase("parliament")){
					  inputVO.setLocationLevel(locationLevel);
					  inputVO.setResultType(null);
					  List<Object[]>  parliamentWisecommitteeDtlsObjLst = tdpCommitteeDAO.getLocationWiseTdpCommitteeDetails(inputVO);
					  inputVO.setResultType("completedCommittee");
					  List<Object[]> completedCommitteeObjLst = tdpCommitteeDAO.getLocationWiseTdpCommitteeDetails(inputVO);
					  //Setting into resultVO
					  prepareCommiteeDetailsData(parliamentWisecommitteeDtlsObjLst, completedCommitteeObjLst, locationMap,inputVO.getLocationLevel(), "Mandal/Town/Division level Committees");
					//  Collections.sort(resultVO.getSubList(),commiteeDecendingCountWiseSorting);
					  //preparing constituency wise committee performance data
				  }else if(locationLevel != null && locationLevel.equalsIgnoreCase("constituency")){
					  inputVO.setLocationLevel(locationLevel);
					  inputVO.setResultType(null);
					  List<Object[]>  constituencyWisecommitteeDtlsObjLst = tdpCommitteeDAO.getLocationWiseTdpCommitteeDetails(inputVO);
					  inputVO.setResultType("completedCommittee");
					  List<Object[]> constituencyWisecompletedCommitteeObjLst = tdpCommitteeDAO.getLocationWiseTdpCommitteeDetails(inputVO);
					  //Setting into resultVO
					  prepareCommiteeDetailsData(constituencyWisecommitteeDtlsObjLst, constituencyWisecompletedCommitteeObjLst, locationMap, inputVO.getLocationLevel(), "Mandal/Town/Division level Committees");
					 // Collections.sort(resultVO.getSubList1(),commiteeDecendingCountWiseSorting);
				  }
				  inputVO.setTdpCommitteeLevelIds(new ArrayList<Long>());
				  inputVO.setTdpBasicCommitteeIds(new ArrayList<Long>());
				  inputVO.getTdpBasicCommitteeIds().add(1l);
				  inputVO.setTdpCommitteeEnrollmentId(2l);
				  inputVO.getTdpCommitteeLevelIds().add(6l);
				  inputVO.getTdpCommitteeLevelIds().add(8l);
				 // inputVO.setResultType("Village/Ward level Committees");
				  if(locationLevel != null && locationLevel.equalsIgnoreCase("parliament")){
					  inputVO.setLocationLevel(locationLevel);
					  inputVO.setResultType(null);
					  List<Object[]>  parliamentWisecommitteeDtlsObjLst = tdpCommitteeDAO.getLocationWiseTdpCommitteeDetails(inputVO);
					  inputVO.setResultType("completedCommittee");
					  List<Object[]> completedCommitteeObjLst = tdpCommitteeDAO.getLocationWiseTdpCommitteeDetails(inputVO);
					  //Setting into resultVO
					  prepareCommiteeDetailsData(parliamentWisecommitteeDtlsObjLst, completedCommitteeObjLst, locationMap, inputVO.getLocationLevel(), "Village/Ward level Committees");
					//  Collections.sort(resultVO.getSubList(),commiteeDecendingCountWiseSorting);
					  //preparing constituency wise committee performance data
				  }else if(locationLevel != null && locationLevel.equalsIgnoreCase("constituency")){
					  inputVO.setLocationLevel(locationLevel);
					  inputVO.setResultType(null);
					  List<Object[]>  constituencyWisecommitteeDtlsObjLst = tdpCommitteeDAO.getLocationWiseTdpCommitteeDetails(inputVO);
					  inputVO.setResultType("completedCommittee");
					  List<Object[]> constituencyWisecompletedCommitteeObjLst = tdpCommitteeDAO.getLocationWiseTdpCommitteeDetails(inputVO);
					  //Setting into resultVO
					  prepareCommiteeDetailsData(constituencyWisecommitteeDtlsObjLst, constituencyWisecompletedCommitteeObjLst, locationMap, inputVO.getLocationLevel(), "Village/Ward level Committees");
					 // Collections.sort(resultVO.getSubList1(),commiteeDecendingCountWiseSorting);
				  }
			 }else if(committeeType != null && committeeType.equalsIgnoreCase("affiliated")){
				  inputVO.setTdpCommitteeLevelIds(new ArrayList<Long>());
				  inputVO.setTdpBasicCommitteeIds(new ArrayList<Long>());//2,3,4,6,7,8,9,11
				  Long[] idsArr = {2l,3l,4l,6l,7l,8l,9l,11l};
				  inputVO.setTdpBasicCommitteeIds(Arrays.asList(idsArr));
				  inputVO.setTdpCommitteeEnrollmentId(2l);
				  inputVO.getTdpCommitteeLevelIds().add(5l);
				  inputVO.getTdpCommitteeLevelIds().add(7l);
				  inputVO.getTdpCommitteeLevelIds().add(9l);
				  //inputVO.setResultType("Affiliated Committees");
				  if(locationLevel != null && locationLevel.equalsIgnoreCase("parliament")){
					  inputVO.setLocationLevel(locationLevel);
					  inputVO.setResultType(null);
					  List<Object[]>  parliamentWisecommitteeDtlsObjLst = tdpCommitteeDAO.getLocationWiseTdpCommitteeDetails(inputVO);
					  inputVO.setResultType("completedCommittee");
					  List<Object[]> completedCommitteeObjLst = tdpCommitteeDAO.getLocationWiseTdpCommitteeDetails(inputVO);
					  //Setting into resultVO
					  prepareCommiteeDetailsData(parliamentWisecommitteeDtlsObjLst, completedCommitteeObjLst, locationMap, inputVO.getLocationLevel(), "Affiliated Committees");
					//  Collections.sort(resultVO.getSubList(),commiteeDecendingCountWiseSorting);
					  //preparing constituency wise committee performance data
				  }else if(locationLevel != null && locationLevel.equalsIgnoreCase("constituency")){
					  inputVO.setLocationLevel(locationLevel);
					  inputVO.setResultType(null);
					  List<Object[]>  constituencyWisecommitteeDtlsObjLst = tdpCommitteeDAO.getLocationWiseTdpCommitteeDetails(inputVO);
					  inputVO.setResultType("completedCommittee");
					  List<Object[]> constituencyWisecompletedCommitteeObjLst = tdpCommitteeDAO.getLocationWiseTdpCommitteeDetails(inputVO);
					  //Setting into resultVO
					  prepareCommiteeDetailsData(constituencyWisecommitteeDtlsObjLst, constituencyWisecompletedCommitteeObjLst, locationMap, inputVO.getLocationLevel(), "Affiliated Committees");
					 // Collections.sort(resultVO.getSubList1(),commiteeDecendingCountWiseSorting);
				  }
			 }
				  //OvarAll percentage
				 // resultVO.setCompletedPerc(Util.calculatePercantage(resultVO.getCompletedCount(), resultVO.getTotalCount()));
				 // resultVO.setNotCompletedCommitteePer(Util.calculatePercantage(resultVO.getNotCompletedCommitteeCount(), resultVO.getTotalCount()));
				  
			 } catch (Exception e) {
				 LOG.error("Exception occured at getCommiteeOverviewPerformanceDetails() in CommitteeExceptionalReportService class ",e);
			 }
			 return locationMap;
		}
		private static Comparator<CommitteeDataVO> commiteeDecendingCountWiseSorting = new Comparator<CommitteeDataVO>() {
	     	public int compare(CommitteeDataVO location2, CommitteeDataVO location1) {
	     	Double per2 = location2.getNotCompletedCommitteePer();
	     	Double per1 = location1.getNotCompletedCommitteePer();
	     	//descending  order of percentage.
	     	 return per1.compareTo(per2);
	     	}
		};
		public Map<Long,ConsolidatedExceptionalReportVO>  prepareCommiteeDetailsData(List<Object[]> commiteeDtlsObjLst,List<Object[]> completedCommitteeObjList,
				Map<Long,ConsolidatedExceptionalReportVO> locationMap,String locationType,String levelType) {
			try {
				  if (commiteeDtlsObjLst != null && commiteeDtlsObjLst.size() > 0 ) {
					  for (Object[] param : commiteeDtlsObjLst) {
						  ConsolidatedExceptionalReportVO locationVO = locationMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
						  if(locationVO != null){
							  ConsolidatedExceptionalReportVO committeeLvl = getMatchedVO(levelType, locationVO.getSubList1());
							  if(committeeLvl != null){
								  locationVO.setTotalCount(locationVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[0]));
								  committeeLvl.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[0]));
								  locationVO.setNotConductedCount(locationVO.getNotConductedCount()+locationVO.getTotalCount());//default
								  committeeLvl.setNotConductedCount(committeeLvl.getTotalCount());//default
								  //setting address
								  locationVO.setAddressVO(getAddressDetails(param, locationType,"committee"));
								  Object[] matchObjArr = getMatchObjectArr(completedCommitteeObjList, locationVO.getLocationId());
								   if (matchObjArr != null && matchObjArr.length > 0) {
									   locationVO.setConductedCount(locationVO.getConductedCount()+commonMethodsUtilService.getLongValueForObject(matchObjArr[0]));
									   locationVO.setNotConductedCount(locationVO.getTotalCount()-locationVO.getConductedCount());
									   committeeLvl.setConductedCount(commonMethodsUtilService.getLongValueForObject(matchObjArr[0]));
									   committeeLvl.setNotConductedCount(committeeLvl.getTotalCount()-committeeLvl.getConductedCount());
								   }
								   locationVO.setPercentage(Util.calculatePercantage(locationVO.getNotConductedCount(), locationVO.getTotalCount()));
								   committeeLvl.setPercentage(Util.calculatePercantage(committeeLvl.getNotConductedCount(), committeeLvl.getTotalCount()));
								   if(committeeLvl.getPercentage() != null && committeeLvl.getPercentage() >0){
									   locationVO.setSortNo(locationVO.getSortNo() +1l);
								   }
								   locationVO.setPercentage1(locationVO.getPercentage1()+committeeLvl.getPercentage());
							}
						  }
					   }
				  }
				
			} catch (Exception e) {
				 LOG.error("Exception occured at prepareCommiteeDetailsData() in CommitteeExceptionalReportService class ",e);
			}
			return locationMap;
		}
		private AddressVO getAddressDetails(Object[] param,String locationType,String dataType) {
		   	 AddressVO addressVO = new AddressVO();
		   	 try {
		   		 if (locationType.equalsIgnoreCase("parliament")) {
		   			if(dataType != null && dataType.equalsIgnoreCase("committee")){
			   			 addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[1]));
			   			 addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[2]));
		   			}else if(dataType != null && dataType.equalsIgnoreCase("meeting")){
		   				addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[0]));
			   			 addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[1]));
		   			}
		   		 } else {
		   			 if(dataType != null && dataType.equalsIgnoreCase("meeting")){
		   				addressVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[0]));
		    			 addressVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[1]));
		    			 addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[2]));
		    			 addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[3]));
		    			 addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[4]));
		    			 addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[5]));
		   			 }else if(dataType != null && dataType.equalsIgnoreCase("committee")){
		   				 addressVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[1]));
			   			 addressVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[2]));
			   			 addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[3]));
			   			 addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[4]));
			   			 addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[5]));
			   			 addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[6]));
		   			 }
		   		}
		   		 
		   	 } catch (Exception e) {
		   		 LOG.error("Exception occurred  at setAddressDetails() in CommitteeExceptionalReportService class",e);
		   	 }
		   	 return addressVO;
		    }
		public List<Long> getTdpCommitteeLevelIds(InputVO inputVO) {
			List<Long> partyMeetingLevelIds = new ArrayList<Long>();
			try {
				if (inputVO.getLocationLevel() != null) {
				   if (inputVO.getLocationLevel().equalsIgnoreCase("mandalTownDivision")) {
						partyMeetingLevelIds.add(5l);
						partyMeetingLevelIds.add(7l);
						partyMeetingLevelIds.add(9l);
					} else if (inputVO.getLocationLevel().equalsIgnoreCase("villageWard")) {
						partyMeetingLevelIds.add(6l);
						partyMeetingLevelIds.add(8l);
					}
				}

			} catch (Exception e) {
				LOG.error("Exception occurred  at getTdpCommitteeLevelIds() in CommitteeExceptionalReportService class",e);
			}
			return partyMeetingLevelIds;
		}
		private Object[] getMatchObjectArr(List<Object[]> objList, Long locationId) {
			try {
				if (objList == null || objList.size() == 0)
					return null;

				for (Object[] param : objList) {
					if (commonMethodsUtilService.getLongValueForObject(param[1]).equals(locationId)) {
						return param;
					}
				}
			} catch (Exception e) {
				LOG.error("Exception occurred  at getMatchObjectArr() in CommitteeExceptionalReportService class",e);
			}
			return null;
		}
		/**
		   * @param InputVO inputVO
		   * @return ConsolidatedExceptionalReportVO 
		   * @author Hymavathi
		   * @Description :This Service Method is used to get booth incharge committee performance details. 
		   * @Date 28-FEB-2018
		   */
		public Map<Long,ConsolidatedExceptionalReportVO> getBoothInchargeCommitteePerformanceDetails(InputVO inputVO,Map<Long,ConsolidatedExceptionalReportVO> locationMap,String leveltype) {
		
			 try {
				  //preparing parliament wise committee performance data
				 inputVO.setBoothInchargeEnrollmentId(1l);
				 if(leveltype != null && leveltype.equalsIgnoreCase("parliament")){
					  inputVO.setLocationLevel(leveltype);
					  inputVO.setResultType(null);
					  List<Object[]>  parliamentWisecommitteeDtlsObjLst = boothInchargeCommitteeDAO.getLocationWiseBoothInchargeCommitteeDetails(inputVO);
					  inputVO.setResultType("completedCommittee");
					  List<Object[]> completedCommitteeObjLst = boothInchargeCommitteeDAO.getLocationWiseBoothInchargeCommitteeDetails(inputVO);
					  //Setting into resultVO
					  prepareCommiteeDetailsData(parliamentWisecommitteeDtlsObjLst, completedCommitteeObjLst, locationMap, inputVO.getLocationLevel(),"Booth Committees");
					  //Collections.sort(resultVO.getSubList(),commiteeDecendingCountWiseSorting);
				 }else if(leveltype != null && leveltype.equalsIgnoreCase("constituency")){
					  //preparing constituency wise committee performance data
					  inputVO.setLocationLevel(leveltype);
					  inputVO.setResultType(null);
					  List<Object[]>  constituencyWisecommitteeDtlsObjLst = boothInchargeCommitteeDAO.getLocationWiseBoothInchargeCommitteeDetails(inputVO);
					  inputVO.setResultType("completedCommittee");
					  List<Object[]> constituencyWisecompletedCommitteeObjLst = boothInchargeCommitteeDAO.getLocationWiseBoothInchargeCommitteeDetails(inputVO);
					  //Setting into resultVO
					  prepareCommiteeDetailsData(constituencyWisecommitteeDtlsObjLst, constituencyWisecompletedCommitteeObjLst, locationMap, inputVO.getLocationLevel(),"Booth Committees");
					 // Collections.sort(resultVO.getSubList1(),commiteeDecendingCountWiseSorting);
				 }
			 } catch (Exception e) {
				 LOG.error("Exception occured at getBoothInchargeCommitteePerformanceDetails() in CommitteeExceptionalReportService class ",e);
			 }
			 return locationMap;
		}
		public Map<Long,ConsolidatedExceptionalReportVO> getConstituencyWisePoorPerformance(Long stateId,String location,Map<Long,ConsolidatedExceptionalReportVO> locationMap){
			try{
				int countPosition;
				if(location != null && location.trim().equalsIgnoreCase("constituency")){
					countPosition = 2;
				}else{
					countPosition = 1;
				}
				List<Object[]> targetList = kaizalaAnswerInfoDAO.getConstituencyWiseTargetList(stateId,location);
				
				List<Object[]> installedCommitteeMembers = kaizalaAnswerInfoDAO.getConstituencyWiseInstalledCommitteeMembers(stateId,location);
				//create Map for constituencyId and installedCommitteeMembersCount
				Map<Long,Long> constituencyIdAndInstalledCommitteeMembersCount = new HashMap<Long,Long>();
				if(installedCommitteeMembers != null && installedCommitteeMembers.size() > 0){
					for(Object[] param : installedCommitteeMembers){
						if(param[0] != null){
							constituencyIdAndInstalledCommitteeMembersCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[countPosition]));
						}
					}
				}
				List<Object[]> notHavingSmartPhone = kaizalaAnswerInfoDAO.getConstituencyWiseNotHavingSmartPhone(stateId,location);
				//create map for constituencyId and notHavingSmartPhoneCount
				Map<Long,Long> constituencyIdAndNotHavingSmartPhoneCount = new HashMap<Long,Long>();
				if(notHavingSmartPhone != null && notHavingSmartPhone.size() > 0){
					for(Object[] param : notHavingSmartPhone){
						if(param[0] != null){
							constituencyIdAndNotHavingSmartPhoneCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[countPosition]));
						}
					}
				}
				List<Object[]> publicInstalled = kaizalaAnswerInfoDAO.getConstituencyWisePublicInstalled(stateId,location);
				//create a map for constituencyId and publicInstalledCount
				Map<Long,Long> constituencyIdAndPublicInstalledCount = new HashMap<Long,Long>();
				if(publicInstalled != null && publicInstalled.size() > 0){
					for(Object[] param : publicInstalled){
						if(param[0] != null){
							constituencyIdAndPublicInstalledCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[countPosition]));
						}
					}
				}
				List<Object[]> cadreInstalled = kaizalaAnswerInfoDAO.getConstituecnyWiseCadreInstalled(stateId,location);
				//create a map for constituencyId and cadreInstalledCount
				Map<Long,Long> constituencyIdAndCadreInstalledCount = new HashMap<Long,Long>();
				if(cadreInstalled != null && cadreInstalled.size() > 0){
					for(Object[] param : cadreInstalled){
						if(param[0] != null){
							constituencyIdAndCadreInstalledCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[countPosition]));
						}
					}
				}
				//collect all the constituencyId and name
				Set<Long> conIds = new HashSet<Long>();
				Set<Long> parIds = new HashSet<Long>();
				if(targetList != null && targetList.size() > 0){
					for(Object[] param : targetList){
						if(location != null && location.trim().equalsIgnoreCase("constituency")){
							conIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
							parIds.add(commonMethodsUtilService.getLongValueForObject(param[1]));
						}else{
							parIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
						}
						
					}
				}
				
				//get the name of constituency
				List<Object[]> constList = null;
				if(conIds != null && conIds.size() > 0){
					constList = constituencyDAO.getConstituenciesNamesByIds(new ArrayList<Long>(conIds));
				}
				//create Map for constituencyId and name
				Map<Long,String> constituencyIdAndName = new HashMap<Long,String>();
				if(constList != null && constList.size() > 0){
					for(Object[] param : constList){
						constituencyIdAndName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					}
				}
				//get the name of parliament
				List<Object[]> parList = null;
				if(parIds != null && parIds.size() > 0){
					parList = constituencyDAO.getConstituenciesNamesByIds(new ArrayList<Long>(parIds));
				}
				//create map for parliamentId and name
				Map<Long,String> parliamentIdAndName = new HashMap<Long,String>();
				if(parList != null && parList.size() > 0){
					for(Object[] param : parList){
						parliamentIdAndName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					}
				}
				
				//create vo object for ui
				//List<KaizalaExceptionalReportVO> exceptionalReportVOs = new ArrayList<KaizalaExceptionalReportVO>();
				ConsolidatedExceptionalReportVO exceptionalReportVO = null;
				
				if(targetList != null && targetList.size() > 0){
					for(Object[] param : targetList){
						if(param[0] != null){
							exceptionalReportVO = locationMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
							if(exceptionalReportVO != null){
							if(location != null && location.trim().equalsIgnoreCase("constituency")){
								exceptionalReportVO.setAddressVO(new AddressVO());
								exceptionalReportVO.getAddressVO().setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[0]));
								exceptionalReportVO.getAddressVO().setConstituencyName(constituencyIdAndName.get(commonMethodsUtilService.getLongValueForObject(param[0])));
								exceptionalReportVO.getAddressVO().setParliamentId(commonMethodsUtilService.getLongValueForObject(param[1]));
								exceptionalReportVO.getAddressVO().setParliamentName(parliamentIdAndName.get(commonMethodsUtilService.getLongValueForObject(param[1])));
							}/*else{
								exceptionalReportVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[0]));
								exceptionalReportVO.setParliamentName(parliamentIdAndName.get(commonMethodsUtilService.getLongValueForObject(param[0])));
							}*/
							ConsolidatedExceptionalReportVO kaizalaVO = getMatchedVO("Kaizala", exceptionalReportVO.getSubList1());
							if(kaizalaVO != null){
								kaizalaVO.setTotalCount(kaizalaVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[countPosition]));
								//set installed committee member count
								kaizalaVO.setConductedCount(kaizalaVO.getConductedCount()+commonMethodsUtilService.getLongValueForObject(constituencyIdAndInstalledCommitteeMembersCount.get(commonMethodsUtilService.getLongValueForObject(param[0]))));
								//set committee having no start phone
								kaizalaVO.setCommitteeHavingNoSmartPhone(kaizalaVO.getCommitteeHavingNoSmartPhone()+commonMethodsUtilService.getLongValueForObject(constituencyIdAndNotHavingSmartPhoneCount.get(commonMethodsUtilService.getLongValueForObject(param[0]))));
								//set committee not installed
								kaizalaVO.setNotConductedCount(kaizalaVO.getTotalCount() - ( kaizalaVO.getConductedCount() + kaizalaVO.getCommitteeHavingNoSmartPhone() ));
								//set committee not installed percent
								kaizalaVO.setPercentage(Util.calculatePercantage( kaizalaVO.getNotConductedCount() , kaizalaVO.getTotalCount() ));
								//set target,
								exceptionalReportVO.setTotalCount(exceptionalReportVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[countPosition]));
								//set installed committee member count
								exceptionalReportVO.setConductedCount(exceptionalReportVO.getConductedCount()+commonMethodsUtilService.getLongValueForObject(constituencyIdAndInstalledCommitteeMembersCount.get(commonMethodsUtilService.getLongValueForObject(param[0]))));
								//set committee having no start phone
								exceptionalReportVO.setCommitteeHavingNoSmartPhone(exceptionalReportVO.getCommitteeHavingNoSmartPhone()+commonMethodsUtilService.getLongValueForObject(constituencyIdAndNotHavingSmartPhoneCount.get(commonMethodsUtilService.getLongValueForObject(param[0]))));
								//set committee not installed
								exceptionalReportVO.setNotConductedCount(exceptionalReportVO.getTotalCount() - ( exceptionalReportVO.getConductedCount() + exceptionalReportVO.getCommitteeHavingNoSmartPhone() ));
								//set committee not installed percent
								exceptionalReportVO.setPercentage(Util.calculatePercantage( exceptionalReportVO.getNotConductedCount() , exceptionalReportVO.getTotalCount() ));
								//set publicInstalled
								//exceptionalReportVO.setPublicInstalled(commonMethodsUtilService.getLongValueForObject(constituencyIdAndPublicInstalledCount.get(commonMethodsUtilService.getLongValueForObject(param[0]))));
								//set cadreInstalled
								//exceptionalReportVO.setCadreInstalled(commonMethodsUtilService.getLongValueForObject(constituencyIdAndCadreInstalledCount.get(commonMethodsUtilService.getLongValueForObject(param[0]))));
								//set totalInstalled
								//exceptionalReportVO.setTotalInstalled(exceptionalReportVO.getCommitteeInstalled() + exceptionalReportVO.getPublicInstalled() + exceptionalReportVO.getCadreInstalled());
								//exceptionalReportVOs.add(exceptionalReportVO);
							}
						}
						}
					}
				}
				/*if(exceptionalReportVOs != null && exceptionalReportVOs.size() > 0){
					Collections.sort(exceptionalReportVOs, new Comparator<KaizalaExceptionalReportVO>() {
						@Override
						public int compare(KaizalaExceptionalReportVO obj1,	KaizalaExceptionalReportVO obj2) {
							Double value1 = obj1.getCommitteeNotInstalledPer();
							Double value2 = obj2.getCommitteeNotInstalledPer();
							return value2.compareTo(value1);
						}
					});
				}*/
				/*if(size >= 0){
					if(exceptionalReportVOs.size() > size){
						exceptionalReportVOs = exceptionalReportVOs.subList(0, size);
					}
				}*/
				return locationMap;
			}catch(Exception e){
				LOG.error("Error occured getConstituencyWisePoorPerformance() method of KaizalaExceptionReportService{}");
			}
			return null;
		}
		
		/**
		   * @param InputVO inputVO
		   * @return Map<Long,ConsolidatedExceptionalReportVO>
		   * @author Hymavathi 
		   * @Description :This Service Method is used to get activity performance details location wise. 
		   * @Date 28-FEB-2018
		   */
		public Map<Long,ConsolidatedExceptionalReportVO> getActivityPerformanceDetailsLocationWise(InputVO inputVO,Map<Long,ConsolidatedExceptionalReportVO> locationMap,String levelType) {
			//ActivityExceptionalReportVO resultVO = new ActivityExceptionalReportVO();
			 try {
				 //preparing parliament wise activities details data
				 inputVO.setActivityScopeId(60l);
				 if(levelType != null && levelType.equalsIgnoreCase("parliament")){
					 List<Object[]> parliamentWiseActivityDtlsObjList = activityLocationInfoDAO.getLocationWiseActiviyDetailsByType(inputVO.getActivityScopeId(), levelType, "total");
					 List<Object[]> prlmntWsActvtyCncctdDtlsObjLst = activityLocationInfoDAO.getLocationWiseActiviyDetailsByType(inputVO.getActivityScopeId(), levelType, "conducted");
					 prepareCommiteeDetailsData(parliamentWiseActivityDtlsObjList, prlmntWsActvtyCncctdDtlsObjLst, locationMap,levelType, "Dalitha Tejam");
					 //Map<Long,ActivityExceptionalReportVO> parliamentDtlsMap = getLocationWiseActivityDtls(parliamentWiseActivityDtlsObjList, prlmntWsActvtyCncctdDtlsObjLst, "parliament");
					 //resultVO.setSubList2(new ArrayList<ActivityExceptionalReportVO>(parliamentDtlsMap.values()));
					// Collections.sort(resultVO.getSubList2(), activityDecendingCountWiseSorting);
				 }else{
					 //preparing constituency wise activities details data 
					 List<Object[]> constituencyWiseActivityDtlsObjList = activityLocationInfoDAO.getLocationWiseActiviyDetailsByType(inputVO.getActivityScopeId(), levelType, "total");
					 List<Object[]> cnsttncyWsActvtyCncctdDtlsObjLst = activityLocationInfoDAO.getLocationWiseActiviyDetailsByType(inputVO.getActivityScopeId(), levelType, "conducted");
					 prepareCommiteeDetailsData(constituencyWiseActivityDtlsObjList, cnsttncyWsActvtyCncctdDtlsObjLst, locationMap,levelType, "Dalitha Tejam");
					 // Map<Long,ActivityExceptionalReportVO> constituencyDtlsMap = getLocationWiseActivityDtls(constituencyWiseActivityDtlsObjList, cnsttncyWsActvtyCncctdDtlsObjLst, "constituency");
					 //resultVO.setSubList1(new ArrayList<ActivityExceptionalReportVO>(constituencyDtlsMap.values()));
					 //Collections.sort(resultVO.getSubList1(), activityDecendingCountWiseSorting);
				 }
			 } catch (Exception e) {
				 LOG.error("Exception occured at getActivityPerformanceDetailsLocationWise() in ActivityExceptionalReportService class",e);
			 }
			 return locationMap;
		}
		
		public Map<Long,ConsolidatedExceptionalReportVO> getListOfParliamentsWithPoorPerformance(InputVO inputVO,Map<Long,ConsolidatedExceptionalReportVO> locationMap){
			try{
				//List<TrainingCampProgramVO> fianlList = new ArrayList<TrainingCampProgramVO>();
				//TrainingCampProgramVO campProgramVO = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date stDate = null;
				Date ndDate = null;
				if(inputVO.getFromDateStr() != null && inputVO.getFromDateStr().trim().length() == 10 && inputVO.getToDateStr() != null && inputVO.getToDateStr().trim().length() == 10){
					stDate = sdf.parse(inputVO.getFromDateStr().trim());
					ndDate = sdf.parse(inputVO.getToDateStr().trim());
				}
				List<Long> tdpCommitteeLevelIds = Arrays.asList(5l,6l,7l,8l,9l);
				List<Long> trainingCampProgramIds =Arrays.asList(8l);
				List<Long> enrollmentYearIds = Arrays.asList(4l);
				Long locationLevelId= 2l;
				 List<Long> locationLevelValues = null;
				//for top section collect sum of total count
				Long overAllEligibleCount = 0L;
				Long asOfNowTrained = 0L;
				Long yetToTrain = 0L;
				
				Long levelId = 10L;//for constituency
				
				// first get constituency wise total invitees from (training_camp_details_info->eligible); 
				List<Object[]> inviteeList = trainingCampDetailsInfoDAO.getInviteesList(levelId,tdpCommitteeLevelIds,trainingCampProgramIds);
				
				//create a map of constituencyId and TrainingCampProgramVO 
				//Map<Long,TrainingCampProgramVO> parliamentIdAndDetailsMap = new HashMap<Long,TrainingCampProgramVO>();
				if(inviteeList != null && inviteeList.size() > 0){
					for(Object[] param : inviteeList){
						ConsolidatedExceptionalReportVO campProgramVO = locationMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						//campProgramVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[0]));
						//campProgramVO.setParliament(commonMethodsUtilService.getStringValueForObject(param[1]));
						//Training Camp
						if(campProgramVO != null){
						ConsolidatedExceptionalReportVO sublevel = getMatchedVO("Training Camp", campProgramVO.getSubList1());
							if(sublevel != null){
								campProgramVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[2]));
								sublevel.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[2]));
								//parliamentIdAndDetailsMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), campProgramVO);
								overAllEligibleCount = overAllEligibleCount + commonMethodsUtilService.getLongValueForObject(param[2]);
							}
						}
					}
				}
				// now take location wise invitee attended;
				List<Object[]>  tempList = null;
				List<Object[]>  tempList2 = null;
				List<Object[]>  tempList3 = null;
				List<Object[]>  tempList4 = null;
				if(locationLevelId.longValue() == IConstants.STATE_LEVEl_ACCESS_ID){
					List<Long> distList1 = new ArrayList<Long>(){{add(11L);add(12L);add(13L);}};
					tempList  = trainingCampAttendanceDAO.getInviteAttendedCountForTrainingCamp(3L,distList1,enrollmentYearIds,trainingCampProgramIds,tdpCommitteeLevelIds);//Procedure Call
					List<Long> distList2 = new ArrayList<Long>(){{add(14L);add(15L);add(16L);}};
					tempList2  = trainingCampAttendanceDAO.getInviteAttendedCountForTrainingCamp(3L,distList2,enrollmentYearIds,trainingCampProgramIds,tdpCommitteeLevelIds);//Procedure Call
					List<Long> distList3 = new ArrayList<Long>(){{add(17L);add(18L);add(19L);add(517L);}};
					tempList3  = trainingCampAttendanceDAO.getInviteAttendedCountForTrainingCamp(3L,distList3,enrollmentYearIds,trainingCampProgramIds,tdpCommitteeLevelIds);//Procedure Call
					List<Long> distList4 = new ArrayList<Long>(){{add(20L);add(21L);add(22L);add(23L);}};
					tempList4  = trainingCampAttendanceDAO.getInviteAttendedCountForTrainingCamp(3L,distList4,enrollmentYearIds,trainingCampProgramIds,tdpCommitteeLevelIds);//Procedure Call
					tempList.addAll(tempList2);
					tempList.addAll(tempList3);
					tempList.addAll(tempList4);
				}
				
				//now collect location wise invitee attended using a map
				Map<Long,Set<Long>> locationIdAndListOfCaders = new HashMap<Long,Set<Long>>();
				Set<Long> caderList = null;
				if(tempList != null && tempList.size() > 0){
					for(Object[] param : tempList){
						if(commonMethodsUtilService.getStringValueForObject(param[7]).trim().equalsIgnoreCase("INVITEE")){
							caderList = locationIdAndListOfCaders.get(commonMethodsUtilService.getLongValueForObject(param[13]));
							if(null == caderList){
								caderList = new HashSet<Long>();
								locationIdAndListOfCaders.put(commonMethodsUtilService.getLongValueForObject(param[13]), caderList);
							}
							caderList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
						}
					}
				}
				
				
				
				if(locationMap != null && locationMap.size() > 0){
					for(Entry<Long,ConsolidatedExceptionalReportVO> entry : locationMap.entrySet()){
						if(entry != null && entry.getValue() != null){
							ConsolidatedExceptionalReportVO sublevel = getMatchedVO("Training Camp", entry.getValue().getSubList1());
							if(sublevel != null){
								if(locationIdAndListOfCaders != null && locationIdAndListOfCaders.get(entry.getValue().getLocationId()) != null && locationIdAndListOfCaders.get(entry.getValue().getLocationId()).size() > 0){
									entry.getValue().setConductedCount(entry.getValue().getConductedCount()+Long.parseLong(String.valueOf(locationIdAndListOfCaders.get(entry.getValue().getLocationId()).size())));
									sublevel.setConductedCount(Long.parseLong(String.valueOf(locationIdAndListOfCaders.get(entry.getValue().getLocationId()).size())));
									asOfNowTrained = asOfNowTrained + entry.getValue().getConductedCount();
								}else{
									entry.getValue().setConductedCount(0L);
									sublevel.setConductedCount(0L);
								}
								entry.getValue().setNotConductedCount(entry.getValue().getTotalCount()-entry.getValue().getConductedCount());
								entry.getValue().setPercentage(Util.calculatePercantage(entry.getValue().getNotConductedCount(),entry.getValue().getTotalCount()));
								sublevel.setNotConductedCount(sublevel.getTotalCount()-sublevel.getConductedCount());
								sublevel.setPercentage(Util.calculatePercantage(sublevel.getNotConductedCount(),sublevel.getTotalCount()));
							}
						}
					}
				}
				
				//yetToTrain = overAllEligibleCount - asOfNowTrained;
				//Double asOfNowTrainedPer = Util.calculatePercantage(asOfNowTrained,overAllEligibleCount);
				//Double yetToTrainPer = Util.calculatePercantage(yetToTrain,overAllEligibleCount);
				//fianlList = new ArrayList<TrainingCampProgramVO>( parliamentIdAndDetailsMap.values());
				
				/*Collections.sort(fianlList, new Comparator<TrainingCampProgramVO>(){
					@Override
					public int compare(TrainingCampProgramVO obj1,TrainingCampProgramVO obj2) {
						Double value1 = obj1.getTotalNotAttenedCountPer();
						Double value2 = obj2.getTotalNotAttenedCountPer();
						return value2.compareTo(value1);
					}
				});*/
				/*if(fianlList.size() > size){
					fianlList = fianlList.subList(0, size);
				}
				fianlList.get(0).setOverAllEligibleCount(overAllEligibleCount);
				fianlList.get(0).setAsOfNowTrained(asOfNowTrained);
				fianlList.get(0).setYetToTrain(yetToTrain);
				fianlList.get(0).setAsOfNowTrainedPer(asOfNowTrainedPer);
				fianlList.get(0).setYetToTrainPer(yetToTrainPer);*/
				return locationMap;
			}catch(Exception e){
				LOG.error("Exception raised at getListOfParliamentsWithPoorPerformance() method of TrainingCampExceptionalReportService", e);
			}
			return null;
		}
		
}
