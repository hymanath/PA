package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IGallaryTypeDAO;
import com.itgrids.partyanalyst.model.GallaryType;

public class GallaryTypeDAO extends GenericDaoHibernate<GallaryType, Long> implements IGallaryTypeDAO{

	public GallaryTypeDAO(){
		super(GallaryType.class); 
	}
}
