package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.NominatedPostVO;
import com.itgrids.partyanalyst.dto.NomintedPostMemberVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface INominatedPostProfileService {
	public List<IdNameVO> getBoardLevels();
	public List<IdNameVO> getDepartments();
	public List<IdNameVO> getDepartmentBoard(Long depmtId);
	public List<IdNameVO> getDepartmentBoardPositions(Long deptId,Long boardId);
	public NomintedPostMemberVO getNominatedPostMemberDetails(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId,String type);
	public String updateApplicationStatusDetails(final Long userId,final Long nominatePostApplicationId,final Long statusId,final String comment);
	public ResultStatus savingNominatedPostProfileApplication(NominatedPostVO nominatedPostVO,final Long loggedUserId);
	public String savechangeAddressForNominatedPost(final NominatedPostVO nominatedPostVO);
	public List<NominatedPostVO> getApplicantDetailsForMember(Long tdpCadreId);
	public List<IdNameVO> getDistrictsForState(Long stateId);
	public List<IdNameVO> getVillagesForMandalId(Long mandalId);
	public NomintedPostMemberVO getCandidateAppliedPostsByCadre(Long tdpCadreId);
	public List<NominatedPostVO> getNominatedPostPostionDetails(Long departmentId,Long boardId,Long positionId,Long boardLevelId,Long locationValue);
	
}
