package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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

	public List<Object[]> getexistingRolesForTdpCadreByTdpCadreId(Long tdpCadreId)
	{
		
		Query query = getSession().createQuery(" select model.cadreLevelId, model.partyDesignationId, model.fromDate, model.toDate  from CadrePreviousRoles model  where " +
				"  model.tdpCadreId = :tdpCadreId and model.isDeleted = 'N' order by  model.cadrePreviousRolesId ");
		query.setParameter("tdpCadreId", tdpCadreId);
				
		return query.list();		
	}
}
