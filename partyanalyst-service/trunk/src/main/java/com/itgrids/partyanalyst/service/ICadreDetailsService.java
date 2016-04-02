package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreOverviewVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.ComplaintStatusCountVO;
import com.itgrids.partyanalyst.dto.GrievanceAmountVO;
import com.itgrids.partyanalyst.dto.IVRResponseVO;
import com.itgrids.partyanalyst.dto.IvrOptionsVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.QuestionAnswerVO;
import com.itgrids.partyanalyst.dto.NtrTrustStudentVO;
import com.itgrids.partyanalyst.dto.RegisteredMembershipCountVO;
import com.itgrids.partyanalyst.dto.TdpCadreFamilyDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.VerifierVO;
import com.itgrids.partyanalyst.dto.WebServiceResultVO;

public interface ICadreDetailsService {
	/*public TdpCadreVO searchTdpCadreDetailsBySearchCriteriaForCommitte(Long locationLevel,Long locationValue, String searchName,String memberShipCardNo,
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategoryId,Long fromAge,Long toAge,String houseNo,String gender);*/
	public TdpCadreVO tdpCadreCastewiseCountDetailsBySearchCriteriaForCommitte(Long locationLevel,Long locationValue, String searchName,String memberShipCardNo, 
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,Long fromAge,Long toAge,String houseNo,String gender);
	public TdpCadreVO searchTdpCadreDetailsBySearchCriteriaForCommitte(Long locationLevel,Long locationValue, String searchName,String memberShipCardNo, 
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,Long fromAge,Long toAge,String houseNo,String gender,int startIndex,int maxIndex,boolean isRemoved);
	public CadreCommitteeMemberVO cadreFormalDetailedInformation(Long cadreId,Long enrollmentYear,Long memberTypeId);
	public CadreCommitteeMemberVO complaintDetailsOfCadre(Long cadreId,String membershipId);
	public List<CadreCommitteeMemberVO> getEventDetailsOfCadre(Long cadreId);
	public VerifierVO getTdpCadreSurveyDetails(Long cadreId,Long surveyId,String searchTypeStr,Long boothId,String isPriority,String voterCardNo,Long constituencyId,String constiTypeStr);
	public List<CandidateDetailsVO>  getCandidateElectDetatails(Long cadreId);
	
	public RegisteredMembershipCountVO getTotalMemberShipRegistrationsInCadreLocation(Long cadreId,Long pcId,String pcType);
	
	public List<RegisteredMembershipCountVO> getElectionPerformanceInCadreLocation(Long tdpCadreId,String voterCardNo);
	
	public List<GrievanceAmountVO> getApprovedFinancialSupprotForCadre(Long tdpCadreId);
	
	public List<TdpCadreFamilyDetailsVO> getCadreFamilyDetails(Long tdpCadreId);
	
	public WebServiceResultVO getCandidateAndLocationSummaryNews(String startDate,String endDate,String locationType,Long locationId,Long candidateId);
	
	public Long getCadreIdByMembershipId(String memberShipNo,Long constituencyId);
	
	public ComplaintStatusCountVO getCategoryWiseStatusCount(Long tdpCadreId);
	
	public List<CommitteeBasicVO> getLocationwiseCommitteesCount(String locationType,Long tdpCadreId,String electionType,Long locationId);
	
	public VerifierVO getDeathsAndHospitalizationDetails(Long panchayatId,Long mandalId,Long constituencyId,Long parliamentId,Long districtId);
	
	public Long getTdpCadreIdBymembershipId(String membershipId);
	
	public CadreCommitteeMemberVO getCadresDetailsOfDeathsAndHospitalization(Long locationId,String locationType,Long insuranceTypeId);
	public CadreOverviewVO getTdpcadreDetailsByTdpCadreId(Long tdpCadreId,String houseNo, String voterCardNo);
	public BasicVO getParticipatedConstituency(Long tdpCadreId);
	public List<QuestionAnswerVO> getCandidateAndConstituencySurveyResult(Long candidateId,Long constituencyId,Long surveyId);
	public NtrTrustStudentVO getNtrTrustStudentDetailsInstitutionWise(List<Long> tdpCadreIds);
	public List<NtrTrustStudentVO> getStudentFormalDetailsByCadre(List<Long> familyCadreIds,Long institutionId);
	 
	public IVRResponseVO getIVRSummaryByTdpCadreId(Long tdpCadreId);
	public List<IVRResponseVO> getTotalIVRDetailsByTdpCadreId(Long tdpCadreId,int startIndex,int maxIndex);
	public CadreOverviewVO getVoterDetailsByVoterIdCardNum(String voterCardNo,String familyVoterCardNo,String memberType,Long memberTypeId);
	public List<IvrOptionsVO> getIvrSurveyInfoByTdpCadreId(Long tdpCadreId,Long entityTypeId,String searchType);
	public List<IvrOptionsVO> getTypeWiseIvrDetailsOFCadre(Long tdpCadreId);
	public String getVoterImageUrlByVoterId(Long voterId);
	public List<LocationVO> getCheckCandidateCadreDtls(Long tdpCadreId);
}
