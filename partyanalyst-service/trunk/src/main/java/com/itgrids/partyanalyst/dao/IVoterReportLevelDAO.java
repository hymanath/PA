package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterReportLevel;

public interface IVoterReportLevelDAO extends GenericDao<VoterReportLevel, Long>{

	public Long getReportLevelIdByType(String type);
	
	public VoterReportLevel getReportLevelByType(String type);
	
}
