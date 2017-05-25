package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ITdpResolutionMailDAO;
import com.itgrids.model.TdpResolutionMail;

@Repository
public class TdpResolutionMailDAO extends GenericDaoHibernate<TdpResolutionMail,Long> implements ITdpResolutionMailDAO{
	
	public TdpResolutionMailDAO()
	{
		super(TdpResolutionMail.class);
	}

}
