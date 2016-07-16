package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.NominatedPostVO;
import com.itgrids.partyanalyst.dto.ImportantLeadersVO;
import com.itgrids.partyanalyst.dto.IvrOptionsVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.MahanaduEventVO;
import com.itgrids.partyanalyst.dto.MobileDetailsVO;
import com.itgrids.partyanalyst.dto.NomintedPostMemberVO;
import com.itgrids.partyanalyst.dto.NominatedPostVO;
import com.itgrids.partyanalyst.dto.NtrTrustStudentVO;
import com.itgrids.partyanalyst.dto.QuestionAnswerVO;
import com.itgrids.partyanalyst.dto.RegisteredMembershipCountVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TdpCadreFamilyDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.VerifierVO;
import com.itgrids.partyanalyst.dto.WebServiceResultVO;

public interface INominatedPostProfileService {
	public List<IdNameVO> getBoardLevels();
	public List<IdNameVO> getDepartments();
	public List<IdNameVO> getDepartmentBoard(Long depmtId);
	public List<IdNameVO> getDepartmentBoardPositions(Long deptId,Long boardId);
	public NomintedPostMemberVO getNominatedPostMemberDetails(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId,String type);
	public String updateApplicationStatusDetails(final Long userId,final Long nominatedPostId,final Long nominatedPostCandidateId,final Long statusId);
	public ResultStatus savingNominatedPostProfileApplication(NominatedPostVO nominatedPostVO,final Long loggedUserId);
	public String savechangeAddressForNominatedPost(final NominatedPostVO nominatedPostVO);
	public List<NominatedPostVO> getApplicantDetailsForMember(Long tdpCadreId);
	public List<IdNameVO> getDistrictsForState(Long stateId);
	public List<IdNameVO> getVillagesForMandalId(Long mandalId);
	
}
