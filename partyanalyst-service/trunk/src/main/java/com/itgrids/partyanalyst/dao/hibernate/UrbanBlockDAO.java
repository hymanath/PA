package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUrbanBlockDAO;
import com.itgrids.partyanalyst.model.UrbanBlock;

public class UrbanBlockDAO extends GenericDaoHibernate<UrbanBlock, Long> implements IUrbanBlockDAO{

	public UrbanBlockDAO() {
		super(UrbanBlock.class);
		
	}

	public List<Object[]> getUrbanBlocksForLocality(Long localityId){
		Query query = getSession().createQuery("select distinct model.urbanBlockId," +
											" model.blockName" +
											" from UrbanBlock model" +
											" where model.urbanLocality.urbanLocalityId = :localityId");
		query.setParameter("localityId", localityId);
		return query.list();
	}
}
