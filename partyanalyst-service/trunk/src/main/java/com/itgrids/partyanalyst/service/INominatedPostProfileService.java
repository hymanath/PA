package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.AddNotcadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.NominatedPostReferVO;
import com.itgrids.partyanalyst.dto.NominatedPostDashboardVO;
import com.itgrids.partyanalyst.dto.NominatedPostVO;
import com.itgrids.partyanalyst.dto.NomintedPostMemberVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface INominatedPostProfileService {
	public List<IdNameVO> getBoardLevels();
	public List<IdNameVO> getDepartments(Long postTpe,Long boardLevelId,Long searchLevelValue);
	public List<IdNameVO> getDepartmentBoard(Long depmtId,Long boardLevelId,Long searchLevelValue);
	public List<IdNameVO> getDepartmentBoardPositions(Long deptId,Long boardId,Long boardLevelId,Long searchLevelValue);
	public NomintedPostMemberVO getNominatedPostMemberDetails(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId,String type,Long searchLevelId);
	public String updateApplicationStatusDetails(final Long userId,final Long nominatePostApplicationId,final Long statusId,final String comment,final Long levelId,
			final Long levelValue,final Long deptId,final Long boardId,final Long positionId,final Long candidateId);
	public ResultStatus savingNominatedPostProfileApplication(NominatedPostVO nominatedPostVO,final Long loggedUserId,final Map<File,String> mapfiles);
	public String savechangeAddressForNominatedPost(final NominatedPostVO nominatedPostVO);
	public List<NominatedPostVO> getApplicantDetailsForMember(Long tdpCadreId,String searchType);
	public List<IdNameVO> getDistrictsForState(Long stateId);
	public List<IdNameVO> getVillagesForMandalId(Long mandalId);
	public NomintedPostMemberVO getCandidateAppliedPostsByCadre(Long tdpCadreId,String searchType,Long nominateCandId);
	public List<NominatedPostVO> getNominatedPostsStatusDetailsInAllLevels(Long levelId,String startDateStr, String endDateStr,Long stateId);
	public List<NominatedPostVO> getNominatedPostPostionDetails(Long departmentId,Long boardId,Long positionId,Long boardLevelId,Long locationValue,Long searchLevelId);
	public ResultStatus saveNominatedPostUploadFiles(Map<File,String> mapfiles,Long nomiPostCandiId);
	public ResultStatus deleteNominatedUploadedFile(String acitivityInfoDocId);
	public List<NominatedPostVO> getBrdWisNominPstAppliedDepOrCorpDetails(Long candidateId);
	public List<IdNameVO> getReferCadreDetailsForCandidate(Long candidateId);
	public List<IdNameVO> getAllDeptsAndBoardsByLevel(Long boardLevelId,List<Long> locationValue,String statusType,String task,Long searchlevelId,Long searchLevelValue);
	public List<NominatedPostVO> getDepartmentWiseBoardAndPositionDetails(Long boardLevelId,List<Long> levelValues,List<Long> deptIds,List<Long> boardIds,String statusType,String task);
	public List<IdNameVO> getAllApplicationStatusList();
	public String savingAnyPostCandidatesToPosition(final Long userId,final Long applicationId,final Long candidateId,final Long levelId,final Long levelValue,
						final Long deptId,final Long boardId,final Long positionId,final Long statusId,final String comment);
	public List<IdNameVO> getFinalReviewCandidateCountLocationWise(Long LocationLevelId,List<Long> lctnLevelValueList,Long departmentId,Long boardId,String status);
    public  List<CadreCommitteeVO> notCadresearch(String searchType,String searchValue);
    public ResultStatus saveNotcadreRegistrationPost(final  AddNotcadreRegistrationVO notcadreRegistrationVO,final Map<File,String> mapfiles,final Long loggerUserId);
	public List<IdNameVO> getCastesForAP();
	public  List<CadreCommitteeVO> getNotCadreDetailsById(Long nominatedPostCandiId);
	public ResultStatus updateNominatedPostStatusDetails(final Long deptId,final Long boardId,final List<Long> positions,final Long levelId,final List<Long> searchLevelValues,final Long statusId,final Long userId);
	public NominatedPostReferVO getAllReferredMemberDetailsForPosition(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId);
	public IdNameVO getOverAllCommentsForCandidate(Long candidateId);
	public String updateFinalyzationStatusForPost(final Long postFinalId,final Long statusId,final String comment,final Long userId,final Long postApplicationId,final Long candidateId);
	public String updateWishListForCandidate(final Long postFinalId,final String remark,final Long userId);
	public NominatedPostDashboardVO getOverAllTotalCountsByPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId);
	public List<NominatedPostDashboardVO> getCasteGroupWiseCountsByPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId);
	public List<NominatedPostDashboardVO> getCasteWiseCountsByPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId);
	public List<NominatedPostDashboardVO> getCasteWisePositionsCountsByPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long casteId);
	public Long validateVoterIdCardNo(String voterIdCardNo);
	public List<IdNameVO> getOpenedPositionsBoardLevels();
	public List<IdNameVO> getStatesForOpenedPositions(Long boardLevelId);
	public List<IdNameVO> getOpenPositionDistrictsForState(Long stateId,Long boardLevelId);
	public List<IdNameVO> getOpenPositionConstituenciesForDistrict(Long districtId,Long boardLevelId);
	public List<LocationWiseBoothDetailsVO> getMandalMuncilIdsForConstituency(Long constituencyId,Long boardLevelId);
	public List<LocationWiseBoothDetailsVO> getPanchaytWardForMandal(String mandalId,Long constituencyId,Long boardLevelId);
}
