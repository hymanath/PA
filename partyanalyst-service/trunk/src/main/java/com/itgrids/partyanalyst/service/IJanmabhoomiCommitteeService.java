package com.itgrids.partyanalyst.service;
import java.util.List;

import com.itgrids.partyanalyst.dto.JanmabhoomiCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.JanmabhoomiCommitteeVO;
import com.itgrids.partyanalyst.dto.ResultStatus;


public interface IJanmabhoomiCommitteeService 
{
	public JanmabhoomiCommitteeMemberVO getJanmabhoomiCommitteeOverview(Long committeId,String fromDate,String toDate);
	public JanmabhoomiCommitteeVO getDistrictWiseCommitteeDetails(String fromDate,String endDate,String type,Long userId);
	public  JanmabhoomiCommitteeVO  getJbCommitteeStatusCount(String fromDateStr, String toDateStr,Long userId);
	//public Map<Long,JanmabhoomiCommitteeMemberVO> getLevelWiseCommiteeStatusCounts();
	public ResultStatus saveJanmabhoomiCommitteeMember(final JanmabhoomiCommitteeMemberVO janmabhoomiCommitteeMemberVO);
	public List<JanmabhoomiCommitteeVO> getJanmabhoomiCommitteesByLocIdAndCommLvlId(String fromDate,String endDate,Long locationId,Long locLvlId,Long committeeLvlId,Long status,Long userId);
	public JanmabhoomiCommitteeMemberVO searchByMemberIdOrVoterId(Long locationLevel,Long locationValue,String memberShipCardNo,String voterCardNo);
	public List<JanmabhoomiCommitteeVO> getStatewiseCastNamesByCasteCategoryGroupId(List<Long> categoryGrouId);
	public List<JanmabhoomiCommitteeVO>  getCategories();
	public ResultStatus updateCommitteStatusByCommiteeId(Long committeeId,String status);
}
