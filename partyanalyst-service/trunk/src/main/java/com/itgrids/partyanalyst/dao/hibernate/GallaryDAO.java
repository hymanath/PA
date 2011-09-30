package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.model.Gallary;

public class GallaryDAO extends GenericDaoHibernate<Gallary, Long> implements IGallaryDAO{
	
	public GallaryDAO(){
		super(Gallary.class);
	}
}
