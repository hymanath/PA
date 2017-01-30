package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.Map;

import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.PMMinuteVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.ToursInputVO;
import com.itgrids.partyanalyst.dto.ToursNewVO;
import com.itgrids.partyanalyst.dto.ToursVO;

import java.util.List;

import com.itgrids.partyanalyst.dto.ToursBasicVO;

public interface IToursService {
	 public ResultStatus saveTourDtls(ToursInputVO toursInputVO,Long userId, Map<File,String> mapfiles);
	 public List<ToursBasicVO> getDesigationList();
	 public List<ToursBasicVO> getConstituenciesList(Long stateId);
	 public List<ToursBasicVO> getCandidateList(Long designationId);
	 public ToursBasicVO getCandiateDetails(Long candidateId);
	 public List<ToursBasicVO> getSearchMembersDetails(Long locationId,String searchType,String searchValue,Long designationId);
	 public List<ToursBasicVO> getToursDetailsOverview(String fromDateStr,String toDateStr);
	 public ToursBasicVO getUniqueMemDtls(Long candidateDtlsId);
	 public ToursBasicVO getDesignationDtls(Long desigId, String startDateStr, String endDateStr);
	 public List<ToursBasicVO> getMemDtls(Long desigId, String startDateStr, String endDateStr);
	 public ResultStatus updateTourDtls(ToursInputVO toursInputVO,Long userId, Map<File,String> mapfiles);
	 public List<IdNameVO> getAllTourTypes();
	 public List<IdNameVO> getAllTourCategorys(Long cadreId,Long designationId);
	 public PMMinuteVO getAllCandidateLocations(Long cadreId,Long categoryId);
	 public ResultStatus saveNewTourDetails(final ToursVO toursVo,final Map<File, String> documentMap);
	 public List<ToursBasicVO> getToursDetailsOverviewForNew(String fromDateStr,String toDateStr);
	 public ToursBasicVO getCandidateDetailedReport(Long candidateId,Long designationId,String fromDate,String toDate);
	 public PMMinuteVO getNewTourRetrivalDetails(Long candidateDayTourId);
	 public List<ToursBasicVO> getMemberDetailsByDesignationWise(String fromDateStr,String toDateStr,Long designationId,Long candidateId);
	 public List<ToursBasicVO> getTourBasicOverviewDtlsDesignationWise(String fromDateStr,String toDateStr,List<Long> designationIds);
	 public String deleteDocumentByDocument(List<Long> documentIds);
	 public ResultStatus checkForExistingTourDetails(ToursVO toursVo);
	 public List<IdNameVO> getDesigationsListByCadreId(Long tdpCadreId);
	 public ToursVO  getToursDetailsBySearch(Long tdpCadreId,Long stateId,String startDateStr,String endDateStr,String searchType,Long designationId,String searchMonth);
	 public ToursVO  getCandidateToursDetailsBySearch(Long tdpCadreId,Long stateId,String startDateStr,String endDateStr,String searchType,Long designationId,Long categoryId,String searchMonth);
	 public ToursBasicVO getCadreTourDetails(Long tdpCadreId,String fromDateStr,String toDateStr);
	 public ResultStatus saveDesignationWiseTourDetails(final ToursVO toursVo,final Map<File, String> documentMap);
}