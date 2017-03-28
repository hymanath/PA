package com.itgrids.partyanalyst.dao.hibernate;

import java.io.Serializable;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISmsSentTypeDAO;
import com.itgrids.partyanalyst.model.SmsSentType;

public class SmsSentTypeDAO extends GenericDaoHibernate<SmsSentType, Serializable> implements ISmsSentTypeDAO{

	public SmsSentTypeDAO() {
		super(SmsSentType.class);
		// TODO Auto-generated constructor stub
	}

}
