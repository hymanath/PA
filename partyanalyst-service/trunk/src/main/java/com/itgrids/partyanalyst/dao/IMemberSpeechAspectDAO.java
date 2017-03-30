package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.LeaderOccasion;
import com.itgrids.partyanalyst.model.MemberSpeechAspect;

public interface IMemberSpeechAspectDAO extends GenericDao<MemberSpeechAspect, Long>{
	
	public List<Object[]> getNoOfDaysForSession(Long termId,String sesYear,List<Long> sessionIds,Date startDate,Date endDate);
	public List<Object[]> getDayWisePartyWiseCount(Long termId,String sesYear,List<Long> sessionIds,Date startDate,Date endDate);
	public List<Object[]> getDayWiseCountDetails(Long admHsSessDayId);

}
