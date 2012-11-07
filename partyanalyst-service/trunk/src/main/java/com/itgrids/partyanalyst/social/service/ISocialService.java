package com.itgrids.partyanalyst.social.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SocialNetworkVO;

public interface ISocialService {
		
	public List<SocialNetworkVO> getPartyNames();
	public List<SocialNetworkVO> getCandidateNames();
	public List<SocialNetworkVO> getNames();
	public ResultStatus saveSocialInformation(SocialNetworkVO socialVO);
	}
