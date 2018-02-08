package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampDetailsInfo;

public interface ITrainingCampDetailsInfoDAO extends GenericDao<TrainingCampDetailsInfo, Long> {
	public List<Object[]> getTrainingCampProgramEligibleAndAttendedDetails(Long locationScopeId,List<Long> locationValues,Date toDate,List<Long> enrollmentYearIds,List<Long> programIdList,List<Long> committeeLevelIds);
	public List<Object[]> getTrainingCampProgramEligibleAndAttendedMemberCommitteeLevelWise(Long locationScopeId,List<Long> locationValues,Date toDate,List<Long> enrollmentYearIds,List<Long> programIdList,List<Long> committeeLevelIds);
	public List<Object[]> getTrainingCampProgramEligibleAndAttendedMemberLocationWise(Long locationScopeId,List<Long> locationValues,Date toDate,List<Long> enrollmentYearIds,List<Long> programIdList);
	public List<Object[]> getLocationWiseReportBasedOnUserType(Long locationScopeId,List<Long> locationValues,Long stateId,Long userTypeId,Long activityMemberId);
	public List<Object[]> getTrainingCampEligibleAndAttendedCntByLocationType(Long locationScopeId,List<Long> locationValues,Long stateId,Long userTypeId,Long activityMemberId,String locationType);
	public int pushTrainginCampDataLocationWiseByCommitteeLevel();
	public List<Object[]> getBoothlevelEligibleCandidates(List<Long> enrollmentYearIds, Long locationScopeId, List<Long> locationValesList,List<Long> programIdList);
}
