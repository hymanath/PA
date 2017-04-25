package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUrbanLocalityDAO;
import com.itgrids.partyanalyst.model.UrbanLocality;

public class UrbanLocalityDAO extends GenericDaoHibernate<UrbanLocality, Long> implements IUrbanLocalityDAO{

	public UrbanLocalityDAO() {
		super(UrbanLocality.class);
		
	}

	public List<Object[]> getUrbanLocalitiesForMuncipality(Long lebId){
		Query query = getSession().createQuery("select distinct model.urbanLocalityId," +
											" model.areaName" +
											" from UrbanLocality model" +
											" where model.localElectionBody.localElectionBodyId = :lebId");
		query.setParameter("lebId", lebId);
		return query.list();
	}
}
