package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertCandidate;

public interface IAlertCandidateDAO extends GenericDao<AlertCandidate, Long> {
	
	public List<Object[]> getAlertCandidateCount(List<Long> alertIds);
}
