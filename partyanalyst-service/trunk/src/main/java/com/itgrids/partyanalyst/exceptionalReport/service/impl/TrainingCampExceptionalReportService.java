package com.itgrids.partyanalyst.exceptionalReport.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IParliamentAssemblyDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampDetailsInfoDAO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.TrainingCampProgramVO;
import com.itgrids.partyanalyst.exceptionalReport.service.ITrainingCampExceptionalReportService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;

public class TrainingCampExceptionalReportService implements ITrainingCampExceptionalReportService{
	
	private final static Logger LOG = Logger.getLogger(TrainingCampExceptionalReportService.class);
	
	private DateUtilService dateUtilService;
	private IConstituencyDAO constituencyDAO;
	private ITrainingCampDAO trainingCampDAO;
	private IParliamentAssemblyDAO parliamentAssemblyDAO;
	private CommonMethodsUtilService commonMethodsUtilService;
	private ITrainingCampAttendanceDAO trainingCampAttendanceDAO;
	private SetterAndGetterUtilService setterAndGetterUtilService;
	private ITrainingCampDetailsInfoDAO trainingCampDetailsInfoDAO;
	
	public void setCommonMethodsUtilService(CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public void setSetterAndGetterUtilService(
			SetterAndGetterUtilService setterAndGetterUtilService) {
		this.setterAndGetterUtilService = setterAndGetterUtilService;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public void setTrainingCampDetailsInfoDAO(
			ITrainingCampDetailsInfoDAO trainingCampDetailsInfoDAO) {
		this.trainingCampDetailsInfoDAO = trainingCampDetailsInfoDAO;
	}
	public void setTrainingCampAttendanceDAO(
			ITrainingCampAttendanceDAO trainingCampAttendanceDAO) {
		this.trainingCampAttendanceDAO = trainingCampAttendanceDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public void setParliamentAssemblyDAO(
			IParliamentAssemblyDAO parliamentAssemblyDAO) {
		this.parliamentAssemblyDAO = parliamentAssemblyDAO;
	}
	public void setTrainingCampDAO(ITrainingCampDAO trainingCampDAO) {
		this.trainingCampDAO = trainingCampDAO;
	}
	public List<TrainingCampProgramVO> getListOfParliamentsWithPoorPerformance(String startDate,String endDate, Long stateId,int size,List<Long> tdpCommitteeLevelIds,List<Long> trainingCampProgramIds,List<Long> enrollmentYearIds,Long locationLevelId, List<Long> locationLevelValues ){
		try{
			List<TrainingCampProgramVO> fianlList = new ArrayList<TrainingCampProgramVO>();
			TrainingCampProgramVO campProgramVO = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date stDate = null;
			Date ndDate = null;
			if(startDate != null && startDate.trim().length() == 10 && endDate != null && endDate.trim().length() == 10){
				stDate = sdf.parse(startDate.trim());
				ndDate = sdf.parse(endDate.trim());
			}
			//for top section collect sum of total count
			Long overAllEligibleCount = 0L;
			Long asOfNowTrained = 0L;
			Long yetToTrain = 0L;
			
			Long levelId = 10L;//for constituency
			
			// first get constituency wise total invitees from (training_camp_details_info->eligible); 
			List<Object[]> inviteeList = trainingCampDetailsInfoDAO.getInviteesList(levelId,tdpCommitteeLevelIds,trainingCampProgramIds);
			
			//create a map of constituencyId and TrainingCampProgramVO 
			Map<Long,TrainingCampProgramVO> parliamentIdAndDetailsMap = new HashMap<Long,TrainingCampProgramVO>();
			if(inviteeList != null && inviteeList.size() > 0){
				for(Object[] param : inviteeList){
					campProgramVO = new TrainingCampProgramVO();
					campProgramVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[0]));
					campProgramVO.setParliament(commonMethodsUtilService.getStringValueForObject(param[1]));
					campProgramVO.setTotalEligibleCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					parliamentIdAndDetailsMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), campProgramVO);
					overAllEligibleCount = overAllEligibleCount + commonMethodsUtilService.getLongValueForObject(param[2]);
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
			
			
			
			if(parliamentIdAndDetailsMap != null && parliamentIdAndDetailsMap.size() > 0){
				for(Entry<Long,TrainingCampProgramVO> entry : parliamentIdAndDetailsMap.entrySet()){
					if(entry != null && entry.getValue() != null){
						if(locationIdAndListOfCaders != null && locationIdAndListOfCaders.get(entry.getValue().getParliamentId()) != null && locationIdAndListOfCaders.get(entry.getValue().getParliamentId()).size() > 0){
							entry.getValue().setTotalAttenedCount(Long.parseLong(String.valueOf(locationIdAndListOfCaders.get(entry.getValue().getParliamentId()).size())));
							asOfNowTrained = asOfNowTrained + entry.getValue().getTotalAttenedCount();
						}else{
							entry.getValue().setTotalAttenedCount(0L);
						}
						entry.getValue().setTotalNotAttenedCount(entry.getValue().getTotalEligibleCount()-entry.getValue().getTotalAttenedCount());
						entry.getValue().setTotalNotAttenedCountPer(calculatePercantage(entry.getValue().getTotalNotAttenedCount(),entry.getValue().getTotalEligibleCount()));
					}
				}
			}
			
			yetToTrain = overAllEligibleCount - asOfNowTrained;
			Double asOfNowTrainedPer = calculatePercantage(asOfNowTrained,overAllEligibleCount);
			Double yetToTrainPer = calculatePercantage(yetToTrain,overAllEligibleCount);
			fianlList = new ArrayList<TrainingCampProgramVO>( parliamentIdAndDetailsMap.values());
			
			Collections.sort(fianlList, new Comparator<TrainingCampProgramVO>(){
				@Override
				public int compare(TrainingCampProgramVO obj1,TrainingCampProgramVO obj2) {
					Double value1 = obj1.getTotalNotAttenedCountPer();
					Double value2 = obj2.getTotalNotAttenedCountPer();
					return value2.compareTo(value1);
				}
			});
			
			
			
			if(fianlList.size() > size){
				fianlList = fianlList.subList(0, size);
			}
			fianlList.get(0).setOverAllEligibleCount(overAllEligibleCount);
			fianlList.get(0).setAsOfNowTrained(asOfNowTrained);
			fianlList.get(0).setYetToTrain(yetToTrain);
			fianlList.get(0).setAsOfNowTrainedPer(asOfNowTrainedPer);
			fianlList.get(0).setYetToTrainPer(yetToTrainPer);
			return fianlList;
		}catch(Exception e){
			LOG.error("Exception raised at getListOfParliamentsWithPoorPerformance() method of TrainingCampExceptionalReportService", e);
		}
		return null;
	}
	public List<TrainingCampProgramVO> getListOfAssemblyWithPoorPerformance(String startDate,String endDate, Long stateId,int size,List<Long> tdpCommitteeLevelIds,List<Long> trainingCampProgramIds,List<Long> enrollmentYearIds,Long locationLevelId, List<Long> locationLevelValues ){
		try{
			List<TrainingCampProgramVO> finalList = new ArrayList<TrainingCampProgramVO>();
			TrainingCampProgramVO campProgramVO = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date stDate = null;
			Date ndDate = null;
			if(startDate != null && startDate.trim().length() == 10 && endDate != null && endDate.trim().length() == 10){
				stDate = sdf.parse(startDate.trim());
				ndDate = sdf.parse(endDate.trim());
			}
			
			//create map of campId and list of constituency in that camp
			List<Long> svvCenterList = IConstants.SVV_CENTER_LOCATION_LIST;
			List<Long> ewkCenterList = IConstants.EWK_CENTER_LOCATION_LIST;
			List<Long> gpnCenterList = IConstants.GPN_CENTER_LOCATION_LIST;
			List<Long> akkcCenterList = IConstants.AKKC_CENTER_LOCATION_LIST;
			
			List<Long> trainingCampIds = IConstants.TRAINING_CAMP_ID;
			List<Object[]> campIdAndNameList = trainingCampDAO.getTrainingCamps(new HashSet<Long>(trainingCampIds));
			Map<Long,String> campIdAndNameMap = new HashMap<Long,String>();
			if(campIdAndNameList != null && campIdAndNameList.size() > 0){
				for(Object[] param : campIdAndNameList){
					campIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
			
			//create map for campId and list of dist ids
			Map<Long,List<Long>> campIdAndDistIdsMap = new HashMap<Long,List<Long>>();
			
			campIdAndDistIdsMap.put(1L,svvCenterList);
			campIdAndDistIdsMap.put(2L,ewkCenterList);
			campIdAndDistIdsMap.put(3L,gpnCenterList);
			campIdAndDistIdsMap.put(4L,akkcCenterList);
			
			List<Long> allDistList = new ArrayList<Long>();
			allDistList.addAll(svvCenterList);
			allDistList.addAll(ewkCenterList);
			allDistList.addAll(gpnCenterList);
			allDistList.addAll(akkcCenterList);
			
			//get all constituency by passing district Ids
			
			List<Object[]> locationList = constituencyDAO.getDistrictConstituenciesList(allDistList);
			//create a map for districtId and list of constituencyId map
			Map<Long,Set<Long>> districtIdAndConstituencyIdSet = new HashMap<Long,Set<Long>>();
			Set<Long> constituencyIds = null;
			Set<Long> allConstituencies = new HashSet<Long>();
			if(locationList != null && locationList.size() > 0){
				for(Object[] param : locationList){
					allConstituencies.add(commonMethodsUtilService.getLongValueForObject(param[0]));
					constituencyIds = districtIdAndConstituencyIdSet.get(commonMethodsUtilService.getLongValueForObject(param[2]));
					if(null == constituencyIds){
						constituencyIds = new HashSet<Long>();
						districtIdAndConstituencyIdSet.put(commonMethodsUtilService.getLongValueForObject(param[2]), constituencyIds);
					}
					constituencyIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
				}
			}
			
			//convert campIdAndDistListMap to campIdAndConstituencyIdMap
			
			Map<Long,Set<Long>> campIdAndConstituencyIdMap = new HashMap<Long,Set<Long>>();
			Set<Long> constIds = null;
			if(campIdAndDistIdsMap != null && campIdAndDistIdsMap.size() > 0){
				for(Entry<Long,List<Long>> entry : campIdAndDistIdsMap.entrySet()){
					if(entry.getValue() != null && entry.getValue().size() > 0){
						constIds = new HashSet<Long>();
						for(Long distId : entry.getValue()){
							constituencyIds = districtIdAndConstituencyIdSet.get(distId);
							if(constituencyIds != null && constituencyIds.size() > 0){
								constIds.addAll(constituencyIds);
							}
						}
						campIdAndConstituencyIdMap.put(entry.getKey(), constIds);
					}
				}
			}
			//by passing constituencies get parliament
			List<Object[]> constiAndParList = parliamentAssemblyDAO.getParliamntIdByConsIds(new ArrayList<Long>(allConstituencies));
			//create a map for constituencyId and idNameVO;
			Map<Long,IdNameVO> constituencyIdAndIdNameVO = new HashMap<Long,IdNameVO>();
			IdNameVO idNameVO = null;
			if(constiAndParList != null && constiAndParList.size() > 0){
				for(Object[] param : constiAndParList){
					idNameVO = new IdNameVO();
					idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					constituencyIdAndIdNameVO.put(commonMethodsUtilService.getLongValueForObject(param[2]), idNameVO);
				}
			}
			
			Long levelId = 4L;//for constituency
			
			// first get constituency wise total invitees from (training_camp_details_info->eligible); 
			List<Object[]> inviteeList = trainingCampDetailsInfoDAO.getInviteesList(levelId,tdpCommitteeLevelIds,trainingCampProgramIds);
			
			//create a map of constituencyId and TrainingCampProgramVO 
			Map<Long,TrainingCampProgramVO> constituencyIdAndDetailsMap = new HashMap<Long,TrainingCampProgramVO>();
			if(inviteeList != null && inviteeList.size() > 0){
				for(Object[] param : inviteeList){
					campProgramVO = new TrainingCampProgramVO();
					campProgramVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					campProgramVO.setConstituency(commonMethodsUtilService.getStringValueForObject(param[1]));
					campProgramVO.setParliamentId(constituencyIdAndIdNameVO.get(campProgramVO.getId()).getId());
					campProgramVO.setParliament(constituencyIdAndIdNameVO.get(campProgramVO.getId()).getName());
					campProgramVO.setTotalEligibleCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					constituencyIdAndDetailsMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), campProgramVO);
				}
			}
			
			
			//create a map of campId and list of trainingCampVo
			Map<Long,List<TrainingCampProgramVO>> mapOfCampIdAndListOfTrainingCampProgramVO = new HashMap<Long,List<TrainingCampProgramVO>>();
			List<TrainingCampProgramVO> tempVoList = null;
			if(campIdAndConstituencyIdMap != null && campIdAndConstituencyIdMap.size() > 0){
				for(Entry<Long,Set<Long>> entryList : campIdAndConstituencyIdMap.entrySet()){
					if(entryList != null && entryList.getValue() != null && entryList.getValue().size() > 0){
						tempVoList = new ArrayList<TrainingCampProgramVO>();
						for(Long constId : entryList.getValue()){
							if(constituencyIdAndDetailsMap != null && constituencyIdAndDetailsMap.get(constId) != null){
								tempVoList.add(constituencyIdAndDetailsMap.get(constId));
							}
						}
						mapOfCampIdAndListOfTrainingCampProgramVO.put(entryList.getKey(), tempVoList);
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
						caderList = locationIdAndListOfCaders.get(commonMethodsUtilService.getLongValueForObject(param[15]));
						if(null == caderList){
							caderList = new HashSet<Long>();
							locationIdAndListOfCaders.put(commonMethodsUtilService.getLongValueForObject(param[15]), caderList);
						}
						caderList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
					}
				}
			}
			//now push the invitee attended into finalList
			if(mapOfCampIdAndListOfTrainingCampProgramVO != null && mapOfCampIdAndListOfTrainingCampProgramVO.size() > 0){
				for(Entry<Long,List<TrainingCampProgramVO>> entry : mapOfCampIdAndListOfTrainingCampProgramVO.entrySet()){
					if(entry.getValue() != null && entry.getValue().size() > 0){
						for(TrainingCampProgramVO param : entry.getValue()){
							if(locationIdAndListOfCaders != null && locationIdAndListOfCaders.get(param.getId()) != null && locationIdAndListOfCaders.get(param.getId()).size() > 0){
								param.setTotalAttenedCount(Long.parseLong(String.valueOf(locationIdAndListOfCaders.get(param.getId()).size())));
							}else{
								param.setTotalAttenedCount(0L);
							}
							param.setTotalNotAttenedCount(param.getTotalEligibleCount()-param.getTotalAttenedCount());
							param.setTotalNotAttenedCountPer(calculatePercantage(param.getTotalNotAttenedCount(),param.getTotalEligibleCount()));
						}
					}
				}
			}
			
			if(mapOfCampIdAndListOfTrainingCampProgramVO != null && mapOfCampIdAndListOfTrainingCampProgramVO.size() > 0){
				for(Entry<Long,List<TrainingCampProgramVO>> entry : mapOfCampIdAndListOfTrainingCampProgramVO.entrySet()){
					if(entry.getValue() != null && entry.getValue().size() > 0){
						Collections.sort(entry.getValue(), new Comparator<TrainingCampProgramVO>(){
							@Override
							public int compare(TrainingCampProgramVO obj1,TrainingCampProgramVO obj2) {
								Double value1 = obj1.getTotalNotAttenedCountPer();
								Double value2 = obj2.getTotalNotAttenedCountPer();
								return value2.compareTo(value1);
							}
						});
					}
				}
			}
			
			List<TrainingCampProgramVO> outputList = new ArrayList<TrainingCampProgramVO>();
			
			TrainingCampProgramVO subVo = null;
			if(mapOfCampIdAndListOfTrainingCampProgramVO != null && mapOfCampIdAndListOfTrainingCampProgramVO.size() > 0){
				for(Entry<Long,List<TrainingCampProgramVO>> entry : mapOfCampIdAndListOfTrainingCampProgramVO.entrySet()){
					if(entry.getValue() != null && entry.getValue().size() > 0){
						subVo = new TrainingCampProgramVO();
						subVo.setId(entry.getKey());//campIdAndNameMap
						subVo.setName(campIdAndNameMap.get(entry.getKey()));
						if(entry.getValue().size() > size){
							subVo.getTrainingProgramList().addAll(entry.getValue().subList(0, size));
						}else{
							subVo.getTrainingProgramList().addAll(entry.getValue());
						}
					}
					outputList.add(subVo);
				}
			}
			
			return outputList;
		}catch(Exception e){
			LOG.error("Exception raised at getListOfAssemblyWithPoorPerformance() method of TrainingCampExceptionalReportService", e);
		}
		return null;
	}
	public Double calculatePercantage(Long subCount,Long totalCount){
		Double d=0.0d;
		if(subCount.longValue()>0l && totalCount.longValue()==0l)
			LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);
	
		if(totalCount.longValue() > 0l){
			d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
		}
		return d;
	}
}
