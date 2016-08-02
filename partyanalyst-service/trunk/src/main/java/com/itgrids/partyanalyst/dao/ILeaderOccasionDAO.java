package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;


import com.itgrids.partyanalyst.model.LeaderOccasion;
import com.itgrids.partyanalyst.model.LeaderOccasionWishDetails;

public interface ILeaderOccasionDAO extends GenericDao<LeaderOccasion, Long>{
	

	public List<Object[]> getLeaderOccasionDetailsForTaday(String searchTypeStr,Long betweenDatesCount,Long occastionTypeId);
	public Long getTotalBirthDays(Date fromDate,Date toDate);
	public Long getTotalWishedBirthDays(Date fromDate,Date toDate);
	//public List<Long> getLeaderBirthDayWishingDetails(Date fromDate, Date toDate);
	//public List<LeaderOccasionWishDetails> getWishingDetails(Long searchId,String year);
	//public List<Object[]> getLeaderOccasionDetailsForTaday1(List<Integer> months,List<Integer> days,Long occastionTypeId);
}
