package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.dto.NominatedPostCandidateDtlsVO;
import com.itgrids.partyanalyst.dto.NominatedPostDetailsVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;


public interface ICoreDashboardNominatedPostService 
{
	public List<NominatedPostCandidateDtlsVO> getLevelWisePostsOverView(List<Long> locationValues,String fromDateStr,String toDateStr,Long locationTypeId,Long boardLevelId);
	public List<NominatedPostDetailsVO> getDepartmentWisePostAndApplicationDetails(List<Long> locationValues,String fromDateStr, String toDateStr,Long locationTypeId,String year,Long boardLevelId,Long deptId);
	public List<NominatedPostCandidateDtlsVO> getNominatedPostLocationWiseBoardLevelCount(List<Long> locationValues,String fromDateStr,String toDateStr,Long locationTypeId,Long boardLevelId);
	public List<NominatedPostCandidateDtlsVO> getNominatedPostStateWiseCount(String fromDateStr,String toDateStr);
	public List<List<UserTypeVO>> getUserTypeWiseNominatedPostDetailsCnt(InputVO inputVO);
	public List<NominatedPostDetailsVO> getDepartMentAndBoardWisePositinsStatusCount(List<Long> locationValues,String fromDateStr, 
			String toDateStr,Long locationTypeId,Long boardLevelId,Long statusId);
	public List<NominatedPostDetailsVO> getDepartMentWiseBoards(List<Long> locationValues,String fromDateStr, 
			String toDateStr,Long locationTypeId,Long boardLevelId,Long statusId,Long departmentId);
	public List<NominatedPostDetailsVO> getBoardWisePositions(List<Long> locationValues,String fromDateStr, 
			String toDateStr,Long locationTypeId,Long boardLevelId,Long statusId,Long boardId);
}
