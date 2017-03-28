package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreCommitteeDAO;
import com.itgrids.partyanalyst.model.CadreCommittee;

public class CadreCommitteeDAO extends GenericDaoHibernate<CadreCommittee, Long> implements ICadreCommitteeDAO{

	public CadreCommitteeDAO() 
	{
		super(CadreCommittee.class);
	}
	
	public List<Object[]> getAllCadreCommittees(){
		Query query = getSession().createQuery(" select model.cadreCommitteeId," +
				" model.name from CadreCommittee model ");
		
		return query.list();
	}

}
