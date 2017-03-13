package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalDesignationProgramTarget;

public interface ISelfAppraisalDesignationProgramTargetDAO extends GenericDao<SelfAppraisalDesignationProgramTarget, Long> {

	public List<Object[]> getDesignationWiseDetails(List<Long> desgIds,Long toursMonthId);
	
}
