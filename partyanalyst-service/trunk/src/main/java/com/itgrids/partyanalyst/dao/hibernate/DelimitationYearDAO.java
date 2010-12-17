package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDelimitationYearDAO;
import com.itgrids.partyanalyst.model.DelimitationYear;

public class DelimitationYearDAO extends GenericDaoHibernate<DelimitationYear, Long> implements IDelimitationYearDAO{

	public DelimitationYearDAO()
	{
		super(DelimitationYear.class);
	}

	@SuppressWarnings("unchecked")
	public List<DelimitationYear> findByDelimitationYear(String year) {
		return getHibernateTemplate().find("from DelimitationYear model where model.year = ?",year);
	}
}
