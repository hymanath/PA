package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ILocalityDAO;
import com.itgrids.partyanalyst.model.Locality;

public class LocalityDAO extends GenericDaoHibernate<Locality, Long> implements ILocalityDAO{

	public LocalityDAO() {
		super(Locality.class);
		
	}
	
	
	public List<Object[]> getAllLocalitiesForHamlet(Long userId , Long hamletId)
	{
		
		Query query = getSession().createQuery("select model.localityId , model.name from Locality model " +
				"where model.hamlet.hamletId = :hamletId and model.user.userId = :userId");
		
		query.setParameter("hamletId", hamletId);
		query.setParameter("userId", userId);
		
		return query.list();
	}

}
