package com.itgrids.partyanalyst.exceptionalReport.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothInchargeCommitteeDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingStatusDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
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
		     	//descending  order of percentage.
		     	//if(per1 != null && per1 != 0.0d){
		     		return per1.compareTo(per2);
		     	//}else {
		     	//	return 0;
		     	//}
		     	}
	};
	public static Comparator<ConsolidatedExceptionalReportVO> consolidatedDecendingCountWiseSorting = new Comparator<ConsolidatedExceptionalReportVO>() {
     	public int compare(ConsolidatedExceptionalReportVO location2, ConsolidatedExceptionalReportVO location1) {
     	Double per2 = location2.getPercentage1();
     	Double per1 = location1.getPercentage1();
     	//descending  order of percentage.
     	//if(per1 != null && per1 != 0.0d){
     		return per1.compareTo(per2);
     	//}else {
     	//	return 0;
     	//}
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
			 
			 getConsolidatedPartyMeetingExceptionReportMeetingLevelWise(inputVO,locationMap,"parliament");
			 getConsolidatedPartyMeetingExceptionReportMeetingLevelWise(inputVO,constituenyDtlsMap,"constituency");
			 getCommiteeOverviewPerformanceDetails(inputVO,locationMap,"parliament","");
			 getCommiteeOverviewPerformanceDetails(inputVO,constituenyDtlsMap,"constituency","");
			 getCommiteeOverviewPerformanceDetails(inputVO,locationMap,"parliament","affiliated");
			 getCommiteeOverviewPerformanceDetails(inputVO,constituenyDtlsMap,"constituency","affiliated");
		
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
	   * @return PartyMeetingExceptionalReportVO 
	   * @author Hymavathi 
	   * @Description :This Service Method is used to get party meeting location level wise report. 
	   * @Date 26-FEB-2018
	   */
	public Map<Long,ConsolidatedExceptionalReportVO> getConsolidatedPartyMeetingExceptionReportMeetingLevelWise(InputVO inputVO, 
			Map<Long,ConsolidatedExceptionalReportVO> locationMap,String resultType) {
		//ConsolidatedExceptionalReportVO resultVO = new ConsolidatedExceptionalReportVO();
		try {
			
			//preparing input parameter
			//List<Long> partyMeetingLevelIds = getPartyMeetingLevelIds(inputVO);
			List<Date> dateList = Util.getDates(inputVO.getFromDateStr(), inputVO.getToDateStr(), new SimpleDateFormat("dd/MM/yyyy"));
			// Map<Long,ConsolidatedExceptionalReportVO> locationMap = new HashMap<Long, ConsolidatedExceptionalReportVO>();
			// Map<Long,ConsolidatedExceptionalReportVO> constituenyDtlsMap =   new HashMap<Long, ConsolidatedExceptionalReportVO>();
			 //inputVO.setPartyMeetingLevelIds(partyMeetingLevelIds);
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
			 /*ConsolidatedExceptionalReportVO boothComm = new ConsolidatedExceptionalReportVO();
			 boothComm.setLocationId(7l);
			 boothComm.setLocationName("Booth Committees");
			 list.add(boothComm);*/
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
								  //locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[1]));
								  //locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
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
								   locationVO.setPercentage1(locationVO.getPercentage1()+committeeLvl.getPercentage());
								   //calculating overall committee details
								   /*if (locationType.equalsIgnoreCase("parliament")) {
									   resultVO.setTotalCount(resultVO.getTotalCount()+locationVO.getTotalCount());
									   resultVO.setNotCompletedCommitteeCount(resultVO.getNotCompletedCommitteeCount()+locationVO.getNotCompletedCommitteeCount());
									   resultVO.setCompletedCount(resultVO.getCompletedCount()+locationVO.getCompletedCount());
								   }*/
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
		   * @return CommitteeDataVO 
		   * @author Santosh Kumar Verma 
		   * @Description :This Service Method is used to get booth incharge committee performance details. 
		   * @Date 22-FEB-2018
		   */
		public CommitteeDataVO getBoothInchargeCommitteePerformanceDetails(InputVO inputVO) {
			CommitteeDataVO resultVO = new CommitteeDataVO();
			 try {
				  //preparing parliament wise committee performance data
				 /*inputVO.setBoothInchargeEnrollmentId(1l);
				  inputVO.setLocationLevel("parliament");
				  List<Object[]>  parliamentWisecommitteeDtlsObjLst = boothInchargeCommitteeDAO.getLocationWiseBoothInchargeCommitteeDetails(inputVO);
				  inputVO.setResultType("completedCommittee");
				  List<Object[]> completedCommitteeObjLst = boothInchargeCommitteeDAO.getLocationWiseBoothInchargeCommitteeDetails(inputVO);
				  //Setting into resultVO
				  resultVO.setSubList(prepareCommiteeDetailsData(parliamentWisecommitteeDtlsObjLst, completedCommitteeObjLst, resultVO, inputVO.getLocationLevel()));
				  Collections.sort(resultVO.getSubList(),commiteeDecendingCountWiseSorting);
				  //preparing constituency wise committee performance data
				  inputVO.setLocationLevel("constituency");
				  inputVO.setResultType(null);
				  List<Object[]>  constituencyWisecommitteeDtlsObjLst = boothInchargeCommitteeDAO.getLocationWiseBoothInchargeCommitteeDetails(inputVO);
				  inputVO.setResultType("completedCommittee");
				  List<Object[]> constituencyWisecompletedCommitteeObjLst = boothInchargeCommitteeDAO.getLocationWiseBoothInchargeCommitteeDetails(inputVO);
				  //Setting into resultVO
				  resultVO.setSubList1(prepareCommiteeDetailsData(constituencyWisecommitteeDtlsObjLst, constituencyWisecompletedCommitteeObjLst, resultVO, inputVO.getLocationLevel()));
				  Collections.sort(resultVO.getSubList1(),commiteeDecendingCountWiseSorting);*/
			 } catch (Exception e) {
				 LOG.error("Exception occured at getBoothInchargeCommitteePerformanceDetails() in CommitteeExceptionalReportService class ",e);
			 }
			 return resultVO;
		}
}
