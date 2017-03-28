package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreCommitteeLevelDAO;
import com.itgrids.partyanalyst.model.CadreCommitteeLevel;

public class CadreCommitteeLevelDAO extends GenericDaoHibernate<CadreCommitteeLevel, Long> implements ICadreCommitteeLevelDAO{

	public CadreCommitteeLevelDAO()
	{
		super(CadreCommitteeLevel.class);
	}
	
	public List<Object[]> getAllCadreCommitteeLevels(){
		Query query = getSession().createQuery(" select model.cadreCommitteeLevel," +
				" model.level from CadreCommitteeLevel model " +
				" where model.isDeleted='N' ");
		
		return query.list();
	}

}
