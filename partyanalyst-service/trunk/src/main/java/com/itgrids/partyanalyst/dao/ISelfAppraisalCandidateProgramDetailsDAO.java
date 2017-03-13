package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidateProgramDetails;

public interface ISelfAppraisalCandidateProgramDetailsDAO extends GenericDao<SelfAppraisalCandidateProgramDetails, Long> {
	public List<Object[]> getToursProgramOverviewByCadre(Long tdpCadreId, Long tourMonthId);
}
