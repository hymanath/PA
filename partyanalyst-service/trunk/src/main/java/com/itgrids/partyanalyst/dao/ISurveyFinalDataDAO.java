package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyFinalData;

public interface ISurveyFinalDataDAO extends GenericDao<SurveyFinalData, Long>
{
	public int deleteExistingBoothDetails(Long boothId);
	public List<Object[]> getBoothWiseErrorCountForAConstituency(Long constituencyId);
	public List<Object[]> getBoothWiseVoterDetails(Long boothId);
	public List<Object[]> getSurveyFinalConstituencyInfo();
	public int updatedThirdPartyStatus(Long voterId,Long statusId);

}
