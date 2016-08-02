package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPostFinal;

public interface INominatedPostFinalDAO extends GenericDao<NominatedPostFinal, Long>{
	public List<Object[]> getFinalShortListedApplciationStatusDtls(Long boardLevelId,Date startDate,Date endDate);
	public List<Object[]> getNominatedPostMemberDetails(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId,String type,Long searchLevelId);
	public Long getNominatedPostFinalDetails(Long nominatedPostId,Long nominationPostCandidateId);
	public List<Long> getAnyShortlistedDepartmentsForCandidateList(Set<Long> nominatedPostCandidateIds);
	public List<Object[]> getAnyAppliedDepartmentsCountForCandidateList(Set<Long> nominatedPostCandidateIds);
	public List<Object[]> getAllReferredMemberDetailsForPosition(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId);
	public List<Object[]> getShortlistedDepartmentsCountForCandidateList(Set<Long> nominatedPostCandidateIds);
	public List<Object[]> getGenderWiseTotalCountsForPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId);
	public List<Object[]> getCasteWiseTotalCountsForPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId);
	public List<Object[]> getAgeGroupWiseTotalCountsForPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId);
	public List<Object[]> getCasteCategoryGroupWiseCountsForPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,String type);
	public List<Object[]> getApplicationStatusList();
	public List<Object[]> getCandidateCasteList(Long locationLevelId);
	public List<Object[]> getLocationWiseCastePositionCount(Long LocationLevelId,Long positionId);
	public List<Object[]> getCasteGroup(Long locationLevelId);
	public List<Object[]> getLocationWiseCasteGroupPositionCount(Long LocationLevelId,Long positionId);
	public List<Object[]> getCasteWisePositionsCountsByPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long casteId);
	public List<Object[]> getPositionCountForGender(Long positionId,Long boardLevelId,Long deptId,Long boardId,Long castegroupId,Long positionStatusId,Long stateId,Long districtId,String type);
	public List<Object[]>  getNominatedCandidateGroupByDist(List<Long> positionIdList, List<Long> locationLevelIdList, List<Long> deptIdList, List<Long> corporationIdList, List<Long> castGroupIdList, List<Long> positionStatusIdList, Long stateId);
	public List<Object[]>  getNominatedCandidateGroupByDistAndGender(List<Long> positionIdList, List<Long> locationLevelIdList, List<Long> deptIdList, List<Long> corporationIdList, List<Long> castGroupIdList, List<Long> positionStatusIdList, Long stateId);
	public List<Object[]>  getNominatedCandidateGroupByDistAndAgeGroup(List<Long> positionIdList, List<Long> locationLevelIdList, List<Long> deptIdList, List<Long> corporationIdList, List<Long> castGroupIdList, List<Long> positionStatusIdList, Long stateId);

	 public Object[] getShortListedPositionCntPositionAndLocationWise(Long positionId,Long boardLevelId);
}
