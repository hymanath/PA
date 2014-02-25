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
	
	public List getLeaderIdForBoothId(String name,Long boothId)
	{
		Query query = getSession().createQuery("select model.hhBoothLeaderId from HHBoothLeader model where model.hhLeader.name= :name and model.boothId= :boothId");
		query.setParameter("name", name);
		query.setParameter("boothId", boothId);
		return query.list();
		
	}
	
	
}
