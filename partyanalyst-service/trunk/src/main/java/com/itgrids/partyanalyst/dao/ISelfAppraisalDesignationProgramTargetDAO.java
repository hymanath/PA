package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalDesignationProgramTarget;

public interface ISelfAppraisalDesignationProgramTargetDAO extends GenericDao<SelfAppraisalDesignationProgramTarget, Long> {
	public List<Object[]> getDesignationWiseTourProgramTarget(List<Long> monthIds);
	public List<Object[]> getCandidateWiseTourProgramTarget(List<Long> monthIds,Long selfAppraisalCandiateId);
	public List<Object[]> getDesignationWiseTourProgramTargetCnt(List<Long> monthIds,List<Long> designationIds);
	public List<Object[]> getDesignationWiseDetails(List<Long> desgIds,Long toursMonthId);
	
}
