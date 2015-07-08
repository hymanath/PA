package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.VerifierVO;

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
	public VerifierVO getTdpCadreSurveyDetails(Long cadreId,Long surveyId);
	public List<CandidateDetailsVO>  getCandidateElectDetatails(Long cadreId);
}
