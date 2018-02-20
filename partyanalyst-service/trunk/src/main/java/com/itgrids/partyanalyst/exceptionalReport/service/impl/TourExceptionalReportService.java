package com.itgrids.partyanalyst.exceptionalReport.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsNewDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateLocationNewDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalToursMonthDAO;
import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.dto.ToursOverviewDtlsvO;
import com.itgrids.partyanalyst.exceptionalReport.service.ITourExceptionalReportService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.Util;

public class TourExceptionalReportService implements ITourExceptionalReportService {

	private final static Logger LOG = Logger.getLogger(TourExceptionalReportService.class);
	
	private CommonMethodsUtilService commonMethodsUtilService;
	private ISelfAppraisalCandidateLocationNewDAO selfAppraisalCandidateLocationNewDAO;
	private ISelfAppraisalToursMonthDAO selfAppraisalToursMonthDAO;
	private ISelfAppraisalCandidateDetailsNewDAO selfAppraisalCandidateDetailsNewDAO;

	public void setCommonMethodsUtilService(CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public void setSelfAppraisalCandidateLocationNewDAO(ISelfAppraisalCandidateLocationNewDAO selfAppraisalCandidateLocationNewDAO) {
		this.selfAppraisalCandidateLocationNewDAO = selfAppraisalCandidateLocationNewDAO;
	}

	public void setSelfAppraisalToursMonthDAO(
			ISelfAppraisalToursMonthDAO selfAppraisalToursMonthDAO) {
		this.selfAppraisalToursMonthDAO = selfAppraisalToursMonthDAO;
	}

	public void setSelfAppraisalCandidateDetailsNewDAO(
			ISelfAppraisalCandidateDetailsNewDAO selfAppraisalCandidateDetailsNewDAO) {
		this.selfAppraisalCandidateDetailsNewDAO = selfAppraisalCandidateDetailsNewDAO;
	}
	 /**
	   * @param InputVO inputVO
	   * @return ToursOverviewDtlsvO 
	   * @author Santosh Kumar Verma 
	   * @Description :This Service Method is used to get designation wise tour submitted details. 
	   * @Date 20-FEB-2018
	   */
    public ToursOverviewDtlsvO getDesignationWiseTourSubmittedOverviewDtls(InputVO inputVO) {
    	 ToursOverviewDtlsvO resultVO = new ToursOverviewDtlsvO();
    	try {
    		List<Date> datesList = Util.getDates(inputVO.getFromDateStr(), inputVO.getToDateStr(),new SimpleDateFormat("dd/MM/yyyy"));
    		inputVO.setFromDate(datesList.get(0));
    		inputVO.setToDate(datesList.get(1));
    		 //getting MonthYearIds
			List<Long> monthyearIds =  getMonthYearIds(inputVO.getFromDate(), inputVO.getToDate());
			List<Object[]> tourCandidateDtlsObjList = selfAppraisalCandidateLocationNewDAO.getAllTourCandiateDetails(inputVO);
			List<Long> totalTourSubmittedCadreIds = null;
			if (monthyearIds != null && monthyearIds.size() > 0 ) {
    		    totalTourSubmittedCadreIds = new ArrayList<Long>();
    			List<Long> before15ThTourSubmittedCadreList      = selfAppraisalCandidateDetailsNewDAO.getUniqueCandidateTourSubmittedByTimePeriod(monthyearIds, "before15days");
        		List<Long> after15ThTourSubmittedCadreList      = selfAppraisalCandidateDetailsNewDAO.getUniqueCandidateTourSubmittedByTimePeriod(monthyearIds, "after15days");
        		totalTourSubmittedCadreIds.addAll(before15ThTourSubmittedCadreList);
        		totalTourSubmittedCadreIds.addAll(after15ThTourSubmittedCadreList);
        		resultVO.setBefore15thDateTourSubmittedCoun(Long.valueOf(before15ThTourSubmittedCadreList.size()));
        		resultVO.setAfter15thDateTourSubmittedCoun(Long.valueOf(after15ThTourSubmittedCadreList.size()));
        		resultVO.setUniqueCandidateSubmittedCount(resultVO.getBefore15thDateTourSubmittedCoun()+resultVO.getAfter15thDateTourSubmittedCoun());
         	}
			 //setNotSubmittedCandidateDtls
    		 setNotSubmittedCadreDetails(tourCandidateDtlsObjList,totalTourSubmittedCadreIds,resultVO);
    		//Designation wise tour submitted details
    		 List<Object[]> candidateObjList = selfAppraisalCandidateLocationNewDAO.getDeisgnationWiseTourCandidate(inputVO);
    		 //getting designation wise candidate details
    		 Map<Long,ToursOverviewDtlsvO> designationMap = getDesignationWiseCandidateDtls(candidateObjList);
    		 
    		 if (monthyearIds != null && monthyearIds.size() > 0 ) {
    			 List<Object[]> befr15ThTourSubmittedCadreList = selfAppraisalCandidateDetailsNewDAO.getDesignationWiseTourSubmittedCandidateByTimePeriod(monthyearIds, "before15days");
    			 List<Object[]> aftr15ThTourSubmittedCadreList = selfAppraisalCandidateDetailsNewDAO.getDesignationWiseTourSubmittedCandidateByTimePeriod(monthyearIds, "after15days");
    			 //setting tour submitted candidate details designation wise
    			 setSubmittedCandidateDetails(designationMap, befr15ThTourSubmittedCadreList, "before15days");
    			 setSubmittedCandidateDetails(designationMap, aftr15ThTourSubmittedCadreList, "after15days");
    		 }
    		 //prepare required data
    		 prepareRequiredData(designationMap, resultVO);
    		 //preparing final designation wise list
    		  resultVO.setSubList2(new ArrayList<ToursOverviewDtlsvO>(designationMap.values()));
    		 //ovarlAll percentage
    		 resultVO.setSubmittedPer(Util.calculatePercantage(resultVO.getSubmittedCandiateCount(), resultVO.getTotalCandiateCount()));
    		 resultVO.setNotSubmittedPer(Util.calculatePercantage(resultVO.getNotSubmittedCandidateCount(), resultVO.getTotalCandiateCount()));
    		  
    		
    	} catch (Exception e) {
    		LOG.error("Exception occured at getDesignationWiseTourSubmittedOverviewDtls() in TourExceptionalReportService class",e);
    	}
    	return resultVO;
    }
     public void prepareRequiredData(Map<Long,ToursOverviewDtlsvO> designationMap,ToursOverviewDtlsvO resultVO) {
    	  try {
    		   if (designationMap != null && designationMap.size() > 0 ){
    			   for (Entry<Long, ToursOverviewDtlsvO> designationEntry : designationMap.entrySet()) {
					 designationEntry.getValue().setSubmittedCandiateCount(designationEntry.getValue().getBefore15thDateTourSubmittedCoun()+designationEntry.getValue().getAfter15thDateTourSubmittedCoun());
					 designationEntry.getValue().setNotSubmittedCandidateCount(designationEntry.getValue().getTotalCandiateCount()-designationEntry.getValue().getSubmittedCandiateCount());
					 designationEntry.getValue().setNotSubmittedPer(Util.calculatePercantage(designationEntry.getValue().getNotSubmittedCandidateCount(),designationEntry.getValue().getTotalCandiateCount()));
					 designationEntry.getValue().setSubmittedPer(Util.calculatePercantage(designationEntry.getValue().getSubmittedCandiateCount(),designationEntry.getValue().getTotalCandiateCount()));
    			     //overall 
					 resultVO.setSubmittedCandiateCount(resultVO.getSubmittedCandiateCount()+designationEntry.getValue().getSubmittedCandiateCount());
					 resultVO.setNotSubmittedCandidateCount(resultVO.getNotSubmittedCandidateCount()+designationEntry.getValue().getNotSubmittedCandidateCount());
					 resultVO.setTotalCandiateCount(resultVO.getTotalCandiateCount()+designationEntry.getValue().getTotalCandiateCount());
    			   }
    			   
    		   }
    	  } catch (Exception e) {
    		  LOG.error("Exception occured at prepareRequiredData() in TourExceptionalReportService class",e);
    	  }
     }
	private void setNotSubmittedCadreDetails(List<Object[]> candidateObjList,List<Long> totalTourSubmittedCadreIds, ToursOverviewDtlsvO resultVO) {
		try {
			if (totalTourSubmittedCadreIds != null && totalTourSubmittedCadreIds.size() > 0) {
				resultVO.setSubList1(new ArrayList<ToursOverviewDtlsvO>(0));
				Set<Long> tempCadreIdSet = new HashSet<Long>();
				for (Object[] param : candidateObjList) {
					Long cadreId = commonMethodsUtilService.getLongValueForObject(param[3]);
					tempCadreIdSet.add(cadreId);// cadreId
					if (totalTourSubmittedCadreIds != null && totalTourSubmittedCadreIds.size() > 0) {
						if (!totalTourSubmittedCadreIds.contains(cadreId)) {
							//setting not submitted candidate details
							resultVO.getSubList1().add(getCandidateDetails(param));
						}
					} else {
						//setting not submitted candidate details
						resultVO.getSubList1().add(getCandidateDetails(param));
					}
				}
				resultVO.setTotalUniqueCandidateCount(Long.valueOf(tempCadreIdSet.size()));// total unique candidate
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getNotSubmittedCadreObjList() in TourExceptionalReportService class",e);
		}
	}
	private ToursOverviewDtlsvO getCandidateDetails(Object[] param) {
		ToursOverviewDtlsvO candidateVO = new ToursOverviewDtlsvO();
		 try {
			 if (param != null && param.length > 0 ) {
				 candidateVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(param[0]));
				 candidateVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[1]));
				 candidateVO.setCandidateId(commonMethodsUtilService.getLongValueForObject(param[2]));
				 candidateVO.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(param[3]));
				 candidateVO.setName(commonMethodsUtilService.getStringValueForObject(param[4]));
				 candidateVO.setAddressVO(getAddressDetails(param));
			 }
			 
		 } catch (Exception e) {
			 LOG.error("Exception occured at getCandidateDetails() in TourExceptionalReportService class",e);
		 }
		 return candidateVO;
	}

	private AddressVO getAddressDetails(Object[] param) {
		AddressVO addressVO = new AddressVO();
		try {
			addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[5]));
			addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[6]));
			addressVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[7]));
			addressVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[8]));
			addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[9]));
			addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[10]));

		} catch (Exception e) {
			LOG.error("Exception occurred  at setAddressDetails() in PartyMeetingExceptionalReportService class",e);
		}
		return addressVO;
	}

	private Map<Long, ToursOverviewDtlsvO> getDesignationWiseCandidateDtls(List<Object[]> objList) {
		Map<Long, ToursOverviewDtlsvO> designationMap = new LinkedHashMap<Long, ToursOverviewDtlsvO>(0);
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					ToursOverviewDtlsvO designationVO = new ToursOverviewDtlsvO();
					designationVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(param[0]));
					designationVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[1]));
					designationVO.setTotalCandiateCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					designationVO.setNotSubmittedCandidateCount(designationVO.getTotalCandiateCount());
					designationMap.put(designationVO.getDesignationId(),designationVO);
				}
			}

		} catch (Exception e) {
			LOG.error("Exception occurred  at getDesignationWiseCandidateDtls() in PartyMeetingExceptionalReportService class",e);
		}
		return designationMap;
	}
	public void setSubmittedCandidateDetails(Map<Long,ToursOverviewDtlsvO> designationMap,List<Object[]> objList,String timePeriod) {
		try {
			 if (objList != null && objList.size() > 0) {
				 for (Object[] param : objList) {
					ToursOverviewDtlsvO designationVO = designationMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					 if (designationVO != null ) {
						 if (timePeriod.equalsIgnoreCase("before15days")) {
							 designationVO.setBefore15thDateTourSubmittedCoun(commonMethodsUtilService.getLongValueForObject(param[1]));
						 } else if (timePeriod.equalsIgnoreCase("after15days")) {
							 designationVO.setAfter15thDateTourSubmittedCoun(commonMethodsUtilService.getLongValueForObject(param[1]));
						 }
					 }
				}
			 }
		} catch (Exception e) {
			LOG.error("Exception occurred  at setSubmittedCandidateDetails() in PartyMeetingExceptionalReportService class",e);
		}
	}
	private List<Long> getMonthYearIds(Date fromDate,Date toDate) {
		List<Long> monthYearIds = new ArrayList<Long>(0);
		 try {
			  //Get month year in string format based on fromDate and toDate
			   List<String> monthYear = selfAppraisalToursMonthDAO.getMonthAndYear(fromDate, toDate);
				 //Get month year ids based on month year 
			     monthYearIds = selfAppraisalToursMonthDAO.getMonthYearByTourMonths(monthYear);
		 } catch (Exception e) {
			 LOG.error("Exception occured at getMonthYearIds() in TourExceptionalReportService class",e);
		 }
		 return monthYearIds;
	}
	
}
