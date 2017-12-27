package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IEncWorksDAO;
import com.itgrids.model.EncWorks;

@Repository
public class EncWorksDAO extends GenericDaoHibernate<EncWorks, Long>  implements IEncWorksDAO {

	public EncWorksDAO()
	{
		super(EncWorks.class);
	}

	@Override
	public List<Long> getAllDistinctWorkIds() {
		Query query= getSession().createQuery("select distinct model.workId from EncWorks model ");
		
		return query.list();
	}
	

}
