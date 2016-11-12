package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.Map;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.ToursInputVO;

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
}
