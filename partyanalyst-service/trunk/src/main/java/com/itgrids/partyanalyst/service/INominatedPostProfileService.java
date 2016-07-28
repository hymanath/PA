package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.AddNotcadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.NominatedPostReferVO;
import com.itgrids.partyanalyst.dto.NominatedPostDashboardVO;
import com.itgrids.partyanalyst.dto.NominatedPostVO;
import com.itgrids.partyanalyst.dto.NomintedPostMemberVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface INominatedPostProfileService {
	public List<IdNameVO> getBoardLevels();
	public List<IdNameVO> getDepartments(Long postTpe);
	public List<IdNameVO> getDepartmentBoard(Long depmtId);
	public List<IdNameVO> getDepartmentBoardPositions(Long deptId,Long boardId);
	public NomintedPostMemberVO getNominatedPostMemberDetails(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId,String type);
	public String updateApplicationStatusDetails(final Long userId,final Long nominatePostApplicationId,final Long statusId,final String comment,final Long levelId,
			final Long levelValue,final Long deptId,final Long boardId,final Long positionId,final Long candidateId);
	public ResultStatus savingNominatedPostProfileApplication(NominatedPostVO nominatedPostVO,final Long loggedUserId,final Map<File,String> mapfiles);
	public String savechangeAddressForNominatedPost(final NominatedPostVO nominatedPostVO);
	public List<NominatedPostVO> getApplicantDetailsForMember(Long tdpCadreId,String searchType);
	public List<IdNameVO> getDistrictsForState(Long stateId);
	public List<IdNameVO> getVillagesForMandalId(Long mandalId);
	public NomintedPostMemberVO getCandidateAppliedPostsByCadre(Long tdpCadreId,String searchType,Long nominateCandId);
	public List<NominatedPostVO> getNominatedPostsStatusDetailsInAllLevels(Long levelId,String startDateStr, String endDateStr,Long stateId);
	public List<NominatedPostVO> getNominatedPostPostionDetails(Long departmentId,Long boardId,Long positionId,Long boardLevelId,Long locationValue);
	public ResultStatus saveNominatedPostUploadFiles(Map<File,String> mapfiles,Long nomiPostCandiId);
	public ResultStatus deleteNominatedUploadedFile(String acitivityInfoDocId);
	public List<NominatedPostVO> getBrdWisNominPstAppliedDepOrCorpDetails(Long candidateId);
	public List<IdNameVO> getReferCadreDetailsForCandidate(Long candidateId);
	public List<IdNameVO> getAllDeptsAndBoardsByLevel(Long boardLevelId,List<Long> locationValue,String statusType);
	public List<NominatedPostVO> getDepartmentWiseBoardAndPositionDetails(Long boardLevelId,List<Long> levelValues,List<Long> deptIds,List<Long> boardIds,String statusType);
	public List<IdNameVO> getAllApplicationStatusList();
	public String savingAnyPostCandidatesToPosition(final Long userId,final Long applicationId,final Long candidateId,final Long levelId,final Long levelValue,
						final Long deptId,final Long boardId,final Long positionId,final Long statusId,final String comment);
	public List<IdNameVO> getFinalReviewCandidateCountLocationWise(Long LocationLevelId,List<Long> lctnLevelValueList,Long departmentId,Long boardId);
    public  List<CadreCommitteeVO> notCadresearch(String searchType,String searchValue);
    public ResultStatus saveNotcadreRegistrationPost(final  AddNotcadreRegistrationVO notcadreRegistrationVO,final Map<File,String> mapfiles,final Long loggerUserId);
	public List<IdNameVO> getCastesForAP();
	public  List<CadreCommitteeVO> getNotCadreDetailsById(Long nominatedPostCandiId);
	public ResultStatus updateNominatedPostStatusDetails(final Long deptId,final Long boardId,final Long positionId,final Long levelId,final Long searchLevelId,final Long searchLevelValue,final Long statusId);
	public NominatedPostReferVO getAllReferredMemberDetailsForPosition(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId);
	public IdNameVO getOverAllCommentsForCandidate(Long candidateId);
	

}
