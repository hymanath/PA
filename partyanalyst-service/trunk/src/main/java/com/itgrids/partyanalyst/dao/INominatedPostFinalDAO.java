package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPostFinal;

public interface INominatedPostFinalDAO extends GenericDao<NominatedPostFinal, Long>{
	public List<Object[]> getFinalShortListedApplciationStatusDtls(Long boardLevelId,Date startDate,Date endDate);
	public List<Object[]> getNominatedPostMemberDetails(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId,String type);
	public Long getNominatedPostFinalDetails(Long nominatedPostId,Long nominationPostCandidateId);
	public List<Long> getAnyShortlistedDepartmentsForCandidateList(Set<Long> nominatedPostCandidateIds);
	public List<Object[]> getAnyAppliedDepartmentsCountForCandidateList(Set<Long> nominatedPostCandidateIds);
}
