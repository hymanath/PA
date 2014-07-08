package com.itgrids.partyanalyst.dao;


import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyDetailsInfo;

public interface ISurveyDetailsInfoDAO  extends GenericDao<SurveyDetailsInfo, Long>
{
	public List<Object[]> getDayWisereportDetailsByConstituencyId(Long constituencyId,Date startDate,Date endDate,Long userTypeId);
	public List<Object[]> getBoothWiseUserSamplesDetailsByDates(Long userId,Date startDate);

	public List<SurveyDetailsInfo> getLatLongForSurveyDetails(Long surveyUserId,Date date);
	
	public List<SurveyDetailsInfo> getSurveyDetilsForAssibnedBooths(Long boothIds);
	public SurveyDetailsInfo checkUserForVoter(long userId,String uuid,Long voterId );

	public List<Object[]> getVoterDetailsForbooths(List<Long> boothIds);
	public List<SurveyDetailsInfo> getVerifiedVotersDetailsBySurveyDetailsInfoId(Long voterId);
	public List<Long> getDataCollectedVoterIdsByBoothIds(List<Long> boothIds);
	public List<Object[]> getDayWisereportDetailsByConstituencyIdAndUserTypeId(
			Long constituencyId, Date startDate, Date endDate, Long userTypeId,List<Long> boothIds);

}
