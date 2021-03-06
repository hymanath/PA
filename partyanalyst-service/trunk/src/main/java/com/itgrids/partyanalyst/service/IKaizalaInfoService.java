package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.dto.KaizalaDashboardVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;

public interface IKaizalaInfoService {
	public void saveEventResponses(final String output);
	
	public List<KaizalaDashboardVO> getLocationWiseCommitteeMemberDetails(InputVO vo);
	public KaizalaDashboardVO getOverAllCommitteeWiseMembersCounts(InputVO inputvo);
	public List<List<UserTypeVO>> getUserTypeWiseKaizalaCommitteeMemberDetailsCnt(InputVO inputVO);
}
