package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadrePrintVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyCadreResponceVO;
import com.itgrids.partyanalyst.dto.VoterInfoVO;

public interface ICadreRegistrationService {

	public SurveyCadreResponceVO saveCadreRegistration(final List<CadreRegistrationVO> cadreRegistrationVO,String registrationType);
	
	public List<VoterInfoVO> getSearchDetailsCadreRegistration(Long constituencyId, String seachType, String candidateName, String voterCardId, String houseNo,Long panchayatId,Long boothId,String villagesCovered);
	
	public List<VoterInfoVO> getCandidateInfoBySearchCriteria(String searchType, Long candidateId,String staticContentLoc,String constiteucnyId);
	
	public List<GenericVO> getBoothCoverdVillagesDetails(List<Long> boothIds);
	
	public List<GenericVO> getBoothForPanchayats(Long constituencyId, Long locationId);
	
	public List<GenericVO> getConstiteuncyDetailsByConstiteuncy(Long constituencyId);
	
	public List<SelectOptionVO> getOptionDetailsForCadre();	
	
	public List<SelectOptionVO> getElectionOptionDetailsForCadre();
	
	public List<SelectOptionVO> getElectionYearsByElectionType(Long electionTypeId);

	public List<GenericVO> getExistingCadreInfo(String candidateName,Long constituencyId,Long panchayatId,Long boothId,String isPresentCadre, String enrollmentNo);
	
	public CadrePrintVO getCadreDetailsForPrinting(String memberCardNo);
	
	public List<CadrePrintVO> getSelectedLevelCadreDetails(Long panchayatId);
	
	public String tagCardIdForNFCReader(String cardNumber,Long voterId);
	
	public ResultStatus saveNewCadreSurveyUser(final Long userId, final String surveyUserName, final String  password, final String mobileNo);

	public List<GenericVO> getSurveyCadreUsersList();
	
	public List<SelectOptionVO> getSurveyCadreAssignedConstituencyList();
	
	public List<GenericVO> getSurveyCadreAssignedUsersList(Long constiteuncyId);
	
	public ResultStatus releaseCadreSurveyUser(final Long cadreSurveyUserAssignedId);
	
	public ResultStatus assignUserForLocation(final Long surveyUserId, final Long levelId, final Long levelValue,final Long constituencyId,final String TabNo);
	
	public List<SelectOptionVO> getSubRegionsInConstituency(Long constituencyId, String scope) ;
	
	public String isTabAssignedAlready(String TabNo);
	
	public List<BasicVO> constituencyListForElection(Long electionId,Long constituencyId);
	
	public List<BasicVO> participatedCandList(Long electionId,Long constituencyId);
	
	public List<GenericVO> getCandidateDetailsForElection(Long candidateId, Long electionId);
	
	public List<SelectOptionVO> getCadreLevelsForCadreSearch();
	
	public List<SelectOptionVO> getCandidateInfoByNomination(Long electionId,Long nominationId);	
	public List<SelectOptionVO> getAllRelationDetails();
	
	public List<SelectOptionVO> getCadreCommitteRoles(Long levelId,Long committeeId);
	
	public List<SelectOptionVO> getCadreCommitteDetails(Long levelId);
		
	public String getCadreImageByPreviousEnrolId(String enrolmentId,String staticContentLoc);
	
	public List<GenericVO> getBoothsForMultipleLocations(Long constituencyId, List<Long> locationIds);
	
	public String checkNFCNumberForVoterId(Long voterId);
}
