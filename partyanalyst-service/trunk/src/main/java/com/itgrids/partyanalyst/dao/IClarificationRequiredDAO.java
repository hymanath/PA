package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ClarificationRequired;

public interface IClarificationRequiredDAO extends GenericDao<ClarificationRequired,Long>{
	
	public String getDetails(Long alertId);

}
