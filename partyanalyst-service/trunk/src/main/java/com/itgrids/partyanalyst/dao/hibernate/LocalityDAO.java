package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ILocalityDAO;
import com.itgrids.partyanalyst.model.Locality;
import com.itgrids.partyanalyst.utils.IConstants;

public class LocalityDAO extends GenericDaoHibernate<Locality, Long> implements ILocalityDAO{

	public LocalityDAO() {
		super(Locality.class);
		
	}
	
	
	public List<Object[]> getAllLocalitiesForHamlet(Long userId , Long id ,String type ,String condition )
	{
		StringBuilder sb = new StringBuilder("select model.localityId , model.name from Locality model where ");
		/*if(type.equalsIgnoreCase(IConstants.HAMLET))
		sb.append("model.hamlet.hamletId = :id ");
		else if(type.equalsIgnoreCase(IConstants.CUSTOMWARD))
		sb.append("model.ward.constituencyId = :id ");	*/
		sb.append(condition);
		
		sb.append(" and model.user.userId = :userId");
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("id", id);
		query.setParameter("userId", userId);
		
		return query.list();
	}
	public List<Object[]> getAllLocalitiesForHamlet(Long userId , Long id,String type)
	{
		StringBuilder queryObj = new StringBuilder();
		queryObj.append("select model.localityId , model.name from Locality model where model.user.userId = :userId ");
		if(type.equalsIgnoreCase(IConstants.HAMLET))
			queryObj.append(" and model.hamlet.hamletId = :id ");
		else if(type.equalsIgnoreCase(IConstants.CUSTOMWARD))
			queryObj.append(" and model.ward.constituencyId = :id ");
		
		Query query = getSession().createQuery(queryObj.toString());
		query.setParameter("id", id);
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	public List<Object[]> getLocalitiesForWard(Long wardId, Long userId)
	{
		Query query = getSession().createQuery("select model.localityId , model.name from Locality model " +
				" where model.ward.constituencyId = ?  and model.user.userId = ?");
		
		query.setParameter(0, wardId);
		query.setParameter(1, userId);
		
		return query.list();
		
		
	}

}
