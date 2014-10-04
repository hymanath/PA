package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadrePreviousRolesDAO;
import com.itgrids.partyanalyst.model.CadrePreviousRoles;

public class CadrePreviousRolesDAO extends GenericDaoHibernate<CadrePreviousRoles , Long> implements ICadrePreviousRolesDAO{

	public CadrePreviousRolesDAO() {
		super(CadrePreviousRoles.class);

	}
	
	public Integer inActiveCadreRollesDetailsById(Long tdpCadreId)
	{
		Query query = getSession().createQuery("update CadrePreviousRoles model set model.isDeleted = 'Y' where model.tdpCadreId = :tdpCadreId");
		query.setParameter("tdpCadreId", tdpCadreId);
		Integer count = query.executeUpdate();
		
		return count;
	}

}
