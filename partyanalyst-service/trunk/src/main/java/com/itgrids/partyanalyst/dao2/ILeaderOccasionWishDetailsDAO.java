package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.LeaderOccasionWishDetails;
import com.itgrids.partyanalyst.model.OccasionType;

public interface ILeaderOccasionWishDetailsDAO extends GenericDao< LeaderOccasionWishDetails, Long>{
	public List<Object[]> getTotalDaysCountsForWishedCount(List<Long> totalIds,String year,Long userId) ;
	public LeaderOccasionWishDetails getLeaderOccassiobnWishngDetails(Long searchId,String year,Long userId);

}
