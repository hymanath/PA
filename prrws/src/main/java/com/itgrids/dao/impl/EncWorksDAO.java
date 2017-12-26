package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IEncWorksDAO;
import com.itgrids.model.EncWorks;

@Repository
public class EncWorksDAO extends GenericDaoHibernate<EncWorks, Long>  implements IEncWorksDAO {

	public EncWorksDAO()
	{
		super(EncWorks.class);
	}
	

}
