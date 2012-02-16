package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ConstituencyLeadCandidate;

public interface IConstituencyLeadCandidateDAO  extends GenericDao<ConstituencyLeadCandidate, Long>{
	
	public List<Object> getCandidateResultStatus(Long candidateId,Long constiElecId);
	
	public List<Object> checkCandidateResultExist(Long constiElecId);
}
