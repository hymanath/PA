package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;


import com.itgrids.partyanalyst.model.LeaderOccasion;
import com.itgrids.partyanalyst.model.LeaderOccasionWishDetails;

public interface ILeaderOccasionDAO extends GenericDao<LeaderOccasion, Long>{
	

	public List<Object[]> getLeaderOccasionDetailsForTaday(String searchTypeStr,Long betweenDatesCount,Long occastionTypeId);
	public Long getLeaderOccasionCountDetailsForTaday(String searchTypeStr,Long betweenDatesCount,Long occastionTypeId);
	public List<Object[]> getleaderOccasionWishDetails();
}
