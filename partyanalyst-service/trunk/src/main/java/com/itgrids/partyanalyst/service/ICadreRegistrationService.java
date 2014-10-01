package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterInfoVO;

public interface ICadreRegistrationService {

	public ResultStatus saveCadreRegistration(final CadreRegistrationVO cadreRegistrationVO,String registrationType);
	
	public List<VoterInfoVO> getSearchDetailsCadreRegistration(Long constituencyId, String seachType, String candidateName, String voterCardId, String houseNo,Long panchayatId,Long boothId,String villagesCovered);
	
	public List<VoterInfoVO> getCandidateInfoBySearchCriteria(String searchType, Long candidateId);
	
	public List<GenericVO> getBoothCoverdVillagesDetails(List<Long> boothIds);
	
	public List<GenericVO> getBoothForPanchayats(Long constituencyId, Long locationId);
	
	public List<GenericVO> getConstiteuncyDetailsByConstiteuncy(Long constituencyId);
	
	public List<SelectOptionVO> getOptionDetailsForCadre();	
	
	public List<SelectOptionVO> getElectionOptionDetailsForCadre();
	
}
