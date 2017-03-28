package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHHBoothLeaderDAO;
import com.itgrids.partyanalyst.model.HHBoothLeader;


public class HHBoothLeaderDAO extends GenericDaoHibernate<HHBoothLeader,Long> implements IHHBoothLeaderDAO {
	
	public HHBoothLeaderDAO() {
		super(HHBoothLeader.class);
	}
	
	public List<HHBoothLeader> getAllLeaderModelByBoothId(Long boothId){
		Query qry=getSession().createQuery(" select model from HHBoothLeader model where model.booth.boothId=:boothId ");
		qry.setParameter("boothId", boothId);
		
		return qry.list();
	}
	
	public List getLeaderIdForBoothId(String voterId,Long boothId)
	{
		Query query = getSession().createQuery("select model.hhBoothLeaderId from HHBoothLeader model where model.hhLeader.voterId= :voterId ");
		query.setParameter("voterId", voterId);
		//query.setParameter("boothId", boothId);
		return query.list();
	}
	
	public List<Object[]> getBoothsOfLeader(Long leaderId){
		Query qry=getSession().createQuery(" select model.booth.boothId,model.booth.partNo from HHBoothLeader model where model.hhLeader.id =:leaderId ");
		
		qry.setParameter("leaderId", leaderId);
		return qry.list();
	}
	
	public List<Object[]> getLeadersOfConstituency(Long constituencyId){
		Query qry=getSession().createQuery(" select distinct model.hhLeader.id,model.hhLeader.name,model.hhLeader.voterId from HHBoothLeader model " +
				" where model.constituency.constituencyId =:constituencyId order by model.hhLeader.voterId asc ");
		
		qry.setParameter("constituencyId", constituencyId);
		return qry.list();
	}
	
	public Integer deleteLeaderWithBooths(List<Long> boothIds,Long leaderId){
		Query query = getSession().createQuery("delete from HHBoothLeader model where model.booth.boothId in(:boothIds)" +
				" and model.hhLeader.id =:leaderId");
		query.setParameterList("boothIds", boothIds);
		query.setParameter("leaderId", leaderId);
		return query.executeUpdate();
	}
	
	public List<Object[]> getConstituenciesOfHouseHolds(){
		Query query = getSession().createQuery(" select distinct model.constituency.constituencyId, model.constituency.name from " +
				" HHBoothLeader model " +
				" where model.hhLeader.is_active = 'YES' " +
				" order by model.constituency.name asc");
		return query.list();
	}
	
	
	
}
