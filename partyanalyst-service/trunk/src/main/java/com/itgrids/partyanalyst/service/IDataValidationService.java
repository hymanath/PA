package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.DataMappingVerificationVO;
import com.itgrids.partyanalyst.dto.DataVerificationVO;
import com.itgrids.partyanalyst.dto.ElectionResultsVerificationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.DataValidationVO;

public interface IDataValidationService {
	
	public List<DataValidationVO> getHamletsAssignedValidation(Long constituencyId,Long publicationDateId,Long userId);
	
	public DataVerificationVO validateVotersBasicInfo(Long constituencyId,Long publicationId,Long userId);
	
	public ElectionResultsVerificationVO validateConstituencyEleResults(Long electionId);
	
	public List<DataMappingVerificationVO> validatePanchayatMappingDataInBooth(Long constituencyId,Long publicationId);
	
	 public List<SelectOptionVO> getEleYears();
	 
	 public List<SelectOptionVO> getConstituenciesByEleId(Long electionId);
	 
	 public List<DataMappingVerificationVO> validatePanchayatData(Long constituencyId, Long electionId,Long eleYear);
	
	 public List<DataValidationVO> getUnMappedBoothsList(Long electionId);

}
