package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreFamilyDetailsDAO;
import com.itgrids.partyanalyst.model.TdpCadreFamilyDetails;

public class TdpCadreFamilyDetailsDAO extends GenericDaoHibernate<TdpCadreFamilyDetails, Long> implements ITdpCadreFamilyDetailsDAO{

	public TdpCadreFamilyDetailsDAO() {
		super(TdpCadreFamilyDetails.class);
	}

	
	public Integer inActiveCadreFamilyDetailsById(Long tdpCadreId)
	{
		Query query = getSession().createQuery("update TdpCadreFamilyDetails model set model.isDeleted = 'Y' where model.tdpCadreId = :tdpCadreId");
		query.setParameter("tdpCadreId", tdpCadreId);
		Integer count = query.executeUpdate();
		
		return count;
	}
	
}
