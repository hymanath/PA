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
}
