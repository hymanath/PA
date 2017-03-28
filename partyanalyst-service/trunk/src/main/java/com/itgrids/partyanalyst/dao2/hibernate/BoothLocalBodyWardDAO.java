package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothLocalBodyWardDAO;
import com.itgrids.partyanalyst.model.BoothLocalBodyWard;

public class BoothLocalBodyWardDAO extends GenericDaoHibernate<BoothLocalBodyWard, Long> implements IBoothLocalBodyWardDAO{

	public BoothLocalBodyWardDAO(){
		super(BoothLocalBodyWard.class);
	}

	public Integer deleteRecords(List<Long> boothIds, Long wardId) {
		StringBuilder query = new StringBuilder();
		query.append("delete from BoothLocalBodyWard model ");
		query.append("where model.localBodyWard.constituencyId = ?");
		query.append(" and model.booth.boothId in (:boothIds) ");
			
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, wardId);
		queryObject.setParameterList("boothIds", boothIds);	
		
		return queryObject.executeUpdate();
	}
	
	public List<Long> getBoothsForWard(Long wardId,Long publicationId){
		return getHibernateTemplate().find("select model.booth.boothId from BoothLocalBodyWard model where model.localBodyWard.constituencyId = ? and model.booth.publicationDate.publicationDateId ", wardId);
		
	}
	
	public List<Object[]> getBoothsDetailsForWard(Long wardId,Long publicationId){
		return getHibernateTemplate().find("select model.booth.boothId,model.booth.partNo from BoothLocalBodyWard model where model.localBodyWard.constituencyId = ? and model.booth.publicationDate.publicationDateId ", wardId);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getBoothIds()
	{
		return getHibernateTemplate().find(" select distinct model.booth.boothId from BoothLocalBodyWard model ");
	}
}
