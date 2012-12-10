package com.itgrids.partyanalyst.social.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SocialNetworkVO;

public interface ISocialService {
	

	public List<SocialNetworkVO> getPartyNames();
	public List<SocialNetworkVO> getCandidateNames();
	public List<SocialNetworkVO> getNames();
	public ResultStatus saveSocialInformation(SocialNetworkVO socialVO);
	public List<SocialNetworkVO> getParliamentInfo(String electionType, Long stateId, Long districtId,  Long constituencyId, String status);
	public List<SocialNetworkVO> getCasteCategoriesNames();
	public List<SocialNetworkVO> getCasteCategoryGroupNames(Long categoryId);
	public List<SocialNetworkVO> getCasteName(Long casteStateId);
	public List findByTehsilNameAndDistrict(Long districtId);
	public ResultStatus insertAddressDetails(CandidateDetailsVO candidateDetailsVO);
	//public ResultStatus insertPhoneDetails(CandidateDetailsVO candidateDetailsVO);
	public ResultStatus insertCasteDetails(CandidateDetailsVO candidateDetailsVO);
	public ResultStatus insertOtherDetails(CandidateDetailsVO candidateDetailsVO);
	public List<SelectOptionVO> getCasteNamesByAutoPopulate(Long stateId,String letters);
	public CandidateDetailsVO getcandidateFullInformation(Long candidateId);
	public List<SelectOptionVO> getAllCasteDetails();
	public List<SelectOptionVO> getAllCasteCategoryDetails();
	public List<SelectOptionVO> getAllCasteCategoryGroupDetails(Long casteCategoryId);
	public List<SelectOptionVO> getAllStateDetails();
	public List<SelectOptionVO> getAllDistrictDetails(Long stateId);
	public List<SelectOptionVO> getAllDistrictInfoDetails();
	public List<SelectOptionVO> getAllTehsilInfoDetails();
	public List<SelectOptionVO> getAllTehsilDetails(Long districtId);
	public List<SelectOptionVO> getAllCasteInfoDetails();
	public List<SelectOptionVO> getAllAddressTypes();
	public List<SelectOptionVO> getAllCasteCategoryGroupInfoDetails();
	public List<SelectOptionVO> getAllPhoneTypes();
	public List<SelectOptionVO> getAllPhoneCategorys();
	}
