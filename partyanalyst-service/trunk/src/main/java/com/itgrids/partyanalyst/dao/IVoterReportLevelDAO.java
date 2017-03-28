package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterReportLevel;

public interface IVoterReportLevelDAO extends GenericDao<VoterReportLevel, Long>{

	public Long getReportLevelIdByType(String type);
	
	public VoterReportLevel getReportLevelByType(String type);
	
	public String getReportLevelTypeById(Long reportLevelId);
	
	public List<VoterReportLevel> getVoterReportLevelList();
	
	
}
