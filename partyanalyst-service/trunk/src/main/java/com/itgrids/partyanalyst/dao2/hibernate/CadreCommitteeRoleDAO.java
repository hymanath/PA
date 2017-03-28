package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreCommitteeRoleDAO;
import com.itgrids.partyanalyst.model.CadreCommitteeRole;

public class CadreCommitteeRoleDAO extends GenericDaoHibernate<CadreCommitteeRole, Long> implements ICadreCommitteeRoleDAO{

	public CadreCommitteeRoleDAO() 
	{
		super(CadreCommitteeRole.class);
	}

	
	@SuppressWarnings("unchecked")
	public List<Long> getCadreCommitteRoleIdBySelection(Long cadreCommitteeLevelId,Long cadreRoleId,Long cadreCommiteeId)
	{
		Query query = getSession().createQuery("select model.cadreCommitteeRoleId from CadreCommitteeRole model where model.cadreCommitteeLevelId =:cadreCommitteeLevelId and model.cadreCommiteeId =:cadreCommiteeId and model.cadreRolesId = :cadreRoleId");
		query.setParameter("cadreCommitteeLevelId", cadreCommitteeLevelId);
		query.setParameter("cadreRoleId", cadreRoleId);
		query.setParameter("cadreCommiteeId", cadreCommiteeId);
		return query.list();
	}
	
	public List<Object[]> getCommitteeRolesByLevelId(Long cadreCommitteeLevelId)
	{
		Query query = getSession().createQuery("select DISTINCT model.cadreCommittee.cadreCommitteeId, model.cadreCommittee.name from CadreCommitteeRole model " +
				" where model.cadreCommitteeLevelId = :cadreCommitteeLevelId order by model.cadreCommittee.name ");
		query.setParameter("cadreCommitteeLevelId", cadreCommitteeLevelId);
		return query.list();
		
	}
	
	public List<Object[]> getCommitteeRolesByLevelIdAndCommitteeId(Long cadreCommitteeLevelId,Long cadreCommiteeId)
	{
		Query query = getSession().createQuery("select DISTINCT  model.cadreRoles.cadreRolesId,model.cadreRoles.role  from CadreCommitteeRole model " +
				" where model.cadreCommitteeLevelId = :cadreCommitteeLevelId and model.cadreCommittee.cadreCommitteeId = :cadreCommiteeId order by model.cadreRoles.role ");
		
		query.setParameter("cadreCommitteeLevelId", cadreCommitteeLevelId);
		query.setParameter("cadreCommiteeId", cadreCommiteeId);
		return query.list();
	}
}
