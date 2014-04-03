package com.itgrids.partyanalyst.dao.hibernate;

import java.io.Serializable;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IReceiverTypeDAO;
import com.itgrids.partyanalyst.model.ReceiverType;

public class ReceiverTypeDAO extends GenericDaoHibernate<ReceiverType, Serializable> implements IReceiverTypeDAO{

	public ReceiverTypeDAO() {
		super(ReceiverType.class);
		// TODO Auto-generated constructor stub
	}

}
