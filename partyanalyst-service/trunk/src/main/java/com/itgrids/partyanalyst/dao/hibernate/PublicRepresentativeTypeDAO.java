package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPublicRepresentativeTypeDAO;
import com.itgrids.partyanalyst.model.PublicRepresentativeType;

public class PublicRepresentativeTypeDAO extends GenericDaoHibernate<PublicRepresentativeType, Long> implements IPublicRepresentativeTypeDAO{

	public PublicRepresentativeTypeDAO() {
		super(PublicRepresentativeType.class);
	}

	public List<Object[]> getAllPublicRepresentativeList(){
		Query query = getSession().createQuery("select model.publicRepresentativeTypeId," +
										" model.type" +
										" from PublicRepresentativeType model" +
										" order by model.orderNo");
		
		return query.list();
	}
}
