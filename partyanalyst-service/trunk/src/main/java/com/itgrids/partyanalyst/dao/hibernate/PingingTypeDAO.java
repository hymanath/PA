package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPingingTypeDAO;
import com.itgrids.partyanalyst.model.PingingType;

public class PingingTypeDAO extends GenericDaoHibernate<PingingType, Long> implements IPingingTypeDAO{

	public PingingTypeDAO() {
		super(PingingType.class);
		
	}
	
	public List<Object> getPingingTypeIdByType(String type)
	{
		return getHibernateTemplate().find("select model.pingingTypeId from PingingType model where model.type = ?",type);
	}
	
}
