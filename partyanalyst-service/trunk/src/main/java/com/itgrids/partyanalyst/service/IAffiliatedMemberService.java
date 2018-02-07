package com.itgrids.partyanalyst.service;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.itgrids.partyanalyst.dto.AffiliatedMemberVO;


public interface IAffiliatedMemberService {

	public List<AffiliatedMemberVO> searchAffiliatedMemberDetails(String searchType,String searchValue,String locationType, Long locationValues);
	public AffiliatedMemberVO saveAffiliatedMemberDetails(JSONObject jobj,String IsActiveUser);
	
}