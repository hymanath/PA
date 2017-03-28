package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyConstituencyTemp;

public interface ISurveyConstituencyTempDAO extends GenericDao<SurveyConstituencyTemp, Long>
{
	public List<Object[]> getTotalVoters(Long type);
	public List<Object[]> getTotalVotersAndBooths(List<Long> constituencyIds);
	public List<Object[]> getTotalVotersForConstituencies();
	public List<Object[]> getTotalVotersAndBoothsByConstituencyes(List<Long> constituencyIds);
}
