package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.AddNotcadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.CadrePerformanceVO;
import com.itgrids.partyanalyst.dto.EventDetailsVO;
import com.itgrids.partyanalyst.dto.GovtOrderVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.LocationsVO;
import com.itgrids.partyanalyst.dto.NominatedPostDashboardVO;
import com.itgrids.partyanalyst.dto.NominatedPostDetailsVO;
import com.itgrids.partyanalyst.dto.NominatedPostReferVO;
import com.itgrids.partyanalyst.dto.NominatedPostVO;
import com.itgrids.partyanalyst.dto.NomintedPostMemberVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface INominatedPostProfileService {
	public List<IdNameVO> getBoardLevels();
	public List<IdNameVO> getDepartments(Long postTpe,Long boardLevelId,Long searchLevelValue,Long searchLevelId,Long positionId);
	public List<IdNameVO> getDepartmentBoard(Long depmtId,Long boardLevelId,Long searchLevelValue,Long searchLevelId,Long applicationId,Long positionId);
	public List<IdNameVO> getDepartmentBoardPositions(Long deptId,Long boardId,Long boardLevelId,Long searchLevelValue,Long searchLevelId,Long nominatedPostCandId);
	public NomintedPostMemberVO getNominatedPostMemberDetails(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId,String type,Long searchLevelId,Long statusId);
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
	public List<NominatedPostVO> getBrdWisNominPstAppliedDepOrCorpDetails(Long candidateId,String type);
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
	public ResultStatus updateNominatedPostStatusDetails(final Long deptId,final Long boardId,final List<Long> positions,final Long levelId,final List<Long> searchLevelValues,final Long statusId,final Long userId,final Long sizeOfMember);
	public NominatedPostReferVO getAllReferredMemberDetailsForPosition(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId,Long statusId);
	public IdNameVO getOverAllCommentsForCandidate(Long candidateId,Long applicationId,Long postFinalId);
	public String updateFinalyzationStatusForPost(final Long postFinalId,final Long statusId,final String comment,final Long userId,final Long postApplicationId,final Long candidateId);
	public String updateWishListForCandidate(final Long postFinalId,final String remark,final Long userId);
	public NominatedPostDashboardVO getOverAllTotalCountsByPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long stateId);
	public List<NominatedPostDashboardVO> getCasteGroupWiseCountsByPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long stateId);
	public List<NominatedPostDashboardVO> getCasteWiseCountsByPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long stateId);
	public List<NominatedPostDashboardVO> getCasteWisePositionsCountsByPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long casteId,Long stateId);
	public ResultStatus validateVoterIdCardNo(String voterIdCardNo);
	public List<IdNameVO> getOpenedPositionsBoardLevels();
	public List<IdNameVO> getStatesForOpenedPositions(Long boardLevelId);
	public List<IdNameVO> getOpenPositionDistrictsForState(Long stateId,Long boardLevelId);
	public List<IdNameVO> getOpenPositionConstituenciesForDistrict(Long districtId,Long boardLevelId);
	public List<LocationWiseBoothDetailsVO> getMandalMuncilIdsForConstituency(Long constituencyId,Long boardLevelId);
	public List<LocationWiseBoothDetailsVO> getPanchaytWardForMandal(String mandalId,Long constituencyId,Long boardLevelId);
	
	 public List<NominatedPostVO> getAnyDeptApplicationOverviewCountLocationWise(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
		      List<Long> locationValue,Long searchLevelId,String statusType);
	 public List<IdNameVO> getPositionsForABoard(Long locationLevelId,List<Long> locationLevelValueList,Long departmentId,Long boardId);
	 public ResultStatus confirmGOForNominatedPosts(GovtOrderVO goVO,Long userId,Map<File,String> mapfiles);
	 public ResultStatus checkPositionAvailableOrNot(Long departmentId,Long boardId,Long positionId,Long boardLevlId,Long searchLevelValue,Long searchLevelId);
	 public List<NomintedPostMemberVO> getFinalReviewCandidateCountForLocation(Long LocationLevelId,List<Long> lctnLevelValueList,Long departmentId,Long boardId, Long positionId, String status);
	 public ResultStatus assginGOToNominationPostCandidate(GovtOrderVO goVO,Long userId,Map<File,String> mapfiles);
	 public List<NomintedPostMemberVO> getFinalReviewCandidateCountForLocationFilter(Long LocationLevelId, List<Long> lctnLevelValueList, List<Long> deptList, List<Long> boardList, List<Long> positionList, String today, String expireDate, String status); 
	 public String savingStatusAsReject(final Long userId,final Long applicationId,final Long candidateId,final Long levelId,final Long levelValue,
				final Long statusId,final String comment);
	 public String isApplicationAlreadyShortlisted(Long nominatePostApplicationId,Long levelId,Long levelValue,Long deptId,Long boardId,Long positionId,Long candId);
	 public NominatedPostDashboardVO getNominatedPostDetails(Long locationLevelId,List<Long> locationValues,Long departmentId,Long boardId,Long positionId);
	 public List<IdAndNameVO> getApplicationDocuments(Long tdpCadreId, String searchType, Long nominateCandId, Long applicationId,Long statusId,String applicationType);
	 public List<IdAndNameVO> getAllAgeRangesByOrder();
	 public List<IdAndNameVO> getAllCasteDetailsForVoters();
	 public List<IdAndNameVO> getAllCasteCategoryDetails();
	 public List<IdAndNameVO> getEducationalQualifications();
	 public List<IdNameVO> getDepartmentBoardPositions1(List<Long> deptIds,List<Long> boardIds,Long boardLevelId,List<Long> searchLevelValueIds,Long searchLevelId,Long nominatedPostCandId);
	 public ResultStatus savingNominatedPostDocumnets(final Long nominatedPostCandidateId,final Long tdpCadreId,final Long applicationId,final Map<File,String> mapfiles,final Long loggerUserId);
	 public String UpdateExpiredAppicationsInNominatedPosts(final Long userId);
	 public CadrePerformanceVO getCadrePeoplePerformanceDetails(List<Long> tdpCadreIdsList);
	 public List<LocationsVO> getLocationByDepartment(Long levelId,Long departmentId,Long boardId,Long positionId);
	 //public List<CadreEventsVO> getCadreDetailedReportEventAttendee(Long parentEventId,Long cadreId,List<Long> extraEventIdsList);
	 public void setBrdWisNominPstAppliedDepOrCorpApplledDetails(List<Object[]> depOCorpList,List<Long> apllicationIds,List<NominatedPostVO> returnVoList,String status);
	 public List<CadrePerformanceVO> getCampDetails(List<Long> tdpCadreIdsList);
	 public List<EventDetailsVO> getMahanaduEventDetilsByCadreIdDetils(Long parentEventId,Long tdpCadreId);
	 public ResultStatus saveNominatedPostProfileDtls(final NominatedPostDetailsVO nominatedPostDtlsVO,final Long userId,final Map<File,String> mapfiles);
	 
}
