package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidate;

public interface ISelfAppraisalCandidateDAO extends GenericDao<SelfAppraisalCandidate, Long> {
	public List<Object[]> getCandiateList(Long designationId);
	public Object[] getCandiateDetailsByCandidateId(Long candiateId);
	public Long getCandidateId(Long tdpCadreId,Long designationId);
}
