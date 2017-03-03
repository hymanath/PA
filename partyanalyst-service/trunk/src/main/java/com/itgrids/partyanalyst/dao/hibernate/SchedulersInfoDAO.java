package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISchedulersInfoDAO;
import com.itgrids.partyanalyst.model.SchedulersInfo;

public class SchedulersInfoDAO extends GenericDaoHibernate<SchedulersInfo, Long> implements
ISchedulersInfoDAO {
	
	public SchedulersInfoDAO(){
		super(SchedulersInfo.class);
	}

}
