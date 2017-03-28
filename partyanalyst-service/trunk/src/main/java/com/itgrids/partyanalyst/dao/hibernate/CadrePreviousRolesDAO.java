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
	
	public Integer inActiveCadreRollesDetailsById(List<Long> tdpCadreIdList)
	{
		Query query = getSession().createQuery("update CadrePreviousRoles model set model.isDeleted = 'H' where model.tdpCadreId in (:tdpCadreIdList) and model.isDeleted = 'N' ");
		query.setParameterList("tdpCadreIdList", tdpCadreIdList);
		Integer count = query.executeUpdate();
		
		return count;
	}

	public List<Object[]> getexistingRolesForTdpCadreByTdpCadreId(Long tdpCadreId)
	{
		
		Query query = getSession().createQuery(" select model.cadreCommitteeRole.cadreCommitteeLevelId,model.cadreCommitteeRole.cadreCommiteeId,model.cadreCommitteeRole.cadreRolesId, model.fromDate, model.toDate  from CadrePreviousRoles model  where " +
				"  model.tdpCadreId = :tdpCadreId and model.isDeleted = 'N' order by  model.cadrePreviousRolesId ");
		query.setParameter("tdpCadreId", tdpCadreId);
				
		return query.list();		
	}
	
	public List<Object[]> getexistingRolesForTdpCadreByTdpCadreIdForCommittee(Long tdpCadreId)
	{
		
		Query query = getSession().createQuery(" select model.cadreCommitteeRole.cadreRolesId, date(model.fromDate), date(model.toDate) ,model.cadreCommitteeRole.cadreRoles.role,model.cadreCommitteeRole.cadreCommitteeLevelId,model.cadreCommitteeRole.cadreCommiteeId  from CadrePreviousRoles model  where " +
				"  model.tdpCadreId = :tdpCadreId and model.isDeleted = 'N' order by  model.cadrePreviousRolesId ");
		query.setParameter("tdpCadreId", tdpCadreId);
				
		return query.list();		
	}
}
