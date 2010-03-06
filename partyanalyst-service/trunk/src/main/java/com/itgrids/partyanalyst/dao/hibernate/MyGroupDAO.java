package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMyGroupDAO;
import com.itgrids.partyanalyst.model.MyGroup;


public class MyGroupDAO extends GenericDaoHibernate<MyGroup, Long> implements IMyGroupDAO {

	public MyGroupDAO() {
		super(MyGroup.class);

	}
}
