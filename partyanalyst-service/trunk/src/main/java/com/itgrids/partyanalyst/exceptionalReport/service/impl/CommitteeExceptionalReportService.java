package com.itgrids.partyanalyst.exceptionalReport.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothInchargeCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.CommitteeDataVO;
import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.exceptionalReport.service.ICommitteeExceptionalReportService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.Util;

public class CommitteeExceptionalReportService implements ICommitteeExceptionalReportService {

	private final static Logger LOG = Logger.getLogger(CommitteeExceptionalReportService.class);
	private CommonMethodsUtilService commonMethodsUtilService;
	private ITdpCommitteeDAO tdpCommitteeDAO;
	private IBoothInchargeCommitteeDAO boothInchargeCommitteeDAO;
	
	public void setCommonMethodsUtilService(CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public void setTdpCommitteeDAO(ITdpCommitteeDAO tdpCommitteeDAO) {
		this.tdpCommitteeDAO = tdpCommitteeDAO;
	}
	public void setBoothInchargeCommitteeDAO(IBoothInchargeCommitteeDAO boothInchargeCommitteeDAO) {
		this.boothInchargeCommitteeDAO = boothInchargeCommitteeDAO;
	}
	/**
	   * @param InputVO inputVO
	   * @return CommitteeDataVO 
	   * @author Santosh Kumar Verma 
	   * @Description :This Service Method is used to get committee performance details. 
	   * @Date 22-FEB-2018
	   */
	public CommitteeDataVO getCommiteeOverviewPerformanceDetails(InputVO inputVO) {
		CommitteeDataVO resultVO = new CommitteeDataVO();
		 try {
			  inputVO.setTdpCommitteeLevelIds(getTdpCommitteeLevelIds(inputVO));//getting tdpCommitee level
			  //preparing parliament wise committee performance data
			  inputVO.setLocationLevel("parliament");
			  List<Object[]>  parliamentWisecommitteeDtlsObjLst = tdpCommitteeDAO.getLocationWiseTdpCommitteeDetails(inputVO);
			  inputVO.setResultType("completedCommittee");
			  List<Object[]> completedCommitteeObjLst = tdpCommitteeDAO.getLocationWiseTdpCommitteeDetails(inputVO);
			  //Setting into resultVO
			  resultVO.setSubList(prepareCommiteeDetailsData(parliamentWisecommitteeDtlsObjLst, completedCommitteeObjLst, resultVO, inputVO.getLocationLevel()));
			  Collections.sort(resultVO.getSubList(),commiteeDecendingCountWiseSorting);
			  //preparing constituency wise committee performance data
			  inputVO.setLocationLevel("constituency");
			  inputVO.setResultType(null);
			  List<Object[]>  constituencyWisecommitteeDtlsObjLst = tdpCommitteeDAO.getLocationWiseTdpCommitteeDetails(inputVO);
			  inputVO.setResultType("completedCommittee");
			  List<Object[]> constituencyWisecompletedCommitteeObjLst = tdpCommitteeDAO.getLocationWiseTdpCommitteeDetails(inputVO);
			  //Setting into resultVO
			  resultVO.setSubList1(prepareCommiteeDetailsData(constituencyWisecommitteeDtlsObjLst, constituencyWisecompletedCommitteeObjLst, resultVO, inputVO.getLocationLevel()));
			  Collections.sort(resultVO.getSubList1(),commiteeDecendingCountWiseSorting);
			  //OvarAll percentage
			  resultVO.setCompletedPerc(Util.calculatePercantage(resultVO.getCompletedCount(), resultVO.getTotalCount()));
			  resultVO.setNotCompletedCommitteePer(Util.calculatePercantage(resultVO.getNotCompletedCommitteeCount(), resultVO.getTotalCount()));
			  
		 } catch (Exception e) {
			 LOG.error("Exception occured at getCommiteeOverviewPerformanceDetails() in CommitteeExceptionalReportService class ",e);
		 }
		 return resultVO;
	}
	private static Comparator<CommitteeDataVO> commiteeDecendingCountWiseSorting = new Comparator<CommitteeDataVO>() {
     	public int compare(CommitteeDataVO location2, CommitteeDataVO location1) {
     	Double per2 = location2.getNotCompletedCommitteePer();
     	Double per1 = location1.getNotCompletedCommitteePer();
     	//descending  order of percentage.
     	 return per1.compareTo(per2);
     	}
	};
	public List<CommitteeDataVO> prepareCommiteeDetailsData(List<Object[]> commiteeDtlsObjLst,List<Object[]> completedCommitteeObjList,CommitteeDataVO resultVO,String locationType) {
		List<CommitteeDataVO> finalList = new ArrayList<CommitteeDataVO>();
		try {
			  if (commiteeDtlsObjLst != null && commiteeDtlsObjLst.size() > 0 ) {
				  for (Object[] param : commiteeDtlsObjLst) {
					  CommitteeDataVO locationVO = new CommitteeDataVO();
					  locationVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[0]));
					  locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
					  locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
					  locationVO.setNotCompletedCommitteeCount(locationVO.getTotalCount());//default
					  //setting address
					  locationVO.setAddressVO(getAddressDetails(param, locationType));
					  Object[] matchObjArr = getMatchObjectArr(completedCommitteeObjList, locationVO.getId());
					   if (matchObjArr != null && matchObjArr.length > 0) {
						   locationVO.setCompletedCount(commonMethodsUtilService.getLongValueForObject(matchObjArr[0]));
						   locationVO.setNotCompletedCommitteeCount(locationVO.getTotalCount()-locationVO.getCompletedCount());
					   }
					   locationVO.setNotCompletedCommitteePer(Util.calculatePercantage(locationVO.getNotCompletedCommitteeCount(), locationVO.getTotalCount()));
					   //calculating overall committee details
					   if (locationType.equalsIgnoreCase("parliament")) {
						   resultVO.setTotalCount(resultVO.getTotalCount()+locationVO.getTotalCount());
						   resultVO.setNotCompletedCommitteeCount(resultVO.getNotCompletedCommitteeCount()+locationVO.getNotCompletedCommitteeCount());
						   resultVO.setCompletedCount(resultVO.getCompletedCount()+locationVO.getCompletedCount());
					   }
					   finalList.add(locationVO);
				}
			  }
			
		} catch (Exception e) {
			 LOG.error("Exception occured at prepareCommiteeDetailsData() in CommitteeExceptionalReportService class ",e);
		}
		return finalList;
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
			  Collections.sort(resultVO.getSubList1(),commiteeDecendingCountWiseSorting);
			  //OvarAll percentage
			  resultVO.setCompletedPerc(Util.calculatePercantage(resultVO.getCompletedCount(), resultVO.getTotalCount()));
			  resultVO.setNotCompletedCommitteePer(Util.calculatePercantage(resultVO.getNotCompletedCommitteeCount(), resultVO.getTotalCount()));
		 } catch (Exception e) {
			 LOG.error("Exception occured at getBoothInchargeCommitteePerformanceDetails() in CommitteeExceptionalReportService class ",e);
		 }
		 return resultVO;
	}
}
