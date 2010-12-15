package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDelimitationYearDAO;
import com.itgrids.partyanalyst.model.DelimitationYear;

public class DelimitationYearDAO extends GenericDaoHibernate<DelimitationYear, Long> implements IDelimitationYearDAO{

	public DelimitationYearDAO()
	{
		super(DelimitationYear.class);
	}
}
