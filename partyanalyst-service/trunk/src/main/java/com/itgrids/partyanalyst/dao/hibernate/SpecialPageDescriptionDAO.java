package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISpecialPageDescriptionDAO;
import com.itgrids.partyanalyst.model.SpecialPageDescription;

public class SpecialPageDescriptionDAO extends GenericDaoHibernate<SpecialPageDescription,Long> implements ISpecialPageDescriptionDAO {
	
	public SpecialPageDescriptionDAO()
	{
		super(SpecialPageDescription.class);
	}    
}
