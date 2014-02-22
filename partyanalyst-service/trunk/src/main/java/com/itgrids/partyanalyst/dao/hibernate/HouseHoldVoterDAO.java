package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHouseHoldVoterDAO;
import com.itgrids.partyanalyst.model.HouseHoldVoter;
import com.itgrids.partyanalyst.utils.IConstants;


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
	
	public List<Object[]> getHouseHoldIdOfFamilyHeadForVoter(String houseNo){
		Query qry=getSession().createQuery(" select model.voterId,model.voterFamilyRelationId,model.houseHoldId from HouseHoldVoter model" +
				" where model.houseHolds.houseNo=:houseNo ");
		qry.setParameter("houseNo", houseNo);
		
		return qry.list();
	}
	
	public List<Object[]> getHouseHoldIdOfVoter(String houseNo){
		Query qry=getSession().createQuery(" select model.houseHoldId from HouseHoldVoter model" +
				" where model.houseHolds.houseNo=:houseNo ");
		qry.setParameter("houseNo", houseNo);
		
		return qry.list();
	}
	
	public List<HouseHoldVoter> getHouseHoldsVoterdDetailsByHouseHoldId(Long houseHoldsId)
	{
		Query query = getSession().createQuery("select HHV from HouseHoldVoter " +
				"HHV where HHV.houseHoldId = :houseHoldsId and HHV.isDelete = :deleteStatus");	
		
		query.setParameter("houseHoldsId", houseHoldsId);
		query.setParameter("deleteStatus", IConstants.FALSE);
		return query.list();
		
	}
	
	public Long getHouseHoldIdForVoter(Long voterId){
		Query query = getSession().createQuery(" select model.houseHoldId from HouseHoldVoter model " +
				" where model.voter.voterId = :voterId");
		
		query.setParameter("voterId", voterId);
		return (Long) query.uniqueResult();
	}
	
}
