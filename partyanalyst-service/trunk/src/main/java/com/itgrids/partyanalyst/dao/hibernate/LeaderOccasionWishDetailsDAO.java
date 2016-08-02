package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ILeaderOccasionWishDetailsDAO;
import com.itgrids.partyanalyst.model.LeaderOccasionWishDetails;
import com.itgrids.partyanalyst.model.OccasionType;

public class LeaderOccasionWishDetailsDAO extends GenericDaoHibernate<LeaderOccasionWishDetails, Long> implements ILeaderOccasionWishDetailsDAO {

	public LeaderOccasionWishDetailsDAO() {
		super(LeaderOccasionWishDetails.class);
		// TODO Auto-generated constructor stub
	}

}
