package com.itgrids.partyanalyst.exceptionalReport.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IPartyMeetingStatusDAO;
import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.dto.PartyMeetingExceptionalReportVO;
import com.itgrids.partyanalyst.exceptionalReport.service.IPartyMeetingExceptionalReportService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class PartyMeetingExceptionalReportService implements IPartyMeetingExceptionalReportService {

	private final static Logger LOG = Logger.getLogger(PartyMeetingExceptionalReportService.class);
	
	private CommonMethodsUtilService commonMethodsUtilService;
	private IPartyMeetingStatusDAO partyMeetingStatusDAO;
	
	public void setCommonMethodsUtilService(CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
    public void setPartyMeetingStatusDAO(IPartyMeetingStatusDAO partyMeetingStatusDAO) {
		this.partyMeetingStatusDAO = partyMeetingStatusDAO;
	}

	/**
	   * @param InputVO inputVO
	   * @return PartyMeetingExceptionalReportVO 
	   * @author Santosh Kumar Verma 
	   * @Description :This Service Method is used to get party meeting location level wise report. 
	   *  @since 19-FEB-2018
	   */
		public PartyMeetingExceptionalReportVO getPartyMeetingExceptionReportMeetingLevelWise(InputVO inputVO) {
			PartyMeetingExceptionalReportVO resultVO = new PartyMeetingExceptionalReportVO();
			try {
				//preparing input parameter
				List<Long> partyMeetingLevelIds = getPartyMeetingLevelIds(inputVO);
				List<Date> dateList = getDates(inputVO.getFromDateStr(), inputVO.getToDateStr(), new SimpleDateFormat("dd/MM/yyyy"));
				inputVO.setPartyMeetingLevelIds(partyMeetingLevelIds);
				inputVO.setFromDate(dateList.get(0));
				inputVO.setToDate(dateList.get(1));
				
				//parliament wise data
				inputVO.setResultType("parliament");
				List<Object[]>  objList1 = partyMeetingStatusDAO.getPartyMeetingStatusWiseCount(inputVO);
				Map<Long,PartyMeetingExceptionalReportVO> parliamentDtlsMap = getLocationWiseMeetingCounductedDtls(objList1, inputVO);
				calculatePercentage(parliamentDtlsMap, resultVO,inputVO.getResultType());
				resultVO.setSubList2(new ArrayList<PartyMeetingExceptionalReportVO>(parliamentDtlsMap.values()));
				//sorting list
				if (resultVO.getSubList2() != null && resultVO.getSubList2().size() > 0) {
					java.util.Collections.sort(resultVO.getSubList2(), meetingDecendingCountWiseSorting);
				}
				//constituency wise data
				inputVO.setResultType("constituency");
				List<Object[]>  objList2 = partyMeetingStatusDAO.getPartyMeetingStatusWiseCount(inputVO);
				Map<Long,PartyMeetingExceptionalReportVO> constituenyDtlsMap = getLocationWiseMeetingCounductedDtls(objList2, inputVO);
				calculatePercentage(constituenyDtlsMap, resultVO,inputVO.getResultType());
				resultVO.setSubList1(new ArrayList<PartyMeetingExceptionalReportVO>(constituenyDtlsMap.values()));
				//sorting list
				if (resultVO.getSubList1() != null && resultVO.getSubList1().size() > 0) {
					java.util.Collections.sort(resultVO.getSubList1(), meetingDecendingCountWiseSorting);
				}
				//calculating overall percentage
				resultVO.setConductedPercentage(calculatePercantage(resultVO.getConductedCount(),resultVO.getTotalCount()));
				resultVO.setNotConductedPercentage(calculatePercantage(resultVO.getNotConductedCount(),resultVO.getTotalCount()));
			} catch (Exception e) {
				LOG.error("Exception occurred  at getPartyMeetingExceptionReportMeetingLevelWise() in PartyMeetingExceptionalReportService class",e);
			}
			return resultVO;
		}
	public static Comparator<PartyMeetingExceptionalReportVO> meetingDecendingCountWiseSorting = new Comparator<PartyMeetingExceptionalReportVO>() {
		     	public int compare(PartyMeetingExceptionalReportVO location2, PartyMeetingExceptionalReportVO location1) {
		     	Double per2 = location2.getPercentage();
		     	Double per1 = location1.getPercentage();
		     	//ascending order of percentage.
		     	 return per1.compareTo(per2);
		     	}
	};
     public Map<Long,PartyMeetingExceptionalReportVO> getLocationWiseMeetingCounductedDtls(List<Object[]> objList,InputVO inputVO) {
    	  Map<Long,PartyMeetingExceptionalReportVO> locationMap = new HashMap<Long, PartyMeetingExceptionalReportVO>();
    	  try {
    		   if (objList != null && objList.size() > 0 ) {
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
					   if (!locationMap.containsKey(locationId)) {
						   PartyMeetingExceptionalReportVO locationVO = new PartyMeetingExceptionalReportVO();
						   locationVO.setAddressVO(getAddressDetails(param, inputVO.getResultType()));
						   locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
						   locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
						   locationMap.put(locationVO.getLocationId(), locationVO);
					   }
					   PartyMeetingExceptionalReportVO locationVO = locationMap.get(locationId);
					   if (locationVO != null ) {
						   locationVO.setTotalCount(locationVO.getTotalCount()+totalMeetingCnt);
						   if (partyMeetingStatus.equalsIgnoreCase("Y")) {
							   locationVO.setConductedCount(locationVO.getConductedCount()+totalMeetingCnt); 
							} else if (partyMeetingStatus.equalsIgnoreCase("N") || partyMeetingStatus.equalsIgnoreCase("M")) {
							   locationVO.setNotConductedCount(locationVO.getNotConductedCount()+totalMeetingCnt);
							  
						   }
					   }
				   }
    		   }
    		  
    	  } catch (Exception e) {
    		  LOG.error("Exception occurred  at getLocationWiseMeetingCounductedDtls() in PartyMeetingExceptionalReportService class",e);
    	  }
    	  return locationMap;
     }

	public void calculatePercentage(Map<Long, PartyMeetingExceptionalReportVO> locationMap,PartyMeetingExceptionalReportVO resultVO,String resultType) {
		try {
			if (locationMap != null && locationMap.size() > 0) {
				for (Entry<Long, PartyMeetingExceptionalReportVO> entry : locationMap.entrySet()) {
					if (!resultType.equalsIgnoreCase("constituency")) {
						// calculating overall conducted meeting count
						resultVO.setConductedCount(resultVO.getConductedCount() + entry.getValue().getConductedCount());
						// calculating overall not conducted meeting count
						resultVO.setNotConductedCount(resultVO.getNotConductedCount() + entry.getValue().getNotConductedCount());
						// calculating overall meeting count
						resultVO.setTotalCount(resultVO.getTotalCount()+ entry.getValue().getTotalCount());	
					}
					entry.getValue().setPercentage(calculatePercantage(entry.getValue().getNotConductedCount(), entry.getValue().getTotalCount()));
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occurred  at calculatePercentage() in PartyMeetingExceptionalReportService class",e);
		}
	}
     public AddressVO getAddressDetails(Object[] param,String locationType) {
    	 AddressVO addressVO = new AddressVO();
    	 try {
    		 if (locationType.equalsIgnoreCase("parliament")) {
    			 addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[0]));
    			 addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[1]));
    		 } else {
    			 addressVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[0]));
    			 addressVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[1]));
    			 addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[2]));
    			 addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[3]));
    			 addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[4]));
    			 addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[5]));
    		 }
    		 
    	 } catch (Exception e) {
    		 LOG.error("Exception occurred  at setAddressDetails() in PartyMeetingExceptionalReportService class",e);
    	 }
    	 return addressVO;
     }
	public List<Long> getPartyMeetingLevelIds(InputVO inputVO) {
		List<Long> partyMeetingLevelIds = new ArrayList<Long>();
		try {
			if (inputVO.getLocationLevel() != null) {
				if (inputVO.getLocationLevel().equalsIgnoreCase("Constituency")) {
					partyMeetingLevelIds.add(3l);
				} else if (inputVO.getLocationLevel().equalsIgnoreCase("mandalTownDivision")) {
					partyMeetingLevelIds.add(4l);
					partyMeetingLevelIds.add(5l);
					partyMeetingLevelIds.add(6l);
				} else if (inputVO.getLocationLevel().equalsIgnoreCase("villageWard")) {
					partyMeetingLevelIds.add(7l);
					partyMeetingLevelIds.add(8l);
				}
			}

		} catch (Exception e) {
			LOG.error("Exception occurred  at getPartyMeetingLevelIds() in PartyMeetingExceptionalReportService class",e);
		}
		return partyMeetingLevelIds;
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
	public List<Date> getDates(String startDateString, String endDateString,SimpleDateFormat sdf) {
		List<Date> datesList = new ArrayList<Date>();
		Date startDate = null;
		Date endDate = null;
		try {
			if (startDateString != null && !startDateString.isEmpty()) {
				startDate = sdf.parse(startDateString);
			}
			if (endDateString != null && !endDateString.isEmpty()) {
				endDate = sdf.parse(endDateString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		datesList.add(0, startDate);
		datesList.add(1, endDate);
		return datesList;
	}
}
