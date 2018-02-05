package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AffiliatedMemberVO;


public interface IAffiliatedMember {

	public AffiliatedMemberVO saveAffiliatedMemberDetails(AffiliatedMemberVO affiliatedMemberVO );
	public List<AffiliatedMemberVO> searchAffiliatedMemberDetails(String searchType,String searchValue,String locationType, Long locationValues);
	
}