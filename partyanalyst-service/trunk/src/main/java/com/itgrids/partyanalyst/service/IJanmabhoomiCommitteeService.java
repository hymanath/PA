package com.itgrids.partyanalyst.service;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.JanmabhoomiCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.JanmabhoomiCommitteeVO;


public interface IJanmabhoomiCommitteeService 
{
	public JanmabhoomiCommitteeMemberVO getJanmabhoomiCommitteeOverview(Long committeId,String fromDate,String toDate);
	public List<JanmabhoomiCommitteeVO> getDistrictWiseCommitteeDetails(String fromDate,String endDate,String type);
	public  JanmabhoomiCommitteeVO  getJbCommitteeStatusCount(String fromDateStr, String toDateStr);
	public Map<Long,JanmabhoomiCommitteeMemberVO> getLevelWiseCommiteeStatusCounts();
}
