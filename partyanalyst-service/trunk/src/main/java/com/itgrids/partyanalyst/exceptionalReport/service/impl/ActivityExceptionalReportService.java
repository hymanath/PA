package com.itgrids.partyanalyst.exceptionalReport.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityInfoDocumentDAO;
import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
import com.itgrids.partyanalyst.dto.ActivityExceptionalReportVO;
import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.exceptionalReport.service.IActivityExceptionalReportService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.Util;

public class ActivityExceptionalReportService implements IActivityExceptionalReportService {

	private final static Logger LOG = Logger.getLogger(ActivityExceptionalReportService.class);
	
	private CommonMethodsUtilService commonMethodsUtilService;
	private IActivityLocationInfoDAO activityLocationInfoDAO;
	private IActivityInfoDocumentDAO activityInfoDocumentDAO;
	
	public void setCommonMethodsUtilService(CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public void setActivityLocationInfoDAO(IActivityLocationInfoDAO activityLocationInfoDAO) {
		this.activityLocationInfoDAO = activityLocationInfoDAO;
	}
	public void setActivityInfoDocumentDAO(IActivityInfoDocumentDAO activityInfoDocumentDAO) {
		this.activityInfoDocumentDAO = activityInfoDocumentDAO;
	}
	/**
	   * @param InputVO inputVO
	   * @return ActivityExceptionalReportVO 
	   * @author Santosh Kumar Verma 
	   * @Description :This Service Method is used to get activity performance details location wise. 
	   * @Date 21-FEB-2018
	   */
	public ActivityExceptionalReportVO getActivityPerformanceDetailsLocationWise(InputVO inputVO) {
		ActivityExceptionalReportVO resultVO = new ActivityExceptionalReportVO();
		 try {
			 //preparing parliament wise activities details data
			 List<Object[]> parliamentWiseActivityDtlsObjList = activityLocationInfoDAO.getLocationWiseActiviyDetailsByType(inputVO.getActivityScopeId(), "parliament", "total");
			 List<Object[]> prlmntWsActvtyCncctdDtlsObjLst = activityLocationInfoDAO.getLocationWiseActiviyDetailsByType(inputVO.getActivityScopeId(), "parliament", "conducted");
			 Map<Long,ActivityExceptionalReportVO> parliamentDtlsMap = getLocationWiseActivityDtls(parliamentWiseActivityDtlsObjList, prlmntWsActvtyCncctdDtlsObjLst, "parliament");
			 resultVO.setSubList2(new ArrayList<ActivityExceptionalReportVO>(parliamentDtlsMap.values()));
			 Collections.sort(resultVO.getSubList2(), activityDecendingCountWiseSorting);
			 //preparing constituency wise activities details data 
			 List<Object[]> constituencyWiseActivityDtlsObjList = activityLocationInfoDAO.getLocationWiseActiviyDetailsByType(inputVO.getActivityScopeId(), "constituency", "total");
			 List<Object[]> cnsttncyWsActvtyCncctdDtlsObjLst = activityLocationInfoDAO.getLocationWiseActiviyDetailsByType(inputVO.getActivityScopeId(), "constituency", "conducted");
			 Map<Long,ActivityExceptionalReportVO> constituencyDtlsMap = getLocationWiseActivityDtls(constituencyWiseActivityDtlsObjList, cnsttncyWsActvtyCncctdDtlsObjLst, "constituency");
			 resultVO.setSubList1(new ArrayList<ActivityExceptionalReportVO>(constituencyDtlsMap.values()));
			 Collections.sort(resultVO.getSubList1(), activityDecendingCountWiseSorting);
		 } catch (Exception e) {
			 LOG.error("Exception occured at getActivityPerformanceDetailsLocationWise() in ActivityExceptionalReportService class",e);
		 }
		 return resultVO;
	}
	private static Comparator<ActivityExceptionalReportVO> activityDecendingCountWiseSorting = new Comparator<ActivityExceptionalReportVO>() {
     	public int compare(ActivityExceptionalReportVO location2, ActivityExceptionalReportVO location1) {
     	Double per2 = location2.getPercentage();
     	Double per1 = location1.getPercentage();
     	//descending  order of percentage.
     	 return per1.compareTo(per2);
     	}
	};
	private Map<Long,ActivityExceptionalReportVO> getLocationWiseActivityDtls(List<Object[]> totalLocationDtlsObjList,List<Object[]> conductedObjList,String locationType) {
		Map<Long,ActivityExceptionalReportVO> locationMap = new HashMap<Long, ActivityExceptionalReportVO>(0);
		try {
			 if (totalLocationDtlsObjList != null && totalLocationDtlsObjList.size() > 0) {
				 for (Object[] param : totalLocationDtlsObjList) {
					 ActivityExceptionalReportVO locationVO = new ActivityExceptionalReportVO();
					 locationVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[0]));
					 locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[1]));
					 locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[2]));
					 //set not conducted activity location
					 Object[] matchObjArr = getMatchObjectArr(conductedObjList, locationVO.getLocationId());
					 if (matchObjArr != null ) {
						 Long conductedCount = commonMethodsUtilService.getLongValueForObject(matchObjArr[0]);
						 locationVO.setNotConductedCount(locationVO.getTotalCount() - conductedCount);
						 locationVO.setPercentage(Util.calculatePercantage(locationVO.getNotConductedCount(), locationVO.getTotalCount()));
					 }
					 //setting address
					 locationVO.setAddressVO(getAddressDetails(param, locationType));
					 locationMap.put(locationVO.getLocationId(), locationVO);
				}
			 }
			
		} catch (Exception e) {
			LOG.error("Exception occured at getLocationWiseActivityDtls() in ActivityExceptionalReportService class",e);
		}
		return locationMap;
	}
	private AddressVO getAddressDetails(Object[] param,String locationType) {
   	 AddressVO addressVO = new AddressVO();
   	 try {
   		 if (locationType.equalsIgnoreCase("parliament")) {
   			 addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[1]));
   			 addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[2]));
   		 } else {
   			 addressVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[1]));
   			 addressVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[2]));
   			 addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[3]));
   			 addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[4]));
   			 addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[5]));
   			 addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[6]));
   		 }
   		 
   	 } catch (Exception e) {
   		 LOG.error("Exception occurred  at setAddressDetails() in ActivityExceptionalReportService class",e);
   	 }
   	 return addressVO;
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
			LOG.error("Exception occurred  at getMatchObjectArr() in ActivityExceptionalReportService class",e);
		}
		return null;
	}
	 /**
	   * @param InputVO inputVO
	   * @return ActivityExceptionalReportVO 
	   * @author Santosh Kumar Verma 
	   * @Description :This Service Method is used to get activity attended and image covered details location wise. 
	   * @Date 21-FEB-2018
	   */
	public ActivityExceptionalReportVO getActivityAttendedAndImageCoveredDetails(InputVO inputVO) {
		ActivityExceptionalReportVO resultVO = new ActivityExceptionalReportVO();
		 try {
			 //activity attended member details
			 List<Object[]> activityAttendedDtlsObjLst = activityLocationInfoDAO.getMPPChairmanMayorAttenedDetatils(inputVO.getActivityScopeId());
			 resultVO.setSubList1(prepareRequiredData(activityAttendedDtlsObjLst));
			 Collections.sort(resultVO.getSubList1(), activityAttendedMemberAscendingCountWiseSorting);
			 //image covered location details
			 List<Object[]> coveredImageDtlsObjList = activityInfoDocumentDAO.getCoveredImagedConstitiency(inputVO.getActivityScopeId());
			 resultVO.setSubList2(prepareRequiredData(coveredImageDtlsObjList));
			 Collections.sort(resultVO.getSubList2(), activityAttendedMemberAscendingCountWiseSorting);
		 } catch (Exception e) {
			 LOG.error("Exception occurred  at getActivityAttendedAndImageCoveredDetails() in ActivityExceptionalReportService class",e);
		 }
		 return resultVO;
	}
	private static Comparator<ActivityExceptionalReportVO> activityAttendedMemberAscendingCountWiseSorting = new Comparator<ActivityExceptionalReportVO>() {
     	public int compare(ActivityExceptionalReportVO location2, ActivityExceptionalReportVO location1) {
     	Long count2 = location2.getTotalCount();
     	Long count1 = location1.getTotalCount();
     	//Ascending  order of percentage.
     	 return count2.compareTo(count1);
     	}
	};
	private List<ActivityExceptionalReportVO> prepareRequiredData(List<Object[]> objList) {
		List<ActivityExceptionalReportVO> finalList = new ArrayList<ActivityExceptionalReportVO>(0);
		 try {
			  if (objList != null && objList.size() > 0) {
				  for (Object[] param : objList) {
					ActivityExceptionalReportVO locationVO = new ActivityExceptionalReportVO();
					 locationVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[4]));
					 AddressVO addressVO = new AddressVO();
					 addressVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[0]));
		   			 addressVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[1]));
		   			 addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[2]));
		   			 addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[3]));
		   			 locationVO.setAddressVO(addressVO);
		   			finalList.add(locationVO);
				}
			  }
		 } catch (Exception e) {
			 LOG.error("Exception occurred  at prepareRequiredData() in ActivityExceptionalReportService class",e);
		 }
		 return finalList;
	}
 }
