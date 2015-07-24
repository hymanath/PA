package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.ComplaintStatusCountVO;
import com.itgrids.partyanalyst.dto.GrievanceAmountVO;
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
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,Long fromAge,Long toAge,String houseNo,String gender,int startIndex,int maxIndex);
	public CadreCommitteeMemberVO cadreFormalDetailedInformation(Long cadreId);
	public CadreCommitteeMemberVO complaintDetailsOfCadre(Long cadreId,String membershipId);
	public List<CadreCommitteeMemberVO> getEventDetailsOfCadre(Long cadreId);
	public VerifierVO getTdpCadreSurveyDetails(Long cadreId,Long surveyId,String searchTypeStr);
	public List<CandidateDetailsVO>  getCandidateElectDetatails(Long cadreId);
	
	public RegisteredMembershipCountVO getTotalMemberShipRegistrationsInCadreLocation(Long cadreId);
	
	public List<RegisteredMembershipCountVO> getElectionPerformanceInCadreLocation(Long tdpCadreId);
	
	public List<GrievanceAmountVO> getApprovedFinancialSupprotForCadre(Long tdpCadreId);
	
	public List<TdpCadreFamilyDetailsVO> getCadreFamilyDetails(Long tdpCadreId);
	
	public WebServiceResultVO getCandidateAndLocationSummaryNews(String startDate,String endDate,String locationType,Long locationId,Long candidateId);
	
	public Long getCadreIdByMembershipId(String memberShipNo,Long constituencyId);
	
	public ComplaintStatusCountVO getCategoryWiseStatusCount(Long tdpCadreId);
	
	public List<CommitteeBasicVO> getLocationwiseCommitteesCount(String locationType,Long tdpCadreId);
	
	public VerifierVO getDeathsAndHospitalizationDetails(Long panchayatId,Long mandalId,Long constituencyId,Long parliamentId,Long districtId);
	
	public Long getTdpCadreIdBymembershipId(String membershipId);
	
	public CadreCommitteeMemberVO getCadresDetailsOfDeathsAndHospitalization(Long locationId,String locationType,Long insuranceTypeId);
}
