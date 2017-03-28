package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ILeaderOccasionWishDetailsDAO;
import com.itgrids.partyanalyst.model.LeaderOccasionWishDetails;
import com.itgrids.partyanalyst.model.OccasionType;

public class LeaderOccasionWishDetailsDAO extends GenericDaoHibernate<LeaderOccasionWishDetails, Long> implements ILeaderOccasionWishDetailsDAO {

	public LeaderOccasionWishDetailsDAO() {
		super(LeaderOccasionWishDetails.class);
	}
	
public List<Object[]> getTotalDaysCountsForWishedCount(List<Long> totalIds,String year,Long userId) {
		
	Query query = getSession().createQuery( "select distinct model.leaderOccasionId,model.leaderOccasion.tdpCadreId from  LeaderOccasionWishDetails model " +
			" where model.isDeleted='false' " +
			" and model.leaderOccasion.tdpCadreId in (:totalIds) and model.year=:year and model.wishedBy=:userId " );
		
		query.setParameterList("totalIds", totalIds);
		query.setParameter("year", year);
		query.setParameter("userId", userId);
		return  query.list();
	}
public LeaderOccasionWishDetails getLeaderOccassiobnWishngDetails(Long searchId,String year,Long userId){
	
	Query query = getSession().createQuery( "select model from  LeaderOccasionWishDetails model " +
			" where model.isDeleted='false' " +
			" and model.leaderOccasionId=:searchId and model.year=:year and model.wishedBy=:userId " );
		query.setParameter("searchId", searchId);
		query.setParameter("year", year);
		query.setParameter("userId", userId);
		return (LeaderOccasionWishDetails)query.uniqueResult();
	}
}
