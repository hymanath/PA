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
	
	public List<Object[]> getLatLongForSurveyUsersByConstituency(Long constituencyId,Date date);
	
	public Long getCasteCountByBooth(Long userId,Long boothId);
	
	public Long getHamletCountByBooth(Long userId,Long boothId);

	public Long getLocalAreaCountByBooth(Long userId,Long boothId);
	
	public Long getCadreCountByBooth(Long userId,Long boothId);
	
	public Long getInfluencingPeopleCountByBooth(Long userId,Long boothId);
	
	public List<Object[]> getsurveyDetailsInfoByConstituencyId(Long constituencyId,Long surveyUsertypeId);
	
	public List<Object[]> getBoothDetailsByConstituencyId(Long constituencyId);
	
	public List<Object[]> getsurveyDetailsInfoByboothId(Long boothId,Long surveyUsertypeId);
	
	public List<Object[]> getVoterDetailsByBoothId(Long boothId, List<Long> assignUsers);
	
	public List<Long> getSurveyStartedConstituenciesDetails();
	
	public List<Object[]> getSurveyStartedConstituencyInfo();

	public List<Object[]> getSurveyBooths();

}
