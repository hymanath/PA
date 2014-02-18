package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHouseHoldVoterDAO;
import com.itgrids.partyanalyst.model.HouseHoldVoter;


public class HouseHoldVoterDAO extends GenericDaoHibernate<HouseHoldVoter,Long> implements IHouseHoldVoterDAO {
	
	public HouseHoldVoterDAO() {
		super(HouseHoldVoter.class);
	}
	
	public List<Object[]> getVoterRelationsByVoterIds(List<Long> voterIds){
		Query qry=getSession().createQuery(" select model.voterId,model.voterFamilyRelationId from HouseHoldVoter model" +
				" where model.voterId in (:voterIds)");
		qry.setParameterList("voterIds", voterIds);
		return qry.list();
	}
	
}
