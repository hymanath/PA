package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyDetailsInfo;

public interface ISurveyDetailsInfoDAO  extends GenericDao<SurveyDetailsInfo, Long>
{
	public List<Object[]> getDayWisereportDetailsByConstituencyId(Long constituencyId,Date startDate,Date endDate);
	public List<Object[]> getBoothWiseUserSamplesDetailsByDates(Long userId,Date startDate,Date endDate);


}
