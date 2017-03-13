package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidateProgramDetails;

public interface ISelfAppraisalCandidateProgramDetailsDAO extends GenericDao<SelfAppraisalCandidateProgramDetails, Long> {
	 public List<Object[]> getDesignationWiseTourProgramVisitedCandidate(List<Long> monthIds,Set<Long> candiateIds);
	 public List<Object[]> getDesignationWiseTourProgramVisitedDtls(List<Long> monthIds,Set<Long> candiateIds);
	 public List<Object[]> getTourProgramWiseVisitedCandiate(List<Long> monthIds,Set<Long> candiateIds);
	 public List<Object[]> getMonthWiseTourSubmittedDetails(List<Long> monthYearIds,Long candidateId);
	 public List<Object[]> getDesignationWiseTourProgramDtls(List<Long> monthIds,List<Long> designationIds,List<Long> candidateIds);
	 public List<Object[]> getDesignationWiseTourProgramSubmittedCandidateCnt(List<Long> monthIds,List<Long> designationIds);
	 public List<Object[]> getToursProgramOverviewByCadre(Long tdpCadreId, Long tourMonthId);
}
