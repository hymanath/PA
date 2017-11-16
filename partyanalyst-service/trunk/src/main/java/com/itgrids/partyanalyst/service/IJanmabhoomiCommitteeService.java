package com.itgrids.partyanalyst.service;
import java.util.List;

import com.itgrids.partyanalyst.dto.JanmabhoomiCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.JanmabhoomiCommitteeVO;
import com.itgrids.partyanalyst.dto.ResultStatus;


public interface IJanmabhoomiCommitteeService 
{
	public JanmabhoomiCommitteeMemberVO getJanmabhoomiCommitteeOverview(Long committeId,String fromDate,String toDate);
	public List<JanmabhoomiCommitteeVO> getDistrictWiseCommitteeDetails(String fromDate,String endDate,String type);
	public  JanmabhoomiCommitteeVO  getJbCommitteeStatusCount(String fromDateStr, String toDateStr);
	//public Map<Long,JanmabhoomiCommitteeMemberVO> getLevelWiseCommiteeStatusCounts();
	public ResultStatus saveJanmabhoomiCommitteeMember(final JanmabhoomiCommitteeMemberVO janmabhoomiCommitteeMemberVO);
	public List<JanmabhoomiCommitteeVO> getJanmabhoomiCommitteesByLocIdAndCommLvlId(String fromDate,String endDate,Long locationId,Long locLvlId,Long committeeLvlId,String status);
}
